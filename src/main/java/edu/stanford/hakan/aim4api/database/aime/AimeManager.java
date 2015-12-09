/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.hakan.aim4api.database.aime;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import edu.stanford.hakan.aim4api.audittrail.AuditTrailManager;
import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.ImageAnnotationCollection;
import edu.stanford.hakan.aim4api.usage.AnnotationGetter;
import edu.stanford.hakan.aim4api.utility.Logger;
import edu.stanford.hakan.aim4api.utility.Utility;

/**
 *
 * @author Emel Alkim
 */
public class AimeManager {

	public static String findFromAIME(String aimeServer,String apiKey,String username, String anatomicalEntity, String anatomicEntityCharacteristic, 
			String imagingObservationEntity,String imagingObservationEntityCharacteristic, String studyInstanceUID,
			String patientName,String patientID, String annotationName,String annotationContainerUID)
            throws AimException {
		return getFromAIME("find",aimeServer, apiKey, username, anatomicalEntity, anatomicEntityCharacteristic, imagingObservationEntity, imagingObservationEntityCharacteristic, studyInstanceUID, patientName, patientID, annotationName, annotationContainerUID);
	}
	
	public static String retrieveFromAIME(String aimeServer,String apiKey,String username, String anatomicalEntity, String anatomicEntityCharacteristic, 
			String imagingObservationEntity,String imagingObservationEntityCharacteristic, String studyInstanceUID,
			String patientName,String patientID, String annotationName,String annotationContainerUID)
            throws AimException {
		return getFromAIME("retrieve",aimeServer, apiKey, username, anatomicalEntity, anatomicEntityCharacteristic, imagingObservationEntity, imagingObservationEntityCharacteristic, studyInstanceUID, patientName, patientID, annotationName, annotationContainerUID);
	}
	//method should be find or retrieve
	private static String getFromAIME(String method,String aimeServer,String apiKey,String username, String anatomicalEntity, String anatomicEntityCharacteristic, 
			String imagingObservationEntity,String imagingObservationEntityCharacteristic, String studyInstanceUID,
			String patientName,String patientID, String annotationName,String annotationContainerUID)
            throws AimException {
		String requestURL=null;
        try {
             requestURL = Utility.correctToUrl(aimeServer) + "query/"+ method;
            requestURL+="?api_key=" + apiKey ;
            if (!username.equals(""))
            	requestURL+="&username="+username;
            if (!anatomicalEntity.equals(""))
            	requestURL+="&anatomicalEntity="+anatomicalEntity;
            if (!anatomicEntityCharacteristic.equals(""))
            	requestURL+="&anatomicEntityCharacteristic="+anatomicEntityCharacteristic;
            if (!imagingObservationEntity.equals(""))
            	requestURL+="&imagingObservationEntity="+imagingObservationEntity;
            if (!imagingObservationEntityCharacteristic.equals(""))
            	requestURL+="&imagingObservationEntityCharacteristic="+imagingObservationEntityCharacteristic;
            if (!studyInstanceUID.equals(""))
            	requestURL+="&studyInstanceUID="+studyInstanceUID;
            if (!patientName.equals(""))
            	requestURL+="&patientName="+patientName;
            if (!patientID.equals(""))
            	requestURL+="&patientID="+patientID;
            if (!annotationName.equals(""))
            	requestURL+="&annotationName="+annotationName;
            if (!annotationContainerUID.equals(""))
            	requestURL+="&annotationContainerUID='"+annotationContainerUID+"'";
            
            URL url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/xml");
 
            if (conn.getResponseCode() != 200)
            {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            StringBuilder answer = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String line;
            while ((line = reader.readLine()) != null) {
              answer.append(line);
            }
            conn.disconnect();
            
            reader.close();
           
            
            // Output the response
            return correctTheServerRespond(answer.toString());
        } catch (Exception ex) {
            throw new AimException("AimException: " + ex.getMessage() + requestURL);
        }
    }
	
	private static String correctTheServerRespond(String serverRespond) {
        serverRespond = serverRespond.replaceAll("\"", "~**~");
        String xmlNs= "xmlns=~**~gme://caCORE.caCORE/4.4/edu.northwestern.radiology.AIM~**~";
        String xmlRdf= "xmlns:rdf=~**~http://www.w3.org/1999/02/22-rdf-syntax-ns#~**~";
        String xmlNsi= "xmlns:xsi=~**~http://www.w3.org/2001/XMLSchema-instance~**~";
        	    
        String xmlHeader = "<?xml version=~**~1.0~**~ encoding=~**~UTF-8~**~ standalone=~**~no~**~?>";
        String xmlTag = "<results>";
        String xmlTag2 = "</results>";
        int indexStart = serverRespond.indexOf(xmlTag);
        if (indexStart >= 0) {
            serverRespond = (xmlHeader + serverRespond.replace(xmlNs, "").replace(xmlTag, "").replace(xmlTag2, "").replace(xmlNsi, xmlRdf+ " "+ xmlNsi).replace(xmlHeader, "")).replace("~**~", "\"");
        }
        return serverRespond.replace("~**~", "\"");
    }

}