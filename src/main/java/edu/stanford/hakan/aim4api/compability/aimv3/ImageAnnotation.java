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
public class ImageAnnotation extends Annotation {

    private SegmentationCollection segmentationCollection = new SegmentationCollection();
    private ImageReferenceCollection imageReferenceCollection = new ImageReferenceCollection();
    private GeometricShapeCollection geometricShapeCollection = new GeometricShapeCollection();
    private List<Person> listPerson = new ArrayList<Person>();
    private TextAnnotationCollection textAnnotationCollection = new TextAnnotationCollection();

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

    public edu.stanford.hakan.aim4api.base.ImageAnnotationCollection toAimV4() throws AimException {
        edu.stanford.hakan.aim4api.base.ImageAnnotationCollection iacV4 = new edu.stanford.hakan.aim4api.base.ImageAnnotationCollection();

        iacV4.setUniqueIdentifier(new II(this.getUniqueIdentifier()));
        iacV4.setDateTime(this.getDateTime());

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

        iaV4.setComment(Converter.toST(this.getComment()));
        iaV4.setName(Converter.toST(this.getName()));
        CD typeCode = new CD();
        typeCode.setCode(this.getCodeValue());
        typeCode.setCodeSystem(this.getCodeMeaning());
        typeCode.setCodeSystemName(this.getCodingSchemeDesignator());
        typeCode.setCodeSystemVersion(this.getCodingSchemeVersion());
        iaV4.addTypeCode(typeCode);
        iaV4.setDateTime(this.getDateTime());

        iacV4.addImageAnnotation(iaV4);
        return iacV4;
    }
}
