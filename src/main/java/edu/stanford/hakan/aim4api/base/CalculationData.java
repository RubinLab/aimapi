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
public class CalculationData implements IAimXMLOperations {

private ST value;
private CoordinateCollection coordinateCollection = new CoordinateCollection();
private String tagName;

public ST getValue() {
return value;
}

public void setValue(ST value) {
value.setTagName("value");
this.value = value;
}

public CoordinateCollection getCoordinateCollection() {
return coordinateCollection;
}

public void setCoordinateCollection(CoordinateCollection coordinateCollection) {
this.coordinateCollection = coordinateCollection;
}

public void addCoordinate(Coordinate newCoordinate) {
this.coordinateCollection.addCoordinate(newCoordinate);
}

public void addCoordinate(Integer dimensionIndex, Integer position) {
this.coordinateCollection.addCoordinate(new Coordinate(0, 0));
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
setTagName("CalculationData");
}
Element res = doc.createElement(getTagName());
if (this.value != null) {
res.appendChild(this.value.getXMLNode(doc));
}
if (this.coordinateCollection.size() > 0) {
res.appendChild(this.coordinateCollection.getXMLNode(doc));
}
return res;
}

@Override
public void setXMLNode(Node node) {
NodeList listChilds = node.getChildNodes();
for (int i = 0; i < listChilds.getLength(); i++) {
Node currentNode = listChilds.item(i);
if ("value".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setValue(obj);
}
if ("coordinateCollection".equals(listChilds.item(i).getNodeName())) {
this.coordinateCollection.setXMLNode(listChilds.item(i));
}
}
}

@Override
public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isEqualTo(Object other) {
CalculationData oth = (CalculationData) other;
if (this.value == null ? oth.value != null : !this.value.isEqualTo(oth.value)) {
return false;
}
if (this.coordinateCollection == null ? oth.coordinateCollection != null : !this.coordinateCollection.isEqualTo(oth.coordinateCollection)) {
return false;
}
return true;
}

public CalculationData getClone() {
CalculationData res = new CalculationData();
if (this.getValue() != null) {
res.setValue(this.getValue().getClone());
}
if (this.getCoordinateCollection() != null) {
res.setCoordinateCollection(this.getCoordinateCollection().getClone());
}
if (this.getTagName() != null) {
res.setTagName(this.getTagName());
}
return res;
}
}
