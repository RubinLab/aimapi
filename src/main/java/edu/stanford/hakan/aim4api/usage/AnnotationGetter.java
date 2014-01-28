/*
 * Copyright (c) 2011, The Board of Trustees of the Leland Stanford Junior 
 * University, creator Daniel L. Rubin. 
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this 
 * list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package main.java.edu.stanford.hakan.aim4api.usage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import main.java.edu.stanford.hakan.aim4api.aimquery.AimQuery;
import main.java.edu.stanford.hakan.aim4api.base.AimException;
import main.java.edu.stanford.hakan.aim4api.base.ImageAnnotation;
import main.java.edu.stanford.hakan.aim4api.base.ImageAnnotationCollection;
import main.java.edu.stanford.hakan.aim4api.utility.Utility;

/**
 *
 * @author Hakan BULU
 */
public class AnnotationGetter {

    private static String validationResult;

    public static String getValidationResult() {
        return validationResult;
    }

    public static void setValidationResult(String valResult) {
        validationResult = valResult;
    }

    private static String getXMLStringFromExist(String Url, String XQuery, String dbUserName, String dbUserPassword)
            throws AimException {
        try {
            Url = Utility.correctToUrl(Url);
            String requestURL = Url + "rest/";
            String data = "";

            data += "<?xml version='1.0' encoding='UTF-8'?>";
            data += "<query xmlns='http://exist.sourceforge.net/NS/exist' start='1' max='10000'>";
            data += "<text>";
            data += XQuery;
            data += "</text>";
            data += "<properties>";
            data += "<property name='indent' value='yes'/>";
            data += "</properties>";
            data += "</query>";

            URL url = new URL(requestURL);
            URLConnection conn = url.openConnection();

            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).setRequestMethod("POST");
                ((HttpURLConnection) conn).setRequestProperty("Content-Type", "application/xml");
                if (!"".equals(dbUserName.trim()) || !"".equals(dbUserPassword.trim())) {
                    String userPassword = dbUserName + ":" + dbUserPassword;
                    String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
                    ((HttpURLConnection) conn).setRequestProperty("Authorization", "Basic " + encoding);
                }
                ((HttpURLConnection) conn).connect();
            }

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

            // write parameters
            writer.write(data);
            writer.flush();

            // Get the response
            StringBuilder answer = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            writer.close();
            reader.close();

