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
public class CD implements IAimXMLOperations
{

	private ST displayName;
	private ED_Text originalText;
	private List<CR> listQualifier = new ArrayList<CR>();
	private List<CDGroup> listGroup = new ArrayList<CDGroup>();
	private List<CD> listTranslation = new ArrayList<CD>();
	private XReference source;
	private String code;
	private String codeSystem;
	private String codeSystemName;
	private String codeSystemVersion;
	private String valueSet;
	private String valueSetVersion;
	private String id;
	private Enumerations.set_CodingRationale codingRationale;
	private Boolean hasIsoAttributes;
	private String tagName;

	public ST getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(ST displayName)
	{
		displayName.setTagName("displayName");
		displayName.setHasIsoAttributes(true);
		this.displayName = displayName;
	}

	public ED_Text getOriginalText()
	{
		return originalText;
	}

	public void setOriginalText(ED_Text originalText)
	{
		originalText.setTagName("originalText");
		originalText.setHasIsoAttributes(true);
		this.originalText = originalText;
	}

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

	public List<CDGroup> getListGroup()
	{
		return this.listGroup;
	}

	public void setGroup(List<CDGroup> listGroup)
	{
		this.listGroup = listGroup;
		for (int i = 0; i < this.listGroup.size(); i++) {
			this.listGroup.get(i).setTagName("group");
		}
	}

	public void addGroup(CDGroup newGroup)
	{
		newGroup.setTagName("group");
		this.listGroup.add(newGroup);
	}

	public List<CD> getListTranslation()
	{
		return this.listTranslation;
	}

	public void setTranslation(List<CD> listTranslation)
	{
		this.listTranslation = listTranslation;
		for (int i = 0; i < this.listTranslation.size(); i++) {
			this.listTranslation.get(i).setTagName("translation");
		}
	}

	public void addTranslation(CD newTranslation)
	{
		newTranslation.setTagName("translation");
		this.listTranslation.add(newTranslation);
	}

	public XReference getSource()
	{
		return source;
	}

	public void setSource(XReference source)
	{
		source.setTagName("source");
		source.setHasIsoAttributes(true);
		this.source = source;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getCodeSystem()
	{
		return codeSystem;
	}

	public void setCodeSystem(String codeSystem)
	{
		this.codeSystem = codeSystem;
	}

	public String getCodeSystemName()
	{
		return codeSystemName;
	}

	public void setCodeSystemName(String codeSystemName)
	{
		this.codeSystemName = codeSystemName;
	}

	public String getCodeSystemVersion()
	{
		return codeSystemVersion;
	}

	public void setCodeSystemVersion(String codeSystemVersion)
	{
		this.codeSystemVersion = codeSystemVersion;
	}

	public String getValueSet()
	{
		return valueSet;
	}

	public void setValueSet(String valueSet)
	{
		this.valueSet = valueSet;
	}

	public String getValueSetVersion()
	{
		return valueSetVersion;
	}

	public void setValueSetVersion(String valueSetVersion)
	{
		this.valueSetVersion = valueSetVersion;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Enumerations.set_CodingRationale getCodingRationale()
	{
		return codingRationale;
	}

	public void setCodingRationale(Enumerations.set_CodingRationale codingRationale)
	{
		this.codingRationale = codingRationale;
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
			setTagName("CD");
		}
		if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
			this.setTagName("iso:".concat(this.getTagName()));
		}
		Element res = doc.createElement(getTagName());
		if (this.getHasIsoAttributes() != null && this.getHasIsoAttributes()) {
			res.setAttribute("xmlns:iso", "uri:iso.org:21090");
		}
		if (this.displayName != null) {
			res.appendChild(this.displayName.getXMLNode(doc));
		}
		if (this.originalText != null) {
			res.appendChild(this.originalText.getXMLNode(doc));
		}
		if (this.listQualifier.size() > 0) {
			for (int i = 0; i < this.listQualifier.size(); i++) {
				res.appendChild(this.listQualifier.get(i).getXMLNode(doc));
			}
		}
		if (this.listGroup.size() > 0) {
			for (int i = 0; i < this.listGroup.size(); i++) {
				res.appendChild(this.listGroup.get(i).getXMLNode(doc));
			}
		}
		if (this.listTranslation.size() > 0) {
			for (int i = 0; i < this.listTranslation.size(); i++) {
				res.appendChild(this.listTranslation.get(i).getXMLNode(doc));
			}
		}
		if (this.source != null) {
			res.appendChild(this.source.getXMLNode(doc));
		}
		if (this.code != null) {
			res.setAttribute("code", this.code.toString());
		}
		if (this.codeSystem != null) {
			res.setAttribute("codeSystem", this.codeSystem.toString());
		}
		if (this.codeSystemName != null) {
			res.setAttribute("codeSystemName", this.codeSystemName.toString());
		}
		if (this.codeSystemVersion != null) {
			res.setAttribute("codeSystemVersion", this.codeSystemVersion.toString());
		}
		if (this.valueSet != null) {
			res.setAttribute("valueSet", this.valueSet.toString());
		}
		if (this.valueSetVersion != null) {
			res.setAttribute("valueSetVersion", this.valueSetVersion.toString());
		}
		if (this.id != null) {
			res.setAttribute("id", this.id.toString());
		}
		if (this.codingRationale != null) {
			res.setAttribute("codingRationale", this.codingRationale.toString());
		}
		return res;
	}

