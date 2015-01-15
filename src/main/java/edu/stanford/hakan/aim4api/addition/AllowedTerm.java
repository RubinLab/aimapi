/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.addition;

import edu.stanford.hakan.aim4api.base.CD;

/**
 *
 * @author Hakan
 */
public class AllowedTerm {

    private String codeValue;
    private String codeMeaning;
    private String codingSchemeDesignator;
    private String codingSchemeVersion;

    public AllowedTerm()
    {
        
    }

    public AllowedTerm(String codeValue, String codeMeaning, String codingSchemeDesignator, String codingSchemeVersion) {
        this.codeValue = codeValue;
        this.codeMeaning = codeMeaning;
        this.codingSchemeDesignator = codingSchemeDesignator;
        this.codingSchemeVersion = codingSchemeVersion;
    }
    
    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeMeaning() {
        return codeMeaning;
    }

    public void setCodeMeaning(String codeMeaning) {
        this.codeMeaning = codeMeaning;
    }

    public String getCodingSchemeDesignator() {
        return codingSchemeDesignator;
    }

    public void setCodingSchemeDesignator(String codingSchemeDesignator) {
        this.codingSchemeDesignator = codingSchemeDesignator;
    }

    public String getCodingSchemeVersion() {
        return codingSchemeVersion;
    }

    public void setCodingSchemeVersion(String codingSchemeVersion) {
        this.codingSchemeVersion = codingSchemeVersion;
    }

    @Override
    public boolean equals(Object object) {
        boolean cv = false;
        boolean cm = false;
        boolean csv = false;
        boolean csd = false;

        if (object != null && object instanceof AllowedTerm) {
            cv = this.codeValue.toLowerCase().trim().equals(((AllowedTerm) object).codeValue.toLowerCase().trim());
            cm = this.codeMeaning.toLowerCase().trim().equals(((AllowedTerm) object).codeMeaning.toLowerCase().trim());
            csv = this.codingSchemeVersion.toLowerCase().trim().equals(((AllowedTerm) object).codingSchemeVersion.toLowerCase().trim());
            csd = this.codingSchemeDesignator.toLowerCase().trim().equals(((AllowedTerm) object).codingSchemeDesignator.toLowerCase().trim());
        }
        return cv && cm && csv && csd;
    }
    
    public CD toCD()
    {
        return new CD(codeValue, codeMeaning, codingSchemeDesignator, codingSchemeVersion);
    }

}
