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
public class DicomSegmentationEntity extends SegmentationEntity {

public DicomSegmentationEntity() {
setXsiType("DicomSegmentationEntity");
}
private II sopInstanceUid;
private II sopClassUid;
private II referencedSopInstanceUid;
private Integer segmentNumber;

public II getSopInstanceUid() {
return sopInstanceUid;
}

public void setSopInstanceUid(II sopInstanceUid) {
sopInstanceUid.setTagName("sopInstanceUid");
this.sopInstanceUid = sopInstanceUid;
}

public II getSopClassUid() {
return sopClassUid;
}

public void setSopClassUid(II sopClassUid) {
sopClassUid.setTagName("sopClassUid");
this.sopClassUid = sopClassUid;
}

public II getReferencedSopInstanceUid() {
return referencedSopInstanceUid;
}

public void setReferencedSopInstanceUid(II referencedSopInstanceUid) {
referencedSopInstanceUid.setTagName("referencedSopInstanceUid");
this.referencedSopInstanceUid = referencedSopInstanceUid;
}

public Integer getSegmentNumber() {
return segmentNumber;
}

public void setSegmentNumber(Integer segmentNumber) {
this.segmentNumber = segmentNumber;
}

@Override
public Node getXMLNode(Document doc) throws AimException {
if (getTagName() == null || "".equals(getTagName())) {
setTagName("DicomSegmentationEntity");
}
Element res = (Element) super.getXMLNode(doc);
if (this.sopInstanceUid != null) {
res.appendChild(this.sopInstanceUid.getXMLNode(doc));
}
if (this.sopClassUid != null) {
res.appendChild(this.sopClassUid.getXMLNode(doc));
}
if (this.referencedSopInstanceUid != null) {
res.appendChild(this.referencedSopInstanceUid.getXMLNode(doc));
}
if (this.segmentNumber != null) {
Element el_segmentNumber = doc.createElement("segmentNumber");
el_segmentNumber.setAttribute("value", this.segmentNumber.toString());
res.appendChild(el_segmentNumber);
}
return res;
}

@Override
public void setXMLNode(Node node) {
super.setXMLNode(node);
NodeList listChilds = node.getChildNodes();
for (int i = 0; i < listChilds.getLength(); i++) {
Node currentNode = listChilds.item(i);
if ("sopInstanceUid".equals(currentNode.getNodeName())) {
II obj = new II();
obj.setXMLNode(currentNode);
this.setSopInstanceUid(obj);
}
if ("sopClassUid".equals(currentNode.getNodeName())) {
II obj = new II();
obj.setXMLNode(currentNode);
this.setSopClassUid(obj);
}
if ("referencedSopInstanceUid".equals(currentNode.getNodeName())) {
II obj = new II();
obj.setXMLNode(currentNode);
this.setReferencedSopInstanceUid(obj);
}
if ("segmentNumber".equals(currentNode.getNodeName())) {
this.segmentNumber = Integer.parseInt(currentNode.getAttributes().getNamedItem("value").getNodeValue());
}
}
}

@Override
public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isEqualTo(Object other) {
DicomSegmentationEntity oth = (DicomSegmentationEntity) other;
if (this.sopInstanceUid == null ? oth.sopInstanceUid != null : !this.sopInstanceUid.isEqualTo(oth.sopInstanceUid)) {
return false;
}
if (this.sopClassUid == null ? oth.sopClassUid != null : !this.sopClassUid.isEqualTo(oth.sopClassUid)) {
return false;
}
if (this.referencedSopInstanceUid == null ? oth.referencedSopInstanceUid != null : !this.referencedSopInstanceUid.isEqualTo(oth.referencedSopInstanceUid)) {
return false;
}
if (this.segmentNumber == null ? oth.segmentNumber != null : !this.segmentNumber.equals(oth.segmentNumber)) {
return false;
}
return super.isEqualTo(other);
}

@Override
public DicomSegmentationEntity getClone() {
DicomSegmentationEntity res = new DicomSegmentationEntity();
if (this.getSopInstanceUid() != null) {
res.setSopInstanceUid(this.getSopInstanceUid().getClone());
}
if (this.getSopClassUid() != null) {
res.setSopClassUid(this.getSopClassUid().getClone());
}
if (this.getReferencedSopInstanceUid() != null) {
res.setReferencedSopInstanceUid(this.getReferencedSopInstanceUid().getClone());
}
if (this.getSegmentNumber() != null) {
res.setSegmentNumber(this.getSegmentNumber());
}
if (this.getUniqueIdentifier() != null) {
res.setUniqueIdentifier(this.getUniqueIdentifier().getClone());
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
