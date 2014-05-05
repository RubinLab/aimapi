/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class SpatialCoordinateCollection extends edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinateCollection  {
        
    public void addSpatialCoordinate(SpatialCoordinate newSpatialCoordinate) {
        super.addTwoDimensionSpatialCoordinate(newSpatialCoordinate);
    }

    public List<SpatialCoordinate> getSpatialCoordinateList() {
        List<edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinate> temp = super.getTwoDimensionSpatialCoordinateList();
        List<SpatialCoordinate> res = new ArrayList<SpatialCoordinate>();
        for (int i = 0; i < temp.size(); i++) {
            res.add((SpatialCoordinate) temp.get(i));
        }
        return res;
    }

    @Override
    public SpatialCoordinate get(int index) {
        if (index >= 0 && index <= super.getTwoDimensionSpatialCoordinateList().size() - 1) {
            return (SpatialCoordinate) super.getTwoDimensionSpatialCoordinateList().get(index);
        }
        return null;
    }
    
}
