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
public class TEL extends URL {

public TEL() {
setXsiType("TEL");
}
private QSET_TS useablePeriod;
private Enumerations.set_TelecommunicationAddressUse use;
private Boolean hasIsoAttributes;

public QSET_TS getUseablePeriod() {
return useablePeriod;
}

public void setUseablePeriod(QSET_TS useablePeriod) {
useablePeriod.setTagName("useablePeriod");
useablePeriod.setHasIsoAttributes(true);
this.useablePeriod = useablePeriod;
}

public Enumerations.set_TelecommunicationAddressUse getUse() {
return use;
}

public void setUse(Enumerations.set_TelecommunicationAddressUse use) {
this.use = use;
}

protected Boolean getHasIsoAttributes() {
return hasIsoAttributes;
}

protected void setHasIsoAttributes(Boolean hasIsoAttributes) {
this.hasIsoAttributes = hasIsoAttributes;
}

@Override
public Node getXMLNode(Document doc) throws AimException {
if (getTagName() == null || "".equals(getTagName())) {
setTagName("TEL");
}
if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
this.setTagName("iso:".concat(this.getTagName()));
}
Element res = (Element) super.getXMLNode(doc);
if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
res.setAttribute("xmlns:iso", "uri:iso.org:21090");
}
if (this.useablePeriod != null) {
res.appendChild(this.useablePeriod.getXMLNode(doc));
}
if (this.use != null) {
res.setAttribute("use", this.use.toString());
}
return res;
}

@Override
public void setXMLNode(Node node) {
super.setXMLNode(node);
NamedNodeMap map = node.getAttributes();
if (map.getNamedItem("use") != null) {
this.use = Enumerations.set_TelecommunicationAddressUse.valueOf(map.getNamedItem("use").getNodeValue());
}
NodeList listChilds = node.getChildNodes();
for (int i = 0; i < listChilds.getLength(); i++) {
Node currentNode = listChilds.item(i);
if ("useablePeriod".equals(currentNode.getNodeName()) || "iso:useablePeriod".equals(currentNode.getNodeName())) {
QSET_TS obj = new QSET_TS();
obj.setXMLNode(currentNode);
this.setUseablePeriod(obj);
}
}
}

@Override
public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isEqualTo(Object other) {
TEL oth = (TEL) other;
if (this.useablePeriod == null ? oth.useablePeriod != null : !this.useablePeriod.isEqualTo(oth.useablePeriod)) {
return false;
}
if (this.use == null ? oth.use != null : !this.use.equals(oth.use)) {
return false;
}
return super.isEqualTo(other);
}

@Override
public TEL getClone() {
TEL res = new TEL();
if (this.getUseablePeriod() != null) {
res.setUseablePeriod(this.getUseablePeriod().getClone());
}
if (this.getUse() != null) {
res.setUse(this.getUse());
}
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
