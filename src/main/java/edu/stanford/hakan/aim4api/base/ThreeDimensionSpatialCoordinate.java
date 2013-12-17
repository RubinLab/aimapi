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
package edu.stanford.hakan.aim4api.base;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class ThreeDimensionSpatialCoordinate implements IAimXMLOperations {

    private Integer coordinateIndex;
    private Double x;
    private Double y;
    private Double z;
    private String tagName;

    public Integer getCoordinateIndex() {
        return coordinateIndex;
    }

    public void setCoordinateIndex(Integer coordinateIndex) {
        this.coordinateIndex = coordinateIndex;
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

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
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
            setTagName("ThreeDimensionSpatialCoordinate");
        }
        Element res = doc.createElement(getTagName());
        if (this.coordinateIndex != null) {
            Element el_coordinateIndex = doc.createElement("coordinateIndex");
            el_coordinateIndex.setAttribute("value", this.coordinateIndex.toString());
            res.appendChild(el_coordinateIndex);
        }
        if (this.x != null) {
            Element el_x = doc.createElement("x");
            el_x.setAttribute("value", this.x.toString());
            res.appendChild(el_x);
        }
        if (this.y != null) {
            Element el_y = doc.createElement("y");
            el_y.setAttribute("value", this.y.toString());
            res.appendChild(el_y);
        }
        if (this.z != null) {
            Element el_z = doc.createElement("z");
            el_z.setAttribute("value", this.z.toString());
            res.appendChild(el_z);
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("coordinateIndex".equals(currentNode.getNodeName())) {
                this.coordinateIndex = Integer.parseInt(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("x".equals(currentNode.getNodeName())) {
                this.x = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("y".equals(currentNode.getNodeName())) {
                this.y = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("z".equals(currentNode.getNodeName())) {
                this.z = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        ThreeDimensionSpatialCoordinate oth = (ThreeDimensionSpatialCoordinate) other;
        if (this.coordinateIndex == null ? oth.coordinateIndex != null : !this.coordinateIndex.equals(oth.coordinateIndex)) {
            return false;
        }
        if (this.x == null ? oth.x != null : !this.x.equals(oth.x)) {
            return false;
        }
        if (this.y == null ? oth.y != null : !this.y.equals(oth.y)) {
            return false;
        }
        if (this.z == null ? oth.z != null : !this.z.equals(oth.z)) {
            return false;
        }
        return true;
    }

    public ThreeDimensionSpatialCoordinate getClone() {
        ThreeDimensionSpatialCoordinate res = new ThreeDimensionSpatialCoordinate();
        if (this.getCoordinateIndex() != null) {
            res.setCoordinateIndex(this.getCoordinateIndex());
        }
        if (this.getX() != null) {
            res.setX(this.getX());
        }
        if (this.getY() != null) {
            res.setY(this.getY());
        }
        if (this.getZ() != null) {
            res.setZ(this.getZ());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        return res;
    }
}
