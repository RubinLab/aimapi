/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.hakan.aim4api.usage;

import edu.stanford.hakan.aim4api.base.AimException;
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
    
    
         public static ImageAnnotationCollection addFeature(ImageAnnotationCollection imageAnnotationCollection, double[] featureValue, String[] featureString, double featureVersion) throws AimException {
        if (featureValue.length != featureString.length) {
            throw new AimException("AimException: lenght of featureValue and featureString must be equal");
        }
        
        ImageAnnotation imageAnnotation = new ImageAnnotation(imageAnnotationCollection);
        Calculation calculation = new Calculation();
        calculation.setCagridId(0);
        calculation.setAlgorithmVersion(Double.toString(featureVersion));
        calculation.setUid("0");
        calculation.setDescription("description");
        calculation.setCodeValue("codeValue");
        calculation.setCodeMeaning("codeMeaning");
        calculation.setCodingSchemeDesignator("codingSchemeDesignator");

        for (int i = 0; i < featureValue.length; i++) {
            if (featureString[i] == null)  
                continue;
            // Create a CalculationResult instance
            CalculationResult calculationResult = new CalculationResult();
            calculationResult.setCagridId(0);
            calculationResult.setType(CalculationResultIdentifier.Scalar);
            calculationResult.setUnitOfMeasure("ratio");
            calculationResult.setNumberOfDimensions(1);
            // Create a CalculationData instance
            CalculationData calculationData = new CalculationData();
            calculationData.setCagridId(0);
            calculationData.setValue(featureValue[i]);
            calculationData.addCoordinate(0, 0, 0);
            // Create a Dimension instance
            Dimension dimension = new Dimension(0, 0, 1, featureString[i]);
            // Add calculationData to calculationResult
            calculationResult.addCalculationData(calculationData);
            // Add dimension to calculationResult
            calculationResult.addDimension(dimension);
            // Add calculationResult to calculation
            calculation.addCalculationResult(calculationResult);
        }

        imageAnnotation.addCalculation(calculation);
        return imageAnnotation.toAimV4();
    }
}
