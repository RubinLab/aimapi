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
public class CharacteristicQuantification extends main.java.edu.stanford.hakan.aim4api.base.CharacteristicQuantification {

    /*
        CharacteristicQuantification ; Epad = Quantification

        //*** I need help to understand detail of the function.
        ? add(CharacteristicQuantification)

        + getXsiType()     
        + setAnnotatorConfidence(Double)

        //*** It seems that, it is always set by zero.
        ? setCagridId(Integer)

        + setName(String)
        + setXsiType(String)
    */
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
