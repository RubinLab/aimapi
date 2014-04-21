/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.edu.stanford.hakan.aim4api.projects.epad;

import main.java.edu.stanford.hakan.aim4api.base.Algorithm;
import main.java.edu.stanford.hakan.aim4api.base.CD;
import main.java.edu.stanford.hakan.aim4api.base.CalculationData;
import main.java.edu.stanford.hakan.aim4api.base.CalculationEntity;
import main.java.edu.stanford.hakan.aim4api.base.Dimension;
import main.java.edu.stanford.hakan.aim4api.base.Enumerations;
import main.java.edu.stanford.hakan.aim4api.base.ExtendedCalculationResult;
import main.java.edu.stanford.hakan.aim4api.base.II;
import main.java.edu.stanford.hakan.aim4api.base.ST;

/**
 *
 * @author Hakan
 */
public class Calculation{
    
    private double length;
    

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public CalculationEntity toAimV4() {
        CalculationEntity res = new CalculationEntity();
                
        res.addTypeCode(new CD("codeValue","codeMeaning", "codingSchemeDesignator", "codingSchemeVersion"));
        res.setDescription(new ST("description"));
        
        ExtendedCalculationResult extendedCalculationResult = new ExtendedCalculationResult();
        extendedCalculationResult.setUnitOfMeasure(new ST("linear"));
        extendedCalculationResult.setDataType(new CD("codeValue","codeMeaning", "codingSchemeDesignator", "codingSchemeVersion"));
        extendedCalculationResult.setType(Enumerations.CalculationResultIdentifier.Scalar);
        extendedCalculationResult.addDimension(new Dimension(0, 0, "RECIST"));

        Algorithm algorithm = new Algorithm();
        algorithm.setUniqueIdentifier(new II("0"));
        algorithm.setName(new ST("name"));
        algorithm.setVersion(new ST("RECIST"));
        algorithm.addType(new CD("codeValue","codeMeaning", "codingSchemeDesignator", "codingSchemeVersion"));
        
        CalculationData calculationData = new CalculationData();
        calculationData.setValue(new ST(String.valueOf(this.length)));
        calculationData.addCoordinate(0, 0);
        
        extendedCalculationResult.addCalculationData(calculationData);        
        res.setAlgorithm(algorithm);
        res.addCalculationResult(extendedCalculationResult);
        return res;
    }
}
