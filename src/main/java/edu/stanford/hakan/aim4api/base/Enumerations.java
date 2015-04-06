/*
 * Copyright (c) 2011, The Board of Trustees of the Leland Stanford Junior
 * University, creator Daniel L. Rubin.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package edu.stanford.hakan.aim4api.base;

/**
 *
 * @author localadmin
 */
public class Enumerations {

    public enum IdentifierScope {

        None, BUSN, OBJ, VER, VW
    };

    public enum IdentifierReliability {

        None, ISS, VRF, USE
    };

    public enum TelecommunicationAddressUse {

        None, H, HP, HV, WP, DIR, PUB, BAD, TMP, AS, EC, MC, PG
    };

    public enum set_TelecommunicationAddressUse {

        None, H, HP, HV, WP, DIR, PUB, BAD, TMP, AS, EC, MC, PG
    };

    public enum IntegrityCheckAlgorithm {

        None, SHA1, SHA256
    };

    public enum Compression {

        None, DF, GZ, ZL, Z
    };

    public enum set_CodingRationale {

        None, O, P, R, S
    };

    public enum CodingRationale {

        None, O, P, R, S
    };

    public enum AimVersion {

        None, AIMv1_0, AIMv2_0, AIMv3_0, AIM_3_0, AIMv3_0_1, AIMv3_0_2, AIMv4_0, TCGA
    };

    public enum CalculationResultIdentifier {

        None, Array, Binary, Histogram, Matrix, Scalar, Vector
    };

    public enum ComparisonOperator {

        None, Equal, NotEqual, LessThan, LessThanEqual, GreaterThan, GreaterThanEqual
    };

    public enum ScaleType {

        None, Nominal, Ordinal, Ratio
    };
}
