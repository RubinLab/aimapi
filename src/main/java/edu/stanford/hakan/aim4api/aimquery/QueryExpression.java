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
package edu.stanford.hakan.aim4api.aimquery;

import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.utility.EPADConfig;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Hakan
 */
public class QueryExpression {

    private static final String aimQLXmlFilePath = EPADConfig.getInstance().getStringPropertyValue("baseSchemaDir") + EPADConfig.getInstance().getStringPropertyValue("aimQLXml");
    
    
    private String expression = "";
    private String leftSide = "";
    private String value = "";
    private String function = "";
    private List<String> listXQuery;
    private List<AimClass> listAimClass;

    public QueryExpression(String expression, String leftSide, String function, String value) throws AimException {
        this.listXQuery = new ArrayList<String>();
        this.listAimClass = new ArrayList<AimClass>();
        this.expression = expression;
        this.leftSide = leftSide;
        this.function = function;
        this.value = value;
        this.fillTheListAimClasses();
        this.fillTheListXQuery();
        // this.ControlTheAimClasses();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(String leftSide) {
        this.leftSide = leftSide;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public List<String> getListXQuery() {
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < this.listXQuery.size(); i++) {
            if (this.listXQuery.get(i).startsWith("ImageAnnotationCollection")) {
                String xQuery = this.listXQuery.get(i);
                xQuery = xQuery.replace("ImageAnnotationCollection", "$x");
                if (xQuery.endsWith("/")) {
                    xQuery = xQuery.substring(0, xQuery.length() - 1);
                }
                res.add(xQuery);
            }
        }
        return res;
    }

    public void setListXQuery(List<String> listXQuery) {
        this.listXQuery = listXQuery;
    }

    private void fillTheListXQuery() throws AimException {
        this.listXQuery.clear();
        String[] arrayLeftSide = this.leftSide.split("\\.");

        // *** Controls
        if (arrayLeftSide.length <= 1) {
            throw new AimException("AimException: Each expression in an AimQuery must start with an aimClass.");
        }
        AimClass startedClass = getAimClassByName(arrayLeftSide[0]);
        if (startedClass == null || !"aim".equals(startedClass.getType())) {
            throw new AimException("AimException: Each expression in an AimQuery must start with an aimClass.");
        }

        AimClass currentAimClass = null;
        for (int i = 0; i < arrayLeftSide.length; i++) {
            // *** aimClass
            AimClass aimClass = getAimClassByName(arrayLeftSide[i]);
            if (aimClass != null && "aim".equals(aimClass.getType())) {
                currentAimClass = aimClass;
                this.listXQuery = currentAimClass.getListXPaths();
            }
            AimProperty aimProperty = currentAimClass.getAimPropertyByName(arrayLeftSide[i]);
            if (aimProperty != null) {
                if (aimProperty.getIsa().equals("aimClass")) {
                    currentAimClass = getAimClassByName(aimProperty.getType());
                    this.listXQuery = currentAimClass.getListXPaths();

                } else if (aimProperty.getIsa().equals("isoClass")) {
                    for (int j = 0; j < this.listXQuery.size(); j++) {
                        String xPath = this.listXQuery.get(j);
                        this.listXQuery.set(j, xPath + aimProperty.getName() + "/");
                    }
                    currentAimClass = getAimClassByName(aimProperty.getType());
                } else if (aimProperty.getIsa().equals("simpleElement")) {
                    if (i != arrayLeftSide.length - 1) {
                        throw new AimException(
                                "AimException: Each expression in an AimQuery must end with a simple element or attribute.");
                    }
                    for (int j = 0; j < this.listXQuery.size(); j++) {
                        String xPath = this.listXQuery.get(j);
                        if (xPath.endsWith("/")) {
                            xPath = xPath.substring(0, xPath.length() - 1);
                        }
                        this.listXQuery.set(j, xPath + getXpathAttribute("value") + "/");
                    }
                } else if (aimProperty.getIsa().equals("simpleAttribute")) {
                    if (i != arrayLeftSide.length - 1) {
                        throw new AimException(
                                "AimException: Each expression in an AimQuery must end with a simple element or attribute.");
                    }
                    for (int j = 0; j < this.listXQuery.size(); j++) {
                        String xPath = this.listXQuery.get(j);
                        if (xPath.endsWith("/")) {
                            xPath = xPath.substring(0, xPath.length() - 1);
                        }
                        this.listXQuery.set(j, xPath + getXpathAttribute(aimProperty.getName()) + "/");
                    }
                }
            }
        }
    }

