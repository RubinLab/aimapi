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

import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class ThreeDimensionGeometricShapeEntity extends GeometricShapeEntity {

    public ThreeDimensionGeometricShapeEntity() {
        setXsiType("ThreeDimensionGeometricShapeEntity");
    }
    private II frameOfReferenceUid;
    private II fiducialUid;
    private ThreeDimensionSpatialCoordinateCollection threeDimensionSpatialCoordinateCollection = new ThreeDimensionSpatialCoordinateCollection();

    public II getFrameOfReferenceUid() {
        return frameOfReferenceUid;
    }

    public void setFrameOfReferenceUid(II frameOfReferenceUid) {
        frameOfReferenceUid.setTagName("frameOfReferenceUid");
        this.frameOfReferenceUid = frameOfReferenceUid;
    }

    public II getFiducialUid() {
        return fiducialUid;
    }

    public void setFiducialUid(II fiducialUid) {
        fiducialUid.setTagName("fiducialUid");
        this.fiducialUid = fiducialUid;
    }

    public ThreeDimensionSpatialCoordinateCollection getThreeDimensionSpatialCoordinateCollection() {
        return threeDimensionSpatialCoordinateCollection;
    }

    public void setThreeDimensionSpatialCoordinateCollection(ThreeDimensionSpatialCoordinateCollection threeDimensionSpatialCoordinateCollection) {
        this.threeDimensionSpatialCoordinateCollection = threeDimensionSpatialCoordinateCollection;
    }

    public void addThreeDimensionSpatialCoordinate(ThreeDimensionSpatialCoordinate newThreeDimensionSpatialCoordinate) {
        this.threeDimensionSpatialCoordinateCollection.addThreeDimensionSpatialCoordinate(newThreeDimensionSpatialCoordinate);
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("ThreeDimensionGeometricShapeEntity");
        }
        Element res = (Element) super.getXMLNode(doc);
        if (this.frameOfReferenceUid != null) {
            res.appendChild(this.frameOfReferenceUid.getXMLNode(doc));
        }
        if (this.fiducialUid != null) {
            res.appendChild(this.fiducialUid.getXMLNode(doc));
        }
        if (this.threeDimensionSpatialCoordinateCollection.size() > 0) {
            res.appendChild(this.threeDimensionSpatialCoordinateCollection.getXMLNode(doc));
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("frameOfReferenceUid".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setFrameOfReferenceUid(obj);
            }
            if ("fiducialUid".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setFiducialUid(obj);
            }
            if ("threeDimensionSpatialCoordinateCollection".equals(listChilds.item(i).getNodeName())) {
                this.threeDimensionSpatialCoordinateCollection.setXMLNode(listChilds.item(i));
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        ThreeDimensionGeometricShapeEntity oth = (ThreeDimensionGeometricShapeEntity) other;
        if (!this.frameOfReferenceUid.isEqualTo(oth.frameOfReferenceUid)) {
            return false;
        }
        if (!this.fiducialUid.isEqualTo(oth.fiducialUid)) {
            return false;
        }
        if (!this.threeDimensionSpatialCoordinateCollection.isEqualTo(oth.threeDimensionSpatialCoordinateCollection)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    @Override
    public ThreeDimensionGeometricShapeEntity getClone() {
        ThreeDimensionGeometricShapeEntity res = new ThreeDimensionGeometricShapeEntity();
        if (this.getFrameOfReferenceUid() != null) {
            res.setFrameOfReferenceUid(this.getFrameOfReferenceUid().getClone());
        }
        if (this.getFiducialUid() != null) {
            res.setFiducialUid(this.getFiducialUid().getClone());
        }
        if (this.getThreeDimensionSpatialCoordinateCollection() != null) {
            res.setThreeDimensionSpatialCoordinateCollection(this.getThreeDimensionSpatialCoordinateCollection().getClone());
        }
        List<CD> listQuestionTypeCode = this.getListQuestionTypeCode();
        for (int i = 0; i < listQuestionTypeCode.size(); i++) {
            if (listQuestionTypeCode.get(i) != null) {
                res.addQuestionTypeCode(listQuestionTypeCode.get(i).getClone());
            }
        }
        if (this.getShapeIdentifier() != null) {
            res.setShapeIdentifier(this.getShapeIdentifier());
        }
        if (this.getLabel() != null) {
            res.setLabel(this.getLabel().getClone());
        }
        if (this.getDescription() != null) {
            res.setDescription(this.getDescription().getClone());
        }
        if (this.getIncludeFlag() != null) {
            res.setIncludeFlag(this.getIncludeFlag());
        }
        if (this.getComment() != null) {
            res.setComment(this.getComment().getClone());
        }
        if (this.getLineColor() != null) {
            res.setLineColor(this.getLineColor().getClone());
        }
        if (this.getLineOpacity() != null) {
            res.setLineOpacity(this.getLineOpacity().getClone());
        }
        if (this.getLineStyle() != null) {
            res.setLineStyle(this.getLineStyle().getClone());
        }
        if (this.getLineThickness() != null) {
            res.setLineThickness(this.getLineThickness().getClone());
        }
        if (this.getQuestionIndex() != null) {
            res.setQuestionIndex(this.getQuestionIndex());
        }
        if (this.getInterpolationMethod() != null) {
            res.setInterpolationMethod(this.getInterpolationMethod().getClone());
        }
        if (this.getUniqueIdentifier() != null) {
            res.setUniqueIdentifier(this.getUniqueIdentifier().getClone());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        if (this.getXsiType() != null) {
            res.setXsiType(this.getXsiType());
        }
        return res;
    }
}
