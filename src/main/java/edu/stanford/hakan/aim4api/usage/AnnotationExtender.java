/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.usage;

import java.util.ArrayList;

import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.Algorithm;
import edu.stanford.hakan.aim4api.base.CD;
import edu.stanford.hakan.aim4api.base.CalculationData;
import edu.stanford.hakan.aim4api.base.CalculationEntity;
import edu.stanford.hakan.aim4api.base.CalculationResult;
import edu.stanford.hakan.aim4api.base.CompactCalculationResult;
import edu.stanford.hakan.aim4api.base.Dimension;
import edu.stanford.hakan.aim4api.base.Enumerations;
import edu.stanford.hakan.aim4api.base.Enumerations.CalculationResultIdentifier;
import edu.stanford.hakan.aim4api.base.ExtendedCalculationResult;
import edu.stanford.hakan.aim4api.base.ImageAnnotationCollection;
import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.project.epad.Aim4;

/**
 *
 * @author Hakan
 */
public class AnnotationExtender {

	//TODO old version.. remove all references
	public static ImageAnnotationCollection addFeature(ImageAnnotationCollection imageAnnotationCollection, double[] featureValue, String[] featureName, double featureVersion) throws AimException {
		return AnnotationExtender.addFeature(imageAnnotationCollection, featureValue, featureName, featureVersion, null);
	}	
	public static ImageAnnotationCollection addFeature(ImageAnnotationCollection imageAnnotationCollection, double[] featureValue, String[] featureName, double featureVersion, CD calcCD) throws AimException {
		if (featureValue.length != featureName.length) {
            throw new AimException("AimException: lenght of featureValue and featureString must be equal");
        }

        CalculationEntity calculation = getCalculationByFeatureNameList(imageAnnotationCollection, featureName);
        if (calculation != null) {
            for (int i = 0; i < featureValue.length; i++) {
                if (featureName[i] == null) {
                    continue;
                }  
                CalculationResult calculationResult = getCalculationResultByFeatureName(calculation, featureName[i]);
                if (calculationResult != null) {
                	if (calculationResult instanceof CompactCalculationResult)
                		((CompactCalculationResult)calculationResult).setValue(new ST(String.valueOf(featureValue[i])));
                	else if (calculationResult instanceof ExtendedCalculationResult)
                		((ExtendedCalculationResult)calculationResult).getCalculationDataCollection().getCalculationDataList().get(0).setValue(new ST(String.valueOf(featureValue[i])));
                } else {
                    addFeatureToCalculation(calculation, featureValue[i], featureName[i],null);
                }
            }
        } else {
            calculation = new CalculationEntity();
            Algorithm alg=new Algorithm();
    		alg.setName(new ST("Plugin"));
    		alg.setVersion(new ST(Double.toString(featureVersion)));
    		ArrayList<CD> types=new ArrayList<>();
    		types.add(new CD("99EPADA2","Plugin","99EPAD"));
    		alg.setType(types);
    		calculation.setAlgorithm(alg);
    		if (calcCD!=null) {
    			
    			calculation.addTypeCode(new CD(calcCD.getCode(),calcCD.getDisplayName().getValue(),calcCD.getCodeSystemName()));
    			calculation.setDescription(new ST(calcCD.getDisplayName().getValue()));
    		}else {

    			calculation.addTypeCode(new CD("99EPADF0","Feature Extraction","99EPAD"));
    			calculation.setDescription(new ST("Feature Extraction"));

            }

            for (int i = 0; i < featureValue.length; i++) {
                if (featureName[i] == null) {
                    continue;
                }
                addFeatureToCalculation(calculation, featureValue[i], featureName[i],null);
            }
            imageAnnotationCollection.getImageAnnotation().addCalculationEntity(calculation);
        }

        
        return imageAnnotationCollection;

	}
	//for keeping backwards compatibility
	public static ImageAnnotationCollection addFeature(ImageAnnotationCollection imageAnnotationCollection, double featureValue, CD feature, double featureVersion) throws AimException {
		return AnnotationExtender.addFeature(imageAnnotationCollection, featureValue, feature, featureVersion, null, feature.getDisplayName().getValue());
	}	
	
	/**
	 * add one feature as a calculation
	 * @param imageAnnotationCollection
	 * @param featureValue
	 * @param featureName
	 * @param featureVersion
	 * @param calcCD
	 * @return
	 * @throws AimException
	 */
	public static ImageAnnotationCollection addFeature(ImageAnnotationCollection imageAnnotationCollection, double featureValue, CD feature, double featureVersion, CD calcCD, String label) throws AimException {
		
       return addFeature(imageAnnotationCollection, featureValue, feature, featureVersion, calcCD, label, null);
    }
	
