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
package edu.stanford.hakan.aim4api.usage;

import edu.stanford.hakan.aim4api.aimquery.AimQuery;
import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.base.ImageAnnotationCollection;
import edu.stanford.hakan.aim4api.database.exist.ExistManager;
import edu.stanford.hakan.aim4api.database.exist.ExistResponderThread;
import edu.stanford.hakan.aim4api.utility.Globals;
import edu.stanford.hakan.aim4api.utility.Utility;
import edu.stanford.hakan.aim4api.utility.XML;
import edu.stanford.hakan.aim4api.utility.dotnet.StreamReader;
import edu.stanford.hakan.aim4api.utility.dotnet.StreamWriter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Hakan BULU
 */
public class AnnotationGetter {

    private static final int MAX_RECORDS = 10000;

    private static String validationResult;

    public static String getValidationResult() {
        return validationResult;
    }

    public static void setValidationResult(String valResult) {
        validationResult = valResult;
    }

    private static List<ImageAnnotation> getImageAnnotationsFromImageAnnotationCollectionList(
            List<ImageAnnotationCollection> listImageAnnotationCollection) {
        List<ImageAnnotation> res = new ArrayList<ImageAnnotation>();
        for (int i = 0; i < listImageAnnotationCollection.size(); i++) {
            res.addAll(listImageAnnotationCollection.get(i).getImageAnnotations());
        }
        return res;
    }

    private static List<ImageAnnotationCollection> getImageAnnotationListFromServer(String Url, String XQuery,
            String dbUserName, String dbUserPassword, String PathXSD, int startIndex, int maxRecords) throws AimException {
        try {
            String serverResponse = ExistManager.getXMLStringFromExistWithStartIndexCount(Url, XQuery, dbUserName, dbUserPassword, 1, maxRecords);
            Document serverDoc = XML.getDocumentFromString(serverResponse);
            List<ImageAnnotationCollection> res = ExistManager.getImageAnnotationCollectionListFromDocument(serverDoc, PathXSD);
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
            Document serverDoc = XML.getDocumentFromString(text);
            List<ImageAnnotationCollection> res = ExistManager.getImageAnnotationCollectionListFromDocument(serverDoc, PathXSD);
            return res;
        } catch (AimException ex) {
            throw new AimException("AimException: " + ex.getMessage());
        }
    }
    
    public static ImageAnnotationCollection getImageAnnotationCollectionFromString(String text, String PathXSD)
            throws AimException {
        try {
            Document serverDoc = XML.getDocumentFromString(text);
            List<ImageAnnotationCollection> res = ExistManager.getImageAnnotationCollectionListFromDocument(serverDoc, PathXSD);
            return res.get(0);
        } catch (AimException ex) {
            throw new AimException("AimException: " + ex.getMessage());
        }
    }

    public static List<ImageAnnotationCollection> getWithAimQuery(String serverURL, String namespace, String dbUserName,
            String dbUserPassword, String aimQuery, String PathXSD) throws AimException {
        return getWithAimQuery(serverURL, namespace, dbUserName,
                dbUserPassword, aimQuery, PathXSD, 1, MAX_RECORDS);
    }

