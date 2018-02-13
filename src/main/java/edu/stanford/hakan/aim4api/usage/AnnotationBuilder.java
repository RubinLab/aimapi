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

import edu.stanford.hakan.aim4api.audittrail.AuditTrailManager;
import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.CD;
import edu.stanford.hakan.aim4api.base.CalculationEntity;
import edu.stanford.hakan.aim4api.base.CalculationEntityReferencesMarkupEntityStatement;
import edu.stanford.hakan.aim4api.base.CalculationEntityReferencesSegmentationEntityStatement;
import edu.stanford.hakan.aim4api.base.CalculationResult;
import edu.stanford.hakan.aim4api.base.DicomImageReferenceEntity;
import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.base.ImageAnnotationCollection;
import edu.stanford.hakan.aim4api.base.ImageReferenceEntity;
import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.base.TwoDimensionMultiPoint;
import edu.stanford.hakan.aim4api.database.exist.ExistManager;
import edu.stanford.hakan.aim4api.plugin.v4.PluginV4;
import edu.stanford.hakan.aim4api.project.epad.Aim4;
import edu.stanford.hakan.aim4api.resources.Resource;
import static edu.stanford.hakan.aim4api.usage.AnnotationGetter.getImageAnnotationCollectionByUniqueIdentifier;
import edu.stanford.hakan.aim4api.utility.Globals;
import edu.stanford.hakan.aim4api.utility.Logger;
import edu.stanford.hakan.aim4api.utility.Utility;
import edu.stanford.hakan.aim4api.utility.XML;
import java.io.StringWriter;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan BULU
 */
public class AnnotationBuilder {

    private static final String apiVersion = "1.4";
    // private static String validationResult;
    private static String aimXMLsaveResult = "";

    public static String getAimXMLsaveResult() {
        return aimXMLsaveResult.trim();
    }

    private static void setAimXMLsaveResult(String str) {
        aimXMLsaveResult = aimXMLsaveResult + str.trim() + "\r\n";
    }

    private static void clearAimXMLsaveResult() {
        aimXMLsaveResult = "";
    }
    
