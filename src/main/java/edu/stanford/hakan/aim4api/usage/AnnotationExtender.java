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
    	        calculation.setDescription(calcCD.getCodeSystem());
    	        calculation.setCodeValue(calcCD.getCode());
    	        calculation.setCodeMeaning(calcCD.getCodeSystem());
    	        calculation.setCodingSchemeDesignator(calcCD.getCodeSystemName());
            }else {
	            calculation.setDescription("Feature Extraction");
	            calculation.setCodeValue("99EPADC0"); //double
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
}
