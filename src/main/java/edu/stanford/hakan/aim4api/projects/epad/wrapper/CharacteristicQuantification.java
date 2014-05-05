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
public class CharacteristicQuantification extends edu.stanford.hakan.aim4api.base.CharacteristicQuantification {

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

    public Numerical toNumerical() {
        edu.stanford.hakan.aim4api.base.CharacteristicQuantification a = (edu.stanford.hakan.aim4api.base.CharacteristicQuantification) this;
        edu.stanford.hakan.aim4api.base.Numerical b = (edu.stanford.hakan.aim4api.base.Numerical) a;
        edu.stanford.hakan.aim4api.projects.epad.wrapper.Numerical c = (edu.stanford.hakan.aim4api.projects.epad.wrapper.Numerical) b;
        return c;
    }
    
    public Interval toInterval() {
        edu.stanford.hakan.aim4api.base.CharacteristicQuantification a = (edu.stanford.hakan.aim4api.base.CharacteristicQuantification) this;
        edu.stanford.hakan.aim4api.base.Interval b = (edu.stanford.hakan.aim4api.base.Interval) a;
        edu.stanford.hakan.aim4api.projects.epad.wrapper.Interval c = (edu.stanford.hakan.aim4api.projects.epad.wrapper.Interval) b;
        return c;
    }
    
    public Quantile toQuantile() {
        edu.stanford.hakan.aim4api.base.CharacteristicQuantification a = (edu.stanford.hakan.aim4api.base.CharacteristicQuantification) this;
        edu.stanford.hakan.aim4api.base.Quantile b = (edu.stanford.hakan.aim4api.base.Quantile) a;
        edu.stanford.hakan.aim4api.projects.epad.wrapper.Quantile c = (edu.stanford.hakan.aim4api.projects.epad.wrapper.Quantile) b;
        return c;
    }
    
    public Scale toScale() {
        edu.stanford.hakan.aim4api.base.CharacteristicQuantification a = (edu.stanford.hakan.aim4api.base.CharacteristicQuantification) this;
        edu.stanford.hakan.aim4api.base.Scale b = (edu.stanford.hakan.aim4api.base.Scale) a;
        edu.stanford.hakan.aim4api.projects.epad.wrapper.Scale c = (edu.stanford.hakan.aim4api.projects.epad.wrapper.Scale) b;
        return c;
    }
    
    
    public NonQuantifiable toNonQuantifiable() {
        edu.stanford.hakan.aim4api.base.CharacteristicQuantification a = (edu.stanford.hakan.aim4api.base.CharacteristicQuantification) this;
        edu.stanford.hakan.aim4api.base.NonQuantifiable b = (edu.stanford.hakan.aim4api.base.NonQuantifiable) a;
        edu.stanford.hakan.aim4api.projects.epad.wrapper.NonQuantifiable c = (edu.stanford.hakan.aim4api.projects.epad.wrapper.NonQuantifiable) b;
        return c;
    }
}
