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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class ImageStudy implements IAimXMLOperations {

    private II instanceUid;
    private String startDate;
    private String startTime;
    private ST procedureDescription;
    private ST accessionNumber;
    private ImageSeries imageSeries;
    private ReferencedDicomObjectCollection referencedDicomObjectCollection = new ReferencedDicomObjectCollection();
    private String tagName;

    public II getInstanceUid() {
        return instanceUid;
    }

    public void setInstanceUid(II instanceUid) {
        instanceUid.setTagName("instanceUid");
        this.instanceUid = instanceUid;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public ST getProcedureDescription() {
        return procedureDescription;
    }

    public void setProcedureDescription(ST procedureDescription) {
        procedureDescription.setTagName("procedureDescription");
        this.procedureDescription = procedureDescription;
    }

    public ImageSeries getImageSeries() {
        return imageSeries;
    }

    public void setImageSeries(ImageSeries imageSeries) {
        imageSeries.setTagName("imageSeries");
        this.imageSeries = imageSeries;
    }

    public ReferencedDicomObjectCollection getReferencedDicomObjectCollection() {
        return referencedDicomObjectCollection;
    }

    public void setReferencedDicomObjectCollection(ReferencedDicomObjectCollection referencedDicomObjectCollection) {
        this.referencedDicomObjectCollection = referencedDicomObjectCollection;
    }

    public void addReferencedDicomObject(ReferencedDicomObject newReferencedDicomObject) {
        this.referencedDicomObjectCollection.addReferencedDicomObject(newReferencedDicomObject);
    }

    public ST getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(ST accessionNumber) {
		accessionNumber.setTagName("accessionNumber");
		this.accessionNumber = accessionNumber;
	}

	protected String getTagName() {
        return tagName;
    }

    protected void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("ImageStudy");
        }
        Element res = doc.createElement(getTagName());
        if (this.instanceUid != null) {
            res.appendChild(this.instanceUid.getXMLNode(doc));
        }
        if (this.startDate != null) {
        	//ml -s removed for proper date (clunie)
//            if (this.startDate.length() == 8 && !this.startDate.contains("-")) {
//                this.startDate = this.startDate.substring(0, 4) + "-" + this.startDate.substring(4, 6) + "-" + this.startDate.substring(6, 8);
//            }
            Element el_startDate = doc.createElement("startDate");
            el_startDate.setAttribute("value", this.startDate.toString());
            res.appendChild(el_startDate);
        }
        if (this.startTime != null) {
            Element el_startTime = doc.createElement("startTime");
            el_startTime.setAttribute("value", this.startTime.toString());
            res.appendChild(el_startTime);
        }
        if (this.procedureDescription != null) {
            res.appendChild(this.procedureDescription.getXMLNode(doc));
        }
        if (this.accessionNumber != null) {
            res.appendChild(this.accessionNumber.getXMLNode(doc));
        }
        if (this.imageSeries != null) {
            res.appendChild(this.imageSeries.getXMLNode(doc));
        }
        if (this.referencedDicomObjectCollection.size() > 0) {
            res.appendChild(this.referencedDicomObjectCollection.getXMLNode(doc));
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("instanceUid".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setInstanceUid(obj);
            }
            if ("startDate".equals(currentNode.getNodeName())) {
                this.startDate = currentNode.getAttributes().getNamedItem("value").getNodeValue();
            }
            if ("startTime".equals(currentNode.getNodeName())) {
                this.startTime = currentNode.getAttributes().getNamedItem("value").getNodeValue();
            }
            if ("procedureDescription".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setProcedureDescription(obj);
            }
            if ("accessionNumber".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setAccessionNumber(obj);
            }
            if ("imageSeries".equals(currentNode.getNodeName())) {
                ImageSeries obj = new ImageSeries();
                obj.setXMLNode(currentNode);
                this.setImageSeries(obj);
            }
            if ("referencedDicomObjectCollection".equals(listChilds.item(i).getNodeName())) {
                this.referencedDicomObjectCollection.setXMLNode(listChilds.item(i));
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        ImageStudy oth = (ImageStudy) other;
        if (this.instanceUid == null ? oth.instanceUid != null : !this.instanceUid.isEqualTo(oth.instanceUid)) {
            return false;
        }
        if (this.startDate == null ? oth.startDate != null : !this.startDate.replace("-", "").equals(oth.startDate.replace("-", ""))) {
            return false;
        }
        if (this.startTime == null ? oth.startTime != null : !this.startTime.equals(oth.startTime)) {
            return false;
        }
        if (this.procedureDescription == null ? oth.procedureDescription != null : !this.procedureDescription.isEqualTo(oth.procedureDescription)) {
            return false;
        }
        if (this.accessionNumber == null ? oth.accessionNumber != null : !this.accessionNumber.isEqualTo(oth.accessionNumber)) {
            return false;
        }
        if (this.imageSeries == null ? oth.imageSeries != null : !this.imageSeries.isEqualTo(oth.imageSeries)) {
            return false;
        }
        if (this.referencedDicomObjectCollection == null ? oth.referencedDicomObjectCollection != null : !this.referencedDicomObjectCollection.isEqualTo(oth.referencedDicomObjectCollection)) {
            return false;
        }
        return true;
    }

    public ImageStudy getClone() {
        ImageStudy res = new ImageStudy();
        if (this.getInstanceUid() != null) {
            res.setInstanceUid(this.getInstanceUid().getClone());
        }
        if (this.getStartDate() != null) {
            res.setStartDate(this.getStartDate());
        }
        if (this.getStartTime() != null) {
            res.setStartTime(this.getStartTime());
        }
        if (this.getProcedureDescription() != null) {
            res.setProcedureDescription(this.getProcedureDescription().getClone());
        }
        if (this.getImageSeries() != null) {
            res.setImageSeries(this.getImageSeries().getClone());
        }
        if (this.getReferencedDicomObjectCollection() != null) {
            res.setReferencedDicomObjectCollection(this.getReferencedDicomObjectCollection().getClone());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        if (this.getAccessionNumber() != null) {
            res.setAccessionNumber(this.getAccessionNumber());
        }
        return res;
    }
}
