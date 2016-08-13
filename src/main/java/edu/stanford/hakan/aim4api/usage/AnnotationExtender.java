/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.usage;

import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.CD;
import edu.stanford.hakan.aim4api.base.ImageAnnotationCollection;
import edu.stanford.hakan.aim4api.compability.aimv3.AimUtility.CalculationResultIdentifier;
import edu.stanford.hakan.aim4api.compability.aimv3.Calculation;
import edu.stanford.hakan.aim4api.compability.aimv3.CalculationData;
import edu.stanford.hakan.aim4api.compability.aimv3.CalculationResult;
import edu.stanford.hakan.aim4api.compability.aimv3.Dimension;
import edu.stanford.hakan.aim4api.compability.aimv3.ImageAnnotation;

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

        ImageAnnotation imageAnnotation = new ImageAnnotation(imageAnnotationCollection);

        Calculation calculation = getCalculationByFeatureNameList(imageAnnotationCollection, featureName);
        if (calculation != null) {
            for (int i = 0; i < featureValue.length; i++) {
                if (featureName[i] == null) {
                    continue;
                }  
                CalculationResult calculationResult = getCalculationResultByFeatureName(calculation, featureName[i]);
                if (calculationResult != null) {
                    calculationResult.getCalculationDataCollection().getCalculationDataList().get(0).setValue(featureValue[i]);
                } else {
                    addFeatureToCalculation(calculation, featureValue[i], featureName[i]);
                }
            }
        } else {
            calculation = new Calculation();
            calculation.setCagridId(0);
            calculation.setAlgorithmVersion(Double.toString(featureVersion));
            calculation.setAlgorithmType("99EPADA2"); //plugin
            calculation.setUid("0");
            if (calcCD!=null) {
    	        calculation.setDescription(calcCD.getDisplayName().getValue());
    	        calculation.setCodeValue(calcCD.getCode());
    	        calculation.setCodeMeaning(calcCD.getDisplayName().getValue());
    	        calculation.setCodingSchemeDesignator(calcCD.getCodeSystemName());
            }else {
	            calculation.setDescription("Feature Extraction");
	            calculation.setCodeValue("99EPADF0"); //feature default
	            calculation.setCodeMeaning("Feature Extraction");
	            calculation.setCodingSchemeDesignator("99EPAD");
            }

            for (int i = 0; i < featureValue.length; i++) {
                if (featureName[i] == null) {
                    continue;
                }
                addFeatureToCalculation(calculation, featureValue[i], featureName[i]);
            }
            imageAnnotation.addCalculation(calculation);
        }

        
        return imageAnnotation.toAimV4();

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
		
        ImageAnnotation imageAnnotation = new ImageAnnotation(imageAnnotationCollection);

       
        Calculation calculation = getCalculationByFeatureName(imageAnnotationCollection, feature.getDisplayName().getValue());
        
        if (calculation != null) {
        	calculation.getCalculationResultCollection().getCalculationResultList().get(0).getCalculationDataCollection().getCalculationDataList().get(0).setValue(featureValue);
        } else {
            imageAnnotation.addCalculation(createCalculationForFeature(imageAnnotationCollection, featureValue, feature, calcCD, label));
        }
            
        return imageAnnotation.toAimV4();
    }

    private static CalculationResult getCalculationResultByFeatureName(Calculation calculation, String featureName) {
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
    private static Calculation getCalculationByFeatureName(ImageAnnotationCollection imageAnnotationCollection, String featureName) {
        ImageAnnotation imageAnnotation = new ImageAnnotation(imageAnnotationCollection);
        for (Calculation calculation : imageAnnotation.getCalculationCollection().getCalculationList()) {
            if (calculation.getCodeMeaning().equals(featureName) || calculation.getDescription().equals(featureName)) {
                return calculation;
                       
            }
        }
        return null;
    }


    private static Calculation getCalculationByFeatureNameList(ImageAnnotationCollection imageAnnotationCollection, String[] featureName) {
        ImageAnnotation imageAnnotation = new ImageAnnotation(imageAnnotationCollection);
        for (Calculation calculation : imageAnnotation.getCalculationCollection().getCalculationList()) {
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

    private static void addFeatureToCalculation(Calculation calculation, double featureValue, String featureName) {

        // Create a CalculationResult instance
        CalculationResult calculationResult = new CalculationResult();
        calculationResult.setCagridId(0);
        calculationResult.setType(CalculationResultIdentifier.Scalar);
        calculationResult.setUnitOfMeasure("ratio");
        //ml double
        calculationResult.setDataType("99EPADD1");;
        calculationResult.setNumberOfDimensions(1);
        // Create a CalculationData instance
        CalculationData calculationData = new CalculationData();
        calculationData.setCagridId(0);
        calculationData.setValue(featureValue);
        calculationData.addCoordinate(0, 0, 0);
        // Create a Dimension instance
        Dimension dimension = new Dimension(0, 0, 1, featureName);
        // Add calculationData to calculationResult
        calculationResult.addCalculationData(calculationData);
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
    private static Calculation createCalculationForFeature(ImageAnnotationCollection iac, double featureValue, CD feature, CD parentCD, String label) throws AimException {
    	
    	Calculation calculation = new Calculation();
    	
    	calculation.setCagridId(0);
    	calculation.setAlgorithmVersion(parentCD.getCodeSystemVersion());
    	calculation.setAlgorithmType(parentCD.getCode()); 
    	calculation.setAlgorithmName(parentCD.getDisplayName().getValue());
    	calculation.setUid("0");
    	
    	
    	if (feature!=null) {
    		calculation.setDescription(feature.getDisplayName().getValue());
    		calculation.setCodeValue(feature.getCode());
    		calculation.setCodeMeaning(feature.getDisplayName().getValue());
    		calculation.setCodingSchemeDesignator(feature.getCodeSystemName());
    	}else {
    		calculation.setDescription("Feature Extraction");
    		calculation.setCodeValue("99EPADC0"); //double
    		calculation.setCodeMeaning("Feature Extraction");
    		calculation.setCodingSchemeDesignator("99EPAD");
    	}

    	
        // Create a CalculationResult instance
        CalculationResult calculationResult = new CalculationResult();
        calculationResult.setCagridId(0);
        calculationResult.setType(CalculationResultIdentifier.Scalar);
        calculationResult.setUnitOfMeasure("ratio");
        //ml double
        calculationResult.setDataType("99EPADD1");;
        calculationResult.setNumberOfDimensions(1);
        // Create a CalculationData instance
        CalculationData calculationData = new CalculationData();
        calculationData.setCagridId(0);
        calculationData.setValue(featureValue);
        calculationData.addCoordinate(0, 0, 0);
        // Create a Dimension instance
        Dimension dimension = new Dimension(0, 0, 1, label);
        // Add calculationData to calculationResult
        calculationResult.addCalculationData(calculationData);
        // Add dimension to calculationResult
        calculationResult.addDimension(dimension);
        // Add calculationResult to calculation
        calculation.addCalculationResult(calculationResult);
        
        
        return calculation;
        
    }
}
