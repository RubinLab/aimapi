/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.edu.stanford.hakan.aim4api.projects.epad;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import main.java.edu.stanford.hakan.aim4api.base.AimException;
import main.java.edu.stanford.hakan.aim4api.database.exist.ExistManager;
import main.java.edu.stanford.hakan.aim4api.utility.XML;
import org.w3c.dom.Document;

/**
 *
 * @author localadmin
 */
public class WrapperEpad {

    public static List<AnnotateTableRow> getAnnotateTableRows(String ownerName, String subjectName, String studyFrom, String studyTo,
            String seriesModality, String annotationName, String annotationTemplate, String annotationAimIdentifier,
            String serverURL, String nameSpace, String collection, String dbUserName, String dbUserPassword) throws AimException {
        List<AnnotateTableRow> res = new ArrayList<>();

        String whereClause = "";
        String spliter = "|~*~|";
        
        //*** owner
        if (!"".equals(ownerName)) {
            if ("".equals(whereClause.trim())) {
                whereClause = " where ";
            } else {
                whereClause += " and ";
            }
            whereClause += " $x/user/name[contains(lower-case(@value), lower-case(" + ownerName + "))] ";
        }
        
        //*** subject name (patient name)
        if (!"".equals(subjectName)) {
            if ("".equals(whereClause.trim())) {
                whereClause = " where ";
            } else {
                whereClause += " and ";
            }
            whereClause += " $x/person/name[contains(lower-case(@value), lower-case(" + subjectName + "))] ";
        }      
        
        //*** study from
        if (!"".equals(studyFrom)) {
            if ("".equals(whereClause.trim())) {
                whereClause = " where ";
            } else {
                whereClause += " and ";
            }
            whereClause += " $x/imageAnnotations/ImageAnnotation/imageReferenceEntityCollection/ImageReferenceEntity/imageStudy/startDate[fn:dateTime(xs:date(@value), xs:time('00:00:00')) gt xs:dateTime('" + studyFrom + "T00:00:00')] ";
        }
        
        //*** study to
        if (!"".equals(studyTo)) {
            if ("".equals(whereClause.trim())) {
                whereClause = " where ";
            } else {
                whereClause += " and ";
            }
            whereClause += " $x/imageAnnotations/ImageAnnotation/imageReferenceEntityCollection/ImageReferenceEntity/imageStudy/startDate[fn:dateTime(xs:date(@value), xs:time('00:00:00')) lt xs:dateTime('" + studyTo + "T00:00:00')] ";
        }
        
        //**** series modality
         if (!"".equals(seriesModality)) {
            if ("".equals(whereClause.trim())) {
                whereClause = " where ";
            } else {
                whereClause += " and ";
            }
            whereClause += " $x/imageAnnotations/ImageAnnotation/imageReferenceEntityCollection/ImageReferenceEntity/imageStudy/imageSeries/modality/iso:displayName[contains(lower-case(@value), lower-case(" + seriesModality + "))] ";
        }           
         
        //*** annotationName
        if (!"".equals(annotationName)) {
            if ("".equals(whereClause.trim())) {
                whereClause = " where ";
            } else {
                whereClause += " and ";
            }
            whereClause += " $x/imageAnnotations/ImageAnnotation/name[contains(lower-case(@value), lower-case(" + annotationName + "))] ";
        }
        
        //*** annotationTemplate
        if (!"".equals(annotationTemplate)) {
            if ("".equals(whereClause.trim())) {
                whereClause = " where ";
            } else {
                whereClause += " and ";
            }
            whereClause += " $x/imageAnnotations/ImageAnnotation/templateUid[contains(lower-case(@root), lower-case(" + annotationTemplate + "))] ";
        }
        
        //*** annotationAimIdentifier
        if (!"".equals(annotationAimIdentifier)) {
            if ("".equals(whereClause.trim())) {
                whereClause = " where ";
            } else {
                whereClause += " and ";
            }
            whereClause += " $x/imageAnnotations/ImageAnnotation/uniqueIdentifier[contains(lower-case(@root), lower-case(" + annotationAimIdentifier + "))] ";
        }

        String returnClause = " return fn:string-join(("
                + "$x/user/name/@value, " //*** owner
                + "$x/person/name/@value, " //*** patient name
                + "$x/imageAnnotations/ImageAnnotation/imageReferenceEntityCollection/ImageReferenceEntity/imageStudy/startDate/@value, " //*** study date
                + "$x/imageAnnotations/ImageAnnotation/name/@value, " //*** lesion
                + "$x/imageAnnotations/ImageAnnotation/comment/@value, " //*** modality / series / slice
                + "$x/imageAnnotations/ImageAnnotation/templateUid/@root, " //*** template
                + "$x/imageAnnotations/ImageAnnotation/uniqueIdentifier/@root " //*** AIM identifier
                + "), $sep) ";

        String query = "declare default element namespace '" + nameSpace + "'; "
                + "declare namespace iso = 'uri:iso.org:21090'; "
                + "let $sep := '" + spliter + "' "
                + "for $x in collection('" + collection + "')/ImageAnnotationCollection " + whereClause + " " + returnClause;
        
        String serverResponse = ExistManager.getXMLStringFromExist(serverURL, query, dbUserName, dbUserPassword);
        Document serverDoc = XML.getDocumentFromString(serverResponse);
        List<String> tempList =  ExistManager.getExistResultValueListFromDocument(serverDoc);
        for(int i = 0 ; i < tempList.size(); i++)
        {
            String[] resSplit = tempList.get(i).split(Pattern.quote(spliter));
            AnnotateTableRow row = new AnnotateTableRow();
            if(resSplit.length == 7)
            {
                row.setOwner(resSplit[0]);
                row.setPatientName(resSplit[1]);
                row.setStudyDate(resSplit[2]);
                row.setLesion(resSplit[3]);
                row.setModalitySeriesSlice(resSplit[4]);
                row.setTemplate(resSplit[5]);
                row.setAimIdentifier(resSplit[6]);
            }
            res.add(row);
        }
        return res;
    }
}
