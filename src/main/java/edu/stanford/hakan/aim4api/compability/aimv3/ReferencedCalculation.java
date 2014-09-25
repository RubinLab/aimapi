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
 * @author pc
 */
public class ReferencedCalculation implements IAimXMLOperations {

    private Integer cagridId;
    private String uniqueIdentifier;

    public ReferencedCalculation() {
    }

    public ReferencedCalculation(int cagridId, String uniqueIdentifier) {
        this.cagridId = cagridId;
        this.uniqueIdentifier = uniqueIdentifier;

    }

    public Integer getCagridId() {
        return cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        this.Control();

        Element referencedCalculation = doc.createElement("ReferencedCalculation");
        referencedCalculation.setAttribute("cagridId", Integer.toString(this.cagridId));
        referencedCalculation.setAttribute("uniqueIdentifier", this.uniqueIdentifier);
        return referencedCalculation;
    }

    @Override
    public void setXMLNode(Node node) {

        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.uniqueIdentifier = map.getNamedItem("uniqueIdentifier").getNodeValue();

    }

   
    private void Control() throws AimException {
        if (getCagridId() == null) {
            throw new AimException("AimException: ReferencedCalculation's cagridId can not be null");
        }
        if (getUniqueIdentifier() == null) {
            throw new AimException("AimException: ReferencedCalculation's uniqueIdentifier can not be null");
        }
    }

    public boolean isEqualTo(Object other) {
        ReferencedCalculation oth = (ReferencedCalculation) other;
        if (this.cagridId != oth.cagridId) {
            return false;
        }
        if (this.uniqueIdentifier == null ? oth.uniqueIdentifier != null : !this.uniqueIdentifier.equals(oth.uniqueIdentifier)) {
            return false;
        }
        return true;
    }

    public edu.stanford.hakan.aim4api.base.CalculationEntityReferencesCalculationEntityStatement toAimV4(Calculation calculation) {
        edu.stanford.hakan.aim4api.base.CalculationEntityReferencesCalculationEntityStatement res = new edu.stanford.hakan.aim4api.base.CalculationEntityReferencesCalculationEntityStatement();
        res.setSubjectUniqueIdentifier(Converter.toII(calculation.getUid()));
        res.setObjectUniqueIdentifier(Converter.toII(this.getUniqueIdentifier()));
        return res;
    }

//    public ReferencedCalculation(edu.stanford.hakan.aim4api.base.CalculationEntityReferencesCalculationEntityStatement v4) {
//    }
}
