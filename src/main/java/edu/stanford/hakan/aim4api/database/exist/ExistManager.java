/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.database.exist;

import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.base.ImageAnnotationCollection;
import edu.stanford.hakan.aim4api.usage.AnnotationGetter;
import static edu.stanford.hakan.aim4api.usage.AnnotationGetter.getValidationResult;
import static edu.stanford.hakan.aim4api.usage.AnnotationGetter.setValidationResult;
import edu.stanford.hakan.aim4api.usage.AnnotationValidator;
import edu.stanford.hakan.aim4api.utility.Utility;
import edu.stanford.hakan.aim4api.utility.XML;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author localadmin
 */
public class ExistManager {
    
     public static String getXMLStringFromExist(String Url, String XQuery, String dbUserName, String dbUserPassword)
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
     
     
     public static String getXMLStringFromExistWithStartIndexEndIndex(String Url, String XQuery, String dbUserName, String dbUserPassword, int startIndex, int endIndex)
            throws AimException {
        try {
            Url = Utility.correctToUrl(Url);
            String requestURL = Url + "rest/";
            String data = "";

            if (startIndex <= 0) {
                startIndex = 1;
            }
            if (endIndex <= startIndex) {
                throw new AimException("AimException: endIndex must be greater than startIndex");
            }
            int count = endIndex - startIndex + 1;
            data += "<?xml version='1.0' encoding='UTF-8'?>";
            data += "<query xmlns='http://exist.sourceforge.net/NS/exist' start='" + Integer.toString(startIndex) + "' max='" + Integer.toString(count) + "'>";
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
     
     public static String getXMLStringFromExistWithStartIndexCount(String Url, String XQuery, String dbUserName, String dbUserPassword, int startIndex, int count)
            throws AimException {
        try {
            Url = Utility.correctToUrl(Url);
            String requestURL = Url + "rest/";
            String data = "";

            if (startIndex <= 0) {
                startIndex = 1;
            }
            data += "<?xml version='1.0' encoding='UTF-8'?>";
            data += "<query xmlns='http://exist.sourceforge.net/NS/exist' start='" + Integer.toString(startIndex) + "' max='" + Integer.toString(count) + "'>";
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
    
    public static String performUploadExist(Document doc, String Url, String Collection,
            String FileName, String userName, String password) {
        try {
            String res = "";
            Url = Utility.correctToUrl(Url);
            URL url = new URL(Url + "rest/db/" + Collection + "/" + FileName);
            URLConnection conn = url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);

            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).setRequestMethod("PUT");
                ((HttpURLConnection) conn).setRequestProperty("Content-Type", "application/xml");
                if (!"".equals(userName.trim()) || !"".equals(password.trim())) {
                    String userPassword = userName + ":" + password;
                    String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
                    ((HttpURLConnection) conn).setRequestProperty("Authorization", "Basic " + encoding);
                }
                ((HttpURLConnection) conn).connect();
            }

            BufferedOutputStream bos = new BufferedOutputStream(conn.getOutputStream());

//            Document doc = createDocument();
//            root.setAttribute("xmlns", "gme://caCORE.caCORE/4.4/edu.northwestern.radiology.AIM");
//            root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
//            root.setAttribute("xsi:schemaLocation",
//                    "gme://caCORE.caCORE/4.4/edu.northwestern.radiology.AIM AIM_v4_rv44_XML.xsd");
//            root.setAttribute("xmlns:rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
//            doc.appendChild(root);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Source xmlSource = new DOMSource(doc);
            Result outputTarget = new StreamResult(outputStream);
            TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
            InputStream is = new ByteArrayInputStream(outputStream.toByteArray());

            BufferedInputStream bis = new BufferedInputStream(is);

            int i;
            while ((i = bis.read()) >= 0) {
                bos.write(i);
            }

            bos.flush();
            bos.close();
            bis.close();

            ((HttpURLConnection) conn).getResponseCode();
            ((HttpURLConnection) conn).disconnect();
            return "OK";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    public static void deleteImageAnnotationFromServer(String serverURL, String namespace, String collection,
            String PathXSD, String dbUserName, String dbUserPassword, String uniqueIdentifier) throws AimException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
        public static List<ImageAnnotationCollection> getImageAnnotationCollectionListFromDocument(Document doc,
            String PathXSD) throws AimException {
        List<ImageAnnotationCollection> res = new ArrayList<ImageAnnotationCollection>();
        try {
            Node firstNode = doc.getFirstChild();
            List<Node> listNodeImageAnnotationCollections = XML.getNodesFromNodeByName(firstNode, "ImageAnnotationCollection");
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
                    XML.setBaseAttributes(root);
//                    root.setAttribute("xmlns", "gme://caCORE.caCORE/4.4/edu.northwestern.radiology.AIM");
//                    root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
//                    root.setAttribute("xsi:schemaLocation","gme://caCORE.caCORE/4.4/edu.northwestern.radiology.AIM AIM_v4_rv44_XML.xsd");
//                    root.setAttribute("xmlns:rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
                    docValidation.appendChild(root);
                    boolean valRes = AnnotationValidator.ValidateXML(docValidation, PathXSD);
                    setValidationResult(AnnotationValidator.getValidationResult());
                    if (!valRes) {
                        throw new AimException(getValidationResult());
                    }
                }
                res.add(imageAnnotationCollection);
            }
        } catch (Exception ex) {
            throw new AimException(ex.getMessage());
        }
        return res;
    }

    public static String getExistResultValueFromDocument(Document doc) {
        Node firstNode = doc.getFirstChild();
        List<Node> listResult = XML.getNodesFromNodeByName(firstNode, "exist:result");
        if (listResult.size() > 0) {
            return listResult.get(0).getTextContent().trim();
        }
        return "";
    }

    public static int getHitsCountFromDocument(Document doc) {
        Node firstNode = doc.getFirstChild();
        List<Node> listResult = XML.getNodesFromNodeByName(firstNode, "exist:result");
        if (listResult.size() > 0) {
            NamedNodeMap namedNodeMap = listResult.get(0).getAttributes();
            if (namedNodeMap != null) {
                String hits = namedNodeMap.getNamedItem("exist:hits").getNodeValue();
                try {
                    return Integer.parseInt(hits);
                } catch (Exception ex) {
                    return -1;
                }
            }
        }
        return -1;
    }

    public static  List<String> getExistResultValueListFromDocument(Document doc) {
         List<String> res = new ArrayList<String>();
        Node firstNode = doc.getFirstChild();
        List<Node> listResult = XML.getNodesFromNodeByName(firstNode, "exist:value");
        for (int i = 0; i < listResult.size(); i++) {
            res.add(listResult.get(i).getTextContent());
        }
//        if (listResult.size() > 0) {
//            return listResult.get(0).getTextContent().trim();
//        }
        return res;
    }

   
}
