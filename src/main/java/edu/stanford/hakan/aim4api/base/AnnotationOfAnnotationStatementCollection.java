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
package main.java.edu.stanford.hakan.aim4api.base;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class AnnotationOfAnnotationStatementCollection implements IAimXMLOperations {

    private final List<AnnotationStatement> listAnnotationOfAnnotationStatement = new ArrayList<AnnotationStatement>();

    public void addAnnotationOfAnnotationStatement(AnnotationStatement newAnnotationOfAnnotationStatement) {
        this.listAnnotationOfAnnotationStatement.add(newAnnotationOfAnnotationStatement);
    }

    public List<AnnotationStatement> getAnnotationOfAnnotationStatementList() {
        return this.listAnnotationOfAnnotationStatement;
    }

    public int size() {
        return this.listAnnotationOfAnnotationStatement.size();
    }

    public AnnotationStatement get(int index) {
        if (index <= this.listAnnotationOfAnnotationStatement.size() - 1) {
            return this.listAnnotationOfAnnotationStatement.get(index);
        }
        return null;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        Element annotationOfAnnotationStatementCollection = doc.createElement("annotationOfAnnotationStatementCollection");
        for (int i = 0; i < this.listAnnotationOfAnnotationStatement.size(); i++) {
            this.listAnnotationOfAnnotationStatement.get(i).setTagName("AnnotationOfAnnotationStatement");
            annotationOfAnnotationStatementCollection.appendChild(this.listAnnotationOfAnnotationStatement.get(i).getXMLNode(
                    doc));
        }
        return annotationOfAnnotationStatementCollection;
    }

    @Override
    public void setXMLNode(Node node) {
        this.listAnnotationOfAnnotationStatement.clear();
        NodeList tempList = node.getChildNodes();
        for (int j = 0; j < tempList.getLength(); j++) {
            Node currentNode = tempList.item(j);
            if ("AnnotationOfAnnotationStatement".equals(currentNode.getNodeName())) {
                NamedNodeMap map = currentNode.getAttributes();
                if (map.getNamedItem("xsi:type") != null) {
                    String xsiType = map.getNamedItem("xsi:type").getNodeValue();
                    if ("ImageAnnotationIsComparedWithAnnotationOfAnnotationStatement".equals(xsiType)) {
                        ImageAnnotationIsComparedWithAnnotationOfAnnotationStatement obj = new ImageAnnotationIsComparedWithAnnotationOfAnnotationStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImageAnnotationStatement".equals(xsiType)) {
                        ImageAnnotationStatement obj = new ImageAnnotationStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ThreeDimensionGeometricShapeEntityIsComprisedOfThreeDimensionGeometricShapeEntityStatement"
                            .equals(xsiType)) {
                        ThreeDimensionGeometricShapeEntityIsComprisedOfThreeDimensionGeometricShapeEntityStatement obj = new ThreeDimensionGeometricShapeEntityIsComprisedOfThreeDimensionGeometricShapeEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("GeneralLesionObservationEntityHasImagingPhysicalEntityStatement".equals(xsiType)) {
                        GeneralLesionObservationEntityHasImagingPhysicalEntityStatement obj = new GeneralLesionObservationEntityHasImagingPhysicalEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImageAnnotationHasTwoDimensionGeometricShapeEntityStatement".equals(xsiType)) {
                        ImageAnnotationHasTwoDimensionGeometricShapeEntityStatement obj = new ImageAnnotationHasTwoDimensionGeometricShapeEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImagingPhysicalEntityHasTextAnnotationEntityStatement".equals(xsiType)) {
                        ImagingPhysicalEntityHasTextAnnotationEntityStatement obj = new ImagingPhysicalEntityHasTextAnnotationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImagingObservationEntityIsIdentifiedByTwoDimensionGeometricShapeEntityStatement".equals(xsiType)) {
                        ImagingObservationEntityIsIdentifiedByTwoDimensionGeometricShapeEntityStatement obj = new ImagingObservationEntityIsIdentifiedByTwoDimensionGeometricShapeEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ThreeDimensionGeometricShapeEntityExcludesThreeDimensionGeometricShapeEntityStatement".equals(xsiType)) {
                        ThreeDimensionGeometricShapeEntityExcludesThreeDimensionGeometricShapeEntityStatement obj = new ThreeDimensionGeometricShapeEntityExcludesThreeDimensionGeometricShapeEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("UriImageReferenceEntityHasImagingPhysicalEntityStatement".equals(xsiType)) {
                        UriImageReferenceEntityHasImagingPhysicalEntityStatement obj = new UriImageReferenceEntityHasImagingPhysicalEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("DicomImageReferenceEntityHasImagingObservationEntityStatement".equals(xsiType)) {
                        DicomImageReferenceEntityHasImagingObservationEntityStatement obj = new DicomImageReferenceEntityHasImagingObservationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImagingObservationEntityIsIdentifiedByTextAnnotationEntityStatement".equals(xsiType)) {
                        ImagingObservationEntityIsIdentifiedByTextAnnotationEntityStatement obj = new ImagingObservationEntityIsIdentifiedByTextAnnotationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("TwoDimensionGeometricShapeEntityIsComprisedOfTwoDimensionGeometricShapeEntityStatement".equals(xsiType)) {
                        TwoDimensionGeometricShapeEntityIsComprisedOfTwoDimensionGeometricShapeEntityStatement obj = new TwoDimensionGeometricShapeEntityIsComprisedOfTwoDimensionGeometricShapeEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImageAnnotationHasInferenceEntityStatement".equals(xsiType)) {
                        ImageAnnotationHasInferenceEntityStatement obj = new ImageAnnotationHasInferenceEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImageAnnotationHasGeneralLesionObservationEntityStatement".equals(xsiType)) {
                        ImageAnnotationHasGeneralLesionObservationEntityStatement obj = new ImageAnnotationHasGeneralLesionObservationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("UriImageReferenceEntityHasCalculationEntityStatement".equals(xsiType)) {
                        UriImageReferenceEntityHasCalculationEntityStatement obj = new UriImageReferenceEntityHasCalculationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("DicomImageReferenceEntityHasCalculationEntityStatement".equals(xsiType)) {
                        DicomImageReferenceEntityHasCalculationEntityStatement obj = new DicomImageReferenceEntityHasCalculationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImagingObservationEntityIsIdentifiedByThreeDimensionGeometricShapeEntityStatement".equals(xsiType)) {
                        ImagingObservationEntityIsIdentifiedByThreeDimensionGeometricShapeEntityStatement obj = new ImagingObservationEntityIsIdentifiedByThreeDimensionGeometricShapeEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImageAnnotationHasDicomImageReferenceEntityStatement".equals(xsiType)) {
                        ImageAnnotationHasDicomImageReferenceEntityStatement obj = new ImageAnnotationHasDicomImageReferenceEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("DicomSegmentationEntityHasImagingObservationEntityStatement".equals(xsiType)) {
                        DicomSegmentationEntityHasImagingObservationEntityStatement obj = new DicomSegmentationEntityHasImagingObservationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("TimePointLesionObservationEntityHasImagingPhysicalEntityStatement".equals(xsiType)) {
                        TimePointLesionObservationEntityHasImagingPhysicalEntityStatement obj = new TimePointLesionObservationEntityHasImagingPhysicalEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImageAnnotationHasImagingPhysicalEntityStatement".equals(xsiType)) {
                        ImageAnnotationHasImagingPhysicalEntityStatement obj = new ImageAnnotationHasImagingPhysicalEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("DicomImageReferenceEntityHasImagingPhysicalEntityStatement".equals(xsiType)) {
                        DicomImageReferenceEntityHasImagingPhysicalEntityStatement obj = new DicomImageReferenceEntityHasImagingPhysicalEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImageAnnotationHasTimePointLesionObservationEntityStatement".equals(xsiType)) {
                        ImageAnnotationHasTimePointLesionObservationEntityStatement obj = new ImageAnnotationHasTimePointLesionObservationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("UriImageReferenceEntityHasImagingObservationEntityStatement".equals(xsiType)) {
                        UriImageReferenceEntityHasImagingObservationEntityStatement obj = new UriImageReferenceEntityHasImagingObservationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("TwoDimensionGeometricShapeEntityExcludesTwoDimensionGeometricShapeEntityStatement".equals(xsiType)) {
                        TwoDimensionGeometricShapeEntityExcludesTwoDimensionGeometricShapeEntityStatement obj = new TwoDimensionGeometricShapeEntityExcludesTwoDimensionGeometricShapeEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImageAnnotationHasChildImageAnnotationStatement".equals(xsiType)) {
                        ImageAnnotationHasChildImageAnnotationStatement obj = new ImageAnnotationHasChildImageAnnotationStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImagingPhysicalEntityHasTwoDimensionGeometricShapeEntityStatement".equals(xsiType)) {
                        ImagingPhysicalEntityHasTwoDimensionGeometricShapeEntityStatement obj = new ImagingPhysicalEntityHasTwoDimensionGeometricShapeEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImageAnnotationHasImagingObservationEntityStatement".equals(xsiType)) {
                        ImageAnnotationHasImagingObservationEntityStatement obj = new ImageAnnotationHasImagingObservationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImageAnnotationHasTextAnnotationEntityStatement".equals(xsiType)) {
                        ImageAnnotationHasTextAnnotationEntityStatement obj = new ImageAnnotationHasTextAnnotationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImageAnnotationHasUriImageReferenceEntityStatement".equals(xsiType)) {
                        ImageAnnotationHasUriImageReferenceEntityStatement obj = new ImageAnnotationHasUriImageReferenceEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImageAnnotationHasCalculationEntityStatement".equals(xsiType)) {
                        ImageAnnotationHasCalculationEntityStatement obj = new ImageAnnotationHasCalculationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImageAnnotationHasThreeDimensionGeometricShapeEntityStatement".equals(xsiType)) {
                        ImageAnnotationHasThreeDimensionGeometricShapeEntityStatement obj = new ImageAnnotationHasThreeDimensionGeometricShapeEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImageAnnotationHasDicomSegmentationEntityStatement".equals(xsiType)) {
                        ImageAnnotationHasDicomSegmentationEntityStatement obj = new ImageAnnotationHasDicomSegmentationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImagingPhysicalEntityHasThreeDimensionGeometricShapeEntityStatement".equals(xsiType)) {
                        ImagingPhysicalEntityHasThreeDimensionGeometricShapeEntityStatement obj = new ImagingPhysicalEntityHasThreeDimensionGeometricShapeEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("AnnotationEntityHasPerformedTaskContextEntityStatement".equals(xsiType)) {
                        AnnotationEntityHasPerformedTaskContextEntityStatement obj = new AnnotationEntityHasPerformedTaskContextEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("CalculationEntityReferencesCalculationEntityStatement".equals(xsiType)) {
                        CalculationEntityReferencesCalculationEntityStatement obj = new CalculationEntityReferencesCalculationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImagingPhysicalEntityHasImagingObservationEntityStatement".equals(xsiType)) {
                        ImagingPhysicalEntityHasImagingObservationEntityStatement obj = new ImagingPhysicalEntityHasImagingObservationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("AnnotationEntityHasPlannedTaskContextEntityStatement".equals(xsiType)) {
                        AnnotationEntityHasPlannedTaskContextEntityStatement obj = new AnnotationEntityHasPlannedTaskContextEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImagingObservationEntityHasCalculationEntityStatement".equals(xsiType)) {
                        ImagingObservationEntityHasCalculationEntityStatement obj = new ImagingObservationEntityHasCalculationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImagingObservationEntityIsFoundInImagingPhysicalEntityStatement".equals(xsiType)) {
                        ImagingObservationEntityIsFoundInImagingPhysicalEntityStatement obj = new ImagingObservationEntityIsFoundInImagingPhysicalEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("AnnotationOfAnnotationIsComparedWithAnnotationOfAnnotationStatement".equals(xsiType)) {
                        AnnotationOfAnnotationIsComparedWithAnnotationOfAnnotationStatement obj = new AnnotationOfAnnotationIsComparedWithAnnotationOfAnnotationStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImagingPhysicalEntityHasCalculationEntityStatement".equals(xsiType)) {
                        ImagingPhysicalEntityHasCalculationEntityStatement obj = new ImagingPhysicalEntityHasCalculationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("CalculationEntityUsesCalculationEntityStatement".equals(xsiType)) {
                        CalculationEntityUsesCalculationEntityStatement obj = new CalculationEntityUsesCalculationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("AnnotationOfAnnotationStatement".equals(xsiType)) {
                        AnnotationOfAnnotationStatement obj = new AnnotationOfAnnotationStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("AnnotationOfAnnotationHasAnnotationOfAnnotationStatement".equals(xsiType)) {
                        AnnotationOfAnnotationHasAnnotationOfAnnotationStatement obj = new AnnotationOfAnnotationHasAnnotationOfAnnotationStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("AnnotationOfAnnotationHasAnnotationRoleEntityStatement".equals(xsiType)) {
                        AnnotationOfAnnotationHasAnnotationRoleEntityStatement obj = new AnnotationOfAnnotationHasAnnotationRoleEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("AnnotationOfAnnotationHasInferenceEntityStatement".equals(xsiType)) {
                        AnnotationOfAnnotationHasInferenceEntityStatement obj = new AnnotationOfAnnotationHasInferenceEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("AnnotationOfAnnotationHasImagingObservationEntityStatement".equals(xsiType)) {
                        AnnotationOfAnnotationHasImagingObservationEntityStatement obj = new AnnotationOfAnnotationHasImagingObservationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("AnnotationOfAnnotationIsComparedWithImageAnnotationStatement".equals(xsiType)) {
                        AnnotationOfAnnotationIsComparedWithImageAnnotationStatement obj = new AnnotationOfAnnotationIsComparedWithImageAnnotationStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("AnnotationOfAnnotationHasGeneralLesionObservationEntityStatement".equals(xsiType)) {
                        AnnotationOfAnnotationHasGeneralLesionObservationEntityStatement obj = new AnnotationOfAnnotationHasGeneralLesionObservationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("CalculationEntityIsComparedWithCalculationEntityStatement".equals(xsiType)) {
                        CalculationEntityIsComparedWithCalculationEntityStatement obj = new CalculationEntityIsComparedWithCalculationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("AnnotationOfAnnotationHasTimePointLesionObservationEntityStatement".equals(xsiType)) {
                        AnnotationOfAnnotationHasTimePointLesionObservationEntityStatement obj = new AnnotationOfAnnotationHasTimePointLesionObservationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("AnnotationOfAnnotationHasImageAnnotationStatement".equals(xsiType)) {
                        AnnotationOfAnnotationHasImageAnnotationStatement obj = new AnnotationOfAnnotationHasImageAnnotationStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImageAnnotationIsComparedWithImageAnnotationStatement".equals(xsiType)) {
                        ImageAnnotationIsComparedWithImageAnnotationStatement obj = new ImageAnnotationIsComparedWithImageAnnotationStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("ImageAnnotationHasAnnotationRoleEntityStatement".equals(xsiType)) {
                        ImageAnnotationHasAnnotationRoleEntityStatement obj = new ImageAnnotationHasAnnotationRoleEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("AnnotationOfAnnotationHasImagingPhysicalEntityStatement".equals(xsiType)) {
                        AnnotationOfAnnotationHasImagingPhysicalEntityStatement obj = new AnnotationOfAnnotationHasImagingPhysicalEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }
                    if ("AnnotationOfAnnotationHasCalculationEntityStatement".equals(xsiType)) {
                        AnnotationOfAnnotationHasCalculationEntityStatement obj = new AnnotationOfAnnotationHasCalculationEntityStatement();
                        obj.setXMLNode(currentNode);
                        this.addAnnotationOfAnnotationStatement(obj);
                    }

                } else {
                    AnnotationStatement obj = new AnnotationStatement();
                    obj.setXMLNode(tempList.item(j));
                    this.addAnnotationOfAnnotationStatement(obj);
                }
            }
        }

    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools
// | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        AnnotationOfAnnotationStatementCollection oth = (AnnotationOfAnnotationStatementCollection) other;
        if (this.listAnnotationOfAnnotationStatement.size() != oth.listAnnotationOfAnnotationStatement.size()) {
            return false;
        }
        for (int i = 0; i < this.listAnnotationOfAnnotationStatement.size(); i++) {
            if (this.listAnnotationOfAnnotationStatement.get(i) == null ? oth.listAnnotationOfAnnotationStatement.get(i) != null : !this.listAnnotationOfAnnotationStatement.get(i).isEqualTo(oth.listAnnotationOfAnnotationStatement.get(i))) {
                return false;
            }
        }
        return true;
    }

    public AnnotationOfAnnotationStatementCollection getClone() {
        AnnotationOfAnnotationStatementCollection res = new AnnotationOfAnnotationStatementCollection();
        for (int i = 0; i < this.listAnnotationOfAnnotationStatement.size(); i++) {
            if (this.listAnnotationOfAnnotationStatement.get(i) != null) {
                res.addAnnotationOfAnnotationStatement(this.listAnnotationOfAnnotationStatement.get(i).getClone());
            }
        }
        return res;
    }
}
