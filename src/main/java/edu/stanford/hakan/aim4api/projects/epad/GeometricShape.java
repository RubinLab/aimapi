/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad;

import java.util.ArrayList;
import java.util.List;
import edu.stanford.hakan.aim4api.base.II;
import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.base.TwoDimensionCircle;
import edu.stanford.hakan.aim4api.base.TwoDimensionEllipse;
import edu.stanford.hakan.aim4api.base.TwoDimensionGeometricShapeEntity;
import edu.stanford.hakan.aim4api.base.TwoDimensionMultiPoint;
import edu.stanford.hakan.aim4api.base.TwoDimensionPoint;
import edu.stanford.hakan.aim4api.base.TwoDimensionPolyline;
import edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinate;

/**
 *
 * @author Hakan
 */
public class GeometricShape {

    public enum ROIShape {

        NONE, LINE, RECTANGLE, CIRCLE, OVAL, POLY, OPENPOLY, COMMENT, ARROW, SPLINE, OPENSPLINE, TEXT
    }
    private ROIShape roiShape;
    private String imageUid;
    private int frame;
    private List<TwoDCoordinate> listCoordinates = new ArrayList<TwoDCoordinate>();

    public GeometricShape() {
    }

    public GeometricShape(String imageUid, int frame, ROIShape shapeType, List<TwoDCoordinate> listCoordinates) {
        this.roiShape = shapeType;
        this.imageUid = imageUid;
        this.frame = frame;
        this.listCoordinates = listCoordinates;
    }

    public GeometricShape(TwoDimensionGeometricShapeEntity twoDimensionGeometricShapeEntity) {
        this.imageUid = twoDimensionGeometricShapeEntity.getImageReferenceUid().getRoot();
        this.frame = twoDimensionGeometricShapeEntity.getReferencedFrameNumber();
        this.roiShape = this.string2EdapShapeType(twoDimensionGeometricShapeEntity.getUri().getValue());
        List<TwoDimensionSpatialCoordinate> listTwoDimensionSpatialCoordinate = twoDimensionGeometricShapeEntity.getTwoDimensionSpatialCoordinateCollection().getTwoDimensionSpatialCoordinateList();
        for (int i = 0; i < listTwoDimensionSpatialCoordinate.size(); i++) {
            this.listCoordinates.add(new TwoDCoordinate(listTwoDimensionSpatialCoordinate.get(i)));
        }
    }

    public void addCoordinate(TwoDCoordinate twoDCoordinate) {
        this.listCoordinates.add(twoDCoordinate);
    }

    public String getImageUid() {
        return imageUid;
    }

    public void setImageUid(String imageUid) {
        this.imageUid = imageUid;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public List<TwoDCoordinate> getListCoordinates() {
        return listCoordinates;
    }

    public void setListCoordinates(List<TwoDCoordinate> listCoordinates) {
        this.listCoordinates = listCoordinates;
    }

    public ROIShape getRoiShape() {
        return roiShape;
    }

    public void setRoiShape(ROIShape roiShape) {
        this.roiShape = roiShape;
    }

    private String epadShapeType2aimShapeType(ROIShape shapeType) {
        switch (shapeType) {
            case ARROW:
            case LINE:
                return "TwoDimensionMultiPoint";
            case CIRCLE:
                return "TwoDimensionCircle";
            case OVAL:
                return "TwoDimensionEllipse";
            case COMMENT:
            case TEXT:
                return "TwoDimensionPoint";
            case RECTANGLE:
            case POLY:
            case OPENPOLY:
            case SPLINE:
            case OPENSPLINE:
                return "TwoDimensionPolyline";
        }
        return "";
    }

    private ROIShape string2EdapShapeType(String shapeType) {
        if ("ARROW".equals(shapeType)) {
            return ROIShape.ARROW;
        }
        if ("LINE".equals(shapeType)) {
            return ROIShape.LINE;
        }
        if ("CIRCLE".equals(shapeType)) {
            return ROIShape.CIRCLE;
        }
        if ("OVAL".equals(shapeType)) {
            return ROIShape.OVAL;
        }
        if ("COMMENT".equals(shapeType)) {
            return ROIShape.COMMENT;
        }
        if ("TEXT".equals(shapeType)) {
            return ROIShape.TEXT;
        }
        if ("RECTANGLE".equals(shapeType)) {
            return ROIShape.RECTANGLE;
        }
        if ("POLY".equals(shapeType)) {
            return ROIShape.POLY;
        }
        if ("OPENPOLY".equals(shapeType)) {
            return ROIShape.OPENPOLY;
        }
        if ("SPLINE".equals(shapeType)) {
            return ROIShape.SPLINE;
        }
        if ("OPENSPLINE".equals(shapeType)) {
            return ROIShape.OPENSPLINE;
        }
        return ROIShape.NONE;
    }

    public TwoDimensionGeometricShapeEntity toAimV4() {
        TwoDimensionGeometricShapeEntity res = new TwoDimensionGeometricShapeEntity();

        switch (roiShape) {
            case ARROW:
            case LINE:
                res = new TwoDimensionMultiPoint();
            case CIRCLE:
                res = new TwoDimensionCircle();
            case OVAL:
                res = new TwoDimensionEllipse();
            case COMMENT:
            case TEXT:
                res = new TwoDimensionPoint();
            case RECTANGLE:
            case POLY:
            case OPENPOLY:
            case SPLINE:
            case OPENSPLINE:
                res = new TwoDimensionPolyline();
        }

        res.setImageReferenceUid(new II(this.imageUid));
        res.setReferencedFrameNumber(this.frame);
        res.setUri(new ST(this.roiShape.toString()));
        for (int i = 0; i < this.listCoordinates.size(); i++) {
            res.addTwoDimensionSpatialCoordinate(this.listCoordinates.get(i).toAimV4());
        }
        return res;
    }

    public boolean isRectangle() {
        if (this.listCoordinates.size() == 4) {
            return IsRectangleAnyOrder(this.listCoordinates.get(0), this.listCoordinates.get(1), this.listCoordinates.get(2), this.listCoordinates.get(3));
        }
        return false;
    }

    public boolean isCircle() {
        return this.roiShape.equals(ROIShape.CIRCLE);
    }

    public boolean isPolyline() {
        return this.roiShape.equals(ROIShape.POLY);
    }

    public boolean isMultiPoint() {
        return this.roiShape.equals(ROIShape.LINE);
    }

    public boolean isSpline() {
        return this.roiShape.equals(ROIShape.SPLINE);
    }

    private boolean IsOrthogonal(TwoDCoordinate a, TwoDCoordinate b, TwoDCoordinate c) {
        return (b.getX() - a.getX()) * (b.getX() - c.getX()) + (b.getY() - a.getY()) * (b.getY() - c.getY()) == 0;
    }

    private boolean IsRectangle(TwoDCoordinate a, TwoDCoordinate b, TwoDCoordinate c, TwoDCoordinate d) {
        return IsOrthogonal(a, b, c) && IsOrthogonal(b, c, d) && IsOrthogonal(c, d, a);
    }

    private boolean IsRectangleAnyOrder(TwoDCoordinate a, TwoDCoordinate b, TwoDCoordinate c, TwoDCoordinate d) {
        return IsRectangle(a, b, c, d) || IsRectangle(b, c, a, d) || IsRectangle(c, a, b, d);
    }
}
