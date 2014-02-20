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
public class AnnotationStatement implements IAimXMLOperations {

    private II subjectUniqueIdentifier;
    private II objectUniqueIdentifier;
    private String tagName;
    private String xsiType;

    public II getSubjectUniqueIdentifier() {
        return subjectUniqueIdentifier;
    }

    public void setSubjectUniqueIdentifier(II subjectUniqueIdentifier) {
        subjectUniqueIdentifier.setTagName("subjectUniqueIdentifier");
        this.subjectUniqueIdentifier = subjectUniqueIdentifier;
    }

    public II getObjectUniqueIdentifier() {
        return objectUniqueIdentifier;
    }

    public void setObjectUniqueIdentifier(II objectUniqueIdentifier) {
        objectUniqueIdentifier.setTagName("objectUniqueIdentifier");
        this.objectUniqueIdentifier = objectUniqueIdentifier;
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
            setTagName("AnnotationStatement");
        }
        Element res = doc.createElement(getTagName());
        if (this.subjectUniqueIdentifier != null) {
            res.appendChild(this.subjectUniqueIdentifier.getXMLNode(doc));
        }
        if (this.objectUniqueIdentifier != null) {
            res.appendChild(this.objectUniqueIdentifier.getXMLNode(doc));
        }
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
            if ("subjectUniqueIdentifier".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setSubjectUniqueIdentifier(obj);
            }
            if ("objectUniqueIdentifier".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setObjectUniqueIdentifier(obj);
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        AnnotationStatement oth = (AnnotationStatement) other;
        if (this.subjectUniqueIdentifier == null ? oth.subjectUniqueIdentifier != null : !this.subjectUniqueIdentifier.isEqualTo(oth.subjectUniqueIdentifier)) {
            return false;
        }
        if (this.objectUniqueIdentifier == null ? oth.objectUniqueIdentifier != null : !this.objectUniqueIdentifier.isEqualTo(oth.objectUniqueIdentifier)) {
            return false;
        }
        return true;
    }

    public AnnotationStatement getClone() {
        AnnotationStatement res = new AnnotationStatement();
        if (this.getSubjectUniqueIdentifier() != null) {
            res.setSubjectUniqueIdentifier(this.getSubjectUniqueIdentifier().getClone());
        }
        if (this.getObjectUniqueIdentifier() != null) {
            res.setObjectUniqueIdentifier(this.getObjectUniqueIdentifier().getClone());
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
