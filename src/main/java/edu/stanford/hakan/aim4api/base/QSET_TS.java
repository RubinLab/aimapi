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
public class QSET_TS implements IAimXMLOperations {

private ED_Text originalText;
private Boolean hasIsoAttributes;
private String tagName;

public ED_Text getOriginalText() {
return originalText;
}

public void setOriginalText(ED_Text originalText) {
originalText.setTagName("originalText");
originalText.setHasIsoAttributes(true);
this.originalText = originalText;
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

@Override
public Node getXMLNode(Document doc) throws AimException {
if (getTagName() == null || "".equals(getTagName())) {
setTagName("QSET_TS");
}
if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
this.setTagName("iso:".concat(this.getTagName()));
}
Element res = doc.createElement(getTagName());
if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
res.setAttribute("xmlns:iso", "uri:iso.org:21090");
}
if (this.originalText != null) {
res.appendChild(this.originalText.getXMLNode(doc));
}
return res;
}

@Override
public void setXMLNode(Node node) {
NodeList listChilds = node.getChildNodes();
for (int i = 0; i < listChilds.getLength(); i++) {
Node currentNode = listChilds.item(i);
if ("originalText".equals(currentNode.getNodeName()) || "iso:originalText".equals(currentNode.getNodeName())) {
ED_Text obj = new ED_Text();
obj.setXMLNode(currentNode);
this.setOriginalText(obj);
}
}
}

@Override
public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isEqualTo(Object other) {
QSET_TS oth = (QSET_TS) other;
if (this.originalText == null ? oth.originalText != null : !this.originalText.isEqualTo(oth.originalText)) {
return false;
}
return true;
}

public QSET_TS getClone() {
QSET_TS res = new QSET_TS();
if (this.getOriginalText() != null) {
res.setOriginalText(this.getOriginalText().getClone());
}
if (this.getTagName() != null) {
res.setTagName(this.getTagName());
}
return res;
}
}
