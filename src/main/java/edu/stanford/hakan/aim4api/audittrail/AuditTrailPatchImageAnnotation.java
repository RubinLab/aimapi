/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.audittrail;

import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan
 */
public class AuditTrailPatchImageAnnotation extends AuditTrailPatch {

    private ImageAnnotation imageAnnotation;
    private ImageAnnotation initialStateImageAnnotation;
    
    public ImageAnnotation getImageAnnotation() {
        return imageAnnotation;
    }

    public void setImageAnnotation(ImageAnnotation imageAnnotation) {
        this.imageAnnotation = imageAnnotation;
    }

    @Override
    public Node getXMLNode(Document doc)  throws AimException {
        Element res = (Element) super.getXMLNode(doc);
        
        
        
                Element el_dateTime = doc.createElement("dateTime");
//            el_dateTime.setAttribute("value", this.dateTime.toString());
            res.appendChild(el_dateTime);
        
        return res;
    }
}
