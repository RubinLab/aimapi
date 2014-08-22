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

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

/**
 *
 * @author Hakan BULU
 */
public class ImageReference {

    private Integer cagridId;
    private String xsiType;
    private String rdfID;
    private boolean dontCreateOwlIntance = false;
    private boolean dontAddIndexAsPostFixToRdfInstanceName = false;

    public String getRdfID() {
        return rdfID;
    }

    public void setRdfID(String rdfID) {
        this.rdfID = rdfID;
    }

    public boolean isDontCreateOwlIntance() {
        return dontCreateOwlIntance;
    }

    public boolean isDontAddIndexAsPostFixToRdfInstanceName() {
        return dontAddIndexAsPostFixToRdfInstanceName;
    }

    public void setDontAddIndexAsPostFixToRdfInstanceName(boolean dontAddIndexAsPostFixToRdfInstanceName) {
        this.dontAddIndexAsPostFixToRdfInstanceName = dontAddIndexAsPostFixToRdfInstanceName;
    }

    public String getXsiType() {
        return xsiType;
    }

    public void setDontCreateOwlIntance(boolean dontCreateOwlIntance) {
        this.dontCreateOwlIntance = dontCreateOwlIntance;
    }

    protected void setXsiType(String xsiType) {
        this.xsiType = xsiType;
    }

    public Integer getCagridId() {
        return this.cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    public void setXMLNode(Node node) {
        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
    }
}
