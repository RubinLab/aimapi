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
package edu.stanford.hakan.aim4api.compability.aimv3;

import edu.stanford.hakan.aim4api.base.AimException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan BULU
 */
public class Equipment implements IAimXMLOperations {

    private Integer cagridId;
    private String manufacturerName;
    private String manufacturerModelName;
    private String softwareVersion;

    public Equipment() {
    }

    public Equipment(Integer cagridId, String manufacturerName, String manufacturerModelName, String softwareVersion) {
        this.cagridId = cagridId;
        this.manufacturerName = manufacturerName;
        this.manufacturerModelName = manufacturerModelName;
        this.softwareVersion = softwareVersion;
    }

    public Integer getCagridId() {
        return cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    public String getManufacturerModelName() {
        return manufacturerModelName;
    }

    public void setManufacturerModelName(String manufacturerModelName) {
        this.manufacturerModelName = manufacturerModelName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//
//        this.Control();
//
//        Element equipment = doc.createElement("Equipment");
//        equipment.setAttribute("cagridId", Integer.toString(getCagridId()));
//        equipment.setAttribute("manufacturerName", this.manufacturerName);
//        if (this.manufacturerModelName != null) {
//            equipment.setAttribute("manufacturerModelName", this.manufacturerModelName);
//        }
//        if (this.softwareVersion != null) {
//            equipment.setAttribute("softwareVersion", this.softwareVersion);
//        }
//        return equipment;
//    }
    @Override
    public void setXMLNode(Node node) {

        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.manufacturerName = map.getNamedItem("manufacturerName").getNodeValue();
        if (map.getNamedItem("manufacturerModelName") != null) {
            this.manufacturerModelName = map.getNamedItem("manufacturerModelName").getNodeValue();
        }
        if (map.getNamedItem("softwareVersion") != null) {
            this.softwareVersion = map.getNamedItem("softwareVersion").getNodeValue();
        }
    }

    private void Control() throws AimException {
        if (getCagridId() == null) {
            throw new AimException("AimException: Equipment's cagridId can not be null");
        }
        if (getManufacturerName() == null) {
            throw new AimException("AimException: Equipment's manufacturerName can not be null");
        }
    }

    public boolean isEqualTo(Object other) {
        Equipment oth = (Equipment) other;
        if (this.cagridId != oth.cagridId) {
            return false;
        }
        if (this.manufacturerName == null ? oth.manufacturerName != null : !this.manufacturerName.equals(oth.manufacturerName)) {
            return false;
        }
        if (this.manufacturerModelName == null ? oth.manufacturerModelName != null : !this.manufacturerModelName.equals(oth.manufacturerModelName)) {
            return false;
        }
        if (this.softwareVersion == null ? oth.softwareVersion != null : !this.softwareVersion.equals(oth.softwareVersion)) {
            return false;
        }
        return true;
    }

    public edu.stanford.hakan.aim4api.base.Equipment toAimV4() {
        edu.stanford.hakan.aim4api.base.Equipment res = new edu.stanford.hakan.aim4api.base.Equipment();
        res.setManufacturerModelName(Converter.toST(this.getManufacturerModelName()));
        res.setManufacturerName(Converter.toST(this.getManufacturerName()));
        res.setSoftwareVersion(Converter.toST(this.getSoftwareVersion()));
        return res;
    }

    public Equipment(edu.stanford.hakan.aim4api.base.Equipment v4) {
        this.setCagridId(0);
        if (v4.getManufacturerModelName() != null) {
            this.setManufacturerModelName(v4.getManufacturerModelName().getValue());
        }
        if (v4.getManufacturerName() != null) {
            this.setManufacturerName(v4.getManufacturerName().getValue());
        }
        if (v4.getSoftwareVersion() != null) {
            this.setSoftwareVersion(v4.getSoftwareVersion().getValue());
        }
    }

    public Equipment getClone() {
        Equipment res = new Equipment();
        if (this.cagridId != null) {
            res.cagridId = this.cagridId;
        }
        if (this.manufacturerName != null) {
            res.manufacturerName = this.manufacturerName;
        }
        if (this.manufacturerModelName != null) {
            res.manufacturerModelName = this.manufacturerModelName;
        }
        if (this.softwareVersion != null) {
            res.softwareVersion = this.softwareVersion;
        }
        return res;
    }
}
