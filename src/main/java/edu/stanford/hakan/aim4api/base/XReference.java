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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author localadmin
 */
public class XReference implements IAimXMLOperations {

    private String xref;
    private Boolean hasIsoAttributes;
    private String tagName;

    public String getXref() {
        return xref;
    }

    public void setXref(String xref) {
        this.xref = xref;
    }

    protected Boolean getHasIsoAttributes() {
        return hasIsoAttributes;
    }

    protected void setHasIsoAttributes(Boolean hasIsoAttributes) {
        this.hasIsoAttributes = hasIsoAttributes;
    }

    protected String getTagName() {
        return tagName;
    }

    protected void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("XReference");
        }
        if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
            this.setTagName("iso:".concat(this.getTagName()));
        }
        Element res = doc.createElement(getTagName());
        if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
            res.setAttribute("xmlns:iso", "uri:iso.org:21090");
        }
        if (this.xref != null) {
            res.setAttribute("xref", this.xref.toString());
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        NamedNodeMap map = node.getAttributes();
        if (map.getNamedItem("xref") != null) {
            this.xref = map.getNamedItem("xref").getNodeValue();
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        XReference oth = (XReference) other;
        if (this.xref == null ? oth.xref != null : !this.xref.equals(oth.xref)) {
            return false;
        }
        return true;
    }

    public XReference getClone() {
        XReference res = new XReference();
        if (this.getXref() != null) {
            res.setXref(this.getXref());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        return res;
    }
}
