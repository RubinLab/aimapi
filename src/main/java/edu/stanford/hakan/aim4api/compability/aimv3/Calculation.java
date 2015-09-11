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
import edu.stanford.hakan.aim4api.base.Algorithm;
import edu.stanford.hakan.aim4api.base.AnnotationStatement;
import edu.stanford.hakan.aim4api.base.CD;
import edu.stanford.hakan.aim4api.base.II;
import edu.stanford.hakan.aim4api.plugin.Plugin;
import edu.stanford.hakan.aim4api.utility.GenerateId;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hakan BULU
 */
public class Calculation implements IAimXMLOperations {

    private Integer cagridId;
    private String uid;
    private String description;
    private String mathML;
    private String codeValue;
    private String codeMeaning;
    private String codingSchemeDesignator;
    private String codingSchemeVersion;
    private String algorithmName;
    private String algorithmVersion;
    private CalculationResultCollection calculationResultCollection = new CalculationResultCollection();
    private ReferencedCalculationCollection referencedCalculationCollection = new ReferencedCalculationCollection();
    private ReferencedGeometricShapeCollection referencedGeometricShapeCollection = new ReferencedGeometricShapeCollection();
    private String rdfID;
    private boolean codeValueCanBeNull;
    private Plugin plugin = null;

    public Calculation() {
        this.codeValueCanBeNull = false;
    }

