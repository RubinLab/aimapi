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
import org.w3c.dom.NodeList;

/**
 *
 * @author Hakan BULU
 */
public class CalculationData implements IAimXMLOperations {

    private CoordinateCollection coordinateCollection = new CoordinateCollection();
    private Integer cagridId;
    private Double value;

    public CalculationData() {
    }

    public CalculationData(Integer cagridId, Double value) {
        this.cagridId = cagridId;
        this.value = value;
    }

    public Integer getCagridId() {
        return cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    public CoordinateCollection getCoordinateCollection() {
        return coordinateCollection;
    }

    public void setCoordinateCollection(CoordinateCollection coordinateCollection) {
        this.coordinateCollection = coordinateCollection;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void addCoordinate(Integer cagridId, Integer dimensionIndex, Integer position) {
        Coordinate newCoordinate = new Coordinate(cagridId, dimensionIndex, position);
        this.coordinateCollection.AddCoordinate(newCoordinate);
    }

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//
//        this.Control();
//
//        Element calculationData = doc.createElement("CalculationData");
//        calculationData.setAttribute("cagridId", this.cagridId.toString());
//        calculationData.setAttribute("value", this.value.toString());
//        if (this.coordinateCollection.getCoordinateList().size() > 0) {
//            calculationData.appendChild(this.coordinateCollection.getXMLNode(doc));
//        }
//        return calculationData;
//
//    }
    @Override
    public void setXMLNode(Node node) {

        NodeList listChils = node.getChildNodes();
        for (int i = 0; i < listChils.getLength(); i++) {
            if ("coordinateCollection".equals(listChils.item(i).getNodeName())) {
                this.coordinateCollection.setXMLNode(listChils.item(i));
            }
        }

        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        try{
        	this.value = Double.parseDouble(map.getNamedItem("value").getNodeValue());
        }catch(NumberFormatException ne){
        	this.value = Double.NaN;
        }
        
    }

    private void Control() throws AimException {

        if (getCagridId() == null) {
            throw new AimException("AimException: CalculationData's cagridId can not be null");
        }
        if (getValue() == null) {
            throw new AimException("AimException: CalculationData's value can not be null");
        }
    }

    public boolean isEqualTo(Object other) {
        CalculationData oth = (CalculationData) other;
        if (this.cagridId != oth.cagridId) {
            return false;
        }
        if (this.value == null ? oth.value != null : !this.value.equals(oth.value)) {
            return false;
        }
        return this.coordinateCollection.isEqualTo(oth.coordinateCollection);
    }

    public edu.stanford.hakan.aim4api.base.CalculationData toAimV4() {
        edu.stanford.hakan.aim4api.base.CalculationData res = new edu.stanford.hakan.aim4api.base.CalculationData();
        res.setCoordinateCollection(this.getCoordinateCollection().toAimV4());
        if (this.getValue()!=null)
        	res.setValue(Converter.toST(this.getValue()));
        else 
        	res.setValue(Converter.toST(Double.NaN));
        return res;
    }

    public CalculationData(edu.stanford.hakan.aim4api.base.CalculationData v4) {
        this.setCagridId(0);
        if (v4.getCoordinateCollection().getCoordinateList().size() > 0) {
            this.setCoordinateCollection(new CoordinateCollection(v4.getCoordinateCollection()));
        }
        if (v4.getValue() != null) {
        	try{
        		this.setValue(Double.parseDouble(v4.getValue().getValue()));
        	}catch(NumberFormatException ne){
        		this.setValue(Double.NaN);
        	}
        }
    }

    public CalculationData getClone() {
        CalculationData res = new CalculationData();
        if (this.coordinateCollection != null) {
            res.coordinateCollection = this.coordinateCollection.getClone();
        }
        if (this.cagridId != null) {
            res.cagridId = this.cagridId;
        }
        if (this.value != null) {
            res.value = this.value;
        }
        return res;
    }
}
