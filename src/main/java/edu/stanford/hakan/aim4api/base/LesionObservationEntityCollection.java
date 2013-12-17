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
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

/**
 *
 * @author localadmin
 */
public class LesionObservationEntityCollection implements IAimXMLOperations {

    private List<LesionObservationEntity> listLesionObservationEntity = new ArrayList<>();

    public void addLesionObservationEntity(LesionObservationEntity newLesionObservationEntity) {
        this.listLesionObservationEntity.add(newLesionObservationEntity);
    }

    public List<LesionObservationEntity> getLesionObservationEntityList() {
        return this.listLesionObservationEntity;
    }

    public int size() {
        return this.listLesionObservationEntity.size();
    }

    public LesionObservationEntity get(int index) {
        if (index <= this.listLesionObservationEntity.size() - 1) {
            return this.listLesionObservationEntity.get(index);
        }
        return null;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        Element lesionObservationEntityCollection = doc.createElement("lesionObservationEntityCollection");
        for (int i = 0; i < this.listLesionObservationEntity.size(); i++) {
            this.listLesionObservationEntity.get(i).setTagName("LesionObservationEntity");
            lesionObservationEntityCollection.appendChild(this.listLesionObservationEntity.get(i).getXMLNode(doc));
        }
        return lesionObservationEntityCollection;
    }

    @Override
    public void setXMLNode(Node node) {
        this.listLesionObservationEntity.clear();
        NodeList tempList = node.getChildNodes();
        for (int j = 0; j < tempList.getLength(); j++) {
            Node currentNode = tempList.item(j);
            if ("LesionObservationEntity".equals(currentNode.getNodeName())) {
                NamedNodeMap map = currentNode.getAttributes();
                if (map.getNamedItem("xsi:type") != null) {
                    String xsiType = map.getNamedItem("xsi:type").getNodeValue();
                    if ("TimePointLesionObservationEntity".equals(xsiType)) {
                        TimePointLesionObservationEntity obj = new TimePointLesionObservationEntity();
                        obj.setXMLNode(currentNode);
                        this.addLesionObservationEntity(obj);
                    }
                    if ("GeneralLesionObservationEntity".equals(xsiType)) {
                        GeneralLesionObservationEntity obj = new GeneralLesionObservationEntity();
                        obj.setXMLNode(currentNode);
                        this.addLesionObservationEntity(obj);
                    }

                } else {
                    LesionObservationEntity obj = new LesionObservationEntity();
                    obj.setXMLNode(tempList.item(j));
                    this.addLesionObservationEntity(obj);
                }
            }
        }

    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        LesionObservationEntityCollection oth = (LesionObservationEntityCollection) other;
        if (this.listLesionObservationEntity.size() != oth.listLesionObservationEntity.size()) {
            return false;
        }
        for (int i = 0; i < this.listLesionObservationEntity.size(); i++) {
            if (!this.listLesionObservationEntity.get(i).isEqualTo(oth.listLesionObservationEntity.get(i))) {
                return false;
            }
        }
        return true;
    }

    public LesionObservationEntityCollection getClone() {
        LesionObservationEntityCollection res = new LesionObservationEntityCollection();
        for (int i = 0; i < this.listLesionObservationEntity.size(); i++) {
            if (this.listLesionObservationEntity.get(i) != null) {
                res.addLesionObservationEntity(this.listLesionObservationEntity.get(i).getClone());
            }
        }
        return res;
    }
}
