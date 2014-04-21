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

import main.java.edu.stanford.hakan.aim4api.utility.GenerateId;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class Entity implements IAimXMLOperations {

    private II uniqueIdentifier;
    private String tagName;
    private String xsiType;
    protected Entity initialState = null;

    public II getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    protected void setUniqueIdentifier(II uniqueIdentifier) {
        uniqueIdentifier.setTagName("uniqueIdentifier");
        this.uniqueIdentifier = uniqueIdentifier;
    }

    protected String getTagName() {
        return tagName;
    }

    protected void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getXsiType() {
        return xsiType;
    }

    protected void setXsiType(String xsiType) {
        this.xsiType = xsiType;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("Entity");
        }
        Element res = doc.createElement(getTagName());
        if (this.uniqueIdentifier == null) {
            this.setUniqueIdentifier(new II(GenerateId.getUUID()));
        }
        res.appendChild(this.uniqueIdentifier.getXMLNode(doc));
        if (this.xsiType != null && (this.xsiType == null ? this.getTagName() != null : !this.xsiType.equals(this.getTagName()))) {
            res.setAttribute("xsi:type", this.xsiType.toString());
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        NamedNodeMap map = node.getAttributes();
        if (map.getNamedItem("xsiType") != null) {
            this.xsiType = map.getNamedItem("xsiType").getNodeValue();
        }
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("uniqueIdentifier".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setUniqueIdentifier(obj);
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        Entity oth = (Entity) other;
        if (this.uniqueIdentifier == null ? oth.uniqueIdentifier != null : !this.uniqueIdentifier.isEqualTo(oth.uniqueIdentifier)) {
            return false;
        }
        return true;
    }

    public Entity getClone() {
        Entity res = new Entity();
        if (this.getUniqueIdentifier() != null) {
            res.setUniqueIdentifier(this.getUniqueIdentifier().getClone());
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
