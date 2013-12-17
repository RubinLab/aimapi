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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author localadmin
 */
public class Algorithm implements IAimXMLOperations {

    private ST name;
    private List<CD> listType = new ArrayList<>();
    private II uniqueIdentifier;
    private ST version;
    private ST description;
    private ParameterCollection parameterCollection = new ParameterCollection();
    private String tagName;

    public ST getName() {
        return name;
    }

    public void setName(ST name) {
        name.setTagName("name");
        this.name = name;
    }

    public List<CD> getListType() {
        return this.listType;
    }

    public void setType(List<CD> listType) {
        this.listType = listType;
        for (int i = 0; i < this.listType.size(); i++) {
            this.listType.get(i).setTagName("type");
        }
    }

    public void addType(CD newType) {
        newType.setTagName("type");
        this.listType.add(newType);
    }

    public II getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(II uniqueIdentifier) {
        uniqueIdentifier.setTagName("uniqueIdentifier");
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public ST getVersion() {
        return version;
    }

    public void setVersion(ST version) {
        version.setTagName("version");
        this.version = version;
    }

    public ST getDescription() {
        return description;
    }

    public void setDescription(ST description) {
        description.setTagName("description");
        this.description = description;
    }

    public ParameterCollection getParameterCollection() {
        return parameterCollection;
    }

    public void setParameterCollection(ParameterCollection parameterCollection) {
        this.parameterCollection = parameterCollection;
    }

    public void addParameter(Parameter newParameter) {
        this.parameterCollection.addParameter(newParameter);
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
            setTagName("Algorithm");
        }
        Element res = doc.createElement(getTagName());
        if (this.name != null) {
            res.appendChild(this.name.getXMLNode(doc));
        }
        if (this.listType.size() > 0) {
            for (int i = 0; i < this.listType.size(); i++) {
                res.appendChild(this.listType.get(i).getXMLNode(doc));
            }
        }
        if (this.uniqueIdentifier != null) {
            res.appendChild(this.uniqueIdentifier.getXMLNode(doc));
        }
        if (this.version != null) {
            res.appendChild(this.version.getXMLNode(doc));
        }
        if (this.description != null) {
            res.appendChild(this.description.getXMLNode(doc));
        }
        if (this.parameterCollection.size() > 0) {
            res.appendChild(this.parameterCollection.getXMLNode(doc));
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        this.listType.clear();
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("name".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setName(obj);
            }
            if ("type".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.addType(obj);
            }
            if ("uniqueIdentifier".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setUniqueIdentifier(obj);
            }
            if ("version".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setVersion(obj);
            }
            if ("description".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setDescription(obj);
            }
            if ("parameterCollection".equals(listChilds.item(i).getNodeName())) {
                this.parameterCollection.setXMLNode(listChilds.item(i));
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        Algorithm oth = (Algorithm) other;
        if (!this.name.isEqualTo(oth.name)) {
            return false;
        }
        if (this.listType.size() != oth.listType.size()) {
            return false;
        }
        for (int i = 0; i < this.listType.size(); i++) {
            if (!this.listType.get(i).isEqualTo(oth.listType.get(i))) {
                return false;
            }
        }
        if (!this.uniqueIdentifier.isEqualTo(oth.uniqueIdentifier)) {
            return false;
        }
        if (!this.version.isEqualTo(oth.version)) {
            return false;
        }
        if (!this.description.isEqualTo(oth.description)) {
            return false;
        }
        if (!this.parameterCollection.isEqualTo(oth.parameterCollection)) {
            return false;
        }
        return true;
    }

    public Algorithm getClone() {
        Algorithm res = new Algorithm();
        if (this.getName() != null) {
            res.setName(this.getName().getClone());
        }
        for (int i = 0; i < this.listType.size(); i++) {
            if (this.listType.get(i) != null) {
                res.addType(this.listType.get(i).getClone());
            }
        }
        if (this.getUniqueIdentifier() != null) {
            res.setUniqueIdentifier(this.getUniqueIdentifier().getClone());
        }
        if (this.getVersion() != null) {
            res.setVersion(this.getVersion().getClone());
        }
        if (this.getDescription() != null) {
            res.setDescription(this.getDescription().getClone());
        }
        if (this.getParameterCollection() != null) {
            res.setParameterCollection(this.getParameterCollection().getClone());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        return res;
    }
}
