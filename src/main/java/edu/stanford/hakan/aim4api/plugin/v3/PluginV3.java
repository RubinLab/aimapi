/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.plugin.v3;

import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.IAimXMLOperations;
import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.base.MarkupEntity;
import edu.stanford.hakan.aim4api.compability.aimv3.Annotation;
import edu.stanford.hakan.aim4api.compability.aimv3.Calculation;
import edu.stanford.hakan.aim4api.compability.aimv3.Circle;
import edu.stanford.hakan.aim4api.compability.aimv3.Converter;
import edu.stanford.hakan.aim4api.compability.aimv3.Ellipse;
import edu.stanford.hakan.aim4api.compability.aimv3.GeometricShape;
import edu.stanford.hakan.aim4api.compability.aimv3.MultiPoint;
import edu.stanford.hakan.aim4api.compability.aimv3.Orthogonal;
import edu.stanford.hakan.aim4api.compability.aimv3.Point;
import edu.stanford.hakan.aim4api.compability.aimv3.Polyline;
import edu.stanford.hakan.aim4api.compability.aimv3.Spline;
import edu.stanford.hakan.aim4api.plugin.*;
import edu.stanford.hakan.aim4api.plugin.v4.PluginV4;
import edu.stanford.hakan.aim4api.utility.Utility;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan
 */
@SuppressWarnings("serial")
public final class PluginV3   implements IAimXMLOperations {

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
            } else if ("TwoDimensionOrthogonal".equals(itemV4.getXsiType())) {
                this.geometricShape = new Orthogonal((edu.stanford.hakan.aim4api.base.TwoDimensionOrthogonal) itemV4);
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

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setXMLNode(Node node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        PluginV3 oth = (PluginV3) other;
        if (this.name == null ? oth.name != null : !this.name.equals(oth.name)) {
            return false;
        }
        if (this.calculation == null ? oth.calculation != null : !this.calculation.isEqualTo(oth.calculation)) {
            return false;
        }
        if (this.geometricShape == null ? oth.geometricShape != null : !this.geometricShape.isEqualTo(oth.geometricShape)) {
            return false;
        }
        if (this.pluginParemeterCollection == null ? oth.pluginParemeterCollection != null : !this.pluginParemeterCollection.isEqualTo(oth.pluginParemeterCollection)) {
            return false;
        }
        return true;
    }
    
    
    public PluginV3 getClone() {
        PluginV3 res = new PluginV3();
        if (this.getName() != null) {
            res.setName(this.getName());
        }
        if (this.getCalculation()!= null) {
            res.setCalculation(this.getCalculation().getClone());
        }
        if (this.getGeometricShape()!= null) {
            res.setGeometricShape(this.getGeometricShape().getClone());
        }
        if (this.getPluginParemeterCollection()!= null) {
            res.setPluginParemeterCollection(this.getPluginParemeterCollection().getClone());
        }
        return res;
    }

}
