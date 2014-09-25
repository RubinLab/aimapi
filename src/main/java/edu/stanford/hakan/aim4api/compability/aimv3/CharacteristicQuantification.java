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
public class CharacteristicQuantification implements ICharacteristicQuantification, IAimXMLOperations {

    private Integer cagridId;
    private String name;
    private Double annotatorConfidence;
    private String xsiType;

    protected void setXsiType(String xsiType) {
        this.xsiType = xsiType;
    }

    public String getXsiType() {
        return xsiType;
    }

    @Override
    public Double getAnnotatorConfidence() {
        return annotatorConfidence;
    }

    @Override
    public void setAnnotatorConfidence(Double annotatorConfidence) {
        this.annotatorConfidence = annotatorConfidence;
    }

    @Override
    public Integer getCagridId() {
        return cagridId;
    }

    @Override
    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        this.Control();

        Element characteristicQuantification = doc.createElement("CharacteristicQuantification");
        characteristicQuantification.setAttribute("cagridId", this.cagridId.toString());
        characteristicQuantification.setAttribute("name", this.name);
        if (this.annotatorConfidence != null) {
            characteristicQuantification.setAttribute("annotatorConfidence", this.annotatorConfidence.toString());
        }
        characteristicQuantification.setAttribute("xsi:type", this.xsiType);

        return characteristicQuantification;
    }

    @Override
    public void setXMLNode(Node node) {

        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.name = map.getNamedItem("name").getNodeValue();
        if (map.getNamedItem("annotatorConfidence") != null) {
            this.annotatorConfidence = Double.parseDouble(map.getNamedItem("annotatorConfidence").getNodeValue());
        }
    }   

    private void Control() throws AimException {
        if (getCagridId() == null) {
            throw new AimException("AimException: CharacteristicQuantification's cagridId can not be null");
        }
        if (getName() == null) {
            throw new AimException("AimException: CharacteristicQuantification's name can not be null");
        }
    }

    public boolean isEqualTo(Object other) {
        CharacteristicQuantification oth = (CharacteristicQuantification) other;
        if (this.cagridId != oth.cagridId) {
            return false;
        }
        if (this.name == null ? oth.name != null : !this.name.equals(oth.name)) {
            return false;
        }
        if (this.annotatorConfidence == null ? oth.annotatorConfidence != null : !this.annotatorConfidence.equals(oth.annotatorConfidence)) {
            return false;
        }
        return true;
    }

    public edu.stanford.hakan.aim4api.base.CharacteristicQuantification toAimV4() {
        edu.stanford.hakan.aim4api.base.CharacteristicQuantification res = new edu.stanford.hakan.aim4api.base.CharacteristicQuantification();
        res.setAnnotatorConfidence(this.getAnnotatorConfidence());//
        res.setLabel(Converter.toST(this.getName()));//
        return res;
    }

    public Numerical toNumerical() {
        return ((Numerical) this);
    }

    public Interval toInterval() {
        return ((Interval) this);
    }

    public Quantile toQuantile() {
        return ((Quantile) this);
    }

    public Scale toScale() {
        return ((Scale) this);
    }

    public NonQuantifiable toNonQuantifiable() {
        return ((NonQuantifiable) this);
    }

    public CharacteristicQuantification(edu.stanford.hakan.aim4api.base.CharacteristicQuantification v4) {
        this.setCagridId(0);
        this.setAnnotatorConfidence(v4.getAnnotatorConfidence());
        if (v4.getLabel() != null) {
            this.setName(v4.getLabel().getValue());
        }
    }

    public CharacteristicQuantification() {

    }
}
