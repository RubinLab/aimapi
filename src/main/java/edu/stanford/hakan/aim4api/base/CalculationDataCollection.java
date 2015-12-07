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
public class CalculationDataCollection implements IAimXMLOperations {

    private List<CalculationData> listCalculationData = new ArrayList<CalculationData>();

    public void addCalculationData(CalculationData newCalculationData) {
        this.listCalculationData.add(newCalculationData);
    }

    public List<CalculationData> getCalculationDataList() {
        return this.listCalculationData;
    }

    public int size() {
        return this.listCalculationData.size();
    }

    public CalculationData get(int index) {
        if (index <= this.listCalculationData.size() - 1) {
            return this.listCalculationData.get(index);
        }
        return null;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        Element calculationDataCollection = doc.createElement("calculationDataCollection");
        for (int i = 0; i < this.listCalculationData.size(); i++) {
            this.listCalculationData.get(i).setTagName("CalculationData");
            calculationDataCollection.appendChild(this.listCalculationData.get(i).getXMLNode(doc));
        }
        return calculationDataCollection;
    }

    @Override
    public void setXMLNode(Node node) {
        this.listCalculationData.clear();
        NodeList tempList = node.getChildNodes();
        for (int j = 0; j < tempList.getLength(); j++) {
            Node currentNode = tempList.item(j);
            if ("CalculationData".equals(currentNode.getNodeName())) {
                NamedNodeMap map = currentNode.getAttributes();
                if (map.getNamedItem("xsi:type") != null) {
                    String xsiType = map.getNamedItem("xsi:type").getNodeValue();

                } else {
                    CalculationData obj = new CalculationData();
                    obj.setXMLNode(tempList.item(j));
                    this.addCalculationData(obj);
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
        CalculationDataCollection oth = (CalculationDataCollection) other;
        if (this.listCalculationData.size() != oth.listCalculationData.size()) {
            return false;
        }
        for (int i = 0; i < this.listCalculationData.size(); i++) {
            if (this.listCalculationData.get(i) == null ? oth.listCalculationData.get(i) != null : !this.listCalculationData.get(i).isEqualTo(oth.listCalculationData.get(i))) {
                return false;
            }
        }
        return true;
    }

    public CalculationDataCollection getClone() {
        CalculationDataCollection res = new CalculationDataCollection();
        for (int i = 0; i < this.listCalculationData.size(); i++) {
            if (this.listCalculationData.get(i) != null) {
                res.addCalculationData(this.listCalculationData.get(i).getClone());
            }
        }
        return res;
    }
}
