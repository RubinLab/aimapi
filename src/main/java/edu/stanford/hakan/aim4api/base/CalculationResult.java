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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
*
* @author localadmin
*/
public class CalculationResult implements IAimXMLOperations {

private ST unitOfMeasure;
private CD dataType;
private DimensionCollection dimensionCollection = new DimensionCollection();
private Enumerations.CalculationResultIdentifier type;
private String tagName;
private String xsiType;

public ST getUnitOfMeasure() {
return unitOfMeasure;
}

public void setUnitOfMeasure(ST unitOfMeasure) {
unitOfMeasure.setTagName("unitOfMeasure");
this.unitOfMeasure = unitOfMeasure;
}

public CD getDataType() {
return dataType;
}

public void setDataType(CD dataType) {
dataType.setTagName("dataType");
this.dataType = dataType;
}

public DimensionCollection getDimensionCollection() {
return dimensionCollection;
}

public void setDimensionCollection(DimensionCollection dimensionCollection) {
this.dimensionCollection = dimensionCollection;
}

public void addDimension(Dimension newDimension) {
this.dimensionCollection.addDimension(newDimension);
}

public Enumerations.CalculationResultIdentifier getType() {
return type;
}

public void setType(Enumerations.CalculationResultIdentifier type) {
this.type = type;
}

protected String getTagName() {
return tagName;
}

protected void setTagName(String tagName) {
this.tagName = tagName;
}

public String getXsiType() {
return xsiType;
}

protected void setXsiType(String xsiType) {
this.xsiType = xsiType;
}

@Override
public Node getXMLNode(Document doc) throws AimException {
if (getTagName() == null || "".equals(getTagName())) {
setTagName("CalculationResult");
}
Element res = doc.createElement(getTagName());
if (this.unitOfMeasure != null) {
res.appendChild(this.unitOfMeasure.getXMLNode(doc));
}
if (this.dataType != null) {
res.appendChild(this.dataType.getXMLNode(doc));
}
if (this.dimensionCollection.size() > 0) {
res.appendChild(this.dimensionCollection.getXMLNode(doc));
}
if (this.type != null) {
res.setAttribute("type", this.type.toString());
}
if (this.xsiType != null && (this.xsiType == null ? this.getTagName() != null : !this.xsiType.equals(this.getTagName()))) {
res.setAttribute("xsi:type", this.xsiType.toString());
}
return res;
}

@Override
public void setXMLNode(Node node) {
NamedNodeMap map = node.getAttributes();
if (map.getNamedItem("type") != null) {
this.type = Enumerations.CalculationResultIdentifier.valueOf(map.getNamedItem("type").getNodeValue());
}
if (map.getNamedItem("xsiType") != null) {
this.xsiType = map.getNamedItem("xsiType").getNodeValue();
}
NodeList listChilds = node.getChildNodes();
for (int i = 0; i < listChilds.getLength(); i++) {
Node currentNode = listChilds.item(i);
if ("unitOfMeasure".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setUnitOfMeasure(obj);
}
if ("dataType".equals(currentNode.getNodeName())) {
CD obj = new CD();
obj.setXMLNode(currentNode);
this.setDataType(obj);
}
if ("dimensionCollection".equals(listChilds.item(i).getNodeName())) {
this.dimensionCollection.setXMLNode(listChilds.item(i));
}
}
}

@Override
public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isEqualTo(Object other) {
CalculationResult oth = (CalculationResult) other;
if (this.unitOfMeasure == null ? oth.unitOfMeasure != null : !this.unitOfMeasure.isEqualTo(oth.unitOfMeasure)) {
return false;
}
if (this.dataType == null ? oth.dataType != null : !this.dataType.isEqualTo(oth.dataType)) {
return false;
}
if (this.dimensionCollection == null ? oth.dimensionCollection != null : !this.dimensionCollection.isEqualTo(oth.dimensionCollection)) {
return false;
}
if (this.type == null ? oth.type != null : !this.type.equals(oth.type)) {
return false;
}
return true;
}

public CalculationResult getClone() {
CalculationResult res = new CalculationResult();
if (this.getUnitOfMeasure() != null) {
res.setUnitOfMeasure(this.getUnitOfMeasure().getClone());
}
if (this.getDataType() != null) {
res.setDataType(this.getDataType().getClone());
}
if (this.getDimensionCollection() != null) {
res.setDimensionCollection(this.getDimensionCollection().getClone());
}
if (this.getType() != null) {
res.setType(this.getType());
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
