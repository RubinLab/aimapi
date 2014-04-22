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
public class TimePointLesionObservationEntity extends LesionObservationEntity {

public TimePointLesionObservationEntity() {
setXsiType("TimePointLesionObservationEntity");
}
private Boolean calibration;
private II predecessorLesionTrackingUid;
private ST comment;
private CD therapeuticResponse;
private CD qualitativeAssessment;
private Boolean canEvaluateLesion;
private CD reasonUnableToEvaluate;
private Boolean canMeasureLesion;
private CD reasonUnableToMeasure;
private Boolean isUnequivocalProgression;

public Boolean getCalibration() {
return calibration;
}

public void setCalibration(Boolean calibration) {
this.calibration = calibration;
}

public II getPredecessorLesionTrackingUid() {
return predecessorLesionTrackingUid;
}

public void setPredecessorLesionTrackingUid(II predecessorLesionTrackingUid) {
predecessorLesionTrackingUid.setTagName("predecessorLesionTrackingUid");
this.predecessorLesionTrackingUid = predecessorLesionTrackingUid;
}

public ST getComment() {
return comment;
}

public void setComment(ST comment) {
comment.setTagName("comment");
this.comment = comment;
}

public CD getTherapeuticResponse() {
return therapeuticResponse;
}

public void setTherapeuticResponse(CD therapeuticResponse) {
therapeuticResponse.setTagName("therapeuticResponse");
this.therapeuticResponse = therapeuticResponse;
}

public CD getQualitativeAssessment() {
return qualitativeAssessment;
}

public void setQualitativeAssessment(CD qualitativeAssessment) {
qualitativeAssessment.setTagName("qualitativeAssessment");
this.qualitativeAssessment = qualitativeAssessment;
}

public Boolean getCanEvaluateLesion() {
return canEvaluateLesion;
}

public void setCanEvaluateLesion(Boolean canEvaluateLesion) {
this.canEvaluateLesion = canEvaluateLesion;
}

public CD getReasonUnableToEvaluate() {
return reasonUnableToEvaluate;
}

public void setReasonUnableToEvaluate(CD reasonUnableToEvaluate) {
reasonUnableToEvaluate.setTagName("reasonUnableToEvaluate");
this.reasonUnableToEvaluate = reasonUnableToEvaluate;
}

public Boolean getCanMeasureLesion() {
return canMeasureLesion;
}

public void setCanMeasureLesion(Boolean canMeasureLesion) {
this.canMeasureLesion = canMeasureLesion;
}

public CD getReasonUnableToMeasure() {
return reasonUnableToMeasure;
}

public void setReasonUnableToMeasure(CD reasonUnableToMeasure) {
reasonUnableToMeasure.setTagName("reasonUnableToMeasure");
this.reasonUnableToMeasure = reasonUnableToMeasure;
}

public Boolean getIsUnequivocalProgression() {
return isUnequivocalProgression;
}

public void setIsUnequivocalProgression(Boolean isUnequivocalProgression) {
this.isUnequivocalProgression = isUnequivocalProgression;
}

@Override
public Node getXMLNode(Document doc) throws AimException {
if (getTagName() == null || "".equals(getTagName())) {
setTagName("TimePointLesionObservationEntity");
}
Element res = (Element) super.getXMLNode(doc);
if (this.calibration != null) {
Element el_calibration = doc.createElement("calibration");
el_calibration.setAttribute("value", this.calibration.toString());
res.appendChild(el_calibration);
}
if (this.predecessorLesionTrackingUid != null) {
res.appendChild(this.predecessorLesionTrackingUid.getXMLNode(doc));
}
if (this.comment != null) {
res.appendChild(this.comment.getXMLNode(doc));
}
if (this.therapeuticResponse != null) {
res.appendChild(this.therapeuticResponse.getXMLNode(doc));
}
if (this.qualitativeAssessment != null) {
res.appendChild(this.qualitativeAssessment.getXMLNode(doc));
}
if (this.canEvaluateLesion != null) {
Element el_canEvaluateLesion = doc.createElement("canEvaluateLesion");
el_canEvaluateLesion.setAttribute("value", this.canEvaluateLesion.toString());
res.appendChild(el_canEvaluateLesion);
}
if (this.reasonUnableToEvaluate != null) {
res.appendChild(this.reasonUnableToEvaluate.getXMLNode(doc));
}
if (this.canMeasureLesion != null) {
Element el_canMeasureLesion = doc.createElement("canMeasureLesion");
el_canMeasureLesion.setAttribute("value", this.canMeasureLesion.toString());
res.appendChild(el_canMeasureLesion);
}
if (this.reasonUnableToMeasure != null) {
res.appendChild(this.reasonUnableToMeasure.getXMLNode(doc));
}
if (this.isUnequivocalProgression != null) {
Element el_isUnequivocalProgression = doc.createElement("isUnequivocalProgression");
el_isUnequivocalProgression.setAttribute("value", this.isUnequivocalProgression.toString());
res.appendChild(el_isUnequivocalProgression);
}
return res;
}

@Override
public void setXMLNode(Node node) {
super.setXMLNode(node);
NodeList listChilds = node.getChildNodes();
for (int i = 0; i < listChilds.getLength(); i++) {
Node currentNode = listChilds.item(i);
if ("calibration".equals(currentNode.getNodeName())) {
this.calibration = Boolean.parseBoolean(currentNode.getAttributes().getNamedItem("value").getNodeValue());
}
if ("predecessorLesionTrackingUid".equals(currentNode.getNodeName())) {
II obj = new II();
obj.setXMLNode(currentNode);
this.setPredecessorLesionTrackingUid(obj);
}
if ("comment".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setComment(obj);
}
if ("therapeuticResponse".equals(currentNode.getNodeName())) {
CD obj = new CD();
obj.setXMLNode(currentNode);
this.setTherapeuticResponse(obj);
}
if ("qualitativeAssessment".equals(currentNode.getNodeName())) {
CD obj = new CD();
obj.setXMLNode(currentNode);
this.setQualitativeAssessment(obj);
}
if ("canEvaluateLesion".equals(currentNode.getNodeName())) {
this.canEvaluateLesion = Boolean.parseBoolean(currentNode.getAttributes().getNamedItem("value").getNodeValue());
}
if ("reasonUnableToEvaluate".equals(currentNode.getNodeName())) {
CD obj = new CD();
obj.setXMLNode(currentNode);
this.setReasonUnableToEvaluate(obj);
}
if ("canMeasureLesion".equals(currentNode.getNodeName())) {
this.canMeasureLesion = Boolean.parseBoolean(currentNode.getAttributes().getNamedItem("value").getNodeValue());
}
if ("reasonUnableToMeasure".equals(currentNode.getNodeName())) {
CD obj = new CD();
obj.setXMLNode(currentNode);
this.setReasonUnableToMeasure(obj);
}
if ("isUnequivocalProgression".equals(currentNode.getNodeName())) {
this.isUnequivocalProgression = Boolean.parseBoolean(currentNode.getAttributes().getNamedItem("value").getNodeValue());
}
}
}

@Override
public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isEqualTo(Object other) {
TimePointLesionObservationEntity oth = (TimePointLesionObservationEntity) other;
if (this.calibration == null ? oth.calibration != null : !this.calibration.equals(oth.calibration)) {
return false;
}
if (this.predecessorLesionTrackingUid == null ? oth.predecessorLesionTrackingUid != null : !this.predecessorLesionTrackingUid.isEqualTo(oth.predecessorLesionTrackingUid)) {
return false;
}
if (this.comment == null ? oth.comment != null : !this.comment.isEqualTo(oth.comment)) {
return false;
}
if (this.therapeuticResponse == null ? oth.therapeuticResponse != null : !this.therapeuticResponse.isEqualTo(oth.therapeuticResponse)) {
return false;
}
if (this.qualitativeAssessment == null ? oth.qualitativeAssessment != null : !this.qualitativeAssessment.isEqualTo(oth.qualitativeAssessment)) {
return false;
}
if (this.canEvaluateLesion == null ? oth.canEvaluateLesion != null : !this.canEvaluateLesion.equals(oth.canEvaluateLesion)) {
return false;
}
if (this.reasonUnableToEvaluate == null ? oth.reasonUnableToEvaluate != null : !this.reasonUnableToEvaluate.isEqualTo(oth.reasonUnableToEvaluate)) {
return false;
}
if (this.canMeasureLesion == null ? oth.canMeasureLesion != null : !this.canMeasureLesion.equals(oth.canMeasureLesion)) {
return false;
}
if (this.reasonUnableToMeasure == null ? oth.reasonUnableToMeasure != null : !this.reasonUnableToMeasure.isEqualTo(oth.reasonUnableToMeasure)) {
return false;
}
if (this.isUnequivocalProgression == null ? oth.isUnequivocalProgression != null : !this.isUnequivocalProgression.equals(oth.isUnequivocalProgression)) {
return false;
}
return super.isEqualTo(other);
}

@Override
public TimePointLesionObservationEntity getClone() {
TimePointLesionObservationEntity res = new TimePointLesionObservationEntity();
if (this.getCalibration() != null) {
res.setCalibration(this.getCalibration());
}
if (this.getPredecessorLesionTrackingUid() != null) {
res.setPredecessorLesionTrackingUid(this.getPredecessorLesionTrackingUid().getClone());
}
if (this.getComment() != null) {
res.setComment(this.getComment().getClone());
}
if (this.getTherapeuticResponse() != null) {
res.setTherapeuticResponse(this.getTherapeuticResponse().getClone());
}
if (this.getQualitativeAssessment() != null) {
res.setQualitativeAssessment(this.getQualitativeAssessment().getClone());
}
if (this.getCanEvaluateLesion() != null) {
res.setCanEvaluateLesion(this.getCanEvaluateLesion());
}
if (this.getReasonUnableToEvaluate() != null) {
res.setReasonUnableToEvaluate(this.getReasonUnableToEvaluate().getClone());
}
if (this.getCanMeasureLesion() != null) {
res.setCanMeasureLesion(this.getCanMeasureLesion());
}
if (this.getReasonUnableToMeasure() != null) {
res.setReasonUnableToMeasure(this.getReasonUnableToMeasure().getClone());
}
if (this.getIsUnequivocalProgression() != null) {
res.setIsUnequivocalProgression(this.getIsUnequivocalProgression());
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
