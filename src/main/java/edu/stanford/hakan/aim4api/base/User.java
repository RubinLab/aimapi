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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class User implements IAimXMLOperations {

    private ST name;
    private ST loginName;
    private ST roleInTrial;
    private Integer numberWithinRoleOfClinicalTrial;
    private String tagName;

    public ST getName() {
        return name;
    }

    public void setName(ST name) {
        name.setTagName("name");
        this.name = name;
    }

    public ST getLoginName() {
        return loginName;
    }

    public void setLoginName(ST loginName) {
        loginName.setTagName("loginName");
        this.loginName = loginName;
    }

    public ST getRoleInTrial() {
        return roleInTrial;
    }

    public void setRoleInTrial(ST roleInTrial) {
        roleInTrial.setTagName("roleInTrial");
        this.roleInTrial = roleInTrial;
    }

    public Integer getNumberWithinRoleOfClinicalTrial() {
        return numberWithinRoleOfClinicalTrial;
    }

    public void setNumberWithinRoleOfClinicalTrial(Integer numberWithinRoleOfClinicalTrial) {
        this.numberWithinRoleOfClinicalTrial = numberWithinRoleOfClinicalTrial;
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
            setTagName("User");
        }
        Element res = doc.createElement(getTagName());
        if (this.name != null) {
            res.appendChild(this.name.getXMLNode(doc));
        }
        if (this.loginName != null) {
            res.appendChild(this.loginName.getXMLNode(doc));
        }
        if (this.roleInTrial != null) {
            res.appendChild(this.roleInTrial.getXMLNode(doc));
        }
        if (this.numberWithinRoleOfClinicalTrial != null) {
            Element el_numberWithinRoleOfClinicalTrial = doc.createElement("numberWithinRoleOfClinicalTrial");
            el_numberWithinRoleOfClinicalTrial.setAttribute("value", this.numberWithinRoleOfClinicalTrial.toString());
            res.appendChild(el_numberWithinRoleOfClinicalTrial);
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("name".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setName(obj);
            }
            if ("loginName".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setLoginName(obj);
            }
            if ("roleInTrial".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setRoleInTrial(obj);
            }
            if ("numberWithinRoleOfClinicalTrial".equals(currentNode.getNodeName())) {
                this.numberWithinRoleOfClinicalTrial = Integer.parseInt(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        User oth = (User) other;
        if (!this.name.isEqualTo(oth.name)) {
            return false;
        }
        if (!this.loginName.isEqualTo(oth.loginName)) {
            return false;
        }
        if (!this.roleInTrial.isEqualTo(oth.roleInTrial)) {
            return false;
        }
        if (this.numberWithinRoleOfClinicalTrial == null ? oth.numberWithinRoleOfClinicalTrial != null : !this.numberWithinRoleOfClinicalTrial.equals(oth.numberWithinRoleOfClinicalTrial)) {
            return false;
        }
        return true;
    }

    public User getClone() {
        User res = new User();
        if (this.getName() != null) {
            res.setName(this.getName().getClone());
        }
        if (this.getLoginName() != null) {
            res.setLoginName(this.getLoginName().getClone());
        }
        if (this.getRoleInTrial() != null) {
            res.setRoleInTrial(this.getRoleInTrial().getClone());
        }
        if (this.getNumberWithinRoleOfClinicalTrial() != null) {
            res.setNumberWithinRoleOfClinicalTrial(this.getNumberWithinRoleOfClinicalTrial());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        return res;
    }
}
