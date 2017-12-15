package edu.stanford.hakan.aim4api.project.epad;

//Copyright (c) 2013 The Board of Trustees of the Leland Stanford Junior University
//All rights reserved.
//
//Redistribution and use in source and binary forms, with or without modification, are permitted provided that 
//the following conditions are met:
//
//Redistributions of source code must retain the above copyright notice, this list of conditions and the following 
//disclaimer.
//
//Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the 
//following disclaimer in the documentation and/or other materials provided with the distribution.
//
//Neither the name of The Board of Trustees of the Leland Stanford Junior University nor the names of its 
//contributors (Daniel Rubin, et al) may be used to endorse or promote products derived from this software without 
//specific prior written permission.
//
//THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
//INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
//DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
//SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
//SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
//WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE 
//USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

import java.util.ArrayList;
import java.util.List;

import edu.stanford.hakan.aim4api.compability.aimv3.GeometricShape;
import edu.stanford.hakan.aim4api.compability.aimv3.SpatialCoordinate;
import edu.stanford.hakan.aim4api.compability.aimv3.TwoDimensionSpatialCoordinate;
import edu.stanford.hakan.aim4api.project.epad.Enumerations.ShapeType;

/**
 * 
 * @author Debra Willrett
 * 
 */

@SuppressWarnings("serial")
public class Shape extends GeometricShape {

	double labelOffsetX, labelOffsetY = 0.0;
	int referencedFrameNumber = 1;
	
	public Shape() {
	}

	public Shape(GeometricShape geometricShape) {

		this.setCagridId(geometricShape.getCagridId());
		this.setIncludeFlag(geometricShape.getIncludeFlag());
		this.setLineColor(geometricShape.getLineColor());
		this.setLineOpacity(geometricShape.getLineOpacity());
		this.setLineStyle(geometricShape.getLineStyle());
		this.setLineThickness(geometricShape.getLineThickness());
		this.setShapeIdentifier(geometricShape.getShapeIdentifier());
		this.setXsiType(geometricShape.getXsiType());
		this.setSpatialCoordinateCollection(geometricShape
				.getSpatialCoordinateCollection());
		
		//this is in twodimendisongeometricentity in v4
		//get the referenced frame number
		List<TwoDCoordinate> coords= getCoords();
		setReferencedFrameNumber(coords.get(0).getReferencedFrameNumber());
	}

	public List<TwoDCoordinate> getCoords() {
		List<TwoDCoordinate> result = new ArrayList<TwoDCoordinate>();
		for (SpatialCoordinate coord : getSpatialCoordinateCollection()
				.getSpatialCoordinateList()) {
			if (coord instanceof TwoDimensionSpatialCoordinate) {
				result.add(new TwoDCoordinate(
						(TwoDimensionSpatialCoordinate) coord));
			}
		}
		return result;
	}

	public ShapeType getShapeType() {

		ShapeType result = ShapeType.NONE;

		String xsiType = getXsiType();
		if ("MultiPoint".equals(xsiType)) {
			result = ShapeType.LINE;
		} else if ("Polyline".equals(xsiType)) {
			result = ShapeType.POLY;
		} else if ("Spline".equals(xsiType)) {
			result = ShapeType.SPLINE;
		}else if ("Circle".equals(xsiType)) {
			result = ShapeType.CIRCLE;
		} else if ("Point".equals(xsiType)) {
			result = ShapeType.POINT;
		} else if ("Ellipse".equals(xsiType) || "Normal".equals(xsiType)) { // To support NORMAL/ORTHOGONAL lines
			//it is not goint to be normal as it is not persisted in xml
			result = ShapeType.NORMAL;
		}
		return result;

	}

	// for moving the labels around on the screen for each shape
	public double getLabelOffsetX() {
		return labelOffsetX;
	}

	public void setLabelOffsetX(double labelOffsetX) {
		this.labelOffsetX = labelOffsetX;
	}

	public double getLabelOffsetY() {
		return this.labelOffsetY;
	}

	public void setLabelOffsetY(double labelOffsetY) {
		this.labelOffsetY = labelOffsetY;
	}

	public int getReferencedFrameNumber() {
		return referencedFrameNumber;
	}

	public void setReferencedFrameNumber(int referencedFrameNumber) {
		this.referencedFrameNumber = referencedFrameNumber;
	}

}
