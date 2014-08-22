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

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hakan BULU
 */
public class CalculationResultCollection {

    private List<CalculationResult> listCalculationResult = new ArrayList<CalculationResult>();

    CalculationResultCollection() {
    }

    public void AddCalculationResult(CalculationResult newCalculationResult) {
        this.listCalculationResult.add(newCalculationResult);
    }

    public List<CalculationResult> getCalculationResultList() {
        return this.listCalculationResult;
    }

    public void setXMLNode(Node node) {

        this.listCalculationResult.clear();

        NodeList tempList = node.getChildNodes();
        for (int j = 0; j < tempList.getLength(); j++) {
            if ("CalculationResult".equals(tempList.item(j).getNodeName())) {
                CalculationResult obj = new CalculationResult();
                obj.setXMLNode(tempList.item(j));
                this.AddCalculationResult(obj);
            }
        }
    }

    public edu.stanford.hakan.aim4api.base.CalculationResultCollection toAimV4() {
        edu.stanford.hakan.aim4api.base.CalculationResultCollection res = new edu.stanford.hakan.aim4api.base.CalculationResultCollection();
        List<edu.stanford.hakan.aim4api.compability.aimv3.CalculationResult> list = this.getCalculationResultList();
        for (edu.stanford.hakan.aim4api.compability.aimv3.CalculationResult itemV3 : list) {
            res.addCalculationResult(itemV3.toAimV4());
        }
        return res;
    }
}
