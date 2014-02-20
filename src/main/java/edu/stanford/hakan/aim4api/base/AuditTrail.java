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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class AuditTrail implements IAimXMLOperations {

    private CD statusCode;
    private String dateTime;
    private CD changeReason;
    private II worklistSubtaskUid;
    private ST comment;
    private String tagName;

    public CD getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(CD statusCode) {
        statusCode.setTagName("statusCode");
        this.statusCode = statusCode;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public CD getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(CD changeReason) {
        changeReason.setTagName("changeReason");
        this.changeReason = changeReason;
    }

    public II getWorklistSubtaskUid() {
        return worklistSubtaskUid;
    }

    public void setWorklistSubtaskUid(II worklistSubtaskUid) {
        worklistSubtaskUid.setTagName("worklistSubtaskUid");
        this.worklistSubtaskUid = worklistSubtaskUid;
    }

    public ST getComment() {
        return comment;
    }

    public void setComment(ST comment) {
        comment.setTagName("comment");
        this.comment = comment;
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
            setTagName("AuditTrail");
        }
        Element res = doc.createElement(getTagName());
        if (this.statusCode != null) {
            res.appendChild(this.statusCode.getXMLNode(doc));
        }
        if (this.dateTime != null) {
            Element el_dateTime = doc.createElement("dateTime");
            el_dateTime.setAttribute("value", this.dateTime.toString());
            res.appendChild(el_dateTime);
        }
        if (this.changeReason != null) {
            res.appendChild(this.changeReason.getXMLNode(doc));
        }
        if (this.worklistSubtaskUid != null) {
            res.appendChild(this.worklistSubtaskUid.getXMLNode(doc));
        }
        if (this.comment != null) {
            res.appendChild(this.comment.getXMLNode(doc));
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("statusCode".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.setStatusCode(obj);
            }
            if ("dateTime".equals(currentNode.getNodeName())) {
                this.dateTime = currentNode.getAttributes().getNamedItem("value").getNodeValue();
            }
            if ("changeReason".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.setChangeReason(obj);
            }
            if ("worklistSubtaskUid".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setWorklistSubtaskUid(obj);
            }
            if ("comment".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setComment(obj);
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        AuditTrail oth = (AuditTrail) other;
        if (this.statusCode == null ? oth.statusCode != null : !this.statusCode.isEqualTo(oth.statusCode)) {
            return false;
        }
        if (this.dateTime == null ? oth.dateTime != null : !this.dateTime.equals(oth.dateTime)) {
            return false;
        }
        if (this.changeReason == null ? oth.changeReason != null : !this.changeReason.isEqualTo(oth.changeReason)) {
            return false;
        }
        if (this.worklistSubtaskUid == null ? oth.worklistSubtaskUid != null : !this.worklistSubtaskUid.isEqualTo(oth.worklistSubtaskUid)) {
            return false;
        }
        if (this.comment == null ? oth.comment != null : !this.comment.isEqualTo(oth.comment)) {
            return false;
        }
        return true;
    }

    public AuditTrail getClone() {
        AuditTrail res = new AuditTrail();
        if (this.getStatusCode() != null) {
            res.setStatusCode(this.getStatusCode().getClone());
        }
        if (this.getDateTime() != null) {
            res.setDateTime(this.getDateTime());
        }
        if (this.getChangeReason() != null) {
            res.setChangeReason(this.getChangeReason().getClone());
        }
        if (this.getWorklistSubtaskUid() != null) {
            res.setWorklistSubtaskUid(this.getWorklistSubtaskUid().getClone());
        }
        if (this.getComment() != null) {
            res.setComment(this.getComment().getClone());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        return res;
    }
}
