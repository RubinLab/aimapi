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
import edu.stanford.hakan.aim4api.compability.aimv3.AimUtility.ComparisonOperators;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan BULU
 */
public class Interval extends CharacteristicQuantification implements IAimXMLOperations {

    private Double minValue;
    private Double maxValue;
    private AimUtility.ComparisonOperators minOperator;
    private String ucumString;
    private AimUtility.ComparisonOperators maxOperator;

    public Interval() {
        setXsiType("Interval");
    }

    public Interval(Integer cagridId, String name, Double annotatorConfidence, Double minValue, Double maxValue, AimUtility.ComparisonOperators minOperator, String ucumString, AimUtility.ComparisonOperators maxOperator) {
        setCagridId(cagridId);
        setName(name);
        setAnnotatorConfidence(annotatorConfidence);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.minOperator = minOperator;
        this.ucumString = ucumString;
        this.maxOperator = maxOperator;
        setXsiType("Interval");
    }

    public ComparisonOperators getMaxOperator() {
        return maxOperator;
    }

    public void setMaxOperator(ComparisonOperators maxOperator) {
        this.maxOperator = maxOperator;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public ComparisonOperators getMinOperator() {
        return minOperator;
    }

    public void setMinOperator(ComparisonOperators minOperator) {
        this.minOperator = minOperator;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public String getUcumString() {
        return ucumString;
    }

    public void setUcumString(String ucumString) {
        this.ucumString = ucumString;
    }

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//
//        this.Control();
//
//        Element characteristicQuantification = (Element) super.getXMLNode(doc);
//
//        characteristicQuantification.setAttribute("minValue", this.getMinValue().toString());
//        characteristicQuantification.setAttribute("maxValue", this.getMaxValue().toString());
//        characteristicQuantification.setAttribute("minOperator", this.getMinOperator().toString());
//        characteristicQuantification.setAttribute("ucumString", this.getUcumString());
//        characteristicQuantification.setAttribute("maxOperator", this.getMaxOperator().toString());
//
//        return characteristicQuantification;
//    }
    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);

        NamedNodeMap map = node.getAttributes();
        this.minValue = Double.parseDouble(map.getNamedItem("minValue").getNodeValue());
        this.maxValue = Double.parseDouble(map.getNamedItem("maxValue").getNodeValue());
        this.ucumString = map.getNamedItem("ucumString").getNodeValue();
        this.minOperator = AimUtility.ComparisonOperators.valueOf(map.getNamedItem("minOperator").getNodeValue());
        this.maxOperator = AimUtility.ComparisonOperators.valueOf(map.getNamedItem("maxOperator").getNodeValue());
    }

    private void Control() throws AimException {

        if (this.getMinValue() == null) {
            throw new AimException("AimException: Interval's minValue can not be null");
        }
        if (this.getMaxValue() == null) {
            throw new AimException("AimException: Interval's maxValue can not be null");
        }
        if (this.getMinOperator() == null) {
            throw new AimException("AimException: Interval's minOperator can not be null");
        }
        if (this.getUcumString() == null) {
            throw new AimException("AimException: Interval's ucumString can not be null");
        }
        if (this.getMaxOperator() == null) {
            throw new AimException("AimException: Interval's maxOperator can not be null");
        }
    }

    @Override
    public boolean isEqualTo(Object other) {
        Interval oth = (Interval) other;
        if (this.minValue == null ? oth.minValue != null : !this.minValue.equals(oth.minValue)) {
            return false;
        }
        if (this.maxValue == null ? oth.maxValue != null : !this.maxValue.equals(oth.maxValue)) {
            return false;
        }
        if (this.minOperator == null ? oth.minOperator != null : !this.minOperator.equals(oth.minOperator)) {
            return false;
        }
        if (this.ucumString == null ? oth.ucumString != null : !this.ucumString.equals(oth.ucumString)) {
            return false;
        }
        if (this.maxOperator == null ? oth.maxOperator != null : !this.maxOperator.equals(oth.maxOperator)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    @Override
    public edu.stanford.hakan.aim4api.base.CharacteristicQuantification toAimV4() {
        edu.stanford.hakan.aim4api.base.Interval res = new edu.stanford.hakan.aim4api.base.Interval();
        res.setAnnotatorConfidence(this.getAnnotatorConfidence());//
        res.setMaxOperator(Converter.toAimV4(this.getMaxOperator()));//
        res.setMaxValue(this.getMaxValue());//
        res.setMinOperator(Converter.toAimV4(this.getMinOperator()));//
        res.setMinValue(this.getMinValue());//
        res.setUcumString(Converter.toST(this.getUcumString()));//
        res.setComment(Converter.toST(this.getName()));//
        res.setLabel(Converter.toST(this.getName())); //
        return res;
    }

    public Interval(edu.stanford.hakan.aim4api.base.Interval v4) {
        setXsiType("Interval");
        this.setCagridId(0);
        this.setAnnotatorConfidence(v4.getAnnotatorConfidence());
        this.setMaxOperator(Converter.toAimV3(v4.getMaxOperator()));
        this.setMaxValue(v4.getMaxValue());
        this.setMinOperator(Converter.toAimV3(v4.getMinOperator()));
        this.setMinValue(v4.getMinValue());
        if (v4.getUcumString() != null) {
            this.setUcumString(v4.getUcumString().getValue());
        }
        if (v4.getComment() != null) {
            this.setName(v4.getComment().getValue());
        }
    }

    @Override
    public Interval getClone() {
        Interval res = new Interval();
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

        if (this.minValue != null) {
            res.minValue = this.minValue;
        }
        if (this.maxValue != null) {
            res.maxValue = this.maxValue;
        }
        if (this.minOperator != null) {
            res.minOperator = this.minOperator;
        }
        if (this.ucumString != null) {
            res.ucumString = this.ucumString;
        }
        if (this.maxOperator != null) {
            res.maxOperator = this.maxOperator;
        }
        return res;
    }
}
