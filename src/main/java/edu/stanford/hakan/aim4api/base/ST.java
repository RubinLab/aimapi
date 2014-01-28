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
public class ST implements IAimXMLOperations
{

	private List<ST> listTranslation = new ArrayList<ST>();
	private String value;
	private String language;
	private Boolean hasIsoAttributes;
	private String tagName;

	public ST()
	{
	}

	public ST(String value)
	{
		this.value = value;
	}

	public List<ST> getListTranslation()
	{
		return this.listTranslation;
	}

	public void setTranslation(List<ST> listTranslation)
	{
		this.listTranslation = listTranslation;
		for (int i = 0; i < this.listTranslation.size(); i++) {
			this.listTranslation.get(i).setTagName("translation");
		}
	}

	public void addTranslation(ST newTranslation)
	{
		newTranslation.setTagName("translation");
		this.listTranslation.add(newTranslation);
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	protected Boolean getHasIsoAttributes()
	{
		return hasIsoAttributes;
	}

	protected void setHasIsoAttributes(Boolean hasIsoAttributes)
	{
		this.hasIsoAttributes = hasIsoAttributes;
	}

	protected String getTagName()
	{
		return tagName;
	}

	protected void setTagName(String tagName)
	{
		this.tagName = tagName;
	}

	@Override
	public Node getXMLNode(Document doc) throws AimException
	{
		if (getTagName() == null || "".equals(getTagName())) {
			setTagName("ST");
		}
		if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
			this.setTagName("iso:".concat(this.getTagName()));
		}
		Element res = doc.createElement(getTagName());
		if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
			res.setAttribute("xmlns:iso", "uri:iso.org:21090");
		}
		if (this.listTranslation.size() > 0) {
			for (int i = 0; i < this.listTranslation.size(); i++) {
				res.appendChild(this.listTranslation.get(i).getXMLNode(doc));
			}
		}
		if (this.value != null) {
			res.setAttribute("value", this.value.toString());
		}
		if (this.language != null) {
			res.setAttribute("language", this.language.toString());
		}
		return res;
	}

	@Override
	public void setXMLNode(Node node)
	{
		this.listTranslation.clear();
		NamedNodeMap map = node.getAttributes();
		if (map.getNamedItem("value") != null) {
			this.value = map.getNamedItem("value").getNodeValue();
		}
		if (map.getNamedItem("language") != null) {
			this.language = map.getNamedItem("language").getNodeValue();
		}
		NodeList listChilds = node.getChildNodes();
		for (int i = 0; i < listChilds.getLength(); i++) {
			Node currentNode = listChilds.item(i);
			if ("translation".equals(currentNode.getNodeName()) || "iso:translation".equals(currentNode.getNodeName())) {
				ST obj = new ST();
				obj.setXMLNode(currentNode);
				this.addTranslation(obj);
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
		ST oth = (ST)other;
		if (this.listTranslation.size() != oth.listTranslation.size()) {
			return false;
		}
		for (int i = 0; i < this.listTranslation.size(); i++) {
			if (!this.listTranslation.get(i).isEqualTo(oth.listTranslation.get(i))) {
				return false;
			}
		}
		if (this.value == null ? oth.value != null : !this.value.equals(oth.value)) {
			return false;
		}
		if (this.language == null ? oth.language != null : !this.language.equals(oth.language)) {
			return false;
		}
		return true;
	}

	public ST getClone()
	{
		ST res = new ST();
		for (int i = 0; i < this.listTranslation.size(); i++) {
			if (this.listTranslation.get(i) != null) {
				res.addTranslation(this.listTranslation.get(i).getClone());
			}
		}
		if (this.getValue() != null) {
			res.setValue(this.getValue());
		}
		if (this.getLanguage() != null) {
			res.setLanguage(this.getLanguage());
		}
		if (this.getTagName() != null) {
			res.setTagName(this.getTagName());
		}
		return res;
	}
}
