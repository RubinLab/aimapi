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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author localadmin
 */
public class AnnotationEntity extends Entity {

    public AnnotationEntity() {
        setXsiType("AnnotationEntity");
    }
    private List<CD> listTypeCode = new ArrayList<>();
    private String dateTime;
    private ST name;
    private ST comment;
    private II precedentReferencedAnnotationUid;
    private II templateUid;
    private AuditTrailCollection auditTrailCollection = new AuditTrailCollection();
    private ImagingPhysicalEntityCollection imagingPhysicalEntityCollection = new ImagingPhysicalEntityCollection();
    private CalculationEntityCollection calculationEntityCollection = new CalculationEntityCollection();
    private InferenceEntityCollection inferenceEntityCollection = new InferenceEntityCollection();
    private AnnotationRoleEntityCollection annotationRoleEntityCollection = new AnnotationRoleEntityCollection();
    private LesionObservationEntityCollection lesionObservationEntityCollection = new LesionObservationEntityCollection();
    private ImagingObservationEntityCollection imagingObservationEntityCollection = new ImagingObservationEntityCollection();
    private TaskContextEntityCollection taskContextEntityCollection = new TaskContextEntityCollection();

    public List<CD> getListTypeCode() {
        return this.listTypeCode;
    }

    public void setTypeCode(List<CD> listTypeCode) {
        this.listTypeCode = listTypeCode;
        for (int i = 0; i < this.listTypeCode.size(); i++) {
            this.listTypeCode.get(i).setTagName("typeCode");
        }
    }