    private Element getDocumentElement(String xmlPath) throws AimException {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(xmlPath));
            doc.getDocumentElement().normalize();
            return doc.getDocumentElement();
        } catch (ParserConfigurationException ex) {
            throw new AimException("AimException: " + ex.getMessage());
        } catch (SAXException ex) {
            throw new AimException("AimException: " + ex.getMessage());
        } catch (IOException ex) {
            throw new AimException("AimException: " + ex.getMessage());
        }
    }

    private void fillTheListAimClasses() throws AimException {
    	
    	Boolean runningOnTheServer = true;
    	
    	Element docElement = null;
    	if(!runningOnTheServer) {
    		URL xmlPath = getClass().getResource("AimXPath.xml");
    		docElement = getDocumentElement(xmlPath.getFile());
    	}
    	else { 		
    		docElement = getDocumentElement(aimQLXmlFilePath);
    	}      
        
        NodeList listAimStructure = docElement.getChildNodes();
        for (int i = 0; i < listAimStructure.getLength(); i++) {
            if ("Class".equals(listAimStructure.item(i).getNodeName())) {
                Node nodeClass = listAimStructure.item(i);
                NamedNodeMap mapClass = nodeClass.getAttributes();
                String className = mapClass.getNamedItem("name").getNodeValue();
                String classType = mapClass.getNamedItem("type").getNodeValue();
                boolean isAbstract = Boolean.parseBoolean(mapClass.getNamedItem("abstract").getNodeValue());
                AimClass aimClass = new AimClass(className, classType, isAbstract);
                NodeList listClass = nodeClass.getChildNodes();
                for (int j = 0; j < listClass.getLength(); j++) {
                    if (listClass.item(j).getNodeName().equals("Paths")) {
                        NodeList listPaths = listClass.item(j).getChildNodes();
                        for (int k = 0; k < listPaths.getLength(); k++) {
                            if ("Path".equals(listPaths.item(k).getNodeName())) {
                                NamedNodeMap mapPath = listPaths.item(k).getAttributes();
                                aimClass.getListXPaths().add(mapPath.getNamedItem("value").getNodeValue());
                            }
                        }
                    } else if (listClass.item(j).getNodeName().equals("Properties")) {
                        NodeList listProperties = listClass.item(j).getChildNodes();
                        for (int k = 0; k < listProperties.getLength(); k++) {
                            if ("Property".equals(listProperties.item(k).getNodeName())) {
                                NamedNodeMap mapProperty = listProperties.item(k).getAttributes();
                                AimProperty aimProperty = new AimProperty(mapProperty.getNamedItem("name").getNodeValue(), mapProperty
                                        .getNamedItem("type").getNodeValue(), mapProperty.getNamedItem("isa").getNodeValue());
                                aimClass.getListAimProperties().add(aimProperty);
                            }
                        }
                    }
                }
                this.listAimClass.add(aimClass);
            }
        }
    }

    private AimClass getAimClassByName(String name) {
        for (int i = 0; i < this.listAimClass.size(); i++) {
            if (this.listAimClass.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                return this.listAimClass.get(i);
            }
        }
        return null;
    }

    private String getXpathAttribute(String attributeName) throws AimException {
        if ("<>".equals(function.trim())) {
            function = "!=";
        }
        if ("like".equals(function.toLowerCase(new Locale("\\u0131")).trim())) {
            if ((value.indexOf("%") < 0) || (value.indexOf("'%") >= 0 && value.indexOf("%'") >= 0)) {
                return "[contains(lower-case(@" + attributeName + "),lower-case(" + value + "))]".replace("%", "");
            } else if (value.indexOf("'%") >= 0) {
                return "[starts-with(lower-case(@" + attributeName + "),lower-case(" + value + "))]".replace("%", "");
            } else if (value.indexOf("%'") >= 0) {
                return "[ends-with(lower-case(@" + attributeName + "),lower-case(" + value + "))]".replace("%", "");
            } else {
                return "[contains(lower-case(@" + attributeName + "),lower-case(" + value + "))]".replace("%", "");
            }
        } else if (value.trim().startsWith("'")) {
            return "[lower-case(@" + attributeName + ") " + function + " lower-case(" + value + ")]".replace("%", "");
        } else {
            return "[@" + attributeName + " " + function + " " + value + "]".replace("%", "");
        }
    }

    private void ControlTheAimClasses() {
        for (int i = 0; i < this.listAimClass.size(); i++) {
            AimClass currentClass = this.listAimClass.get(i);
            if ("aim".equals(currentClass.getType())) {
                List<String> listXQuerysClass = currentClass.getListXPaths();
                List<AimProperty> listProperties = currentClass.getListAimProperties();
                for (int j = 0; j < listProperties.size(); j++) {
                    AimProperty currentProperty = listProperties.get(j);
                    if ("aimClass".equals(currentProperty.getIsa())) {
                        AimClass classProperty = this.getAimClassByName(currentProperty.getType());
                        List<String> listXQuerysProperty = classProperty.getListXPaths();
                        if (listXQuerysClass.size() != listXQuerysProperty.size()) {
                        }
                    }
                }
            }
        }
    }
}
