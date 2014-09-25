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
import edu.stanford.hakan.aim4api.base.II;
import edu.stanford.hakan.aim4api.base.ImagingPhysicalEntityHasCalculationEntityStatement;
import edu.stanford.hakan.aim4api.base.ImagingPhysicalEntityHasTwoDimensionGeometricShapeEntityStatement;
import edu.stanford.hakan.aim4api.utility.GenerateId;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan BULU
 */
public class ReferencedGeometricShape implements IAimXMLOperations {

    private Integer cagridId;
    private Integer referencedShapeIdentifier;

    public ReferencedGeometricShape() {
    }

    public ReferencedGeometricShape(Integer cagridId, Integer referencedShapeIdentifier) {
        this.cagridId = cagridId;
        this.referencedShapeIdentifier = referencedShapeIdentifier;
    }

    public Integer getCagridId() {
        return cagridId;
    }

    public void setCagridId(Integer cagridId) {
        this.cagridId = cagridId;
    }

    public Integer getReferencedShapeIdentifier() {
        return referencedShapeIdentifier;
    }

    public void setReferencedShapeIdentifier(Integer referencedShapeIdentifier) {
        this.referencedShapeIdentifier = referencedShapeIdentifier;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {

        this.Control();

        Element referencedGeometricShape = doc.createElement("ReferencedGeometricShape");
        referencedGeometricShape.setAttribute("cagridId", this.cagridId.toString());
        referencedGeometricShape.setAttribute("referencedShapeIdentifier", this.referencedShapeIdentifier.toString());
        return referencedGeometricShape;
    }

    @Override
    public void setXMLNode(Node node) {
        NamedNodeMap map = node.getAttributes();
        this.cagridId = Integer.parseInt(map.getNamedItem("cagridId").getNodeValue());
        this.referencedShapeIdentifier = Integer.parseInt(map.getNamedItem("referencedShapeIdentifier").getNodeValue());
    }

    private void Control() throws AimException {
        if (getCagridId() == null) {
            throw new AimException("AimException: ReferencedGeometricShape's cagridId can not be null");
        }
        if (getReferencedShapeIdentifier() == null) {
            throw new AimException("AimException: ReferencedGeometricShape's referencedShapeIdentifier can not be null");
        }
    }

    public boolean isEqualTo(Object other) {
        ReferencedGeometricShape oth = (ReferencedGeometricShape) other;
        if (this.cagridId != oth.cagridId) {
            return false;
        }
        if (this.referencedShapeIdentifier == null ? oth.referencedShapeIdentifier != null : !this.referencedShapeIdentifier.equals(oth.referencedShapeIdentifier)) {
            return false;
        }
        return true;
    }

    public void toAimV4(edu.stanford.hakan.aim4api.base.ImageAnnotation imageAnnotation, II calculationUniqueIdentifier) {
        String uid = GenerateId.getUUID();
        ImagingPhysicalEntityHasTwoDimensionGeometricShapeEntityStatement connectGeoShapeWithImagingPhysicalEntity = new ImagingPhysicalEntityHasTwoDimensionGeometricShapeEntityStatement();
        connectGeoShapeWithImagingPhysicalEntity.setSubjectUniqueIdentifier(Converter.toII(this.getReferencedShapeIdentifier()));
        connectGeoShapeWithImagingPhysicalEntity.setObjectUniqueIdentifier(Converter.toII(uid));
        imageAnnotation.addImageAnnotationStatement(connectGeoShapeWithImagingPhysicalEntity);

        ImagingPhysicalEntityHasCalculationEntityStatement connectCalculationWithImagingPhysicalEntity = new ImagingPhysicalEntityHasCalculationEntityStatement();
        connectCalculationWithImagingPhysicalEntity.setSubjectUniqueIdentifier(Converter.toII(calculationUniqueIdentifier.getRoot()));
        connectCalculationWithImagingPhysicalEntity.setObjectUniqueIdentifier(Converter.toII(uid));
        imageAnnotation.addImageAnnotationStatement(connectCalculationWithImagingPhysicalEntity);
    }

//    public ReferencedGeometricShape(ImagingPhysicalEntityHasTwoDimensionGeometricShapeEntityStatement v4_1, ImagingPhysicalEntityHasCalculationEntityStatement v4_2) {
//
//    }
}
