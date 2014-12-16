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
import edu.stanford.hakan.aim4api.base.TwoDimensionGeometricShapeEntity;
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
public class SpatialCoordinateCollection implements IAimXMLOperations {

    private List<SpatialCoordinate> listSpatialCoordinate = new ArrayList<SpatialCoordinate>();

    public SpatialCoordinateCollection() {
    }

    public void AddSpatialCoordinate(SpatialCoordinate newSpatialCoordinate) {
        this.listSpatialCoordinate.add(newSpatialCoordinate);
    }

    public List<SpatialCoordinate> getSpatialCoordinateList() {
        return this.listSpatialCoordinate;
    }

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//
//        Element spatialCoordinateCollection = doc.createElement("spatialCoordinateCollection");
//        for (int i = 0; i < listSpatialCoordinate.size(); i++) {
//            spatialCoordinateCollection.appendChild(this.listSpatialCoordinate.get(i).getXMLNode(doc));
//        }
//        return spatialCoordinateCollection;
//    }

    @Override
    public void setXMLNode(Node node) {

        this.listSpatialCoordinate.clear();

        NodeList tempList = node.getChildNodes();
        for (int j = 0; j < tempList.getLength(); j++) {
            if ("SpatialCoordinate".equals(tempList.item(j).getNodeName())) {
                Node temp = tempList.item(j);
                String type = temp.getAttributes().getNamedItem("xsi:type").getNodeValue();
                if ("TwoDimensionSpatialCoordinate".equals(type)) {
                    TwoDimensionSpatialCoordinate obj = new TwoDimensionSpatialCoordinate();
                    obj.setXMLNode(tempList.item(j));
                    this.AddSpatialCoordinate(obj);
                } else if ("ThreeDimensionSpatialCoordinate".equals(type)) {
                    ThreeDimensionSpatialCoordinate obj = new ThreeDimensionSpatialCoordinate();
                    obj.setXMLNode(tempList.item(j));
                    this.AddSpatialCoordinate(obj);
                }
            }
        }
    }

    public boolean isEqualTo(Object other) {
        SpatialCoordinateCollection oth = (SpatialCoordinateCollection) other;
        if (this.listSpatialCoordinate.size() != oth.listSpatialCoordinate.size()) {
            return false;
        }
        for (int i = 0; i < this.listSpatialCoordinate.size(); i++) {
            if (!this.listSpatialCoordinate.get(i).isEqualTo(oth.listSpatialCoordinate.get(i))) {
                return false;
            }
        }
        return true;
    }

    public edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinateCollection toAimV4(TwoDimensionGeometricShapeEntity twoDimensionGeometricShapeEntity) {
        edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinateCollection res = new edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinateCollection();
        List<SpatialCoordinate> list = this.getSpatialCoordinateList();
        for (SpatialCoordinate itemV3 : list) {
            TwoDimensionSpatialCoordinate itemV3Plus = (TwoDimensionSpatialCoordinate) itemV3;
            res.addTwoDimensionSpatialCoordinate(itemV3Plus.toAimV4(twoDimensionGeometricShapeEntity));
        }
        return res;
    }

    public SpatialCoordinateCollection(edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinateCollection v4, edu.stanford.hakan.aim4api.base.TwoDimensionGeometricShapeEntity geoShapeV4) {
        List<edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinate> listV4 = v4.getTwoDimensionSpatialCoordinateList();
        for (edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinate itemV4 : listV4) {
            itemV4.setTwoDimensionGeometricShapeEntity(geoShapeV4);
            this.AddSpatialCoordinate(new TwoDimensionSpatialCoordinate(itemV4));
        }
    }
    
    
}
