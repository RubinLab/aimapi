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

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan BULU
 */
public class AnnotationConverter {

    public static Node annotationTCGA2V3(Node node) {

        NamedNodeMap map = node.getAttributes();
        Element res = (Element) node;
        if (!res.hasAttribute("codeValue")) {
            res.setAttribute("codeValue", "-");
        }
        if (!res.hasAttribute("codingSchemeDesignator")) {
            res.setAttribute("codingSchemeDesignator", "-");
        }
        if (!res.hasAttribute("uniqueIdentifier")) {
            res.setAttribute("uniqueIdentifier", "-");
        }
        if (!res.hasAttribute("codeMeaning")) {
            res.setAttribute("codeMeaning", "-");
        }
        if (!res.hasAttribute("cagridId") && res.hasAttribute("id")) {
            res.setAttribute("cagridId", map.getNamedItem("id").getNodeValue());
        } else if (!res.hasAttribute("cagridId") && !res.hasAttribute("id")) {
            res.setAttribute("cagridId", "0");
        }
        if (res.hasAttribute("id")) {
            res.removeAttribute("id");
        }

        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("user".equals(currentNode.getNodeName())) {
                res.replaceChild(userTCGA2V3(currentNode), currentNode);
            } else if ("anatomicEntityCollection".equals(currentNode.getNodeName())) {
                res.replaceChild(anatomicEntityCollectionTCGA2V3(currentNode), currentNode);
            } else if ("geometricShapeCollection".equals(currentNode.getNodeName())) {
                res.replaceChild(geometricShapeCollectionTCGA2V3(currentNode), currentNode);
            } else if ("imagingObservationCollection".equals(currentNode.getNodeName())) {
                res.replaceChild(imagingObservationCollectionTCGA2V3(currentNode), currentNode);
            } else if ("patient".equals(currentNode.getNodeName())) {
                res.replaceChild(patientTCGA2V3(currentNode), currentNode);
            } else if ("imageReferenceCollection".equals(currentNode.getNodeName())) {
                res.replaceChild(imageReferenceCollectionTCGA2V3(currentNode), currentNode);
            } else if ("calculationCollection".equals(currentNode.getNodeName())) {
                res.replaceChild(calculationCollectionTCGA2V3(currentNode), currentNode);
            }
        }
        return res;
        
        
    }

    private static Node userTCGA2V3(Node node) {
        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("User".equals(currentNode.getNodeName())) {
                node.replaceChild(UserTCGA2V3(currentNode), currentNode);
            }
        }
        return node;
    }

    private static Node UserTCGA2V3(Node node) {
        Element res = (Element) node;
        NamedNodeMap map = node.getAttributes();
        if (!res.hasAttribute("cagridId") && res.hasAttribute("id")) {
            res.setAttribute("cagridId", map.getNamedItem("id").getNodeValue());
        } else if (!res.hasAttribute("cagridId") && !res.hasAttribute("id")) {
            res.setAttribute("cagridId", "0");
        }
        if (res.hasAttribute("id")) {
            res.removeAttribute("id");
        }
        if (!res.hasAttribute("name")) {
            res.setAttribute("name", "-");
        }
        if (!res.hasAttribute("loginName")) {
            res.setAttribute("loginName", "-");
        }
        if (res.hasAttribute("numberWithinRoleOfClinicalTrial") && "".equals(map.getNamedItem("numberWithinRoleOfClinicalTrial").getNodeValue())) {
            res.setAttribute("numberWithinRoleOfClinicalTrial", "0");
        }
        return res;
    }

    private static Node anatomicEntityCollectionTCGA2V3(Node node) {
        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("AnatomicEntity".equals(currentNode.getNodeName())) {
                node.replaceChild(AnatomicEntityTCGA2V3(currentNode), currentNode);
            }
        }
        return node;
    }

    private static Node AnatomicEntityTCGA2V3(Node node) {
        NamedNodeMap map = node.getAttributes();
        Element res = (Element) node;
        if (!res.hasAttribute("cagridId") && res.hasAttribute("id")) {
            res.setAttribute("cagridId", map.getNamedItem("id").getNodeValue());
        } else if (!res.hasAttribute("cagridId") && !res.hasAttribute("id")) {
            res.setAttribute("cagridId", "0");
        }
        if (res.hasAttribute("id")) {
            res.removeAttribute("id");
        }
        if (!res.hasAttribute("label")) {
            res.setAttribute("label", "-");
        }
        if (!res.hasAttribute("codingSchemeDesignator") && res.hasAttribute("codeSchemeDesignator")) {
            res.setAttribute("codingSchemeDesignator", map.getNamedItem("codeSchemeDesignator").getNodeValue());
        } else if (!res.hasAttribute("codingSchemeDesignator") && !res.hasAttribute("codeSchemeDesignator")) {
            res.setAttribute("codingSchemeDesignator", "");
        }
        if (res.hasAttribute("codeSchemeDesignator")) {
            res.removeAttribute("codeSchemeDesignator");
        }

        NodeList tempList = res.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("anatomicEntityCharacteristicCollection".equals(currentNode.getNodeName())) {
                res.replaceChild(anatomicEntityCharacteristicCollectionTCGA2V3(currentNode), currentNode);
            }
        }
        return res;
    }

    private static Node anatomicEntityCharacteristicCollectionTCGA2V3(Node node) {
        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("AnatomicEntityCharacteristic".equals(currentNode.getNodeName())) {
                node.replaceChild(AnatomicEntityCharacteristicTCGA2V3(currentNode), currentNode);
            }
        }
        return node;
    }

    private static Node AnatomicEntityCharacteristicTCGA2V3(Node node) {
        NamedNodeMap map = node.getAttributes();
        Element res = (Element) node;
        if (!res.hasAttribute("cagridId") && res.hasAttribute("id")) {
            res.setAttribute("cagridId", map.getNamedItem("id").getNodeValue());
        } else if (!res.hasAttribute("cagridId") && !res.hasAttribute("id")) {
            res.setAttribute("cagridId", "0");
        }
        if (res.hasAttribute("id")) {
            res.removeAttribute("id");
        }
        if (!res.hasAttribute("codingSchemeDesignator")) {
            res.setAttribute("codingSchemeDesignator", "");
        }
        if (!res.hasAttribute("label")) {
            res.setAttribute("label", "-");
        }
        return res;
    }

    private static Node geometricShapeCollectionTCGA2V3(Node node) {
        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("GeometricShape".equals(currentNode.getNodeName())) {
                node.replaceChild(geometricShapeTCGA2V3(currentNode), currentNode);
            }
        }
        return node;
    }

    private static Node geometricShapeTCGA2V3(Node node) {
        NamedNodeMap map = node.getAttributes();
        Element res = (Element) node;
        if (!res.hasAttribute("cagridId") && res.hasAttribute("id")) {
            res.setAttribute("cagridId", map.getNamedItem("id").getNodeValue());
        } else if (!res.hasAttribute("cagridId") && !res.hasAttribute("id")) {
            res.setAttribute("cagridId", "0");
        }
        if (res.hasAttribute("id")) {
            res.removeAttribute("id");
        }
        if (!res.hasAttribute("shapeIdentifier")) {
            res.setAttribute("shapeIdentifier", "0");
        }

        NodeList tempList = res.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("spatialCoordinateCollection".equals(currentNode.getNodeName())) {
                res.replaceChild(spatialCoordinateCollectionTCGA2V3(currentNode), currentNode);
            }
        }
        return res;
    }

    private static Node spatialCoordinateCollectionTCGA2V3(Node node) {
        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("SpatialCoordinate".equals(currentNode.getNodeName())) {
                node.replaceChild(spatialCoordinateTCGA2V3(currentNode), currentNode);
            }
        }
        return node;
    }

    private static Node spatialCoordinateTCGA2V3(Node node) {
        NamedNodeMap map = node.getAttributes();
        Element res = (Element) node;
        if (!res.hasAttribute("cagridId") && res.hasAttribute("id")) {
            res.setAttribute("cagridId", map.getNamedItem("id").getNodeValue());
        } else if (!res.hasAttribute("cagridId") && !res.hasAttribute("id")) {
            res.setAttribute("cagridId", "0");
        }
        if (res.hasAttribute("id")) {
            res.removeAttribute("id");
        }
        if (res.hasAttribute("xsi:type") && "TwoDSpatialCoordinate".equals(map.getNamedItem("xsi:type").getNodeValue())) {
            res.setAttribute("xsi:type", "TwoDimensionSpatialCoordinate");
        }
        if (res.hasAttribute("referencedFrameNumber") && "".equals(map.getNamedItem("referencedFrameNumber").getNodeValue())) {
            res.setAttribute("referencedFrameNumber", "0");
        }
        return res;
    }

    private static Node imagingObservationCollectionTCGA2V3(Node node) {
        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("ImagingObservation".equals(currentNode.getNodeName())) {
                node.replaceChild(imagingObservationTCGA2V3(currentNode), currentNode);
            }
        }
        return node;
    }

    private static Node imagingObservationTCGA2V3(Node node) {
        NamedNodeMap map = node.getAttributes();
        Element res = (Element) node;
        if (!res.hasAttribute("cagridId") && res.hasAttribute("id")) {
            res.setAttribute("cagridId", map.getNamedItem("id").getNodeValue());
        } else if (!res.hasAttribute("cagridId") && !res.hasAttribute("id")) {
            res.setAttribute("cagridId", "0");
        }
        if (res.hasAttribute("id")) {
            res.removeAttribute("id");
        }
        if (!res.hasAttribute("codingSchemeDesignator")) {
            res.setAttribute("codingSchemeDesignator", "-");
        }
        if (!res.hasAttribute("label")) {
            res.setAttribute("label", "-");
        }
        if (!res.hasAttribute("codeValue")) {
            res.setAttribute("codeValue", "-");
        }
        if (!res.hasAttribute("codeMeaning")) {
            res.setAttribute("codeMeaning", "-");
        }

        NodeList tempList = res.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("imagingObservationCharacteristicCollection".equals(currentNode.getNodeName())) {
                res.replaceChild(imagingObservationCharacteristicCollectionTCGA2V3(currentNode), currentNode);
            }
        }
        return res;
    }

    private static Node imagingObservationCharacteristicCollectionTCGA2V3(Node node) {
        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("ImagingObservationCharacteristic".equals(currentNode.getNodeName())) {
                node.replaceChild(imagingObservationCharacteristicTCGA2V3(currentNode), currentNode);
            }
        }
        return node;
    }

    private static Node imagingObservationCharacteristicTCGA2V3(Node node) {
        NamedNodeMap map = node.getAttributes();
        Element res = (Element) node;

        if (!res.hasAttribute("cagridId") && res.hasAttribute("id")) {
            res.setAttribute("cagridId", map.getNamedItem("id").getNodeValue());
        } else if (!res.hasAttribute("cagridId") && !res.hasAttribute("id")) {
            res.setAttribute("cagridId", "0");
        }
        if (res.hasAttribute("id")) {
            res.removeAttribute("id");
        }
        if (!res.hasAttribute("codingSchemeDesignator")) {
            res.setAttribute("codingSchemeDesignator", "-");
        }
        if (!res.hasAttribute("label")) {
            res.setAttribute("label", "-");
        }
        return res;
    }

    private static Node patientTCGA2V3(Node node) {
        node = renameNode(node, "person");
        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("Patient".equals(currentNode.getNodeName())) {
                node.replaceChild(PatientTCGA2V3(currentNode), currentNode);
            }
        }
        return node;
    }

    private static Node PatientTCGA2V3(Node node) {
        node = renameNode(node, "Person");
        NamedNodeMap map = node.getAttributes();
        Element res = (Element) node;
        String id = map.getNamedItem("patientID").getNodeValue();
        String cagridId = map.getNamedItem("id").getNodeValue();
        res.setAttribute("cagridId", cagridId);
        res.setAttribute("id", id);
        res.removeAttribute("patientID");
        if (!res.hasAttribute("name")) {
            res.setAttribute("name", "-");
        }
        if (res.hasAttribute("birthDate") && "".equals(map.getNamedItem("birthDate").getNodeValue())) {
            res.setAttribute("birthDate", "1900-01-01T00:00:00");
        }
        return res;
    }

    private static Node imageReferenceCollectionTCGA2V3(Node node) {
        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("ImageReference".equals(currentNode.getNodeName())) {
                node.replaceChild(imageReferenceTCGA2V3(currentNode), currentNode);
            }
        }
        return node;
    }

    private static Node imageReferenceTCGA2V3(Node node) {
        Element res = (Element) node;
        NamedNodeMap map = node.getAttributes();
        if (!res.hasAttribute("cagridId") && res.hasAttribute("id")) {
            res.setAttribute("cagridId", map.getNamedItem("id").getNodeValue());
        } else if (!res.hasAttribute("cagridId") && !res.hasAttribute("id")) {
            res.setAttribute("cagridId", "0");
        }
        if (res.hasAttribute("id")) {
            res.removeAttribute("id");
        }
        if (!res.hasAttribute("xsi:type")) {
            res.setAttribute("xsi:type", "DICOMImageReference");
        }

        NodeList tempList = res.getChildNodes();
        boolean containOutherStudyTag = false;
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("study".equals(currentNode.getNodeName())) {
                containOutherStudyTag = true;
                break;
            }
        }
        if (!containOutherStudyTag) {
            Element elementStudy = node.getOwnerDocument().createElement("study");
            for (int i = 0; i < tempList.getLength(); i++) {
                Node currentNode = tempList.item(i);
                if ("Study".equals(currentNode.getNodeName())) {
                    elementStudy.appendChild(currentNode);
                }
            }
            res.appendChild(elementStudy);
        }
        tempList = res.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("study".equals(currentNode.getNodeName())) {
                node.replaceChild(studyTCGA2V3(currentNode), currentNode);
            }
        }

        return res;
    }

    private static Node studyTCGA2V3(Node node) {
        node = renameNode(node, "imageStudy");
        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("Study".equals(currentNode.getNodeName())) {
                node.replaceChild(StudyTCGA2V3(currentNode), currentNode);
            }
        }
        return node;
    }

    private static Node StudyTCGA2V3(Node node) {
        node = renameNode(node, "ImageStudy");
        NamedNodeMap map = node.getAttributes();
        Element res = (Element) node;
        if (!res.hasAttribute("cagridId") && res.hasAttribute("id")) {
            res.setAttribute("cagridId", map.getNamedItem("id").getNodeValue());
        } else if (!res.hasAttribute("cagridId") && !res.hasAttribute("id")) {
            res.setAttribute("cagridId", "0");
        }
        if (res.hasAttribute("id")) {
            res.removeAttribute("id");
        }
        if (res.hasAttribute("studyInstanceUID")) {
            res.setAttribute("instanceUID", map.getNamedItem("studyInstanceUID").getNodeValue());
            res.removeAttribute("studyInstanceUID");
        }
        if (res.hasAttribute("date")) {
            res.setAttribute("startDate", map.getNamedItem("date").getNodeValue());
            res.removeAttribute("date");
        }
        if (res.hasAttribute("studyDate")) {
            res.setAttribute("startDate", map.getNamedItem("studyDate").getNodeValue());
            res.removeAttribute("studyDate");
        }
        res.setAttribute("startTime", "-");
        NodeList tempList = res.getChildNodes();
        boolean containOutherSeriesTag = false;
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("series".equals(currentNode.getNodeName())) {
                containOutherSeriesTag = true;
                break;
            }
        }
        if (!containOutherSeriesTag) {
            Element elementStudy = node.getOwnerDocument().createElement("series");
            for (int i = 0; i < tempList.getLength(); i++) {
                Node currentNode = tempList.item(i);
                if ("Series".equals(currentNode.getNodeName())) {
                    elementStudy.appendChild(currentNode);
                }
            }
            res.appendChild(elementStudy);
        }
        tempList = res.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("series".equals(currentNode.getNodeName())) {
                node.replaceChild(seriesTCGA2V3(currentNode), currentNode);
            }
        }
        return res;
    }

    private static Node seriesTCGA2V3(Node node) {
        node = renameNode(node, "imageSeries");
        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("Series".equals(currentNode.getNodeName())) {
                node.replaceChild(SeriesTCGA2V3(currentNode), currentNode);
            }
        }
        return node;
    }

    private static Node SeriesTCGA2V3(Node node) {
        node = renameNode(node, "ImageSeries");
        NamedNodeMap map = node.getAttributes();
        Element res = (Element) node;
        if (!res.hasAttribute("cagridId") && res.hasAttribute("id")) {
            res.setAttribute("cagridId", map.getNamedItem("id").getNodeValue());
        } else if (!res.hasAttribute("cagridId") && !res.hasAttribute("id")) {
            res.setAttribute("cagridId", "0");
        }
        if (res.hasAttribute("id")) {
            res.removeAttribute("id");
        }
        if (res.hasAttribute("seriesInstanceUID")) {
            res.setAttribute("instanceUID", map.getNamedItem("seriesInstanceUID").getNodeValue());
            res.removeAttribute("seriesInstanceUID");
        }

        NodeList tempList = res.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("imageCollection".equals(currentNode.getNodeName())) {
                node.replaceChild(imageCollectionTCGA2V3(currentNode), currentNode);
            }
        }
        return res;
    }

    private static Node imageCollectionTCGA2V3(Node node) {
        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("Image".equals(currentNode.getNodeName())) {
                node.replaceChild(ImageTCGA2V3(currentNode), currentNode);
            }
        }
        return node;
    }

    private static Node ImageTCGA2V3(Node node) {
        NamedNodeMap map = node.getAttributes();
        Element res = (Element) node;
        if (!res.hasAttribute("cagridId") && res.hasAttribute("id")) {
            res.setAttribute("cagridId", map.getNamedItem("id").getNodeValue());
        } else if (!res.hasAttribute("cagridId") && !res.hasAttribute("id")) {
            res.setAttribute("cagridId", "0");
        }
        if (res.hasAttribute("id")) {
            res.removeAttribute("id");
        }
        if (res.hasAttribute("SOPInstanceUID")) {
            res.setAttribute("sopInstanceUID", map.getNamedItem("SOPInstanceUID").getNodeValue());
            res.removeAttribute("SOPInstanceUID");
        }
        if (res.hasAttribute("SOPClassUID")) {
            res.setAttribute("sopClassUID", map.getNamedItem("SOPClassUID").getNodeValue());
            res.removeAttribute("SOPClassUID");
        }
        return res;
    }

    private static Node renameNode(Node node, String newName) {

        Element newNode = node.getOwnerDocument().createElement(newName);
        NamedNodeMap map = node.getAttributes();
        for (int i = 0; i < map.getLength(); i++) {
            newNode.setAttribute(map.item(i).getNodeName(), map.item(i).getNodeValue());
        }

        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            newNode.appendChild(tempList.item(i).cloneNode(true));
        }

        return newNode;
    }

    private static Node calculationCollectionTCGA2V3(Node node) {
        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("Calculation".equals(currentNode.getNodeName())) {
                node.replaceChild(CalculationTCGA2V3(currentNode), currentNode);
            }
        }
        return node;
    }

    private static Node CalculationTCGA2V3(Node node) {
        NamedNodeMap map = node.getAttributes();
        Element res = (Element) node;

        if (!res.hasAttribute("cagridId") && res.hasAttribute("id")) {
            res.setAttribute("cagridId", map.getNamedItem("id").getNodeValue());
        } else if (!res.hasAttribute("cagridId") && !res.hasAttribute("id")) {
            res.setAttribute("cagridId", "0");
        }
        if (res.hasAttribute("id")) {
            res.removeAttribute("id");
        }
        if (!res.hasAttribute("codeMeaning")) {
            res.setAttribute("codeMeaning", "-");
        }
        if (!res.hasAttribute("codingSchemeDesignator")) {
            res.setAttribute("codingSchemeDesignator", "-");
        }
        if (!res.hasAttribute("uid")) {
            res.setAttribute("uid", "0");
        }
        if (!res.hasAttribute("description")) {
            res.setAttribute("description", "-");
        }
        if (!res.hasAttribute("codeValue")) {
            res.setAttribute("codeValue", "-");
        }

        NodeList tempList = res.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("calculationResultCollection".equals(currentNode.getNodeName())) {
                node.replaceChild(calculationResultCollectionTCGA2V3(currentNode), currentNode);
            } else if ("referencedGeometricShapeCollection".equals(currentNode.getNodeName())) {
                node.replaceChild(referencedGeometricShapeCollectionTCGA2V3(currentNode), currentNode);
            }
        }
        return res;
    }

    private static Node calculationResultCollectionTCGA2V3(Node node) {
        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("CalculationResult".equals(currentNode.getNodeName())) {
                node.replaceChild(CalculationResultTCGA2V3(currentNode), currentNode);
            }
        }
        return node;
    }

    private static Node CalculationResultTCGA2V3(Node node) {
        NamedNodeMap map = node.getAttributes();
        Element res = (Element) node;

        if (!res.hasAttribute("cagridId") && res.hasAttribute("id")) {
            res.setAttribute("cagridId", map.getNamedItem("id").getNodeValue());
        } else if (!res.hasAttribute("cagridId") && !res.hasAttribute("id")) {
            res.setAttribute("cagridId", "0");
        }
        if (res.hasAttribute("id")) {
            res.removeAttribute("id");
        }
        if (!res.hasAttribute("type")) {
            res.setAttribute("type", "Scalar");
        }

        NodeList tempList = res.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("dimensionCollection".equals(currentNode.getNodeName())) {
                node.replaceChild(dimensionCollectionTCGA2V3(currentNode), currentNode);
            }
        }
        return res;
    }

    private static Node dimensionCollectionTCGA2V3(Node node) {
        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("Dimension".equals(currentNode.getNodeName())) {
                node.replaceChild(DimensionTCGA2V3(currentNode), currentNode);
            }
        }
        return node;
    }

    private static Node DimensionTCGA2V3(Node node) {
        NamedNodeMap map = node.getAttributes();
        Element res = (Element) node;

        if (!res.hasAttribute("cagridId") && res.hasAttribute("id")) {
            res.setAttribute("cagridId", map.getNamedItem("id").getNodeValue());
        } else if (!res.hasAttribute("cagridId") && !res.hasAttribute("id")) {
            res.setAttribute("cagridId", "0");
        }
        if (res.hasAttribute("id")) {
            res.removeAttribute("id");
        }
        if (!res.hasAttribute("label")) {
            res.setAttribute("label", "-");
        }
        return res;
    }

    private static Node referencedGeometricShapeCollectionTCGA2V3(Node node) {
        NodeList tempList = node.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            Node currentNode = tempList.item(i);
            if ("ReferencedGeometricShape".equals(currentNode.getNodeName())) {
                node.replaceChild(ReferencedGeometricShapeTCGA2V3(currentNode), currentNode);
            }
        }
        return node;
    }

    private static Node ReferencedGeometricShapeTCGA2V3(Node node) {
        NamedNodeMap map = node.getAttributes();
        Element res = (Element) node;

        if (!res.hasAttribute("cagridId") && res.hasAttribute("id")) {
            res.setAttribute("cagridId", map.getNamedItem("id").getNodeValue());
        } else if (!res.hasAttribute("cagridId") && !res.hasAttribute("id")) {
            res.setAttribute("cagridId", "0");
        }
        if (res.hasAttribute("id")) {
            res.removeAttribute("id");
        }
        return res;
    }
}
