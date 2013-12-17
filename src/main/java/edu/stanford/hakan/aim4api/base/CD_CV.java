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
public class CD_CV implements IAimXMLOperations {

    private ST displayName;
    private ED_Text originalText;
    private List<CR> listQualifier = new ArrayList<>();
    private List<CDGroup> listGroup = new ArrayList<>();
    private CD translation;
    private XReference source;
    private Boolean hasIsoAttributes;
    private String tagName;

    public ST getDisplayName() {
        return displayName;
    }

    public void setDisplayName(ST displayName) {
        displayName.setTagName("displayName");
        displayName.setHasIsoAttributes(true);
        this.displayName = displayName;
    }

    public ED_Text getOriginalText() {
        return originalText;
    }

    public void setOriginalText(ED_Text originalText) {
        originalText.setTagName("originalText");
        originalText.setHasIsoAttributes(true);
        this.originalText = originalText;
    }

    public List<CR> getListQualifier() {
        return this.listQualifier;
    }

    public void setQualifier(List<CR> listQualifier) {
        this.listQualifier = listQualifier;
        for (int i = 0; i < this.listQualifier.size(); i++) {
            this.listQualifier.get(i).setTagName("qualifier");
        }
    }

    public void addQualifier(CR newQualifier) {
        newQualifier.setTagName("qualifier");
        this.listQualifier.add(newQualifier);
    }

    public List<CDGroup> getListGroup() {
        return this.listGroup;
    }

    public void setGroup(List<CDGroup> listGroup) {
        this.listGroup = listGroup;
        for (int i = 0; i < this.listGroup.size(); i++) {
            this.listGroup.get(i).setTagName("group");
        }
    }

    public void addGroup(CDGroup newGroup) {
        newGroup.setTagName("group");
        this.listGroup.add(newGroup);
    }

    public CD getTranslation() {
        return translation;
    }

    public void setTranslation(CD translation) {
        translation.setTagName("translation");
        translation.setHasIsoAttributes(true);
        this.translation = translation;
    }

    public XReference getSource() {
        return source;
    }

    public void setSource(XReference source) {
        source.setTagName("source");
        source.setHasIsoAttributes(true);
        this.source = source;
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
            setTagName("CD_CV");
        }
        if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
            this.setTagName("iso:".concat(this.getTagName()));
        }
        Element res = doc.createElement(getTagName());
        if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
            res.setAttribute("xmlns:iso", "uri:iso.org:21090");
        }
        if (this.displayName != null) {
            res.appendChild(this.displayName.getXMLNode(doc));
        }
        if (this.originalText != null) {
            res.appendChild(this.originalText.getXMLNode(doc));
        }
        if (this.listQualifier.size() > 0) {
            for (int i = 0; i < this.listQualifier.size(); i++) {
                res.appendChild(this.listQualifier.get(i).getXMLNode(doc));
            }
        }
        if (this.listGroup.size() > 0) {
            for (int i = 0; i < this.listGroup.size(); i++) {
                res.appendChild(this.listGroup.get(i).getXMLNode(doc));
            }
        }
        if (this.translation != null) {
            res.appendChild(this.translation.getXMLNode(doc));
        }
        if (this.source != null) {
            res.appendChild(this.source.getXMLNode(doc));
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        this.listQualifier.clear();
        this.listGroup.clear();
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("displayName".equals(currentNode.getNodeName()) || "iso:displayName".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setDisplayName(obj);
            }
            if ("originalText".equals(currentNode.getNodeName()) || "iso:originalText".equals(currentNode.getNodeName())) {
                ED_Text obj = new ED_Text();
                obj.setXMLNode(currentNode);
                this.setOriginalText(obj);
            }
            if ("qualifier".equals(currentNode.getNodeName()) || "iso:qualifier".equals(currentNode.getNodeName())) {
                CR obj = new CR();
                obj.setXMLNode(currentNode);
                this.addQualifier(obj);
            }
            if ("group".equals(currentNode.getNodeName()) || "iso:group".equals(currentNode.getNodeName())) {
                CDGroup obj = new CDGroup();
                obj.setXMLNode(currentNode);
                this.addGroup(obj);
            }
            if ("translation".equals(currentNode.getNodeName()) || "iso:translation".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.setTranslation(obj);
            }
            if ("source".equals(currentNode.getNodeName()) || "iso:source".equals(currentNode.getNodeName())) {
                XReference obj = new XReference();
                obj.setXMLNode(currentNode);
                this.setSource(obj);
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        CD_CV oth = (CD_CV) other;
        if (!this.displayName.isEqualTo(oth.displayName)) {
            return false;
        }
        if (!this.originalText.isEqualTo(oth.originalText)) {
            return false;
        }
        if (this.listQualifier.size() != oth.listQualifier.size()) {
            return false;
        }
        for (int i = 0; i < this.listQualifier.size(); i++) {
            if (!this.listQualifier.get(i).isEqualTo(oth.listQualifier.get(i))) {
                return false;
            }
        }
        if (this.listGroup.size() != oth.listGroup.size()) {
            return false;
        }
        for (int i = 0; i < this.listGroup.size(); i++) {
            if (!this.listGroup.get(i).isEqualTo(oth.listGroup.get(i))) {
                return false;
            }
        }
        if (!this.translation.isEqualTo(oth.translation)) {
            return false;
        }
        if (!this.source.isEqualTo(oth.source)) {
            return false;
        }
        return true;
    }

    public CD_CV getClone() {
        CD_CV res = new CD_CV();
        if (this.getDisplayName() != null) {
            res.setDisplayName(this.getDisplayName().getClone());
        }
        if (this.getOriginalText() != null) {
            res.setOriginalText(this.getOriginalText().getClone());
        }
        for (int i = 0; i < this.listQualifier.size(); i++) {
            if (this.listQualifier.get(i) != null) {
                res.addQualifier(this.listQualifier.get(i).getClone());
            }
        }
        for (int i = 0; i < this.listGroup.size(); i++) {
            if (this.listGroup.get(i) != null) {
                res.addGroup(this.listGroup.get(i).getClone());
            }
        }
        if (this.getTranslation() != null) {
            res.setTranslation(this.getTranslation().getClone());
        }
        if (this.getSource() != null) {
            res.setSource(this.getSource().getClone());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        return res;
    }
}
