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

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class GeometricShapeEntity extends MarkupEntity {

    public GeometricShapeEntity() {
        setXsiType("GeometricShapeEntity");
    }
    private List<CD> listQuestionTypeCode = new ArrayList<CD>();
    private Integer shapeIdentifier;
    private ST label;
    private ST description;
    private Boolean includeFlag;
    private ST comment;
    private ST lineColor;
    private ST lineOpacity;
    private ST lineStyle;
    private ST lineThickness;
    private Integer questionIndex;
    private CD interpolationMethod;

    public List<CD> getListQuestionTypeCode() {
        return this.listQuestionTypeCode;
    }

    public void setQuestionTypeCode(List<CD> listQuestionTypeCode) {
        this.listQuestionTypeCode = listQuestionTypeCode;
        for (int i = 0; i < this.listQuestionTypeCode.size(); i++) {
            this.listQuestionTypeCode.get(i).setTagName("questionTypeCode");
        }
    }

    public void addQuestionTypeCode(CD newQuestionTypeCode) {
        newQuestionTypeCode.setTagName("questionTypeCode");
        this.listQuestionTypeCode.add(newQuestionTypeCode);
    }

    public Integer getShapeIdentifier() {
        return shapeIdentifier;
    }

    public void setShapeIdentifier(Integer shapeIdentifier) {
        this.shapeIdentifier = shapeIdentifier;
    }

    public ST getLabel() {
        return label;
    }

    public void setLabel(ST label) {
        label.setTagName("label");
        this.label = label;
    }

    public ST getDescription() {
        return description;
    }

    public void setDescription(ST description) {
        description.setTagName("description");
        this.description = description;
    }

    public Boolean getIncludeFlag() {
        return includeFlag;
    }

    public void setIncludeFlag(Boolean includeFlag) {
        this.includeFlag = includeFlag;
    }

    public ST getComment() {
        return comment;
    }

    public void setComment(ST comment) {
        comment.setTagName("comment");
        this.comment = comment;
    }

    public ST getLineColor() {
        return lineColor;
    }

    public void setLineColor(ST lineColor) {
        lineColor.setTagName("lineColor");
        this.lineColor = lineColor;
    }

    public ST getLineOpacity() {
        return lineOpacity;
    }

    public void setLineOpacity(ST lineOpacity) {
        lineOpacity.setTagName("lineOpacity");
        this.lineOpacity = lineOpacity;
    }

    public ST getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(ST lineStyle) {
        lineStyle.setTagName("lineStyle");
        this.lineStyle = lineStyle;
    }

    public ST getLineThickness() {
        return lineThickness;
    }

    public void setLineThickness(ST lineThickness) {
        lineThickness.setTagName("lineThickness");
        this.lineThickness = lineThickness;
    }

    public Integer getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(Integer questionIndex) {
        this.questionIndex = questionIndex;
    }

    public CD getInterpolationMethod() {
        return interpolationMethod;
    }

    public void setInterpolationMethod(CD interpolationMethod) {
        interpolationMethod.setTagName("interpolationMethod");
        this.interpolationMethod = interpolationMethod;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("GeometricShapeEntity");
        }
        Element res = (Element) super.getXMLNode(doc);
        if (this.listQuestionTypeCode.size() > 0) {
            for (int i = 0; i < this.listQuestionTypeCode.size(); i++) {
                res.appendChild(this.listQuestionTypeCode.get(i).getXMLNode(doc));
            }
        }
        if (this.shapeIdentifier != null) {
            Element el_shapeIdentifier = doc.createElement("shapeIdentifier");
            el_shapeIdentifier.setAttribute("value", this.shapeIdentifier.toString());
            res.appendChild(el_shapeIdentifier);
        }
        if (this.label != null) {
            res.appendChild(this.label.getXMLNode(doc));
        }
        if (this.description != null) {
            res.appendChild(this.description.getXMLNode(doc));
        }
        if (this.includeFlag != null) {
            Element el_includeFlag = doc.createElement("includeFlag");
            el_includeFlag.setAttribute("value", this.includeFlag.toString());
            res.appendChild(el_includeFlag);
        }
        if (this.comment != null) {
            res.appendChild(this.comment.getXMLNode(doc));
        }
        if (this.lineColor != null) {
            res.appendChild(this.lineColor.getXMLNode(doc));
        }
        if (this.lineOpacity != null) {
            res.appendChild(this.lineOpacity.getXMLNode(doc));
        }
        if (this.lineStyle != null) {
            res.appendChild(this.lineStyle.getXMLNode(doc));
        }
        if (this.lineThickness != null) {
            res.appendChild(this.lineThickness.getXMLNode(doc));
        }
        if (this.questionIndex != null) {
            Element el_questionIndex = doc.createElement("questionIndex");
            el_questionIndex.setAttribute("value", this.questionIndex.toString());
            res.appendChild(el_questionIndex);
        }
        if (this.interpolationMethod != null) {
            res.appendChild(this.interpolationMethod.getXMLNode(doc));
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
        this.listQuestionTypeCode.clear();
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("questionTypeCode".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.addQuestionTypeCode(obj);
            }
            if ("shapeIdentifier".equals(currentNode.getNodeName())) {
                this.shapeIdentifier = Integer.parseInt(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("label".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setLabel(obj);
            }
            if ("description".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setDescription(obj);
            }
            if ("includeFlag".equals(currentNode.getNodeName())) {
                this.includeFlag = Boolean.parseBoolean(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("comment".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setComment(obj);
            }
            if ("lineColor".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setLineColor(obj);
            }
            if ("lineOpacity".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setLineOpacity(obj);
            }
            if ("lineStyle".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setLineStyle(obj);
            }
            if ("lineThickness".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setLineThickness(obj);
            }
            if ("questionIndex".equals(currentNode.getNodeName())) {
                this.questionIndex = Integer.parseInt(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("interpolationMethod".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.setInterpolationMethod(obj);
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools
// | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        GeometricShapeEntity oth = (GeometricShapeEntity) other;
        if (this.listQuestionTypeCode.size() != oth.listQuestionTypeCode.size()) {
            return false;
        }
        for (int i = 0; i < this.listQuestionTypeCode.size(); i++) {
            if (this.listQuestionTypeCode.get(i) == null ? oth.listQuestionTypeCode.get(i) != null : !this.listQuestionTypeCode.get(i).isEqualTo(oth.listQuestionTypeCode.get(i))) {
                return false;
            }
        }
        if (this.shapeIdentifier == null ? oth.shapeIdentifier != null : !this.shapeIdentifier.equals(oth.shapeIdentifier)) {
            return false;
        }
        if (this.label == null ? oth.label != null : !this.label.isEqualTo(oth.label)) {
            return false;
        }
        if (this.description == null ? oth.description != null : !this.description.isEqualTo(oth.description)) {
            return false;
        }
        if (this.includeFlag == null ? oth.includeFlag != null : !this.includeFlag.equals(oth.includeFlag)) {
            return false;
        }
        if (this.comment == null ? oth.comment != null : !this.comment.isEqualTo(oth.comment)) {
            return false;
        }
        if (this.lineColor == null ? oth.lineColor != null : !this.lineColor.isEqualTo(oth.lineColor)) {
            return false;
        }
        if (this.lineOpacity == null ? oth.lineOpacity != null : !this.lineOpacity.isEqualTo(oth.lineOpacity)) {
            return false;
        }
        if (this.lineStyle == null ? oth.lineStyle != null : !this.lineStyle.isEqualTo(oth.lineStyle)) {
            return false;
        }
        if (this.lineThickness == null ? oth.lineThickness != null : !this.lineThickness.isEqualTo(oth.lineThickness)) {
            return false;
        }
        if (this.questionIndex == null ? oth.questionIndex != null : !this.questionIndex.equals(oth.questionIndex)) {
            return false;
        }
        if (this.interpolationMethod == null ? oth.interpolationMethod != null : !this.interpolationMethod.isEqualTo(oth.interpolationMethod)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    @Override
    public GeometricShapeEntity getClone() {
        GeometricShapeEntity res = new GeometricShapeEntity();
        for (int i = 0; i < this.listQuestionTypeCode.size(); i++) {
            if (this.listQuestionTypeCode.get(i) != null) {
                res.addQuestionTypeCode(this.listQuestionTypeCode.get(i).getClone());
            }
        }
        if (this.getShapeIdentifier() != null) {
            res.setShapeIdentifier(this.getShapeIdentifier());
        }
        if (this.getLabel() != null) {
            res.setLabel(this.getLabel().getClone());
        }
        if (this.getDescription() != null) {
            res.setDescription(this.getDescription().getClone());
        }
        if (this.getIncludeFlag() != null) {
            res.setIncludeFlag(this.getIncludeFlag());
        }
        if (this.getComment() != null) {
            res.setComment(this.getComment().getClone());
        }
        if (this.getLineColor() != null) {
            res.setLineColor(this.getLineColor().getClone());
        }
        if (this.getLineOpacity() != null) {
            res.setLineOpacity(this.getLineOpacity().getClone());
        }
        if (this.getLineStyle() != null) {
            res.setLineStyle(this.getLineStyle().getClone());
        }
        if (this.getLineThickness() != null) {
            res.setLineThickness(this.getLineThickness().getClone());
        }
        if (this.getQuestionIndex() != null) {
            res.setQuestionIndex(this.getQuestionIndex());
        }
        if (this.getInterpolationMethod() != null) {
            res.setInterpolationMethod(this.getInterpolationMethod().getClone());
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
