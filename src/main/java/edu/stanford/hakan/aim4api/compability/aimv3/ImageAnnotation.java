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
package edu.stanford.hakan.aim4api.compability.aimv3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.stanford.hakan.aim4api.addition.AllowedTerm;
import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.CD;
import edu.stanford.hakan.aim4api.base.II;
import edu.stanford.hakan.aim4api.plugin.v3.PluginCollectionV3;
import edu.stanford.hakan.aim4api.plugin.v3.PluginV3;
import edu.stanford.hakan.aim4api.utility.GenerateId;

/**
 *
 * @author Hakan BULU
 */
@SuppressWarnings("serial")
public class ImageAnnotation extends Annotation implements IAimXMLOperations, Serializable {

    private SegmentationCollection segmentationCollection = new SegmentationCollection();
    private ImageReferenceCollection imageReferenceCollection = new ImageReferenceCollection();
    private GeometricShapeCollection geometricShapeCollection = new GeometricShapeCollection();
    private List<Person> listPerson = new ArrayList<Person>();
    private TextAnnotationCollection textAnnotationCollection = new TextAnnotationCollection();
    private edu.stanford.hakan.aim4api.base.ImageAnnotation imageAnnotationV4 = null;
    //public static String line = "";

    private String iaV3UID ="";
    private PluginCollectionV3 pluginCollection  = new PluginCollectionV3();
    private int dsoStartIndex = -1;
    private String dsoColor = "#FFFFFF";

    public ImageAnnotation() {
        super();
        this.setIAv3UID(GenerateId.getUUID());
        setXsiType("ImageAnnotation");
    }

    public ImageAnnotation(Integer cagridId, String comment, String dateTime, String name, String codeValue, String codeMeaning, String codingSchemeDesignator, String codingSchemeVersion, String precedentReferencedAnnotationUID) {
        super(cagridId, comment, dateTime, name, codeValue, codeMeaning, codingSchemeDesignator, codingSchemeVersion, precedentReferencedAnnotationUID);
        this.setIAv3UID(GenerateId.getUUID());
        setXsiType("ImageAnnotation");
    }

    public GeometricShapeCollection getGeometricShapeCollection() {
        return geometricShapeCollection;
    }

    public void setGeometricShapeCollection(GeometricShapeCollection geometricShapeCollection) {
        this.geometricShapeCollection = geometricShapeCollection;
    }

    public ImageReferenceCollection getImageReferenceCollection() {
        return imageReferenceCollection;
    }

    public void setImageReferenceCollection(ImageReferenceCollection imageReferenceCollection) {
        this.imageReferenceCollection = imageReferenceCollection;
    }

    public List<Person> getListPerson() {
        return listPerson;
    }

    public void setListPerson(List<Person> listPerson) {
        this.listPerson = listPerson;
    }

    public SegmentationCollection getSegmentationCollection() {
        return segmentationCollection;
    }

    public void setSegmentationCollection(SegmentationCollection segmentationCollection) {
        this.segmentationCollection = segmentationCollection;
    }

    public TextAnnotationCollection getTextAnnotationCollection() {
        return textAnnotationCollection;
    }

    public void setTextAnnotationCollection(TextAnnotationCollection textAnnotationCollection) {
        this.textAnnotationCollection = textAnnotationCollection;
    }

    public void addPerson(Person newPerson) {
        this.listPerson.add(newPerson);
    }

    public void addSegmentation(Segmentation newSegmentation) {
        this.segmentationCollection.AddSegmentation(newSegmentation);
    }

    public void addImageReference(ImageReference newSImageReference) {
        this.imageReferenceCollection.AddImageReference(newSImageReference);
    }

    public void addGeometricShape(GeometricShape newGeometricShape) {
        this.geometricShapeCollection.AddGeometricShape(newGeometricShape);
    }

    public void addTextAnnotation(TextAnnotation newTextAnnotation) {
        this.textAnnotationCollection.AddTextAnnotation(newTextAnnotation);
    }

    public String getIAv3UID() {
        return iaV3UID;
    }

    private void setIAv3UID(String iaV3UID) {
        this.iaV3UID = iaV3UID;
    }

    public int getDsoStartIndex() {
        return dsoStartIndex;
    }

    public void setDsoStartIndex(int dsoStartIndex) {
        this.dsoStartIndex = dsoStartIndex;
    }

