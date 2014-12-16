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
public class Image implements IAimXMLOperations {

    private Integer cagridId;
    private String sopClassUID;
    private String sopInstanceUID;
    private String imageURL;

    public Image() {
    }

    public Image(Integer cagridId, String sopClassUID, String sopInstanceUID) {
        this.cagridId = cagridId;
        this.sopClassUID = sopClassUID;
        this.sopInstanceUID = sopInstanceUID;
    }

    public Integer getCagridId() {
        return cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    public String getSopClassUID() {
        return sopClassUID;
    }

    public void setSopClassUID(String sopClassUID) {
        this.sopClassUID = sopClassUID;
    }

    public String getSopInstanceUID() {
        return sopInstanceUID;
    }

    public void setSopInstanceUID(String sopInstanceUID) {
        this.sopInstanceUID = sopInstanceUID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//
//        this.Control();
//
//        Element image = doc.createElement("Image");
//        image.setAttribute("cagridId", Integer.toString(getCagridId()));
//        image.setAttribute("sopClassUID", this.sopClassUID);
//        image.setAttribute("sopInstanceUID", this.sopInstanceUID);
//        return image;
//    }

    @Override
    public void setXMLNode(Node node) {

        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.sopClassUID = map.getNamedItem("sopClassUID").getNodeValue();
        this.sopInstanceUID = map.getNamedItem("sopInstanceUID").getNodeValue();
    }

    
    private void Control() throws AimException {
        if (getCagridId() == null) {
            throw new AimException("AimException: Image's cagridId can not be null");
        }
        if (getSopClassUID() == null) {
            throw new AimException("AimException: Image's sopClassUID can not be null");
        }
        if (getSopInstanceUID() == null) {
            throw new AimException("AimException: Image's sopInstanceUID can not be null");
        }
    }

    public boolean isEqualTo(Object other) {
        Image oth = (Image) other;
        if (this.cagridId != oth.cagridId) {
            return false;
        }
        if (this.sopClassUID == null ? oth.sopClassUID != null : !this.sopClassUID.equals(oth.sopClassUID)) {
            return false;
        }
        if (this.sopInstanceUID == null ? oth.sopInstanceUID != null : !this.sopInstanceUID.equals(oth.sopInstanceUID)) {
            return false;
        }
        return true;
    }

    public edu.stanford.hakan.aim4api.base.Image toAimV4() {
        edu.stanford.hakan.aim4api.base.Image res = new edu.stanford.hakan.aim4api.base.Image();
        res.setSopClassUid(Converter.toII(this.getSopClassUID()));
        res.setSopInstanceUid(Converter.toII(this.getSopInstanceUID()));
        return res;
    }

    public Image(edu.stanford.hakan.aim4api.base.Image v4) {
        this.setCagridId(0);
        if (v4.getSopClassUid() != null) {
            this.setSopClassUID(v4.getSopClassUid().getRoot());
        }
        if (v4.getSopInstanceUid() != null) {
            this.setSopInstanceUID(v4.getSopInstanceUid().getRoot());
        }
    }
}
