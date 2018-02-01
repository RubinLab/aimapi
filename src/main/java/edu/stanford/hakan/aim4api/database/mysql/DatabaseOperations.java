/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.database.mysql;

import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.Enumerations.AimVersion;
import edu.stanford.hakan.aim4api.database.exist.ExistManager;
import edu.stanford.hakan.aim4api.database.exist.ExistResponderThread;
import edu.stanford.hakan.aim4api.utility.Globals;
import edu.stanford.hakan.aim4api.utility.XML;
import edu.stanford.hakan.aim4api.utility.dotnet.StreamWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hakan
 */
public class DatabaseOperations {

    private Statement statement = null;
    private String existServerURL = "";
    private String existNameSpace = "";
    private String existCollectionName = "";
    private String existUserName = "";
    private String existUserPassword = "";

    public DatabaseOperations(Connection mySqlConnection, String existServerURL,
            String existNameSpace, String existCollectionName, String existUserName, String existUserPassword) throws SQLException {
        this.existServerURL = existServerURL;
        this.existNameSpace = existNameSpace;
        this.existCollectionName = existCollectionName;
        this.existUserName = existUserName;
        this.existUserPassword = existUserPassword;
        this.statement = mySqlConnection.createStatement();
        createAnnotationsTable();
    }

    private void createAnnotationsTable() throws SQLException {
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS `epaddb`.`annotations` (\n"
                + "  `UserLoginName` VARCHAR(255) NOT NULL,\n"
                + "  `PatientID` VARCHAR(255) NOT NULL,\n"
                + "  `StudyUID` VARCHAR(255) NOT NULL,\n"
                + "  `SeriesUID` VARCHAR(255) NOT NULL,\n"
                + "  `ImageUID` VARCHAR(255) NOT NULL,\n"
                + "  `FrameID` INT NOT NULL,\n"
                + "  `AnnotationUID` VARCHAR(255) NOT NULL,\n"
                + "  PRIMARY KEY (`AnnotationUID`));";
        this.statement.executeUpdate(sqlCreateTable);
    }

    public void refreshTheAnnotationTable(AimVersion aimVersion) throws AimException, IOException, SQLException {

        String query = " declare default element namespace '" + this.existNameSpace + "'; ";
        if (aimVersion == AimVersion.AIMv4_0 || aimVersion == AimVersion.AIMv4_2) {
            query += " for $iac in collection('" + this.existCollectionName + "')/ImageAnnotationCollection ";
            query += " return ($iac/uniqueIdentifier, $iac/person/id, $iac/user/loginName, $iac/imageAnnotations/ImageAnnotation/markupEntityCollection, $iac/imageAnnotations/ImageAnnotation/imageReferenceEntityCollection) ";
        } else if (aimVersion == AimVersion.AIMv3_0 || aimVersion == AimVersion.AIMv3_0_1 || aimVersion == AimVersion.AIMv3_0_2) {
            query += " for $ia in collection('" + this.existCollectionName + "')/ImageAnnotation ";
            query += " return (string($ia/@uniqueIdentifier), string($ia/person/Person/@id), string($ia/user/User/@loginName), $ia/imageReferenceCollection/ImageReference/imageStudy/ImageStudy) ";
        } else {
            throw new AimException("Error: refreshTheAnnotationTable, the aimVersion is not correct.");
        }

        int totalCount = 0;
        int totalRetrieved = 0;
        int pageCount = 10000;
        String serverResponse = "";
        int startIndex = 1;
        StringBuilder sb = new StringBuilder();

        serverResponse = ExistManager.getXMLStringFromExistWithStartIndexCount(this.existServerURL, query, this.existUserName, this.existUserPassword, 1, 1);
        Document doc = XML.getDocumentFromString(serverResponse);
        totalCount = ExistManager.getHitsCountFromDocument(doc);

        if (totalCount > pageCount) {
            pageCount = totalCount / 3;
        }

        List<ExistResponderThread> listThreads = new ArrayList<>();
        while (true) {
            listThreads.add(new ExistResponderThread(this.existServerURL + "~" + query + "~" + this.existUserName + "~" + this.existUserPassword + "~" + startIndex + "~" + pageCount));
            totalRetrieved = startIndex + pageCount - 1;
            if (totalRetrieved >= totalCount) {
                break;
            }
            startIndex = startIndex + pageCount;
        }

        for (ExistResponderThread thread : listThreads) {
            thread.start();
        }

        boolean allOK = false;
        while (!allOK) {
            allOK = true;
            for (ExistResponderThread thread : listThreads) {
                if (!thread.isFinished()) {
                    allOK = false;
                }
            }
        }
        for (ExistResponderThread thread : listThreads) {
            serverResponse = thread.getRespond();
            serverResponse = serverResponse.replace(serverResponse.substring(0, serverResponse.indexOf(">") + 1), "");
            serverResponse = serverResponse.replace("</exist:result>", "");
            serverResponse = serverResponse.replace("exist:value", "exist");
            serverResponse = serverResponse.replace("exist >", "exist>");
            serverResponse = serverResponse.replace("<exist >", "<exist>");
            serverResponse = serverResponse.replace("exist:type=\"xs:string\"", "");
            sb.append(serverResponse);
        }

        doc = XML.getDocumentFromString("<results>" + sb.toString() + "</results>");

        
        Node node = doc.getFirstChild();
        boolean uidOK = false;
        boolean markupOK = false;
        boolean irefOK = false;
        boolean patOK = false;
        boolean userOK = false;

        String userLoginName = "";
        String patientID = "";
        String annotationID = "";
        String imageID = "";
        String frameID = "0";
        String studyID = "";
        String seriesID = "";

        this.statement.executeUpdate("DELETE FROM annotations");

        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if (aimVersion == AimVersion.AIMv4_0 || aimVersion == AimVersion.AIMv4_2) {

                if ("uniqueIdentifier".equals(currentNode.getNodeName())) {
                    annotationID = currentNode.getAttributes().getNamedItem("root").getNodeValue();
                    uidOK = true;
                } else if ("id".equals(currentNode.getNodeName())) {
                    patientID = currentNode.getAttributes().getNamedItem("value").getNodeValue();
                    patOK = true;
                } else if ("loginName".equals(currentNode.getNodeName())) {
                    userLoginName = currentNode.getAttributes().getNamedItem("value").getNodeValue();
                    userOK = true;
                } else if ("markupEntityCollection".equals(currentNode.getNodeName())) {
                    NodeList listMarkupEntityCollection = currentNode.getChildNodes();
                    for (int j = 0; j < listMarkupEntityCollection.getLength(); j++) {
                        Node nodeMarkupEntity = listMarkupEntityCollection.item(j);
                        if ("MarkupEntity".equals(nodeMarkupEntity.getNodeName())) {
                            NodeList listMarkupChilds = nodeMarkupEntity.getChildNodes();
                            for (int k = 0; k < listMarkupChilds.getLength(); k++) {
                                Node nodeMarkupEntityChild = listMarkupChilds.item(k);
                                if ("imageReferenceUid".equals(nodeMarkupEntityChild.getNodeName())) {
                                    imageID = nodeMarkupEntityChild.getAttributes().getNamedItem("root").getNodeValue();
                                } else if ("referencedFrameNumber".equals(nodeMarkupEntityChild.getNodeName())) {
                                    frameID = nodeMarkupEntityChild.getAttributes().getNamedItem("value").getNodeValue();
                                }
                            }
                        }
                    }
                    markupOK = true;
                } else if ("imageReferenceEntityCollection".equals(currentNode.getNodeName())) {
                    NodeList listImageReferenceEntityCollection = currentNode.getChildNodes();
                    for (int j = 0; j < listImageReferenceEntityCollection.getLength(); j++) {
                        Node nodeImageReferenceEntity = listImageReferenceEntityCollection.item(j);
                        NodeList listChildImageReferenceEntity = nodeImageReferenceEntity.getChildNodes();
                        for (int k = 0; k < listChildImageReferenceEntity.getLength(); k++) {
                            Node nodeImageStudy = listChildImageReferenceEntity.item(k);
                            if ("imageStudy".equals(nodeImageStudy.getNodeName())) {
                                NodeList listChildImageStudy = nodeImageStudy.getChildNodes();
                                for (int l = 0; l < listChildImageStudy.getLength(); l++) {
                                    Node nodeImageSeries = listChildImageStudy.item(l);
                                    if ("instanceUid".equals(nodeImageSeries.getNodeName())) {
                                        studyID = nodeImageSeries.getAttributes().getNamedItem("root").getNodeValue();
                                    } else if ("imageSeries".equals(nodeImageSeries.getNodeName())) {
                                        NodeList listChildImageSeries = nodeImageSeries.getChildNodes();
                                        for (int m = 0; m < listChildImageSeries.getLength(); m++) {
                                            Node nodeChildImageSeries = listChildImageSeries.item(m);
                                            if ("instanceUid".equals(nodeChildImageSeries.getNodeName())) {
                                                seriesID = nodeChildImageSeries.getAttributes().getNamedItem("root").getNodeValue();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    irefOK = true;
                }
            } else {
                if (!uidOK && "exist".equals(currentNode.getNodeName())) {
                    annotationID = currentNode.getTextContent();
                    if(annotationID.equals("e1lu3gachgs31rjsxs81bdllsjwijq73dtm4y5jr"))
                        annotationID = "e1lu3gachgs31rjsxs81bdllsjwijq73dtm4y5jr";
                    uidOK = true;
                } else if (!patOK && uidOK && "exist".equals(currentNode.getNodeName())) {
                    patientID = currentNode.getTextContent();
                    patOK = true;
                } else if (!userOK && patOK && uidOK && "exist".equals(currentNode.getNodeName())) {
                    userLoginName = currentNode.getTextContent();
                    userOK = true;

                } else if (userOK && patOK && uidOK && "ImageStudy".equals(currentNode.getNodeName())) {
                    studyID = currentNode.getAttributes().getNamedItem("instanceUID").getNodeValue();
                    NodeList childsImageStudy = currentNode.getChildNodes();
                    for (int j = 0; j < childsImageStudy.getLength(); j++) {
                        Node childImageStudy = childsImageStudy.item(j);
                        if ("imageSeries".equals(childImageStudy.getNodeName())) {
                            NodeList childsimageSeries = childImageStudy.getChildNodes();
                            for (int k = 0; k < childsimageSeries.getLength(); k++) {
                                Node childimageSeries = childsimageSeries.item(k);
                                if ("ImageSeries".equals(childimageSeries.getNodeName())) {
                                    seriesID = childimageSeries.getAttributes().getNamedItem("instanceUID").getNodeValue();
                                    NodeList childsImageSeries = childimageSeries.getChildNodes();
                                    for (int l = 0; l < childsImageSeries.getLength(); l++) {
                                        Node childImageSeries = childsImageSeries.item(l);
                                        if ("imageCollection".equals(childImageSeries.getNodeName())) {
                                            NodeList childsimageCollection = childImageSeries.getChildNodes();
                                            for (int m = 0; m < childsimageCollection.getLength(); m++) {
                                                Node childimageCollection = childsimageCollection.item(m);
                                                if ("Image".equals(childimageCollection.getNodeName())) {
                                                    imageID = childimageCollection.getAttributes().getNamedItem("sopInstanceUID").getNodeValue();
                                                    markupOK = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (((aimVersion == AimVersion.AIMv4_0 || aimVersion == AimVersion.AIMv4_2) && uidOK && patOK && markupOK && irefOK && userOK) || (aimVersion != AimVersion.AIMv4_0 && uidOK && patOK && userOK && markupOK)) {
                    uidOK = false;
                    patOK = false;
                    markupOK = false;
                    irefOK = false;
                    userOK = false;

                    this.insert(userLoginName, patientID, studyID, seriesID, imageID, Integer.parseInt(frameID), annotationID);

                    userLoginName = "";
                    annotationID = "";
                    imageID = "";
                    frameID = "0";
                    studyID = "";
                    seriesID = "";
                    patientID = "";
                }
            }
        }
    }

    public void delete(String annotationUID) throws SQLException {
        this.statement.executeUpdate("DELETE FROM annotatons WHERE(AnnotationUID = " + annotationUID + ")");
    }

    public void insert(String userLoginName, String patientID, String studyUID, String seriesUID, String imageUID, int frameID, String annotationUID) throws SQLException {
        if (seriesUID == null) {
            seriesUID = "";
        }
        if (studyUID == null) {
            studyUID = "";
        }
        if (imageUID == null) {
            imageUID = "";
        }
        String sqlInsert = "INSERT INTO annotations VALUES ('" + userLoginName + "', '" + patientID + "', '" + studyUID + "', '" + seriesUID + "', '" + imageUID + "', " + Integer.toString(frameID) + ", '" + annotationUID + "')";
        this.statement.executeUpdate(sqlInsert);
    }

    public void update(String userLoginName, String patientID, String studyUID, String seriesUID, String imageUID, int frameID, String annotationUID) throws SQLException {
        if (seriesUID == null) {
            seriesUID = "";
        }
        if (studyUID == null) {
            studyUID = "";
        }
        if (imageUID == null) {
            imageUID = "";
        }
        String sqlUpdate = "UPDATE annotations SET UserLoginName = '" + userLoginName + "', PatientID = '" + patientID + "', StudyUID = '" + studyUID + "', SeriesUID = '" + seriesUID + "', ImageUID = '" + imageUID + "', FrameID = " + Integer.toString(frameID) + " WHERE(AnnotationID = '" + annotationUID + "')";
        this.statement.executeUpdate(sqlUpdate);
    }

    public ResultSet getAnnotationCountPatientID() throws SQLException {
        String sqlSelect = "SELECT PatientID, COUNT(AnnotationID) FROM annotations GROUP BY PatientID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountPatientID(String userLoginName) throws SQLException {
        String sqlSelect = "SELECT PatientID, COUNT(AnnotationID) FROM annotations WHERE(UserLoginName = '" + userLoginName + "') GROUP BY PatientID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountPatientID(List<String> userLoginNames) throws SQLException {
        String sqlSelect = "SELECT PatientID, COUNT(AnnotationID) FROM annotations WHERE " + getUserNameConditionString(userLoginNames) + " GROUP BY PatientID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountPatientIDStudyUID() throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, COUNT(AnnotationID) FROM annotations GROUP BY PatientID, StudyUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountPatientIDStudyUID(String userLoginName) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, COUNT(AnnotationID) FROM annotations WHERE(UserLoginName = '" + userLoginName + "') GROUP BY PatientID, StudyUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountPatientIDStudyUID(List<String> userLoginNames) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, COUNT(AnnotationID) FROM annotations WHERE " + getUserNameConditionString(userLoginNames) + " GROUP BY PatientID, StudyUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountPatientIDStudyUIDSeriesUID() throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, COUNT(AnnotationID) FROM annotations GROUP BY PatientID, StudyUID, SeriesUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountPatientIDStudyUIDSeriesUID(String userLoginName) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, COUNT(AnnotationID) FROM annotations WHERE(UserLoginName = '" + userLoginName + "') GROUP BY PatientID, StudyUID, SeriesUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountPatientIDStudyUIDSeriesUID(List<String> userLoginNames) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, COUNT(AnnotationID) FROM annotations WHERE " + getUserNameConditionString(userLoginNames) + " GROUP BY PatientID, StudyUID, SeriesUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountPatientIDStudyUIDSeriesUIDImageUID() throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, ImageUID, COUNT(AnnotationID) FROM annotations GROUP BY PatientID, StudyUID, SeriesUID, ImageUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountPatientIDStudyUIDSeriesUIDImageUID(String userLoginName) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, ImageUID, COUNT(AnnotationID) FROM annotations WHERE(UserLoginName = '" + userLoginName + "') GROUP BY PatientID, StudyUID, SeriesUID, ImageUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountPatientIDStudyUIDSeriesUIDImageUID(List<String> userLoginNames) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, ImageUID, COUNT(AnnotationID) FROM annotations WHERE " + getUserNameConditionString(userLoginNames) + " GROUP BY PatientID, StudyUID, SeriesUID, ImageUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountPatientIDStudyUIDSeriesUIDImageUIDFrameID() throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, ImageUID, FrameID, COUNT(AnnotationID) FROM annotations GROUP BY PatientID, StudyUID, SeriesUID, ImageUID, FrameID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountPatientIDStudyUIDSeriesUIDImageUIDFrameID(String userLoginName) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, ImageUID, FrameID, COUNT(AnnotationID) FROM annotations WHERE(UserLoginName = '" + userLoginName + "') GROUP BY PatientID, StudyUID, SeriesUID, ImageUID, FrameID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountPatientIDStudyUIDSeriesUIDImageUIDFrameID(List<String> userLoginNames) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, ImageUID, FrameID, COUNT(AnnotationID) FROM annotations WHERE " + getUserNameConditionString(userLoginNames) + " GROUP BY PatientID, StudyUID, SeriesUID, ImageUID, FrameID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountByPatientID(String patientID) throws SQLException {
        String sqlSelect = "SELECT PatientID, COUNT(AnnotationID) FROM annotations WHERE(PatientID = '" + patientID + "') GROUP BY PatientID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountByPatientID(String userLoginName, String patientID) throws SQLException {
        String sqlSelect = "SELECT PatientID, COUNT(AnnotationID) FROM annotations WHERE(UserLoginName = '" + userLoginName + "' AND PatientID = '" + patientID + "') GROUP BY PatientID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountByPatientID(List<String> userLoginNames, String patientID) throws SQLException {
        String sqlSelect = "SELECT PatientID, COUNT(AnnotationID) FROM annotations WHERE(" + getUserNameConditionString(userLoginNames) + " AND PatientID = '" + patientID + "') GROUP BY PatientID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountByPatientIDStudyUID(String patientID, String StudyUID) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, COUNT(AnnotationID) FROM annotations WHERE(PatientID = '" + patientID + "' AND StudyUID = '" + StudyUID + "') GROUP BY PatientID, StudyUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountByPatientIDStudyUID(String userLoginName, String patientID, String StudyUID) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, COUNT(AnnotationID) FROM annotations WHERE(UserLoginName = '" + userLoginName + "' AND PatientID = '" + patientID + "' AND StudyUID = '" + StudyUID + "') GROUP BY PatientID, StudyUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountByPatientIDStudyUID(List<String> userLoginNames, String patientID, String StudyUID) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, COUNT(AnnotationID) FROM annotations WHERE(" + getUserNameConditionString(userLoginNames) + " AND PatientID = '" + patientID + "' AND StudyUID = '" + StudyUID + "') GROUP BY PatientID, StudyUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountByPatientIDStudyUIDSeriesUID(String patientID, String studyUID, String seriesUID) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, COUNT(AnnotationID) FROM annotations WHERE(PatientID = '" + patientID + "' AND StudyUID = '" + studyUID + "' AND SeriesUID = '" + seriesUID + "') GROUP BY PatientID, StudyUID, SeriesUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountByPatientIDStudyUIDSeriesUID(String userLoginName, String patientID, String studyUID, String seriesUID) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, COUNT(AnnotationID) FROM annotations WHERE(UserLoginName = '" + userLoginName + "' AND PatientID = '" + patientID + "' AND StudyUID = '" + studyUID + "' AND SeriesUID = '" + seriesUID + "') GROUP BY PatientID, StudyUID, SeriesUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountByPatientIDStudyUIDSeriesUID(List<String> userLoginNames, String patientID, String studyUID, String seriesUID) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, COUNT(AnnotationID) FROM annotations WHERE(" + getUserNameConditionString(userLoginNames) + " AND PatientID = '" + patientID + "' AND StudyUID = '" + studyUID + "' AND SeriesUID = '" + seriesUID + "') GROUP BY PatientID, StudyUID, SeriesUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountByPatientIDStudyUIDSeriesUIDImageUID(String patientID, String studyUID, String seriesUID, String imageUID) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, ImageUID, COUNT(AnnotationID) FROM annotations WHERE(PatientID = '" + patientID + "' AND StudyUID = '" + studyUID + "' AND SeriesUID = '" + seriesUID + "' AND ImageUID = '" + imageUID + "') GROUP BY PatientID, StudyUID, SeriesUID, ImageUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountByPatientIDStudyUIDSeriesUIDImageUID(String userLoginName, String patientID, String studyUID, String seriesUID, String imageUID) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, ImageUID, COUNT(AnnotationID) FROM annotations WHERE(UserLoginName = '" + userLoginName + "' AND PatientID = '" + patientID + "' AND StudyUID = '" + studyUID + "' AND SeriesUID = '" + seriesUID + "' AND ImageUID = '" + imageUID + "') GROUP BY PatientID, StudyUID, SeriesUID, ImageUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountByPatientIDStudyUIDSeriesUIDImageUID(List<String> userLoginNames, String patientID, String studyUID, String seriesUID, String imageUID) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, ImageUID, COUNT(AnnotationID) FROM annotations WHERE(" + getUserNameConditionString(userLoginNames) + " AND PatientID = '" + patientID + "' AND StudyUID = '" + studyUID + "' AND SeriesUID = '" + seriesUID + "' AND ImageUID = '" + imageUID + "') GROUP BY PatientID, StudyUID, SeriesUID, ImageUID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountByPatientIDStudyUIDSeriesUIDImageUIDFrameID(String patientID, String studyUID, String seriesUID, String imageUID, String frameID) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, ImageUID, frameID, COUNT(AnnotationID) FROM annotations WHERE(PatientID = '" + patientID + "' AND StudyUID = '" + studyUID + "' AND SeriesUID = '" + seriesUID + "' AND ImageUID = '" + imageUID + "' AND FrameID = '" + frameID + "') GROUP BY PatientID, StudyUID, SeriesUID, ImageUID, FrameID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountByPatientIDStudyUIDSeriesUIDImageUIDFrameID(String userLoginName, String patientID, String studyUID, String seriesUID, String imageUID, String frameID) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, ImageUID, frameID, COUNT(AnnotationID) FROM annotations WHERE(UserLoginName = '" + userLoginName + "' AND PatientID = '" + patientID + "' AND StudyUID = '" + studyUID + "' AND SeriesUID = '" + seriesUID + "' AND ImageUID = '" + imageUID + "' AND FrameID = '" + frameID + "') GROUP BY PatientID, StudyUID, SeriesUID, ImageUID, FrameID";
        return this.statement.executeQuery(sqlSelect);
    }

    public ResultSet getAnnotationCountByPatientIDStudyUIDSeriesUIDImageUIDFrameID(List<String> userLoginNames, String patientID, String studyUID, String seriesUID, String imageUID, String frameID) throws SQLException {
        String sqlSelect = "SELECT PatientID, StudyUID, SeriesUID, ImageUID, frameID, COUNT(AnnotationID) FROM annotations WHERE(" + getUserNameConditionString(userLoginNames) + " AND PatientID = '" + patientID + "' AND StudyUID = '" + studyUID + "' AND SeriesUID = '" + seriesUID + "' AND ImageUID = '" + imageUID + "' AND FrameID = '" + frameID + "') GROUP BY PatientID, StudyUID, SeriesUID, ImageUID, FrameID";
        return this.statement.executeQuery(sqlSelect);
    }

    private String getUserNameConditionString(List<String> userLoginNames) {
        String conditonUserName = "";
        for (String userLoginName : userLoginNames) {
            conditonUserName += " UserLoginName = '" + userLoginName + "' OR ";
        }
        conditonUserName = "(" + conditonUserName.trim().substring(0, conditonUserName.length() - 2) + ")";
        return conditonUserName;
    }
}
