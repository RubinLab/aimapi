/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import edu.stanford.hakan.aim4api.base.II;
import edu.stanford.hakan.aim4api.base.TwoDimensionGeometricShapeEntity;

/**
 *
 * @author Hakan
 */
public class TwoDimensionSpatialCoordinate extends edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinate {

    /*    
     TwoDimensionSpatialCoordinate ; Epad = TwoDCoordinate
        
     + getX()
     + getY()
        
     //*** It seems that, it is always set by zero.

     ? setCagridId(Integer)
        
     + setCoordinateIndex(Integer)
    
     //*** The following attributes were moved to TwoDimensionGeometricShapeEntity class.
     //*** The class has twoDimensionSpatialCoordinateCollection
     ? setImageReferenceUID(String)
     ? setReferencedFrameNumber(Integer)
        
     + setX(double)
     */
    
     public TwoDimensionSpatialCoordinate()
     {
         
     }
    
     public TwoDimensionSpatialCoordinate(Integer cagridId, Integer coordinateIndex, String imageReferenceUID, Integer referencedFrameNumber, Double x, Double y)
     {
         super.setCoordinateIndex(coordinateIndex);
         this.setImageReferenceUID(imageReferenceUID);
         this.setReferencedFrameNumber(referencedFrameNumber);
         super.setX(x);
         super.setY(y);
     
     }
    public void setImageReferenceUID(String imageReferenceUID) {
        if(super.getTwoDimensionGeometricShapeEntity() == null)
            super.setTwoDimensionGeometricShapeEntity(new TwoDimensionGeometricShapeEntity());
        super.getTwoDimensionGeometricShapeEntity().setImageReferenceUid(new II(imageReferenceUID));
    }

    public void setReferencedFrameNumber(int referencedFrameNumber) {
        if(super.getTwoDimensionGeometricShapeEntity() == null)
            super.setTwoDimensionGeometricShapeEntity(new TwoDimensionGeometricShapeEntity());
        super.getTwoDimensionGeometricShapeEntity().setReferencedFrameNumber(referencedFrameNumber);
    }
}
