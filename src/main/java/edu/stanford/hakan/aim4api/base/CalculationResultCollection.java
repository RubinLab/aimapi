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

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class CalculationResultCollection implements IAimXMLOperations {

    private final List<CalculationResult> listCalculationResult = new ArrayList<CalculationResult>();

    public void addCalculationResult(CalculationResult newCalculationResult) {
        this.listCalculationResult.add(newCalculationResult);
    }

    public List<CalculationResult> getCalculationResultList() {
        return this.listCalculationResult;
    }

    public int size() {
        return this.listCalculationResult.size();
    }

    public CalculationResult get(int index) {
        if (index <= this.listCalculationResult.size() - 1) {
            return this.listCalculationResult.get(index);
        }
        return null;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        Element calculationResultCollection = doc.createElement("calculationResultCollection");
        for (int i = 0; i < this.listCalculationResult.size(); i++) {
            this.listCalculationResult.get(i).setTagName("CalculationResult");
            calculationResultCollection.appendChild(this.listCalculationResult.get(i).getXMLNode(doc));
        }
        return calculationResultCollection;
    }

    @Override
    public void setXMLNode(Node node) {
        this.listCalculationResult.clear();
        NodeList tempList = node.getChildNodes();
        for (int j = 0; j < tempList.getLength(); j++) {
            Node currentNode = tempList.item(j);
            if ("CalculationResult".equals(currentNode.getNodeName())) {
                NamedNodeMap map = currentNode.getAttributes();
                if (map.getNamedItem("xsi:type") != null) {
                    String xsiType = map.getNamedItem("xsi:type").getNodeValue();
                    if ("CompactCalculationResult".equals(xsiType)) {
                        CompactCalculationResult obj = new CompactCalculationResult();
                        obj.setXMLNode(currentNode);
                        this.addCalculationResult(obj);
                    }
                    if ("ExtendedCalculationResult".equals(xsiType)) {
                        ExtendedCalculationResult obj = new ExtendedCalculationResult();
                        obj.setXMLNode(currentNode);
                        this.addCalculationResult(obj);
                    }

                } else {
                    CalculationResult obj = new CalculationResult();
                    obj.setXMLNode(tempList.item(j));
                    this.addCalculationResult(obj);
                }
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
        CalculationResultCollection oth = (CalculationResultCollection) other;
        if (this.listCalculationResult.size() != oth.listCalculationResult.size()) {
            return false;
        }
        for (int i = 0; i < this.listCalculationResult.size(); i++) {
            if (this.listCalculationResult.get(i) == null ? oth.listCalculationResult.get(i) != null : !this.listCalculationResult.get(i).isEqualTo(oth.listCalculationResult.get(i))) {
                return false;
            }
        }
        return true;
    }

    public CalculationResultCollection getClone() {
        CalculationResultCollection res = new CalculationResultCollection();
        for (int i = 0; i < this.listCalculationResult.size(); i++) {
            if (this.listCalculationResult.get(i) != null) {
                res.addCalculationResult(this.listCalculationResult.get(i).getClone());
            }
        }
        return res;
    }
}