    private static ImageAnnotationCollection fixVersionChangeIssues(ImageAnnotationCollection Anno){
    	for (ImageAnnotation ia:Anno.getImageAnnotations()) {
    		//fix the old incorrect CT value 
            for (ImageReferenceEntity ir:ia.getImageReferenceEntityCollection().getImageReferenceEntityList()){
        		if (ir instanceof DicomImageReferenceEntity){
        			try {
        				CD obj=((DicomImageReferenceEntity)ir).getImageStudy().getImageSeries().getModality();
        				if (obj!=null && obj.getCode().equals("CT") && obj.getDisplayName().getValue().equals("Computed Radiography"))
        					obj.getDisplayName().setValue("Computed Tomography");
        				((DicomImageReferenceEntity)ir).getImageStudy().getImageSeries().setModality(obj);
        			}catch(Exception e){
        				
        			}
        			
        		}
        	}
            boolean addAnnotationStatements=false;
            //no annotation statements in aim
            if (ia.getImageAnnotationStatementCollection()==null || ia.getImageAnnotationStatementCollection().getImageAnnotationStatementList().isEmpty()){
            	addAnnotationStatements=true;
            }else if (!(ia.getImageAnnotationStatementCollection().getImageAnnotationStatementList().get(0) instanceof CalculationEntityReferencesSegmentationEntityStatement) && !(ia.getImageAnnotationStatementCollection().getImageAnnotationStatementList().get(0) instanceof CalculationEntityReferencesMarkupEntityStatement)){
            	//clear image annotation statements and create them correctly
            	ia.getImageAnnotationStatementCollection().getImageAnnotationStatementList().clear();
            	addAnnotationStatements=true;
            }
            String units="";
        	//fix calculations. issues: two types according to the modality, ucumstring in unitofmeasure,datatype 
            for (CalculationEntity ce:ia.getCalculationEntityCollection().getCalculationEntityList()){
            	for (CalculationResult cr: ce.getCalculationResultCollection().getCalculationResultList()){
            		cr.setUnitOfMeasure(new ST(Aim4.getUCUMUnit(cr.getUnitOfMeasure().getValue())));
            		units=cr.getUnitOfMeasure().getValue();
            		//everything is double!
            		cr.setDataType(new CD("C48870","Double","NCI"));
            	}
            	if (ce.getListTypeCode().size()==1){//this is old format see if the modality is ct or pet and add to the beginning
            		CD typeCode=null;
            		if (units.equals("SUV") || units.equals("{SUVbw}g/ml")) {
    					typeCode = new CD("126401","SUVbw","DCM");
    				}
    				if (units.equals("HU") || units.equals("[hnsf'U]")) {
    					typeCode = new CD("112031","Attenuation Coefficient","DCM");
    				}
    				//try and fix old aims with pixels in units
    				if (units.equals("pixels")){
	            		try {
	            			//get the first and see if you can find modalty
	        				CD obj=((DicomImageReferenceEntity)ia.getImageReferenceEntityCollection().get(0)).getImageStudy().getImageSeries().getModality();
	        				if (obj!=null && obj.getCode().equals("PET")) {
	        					typeCode = new CD("126401","SUVbw","DCM");
	        				}
	        				if (obj!=null && obj.getCode().equals("CT")) {
	            				typeCode = new CD("112031","Attenuation Coefficient","DCM");
	        				}
	        					
	        			}catch(Exception e){
	        				
	        			}
            		}
            		//we need to add to the beginning
            		if (typeCode!=null){
	            		List<CD> listTypeCode=ce.getListTypeCode();
	            		listTypeCode.add(0,typeCode);
	            		ce.setTypeCode(listTypeCode);
            		}
            	}
            	
            	//template fix???
            	
            	//addrefs
            	if (addAnnotationStatements){
            		if (ia.getMarkupEntityCollection()!=null && ia.getSegmentationEntityCollection()!=null){
            			Logger.write("Don't know which shape is the calculation from not adding ref");
            		}
            		//find the shape. what if more than one shape
            		//geometric
            		if (ia.getMarkupEntityCollection()!=null && ia.getMarkupEntityCollection().size()==1){
            			ia.addImageAnnotationStatement(Aim4.createCalcRefMarkupStatement(ce, ia.getMarkupEntityCollection().getMarkupEntityList().get(0)));
            		}
            		//segmentation
            		if (ia.getSegmentationEntityCollection()!=null && ia.getSegmentationEntityCollection().size()==1){
            			ia.addImageAnnotationStatement(Aim4.createCalcRefSegStatement(ce, ia.getSegmentationEntityCollection().getSegmentationEntityList().get(0)));
            		}
            	}
            	
            	
            	//datetime
            	
            }
	    	
        }
        return Anno;
    	
    }
    /**
     * added this code to change the datetime to dicomformat. 
     * Changes it but getXMLNode puts back a new GMT as it figures the aim is edited
     * @param Anno
     * @return
     */
    private static ImageAnnotationCollection formatDateTimeForFile(ImageAnnotationCollection Anno){
    	for (ImageAnnotation ia:Anno.getImageAnnotations()) {
    		Logger.write("old datetime is "+ia.getDateTime());
    		if (ia.getDateTime().contains("GMT")){
    			ia.setDateTime(Utility.parseGMTToLocalDicomTime(ia.getDateTime()));
        		Logger.write("new datetime is "+ia.getDateTime());
    		}
    		
    	}
    	return Anno;
    }
    public static void saveToFile(ImageAnnotationCollection Anno, String PathXML, String PathXSD) throws AimException {
        try {
        	Anno=fixVersionChangeIssues(Anno);
        	//only for save change the datetime format
        	Anno=formatDateTimeForFile(Anno);
        	Logger.write("save ");
            if (PathXSD != null && !"".equals(Globals.getXSDPath())) {
                PathXSD = Globals.getXSDPath();
            }
            Logger.write("aim="+convertToString(Anno));
            clearAimXMLsaveResult();
            Document doc = XML.createDocument();
            Element root = (Element) Anno.getXMLNode(doc);
            XML.setBaseAttributes(root);
            doc.appendChild(root);
            boolean valRes = true;
            if (PathXSD != null) {
                // *** Validation doc
                valRes = AnnotationValidator.ValidateXML(doc, PathXSD);
                setAimXMLsaveResult(AnnotationValidator.getValidationResult());
                // *** Validation End
            }
            if (valRes) {
                String res = XML.SaveDocucument(doc, PathXML);
                setAimXMLsaveResult(res);
                if ("OK".equals(res)) {
                } else {
                    throw new AimException(res);
                }
            } else {
                setAimXMLsaveResult("XML Saving operation is Unsuccessful (Method Name; saveToFile): " + getAimXMLsaveResult());
                throw new AimException("XML Saving operation is Unsuccessful (Method Name; saveToFile): "
                        + getAimXMLsaveResult());
            }
        } catch (AimException ex) {
            setAimXMLsaveResult("XML Saving operation is Unsuccessful (Method Name; saveToFile): " + ex.getMessage());
            throw new AimException("XML Saving operation is Unsuccessful (Method Name; saveToFile): " + ex.getMessage());
        }
    }

