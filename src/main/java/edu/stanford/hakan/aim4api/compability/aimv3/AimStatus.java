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
package edu.stanford.hakan.aim4api.compability.aimv3;

import edu.stanford.hakan.aim4api.base.AimException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan BULU
 */
@SuppressWarnings("serial")
public class AimStatus implements IAimXMLOperations {

    private Integer cagridId;
    private Double annotationVersion;
    private String codeValue;
    private String codeMeaning;
    private String codingSchemeDesignator;
    private String codingSchemeVersion;
    private String authorizedBy;

    public AimStatus() {
    }

    public AimStatus(Integer cagridId, Double annotationVersion, String codeValue, String codeMeaning, String codingSchemeDesignator, String codingSchemeVersion, String authorizedBy) {
        this.cagridId = cagridId;
        this.annotationVersion = annotationVersion;
        this.codeValue = codeValue;
        this.codeMeaning = codeMeaning;
        this.codingSchemeDesignator = codingSchemeDesignator;
        this.codingSchemeVersion = codingSchemeVersion;
        this.authorizedBy = authorizedBy;
    }

    public Double getAnnotationVersion() {
        return annotationVersion;
    }

    public void setAnnotationVersion(Double annotationVersion) {
        this.annotationVersion = annotationVersion;
    }

    public String getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(String authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public Integer getCagridId() {
        return cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    public String getCodeMeaning() {
        return codeMeaning;
    }

    public void setCodeMeaning(String codeMeaning) {
        this.codeMeaning = codeMeaning;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
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
    public Node getXMLNode(Document doc) throws AimException {

        this.Control();

        Element aimStatus = doc.createElement("AimStatus");
        aimStatus.setAttribute("cagridId", this.cagridId.toString());
        aimStatus.setAttribute("annotationVersion", this.annotationVersion.toString());
        aimStatus.setAttribute("codeValue", this.codeValue);
        aimStatus.setAttribute("codeMeaning", this.codeMeaning);
        aimStatus.setAttribute("codingSchemeDesignator", this.codingSchemeDesignator);
        if (this.codingSchemeVersion != null) {
            aimStatus.setAttribute("codingSchemeVersion", this.codingSchemeVersion);
        }
        if (this.authorizedBy != null) {
            aimStatus.setAttribute("authorizedBy", this.authorizedBy);
        }
        return aimStatus;
    }

    @Override
    public void setXMLNode(Node node) {

        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.annotationVersion = Double.parseDouble(map.getNamedItem("annotationVersion").getNodeValue());
        this.codeValue = map.getNamedItem("codeValue").getNodeValue();
        this.codeMeaning = map.getNamedItem("codeMeaning").getNodeValue();
        this.codingSchemeDesignator = map.getNamedItem("codingSchemeDesignator").getNodeValue();
        if (map.getNamedItem("codingSchemeVersion") != null) {
            this.codingSchemeVersion = map.getNamedItem("codingSchemeVersion").getNodeValue();
        }
        if (map.getNamedItem("authorizedBy") != null) {
            this.authorizedBy = map.getNamedItem("authorizedBy").getNodeValue();
        }
    }    

    private void Control() throws AimException {
        if (getCagridId() == null) {
            throw new AimException("AimException: AimStatus's cagridId can not be null");
        }
        if (getAnnotationVersion() == null) {
            throw new AimException("AimException: AimStatus's annotationVersion can not be null");
        }
        if (getCodeValue() == null) {
            throw new AimException("AimException: AimStatus's codeValue can not be null");
        }
        if (getCodeMeaning() == null) {
            throw new AimException("AimException: AimStatus's codeMeaning can not be null");
        }
        if (getCodingSchemeDesignator() == null) {
            throw new AimException("AimException: AimStatus's codingSchemeDesignator can not be null");
        }
    }

    public boolean isEqualTo(Object other) {
        AimStatus oth = (AimStatus) other;
        if (this.cagridId != oth.cagridId) {
            return false;
        }
        if (this.annotationVersion == null ? oth.annotationVersion != null : !this.annotationVersion.equals(oth.annotationVersion)) {
            return false;
        }
        if (this.codeValue == null ? oth.codeValue != null : !this.codeValue.equals(oth.codeValue)) {
            return false;
        }
        if (this.codeMeaning == null ? oth.codeMeaning != null : !this.codeMeaning.equals(oth.codeMeaning)) {
            return false;
        }
        if (this.codingSchemeDesignator == null ? oth.codingSchemeDesignator != null : !this.codingSchemeDesignator.equals(oth.codingSchemeDesignator)) {
            return false;
        }
        if (this.codingSchemeVersion == null ? oth.codingSchemeVersion != null : !this.codingSchemeVersion.equals(oth.codingSchemeVersion)) {
            return false;
        }
        if (this.authorizedBy == null ? oth.authorizedBy != null : !this.authorizedBy.equals(oth.authorizedBy)) {
            return false;
        }
        return true;
    }
}