	@Override
	public void setXMLNode(Node node)
	{
		this.listQualifier.clear();
		this.listGroup.clear();
		this.listTranslation.clear();
		NamedNodeMap map = node.getAttributes();
		if (map.getNamedItem("code") != null) {
			this.code = map.getNamedItem("code").getNodeValue();
		}
		if (map.getNamedItem("codeSystem") != null) {
			this.codeSystem = map.getNamedItem("codeSystem").getNodeValue();
		}
		if (map.getNamedItem("codeSystemName") != null) {
			this.codeSystemName = map.getNamedItem("codeSystemName").getNodeValue();
		}
		if (map.getNamedItem("codeSystemVersion") != null) {
			this.codeSystemVersion = map.getNamedItem("codeSystemVersion").getNodeValue();
		}
		if (map.getNamedItem("valueSet") != null) {
			this.valueSet = map.getNamedItem("valueSet").getNodeValue();
		}
		if (map.getNamedItem("valueSetVersion") != null) {
			this.valueSetVersion = map.getNamedItem("valueSetVersion").getNodeValue();
		}
		if (map.getNamedItem("id") != null) {
			this.id = map.getNamedItem("id").getNodeValue();
		}
		if (map.getNamedItem("codingRationale") != null) {
			this.codingRationale = Enumerations.set_CodingRationale.valueOf(map.getNamedItem("codingRationale")
					.getNodeValue());
		}
		NodeList listChilds = node.getChildNodes();
		for (int i = 0; i < listChilds.getLength(); i++) {
			Node currentNode = listChilds.item(i);
			if ("displayName".equals(currentNode.getNodeName()) || "iso:displayName".equals(currentNode.getNodeName())) {
				ST obj = new ST();
				obj.setXMLNode(currentNode);
				this.setDisplayName(obj);
			}
			if ("originalText".equals(currentNode.getNodeName()) || "iso:originalText".equals(currentNode.getNodeName())) {
				ED_Text obj = new ED_Text();
				obj.setXMLNode(currentNode);
				this.setOriginalText(obj);
			}
			if ("qualifier".equals(currentNode.getNodeName()) || "iso:qualifier".equals(currentNode.getNodeName())) {
				CR obj = new CR();
				obj.setXMLNode(currentNode);
				this.addQualifier(obj);
			}
			if ("group".equals(currentNode.getNodeName()) || "iso:group".equals(currentNode.getNodeName())) {
				CDGroup obj = new CDGroup();
				obj.setXMLNode(currentNode);
				this.addGroup(obj);
			}
			if ("translation".equals(currentNode.getNodeName()) || "iso:translation".equals(currentNode.getNodeName())) {
				CD obj = new CD();
				obj.setXMLNode(currentNode);
				this.addTranslation(obj);
			}
			if ("source".equals(currentNode.getNodeName()) || "iso:source".equals(currentNode.getNodeName())) {
				XReference obj = new XReference();
				obj.setXMLNode(currentNode);
				this.setSource(obj);
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
		CD oth = (CD)other;
		if (!this.displayName.isEqualTo(oth.displayName)) {
			return false;
		}
		if (!this.originalText.isEqualTo(oth.originalText)) {
			return false;
		}
		if (this.listQualifier.size() != oth.listQualifier.size()) {
			return false;
		}
		for (int i = 0; i < this.listQualifier.size(); i++) {
			if (!this.listQualifier.get(i).isEqualTo(oth.listQualifier.get(i))) {
				return false;
			}
		}
		if (this.listGroup.size() != oth.listGroup.size()) {
			return false;
		}
		for (int i = 0; i < this.listGroup.size(); i++) {
			if (!this.listGroup.get(i).isEqualTo(oth.listGroup.get(i))) {
				return false;
			}
		}
		if (this.listTranslation.size() != oth.listTranslation.size()) {
			return false;
		}
		for (int i = 0; i < this.listTranslation.size(); i++) {
			if (!this.listTranslation.get(i).isEqualTo(oth.listTranslation.get(i))) {
				return false;
			}
		}
		if (!this.source.isEqualTo(oth.source)) {
			return false;
		}
		if (this.code == null ? oth.code != null : !this.code.equals(oth.code)) {
			return false;
		}
		if (this.codeSystem == null ? oth.codeSystem != null : !this.codeSystem.equals(oth.codeSystem)) {
			return false;
		}
		if (this.codeSystemName == null ? oth.codeSystemName != null : !this.codeSystemName.equals(oth.codeSystemName)) {
			return false;
		}
		if (this.codeSystemVersion == null ? oth.codeSystemVersion != null : !this.codeSystemVersion
				.equals(oth.codeSystemVersion)) {
			return false;
		}
		if (this.valueSet == null ? oth.valueSet != null : !this.valueSet.equals(oth.valueSet)) {
			return false;
		}
		if (this.valueSetVersion == null ? oth.valueSetVersion != null : !this.valueSetVersion.equals(oth.valueSetVersion)) {
			return false;
		}
		if (this.id == null ? oth.id != null : !this.id.equals(oth.id)) {
			return false;
		}
		if (this.codingRationale == null ? oth.codingRationale != null : !this.codingRationale.equals(oth.codingRationale)) {
			return false;
		}
		return true;
	}

	public CD getClone()
	{
		CD res = new CD();
		if (this.getDisplayName() != null) {
			res.setDisplayName(this.getDisplayName().getClone());
		}
		if (this.getOriginalText() != null) {
			res.setOriginalText(this.getOriginalText().getClone());
		}
		for (int i = 0; i < this.listQualifier.size(); i++) {
			if (this.listQualifier.get(i) != null) {
				res.addQualifier(this.listQualifier.get(i).getClone());
			}
		}
		for (int i = 0; i < this.listGroup.size(); i++) {
			if (this.listGroup.get(i) != null) {
				res.addGroup(this.listGroup.get(i).getClone());
			}
		}
		for (int i = 0; i < this.listTranslation.size(); i++) {
			if (this.listTranslation.get(i) != null) {
				res.addTranslation(this.listTranslation.get(i).getClone());
			}
		}
		if (this.getSource() != null) {
			res.setSource(this.getSource().getClone());
		}
		if (this.getCode() != null) {
			res.setCode(this.getCode());
		}
		if (this.getCodeSystem() != null) {
			res.setCodeSystem(this.getCodeSystem());
		}
		if (this.getCodeSystemName() != null) {
			res.setCodeSystemName(this.getCodeSystemName());
		}
		if (this.getCodeSystemVersion() != null) {
			res.setCodeSystemVersion(this.getCodeSystemVersion());
		}
		if (this.getValueSet() != null) {
			res.setValueSet(this.getValueSet());
		}
		if (this.getValueSetVersion() != null) {
			res.setValueSetVersion(this.getValueSetVersion());
		}
		if (this.getId() != null) {
			res.setId(this.getId());
		}
		if (this.getCodingRationale() != null) {
			res.setCodingRationale(this.getCodingRationale());
		}
		if (this.getTagName() != null) {
			res.setTagName(this.getTagName());
		}
		return res;
	}
}
