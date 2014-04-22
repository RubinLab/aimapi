/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.audittrail;

import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.base.ImageAnnotationCollection;

/**
 *
 * @author Hakan
 */
public class AuditTrailPatchManager {

    public static AuditTrailPatchImageAnnotationCollection getAuditTrailPatch(ImageAnnotationCollection iac) {
        AuditTrailPatchImageAnnotationCollection res = new AuditTrailPatchImageAnnotationCollection();
        res.setImageAnnotationCollection(iac);
       
        return res;
    }
    public static AuditTrailPatchImageAnnotation getAuditTrailPatch(ImageAnnotation ia) {
        AuditTrailPatchImageAnnotation res = new AuditTrailPatchImageAnnotation();
        return res;
    }
    
    public static void test(ImageAnnotationCollection iac) throws AimException
    {
        AuditTrailPatchImageAnnotationCollection temp = new AuditTrailPatchImageAnnotationCollection();
        temp.setImageAnnotationCollection(iac);
        temp.saveToFile("C:\\Temp\\temptemptemp.xml");
    
    }
}
