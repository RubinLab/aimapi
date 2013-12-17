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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class Quantile extends CharacteristicQuantification {

    public Quantile() {
        setXsiType("Quantile");
    }
    private Integer bins;
    private Integer selectedBin;
    private Double minValue;
    private Double maxValue;

    public Integer getBins() {
        return bins;
    }

    public void setBins(Integer bins) {
        this.bins = bins;
    }

    public Integer getSelectedBin() {
        return selectedBin;
    }

    public void setSelectedBin(Integer selectedBin) {
        this.selectedBin = selectedBin;
    }

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

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("Quantile");
        }
        Element res = (Element) super.getXMLNode(doc);
        if (this.bins != null) {
            Element el_bins = doc.createElement("bins");
            el_bins.setAttribute("value", this.bins.toString());
            res.appendChild(el_bins);
        }
        if (this.selectedBin != null) {
            Element el_selectedBin = doc.createElement("selectedBin");
            el_selectedBin.setAttribute("value", this.selectedBin.toString());
            res.appendChild(el_selectedBin);
        }
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
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("bins".equals(currentNode.getNodeName())) {
                this.bins = Integer.parseInt(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("selectedBin".equals(currentNode.getNodeName())) {
                this.selectedBin = Integer.parseInt(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("minValue".equals(currentNode.getNodeName())) {
                this.minValue = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("maxValue".equals(currentNode.getNodeName())) {
                this.maxValue = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        Quantile oth = (Quantile) other;
        if (this.bins == null ? oth.bins != null : !this.bins.equals(oth.bins)) {
            return false;
        }
        if (this.selectedBin == null ? oth.selectedBin != null : !this.selectedBin.equals(oth.selectedBin)) {
            return false;
        }
        if (this.minValue == null ? oth.minValue != null : !this.minValue.equals(oth.minValue)) {
            return false;
        }
        if (this.maxValue == null ? oth.maxValue != null : !this.maxValue.equals(oth.maxValue)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    @Override
    public Quantile getClone() {
        Quantile res = new Quantile();
        if (this.getBins() != null) {
            res.setBins(this.getBins());
        }
        if (this.getSelectedBin() != null) {
            res.setSelectedBin(this.getSelectedBin());
        }
        if (this.getMinValue() != null) {
            res.setMinValue(this.getMinValue());
        }
        if (this.getMaxValue() != null) {
            res.setMaxValue(this.getMaxValue());
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
