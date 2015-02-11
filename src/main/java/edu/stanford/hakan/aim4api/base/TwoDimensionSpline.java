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

import edu.stanford.hakan.aim4api.utility.Globals;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author localadmin
 */
public class TwoDimensionSpline extends TwoDimensionGeometricShapeEntity {

    public TwoDimensionSpline() {
        setXsiType("TwoDimensionSpline");
    }

    public TwoDimensionSpline(TwoDimensionPolyline twoDimensionPolyline) {
        if (twoDimensionPolyline.getImageReferenceUid() != null) {
            this.setImageReferenceUid(twoDimensionPolyline.getImageReferenceUid().getClone());
        }
        if (twoDimensionPolyline.getReferencedFrameNumber() != null) {
            this.setReferencedFrameNumber(twoDimensionPolyline.getReferencedFrameNumber());
        }
        if (twoDimensionPolyline.getUri() != null) {
            this.setUri(twoDimensionPolyline.getUri().getClone());
        }
        if (twoDimensionPolyline.getTwoDimensionSpatialCoordinateCollection() != null) {
            this.setTwoDimensionSpatialCoordinateCollection(twoDimensionPolyline.getTwoDimensionSpatialCoordinateCollection().getClone());
        }
        List<CD> listQuestionTypeCode = twoDimensionPolyline.getListQuestionTypeCode();
        for (int i = 0; i < listQuestionTypeCode.size(); i++) {
            if (listQuestionTypeCode.get(i) != null) {
                this.addQuestionTypeCode(listQuestionTypeCode.get(i).getClone());
            }
        }
        if (twoDimensionPolyline.getShapeIdentifier() != null) {
            this.setShapeIdentifier(twoDimensionPolyline.getShapeIdentifier());
        }
        if (twoDimensionPolyline.getLabel() != null) {
            this.setLabel(twoDimensionPolyline.getLabel().getClone());
        }
        if (twoDimensionPolyline.getDescription() != null) {
            this.setDescription(twoDimensionPolyline.getDescription().getClone());
        }
        if (twoDimensionPolyline.getIncludeFlag() != null) {
            this.setIncludeFlag(twoDimensionPolyline.getIncludeFlag());
        }
        if (twoDimensionPolyline.getComment() != null) {
            this.setComment(twoDimensionPolyline.getComment().getClone());
        }
        if (twoDimensionPolyline.getLineColor() != null) {
            this.setLineColor(twoDimensionPolyline.getLineColor().getClone());
        }
        if (twoDimensionPolyline.getLineOpacity() != null) {
            this.setLineOpacity(twoDimensionPolyline.getLineOpacity().getClone());
        }
        if (twoDimensionPolyline.getLineStyle() != null) {
            this.setLineStyle(twoDimensionPolyline.getLineStyle().getClone());
        }
        if (twoDimensionPolyline.getLineThickness() != null) {
            this.setLineThickness(twoDimensionPolyline.getLineThickness().getClone());
        }
        if (twoDimensionPolyline.getQuestionIndex() != null) {
            this.setQuestionIndex(twoDimensionPolyline.getQuestionIndex());
        }
        if (twoDimensionPolyline.getInterpolationMethod() != null) {
            this.setInterpolationMethod(twoDimensionPolyline.getInterpolationMethod().getClone());
        }
        if (twoDimensionPolyline.getUniqueIdentifier() != null) {
            this.setUniqueIdentifier(twoDimensionPolyline.getUniqueIdentifier().getClone());
        }
        if (twoDimensionPolyline.getTagName() != null) {
            this.setTagName(twoDimensionPolyline.getTagName());
        }
        this.setXsiType("TwoDimensionSpline");
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (super.getUniqueIdentifier() == null || super.getUniqueIdentifier().getRoot() == null) {
            super.setUniqueIdentifier();
        }
        String uid = super.getUniqueIdentifier().getRoot();
        if (!uid.contains(Globals.getSplineFlag())) {
            super.setUniqueIdentifier(new II(uid + Globals.getSplineFlag()));
        }
        
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("TwoDimensionPolyline");
        }
        setXsiType("TwoDimensionPolyline");
        Element res = (Element) super.getXMLNode(doc);
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        return super.isEqualTo(other);
    }

    @Override
    public TwoDimensionSpline getClone() {
        TwoDimensionSpline res = new TwoDimensionSpline();
        if (this.getImageReferenceUid() != null) {
            res.setImageReferenceUid(this.getImageReferenceUid().getClone());
        }
        if (this.getReferencedFrameNumber() != null) {
            res.setReferencedFrameNumber(this.getReferencedFrameNumber());
        }
        if (this.getUri() != null) {
            res.setUri(this.getUri().getClone());
        }
        if (this.getTwoDimensionSpatialCoordinateCollection() != null) {
            res.setTwoDimensionSpatialCoordinateCollection(this.getTwoDimensionSpatialCoordinateCollection().getClone());
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
