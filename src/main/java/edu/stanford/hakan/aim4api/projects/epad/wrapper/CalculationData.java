/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import edu.stanford.hakan.aim4api.base.ST;

/**
 *
 * @author Hakan
 */
public class CalculationData extends edu.stanford.hakan.aim4api.base.CalculationData {
    /*
        CalculationData ; Epad = CalcData
        
        + getCoordinateCollection()
        + getValue()       
        
        //*** It seems that, it is always set by zero. 
        ? setCagridId(Integer) 
        
        + setCoordinateCollection(CoordinateCollection)
     + setValue(Double)
     */

    public String getValueEpad() {
        if (super.getValue() != null) {
            return super.getValue().getValue();
        }
        return "";
    }

    public void setValue(String value) {
        if (super.getValue() == null) {
            super.setValue(new ST());
        }
        this.getValue().setValue(value);
    }

    public Coordinate getCoordItem(int k) {
        return (Coordinate) super.getCoordinateCollection().getCoordinateList().get(k).getClone();
    }

}
