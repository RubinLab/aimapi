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
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hakan BULU
 */
public class ImageReferenceCollection implements IAimXMLOperations {

    private List<ImageReference> listImageReference = new ArrayList<ImageReference>();

    ImageReferenceCollection() {
    }

    public void AddImageReference(ImageReference newImageReference) {
        this.listImageReference.add(newImageReference);
    }

    public List<ImageReference> getImageReferenceList() {
        return this.listImageReference;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        Element imageReferenceCollection = doc.createElement("imageReferenceCollection");
        for (int i = 0; i < this.listImageReference.size(); i++) {
            imageReferenceCollection.appendChild(this.listImageReference.get(i).getXMLNode(doc));
        }
        return imageReferenceCollection;
    }

    @Override
    public void setXMLNode(Node node) {

        this.listImageReference.clear();

        NodeList tempList = node.getChildNodes();
        for (int j = 0; j < tempList.getLength(); j++) {
            if ("ImageReference".equals(tempList.item(j).getNodeName())) {
                NamedNodeMap map = tempList.item(j).getAttributes();
                if ("DICOMImageReference".equals(map.getNamedItem("xsi:type").getNodeValue())) {
                    DICOMImageReference obj = new DICOMImageReference();
                    obj.setXMLNode(tempList.item(j));
                    this.AddImageReference(obj);
                } else if ("WebImageReference".equals(map.getNamedItem("xsi:type").getNodeValue())) {
                    WebImageReference obj = new WebImageReference();
                    obj.setXMLNode(tempList.item(j));
                    this.AddImageReference(obj);
                }
            }
        }
    }

    public boolean isEqualTo(Object other) {
        ImageReferenceCollection oth = (ImageReferenceCollection) other;
        if (this.listImageReference.size() != oth.listImageReference.size()) {
            return false;
        }
        for (int i = 0; i < this.listImageReference.size(); i++) {
            if (!this.listImageReference.get(i).isEqualTo(oth.listImageReference.get(i))) {
                return false;
            }
        }
        return true;
    }

    public edu.stanford.hakan.aim4api.base.ImageReferenceEntityCollection toAimV4() {
        edu.stanford.hakan.aim4api.base.ImageReferenceEntityCollection res = new edu.stanford.hakan.aim4api.base.ImageReferenceEntityCollection();
        List<ImageReference> list = this.getImageReferenceList();
        for (ImageReference itemV3 : list) {
            DICOMImageReference itemV3Plus = (DICOMImageReference) itemV3;
            res.addImageReferenceEntity(itemV3Plus.toAimV4());
        }
        return res;
    }

    public ImageReferenceCollection(edu.stanford.hakan.aim4api.base.ImageReferenceEntityCollection v4) {
        List<edu.stanford.hakan.aim4api.base.ImageReferenceEntity> listV4 = v4.getImageReferenceEntityList();
        for (edu.stanford.hakan.aim4api.base.ImageReferenceEntity itemV4 : listV4) {
            if ("DicomImageReferenceEntity".equals(itemV4.getXsiType())) {
                this.AddImageReference(new DICOMImageReference((edu.stanford.hakan.aim4api.base.DicomImageReferenceEntity) itemV4));
            }
        }
    }
}
