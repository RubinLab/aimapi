/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.edu.stanford.hakan.aim4api.projects.epad.wrapper;

import main.java.edu.stanford.hakan.aim4api.base.ST;

/**
 *
 * @author Hakan
 */
public class Interval extends main.java.edu.stanford.hakan.aim4api.base.Interval {

    /*
        Interval ; Epad = Intl
        
        //*** I need to see the details of the function.
        ? addCharacteristicQuantification(CharacteristicQuantification)
        
        + setAnnotatorConfidence(double)
        
        //*** It seems that, it is always set by zero.
        ? setCagridId(int)
        
        + setMaxOperator(AimUtility.ComparisonOperators)
        + setMaxValue(Double)
        + setMinOperator(AimUtility.ComparisonOperators)
        + setMinValue(Double)
        + setName(String)
        + setUcumString(String)    
     */
    public String getUcumStringEpad() {
        if (this.getUcumString() != null) {
            return this.getUcumString().getValue();
        }
        return "";
    }

    public void setUcumString(String ucumString) {
        if (this.getUcumString() == null) {
            this.setUcumString(new ST());
        }
        this.getUcumString().setValue(ucumString);
    }

    public String getName() {
        if (this.getLabel() != null) {
            return this.getLabel().getValue();
        }
        return "";
    }

    public void setName(String label) {
        if (this.getLabel() == null) {
            this.setLabel(new ST());
        }
        this.getLabel().setValue(label);
    }

}