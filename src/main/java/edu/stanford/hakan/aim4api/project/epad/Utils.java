package edu.stanford.hakan.aim4api.project.epad;

//Copyright (c) 2013 The Board of Trustees of the Leland Stanford Junior University
//All rights reserved.
//
//Redistribution and use in source and binary forms, with or without modification, are permitted provided that 
//the following conditions are met:
//
//Redistributions of source code must retain the above copyright notice, this list of conditions and the following 
//disclaimer.
//
//Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the 
//following disclaimer in the documentation and/or other materials provided with the distribution.
//
//Neither the name of The Board of Trustees of the Leland Stanford Junior University nor the names of its 
//contributors (Daniel Rubin, et al) may be used to endorse or promote products derived from this software without 
//specific prior written permission.
//
//THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
//INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
//DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
//SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
//SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
//WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE 
//USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

import edu.stanford.hakan.aim4api.compability.aimv3.AimUtility;

/**
 * Utils for aimapi.
 * 
 * @author Debra Willrett
 * 
 */
public class Utils {

	public static AimUtility.ComparisonOperators getComparisonOperator(
			String operator) {
		AimUtility.ComparisonOperators result = AimUtility.ComparisonOperators.None;
		if (operator.equalsIgnoreCase("Equal")) {
			result = AimUtility.ComparisonOperators.Equal;
		}
		if (operator.equalsIgnoreCase("GreaterThan")) {
			result = AimUtility.ComparisonOperators.GreaterThan;
		}
		if (operator.equalsIgnoreCase("LessThan")) {
			result = AimUtility.ComparisonOperators.LessThan;
		}
		if (operator.equalsIgnoreCase("GreaterThanEqual")) {
			result = AimUtility.ComparisonOperators.GreaterThanEqual;
		}
		if (operator.equalsIgnoreCase("LessThanEqual")) {
			result = AimUtility.ComparisonOperators.LessThanEqual;
		}
		if (operator.equalsIgnoreCase("NotEqual")) {
			result = AimUtility.ComparisonOperators.NotEqual;
		}
		return result;
	}
}