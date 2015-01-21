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
public class DICOMImageReference extends ImageReference implements IAimXMLOperations {

    private ImageStudy ImageStudy;
    private PresentationStateCollection presentationStateCollection;

    public DICOMImageReference() {
        setXsiType("DICOMImageReference");
        this.presentationStateCollection = new PresentationStateCollection();
    }

    public DICOMImageReference(Integer cagridId) {
        setCagridId(cagridId);
        setXsiType("DICOMImageReference");
        this.presentationStateCollection = new PresentationStateCollection();
    }

    public ImageStudy getImageStudy() {
        return ImageStudy;
    }

    public void setImageStudy(ImageStudy imageStudy) {
        this.ImageStudy = imageStudy;
    }

    public PresentationStateCollection getPresentationStateCollection() {
        return presentationStateCollection;
    }

    public void setPresentationStateCollection(PresentationStateCollection presentationStateCollection) {
        this.presentationStateCollection = presentationStateCollection;
    }

    public void addPresentationState(PresentationState presentationState) {
        this.presentationStateCollection.addPresentationState(presentationState);
    }

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//
//        this.Control();
//
//        Element imageReference = (Element) super.getXMLNode(doc);
//        Element imageStudy = doc.createElement("imageStudy");
//        imageStudy.appendChild(this.ImageStudy.getXMLNode(doc));
//        imageReference.appendChild(imageStudy);
//        if (this.getPresentationStateCollection() != null && this.getPresentationStateCollection().getPresentationStateList().size() > 0) {
//            imageReference.appendChild(this.getPresentationStateCollection().getXMLNode(doc));
//        }
//
//        return imageReference;
//    }
    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);

        NodeList listChils = node.getChildNodes();
        for (int i = 0; i < listChils.getLength(); i++) {
            if ("presentationStateCollection".equals(listChils.item(i).getNodeName())) {
                this.presentationStateCollection.setXMLNode(listChils.item(i));
            } else if ("imageStudy".equals(listChils.item(i).getNodeName())) {
                NodeList tempList = listChils.item(i).getChildNodes();
                for (int j = 0; j < tempList.getLength(); j++) {
                    if ("ImageStudy".equals(tempList.item(j).getNodeName())) {
                        ImageStudy obj = new ImageStudy();
                        obj.setXMLNode(tempList.item(j));
                        this.setImageStudy(obj);
                    }
                }
            }
        }
    }

    private void Control() {
    }

    @Override
    public boolean isEqualTo(Object other) {
        DICOMImageReference oth = (DICOMImageReference) other;
        if (this.ImageStudy == null ? oth.ImageStudy != null : !this.ImageStudy.isEqualTo(oth.ImageStudy)) {
            return false;
        }
        if (!this.presentationStateCollection.isEqualTo(oth.presentationStateCollection)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    public edu.stanford.hakan.aim4api.base.ImageReferenceEntity toAimV4() {
        edu.stanford.hakan.aim4api.base.DicomImageReferenceEntity res = new edu.stanford.hakan.aim4api.base.DicomImageReferenceEntity();
        res.setImageStudy(this.getImageStudy().toAimV4());
        return res;
    }

    public DICOMImageReference(edu.stanford.hakan.aim4api.base.DicomImageReferenceEntity v4) {
        edu.stanford.hakan.aim4api.base.DicomImageReferenceEntity dicomImageReferenceEntity = (edu.stanford.hakan.aim4api.base.DicomImageReferenceEntity) v4;
        setXsiType("DICOMImageReference");
        this.setCagridId(0);
        if (dicomImageReferenceEntity.getImageStudy() != null) {
            this.setImageStudy(new ImageStudy(dicomImageReferenceEntity.getImageStudy()));
        }
    }

    @Override
    public DICOMImageReference getClone() {
        DICOMImageReference res = new DICOMImageReference();
        if (this.getCagridId() != null) {
            res.setCagridId(this.getCagridId());
        }
        if (this.getXsiType() != null) {
            res.setXsiType(this.getXsiType());
        }
        if (this.ImageStudy != null) {
            res.ImageStudy = this.ImageStudy.getClone();
        }
        if (this.presentationStateCollection != null) {
            res.presentationStateCollection = this.presentationStateCollection.getClone();
        }
        return res;
    }
}
