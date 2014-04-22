/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad;

import edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinate;

/**
 *
 * @author Hakan
 */
public class TwoDCoordinate {
        
    private Integer coordinateIndex;
    private Double x;
    private Double y;

    public TwoDCoordinate() {
    }

    public TwoDCoordinate(Integer coordinateIndex, Double x, Double y) {
        this.coordinateIndex = coordinateIndex;
        this.x = x;
        this.y = y;
    }

    public TwoDCoordinate(TwoDimensionSpatialCoordinate twoDimensionSpatialCoordinate) {
        this.coordinateIndex = twoDimensionSpatialCoordinate.getCoordinateIndex();
        this.x = twoDimensionSpatialCoordinate.getX();
        this.y = twoDimensionSpatialCoordinate.getY();
    }

    public Integer getCoordinateIndex() {
        return coordinateIndex;
    }

    public void setCoordinateIndex(Integer coordinateIndex) {
        this.coordinateIndex = coordinateIndex;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
    
    public TwoDimensionSpatialCoordinate toAimV4()
    {
        TwoDimensionSpatialCoordinate res = new TwoDimensionSpatialCoordinate();
        res.setCoordinateIndex(coordinateIndex);
        res.setX(x);
        res.setY(y);
        return res;
    }
}
