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
package edu.stanford.hakan.aim4api.utility;

import edu.stanford.hakan.aim4api.base.MarkupEntity;
import edu.stanford.hakan.aim4api.base.TwoDimensionPolyline;
import edu.stanford.hakan.aim4api.base.TwoDimensionSpline;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author localadmin
 */
public class Utility {

    public static String getNowAtGMT() {
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy'T'hh:mm:ssa'.Zone:'z");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(currentTime);
    }

    public static String correctToUrl(String Url) {
        String[] tempArray = Url.split("/");
        String res = "";
        for (int i = 0; i < tempArray.length; i++) {
            if (!"".equals(tempArray[i].trim()) && !"http:".equals(tempArray[i].trim().toLowerCase(new Locale("\\u0131")))) {
                res += tempArray[i] + "/";
            }
        }
        return "http://" + res;
    }

    public static boolean isSpline(MarkupEntity markupEntity) {
        if ("TwoDimensionSpline".equals(markupEntity.getXsiType()) || markupEntity.getUniqueIdentifier().getRoot().contains(Globals.getSplineFlag())) {
            return true;
        }
        return false;
    }
    
    public static TwoDimensionSpline toSpline(TwoDimensionPolyline twoDimensionPolyline) {
        return new TwoDimensionSpline(twoDimensionPolyline);
    }
}
