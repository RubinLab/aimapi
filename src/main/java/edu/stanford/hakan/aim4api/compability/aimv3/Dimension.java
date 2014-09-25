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
package edu.stanford.hakan.aim4api.compability.aimv3;


import edu.stanford.hakan.aim4api.base.AimException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan BULU
 */
public class Dimension implements IAimXMLOperations {
    
    private Integer cagridId;
    private Integer index;
    private Integer size;
    private String label;
    
    public Dimension() {
    }
    
    public Dimension(Integer cagridId, Integer index, Integer size, String label) {
        this.cagridId = cagridId;
        this.index = index;
        this.size = size;
        this.label = label;
    }
    
    public Integer getCagridId() {
        return cagridId;
    }
    
    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }
    
    public Integer getIndex() {
        return index;
    }
    
    public void setIndex(Integer index) {
        this.index = index;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public Integer getSize() {
        return size;
    }
    
    public void setSize(Integer size) {
        this.size = size;
    }
    
    @Override
    public Node getXMLNode(Document doc) throws AimException {
        
        this.Control();
        
        Element dimension = doc.createElement("Dimension");
        dimension.setAttribute("cagridId", this.cagridId.toString());
        dimension.setAttribute("index", this.index.toString());
        dimension.setAttribute("size", this.size.toString());
        dimension.setAttribute("label", this.label);
        return dimension;
    }
    
    @Override
    public void setXMLNode(Node node) {
        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.index = Integer.parseInt(map.getNamedItem("index").getNodeValue());
        this.size = Integer.parseInt(map.getNamedItem("size").getNodeValue());
        this.label = map.getNamedItem("label").getNodeValue();
    }   
    
    
    private void Control() throws AimException {
        if (getCagridId() == null) {
            throw new AimException("AimException: Dimension's cagridId can not be null");
        }
        if (getIndex() == null) {
            throw new AimException("AimException: Dimension's index can not be null");
        }
        if (getSize() == null) {
            throw new AimException("AimException: Dimension's size can not be null");
        }
        if (getLabel() == null) {
            throw new AimException("AimException: Dimension's label can not be null");
        }
    }
    
    public boolean isEqualTo(Object other) {
        Dimension oth = (Dimension) other;
        if (this.cagridId != oth.cagridId) {
            return false;
        }
        if (this.index == null ? oth.index != null : !this.index.equals(oth.index)) {
            return false;
        }
        if (this.size == null ? oth.size != null : !this.size.equals(oth.size)) {
            return false;
        }
        if (this.label == null ? oth.label != null : !this.label.equals(oth.label)) {
            return false;
        }
        return true;
    }
    
    public edu.stanford.hakan.aim4api.base.Dimension toAimV4() {
        edu.stanford.hakan.aim4api.base.Dimension res = new edu.stanford.hakan.aim4api.base.Dimension();
        res.setIndex(this.getIndex());
        res.setLabel(Converter.toST(this.getLabel()));
        res.setSize(this.getSize());
        return res;
    }
    
    public Dimension(edu.stanford.hakan.aim4api.base.Dimension v4) {
        this.setCagridId(0);
        this.setIndex(v4.getIndex());
        if (v4.getLabel() != null) {
            this.setLabel(v4.getLabel().getValue());
        }
        this.setSize(v4.getSize());
    }
}
