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


import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.CD;
import edu.stanford.hakan.aim4api.base.II;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hakan BULU
 */
@SuppressWarnings("serial")
public class ImageAnnotation extends Annotation implements IAimXMLOperations {

    private SegmentationCollection segmentationCollection = new SegmentationCollection();
    private ImageReferenceCollection imageReferenceCollection = new ImageReferenceCollection();
    private GeometricShapeCollection geometricShapeCollection = new GeometricShapeCollection();
    private List<Person> listPerson = new ArrayList<Person>();
    private TextAnnotationCollection textAnnotationCollection= new TextAnnotationCollection();
    

    public ImageAnnotation() {
        super();
        setXsiType("ImageAnnotation");
    }

    public ImageAnnotation(Integer cagridId, String comment, String dateTime, String name, String codeValue, String codeMeaning, String codingSchemeDesignator, String codingSchemeVersion, String precedentReferencedAnnotationUID) {
        super(cagridId, comment, dateTime, name, codeValue, codeMeaning, codingSchemeDesignator, codingSchemeVersion, precedentReferencedAnnotationUID);
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

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        Element annotation = (Element) super.getXMLNode(doc);

        if (this.getSegmentationCollection().getSegmentationList().size() > 0) {
            annotation.appendChild(this.getSegmentationCollection().getXMLNode(doc));
        }
        if (this.getImageReferenceCollection().getImageReferenceList().size() > 0) {
            annotation.appendChild(this.getImageReferenceCollection().getXMLNode(doc));
        }
        if (this.getGeometricShapeCollection().getGeometricShapeList().size() > 0) {
            annotation.appendChild(this.getGeometricShapeCollection().getXMLNode(doc));
        }
        Element person = doc.createElement("person");
        for (int i = 0; i < this.getListPerson().size(); i++) {
            person.appendChild(this.getListPerson().get(i).getXMLNode(doc));
        }
        if (this.getListPerson().size() > 0) {
            annotation.appendChild(person);
        }
        if (this.getTextAnnotationCollection().getTextAnnotationList().size() > 0) {
            annotation.appendChild(this.getTextAnnotationCollection().getXMLNode(doc));
        }
        return annotation;
    }

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

        iaV4.setComment(Converter.toST(this.getComment()));//
        iaV4.setName(Converter.toST(this.getName()));//
        CD typeCode = new CD();//
        typeCode.setCode(this.getCodeValue());//
        typeCode.setCodeSystem(this.getCodeMeaning());//
        typeCode.setCodeSystemName(this.getCodingSchemeDesignator());//
        typeCode.setCodeSystemVersion(this.getCodingSchemeVersion());//
        iaV4.addTypeCode(typeCode);//
        iaV4.setDateTime(this.getDateTime());//

        iacV4.addImageAnnotation(iaV4);
        return iacV4;
    }

    public ImageAnnotation(edu.stanford.hakan.aim4api.base.ImageAnnotationCollection iacv4) {
        edu.stanford.hakan.aim4api.base.ImageAnnotation iav4 = iacv4.getImageAnnotations().get(0);
        setXsiType("ImageAnnotation");
        this.setUniqueIdentifier(iacv4.getUniqueIdentifier().getRoot(), "al536anhb55555");
        this.setDateTime(iacv4.getDateTime());
        this.setCagridId(0);
        this.setAimVersion("AIM.3.0", "al536anhb55555");

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
    }

}
