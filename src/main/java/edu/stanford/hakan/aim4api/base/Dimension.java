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
public class Dimension implements IAimXMLOperations {

    private Integer index;
    private Integer size;
    private ST label;
    private String tagName;

    public Dimension() {
    }

    public Dimension(Integer index, Integer size, String label) {
        this.index = index;
        this.size = size;
        this.setLabel(new ST(label));
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public ST getLabel() {
        return label;
    }

    public void setLabel(ST label) {
        label.setTagName("label");
        this.label = label;
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
            setTagName("Dimension");
        }
        Element res = doc.createElement(getTagName());
        if (this.index != null) {
            Element el_index = doc.createElement("index");
            el_index.setAttribute("value", this.index.toString());
            res.appendChild(el_index);
        }
        if (this.size != null) {
            Element el_size = doc.createElement("size");
            el_size.setAttribute("value", this.size.toString());
            res.appendChild(el_size);
        }
        if (this.label != null) {
            res.appendChild(this.label.getXMLNode(doc));
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("index".equals(currentNode.getNodeName())) {
                this.index = Integer.parseInt(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("size".equals(currentNode.getNodeName())) {
                this.size = Integer.parseInt(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("label".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setLabel(obj);
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        Dimension oth = (Dimension) other;
        if (this.index == null ? oth.index != null : !this.index.equals(oth.index)) {
            return false;
        }
        if (this.size == null ? oth.size != null : !this.size.equals(oth.size)) {
            return false;
        }
        if (this.label == null ? oth.label != null : !this.label.isEqualTo(oth.label)) {
            return false;
        }
        return true;
    }

    public Dimension getClone() {
        Dimension res = new Dimension();
        if (this.getIndex() != null) {
            res.setIndex(this.getIndex());
        }
        if (this.getSize() != null) {
            res.setSize(this.getSize());
        }
        if (this.getLabel() != null) {
            res.setLabel(this.getLabel().getClone());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        return res;
    }
}
