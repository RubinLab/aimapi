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
public class Dimension extends edu.stanford.hakan.aim4api.base.Dimension {
    /*
        Epad = CalcDimension
    
        + getLabel()
        
        //*** It seems that, it is always set by zero.
        ? setCagridId(Integer)
        
        + setIndex(Integer)
        + setLabel(String)
        + setSize(Integer)
    */

    public String getLabelEpad() {
        if (this.getLabel() != null) {
            return this.getLabel().getValue();
        }
        return "";
    }

    public void setLabel(String label) {
        if (this.getLabel() == null) {
            this.setLabel(new ST());
        }
        this.getLabel().setValue(label);
    }

}
