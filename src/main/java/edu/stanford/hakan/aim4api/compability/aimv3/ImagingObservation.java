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
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hakan BULU
 */
public class ImagingObservation implements IAimXMLOperations {

    private Integer cagridId;
    private String codeValue;
    private String codeMeaning;
    private String codingSchemeDesignator;
    private String codingSchemeVersion;
    private String comment;
    private Double annotatorConfidence;
    private Boolean isPresent;
    private String label;
    private ImagingObservationCharacteristicCollection imagingObservationCharacteristicCollection = new ImagingObservationCharacteristicCollection();
    private List<ReferencedGeometricShape> listReferencedGeometricShape = new ArrayList<ReferencedGeometricShape>();

    public ImagingObservation() {
    }

    public ImagingObservation(Integer cagridId, String codeValue, String codeMeaning, String codingSchemeDesignator, String codingSchemeVersion, String comment, Double annotatorConfidence, Boolean isPresent, String label) {
        this.cagridId = cagridId;
        this.codeValue = codeValue;
        this.codeMeaning = codeMeaning;
        this.codingSchemeDesignator = codingSchemeDesignator;
        this.codingSchemeVersion = codingSchemeVersion;
        this.comment = comment;
        this.annotatorConfidence = annotatorConfidence;
        this.isPresent = isPresent;
        this.label = label;
    }

    public void addReferencedGeometricShape(ReferencedGeometricShape newReferencedGeometricShape) {
        this.listReferencedGeometricShape.add(newReferencedGeometricShape);
    }

    public void addImagingObservationCharacteristic(ImagingObservationCharacteristic newImagingObservationCharacteristic) {
        this.imagingObservationCharacteristicCollection.AddImagingObservationCharacteristic(newImagingObservationCharacteristic);
    }

    public Double getAnnotatorConfidence() {
        return annotatorConfidence;
    }

    public void setAnnotatorConfidence(Double annotatorConfidence) {
        this.annotatorConfidence = annotatorConfidence;
    }

