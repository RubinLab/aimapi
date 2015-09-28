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
 * @author Hakan
 */
public class AimeManager {
	
	public static String BASE_URL="http://128.252.63.157:9099/services/TEST-AIM4.0/TEST/";
	public static String API_KEY="aede9f53-5cb6-4e03-92fc-4b0b58258a82";
	
	private static String removeFromAIMEReal(String annotationContainerUID) throws AimException {
        try {
        	//control???
//            if (!AnnotationGetter
//                    .isExistInTheServer(Url, nameSpace, collection, dbUserName, dbUserPassword, uniqueIdentifier)) {
//                throw new AimException(
//                        "AimException: The Image Annotation which you want to remove is not exist. Please check your parameters.");
//            }

            String requestURL = Utility.correctToUrl(BASE_URL) + "delete/byUID" ;

            if (!annotationContainerUID.equals(""))
            	requestURL+="&annotationContainerUID="+annotationContainerUID;
           
            URL url = new URL(requestURL);
            URLConnection conn = url.openConnection();

            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).setRequestMethod("DELETE");
                ((HttpURLConnection) conn).setRequestProperty("Content-Type", "application/xml");
                
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
            return "XML removing operation is Successful.";
        } catch (IOException ex) {
            throw new AimException("AimException: " + ex.getMessage());
        }
    }
	
	public static String performXMLUploadToAIME(String XMLData)
            throws AimException {
        try {
            String requestURL = Utility.correctToUrl(BASE_URL) + "submit/xml/";
            String data = "";

            URL url = new URL(requestURL);
            URLConnection conn = url.openConnection();

            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).setRequestMethod("POST");
                ((HttpURLConnection) conn).setRequestProperty("Content-Type", "application/xml");
                
                ((HttpURLConnection) conn).connect();
            }

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

            // write parameters
            writer.write(XMLData);
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
            return answer.toString();
        } catch (Exception ex) {
            throw new AimException("AimException: " + ex.getMessage());
        }
    }
	
	public static String findFromAIME(String username, String anatomicalEntity, String anatomicEntityCharacteristic, 
			String imagingObservationEntity,String imagingObservationEntityCharacteristic, String studyInstanceUID,
			String patientName,String patientID, String annotationName,String annotationContainerUID)
            throws AimException {
		return getFromAIME("find", username, anatomicalEntity, anatomicEntityCharacteristic, imagingObservationEntity, imagingObservationEntityCharacteristic, studyInstanceUID, patientName, patientID, annotationName, annotationContainerUID);
	}
	
	public static String retrieveFromAIME(String username, String anatomicalEntity, String anatomicEntityCharacteristic, 
			String imagingObservationEntity,String imagingObservationEntityCharacteristic, String studyInstanceUID,
			String patientName,String patientID, String annotationName,String annotationContainerUID)
            throws AimException {
		return getFromAIME("retrieve", username, anatomicalEntity, anatomicEntityCharacteristic, imagingObservationEntity, imagingObservationEntityCharacteristic, studyInstanceUID, patientName, patientID, annotationName, annotationContainerUID);
	}
	//method should be find or retrieve
	private static String getFromAIME(String method,String username, String anatomicalEntity, String anatomicEntityCharacteristic, 
			String imagingObservationEntity,String imagingObservationEntityCharacteristic, String studyInstanceUID,
			String patientName,String patientID, String annotationName,String annotationContainerUID)
            throws AimException {
        try {
            String requestURL = Utility.correctToUrl(BASE_URL) + "query/"+ method;
            requestURL+="?api_key=" + API_KEY ;
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
            	requestURL+="&annotationContainerUID="+annotationContainerUID;
            
            URL url = new URL(requestURL);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            
            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).setRequestMethod("GET");
                ((HttpURLConnection) conn).setRequestProperty("Content-Type", "application/json");

                //put icin
//                DataOutputStream wr = new DataOutputStream(conn.getOutputStream ());
//                wr.writeBytes("api_key=" + API_KEY + "&patientID="+patientID );
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
            return answer.toString();
        } catch (Exception ex) {
            throw new AimException("AimException: " + ex.getMessage());
        }
    }

}
