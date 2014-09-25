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
 * @author Hakan BULU
 */
public class User implements IAimXMLOperations {

    private Integer cagridId;
    private String name;
    private String loginName;
    private String roleInTrial;
    private Integer numberWithinRoleOfClinicalTrial;

    public User(Integer cagridId, String name, String loginName, String roleInTrial, Integer numberWithinRoleOfClinicalTrial) {
        this.cagridId = cagridId;
        this.name = name;
        this.loginName = loginName;
        this.roleInTrial = roleInTrial;
        this.numberWithinRoleOfClinicalTrial = numberWithinRoleOfClinicalTrial;
    }

    public User() {
    }

    public Integer getCagridId() {
        return cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberWithinRoleOfClinicalTrial() {
        return numberWithinRoleOfClinicalTrial;
    }

    public void setNumberWithinRoleOfClinicalTrial(Integer numberWithinRoleOfClinicalTrial) {
        this.numberWithinRoleOfClinicalTrial = numberWithinRoleOfClinicalTrial;
    }

    public String getRoleInTrial() {
        return roleInTrial;
    }

    public void setRoleInTrial(String roleInTrial) {
        this.roleInTrial = roleInTrial;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        this.Control();

        Element user = doc.createElement("User");
        user.setAttribute("cagridId", this.cagridId.toString());
        user.setAttribute("name", this.name);
        user.setAttribute("loginName", this.loginName);
        if (this.roleInTrial != null) {
            user.setAttribute("roleInTrial", this.roleInTrial);
        }
        if (this.numberWithinRoleOfClinicalTrial != null) {
            user.setAttribute("numberWithinRoleOfClinicalTrial", Integer.toString(this.numberWithinRoleOfClinicalTrial));
        }
        return user;
    }

    @Override
    public void setXMLNode(Node node) {

        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.name = map.getNamedItem("name").getNodeValue();
        this.loginName = map.getNamedItem("loginName").getNodeValue();
        if (map.getNamedItem("roleInTrial") != null) {
            this.roleInTrial = map.getNamedItem("roleInTrial").getNodeValue();
        }
        if (map.getNamedItem("numberWithinRoleOfClinicalTrial") != null) {
            this.numberWithinRoleOfClinicalTrial = Integer.parseInt(map.getNamedItem("numberWithinRoleOfClinicalTrial").getNodeValue());
        }
    }

    
    private void Control() throws AimException {
        if (getCagridId() == null) {
            throw new AimException("AimException: User's cagridId can not be null");
        }
        if (getName() == null) {
            throw new AimException("AimException: User's name can not be null");
        }
        if (getLoginName() == null) {
            throw new AimException("AimException: User's loginName can not be null");
        }
    }

    public boolean isEqualTo(Object other) {
        User oth = (User) other;
        if (this.cagridId != oth.cagridId) {
            return false;
        }
        if (this.name == null ? oth.name != null : !this.name.equals(oth.name)) {
            return false;
        }
        if (this.loginName == null ? oth.loginName != null : !this.loginName.equals(oth.loginName)) {
            return false;
        }
        if (this.roleInTrial == null ? oth.roleInTrial != null : !this.roleInTrial.equals(oth.roleInTrial)) {
            return false;
        }
        if (this.numberWithinRoleOfClinicalTrial == null ? oth.numberWithinRoleOfClinicalTrial != null : !this.numberWithinRoleOfClinicalTrial.equals(oth.numberWithinRoleOfClinicalTrial)) {
            return false;
        }
        return true;
    }

    public edu.stanford.hakan.aim4api.base.User toAimV4() {
        edu.stanford.hakan.aim4api.base.User res = new edu.stanford.hakan.aim4api.base.User();
        res.setLoginName(Converter.toST(this.getLoginName()));//
        res.setName(Converter.toST(this.getName()));//
        res.setNumberWithinRoleOfClinicalTrial(this.getNumberWithinRoleOfClinicalTrial());
        res.setRoleInTrial(Converter.toST(this.getRoleInTrial()));
        return res;
    }

    public User(edu.stanford.hakan.aim4api.base.User v4) {
        this.setCagridId(0);
        if (v4.getLoginName() != null) {
            this.setLoginName(v4.getLoginName().getValue());
        }
        if (v4.getName() != null) {
            this.setName(v4.getName().getValue());
        }
        this.setNumberWithinRoleOfClinicalTrial(v4.getNumberWithinRoleOfClinicalTrial());
        if (v4.getRoleInTrial() != null) {
            this.setRoleInTrial(v4.getRoleInTrial().getValue());
        }
    }
}
