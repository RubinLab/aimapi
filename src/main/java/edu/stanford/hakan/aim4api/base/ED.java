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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author localadmin
 */
public class ED implements IAimXMLOperations
{

	private Object data;
	private Object xml;
	private TEL reference;
	private Object integrityCheck;
	private ED thumbnail;
	private List<ED> listTranslation = new ArrayList<ED>();
	private String value;
	private String mediaType;
	private String charset;
	private String language;
	private Enumerations.Compression compression;
	private Enumerations.IntegrityCheckAlgorithm integrityCheckAlgorithm;
	private Boolean hasIsoAttributes;
	private String tagName;

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public Object getXml()
	{
		return xml;
	}

	public void setXml(Object xml)
	{
		this.xml = xml;
	}

	public TEL getReference()
	{
		return reference;
	}

	public void setReference(TEL reference)
	{
		reference.setTagName("reference");
		reference.setHasIsoAttributes(true);
		this.reference = reference;
	}

	public Object getIntegrityCheck()
	{
		return integrityCheck;
	}

	public void setIntegrityCheck(Object integrityCheck)
	{
		this.integrityCheck = integrityCheck;
	}

	public ED getThumbnail()
	{
		return thumbnail;
	}

	public void setThumbnail(ED thumbnail)
	{
		thumbnail.setTagName("thumbnail");
		thumbnail.setHasIsoAttributes(true);
		this.thumbnail = thumbnail;
	}

	public List<ED> getListTranslation()
	{
		return this.listTranslation;
	}

	public void setTranslation(List<ED> listTranslation)
	{
		this.listTranslation = listTranslation;
		for (int i = 0; i < this.listTranslation.size(); i++) {
			this.listTranslation.get(i).setTagName("translation");
		}
	}

	public void addTranslation(ED newTranslation)
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

	public String getMediaType()
	{
		return mediaType;
	}

	public void setMediaType(String mediaType)
	{
		this.mediaType = mediaType;
	}

	public String getCharset()
	{
		return charset;
	}

