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

/**
 *
 * @author pc
 */
public class SpatialCoordinate implements ISpatialCoordinate, IAimXMLOperations {

    private Integer cagridId;
    private Integer coordinateIndex;
    private String xsiType;

    protected void setXsiType(String xsiType) {
        this.xsiType = xsiType;
    }

    public String getXsiType() {
        return this.xsiType;
    }

    @Override
    public Integer getCagridId() {
        return this.cagridId;
    }

    @Override
    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    @Override
    public Integer getCoordinateIndex() {
        return this.coordinateIndex;
    }

    @Override
    public void setCoordinateIndex(Integer coordinateIndex) {
        this.coordinateIndex = coordinateIndex;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        this.Control();

        Element spatialCoordinate = doc.createElement("SpatialCoordinate");
        spatialCoordinate.setAttribute("cagridId", this.cagridId.toString());
        spatialCoordinate.setAttribute("coordinateIndex", this.coordinateIndex.toString());
        spatialCoordinate.setAttribute("xsi:type", this.xsiType);
        return spatialCoordinate;
    }

    @Override
    public void setXMLNode(Node node) {
        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.coordinateIndex = Integer.parseInt(map.getNamedItem("coordinateIndex").getNodeValue());
    }

    private void Control() throws AimException {

        if (getCagridId() == null) {
            throw new AimException("AimException: SpatialCoordinate's cagridId can not be null");
        }
        if (getCoordinateIndex() == null) {
            throw new AimException("AimException: SpatialCoordinate's coordinateIndex can not be null");
        }
    }

    public boolean isEqualTo(Object other) {
        SpatialCoordinate oth = (SpatialCoordinate) other;
        if (this.cagridId != oth.cagridId) {
            return false;
        }
        if (this.coordinateIndex == null ? oth.coordinateIndex != null : !this.coordinateIndex.equals(oth.coordinateIndex)) {
            return false;
        }
        return true;
    }
    

}
