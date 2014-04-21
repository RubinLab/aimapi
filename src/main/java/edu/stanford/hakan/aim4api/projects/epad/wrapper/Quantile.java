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
public class Quantile extends main.java.edu.stanford.hakan.aim4api.base.Quantile {

    /*
        Quantile ; Epad = Quant
        
        //*** I need to see the deail of the function.
        ? addCharacteristicQuantification(CharacteristicQuantification)
     
        + setAnnotatorConfidence(double)
        + setBin(int)
    
        //*** It seems that, it is always set by zero.
        ? setCagridId(int)
    
        + setName(String)
     */
    public void setBin(int bin) {
        this.setBins(bin);
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