    public static String convertToString(ImageAnnotationCollection Anno) throws AimException {
        try {
        	Anno=fixVersionChangeIssues(Anno);
        	Document doc = XML.createDocument();
            Element root = (Element) Anno.getXMLNode(doc);
            XML.setBaseAttributes(root);
            doc.appendChild(root);
            
            // set up a transformer
            TransformerFactory transfac = TransformerFactory.newInstance();
            Transformer trans;
			try {
				trans = transfac.newTransformer();
				trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
				trans.setOutputProperty(OutputKeys.INDENT, "yes");
	            // create string from xml tree
	            StringWriter sw = new StringWriter();
	            StreamResult result = new StreamResult(sw);
	            DOMSource source = new DOMSource(doc);
	            trans.transform(source, result);
	            String xmlString = sw.toString();
	            return xmlString;
            } catch ( TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
            

            
           
        } catch (Exception ex) {
            setAimXMLsaveResult("XML Convertion operation is Unsuccessful (Method Name; convertToString)  ml: " + ex.getMessage());
            throw new AimException("XML Convertion operation is Unsuccessful (Method Name; convertToString)  ml: "
                    + " " + ex.getMessage());
        }
    }

    public static ImageAnnotationCollection saveToServer(ImageAnnotationCollection Anno, String serverUrl, String nameSpace,
            String collection, String PathXSD, String dbUserName, String dbUserPassword) throws AimException {
        
        
        Logger.write("BEFORE saveToServer");
        for(PluginV4 p4:Anno.getPluginCollection().getListPlugin())
        {
            System.out.println(p4.getName());
        }
        
//        PluginV4 pv4 = new PluginV4();
//        pv4.setName("pluginName-3");        
//        Anno.getImageAnnotation().addPlugin(pv4);
        
        
        Logger.write("AFTER saveToServer");
        for(PluginV4 p4:Anno.getPluginCollection().getListPlugin())
        {
            System.out.println(p4.getName());
        }
        
        Logger.write(" ");
        Logger.write(" ");
        Logger.write("**************** API VERSION " + apiVersion + " *****************");
        
        
        if (Anno.getImageAnnotation().getUniqueIdentifier() == null || "".equals(Anno.getImageAnnotation().getUniqueIdentifier().getRoot())) {
            Anno.getImageAnnotation().refreshUniqueIdentifier();
        }

        if (PathXSD != null && !"".equals(Globals.getXSDPath())) {
            PathXSD = Globals.getXSDPath();
        }
        boolean checkTheServer = true;
        boolean withAuditTrail = true;
        String operation = "Saving";
        try {

            if (Anno.getDescription() != null && Anno.getDescription().getValue() != null && Anno.getDescription().getValue().contains(Globals.flagDeleted)) {
                withAuditTrail = false;
            }

            ImageAnnotationCollection iacDatabase = getImageAnnotationCollectionByUniqueIdentifier(serverUrl, nameSpace, collection,
                    dbUserName, dbUserPassword, Anno.getUniqueIdentifier().getRoot());

            if (iacDatabase != null && iacDatabase.getDescription() != null && iacDatabase.getDescription().getValue() != null && iacDatabase.getDescription().getValue().contains(Globals.flagDeleted)) {
                withAuditTrail = false;
            }

            if (withAuditTrail) {
                AuditTrailManager auditTrailManager = new AuditTrailManager(serverUrl, nameSpace, collection, dbUserName, dbUserPassword, PathXSD);
                Anno = auditTrailManager.performV4(Anno);
            } else {
                performUploadExist(Anno, serverUrl, collection, "AIM_" + Anno.getUniqueIdentifier().getRoot() + ".xml", PathXSD,
                        dbUserName, dbUserPassword);
            }

            if (checkTheServer) {
                if (withAuditTrail && AnnotationGetter.isExistInTheServer(serverUrl, nameSpace, collection, dbUserName, dbUserPassword, Anno
                        .getUniqueIdentifier().getRoot())) {
                    setAimXMLsaveResult("XML " + operation + " operation is Successful.");
                } else if (!withAuditTrail && AnnotationGetter.isExistInTheServerPlus(serverUrl, nameSpace, collection, dbUserName, dbUserPassword, Anno
                        .getUniqueIdentifier().getRoot())) {
                    setAimXMLsaveResult("XML " + operation + " operation is Successful.");
                } else {
                    setAimXMLsaveResult("XML " + operation + " operation is Unsuccessful (Method Name; saveToServer)");
                    throw new AimException("XML " + operation + " operation is Unsuccessful (Method Name; saveToServer)");
                }

            }
        } catch (Exception ex) {
            setAimXMLsaveResult("XML " + operation + " operation is Unsuccessful (Method Name; saveToServer): "
                    + ex.getMessage());
            throw new AimException("XML " + operation + " operation is Unsuccessful (Method Name; saveToServer): "
                    + ex.getMessage());
        }

        return Anno;
    }

    public static void saveNode(Node node, String Path) throws AimException {
        try {
            Document doc = XML.createDocument();
            Node nodeCopy = doc.importNode(node, true);
            doc.appendChild(nodeCopy);
            String res = XML.SaveDocucument(doc, Path);
            setAimXMLsaveResult(res);
            if ("OK".equals(res)) {
            } else {
                throw new AimException(res);
            }
        } catch (Exception ex) {
            setAimXMLsaveResult(ex.getMessage());
            setAimXMLsaveResult("XML Saving operation is Unsuccessful (Method Name; saveNode): " + getAimXMLsaveResult());
            throw new AimException("XML Saving operation is Unsuccessful (Method Name; saveNode): " + getAimXMLsaveResult());
        }
    }

    public static void performUploadExist(ImageAnnotationCollection Anno, String Url, String Collection,
            String FileName, String PathXSD, String userName, String password) throws AimException {

        if (PathXSD != null && !"".equals(Globals.getXSDPath())) {
            PathXSD = Globals.getXSDPath();
        }

        Document doc = XML.createDocument();
        Element root = (Element) Anno.getXMLNode(doc);
        XML.setBaseAttributes(root);
        doc.appendChild(root);
        boolean valRes = AnnotationValidator.ValidateXML(doc, PathXSD);
        setAimXMLsaveResult(AnnotationValidator.getValidationResult());
        if (!valRes) {
            throw new AimException(AnnotationValidator.getValidationResult());
        }
        String uploadResult = ExistManager.performUploadExist(doc, Url, Collection, FileName, userName, password);

        if (!"OK".equals(uploadResult)) {
            setAimXMLsaveResult("XML Saving operation is Unsuccessful (Method Name; performUploadExist): " + uploadResult);
            throw new AimException("XML Saving operation is Unsuccessful (Method Name; performUploadExist): " + uploadResult);
        }
    }
}