	public void setCharset(String charset)
	{
		this.charset = charset;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	public Enumerations.Compression getCompression()
	{
		return compression;
	}

	public void setCompression(Enumerations.Compression compression)
	{
		this.compression = compression;
	}

	public Enumerations.IntegrityCheckAlgorithm getIntegrityCheckAlgorithm()
	{
		return integrityCheckAlgorithm;
	}

	public void setIntegrityCheckAlgorithm(Enumerations.IntegrityCheckAlgorithm integrityCheckAlgorithm)
	{
		this.integrityCheckAlgorithm = integrityCheckAlgorithm;
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
			setTagName("ED");
		}
		if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
			this.setTagName("iso:".concat(this.getTagName()));
		}
		Element res = doc.createElement(getTagName());
		if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
			res.setAttribute("xmlns:iso", "uri:iso.org:21090");
		}
		if (this.data != null) {
			Element el_data = doc.createElement("data");
			el_data.setAttribute("value", this.data.toString());
			res.appendChild(el_data);
		}
		if (this.xml != null) {
			Element el_xml = doc.createElement("xml");
			el_xml.setAttribute("value", this.xml.toString());
			res.appendChild(el_xml);
		}
		if (this.reference != null) {
			res.appendChild(this.reference.getXMLNode(doc));
		}
		if (this.integrityCheck != null) {
			Element el_integrityCheck = doc.createElement("integrityCheck");
			el_integrityCheck.setAttribute("value", this.integrityCheck.toString());
			res.appendChild(el_integrityCheck);
		}
		if (this.thumbnail != null) {
			res.appendChild(this.thumbnail.getXMLNode(doc));
		}
		if (this.listTranslation.size() > 0) {
			for (int i = 0; i < this.listTranslation.size(); i++) {
				res.appendChild(this.listTranslation.get(i).getXMLNode(doc));
			}
		}
		if (this.value != null) {
			res.setAttribute("value", this.value.toString());
		}
		if (this.mediaType != null) {
			res.setAttribute("mediaType", this.mediaType.toString());
		}
		if (this.charset != null) {
			res.setAttribute("charset", this.charset.toString());
		}
		if (this.language != null) {
			res.setAttribute("language", this.language.toString());
		}
		if (this.compression != null) {
			res.setAttribute("compression", this.compression.toString());
		}
		if (this.integrityCheckAlgorithm != null) {
			res.setAttribute("integrityCheckAlgorithm", this.integrityCheckAlgorithm.toString());
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
		if (map.getNamedItem("mediaType") != null) {
			this.mediaType = map.getNamedItem("mediaType").getNodeValue();
		}
		if (map.getNamedItem("charset") != null) {
			this.charset = map.getNamedItem("charset").getNodeValue();
		}
		if (map.getNamedItem("language") != null) {
			this.language = map.getNamedItem("language").getNodeValue();
		}
		if (map.getNamedItem("compression") != null) {
			this.compression = Enumerations.Compression.valueOf(map.getNamedItem("compression").getNodeValue());
		}
		if (map.getNamedItem("integrityCheckAlgorithm") != null) {
			this.integrityCheckAlgorithm = Enumerations.IntegrityCheckAlgorithm.valueOf(map.getNamedItem(
					"integrityCheckAlgorithm").getNodeValue());
		}
		NodeList listChilds = node.getChildNodes();
		for (int i = 0; i < listChilds.getLength(); i++) {
			Node currentNode = listChilds.item(i);
			if ("data".equals(currentNode.getNodeName()) || "iso:data".equals(currentNode.getNodeName())) {
				this.data = currentNode.getAttributes().getNamedItem("value").getNodeValue();
			}
			if ("xml".equals(currentNode.getNodeName()) || "iso:xml".equals(currentNode.getNodeName())) {
				this.xml = currentNode.getAttributes().getNamedItem("value").getNodeValue();
			}
			if ("reference".equals(currentNode.getNodeName()) || "iso:reference".equals(currentNode.getNodeName())) {
				NamedNodeMap mapXsiType = currentNode.getAttributes();
				if (mapXsiType.getNamedItem("xsi:type") != null) {
					String xsiType = mapXsiType.getNamedItem("xsi:type").getNodeValue();
				}
			}
			if ("integrityCheck".equals(currentNode.getNodeName()) || "iso:integrityCheck".equals(currentNode.getNodeName())) {
				this.integrityCheck = currentNode.getAttributes().getNamedItem("value").getNodeValue();
			}
			if ("thumbnail".equals(currentNode.getNodeName()) || "iso:thumbnail".equals(currentNode.getNodeName())) {
				ED obj = new ED();
				obj.setXMLNode(currentNode);
				this.setThumbnail(obj);
			}
			if ("translation".equals(currentNode.getNodeName()) || "iso:translation".equals(currentNode.getNodeName())) {
				ED obj = new ED();
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
		ED oth = (ED)other;
		if (this.data == null ? oth.data != null : !this.data.equals(oth.data)) {
			return false;
		}
		if (this.xml == null ? oth.xml != null : !this.xml.equals(oth.xml)) {
			return false;
		}
		if (!this.reference.isEqualTo(oth.reference)) {
			return false;
		}
		if (this.integrityCheck == null ? oth.integrityCheck != null : !this.integrityCheck.equals(oth.integrityCheck)) {
			return false;
		}
		if (!this.thumbnail.isEqualTo(oth.thumbnail)) {
			return false;
		}
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
		if (this.mediaType == null ? oth.mediaType != null : !this.mediaType.equals(oth.mediaType)) {
			return false;
		}
		if (this.charset == null ? oth.charset != null : !this.charset.equals(oth.charset)) {
			return false;
		}
		if (this.language == null ? oth.language != null : !this.language.equals(oth.language)) {
			return false;
		}
		if (this.compression == null ? oth.compression != null : !this.compression.equals(oth.compression)) {
			return false;
		}
		if (this.integrityCheckAlgorithm == null ? oth.integrityCheckAlgorithm != null : !this.integrityCheckAlgorithm
				.equals(oth.integrityCheckAlgorithm)) {
			return false;
		}
		return true;
	}

	public ED getClone()
	{
		ED res = new ED();
		if (this.getData() != null) {
			res.setData(this.getData());
		}
		if (this.getXml() != null) {
			res.setXml(this.getXml());
		}
		if (this.getReference() != null) {
			res.setReference(this.getReference().getClone());
		}
		if (this.getIntegrityCheck() != null) {
			res.setIntegrityCheck(this.getIntegrityCheck());
		}
		if (this.getThumbnail() != null) {
			res.setThumbnail(this.getThumbnail().getClone());
		}
		for (int i = 0; i < this.listTranslation.size(); i++) {
			if (this.listTranslation.get(i) != null) {
				res.addTranslation(this.listTranslation.get(i).getClone());
			}
		}
		if (this.getValue() != null) {
			res.setValue(this.getValue());
		}
		if (this.getMediaType() != null) {
			res.setMediaType(this.getMediaType());
		}
		if (this.getCharset() != null) {
			res.setCharset(this.getCharset());
		}
		if (this.getLanguage() != null) {
			res.setLanguage(this.getLanguage());
		}
		if (this.getCompression() != null) {
			res.setCompression(this.getCompression());
		}
		if (this.getIntegrityCheckAlgorithm() != null) {
			res.setIntegrityCheckAlgorithm(this.getIntegrityCheckAlgorithm());
		}
		if (this.getTagName() != null) {
			res.setTagName(this.getTagName());
		}
		return res;
	}
}
