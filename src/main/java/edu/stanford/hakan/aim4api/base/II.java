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
public class II implements IAimXMLOperations {

    private String root;
    private String extension;
    private String identifierName;
    private Boolean displayable;
    private Enumerations.IdentifierScope scope;
    private Enumerations.IdentifierReliability reliability;
    private Boolean hasIsoAttributes;
    private String tagName;

    public II() {
    }

    public II(String root) {
        this.root = root;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getIdentifierName() {
        return identifierName;
    }

    public void setIdentifierName(String identifierName) {
        this.identifierName = identifierName;
    }

    public Boolean getDisplayable() {
        return displayable;
    }

    public void setDisplayable(Boolean displayable) {
        this.displayable = displayable;
    }

    public Enumerations.IdentifierScope getScope() {
        return scope;
    }

    public void setScope(Enumerations.IdentifierScope scope) {
        this.scope = scope;
    }

    public Enumerations.IdentifierReliability getReliability() {
        return reliability;
    }

    public void setReliability(Enumerations.IdentifierReliability reliability) {
        this.reliability = reliability;
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
            setTagName("II");
        }
        if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
            this.setTagName("iso:".concat(this.getTagName()));
        }
        Element res = doc.createElement(getTagName());
        if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
            res.setAttribute("xmlns:iso", "uri:iso.org:21090");
        }
        if (this.root != null) {
            res.setAttribute("root", this.root.toString());
        }
        if (this.extension != null) {
            res.setAttribute("extension", this.extension.toString());
        }
        if (this.identifierName != null) {
            res.setAttribute("identifierName", this.identifierName.toString());
        }
        if (this.displayable != null) {
            res.setAttribute("displayable", this.displayable.toString());
        }
        if (this.scope != null) {
            res.setAttribute("scope", this.scope.toString());
        }
        if (this.reliability != null) {
            res.setAttribute("reliability", this.reliability.toString());
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        NamedNodeMap map = node.getAttributes();
        if (map.getNamedItem("root") != null) {
            this.root = map.getNamedItem("root").getNodeValue();
        }
        if (map.getNamedItem("extension") != null) {
            this.extension = map.getNamedItem("extension").getNodeValue();
        }
        if (map.getNamedItem("identifierName") != null) {
            this.identifierName = map.getNamedItem("identifierName").getNodeValue();
        }
        if (map.getNamedItem("displayable") != null) {
            this.displayable = Boolean.parseBoolean(map.getNamedItem("displayable").getNodeValue());
        }
        if (map.getNamedItem("scope") != null) {
            this.scope = Enumerations.IdentifierScope.valueOf(map.getNamedItem("scope").getNodeValue());
        }
        if (map.getNamedItem("reliability") != null) {
            this.reliability = Enumerations.IdentifierReliability.valueOf(map.getNamedItem("reliability").getNodeValue());
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        II oth = (II) other;
        if (this.root == null ? oth.root != null : !this.root.equals(oth.root)) {
            return false;
        }
        if (this.extension == null ? oth.extension != null : !this.extension.equals(oth.extension)) {
            return false;
        }
        if (this.identifierName == null ? oth.identifierName != null : !this.identifierName.equals(oth.identifierName)) {
            return false;
        }
        if (this.displayable == null ? oth.displayable != null : !this.displayable.equals(oth.displayable)) {
            return false;
        }
        if (this.scope == null ? oth.scope != null : !this.scope.equals(oth.scope)) {
            return false;
        }
        if (this.reliability == null ? oth.reliability != null : !this.reliability.equals(oth.reliability)) {
            return false;
        }
        return true;
    }

    public II getClone() {
        II res = new II();
        if (this.getRoot() != null) {
            res.setRoot(this.getRoot());
        }
        if (this.getExtension() != null) {
            res.setExtension(this.getExtension());
        }
        if (this.getIdentifierName() != null) {
            res.setIdentifierName(this.getIdentifierName());
        }
        if (this.getDisplayable() != null) {
            res.setDisplayable(this.getDisplayable());
        }
        if (this.getScope() != null) {
            res.setScope(this.getScope());
        }
        if (this.getReliability() != null) {
            res.setReliability(this.getReliability());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        return res;
    }
}
