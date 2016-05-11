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

import edu.stanford.hakan.aim4api.utility.GenerateId;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class AnnotationCollection implements IAimXMLOperations {

    private II uniqueIdentifier;
    private ST description;
    private String dateTime;
    private User user;
    private Equipment equipment;
    private Enumerations.AimVersion aimVersion;
    private String tagName;
    private String xsiType;

    public AnnotationCollection() {
        this.setUniqueIdentifier(new II(GenerateId.getUUID()));
    }

    public II getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(II uniqueIdentifier) {
        uniqueIdentifier.setTagName("uniqueIdentifier");
        this.uniqueIdentifier = uniqueIdentifier;
    }    
    
    public String  refreshUniqueIdentifier() {
        this.setUniqueIdentifier(new II(GenerateId.getUUID()));
        return this.getUniqueIdentifier().getRoot();
    }

    public ST getDescription() {
        return description;
    }

    public void setDescription(ST description) {
        if(description != null)
        description.setTagName("description");
        this.description = description;
    }

    public String getDateTime() {
    	String date=dateTime;
    	if (!date.contains("-")) {//new format change to old
			if (date.length()==14)
				date=date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8)+"T"+date.substring(8,10)+":"+date.substring(10,12)+":"+date.substring(12,14);
			else
				date=date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
				
		}
        return date;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        user.setTagName("user");
        this.user = user;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        equipment.setTagName("equipment");
        this.equipment = equipment;
    }

    public Enumerations.AimVersion getAimVersion() {
        return aimVersion;
    }

    protected void setAimVersion(Enumerations.AimVersion aimVersion) {
        this.aimVersion = aimVersion;
    }

    protected String getTagName() {
        return tagName;
    }

    protected void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getXsiType() {
        return xsiType;
    }

    protected void setXsiType(String xsiType) {
        this.xsiType = xsiType;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("AnnotationCollection");
        }
        Element res = doc.createElement(getTagName());
        if (this.uniqueIdentifier == null) {
            this.setUniqueIdentifier(new II(GenerateId.getUUID()));
        }
        res.appendChild(this.uniqueIdentifier.getXMLNode(doc));
        if (this.description != null) {
            res.appendChild(this.description.getXMLNode(doc));
        }
        if (this.dateTime != null) {
            Element el_dateTime = doc.createElement("dateTime");
            el_dateTime.setAttribute("value", this.dateTime.toString());
            res.appendChild(el_dateTime);
        }
        if (this.user != null) {
            res.appendChild(this.user.getXMLNode(doc));
        }
        if (this.equipment != null) {
            res.appendChild(this.equipment.getXMLNode(doc));
        }
        if (this.aimVersion != null) {
            res.setAttribute("aimVersion", this.aimVersion.toString());
        }
        if (this.xsiType != null && (this.xsiType == null ? this.getTagName() != null : !this.xsiType.equals(this.getTagName()))) {
            res.setAttribute("xsi:type", this.xsiType.toString());
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        
        NamedNodeMap map = node.getAttributes();
//        if (map.getNamedItem("aimVersion") != null) {
//            this.aimVersion = Enumerations.AimVersion.valueOf(map.getNamedItem("aimVersion").getNodeValue().replace('.', '_'));
//        }
//        
//        if(Enumerations.AimVersion.AIMv4_0 != this.aimVersion )
//        {
//            try {
//                node = AnnotationConverter.annotationV3ToV4(node);
//                this.aimVersion = Enumerations.AimVersion.AIMv4_0;
//            } catch (AimException ex) {
//                Logger.getLogger(AnnotationCollection.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        
        if (map.getNamedItem("xsiType") != null) {
            this.xsiType = map.getNamedItem("xsiType").getNodeValue();
        }
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("uniqueIdentifier".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setUniqueIdentifier(obj);
            }
            if ("description".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setDescription(obj);
            }
            if ("dateTime".equals(currentNode.getNodeName())) {
                this.dateTime = currentNode.getAttributes().getNamedItem("value").getNodeValue();
            }
            if ("user".equals(currentNode.getNodeName())) {
                User obj = new User();
                obj.setXMLNode(currentNode);
                this.setUser(obj);
            }
            if ("equipment".equals(currentNode.getNodeName())) {
                Equipment obj = new Equipment();
                obj.setXMLNode(currentNode);
                this.setEquipment(obj);
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        AnnotationCollection oth = (AnnotationCollection) other;
        if (this.uniqueIdentifier == null ? oth.uniqueIdentifier != null : !this.uniqueIdentifier.isEqualTo(oth.uniqueIdentifier)) {
            return false;
        }
        if (this.description == null ? oth.description != null : !this.description.isEqualTo(oth.description)) {
            return false;
        }
        if (this.dateTime == null ? oth.dateTime != null : !this.dateTime.equals(oth.dateTime)) {
            return false;
        }
        if (this.user == null ? oth.user != null : !this.user.isEqualTo(oth.user)) {
            return false;
        }
        if (this.equipment == null ? oth.equipment != null : !this.equipment.isEqualTo(oth.equipment)) {
            return false;
        }
        if (this.aimVersion == null ? oth.aimVersion != null : !this.aimVersion.equals(oth.aimVersion)) {
            return false;
        }
        return true;
    }

    public AnnotationCollection getClone() {
        AnnotationCollection res = new AnnotationCollection();
        if (this.getUniqueIdentifier() != null) {
            res.setUniqueIdentifier(this.getUniqueIdentifier().getClone());
        }
        if (this.getDescription() != null) {
            res.setDescription(this.getDescription().getClone());
        }
        if (this.getDateTime() != null) {
            res.setDateTime(this.getDateTime());
        }
        if (this.getUser() != null) {
            res.setUser(this.getUser().getClone());
        }
        if (this.getEquipment() != null) {
            res.setEquipment(this.getEquipment().getClone());
        }
        if (this.getAimVersion() != null) {
            res.setAimVersion(this.getAimVersion());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        if (this.getXsiType() != null) {
            res.setXsiType(this.getXsiType());
        }
        return res;
    }
}