    public static List<ImageAnnotationCollection> getWithAimQuery(String serverURL, String namespace, String dbUserName,
            String dbUserPassword, String aimQuery, String PathXSD, int startIndex, int maxRecords) throws AimException {
        
            if (PathXSD != null && !"".equals(Globals.getXSDPath())) {
                PathXSD = Globals.getXSDPath();
            }
            
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
        //XQuery = "declare default element namespace 'gme://caCORE.caCORE/4.4/edu.northwestern.radiology.AIM'; for $x in collection('/aimV4.dbxml/napel_nsclc')/ImageAnnotationCollection where  $x/person/name[contains(lower-case(@value),lower-case('274'))]  return $x";
        return getImageAnnotationListFromServer(serverURL, XQuery, dbUserName, dbUserPassword, PathXSD, startIndex, maxRecords);// getDocumentFromServer(serverURL,
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

    //*** ImageAnnotationCollection.uniqueIdentifier list Equals
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByUniqueIdentifierList(String serverURL, String namespace, String collection,
            String dbUserName, String dbUserPassword, String[] uniqueIdentifiers) throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (uniqueIdentifiers == null || "".equals(uniqueIdentifiers.length == 0)) {
            throw new AimException("AimException: UniqueIdentifiers must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE ";
        String operator = "";
        for (String uniqueIdentifier : uniqueIdentifiers) {
            aimQL = aimQL + operator + "ImageAnnotationCollection.uniqueIdentifier.root = '" + uniqueIdentifier + "'";
            operator = " OR ";
        }
        return getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL, "");
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

    // *** User.loginName Equal
    public static List<ImageAnnotation> getImageAnnotationsByUserLoginNameEqual(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String userLoginName)
            throws AimException {

        List<ImageAnnotationCollection> tempList = getImageAnnotationCollectionByUserLoginNameEqual(serverURL,
                namespace, collection, dbUserName, dbUserPassword, userLoginName);
        return getImageAnnotationsFromImageAnnotationCollectionList(tempList);
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

    // *** Person.Id Equal
    public static List<ImageAnnotation> getImageAnnotationsByPersonIdEqual(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String PersonId)
            throws AimException {

        List<ImageAnnotationCollection> tempList = getImageAnnotationCollectionByPersonIdEqual(serverURL,
                namespace, collection, dbUserName, dbUserPassword, PersonId);
        return getImageAnnotationsFromImageAnnotationCollectionList(tempList);
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

    // *** ImageAnnotation.typeCode.code Equal
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByImageAnnotationCodeEqual(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String Code)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (Code == null || "".equals(Code.trim())) {
            throw new AimException("AimException: Code must be defined");
        }
        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotation.typeCode.code = '" + Code + "'";
        return getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL, "");
    }

    // *** ImageAnnotation.typeCode.code Like
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByImageAnnotationCodeLike(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String Code)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (Code == null || "".equals(Code.trim())) {
            throw new AimException("AimException: Code must be defined");
        }
        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotation.typeCode.code LIKE '%" + Code + "%'";
        return getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL, "");
    }

    // *** count ImageAnnotationCollection by userName
    public static int getCountImageAnnotationCollectionByUserNameEqual(String serverURL,
            String nameSpace, String collectionName, String dbUserName, String dbUserPassword,
            String userName) throws AimException {

        //String query = "declare default element namespace '" + nameSpace + "'; count(/collection('" + collectionName + "')/ImageAnnotationCollection/user/name[lower-case(@value)=lower-case('" + userName + "')])";
        String query = "declare default element namespace '" + nameSpace + "'; let $iac := collection('" + collectionName + "')/ImageAnnotationCollection  where ($iac/user/name[lower-case(@value)=lower-case('" + userName + "')]) return count($iac)";

        String serverResponse = ExistManager.getXMLStringFromExist(serverURL, query, dbUserName, dbUserPassword);
        Document serverDoc = XML.getDocumentFromString(serverResponse);
        String res = ExistManager.getExistResultValueFromDocument(serverDoc);
        if ("".equals(res)) {
            return -1;
        }
        return Integer.parseInt(res);
    }

    // *** count ImageAnnotationCollection  by person Name
    public static int getCountImageAnnotationCollectionByPersonNameEqual(String serverURL,
            String nameSpace, String collectionName, String dbUserName, String dbUserPassword,
            String personName) throws AimException {

        String query = "declare default element namespace '" + nameSpace + "'; count(/collection('" + collectionName + "')/ImageAnnotationCollection/person/name[lower-case(@value)=lower-case('" + personName + "')])";
        String serverResponse = ExistManager.getXMLStringFromExist(serverURL, query, dbUserName, dbUserPassword);
        Document serverDoc = XML.getDocumentFromString(serverResponse);
        String res = ExistManager.getExistResultValueFromDocument(serverDoc);
        if ("".equals(res)) {
            return -1;
        }
        return Integer.parseInt(res);
    }

    // *** count ImageAnnotation by userName
    public static int getCountImageAnnotationByUserNameEqual(String serverURL,
            String nameSpace, String collectionName, String dbUserName, String dbUserPassword,
            String userName) throws AimException {

        String query = "declare default element namespace '" + nameSpace + "'; let $iac := collection('" + collectionName + "')/ImageAnnotationCollection  where ($iac/user/name[lower-case(@value)=lower-case('" + userName + "')]) return count($iac/imageAnnotations/ImageAnnotation)";
        String serverResponse = ExistManager.getXMLStringFromExist(serverURL, query, dbUserName, dbUserPassword);
        Document serverDoc = XML.getDocumentFromString(serverResponse);
        String res = ExistManager.getExistResultValueFromDocument(serverDoc);
        if ("".equals(res)) {
            return -1;
        }
        return Integer.parseInt(res);
    }