    public Calculation(Integer cagridId, String uid, String description, String mathML, String codeValue, String codeMeaning, String codingSchemeDesignator, String codingSchemeVersion, String algorithmName, String algorithmVersion) {
        this.cagridId = cagridId;
        this.uid = uid;
        this.description = description;
        this.mathML = mathML;
        this.codeValue = codeValue;
        this.codeMeaning = codeMeaning;
        this.codingSchemeDesignator = codingSchemeDesignator;
        this.codingSchemeVersion = codingSchemeVersion;
        this.algorithmName = algorithmName;
        this.algorithmVersion = algorithmVersion;
        this.codeValueCanBeNull = false;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getAlgorithmVersion() {
        return algorithmVersion;
    }

    public void setAlgorithmVersion(String algorithmVersion) {
        this.algorithmVersion = algorithmVersion;
    }

    public Integer getCagridId() {
        return cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    public CalculationResultCollection getCalculationResultCollection() {
        return calculationResultCollection;
    }

    public void setCalculationResultCollection(CalculationResultCollection calculationResultCollection) {
        this.calculationResultCollection = calculationResultCollection;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMathML() {
        return mathML;
    }

    public void setMathML(String mathML) {
        this.mathML = mathML;
    }

    public ReferencedCalculationCollection getReferencedCalculationCollection() {
        return referencedCalculationCollection;
    }

    public void setReferencedCalculationCollection(ReferencedCalculationCollection referencedCalculationCollection) {
        this.referencedCalculationCollection = referencedCalculationCollection;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ReferencedGeometricShapeCollection getReferencedGeometricShapeCollection() {
        return referencedGeometricShapeCollection;
    }

    public void setReferencedGeometricShapeCollection(ReferencedGeometricShapeCollection referencedGeometricShapeCollection) {
        this.referencedGeometricShapeCollection = referencedGeometricShapeCollection;
    }

    public void addReferencedCalculation(ReferencedCalculation newReferencedCalculation) {
        this.referencedCalculationCollection.AddReferencedCalculation(newReferencedCalculation);
    }

    public void addCalculationResult(CalculationResult newCalculationResult) {
        this.calculationResultCollection.AddCalculationResult(newCalculationResult);
    }

    public void addReferencedGeometricShape(ReferencedGeometricShape newReferencedGeometricShape) {
        this.referencedGeometricShapeCollection.AddReferencedGeometricShape(newReferencedGeometricShape);
    }

    public String getRdfID() {
        return rdfID;
    }

    public void setRdfID(String rdfID) {
        this.rdfID = rdfID;
    }

    public boolean isCodeValueCanBeNull() {
        return codeValueCanBeNull;
    }

    public void setCodeValueCanBeNull(boolean codeValueCanBeNull) {
        this.codeValueCanBeNull = codeValueCanBeNull;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }
    

//    @Override
//    public Node getXMLNode(Document doc) throws AimException {
//        this.Control();
//
//        Element calculation = doc.createElement("Calculation");
//        calculation.setAttribute("cagridId", this.cagridId.toString());
//        calculation.setAttribute("uid", this.uid);
//        calculation.setAttribute("description", this.description);
//        if (this.mathML != null) {
//            calculation.setAttribute("mathML", this.mathML);
//        }
//        if (this.codeValue != null) {
//            calculation.setAttribute("codeValue", this.codeValue);
//        }
//        calculation.setAttribute("codeMeaning", this.codeMeaning);
//        calculation.setAttribute("codingSchemeDesignator", this.codingSchemeDesignator);
//        if (this.codingSchemeVersion != null) {
//            calculation.setAttribute("codingSchemeVersion", this.codingSchemeVersion);
//        }
//        if (this.algorithmName != null) {
//            calculation.setAttribute("algorithmName", this.algorithmName);
//        }
//        if (this.algorithmVersion != null) {
//            calculation.setAttribute("algorithmVersion", this.algorithmVersion);
//        }
//        if (this.referencedCalculationCollection.getReferencedCalculationList().size() > 0) {
//            calculation.appendChild(this.referencedCalculationCollection.getXMLNode(doc));
//        }
//        if (this.calculationResultCollection.getCalculationResultList().size() > 0) {
//            calculation.appendChild(this.calculationResultCollection.getXMLNode(doc));
//        }
//        if (this.referencedGeometricShapeCollection.getReferencedGeometricShapeList().size() > 0) {
//            calculation.appendChild(this.referencedGeometricShapeCollection.getXMLNode(doc));
//        }
//        return calculation;
//    }
    @Override
    public void setXMLNode(Node node) {

        NodeList listChils = node.getChildNodes();
        for (int i = 0; i < listChils.getLength(); i++) {
            if ("referencedCalculationCollection".equals(listChils.item(i).getNodeName())) {
                this.referencedCalculationCollection.setXMLNode(listChils.item(i));
            } else if ("calculationResultCollection".equals(listChils.item(i).getNodeName())) {
                this.calculationResultCollection.setXMLNode(listChils.item(i));
            } else if ("referencedGeometricShapeCollection".equals(listChils.item(i).getNodeName())) {
                this.referencedGeometricShapeCollection.setXMLNode(listChils.item(i));
            }
        }

        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.uid = map.getNamedItem("uid").getNodeValue();
        this.description = map.getNamedItem("description").getNodeValue();
        if (map.getNamedItem("mathML") != null) {
            this.mathML = map.getNamedItem("mathML").getNodeValue();
        }
        if (map.getNamedItem("codeValue") != null) {
            this.codeValue = map.getNamedItem("codeValue").getNodeValue();
        }
        this.codeMeaning = map.getNamedItem("codeMeaning").getNodeValue();
        this.codingSchemeDesignator = map.getNamedItem("codingSchemeDesignator").getNodeValue();
        if (map.getNamedItem("codingSchemeVersion") != null) {
            this.codingSchemeVersion = map.getNamedItem("codingSchemeVersion").getNodeValue();
        }
        if (map.getNamedItem("algorithmName") != null) {
            this.algorithmName = map.getNamedItem("algorithmName").getNodeValue();
        }
        if (map.getNamedItem("algorithmVersion") != null) {
            this.algorithmVersion = map.getNamedItem("algorithmVersion").getNodeValue();
        }
    }

    private void Control() throws AimException {

        if (getCagridId() == null) {
            throw new AimException("AimException: Calculation's cagridId can not be null");
        }
        if (getUid() == null) {
            throw new AimException("AimException: Calculation's uid can not be null");
        }
        if (getDescription() == null) {
            throw new AimException("AimException: Calculation's description can not be null");
        }
        if (!this.codeValueCanBeNull && getCodeValue() == null) {
            throw new AimException("AimException: Calculation's codeValue can not be null");
        }
        if (getCodeMeaning() == null) {
            throw new AimException("AimException: Calculation's codeMeaning can not be null");
        }
        if (getCodingSchemeDesignator() == null) {
            throw new AimException("AimException: Calculation's codingSchemeDesignator can not be null");
        }
    }

    public boolean isEqualTo(Object other) {
        Calculation oth = (Calculation) other;
        if (this.cagridId != oth.cagridId) {
            return false;
        }
        if (this.uid == null ? oth.uid != null : !this.uid.equals(oth.uid)) {
            return false;
        }
        if (this.description == null ? oth.description != null : !this.description.equals(oth.description)) {
            return false;
        }
        if (this.mathML == null ? oth.mathML != null : !this.mathML.equals(oth.mathML)) {
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
        if (this.algorithmName == null ? oth.algorithmName != null : !this.algorithmName.equals(oth.algorithmName)) {
            return false;
        }
        if (this.algorithmVersion == null ? oth.algorithmVersion != null : !this.algorithmVersion.equals(oth.algorithmVersion)) {
            return false;
        }
        if (!this.referencedCalculationCollection.isEqualTo(oth.referencedCalculationCollection)) {
            return false;
        }
        if (!this.calculationResultCollection.isEqualTo(oth.calculationResultCollection)) {
            return false;
        }
        if (!this.referencedGeometricShapeCollection.isEqualTo(oth.referencedGeometricShapeCollection)) {
            return false;
        }
        return true;
    }

    public edu.stanford.hakan.aim4api.base.CalculationEntity toAimV4(edu.stanford.hakan.aim4api.base.ImageAnnotation imageAnnotation) {
        edu.stanford.hakan.aim4api.base.CalculationEntity res = new edu.stanford.hakan.aim4api.base.CalculationEntity();
        res.setUniqueIdentifier();
        Algorithm algorithm = new Algorithm();
        algorithm.setName(Converter.toST(this.getAlgorithmName()));
        algorithm.setVersion(Converter.toST(this.getAlgorithmVersion()));
        algorithm.addType(new CD("", "", "", ""));
        res.setAlgorithm(algorithm);
        res.setCalculationResultCollection(this.getCalculationResultCollection().toAimV4());//
        res.setDescription(Converter.toST(this.getDescription()));//
        res.setMathML(Converter.toST(this.getMathML()));//
        CD typeCode = new CD();
        typeCode.setCode(this.getCodeValue());
        typeCode.setCodeSystem(this.getCodeMeaning());
        typeCode.setCodeSystemName(this.getCodingSchemeDesignator());
        typeCode.setCodeSystemVersion(this.getCodingSchemeVersion());//
        res.addTypeCode(typeCode);

        if (this.getReferencedCalculationCollection().getReferencedCalculationList().size() > 0) {
            for (edu.stanford.hakan.aim4api.compability.aimv3.ReferencedCalculation itemV3 : this.getReferencedCalculationCollection().getReferencedCalculationList()) {
                edu.stanford.hakan.aim4api.base.CalculationEntityReferencesCalculationEntityStatement annotationStatement = itemV3.toAimV4(this);
                imageAnnotation.addImageAnnotationStatement(annotationStatement);
            }
        }
        if (this.getReferencedGeometricShapeCollection().getReferencedGeometricShapeList().size() > 0) {
            for (edu.stanford.hakan.aim4api.compability.aimv3.ReferencedGeometricShape itemV3 : this.getReferencedGeometricShapeCollection().getReferencedGeometricShapeList()) {
                itemV3.toAimV4(imageAnnotation, res.getUniqueIdentifier());
            }
        }
        if (this.getPlugin() != null) {
            if (res.getUniqueIdentifier() == null) {
                res.setUniqueIdentifier(new II(GenerateId.getUUID()));
            }
            this.getPlugin().setCalculationEntityID(res.getUniqueIdentifier().getRoot());
        }

        return res;
    }

    public Calculation(edu.stanford.hakan.aim4api.base.CalculationEntity v4, edu.stanford.hakan.aim4api.base.ImageAnnotation ia) {
        this.setCagridId(0);
        this.setUid(v4.getUniqueIdentifier().getRoot());
        if (v4.getAlgorithm() != null) {
            this.setAlgorithmName(v4.getAlgorithm().getName().getValue());
            this.setAlgorithmVersion(v4.getAlgorithm().getVersion().getValue());
        }
        if (v4.getCalculationResultCollection().getExtendedCalculationResultList().size() > 0) {
            this.setCalculationResultCollection(new CalculationResultCollection(v4.getCalculationResultCollection()));
        }
        if (v4.getDescription() != null) {
            this.setDescription(v4.getDescription().getValue());
        }
        if (v4.getMathML() != null) {
            this.setMathML(v4.getMathML().getValue());
        }
        if (v4.getListTypeCode().size() > 0) {
            CD typeCode = v4.getListTypeCode().get(0);
            if (typeCode.getCode() != null) {
                this.setCodeValue(typeCode.getCode());
            }
            if (typeCode.getCodeSystem() != null) {
                this.setCodeMeaning(typeCode.getCodeSystem());
            }
            if (typeCode.getCodeSystemName() != null) {
                this.setCodingSchemeDesignator(typeCode.getCodeSystemName());
            }
            if (typeCode.getCodeSystemVersion() != null) {
                this.setCodingSchemeVersion(typeCode.getCodeSystemVersion());
            }
        }

        List<AnnotationStatement> listAnnotationStatement = ia.getImageAnnotationStatementCollection().getImageAnnotationStatementList();
        String annotationStatementID = "";
        for (int i = 0; i < listAnnotationStatement.size(); i++) {
            AnnotationStatement annotationStatement = listAnnotationStatement.get(i);
            if ("ImagingPhysicalEntityHasCalculationEntityStatement".equals(annotationStatement.getXsiType())) {
                if (v4.getUniqueIdentifier().getRoot().equals(annotationStatement.getSubjectUniqueIdentifier().getRoot())) {
                    annotationStatementID = annotationStatement.getObjectUniqueIdentifier().getRoot();
                }
            }
        }
        String referencedShapeIdentifier = "";
        for (int i = 0; i < listAnnotationStatement.size(); i++) {
            AnnotationStatement annotationStatement = listAnnotationStatement.get(i);
            if ("ImagingPhysicalEntityHasTwoDimensionGeometricShapeEntityStatement".equals(annotationStatement.getXsiType())) {
                if (annotationStatementID.equals(annotationStatement.getObjectUniqueIdentifier().getRoot())) {
                    referencedShapeIdentifier = annotationStatement.getSubjectUniqueIdentifier().getRoot();
                }
            }
        }
        if (!"".equals(referencedShapeIdentifier)) {
            this.addReferencedGeometricShape(new ReferencedGeometricShape(0, Integer.parseInt(referencedShapeIdentifier)));
        }
    }

    public Calculation getClone() {
        Calculation res = new Calculation();
        if (this.cagridId != null) {
            res.cagridId = this.cagridId;
        }
        if (this.uid != null) {
            res.uid = this.uid;
        }
        if (this.description != null) {
            res.description = this.description;
        }
        if (this.mathML != null) {
            res.mathML = this.mathML;
        }
        if (this.codeValue != null) {
            res.codeValue = this.codeValue;
        }
        if (this.codeMeaning != null) {
            res.codeMeaning = this.codeMeaning;
        }
        if (this.codingSchemeDesignator != null) {
            res.codingSchemeDesignator = this.codingSchemeDesignator;
        }
        if (this.codingSchemeVersion != null) {
            res.codingSchemeVersion = this.codingSchemeVersion;
        }
        if (this.algorithmName != null) {
            res.algorithmName = this.algorithmName;
        }
        if (this.algorithmVersion != null) {
            res.algorithmVersion = this.algorithmVersion;
        }
        if (this.calculationResultCollection != null) {
            res.calculationResultCollection = this.calculationResultCollection.getClone();
        }
        if (this.referencedCalculationCollection != null) {
            res.referencedCalculationCollection = this.referencedCalculationCollection.getClone();
        }
        if (this.referencedGeometricShapeCollection != null) {
            res.referencedGeometricShapeCollection = this.referencedGeometricShapeCollection.getClone();
        }
        if (this.rdfID != null) {
            res.rdfID = this.rdfID;
        }

        res.codeValueCanBeNull = this.codeValueCanBeNull;

        return res;
    }
}
