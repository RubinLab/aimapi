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
public class Quantile extends CharacteristicQuantification implements IAimXMLOperations {
    
    private Integer bin;
    
    public Quantile() {
        setXsiType("Quantile");
    }
    
    public Quantile(Integer cagridId, String name, Double annotatorConfidence, Integer bin) {
        setCagridId(cagridId);
        setName(name);
        setAnnotatorConfidence(annotatorConfidence);
        this.bin = bin;
        setXsiType("Quantile");
    }
    
    public Integer getBin() {
        return bin;
    }
    
    public void setBin(Integer bin) {
        this.bin = bin;
    }
    
//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//        
//        this.Control();
//        
//        Element characteristicQuantification = (Element) super.getXMLNode(doc);
//        characteristicQuantification.setAttribute("bin", this.getBin().toString());
//        return characteristicQuantification;
//    }
    
    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
        
        NamedNodeMap map = node.getAttributes();
        this.bin = Integer.parseInt(map.getNamedItem("bin").getNodeValue());
    }
    
        
    private void Control() throws AimException {
        if (this.getBin() == null) {
            throw new AimException("AimException: Quantile's bin can not be null");
        }
    }
    
    @Override
    public boolean isEqualTo(Object other) {
        Quantile oth = (Quantile) other;
        if (this.bin == null ? oth.bin != null : !this.bin.equals(oth.bin)) {
            return false;
        }
        return super.isEqualTo(other);
    }
    
    @Override
    public edu.stanford.hakan.aim4api.base.CharacteristicQuantification toAimV4() {
        edu.stanford.hakan.aim4api.base.Quantile res = new edu.stanford.hakan.aim4api.base.Quantile();
        res.setAnnotatorConfidence(this.getAnnotatorConfidence());//
        res.setBins(this.getBin());
        res.setComment(Converter.toST(this.getName()));//
        return res;
    }
    
    public Quantile(edu.stanford.hakan.aim4api.base.Quantile v4) {
        setXsiType("Quantile");
        this.setCagridId(0);
        this.setAnnotatorConfidence(v4.getAnnotatorConfidence());
        if (v4.getComment() != null) {
            this.setName(v4.getComment().getValue());
        }
        this.setBin(v4.getBins());
    }
}
