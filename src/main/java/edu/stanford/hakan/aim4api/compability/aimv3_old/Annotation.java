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
 package edu.stanford.hakan.aim4api.compability.aimv3_old;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import edu.stanford.hakan.aim4api.utility.GenerateId;
import java.io.Serializable;

/**
 *
 * @author Hakan BULU
 */
//@SuppressWarnings("serial")
public class Annotation implements Serializable {

    private CalculationCollection calculationCollection = new CalculationCollection();
    private InferenceCollection inferenceCollection = new InferenceCollection();
    private AnatomicEntityCollection anatomicEntityCollection = new AnatomicEntityCollection();
    private ImagingObservationCollection imagingObservationCollection = new ImagingObservationCollection();
    private List<User> listUser = new ArrayList<User>();
    private List<Equipment> listEquipment = new ArrayList<Equipment>();
    private List<AimStatus> listAimStatus = new ArrayList<AimStatus>();
    private Integer cagridId;
    private String aimVersion;
    private String comment;
    private String dateTime;
    private String name;
    private String uniqueIdentifier;
    private String codeValue;
    private String codeMeaning;
    private String codingSchemeDesignator;
    private String codingSchemeVersion;
    private String precedentReferencedAnnotationUID;
    private String xsiType;
    private String OntologyPrefix;

    public Annotation intitalState = null;
    private String accessKey = "al536anhb55555";

    public Annotation() {
        this.uniqueIdentifier = GenerateId.getUUID();
        this.aimVersion = "AIM.3.0";
    }

    public Annotation(Integer cagridId, String comment, String dateTime, String name, String codeValue, String codeMeaning, String codingSchemeDesignator, String codingSchemeVersion, String precedentReferencedAnnotationUID) {
        this.cagridId = cagridId;
        this.aimVersion = "AIM.3.0";
        this.comment = comment;
        this.dateTime = dateTime;
        this.name = name;
        this.uniqueIdentifier = GenerateId.getUUID();
        this.codeValue = codeValue;
        this.codeMeaning = codeMeaning;
        this.codingSchemeDesignator = codingSchemeDesignator;
        this.codingSchemeVersion = codingSchemeVersion;
        this.precedentReferencedAnnotationUID = precedentReferencedAnnotationUID;
    }

    public void addCalculation(Calculation newCalculation) {
        this.calculationCollection.AddCalculation(newCalculation);
    }

    public void addInference(Inference newInference) {
        this.inferenceCollection.AddInference(newInference);
    }

    public void addUser(User newUser) {
        this.listUser.add(newUser);
    }

    public void addEquipment(Equipment newEquipment) {
        this.listEquipment.add(newEquipment);
    }

    public void addAnatomicEntity(AnatomicEntity newAnatomicEntity) {
        this.anatomicEntityCollection.AddAnatomicEntity(newAnatomicEntity);
    }

    public void addImagingObservation(ImagingObservation newImagingObservation) {
        this.imagingObservationCollection.AddImagingObservation(newImagingObservation);
    }

    public void addAimStatus(AimStatus newAimStatus) {
        this.listAimStatus.add(newAimStatus);
    }

    public String getAimVersion() {
        return aimVersion;
    }

    public void setAimVersion(String aimVersion, String accessKey) {
        if (!accessKey.equals(this.accessKey)) {
            return;
        }
        this.aimVersion = aimVersion;
    }

    public AnatomicEntityCollection getAnatomicEntityCollection() {
        return anatomicEntityCollection;
    }

    public void setAnatomicEntityCollection(AnatomicEntityCollection anatomicEntityCollection) {
        this.anatomicEntityCollection = anatomicEntityCollection;
    }

