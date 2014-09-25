/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.hakan.aim4api.utility;

import edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinate;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class Imaging {

    public List<TwoDimensionSpatialCoordinate> parseTheTransformationResult(String transformationResult) {
        List<TwoDimensionSpatialCoordinate> res = new ArrayList<TwoDimensionSpatialCoordinate>();
        transformationResult = transformationResult.replace("M", "");
        List<String> listPoints = new ArrayList<String>();
        listPoints.addAll(Arrays.asList(transformationResult.split("L")));
        for (String pointStr : listPoints) {
            String[] xy=pointStr.split(",");
            TwoDimensionSpatialCoordinate twoDCoord = new TwoDimensionSpatialCoordinate();
            twoDCoord.setX(Double.parseDouble(xy[0]));
            twoDCoord.setY(Double.parseDouble(xy[1]));
            res.add(twoDCoord);
        }
        return res;
    }

    public List<TwoDimensionSpatialCoordinate> getPixelsOfCircle(TwoDimensionSpatialCoordinate center, double radius) {
        List<TwoDimensionSpatialCoordinate> res = new ArrayList<TwoDimensionSpatialCoordinate>();
        Ellipse2D.Double ellipse = new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius);
        Rectangle bounds = ellipse.getBounds();
        for (int x = bounds.x; x < bounds.width; x++) {
            for (int y = bounds.y; y < bounds.height; y++) {
                if (ellipse.contains(x, y)) {
                    TwoDimensionSpatialCoordinate twoDCoord = new TwoDimensionSpatialCoordinate();
                    twoDCoord.setX((double) x);
                    twoDCoord.setY((double) y);
                    res.add(twoDCoord);
                }
            }
        }
        return res;
    }
    
    public List<TwoDimensionSpatialCoordinate> getPixelsOfClosedShape(List<TwoDimensionSpatialCoordinate> points) {
        List<TwoDimensionSpatialCoordinate> res = new ArrayList<TwoDimensionSpatialCoordinate>();
        Path2D path = new Path2D.Double();
        path.moveTo(points.get(0).getX(), points.get(0).getY());
        for (int i = 1; i < points.size(); ++i) {
            path.lineTo(points.get(i).getX(), points.get(i).getY());
        }
        path.closePath();

        Rectangle bounds = path.getBounds();
        for (int x = bounds.x; x < bounds.width; x++) {
            for (int y = bounds.y; y < bounds.height; y++) {
                if (path.contains(x, y)) {
                    TwoDimensionSpatialCoordinate twoDCoord = new TwoDimensionSpatialCoordinate();
                    twoDCoord.setX((double) x);
                    twoDCoord.setY((double) y);
                    res.add(twoDCoord);
                }
            }
        }

        return res;
    }
}
