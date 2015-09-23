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

import edu.stanford.hakan.aim4api.usage.AnnotationBuilder;
import edu.stanford.hakan.aim4api.usage.AnnotationConverter;
import edu.stanford.hakan.aim4api.utility.GenerateId;
import edu.stanford.hakan.aim4api.utility.Logger;
import edu.stanford.hakan.aim4api.utility.Utility;

import edu.stanford.hakan.aim4api.utility.XML;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class ImageAnnotationCollection extends AnnotationCollection {

    private Person person;
    private List<ImageAnnotation> listImageAnnotations = new ArrayList<ImageAnnotation>();

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        person.setTagName("person");
        this.person = person;
    }

    public void addImageAnnotation(ImageAnnotation newImageAnnotation) {
        newImageAnnotation.setImageAnnotationCollection(this);
        this.listImageAnnotations.add(newImageAnnotation);
    }

    public List<ImageAnnotation> getImageAnnotations() {
        return listImageAnnotations;
    }
    
    public ImageAnnotation getImageAnnotation() {
        if(this.listImageAnnotations.size() > 0)
            return this.listImageAnnotations.get(0);
        return null;
    }

    public void setImageAnnotations(List<ImageAnnotation> listImageAnnotations) {
        this.listImageAnnotations = listImageAnnotations;
        for (int i = 0; i < this.listImageAnnotations.size(); i++) {
            this.listImageAnnotations.get(i).setImageAnnotationCollection(this);
        }
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("ImageAnnotationCollection");
        }
        if (getAimVersion() == null || Enumerations.AimVersion.None.equals(getAimVersion())) {
            setAimVersion(Enumerations.AimVersion.AIMv4_0);
        }
        Element res = (Element) super.getXMLNode(doc);
        if (this.person != null) {
            res.appendChild(this.person.getXMLNode(doc));
        }

        if (this.listImageAnnotations.size() > 0) {
            Element imageAnnotations = doc.createElement("imageAnnotations");
            for (int i = 0; i < this.listImageAnnotations.size(); i++) {
                this.listImageAnnotations.get(i).setTagName("ImageAnnotation");
                imageAnnotations.appendChild(this.listImageAnnotations.get(i).getXMLNode(doc));
            }
            res.appendChild(imageAnnotations);
        }

//        //*** Audit trail operations.
//        if (this.getIsEdited()) {
//            this.setUniqueIdentifier(new II(GenerateId.getUUID()));
//            this.setDateTime(Utility.getNowAtGMT());
//        }
        return res;
    }

    public int getVersion() {
        return this.getImageAnnotations().get(0).getVersion();
    }

    public void setVersion(int version) {
        this.getImageAnnotations().get(0).setVersion(version);
    }
    
    @Override
    public void setXMLNode(Node node) {
        
        Logger.write("ImageAnnotationCollection-setXMLNode-s");
        
        this.listImageAnnotations.clear();

        NamedNodeMap map = node.getAttributes();
        if (map.getNamedItem("aimVersion") != null) {
            String tempVersion = map.getNamedItem("aimVersion").getNodeValue().replace('.', '_');
            if("3_0".equals(tempVersion))
                tempVersion = "AIM_3_0";
            else if("1_0".equals(tempVersion))
                tempVersion = "AIMv1_0";
            if(tempVersion.indexOf("^") > 0)
                tempVersion = tempVersion.split("\\^")[0];
            super.setAimVersion(Enumerations.AimVersion.valueOf(tempVersion));
        }

        if (Enumerations.AimVersion.AIMv4_0 != super.getAimVersion()) {
            try {
                node = AnnotationConverter.annotationV3ToV4(node);
                super.setAimVersion(Enumerations.AimVersion.AIMv4_0);
            } catch (AimException ex) {
            }
        }

        super.setXMLNode(node);
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("imageAnnotations".equals(currentNode.getNodeName())) {
                NodeList tempList = currentNode.getChildNodes();
                for (int j = 0; j < tempList.getLength(); j++) {
                    Node childNode = tempList.item(j);
                    if ("ImageAnnotation".equals(childNode.getNodeName())) {
                        ImageAnnotation obj = new ImageAnnotation();
                        obj.setXMLNode(childNode);
                        this.addImageAnnotation(obj);
                    }
                }
            }
            if ("person".equals(currentNode.getNodeName())) {
                Person obj = new Person();
                obj.setXMLNode(currentNode);
                this.setPerson(obj);
            }
        }
//        //*** Setting the initialState. I will use it while saving operation, if the class is updated or not.
//        this.initialState = this.getClone();
        
        if(this.getImageAnnotation() != null && this.getImageAnnotation().getAuditTrailCollection() != null &&this.getImageAnnotation().getAuditTrailCollection().getAuditTrailList().size() > 0 )
        {
        AuditTrail auditTrail = this.getImageAnnotation().getAuditTrailCollection().getAuditTrailList().get(0);
        this.setVersion(Integer.parseInt(auditTrail.getComment().getValue()));
        }
        
        Logger.write("--------- iac " + this.getImageAnnotation().getComment().getValue());
        Logger.write("ImageAnnotationCollection-setXMLNode-e");
    }

//    public boolean getIsEdited() {
//        if (this.initialState == null) {
//            return false;
//        }
//        return !this.isEqualTo(this.initialState);
//    }
//
//    public ImageAnnotationCollection getInitialState() {
//        return (ImageAnnotationCollection) this.initialState;
//    }
    
    public boolean getIsEdited() {
        for(ImageAnnotation ia:this.getImageAnnotations())
            if(ia.getIsEdited())
                return true;
        return false;
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet.");
        // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        ImageAnnotationCollection oth = (ImageAnnotationCollection) other;
        if (this.person == null ? oth.person != null : !this.person.isEqualTo(oth.person)) {
            return false;
        }
        if (this.listImageAnnotations.size() != oth.listImageAnnotations.size()) {
            return false;
        }
        for (int i = 0; i < this.listImageAnnotations.size(); i++) {
            if (this.listImageAnnotations.get(i) == null ? oth.listImageAnnotations.get(i) != null : !this.listImageAnnotations.get(i).isEqualTo(oth.listImageAnnotations.get(i))) {
                return false;
            }
        }
        return super.isEqualTo(other);
    }

    @Override
    public ImageAnnotationCollection getClone() {
        ImageAnnotationCollection res = new ImageAnnotationCollection();
        if (this.getPerson() != null) {
            res.setPerson(this.getPerson().getClone());
        }
        for (int i = 0; i < this.listImageAnnotations.size(); i++) {
            if (this.listImageAnnotations.get(i) != null) {
                res.addImageAnnotation(this.listImageAnnotations.get(i).getClone());
            }
        }
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
    
    
    public String getXMLString() throws AimException
    {
      return AnnotationBuilder.convertToString(this);
    }
    
    public String toStringXML() throws AimException
    {
      return getXMLString();
    }
    
    public String getXMLStringGWT() throws AimException {

        return convertToStringGWT(this);
    }

    public static String convertToStringGWT(ImageAnnotationCollection Anno) throws AimException {
        try {
            Document doc = XML.createDocument();
            Element root = (Element) Anno.getXMLNode(doc);
            XML.setBaseAttributes(root);
            doc.appendChild(root);
            return doc.toString();
        } catch (Exception ex) {
            throw new AimException("XML Convertion operation is Unsuccessful (Method Name; convertToString): "
                    + ex.getMessage());
        }
    }

    public String toStringXMLGWT() throws AimException {
        return getXMLStringGWT();
    }
}