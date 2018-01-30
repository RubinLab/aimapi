package edu.stanford.hakan.aim4api.project.epad;

import edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator;

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


/**
 * Utils for aimapi.
 * 
 * @author Debra Willrett
 * 
 */
public class Utils {

	public static ComparisonOperator getComparisonOperator(
			String operator) {
		ComparisonOperator result =ComparisonOperator.None;
		if (operator.equalsIgnoreCase("Equal")) {
			result = ComparisonOperator.Equal;
		}
		if (operator.equalsIgnoreCase("GreaterThan")) {
			result = ComparisonOperator.GreaterThan;
		}
		if (operator.equalsIgnoreCase("LessThan")) {
			result = ComparisonOperator.LessThan;
		}
		if (operator.equalsIgnoreCase("GreaterThanEqual")) {
			result = ComparisonOperator.GreaterThanEqual;
		}
		if (operator.equalsIgnoreCase("LessThanEqual")) {
			result = ComparisonOperator.LessThanEqual;
		}
		if (operator.equalsIgnoreCase("NotEqual")) {
			result = ComparisonOperator.NotEqual;
		}
		return result;
	}
}
