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
package edu.stanford.hakan.aim4api.compability.aimv3_old;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hakan BULU
 */
public class TextAnnotation {

    public Integer cagridId;
    private String font;
    private String fontColor;
    private String fontEffect;
    private String fontSize;
    private String fontStyle;
    private String text;
    private String textJustify;
    private String fontOpacity;
    private List<MultiPoint> listMultiPoint;

    public TextAnnotation() {
        this.listMultiPoint = new ArrayList<MultiPoint>();
    }

    public TextAnnotation(Integer cagridId, String font, String fontColor, String fontEffect, String fontSize, String fontStyle, String text, String textJustify, String fontOpacity) {
        this.cagridId = cagridId;
        this.font = font;
        this.fontColor = fontColor;
        this.fontEffect = fontEffect;
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
        this.text = text;
        this.textJustify = textJustify;
        this.fontOpacity = fontOpacity;
        this.listMultiPoint = new ArrayList<MultiPoint>();
    }

    public Integer getCagridId() {
        return cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getFontEffect() {
        return fontEffect;
    }

    public void setFontEffect(String fontEffect) {
        this.fontEffect = fontEffect;
    }

    public String getFontOpacity() {
        return fontOpacity;
    }

    public void setFontOpacity(String fontOpacity) {
        this.fontOpacity = fontOpacity;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public List<MultiPoint> getListMultiPoint() {
        return listMultiPoint;
    }

    public void setListMultiPoint(List<MultiPoint> listMultiPoint) {
        this.listMultiPoint = listMultiPoint;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextJustify() {
        return textJustify;
    }

    public void setTextJustify(String textJustify) {
        this.textJustify = textJustify;
    }

    public void addMultiPoint(MultiPoint newMultiPoint) {
        this.listMultiPoint.add(newMultiPoint);
    }

    public void addMultiPoint(Integer cagridId, String lineColor, String lineOpacity, String lineStyle, String lineThickness, Boolean includeFlag, Integer shapeIdentifier) {
        MultiPoint newMultiPoint = new MultiPoint(cagridId, lineColor, lineOpacity, lineStyle, lineThickness, includeFlag, shapeIdentifier);
        this.listMultiPoint.add(newMultiPoint);
    }

    public void setXMLNode(Node node) {

        this.listMultiPoint.clear();
        NodeList listChils = node.getChildNodes();
        for (int i = 0; i < listChils.getLength(); i++) {
            if ("multiPoint".equals(listChils.item(i).getNodeName())) {
                NodeList tempList = listChils.item(i).getChildNodes();
                for (int j = 0; j < tempList.getLength(); j++) {
                    if ("MultiPoint".equals(tempList.item(j).getNodeName())) {
                        MultiPoint obj = new MultiPoint();
                        obj.setXMLNode(tempList.item(j));
                        this.addMultiPoint(obj);
                    }
                }
            }
        }

        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.text = map.getNamedItem("text").getNodeValue();
        if (map.getNamedItem("font") != null) {
            this.font = map.getNamedItem("font").getNodeValue();
        }
        if (map.getNamedItem("fontColor") != null) {
            this.fontColor = map.getNamedItem("fontColor").getNodeValue();
        }
        if (map.getNamedItem("fontEffect") != null) {
            this.fontEffect = map.getNamedItem("fontEffect").getNodeValue();
        }
        if (map.getNamedItem("fontSize") != null) {
            this.fontSize = map.getNamedItem("fontSize").getNodeValue();
        }
        if (map.getNamedItem("fontStyle") != null) {
            this.fontStyle = map.getNamedItem("fontStyle").getNodeValue();
        }
        if (map.getNamedItem("textJustify") != null) {
            this.textJustify = map.getNamedItem("textJustify").getNodeValue();
        }
        if (map.getNamedItem("fontOpacity") != null) {
            this.fontOpacity = map.getNamedItem("fontOpacity").getNodeValue();
        }
    }
}
