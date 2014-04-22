/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad;

import edu.stanford.hakan.aim4api.base.CD;
import edu.stanford.hakan.aim4api.base.ImagingPhysicalEntity;
import edu.stanford.hakan.aim4api.base.ST;

/**
 *
 * @author Hakan
 */
public class AnatomicEntity {

    private String codeValue;
    private String codeMeaning;
    private String codingSchemeDesignator;
    private String codingSchemeVersion;
    private Double annotatorConfidence;
    private Boolean isPresent;
    private String label;

    public AnatomicEntity() {
    }
    
    public AnatomicEntity(ImagingPhysicalEntity imagingPhysicalEntity) {
        if (imagingPhysicalEntity.getListTypeCode().size() > 0) {
            CD cd = imagingPhysicalEntity.getListTypeCode().get(0);
            if (cd.getCode() != null) {
                this.codeValue = cd.getCode();
            }
            if (cd.getCodeSystem() != null) {
                this.codeMeaning = cd.getCodeSystem();
            }
            if (cd.getCodeSystemName() != null) {
                this.codingSchemeDesignator = cd.getCodeSystemName();
            }
            if (cd.getCodeSystemVersion() != null) {
                this.codingSchemeVersion = cd.getCodeSystemVersion();
            }
        }
        if (imagingPhysicalEntity.getAnnotatorConfidence() != null) {
            this.annotatorConfidence = imagingPhysicalEntity.getAnnotatorConfidence();
        }
        if (imagingPhysicalEntity.getIsPresent() != null) {
            this.isPresent = imagingPhysicalEntity.getIsPresent();
        }
        if (imagingPhysicalEntity.getLabel() != null) {
            this.label = imagingPhysicalEntity.getLabel().getValue();
        }
    }

    
    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeMeaning() {
        return codeMeaning;
    }

    public void setCodeMeaning(String codeMeaning) {
        this.codeMeaning = codeMeaning;
    }

    public String getCodingSchemeDesignator() {
        return codingSchemeDesignator;
    }

    public void setCodingSchemeDesignator(String codingSchemeDesignator) {
        this.codingSchemeDesignator = codingSchemeDesignator;
    }

    public String getCodingSchemeVersion() {
        return codingSchemeVersion;
    }

    public void setCodingSchemeVersion(String codingSchemeVersion) {
        this.codingSchemeVersion = codingSchemeVersion;
    }

    public Double getAnnotatorConfidence() {
        return annotatorConfidence;
    }

    public void setAnnotatorConfidence(Double annotatorConfidence) {
        this.annotatorConfidence = annotatorConfidence;
    }

    public Boolean getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(Boolean isPresent) {
        this.isPresent = isPresent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ImagingPhysicalEntity toAimV4() {
        ImagingPhysicalEntity res = new ImagingPhysicalEntity();
        CD typeCode = new CD(codeValue, codeMeaning, codingSchemeDesignator, codingSchemeVersion);
        res.addTypeCode(typeCode);
        res.setAnnotatorConfidence(this.getAnnotatorConfidence());
        res.setIsPresent(this.getIsPresent());
        res.setLabel(new ST(this.getLabel()));
        return res;
    }
}
