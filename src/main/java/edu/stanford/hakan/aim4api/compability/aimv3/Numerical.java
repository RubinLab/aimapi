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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan BULU
 */
public class Numerical extends CharacteristicQuantification implements IAimXMLOperations {

    private String ucumString;
    private Double value;
    private AimUtility.ComparisonOperators operator;

    public Numerical() {
        setXsiType("Numerical");
    }

    public Numerical(Integer cagridId, String name, Double annotatorConfidence, String ucumString, Double value, AimUtility.ComparisonOperators operator) {
        setCagridId(cagridId);
        setName(name);
        setAnnotatorConfidence(annotatorConfidence);
        this.ucumString = ucumString;
        this.value = value;
        this.operator = operator;
        setXsiType("Numerical");
    }

    public AimUtility.ComparisonOperators getComparisonOperators() {
        return operator;
    }

    public void setComparisonOperators(AimUtility.ComparisonOperators operator) {
        this.operator = operator;
    }

    public String getUcumString() {
        return ucumString;
    }

    public void setUcumString(String ucumString) {
        this.ucumString = ucumString;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//
//        this.Control();
//
//        Element characteristicQuantification = (Element) super.getXMLNode(doc);
//        characteristicQuantification.setAttribute("ucumString", this.getUcumString());
//        characteristicQuantification.setAttribute("value", this.getValue().toString());
//        if (this.getComparisonOperators() != null) {
//            characteristicQuantification.setAttribute("operator", this.getComparisonOperators().toString());
//        }
//        return characteristicQuantification;
//    }
    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);

        NamedNodeMap map = node.getAttributes();
        this.ucumString = map.getNamedItem("ucumString").getNodeValue();
        this.value = Double.parseDouble(map.getNamedItem("value").getNodeValue());
        if (map.getNamedItem("operator") != null) {
            this.operator = AimUtility.ComparisonOperators.valueOf(map.getNamedItem("operator").getNodeValue());
        }
    }

    private void Control() throws AimException {
        if (this.getUcumString() == null) {
            throw new AimException("AimException: Numerical's ucumString can not be null");
        }
        if (this.getValue() == null) {
            throw new AimException("AimException: Numerical's value can not be null");
        }
    }

    @Override
    public boolean isEqualTo(Object other) {
        Numerical oth = (Numerical) other;
        if (this.ucumString == null ? oth.ucumString != null : !this.ucumString.equals(oth.ucumString)) {
            return false;
        }
        if (this.value == null ? oth.value != null : !this.value.equals(oth.value)) {
            return false;
        }
        if (this.operator == null ? oth.operator != null : !this.operator.equals(oth.operator)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    @Override
    public edu.stanford.hakan.aim4api.base.CharacteristicQuantification toAimV4() {
        edu.stanford.hakan.aim4api.base.Numerical res = new edu.stanford.hakan.aim4api.base.Numerical();
        res.setAnnotatorConfidence(this.getAnnotatorConfidence());//
        res.setValue(this.getValue());//
        res.setUcumString(Converter.toST(this.getUcumString()));//
        res.setOperator(Converter.toAimV4(this.getComparisonOperators()));//
        res.setComment(Converter.toST(this.getName()));//
        res.setLabel(Converter.toST(this.getName())); //
        return res;
    }

    public Numerical(edu.stanford.hakan.aim4api.base.Numerical v4) {
        setXsiType("Numerical");
        this.setCagridId(0);
        this.setAnnotatorConfidence(v4.getAnnotatorConfidence());
        if (v4.getUcumString() != null) {
            this.setUcumString(v4.getUcumString().getValue());
        }
        if (v4.getComment() != null) {
            this.setName(v4.getComment().getValue());
        }
        this.setValue(v4.getValue());
        this.setComparisonOperators(Converter.toAimV3(v4.getOperator()));
    }

    @Override
    public Numerical getClone() {
        Numerical res = new Numerical();
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

        if (this.ucumString != null) {
            res.ucumString = this.ucumString;
        }
        if (this.value != null) {
            res.value = this.value;
        }
        if (this.operator != null) {
            res.operator = this.operator;
        }
        return res;
    }
}
