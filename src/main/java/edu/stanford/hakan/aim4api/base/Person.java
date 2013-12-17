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
public class Person implements IAimXMLOperations {

    private ST name;
    private ST id;
    private String birthDate;
    private ST sex;
    private ST ethnicGroup;
    private String tagName;

    public ST getName() {
        return name;
    }

    public void setName(ST name) {
        name.setTagName("name");
        this.name = name;
    }

    public ST getId() {
        return id;
    }

    public void setId(ST id) {
        id.setTagName("id");
        this.id = id;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public ST getSex() {
        return sex;
    }

    public void setSex(ST sex) {
        sex.setTagName("sex");
        this.sex = sex;
    }

    public ST getEthnicGroup() {
        return ethnicGroup;
    }

    public void setEthnicGroup(ST ethnicGroup) {
        ethnicGroup.setTagName("ethnicGroup");
        this.ethnicGroup = ethnicGroup;
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
            setTagName("Person");
        }
        Element res = doc.createElement(getTagName());
        if (this.name != null) {
            res.appendChild(this.name.getXMLNode(doc));
        }
        if (this.id != null) {
            res.appendChild(this.id.getXMLNode(doc));
        }
        if (this.birthDate != null) {
            Element el_birthDate = doc.createElement("birthDate");
            el_birthDate.setAttribute("value", this.birthDate.toString());
            res.appendChild(el_birthDate);
        }
        if (this.sex != null) {
            res.appendChild(this.sex.getXMLNode(doc));
        }
        if (this.ethnicGroup != null) {
            res.appendChild(this.ethnicGroup.getXMLNode(doc));
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
            if ("id".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setId(obj);
            }
            if ("birthDate".equals(currentNode.getNodeName())) {
                this.birthDate = currentNode.getAttributes().getNamedItem("value").getNodeValue();
            }
            if ("sex".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setSex(obj);
            }
            if ("ethnicGroup".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setEthnicGroup(obj);
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        Person oth = (Person) other;
        if (!this.name.isEqualTo(oth.name)) {
            return false;
        }
        if (!this.id.isEqualTo(oth.id)) {
            return false;
        }
        if (this.birthDate == null ? oth.birthDate != null : !this.birthDate.equals(oth.birthDate)) {
            return false;
        }
        if (!this.sex.isEqualTo(oth.sex)) {
            return false;
        }
        if (!this.ethnicGroup.isEqualTo(oth.ethnicGroup)) {
            return false;
        }
        return true;
    }

    public Person getClone() {
        Person res = new Person();
        if (this.getName() != null) {
            res.setName(this.getName().getClone());
        }
        if (this.getId() != null) {
            res.setId(this.getId().getClone());
        }
        if (this.getBirthDate() != null) {
            res.setBirthDate(this.getBirthDate());
        }
        if (this.getSex() != null) {
            res.setSex(this.getSex().getClone());
        }
        if (this.getEthnicGroup() != null) {
            res.setEthnicGroup(this.getEthnicGroup().getClone());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        return res;
    }
}
