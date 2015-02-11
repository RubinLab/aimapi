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
import edu.stanford.hakan.aim4api.utility.Utility;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hakan BULU
 */
public class GeometricShapeCollection implements IAimXMLOperations {

    private List<GeometricShape> listGeometricShape = new ArrayList<GeometricShape>();

    GeometricShapeCollection() {
    }

    public void AddGeometricShape(GeometricShape newGeometricShape) {
        this.listGeometricShape.add(newGeometricShape);
    }

    public List<GeometricShape> getGeometricShapeList() {
        return this.listGeometricShape;
    }

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//        Element geometricShapeCollection = doc.createElement("geometricShapeCollection");
//        for (int i = 0; i < this.listGeometricShape.size(); i++) {
//            geometricShapeCollection.appendChild(this.listGeometricShape.get(i).getXMLNode(doc));
//        }
//        return geometricShapeCollection;
//    }
    @Override
    public void setXMLNode(Node node) {

        this.listGeometricShape.clear();

        NodeList tempList = node.getChildNodes();
        for (int j = 0; j < tempList.getLength(); j++) {
            if ("GeometricShape".equals(tempList.item(j).getNodeName())) {
                GeometricShape obj = new GeometricShape();
                obj.setXMLNode(tempList.item(j));
                this.AddGeometricShape(obj);
            }
        }
    }

    public boolean isEqualTo(Object other) {
        GeometricShapeCollection oth = (GeometricShapeCollection) other;
        if (this.listGeometricShape.size() != oth.listGeometricShape.size()) {
            return false;
        }
        for (int i = 0; i < this.listGeometricShape.size(); i++) {
            if (!this.listGeometricShape.get(i).isEqualTo(oth.listGeometricShape.get(i))) {
                return false;
            }
        }
        return true;
    }

    public edu.stanford.hakan.aim4api.base.MarkupEntityCollection toAimV4() {
        edu.stanford.hakan.aim4api.base.MarkupEntityCollection res = new edu.stanford.hakan.aim4api.base.MarkupEntityCollection();
        List<GeometricShape> list = this.getGeometricShapeList();
        for (GeometricShape itemV3 : list) {
            res.addMarkupEntity(itemV3.toAimV4());
        }
        return res;
    }

    public GeometricShapeCollection(edu.stanford.hakan.aim4api.base.MarkupEntityCollection v4) {
        List<edu.stanford.hakan.aim4api.base.MarkupEntity> listV4 = v4.getMarkupEntityList();
        for (edu.stanford.hakan.aim4api.base.MarkupEntity itemV4 : listV4) {
            if ("TwoDimensionCircle".equals(itemV4.getXsiType())) {
                this.AddGeometricShape(new Circle((edu.stanford.hakan.aim4api.base.TwoDimensionCircle) itemV4));
            } else if ("TwoDimensionEllipse".equals(itemV4.getXsiType())) {
                this.AddGeometricShape(new Ellipse((edu.stanford.hakan.aim4api.base.TwoDimensionEllipse) itemV4));
            } else if ("TwoDimensionMultiPoint".equals(itemV4.getXsiType())) {
                this.AddGeometricShape(new MultiPoint((edu.stanford.hakan.aim4api.base.TwoDimensionMultiPoint) itemV4));
            } else if ("TwoDimensionPoint".equals(itemV4.getXsiType())) {
                this.AddGeometricShape(new Point((edu.stanford.hakan.aim4api.base.TwoDimensionPoint) itemV4));
            } else if ("TwoDimensionPolyline".equals(itemV4.getXsiType())) {
                if (Utility.isSpline(itemV4)) {
                    this.AddGeometricShape(new Spline((edu.stanford.hakan.aim4api.base.TwoDimensionSpline) itemV4));
                } else {
                    this.AddGeometricShape(new Polyline((edu.stanford.hakan.aim4api.base.TwoDimensionPolyline) itemV4));
                }
            } else if ("TwoDimensionSpline".equals(itemV4.getXsiType())) {
                this.AddGeometricShape(new Spline((edu.stanford.hakan.aim4api.base.TwoDimensionSpline) itemV4));
            }
        }
    }

    public GeometricShapeCollection getClone() {
        GeometricShapeCollection res = new GeometricShapeCollection();
        for (int i = 0; i < this.listGeometricShape.size(); i++) {
            if (this.listGeometricShape.get(i) != null) {
                res.AddGeometricShape(this.listGeometricShape.get(i).getClone());
            }
        }
        return res;
    }
}
