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
public class NonQuantifiable extends edu.stanford.hakan.aim4api.base.NonQuantifiable {

    /*    
        NonQuantifiable ; Epad = NonQuant
        
        //*** I need to see the deail of the function.
        ? addCharacteristicQuantification(CharacteristicQuantification)
        
        + setAnnotatorConfidence(double)
        
        //*** It seems that, it is always set by zero.
        ? setCagridId(int)
        
        + setCodeMeaning(String)
        + setCodeValue(String)
        + setCodingSchemeDesignator(String)
        + setCodingSchemeVersion(String)
        + setName(String)
     */
    
    public String getCodeValue() {
        if (this.getTypeCode() != null) {
            return this.getTypeCode().getCode();
        }
        return "";
    }

    public void setCodeValue(String codeValue) {
        if (this.getTypeCode() == null) {
            this.setTypeCode(new CD());
        }
        this.getTypeCode().setCode(codeValue);
    }

    public String getCodeMeaning() {
        if (this.getTypeCode() != null) {
            return this.getTypeCode().getCodeSystem();
        }
        return "";
    }

    public void setCodeMeaning(String codeMeaning) {
        if (this.getTypeCode() == null) {
            this.setTypeCode(new CD());
        }
        this.getTypeCode().setCodeSystem(codeMeaning);

    }

    public String getCodingSchemeVersion() {
        if (this.getTypeCode() != null) {
            return this.getTypeCode().getCodeSystemVersion();
        }
        return "";
    }

    public void setCodingSchemeVersion(String codingSchemeVersion) {
        if (this.getTypeCode() == null) {
            this.setTypeCode(new CD());
        }
        this.getTypeCode().setCodeSystemVersion(codingSchemeVersion);
    }

    public String getCodingSchemeDesignator() {
        if (this.getTypeCode() != null) {
            return this.getTypeCode().getCodeSystemName();
        }
        return "";
    }

    public void setCodingSchemeDesignator(String codingSchemeDesignator) {
        if (this.getTypeCode() == null) {
            this.setTypeCode(new CD());
        }
        this.getTypeCode().setCodeSystemName(codingSchemeDesignator);
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
