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
public class ExtendedCalculationResult extends CalculationResult {

public ExtendedCalculationResult() {
setXsiType("ExtendedCalculationResult");
}
private CalculationDataCollection calculationDataCollection = new CalculationDataCollection();

public CalculationDataCollection getCalculationDataCollection() {
return calculationDataCollection;
}

public void setCalculationDataCollection(CalculationDataCollection calculationDataCollection) {
this.calculationDataCollection = calculationDataCollection;
}

public void addCalculationData(CalculationData newCalculationData) {
this.calculationDataCollection.addCalculationData(newCalculationData);
}

@Override
public Node getXMLNode(Document doc) throws AimException {
if (getTagName() == null || "".equals(getTagName())) {
setTagName("ExtendedCalculationResult");
}
Element res = (Element) super.getXMLNode(doc);
if (this.calculationDataCollection.size() > 0) {
res.appendChild(this.calculationDataCollection.getXMLNode(doc));
}
return res;
}

@Override
public void setXMLNode(Node node) {
super.setXMLNode(node);
NodeList listChilds = node.getChildNodes();
for (int i = 0; i < listChilds.getLength(); i++) {
Node currentNode = listChilds.item(i);
if ("calculationDataCollection".equals(listChilds.item(i).getNodeName())) {
this.calculationDataCollection.setXMLNode(listChilds.item(i));
}
}
}

@Override
public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isEqualTo(Object other) {
ExtendedCalculationResult oth = (ExtendedCalculationResult) other;
if (this.calculationDataCollection == null ? oth.calculationDataCollection != null : !this.calculationDataCollection.isEqualTo(oth.calculationDataCollection)) {
return false;
}
return super.isEqualTo(other);
}

@Override
public ExtendedCalculationResult getClone() {
ExtendedCalculationResult res = new ExtendedCalculationResult();
if (this.getCalculationDataCollection() != null) {
res.setCalculationDataCollection(this.getCalculationDataCollection().getClone());
}
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
