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
public class AdjudicationObservation implements IAimXMLOperations {

    private II observationUid;
    private CD observationScope;
    private CD personObserversRoleInThisProcedure;
    private List<ST> listIdentifierWithinAcceptedPersonObserversRole = new ArrayList<>();
    private List<ST> listIdentifierWithinRejectedPersonObserversRole = new ArrayList<>();
    private CD reasonForChoice;
    private CD reasonForDiscordance;
    private ST comment;
    private Boolean imageQualityIssuesDiscordance;
    private String tagName;

    public II getObservationUid() {
        return observationUid;
    }

    public void setObservationUid(II observationUid) {
        observationUid.setTagName("observationUid");
        this.observationUid = observationUid;
    }

    public CD getObservationScope() {
        return observationScope;
    }

    public void setObservationScope(CD observationScope) {
        observationScope.setTagName("observationScope");
        this.observationScope = observationScope;
    }

    public CD getPersonObserversRoleInThisProcedure() {
        return personObserversRoleInThisProcedure;
    }

    public void setPersonObserversRoleInThisProcedure(CD personObserversRoleInThisProcedure) {
        personObserversRoleInThisProcedure.setTagName("personObserversRoleInThisProcedure");
        this.personObserversRoleInThisProcedure = personObserversRoleInThisProcedure;
    }

    public List<ST> getListIdentifierWithinAcceptedPersonObserversRole() {
        return this.listIdentifierWithinAcceptedPersonObserversRole;
    }

    public void setIdentifierWithinAcceptedPersonObserversRole(List<ST> listIdentifierWithinAcceptedPersonObserversRole) {
        this.listIdentifierWithinAcceptedPersonObserversRole = listIdentifierWithinAcceptedPersonObserversRole;
        for (int i = 0; i < this.listIdentifierWithinAcceptedPersonObserversRole.size(); i++) {
            this.listIdentifierWithinAcceptedPersonObserversRole.get(i).setTagName("identifierWithinAcceptedPersonObserversRole");
        }
    }

    public void addIdentifierWithinAcceptedPersonObserversRole(ST newIdentifierWithinAcceptedPersonObserversRole) {
        newIdentifierWithinAcceptedPersonObserversRole.setTagName("identifierWithinAcceptedPersonObserversRole");
        this.listIdentifierWithinAcceptedPersonObserversRole.add(newIdentifierWithinAcceptedPersonObserversRole);
    }

    public List<ST> getListIdentifierWithinRejectedPersonObserversRole() {
        return this.listIdentifierWithinRejectedPersonObserversRole;
    }

    public void setIdentifierWithinRejectedPersonObserversRole(List<ST> listIdentifierWithinRejectedPersonObserversRole) {
        this.listIdentifierWithinRejectedPersonObserversRole = listIdentifierWithinRejectedPersonObserversRole;
        for (int i = 0; i < this.listIdentifierWithinRejectedPersonObserversRole.size(); i++) {
            this.listIdentifierWithinRejectedPersonObserversRole.get(i).setTagName("identifierWithinRejectedPersonObserversRole");
        }
    }

    public void addIdentifierWithinRejectedPersonObserversRole(ST newIdentifierWithinRejectedPersonObserversRole) {
        newIdentifierWithinRejectedPersonObserversRole.setTagName("identifierWithinRejectedPersonObserversRole");
        this.listIdentifierWithinRejectedPersonObserversRole.add(newIdentifierWithinRejectedPersonObserversRole);
    }

    public CD getReasonForChoice() {
        return reasonForChoice;
    }

    public void setReasonForChoice(CD reasonForChoice) {
        reasonForChoice.setTagName("reasonForChoice");
        this.reasonForChoice = reasonForChoice;
    }

    public CD getReasonForDiscordance() {
        return reasonForDiscordance;
    }

    public void setReasonForDiscordance(CD reasonForDiscordance) {
        reasonForDiscordance.setTagName("reasonForDiscordance");
        this.reasonForDiscordance = reasonForDiscordance;
    }

    public ST getComment() {
        return comment;
    }

    public void setComment(ST comment) {
        comment.setTagName("comment");
        this.comment = comment;
    }

    public Boolean getImageQualityIssuesDiscordance() {
        return imageQualityIssuesDiscordance;
    }