	public static ImageAnnotationCollection addFeature(ImageAnnotationCollection imageAnnotationCollection, double featureValue, CD feature, double featureVersion, CD calcCD, String label, String unit) throws AimException {
		
       
        CalculationEntity calculation = getCalculationByFeatureName(imageAnnotationCollection, feature.getDisplayName().getValue());
        
        if (calculation != null) {
        	if (calculation.getCalculationResultCollection().getCalculationResultList().get(0) instanceof CompactCalculationResult)
        		if (label.equalsIgnoreCase(((CompactCalculationResult)calculation.getCalculationResultCollection().getCalculationResultList().get(0)).getDimensionCollection().get(0).getLabel().getValue()))
        			((CompactCalculationResult)calculation.getCalculationResultCollection().getCalculationResultList().get(0)).setValue(new ST(String.valueOf(featureValue)));
        		else{//add a new calculation result
        			addFeatureToCalculation(calculation,  featureValue,  label,  unit);
        		}
        			
        	else if (calculation.getCalculationResultCollection().getCalculationResultList().get(0) instanceof ExtendedCalculationResult)
        		if (label.equalsIgnoreCase(((ExtendedCalculationResult)calculation.getCalculationResultCollection().getCalculationResultList().get(0)).getDimensionCollection().get(0).getLabel().getValue()))
            		((ExtendedCalculationResult)calculation.getCalculationResultCollection().getCalculationResultList().get(0)).getCalculationDataCollection().getCalculationDataList().get(0).setValue(new ST(String.valueOf(featureValue)));
        		else{//add a new calculation result
        			addFeatureToCalculation(calculation,  featureValue,  label,  unit);
        		}
        } else {
        	imageAnnotationCollection.getImageAnnotation().addCalculationEntity(createCalculationForFeature(imageAnnotationCollection, featureValue, feature, calcCD, label, unit));
        }
            
        return imageAnnotationCollection;
    }

    private static CalculationResult getCalculationResultByFeatureName(CalculationEntity calculation, String featureName) {
        for (CalculationResult calculationResult : calculation.getCalculationResultCollection().getCalculationResultList()) {
            for (Dimension dimension : calculationResult.getDimensionCollection().getDimensionList()) {
                if (dimension.getLabel().equals(featureName)) {
                    return calculationResult;
                }
            }
        }
        return null;
    }
    
    /**
     * get the calculation entity with featurename
     * @param imageAnnotationCollection
     * @param featureName
     * @return
     */
    private static CalculationEntity getCalculationByFeatureName(ImageAnnotationCollection imageAnnotationCollection, String featureName) {
        for (CalculationEntity calculation : imageAnnotationCollection.getImageAnnotation().getCalculationEntityCollection().getCalculationEntityList()) {
            if ((calculation.getListTypeCode().get(0).getDisplayName().getValue()!=null && calculation.getListTypeCode().get(0).getDisplayName().getValue().equals(featureName)) ||(calculation.getListTypeCode().get(0).getCodeSystem()!=null && calculation.getListTypeCode().get(0).getCodeSystem().equals(featureName)) || calculation.getDescription().getValue().equals(featureName)) {
                return calculation;
                       
            }
        }
        return null;
    }


    private static CalculationEntity getCalculationByFeatureNameList(ImageAnnotationCollection imageAnnotationCollection, String[] featureName) {
        for (CalculationEntity calculation : imageAnnotationCollection.getImageAnnotation().getCalculationEntityCollection().getCalculationEntityList()) {
            for (CalculationResult calculationResult : calculation.getCalculationResultCollection().getCalculationResultList()) {
                for (Dimension dimension : calculationResult.getDimensionCollection().getDimensionList()) {
                    for (String fName : featureName) {
                        if (dimension.getLabel().equals(fName)) {
                            return calculation;
                        }
                    }
                }
            }
        }
        return null;
    }

    private static void addFeatureToCalculation(CalculationEntity calculation, double featureValue, String featureName, String unit) {

        // Create a CalculationResult instance
    	CompactCalculationResult calculationResult = new CompactCalculationResult();
        calculationResult.setType(CalculationResultIdentifier.Scalar);
        if (unit==null)
        	unit="ratio";
        calculationResult.setUnitOfMeasure(new ST(Aim4.getUCUMUnit(unit)));
        //ml double
        calculationResult.setDataType(new CD("C48870","Double","NCI"));
        calculationResult.setType(Enumerations.CalculationResultIdentifier.Scalar);
        calculationResult.setValue(new ST(String.valueOf(featureValue)));
        // Create a Dimension instance
        Dimension dimension = new Dimension(0, 1, featureName);
        // Add dimension to calculationResult
        calculationResult.addDimension(dimension);
        // Add calculationResult to calculation
        calculation.addCalculationResult(calculationResult);

    }
    
    /**
     * create feature as a standalone calculation
     * @param iac
     * @param featureValue
     * @param featureName
     * @throws AimException 
     */
    private static CalculationEntity createCalculationForFeature(ImageAnnotationCollection iac, double featureValue, CD feature, CD parentCD, String label) throws AimException {
    	
    	return createCalculationForFeature(iac, featureValue, feature, parentCD, label, "ratio");
    }
    
    private static CalculationEntity createCalculationForFeature(ImageAnnotationCollection iac, double featureValue, CD feature, CD parentCD, String label, String unit) throws AimException {
    	
        CalculationEntity calculation = new CalculationEntity();
        Algorithm alg=new Algorithm();
		alg.setName(new ST(parentCD.getDisplayName().getValue()));
		alg.setVersion(new ST(parentCD.getCodeSystemVersion()));
		ArrayList<CD> types=new ArrayList<>();
		types.add(parentCD);
		alg.setType(types);
		calculation.setAlgorithm(alg);
    	
    	if (feature!=null) {
			
			calculation.addTypeCode(new CD(feature.getCode(),feature.getDisplayName().getValue(),feature.getCodeSystemName()));
			calculation.setDescription(new ST(feature.getDisplayName().getValue()));
		}else {

			calculation.addTypeCode(new CD("99EPADF0","Feature Extraction","99EPAD"));
			calculation.setDescription(new ST("Feature Extraction"));

        }
    	addFeatureToCalculation(calculation, featureValue, label, unit);
    	
        
        return calculation;
        
    }
}
