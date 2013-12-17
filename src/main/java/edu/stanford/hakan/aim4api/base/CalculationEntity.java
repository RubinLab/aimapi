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

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author localadmin
 */
public class CalculationEntity extends Entity
{

	public CalculationEntity()
	{
		setXsiType("CalculationEntity");
	}

	private List<CD> listTypeCode = new ArrayList<CD>();
	private List<CD> listQuestionTypeCode = new ArrayList<CD>();
	private ST description;
	private ST mathML;
	private Integer questionIndex;
	private CalculationResultCollection calculationResultCollection = new CalculationResultCollection();
	private Algorithm algorithm;

	public List<CD> getListTypeCode()
	{
		return this.listTypeCode;
	}

	public void setTypeCode(List<CD> listTypeCode)
	{
		this.listTypeCode = listTypeCode;
		for (int i = 0; i < this.listTypeCode.size(); i++) {
			this.listTypeCode.get(i).setTagName("typeCode");
		}
	}

	public void addTypeCode(CD newTypeCode)
	{
		newTypeCode.setTagName("typeCode");
		this.listTypeCode.add(newTypeCode);
	}

	public List<CD> getListQuestionTypeCode()
	{
		return this.listQuestionTypeCode;
	}

	public void setQuestionTypeCode(List<CD> listQuestionTypeCode)
	{
		this.listQuestionTypeCode = listQuestionTypeCode;
		for (int i = 0; i < this.listQuestionTypeCode.size(); i++) {
			this.listQuestionTypeCode.get(i).setTagName("questionTypeCode");
		}
	}

	public void addQuestionTypeCode(CD newQuestionTypeCode)
	{
		newQuestionTypeCode.setTagName("questionTypeCode");
		this.listQuestionTypeCode.add(newQuestionTypeCode);
	}

	public ST getDescription()
	{
		return description;
	}

	public void setDescription(ST description)
	{
		description.setTagName("description");
		this.description = description;
	}

	public ST getMathML()
	{
		return mathML;
	}

	public void setMathML(ST mathML)
	{
		mathML.setTagName("mathML");
		this.mathML = mathML;
	}

	public Integer getQuestionIndex()
	{
		return questionIndex;
	}

	public void setQuestionIndex(Integer questionIndex)
	{
		this.questionIndex = questionIndex;
	}

	public CalculationResultCollection getCalculationResultCollection()
	{
		return calculationResultCollection;
	}

	public void setCalculationResultCollection(CalculationResultCollection calculationResultCollection)
	{
		this.calculationResultCollection = calculationResultCollection;
	}

	public void addCalculationResult(CalculationResult newCalculationResult)
	{
		this.calculationResultCollection.addCalculationResult(newCalculationResult);
	}

	public Algorithm getAlgorithm()
	{
		return algorithm;
	}

	public void setAlgorithm(Algorithm algorithm)
	{
		algorithm.setTagName("algorithm");
		this.algorithm = algorithm;
	}

	@Override
	public Node getXMLNode(Document doc) throws AimException
	{
		if (getTagName() == null || "".equals(getTagName())) {
			setTagName("CalculationEntity");
		}
		Element res = (Element)super.getXMLNode(doc);
		if (this.listTypeCode.size() > 0) {
			for (int i = 0; i < this.listTypeCode.size(); i++) {
				res.appendChild(this.listTypeCode.get(i).getXMLNode(doc));
			}
		}
		if (this.listQuestionTypeCode.size() > 0) {
			for (int i = 0; i < this.listQuestionTypeCode.size(); i++) {
				res.appendChild(this.listQuestionTypeCode.get(i).getXMLNode(doc));
			}
		}
		if (this.description != null) {
			res.appendChild(this.description.getXMLNode(doc));
		}
		if (this.mathML != null) {
			res.appendChild(this.mathML.getXMLNode(doc));
		}
		if (this.questionIndex != null) {
			Element el_questionIndex = doc.createElement("questionIndex");
			el_questionIndex.setAttribute("value", this.questionIndex.toString());
			res.appendChild(el_questionIndex);
		}
		if (this.calculationResultCollection.size() > 0) {
			res.appendChild(this.calculationResultCollection.getXMLNode(doc));
		}
		if (this.algorithm != null) {
			res.appendChild(this.algorithm.getXMLNode(doc));
		}
		return res;
	}

