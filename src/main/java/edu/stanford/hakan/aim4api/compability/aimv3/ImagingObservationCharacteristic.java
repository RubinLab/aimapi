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

import edu.stanford.hakan.aim4api.addition.AllowedTerm;
import edu.stanford.hakan.aim4api.addition.ValidTerm;
import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.CD;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hakan BULU
 */
public class ImagingObservationCharacteristic implements IAimXMLOperations {

    private Integer cagridId;
    private String codeValue;
    private String codeMeaning;
    private String codingSchemeDesignator;
    private String codingSchemeVersion;
    private String comment;
    private Double annotatorConfidence;
    private String label;
    private CharacteristicQuantificationCollection characteristicQuantificationCollection = new CharacteristicQuantificationCollection();
    //private List<AllowedTerm> listAllowedTerm = new ArrayList<AllowedTerm>();
    private AllowedTerm allowedTerm;

    public ImagingObservationCharacteristic() {
    }

    public ImagingObservationCharacteristic(Integer cagridId, String codeValue, String codeMeaning, String codingSchemeDesignator, String codingSchemeVersion, String comment, Double annotatorConfidence, String label) {
        this.cagridId = cagridId;
        this.codeValue = codeValue;
        this.codeMeaning = codeMeaning;
        this.codingSchemeDesignator = codingSchemeDesignator;
        this.codingSchemeVersion = codingSchemeVersion;
        this.comment = comment;
        this.annotatorConfidence = annotatorConfidence;
        this.label = label;
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

    public CharacteristicQuantificationCollection getCharacteristicQuantificationCollection() {
        return characteristicQuantificationCollection;
    }

    public void setCharacteristicQuantificationCollection(CharacteristicQuantificationCollection characteristicQuantificationCollection) {
        this.characteristicQuantificationCollection = characteristicQuantificationCollection;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void addCharacteristicQuantification(CharacteristicQuantification newCharacteristicQuantification) {
        this.characteristicQuantificationCollection.AddCharacteristicQuantification(newCharacteristicQuantification);
    }

//    public void addAllowedTerm(AllowedTerm allowedTerm) {
//        if (!this.listAllowedTerm.contains(allowedTerm)) {
//            this.listAllowedTerm.add(allowedTerm);
//        }
//    }
//
//    public void addAllowedTerm(String codeValue, String codeMeaning, String codingSchemeDesignator, String codingSchemeVersion) {
//        AllowedTerm allowedTerm = new AllowedTerm(codeValue, codeMeaning, codingSchemeDesignator, codingSchemeVersion);
//        if (!this.listAllowedTerm.contains(allowedTerm)) {
//            this.listAllowedTerm.add(allowedTerm);
//        }
//    }
    public AllowedTerm getAllowedTerm() {
        return allowedTerm;
    }

    public void setAllowedTerm(AllowedTerm allowedTerm) {
        this.allowedTerm = allowedTerm;
    }

    public void setAllowedTerm(String codeValue, String codeMeaning, String codingSchemeDesignator, String codingSchemeVersion) {
        this.allowedTerm = new AllowedTerm(codeValue, codeMeaning, codingSchemeDesignator, codingSchemeVersion);
    }

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//
//        this.Control();
//
//        Element imagingObservationCharacteristic = doc.createElement("ImagingObservationCharacteristic");
//        imagingObservationCharacteristic.setAttribute("cagridId", this.cagridId.toString());
//        imagingObservationCharacteristic.setAttribute("codeValue", this.codeValue);
//        imagingObservationCharacteristic.setAttribute("codeMeaning", this.codeMeaning);
//        imagingObservationCharacteristic.setAttribute("codingSchemeDesignator", this.codingSchemeDesignator);
//        if (this.codingSchemeVersion != null) {
//            imagingObservationCharacteristic.setAttribute("codingSchemeVersion", this.codingSchemeVersion);
//        }
//        if (this.comment != null) {
//            imagingObservationCharacteristic.setAttribute("comment", this.comment);
//        }
//        if (this.annotatorConfidence != null) {
//            imagingObservationCharacteristic.setAttribute("annotatorConfidence", this.annotatorConfidence.toString());
//        }
//        imagingObservationCharacteristic.setAttribute("label", this.label);
//        if (this.characteristicQuantificationCollection.getCharacteristicQuantificationList().size() > 0) {
//            imagingObservationCharacteristic.appendChild(this.characteristicQuantificationCollection.getXMLNode(doc));
//        }
//        return imagingObservationCharacteristic;
//    }
    @Override
    public void setXMLNode(Node node) {

        NodeList listChils = node.getChildNodes();
        for (int i = 0; i < listChils.getLength(); i++) {
            if ("characteristicQuantificationCollection".equals(listChils.item(i).getNodeName())) {
                this.characteristicQuantificationCollection.setXMLNode(listChils.item(i));
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
        this.setAllowedTerm(codeValue, codeMeaning, codingSchemeDesignator, codingSchemeVersion);
        if (map.getNamedItem("comment") != null) {
            this.comment = map.getNamedItem("comment").getNodeValue();
        }
        if (map.getNamedItem("annotatorConfidence") != null) {
            this.annotatorConfidence = Double.parseDouble(map.getNamedItem("annotatorConfidence").getNodeValue());
        }
        this.label = map.getNamedItem("label").getNodeValue();
    }

    private void Control() throws AimException {
        if (getCagridId() == null) {
            throw new AimException("AimException: ImagingObservationCharacteristic's cagridId can not be null");
        }
        if (getCodeValue() == null) {
            throw new AimException("AimException: ImagingObservationCharacteristic's codeValue can not be null");
        }
        if (getCodeMeaning() == null) {
            throw new AimException("AimException: ImagingObservationCharacteristic's codeMeaning can not be null");
        }
        if (getCodingSchemeDesignator() == null) {
            throw new AimException("AimException: ImagingObservationCharacteristic's codingSchemeDesignator can not be null");
        }
        if (getLabel() == null) {
            throw new AimException("AimException: ImagingObservationCharacteristic's label can not be null");
        }
    }

    public boolean isEqualTo(Object other) {
        ImagingObservationCharacteristic oth = (ImagingObservationCharacteristic) other;
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
        if (this.label == null ? oth.label != null : !this.label.equals(oth.label)) {
            return false;
        }
        return this.characteristicQuantificationCollection.isEqualTo(oth.characteristicQuantificationCollection);
    }

    public edu.stanford.hakan.aim4api.base.ImagingObservationCharacteristic toAimV4() {
        edu.stanford.hakan.aim4api.base.ImagingObservationCharacteristic res = new edu.stanford.hakan.aim4api.base.ImagingObservationCharacteristic();
        res.setAnnotatorConfidence(this.getAnnotatorConfidence());//
        res.setCharacteristicQuantificationCollection(this.getCharacteristicQuantificationCollection().toAimV4());//
        res.setComment(Converter.toST(this.getComment()));//
        res.setLabel(Converter.toST(this.getLabel()));//

        if (this.allowedTerm != null) {
            res.addTypeCode(this.allowedTerm.toCD());
            for (ValidTerm validTerm : this.allowedTerm.getListValidTerm()) {
                res.addTypeCode(validTerm.toCD());
            }
        }

//        for (AllowedTerm allowedTerm : this.listAllowedTerm) {
//            res.addTypeCode(allowedTerm.toCD());
//        }
//        CD typeCode = new CD();//
//        typeCode.setCode(this.getCodeValue());//
//        typeCode.setCodeSystem(this.getCodeMeaning());//
//        typeCode.setCodeSystemName(this.getCodingSchemeDesignator());//
//        typeCode.setCodeSystemVersion(this.getCodingSchemeVersion());//
//        res.addTypeCode(typeCode);//
        return res;
    }

    public ImagingObservationCharacteristic(edu.stanford.hakan.aim4api.base.ImagingObservationCharacteristic v4) {
        this.setCagridId(0);

        if (v4.getListTypeCode().size() > 0) {
            String code_Value = "";
            String code_Meaning = "";
            String coding_SchemeDesignator = "";
            String coding_SchemeVersion = "";
            CD typeCode = v4.getListTypeCode().get(0);
            if (typeCode.getCode() != null) {
                code_Value = typeCode.getCode();
            }
            if (typeCode.getCodeSystem() != null) {
                code_Meaning = typeCode.getCodeSystem();
            }
            if (typeCode.getCodeSystemName() != null) {
                coding_SchemeDesignator = typeCode.getCodeSystemName();
            }
            if (typeCode.getCodeSystemVersion() != null) {
                coding_SchemeVersion = typeCode.getCodeSystemVersion();
            }
            this.setAllowedTerm(code_Value, code_Meaning, coding_SchemeDesignator, coding_SchemeVersion);

            for (int i = 1; i < v4.getListTypeCode().size(); i++) {
                typeCode = v4.getListTypeCode().get(i);

                if (typeCode.getCode() != null) {
                    code_Value = typeCode.getCode();
                }
                if (typeCode.getCodeSystem() != null) {
                    code_Meaning = typeCode.getCodeSystem();
                }
                if (typeCode.getCodeSystemName() != null) {
                    coding_SchemeDesignator = typeCode.getCodeSystemName();
                }
                if (typeCode.getCodeSystemVersion() != null) {
                    coding_SchemeVersion = typeCode.getCodeSystemVersion();
                }
                this.allowedTerm.addValidTerm(code_Value, code_Meaning, coding_SchemeDesignator, coding_SchemeVersion);
            }
        }
        this.setAnnotatorConfidence(v4.getAnnotatorConfidence());
        if (v4.getCharacteristicQuantificationCollection().getCharacteristicQuantificationList().size() > 0) {
            this.setCharacteristicQuantificationCollection(new CharacteristicQuantificationCollection(v4.getCharacteristicQuantificationCollection()));
        }
        if (v4.getComment() != null) {
            this.setComment(v4.getComment().getValue());
        }
        if (v4.getLabel() != null) {
            this.setLabel(v4.getLabel().getValue());
        }
    }

    public ImagingObservationCharacteristic getClone() {
        ImagingObservationCharacteristic res = new ImagingObservationCharacteristic();
        if (this.cagridId != null) {
            res.cagridId = this.cagridId;
        }
        if (this.codeValue != null) {
            res.codeValue = this.codeValue;
        }
        if (this.codeMeaning != null) {
            res.codeMeaning = this.codeMeaning;
        }
        if (this.codingSchemeDesignator != null) {
            res.codingSchemeDesignator = this.codingSchemeDesignator;
        }
        if (this.codingSchemeVersion != null) {
            res.codingSchemeVersion = this.codingSchemeVersion;
        }
        if (this.comment != null) {
            res.comment = this.comment;
        }
        if (this.annotatorConfidence != null) {
            res.annotatorConfidence = this.annotatorConfidence;
        }
        if (this.label != null) {
            res.label = this.label;
        }
        if (this.characteristicQuantificationCollection != null) {
            res.characteristicQuantificationCollection = this.characteristicQuantificationCollection.getClone();
        }
        if (this.allowedTerm != null) {
            res.allowedTerm = this.allowedTerm.getClone();
        }
        return res;
    }
}
