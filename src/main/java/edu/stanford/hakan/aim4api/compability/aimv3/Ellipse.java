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
import org.w3c.dom.Node;

/**
 *
 * @author Hakan BULU
 */
public class Ellipse extends GeometricShape implements IAimXMLOperations {

    public Ellipse() {
        super();
        setXsiType("Ellipse");
    }

    public Ellipse(Integer cagridId, String lineColor, String lineOpacity, String lineStyle, String lineThickness, Boolean includeFlag, Integer shapeIdentifier) {
        super(cagridId, lineColor, lineOpacity, lineStyle, lineThickness, includeFlag, shapeIdentifier);
        setXsiType("Ellipse");
    }

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//        return super.getXMLNode(doc);
//    }
    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
    }

    @Override
    public boolean isEqualTo(Object other) {
        return super.isEqualTo(other);
    }

//    @Override
//    public edu.stanford.hakan.aim4api.base.TwoDimensionGeometricShapeEntity toAimV4() {
//        edu.stanford.hakan.aim4api.base.TwoDimensionEllipse res = new edu.stanford.hakan.aim4api.base.TwoDimensionEllipse();
//        res.setIncludeFlag(this.getIncludeFlag());
//        res.setLineColor(Converter.toST(this.getLineColor()));
//        res.setLineOpacity(Converter.toST(this.getLineOpacity()));
//        res.setLineStyle(Converter.toST(this.getLineStyle()));
//        res.setLineThickness(Converter.toST(this.getLineThickness()));
//        res.setShapeIdentifier(this.getShapeIdentifier());
//        res.setTwoDimensionSpatialCoordinateCollection(this.getSpatialCoordinateCollection().toAimV4(res));
//        return res;
//    }
    public Ellipse(edu.stanford.hakan.aim4api.base.TwoDimensionEllipse v4) {
        setXsiType("Ellipse");
        this.setCagridId(0);
        this.setIncludeFlag(v4.getIncludeFlag());
        if (v4.getLineColor() != null) {
            this.setLineColor(v4.getLineColor().getValue());
        }
        if (v4.getLineOpacity() != null) {
            this.setLineOpacity(v4.getLineOpacity().getValue());
        }
        if (v4.getLineStyle() != null) {
            this.setLineStyle(v4.getLineStyle().getValue());
        }
        if (v4.getLineThickness() != null) {
            this.setLineThickness(v4.getLineThickness().getValue());
        }
        this.setShapeIdentifier(v4.getShapeIdentifier());
        this.setSpatialCoordinateCollection(new SpatialCoordinateCollection(v4.getTwoDimensionSpatialCoordinateCollection(), v4));
    }

    @Override
    public Ellipse getClone() {
        Ellipse res = new Ellipse();
        if (this.getCagridId() != null) {
            res.setCagridId(this.getCagridId());
        }
        if (this.getLineColor() != null) {
            res.setLineColor(this.getLineColor());
        }
        if (this.getLineOpacity() != null) {
            res.setLineOpacity(this.getLineOpacity());
        }
        if (this.getLineStyle() != null) {
            res.setLineStyle(this.getLineStyle());
        }
        if (this.getLineThickness() != null) {
            res.setLineThickness(this.getLineThickness());
        }
        if (this.getIncludeFlag() != null) {
            res.setIncludeFlag(this.getIncludeFlag());
        }
        if (this.getShapeIdentifier() != null) {
            res.setShapeIdentifier(this.getShapeIdentifier());
        }
        if (this.getSpatialCoordinateCollection() != null) {
            res.setSpatialCoordinateCollection(this.getSpatialCoordinateCollection().getClone());
        }
        if (this.getXsiType() != null) {
            res.setXsiType(this.getXsiType());
        }
        return res;
    }

    private void setBaseProperties(edu.stanford.hakan.aim4api.base.GeometricShapeEntity geometricShapeEntity) {
        geometricShapeEntity.setIncludeFlag(this.getIncludeFlag());
        geometricShapeEntity.setLineColor(Converter.toST(this.getLineColor()));
        geometricShapeEntity.setLineOpacity(Converter.toST(this.getLineOpacity()));
        geometricShapeEntity.setLineStyle(Converter.toST(this.getLineStyle()));
        geometricShapeEntity.setLineThickness(Converter.toST(this.getLineThickness()));
        geometricShapeEntity.setShapeIdentifier(this.getShapeIdentifier());
    }

    @Override
    public edu.stanford.hakan.aim4api.base.GeometricShapeEntity toAimV4() {
        if (this.getShapeDimension() == ShapeDimension.TwoD) {
            edu.stanford.hakan.aim4api.base.TwoDimensionEllipse res = new edu.stanford.hakan.aim4api.base.TwoDimensionEllipse();
            setBaseProperties(res);
            res.setTwoDimensionSpatialCoordinateCollection(this.getSpatialCoordinateCollection().toAimV4_2D(res));
            return res;
        } else if (this.getShapeDimension() == ShapeDimension.ThreeD) {
            edu.stanford.hakan.aim4api.base.ThreeDimensionEllipse res = new edu.stanford.hakan.aim4api.base.ThreeDimensionEllipse();
            setBaseProperties(res);
            res.setThreeDimensionSpatialCoordinateCollection(this.getSpatialCoordinateCollection().toAimV4_3D(res));
            return res;
        }
        return null;
    }
}
