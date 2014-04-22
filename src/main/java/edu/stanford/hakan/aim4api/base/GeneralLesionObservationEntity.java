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
public class GeneralLesionObservationEntity extends LesionObservationEntity {

public GeneralLesionObservationEntity() {
setXsiType("GeneralLesionObservationEntity");
}
private ST trackingIdentifier;
private CD lesionType;
private CD reconstructionInterval;
private CD laterality;

public ST getTrackingIdentifier() {
return trackingIdentifier;
}

public void setTrackingIdentifier(ST trackingIdentifier) {
trackingIdentifier.setTagName("trackingIdentifier");
this.trackingIdentifier = trackingIdentifier;
}

public CD getLesionType() {
return lesionType;
}

public void setLesionType(CD lesionType) {
lesionType.setTagName("lesionType");
this.lesionType = lesionType;
}

public CD getReconstructionInterval() {
return reconstructionInterval;
}

public void setReconstructionInterval(CD reconstructionInterval) {
reconstructionInterval.setTagName("reconstructionInterval");
this.reconstructionInterval = reconstructionInterval;
}

public CD getLaterality() {
return laterality;
}

public void setLaterality(CD laterality) {
laterality.setTagName("laterality");
this.laterality = laterality;
}

@Override
public Node getXMLNode(Document doc) throws AimException {
if (getTagName() == null || "".equals(getTagName())) {
setTagName("GeneralLesionObservationEntity");
}
Element res = (Element) super.getXMLNode(doc);
if (this.trackingIdentifier != null) {
res.appendChild(this.trackingIdentifier.getXMLNode(doc));
}
if (this.lesionType != null) {
res.appendChild(this.lesionType.getXMLNode(doc));
}
if (this.reconstructionInterval != null) {
res.appendChild(this.reconstructionInterval.getXMLNode(doc));
}
if (this.laterality != null) {
res.appendChild(this.laterality.getXMLNode(doc));
}
return res;
}

@Override
public void setXMLNode(Node node) {
super.setXMLNode(node);
NodeList listChilds = node.getChildNodes();
for (int i = 0; i < listChilds.getLength(); i++) {
Node currentNode = listChilds.item(i);
if ("trackingIdentifier".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setTrackingIdentifier(obj);
}
if ("lesionType".equals(currentNode.getNodeName())) {
CD obj = new CD();
obj.setXMLNode(currentNode);
this.setLesionType(obj);
}
if ("reconstructionInterval".equals(currentNode.getNodeName())) {
CD obj = new CD();
obj.setXMLNode(currentNode);
this.setReconstructionInterval(obj);
}
if ("laterality".equals(currentNode.getNodeName())) {
CD obj = new CD();
obj.setXMLNode(currentNode);
this.setLaterality(obj);
}
}
}

@Override
public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isEqualTo(Object other) {
GeneralLesionObservationEntity oth = (GeneralLesionObservationEntity) other;
if (this.trackingIdentifier == null ? oth.trackingIdentifier != null : !this.trackingIdentifier.isEqualTo(oth.trackingIdentifier)) {
return false;
}
if (this.lesionType == null ? oth.lesionType != null : !this.lesionType.isEqualTo(oth.lesionType)) {
return false;
}
if (this.reconstructionInterval == null ? oth.reconstructionInterval != null : !this.reconstructionInterval.isEqualTo(oth.reconstructionInterval)) {
return false;
}
if (this.laterality == null ? oth.laterality != null : !this.laterality.isEqualTo(oth.laterality)) {
return false;
}
return super.isEqualTo(other);
}

@Override
public GeneralLesionObservationEntity getClone() {
GeneralLesionObservationEntity res = new GeneralLesionObservationEntity();
if (this.getTrackingIdentifier() != null) {
res.setTrackingIdentifier(this.getTrackingIdentifier().getClone());
}
if (this.getLesionType() != null) {
res.setLesionType(this.getLesionType().getClone());
}
if (this.getReconstructionInterval() != null) {
res.setReconstructionInterval(this.getReconstructionInterval().getClone());
}
if (this.getLaterality() != null) {
res.setLaterality(this.getLaterality().getClone());
}
if (this.getLesionUniqueIdentifier() != null) {
res.setLesionUniqueIdentifier(this.getLesionUniqueIdentifier().getClone());
}
if (this.getIsAdditionalObservation() != null) {
res.setIsAdditionalObservation(this.getIsAdditionalObservation());
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
