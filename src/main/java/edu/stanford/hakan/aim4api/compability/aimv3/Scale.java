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
import edu.stanford.hakan.aim4api.base.Enumerations;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan BULU
 */
public class Scale extends CharacteristicQuantification implements IAimXMLOperations {

    private String comment;
    private String description;
    private String value;

    public Scale() {
        setXsiType("Scale");
    }

    public Scale(Integer cagridId, String name, Double annotatorConfidence, String comment, String description, String value) {
        setCagridId(cagridId);
        setName(name);
        setAnnotatorConfidence(annotatorConfidence);
        this.comment = comment;
        this.description = description;
        this.value = value;
        setXsiType("Scale");
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//
//        this.Control();
//
//        Element characteristicQuantification = (Element) super.getXMLNode(doc);
//
//        if (this.getComment() != null) {
//            characteristicQuantification.setAttribute("comment", this.getComment());
//        }
//        if (this.getDescription() != null) {
//            characteristicQuantification.setAttribute("description", this.getDescription());
//        }
//        characteristicQuantification.setAttribute("value", this.getValue());
//
//        return characteristicQuantification;
//    }
    @Override
    public void setXMLNode(Node node) {

        super.setXMLNode(node);

        NamedNodeMap map = node.getAttributes();
        if (map.getNamedItem("comment") != null) {
            this.comment = map.getNamedItem("comment").getNodeValue();
        }
        if (map.getNamedItem("description") != null) {
            this.description = map.getNamedItem("description").getNodeValue();
        }
        this.value = map.getNamedItem("value").getNodeValue();
    }

    private void Control() throws AimException {
        if (this.getValue() == null) {
            throw new AimException("AimException: Scale's value can not be null");
        }
    }

    @Override
    public boolean isEqualTo(Object other) {
        Scale oth = (Scale) other;
        if (this.comment == null ? oth.comment != null : !this.comment.equals(oth.comment)) {
            return false;
        }
        if (this.description == null ? oth.description != null : !this.description.equals(oth.description)) {
            return false;
        }
        if (this.value == null ? oth.value != null : !this.value.equals(oth.value)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    @Override
    public edu.stanford.hakan.aim4api.base.CharacteristicQuantification toAimV4() {
        edu.stanford.hakan.aim4api.base.Scale res = new edu.stanford.hakan.aim4api.base.Scale();
        res.setAnnotatorConfidence(this.getAnnotatorConfidence());//
        res.setComment(Converter.toST(this.getComment()));//
        res.setValue(Converter.toST(this.getValue()));//
        res.setLabel(Converter.toST(this.getName())); //*** label-name
        res.setValueLabel(Converter.toST(this.getDescription())); //*** valuelabel-description
        res.setType(Enumerations.ScaleType.Nominal);
        return res;
    }

    public Scale(edu.stanford.hakan.aim4api.base.Scale v4) {
        setXsiType("Scale");
        this.setCagridId(0);
        this.setAnnotatorConfidence(v4.getAnnotatorConfidence());
        if (v4.getValue() != null) {
            this.setValue(v4.getValue().getValue());
        }
        if (v4.getComment() != null) {
            this.setComment(v4.getComment().getValue());
        }
        if (v4.getLabel() != null) {
            this.setName(v4.getLabel().getValue());
        }
        
        if (v4.getValueLabel() != null) {
            this.setDescription(v4.getValueLabel().getValue());
        }
    }

    @Override
    public Scale getClone() {
        Scale res = new Scale();
        if (this.getCagridId() != null) {
            res.setCagridId(this.getCagridId());
        }
        if (this.getName() != null) {
            res.setName(this.getName());
        }
        if (this.getAnnotatorConfidence() != null) {
            res.setAnnotatorConfidence(this.getAnnotatorConfidence());
        }
        if (this.getXsiType() != null) {
            res.setXsiType(this.getXsiType());
        }
        if (this.comment != null) {
            res.comment = this.comment;
        }
        if (this.description != null) {
            res.description = this.description;
        }
        if (this.value != null) {
            res.value = this.value;
        }
        return res;
    }
}
