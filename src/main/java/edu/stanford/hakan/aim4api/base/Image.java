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
public class Image implements IAimXMLOperations {

private II sopClassUid;
private II sopInstanceUid;
private ImagePlane imagePlane;
private GeneralImage generalImage;
private String tagName;

public Image() {
}

public Image(II sopClassUid, II sopInstanceUid) {
this.sopClassUid = sopClassUid;
this.sopInstanceUid = sopInstanceUid;
}

public II getSopClassUid() {
return sopClassUid;
}

public void setSopClassUid(II sopClassUid) {
sopClassUid.setTagName("sopClassUid");
this.sopClassUid = sopClassUid;
}

public II getSopInstanceUid() {
return sopInstanceUid;
}

public void setSopInstanceUid(II sopInstanceUid) {
sopInstanceUid.setTagName("sopInstanceUid");
this.sopInstanceUid = sopInstanceUid;
}

public ImagePlane getImagePlane() {
return imagePlane;
}

public void setImagePlane(ImagePlane imagePlane) {
imagePlane.setTagName("imagePlane");
this.imagePlane = imagePlane;
}

public GeneralImage getGeneralImage() {
return generalImage;
}

public void setGeneralImage(GeneralImage generalImage) {
generalImage.setTagName("generalImage");
this.generalImage = generalImage;
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
setTagName("Image");
}
Element res = doc.createElement(getTagName());
if (this.sopClassUid != null) {
res.appendChild(this.sopClassUid.getXMLNode(doc));
}
if (this.sopInstanceUid != null) {
res.appendChild(this.sopInstanceUid.getXMLNode(doc));
}
if (this.imagePlane != null) {
res.appendChild(this.imagePlane.getXMLNode(doc));
}
if (this.generalImage != null) {
res.appendChild(this.generalImage.getXMLNode(doc));
}
return res;
}

@Override
public void setXMLNode(Node node) {
NodeList listChilds = node.getChildNodes();
for (int i = 0; i < listChilds.getLength(); i++) {
Node currentNode = listChilds.item(i);
if ("sopClassUid".equals(currentNode.getNodeName())) {
II obj = new II();
obj.setXMLNode(currentNode);
this.setSopClassUid(obj);
}
if ("sopInstanceUid".equals(currentNode.getNodeName())) {
II obj = new II();
obj.setXMLNode(currentNode);
this.setSopInstanceUid(obj);
}
if ("imagePlane".equals(currentNode.getNodeName())) {
ImagePlane obj = new ImagePlane();
obj.setXMLNode(currentNode);
this.setImagePlane(obj);
}
if ("generalImage".equals(currentNode.getNodeName())) {
GeneralImage obj = new GeneralImage();
obj.setXMLNode(currentNode);
this.setGeneralImage(obj);
}
}
}

@Override
public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isEqualTo(Object other) {
Image oth = (Image) other;
if (this.sopClassUid == null ? oth.sopClassUid != null : !this.sopClassUid.isEqualTo(oth.sopClassUid)) {
return false;
}
if (this.sopInstanceUid == null ? oth.sopInstanceUid != null : !this.sopInstanceUid.isEqualTo(oth.sopInstanceUid)) {
return false;
}
if (this.imagePlane == null ? oth.imagePlane != null : !this.imagePlane.isEqualTo(oth.imagePlane)) {
return false;
}
if (this.generalImage == null ? oth.generalImage != null : !this.generalImage.isEqualTo(oth.generalImage)) {
return false;
}
return true;
}

public Image getClone() {
Image res = new Image();
if (this.getSopClassUid() != null) {
res.setSopClassUid(this.getSopClassUid().getClone());
}
if (this.getSopInstanceUid() != null) {
res.setSopInstanceUid(this.getSopInstanceUid().getClone());
}
if (this.getImagePlane() != null) {
res.setImagePlane(this.getImagePlane().getClone());
}
if (this.getGeneralImage() != null) {
res.setGeneralImage(this.getGeneralImage().getClone());
}
if (this.getTagName() != null) {
res.setTagName(this.getTagName());
}
return res;
}
}
