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
public class ImageSeries implements IAimXMLOperations {

private II instanceUid;
private CD modality;
private ImageCollection imageCollection = new ImageCollection();
private String tagName;

public II getInstanceUid() {
return instanceUid;
}

public void setInstanceUid(II instanceUid) {
instanceUid.setTagName("instanceUid");
this.instanceUid = instanceUid;
}

public CD getModality() {
return modality;
}

public void setModality(CD modality) {
modality.setTagName("modality");
this.modality = modality;
}

public ImageCollection getImageCollection() {
return imageCollection;
}

public void setImageCollection(ImageCollection imageCollection) {
this.imageCollection = imageCollection;
}

public void addImage(Image newImage) {
this.imageCollection.addImage(newImage);
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
setTagName("ImageSeries");
}
Element res = doc.createElement(getTagName());
if (this.instanceUid != null) {
res.appendChild(this.instanceUid.getXMLNode(doc));
}
if (this.modality != null) {
res.appendChild(this.modality.getXMLNode(doc));
}
if (this.imageCollection.size() > 0) {
res.appendChild(this.imageCollection.getXMLNode(doc));
}
return res;
}

@Override
public void setXMLNode(Node node) {
NodeList listChilds = node.getChildNodes();
for (int i = 0; i < listChilds.getLength(); i++) {
Node currentNode = listChilds.item(i);
if ("instanceUid".equals(currentNode.getNodeName())) {
II obj = new II();
obj.setXMLNode(currentNode);
this.setInstanceUid(obj);
}
if ("modality".equals(currentNode.getNodeName())) {
CD obj = new CD();
obj.setXMLNode(currentNode);
this.setModality(obj);
}
if ("imageCollection".equals(listChilds.item(i).getNodeName())) {
this.imageCollection.setXMLNode(listChilds.item(i));
}
}
}

@Override
public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isEqualTo(Object other) {
ImageSeries oth = (ImageSeries) other;
if (this.instanceUid == null ? oth.instanceUid != null : !this.instanceUid.isEqualTo(oth.instanceUid)) {
return false;
}
if (this.modality == null ? oth.modality != null : !this.modality.isEqualTo(oth.modality)) {
return false;
}
if (this.imageCollection == null ? oth.imageCollection != null : !this.imageCollection.isEqualTo(oth.imageCollection)) {
return false;
}
return true;
}

public ImageSeries getClone() {
ImageSeries res = new ImageSeries();
if (this.getInstanceUid() != null) {
res.setInstanceUid(this.getInstanceUid().getClone());
}
if (this.getModality() != null) {
res.setModality(this.getModality().getClone());
}
if (this.getImageCollection() != null) {
res.setImageCollection(this.getImageCollection().getClone());
}
if (this.getTagName() != null) {
res.setTagName(this.getTagName());
}
return res;
}
}