	@Override
	public void setXMLNode(Node node)
	{
		super.setXMLNode(node);
		this.listTypeCode.clear();
		this.listQuestionTypeCode.clear();
		NodeList listChilds = node.getChildNodes();
		for (int i = 0; i < listChilds.getLength(); i++) {
			Node currentNode = listChilds.item(i);
			if ("typeCode".equals(currentNode.getNodeName())) {
				CD obj = new CD();
				obj.setXMLNode(currentNode);
				this.addTypeCode(obj);
			}
			if ("questionTypeCode".equals(currentNode.getNodeName())) {
				CD obj = new CD();
				obj.setXMLNode(currentNode);
				this.addQuestionTypeCode(obj);
			}
			if ("description".equals(currentNode.getNodeName())) {
				ST obj = new ST();
				obj.setXMLNode(currentNode);
				this.setDescription(obj);
			}
			if ("mathML".equals(currentNode.getNodeName())) {
				ST obj = new ST();
				obj.setXMLNode(currentNode);
				this.setMathML(obj);
			}
			if ("questionIndex".equals(currentNode.getNodeName())) {
				this.questionIndex = Integer.parseInt(currentNode.getAttributes().getNamedItem("value").getNodeValue());
			}
			if ("calculationResultCollection".equals(listChilds.item(i).getNodeName())) {
				this.calculationResultCollection.setXMLNode(listChilds.item(i));
			}
			if ("algorithm".equals(currentNode.getNodeName())) {
				Algorithm obj = new Algorithm();
				obj.setXMLNode(currentNode);
				this.setAlgorithm(obj);
			}
		}
	}

	@Override
	public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException
	{
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools
																																		// | Templates.
	}

	@Override
	public boolean isEqualTo(Object other)
	{
		CalculationEntity oth = (CalculationEntity)other;
		if (this.listTypeCode.size() != oth.listTypeCode.size()) {
			return false;
		}
		for (int i = 0; i < this.listTypeCode.size(); i++) {
			if (!this.listTypeCode.get(i).isEqualTo(oth.listTypeCode.get(i))) {
				return false;
			}
		}
		if (this.listQuestionTypeCode.size() != oth.listQuestionTypeCode.size()) {
			return false;
		}
		for (int i = 0; i < this.listQuestionTypeCode.size(); i++) {
			if (!this.listQuestionTypeCode.get(i).isEqualTo(oth.listQuestionTypeCode.get(i))) {
				return false;
			}
		}
		if (!this.description.isEqualTo(oth.description)) {
			return false;
		}
		if (!this.mathML.isEqualTo(oth.mathML)) {
			return false;
		}
		if (this.questionIndex == null ? oth.questionIndex != null : !this.questionIndex.equals(oth.questionIndex)) {
			return false;
		}
		if (!this.calculationResultCollection.isEqualTo(oth.calculationResultCollection)) {
			return false;
		}
		if (!this.algorithm.isEqualTo(oth.algorithm)) {
			return false;
		}
		return super.isEqualTo(other);
	}

	@Override
	public CalculationEntity getClone()
	{
		CalculationEntity res = new CalculationEntity();
		for (int i = 0; i < this.listTypeCode.size(); i++) {
			if (this.listTypeCode.get(i) != null) {
				res.addTypeCode(this.listTypeCode.get(i).getClone());
			}
		}
		for (int i = 0; i < this.listQuestionTypeCode.size(); i++) {
			if (this.listQuestionTypeCode.get(i) != null) {
				res.addQuestionTypeCode(this.listQuestionTypeCode.get(i).getClone());
			}
		}
		if (this.getDescription() != null) {
			res.setDescription(this.getDescription().getClone());
		}
		if (this.getMathML() != null) {
			res.setMathML(this.getMathML().getClone());
		}
		if (this.getQuestionIndex() != null) {
			res.setQuestionIndex(this.getQuestionIndex());
		}
		if (this.getCalculationResultCollection() != null) {
			res.setCalculationResultCollection(this.getCalculationResultCollection().getClone());
		}
		if (this.getAlgorithm() != null) {
			res.setAlgorithm(this.getAlgorithm().getClone());
		}
		if (this.getUniqueIdentifier() != null) {
			res.setUniqueIdentifier(this.getUniqueIdentifier().getClone());
		}
		if (this.getTagName() != null) {
			res.setTagName(this.getTagName());
		}
		if (this.getXsiType() != null) {
			res.setXsiType(this.getXsiType());
		}
		return res;
	}
}
