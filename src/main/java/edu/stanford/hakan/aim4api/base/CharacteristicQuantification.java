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
public class CharacteristicQuantification implements IAimXMLOperations {

private Double annotatorConfidence;
private Integer characteristicQuantificationIndex;
private ST label;
private ST valueLabel;
private ST valueDescription;
private ST comment;
private String tagName;
private String xsiType;

public Double getAnnotatorConfidence() {
return annotatorConfidence;
}

public void setAnnotatorConfidence(Double annotatorConfidence) {
this.annotatorConfidence = annotatorConfidence;
}

public Integer getCharacteristicQuantificationIndex() {
return characteristicQuantificationIndex;
}

public void setCharacteristicQuantificationIndex(Integer characteristicQuantificationIndex) {
this.characteristicQuantificationIndex = characteristicQuantificationIndex;
}

public ST getLabel() {
return label;
}

public void setLabel(ST label) {
label.setTagName("label");
this.label = label;
}

public ST getValueLabel() {
return valueLabel;
}

public void setValueLabel(ST valueLabel) {
valueLabel.setTagName("valueLabel");
this.valueLabel = valueLabel;
}

public ST getValueDescription() {
return valueDescription;
}

public void setValueDescription(ST valueDescription) {
valueDescription.setTagName("valueDescription");
this.valueDescription = valueDescription;
}

public ST getComment() {
return comment;
}

public void setComment(ST comment) {
comment.setTagName("comment");
this.comment = comment;
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
setTagName("CharacteristicQuantification");
}
Element res = doc.createElement(getTagName());
if (this.annotatorConfidence != null) {
Element el_annotatorConfidence = doc.createElement("annotatorConfidence");
el_annotatorConfidence.setAttribute("value", this.annotatorConfidence.toString());
res.appendChild(el_annotatorConfidence);
}
if (this.characteristicQuantificationIndex != null) {
Element el_characteristicQuantificationIndex = doc.createElement("characteristicQuantificationIndex");
el_characteristicQuantificationIndex.setAttribute("value", this.characteristicQuantificationIndex.toString());
res.appendChild(el_characteristicQuantificationIndex);
}
if (this.label != null) {
res.appendChild(this.label.getXMLNode(doc));
}
if (this.valueLabel != null) {
res.appendChild(this.valueLabel.getXMLNode(doc));
}
if (this.valueDescription != null) {
res.appendChild(this.valueDescription.getXMLNode(doc));
}
if (this.comment != null) {
res.appendChild(this.comment.getXMLNode(doc));
}
if (this.xsiType != null && (this.xsiType == null ? this.getTagName() != null : !this.xsiType.equals(this.getTagName()))) {
res.setAttribute("xsi:type", this.xsiType.toString());
}
return res;
}

@Override
public void setXMLNode(Node node) {
NamedNodeMap map = node.getAttributes();
if (map.getNamedItem("xsiType") != null) {
this.xsiType = map.getNamedItem("xsiType").getNodeValue();
}
NodeList listChilds = node.getChildNodes();
for (int i = 0; i < listChilds.getLength(); i++) {
Node currentNode = listChilds.item(i);
if ("annotatorConfidence".equals(currentNode.getNodeName())) {
this.annotatorConfidence = Double.parseDouble(currentNode.getAttributes().getNamedItem("value").getNodeValue());
}
if ("characteristicQuantificationIndex".equals(currentNode.getNodeName())) {
this.characteristicQuantificationIndex = Integer.parseInt(currentNode.getAttributes().getNamedItem("value").getNodeValue());
}
if ("label".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setLabel(obj);
}
if ("valueLabel".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setValueLabel(obj);
}
if ("valueDescription".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setValueDescription(obj);
}
if ("comment".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setComment(obj);
}
}
}

@Override
public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isEqualTo(Object other) {
CharacteristicQuantification oth = (CharacteristicQuantification) other;
if (this.annotatorConfidence == null ? oth.annotatorConfidence != null : !this.annotatorConfidence.equals(oth.annotatorConfidence)) {
return false;
}
if (this.characteristicQuantificationIndex == null ? oth.characteristicQuantificationIndex != null : !this.characteristicQuantificationIndex.equals(oth.characteristicQuantificationIndex)) {
return false;
}
if (this.label == null ? oth.label != null : !this.label.isEqualTo(oth.label)) {
return false;
}
if (this.valueLabel == null ? oth.valueLabel != null : !this.valueLabel.isEqualTo(oth.valueLabel)) {
return false;
}
if (this.valueDescription == null ? oth.valueDescription != null : !this.valueDescription.isEqualTo(oth.valueDescription)) {
return false;
}
if (this.comment == null ? oth.comment != null : !this.comment.isEqualTo(oth.comment)) {
return false;
}
return true;
}

public CharacteristicQuantification getClone() {
CharacteristicQuantification res = new CharacteristicQuantification();
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
