/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.projects.epad.wrapper.Enumerations.ROIShape;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class GeometricShape extends edu.stanford.hakan.aim4api.base.TwoDimensionGeometricShapeEntity {

//    private String imageUid;
//    private int frame;
//    private List<TwoDimensionSpatialCoordinate> listCoordinates = new ArrayList<TwoDimensionSpatialCoordinate>();
//    public GeometricShape() {
//    }
//
//    public GeometricShape(String imageUid, int frame, ROIShape shapeType, List<TwoDimensionSpatialCoordinate> listCoordinates) {
//        this.roiShape = shapeType;
//        this.imageUid = imageUid;
//        this.frame = frame;
//        this.listCoordinates = listCoordinates;
//    }
//
//    public GeometricShape(TwoDimensionGeometricShapeEntity twoDimensionGeometricShapeEntity) {
//        this.imageUid = twoDimensionGeometricShapeEntity.getImageReferenceUid().getRoot();
//        this.frame = twoDimensionGeometricShapeEntity.getReferencedFrameNumber();
//        this.roiShape = this.string2EdapShapeType(twoDimensionGeometricShapeEntity.getUri().getValue());
//        List<TwoDimensionSpatialCoordinate> listTwoDimensionSpatialCoordinate = twoDimensionGeometricShapeEntity.getTwoDimensionSpatialCoordinateCollection().getTwoDimensionSpatialCoordinateList();
//        for (int i = 0; i < listTwoDimensionSpatialCoordinate.size(); i++) {
//            this.listCoordinates.add(new TwoDimensionSpatialCoordinate(listTwoDimensionSpatialCoordinate.get(i)));
//        }
//    }
//    public void addCoordinate(TwoDimensionSpatialCoordinate twoDCoordinate) {
//        this.listCoordinates.add(twoDCoordinate);
//    }
//
//    public String getImageUid() {
//        return imageUid;
//    }
//
//    public void setImageUid(String imageUid) {
//        this.imageUid = imageUid;
//    }
//
//    public int getFrame() {
//        return frame;
//    }
//
//    public void setFrame(int frame) {
//        this.frame = frame;
//    }
//
//    public List<TwoDimensionSpatialCoordinate> getListCoordinates() {
//        return listCoordinates;
//    }
//
//    public void setListCoordinates(List<TwoDimensionSpatialCoordinate> listCoordinates) {
//        this.listCoordinates = listCoordinates;
//    }
//
//    public ROIShape getRoiShape() {
//        return roiShape;
//    }
//
//    public void setRoiShape(ROIShape roiShape) {
//        this.roiShape = roiShape;
//    }
    
    
//    
    
    public List<TwoDimensionSpatialCoordinate> getListCoordinates() {
        List<TwoDimensionSpatialCoordinate> res = new ArrayList<TwoDimensionSpatialCoordinate>();
        List<edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinate> temp = super.getTwoDimensionSpatialCoordinateCollection().getTwoDimensionSpatialCoordinateList();
        for (int i = 0; i < temp.size(); i++) {
            res.add((TwoDimensionSpatialCoordinate) temp.get(i));
        }
        return res;
    }

    public boolean isRectangle() {

        if (this.getListCoordinates() != null && this.getListCoordinates().size() == 4) {
            List<TwoDimensionSpatialCoordinate> listCoordinates = this.getListCoordinates();
            return IsRectangleAnyOrder(listCoordinates.get(0), listCoordinates.get(1), listCoordinates.get(2), listCoordinates.get(3));
        }
        return false;
    }

    //Preferences.useSpline()
    public ROIShape getROIShape() {
        String s = getXsiType();
        if (s.equalsIgnoreCase("TwoDimensionMultiPoint")) {
            return ROIShape.LINE;
        } else if (s.equalsIgnoreCase("TwoDimensionPolyline")) {
            if (super.getDescription().getValue().contains(Spline.getSplineKey())) {
                return ROIShape.SPLINE;
            } else {
                return ROIShape.POLY;
            }
        } else if (s.equalsIgnoreCase("TwoDimensionCircle")) {
            return ROIShape.CIRCLE;
        }
        return ROIShape.NONE;
    }

    public boolean isCircle() {
        return this.getROIShape().equals(ROIShape.CIRCLE);
    }

    public boolean isPolyline() {
        return this.getROIShape().equals(ROIShape.POLY);
    }

    public boolean isMultiPoint() {
        return this.getROIShape().equals(ROIShape.LINE);
    }

    public boolean isSpline() {
        return this.getROIShape().equals(ROIShape.SPLINE);
    }

    private boolean IsOrthogonal(TwoDimensionSpatialCoordinate a, TwoDimensionSpatialCoordinate b, TwoDimensionSpatialCoordinate c) {
        return (b.getX() - a.getX()) * (b.getX() - c.getX()) + (b.getY() - a.getY()) * (b.getY() - c.getY()) == 0;
    }

    private boolean IsRectangle(TwoDimensionSpatialCoordinate a, TwoDimensionSpatialCoordinate b, TwoDimensionSpatialCoordinate c, TwoDimensionSpatialCoordinate d) {
        return IsOrthogonal(a, b, c) && IsOrthogonal(b, c, d) && IsOrthogonal(c, d, a);
    }

    private boolean IsRectangleAnyOrder(TwoDimensionSpatialCoordinate a, TwoDimensionSpatialCoordinate b, TwoDimensionSpatialCoordinate c, TwoDimensionSpatialCoordinate d) {
        return IsRectangle(a, b, c, d) || IsRectangle(b, c, a, d) || IsRectangle(c, a, b, d);
    }

    public void addSpatialCoordinate(TwoDimensionSpatialCoordinate twoDimensionSpatialCoordinate) {
        super.addTwoDimensionSpatialCoordinate(twoDimensionSpatialCoordinate);
    }

    public SpatialCoordinateCollection getSpatialCoordinateCollection() {
        return (SpatialCoordinateCollection) super.getTwoDimensionSpatialCoordinateCollection();
    }
    
    public void setLineStyle(String lineStyle)
    {
        super.setLineStyle(new ST(lineStyle));
    }
}
