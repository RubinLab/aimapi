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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
*
* @author localadmin
*/
public class URL implements IAimXMLOperations {

private String value;
private Boolean hasIsoAttributes;
private String tagName;
private String xsiType;

public String getValue() {
return value;
}

public void setValue(String value) {
this.value = value;
}

protected Boolean getHasIsoAttributes() {
return hasIsoAttributes;
}

protected void setHasIsoAttributes(Boolean hasIsoAttributes) {
this.hasIsoAttributes = hasIsoAttributes;
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
setTagName("URL");
}
if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
this.setTagName("iso:".concat(this.getTagName()));
}
Element res = doc.createElement(getTagName());
if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
res.setAttribute("xmlns:iso", "uri:iso.org:21090");
}
if (this.value != null) {
res.setAttribute("value", this.value.toString());
}
if (this.xsiType != null && (this.xsiType == null ? this.getTagName() != null : !this.xsiType.equals(this.getTagName()))) {
res.setAttribute("xsi:type", this.xsiType.toString());
}
return res;
}

@Override
public void setXMLNode(Node node) {
NamedNodeMap map = node.getAttributes();
if (map.getNamedItem("value") != null) {
this.value = map.getNamedItem("value").getNodeValue();
}
if (map.getNamedItem("xsiType") != null) {
this.xsiType = map.getNamedItem("xsiType").getNodeValue();
}
}

@Override
public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isEqualTo(Object other) {
URL oth = (URL) other;
if (this.value == null ? oth.value != null : !this.value.equals(oth.value)) {
return false;
}
return true;
}

public URL getClone() {
URL res = new URL();
if (this.getValue() != null) {
res.setValue(this.getValue());
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