    public Integer getCagridId() {
        return cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    public CalculationCollection getCalculationCollection() {
        return calculationCollection;
    }

    public void setCalculationCollection(CalculationCollection calculationCollection) {
        this.calculationCollection = calculationCollection;
    }

    public String getCodeMeaning() {
        return codeMeaning;
    }

    public void setCodeMeaning(String codeMeaning) {
        this.codeMeaning = codeMeaning;
    }

    public String getCodeValue() {
        return codeValue;
    }
   
    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodingSchemeDesignator() {
        return codingSchemeDesignator;
    }

    public void setCodingSchemeDesignator(String codingSchemeDesignator) {
        this.codingSchemeDesignator = codingSchemeDesignator;
    }

    public String getCodingSchemeVersion() {
        return codingSchemeVersion;
    }

    public void setCodingSchemeVersion(String codingSchemeVersion) {
        this.codingSchemeVersion = codingSchemeVersion;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public ImagingObservationCollection getImagingObservationCollection() {
        return imagingObservationCollection;
    }

    public void setImagingObservationCollection(ImagingObservationCollection imagingObservationCollection) {
        this.imagingObservationCollection = imagingObservationCollection;
    }

    public InferenceCollection getInferenceCollection() {
        return inferenceCollection;
    }

    public void setInferenceCollection(InferenceCollection inferenceCollection) {
        this.inferenceCollection = inferenceCollection;
    }

    public List<AimStatus> getListAimStatus() {
        return listAimStatus;
    }

    public void setListAimStatus(List<AimStatus> listAimStatus) {
        this.listAimStatus = listAimStatus;
    }

    public List<Equipment> getListEquipment() {
        return listEquipment;
    }

    public void setListEquipment(List<Equipment> listEquipment) {
        this.listEquipment = listEquipment;
    }

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrecedentReferencedAnnotationUID() {
        return precedentReferencedAnnotationUID;
    }

    public void setPrecedentReferencedAnnotationUID(String precedentReferencedAnnotationUID) {
        this.precedentReferencedAnnotationUID = precedentReferencedAnnotationUID;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier, String accessKey) {
        if (!accessKey.equals(this.accessKey)) {
            return;
        }
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public void refreshUniqueIdentifier() {
        this.uniqueIdentifier = GenerateId.getUUID();
    }    

    public void setOntologyPrefix(String Prefix) {
        this.OntologyPrefix = Prefix;
    }

    public String getOntologyPrefix() {
        return this.OntologyPrefix;
    }

    protected void setXsiType(String xsiType) {
        this.xsiType = xsiType;
    }

    public String getXsiType() {
        return xsiType;
    }  
    
    public void setXMLNode(Node node) {

        NamedNodeMap map = node.getAttributes();
        this.aimVersion = map.getNamedItem("aimVersion").getNodeValue();

        if ("TCGA".equals(this.aimVersion) || "2.0".equals(this.aimVersion) || "1.0".equals(this.aimVersion)) {
            node = AnnotationConverter.annotationTCGA2V3(node);
            map = node.getAttributes();
            this.aimVersion = "AIM.3.0";
        }

        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.dateTime = map.getNamedItem("dateTime").getNodeValue();
        this.name = map.getNamedItem("name").getNodeValue();
        this.uniqueIdentifier = map.getNamedItem("uniqueIdentifier").getNodeValue();
        this.codeValue = map.getNamedItem("codeValue").getNodeValue();
        this.codeMeaning = map.getNamedItem("codeMeaning").getNodeValue();
        this.codingSchemeDesignator = map.getNamedItem("codingSchemeDesignator").getNodeValue();

        if (map.getNamedItem("comment") != null) {
            this.comment = map.getNamedItem("comment").getNodeValue();
        }
        if (map.getNamedItem("codingSchemeVersion") != null) {
            this.codingSchemeVersion = map.getNamedItem("codingSchemeVersion").getNodeValue();
        }
        if (map.getNamedItem("precedentReferencedAnnotationUID") != null) {
            this.precedentReferencedAnnotationUID = map.getNamedItem("precedentReferencedAnnotationUID").getNodeValue();
        }

        this.listAimStatus.clear();
        this.listEquipment.clear();
        this.listUser.clear();
        NodeList listChils = node.getChildNodes();
        for (int i = 0; i < listChils.getLength(); i++) {
            if ("calculationCollection".equals(listChils.item(i).getNodeName())) {
                this.calculationCollection.setXMLNode(listChils.item(i));
            } else if ("inferenceCollection".equals(listChils.item(i).getNodeName())) {
                this.inferenceCollection.setXMLNode(listChils.item(i));
            } else if ("anatomicEntityCollection".equals(listChils.item(i).getNodeName())) {
                this.anatomicEntityCollection.setXMLNode(listChils.item(i));
            } else if ("imagingObservationCollection".equals(listChils.item(i).getNodeName())) {
                this.imagingObservationCollection.setXMLNode(listChils.item(i));
            } else if ("user".equals(listChils.item(i).getNodeName())) {
                NodeList tempList = listChils.item(i).getChildNodes();
                for (int j = 0; j < tempList.getLength(); j++) {
                    if ("User".equals(tempList.item(j).getNodeName())) {
                        User obj = new User();
                        obj.setXMLNode(tempList.item(j));
                        this.addUser(obj);
                    }
                }
            } else if ("equipment".equals(listChils.item(i).getNodeName())) {
                NodeList tempList = listChils.item(i).getChildNodes();
                for (int j = 0; j < tempList.getLength(); j++) {
                    if ("Equipment".equals(tempList.item(j).getNodeName())) {
                        Equipment obj = new Equipment();
                        obj.setXMLNode(tempList.item(j));
                        this.addEquipment(obj);
                    }
                }
            } else if ("aimStatus".equals(listChils.item(i).getNodeName())) {
                NodeList tempList = listChils.item(i).getChildNodes();
                for (int j = 0; j < tempList.getLength(); j++) {
                    if ("AimStatus".equals(tempList.item(j).getNodeName())) {
                        AimStatus obj = new AimStatus();
                        obj.setXMLNode(tempList.item(j));
                        this.addAimStatus(obj);
                    }
                }
            }
        }
    }
}
