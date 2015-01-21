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
public class Person implements IAimXMLOperations {

    private Integer cagridId;
    private String name;
    private String id;
    private String birthDate;
    private String sex;
    private String ethnicGroup;
    private String rdfID;
    private boolean dontCreateOwlIntance = false;
    private boolean dontAddIndexAsPostFixToRdfInstanceName = false;

    public Person() {
    }

    public void setDontAddIndexAsPostFixToRdfInstanceName(boolean dontAddIndexAsPostFixToRdfInstanceName) {
        this.dontAddIndexAsPostFixToRdfInstanceName = dontAddIndexAsPostFixToRdfInstanceName;
    }

    public void setDontCreateOwlIntance(boolean dontCreateOwlIntance) {
        this.dontCreateOwlIntance = dontCreateOwlIntance;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getCagridId() {
        return cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    public String getEthnicGroup() {
        return ethnicGroup;
    }

    public void setEthnicGroup(String ethnicGroup) {
        this.ethnicGroup = ethnicGroup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isDontAddIndexAsPostFixToRdfInstanceName() {
        return dontAddIndexAsPostFixToRdfInstanceName;
    }

    public boolean isDontCreateOwlIntance() {
        return dontCreateOwlIntance;
    }

    public String getRdfID() {
        return rdfID;
    }

    public void setRdfID(String rdfID) {
        this.rdfID = rdfID;
    }

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//
//        this.Control();
//
//        Element person = doc.createElement("Person");
//        person.setAttribute("cagridId", this.cagridId.toString());
//        person.setAttribute("name", this.name);
//        person.setAttribute("id", this.id);
//        if (this.birthDate != null) {
//            person.setAttribute("birthDate", this.birthDate.toString());
//        }
//        if (this.sex != null) {
//            person.setAttribute("sex", this.sex);
//        }
//        if (this.ethnicGroup != null) {
//            person.setAttribute("ethnicGroup", this.ethnicGroup);
//        }
//        return person;
//    }
    @Override
    public void setXMLNode(Node node) {

        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.name = map.getNamedItem("name").getNodeValue();
        this.id = map.getNamedItem("id").getNodeValue();
        if (map.getNamedItem("birthDate") != null) {
            this.birthDate = map.getNamedItem("birthDate").getNodeValue();
        }
        if (map.getNamedItem("sex") != null) {
            this.sex = map.getNamedItem("sex").getNodeValue();
        }
        if (map.getNamedItem("ethnicGroup") != null) {
            this.ethnicGroup = map.getNamedItem("ethnicGroup").getNodeValue();
        }
    }

    private void Control() throws AimException {
        if (getCagridId() == null) {
            throw new AimException("AimException: Person's cagridId can not be null");
        }
        if (getName() == null) {
            throw new AimException("AimException: Person's name can not be null");
        }
        if (getId() == null) {
            throw new AimException("AimException: Person's id can not be null");
        }
    }

    public boolean isEqualTo(Object other) {
        Person oth = (Person) other;
        if (this.cagridId != oth.cagridId) {
            return false;
        }
        if (this.name == null ? oth.name != null : !this.name.equals(oth.name)) {
            return false;
        }
        if (this.id == null ? oth.id != null : !this.id.equals(oth.id)) {
            return false;
        }
        if (this.birthDate == null ? oth.birthDate != null : !this.birthDate.equals(oth.birthDate)) {
            return false;
        }
        if (this.sex == null ? oth.sex != null : !this.sex.equals(oth.sex)) {
            return false;
        }
        if (this.ethnicGroup == null ? oth.ethnicGroup != null : !this.ethnicGroup.equals(oth.ethnicGroup)) {
            return false;
        }
        return true;
    }

    public edu.stanford.hakan.aim4api.base.Person toAimV4() {
        edu.stanford.hakan.aim4api.base.Person res = new edu.stanford.hakan.aim4api.base.Person();
        res.setBirthDate(this.getBirthDate());//
        res.setEthnicGroup(Converter.toST(this.getEthnicGroup()));//
        res.setId(Converter.toST(this.getId()));//
        res.setName(Converter.toST(this.getName()));//
        res.setSex(Converter.toST(this.getSex()));
        return res;
    }

    public Person(edu.stanford.hakan.aim4api.base.Person v4) {
        this.setCagridId(0);
        this.setBirthDate(v4.getBirthDate());
        if (v4.getEthnicGroup() != null) {
            this.setEthnicGroup(v4.getEthnicGroup().getValue());
        }
        if (v4.getId() != null) {
            this.setId(v4.getId().getValue());
        }
        if (v4.getName() != null) {
            this.setName(v4.getName().getValue());
        }
        if (v4.getSex() != null) {
            this.setSex(v4.getSex().getValue());
        }
    }

    public Person getClone() {
        Person res = new Person();
        if (this.cagridId != null) {
            res.cagridId = this.cagridId;
        }
        if (this.name != null) {
            res.name = this.name;
        }
        if (this.id != null) {
            res.id = this.id;
        }
        if (this.birthDate != null) {
            res.birthDate = this.birthDate;
        }
        if (this.sex != null) {
            res.sex = this.sex;
        }
        if (this.ethnicGroup != null) {
            res.ethnicGroup = this.ethnicGroup;
        }
        if (this.rdfID != null) {
            res.rdfID = this.rdfID;
        }

        res.dontCreateOwlIntance = this.dontCreateOwlIntance;
        res.dontAddIndexAsPostFixToRdfInstanceName = this.dontAddIndexAsPostFixToRdfInstanceName;

        return res;
    }
}
