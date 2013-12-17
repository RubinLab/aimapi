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

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

/**
 *
 * @author localadmin
 */
public class CoordinateCollection implements IAimXMLOperations {

    private List<Coordinate> listCoordinate = new ArrayList<>();

    public void addCoordinate(Coordinate newCoordinate) {
        this.listCoordinate.add(newCoordinate);
    }

    public List<Coordinate> getCoordinateList() {
        return this.listCoordinate;
    }

    public int size() {
        return this.listCoordinate.size();
    }

    public Coordinate get(int index) {
        if (index <= this.listCoordinate.size() - 1) {
            return this.listCoordinate.get(index);
        }
        return null;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        Element coordinateCollection = doc.createElement("coordinateCollection");
        for (int i = 0; i < this.listCoordinate.size(); i++) {
            this.listCoordinate.get(i).setTagName("Coordinate");
            coordinateCollection.appendChild(this.listCoordinate.get(i).getXMLNode(doc));
        }
        return coordinateCollection;
    }

    @Override
    public void setXMLNode(Node node) {
        this.listCoordinate.clear();
        NodeList tempList = node.getChildNodes();
        for (int j = 0; j < tempList.getLength(); j++) {
            Node currentNode = tempList.item(j);
            if ("Coordinate".equals(currentNode.getNodeName())) {
                NamedNodeMap map = currentNode.getAttributes();
                if (map.getNamedItem("xsi:type") != null) {
                    String xsiType = map.getNamedItem("xsi:type").getNodeValue();

                } else {
                    Coordinate obj = new Coordinate();
                    obj.setXMLNode(tempList.item(j));
                    this.addCoordinate(obj);
                }
            }
        }

    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
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

    public CoordinateCollection getClone() {
        CoordinateCollection res = new CoordinateCollection();
        for (int i = 0; i < this.listCoordinate.size(); i++) {
            if (this.listCoordinate.get(i) != null) {
                res.addCoordinate(this.listCoordinate.get(i).getClone());
            }
        }
        return res;
    }
}