            // Output the response
            return (answer.toString());
        } catch (Exception ex) {
            throw new AimException("AimException: " + ex.getMessage());
        }
    }

    public static String removeImageAnnotationCollectionFromServer(String Url, String nameSpace, String collection,
            String dbUserName, String dbUserPassword, String uniqueIdentifier) throws AimException {
        try {
            if (!AnnotationGetter
                    .isExistInTheServer(Url, nameSpace, collection, dbUserName, dbUserPassword, uniqueIdentifier)) {
                throw new AimException(
                        "AimException: The Image Annotation which you want to remove is not exist. Please check your parameters.");
            }

            String requestURL = Utility.correctToUrl(Url) + "rest/" + collection + "/AIM_" + uniqueIdentifier + ".xml";

            URL url = new URL(requestURL);
            URLConnection conn = url.openConnection();

            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).setRequestMethod("DELETE");
                ((HttpURLConnection) conn).setRequestProperty("Content-Type", "application/xml");
                if (!"".equals(dbUserName.trim()) || !"".equals(dbUserPassword.trim())) {
                    String userPassword = dbUserName + ":" + dbUserPassword;
                    String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
                    ((HttpURLConnection) conn).setRequestProperty("Authorization", "Basic " + encoding);
                }
                ((HttpURLConnection) conn).connect();
            }

            // Get the response
            StringBuilder answer = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            reader.close();

            // Output the response
            return "XML Saving operation is Successful.";
        } catch (AimException ex) {
            throw new AimException("AimException: " + ex.getMessage());
        } catch (IOException ex) {
            throw new AimException("AimException: " + ex.getMessage());
        }
    }

    public static void deleteImageAnnotationFromServer(String serverURL, String namespace, String collection,
            String PathXSD, String dbUserName, String dbUserPassword, String uniqueIdentifier) throws AimException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static Document getDocumentFromString(String xml) throws AimException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xml)));
        } catch (ParserConfigurationException ex) {
            throw new AimException("AimException: " + ex.getMessage());
        } catch (SAXException ex) {
            throw new AimException("AimException: " + ex.getMessage());
        } catch (IOException ex) {
            throw new AimException("AimException: " + ex.getMessage());
        }
    }

    private static List<Node> getNodesFromNodeByName(Node node, String nodeName) {
        List<Node> res = new ArrayList<Node>();
        if (node.getNodeName() == null ? nodeName == null : node.getNodeName().equals(nodeName)) {
            res.add(node);
        }
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            res.addAll(getNodesFromNodeByName(nodeList.item(i), nodeName));
        }
        return res;
    }

    private static List<ImageAnnotationCollection> getImageAnnotationCollectionListFromDocument(Document doc,
            String PathXSD) throws AimException {
        List<ImageAnnotationCollection> res = new ArrayList<ImageAnnotationCollection>();
        try {
            Node firstNode = doc.getFirstChild();
            List<Node> listNodeImageAnnotationCollections = getNodesFromNodeByName(firstNode, "ImageAnnotationCollection");
            for (int i = 0; i < listNodeImageAnnotationCollections.size(); i++) {
                ImageAnnotationCollection imageAnnotationCollection = new ImageAnnotationCollection();
                Node nodeImageAnnotation = listNodeImageAnnotationCollections.get(i);
                imageAnnotationCollection.setXMLNode(nodeImageAnnotation);
                if (!"".equals(PathXSD.trim())) {
                    // *** Validation Step
                    DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
                    Document docValidation = docBuilder.newDocument();
                    Element root = (Element) imageAnnotationCollection.getXMLNode(docValidation);
                    root.setAttribute("xmlns", "gme://caCORE.caCORE/4.4/edu.northwestern.radiology.AIM");
                    root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
                    root.setAttribute("xsi:schemaLocation",
                            "gme://caCORE.caCORE/4.4/edu.northwestern.radiology.AIM AIM_v4_rv44_XML.xsd");
                    root.setAttribute("xmlns:rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
                    docValidation.appendChild(root);
                    boolean valRes = AnnotationValidator.ValidateXML(docValidation, PathXSD);
                    setValidationResult(AnnotationValidator.getValidationResult());
                    if (!valRes) {
                        throw new AimException(getValidationResult());
                    }
                }
                res.add(imageAnnotationCollection);
            }
        } catch (ParserConfigurationException ex) {
            throw new AimException(ex.getMessage());
        } catch (AimException ex) {
            throw new AimException(ex.getMessage());
        } catch (DOMException ex) {
            throw new AimException(ex.getMessage());
        }
        return res; // *** hakan
    }

    public static List<ImageAnnotation> getImageAnnotationsFromImageAnnotationCollectionList(
            List<ImageAnnotationCollection> listImageAnnotationCollection) {
        List<ImageAnnotation> res = new ArrayList<ImageAnnotation>();
        for (int i = 0; i < listImageAnnotationCollection.size(); i++) {
            res.addAll(listImageAnnotationCollection.get(i).getImageAnnotations());
        }
        return res;
    }

    private static List<ImageAnnotationCollection> getImageAnnotationListFromServer(String Url, String XQuery,
            String dbUserName, String dbUserPassword, String PathXSD) throws AimException {
        try {
            String serverResponse = getXMLStringFromExist(Url, XQuery, dbUserName, dbUserPassword);
            Document serverDoc = getDocumentFromString(serverResponse);
            List<ImageAnnotationCollection> res = getImageAnnotationCollectionListFromDocument(serverDoc, PathXSD);
            return res;
        } catch (AimException ex) {
            throw new AimException("AimException: " + ex.getMessage());
        }
    }

    private static void control(String serverURL, String namespace, String collection) throws AimException {
        if (collection == null || "".equals(collection.trim())) {
            throw new AimException("AimException: Collection must be defined");
        }
        if (namespace == null || "".equals(namespace.trim())) {
            throw new AimException("AimException: Namespace must be defined");
        }
        if (serverURL == null || "".equals(serverURL.trim())) {
            throw new AimException("AimException: ServerURL must be defined");
        }
    }

    public static ImageAnnotationCollection getImageAnnotationCollectionFromFile(String PathXML, String PathXSD)
            throws AimException {
        try {
            if (!"".equals(PathXSD.trim())) {
                // *** Validation
                boolean valRes = AnnotationValidator.ValidateXML(PathXML, PathXSD);
                setValidationResult(AnnotationValidator.getValidationResult());
                if (!valRes) {
                    throw new AimException(getValidationResult());
                }
            }
            ImageAnnotationCollection res = new ImageAnnotationCollection();
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(PathXML));
            doc.getDocumentElement().normalize();
            res.setXMLNode(doc.getDocumentElement());
            return res; // *** hakan
        } catch (SAXException ex) {
            throw new AimException(ex.getMessage());
        } catch (IOException ex) {
            throw new AimException(ex.getMessage());
        } catch (ParserConfigurationException ex) {
            throw new AimException(ex.getMessage());
        }
    }

    public static ImageAnnotationCollection getImageAnnotationCollectionFromFile(String PathXML) throws AimException {
        try {
            return getImageAnnotationCollectionFromFile(PathXML, "");
        } catch (AimException ex) {
            throw new AimException(ex.getMessage());
        }
    }

    public static List<ImageAnnotationCollection> getImageAnnotationCollectionsFromString(String text, String PathXSD)
            throws AimException {
        try {
            Document serverDoc = getDocumentFromString(text);
            List<ImageAnnotationCollection> res = getImageAnnotationCollectionListFromDocument(serverDoc, PathXSD);
            return res;
        } catch (AimException ex) {
            throw new AimException("AimException: " + ex.getMessage());
        }
    }

    public static List<ImageAnnotationCollection> getWithAimQuery(String serverURL, String namespace, String dbUserName,
            String dbUserPassword, String aimQuery, String PathXSD) throws AimException {
        if (namespace == null || "".equals(namespace.trim())) {
            throw new AimException("AimException: Namespace must be defined");
        }
        if (serverURL == null || "".equals(serverURL.trim())) {
            throw new AimException("AimException: ServerURL must be defined");
        }
        if (aimQuery == null || "".equals(aimQuery.trim())) {
            throw new AimException("AimException: AimQuery must be defined");
        }
        String XQuery = AimQuery.convertToXQuery(aimQuery, namespace);
        return getImageAnnotationListFromServer(serverURL, XQuery, dbUserName, dbUserPassword, PathXSD);// getDocumentFromServer(serverURL,
        // namespace,
        // XQuery);
    }

    public static boolean isExistInTheServer(String serverURL, String namespace, String collection, String dbUserName,
            String dbUserPassword, String uniqueIdentifier) throws AimException {
        ImageAnnotationCollection temp = getImageAnnotationCollectionByUniqueIdentifier(serverURL, namespace, collection,
                dbUserName, dbUserPassword, uniqueIdentifier);
        if (temp != null) {
            return true;
        }
        return false;
    }

    // *************************************************//
    // ****************** QUERIES **********************//
    // *************************************************//
    // *** ImageAnnotationCollection.uniqueIdentifier Equal
    public static ImageAnnotationCollection getImageAnnotationCollectionByUniqueIdentifier(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String uniqueIdentifier)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (uniqueIdentifier == null || "".equals(uniqueIdentifier.trim())) {
            throw new AimException("AimException: UniqueIdentifier must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotationCollection.uniqueIdentifier.root = '"
                + uniqueIdentifier + "'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        if (listAnno.size() <= 0) {
            return null;
        }
        return listAnno.get(0);
    }

    // *** ImageAnnotationCollection.description Equal
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByDescriptionEqual(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String description)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (description == null || "".equals(description.trim())) {
            throw new AimException("AimException: Name must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotationCollection.description = '" + description + "'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return listAnno;
    }

    // *** ImageAnnotationCollection.description Contains
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByDescriptionContains(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String description)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (description == null || "".equals(description.trim())) {
            throw new AimException("AimException: Name must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotationCollection.description LIKE '%" + description
                + "%'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return listAnno;
    }

    // *** ImageAnnotationCollection.dateTime Equal
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByDateTimeEqual(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String dateTime)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (dateTime == null || "".equals(dateTime.trim())) {
            throw new AimException("AimException: DateTime must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotationCollection.dateTime = '" + dateTime + "'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return listAnno;
    }

    // *** ImageAnnotationCollection.dateTime Contains
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByDateTimeContains(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String dateTime)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (dateTime == null || "".equals(dateTime.trim())) {
            throw new AimException("AimException: DateTime must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotationCollection.dateTime LIKE '%" + dateTime + "%'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return listAnno;
    }

    // *** User.name Equal
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByUserNameEqual(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String userName)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (userName == null || "".equals(userName.trim())) {
            throw new AimException("AimException: UserName must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE user.name = '" + userName + "'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return listAnno;
    }

    // *** User.name Contains
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByUserNameContains(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String userName)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (userName == null || "".equals(userName.trim())) {
            throw new AimException("AimException: UserName must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE user.name LIKE '%" + userName + "%'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return listAnno;
    }

    // *** User.loginName Equal
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByUserLoginNameEqual(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String userLoginName)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (userLoginName == null || "".equals(userLoginName.trim())) {
            throw new AimException("AimException: UserLoginName must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE user.loginName = '" + userLoginName + "'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return listAnno;
    }

    // *** User.loginName Contains
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByUserLoginNameContains(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String userLoginName)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (userLoginName == null || "".equals(userLoginName.trim())) {
            throw new AimException("AimException: UserLoginName must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE user.loginName LIKE '%" + userLoginName + "%'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return listAnno;
    }

    // *** User.roleInTrial Equal
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByUserRoleInTrialEqual(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String userRoleInTrial)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (userRoleInTrial == null || "".equals(userRoleInTrial.trim())) {
            throw new AimException("AimException: UserRoleInTrial must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE user.roleInTrial = '" + userRoleInTrial + "'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return listAnno;
    }

    // *** User.roleInTrial Contains
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByUserRoleInTrialContains(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String userRoleInTrial)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (userRoleInTrial == null || "".equals(userRoleInTrial.trim())) {
            throw new AimException("AimException: UserRoleInTrial must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE user.roleInTrial LIKE '%" + userRoleInTrial + "%'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return listAnno;
    }

    // *** User.numberWithinRoleOfClinicalTrial Equal
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByUserNumberWithinRoleOfClinicalTrialEqual(
            String serverURL, String namespace, String collection, String dbUserName, String dbUserPassword,
            String userNumberWithinRoleOfClinicalTrial) throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (userNumberWithinRoleOfClinicalTrial == null || "".equals(userNumberWithinRoleOfClinicalTrial.trim())) {
            throw new AimException("AimException: UserNumberWithinRoleOfClinicalTrial must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE user.numberWithinRoleOfClinicalTrial = '"
                + userNumberWithinRoleOfClinicalTrial + "'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return listAnno;
    }

    // *** User.numberWithinRoleOfClinicalTrial Contains
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByUserNumberWithinRoleOfClinicalTrialContains(
            String serverURL, String namespace, String collection, String dbUserName, String dbUserPassword,
            String userNumberWithinRoleOfClinicalTrial) throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (userNumberWithinRoleOfClinicalTrial == null || "".equals(userNumberWithinRoleOfClinicalTrial.trim())) {
            throw new AimException("AimException: UserNumberWithinRoleOfClinicalTrial must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE user.numberWithinRoleOfClinicalTrial LIKE '%"
                + userNumberWithinRoleOfClinicalTrial + "%'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return listAnno;
    }

    // *** Person.Name Equal
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByPersonNameEqual(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String PersonName)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (PersonName == null || "".equals(PersonName.trim())) {
            throw new AimException("AimException: PersonName must be defined");
        }
        String aimQL = "SELECT FROM " + collection + " WHERE person.name = '" + PersonName + "'";
        return getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL, "");
    }

    // *** Person.Name Contains
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByPersonNameContains(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String PersonName)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (PersonName == null || "".equals(PersonName.trim())) {
            throw new AimException("AimException: PersonName must be defined");
        }
        String aimQL = "SELECT FROM " + collection + " WHERE person.name LIKE '%" + PersonName + "%'";
        return getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL, "");
    }

    // *** Person.Id Equal
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByPersonIdEqual(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String PersonId)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (PersonId == null || "".equals(PersonId.trim())) {
            throw new AimException("AimException: PersonId must be defined");
        }
        String aimQL = "SELECT FROM " + collection + " WHERE person.id = '" + PersonId + "'";
        return getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL, "");
    }

    // *** Person.Id Contains
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByPersonIdContains(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String PersonId)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (PersonId == null || "".equals(PersonId.trim())) {
            throw new AimException("AimException: PersonId must be defined");
        }
        String aimQL = "SELECT FROM " + collection + " WHERE person.id LIKE '%" + PersonId + "%'";
        return getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL, "");
    }

    // *** ImageAnnotation.uniqueIdentifier Equal
    public static List<ImageAnnotation> getImageAnnotationByUniqueIdentifier(String serverURL, String namespace,
            String collection, String dbUserName, String dbUserPassword, String uniqueIdentifier) throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (uniqueIdentifier == null || "".equals(uniqueIdentifier.trim())) {
            throw new AimException("AimException: UniqueIdentifier must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotation.uniqueIdentifier = '" + uniqueIdentifier + "'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return getImageAnnotationsFromImageAnnotationCollectionList(listAnno);
    }

    // *** ImageAnnotation.dateTime Equal
    public static List<ImageAnnotation> getImageAnnotationByDateTimeEqual(String serverURL, String namespace,
            String collection, String dbUserName, String dbUserPassword, String dateTime) throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (dateTime == null || "".equals(dateTime.trim())) {
            throw new AimException("AimException: DateTime must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotation.dateTime = '" + dateTime + "'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return getImageAnnotationsFromImageAnnotationCollectionList(listAnno);
    }

    // *** ImageAnnotation.dateTime Contains
    public static List<ImageAnnotation> getImageAnnotationByDateTimeContains(String serverURL, String namespace,
            String collection, String dbUserName, String dbUserPassword, String dateTime) throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (dateTime == null || "".equals(dateTime.trim())) {
            throw new AimException("AimException: DateTime must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotation.dateTime LIKE '%" + dateTime + "%'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return getImageAnnotationsFromImageAnnotationCollectionList(listAnno);
    }

    // *** ImageAnnotation.name Equal
    public static List<ImageAnnotation> getImageAnnotationByNameEqual(String serverURL, String namespace,
            String collection, String dbUserName, String dbUserPassword, String name) throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (name == null || "".equals(name.trim())) {
            throw new AimException("AimException: Name must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotation.name = '" + name + "'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return getImageAnnotationsFromImageAnnotationCollectionList(listAnno);
    }

    // *** ImageAnnotation.name Contains
    public static List<ImageAnnotation> getImageAnnotationByNameContains(String serverURL, String namespace,
            String collection, String dbUserName, String dbUserPassword, String name) throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (name == null || "".equals(name.trim())) {
            throw new AimException("AimException: Name must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotation.name LIKE '%" + name + "%'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return getImageAnnotationsFromImageAnnotationCollectionList(listAnno);
    }

    // *** ImageAnnotation.comment Equal
    public static List<ImageAnnotation> getImageAnnotationByCommentEqual(String serverURL, String namespace,
            String collection, String dbUserName, String dbUserPassword, String comment) throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (comment == null || "".equals(comment.trim())) {
            throw new AimException("AimException: Comment must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotation.comment = '" + comment + "'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return getImageAnnotationsFromImageAnnotationCollectionList(listAnno);
    }

    // *** ImageAnnotation.comment Contains
    public static List<ImageAnnotation> getImageAnnotationByCommentContains(String serverURL, String namespace,
            String collection, String dbUserName, String dbUserPassword, String comment) throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (comment == null || "".equals(comment.trim())) {
            throw new AimException("AimException: Comment must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotation.comment LIKE '%" + comment + "%'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return getImageAnnotationsFromImageAnnotationCollectionList(listAnno);
    }

    // *** ImageAnnotation.precedentReferencedAnnotationUid Equal
    public static List<ImageAnnotation> getImageAnnotationByPrecedentReferencedAnnotationUidEqual(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword,
            String precedentReferencedAnnotationUid) throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (precedentReferencedAnnotationUid == null || "".equals(precedentReferencedAnnotationUid.trim())) {
            throw new AimException("AimException: PrecedentReferencedAnnotationUid must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotation.precedentReferencedAnnotationUid = '"
                + precedentReferencedAnnotationUid + "'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return getImageAnnotationsFromImageAnnotationCollectionList(listAnno);
    }

    // *** ImageAnnotation.precedentReferencedAnnotationUid Contains
    public static List<ImageAnnotation> getImageAnnotationByPrecedentReferencedAnnotationUidContains(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword,
            String precedentReferencedAnnotationUid) throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (precedentReferencedAnnotationUid == null || "".equals(precedentReferencedAnnotationUid.trim())) {
            throw new AimException("AimException: PrecedentReferencedAnnotationUid must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotation.precedentReferencedAnnotationUid LIKE '%"
                + precedentReferencedAnnotationUid + "%'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL,
                "");
        return getImageAnnotationsFromImageAnnotationCollectionList(listAnno);
    }
}
