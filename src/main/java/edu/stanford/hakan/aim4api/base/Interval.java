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
package edu.stanford.hakan.aim4api.base;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class Interval extends CharacteristicQuantification {

    public Interval() {
        setXsiType("Interval");
    }
    private Double minValue;
    private Double maxValue;
    private ST ucumString;
    private Enumerations.ComparisonOperator minOperator;
    private Enumerations.ComparisonOperator maxOperator;

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public ST getUcumString() {
        return ucumString;
    }

    public void setUcumString(ST ucumString) {
        ucumString.setTagName("ucumString");
        this.ucumString = ucumString;
    }

    public Enumerations.ComparisonOperator getMinOperator() {
        return minOperator;
    }

    public void setMinOperator(Enumerations.ComparisonOperator minOperator) {
        this.minOperator = minOperator;
    }

    public Enumerations.ComparisonOperator getMaxOperator() {
        return maxOperator;
    }

    public void setMaxOperator(Enumerations.ComparisonOperator maxOperator) {
        this.maxOperator = maxOperator;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("Interval");
        }
        Element res = (Element) super.getXMLNode(doc);
        if (this.minValue != null) {
            Element el_minValue = doc.createElement("minValue");
            el_minValue.setAttribute("value", this.minValue.toString());
            res.appendChild(el_minValue);
        }
        if (this.maxValue != null) {
            Element el_maxValue = doc.createElement("maxValue");
            el_maxValue.setAttribute("value", this.maxValue.toString());
            res.appendChild(el_maxValue);
        }
        if (this.ucumString != null) {
            res.appendChild(this.ucumString.getXMLNode(doc));
        }
        if (this.minOperator != null) {
            res.setAttribute("minOperator", this.minOperator.toString());
        }
        if (this.maxOperator != null) {
            res.setAttribute("maxOperator", this.maxOperator.toString());
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
        NamedNodeMap map = node.getAttributes();
        if (map.getNamedItem("minOperator") != null) {
            this.minOperator = Enumerations.ComparisonOperator.valueOf(map.getNamedItem("minOperator").getNodeValue());
        }
        if (map.getNamedItem("maxOperator") != null) {
            this.maxOperator = Enumerations.ComparisonOperator.valueOf(map.getNamedItem("maxOperator").getNodeValue());
        }
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("minValue".equals(currentNode.getNodeName())) {
                this.minValue = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("maxValue".equals(currentNode.getNodeName())) {
                this.maxValue = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("ucumString".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setUcumString(obj);
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        if (this.ucumString == null ? oth.ucumString != null : !this.ucumString.isEqualTo(oth.ucumString)) {
            return false;
        }
        if (this.minOperator == null ? oth.minOperator != null : !this.minOperator.equals(oth.minOperator)) {
            return false;
        }
        if (this.maxOperator == null ? oth.maxOperator != null : !this.maxOperator.equals(oth.maxOperator)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    @Override
    public Interval getClone() {
        Interval res = new Interval();
        if (this.getMinValue() != null) {
            res.setMinValue(this.getMinValue());
        }
        if (this.getMaxValue() != null) {
            res.setMaxValue(this.getMaxValue());
        }
        if (this.getUcumString() != null) {
            res.setUcumString(this.getUcumString().getClone());
        }
        if (this.getMinOperator() != null) {
            res.setMinOperator(this.getMinOperator());
        }
        if (this.getMaxOperator() != null) {
            res.setMaxOperator(this.getMaxOperator());
        }
        if (this.getAnnotatorConfidence() != null) {
            res.setAnnotatorConfidence(this.getAnnotatorConfidence());
        }
        if (this.getCharacteristicQuantificationIndex() != null) {
            res.setCharacteristicQuantificationIndex(this.getCharacteristicQuantificationIndex());
        }
        if (this.getLabel() != null) {
            res.setLabel(this.getLabel().getClone());
        }
        if (this.getValueLabel() != null) {
            res.setValueLabel(this.getValueLabel().getClone());
        }
        if (this.getValueDescription() != null) {
            res.setValueDescription(this.getValueDescription().getClone());
        }
        if (this.getComment() != null) {
            res.setComment(this.getComment().getClone());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        if (this.getXsiType() != null) {
            res.setXsiType(this.getXsiType());
        }
        return res;
    }
}
