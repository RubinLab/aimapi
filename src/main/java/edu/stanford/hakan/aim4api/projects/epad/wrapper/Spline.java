/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import edu.stanford.hakan.aim4api.base.CD;
import edu.stanford.hakan.aim4api.base.ST;

/**
 *
 * @author Hakan
 */
public class Spline extends GeometricShape {

    private static String splineKey = "^~^spline^~^";

    public static String getSplineKey() {
        return splineKey;
    }   
    
    public Spline() {
        super.setXsiType("TwoDimensionPolyline");
        super.setDescription(new ST(""));
    }
    
    @Override
    public void setDescription(ST description) {
        String desc = super.getDescription().getValue();
        if (!desc.contains(splineKey)) {
            desc += splineKey;
        }
        super.setDescription(new ST(desc));
    }

    @Override
    public ST getDescription() {
        String desc = super.getDescription().getValue();
        if (!desc.contains(splineKey)) {
            desc = desc.replace(splineKey, "");
        }
        return new ST(desc);
    }

    public void setInterpolationMethodName(String interpolationMethodName) {
        super.setInterpolationMethod(new CD(interpolationMethodName, "Method Name", "", ""));
    }

    public String getInterpolationMethodName() {
        if (super.getInterpolationMethod() != null) {
            super.getInterpolationMethod().getCode();
        }
        return "";
    }
}
