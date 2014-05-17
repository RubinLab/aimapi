/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import edu.stanford.hakan.aim4api.base.Algorithm;
import edu.stanford.hakan.aim4api.base.CD;
import edu.stanford.hakan.aim4api.base.II;
import edu.stanford.hakan.aim4api.base.ST;

/**
 *
 * @author Hakan
 */
public class Calculation extends edu.stanford.hakan.aim4api.base.CalculationEntity {

    /*    
     Calculation ; Epad = Calc       
        
     //*** ReferencedGeometricShape was renamed to ImagingObservationEntityIsIdentifiedByGeometricShapeEntityStatement 
     //*** but I couldn't see the ImagingObservationEntityIsIdentifiedByGeometricShapeEntityStatement in the AIM V4 structure.
     //*** there is ImagingObservationEntityIsIdentifiedByTwoDimensionGeometricShapeEntityStatement and it is a kind of ImageAnnotationStatement
     //*** ImageAnnotation contains ImageAnnotationStatementCollection
     //*** in the Epad, the function is used to getReferencedGeometricShapeCollection and getReferencedGeometricShapeCollection is used to clone the Calculation class.
     //*** in AIM API, each class has its own clone method, so the function may not be necessary anymore.
     ? setReferencedGeometricShapeCollection(ReferencedGeometricShapeCollection) 
        
     //*** ReferencedCalculation was renamed to CalculationEntityReferencesCalculationEntityStatement
     //*** CalculationEntityReferencesCalculationEntityStatement is used in ImageAnnotationStatementCollection
     //*** ImageAnnotation contains ImageAnnotationStatementCollection
     ? setReferencedCalculationCollection(ReferencedCalculationCollection) 
        
     //*** I don't thing so it is used by Epad. Because it is related with creating ontology intances.
     ? setRdfID(String)
        
     + setMathML(String) 
     + setDescription(String) 
     + setCodingSchemeVersion(String) 
     + setCodingSchemeDesignator(String) 
        
     //*** I searched in Epad and It seems like unnecessary.
     ? setCodeValueCanBeNull(boolean) 
        
     + setCodeValue(String) 
     + setCodeMeaning(String) 
     + setCalculationResultCollection(CalculationResultCollection) 
        
     //*** It seems that, it is always set by zero. 
     ? setCagridId(Integer) 
        
     + setAlgorithmVersion(String) 
     + setAlgorithmName(String) 
     + getUid() 
     + getDescription() 
     + getCodingSchemeVersion() 
     + getCodingSchemeDesignator() 
     + getCodeValue() 
     + getCodeMeaning() 
     + getCalculationResultCollection() 
     + getAlgorithmVersion() 
     + getAlgorithmName()     
     */
    public String getUid() {
        if (this.getUniqueIdentifier() != null) {
            return this.getUniqueIdentifier().getRoot();
        }
        return "";
    }
    public void setUid(String uid) {
        super.setUniqueIdentifier(new II(uid));
    }

    public String getDescriptionEpad() {
        if (this.getDescription() != null) {
            return this.getDescription().getValue();
        }
        return "";
    }

    public void setDescription(String description) {
        if (this.getDescription() == null) {
            this.setDescription(new ST());
        }
        this.getDescription().setValue(description);
    }

    public String getMathMLEpad() {
        if (this.getMathML() != null) {
            return this.getMathML().getValue();
        }
        return "";
    }

    public void setMathML(String mathML) {
        if (this.getMathML() == null) {
            this.setMathML(new ST());
        }
        this.getMathML().setValue(mathML);
    }

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

    public String getAlgorithmVersion() {
        if (this.getAlgorithm() != null) {
            return this.getAlgorithm().getVersion().getValue();
        }
        return "";
    }

    public void setAlgorithmVersion(String algorithmVersion) {
        if (this.getAlgorithm() != null) {
            Algorithm algorithm = new Algorithm();
            this.setAlgorithm(algorithm);
        }
        this.getAlgorithm().setVersion(new ST(algorithmVersion));
    }

    public String getAlgorithmName() {
        if (this.getAlgorithm() != null) {
            return this.getAlgorithm().getName().getValue();
        }
        return "";
    }

    public void setAlgorithmName(String algorithmName) {
        if (this.getAlgorithm() != null) {
            Algorithm algorithm = new Algorithm();
            this.setAlgorithm(algorithm);
        }
        this.getAlgorithm().setName(new ST(algorithmName));
    }

    public void setCalculationResultCollection(edu.stanford.hakan.aim4api.projects.epad.wrapper.CalculationResultCollection calculationResultCollection) {
        super.setCalculationResultCollection(calculationResultCollection);
    }

    @Override
    public CalculationResultCollection getCalculationResultCollection() {
        return (CalculationResultCollection) super.getCalculationResultCollection();
    }

    public CalculationResult getItem(int j) {
        return (CalculationResult) super.getCalculationResultCollection().get(j).getClone();
    }

}
