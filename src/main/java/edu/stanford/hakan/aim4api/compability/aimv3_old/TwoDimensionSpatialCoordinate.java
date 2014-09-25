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
package edu.stanford.hakan.aim4api.compability.aimv3_old;

import edu.stanford.hakan.aim4api.base.TwoDimensionGeometricShapeEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan BULU
 */
public class TwoDimensionSpatialCoordinate extends SpatialCoordinate {

    private String imageReferenceUID;
    private Integer referencedFrameNumber;
    private Double x;
    private Double y;

    public TwoDimensionSpatialCoordinate() {
        setXsiType("TwoDimensionSpatialCoordinate");
    }

    public TwoDimensionSpatialCoordinate(Integer cagridId, Integer coordinateIndex, String imageReferenceUID, Integer referencedFrameNumber, Double x, Double y) {

        setCagridId(cagridId);
        setCoordinateIndex(coordinateIndex);
        this.imageReferenceUID = imageReferenceUID;
        this.referencedFrameNumber = referencedFrameNumber;
        this.x = x;
        this.y = y;
        setXsiType("TwoDimensionSpatialCoordinate");
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getImageReferenceUID() {
        return imageReferenceUID;
    }

    public void setImageReferenceUID(String imageReferenceUID) {
        this.imageReferenceUID = imageReferenceUID;
    }

    public Integer getReferencedFrameNumber() {
        return referencedFrameNumber;
    }

    public void setReferencedFrameNumber(Integer referencedFrameNumber) {
        this.referencedFrameNumber = referencedFrameNumber;
    }

    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
        NamedNodeMap map = node.getAttributes();
        this.setImageReferenceUID(map.getNamedItem("imageReferenceUID").getNodeValue());
        this.setReferencedFrameNumber(Integer.parseInt(map.getNamedItem("referencedFrameNumber").getNodeValue()));
        this.setX(Double.parseDouble(map.getNamedItem("x").getNodeValue()));
        this.setY(Double.parseDouble(map.getNamedItem("y").getNodeValue()));
    }

    public TwoDimensionSpatialCoordinate(edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinate v4) {
        setXsiType("TwoDimensionSpatialCoordinate");
        this.setCagridId(0);
        this.setCoordinateIndex(v4.getCoordinateIndex());
        this.setX(v4.getX());
        this.setY(v4.getY());
        if (v4.getTwoDimensionGeometricShapeEntity() != null && v4.getTwoDimensionGeometricShapeEntity().getImageReferenceUid() != null) {
            this.setImageReferenceUID(v4.getTwoDimensionGeometricShapeEntity().getImageReferenceUid().getRoot());
        }
        if (v4.getTwoDimensionGeometricShapeEntity() != null) {
            this.setReferencedFrameNumber(v4.getTwoDimensionGeometricShapeEntity().getReferencedFrameNumber());
        }
    }

    public edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinate toAimV4(TwoDimensionGeometricShapeEntity twoDimensionGeometricShapeEntity) {
        edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinate res = new edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinate();
        res.setCoordinateIndex(this.getCoordinateIndex());
        res.setX(this.getX());
        res.setY(this.getY());
        res.setTwoDimensionGeometricShapeEntity(twoDimensionGeometricShapeEntity);
        //res.setTwoDimensionSpatialCoordinateCollection(twoDimensionSpatialCoordinateCollection);
        res.getTwoDimensionGeometricShapeEntity().setReferencedFrameNumber(this.getReferencedFrameNumber());
        res.getTwoDimensionGeometricShapeEntity().setImageReferenceUid(Converter.toII(this.getImageReferenceUID()));
        return res;
    }

}
