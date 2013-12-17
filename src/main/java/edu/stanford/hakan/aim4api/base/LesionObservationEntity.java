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
public class LesionObservationEntity extends Entity {

    public LesionObservationEntity() {
        setXsiType("LesionObservationEntity");
    }
    private II lesionUniqueIdentifier;
    private Boolean isAdditionalObservation;

    public II getLesionUniqueIdentifier() {
        return lesionUniqueIdentifier;
    }

    public void setLesionUniqueIdentifier(II lesionUniqueIdentifier) {
        lesionUniqueIdentifier.setTagName("lesionUniqueIdentifier");
        this.lesionUniqueIdentifier = lesionUniqueIdentifier;
    }

    public Boolean getIsAdditionalObservation() {
        return isAdditionalObservation;
    }

    public void setIsAdditionalObservation(Boolean isAdditionalObservation) {
        this.isAdditionalObservation = isAdditionalObservation;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("LesionObservationEntity");
        }
        Element res = (Element) super.getXMLNode(doc);
        if (this.lesionUniqueIdentifier != null) {
            res.appendChild(this.lesionUniqueIdentifier.getXMLNode(doc));
        }
        if (this.isAdditionalObservation != null) {
            Element el_isAdditionalObservation = doc.createElement("isAdditionalObservation");
            el_isAdditionalObservation.setAttribute("value", this.isAdditionalObservation.toString());
            res.appendChild(el_isAdditionalObservation);
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("lesionUniqueIdentifier".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setLesionUniqueIdentifier(obj);
            }
            if ("isAdditionalObservation".equals(currentNode.getNodeName())) {
                this.isAdditionalObservation = Boolean.parseBoolean(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        LesionObservationEntity oth = (LesionObservationEntity) other;
        if (!this.lesionUniqueIdentifier.isEqualTo(oth.lesionUniqueIdentifier)) {
            return false;
        }
        if (this.isAdditionalObservation == null ? oth.isAdditionalObservation != null : !this.isAdditionalObservation.equals(oth.isAdditionalObservation)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    @Override
    public LesionObservationEntity getClone() {
        LesionObservationEntity res = new LesionObservationEntity();
        if (this.getLesionUniqueIdentifier() != null) {
            res.setLesionUniqueIdentifier(this.getLesionUniqueIdentifier().getClone());
        }
        if (this.getIsAdditionalObservation() != null) {
            res.setIsAdditionalObservation(this.getIsAdditionalObservation());
        }
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