    public void setImageQualityIssuesDiscordance(Boolean imageQualityIssuesDiscordance) {
        this.imageQualityIssuesDiscordance = imageQualityIssuesDiscordance;
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
            setTagName("AdjudicationObservation");
        }
        Element res = doc.createElement(getTagName());
        if (this.observationUid != null) {
            res.appendChild(this.observationUid.getXMLNode(doc));
        }
        if (this.observationScope != null) {
            res.appendChild(this.observationScope.getXMLNode(doc));
        }
        if (this.personObserversRoleInThisProcedure != null) {
            res.appendChild(this.personObserversRoleInThisProcedure.getXMLNode(doc));
        }
        if (this.listIdentifierWithinAcceptedPersonObserversRole.size() > 0) {
            for (int i = 0; i < this.listIdentifierWithinAcceptedPersonObserversRole.size(); i++) {
                res.appendChild(this.listIdentifierWithinAcceptedPersonObserversRole.get(i).getXMLNode(doc));
            }
        }
        if (this.listIdentifierWithinRejectedPersonObserversRole.size() > 0) {
            for (int i = 0; i < this.listIdentifierWithinRejectedPersonObserversRole.size(); i++) {
                res.appendChild(this.listIdentifierWithinRejectedPersonObserversRole.get(i).getXMLNode(doc));
            }
        }
        if (this.reasonForChoice != null) {
            res.appendChild(this.reasonForChoice.getXMLNode(doc));
        }
        if (this.reasonForDiscordance != null) {
            res.appendChild(this.reasonForDiscordance.getXMLNode(doc));
        }
        if (this.comment != null) {
            res.appendChild(this.comment.getXMLNode(doc));
        }
        if (this.imageQualityIssuesDiscordance != null) {
            Element el_imageQualityIssuesDiscordance = doc.createElement("imageQualityIssuesDiscordance");
            el_imageQualityIssuesDiscordance.setAttribute("value", this.imageQualityIssuesDiscordance.toString());
            res.appendChild(el_imageQualityIssuesDiscordance);
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        this.listIdentifierWithinAcceptedPersonObserversRole.clear();
        this.listIdentifierWithinRejectedPersonObserversRole.clear();
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("observationUid".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setObservationUid(obj);
            }
            if ("observationScope".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.setObservationScope(obj);
            }
            if ("personObserversRoleInThisProcedure".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.setPersonObserversRoleInThisProcedure(obj);
            }
            if ("identifierWithinAcceptedPersonObserversRole".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.addIdentifierWithinAcceptedPersonObserversRole(obj);
            }
            if ("identifierWithinRejectedPersonObserversRole".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.addIdentifierWithinRejectedPersonObserversRole(obj);
            }
            if ("reasonForChoice".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.setReasonForChoice(obj);
            }
            if ("reasonForDiscordance".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.setReasonForDiscordance(obj);
            }
            if ("comment".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setComment(obj);
            }
            if ("imageQualityIssuesDiscordance".equals(currentNode.getNodeName())) {
                this.imageQualityIssuesDiscordance = Boolean.parseBoolean(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        AdjudicationObservation oth = (AdjudicationObservation) other;
        if (!this.observationUid.isEqualTo(oth.observationUid)) {
            return false;
        }
        if (!this.observationScope.isEqualTo(oth.observationScope)) {
            return false;
        }
        if (!this.personObserversRoleInThisProcedure.isEqualTo(oth.personObserversRoleInThisProcedure)) {
            return false;
        }
        if (this.listIdentifierWithinAcceptedPersonObserversRole.size() != oth.listIdentifierWithinAcceptedPersonObserversRole.size()) {
            return false;
        }
        for (int i = 0; i < this.listIdentifierWithinAcceptedPersonObserversRole.size(); i++) {
            if (!this.listIdentifierWithinAcceptedPersonObserversRole.get(i).isEqualTo(oth.listIdentifierWithinAcceptedPersonObserversRole.get(i))) {
                return false;
            }
        }
        if (this.listIdentifierWithinRejectedPersonObserversRole.size() != oth.listIdentifierWithinRejectedPersonObserversRole.size()) {
            return false;
        }
        for (int i = 0; i < this.listIdentifierWithinRejectedPersonObserversRole.size(); i++) {
            if (!this.listIdentifierWithinRejectedPersonObserversRole.get(i).isEqualTo(oth.listIdentifierWithinRejectedPersonObserversRole.get(i))) {
                return false;
            }
        }
        if (!this.reasonForChoice.isEqualTo(oth.reasonForChoice)) {
            return false;
        }
        if (!this.reasonForDiscordance.isEqualTo(oth.reasonForDiscordance)) {
            return false;
        }
        if (!this.comment.isEqualTo(oth.comment)) {
            return false;
        }
        if (this.imageQualityIssuesDiscordance == null ? oth.imageQualityIssuesDiscordance != null : !this.imageQualityIssuesDiscordance.equals(oth.imageQualityIssuesDiscordance)) {
            return false;
        }
        return true;
    }

    public AdjudicationObservation getClone() {
        AdjudicationObservation res = new AdjudicationObservation();
        if (this.getObservationUid() != null) {
            res.setObservationUid(this.getObservationUid().getClone());
        }
        if (this.getObservationScope() != null) {
            res.setObservationScope(this.getObservationScope().getClone());
        }
        if (this.getPersonObserversRoleInThisProcedure() != null) {
            res.setPersonObserversRoleInThisProcedure(this.getPersonObserversRoleInThisProcedure().getClone());
        }
        for (int i = 0; i < this.listIdentifierWithinAcceptedPersonObserversRole.size(); i++) {
            if (this.listIdentifierWithinAcceptedPersonObserversRole.get(i) != null) {
                res.addIdentifierWithinAcceptedPersonObserversRole(this.listIdentifierWithinAcceptedPersonObserversRole.get(i).getClone());
            }
        }
        for (int i = 0; i < this.listIdentifierWithinRejectedPersonObserversRole.size(); i++) {
            if (this.listIdentifierWithinRejectedPersonObserversRole.get(i) != null) {
                res.addIdentifierWithinRejectedPersonObserversRole(this.listIdentifierWithinRejectedPersonObserversRole.get(i).getClone());
            }
        }
        if (this.getReasonForChoice() != null) {
            res.setReasonForChoice(this.getReasonForChoice().getClone());
        }
        if (this.getReasonForDiscordance() != null) {
            res.setReasonForDiscordance(this.getReasonForDiscordance().getClone());
        }
        if (this.getComment() != null) {
            res.setComment(this.getComment().getClone());
        }
        if (this.getImageQualityIssuesDiscordance() != null) {
            res.setImageQualityIssuesDiscordance(this.getImageQualityIssuesDiscordance());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        return res;
    }
}
