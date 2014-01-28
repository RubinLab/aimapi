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

import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class AnnotationOfAnnotation extends AnnotationEntity {

    public AnnotationOfAnnotation() {
        setXsiType("AnnotationOfAnnotation");
    }
    private AdjudicationObservation adjudicationObservation;
    private AnnotationOfAnnotationStatementCollection annotationOfAnnotationStatementCollection = new AnnotationOfAnnotationStatementCollection();

    public AdjudicationObservation getAdjudicationObservation() {
        return adjudicationObservation;
    }

    public void setAdjudicationObservation(AdjudicationObservation adjudicationObservation) {
        adjudicationObservation.setTagName("adjudicationObservation");
        this.adjudicationObservation = adjudicationObservation;
    }

    public AnnotationOfAnnotationStatementCollection getAnnotationOfAnnotationStatementCollection() {
        return annotationOfAnnotationStatementCollection;
    }

    public void setAnnotationOfAnnotationStatementCollection(AnnotationOfAnnotationStatementCollection annotationOfAnnotationStatementCollection) {
        this.annotationOfAnnotationStatementCollection = annotationOfAnnotationStatementCollection;
    }

    public void addAnnotationOfAnnotationStatement(AnnotationStatement newAnnotationOfAnnotationStatement) {
        this.annotationOfAnnotationStatementCollection.addAnnotationOfAnnotationStatement(newAnnotationOfAnnotationStatement);
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("AnnotationOfAnnotation");
        }
        Element res = (Element) super.getXMLNode(doc);
        if (this.adjudicationObservation != null) {
            res.appendChild(this.adjudicationObservation.getXMLNode(doc));
        }
        if (this.annotationOfAnnotationStatementCollection.size() > 0) {
            res.appendChild(this.annotationOfAnnotationStatementCollection.getXMLNode(doc));
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("adjudicationObservation".equals(currentNode.getNodeName())) {
                AdjudicationObservation obj = new AdjudicationObservation();
                obj.setXMLNode(currentNode);
                this.setAdjudicationObservation(obj);
            }
            if ("annotationOfAnnotationStatementCollection".equals(listChilds.item(i).getNodeName())) {
                this.annotationOfAnnotationStatementCollection.setXMLNode(listChilds.item(i));
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        AnnotationOfAnnotation oth = (AnnotationOfAnnotation) other;
        if (!this.adjudicationObservation.isEqualTo(oth.adjudicationObservation)) {
            return false;
        }
        if (!this.annotationOfAnnotationStatementCollection.isEqualTo(oth.annotationOfAnnotationStatementCollection)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    @Override
    public AnnotationOfAnnotation getClone() {
        AnnotationOfAnnotation res = new AnnotationOfAnnotation();
        if (this.getAdjudicationObservation() != null) {
            res.setAdjudicationObservation(this.getAdjudicationObservation().getClone());
        }
        if (this.getAnnotationOfAnnotationStatementCollection() != null) {
            res.setAnnotationOfAnnotationStatementCollection(this.getAnnotationOfAnnotationStatementCollection().getClone());
        }
        List<CD> listTypeCode = this.getListTypeCode();
        for (int i = 0; i < listTypeCode.size(); i++) {
            if (listTypeCode.get(i) != null) {
                res.addTypeCode(listTypeCode.get(i).getClone());
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
