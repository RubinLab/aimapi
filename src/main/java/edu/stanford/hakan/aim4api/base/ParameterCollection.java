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
public class ParameterCollection implements IAimXMLOperations {

    private List<Parameter> listParameter = new ArrayList<Parameter>();

    public void addParameter(Parameter newParameter) {
        this.listParameter.add(newParameter);
    }

    public List<Parameter> getParameterList() {
        return this.listParameter;
    }

    public int size() {
        return this.listParameter.size();
    }

    public Parameter get(int index) {
        if (index <= this.listParameter.size() - 1) {
            return this.listParameter.get(index);
        }
        return null;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        Element parameterCollection = doc.createElement("parameterCollection");
        for (int i = 0; i < this.listParameter.size(); i++) {
            this.listParameter.get(i).setTagName("Parameter");
            parameterCollection.appendChild(this.listParameter.get(i).getXMLNode(doc));
        }
        return parameterCollection;
    }

    @Override
    public void setXMLNode(Node node) {
        this.listParameter.clear();
        NodeList tempList = node.getChildNodes();
        for (int j = 0; j < tempList.getLength(); j++) {
            Node currentNode = tempList.item(j);
            if ("Parameter".equals(currentNode.getNodeName())) {
                NamedNodeMap map = currentNode.getAttributes();
                if (map.getNamedItem("xsi:type") != null) {
                    String xsiType = map.getNamedItem("xsi:type").getNodeValue();

                } else {
                    Parameter obj = new Parameter();
                    obj.setXMLNode(tempList.item(j));
                    this.addParameter(obj);
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
        ParameterCollection oth = (ParameterCollection) other;
        if (this.listParameter.size() != oth.listParameter.size()) {
            return false;
        }
        for (int i = 0; i < this.listParameter.size(); i++) {
            if (this.listParameter.get(i) == null ? oth.listParameter.get(i) != null : !this.listParameter.get(i).isEqualTo(oth.listParameter.get(i))) {
                return false;
            }
        }
        return true;
    }

    public ParameterCollection getClone() {
        ParameterCollection res = new ParameterCollection();
        for (int i = 0; i < this.listParameter.size(); i++) {
            if (this.listParameter.get(i) != null) {
                res.addParameter(this.listParameter.get(i).getClone());
            }
        }
        return res;
    }
}
