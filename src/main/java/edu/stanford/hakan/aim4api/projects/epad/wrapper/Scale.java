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
public class Scale extends main.java.edu.stanford.hakan.aim4api.base.Scale {

    /*    
        Scale ; Epad = Scal
        
        //*** I need to see the details of the function.
        ? addCharacteristicQuantification(CharacteristicQuantification)  
        
        + setValue(String)
        + setName(String)
        + setDescription(String) (not exist) 
                
        //*** It seems that, it is always set by zero.
        ? setCagridId(int)
        
        + setAnnotatorConfidence(double)
     */
    
    public void setValue(String value) {
        if (this.getValue() == null) {
            this.setValue(new ST());
        }
        this.getValue().setValue(value);
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

    public String getDescription() {
        if (this.getValueDescription() != null) {
            return this.getValueDescription().getValue();
        }
        return "";
    }

    public void setDescription(String valueDescription) {
        if (this.getValueDescription() == null) {
            this.setValueDescription(new ST());
        }
        this.getValueDescription().setValue(valueDescription);
    }
 


}
