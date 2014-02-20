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
public class Numerical extends CharacteristicQuantification {

public Numerical() {
setXsiType("Numerical");
}
private ST ucumString;
private Double value;
private Enumerations.ComparisonOperator operator;

public ST getUcumString() {
return ucumString;
}

public void setUcumString(ST ucumString) {
ucumString.setTagName("ucumString");
this.ucumString = ucumString;
}

public Double getValue() {
return value;
}

public void setValue(Double value) {
this.value = value;
}

public Enumerations.ComparisonOperator getOperator() {
return operator;
}

public void setOperator(Enumerations.ComparisonOperator operator) {
this.operator = operator;
}

@Override
public Node getXMLNode(Document doc) throws AimException {
if (getTagName() == null || "".equals(getTagName())) {
setTagName("Numerical");
}
Element res = (Element) super.getXMLNode(doc);
if (this.ucumString != null) {
res.appendChild(this.ucumString.getXMLNode(doc));
}
if (this.value != null) {
Element el_value = doc.createElement("value");
el_value.setAttribute("value", this.value.toString());
res.appendChild(el_value);
}
if (this.operator != null) {
res.setAttribute("operator", this.operator.toString());
}
return res;
}

@Override
public void setXMLNode(Node node) {
super.setXMLNode(node);
NamedNodeMap map = node.getAttributes();
if (map.getNamedItem("operator") != null) {
this.operator = Enumerations.ComparisonOperator.valueOf(map.getNamedItem("operator").getNodeValue());
}
NodeList listChilds = node.getChildNodes();
for (int i = 0; i < listChilds.getLength(); i++) {
Node currentNode = listChilds.item(i);
if ("ucumString".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setUcumString(obj);
}
if ("value".equals(currentNode.getNodeName())) {
this.value = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
}
}
}

@Override
public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isEqualTo(Object other) {
Numerical oth = (Numerical) other;
if (this.ucumString == null ? oth.ucumString != null : !this.ucumString.isEqualTo(oth.ucumString)) {
return false;
}
if (this.value == null ? oth.value != null : !this.value.equals(oth.value)) {
return false;
}
if (this.operator == null ? oth.operator != null : !this.operator.equals(oth.operator)) {
return false;
}
return super.isEqualTo(other);
}

@Override
public Numerical getClone() {
Numerical res = new Numerical();
if (this.getUcumString() != null) {
res.setUcumString(this.getUcumString().getClone());
}
if (this.getValue() != null) {
res.setValue(this.getValue());
}
if (this.getOperator() != null) {
res.setOperator(this.getOperator());
}
if (this.getAnnotatorConfidence() != null) {
res.setAnnotatorConfidence(this.getAnnotatorConfidence());
}
if (this.getCharacteristicQuantificationIndex() != null) {
res.setCharacteristicQuantificationIndex(this.getCharacteristicQuantificationIndex());
}
if (this.getLabel() != null) {
res.setLabel(this.getLabel().getClone());
}
if (this.getValueLabel() != null) {
res.setValueLabel(this.getValueLabel().getClone());
}
if (this.getValueDescription() != null) {
res.setValueDescription(this.getValueDescription().getClone());
}
if (this.getComment() != null) {
res.setComment(this.getComment().getClone());
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
