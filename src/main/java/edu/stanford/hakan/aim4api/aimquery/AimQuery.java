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
package edu.stanford.hakan.aim4api.aimquery;

import edu.stanford.hakan.aim4api.base.AimException;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class AimQuery {

    public static String convertToXQuery(String aimQuery, String nameSpace) throws AimException {
        QueryParser myParser = new QueryParser(aimQuery);
        String collectionName = myParser.getAimCollection();
        String whereClause = myParser.getWhereClause();
        List<QueryExpression> listExp = myParser.getListExpressions();
        for (int i = 0; i < listExp.size(); i++) {
            QueryExpression exp = listExp.get(i);
            String expression = exp.getExpression();
            List<String> listXQuery = exp.getListXQuery();
            for (int j = 0; j < listXQuery.size(); j++) {
                whereClause = whereClause.replace(expression, listXQuery.get(j));
            }
        }

        if (!"/".equals(collectionName.substring(0, 1))) {
            collectionName = "/" + collectionName.trim();
        }
        String res = "declare default element namespace '" + nameSpace + "'; for $x in collection('" + collectionName + "')/ImageAnnotationCollection where " + whereClause + " order by $x/uniqueIdentifier/root return $x";
        //String res = "declare default element namespace '" + nameSpace + "'; for $x in collection('" + collectionName + "')/ImageAnnotationCollection where " + whereClause + " return $x";
        return res.replaceAll(" (?i)and ", " and ").replaceAll(" (?i)or ", " or ");
    }
}
