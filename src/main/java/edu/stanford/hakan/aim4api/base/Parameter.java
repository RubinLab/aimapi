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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
*
* @author localadmin
*/
public class Parameter implements IAimXMLOperations {

private ST name;
private ST value;
private CD dataType;
private String tagName;

public ST getName() {
return name;
}

public void setName(ST name) {
name.setTagName("name");
this.name = name;
}

public ST getValue() {
return value;
}

public void setValue(ST value) {
value.setTagName("value");
this.value = value;
}

public CD getDataType() {
return dataType;
}

public void setDataType(CD dataType) {
dataType.setTagName("dataType");
this.dataType = dataType;
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
setTagName("Parameter");
}
Element res = doc.createElement(getTagName());
if (this.name != null) {
res.appendChild(this.name.getXMLNode(doc));
}
if (this.value != null) {
res.appendChild(this.value.getXMLNode(doc));
}
if (this.dataType != null) {
res.appendChild(this.dataType.getXMLNode(doc));
}
return res;
}

@Override
public void setXMLNode(Node node) {
NodeList listChilds = node.getChildNodes();
for (int i = 0; i < listChilds.getLength(); i++) {
Node currentNode = listChilds.item(i);
if ("name".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setName(obj);
}
if ("value".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setValue(obj);
}
if ("dataType".equals(currentNode.getNodeName())) {
CD obj = new CD();
obj.setXMLNode(currentNode);
this.setDataType(obj);
}
}
}

@Override
public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isEqualTo(Object other) {
Parameter oth = (Parameter) other;
if (this.name == null ? oth.name != null : !this.name.isEqualTo(oth.name)) {
return false;
}
if (this.value == null ? oth.value != null : !this.value.isEqualTo(oth.value)) {
return false;
}
if (this.dataType == null ? oth.dataType != null : !this.dataType.isEqualTo(oth.dataType)) {
return false;
}
return true;
}

public Parameter getClone() {
Parameter res = new Parameter();
if (this.getName() != null) {
res.setName(this.getName().getClone());
}
if (this.getValue() != null) {
res.setValue(this.getValue().getClone());
}
if (this.getDataType() != null) {
res.setDataType(this.getDataType().getClone());
}
if (this.getTagName() != null) {
res.setTagName(this.getTagName());
}
return res;
}
}
