/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 package edu.stanford.hakan.aim4api.compability.aimv3;

import edu.stanford.hakan.aim4api.compability.aimv3_old.*;
import edu.stanford.hakan.aim4api.base.CD;
import edu.stanford.hakan.aim4api.base.II;
import edu.stanford.hakan.aim4api.base.ST;

/**
 *
 * @author Hakan
 */
public class Converter {
    
    public static II toII(String str) {
        if (str != null) {
            return new II(str);
        }
        return new II();
    }

    public static II toII(int val) {
        if (val >= 0) {
            return new II(Integer.toString(val));
        }
        return new II();
    }

    public static ST toST(String str) {
        if (str != null) {
            return new ST(str);
        }
        return new ST();
    }

    public static ST toST(Double val) {
        if (val != Double.NaN) {
            return new ST(Double.toString(val));
        }
        return new ST();
    }

    public static CD toCD(String codeValue, String codeMeaning, String codingSchemeDesignator, String codingSchemeVersion) {
        if (codeValue == null) {
            codeValue = "";
        }
        if (codeMeaning == null) {
            codeMeaning = "";
        }
        if (codingSchemeDesignator == null) {
            codingSchemeDesignator = "";
        }
        if (codingSchemeVersion == null) {
            codingSchemeVersion = "";
        }
        CD res = new CD();
        res.setCode(codeValue);
        res.setCodeSystem(codeMeaning);
        res.setCodeSystemName(codingSchemeDesignator);
        res.setCodeSystemVersion(codingSchemeVersion);
        return res;
    }
    
    public static edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator toAimV4(AimUtility.ComparisonOperators comparisonOperatorsV3) {
        if (comparisonOperatorsV3 == AimUtility.ComparisonOperators.Equal) {
            return edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator.Equal;
        }
        if (comparisonOperatorsV3 == AimUtility.ComparisonOperators.GreaterThan) {
            return edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator.GreaterThan;
        }
        if (comparisonOperatorsV3 == AimUtility.ComparisonOperators.GreaterThanEqual) {
            return edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator.GreaterThanEqual;
        }
        if (comparisonOperatorsV3 == AimUtility.ComparisonOperators.LessThan) {
            return edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator.LessThan;
        }
        if (comparisonOperatorsV3 == AimUtility.ComparisonOperators.LessThanEqual) {
            return edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator.LessThanEqual;
        }
        if (comparisonOperatorsV3 == AimUtility.ComparisonOperators.NotEqual) {
            return edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator.NotEqual;
        }
        return edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator.None;
    }

    public static edu.stanford.hakan.aim4api.base.Enumerations.CalculationResultIdentifier toAimV4(AimUtility.CalculationResultIdentifier calculationResultIdentifierV3) {
        if (calculationResultIdentifierV3 == AimUtility.CalculationResultIdentifier.Array) {
            return edu.stanford.hakan.aim4api.base.Enumerations.CalculationResultIdentifier.Array;
        }
        if (calculationResultIdentifierV3 == AimUtility.CalculationResultIdentifier.Histogram) {
            return edu.stanford.hakan.aim4api.base.Enumerations.CalculationResultIdentifier.Histogram;
        }
        if (calculationResultIdentifierV3 == AimUtility.CalculationResultIdentifier.Matrix) {
            return edu.stanford.hakan.aim4api.base.Enumerations.CalculationResultIdentifier.Matrix;
        }
        if (calculationResultIdentifierV3 == AimUtility.CalculationResultIdentifier.Scalar) {
            return edu.stanford.hakan.aim4api.base.Enumerations.CalculationResultIdentifier.Scalar;
        }
        if (calculationResultIdentifierV3 == AimUtility.CalculationResultIdentifier.Vector) {
            return edu.stanford.hakan.aim4api.base.Enumerations.CalculationResultIdentifier.Vector;
        }

        return edu.stanford.hakan.aim4api.base.Enumerations.CalculationResultIdentifier.None;
    }
    
    public static AimUtility.ComparisonOperators toAimV3(edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator comparisonOperatorsV4) {
        if (comparisonOperatorsV4 == edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator.Equal) {
            return AimUtility.ComparisonOperators.Equal;
        }
        if (comparisonOperatorsV4 == edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator.GreaterThan) {
            return AimUtility.ComparisonOperators.GreaterThan;
        }
        if (comparisonOperatorsV4 == edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator.GreaterThanEqual) {
            return AimUtility.ComparisonOperators.GreaterThanEqual;
        }
        if (comparisonOperatorsV4 == edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator.LessThan) {
            return AimUtility.ComparisonOperators.LessThan;
        }
        if (comparisonOperatorsV4 == edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator.LessThanEqual) {
            return AimUtility.ComparisonOperators.LessThanEqual;
        }
        if (comparisonOperatorsV4 == edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator.NotEqual) {
            return AimUtility.ComparisonOperators.NotEqual;
        }
        return AimUtility.ComparisonOperators.None;
    }
    
    
    public static AimUtility.CalculationResultIdentifier toAimV3( edu.stanford.hakan.aim4api.base.Enumerations.CalculationResultIdentifier calculationResultIdentifierV4) {
        if (calculationResultIdentifierV4 == edu.stanford.hakan.aim4api.base.Enumerations.CalculationResultIdentifier.Array) {
            return AimUtility.CalculationResultIdentifier.Array;
        }
        if (calculationResultIdentifierV4 == edu.stanford.hakan.aim4api.base.Enumerations.CalculationResultIdentifier.Histogram) {
            return AimUtility.CalculationResultIdentifier.Histogram;
        }
        if (calculationResultIdentifierV4 == edu.stanford.hakan.aim4api.base.Enumerations.CalculationResultIdentifier.Matrix) {
            return AimUtility.CalculationResultIdentifier.Matrix;
        }
        if (calculationResultIdentifierV4 == edu.stanford.hakan.aim4api.base.Enumerations.CalculationResultIdentifier.Scalar) {
            return AimUtility.CalculationResultIdentifier.Scalar;
        }
        if (calculationResultIdentifierV4 == edu.stanford.hakan.aim4api.base.Enumerations.CalculationResultIdentifier.Vector) {
            return AimUtility.CalculationResultIdentifier.Vector;
        }

        return AimUtility.CalculationResultIdentifier.None;
    }
}
