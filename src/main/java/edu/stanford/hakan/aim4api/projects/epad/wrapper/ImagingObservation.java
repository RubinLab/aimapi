/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import edu.stanford.hakan.aim4api.base.CD;
import edu.stanford.hakan.aim4api.base.ST;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class ImagingObservation extends edu.stanford.hakan.aim4api.base.ImagingObservationEntity {

    /*
     ImagingObservationEntity ; V3 = ImagingObservation ; Epad = IO    

     //*** I need the detail of this function.
     ? addAll(Collection<? extends ImagingObservation>)
        
     + getAnnotatorConfidence()
     + getCodeMeaning()
     + getCodeValue()
     + getCodingSchemeDesignator()
     + getImagingObservationCharacteristicCollection()
     + getLabel()
     + setAnnotatorConfidence(double)      
    
     //*** It seems that, it is always set by zero.
     ? setCagridId(Integer)
    
     + setCodeMeaning(String)
     + setCodeValue(String)
     + setCodingSchemeDesignator(String)
     + setCodingSchemeVersion(String)
     + setImagingObservationCharacteristicCollection(ImagingObservationCharacteristicCollection)
     + setIsPresent(Boolean)
     + setLabel(String)
    
     //*** ReferencedGeometricShape was renamed to ImagingObservationEntityIsIdentifiedByGeometricShapeEntityStatement 
     //*** but I couldn't see the ImagingObservationEntityIsIdentifiedByGeometricShapeEntityStatement in the AIM V4 structure.
     //*** there is ImagingObservationEntityIsIdentifiedByTwoDimensionGeometricShapeEntityStatement and it is a kind of ImageAnnotationStatement
     //*** ImageAnnotation contains ImageAnnotationStatementCollection
     ? setListReferencedGeometricShape(List<ReferencedGeometricShape>)
     */
    public String getCodeValue() {
        if (this.getListTypeCode() != null && this.getListTypeCode().size() > 0) {
            return this.getListTypeCode().get(0).getCode();
        }
        return "";
    }

    public void setCodeValue(String codeValue) {
        if (this.getListTypeCode() == null || this.getListTypeCode().size() <= 0) {
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
        if (this.getListTypeCode() == null || this.getListTypeCode().size() <= 0) {
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
        if (this.getListTypeCode() == null || this.getListTypeCode().size() <= 0) {
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
        if (this.getListTypeCode() == null || this.getListTypeCode().size() <= 0) {
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

    public ImagingObservationCharacteristicCollection getImagingObservationCharacteristicCollectionEpad() {
        return (ImagingObservationCharacteristicCollection) super.getImagingObservationCharacteristicCollection();
    }

    public ImagingObservationCharacteristic getItem(int j) {
        return (ImagingObservationCharacteristic) super.getImagingObservationCharacteristicCollection().getImagingObservationCharacteristicList().get(j).getClone();
    }

    public List<ImagingObservationCharacteristic> getIOCs() {
        List<ImagingObservationCharacteristic> result = new ArrayList<ImagingObservationCharacteristic>();
        for (int i = 0; i < super.getImagingObservationCharacteristicCollection().getImagingObservationCharacteristicList().size(); i++) {
            result.add((ImagingObservationCharacteristic) super.getImagingObservationCharacteristicCollection().getImagingObservationCharacteristicList().get(i).getClone());
        }
        return result;
    }
    


}
