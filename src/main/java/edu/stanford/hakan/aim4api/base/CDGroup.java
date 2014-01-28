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
public class CDGroup implements IAimXMLOperations
{

	private List<CR> listQualifier = new ArrayList<CR>();
	private Boolean hasIsoAttributes;
	private String tagName;

	public List<CR> getListQualifier()
	{
		return this.listQualifier;
	}

	public void setQualifier(List<CR> listQualifier)
	{
		this.listQualifier = listQualifier;
		for (int i = 0; i < this.listQualifier.size(); i++) {
			this.listQualifier.get(i).setTagName("qualifier");
		}
	}

	public void addQualifier(CR newQualifier)
	{
		newQualifier.setTagName("qualifier");
		this.listQualifier.add(newQualifier);
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
			setTagName("CDGroup");
		}
		if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
			this.setTagName("iso:".concat(this.getTagName()));
		}
		Element res = doc.createElement(getTagName());
		if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
			res.setAttribute("xmlns:iso", "uri:iso.org:21090");
		}
		if (this.listQualifier.size() > 0) {
			for (int i = 0; i < this.listQualifier.size(); i++) {
				res.appendChild(this.listQualifier.get(i).getXMLNode(doc));
			}
		}
		return res;
	}

	@Override
	public void setXMLNode(Node node)
	{
		this.listQualifier.clear();
		NodeList listChilds = node.getChildNodes();
		for (int i = 0; i < listChilds.getLength(); i++) {
			Node currentNode = listChilds.item(i);
			if ("qualifier".equals(currentNode.getNodeName()) || "iso:qualifier".equals(currentNode.getNodeName())) {
				CR obj = new CR();
				obj.setXMLNode(currentNode);
				this.addQualifier(obj);
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
		CDGroup oth = (CDGroup)other;
		if (this.listQualifier.size() != oth.listQualifier.size()) {
			return false;
		}
		for (int i = 0; i < this.listQualifier.size(); i++) {
			if (!this.listQualifier.get(i).isEqualTo(oth.listQualifier.get(i))) {
				return false;
			}
		}
		return true;
	}

	public CDGroup getClone()
	{
		CDGroup res = new CDGroup();
		for (int i = 0; i < this.listQualifier.size(); i++) {
			if (this.listQualifier.get(i) != null) {
				res.addQualifier(this.listQualifier.get(i).getClone());
			}
		}
		if (this.getTagName() != null) {
			res.setTagName(this.getTagName());
		}
		return res;
	}
}
