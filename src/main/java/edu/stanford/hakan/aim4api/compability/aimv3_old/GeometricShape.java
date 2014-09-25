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
public class GeometricShape {

    private Integer cagridId;
    private String lineColor;
    private String lineOpacity;
    private String lineStyle;
    private String lineThickness;
    private Boolean includeFlag;
    private Integer shapeIdentifier;
    private SpatialCoordinateCollection spatialCoordinateCollection = new SpatialCoordinateCollection();
    private String xsiType;

    protected void setXsiType(String xsiType) {
        this.xsiType = xsiType;
    }

    public String getXsiType() {
        return this.xsiType;
    }

    public GeometricShape() {
    }

    public GeometricShape(Integer cagridId, String lineColor, String lineOpacity, String lineStyle, String lineThickness, Boolean includeFlag, Integer shapeIdentifier) {
        this.cagridId = cagridId;
        this.lineColor = lineColor;
        this.lineOpacity = lineOpacity;
        this.lineStyle = lineStyle;
        this.lineThickness = lineThickness;
        this.includeFlag = includeFlag;
        this.shapeIdentifier = shapeIdentifier;
    }

    public Integer getCagridId() {
        return this.cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    public Boolean getIncludeFlag() {
        return this.includeFlag;
    }

    public void setIncludeFlag(Boolean includeFlag) {
        this.includeFlag = includeFlag;
    }

    public String getLineColor() {
        return this.lineColor;
    }

    public void setLineColor(String lineColor) {
        this.lineColor = lineColor;
    }

    public String getLineOpacity() {
        return this.lineOpacity;
    }

    public void setLineOpacity(String lineOpacity) {
        this.lineOpacity = lineOpacity;
    }

    public String getLineStyle() {
        return this.lineStyle;
    }

    public void setLineStyle(String lineStyle) {
        this.lineStyle = lineStyle;
    }

    public String getLineThickness() {
        return this.lineThickness;
    }

    public void setLineThickness(String lineThickness) {
        this.lineThickness = lineThickness;
    }

    public Integer getShapeIdentifier() {
        return this.shapeIdentifier;
    }

    public void setShapeIdentifier(Integer shapeIdentifier) {
        this.shapeIdentifier = shapeIdentifier;
    }

    public void setSpatialCoordinateCollection(SpatialCoordinateCollection spatialCoordinateCollection) {
        this.spatialCoordinateCollection = spatialCoordinateCollection;
    }

    public SpatialCoordinateCollection getSpatialCoordinateCollection() {
        return this.spatialCoordinateCollection;
    }

    public void addSpatialCoordinate(SpatialCoordinate spatialCoordinate) {
        this.spatialCoordinateCollection.AddSpatialCoordinate(spatialCoordinate);
    }

    public List<SpatialCoordinate> getSpatialCoordinateList() {
        return this.spatialCoordinateCollection.getSpatialCoordinateList();
    }

    public void setXMLNode(Node node) {

        NodeList listChils = node.getChildNodes();
        for (int i = 0; i < listChils.getLength(); i++) {
            if ("spatialCoordinateCollection".equals(listChils.item(i).getNodeName())) {
                this.spatialCoordinateCollection.setXMLNode(listChils.item(i));
            }
        }

        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        if (map.getNamedItem("lineColor") != null) {
            this.lineColor = map.getNamedItem("lineColor").getNodeValue();
        }
        if (map.getNamedItem("lineOpacity") != null) {
            this.lineOpacity = map.getNamedItem("lineOpacity").getNodeValue();
        }
        if (map.getNamedItem("lineStyle") != null) {
            this.lineStyle = map.getNamedItem("lineStyle").getNodeValue();
        }
        if (map.getNamedItem("lineThickness") != null) {
            this.lineThickness = map.getNamedItem("lineThickness").getNodeValue();
        }
        this.includeFlag = Boolean.parseBoolean(map.getNamedItem("includeFlag").getNodeValue());
        this.shapeIdentifier = Integer.parseInt(map.getNamedItem("shapeIdentifier").getNodeValue());
        this.xsiType = map.getNamedItem("xsi:type").getNodeValue();

    }

    public edu.stanford.hakan.aim4api.base.MarkupEntity toAimV4() {
        edu.stanford.hakan.aim4api.base.TwoDimensionGeometricShapeEntity res = null;

        if ("Circle".equals(this.getXsiType())) {
            res = new edu.stanford.hakan.aim4api.base.TwoDimensionCircle();
        } else if ("Ellipse".equals(this.getXsiType())) {
            res = new edu.stanford.hakan.aim4api.base.TwoDimensionEllipse();
        } else if ("MultiPoint".equals(this.getXsiType())) {
            res = new edu.stanford.hakan.aim4api.base.TwoDimensionMultiPoint();
        } else if ("Point".equals(this.getXsiType())) {
            res = new edu.stanford.hakan.aim4api.base.TwoDimensionPoint();
        } else if ("Polyline".equals(this.getXsiType())) {
            res = new edu.stanford.hakan.aim4api.base.TwoDimensionPolyline();
        }

        res.setIncludeFlag(this.getIncludeFlag());
        res.setLineColor(Converter.toST(this.getLineColor()));
        res.setLineOpacity(Converter.toST(this.getLineOpacity()));
        res.setLineStyle(Converter.toST(this.getLineStyle()));
        res.setLineThickness(Converter.toST(this.getLineThickness()));
        res.setShapeIdentifier(this.getShapeIdentifier());
        res.setTwoDimensionSpatialCoordinateCollection(this.getSpatialCoordinateCollection().toAimV4(res));
        return res;
    }
}
