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
public class GeneralImage implements IAimXMLOperations {

    private ST patientOrientationColumn;
    private ST patientOrientationRow;
    private String tagName;

    public ST getPatientOrientationColumn() {
        return patientOrientationColumn;
    }

    public void setPatientOrientationColumn(ST patientOrientationColumn) {
        patientOrientationColumn.setTagName("patientOrientationColumn");
        this.patientOrientationColumn = patientOrientationColumn;
    }

    public ST getPatientOrientationRow() {
        return patientOrientationRow;
    }

    public void setPatientOrientationRow(ST patientOrientationRow) {
        patientOrientationRow.setTagName("patientOrientationRow");
        this.patientOrientationRow = patientOrientationRow;
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
            setTagName("GeneralImage");
        }
        Element res = doc.createElement(getTagName());
        if (this.patientOrientationColumn != null) {
            res.appendChild(this.patientOrientationColumn.getXMLNode(doc));
        }
        if (this.patientOrientationRow != null) {
            res.appendChild(this.patientOrientationRow.getXMLNode(doc));
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("patientOrientationColumn".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setPatientOrientationColumn(obj);
            }
            if ("patientOrientationRow".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setPatientOrientationRow(obj);
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        GeneralImage oth = (GeneralImage) other;
        if (!this.patientOrientationColumn.isEqualTo(oth.patientOrientationColumn)) {
            return false;
        }
        if (!this.patientOrientationRow.isEqualTo(oth.patientOrientationRow)) {
            return false;
        }
        return true;
    }

    public GeneralImage getClone() {
        GeneralImage res = new GeneralImage();
        if (this.getPatientOrientationColumn() != null) {
            res.setPatientOrientationColumn(this.getPatientOrientationColumn().getClone());
        }
        if (this.getPatientOrientationRow() != null) {
            res.setPatientOrientationRow(this.getPatientOrientationRow().getClone());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        return res;
    }
}
