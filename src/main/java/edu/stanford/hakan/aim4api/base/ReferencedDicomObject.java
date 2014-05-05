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
public class ReferencedDicomObject implements IAimXMLOperations {

    private CD modality;
    private II sopInstanceUid;
    private String tagName;

    public CD getModality() {
        return modality;
    }

    public void setModality(CD modality) {
        modality.setTagName("modality");
        this.modality = modality;
    }

    public II getSopInstanceUid() {
        return sopInstanceUid;
    }

    public void setSopInstanceUid(II sopInstanceUid) {
        sopInstanceUid.setTagName("sopInstanceUid");
        this.sopInstanceUid = sopInstanceUid;
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
            setTagName("ReferencedDicomObject");
        }
        Element res = doc.createElement(getTagName());
        if (this.modality != null) {
            res.appendChild(this.modality.getXMLNode(doc));
        }
        if (this.sopInstanceUid != null) {
            res.appendChild(this.sopInstanceUid.getXMLNode(doc));
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("modality".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.setModality(obj);
            }
            if ("sopInstanceUid".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setSopInstanceUid(obj);
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        ReferencedDicomObject oth = (ReferencedDicomObject) other;
        if (this.modality == null ? oth.modality != null : !this.modality.isEqualTo(oth.modality)) {
            return false;
        }
        if (this.sopInstanceUid == null ? oth.sopInstanceUid != null : !this.sopInstanceUid.isEqualTo(oth.sopInstanceUid)) {
            return false;
        }
        return true;
    }

    public ReferencedDicomObject getClone() {
        ReferencedDicomObject res = new ReferencedDicomObject();
        if (this.getModality() != null) {
            res.setModality(this.getModality().getClone());
        }
        if (this.getSopInstanceUid() != null) {
            res.setSopInstanceUid(this.getSopInstanceUid().getClone());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        return res;
    }
}
