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
import edu.stanford.hakan.aim4api.base.CD;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hakan BULU
 */
public class ImageSeries implements IAimXMLOperations {

    private ImageCollection imageCollection = new ImageCollection();
    private Integer cagridId;
    private String instanceUID;

    public ImageSeries() {
    }

    public ImageSeries(Integer cagridId, String instanceUID) {
        this.cagridId = cagridId;
        this.instanceUID = instanceUID;
    }

    public Integer getCagridId() {
        return cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    public ImageCollection getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(ImageCollection imageCollection) {
        this.imageCollection = imageCollection;
    }

    public String getInstanceUID() {
        return instanceUID;
    }

    public void setInstanceUID(String instanceUID) {
        this.instanceUID = instanceUID;
    }

    public void addImage(Image image) {
        this.imageCollection.AddImage(image);
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        this.Control();

        Element imageSeries = doc.createElement("ImageSeries");
        imageSeries.setAttribute("cagridId", Integer.toString(getCagridId()));
        imageSeries.setAttribute("instanceUID", this.instanceUID);

        if (this.imageCollection.getImageList().size() > 0) {
            imageSeries.appendChild(this.imageCollection.getXMLNode(doc));
        }

        return imageSeries;
    }

    @Override
    public void setXMLNode(Node node) {

        NodeList listChils = node.getChildNodes();
        for (int i = 0; i < listChils.getLength(); i++) {
            if ("imageCollection".equals(listChils.item(i).getNodeName())) {
                this.imageCollection.setXMLNode(listChils.item(i));
            }
        }

        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.instanceUID = map.getNamedItem("instanceUID").getNodeValue();
    }

   
    private void Control() throws AimException {
        if (getCagridId() == null) {
            throw new AimException("AimException: ImageSeries's cagridId can not be null");
        }
        if (getInstanceUID() == null) {
            throw new AimException("AimException: ImageSeries's instanceUID can not be null");
        }
    }

    public boolean isEqualTo(Object other) {
        ImageSeries oth = (ImageSeries) other;
        if (this.cagridId != oth.cagridId) {
            return false;
        }
        if (this.instanceUID == null ? oth.instanceUID != null : !this.instanceUID.equals(oth.instanceUID)) {
            return false;
        }
        return this.imageCollection.isEqualTo(oth.imageCollection);
    }

    public edu.stanford.hakan.aim4api.base.ImageSeries toAimV4() {
        edu.stanford.hakan.aim4api.base.ImageSeries res = new edu.stanford.hakan.aim4api.base.ImageSeries();
        res.setImageCollection(this.getImageCollection().toAimV4());
        res.setInstanceUid(Converter.toII(this.getInstanceUID()));
        res.setModality(new CD("", "", "", ""));
        return res;
    }

    public ImageSeries(edu.stanford.hakan.aim4api.base.ImageSeries v4) {
        this.setCagridId(0);
        if (v4.getImageCollection().getImageList().size() > 0) {
            this.setImageCollection(new ImageCollection(v4.getImageCollection()));
        }
        if (v4.getInstanceUid() != null) {
            this.setInstanceUID(v4.getInstanceUid().getRoot());
        }
    }
}
