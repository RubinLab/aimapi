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

import java.io.StringWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import main.java.edu.stanford.hakan.aim4api.base.AimException;
import main.java.edu.stanford.hakan.aim4api.base.ImageAnnotationCollection;
import main.java.edu.stanford.hakan.aim4api.database.exist.ExistManager;
import main.java.edu.stanford.hakan.aim4api.utility.XML;

/**
 *
 * @author Hakan BULU
 */
public class AnnotationBuilder {

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
            clearAimXMLsaveResult();
            Document doc = XML.createDocument();
            Element root = (Element) Anno.getXMLNode(doc);
            XML.setBaseAttributes(root);
//            root.setAttribute("xmlns", "gme://caCORE.caCORE/4.4/edu.northwestern.radiology.AIM");
//            root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
//            root.setAttribute("xsi:schemaLocation",
//                    "gme://caCORE.caCORE/4.4/edu.northwestern.radiology.AIM AIM_v4_rv44_XML.xsd");
//            root.setAttribute("xmlns:rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
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
        } catch (Exception ex) {
            setAimXMLsaveResult("XML Saving operation is Unsuccessful (Method Name; saveToFile): " + ex.getMessage());
            throw new AimException("XML Saving operation is Unsuccessful (Method Name; saveToFile): " + ex.getMessage());
        }
    }

    public static String convertToString(ImageAnnotationCollection Anno) throws AimException {
        try {
            Document doc = XML.createDocument();
            Element root = (Element) Anno.getXMLNode(doc);
            XML.setBaseAttributes(root);
//            root.setAttribute("xmlns", "gme://caCORE.caCORE/4.4/edu.northwestern.radiology.AIM");
//            root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
//            root.setAttribute("xsi:schemaLocation",
//                    "gme://caCORE.caCORE/4.4/edu.northwestern.radiology.AIM AIM_v4_rv44_XML.xsd");
//            root.setAttribute("xmlns:rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
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

    public static void saveToServer(ImageAnnotationCollection Anno, String serverUrl, String nameSpace,
            String collection, String PathXSD, String dbUserName, String dbUserPassword) throws AimException {

        String operation = "Saving";
        try {
            performUploadExist(Anno, serverUrl, collection, "AIM_" + Anno.getUniqueIdentifier().getRoot() + ".xml", PathXSD,
                    dbUserName, dbUserPassword);

            if (AnnotationGetter.isExistInTheServer(serverUrl, nameSpace, collection, dbUserName, dbUserPassword, Anno
                    .getUniqueIdentifier().getRoot())) {
                setAimXMLsaveResult("XML " + operation + " operation is Successful.");
            } else {
                setAimXMLsaveResult("XML " + operation + " operation is Unsuccessful (Method Name; saveToServer)");
                throw new AimException("XML " + operation + " operation is Unsuccessful (Method Name; saveToServer)");
            }
        } catch (Exception ex) {
            setAimXMLsaveResult("XML " + operation + " operation is Unsuccessful (Method Name; saveToServer): "
                    + ex.getMessage());
            throw new AimException("XML " + operation + " operation is Unsuccessful (Method Name; saveToServer): "
                    + ex.getMessage());
        }
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

    private static void performUploadExist(ImageAnnotationCollection Anno, String Url, String Collection,
            String FileName, String PathXSD, String userName, String password) throws AimException {

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
