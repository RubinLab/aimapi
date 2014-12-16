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
public class ImagingObservationCharacteristicCollection implements IAimXMLOperations {

    private List<ImagingObservationCharacteristic> listImagingObservationCharacteristic = new ArrayList<ImagingObservationCharacteristic>();

    ImagingObservationCharacteristicCollection() {
    }

    public void AddImagingObservationCharacteristic(ImagingObservationCharacteristic newImagingObservationCharacteristic) {
        this.listImagingObservationCharacteristic.add(newImagingObservationCharacteristic);
    }

    public List<ImagingObservationCharacteristic> getImagingObservationCharacteristicList() {
        return this.listImagingObservationCharacteristic;
    }

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//
//        Element imagingObservationCharacteristicCollection = doc.createElement("imagingObservationCharacteristicCollection");
//        for (int i = 0; i < this.listImagingObservationCharacteristic.size(); i++) {
//            imagingObservationCharacteristicCollection.appendChild(this.listImagingObservationCharacteristic.get(i).getXMLNode(doc));
//        }
//        return imagingObservationCharacteristicCollection;
//    }

    @Override
    public void setXMLNode(Node node) {

        this.listImagingObservationCharacteristic.clear();

        NodeList tempList = node.getChildNodes();
        for (int j = 0; j < tempList.getLength(); j++) {
            if ("ImagingObservationCharacteristic".equals(tempList.item(j).getNodeName())) {
                ImagingObservationCharacteristic obj = new ImagingObservationCharacteristic();
                obj.setXMLNode(tempList.item(j));
                this.AddImagingObservationCharacteristic(obj);
            }
        }
    }

        
    public boolean isEqualTo(Object other) {
        ImagingObservationCharacteristicCollection oth = (ImagingObservationCharacteristicCollection) other;
        if (this.listImagingObservationCharacteristic.size() != oth.listImagingObservationCharacteristic.size()) {
            return false;
        }
        for (int i = 0; i < this.listImagingObservationCharacteristic.size(); i++) {
            if (!this.listImagingObservationCharacteristic.get(i).isEqualTo(oth.listImagingObservationCharacteristic.get(i))) {
                return false;
            }
        }
        return true;
    }

    public edu.stanford.hakan.aim4api.base.ImagingObservationCharacteristicCollection toAimV4() {
        edu.stanford.hakan.aim4api.base.ImagingObservationCharacteristicCollection res = new edu.stanford.hakan.aim4api.base.ImagingObservationCharacteristicCollection();
        List<ImagingObservationCharacteristic> list = this.getImagingObservationCharacteristicList();
        for (ImagingObservationCharacteristic itemV3 : list) {
            res.addImagingObservationCharacteristic(itemV3.toAimV4());
        }
        return res;
    }

    public ImagingObservationCharacteristicCollection(edu.stanford.hakan.aim4api.base.ImagingObservationCharacteristicCollection v4) {
       List<edu.stanford.hakan.aim4api.base.ImagingObservationCharacteristic> listV4 = v4.getImagingObservationCharacteristicList();
        for (edu.stanford.hakan.aim4api.base.ImagingObservationCharacteristic itemV4 : listV4) {
            this.AddImagingObservationCharacteristic(new ImagingObservationCharacteristic(itemV4));
        }
    }
}
