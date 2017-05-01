package edu.stanford.hakan.aim4api.project.epad;

import java.io.Serializable;

import edu.stanford.hakan.aim4api.compability.aimv3.ThreeDimensionSpatialCoordinate;

/**
 * 
 * @author sherylj
 *
 */
public class ThreeDCoordinate extends ThreeDimensionSpatialCoordinate implements Serializable {

	public ThreeDCoordinate() {
		super();
	}
	
	public ThreeDCoordinate(ThreeDimensionSpatialCoordinate c) {
		super();
		
		setCagridId(c.getCagridId());
		setCoordinateIndex(c.getCoordinateIndex());
		setXsiType(c.getXsiType());

		
		setFrameOfReferenceUID(c.getFrameOfReferenceUID());
		setFiducialUid(c.getFiducialUid());
		setX(c.getX());
		setY(c.getY());
		setZ(c.getY());
	}
	
	public ThreeDCoordinate(Double x, Double y, Double z) {
		super(0, 0,  x, y, z, "");
	}

	public ThreeDCoordinate(Integer cagridId, Integer coordinateIndex,
			Double x, Double y, Double z, String frameOfReference) {

		super(cagridId, coordinateIndex, x, y, z, frameOfReference);
	}
	
	
}
