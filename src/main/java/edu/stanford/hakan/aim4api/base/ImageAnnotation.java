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

import edu.stanford.hakan.aim4api.plugin.v4.NameManagerV4;
import edu.stanford.hakan.aim4api.plugin.v4.PluginCollectionV4;
import edu.stanford.hakan.aim4api.plugin.v4.PluginV4;
import java.util.List;
import edu.stanford.hakan.aim4api.utility.GenerateId;
import edu.stanford.hakan.aim4api.utility.Logger;
import edu.stanford.hakan.aim4api.utility.Utility;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class ImageAnnotation extends AnnotationEntity {

    private SegmentationEntityCollection segmentationEntityCollection = new SegmentationEntityCollection();
    private MarkupEntityCollection markupEntityCollection = new MarkupEntityCollection();
    private ImageAnnotationStatementCollection imageAnnotationStatementCollection = new ImageAnnotationStatementCollection();
    private ImageReferenceEntityCollection imageReferenceEntityCollection = new ImageReferenceEntityCollection();
    private ImageAnnotationCollection imageAnnotationCollection;
    
    private ImageAnnotation initialState = null;
    private int version = -1;
    private int dsoStartIndex = -1;
    private String dsoColor = "#FFFFFF";
    
    private PluginCollectionV4 pluginCollection = new PluginCollectionV4();

    public ImageAnnotation() {
        this.version = -1;
        setXsiType("ImageAnnotation");
    }
    
    public SegmentationEntityCollection getSegmentationEntityCollection() {
        return segmentationEntityCollection;
    }

    public void setSegmentationEntityCollection(SegmentationEntityCollection segmentationEntityCollection) {
        this.segmentationEntityCollection = segmentationEntityCollection;
    }

    public void addSegmentationEntity(SegmentationEntity newSegmentationEntity) {
        this.segmentationEntityCollection.addSegmentationEntity(newSegmentationEntity);
    }

    public MarkupEntityCollection getMarkupEntityCollection() {
        return markupEntityCollection;
    }

    public void setMarkupEntityCollection(MarkupEntityCollection markupEntityCollection) {
        this.markupEntityCollection = markupEntityCollection;
    }

    public void addMarkupEntity(MarkupEntity newMarkupEntity) {
        this.markupEntityCollection.addMarkupEntity(newMarkupEntity);
    }

    public ImageAnnotationStatementCollection getImageAnnotationStatementCollection() {
        return imageAnnotationStatementCollection;
    }

    public void setImageAnnotationStatementCollection(ImageAnnotationStatementCollection imageAnnotationStatementCollection) {
        this.imageAnnotationStatementCollection = imageAnnotationStatementCollection;
    }

    public void addImageAnnotationStatement(AnnotationStatement newImageAnnotationStatement) {
        this.imageAnnotationStatementCollection.addImageAnnotationStatement(newImageAnnotationStatement);
    }

    public ImageReferenceEntityCollection getImageReferenceEntityCollection() {
        return imageReferenceEntityCollection;
    }

    public void setImageReferenceEntityCollection(ImageReferenceEntityCollection imageReferenceEntityCollection) {
        this.imageReferenceEntityCollection = imageReferenceEntityCollection;
    }

    public void addImageReferenceEntity(ImageReferenceEntity newImageReferenceEntity) {
        this.imageReferenceEntityCollection.addImageReferenceEntity(newImageReferenceEntity);
    }

    public ImageAnnotationCollection getImageAnnotationCollection() {
        return imageAnnotationCollection;
    }

    public void setImageAnnotationCollection(ImageAnnotationCollection imageAnnotationCollection) {
        this.imageAnnotationCollection = imageAnnotationCollection;
    }

    public String refreshUniqueIdentifier() {
        this.setUniqueIdentifier(new II(GenerateId.getUUID()));
        return this.getUniqueIdentifier().getRoot();
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getDsoStartIndex() {
        return dsoStartIndex;
    }

    public void setDsoStartIndex(int dsoStartIndex) {
        this.dsoStartIndex = dsoStartIndex;
    }

    public String getDsoColor() {
        return dsoColor;
    }

    public void setDsoColor(String dsoColor) {
        this.dsoColor = dsoColor;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("ImageAnnotation");
        }

//        if(this.pluginCollection.size() > 0)
//            System.out.println("has plugin");
        
        NameManagerV4 commentManagerV4 = new NameManagerV4();
        this.setName(new ST(commentManagerV4.toString(this)));
        Element res = (Element) super.getXMLNode(doc);
        commentManagerV4 = new NameManagerV4(this);
        if (this.segmentationEntityCollection.size() > 0) {
            res.appendChild(this.segmentationEntityCollection.getXMLNode(doc));
        }
        if (this.markupEntityCollection.size() > 0) {
            res.appendChild(this.markupEntityCollection.getXMLNode(doc));
        }
        if (this.imageAnnotationStatementCollection.size() > 0) {
            res.appendChild(this.imageAnnotationStatementCollection.getXMLNode(doc));
        }
        if (this.imageReferenceEntityCollection.size() > 0) {
            res.appendChild(this.imageReferenceEntityCollection.getXMLNode(doc));
        }
        //*** Audit trail operations.
        if (this.getIsEdited()) {
            this.setUniqueIdentifier(new II(GenerateId.getUUID()));
            this.setDateTime(Utility.getNowAtGMT());
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("segmentationEntityCollection".equals(listChilds.item(i).getNodeName())) {
                this.segmentationEntityCollection.setXMLNode(listChilds.item(i));
            }
            if ("markupEntityCollection".equals(listChilds.item(i).getNodeName())) {
                this.markupEntityCollection.setXMLNode(listChilds.item(i));
            }
            if ("imageAnnotationStatementCollection".equals(listChilds.item(i).getNodeName())) {
                this.imageAnnotationStatementCollection.setXMLNode(listChilds.item(i));
            }
            if ("imageReferenceEntityCollection".equals(listChilds.item(i).getNodeName())) {
                this.imageReferenceEntityCollection.setXMLNode(listChilds.item(i));
            }
        }
        //*** Setting the initialState. I will use it while saving operation, if the class is updated or not.
        this.setInitialState();

        if (this.getAuditTrailCollection() != null && this.getAuditTrailCollection().size() > 0) {
            this.setVersion(Integer.parseInt(this.getAuditTrailCollection().get(0).getComment().getValue()));
        }

        NameManagerV4 commentManagerV4 = new NameManagerV4(this);
        //ml
        this.setPluginCollection(commentManagerV4.getPluginCollection());
        Logger.write("Checking plugin parameters inside imageAnnotation setXML");
        Logger.write(this.imageAnnotationCollection.getUniqueIdentifier().getRoot());
        if (getPluginCollection().size() > 0) {
            PluginCollectionV4 pCollection = getPluginCollection();
            for (PluginV4 p4 : pCollection.getListPlugin()) {
                Logger.write(p4.getName());
            }
        }
        Logger.write("================");

    }

    public boolean getIsEdited() {
        if (this.initialState == null) {
            return false;
        }
        return !this.isEqualTo(this.initialState);
    }

    public ImageAnnotation getInitialState() {
        return this.initialState;
    }
    
    public void setInitialState()
    {
        this.initialState = this.getClone();
        
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        ImageAnnotation oth = (ImageAnnotation) other;
        if (this.segmentationEntityCollection == null ? oth.segmentationEntityCollection != null : !this.segmentationEntityCollection.isEqualTo(oth.segmentationEntityCollection)) {
            return false;
        }
        if (this.markupEntityCollection == null ? oth.markupEntityCollection != null : !this.markupEntityCollection.isEqualTo(oth.markupEntityCollection)) {
            return false;
        }
        if (this.imageAnnotationStatementCollection == null ? oth.imageAnnotationStatementCollection != null : !this.imageAnnotationStatementCollection.isEqualTo(oth.imageAnnotationStatementCollection)) {
            return false;
        }
        if (this.imageReferenceEntityCollection == null ? oth.imageReferenceEntityCollection != null : !this.imageReferenceEntityCollection.isEqualTo(oth.imageReferenceEntityCollection)) {
            return false;
        }
        if (this.pluginCollection == null ? oth.pluginCollection != null : !this.pluginCollection.isEqualTo(oth.pluginCollection)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    @Override
    public ImageAnnotation getClone() {
        ImageAnnotation res = new ImageAnnotation();
        if (this.getSegmentationEntityCollection() != null) {
            res.setSegmentationEntityCollection(this.getSegmentationEntityCollection().getClone());
        }
        if (this.getMarkupEntityCollection() != null) {
            res.setMarkupEntityCollection(this.getMarkupEntityCollection().getClone());
        }
        if (this.getImageAnnotationStatementCollection() != null) {
            res.setImageAnnotationStatementCollection(this.getImageAnnotationStatementCollection().getClone());
        }
        if (this.getImageReferenceEntityCollection() != null) {
            res.setImageReferenceEntityCollection(this.getImageReferenceEntityCollection().getClone());
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
        
        if(this.getPluginCollection() != null) {
            res.setPluginCollection(this.getPluginCollection().getClone());
        }
        
        res.setVersion(this.getVersion());
        return res;
    }

    public List<TwoDimensionGeometricShapeEntity> getTwoDimensionShapes() {
        List<TwoDimensionGeometricShapeEntity> res = new ArrayList<TwoDimensionGeometricShapeEntity>();
        for (MarkupEntity markup : this.markupEntityCollection.getMarkupEntityList()) {
            if (markup.getXsiType().equals("TwoDimensionPolyline")) {
                if (Utility.isSpline(markup)) {
                    markup.setXsiType("TwoDimensionSpline");
                    res.add((TwoDimensionSpline) markup);
                } else {
                    res.add((TwoDimensionPolyline) markup);
                }
            }    
            else if (markup.getXsiType().equals("TwoDimensionSpline")) {
            	res.add((TwoDimensionSpline) markup);}
            else if (markup.getXsiType().equals("TwoDimensionMultiPoint")) {
                res.add((TwoDimensionMultiPoint) markup);
            } else if (markup.getXsiType().equals("TwoDimensionPoint")) {
                res.add((TwoDimensionPoint) markup);
            } else if (markup.getXsiType().equals("TwoDimensionCircle")) {
                res.add((TwoDimensionCircle) markup);
            } else if (markup.getXsiType().equals("TwoDimensionEllipse")) {
                res.add((TwoDimensionEllipse) markup);
            }
        }
        return res;
    }

    public boolean isEdited() {
        return this.isEqualTo(this.initialState);
    }
       @Override
    public boolean equals(Object obj) {
       if (!(obj instanceof ImageAnnotation))
            return false;
       
        if (this.getUniqueIdentifier().getRoot().equals(((ImageAnnotation) obj).getUniqueIdentifier().getRoot())) {
            return true;
        }
        return false;
    }

    public PluginCollectionV4 getPluginCollection() {
        return pluginCollection;
    }

    public void setPluginCollection(PluginCollectionV4 pluginCollection) {
        this.pluginCollection = pluginCollection;
    }

    public void addPlugin(PluginV4 newPlugin) {
        if (this.pluginCollection == null) {
            this.pluginCollection = new PluginCollectionV4();
        }
        this.pluginCollection.setImageAnnotation(this);
        this.pluginCollection.addPlugin(newPlugin);
    }
}
