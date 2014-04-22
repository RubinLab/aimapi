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
import org.w3c.dom.NodeList;

/**
*
* @author localadmin
*/
public class TextAnnotationEntity extends MarkupEntity {

public TextAnnotationEntity() {
setXsiType("TextAnnotationEntity");
}
private ST text;
private ST font;
private ST fontColor;
private ST fontEffect;
private ST fontSize;
private ST fontStyle;
private ST textJustify;
private ST fontOpacity;
private GeometricShapeEntity geometricShapeEntity;

public ST getText() {
return text;
}

public void setText(ST text) {
text.setTagName("text");
this.text = text;
}

public ST getFont() {
return font;
}

public void setFont(ST font) {
font.setTagName("font");
this.font = font;
}

public ST getFontColor() {
return fontColor;
}

public void setFontColor(ST fontColor) {
fontColor.setTagName("fontColor");
this.fontColor = fontColor;
}

public ST getFontEffect() {
return fontEffect;
}

public void setFontEffect(ST fontEffect) {
fontEffect.setTagName("fontEffect");
this.fontEffect = fontEffect;
}

public ST getFontSize() {
return fontSize;
}

public void setFontSize(ST fontSize) {
fontSize.setTagName("fontSize");
this.fontSize = fontSize;
}

public ST getFontStyle() {
return fontStyle;
}

public void setFontStyle(ST fontStyle) {
fontStyle.setTagName("fontStyle");
this.fontStyle = fontStyle;
}

public ST getTextJustify() {
return textJustify;
}

public void setTextJustify(ST textJustify) {
textJustify.setTagName("textJustify");
this.textJustify = textJustify;
}

public ST getFontOpacity() {
return fontOpacity;
}

public void setFontOpacity(ST fontOpacity) {
fontOpacity.setTagName("fontOpacity");
this.fontOpacity = fontOpacity;
}

public GeometricShapeEntity getGeometricShapeEntity() {
return geometricShapeEntity;
}

public void setGeometricShapeEntity(GeometricShapeEntity geometricShapeEntity) {
geometricShapeEntity.setTagName("geometricShapeEntity");
this.geometricShapeEntity = geometricShapeEntity;
}

@Override
public Node getXMLNode(Document doc) throws AimException {
if (getTagName() == null || "".equals(getTagName())) {
setTagName("TextAnnotationEntity");
}
Element res = (Element) super.getXMLNode(doc);
if (this.text != null) {
res.appendChild(this.text.getXMLNode(doc));
}
if (this.font != null) {
res.appendChild(this.font.getXMLNode(doc));
}
if (this.fontColor != null) {
res.appendChild(this.fontColor.getXMLNode(doc));
}
if (this.fontEffect != null) {
res.appendChild(this.fontEffect.getXMLNode(doc));
}
if (this.fontSize != null) {
res.appendChild(this.fontSize.getXMLNode(doc));
}
if (this.fontStyle != null) {
res.appendChild(this.fontStyle.getXMLNode(doc));
}
if (this.textJustify != null) {
res.appendChild(this.textJustify.getXMLNode(doc));
}
if (this.fontOpacity != null) {
res.appendChild(this.fontOpacity.getXMLNode(doc));
}
if (this.geometricShapeEntity != null) {
res.appendChild(this.geometricShapeEntity.getXMLNode(doc));
}
return res;
}

@Override
public void setXMLNode(Node node) {
super.setXMLNode(node);
NodeList listChilds = node.getChildNodes();
for (int i = 0; i < listChilds.getLength(); i++) {
Node currentNode = listChilds.item(i);
if ("text".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setText(obj);
}
if ("font".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setFont(obj);
}
if ("fontColor".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setFontColor(obj);
}
if ("fontEffect".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setFontEffect(obj);
}
if ("fontSize".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setFontSize(obj);
}
if ("fontStyle".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setFontStyle(obj);
}
if ("textJustify".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setTextJustify(obj);
}
if ("fontOpacity".equals(currentNode.getNodeName())) {
ST obj = new ST();
obj.setXMLNode(currentNode);
this.setFontOpacity(obj);
}
if ("geometricShapeEntity".equals(currentNode.getNodeName())) {
NamedNodeMap mapXsiType = currentNode.getAttributes();
if (mapXsiType.getNamedItem("xsi:type") != null) {
String xsiType = mapXsiType.getNamedItem("xsi:type").getNodeValue();
if ("ThreeDimensionGeometricShapeEntity".equals(xsiType)) {
ThreeDimensionGeometricShapeEntity obj = new ThreeDimensionGeometricShapeEntity();
obj.setXMLNode(currentNode);
this.setGeometricShapeEntity(obj);
}
if ("ThreeDimensionPoint".equals(xsiType)) {
ThreeDimensionPoint obj = new ThreeDimensionPoint();
obj.setXMLNode(currentNode);
this.setGeometricShapeEntity(obj);
}
if ("ThreeDimensionPolygon".equals(xsiType)) {
ThreeDimensionPolygon obj = new ThreeDimensionPolygon();
obj.setXMLNode(currentNode);
this.setGeometricShapeEntity(obj);
}
if ("ThreeDimensionPolyline".equals(xsiType)) {
ThreeDimensionPolyline obj = new ThreeDimensionPolyline();
obj.setXMLNode(currentNode);
this.setGeometricShapeEntity(obj);
}
if ("ThreeDimensionEllipse".equals(xsiType)) {
ThreeDimensionEllipse obj = new ThreeDimensionEllipse();
obj.setXMLNode(currentNode);
this.setGeometricShapeEntity(obj);
}
if ("ThreeDimensionEllipsoid".equals(xsiType)) {
ThreeDimensionEllipsoid obj = new ThreeDimensionEllipsoid();
obj.setXMLNode(currentNode);
this.setGeometricShapeEntity(obj);
}
if ("ThreeDimensionMultiPoint".equals(xsiType)) {
ThreeDimensionMultiPoint obj = new ThreeDimensionMultiPoint();
obj.setXMLNode(currentNode);
this.setGeometricShapeEntity(obj);
}
if ("TwoDimensionGeometricShapeEntity".equals(xsiType)) {
TwoDimensionGeometricShapeEntity obj = new TwoDimensionGeometricShapeEntity();
obj.setXMLNode(currentNode);
this.setGeometricShapeEntity(obj);
}
if ("TwoDimensionMultiPoint".equals(xsiType)) {
TwoDimensionMultiPoint obj = new TwoDimensionMultiPoint();
obj.setXMLNode(currentNode);
this.setGeometricShapeEntity(obj);
}
if ("TwoDimensionPoint".equals(xsiType)) {
TwoDimensionPoint obj = new TwoDimensionPoint();
obj.setXMLNode(currentNode);
this.setGeometricShapeEntity(obj);
}
if ("TwoDimensionCircle".equals(xsiType)) {
TwoDimensionCircle obj = new TwoDimensionCircle();
obj.setXMLNode(currentNode);
this.setGeometricShapeEntity(obj);
}
if ("TwoDimensionEllipse".equals(xsiType)) {
TwoDimensionEllipse obj = new TwoDimensionEllipse();
obj.setXMLNode(currentNode);
this.setGeometricShapeEntity(obj);
}
if ("TwoDimensionPolyline".equals(xsiType)) {
TwoDimensionPolyline obj = new TwoDimensionPolyline();
obj.setXMLNode(currentNode);
this.setGeometricShapeEntity(obj);
}
}
}
}
}

@Override
public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isEqualTo(Object other) {
TextAnnotationEntity oth = (TextAnnotationEntity) other;
if (this.text == null ? oth.text != null : !this.text.isEqualTo(oth.text)) {
return false;
}
if (this.font == null ? oth.font != null : !this.font.isEqualTo(oth.font)) {
return false;
}
if (this.fontColor == null ? oth.fontColor != null : !this.fontColor.isEqualTo(oth.fontColor)) {
return false;
}
if (this.fontEffect == null ? oth.fontEffect != null : !this.fontEffect.isEqualTo(oth.fontEffect)) {
return false;
}
if (this.fontSize == null ? oth.fontSize != null : !this.fontSize.isEqualTo(oth.fontSize)) {
return false;
}
if (this.fontStyle == null ? oth.fontStyle != null : !this.fontStyle.isEqualTo(oth.fontStyle)) {
return false;
}
if (this.textJustify == null ? oth.textJustify != null : !this.textJustify.isEqualTo(oth.textJustify)) {
return false;
}
if (this.fontOpacity == null ? oth.fontOpacity != null : !this.fontOpacity.isEqualTo(oth.fontOpacity)) {
return false;
}
if (this.geometricShapeEntity == null ? oth.geometricShapeEntity != null : !this.geometricShapeEntity.isEqualTo(oth.geometricShapeEntity)) {
return false;
}
return super.isEqualTo(other);
}

@Override
public TextAnnotationEntity getClone() {
TextAnnotationEntity res = new TextAnnotationEntity();
if (this.getText() != null) {
res.setText(this.getText().getClone());
}
if (this.getFont() != null) {
res.setFont(this.getFont().getClone());
}
if (this.getFontColor() != null) {
res.setFontColor(this.getFontColor().getClone());
}
if (this.getFontEffect() != null) {
res.setFontEffect(this.getFontEffect().getClone());
}
if (this.getFontSize() != null) {
res.setFontSize(this.getFontSize().getClone());
}
if (this.getFontStyle() != null) {
res.setFontStyle(this.getFontStyle().getClone());
}
if (this.getTextJustify() != null) {
res.setTextJustify(this.getTextJustify().getClone());
}
if (this.getFontOpacity() != null) {
res.setFontOpacity(this.getFontOpacity().getClone());
}
if (this.getGeometricShapeEntity() != null) {
res.setGeometricShapeEntity(this.getGeometricShapeEntity().getClone());
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
