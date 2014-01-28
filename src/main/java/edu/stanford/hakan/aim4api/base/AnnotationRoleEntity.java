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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author localadmin
 */
public class AnnotationRoleEntity extends Entity
{

	public AnnotationRoleEntity()
	{
		setXsiType("AnnotationRoleEntity");
	}

	private CD roleCode;
	private List<CD> listQuestionTypeCode = new ArrayList<CD>();
	private Integer roleSequenceNumber;

	public CD getRoleCode()
	{
		return roleCode;
	}

	public void setRoleCode(CD roleCode)
	{
		roleCode.setTagName("roleCode");
		this.roleCode = roleCode;
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

	public Integer getRoleSequenceNumber()
	{
		return roleSequenceNumber;
	}

	public void setRoleSequenceNumber(Integer roleSequenceNumber)
	{
		this.roleSequenceNumber = roleSequenceNumber;
	}

	@Override
	public Node getXMLNode(Document doc) throws AimException
	{
		if (getTagName() == null || "".equals(getTagName())) {
			setTagName("AnnotationRoleEntity");
		}
		Element res = (Element)super.getXMLNode(doc);
		if (this.roleCode != null) {
			res.appendChild(this.roleCode.getXMLNode(doc));
		}
		if (this.listQuestionTypeCode.size() > 0) {
			for (int i = 0; i < this.listQuestionTypeCode.size(); i++) {
				res.appendChild(this.listQuestionTypeCode.get(i).getXMLNode(doc));
			}
		}
		if (this.roleSequenceNumber != null) {
			Element el_roleSequenceNumber = doc.createElement("roleSequenceNumber");
			el_roleSequenceNumber.setAttribute("value", this.roleSequenceNumber.toString());
			res.appendChild(el_roleSequenceNumber);
		}
		return res;
	}

	@Override
	public void setXMLNode(Node node)
	{
		super.setXMLNode(node);
		this.listQuestionTypeCode.clear();
		NodeList listChilds = node.getChildNodes();
		for (int i = 0; i < listChilds.getLength(); i++) {
			Node currentNode = listChilds.item(i);
			if ("roleCode".equals(currentNode.getNodeName())) {
				CD obj = new CD();
				obj.setXMLNode(currentNode);
				this.setRoleCode(obj);
			}
			if ("questionTypeCode".equals(currentNode.getNodeName())) {
				CD obj = new CD();
				obj.setXMLNode(currentNode);
				this.addQuestionTypeCode(obj);
			}
			if ("roleSequenceNumber".equals(currentNode.getNodeName())) {
				this.roleSequenceNumber = Integer.parseInt(currentNode.getAttributes().getNamedItem("value").getNodeValue());
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
		AnnotationRoleEntity oth = (AnnotationRoleEntity)other;
		if (!this.roleCode.isEqualTo(oth.roleCode)) {
			return false;
		}
		if (this.listQuestionTypeCode.size() != oth.listQuestionTypeCode.size()) {
			return false;
		}
		for (int i = 0; i < this.listQuestionTypeCode.size(); i++) {
			if (!this.listQuestionTypeCode.get(i).isEqualTo(oth.listQuestionTypeCode.get(i))) {
				return false;
			}
		}
		if (this.roleSequenceNumber == null ? oth.roleSequenceNumber != null : !this.roleSequenceNumber
				.equals(oth.roleSequenceNumber)) {
			return false;
		}
		return super.isEqualTo(other);
	}

	@Override
	public AnnotationRoleEntity getClone()
	{
		AnnotationRoleEntity res = new AnnotationRoleEntity();
		if (this.getRoleCode() != null) {
			res.setRoleCode(this.getRoleCode().getClone());
		}
		for (int i = 0; i < this.listQuestionTypeCode.size(); i++) {
			if (this.listQuestionTypeCode.get(i) != null) {
				res.addQuestionTypeCode(this.listQuestionTypeCode.get(i).getClone());
			}
		}
		if (this.getRoleSequenceNumber() != null) {
			res.setRoleSequenceNumber(this.getRoleSequenceNumber());
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
