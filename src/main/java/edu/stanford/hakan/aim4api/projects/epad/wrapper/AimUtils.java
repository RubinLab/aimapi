/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import com.google.gwt.i18n.client.DateTimeFormat;
import edu.stanford.hakan.aim4api.base.MarkupEntity;
import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.base.TwoDimensionCircle;
import edu.stanford.hakan.aim4api.base.TwoDimensionGeometricShapeEntity;
import edu.stanford.hakan.aim4api.base.TwoDimensionMultiPoint;
import edu.stanford.hakan.aim4api.base.TwoDimensionPolyline;
import static edu.stanford.hakan.aim4api.projects.epad.wrapper.Enumerations.ROIShape.LINE;
import static edu.stanford.hakan.aim4api.projects.epad.wrapper.Enumerations.ROIShape.OPENPOLY;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Hakan
 */
public class AimUtils {


    private static final Logger logger = Logger.getLogger("AimUtils");
    
//    /**
//     * create a geometric shape for this image
//     * @param id
//     * @param roiShape
//     * @param coords
//     * @return 
//     */
//    public static GeometricShape createGeometricShape(String id, GeometricShape.ROIShape roiShape,
//            List<TwoDimensionSpatialCoordinate> coords) {
//
//        int index = 0;
//        for (TwoDimensionSpatialCoordinate c : coords) {
//            c.setImageReferenceUID(id);
//            c.setReferencedFrameNumber(0);
//            c.setCoordinateIndex(index++);
//        }
//
//        TwoDimensionGeometricShapeEntity shape = new TwoDimensionGeometricShapeEntity();
//        switch (roiShape) {
//            case LINE:
//
//                shape = new TwoDimensionMultiPoint();
//                shape.setLineStyle(new ST("straight"));
//                for (int i = 0; i < coords.size(); i++) {
//                    double x = coords.get(i).getX();
//                    double y = coords.get(i).getY();
//                    shape.addTwoDimensionSpatialCoordinate(new TwoDimensionSpatialCoordinate(i, id, 0, x, y));
//                }
//                break;
//            case OPENPOLY:
//                shape = new TwoDimensionMultiPoint();
//                shape.setLineStyle(new ST("straight"));
//                for (int i = 0; i < coords.size(); i++) {
//                    // shape.addSpatialCoordinate(c);
//                    double x = coords.get(i).getX();
//                    double y = coords.get(i).getY();
//                    shape.addTwoDimensionSpatialCoordinate(new TwoDimensionSpatialCoordinate(i, id, 0, x, y));
//                }
//                break;
//            case POLY:
//                shape = new TwoDimensionPolyline();
//                shape.setLineStyle(new ST("straight"));
//                for (int i = 0; i < coords.size(); i++) {
//                    // shape.addSpatialCoordinate(c);
//                    double x = coords.get(i).getX();
//                    double y = coords.get(i).getY();
//                    shape.addTwoDimensionSpatialCoordinate(new TwoDimensionSpatialCoordinate(i, id, 0, x, y));
//                }
//
//                break;
//            case SPLINE:
//                // TODO mark this as a spline somehow
//                shape = new TwoDimensionPolyline();
//                shape.setLineStyle(new ST("spline"));
//
//                for (int i = 0; i < coords.size(); i++) {
//                    // shape.addSpatialCoordinate(c);
//                    double x = coords.get(i).getX();
//                    double y = coords.get(i).getY();
//                    shape.addTwoDimensionSpatialCoordinate(new TwoDimensionSpatialCoordinate(i, id, 0, x, y));
//                }
//                break;
//
//            case OPENSPLINE:
//                // TODO mark this as a spline somehow
//                shape = new TwoDimensionMultiPoint();
//                shape.setLineStyle(new ST("spline"));
//
//                for (int i = 0; i < coords.size(); i++) {
//                    // shape.addSpatialCoordinate(c);
//                    double x = coords.get(i).getX();
//                    double y = coords.get(i).getY();
//                    shape.addTwoDimensionSpatialCoordinate(new TwoDimensionSpatialCoordinate(i, id, 0, x, y));
//                }
//                break;
//
//            case CIRCLE:
//                shape = new TwoDimensionCircle();
//                shape.setLineStyle(new ST("none"));
//                for (int i = 0; i < coords.size(); i++) {
//                    double x = coords.get(i).getX();
//                    double y = coords.get(i).getY();
//                    shape.addTwoDimensionSpatialCoordinate(new TwoDimensionSpatialCoordinate(i, id, 0, x, y));
//                }
//                break;
//
//            case RECTANGLE:
//                // TODO mark this as a rectangle somehow
//                shape = new TwoDimensionPolyline();
//                shape.setLineStyle(new ST("rectangle"));
//                double x1 = Math.min(coords.get(0).getX(), coords.get(1).getX());
//                double y1 = Math.min(coords.get(0).getY(), coords.get(1).getY());
//                double x2 = Math.max(coords.get(0).getX(), coords.get(1).getX());
//                double y2 = Math.max(coords.get(0).getY(), coords.get(1).getY());
//                shape.addTwoDimensionSpatialCoordinate(new TwoDimensionSpatialCoordinate(0, id, 0, x1, y1));
//                shape.addTwoDimensionSpatialCoordinate(new TwoDimensionSpatialCoordinate(1, id, 0, x1, y2));
//                shape.addTwoDimensionSpatialCoordinate(new TwoDimensionSpatialCoordinate(2, id, 0, x2, y2));
//                shape.addTwoDimensionSpatialCoordinate(new TwoDimensionSpatialCoordinate(3, id, 0, x2, y1));
//                break;
//
//        }
//
//        MarkupEntity me = (MarkupEntity) shape;
//        return (GeometricShape) me;
//    }
    
