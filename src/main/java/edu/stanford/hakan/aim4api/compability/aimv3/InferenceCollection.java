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
public class InferenceCollection implements IAimXMLOperations {

    private List<Inference> listInference = new ArrayList<Inference>();

    InferenceCollection() {
    }

    public void AddInference(Inference newInference) {
        this.listInference.add(newInference);
    }

    public List<Inference> getInferenceList() {
        return this.listInference;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        Element inferenceCollection = doc.createElement("inferenceCollection");
        for (int i = 0; i < this.listInference.size(); i++) {
            inferenceCollection.appendChild(this.listInference.get(i).getXMLNode(doc));
        }

        return inferenceCollection;
    }

    @Override
    public void setXMLNode(Node node) {

        this.listInference.clear();

        NodeList tempList = node.getChildNodes();
        for (int j = 0; j < tempList.getLength(); j++) {
            if ("Inference".equals(tempList.item(j).getNodeName())) {
                Inference obj = new Inference();
                obj.setXMLNode(tempList.item(j));
                this.AddInference(obj);
            }
        }
    }

    
    public boolean isEqualTo(Object other) {
        InferenceCollection oth = (InferenceCollection) other;
        if (this.listInference.size() != oth.listInference.size()) {
            return false;
        }
        for (int i = 0; i < this.listInference.size(); i++) {
            if (!this.listInference.get(i).isEqualTo(oth.listInference.get(i))) {
                return false;
            }
        }
        return true;
    }

    public edu.stanford.hakan.aim4api.base.InferenceEntityCollection toAimV4() {
        edu.stanford.hakan.aim4api.base.InferenceEntityCollection res = new edu.stanford.hakan.aim4api.base.InferenceEntityCollection();
        List<Inference> list = this.getInferenceList();
        for (Inference itemV3 : list) {
            res.addInferenceEntity(itemV3.toAimV4());
        }
        return res;
    }

    public InferenceCollection(edu.stanford.hakan.aim4api.base.InferenceEntityCollection v4) {
        List<edu.stanford.hakan.aim4api.base.InferenceEntity> listV4 = v4.getInferenceEntityList();
        for (edu.stanford.hakan.aim4api.base.InferenceEntity itemV4 : listV4) {
            this.AddInference(new Inference(itemV4));
        }
    }
}