    // *** count ImageAnnotation by person Name
    public static int getCountImageAnnotationByPersonNameEqual(String serverURL,
            String nameSpace, String collectionName, String dbUserName, String dbUserPassword,
            String personName) throws AimException {

        String query = "declare default element namespace '" + nameSpace + "'; let $iac := collection('" + collectionName + "')/ImageAnnotationCollection  where ($iac/person/name[lower-case(@value)=lower-case('" + personName + "')]) return count($iac/imageAnnotations/ImageAnnotation)";
        String serverResponse = ExistManager.getXMLStringFromExist(serverURL, query, dbUserName, dbUserPassword);
        Document serverDoc = XML.getDocumentFromString(serverResponse);
        String res = ExistManager.getExistResultValueFromDocument(serverDoc);
        if ("".equals(res)) {
            return -1;
        }
        return Integer.parseInt(res);
    }

    // *** count ImageAnnotationCollection by userName
    public static int getCountImageAnnotationCollectionByUserNameContains(String serverURL,
            String nameSpace, String collectionName, String dbUserName, String dbUserPassword,
            String userName) throws AimException {

        String query = "declare default element namespace '" + nameSpace + "'; count(/collection('" + collectionName + "')/ImageAnnotationCollection/user/name[contains(lower-case(@value),lower-case('" + userName + "'))])";
        String serverResponse = ExistManager.getXMLStringFromExist(serverURL, query, dbUserName, dbUserPassword);
        Document serverDoc = XML.getDocumentFromString(serverResponse);
        String res = ExistManager.getExistResultValueFromDocument(serverDoc);
        if ("".equals(res)) {
            return -1;
        }
        return Integer.parseInt(res);
    }

    // *** count ImageAnnotationCollection  by person Name
    public static int getCountImageAnnotationCollectionByPersonNameContains(String serverURL,
            String nameSpace, String collectionName, String dbUserName, String dbUserPassword,
            String personName) throws AimException {

        String query = "declare default element namespace '" + nameSpace + "'; count(/collection('" + collectionName + "')/ImageAnnotationCollection/person/name[contains(lower-case(@value),lower-case('" + personName + "'))])";
        String serverResponse = ExistManager.getXMLStringFromExist(serverURL, query, dbUserName, dbUserPassword);
        Document serverDoc = XML.getDocumentFromString(serverResponse);
        String res = ExistManager.getExistResultValueFromDocument(serverDoc);
        if ("".equals(res)) {
            return -1;
        }
        return Integer.parseInt(res);
    }

    // *** count ImageAnnotation by userName
    public static int getCountImageAnnotationByUserNameContains(String serverURL,
            String nameSpace, String collectionName, String dbUserName, String dbUserPassword,
            String userName) throws AimException {

        String query = "declare default element namespace '" + nameSpace + "'; let $iac := collection('" + collectionName + "')/ImageAnnotationCollection  where ($iac/user/name[contains(lower-case(@value),lower-case('" + userName + "'))]) return count($iac/imageAnnotations/ImageAnnotation)";
        String serverResponse = ExistManager.getXMLStringFromExist(serverURL, query, dbUserName, dbUserPassword);
        Document serverDoc = XML.getDocumentFromString(serverResponse);
        String res = ExistManager.getExistResultValueFromDocument(serverDoc);
        if ("".equals(res)) {
            return -1;
        }
        return Integer.parseInt(res);
    }

    // *** count ImageAnnotation by person Name
    public static int getCountImageAnnotationByPersonNameContains(String serverURL,
            String nameSpace, String collectionName, String dbUserName, String dbUserPassword,
            String personName) throws AimException {

        String query = "declare default element namespace '" + nameSpace + "'; let $iac := collection('" + collectionName + "')/ImageAnnotationCollection  where ($iac/person/name[contains(lower-case(@value),lower-case('" + personName + "'))]) return count($iac/imageAnnotations/ImageAnnotation)";
        String serverResponse = ExistManager.getXMLStringFromExist(serverURL, query, dbUserName, dbUserPassword);

        Document serverDoc = XML.getDocumentFromString(serverResponse);
        String res = ExistManager.getExistResultValueFromDocument(serverDoc);
        if ("".equals(res)) {
            return -1;
        }
        return Integer.parseInt(res);
    }

