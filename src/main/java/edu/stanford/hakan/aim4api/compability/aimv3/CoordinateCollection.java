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
public class CoordinateCollection implements IAimXMLOperations {

    private List<Coordinate> listCoordinate = new ArrayList<Coordinate>();

    CoordinateCollection() {
    }

    public void AddCoordinate(Coordinate newCoordinate) {
        this.listCoordinate.add(newCoordinate);
    }

    public List<Coordinate> getCoordinateList() {
        return this.listCoordinate;
    }

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//
//        Element coordinateCollection = doc.createElement("coordinateCollection");
//        for (int i = 0; i < this.listCoordinate.size(); i++) {
//            coordinateCollection.appendChild(this.listCoordinate.get(i).getXMLNode(doc));
//        }
//        return coordinateCollection;
//    }

    @Override
    public void setXMLNode(Node node) {

        this.listCoordinate.clear();

        NodeList tempList = node.getChildNodes();
        for (int j = 0; j < tempList.getLength(); j++) {
            if ("Coordinate".equals(tempList.item(j).getNodeName())) {
                Coordinate obj = new Coordinate();
                obj.setXMLNode(tempList.item(j));
                this.AddCoordinate(obj);
            }
        }
    }    
    
    public boolean isEqualTo(Object other) {
        CoordinateCollection oth = (CoordinateCollection) other;
        if (this.listCoordinate.size() != oth.listCoordinate.size()) {
            return false;
        }
        for (int i = 0; i < this.listCoordinate.size(); i++) {
            if (!this.listCoordinate.get(i).isEqualTo(oth.listCoordinate.get(i))) {
                return false;
            }
        }
        return true;
    }

    public edu.stanford.hakan.aim4api.base.CoordinateCollection toAimV4() {
        edu.stanford.hakan.aim4api.base.CoordinateCollection res = new edu.stanford.hakan.aim4api.base.CoordinateCollection();
        List<edu.stanford.hakan.aim4api.compability.aimv3.Coordinate> list = this.getCoordinateList();
        for (edu.stanford.hakan.aim4api.compability.aimv3.Coordinate itemV3 : list) {
            res.addCoordinate(itemV3.toAimV4());
        }
        return res;
    }

    public CoordinateCollection(edu.stanford.hakan.aim4api.base.CoordinateCollection v4) {
        List<edu.stanford.hakan.aim4api.base.Coordinate> listV4 = v4.getCoordinateList();
        for (edu.stanford.hakan.aim4api.base.Coordinate itemV4 : listV4) {
            this.AddCoordinate(new Coordinate(itemV4));
        }
    }
}
