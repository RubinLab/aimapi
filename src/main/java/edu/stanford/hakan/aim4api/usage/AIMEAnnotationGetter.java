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
import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.database.aime.AimeManager;
import edu.stanford.hakan.aim4api.database.exist.ExistManager;
import edu.stanford.hakan.aim4api.plugin.PluginParemeterCollection;
import edu.stanford.hakan.aim4api.plugin.v4.PluginCollectionV4;
import edu.stanford.hakan.aim4api.plugin.v4.PluginV4;
import edu.stanford.hakan.aim4api.utility.Globals;
import edu.stanford.hakan.aim4api.utility.Logger;
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
 * @author Emel ALKIM
 */
public class AIMEAnnotationGetter {
	
         




    private static List<ImageAnnotation> getImageAnnotationsFromImageAnnotationCollectionList(
            List<ImageAnnotationCollection> listImageAnnotationCollection) {
        List<ImageAnnotation> res = new ArrayList<ImageAnnotation>();
        for (int i = 0; i < listImageAnnotationCollection.size(); i++) {
            res.addAll(listImageAnnotationCollection.get(i).getImageAnnotations());
        }
        return res;
    }

    public static List<ImageAnnotationCollection> getImageAnnotationListFromServer(String aimeServer,String apiKey,String username, String anatomicalEntity, String anatomicEntityCharacteristic, 
			String imagingObservationEntity,String imagingObservationEntityCharacteristic, String studyInstanceUID,
			String patientName,String patientID, String annotationName,String annotationContainerUID) throws AimException {
        try {
        	String serverResponse = AimeManager.retrieveFromAIME(aimeServer,apiKey,username, anatomicalEntity, anatomicEntityCharacteristic, imagingObservationEntity, imagingObservationEntityCharacteristic, studyInstanceUID, patientName, patientID, annotationName, annotationContainerUID);
            Document serverDoc = XML.getDocumentFromString(serverResponse);
            List<ImageAnnotationCollection> res = ExistManager.getImageAnnotationCollectionListFromDocument(serverDoc, "");

            return res;
        } catch (AimException ex) {
            throw new AimException("AimException: connecting " + aimeServer + " with apikey" + apiKey + ex.getMessage());
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


    public static boolean isExistInTheServer(String aimeServer,String apiKey,String uniqueIdentifier) throws AimException {
        ImageAnnotationCollection temp = getImageAnnotationCollectionByUniqueIdentifier(aimeServer,apiKey,uniqueIdentifier);
        if (temp != null) {
            return true;
        }
        return false;
    }

    

    // *************************************************//
    // ****************** QUERIES **********************//
    // *************************************************//
    // *** ImageAnnotationCollection.uniqueIdentifier Equal
    public static ImageAnnotationCollection getImageAnnotationCollectionByUniqueIdentifier(String aimeServer,String apiKey,String uniqueIdentifier)
            throws AimException {
//        control(serverURL, namespace, collection);
        if (uniqueIdentifier == null || "".equals(uniqueIdentifier.trim())) {
            throw new AimException("AimException: UniqueIdentifier must be defined");
        }
        if (!uniqueIdentifier.startsWith("'"))
        	uniqueIdentifier="'"+uniqueIdentifier+"'";

        List<ImageAnnotationCollection> listAnno = getImageAnnotationListFromServer(aimeServer,apiKey,"", "", "", "", "", "", "", "", "", uniqueIdentifier);
        if (listAnno.size() <= 0) {
            return null;
        }
        return listAnno.get(0);
    }

    //*** ImageAnnotationCollection.uniqueIdentifier list Equals
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByUniqueIdentifierList(String aimeServer,String apiKey,String[] uniqueIdentifiers) throws AimException {
//        control(serverURL, namespace, collection);
        if (uniqueIdentifiers == null || "".equals(uniqueIdentifiers.length == 0)) {
            throw new AimException("AimException: UniqueIdentifiers must be defined");
        }

        String uiList = "'";
        String operator = "";
        for (String uniqueIdentifier : uniqueIdentifiers) {
            uiList = uiList + operator + "" + uniqueIdentifier ;
            operator = "' , '";
        }
        uiList+="'";
        return getImageAnnotationListFromServer(aimeServer,apiKey,"", "", "", "", "", "", "", "", "", uiList);

    }

    
    // *** User.loginName Contains
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByUserLoginNameContains(String aimeServer,String apiKey,String userLoginName)
            throws AimException {
//        control(serverURL, namespace, collection);
        if (userLoginName == null || "".equals(userLoginName.trim())) {
            throw new AimException("AimException: UserLoginName must be defined");
        }

        List<ImageAnnotationCollection> listAnno = getImageAnnotationListFromServer( aimeServer,apiKey,userLoginName, "", "", "", "", "", "", "", "","");
        return listAnno;
    }

    // *** User.loginName Contains
    public static List<ImageAnnotation> getImageAnnotationsByUserLoginNameContains(String aimeServer,String apiKey,String userLoginName)
            throws AimException {

        List<ImageAnnotationCollection> tempList = getImageAnnotationCollectionByUserLoginNameContains(aimeServer,apiKey,userLoginName);
        return getImageAnnotationsFromImageAnnotationCollectionList(tempList);
    }

    
    // *** Person.Name Equal
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByPersonNameEqual(String aimeServer,String apiKey,String PersonName)
            throws AimException {
//        control(serverURL, namespace, collection);
        if (PersonName == null || "".equals(PersonName.trim())) {
            throw new AimException("AimException: PersonName must be defined");
        }

        List<ImageAnnotationCollection> listAnno = getImageAnnotationListFromServer( aimeServer,apiKey,"", "", "", "", "", "", PersonName, "", "","");
        return listAnno;
    }

   
    // *** Person.Id Equal
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByPersonIdEqual(String aimeServer,String apiKey,String PersonId)
            throws AimException {
       // control(serverURL, namespace, collection);
        if (PersonId == null || "".equals(PersonId.trim())) {
            throw new AimException("AimException: PersonId must be defined");
        }

        List<ImageAnnotationCollection> listAnno = getImageAnnotationListFromServer( aimeServer,apiKey,"", "", "", "", "", "", "", PersonId, "","");
        return listAnno;
    }

    // *** Person.Id Equal
    public static List<ImageAnnotation> getImageAnnotationsByPersonIdEqual(String aimeServer,String apiKey,String PersonId)
            throws AimException {

        List<ImageAnnotationCollection> tempList = getImageAnnotationListFromServer( aimeServer,apiKey,"", "", "", "", "", "", "", PersonId, "","");

        return getImageAnnotationsFromImageAnnotationCollectionList(tempList);
    }

   
    // *** ImageAnnotation.uniqueIdentifier Equal
    public static List<ImageAnnotation> getImageAnnotationByUniqueIdentifier(String aimeServer,String apiKey, String uniqueIdentifier) throws AimException {
//        control(serverURL, namespace, collection);
        if (uniqueIdentifier == null || "".equals(uniqueIdentifier.trim())) {
            throw new AimException("AimException: UniqueIdentifier must be defined");
        }
        List<ImageAnnotationCollection> listAnno = getImageAnnotationListFromServer(aimeServer,apiKey,"", "", "", "", "", "", "", "", "", uniqueIdentifier);
        return getImageAnnotationsFromImageAnnotationCollectionList(listAnno);
    }

   
    // *** ImageAnnotation.name Equal
    public static List<ImageAnnotation> getImageAnnotationByNameEqual(String aimeServer,String apiKey,String name) throws AimException {
//        control(serverURL, namespace, collection);
        if (name == null || "".equals(name.trim())) {
            throw new AimException("AimException: Name must be defined");
        }

        List<ImageAnnotationCollection> listAnno = getImageAnnotationListFromServer(aimeServer,apiKey,"", "", "", "", "", "", "", "", name, "");
        return getImageAnnotationsFromImageAnnotationCollectionList(listAnno);
    }

    // *** ImageAnnotation.name Contains
    public static List<ImageAnnotation> getImageAnnotationByNameContains( String aimeServer,String apiKey,String name) throws AimException {
        //control(serverURL, namespace, collection);
        if (name == null || "".equals(name.trim())) {
            throw new AimException("AimException: Name must be defined");
        }

        List<ImageAnnotationCollection> listAnno = getImageAnnotationListFromServer(aimeServer,apiKey,"", "", "", "", "", "", "", "", name, "");
        return getImageAnnotationsFromImageAnnotationCollectionList(listAnno);
    }

    // *** Person.Id Equal AND User.name Equal 
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByUserNameAndPersonIdEqual(String aimeServer,String apiKey,String userName, String PersonId)
            throws AimException {
        //control(serverURL, namespace, collection);
        if (userName == null || "".equals(userName.trim())) {
            throw new AimException("AimException: UserName must be defined");
        }

        List<ImageAnnotationCollection> listAnno = getImageAnnotationListFromServer(aimeServer,apiKey,userName, "", "", "", "", "", "", PersonId, "", "");
        return listAnno;
    }

    // *** study instance 
    public static List<ImageAnnotationCollection> getImageAnnotationCollectionByStudyInstanceUID(String aimeServer,String apiKey,String studyInstanceUId)
            throws AimException {
        //control(serverURL, namespace, collection);
        if (studyInstanceUId == null || "".equals(studyInstanceUId.trim())) {
            throw new AimException("AimException: StudyInstance must be defined");
        }

        List<ImageAnnotationCollection> listAnno = getImageAnnotationListFromServer(aimeServer,apiKey,"", "", "", "", "", studyInstanceUId, "", "", "", "");
        return listAnno;
    }
   

    // *** All ImageAnnotationCollections
    public static List<ImageAnnotationCollection> getAllImageAnnotationCollections(String aimeServer,String apiKey)
            throws AimException {
       
        List<ImageAnnotationCollection> listAnno = getImageAnnotationListFromServer(aimeServer,apiKey,"", "", "", "", "", "", "", "", "", "");
        return listAnno;
    }



}
