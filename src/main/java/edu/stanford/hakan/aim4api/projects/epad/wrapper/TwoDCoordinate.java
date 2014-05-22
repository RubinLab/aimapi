/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.hakan.aim4api.projects.epad.wrapper;

/**
 *
 * @author Hakan
 */
public class TwoDCoordinate extends TwoDimensionSpatialCoordinate {
    
    	public TwoDCoordinate() {
		super();
	}
	

//	public TwoDCoordinate(TwoDimensionSpatialCoordinate c) {
//		super();
//		
//		setCoordinateIndex(c.getCoordinateIndex());
//		setXsiType(c.getXsiType());
//		
//		setImageReferenceUID(c.getImageReferenceUID());
//		setReferencedFrameNumber(c.getReferencedFrameNumber());
//		setX(c.getX());
//		setY(c.getY());
//		
//
//	}
    public TwoDCoordinate(Integer cagridId, Integer coordinateIndex,
            String imageReferenceUID, Integer referencedFrameNumber, Double x,
            Double y) {

        super(cagridId, coordinateIndex,
                imageReferenceUID, referencedFrameNumber, x,
                y);
    }
}