    public String getDsoColor() {
        return dsoColor;
    }

    public void setDsoColor(String dsoColor) {
        this.dsoColor = dsoColor;
    }
    

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//
//        Element annotation = (Element) super.getXMLNode(doc);
//
//        if (this.getSegmentationCollection().getSegmentationList().size() > 0) {
//            annotation.appendChild(this.getSegmentationCollection().getXMLNode(doc));
//        }
//        if (this.getImageReferenceCollection().getImageReferenceList().size() > 0) {
//            annotation.appendChild(this.getImageReferenceCollection().getXMLNode(doc));
//        }
//        if (this.getGeometricShapeCollection().getGeometricShapeList().size() > 0) {
//            annotation.appendChild(this.getGeometricShapeCollection().getXMLNode(doc));
//        }
//        Element person = doc.createElement("person");
//        for (int i = 0; i < this.getListPerson().size(); i++) {
//            person.appendChild(this.getListPerson().get(i).getXMLNode(doc));
//        }
//        if (this.getListPerson().size() > 0) {
//            annotation.appendChild(person);
//        }
//        if (this.getTextAnnotationCollection().getTextAnnotationList().size() > 0) {
//            annotation.appendChild(this.getTextAnnotationCollection().getXMLNode(doc));
//        }
//        return annotation;
//    }
    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);

        this.listPerson.clear();
        NodeList listChils = node.getChildNodes();
        for (int i = 0; i < listChils.getLength(); i++) {
            if ("segmentationCollection".equals(listChils.item(i).getNodeName())) {
                this.segmentationCollection.setXMLNode(listChils.item(i));
            } else if ("imageReferenceCollection".equals(listChils.item(i).getNodeName())) {
                this.imageReferenceCollection.setXMLNode(listChils.item(i));
            } else if ("geometricShapeCollection".equals(listChils.item(i).getNodeName())) {
                this.geometricShapeCollection.setXMLNode(listChils.item(i));
            } else if ("textAnnotationCollection".equals(listChils.item(i).getNodeName())) {
                this.textAnnotationCollection.setXMLNode(listChils.item(i));
            } else if ("person".equals(listChils.item(i).getNodeName())) {
                NodeList tempList = listChils.item(i).getChildNodes();
                for (int j = 0; j < tempList.getLength(); j++) {
                    if ("Person".equals(tempList.item(j).getNodeName())) {
                        Person obj = new Person();
                        obj.setXMLNode(tempList.item(j));
                        this.addPerson(obj);
                    }
                }
            }
        }
    }

    @Override
    public boolean isEqualTo(Object other) {
        ImageAnnotation oth = (ImageAnnotation) other;
        if (this.listPerson.size() != oth.listPerson.size()) {
            return false;
        }
        for (int i = 0; i < this.listPerson.size(); i++) {
            if (!this.listPerson.get(i).isEqualTo(oth.listPerson.get(i))) {
                return false;
            }
        }
        if (!this.segmentationCollection.isEqualTo(oth.segmentationCollection)) {
            return false;
        }
        if (!this.imageReferenceCollection.isEqualTo(oth.imageReferenceCollection)) {
            return false;
        }
        if (!this.geometricShapeCollection.isEqualTo(oth.geometricShapeCollection)) {
            return false;
        }
        if (!this.textAnnotationCollection.isEqualTo(oth.textAnnotationCollection)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    public edu.stanford.hakan.aim4api.base.ImageAnnotationCollection toAimV4() throws AimException {
        edu.stanford.hakan.aim4api.base.ImageAnnotationCollection iacV4 = new edu.stanford.hakan.aim4api.base.ImageAnnotationCollection();

        iacV4.setUniqueIdentifier(new II(this.getUniqueIdentifier()));
        iacV4.setDateTime(this.getDateTime());//

        if (this.getListEquipment().size() > 0) {//
            iacV4.setEquipment(this.getListEquipment().get(0).toAimV4());
        }
        if (this.getListPerson().size() > 0) {//
            iacV4.setPerson(this.getListPerson().get(0).toAimV4());
        }
        if (this.getListUser().size() > 0) {//
            iacV4.setUser(this.getListUser().get(0).toAimV4());
        }

        edu.stanford.hakan.aim4api.base.ImageAnnotation iaV4 = new edu.stanford.hakan.aim4api.base.ImageAnnotation();
        if (this.getSegmentationCollection().getSegmentationList().size() > 0) {//
            iaV4.setSegmentationEntityCollection(this.getSegmentationCollection().toAimV4());
        }
        if (this.getImageReferenceCollection().getImageReferenceList().size() > 0) {//
            iaV4.setImageReferenceEntityCollection(this.getImageReferenceCollection().toAimV4());
        }
        if (this.getGeometricShapeCollection().getGeometricShapeList().size() > 0) {//
            iaV4.setMarkupEntityCollection(this.getGeometricShapeCollection().toAimV4());
        }
        if (this.getCalculationCollection().getCalculationList().size() > 0) {//
            iaV4.setCalculationEntityCollection(this.getCalculationCollection().toAimV4(iaV4));
        }
        if (this.getAnatomicEntityCollection().getAnatomicEntityList().size() > 0) {//
            iaV4.setImagingPhysicalEntityCollection(this.getAnatomicEntityCollection().toAimV4());
        }
        if (this.getImagingObservationCollection().getImagingObservationList().size() > 0) {//
            iaV4.setImagingObservationEntityCollection(this.getImagingObservationCollection().toAimV4());
        }
        if (this.getInferenceCollection().getInferenceList().size() > 0) {//
            iaV4.setInferenceEntityCollection(this.getInferenceCollection().toAimV4());
        }

        iaV4.setComment(Converter.toST(this.getComment()));//
        iaV4.setName(Converter.toST(this.getName()));//
        CD typeCode = new CD();//
        typeCode.setCode(this.getCodeValue());//
        typeCode.setCodeSystem(this.getCodeMeaning());//
        typeCode.setCodeSystemName(this.getCodingSchemeDesignator());//
        typeCode.setCodeSystemVersion(this.getCodingSchemeVersion());//
        iaV4.addTypeCode(typeCode);//
        iaV4.setDateTime(this.getDateTime());//
        if ("".equals(this.iaV3UID)) {
            this.setIAv3UID(GenerateId.getUUID());
        }
        iaV4.setUniqueIdentifier(new II(this.getIAv3UID()));

        iaV4.setPluginCollection(this.pluginCollection.toAimV4(this.imageAnnotationV4));
        iaV4.setDsoStartIndex(this.dsoStartIndex);
        iaV4.setDsoColor(this.dsoColor);
        
        iacV4.addImageAnnotation(iaV4);
        
        return iacV4;
    }
    
    /*
     * Each entry in the coordinationTerms list contains the keys:
     * 		coordinationID, termID, termSchema, description
     */
    public edu.stanford.hakan.aim4api.base.ImageAnnotationCollection toAimV4(List<Map<String, String>> coordinationTerms) throws AimException {
        correctTheCoordinations(coordinationTerms);
        return toAimV4();
    }

    public void correctTheCoordinations(List<Map<String, String>> coordinationTerms) {
        //*** AnatomicEntity
        for (int i = 0; i < this.getAnatomicEntityCollection().getAnatomicEntityList().size(); i++) {
            AnatomicEntity ae = this.getAnatomicEntityCollection().getAnatomicEntityList().get(i);
            if (!"".equals(ae.getCodeValue()) && ae.getAllowedTerm() == null) {
                ae.setAllowedTerm(new AllowedTerm(ae.getCodeValue(), ae.getCodeMeaning(), ae.getCodingSchemeDesignator(), ae.getCodingSchemeVersion()));
            }
            if (ae.getAllowedTerm() != null) {
                AllowedTerm correctedAllowedTerm = getCorrectAllowedTerm(ae.getAllowedTerm().getCodeValue(), coordinationTerms);
                if (correctedAllowedTerm != null) {
                    ae.setAllowedTerm(correctedAllowedTerm);
                }
            }

            //*** AnatomicEntityCharacteristic
            for (int j = 0; j < ae.getAnatomicEntityCharacteristicCollection().getAnatomicEntityCharacteristicList().size(); j++) {
                AnatomicEntityCharacteristic aec = ae.getAnatomicEntityCharacteristicCollection().getAnatomicEntityCharacteristicList().get(j);
                if (!"".equals(aec.getCodeValue()) && aec.getAllowedTerm() == null) {
                    aec.setAllowedTerm(new AllowedTerm(aec.getCodeValue(), aec.getCodeMeaning(), aec.getCodingSchemeDesignator(), aec.getCodingSchemeVersion()));
                }
                if (aec.getAllowedTerm() != null) {
                    AllowedTerm correctedAllowedTerm = getCorrectAllowedTerm(aec.getAllowedTerm().getCodeValue(), coordinationTerms);
                    if (correctedAllowedTerm != null) {
                        aec.setAllowedTerm(correctedAllowedTerm);
                    }
                }
            }
        }

        //*** ImagingObservation
        for (int i = 0; i < this.getImagingObservationCollection().getImagingObservationList().size(); i++) {
            ImagingObservation io = this.getImagingObservationCollection().getImagingObservationList().get(i);
            if (!"".equals(io.getCodeValue()) && io.getAllowedTerm() == null) {
                io.setAllowedTerm(new AllowedTerm(io.getCodeValue(), io.getCodeMeaning(), io.getCodingSchemeDesignator(), io.getCodingSchemeVersion()));
            }
            if (io.getAllowedTerm() != null) {
                AllowedTerm correctedAllowedTerm = getCorrectAllowedTerm(io.getAllowedTerm().getCodeValue(), coordinationTerms);
                if (correctedAllowedTerm != null) {
                    io.setAllowedTerm(correctedAllowedTerm);
                }
            }

            //*** ImagingObservationCharacteristic
            for (int j = 0; j < io.getImagingObservationCharacteristicCollection().getImagingObservationCharacteristicList().size(); j++) {
                ImagingObservationCharacteristic ioc = io.getImagingObservationCharacteristicCollection().getImagingObservationCharacteristicList().get(j);
                if (!"".equals(ioc.getCodeValue()) && ioc.getAllowedTerm() == null) {
                    ioc.setAllowedTerm(new AllowedTerm(ioc.getCodeValue(), ioc.getCodeMeaning(), ioc.getCodingSchemeDesignator(), ioc.getCodingSchemeVersion()));
                }
                if (ioc.getAllowedTerm() != null) {
                    AllowedTerm correctedAllowedTerm = getCorrectAllowedTerm(ioc.getAllowedTerm().getCodeValue(), coordinationTerms);
                    if (correctedAllowedTerm != null) {
                        ioc.setAllowedTerm(correctedAllowedTerm);
                    }
                }
            }
        }

        //*** ImagingObservation
        for (int i = 0; i < this.getImagingObservationCollection().getImagingObservationList().size(); i++) {
            ImagingObservation io = this.getImagingObservationCollection().getImagingObservationList().get(i);
            if (!"".equals(io.getCodeValue()) && io.getAllowedTerm() == null) {
                io.setAllowedTerm(new AllowedTerm(io.getCodeValue(), io.getCodeMeaning(), io.getCodingSchemeDesignator(), io.getCodingSchemeVersion()));
            }
            if (io.getAllowedTerm() != null) {
                AllowedTerm correctedAllowedTerm = getCorrectAllowedTerm(io.getAllowedTerm().getCodeValue(), coordinationTerms);
                if (correctedAllowedTerm != null) {
                    io.setAllowedTerm(correctedAllowedTerm);
                }
            }
        }

        //*** Inference
        for (int i = 0; i < this.getInferenceCollection().getInferenceList().size(); i++) {
            Inference in = this.getInferenceCollection().getInferenceList().get(i);
            if (!"".equals(in.getCodeValue()) && in.getAllowedTerm() == null) {
                in.setAllowedTerm(new AllowedTerm(in.getCodeValue(), in.getCodeMeaning(), in.getCodingSchemeDesignator(), in.getCodingSchemeVersion()));
            }
            if (in.getAllowedTerm() != null) {
                AllowedTerm correctedAllowedTerm = getCorrectAllowedTerm(in.getAllowedTerm().getCodeValue(), coordinationTerms);
                if (correctedAllowedTerm != null) {
                    in.setAllowedTerm(correctedAllowedTerm);
                }
            }
        }
    }

    /*
     * Each entry in the coordinationTerms list contains the keys:
     * 		coordinationID, termID, termSchema, description
     */
    private AllowedTerm getCorrectAllowedTerm(String codeValueCurrent, List<Map<String, String>> coordinationTerms) {
        boolean isFirst = true;
        AllowedTerm res = null;
        for (Map<String, String> coordinationTerm : coordinationTerms) {
        	if (coordinationTerm.get("coordinationID").equals(codeValueCurrent)) {
        		if (res == null)
                	res = new AllowedTerm();
                String codeValue = coordinationTerm.get("termID"); //code
                String codeMeaning = coordinationTerm.get("description"); //codeSystem
                String codingSchemeDesignator = coordinationTerm.get("termSchema"); //codeSystemName
                if (isFirst) {
                    res.setCodeValue(codeValue);
                    res.setCodeMeaning(codeMeaning);
                    res.setCodingSchemeDesignator(codingSchemeDesignator);
                    isFirst = false;
                } else {
                    res.addValidTerm(codeValue, codeMeaning, codingSchemeDesignator, "");
                }
      		}
        }
        return res;
    }

    public ImageAnnotation(edu.stanford.hakan.aim4api.base.ImageAnnotationCollection iacv4) {
        edu.stanford.hakan.aim4api.base.ImageAnnotation iav4 = iacv4.getImageAnnotations().get(0);
        setXsiType("ImageAnnotation");
        this.setUniqueIdentifier(iacv4.getUniqueIdentifier().getRoot(), "al536anhb55555");
        this.setDateTime(iacv4.getDateTime());
        this.setCagridId(0);
        this.setAimVersion("AIM.3.0", "al536anhb55555");
        this.setIAv3UID(iav4.getUniqueIdentifier().getRoot());

        if (iacv4.getEquipment() != null) {
            this.addEquipment(new Equipment(iacv4.getEquipment()));
        }
        if (iacv4.getPerson() != null) {
            this.addPerson(new Person(iacv4.getPerson()));
        }
        if (iacv4.getUser() != null) {
            this.addUser(new User(iacv4.getUser()));
        }
        if (iav4.getSegmentationEntityCollection() != null && iav4.getSegmentationEntityCollection().getSegmentationEntityList().size() > 0) {
            this.setSegmentationCollection(new SegmentationCollection(iav4.getSegmentationEntityCollection()));
        }
        if (iav4.getImageReferenceEntityCollection() != null && iav4.getImageReferenceEntityCollection().getImageReferenceEntityList().size() > 0) {
            this.setImageReferenceCollection(new ImageReferenceCollection(iav4.getImageReferenceEntityCollection()));
        }
        if (iav4.getMarkupEntityCollection() != null && iav4.getMarkupEntityCollection().getMarkupEntityList().size() > 0) {
            this.setGeometricShapeCollection(new GeometricShapeCollection(iav4.getMarkupEntityCollection()));
        }
        if (iav4.getCalculationEntityCollection() != null && iav4.getCalculationEntityCollection().getCalculationEntityList().size() > 0) {
            this.setCalculationCollection(new CalculationCollection(iav4.getCalculationEntityCollection(), iav4));
        }
        if (iav4.getImagingPhysicalEntityCollection() != null && iav4.getImagingPhysicalEntityCollection().getImagingPhysicalEntityList().size() > 0) {
            this.setAnatomicEntityCollection(new AnatomicEntityCollection(iav4.getImagingPhysicalEntityCollection()));
        }
        if (iav4.getImagingObservationEntityCollection() != null && iav4.getImagingObservationEntityCollection().getImagingObservationEntityList().size() > 0) {
            this.setImagingObservationCollection(new ImagingObservationCollection(iav4.getImagingObservationEntityCollection()));
        }
        if (iav4.getInferenceEntityCollection() != null && iav4.getInferenceEntityCollection().getInferenceEntityList().size() > 0) {
            this.setInferenceCollection(new InferenceCollection(iav4.getInferenceEntityCollection()));
        }

        if (iav4.getListTypeCode().size() > 0) {
            CD typeCode = iav4.getListTypeCode().get(0);
            if (typeCode.getCode() != null) {
                this.setCodeValue(typeCode.getCode());
            }
            if (typeCode.getCodeSystem() != null) {
                this.setCodeMeaning(typeCode.getCodeSystem());
            }
            if (typeCode.getCodeSystemName() != null) {
                this.setCodingSchemeDesignator(typeCode.getCodeSystemName());
            }
            if (typeCode.getCodeSystemVersion() != null) {
                this.setCodingSchemeVersion(typeCode.getCodeSystemVersion());
            }
        }

        if (iav4.getComment() != null) {
            this.setComment(iav4.getComment().getValue());
        }
        if (iav4.getName() != null) {
            this.setName(iav4.getName().getValue());
        }

        this.imageAnnotationV4 = iav4;
        if (iav4.getPluginCollection().size() > 0) {
            this.pluginCollection = new PluginCollectionV3(iav4.getPluginCollection());
        }
        this.setDsoStartIndex(iav4.getDsoStartIndex());
    }

    @Override
    public ImageAnnotation getClone() {
        ImageAnnotation res = new ImageAnnotation();

        if (this.getCalculationCollection() != null) {
            res.setCalculationCollection(this.getCalculationCollection().getClone());
        }
        if (this.getInferenceCollection() != null) {
            res.setInferenceCollection(this.getInferenceCollection().getClone());
        }
        if (this.getAnatomicEntityCollection() != null) {
            res.setAnatomicEntityCollection(this.getAnatomicEntityCollection().getClone());
        }
        if (this.getImagingObservationCollection() != null) {
            res.setImagingObservationCollection(this.getImagingObservationCollection().getClone());
        }
        for (int i = 0; i < this.getListUser().size(); i++) {
            if (this.getListUser().get(i) != null) {
                res.addUser(this.getListUser().get(i).getClone());
            }
        }
        for (int i = 0; i < this.getListEquipment().size(); i++) {
            if (this.getListEquipment().get(i) != null) {
                res.addEquipment(this.getListEquipment().get(i).getClone());
            }
        }
        for (int i = 0; i < this.getListAimStatus().size(); i++) {
            if (this.getListAimStatus().get(i) != null) {
                res.addAimStatus(this.getListAimStatus().get(i).getClone());
            }
        }
        if (this.getCagridId() != null) {
            res.setCagridId(this.getCagridId());
        }
        if (this.getAimVersion() != null) {
            res.setAimVersion(this.getAimVersion(), "al536anhb55555");
        }
        if (this.getComment() != null) {
            res.setComment(this.getComment());
        }
        if (this.getDateTime() != null) {
            res.setDateTime(this.getDateTime());
        }
        if (this.getName() != null) {
            res.setName(this.getName());
        }
        if (this.getUniqueIdentifier() != null) {
            res.setUniqueIdentifier(this.getUniqueIdentifier(), "al536anhb55555");
        }
        if (this.getCodeValue() != null) {
            res.setCodeValue(this.getCodeValue());
        }
        if (this.getCodeMeaning() != null) {
            res.setCodeMeaning(this.getCodeMeaning());
        }
        if (this.getCodingSchemeDesignator() != null) {
            res.setCodingSchemeDesignator(this.getCodingSchemeDesignator());
        }
        if (this.getCodingSchemeVersion() != null) {
            res.setCodingSchemeVersion(this.getCodingSchemeVersion());
        }
        if (this.getPrecedentReferencedAnnotationUID() != null) {
            res.setPrecedentReferencedAnnotationUID(this.getPrecedentReferencedAnnotationUID());
        }
        if (this.getXsiType() != null) {
            res.setXsiType(this.getXsiType());
        }
        if (this.getOntologyPrefix() != null) {
            res.setOntologyPrefix(this.getOntologyPrefix());
        }

        if (this.segmentationCollection != null) {
            res.segmentationCollection = this.segmentationCollection.getClone();
        }
        if (this.imageReferenceCollection != null) {
            res.imageReferenceCollection = this.imageReferenceCollection.getClone();
        }
        if (this.geometricShapeCollection != null) {
            res.geometricShapeCollection = this.geometricShapeCollection.getClone();
        }
        for (int i = 0; i < this.listPerson.size(); i++) {
            if (this.listPerson.get(i) != null) {
                res.addPerson(this.listPerson.get(i).getClone());
            }
        }
        if (this.textAnnotationCollection != null) {
            res.textAnnotationCollection = this.textAnnotationCollection.getClone();
        }
        return res;
    }

    public PluginCollectionV3 getPluginCollection() {
        return pluginCollection;
    }

    public void setPluginCollection(PluginCollectionV3 pluginCollection) {
        this.pluginCollection = pluginCollection;
    }

    public void addPlugin(PluginV3 newPlugin) {
        if (this.pluginCollection == null) {
            this.pluginCollection = new PluginCollectionV3();

        }
        this.pluginCollection.addPlugin(newPlugin);
    }

}
