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
import edu.stanford.hakan.aim4api.project.epad.Enumerations.ShapeType;

@SuppressWarnings("serial")
public class Normal extends Shape {
	Shape l1;
	Shape l2;
	public Normal(Shape l1, Shape l2){
		this.l1=l1;
		this.l2=l2;
//		this.setCagridId(l1.getCagridId());
		if (l1.getIncludeFlag()!=null) this.setIncludeFlag(l1.getIncludeFlag());
		if (l1.getLineColor()!=null) this.setLineColor(l1.getLineColor());
		if (l1.getLineOpacity()!=null) this.setLineOpacity(l1.getLineOpacity());
		if (l1.getLineStyle()!=null) this.setLineStyle(l1.getLineStyle());
		if (l1.getLineThickness()!=null) this.setLineThickness(l1.getLineThickness());
		this.setShapeIdentifier(l1.getShapeIdentifier());
		this.setXsiType("Normal");
		this.setTwoDimensionSpatialCoordinateCollection(l1
				.getTwoDimensionSpatialCoordinateCollection().getClone());
		this.getTwoDimensionSpatialCoordinateCollection().getTwoDimensionSpatialCoordinateList().addAll(l2
				.getTwoDimensionSpatialCoordinateCollection().getClone().getTwoDimensionSpatialCoordinateList());
		this.setReferencedFrameNumber(l1.getReferencedFrameNumber());
		this.setImageReferenceUid(l1.getImageReferenceUid());
		
	}

	public Shape line1(){
		return this.l1;
	}
	
	public Shape line2(){
		return this.l2;
	}
	
	@Override
	public ShapeType getShapeType() {
		return ShapeType.NORMAL;
	}
	
	
}
