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
public class ImagingPhysicalEntityCharacteristic extends edu.stanford.hakan.aim4api.base.ImagingPhysicalEntityCharacteristic {

    /*
        ImagingPhysicalEntityCharacteristic ; V3 = AnatomicEntityCharacteristic ; Epad = AEC
        
        //*** Need help to understand following functions.
        ? AddAnatomicEntityCharacteristic(AnatomicEntityCharacteristic)
        ? addCharacteristicQuantification(Intl)
        ? addCharacteristicQuantification(NonQuant)
        ? addCharacteristicQuantification(Num)
        ? addCharacteristicQuantification(Quant)
        ? addCharacteristicQuantification(Scal)
        
        + getAnnotatorConfidence()
        getCharacteristicQuantificationCollection()
        + getCodeMeaning()
        + getCodeValue()
        + getCodingSchemeDesignator()
        + getLabel()
        + setAnnotatorConfidence(double)
                
        //*** It seems that, it is always set by zero.
        ? setCagridId(Integer)
        
        + setCodeMeaning(String)
        + setCodeValue(String)
        + setCodingSchemeDesignator(String)
        + setCodingSchemeVersion(String)
        + setLabel(String)
     */
    
    public String getCodeValue() {
        if (this.getListTypeCode() != null && this.getListTypeCode().size() > 0) {
            return this.getListTypeCode().get(0).getCode();
        }
        return "";
    }

    public void setCodeValue(String codeValue) {
        if (this.getListTypeCode() == null && this.getListTypeCode().size() <= 0) {
            this.getListTypeCode().add(new CD());
        }
        this.getListTypeCode().get(0).setCode(codeValue);
    }

    public String getCodeMeaning() {
        if (this.getListTypeCode() != null && this.getListTypeCode().size() > 0) {
            return this.getListTypeCode().get(0).getCodeSystem();
        }
        return "";
    }

    public void setCodeMeaning(String codeMeaning) {
        if (this.getListTypeCode() == null && this.getListTypeCode().size() <= 0) {
            this.getListTypeCode().add(new CD());
        }
        this.getListTypeCode().get(0).setCodeSystem(codeMeaning);

    }

    public String getCodingSchemeVersion() {
        if (this.getListTypeCode() != null && this.getListTypeCode().size() > 0) {
            return this.getListTypeCode().get(0).getCodeSystemVersion();
        }
        return "";
    }

    public void setCodingSchemeVersion(String codingSchemeVersion) {
        if (this.getListTypeCode() == null && this.getListTypeCode().size() <= 0) {
            this.getListTypeCode().add(new CD());
        }
        this.getListTypeCode().get(0).setCodeSystemVersion(codingSchemeVersion);
    }

    public String getCodingSchemeDesignator() {
        if (this.getListTypeCode() != null && this.getListTypeCode().size() > 0) {
            return this.getListTypeCode().get(0).getCodeSystemName();
        }
        return "";
    }

    public void setCodingSchemeDesignator(String codingSchemeDesignator) {
        if (this.getListTypeCode() == null && this.getListTypeCode().size() <= 0) {
            this.getListTypeCode().add(new CD());
        }
        this.getListTypeCode().get(0).setCodeSystemName(codingSchemeDesignator);
    }

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
