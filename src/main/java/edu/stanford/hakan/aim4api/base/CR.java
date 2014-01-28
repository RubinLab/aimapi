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
public class CR implements IAimXMLOperations {

    private CD_CV name;
    private CD value;
    private Boolean inverted;
    private Boolean hasIsoAttributes;
    private String tagName;

    public CD_CV getName() {
        return name;
    }

    public void setName(CD_CV name) {
        name.setTagName("name");
        name.setHasIsoAttributes(true);
        this.name = name;
    }

    public CD getValue() {
        return value;
    }

    public void setValue(CD value) {
        value.setTagName("value");
        value.setHasIsoAttributes(true);
        this.value = value;
    }

    public Boolean getInverted() {
        return inverted;
    }

    public void setInverted(Boolean inverted) {
        this.inverted = inverted;
    }

    protected Boolean getHasIsoAttributes() {
        return hasIsoAttributes;
    }

    protected void setHasIsoAttributes(Boolean hasIsoAttributes) {
        this.hasIsoAttributes = hasIsoAttributes;
    }

    protected String getTagName() {
        return tagName;
    }

    protected void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("CR");
        }
        if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
            this.setTagName("iso:".concat(this.getTagName()));
        }
        Element res = doc.createElement(getTagName());
        if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
            res.setAttribute("xmlns:iso", "uri:iso.org:21090");
        }
        if (this.name != null) {
            res.appendChild(this.name.getXMLNode(doc));
        }
        if (this.value != null) {
            res.appendChild(this.value.getXMLNode(doc));
        }
        if (this.inverted != null) {
            res.setAttribute("inverted", this.inverted.toString());
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        NamedNodeMap map = node.getAttributes();
        if (map.getNamedItem("inverted") != null) {
            this.inverted = Boolean.parseBoolean(map.getNamedItem("inverted").getNodeValue());
        }
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("name".equals(currentNode.getNodeName()) || "iso:name".equals(currentNode.getNodeName())) {
                CD_CV obj = new CD_CV();
                obj.setXMLNode(currentNode);
                this.setName(obj);
            }
            if ("value".equals(currentNode.getNodeName()) || "iso:value".equals(currentNode.getNodeName())) {
                CD obj = new CD();
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
        CR oth = (CR) other;
        if (!this.name.isEqualTo(oth.name)) {
            return false;
        }
        if (!this.value.isEqualTo(oth.value)) {
            return false;
        }
        if (this.inverted == null ? oth.inverted != null : !this.inverted.equals(oth.inverted)) {
            return false;
        }
        return true;
    }

    public CR getClone() {
        CR res = new CR();
        if (this.getName() != null) {
            res.setName(this.getName().getClone());
        }
        if (this.getValue() != null) {
            res.setValue(this.getValue().getClone());
        }
        if (this.getInverted() != null) {
            res.setInverted(this.getInverted());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        return res;
    }
}
