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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hakan BULU
 */
@SuppressWarnings("serial")
public class AnnotationOfAnnotation extends Annotation implements IAimXMLOperations {

    private ReferencedAnnotationCollection referencedAnnotationCollection;

    public AnnotationOfAnnotation() {
        super();
        this.referencedAnnotationCollection = new ReferencedAnnotationCollection();
        setXsiType("AnnotationOfAnnotation");
    }

    public AnnotationOfAnnotation(Integer cagridId, String comment, String dateTime, String name, String codeValue, String codeMeaning, String codingSchemeDesignator, String codingSchemeVersion, String precedentReferencedAnnotationUID) {
        super(cagridId, comment, dateTime, name, codeValue, codeMeaning, codingSchemeDesignator, codingSchemeVersion, precedentReferencedAnnotationUID);
        this.referencedAnnotationCollection = new ReferencedAnnotationCollection();
        setXsiType("AnnotationOfAnnotation");
    }

    public void addReferencedAnnotation(ReferencedAnnotation newReferencedAnnotation) {
        this.referencedAnnotationCollection.AddReferencedAnnotation(newReferencedAnnotation);
    }

    public ReferencedAnnotationCollection getReferencedAnnotationCollection() {
        return referencedAnnotationCollection;
    }

    public void setReferencedAnnotationCollection(ReferencedAnnotationCollection referencedAnnotationCollection) {
        this.referencedAnnotationCollection = referencedAnnotationCollection;
    }

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//
//        Element annotation = (Element) super.getXMLNode(doc);
//
//        if (this.getReferencedAnnotationCollection().getReferencedAnnotationList().size() > 0) {
//            annotation.appendChild(this.getReferencedAnnotationCollection().getXMLNode(doc));
//        }
//        return annotation;
//
//    }
    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
        NodeList nodelist = node.getChildNodes();
        for (int i = 0; i < nodelist.getLength(); i++) {
            if ("referencedAnnotationCollection".equals(nodelist.item(i).getNodeName())) {
                this.referencedAnnotationCollection.setXMLNode(nodelist.item(i));
            }
        }
    }

    @Override
    public boolean isEqualTo(Object other) {
        AnnotationOfAnnotation oth = (AnnotationOfAnnotation) other;
        if (!this.referencedAnnotationCollection.isEqualTo(oth.referencedAnnotationCollection)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    @Override
    public AnnotationOfAnnotation getClone() {
        AnnotationOfAnnotation res = new AnnotationOfAnnotation();

        if (this.getCalculationCollection() != null) {
            res.setCalculationCollection(this.getCalculationCollection().getClone());
        }
        if (this.getInferenceCollection() != null) {
           res.setInferenceCollection(this.getInferenceCollection().getClone());
        }
        if (this.getAnatomicEntityCollection() != null) {
            res.setAnatomicEntityCollection(this.getAnatomicEntityCollection().getClone());
        }
        if (this.getImagingObservationCollection() != null) {
            res.setImagingObservationCollection(this.getImagingObservationCollection().getClone());
        }
        for (int i = 0; i < this.getListUser().size(); i++) {
            if (this.getListUser().get(i) != null) {
                res.addUser(this.getListUser().get(i).getClone());
            }
        }
        for (int i = 0; i < this.getListEquipment().size(); i++) {
            if (this.getListEquipment().get(i) != null) {
                res.addEquipment(this.getListEquipment().get(i).getClone());
            }
        }
        for (int i = 0; i < this.getListAimStatus().size(); i++) {
            if (this.getListAimStatus().get(i) != null) {
                res.addAimStatus(this.getListAimStatus().get(i).getClone());
            }
        }
        if (this.getCagridId() != null) {
            res.setCagridId(this.getCagridId());
        }
        if (this.getAimVersion() != null) {
            res.setAimVersion(this.getAimVersion(), "al536anhb55555");
        }
        if (this.getComment() != null) {
            res.setComment(this.getComment());
        }
        if (this.getDateTime() != null) {
            res.setDateTime(this.getDateTime());
        }
        if (this.getName() != null) {
            res.setName(this.getName());
        }
        if (this.getUniqueIdentifier() != null) {
            res.setUniqueIdentifier(this.getUniqueIdentifier(), "al536anhb55555");
        }
        if (this.getCodeValue() != null) {
            res.setCodeValue(this.getCodeValue());
        }
        if (this.getCodeMeaning() != null) {
            res.setCodeMeaning(this.getCodeMeaning());
        }
        if (this.getCodingSchemeDesignator() != null) {
            res.setCodingSchemeDesignator(this.getCodingSchemeDesignator());
        }
        if (this.getCodingSchemeVersion() != null) {
            res.setCodingSchemeVersion( this.getCodingSchemeVersion());
        }
        if (this.getPrecedentReferencedAnnotationUID() != null) {
            res.setPrecedentReferencedAnnotationUID(this.getPrecedentReferencedAnnotationUID());
        }
        if (this.getXsiType() != null) {
            res.setXsiType(this.getXsiType());
        }
        if (this.getOntologyPrefix() != null) {
            res.setOntologyPrefix(this.getOntologyPrefix());
        }
        if (this.getReferencedAnnotationCollection() != null) {
            res.setReferencedAnnotationCollection(this.getReferencedAnnotationCollection().getClone());
        }
        return res;
    }

}