    // *** Person.Id Equal AND User.loginName Equal 
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByUserLoginNameAndPersonIdEqual(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String userLoginName, String PersonId)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (userLoginName == null || "".equals(userLoginName.trim())) {
            throw new AimException("AimException: UserLoginName must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE user.loginName = '" + userLoginName + "' AND person.id = '" + PersonId + "'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL, "");
        return listAnno;
    }

    // *** Person.Id Equal AND User.loginName Equal 
    public static List<ImageAnnotation> getImageAnnotationsByUserLoginNameAndPersonIdEqual(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String userLoginName, String PersonId)
            throws AimException {

        List<ImageAnnotationCollection> tempList = getImageAnnotationCollectionByUserLoginNameAndPersonIdEqual(serverURL,
                namespace, collection, dbUserName, dbUserPassword, userLoginName, PersonId);
        return getImageAnnotationsFromImageAnnotationCollectionList(tempList);
    }

    // *** Person.Id Equal AND User.name Equal 
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByUserNameAndPersonIdEqual(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String userName, String PersonId)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (userName == null || "".equals(userName.trim())) {
            throw new AimException("AimException: UserName must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE user.name = '" + userName + "' AND person.id = '" + PersonId + "'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL, "");
        return listAnno;
    }

    // *** ImageSeries.instanceUid Equal
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByImageSeriesInstanceUIDEqual(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, String instanceUid)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);
        if (instanceUid == null || "".equals(instanceUid.trim())) {
            throw new AimException("AimException: instanceUid must be defined");
        }

        String aimQL = "SELECT FROM " + collection + " WHERE ImageSeries.instanceUid = '" + instanceUid + "'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL, "");
        return listAnno;
    }

    // *** All ImageAnnotationCollections
    public static List<ImageAnnotationCollection> getAllImageAnnotationCollections(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword)
            throws AimException {
        return getAllImageAnnotationCollections(serverURL,
                namespace, collection, dbUserName, dbUserPassword, 1, MAX_RECORDS);
    }

    public static List<ImageAnnotationCollection> getAllImageAnnotationCollections(String serverURL,
            String namespace, String collection, String dbUserName, String dbUserPassword, int startIndex, int maxRecords)
            throws AimException {
        serverURL = Utility.correctToUrl(serverURL);
        control(serverURL, namespace, collection);

        String aimQL = "SELECT FROM " + collection + " WHERE ImageAnnotationCollection.uniqueIdentifier.root <> '-'";
        List<ImageAnnotationCollection> listAnno = getWithAimQuery(serverURL, namespace, dbUserName, dbUserPassword, aimQL, "", startIndex, maxRecords);
        return listAnno;
    }

    // *** count ImageAnnotation by person Name
    public static int getStudyIdByPersonID(String serverURL,
            String nameSpace, String collectionName, String dbUserName, String dbUserPassword,
            String personID) throws AimException {

        String query = " declare default element namespace '" + nameSpace + "'; ";

        String listPatientIds = "";
        String strPatientIds = "";

//        for (int i = 1; i <= 400; i++) {
//            strPatientIds += "~Patient:" + Integer.toString(i);
//        }
//        query += " let $strPatientIds := '" + strPatientIds + "' ";
//=====================================================================================
        for (int i = 1; i <= 40; i++) {
            if (i != 40) {
                listPatientIds += "'Patient:" + Integer.toString(i) + "',";
            } else {
                listPatientIds += "'Patient:" + Integer.toString(i) + "'";
            }
        }
        query += " let $listPatientIds := (" + listPatientIds + ") ";

//=====================================================================================
//        String patientQuery = "";
//        for(int pid = 1; pid <= 100; pid++)
//        {
//            if(pid != 100)
//              patientQuery += "@value='Patient:" + Integer.toString(pid) + "' or ";
//            else
//              patientQuery += "@value='Patient:" + Integer.toString(pid) + "'";
//        }
//=====================================================================================
        //query += " for $iac in collection('" + collectionName + "')/ImageAnnotationCollection "; 
        //query += " where ($iac/person/id[" + patientQuery + "]) ";
        //query += " let $pid := $iac/person/id[@value]  ";
        //query += " where one-or-more(index-of($pids, $pid)) ";
        //=========================return nothing ====================
//        query += " for $id in $listPatientIds "; 
//        query += " let $iac := collection('" + collectionName + "')/ImageAnnotationCollection/person/id[@value = $id ]  ";    
//        query += " return $iac/imageAnnotations/ImageAnnotation[1]/imageReferenceEntityCollection[1]/ImageReferenceEntity[1]/imageStudy[1] ";
        //=============================================
        //=============================================
//        query += " for $iac in collection('" + collectionName + "')/ImageAnnotationCollection "; 
//        query += " where $iac/person/id[index-of($listPatientIds, @value) > 0]  ";    
//        query += " return $iac/imageAnnotations/ImageAnnotation[1]/imageReferenceEntityCollection[1]/ImageReferenceEntity[1]/imageStudy[1] ";
        //=============================================
        //=============================================
        query += " for $iac in collection('" + collectionName + "')/ImageAnnotationCollection ";
        query += " return $iac/imageAnnotations/ImageAnnotation[1]/imageReferenceEntityCollection[1]/ImageReferenceEntity[1]/imageStudy[1] ";
        //=============================================

        String serverResponse = ExistManager.getXMLStringFromExist(serverURL, query, dbUserName, dbUserPassword);

        Document serverDoc = XML.getDocumentFromString(serverResponse);
        String res = ExistManager.getExistResultValueFromDocument(serverDoc);
        if ("".equals(res)) {
            return -1;
        }
        return Integer.parseInt(res);
    }

