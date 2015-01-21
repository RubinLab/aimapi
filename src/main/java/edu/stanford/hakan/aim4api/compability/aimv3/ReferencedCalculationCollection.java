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
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hakan BULU
 */
public class ReferencedCalculationCollection implements IAimXMLOperations {

    private List<ReferencedCalculation> listReferencedCalculation = new ArrayList<ReferencedCalculation>();

    public void AddReferencedCalculation(ReferencedCalculation newReferencedCalculation) {
        this.listReferencedCalculation.add(newReferencedCalculation);
    }

    public List<ReferencedCalculation> getReferencedCalculationList() {
        return this.listReferencedCalculation;
    }

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//
//        Element referencedCalculationCollection = doc.createElement("referencedCalculationCollection");
//        for (int i = 0; i < this.listReferencedCalculation.size(); i++) {
//            referencedCalculationCollection.appendChild(this.listReferencedCalculation.get(i).getXMLNode(doc));
//        }
//        return referencedCalculationCollection;
//    }
    @Override
    public void setXMLNode(Node node) {

        this.listReferencedCalculation.clear();

        NodeList tempList = node.getChildNodes();
        for (int j = 0; j < tempList.getLength(); j++) {
            if ("ReferencedCalculation".equals(tempList.item(j).getNodeName())) {
                ReferencedCalculation obj = new ReferencedCalculation();
                obj.setXMLNode(tempList.item(j));
                this.AddReferencedCalculation(obj);
            }
        }
    }

    public boolean isEqualTo(Object other) {
        ReferencedCalculationCollection oth = (ReferencedCalculationCollection) other;
        if (this.listReferencedCalculation.size() != oth.listReferencedCalculation.size()) {
            return false;
        }
        for (int i = 0; i < this.listReferencedCalculation.size(); i++) {
            if (!this.listReferencedCalculation.get(i).isEqualTo(oth.listReferencedCalculation.get(i))) {
                return false;
            }
        }
        return true;
    }

    public ReferencedCalculationCollection getClone() {
        ReferencedCalculationCollection res = new ReferencedCalculationCollection();
        for (int i = 0; i < this.listReferencedCalculation.size(); i++) {
            if (this.listReferencedCalculation.get(i) != null) {
                res.AddReferencedCalculation(this.listReferencedCalculation.get(i).getClone());
            }
        }
        return res;
    }
}
