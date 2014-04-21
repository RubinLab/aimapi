/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.edu.stanford.hakan.aim4api.projects.epad.wrapper;

import java.util.ArrayList;
import java.util.List;
import main.java.edu.stanford.hakan.aim4api.base.MarkupEntity;

/**
 *
 * @author Hakan
 */
public class GeometricShapeCollection extends main.java.edu.stanford.hakan.aim4api.base.MarkupEntityCollection {

    public void addGeometricShape(GeometricShape newGeometricShape) {
        super.addMarkupEntity(newGeometricShape);
    }

    public List<GeometricShape> getGeometricShapeList() {
        List<MarkupEntity> temp = super.getMarkupEntityList();
        List<GeometricShape> res = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            res.add((GeometricShape) temp.get(i));
        }
        return res;
    }

    @Override
    public GeometricShape get(int index) {
        if (index >= 0 && index <= super.getMarkupEntityList().size() - 1) {
            return (GeometricShape) super.getMarkupEntityList().get(index);
        }
        return null;
    }
}
