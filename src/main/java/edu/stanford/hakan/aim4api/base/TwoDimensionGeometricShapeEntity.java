/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
public class TwoDimensionGeometricShapeEntity extends GeometricShapeEntity {

    public TwoDimensionGeometricShapeEntity() {
        setXsiType("TwoDimensionGeometricShapeEntity");
    }
    private II imageReferenceUid;
    private Integer referencedFrameNumber;
    private ST uri;
    private TwoDimensionSpatialCoordinateCollection twoDimensionSpatialCoordinateCollection = new TwoDimensionSpatialCoordinateCollection();

    public II getImageReferenceUid() {
        return imageReferenceUid;
    }

    public void setImageReferenceUid(II imageReferenceUid) {
        imageReferenceUid.setTagName("imageReferenceUid");
        this.imageReferenceUid = imageReferenceUid;
    }

    public Integer getReferencedFrameNumber() {
        return referencedFrameNumber;
    }

    public void setReferencedFrameNumber(Integer referencedFrameNumber) {
        this.referencedFrameNumber = referencedFrameNumber;
    }

    public ST getUri() {
        return uri;
    }

    public void setUri(ST uri) {
        uri.setTagName("uri");
        this.uri = uri;
    }

    public TwoDimensionSpatialCoordinateCollection getTwoDimensionSpatialCoordinateCollection() {
        return twoDimensionSpatialCoordinateCollection;
    }

    public void setTwoDimensionSpatialCoordinateCollection(TwoDimensionSpatialCoordinateCollection twoDimensionSpatialCoordinateCollection) {
        this.twoDimensionSpatialCoordinateCollection = twoDimensionSpatialCoordinateCollection;
    }

    public void addTwoDimensionSpatialCoordinate(TwoDimensionSpatialCoordinate newTwoDimensionSpatialCoordinate) {
        this.twoDimensionSpatialCoordinateCollection.addTwoDimensionSpatialCoordinate(newTwoDimensionSpatialCoordinate);
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("TwoDimensionGeometricShapeEntity");
        }
        Element res = (Element) super.getXMLNode(doc);
        if (this.imageReferenceUid != null) {
            res.appendChild(this.imageReferenceUid.getXMLNode(doc));
        }
        if (this.referencedFrameNumber != null) {
            Element el_referencedFrameNumber = doc.createElement("referencedFrameNumber");
            el_referencedFrameNumber.setAttribute("value", this.referencedFrameNumber.toString());
            res.appendChild(el_referencedFrameNumber);
        }
        if (this.uri != null) {
            res.appendChild(this.uri.getXMLNode(doc));
        }
        if (this.twoDimensionSpatialCoordinateCollection.size() > 0) {
            res.appendChild(this.twoDimensionSpatialCoordinateCollection.getXMLNode(doc));
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("imageReferenceUid".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setImageReferenceUid(obj);
            }
            if ("referencedFrameNumber".equals(currentNode.getNodeName())) {
                this.referencedFrameNumber = Integer.parseInt(currentNode.getAttributes().getNamedItem("value").getNodeValue());
            }
            if ("uri".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setUri(obj);
            }
            if ("twoDimensionSpatialCoordinateCollection".equals(listChilds.item(i).getNodeName())) {
                this.twoDimensionSpatialCoordinateCollection.setXMLNode(listChilds.item(i));
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        TwoDimensionGeometricShapeEntity oth = (TwoDimensionGeometricShapeEntity) other;
        if (!this.imageReferenceUid.isEqualTo(oth.imageReferenceUid)) {
            return false;
        }
        if (this.referencedFrameNumber == null ? oth.referencedFrameNumber != null : !this.referencedFrameNumber.equals(oth.referencedFrameNumber)) {
            return false;
        }
        if (!this.uri.isEqualTo(oth.uri)) {
            return false;
        }
        if (!this.twoDimensionSpatialCoordinateCollection.isEqualTo(oth.twoDimensionSpatialCoordinateCollection)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    @Override
    public TwoDimensionGeometricShapeEntity getClone() {
        TwoDimensionGeometricShapeEntity res = new TwoDimensionGeometricShapeEntity();
        if (this.getImageReferenceUid() != null) {
            res.setImageReferenceUid(this.getImageReferenceUid().getClone());
        }
        if (this.getReferencedFrameNumber() != null) {
            res.setReferencedFrameNumber(this.getReferencedFrameNumber());
        }
        if (this.getUri() != null) {
            res.setUri(this.getUri().getClone());
        }
        if (this.getTwoDimensionSpatialCoordinateCollection() != null) {
            res.setTwoDimensionSpatialCoordinateCollection(this.getTwoDimensionSpatialCoordinateCollection().getClone());
        }
        List<CD> listQuestionTypeCode = this.getListQuestionTypeCode();
        for (int i = 0; i < listQuestionTypeCode.size(); i++) {
            if (listQuestionTypeCode.get(i) != null) {
                res.addQuestionTypeCode(listQuestionTypeCode.get(i).getClone());
            }
        }
        if (this.getShapeIdentifier() != null) {
            res.setShapeIdentifier(this.getShapeIdentifier());
        }
        if (this.getLabel() != null) {
            res.setLabel(this.getLabel().getClone());
        }
        if (this.getDescription() != null) {
            res.setDescription(this.getDescription().getClone());
        }
        if (this.getIncludeFlag() != null) {
            res.setIncludeFlag(this.getIncludeFlag());
        }
        if (this.getComment() != null) {
            res.setComment(this.getComment().getClone());
        }
        if (this.getLineColor() != null) {
            res.setLineColor(this.getLineColor().getClone());
        }
        if (this.getLineOpacity() != null) {
            res.setLineOpacity(this.getLineOpacity().getClone());
        }
        if (this.getLineStyle() != null) {
            res.setLineStyle(this.getLineStyle().getClone());
        }
        if (this.getLineThickness() != null) {
            res.setLineThickness(this.getLineThickness().getClone());
        }
        if (this.getQuestionIndex() != null) {
            res.setQuestionIndex(this.getQuestionIndex());
        }
        if (this.getInterpolationMethod() != null) {
            res.setInterpolationMethod(this.getInterpolationMethod().getClone());
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