    public static boolean makeNameUnique(List<ImageAnnotation> aims, ImageAnnotation aim) {

        boolean duplicate = false;
        duplicateloop:
        for (ImageAnnotation ia : aims) {
            if (!ia.equals(aim)) {
                if (ia.getName().equals(aim.getName())) {
                    duplicate = true;
                    break duplicateloop;
                }
            }
        }

        if (duplicate) {
            // Window.alert("Duplicate name for this Annotation.");
            boolean foundReplacement = false;
            int modifier = 1;
            while (!foundReplacement) {
                String possible = aim.getNameEpad().replaceAll("\\([0-9]+\\)", "")
                        + "(" + modifier++ + ")";
                duplicate = false;
                loop:
                for (ImageAnnotation ia : aims) {
                    if (ia.getName().equals(possible)) {
                        duplicate = true;
                        break loop;
                    }
                }
                if (!duplicate) {
                    aim.setName(new ST(possible));
                    return true;
                }
            }
        }
        return false;
    }

    public static String formatSeriesDate(String seriesDate) {

        DateTimeFormat incoming = DateTimeFormat.getFormat("yyyyMMdd");
        Date date = new Date();
        try {
            date = incoming.parse(seriesDate.substring(0, 8));
        } catch (Exception e) {
            logger.info("Error: formatSeriesDate " + e.getMessage());
        }
        DateTimeFormat outgoing = DateTimeFormat.getFormat("MM/dd/yyyy");
        return outgoing.format(date);
    }
    
    
//        private String epadShapeType2aimShapeType(GeometricShape.ROIShape shapeType) {
//        switch (shapeType) {
//            case ARROW:
//            case LINE:
//                return "TwoDimensionMultiPoint";
//            case CIRCLE:
//                return "TwoDimensionCircle";
//            case OVAL:
//                return "TwoDimensionEllipse";
//            case COMMENT:
//            case TEXT:
//                return "TwoDimensionPoint";
//            case RECTANGLE:
//            case POLY:
//            case OPENPOLY:
//            case SPLINE:
//            case OPENSPLINE:
//                return "TwoDimensionPolyline";
//        }
//        return "";
//    }
//
//    private GeometricShape.ROIShape string2EdapShapeType(String shapeType) {
//        if ("ARROW".equals(shapeType)) {
//            return GeometricShape.ROIShape.ARROW;
//        }
//        if ("LINE".equals(shapeType)) {
//            return GeometricShape.ROIShape.LINE;
//        }
//        if ("CIRCLE".equals(shapeType)) {
//            return GeometricShape.ROIShape.CIRCLE;
//        }
//        if ("OVAL".equals(shapeType)) {
//            return GeometricShape.ROIShape.OVAL;
//        }
//        if ("COMMENT".equals(shapeType)) {
//            return GeometricShape.ROIShape.COMMENT;
//        }
//        if ("TEXT".equals(shapeType)) {
//            return GeometricShape.ROIShape.TEXT;
//        }
//        if ("RECTANGLE".equals(shapeType)) {
//            return GeometricShape.ROIShape.RECTANGLE;
//        }
//        if ("POLY".equals(shapeType)) {
//            return GeometricShape.ROIShape.POLY;
//        }
//        if ("OPENPOLY".equals(shapeType)) {
//            return GeometricShape.ROIShape.OPENPOLY;
//        }
//        if ("SPLINE".equals(shapeType)) {
//            return GeometricShape.ROIShape.SPLINE;
//        }
//        if ("OPENSPLINE".equals(shapeType)) {
//            return GeometricShape.ROIShape.OPENSPLINE;
//        }
//        return GeometricShape.ROIShape.NONE;
//    }

}
