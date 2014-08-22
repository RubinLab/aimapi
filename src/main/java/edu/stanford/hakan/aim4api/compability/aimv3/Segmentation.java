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

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hakan BULU
 */
public class Segmentation {

    private Integer cagridId;
    private String sopInstanceUID;
    private String sopClassUID;
    private String referencedSopInstanceUID;
    private Integer segmentNumber;
    private List<ImagingObservation> listImagingObservation = new ArrayList<ImagingObservation>();

    public Segmentation() {
    }

    public Segmentation(Integer cagridId, String sopInstanceUID, String sopClassUID, String referencedSopInstanceUID, Integer segmentNumber) {
        this.cagridId = cagridId;
        this.sopInstanceUID = sopInstanceUID;
        this.sopClassUID = sopClassUID;
        this.referencedSopInstanceUID = referencedSopInstanceUID;
        this.segmentNumber = segmentNumber;
    }

    public void addImagingObservation(ImagingObservation newImagingObservation) {
        this.listImagingObservation.add(newImagingObservation);
    }

    public Integer getCagridId() {
        return cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    public List<ImagingObservation> getListImagingObservation() {
        return listImagingObservation;
    }

    public void setListImagingObservation(List<ImagingObservation> listImagingObservation) {
        this.listImagingObservation = listImagingObservation;
    }

    public String getReferencedSopInstanceUID() {
        return referencedSopInstanceUID;
    }

    public void setReferencedSopInstanceUID(String referencedSopInstanceUID) {
        this.referencedSopInstanceUID = referencedSopInstanceUID;
    }

    public Integer getSegmentNumber() {
        return segmentNumber;
    }

    public void setSegmentNumber(Integer segmentNumber) {
        this.segmentNumber = segmentNumber;
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

    public void setXMLNode(Node node) {

        this.listImagingObservation.clear();
        NodeList listChils = node.getChildNodes();
        for (int i = 0; i < listChils.getLength(); i++) {
            if ("imagingObservation".equals(listChils.item(i).getNodeName())) {
                NodeList tempList = listChils.item(i).getChildNodes();
                for (int j = 0; j < tempList.getLength(); j++) {
                    if ("ImagingObservation".equals(tempList.item(j).getNodeName())) {
                        ImagingObservation obj = new ImagingObservation();
                        obj.setXMLNode(tempList.item(j));
                        this.addImagingObservation(obj);
                    }
                }
            }
        }

        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.sopInstanceUID = map.getNamedItem("sopInstanceUID").getNodeValue();
        this.sopClassUID = map.getNamedItem("sopClassUID").getNodeValue();
        this.referencedSopInstanceUID = map.getNamedItem("referencedSopInstanceUID").getNodeValue();
        this.segmentNumber = Integer.parseInt(map.getNamedItem("segmentNumber").getNodeValue());
    }

    public edu.stanford.hakan.aim4api.base.SegmentationEntity toAimV4() {
        edu.stanford.hakan.aim4api.base.DicomSegmentationEntity res = new edu.stanford.hakan.aim4api.base.DicomSegmentationEntity();
        res.setReferencedSopInstanceUid(Converter.toII(this.getReferencedSopInstanceUID()));
        res.setSegmentNumber(this.getSegmentNumber());
        res.setSopClassUid(Converter.toII(this.getSopClassUID()));
        res.setSopInstanceUid(Converter.toII(this.getSopInstanceUID()));
        return res;
    }
}
