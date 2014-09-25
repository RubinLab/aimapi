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
import org.w3c.dom.NodeList;

/**
 *
 * @author Hakan BULU
 */
public class ImageStudy implements IAimXMLOperations {

    private Integer cagridId;
    private String instanceUID;
    private String startDate;
    private String startTime;
    private ImageSeries imageSeries;

    public ImageStudy() {
    }

    public ImageStudy(Integer cagridId, String instanceUID, String startDate, String startTime) {
        this.cagridId = cagridId;
        this.instanceUID = instanceUID;
        this.startDate = startDate;
        this.startTime = startTime;
    }

    public Integer getCagridId() {
        return cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    public String getInstanceUID() {
        return instanceUID;
    }

    public void setInstanceUID(String instanceUID) {
        this.instanceUID = instanceUID;
    }

    public ImageSeries getImageSeries() {
        return imageSeries;
    }

    public void setImageSeries(ImageSeries imageSeries) {
        this.imageSeries = imageSeries;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        this.Control();

        Element imageStudy = doc.createElement("ImageStudy");
        imageStudy.setAttribute("cagridId", Integer.toString(getCagridId()));
        imageStudy.setAttribute("instanceUID", this.instanceUID);
        imageStudy.setAttribute("startDate", this.startDate);
        imageStudy.setAttribute("startTime", this.startTime);

        if (this.imageSeries != null) {
            Element _imageSeries = doc.createElement("imageSeries");
            _imageSeries.appendChild(this.imageSeries.getXMLNode(doc));
            imageStudy.appendChild(_imageSeries);
        }

        return imageStudy;
    }

    @Override
    public void setXMLNode(Node node) {

        NodeList listChils = node.getChildNodes();
        for (int i = 0; i < listChils.getLength(); i++) {
            if ("imageSeries".equals(listChils.item(i).getNodeName())) {
                NodeList tempList = listChils.item(i).getChildNodes();
                for (int j = 0; j < tempList.getLength(); j++) {
                    if ("ImageSeries".equals(tempList.item(j).getNodeName())) {
                        ImageSeries obj = new ImageSeries();
                        obj.setXMLNode(tempList.item(j));
                        this.setImageSeries(obj);
                    }
                }
            }
        }

        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.instanceUID = map.getNamedItem("instanceUID").getNodeValue();
        this.startDate = map.getNamedItem("startDate").getNodeValue();
        this.startTime = map.getNamedItem("startTime").getNodeValue();
    }

    
    private void Control() throws AimException {
        if (getCagridId() == null) {
            throw new AimException("AimException: ImageStudy's cagridId can not be null");
        }
        if (getInstanceUID() == null) {
            throw new AimException("AimException: ImageStudy's instanceUID can not be null");
        }
        if (getStartDate() == null) {
            throw new AimException("AimException: ImageStudy's startDate can not be null");
        }
        if (getStartTime() == null) {
            throw new AimException("AimException: ImageStudy's startTime can not be null");
        }
    }

    public boolean isEqualTo(Object other) {
        ImageStudy oth = (ImageStudy) other;
        if (this.cagridId != oth.cagridId) {
            return false;
        }
        if (this.instanceUID == null ? oth.instanceUID != null : !this.instanceUID.equals(oth.instanceUID)) {
            return false;
        }
        if (this.startDate == null ? oth.startDate != null : !this.startDate.equals(oth.startDate)) {
            return false;
        }
        if (this.startTime == null ? oth.startTime != null : !this.startTime.equals(oth.startTime)) {
            return false;
        }
        return this.imageSeries.isEqualTo(oth.imageSeries);
    }

    public edu.stanford.hakan.aim4api.base.ImageStudy toAimV4() {
        edu.stanford.hakan.aim4api.base.ImageStudy res = new edu.stanford.hakan.aim4api.base.ImageStudy();
        res.setImageSeries(this.getImageSeries().toAimV4());
        res.setInstanceUid(Converter.toII(this.getInstanceUID()));
        res.setStartDate(this.getStartDate());
        res.setStartTime(this.getStartTime());
        return res;
    }

    public ImageStudy(edu.stanford.hakan.aim4api.base.ImageStudy v4) {
        this.setCagridId(0);
        if (v4.getImageSeries() != null) {
            this.setImageSeries(new ImageSeries(v4.getImageSeries()));
        }
        if (v4.getInstanceUid() != null) {
            this.setInstanceUID(v4.getInstanceUid().getRoot());
        }
        this.setStartDate(v4.getStartDate());
        this.setStartTime(v4.getStartTime());
    }

}
