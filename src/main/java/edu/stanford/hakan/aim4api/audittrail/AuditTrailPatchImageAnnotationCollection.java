/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.audittrail;

import java.util.ArrayList;
import java.util.List;
import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.ImageAnnotationCollection;
import edu.stanford.hakan.aim4api.utility.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan
 */
public class AuditTrailPatchImageAnnotationCollection extends AuditTrailPatch {

    private ImageAnnotationCollection imageAnnotationCollection;
    private ImageAnnotationCollection initialStateImageAnnotationCollection;
    private List<AuditTrailPatchImageAnnotation> listAuditTrailPatchImageAnnotation = new ArrayList<AuditTrailPatchImageAnnotation>();

    public ImageAnnotationCollection getImageAnnotationCollection() {
        return imageAnnotationCollection;
    }

    public void setImageAnnotationCollection(ImageAnnotationCollection imageAnnotationCollection) {
        this.imageAnnotationCollection = imageAnnotationCollection;
        //this.initialStateImageAnnotationCollection = imageAnnotationCollection.getInitialState();
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("ImageAnnotationCollection");
        }

        Element res = (Element) super.getXMLNode(doc);
        //*** uniqueIdentifier
        if (this.initialStateImageAnnotationCollection.getUniqueIdentifier() != null) {
            res.appendChild(this.initialStateImageAnnotationCollection.getUniqueIdentifier().getXMLNode(doc));
        }
        //*** description was changed.
        if (this.initialStateImageAnnotationCollection.getDescription() != null) {
            if (!this.imageAnnotationCollection.getDescription().isEqualTo(this.initialStateImageAnnotationCollection.getDescription())) {
                res.appendChild(this.initialStateImageAnnotationCollection.getDescription().getXMLNode(doc));
            }
        }
        //*** dateTime
        if (this.initialStateImageAnnotationCollection.getDateTime() != null) {
            Element el_dateTime = doc.createElement("dateTime");
            el_dateTime.setAttribute("value", this.initialStateImageAnnotationCollection.getDateTime());
            res.appendChild(el_dateTime);
        }
//        //*** user
//        if (this.imageAnnotationCollection.getUser().getIsEdited()) {
//            res.appendChild(this.initialStateImageAnnotationCollection.getUser().getXMLNode(doc));
//        }
//        //*** equipment
//        if (this.imageAnnotationCollection.getEquipment().getIsEdited()) {
//            res.appendChild(this.initialStateImageAnnotationCollection.getEquipment().getXMLNode(doc));
//        }
//        //*** person
//        if (this.imageAnnotationCollection.getPerson().getIsEdited()) {
//            res.appendChild(this.initialStateImageAnnotationCollection.getDescription().getXMLNode(doc));
//        }
        return res;
    }
    
    public void saveToFile(String Path) throws AimException
    {
        Document doc = XML.createDocument();
        Element root = (Element) this.getXMLNode(doc);
        doc.appendChild(root);
        XML.SaveDocucument(doc, Path);
    }
}
