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
import edu.stanford.hakan.aim4api.base.ImageAnnotationCollection;
import edu.stanford.hakan.aim4api.base.TwoDimensionMultiPoint;
import edu.stanford.hakan.aim4api.database.exist.ExistManager;
import edu.stanford.hakan.aim4api.plugin.v4.PluginV4;
import edu.stanford.hakan.aim4api.resources.Resource;
import static edu.stanford.hakan.aim4api.usage.AnnotationGetter.getImageAnnotationCollectionByUniqueIdentifier;
import edu.stanford.hakan.aim4api.utility.Globals;
import edu.stanford.hakan.aim4api.utility.Logger;
import edu.stanford.hakan.aim4api.utility.XML;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
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

    public static void saveToFile(ImageAnnotationCollection Anno, String PathXML, String PathXSD) throws AimException {
        try {
            if (PathXSD != null && !"".equals(Globals.getXSDPath())) {
                PathXSD = Globals.getXSDPath();
            }

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
            Document doc = XML.createDocument();
            Element root = (Element) Anno.getXMLNode(doc);
            XML.setBaseAttributes(root);
            doc.appendChild(root);

            // set up a transformer
            TransformerFactory transfac = TransformerFactory.newInstance();
            Transformer trans = transfac.newTransformer();
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            // create string from xml tree
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);
            trans.transform(source, result);
            String xmlString = sw.toString();
            return xmlString;
        } catch (Exception ex) {
            setAimXMLsaveResult("XML Convertion operation is Unsuccessful (Method Name; convertToString): " + ex.getMessage());
            throw new AimException("XML Convertion operation is Unsuccessful (Method Name; convertToString): "
                    + ex.getMessage());
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
