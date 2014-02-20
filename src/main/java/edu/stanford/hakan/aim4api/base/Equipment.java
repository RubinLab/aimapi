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
public class Equipment implements IAimXMLOperations {

    private ST manufacturerName;
    private ST manufacturerModelName;
    private ST deviceSerialNumber;
    private ST softwareVersion;
    private String tagName;
    private Equipment initialState = null;

    public ST getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(ST manufacturerName) {
        manufacturerName.setTagName("manufacturerName");
        this.manufacturerName = manufacturerName;
    }

    public ST getManufacturerModelName() {
        return manufacturerModelName;
    }

    public void setManufacturerModelName(ST manufacturerModelName) {
        manufacturerModelName.setTagName("manufacturerModelName");
        this.manufacturerModelName = manufacturerModelName;
    }

    public ST getDeviceSerialNumber() {
        return deviceSerialNumber;
    }

    public void setDeviceSerialNumber(ST deviceSerialNumber) {
        deviceSerialNumber.setTagName("deviceSerialNumber");
        this.deviceSerialNumber = deviceSerialNumber;
    }

    public ST getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(ST softwareVersion) {
        softwareVersion.setTagName("softwareVersion");
        this.softwareVersion = softwareVersion;
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
            setTagName("Equipment");
        }
        Element res = doc.createElement(getTagName());
        if (this.manufacturerName != null) {
            res.appendChild(this.manufacturerName.getXMLNode(doc));
        }
        if (this.manufacturerModelName != null) {
            res.appendChild(this.manufacturerModelName.getXMLNode(doc));
        }
        if (this.deviceSerialNumber != null) {
            res.appendChild(this.deviceSerialNumber.getXMLNode(doc));
        }
        if (this.softwareVersion != null) {
            res.appendChild(this.softwareVersion.getXMLNode(doc));
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("manufacturerName".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setManufacturerName(obj);
            }
            if ("manufacturerModelName".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setManufacturerModelName(obj);
            }
            if ("deviceSerialNumber".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setDeviceSerialNumber(obj);
            }
            if ("softwareVersion".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setSoftwareVersion(obj);
            }
        }
        //*** Setting the initialState. I will use it while saving operation, if the class is updated or not.
        this.initialState = this.getClone();
    }

    public boolean getIsEdited() {
        if (this.initialState == null) {
            return false;
        }
        return !this.isEqualTo(this.initialState);
    }

    public Equipment getInitialState() {
        return this.initialState;
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        Equipment oth = (Equipment) other;
        if (this.manufacturerName == null ? oth.manufacturerName != null : !this.manufacturerName.isEqualTo(oth.manufacturerName)) {
            return false;
        }
        if (this.manufacturerModelName == null ? oth.manufacturerModelName != null : !this.manufacturerModelName.isEqualTo(oth.manufacturerModelName)) {
            return false;
        }
        if (this.deviceSerialNumber == null ? oth.deviceSerialNumber != null : !this.deviceSerialNumber.isEqualTo(oth.deviceSerialNumber)) {
            return false;
        }
        if (this.softwareVersion == null ? oth.softwareVersion != null : !this.softwareVersion.isEqualTo(oth.softwareVersion)) {
            return false;
        }
        return true;
    }

    public Equipment getClone() {
        Equipment res = new Equipment();
        if (this.getManufacturerName() != null) {
            res.setManufacturerName(this.getManufacturerName().getClone());
        }
        if (this.getManufacturerModelName() != null) {
            res.setManufacturerModelName(this.getManufacturerModelName().getClone());
        }
        if (this.getDeviceSerialNumber() != null) {
            res.setDeviceSerialNumber(this.getDeviceSerialNumber().getClone());
        }
        if (this.getSoftwareVersion() != null) {
            res.setSoftwareVersion(this.getSoftwareVersion().getClone());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        return res;
    }
}