    public void addTypeCode(CD newTypeCode) {
        newTypeCode.setTagName("typeCode");
        this.listTypeCode.add(newTypeCode);
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public ST getName() {
        return name;
    }

    public void setName(ST name) {
        name.setTagName("name");
        this.name = name;
    }

    public ST getComment() {
        return comment;
    }

    public void setComment(ST comment) {
        comment.setTagName("comment");
        this.comment = comment;
    }

    public II getPrecedentReferencedAnnotationUid() {
        return precedentReferencedAnnotationUid;
    }

    public void setPrecedentReferencedAnnotationUid(II precedentReferencedAnnotationUid) {
        precedentReferencedAnnotationUid.setTagName("precedentReferencedAnnotationUid");
        this.precedentReferencedAnnotationUid = precedentReferencedAnnotationUid;
    }

    public II getTemplateUid() {
        return templateUid;
    }

    public void setTemplateUid(II templateUid) {
        templateUid.setTagName("templateUid");
        this.templateUid = templateUid;
    }

    public AuditTrailCollection getAuditTrailCollection() {
        return auditTrailCollection;
    }

    public void setAuditTrailCollection(AuditTrailCollection auditTrailCollection) {
        this.auditTrailCollection = auditTrailCollection;
    }

    public void addAuditTrail(AuditTrail newAuditTrail) {
        this.auditTrailCollection.addAuditTrail(newAuditTrail);
    }

    public ImagingPhysicalEntityCollection getImagingPhysicalEntityCollection() {
        return imagingPhysicalEntityCollection;
    }

    public void setImagingPhysicalEntityCollection(ImagingPhysicalEntityCollection imagingPhysicalEntityCollection) {
        this.imagingPhysicalEntityCollection = imagingPhysicalEntityCollection;
    }

    public void addImagingPhysicalEntity(ImagingPhysicalEntity newImagingPhysicalEntity) {
        this.imagingPhysicalEntityCollection.addImagingPhysicalEntity(newImagingPhysicalEntity);
    }

    public CalculationEntityCollection getCalculationEntityCollection() {
        return calculationEntityCollection;
    }

    public void setCalculationEntityCollection(CalculationEntityCollection calculationEntityCollection) {
        this.calculationEntityCollection = calculationEntityCollection;
    }

    public void addCalculationEntity(CalculationEntity newCalculationEntity) {
        this.calculationEntityCollection.addCalculationEntity(newCalculationEntity);
    }

    public InferenceEntityCollection getInferenceEntityCollection() {
        return inferenceEntityCollection;
    }

    public void setInferenceEntityCollection(InferenceEntityCollection inferenceEntityCollection) {
        this.inferenceEntityCollection = inferenceEntityCollection;
    }

    public void addInferenceEntity(InferenceEntity newInferenceEntity) {
        this.inferenceEntityCollection.addInferenceEntity(newInferenceEntity);
    }

    public AnnotationRoleEntityCollection getAnnotationRoleEntityCollection() {
        return annotationRoleEntityCollection;
    }

    public void setAnnotationRoleEntityCollection(AnnotationRoleEntityCollection annotationRoleEntityCollection) {
        this.annotationRoleEntityCollection = annotationRoleEntityCollection;
    }

    public void addAnnotationRoleEntity(AnnotationRoleEntity newAnnotationRoleEntity) {
        this.annotationRoleEntityCollection.addAnnotationRoleEntity(newAnnotationRoleEntity);
    }

    public LesionObservationEntityCollection getLesionObservationEntityCollection() {
        return lesionObservationEntityCollection;
    }

    public void setLesionObservationEntityCollection(LesionObservationEntityCollection lesionObservationEntityCollection) {
        this.lesionObservationEntityCollection = lesionObservationEntityCollection;
    }

    public void addLesionObservationEntity(LesionObservationEntity newLesionObservationEntity) {
        this.lesionObservationEntityCollection.addLesionObservationEntity(newLesionObservationEntity);
    }

    public ImagingObservationEntityCollection getImagingObservationEntityCollection() {
        return imagingObservationEntityCollection;
    }

    public void setImagingObservationEntityCollection(ImagingObservationEntityCollection imagingObservationEntityCollection) {
        this.imagingObservationEntityCollection = imagingObservationEntityCollection;
    }

    public void addImagingObservationEntity(ImagingObservationEntity newImagingObservationEntity) {
        this.imagingObservationEntityCollection.addImagingObservationEntity(newImagingObservationEntity);
    }

    public TaskContextEntityCollection getTaskContextEntityCollection() {
        return taskContextEntityCollection;
    }

    public void setTaskContextEntityCollection(TaskContextEntityCollection taskContextEntityCollection) {
        this.taskContextEntityCollection = taskContextEntityCollection;
    }

    public void addTaskContextEntity(TaskContextEntity newTaskContextEntity) {
        this.taskContextEntityCollection.addTaskContextEntity(newTaskContextEntity);
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("AnnotationEntity");
        }
        Element res = (Element) super.getXMLNode(doc);
        if (this.listTypeCode.size() > 0) {
            for (int i = 0; i < this.listTypeCode.size(); i++) {
                res.appendChild(this.listTypeCode.get(i).getXMLNode(doc));
            }
        }
        if (this.dateTime != null) {
            Element el_dateTime = doc.createElement("dateTime");
            el_dateTime.setAttribute("value", this.dateTime.toString());
            res.appendChild(el_dateTime);
        }
        if (this.name != null) {
            res.appendChild(this.name.getXMLNode(doc));
        }
        if (this.comment != null) {
            res.appendChild(this.comment.getXMLNode(doc));
        }
        if (this.precedentReferencedAnnotationUid != null) {
            res.appendChild(this.precedentReferencedAnnotationUid.getXMLNode(doc));
        }
        if (this.templateUid != null) {
            res.appendChild(this.templateUid.getXMLNode(doc));
        }
        if (this.auditTrailCollection.size() > 0) {
            res.appendChild(this.auditTrailCollection.getXMLNode(doc));
        }
        if (this.imagingPhysicalEntityCollection.size() > 0) {
            res.appendChild(this.imagingPhysicalEntityCollection.getXMLNode(doc));
        }
        if (this.calculationEntityCollection.size() > 0) {
            res.appendChild(this.calculationEntityCollection.getXMLNode(doc));
        }
        if (this.inferenceEntityCollection.size() > 0) {
            res.appendChild(this.inferenceEntityCollection.getXMLNode(doc));
        }
        if (this.annotationRoleEntityCollection.size() > 0) {
            res.appendChild(this.annotationRoleEntityCollection.getXMLNode(doc));
        }
        if (this.lesionObservationEntityCollection.size() > 0) {
            res.appendChild(this.lesionObservationEntityCollection.getXMLNode(doc));
        }
        if (this.imagingObservationEntityCollection.size() > 0) {
            res.appendChild(this.imagingObservationEntityCollection.getXMLNode(doc));
        }
        if (this.taskContextEntityCollection.size() > 0) {
            res.appendChild(this.taskContextEntityCollection.getXMLNode(doc));
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
        this.listTypeCode.clear();
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("typeCode".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.addTypeCode(obj);
            }
            if ("dateTime".equals(currentNode.getNodeName())) {
                this.dateTime = currentNode.getAttributes().getNamedItem("value").getNodeValue();
            }
            if ("name".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setName(obj);
            }
            if ("comment".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setComment(obj);
            }
            if ("precedentReferencedAnnotationUid".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setPrecedentReferencedAnnotationUid(obj);
            }
            if ("templateUid".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setTemplateUid(obj);
            }
            if ("auditTrailCollection".equals(listChilds.item(i).getNodeName())) {
                this.auditTrailCollection.setXMLNode(listChilds.item(i));
            }
            if ("imagingPhysicalEntityCollection".equals(listChilds.item(i).getNodeName())) {
                this.imagingPhysicalEntityCollection.setXMLNode(listChilds.item(i));
            }
            if ("calculationEntityCollection".equals(listChilds.item(i).getNodeName())) {
                this.calculationEntityCollection.setXMLNode(listChilds.item(i));
            }
            if ("inferenceEntityCollection".equals(listChilds.item(i).getNodeName())) {
                this.inferenceEntityCollection.setXMLNode(listChilds.item(i));
            }
            if ("annotationRoleEntityCollection".equals(listChilds.item(i).getNodeName())) {
                this.annotationRoleEntityCollection.setXMLNode(listChilds.item(i));
            }
            if ("lesionObservationEntityCollection".equals(listChilds.item(i).getNodeName())) {
                this.lesionObservationEntityCollection.setXMLNode(listChilds.item(i));
            }
            if ("imagingObservationEntityCollection".equals(listChilds.item(i).getNodeName())) {
                this.imagingObservationEntityCollection.setXMLNode(listChilds.item(i));
            }
            if ("taskContextEntityCollection".equals(listChilds.item(i).getNodeName())) {
                this.taskContextEntityCollection.setXMLNode(listChilds.item(i));
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        AnnotationEntity oth = (AnnotationEntity) other;
        if (this.listTypeCode.size() != oth.listTypeCode.size()) {
            return false;
        }
        for (int i = 0; i < this.listTypeCode.size(); i++) {
            if (!this.listTypeCode.get(i).isEqualTo(oth.listTypeCode.get(i))) {
                return false;
            }
        }
        if (this.dateTime == null ? oth.dateTime != null : !this.dateTime.equals(oth.dateTime)) {
            return false;
        }
        if (!this.name.isEqualTo(oth.name)) {
            return false;
        }
        if (!this.comment.isEqualTo(oth.comment)) {
            return false;
        }
        if (!this.precedentReferencedAnnotationUid.isEqualTo(oth.precedentReferencedAnnotationUid)) {
            return false;
        }
        if (!this.templateUid.isEqualTo(oth.templateUid)) {
            return false;
        }
        if (!this.auditTrailCollection.isEqualTo(oth.auditTrailCollection)) {
            return false;
        }
        if (!this.imagingPhysicalEntityCollection.isEqualTo(oth.imagingPhysicalEntityCollection)) {
            return false;
        }
        if (!this.calculationEntityCollection.isEqualTo(oth.calculationEntityCollection)) {
            return false;
        }
        if (!this.inferenceEntityCollection.isEqualTo(oth.inferenceEntityCollection)) {
            return false;
        }
        if (!this.annotationRoleEntityCollection.isEqualTo(oth.annotationRoleEntityCollection)) {
            return false;
        }
        if (!this.lesionObservationEntityCollection.isEqualTo(oth.lesionObservationEntityCollection)) {
            return false;
        }
        if (!this.imagingObservationEntityCollection.isEqualTo(oth.imagingObservationEntityCollection)) {
            return false;
        }
        if (!this.taskContextEntityCollection.isEqualTo(oth.taskContextEntityCollection)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    @Override
    public AnnotationEntity getClone() {
        AnnotationEntity res = new AnnotationEntity();
        for (int i = 0; i < this.listTypeCode.size(); i++) {
            if (this.listTypeCode.get(i) != null) {
                res.addTypeCode(this.listTypeCode.get(i).getClone());
            }
        }
        if (this.getDateTime() != null) {
            res.setDateTime(this.getDateTime());
        }
        if (this.getName() != null) {
            res.setName(this.getName().getClone());
        }
        if (this.getComment() != null) {
            res.setComment(this.getComment().getClone());
        }
        if (this.getPrecedentReferencedAnnotationUid() != null) {
            res.setPrecedentReferencedAnnotationUid(this.getPrecedentReferencedAnnotationUid().getClone());
        }
        if (this.getTemplateUid() != null) {
            res.setTemplateUid(this.getTemplateUid().getClone());
        }
        if (this.getAuditTrailCollection() != null) {
            res.setAuditTrailCollection(this.getAuditTrailCollection().getClone());
        }
        if (this.getImagingPhysicalEntityCollection() != null) {
            res.setImagingPhysicalEntityCollection(this.getImagingPhysicalEntityCollection().getClone());
        }
        if (this.getCalculationEntityCollection() != null) {
            res.setCalculationEntityCollection(this.getCalculationEntityCollection().getClone());
        }
        if (this.getInferenceEntityCollection() != null) {
            res.setInferenceEntityCollection(this.getInferenceEntityCollection().getClone());
        }
        if (this.getAnnotationRoleEntityCollection() != null) {
            res.setAnnotationRoleEntityCollection(this.getAnnotationRoleEntityCollection().getClone());
        }
        if (this.getLesionObservationEntityCollection() != null) {
            res.setLesionObservationEntityCollection(this.getLesionObservationEntityCollection().getClone());
        }
        if (this.getImagingObservationEntityCollection() != null) {
            res.setImagingObservationEntityCollection(this.getImagingObservationEntityCollection().getClone());
        }
        if (this.getTaskContextEntityCollection() != null) {
            res.setTaskContextEntityCollection(this.getTaskContextEntityCollection().getClone());
        }
        if (this.getUniqueIdentifier() != null) {
            res.setUniqueIdentifier(this.getUniqueIdentifier().getClone());
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
