/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.plugin.v3;

import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.base.MarkupEntity;
import edu.stanford.hakan.aim4api.compability.aimv3.Annotation;
import edu.stanford.hakan.aim4api.compability.aimv3.Calculation;
import edu.stanford.hakan.aim4api.compability.aimv3.Circle;
import edu.stanford.hakan.aim4api.compability.aimv3.Converter;
import edu.stanford.hakan.aim4api.compability.aimv3.Ellipse;
import edu.stanford.hakan.aim4api.compability.aimv3.GeometricShape;
import edu.stanford.hakan.aim4api.compability.aimv3.MultiPoint;
import edu.stanford.hakan.aim4api.compability.aimv3.Point;
import edu.stanford.hakan.aim4api.compability.aimv3.Polyline;
import edu.stanford.hakan.aim4api.compability.aimv3.Spline;
import edu.stanford.hakan.aim4api.plugin.*;
import edu.stanford.hakan.aim4api.plugin.v4.PluginV4;
import edu.stanford.hakan.aim4api.utility.Utility;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakan
 */
@SuppressWarnings("serial")
public final class PluginV3 {

    private PluginCollectionV3 pluginCollection = null;
    private String name = "";
    private Calculation calculation = null;
    private GeometricShape geometricShape = null;
    private PluginParemeterCollection pluginParemeterCollection = new PluginParemeterCollection();

    public PluginV3() {
    }

    public PluginV3(PluginV4 v4) {

        this.name = v4.getName();
        if (v4.getCalculationEntity() != null) {
            this.calculation = new Calculation(v4.getCalculationEntity(), v4.getPluginCollection().getImageAnnotation());
        }
        if (v4.getMarkupEntity() != null) {
            MarkupEntity itemV4 = v4.getMarkupEntity();
            if ("TwoDimensionCircle".equals(itemV4.getXsiType())) {
                this.geometricShape = new Circle((edu.stanford.hakan.aim4api.base.TwoDimensionCircle) itemV4);
            } else if ("TwoDimensionEllipse".equals(itemV4.getXsiType())) {
                this.geometricShape = new Ellipse((edu.stanford.hakan.aim4api.base.TwoDimensionEllipse) itemV4);
            } else if ("TwoDimensionMultiPoint".equals(itemV4.getXsiType())) {
                this.geometricShape = new MultiPoint((edu.stanford.hakan.aim4api.base.TwoDimensionMultiPoint) itemV4);
            } else if ("TwoDimensionPoint".equals(itemV4.getXsiType())) {
                this.geometricShape = new Point((edu.stanford.hakan.aim4api.base.TwoDimensionPoint) itemV4);
            } else if ("TwoDimensionPolyline".equals(itemV4.getXsiType())) {
                if (Utility.isSpline(itemV4)) {
                    this.geometricShape = new Spline((edu.stanford.hakan.aim4api.base.TwoDimensionSpline) itemV4);
                } else {
                    this.geometricShape = new Polyline((edu.stanford.hakan.aim4api.base.TwoDimensionPolyline) itemV4);
                }
            } else if ("TwoDimensionSpline".equals(itemV4.getXsiType())) {
                this.geometricShape = new Spline((edu.stanford.hakan.aim4api.base.TwoDimensionSpline) itemV4);
            }
        }
        if (v4.getPluginParemeterCollection() != null) {
            this.pluginParemeterCollection = v4.getPluginParemeterCollection();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PluginCollectionV3 getPluginCollection() {
        return pluginCollection;
    }

    public Calculation getCalculation() {
        return calculation;
    }

    public void setCalculation(Calculation calculation) {
        this.calculation = calculation;
    }

    public GeometricShape getGeometricShape() {
        return geometricShape;
    }

    public void setGeometricShape(GeometricShape geometricShape) {
        this.geometricShape = geometricShape;
    }

    public PluginParemeterCollection getPluginParemeterCollection() {
        return pluginParemeterCollection;
    }

    public void setPluginParemeterCollection(PluginParemeterCollection pluginParemeterCollection) {
        this.pluginParemeterCollection = pluginParemeterCollection;
    }

    public void addPluginParameter(PluginParameter newPluginParameter) {
        this.pluginParemeterCollection.addPluginParameter(newPluginParameter);
    }

    public void addPluginParameter(String name, String value) {
        this.pluginParemeterCollection.addPluginParameter(new PluginParameter(name, value));
    }

    public PluginV4 toAimV4(ImageAnnotation imageAnnotation) {
        PluginV4 res = new PluginV4();
        res.setName(this.getName());
        if (this.geometricShape != null) {
            res.setMarkupEntity(this.geometricShape.toAimV4());
        }
        if (this.calculation != null) {
            res.setCalculationEntity(this.calculation.toAimV4(imageAnnotation));
        }
        res.setPluginParemeterCollection(pluginParemeterCollection);
        return res;
    }
}
