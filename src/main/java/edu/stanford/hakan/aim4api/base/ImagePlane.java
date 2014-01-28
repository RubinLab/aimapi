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
public class ImagePlane implements IAimXMLOperations {

    private Double rowImageOrientationX;
    private Double rowImageOrientationY;
    private Double rowImageOrientationZ;
    private Double columnImageOrientationX;
    private Double columnImageOrientationY;
    private Double columnImageOrientationZ;
    private Double verticalPixelSpacing;
    private Double horizontalPixelSpacing;
    private Double sliceThickness;
    private Double imagePositionX;
    private Double imagePositionY;
    private Double imagePositionZ;
    private String tagName;

    public Double getRowImageOrientationX() {
        return rowImageOrientationX;
    }

    public void setRowImageOrientationX(Double rowImageOrientationX) {
        this.rowImageOrientationX = rowImageOrientationX;
    }

    public Double getRowImageOrientationY() {
        return rowImageOrientationY;
    }

    public void setRowImageOrientationY(Double rowImageOrientationY) {
        this.rowImageOrientationY = rowImageOrientationY;
    }

    public Double getRowImageOrientationZ() {
        return rowImageOrientationZ;
    }

    public void setRowImageOrientationZ(Double rowImageOrientationZ) {
        this.rowImageOrientationZ = rowImageOrientationZ;
    }

    public Double getColumnImageOrientationX() {
        return columnImageOrientationX;
    }

    public void setColumnImageOrientationX(Double columnImageOrientationX) {
        this.columnImageOrientationX = columnImageOrientationX;
    }

    public Double getColumnImageOrientationY() {
        return columnImageOrientationY;
    }

    public void setColumnImageOrientationY(Double columnImageOrientationY) {
        this.columnImageOrientationY = columnImageOrientationY;
    }

    public Double getColumnImageOrientationZ() {
        return columnImageOrientationZ;
    }

    public void setColumnImageOrientationZ(Double columnImageOrientationZ) {
        this.columnImageOrientationZ = columnImageOrientationZ;
    }

    public Double getVerticalPixelSpacing() {
        return verticalPixelSpacing;
    }

    public void setVerticalPixelSpacing(Double verticalPixelSpacing) {
        this.verticalPixelSpacing = verticalPixelSpacing;
    }

    public Double getHorizontalPixelSpacing() {
        return horizontalPixelSpacing;
    }

    public void setHorizontalPixelSpacing(Double horizontalPixelSpacing) {
        this.horizontalPixelSpacing = horizontalPixelSpacing;
    }

    public Double getSliceThickness() {
        return sliceThickness;
    }

    public void setSliceThickness(Double sliceThickness) {
        this.sliceThickness = sliceThickness;
    }

    public Double getImagePositionX() {
        return imagePositionX;
    }

    public void setImagePositionX(Double imagePositionX) {
        this.imagePositionX = imagePositionX;
    }

    public Double getImagePositionY() {
        return imagePositionY;
    }

    public void setImagePositionY(Double imagePositionY) {
        this.imagePositionY = imagePositionY;
    }

    public Double getImagePositionZ() {
        return imagePositionZ;
    }

