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
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
*
* @author localadmin
*/
public class MarkupEntityCollection implements IAimXMLOperations
{

private List<MarkupEntity> listMarkupEntity = new ArrayList<MarkupEntity>();

public void addMarkupEntity(MarkupEntity newMarkupEntity)
{
this.listMarkupEntity.add(newMarkupEntity);
}

public List<MarkupEntity> getMarkupEntityList()
{
return this.listMarkupEntity;
}

public int size()
{
return this.listMarkupEntity.size();
}

public MarkupEntity get(int index)
{
if (index <= this.listMarkupEntity.size() - 1) {
return this.listMarkupEntity.get(index);
}
return null;
}

@Override
public Node getXMLNode(Document doc) throws AimException
{

Element markupEntityCollection = doc.createElement("markupEntityCollection");
for (int i = 0; i < this.listMarkupEntity.size(); i++) {
this.listMarkupEntity.get(i).setTagName("MarkupEntity");
markupEntityCollection.appendChild(this.listMarkupEntity.get(i).getXMLNode(doc));
}
return markupEntityCollection;
}

@Override
public void setXMLNode(Node node)
{
this.listMarkupEntity.clear();
NodeList tempList = node.getChildNodes();
for (int j = 0; j < tempList.getLength(); j++) {
Node currentNode = tempList.item(j);
if ("MarkupEntity".equals(currentNode.getNodeName())) {
NamedNodeMap map = currentNode.getAttributes();
if (map.getNamedItem("xsi:type") != null) {
String xsiType = map.getNamedItem("xsi:type").getNodeValue();
if ("TextAnnotationEntity".equals(xsiType)) {
TextAnnotationEntity obj = new TextAnnotationEntity();
obj.setXMLNode(currentNode);
this.addMarkupEntity(obj);
}
if ("GeometricShapeEntity".equals(xsiType)) {
GeometricShapeEntity obj = new GeometricShapeEntity();
obj.setXMLNode(currentNode);
this.addMarkupEntity(obj);
}
if ("ThreeDimensionGeometricShapeEntity".equals(xsiType)) {
ThreeDimensionGeometricShapeEntity obj = new ThreeDimensionGeometricShapeEntity();
obj.setXMLNode(currentNode);
this.addMarkupEntity(obj);
}
if ("ThreeDimensionPoint".equals(xsiType)) {
ThreeDimensionPoint obj = new ThreeDimensionPoint();
obj.setXMLNode(currentNode);
this.addMarkupEntity(obj);
}
if ("ThreeDimensionPolygon".equals(xsiType)) {
ThreeDimensionPolygon obj = new ThreeDimensionPolygon();
obj.setXMLNode(currentNode);
this.addMarkupEntity(obj);
}
if ("ThreeDimensionPolyline".equals(xsiType)) {
ThreeDimensionPolyline obj = new ThreeDimensionPolyline();
obj.setXMLNode(currentNode);
this.addMarkupEntity(obj);
}
if ("ThreeDimensionEllipse".equals(xsiType)) {
ThreeDimensionEllipse obj = new ThreeDimensionEllipse();
obj.setXMLNode(currentNode);
this.addMarkupEntity(obj);
}
if ("ThreeDimensionEllipsoid".equals(xsiType)) {
ThreeDimensionEllipsoid obj = new ThreeDimensionEllipsoid();
obj.setXMLNode(currentNode);
this.addMarkupEntity(obj);
}
if ("ThreeDimensionMultiPoint".equals(xsiType)) {
ThreeDimensionMultiPoint obj = new ThreeDimensionMultiPoint();
obj.setXMLNode(currentNode);
this.addMarkupEntity(obj);
}
if ("TwoDimensionGeometricShapeEntity".equals(xsiType)) {
TwoDimensionGeometricShapeEntity obj = new TwoDimensionGeometricShapeEntity();
obj.setXMLNode(currentNode);
this.addMarkupEntity(obj);
}
if ("TwoDimensionMultiPoint".equals(xsiType)) {
TwoDimensionMultiPoint obj = new TwoDimensionMultiPoint();
obj.setXMLNode(currentNode);
this.addMarkupEntity(obj);
}
if ("TwoDimensionPoint".equals(xsiType)) {
TwoDimensionPoint obj = new TwoDimensionPoint();
obj.setXMLNode(currentNode);
this.addMarkupEntity(obj);
}
if ("TwoDimensionCircle".equals(xsiType)) {
TwoDimensionCircle obj = new TwoDimensionCircle();
obj.setXMLNode(currentNode);
this.addMarkupEntity(obj);
}
if ("TwoDimensionEllipse".equals(xsiType)) {
TwoDimensionEllipse obj = new TwoDimensionEllipse();
obj.setXMLNode(currentNode);
this.addMarkupEntity(obj);
}
if ("TwoDimensionPolyline".equals(xsiType)) {
TwoDimensionPolyline obj = new TwoDimensionPolyline();
obj.setXMLNode(currentNode);
this.addMarkupEntity(obj);
}

} else {
MarkupEntity obj = new MarkupEntity();
obj.setXMLNode(tempList.item(j));
this.addMarkupEntity(obj);
}
}
}

}

@Override
public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools
// | Templates.
}

@Override
public boolean isEqualTo(Object other) {
MarkupEntityCollection oth = (MarkupEntityCollection) other;
if (this.listMarkupEntity.size() != oth.listMarkupEntity.size()) {
return false;
}
for (int i = 0; i < this.listMarkupEntity.size(); i++) {
if (this.listMarkupEntity.get(i) == null ? oth.listMarkupEntity.get(i) != null : !this.listMarkupEntity.get(i).isEqualTo(oth.listMarkupEntity.get(i))) {
return false;
}
}
return true;
}

public MarkupEntityCollection getClone()
{
MarkupEntityCollection res = new MarkupEntityCollection();
for (int i = 0; i < this.listMarkupEntity.size(); i++) {
if (this.listMarkupEntity.get(i) != null) {
res.addMarkupEntity(this.listMarkupEntity.get(i).getClone());
}
}
return res;
}
}
