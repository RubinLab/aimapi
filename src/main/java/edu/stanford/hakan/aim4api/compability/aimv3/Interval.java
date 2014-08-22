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

import edu.stanford.hakan.aim4api.compability.aimv3.AimUtility.ComparisonOperators;
import edu.stanford.hakan.aim4api.base.Enumerations;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan BULU
 */
public class Interval extends CharacteristicQuantification {

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

    @Override
    public edu.stanford.hakan.aim4api.base.CharacteristicQuantification toAimV4() {
        edu.stanford.hakan.aim4api.base.Interval res = new edu.stanford.hakan.aim4api.base.Interval();
        res.setAnnotatorConfidence(this.getAnnotatorConfidence());
        res.setMaxOperator(Converter.toAimV4(this.getMaxOperator()));
        res.setMaxValue(this.getMaxValue());
        res.setMinOperator(Converter.toAimV4(this.getMinOperator()));
        res.setMinValue(this.getMinValue());
        res.setUcumString(Converter.toST(this.getUcumString()));
        res.setComment(Converter.toST(this.getName()));
        return res;
    }
}
