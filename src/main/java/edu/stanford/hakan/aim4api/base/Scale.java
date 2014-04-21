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
package main.java.edu.stanford.hakan.aim4api.base;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class Scale extends CharacteristicQuantification {

    public Scale() {
        setXsiType("Scale");
    }
    private ST value;
    private Enumerations.ScaleType type;

    public ST getValue() {
        return value;
    }

    public void setValue(ST value) {
        value.setTagName("value");
        this.value = value;
    }

    public Enumerations.ScaleType getType() {
        return type;
    }

    public void setType(Enumerations.ScaleType type) {
        this.type = type;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("Scale");
        }
        Element res = (Element) super.getXMLNode(doc);
        if (this.value != null) {
            res.appendChild(this.value.getXMLNode(doc));
        }
        if (this.type != null) {
            res.setAttribute("type", this.type.toString());
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
        NamedNodeMap map = node.getAttributes();
        if (map.getNamedItem("type") != null) {
            this.type = Enumerations.ScaleType.valueOf(map.getNamedItem("type").getNodeValue());
        }
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("value".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setValue(obj);
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        Scale oth = (Scale) other;
        if (this.value == null ? oth.value != null : !this.value.isEqualTo(oth.value)) {
            return false;
        }
        if (this.type == null ? oth.type != null : !this.type.equals(oth.type)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    @Override
    public Scale getClone() {
        Scale res = new Scale();
        if (this.getValue() != null) {
            res.setValue(this.getValue().getClone());
        }
        if (this.getType() != null) {
            res.setType(this.getType());
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
