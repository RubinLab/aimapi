/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.edu.stanford.hakan.aim4api.utility;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import main.java.edu.stanford.hakan.aim4api.base.AimException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Hakan
 */
public class XML {

    public static Document createDocument() {
        try {
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            return doc;
        } catch (Exception ex) {
            return null;
        }
    }

    public static String SaveDocucument(Document doc, String Path) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Path));
            transformer.transform(source, result);
            return "OK";
        } catch (TransformerFactoryConfigurationError ex) {
            return "XML Saving operation is Unsuccessful (Method Name; SaveDocucument): " + ex.getMessage();
        } catch (IllegalArgumentException ex) {
            return "XML Saving operation is Unsuccessful (Method Name; SaveDocucument): " + ex.getMessage();
        } catch (TransformerException ex) {
            return "XML Saving operation is Unsuccessful (Method Name; SaveDocucument): " + ex.getMessage();
        }
    }

    public static Document getDocumentFromString(String xml) throws AimException {
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

    public static List<Node> getNodesFromNodeByName(Node node, String nodeName) {
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

    public static void setBaseAttributes(Element root) {
        root.setAttribute("xmlns", "gme://caCORE.caCORE/4.4/edu.northwestern.radiology.AIM");
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xsi:schemaLocation", "gme://caCORE.caCORE/4.4/edu.northwestern.radiology.AIM AIM_v4_rv44_XML.xsd");
        root.setAttribute("xmlns:rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
    }
}
