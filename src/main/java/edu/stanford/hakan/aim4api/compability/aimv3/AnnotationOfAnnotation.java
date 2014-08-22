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

import org.w3c.dom.NodeList;


import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan BULU
 */
@SuppressWarnings("serial")
public class AnnotationOfAnnotation extends Annotation{

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
    
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
        NodeList nodelist = node.getChildNodes();
        for (int i = 0; i < nodelist.getLength(); i++) {
            if ("referencedAnnotationCollection".equals(nodelist.item(i).getNodeName())) {
                this.referencedAnnotationCollection.setXMLNode(nodelist.item(i));
            }
        }
    }
}