    public static void refreshTheAnnotationCountXML(String serverURL,
            String nameSpace, String collectionName, String dbUserName, String dbUserPassword) throws AimException, IOException {

        String query = " declare default element namespace '" + nameSpace + "'; ";
        query += " for $iac in collection('" + collectionName + "')/ImageAnnotationCollection ";
        query += " return ($iac/uniqueIdentifier, $iac/person/id, $iac/imageAnnotations/ImageAnnotation/markupEntityCollection, $iac/imageAnnotations/ImageAnnotation/imageReferenceEntityCollection) ";

//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Calendar cal = Calendar.getInstance();
//        System.out.println(dateFormat.format(cal.getTime()));
        Document doc = null;
        int totalCount = 0;
        int totalRetrieved = 0;
        int pageCount = 10000;
        String serverResponse = "";
        int startIndex = 1;
        StringBuilder sb = new StringBuilder();

        serverResponse = ExistManager.getXMLStringFromExistWithStartIndexCount(serverURL, query, dbUserName, dbUserPassword, 1, 1);
        doc = XML.getDocumentFromString(serverResponse);
        totalCount = ExistManager.getHitsCountFromDocument(doc);

        if (totalCount > pageCount) {
            pageCount = totalCount / 3;
        }

        List<ExistResponderThread> listThreads = new ArrayList<>();
        while (true) {
            listThreads.add(new ExistResponderThread(serverURL + "~" + query + "~" + dbUserName + "~" + dbUserPassword + "~" + startIndex + "~" + pageCount));
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

//        cal = Calendar.getInstance();
//        System.out.println("=== Replaced started " + dateFormat.format(cal.getTime()));
        for (ExistResponderThread thread : listThreads) {
            serverResponse = thread.getRespond();
            serverResponse = serverResponse.replace(serverResponse.substring(0, serverResponse.indexOf(">") + 1), "");
//            serverResponse = serverResponse.replace("xmlns=\"gme://caCORE.caCORE/4.4/edu.northwestern.radiology.AIM\"", "");
//            serverResponse = serverResponse.replace("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
//            serverResponse = serverResponse.replace("xsi:", "");
            serverResponse = serverResponse.replace("</exist:result>", "");
            sb.append(serverResponse);
        }
//        cal = Calendar.getInstance();
//        System.out.println("=== Replaced ended " + dateFormat.format(cal.getTime()));

        doc = XML.getDocumentFromString("<results>" + sb.toString() + "</results>");

//        cal = Calendar.getInstance();
//        System.out.println(dateFormat.format(cal.getTime()));
        Node node = doc.getFirstChild();
        boolean uidOK = false;
        boolean markupOK = false;
        boolean irefOK = false;
        boolean patOK = false;

        String patientID = "";
        String annotationID = "";
        String imageID = "";
        String frameID = "";
        String studyID = "";
        String seriesID = "";

        StreamWriter sw = new StreamWriter(Globals.getAnnotationListTxtFilePath());
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);

            if ("uniqueIdentifier".equals(currentNode.getNodeName())) {
                annotationID = currentNode.getAttributes().getNamedItem("root").getNodeValue();
                uidOK = true;
            } else if ("id".equals(currentNode.getNodeName())) {
                patientID = currentNode.getAttributes().getNamedItem("value").getNodeValue();
                patOK = true;
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

            if (uidOK && patOK && markupOK && irefOK) {
                uidOK = false;
                markupOK = false;
                irefOK = false;
                patOK = false;

                String line = patientID + "~" + seriesID + "~" + studyID + "~" + imageID + "~" + frameID + "~" + annotationID;
                sw.WriteLine(line);

                annotationID = "";
                imageID = "";
                frameID = "";
                studyID = "";
                seriesID = "";
                patientID = "";
            }
        }
        sw.Close();
    }

    public static List<String> getAnnotationsTableRows() throws IOException {
        List<String> res = new ArrayList<String>();
        String fileName = Globals.getAnnotationListTxtFilePath();
        StreamReader sr = new StreamReader(fileName);
        while (sr.Peek() > 0) {
            res.add(sr.ReadLine());
        }
        sr.Close();
        return res;
    }
}
