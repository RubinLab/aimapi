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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Hakan
 */
public class QueryParser {

    private String query = "";
    private String aimCollection = "";
    private String whereClause = "";
    private List<QueryExpression> listExpressions;

    public QueryParser(String queryString) throws AimException {
        this.listExpressions = new ArrayList<>();
        this.query = queryString;
        parseTheQuery();
        this.fillListExpression();
    }

    private void parseTheQuery() throws AimException {
        int indexOfWhere = this.query.toLowerCase(new Locale("\\u0131")).indexOf("where");
        if (indexOfWhere < 0) {
            throw new AimException("AimException: AimQL Syntax Error.");
        }
        String wherePart = this.query.substring(indexOfWhere + 5);
        String selectPart = this.query.substring(0, indexOfWhere);
        int indexOfFrom = selectPart.toLowerCase(new Locale("\\u0131")).indexOf("from");
        if (indexOfFrom < 0) {
            throw new AimException("AimException: AimQL Syntax Error.");
        }
        String collection = selectPart.substring(indexOfFrom + 4);
        this.aimCollection = collection;
        this.whereClause = wherePart;
    }

    private void fillListExpression() throws AimException {
        Hashtable<String, String> hasSingleQuoteValues = new Hashtable<>();
        String regex = "'.*?'";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.whereClause);
        int counter = 1;
        String updatedWhereClause = this.whereClause;
        while (matcher.find()) {
            String val = matcher.group(0);
            String key = "'~%value" + counter + "%~'";
            hasSingleQuoteValues.put(key, val);
            updatedWhereClause = updatedWhereClause.replace(val, key);
            counter++;
        }
        String spliter = " ~%con%~ ";
        updatedWhereClause = updatedWhereClause.replace("(", "").replace(")", "").trim().replaceAll(" (?i)and ", spliter).replaceAll(" (?i)or ", spliter);
        List<String> listUpdatedExpressions = new ArrayList<>();
        listUpdatedExpressions.addAll(Arrays.asList(updatedWhereClause.split(spliter)));

        for (int i = 0; i < listUpdatedExpressions.size(); i++) {
            //regex = "([a-zA -Z][a-zA-Z0-9]*)\\.([a-zA-Z][a-zA-Z0-9]*)\\s*(=|(?:<>)|(?:>=)|(?:<=)|(?:<)|(?:>)|(?:(?i)like))\\s*('?[a-zA-Z0-9\\s\\)\\(\\.%~]*'?)";
            //regex = "(^[A-Za-z ].*)\\s*(=|(?:<>)|(?:>=)|(?:<=)|(?:<)|(?:>)|(?:(?i)like))\\s*('?[a-zA-Z0-9\\s\\)\\(\\.%~]*'?)";
            regex = "([a-zA-Z][a-zA-Z0-9.]*)\\s*(=|(?:<>)|(?:>=)|(?:<=)|(?:<)|(?:>)|(?:(?i)like))\\s*('?[a-zA-Z0-9\\s\\)\\(\\.%~]*'?)";

            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(listUpdatedExpressions.get(i));
            boolean find = false;
            while (matcher.find()) {
                String expression = matcher.group(0);
                String value = matcher.group(3);
                if (value.indexOf("'~%value") >= 0) {
                    String originalValue = hasSingleQuoteValues.get(value);
                    String updatedValue = value;
                    value = originalValue;
                    expression = expression.replace(updatedValue, originalValue);
                }
                String leftSide = matcher.group(1);
                String function = matcher.group(2);
                QueryExpression exp = new QueryExpression(expression, leftSide, function, value);
                this.listExpressions.add(exp);
                find = true;
            }
            if (!find) {
                throw new AimException("AimException: AimQL Syntax Error.");
            }
        }
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getAimCollection() {
        return aimCollection;
    }

    public void setAimCollection(String aimCollection) {
        this.aimCollection = aimCollection;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public List<QueryExpression> getListExpressions() {
        return listExpressions;
    }

    public void setListExpressions(List<QueryExpression> listExpressions) {
        this.listExpressions = listExpressions;
    }
}
