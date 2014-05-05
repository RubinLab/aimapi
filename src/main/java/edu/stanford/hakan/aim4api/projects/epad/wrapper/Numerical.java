/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import edu.stanford.hakan.aim4api.base.Enumerations;
import edu.stanford.hakan.aim4api.base.ST;

/**
 *
 * @author Hakan
 */
public class Numerical extends edu.stanford.hakan.aim4api.base.Numerical {

    /*
        Numerical ; Epad = Num
        
        //*** I need to see the detail of the function.
        ? addCharacteristicQuantification(CharacteristicQuantification)
        
        + setAnnotatorConfidence(double)
        
        //*** It seems that, it is always set by zero.
        ? setCagridId(int)
        
        + setComparisonOperators(AimUtility.ComparisonOperators)
        + setName(String)
        + setUcumString(String)
        + setValue(Double)
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

    public Enumerations.ComparisonOperator getComparisonOperators() {
        return super.getOperator();
    }
    
    
    public void  setComparisonOperators(Enumerations.ComparisonOperator comparisonOperator) {
        super.setOperator(comparisonOperator);
    }
}