    public Integer getCagridId() {
        return cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
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

    public ImagingObservationCharacteristicCollection getImagingObservationCharacteristicCollection() {
        return imagingObservationCharacteristicCollection;
    }

    public void setImagingObservationCharacteristicCollection(ImagingObservationCharacteristicCollection imagingObservationCharacteristicCollection) {
        this.imagingObservationCharacteristicCollection = imagingObservationCharacteristicCollection;
    }

    public Boolean getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(Boolean isPresent) {
        this.isPresent = isPresent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ReferencedGeometricShape> getListReferencedGeometricShape() {
        return listReferencedGeometricShape;
    }

    public void setListReferencedGeometricShape(List<ReferencedGeometricShape> listReferencedGeometricShape) {
        this.listReferencedGeometricShape = listReferencedGeometricShape;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        this.Control();

        Element imagingObservation = doc.createElement("ImagingObservation");
        imagingObservation.setAttribute("cagridId", this.cagridId.toString());
        imagingObservation.setAttribute("codeValue", this.codeValue);
        imagingObservation.setAttribute("codeMeaning", this.codeMeaning);
        imagingObservation.setAttribute("codingSchemeDesignator", this.codingSchemeDesignator);
        if (this.codingSchemeVersion != null) {
            imagingObservation.setAttribute("codingSchemeVersion", this.codingSchemeVersion);
        }
        if (this.comment != null) {
            imagingObservation.setAttribute("comment", this.comment);
        }
        if (this.annotatorConfidence != null) {
            imagingObservation.setAttribute("annotatorConfidence", this.annotatorConfidence.toString());
        }
        if (this.isPresent != null) {
            imagingObservation.setAttribute("isPresent", this.isPresent.toString());
        }
        imagingObservation.setAttribute("label", this.label);

        Element referencedGeometricShape = doc.createElement("referencedGeometricShape");
        for (int i = 0; i < this.listReferencedGeometricShape.size(); i++) {
            referencedGeometricShape.appendChild(this.listReferencedGeometricShape.get(i).getXMLNode(doc));
        }
        if (this.listReferencedGeometricShape.size() > 0) {
            imagingObservation.appendChild(referencedGeometricShape);
        }
        if (this.imagingObservationCharacteristicCollection.getImagingObservationCharacteristicList().size() > 0) {
            imagingObservation.appendChild(this.imagingObservationCharacteristicCollection.getXMLNode(doc));
        }
        return imagingObservation;
    }

    @Override
    public void setXMLNode(Node node) {

        this.listReferencedGeometricShape.clear();
        NodeList listChils = node.getChildNodes();
        for (int i = 0; i < listChils.getLength(); i++) {
            if ("imagingObservationCharacteristicCollection".equals(listChils.item(i).getNodeName())) {
                this.imagingObservationCharacteristicCollection.setXMLNode(listChils.item(i));
            } else if ("referencedGeometricShape".equals(listChils.item(i).getNodeName())) {
                NodeList tempList = listChils.item(i).getChildNodes();
                for (int j = 0; j < tempList.getLength(); j++) {
                    if ("ReferencedGeometricShape".equals(tempList.item(j).getNodeName())) {
                        ReferencedGeometricShape obj = new ReferencedGeometricShape();
                        obj.setXMLNode(tempList.item(j));
                        this.addReferencedGeometricShape(obj);
                    }
                }
            }
        }

        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.codeValue = map.getNamedItem("codeValue").getNodeValue();
        this.codeMeaning = map.getNamedItem("codeMeaning").getNodeValue();
        this.codingSchemeDesignator = map.getNamedItem("codingSchemeDesignator").getNodeValue();
        if (map.getNamedItem("codingSchemeVersion") != null) {
            this.codingSchemeVersion = map.getNamedItem("codingSchemeVersion").getNodeValue();
        }
        if (map.getNamedItem("comment") != null) {
            this.comment = map.getNamedItem("comment").getNodeValue();
        }
        if (map.getNamedItem("annotatorConfidence") != null) {
            this.annotatorConfidence = Double.parseDouble(map.getNamedItem("annotatorConfidence").getNodeValue());
        }
        if (map.getNamedItem("isPresent") != null) {
            this.isPresent = Boolean.parseBoolean(map.getNamedItem("isPresent").getNodeValue());
        }
        this.label = map.getNamedItem("label").getNodeValue();
    }

    
    private void Control() throws AimException {
        if (getCagridId() == null) {
            throw new AimException("AimException: ImagingObservation's cagridId can not be null");
        }
        if (getCodeValue() == null) {
            throw new AimException("AimException: ImagingObservation's codeValue can not be null");
        }
        if (getCodeMeaning() == null) {
            throw new AimException("AimException: ImagingObservation's codeMeaning can not be null");
        }
        if (getCodingSchemeDesignator() == null) {
            throw new AimException("AimException: ImagingObservation's codingSchemeDesignator can not be null");
        }
        if (getLabel() == null) {
            throw new AimException("AimException: ImagingObservation's label can not be null");
        }
    }

    public boolean isEqualTo(Object other) {
        ImagingObservation oth = (ImagingObservation) other;
        if (this.cagridId != oth.cagridId) {
            return false;
        }
        if (this.codeValue == null ? oth.codeValue != null : !this.codeValue.equals(oth.codeValue)) {
            return false;
        }
        if (this.codeMeaning == null ? oth.codeMeaning != null : !this.codeMeaning.equals(oth.codeMeaning)) {
            return false;
        }
        if (this.codingSchemeDesignator == null ? oth.codingSchemeDesignator != null : !this.codingSchemeDesignator.equals(oth.codingSchemeDesignator)) {
            return false;
        }
        if (this.codingSchemeVersion == null ? oth.codingSchemeVersion != null : !this.codingSchemeVersion.equals(oth.codingSchemeVersion)) {
            return false;
        }
        if (this.comment == null ? oth.comment != null : !this.comment.equals(oth.comment)) {
            return false;
        }
        if (this.annotatorConfidence == null ? oth.annotatorConfidence != null : !this.annotatorConfidence.equals(oth.annotatorConfidence)) {
            return false;
        }
        if (this.isPresent == null ? oth.isPresent != null : !this.isPresent.equals(oth.isPresent)) {
            return false;
        }
        if (this.label == null ? oth.label != null : !this.label.equals(oth.label)) {
            return false;
        }
        if (!this.imagingObservationCharacteristicCollection.isEqualTo(oth.imagingObservationCharacteristicCollection)) {
            return false;
        }
        if (this.listReferencedGeometricShape.size() != oth.listReferencedGeometricShape.size()) {
            return false;
        }
        for (int i = 0; i < this.listReferencedGeometricShape.size(); i++) {
            if (!this.listReferencedGeometricShape.get(i).isEqualTo(oth.listReferencedGeometricShape.get(i))) {
                return false;
            }
        }
        return true;
    }

    public edu.stanford.hakan.aim4api.base.ImagingObservationEntity toAimV4() {
        edu.stanford.hakan.aim4api.base.ImagingObservationEntity res = new edu.stanford.hakan.aim4api.base.ImagingObservationEntity();
        res.setAnnotatorConfidence(this.getAnnotatorConfidence());//
        res.setComment(Converter.toST(this.getComment()));//
        CD typeCode = new CD();
        typeCode.setCode(this.getCodeValue());//
        typeCode.setCodeSystem(this.getCodeMeaning());//
        typeCode.setCodeSystemName(this.getCodingSchemeDesignator());//
        typeCode.setCodeSystemVersion(this.getCodingSchemeVersion());//
        res.addTypeCode(typeCode);//
        res.setImagingObservationCharacteristicCollection(this.getImagingObservationCharacteristicCollection().toAimV4());//
        res.setIsPresent(this.getIsPresent());
        res.setLabel(Converter.toST(this.getLabel()));
        return res;
    }

    public ImagingObservation(edu.stanford.hakan.aim4api.base.ImagingObservationEntity v4) {
        this.setCagridId(0);
        if (v4.getListTypeCode().size() > 0) {
            CD typeCode = v4.getListTypeCode().get(0);
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
        this.setAnnotatorConfidence(v4.getAnnotatorConfidence());
        if (v4.getComment() != null) {
            this.setComment(v4.getComment().getValue());
        }
        if (v4.getImagingObservationCharacteristicCollection().getImagingObservationCharacteristicList().size() > 0) {
            this.setImagingObservationCharacteristicCollection(new ImagingObservationCharacteristicCollection(v4.getImagingObservationCharacteristicCollection()));
        }
        this.setIsPresent(v4.getIsPresent());
        if (v4.getLabel() != null) {
            this.setLabel(v4.getLabel().getValue());
        }
    }
}