    public void setImagePositionZ(Double imagePositionZ) {
        this.imagePositionZ = imagePositionZ;
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
            setTagName("ImagePlane");
        }
        Element res = doc.createElement(getTagName());
        if (this.rowImageOrientationX != null) {
            Element el_rowImageOrientationX = doc.createElement("rowImageOrientationX");
            el_rowImageOrientationX.setAttribute("value", this.rowImageOrientationX.toString());
            res.appendChild(el_rowImageOrientationX);
        }
        if (this.rowImageOrientationY != null) {
            Element el_rowImageOrientationY = doc.createElement("rowImageOrientationY");
            el_rowImageOrientationY.setAttribute("value", this.rowImageOrientationY.toString());
            res.appendChild(el_rowImageOrientationY);
        }
        if (this.rowImageOrientationZ != null) {
            Element el_rowImageOrientationZ = doc.createElement("rowImageOrientationZ");
            el_rowImageOrientationZ.setAttribute("value", this.rowImageOrientationZ.toString());
            res.appendChild(el_rowImageOrientationZ);
        }
        if (this.columnImageOrientationX != null) {
            Element el_columnImageOrientationX = doc.createElement("columnImageOrientationX");
            el_columnImageOrientationX.setAttribute("value", this.columnImageOrientationX.toString());
            res.appendChild(el_columnImageOrientationX);
        }
        if (this.columnImageOrientationY != null) {
            Element el_columnImageOrientationY = doc.createElement("columnImageOrientationY");
            el_columnImageOrientationY.setAttribute("value", this.columnImageOrientationY.toString());
            res.appendChild(el_columnImageOrientationY);
        }
        if (this.columnImageOrientationZ != null) {
            Element el_columnImageOrientationZ = doc.createElement("columnImageOrientationZ");
            el_columnImageOrientationZ.setAttribute("value", this.columnImageOrientationZ.toString());
            res.appendChild(el_columnImageOrientationZ);
        }
        if (this.verticalPixelSpacing != null) {
            Element el_verticalPixelSpacing = doc.createElement("verticalPixelSpacing");
            el_verticalPixelSpacing.setAttribute("value", this.verticalPixelSpacing.toString());
            res.appendChild(el_verticalPixelSpacing);
        }
        if (this.horizontalPixelSpacing != null) {
            Element el_horizontalPixelSpacing = doc.createElement("horizontalPixelSpacing");
            el_horizontalPixelSpacing.setAttribute("value", this.horizontalPixelSpacing.toString());
            res.appendChild(el_horizontalPixelSpacing);
        }
        if (this.sliceThickness != null) {
            Element el_sliceThickness = doc.createElement("sliceThickness");
            el_sliceThickness.setAttribute("value", this.sliceThickness.toString());
            res.appendChild(el_sliceThickness);
        }
        if (this.imagePositionX != null) {
            Element el_imagePositionX = doc.createElement("imagePositionX");
            el_imagePositionX.setAttribute("value", this.imagePositionX.toString());
            res.appendChild(el_imagePositionX);
        }
        if (this.imagePositionY != null) {
            Element el_imagePositionY = doc.createElement("imagePositionY");
            el_imagePositionY.setAttribute("value", this.imagePositionY.toString());
            res.appendChild(el_imagePositionY);
        }
        if (this.imagePositionZ != null) {
            Element el_imagePositionZ = doc.createElement("imagePositionZ");
            el_imagePositionZ.setAttribute("value", this.imagePositionZ.toString());
            res.appendChild(el_imagePositionZ);
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("rowImageOrientationX".equals(currentNode.getNodeName())) {
                this.rowImageOrientationX = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("rowImageOrientationY".equals(currentNode.getNodeName())) {
                this.rowImageOrientationY = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("rowImageOrientationZ".equals(currentNode.getNodeName())) {
                this.rowImageOrientationZ = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("columnImageOrientationX".equals(currentNode.getNodeName())) {
                this.columnImageOrientationX = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("columnImageOrientationY".equals(currentNode.getNodeName())) {
                this.columnImageOrientationY = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("columnImageOrientationZ".equals(currentNode.getNodeName())) {
                this.columnImageOrientationZ = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("verticalPixelSpacing".equals(currentNode.getNodeName())) {
                this.verticalPixelSpacing = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("horizontalPixelSpacing".equals(currentNode.getNodeName())) {
                this.horizontalPixelSpacing = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("sliceThickness".equals(currentNode.getNodeName())) {
                this.sliceThickness = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("imagePositionX".equals(currentNode.getNodeName())) {
                this.imagePositionX = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("imagePositionY".equals(currentNode.getNodeName())) {
                this.imagePositionY = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("imagePositionZ".equals(currentNode.getNodeName())) {
                this.imagePositionZ = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        ImagePlane oth = (ImagePlane) other;
        if (this.rowImageOrientationX == null ? oth.rowImageOrientationX != null : !this.rowImageOrientationX.equals(oth.rowImageOrientationX)) {
            return false;
        }
        if (this.rowImageOrientationY == null ? oth.rowImageOrientationY != null : !this.rowImageOrientationY.equals(oth.rowImageOrientationY)) {
            return false;
        }
        if (this.rowImageOrientationZ == null ? oth.rowImageOrientationZ != null : !this.rowImageOrientationZ.equals(oth.rowImageOrientationZ)) {
            return false;
        }
        if (this.columnImageOrientationX == null ? oth.columnImageOrientationX != null : !this.columnImageOrientationX.equals(oth.columnImageOrientationX)) {
            return false;
        }
        if (this.columnImageOrientationY == null ? oth.columnImageOrientationY != null : !this.columnImageOrientationY.equals(oth.columnImageOrientationY)) {
            return false;
        }
        if (this.columnImageOrientationZ == null ? oth.columnImageOrientationZ != null : !this.columnImageOrientationZ.equals(oth.columnImageOrientationZ)) {
            return false;
        }
        if (this.verticalPixelSpacing == null ? oth.verticalPixelSpacing != null : !this.verticalPixelSpacing.equals(oth.verticalPixelSpacing)) {
            return false;
        }
        if (this.horizontalPixelSpacing == null ? oth.horizontalPixelSpacing != null : !this.horizontalPixelSpacing.equals(oth.horizontalPixelSpacing)) {
            return false;
        }
        if (this.sliceThickness == null ? oth.sliceThickness != null : !this.sliceThickness.equals(oth.sliceThickness)) {
            return false;
        }
        if (this.imagePositionX == null ? oth.imagePositionX != null : !this.imagePositionX.equals(oth.imagePositionX)) {
            return false;
        }
        if (this.imagePositionY == null ? oth.imagePositionY != null : !this.imagePositionY.equals(oth.imagePositionY)) {
            return false;
        }
        if (this.imagePositionZ == null ? oth.imagePositionZ != null : !this.imagePositionZ.equals(oth.imagePositionZ)) {
            return false;
        }
        return true;
    }

    public ImagePlane getClone() {
        ImagePlane res = new ImagePlane();
        if (this.getRowImageOrientationX() != null) {
            res.setRowImageOrientationX(this.getRowImageOrientationX());
        }
        if (this.getRowImageOrientationY() != null) {
            res.setRowImageOrientationY(this.getRowImageOrientationY());
        }
        if (this.getRowImageOrientationZ() != null) {
            res.setRowImageOrientationZ(this.getRowImageOrientationZ());
        }
        if (this.getColumnImageOrientationX() != null) {
            res.setColumnImageOrientationX(this.getColumnImageOrientationX());
        }
        if (this.getColumnImageOrientationY() != null) {
            res.setColumnImageOrientationY(this.getColumnImageOrientationY());
        }
        if (this.getColumnImageOrientationZ() != null) {
            res.setColumnImageOrientationZ(this.getColumnImageOrientationZ());
        }
        if (this.getVerticalPixelSpacing() != null) {
            res.setVerticalPixelSpacing(this.getVerticalPixelSpacing());
        }
        if (this.getHorizontalPixelSpacing() != null) {
            res.setHorizontalPixelSpacing(this.getHorizontalPixelSpacing());
        }
        if (this.getSliceThickness() != null) {
            res.setSliceThickness(this.getSliceThickness());
        }
        if (this.getImagePositionX() != null) {
            res.setImagePositionX(this.getImagePositionX());
        }
        if (this.getImagePositionY() != null) {
            res.setImagePositionY(this.getImagePositionY());
        }
        if (this.getImagePositionZ() != null) {
            res.setImagePositionZ(this.getImagePositionZ());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        return res;
    }
}
