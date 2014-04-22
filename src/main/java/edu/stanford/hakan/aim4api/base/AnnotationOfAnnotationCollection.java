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

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author localadmin
 */
public class AnnotationOfAnnotationCollection extends AnnotationCollection {

    private final List<AnnotationOfAnnotation> listAnnotationOfAnnotation = new ArrayList<AnnotationOfAnnotation>();

    public void addAnnotationOfAnnotation(AnnotationOfAnnotation newAnnotationOfAnnotation) {
        this.listAnnotationOfAnnotation.add(newAnnotationOfAnnotation);
    }

    public List<AnnotationOfAnnotation> getAnnotationOfAnnotationList() {
        return this.listAnnotationOfAnnotation;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools
// | Templates.
    }

    @Override
    public void setXMLNode(Node node) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools
// | Templates.
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools
// | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        AnnotationOfAnnotationCollection oth = (AnnotationOfAnnotationCollection) other;
        if (this.listAnnotationOfAnnotation.size() != oth.listAnnotationOfAnnotation.size()) {
            return false;
        }
        for (int i = 0; i < this.listAnnotationOfAnnotation.size(); i++) {
            if (this.listAnnotationOfAnnotation.get(i) == null ? oth.listAnnotationOfAnnotation.get(i) != null : !this.listAnnotationOfAnnotation.get(i).isEqualTo(oth.listAnnotationOfAnnotation.get(i))) {
                return false;
            }
        }
        return super.isEqualTo(other);
    }

    @Override
    public AnnotationOfAnnotationCollection getClone() {
        AnnotationOfAnnotationCollection res = new AnnotationOfAnnotationCollection();
        for (int i = 0; i < this.listAnnotationOfAnnotation.size(); i++) {
            if (this.listAnnotationOfAnnotation.get(i) != null) {
                res.addAnnotationOfAnnotation(this.listAnnotationOfAnnotation.get(i).getClone());
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
}
