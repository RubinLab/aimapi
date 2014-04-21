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
public class ImagingPhysicalEntityCharacteristicCollection implements IAimXMLOperations {

    private final List<ImagingPhysicalEntityCharacteristic> listImagingPhysicalEntityCharacteristic = new ArrayList<>();

    public void addImagingPhysicalEntityCharacteristic(
            ImagingPhysicalEntityCharacteristic newImagingPhysicalEntityCharacteristic) {
        this.listImagingPhysicalEntityCharacteristic.add(newImagingPhysicalEntityCharacteristic);
    }

    public List<ImagingPhysicalEntityCharacteristic> getImagingPhysicalEntityCharacteristicList() {
        return this.listImagingPhysicalEntityCharacteristic;
    }

    public int size() {
        return this.listImagingPhysicalEntityCharacteristic.size();
    }

    public ImagingPhysicalEntityCharacteristic get(int index) {
        if (index <= this.listImagingPhysicalEntityCharacteristic.size() - 1) {
            return this.listImagingPhysicalEntityCharacteristic.get(index);
        }
        return null;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        Element imagingPhysicalEntityCharacteristicCollection = doc
                .createElement("imagingPhysicalEntityCharacteristicCollection");
        for (int i = 0; i < this.listImagingPhysicalEntityCharacteristic.size(); i++) {
            this.listImagingPhysicalEntityCharacteristic.get(i).setTagName("ImagingPhysicalEntityCharacteristic");
            imagingPhysicalEntityCharacteristicCollection.appendChild(this.listImagingPhysicalEntityCharacteristic.get(i)
                    .getXMLNode(doc));
        }
        return imagingPhysicalEntityCharacteristicCollection;
    }

    @Override
    public void setXMLNode(Node node) {
        this.listImagingPhysicalEntityCharacteristic.clear();
        NodeList tempList = node.getChildNodes();
        for (int j = 0; j < tempList.getLength(); j++) {
            Node currentNode = tempList.item(j);
            if ("ImagingPhysicalEntityCharacteristic".equals(currentNode.getNodeName())) {
                NamedNodeMap map = currentNode.getAttributes();
                if (map.getNamedItem("xsi:type") != null) {
                    String xsiType = map.getNamedItem("xsi:type").getNodeValue();

                } else {
                    ImagingPhysicalEntityCharacteristic obj = new ImagingPhysicalEntityCharacteristic();
                    obj.setXMLNode(tempList.item(j));
                    this.addImagingPhysicalEntityCharacteristic(obj);
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
        ImagingPhysicalEntityCharacteristicCollection oth = (ImagingPhysicalEntityCharacteristicCollection) other;
        if (this.listImagingPhysicalEntityCharacteristic.size() != oth.listImagingPhysicalEntityCharacteristic.size()) {
            return false;
        }
        for (int i = 0; i < this.listImagingPhysicalEntityCharacteristic.size(); i++) {
            if (this.listImagingPhysicalEntityCharacteristic.get(i) == null ? oth.listImagingPhysicalEntityCharacteristic.get(i) != null : !this.listImagingPhysicalEntityCharacteristic.get(i).isEqualTo(oth.listImagingPhysicalEntityCharacteristic.get(i))) {
                return false;
            }
        }
        return true;
    }

    public ImagingPhysicalEntityCharacteristicCollection getClone() {
        ImagingPhysicalEntityCharacteristicCollection res = new ImagingPhysicalEntityCharacteristicCollection();
        for (int i = 0; i < this.listImagingPhysicalEntityCharacteristic.size(); i++) {
            if (this.listImagingPhysicalEntityCharacteristic.get(i) != null) {
                res.addImagingPhysicalEntityCharacteristic(this.listImagingPhysicalEntityCharacteristic.get(i).getClone());
            }
        }
        return res;
    }
}
