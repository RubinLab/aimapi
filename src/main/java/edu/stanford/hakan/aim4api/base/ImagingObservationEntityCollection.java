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
public class ImagingObservationEntityCollection implements IAimXMLOperations
{

private final List<ImagingObservationEntity> listImagingObservationEntity = new ArrayList<ImagingObservationEntity>();

public void addImagingObservationEntity(ImagingObservationEntity newImagingObservationEntity)
{
this.listImagingObservationEntity.add(newImagingObservationEntity);
}

public List<ImagingObservationEntity> getImagingObservationEntityList()
{
return this.listImagingObservationEntity;
}

public int size()
{
return this.listImagingObservationEntity.size();
}

public ImagingObservationEntity get(int index)
{
if (index <= this.listImagingObservationEntity.size() - 1) {
return this.listImagingObservationEntity.get(index);
}
return null;
}

@Override
public Node getXMLNode(Document doc) throws AimException
{

Element imagingObservationEntityCollection = doc.createElement("imagingObservationEntityCollection");
for (int i = 0; i < this.listImagingObservationEntity.size(); i++) {
this.listImagingObservationEntity.get(i).setTagName("ImagingObservationEntity");
imagingObservationEntityCollection.appendChild(this.listImagingObservationEntity.get(i).getXMLNode(doc));
}
return imagingObservationEntityCollection;
}

@Override
public void setXMLNode(Node node)
{
this.listImagingObservationEntity.clear();
NodeList tempList = node.getChildNodes();
for (int j = 0; j < tempList.getLength(); j++) {
Node currentNode = tempList.item(j);
if ("ImagingObservationEntity".equals(currentNode.getNodeName())) {
NamedNodeMap map = currentNode.getAttributes();
if (map.getNamedItem("xsi:type") != null) {
String xsiType = map.getNamedItem("xsi:type").getNodeValue();

} else {
ImagingObservationEntity obj = new ImagingObservationEntity();
obj.setXMLNode(tempList.item(j));
this.addImagingObservationEntity(obj);
}
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
ImagingObservationEntityCollection oth = (ImagingObservationEntityCollection)other;
if (this.listImagingObservationEntity.size() != oth.listImagingObservationEntity.size()) {
return false;
}
for (int i = 0; i < this.listImagingObservationEntity.size(); i++) {
if (this.listImagingObservationEntity.get(i) == null ? oth.listImagingObservationEntity.get(i) != null : !this.listImagingObservationEntity.get(i).isEqualTo(oth.listImagingObservationEntity.get(i))) {
return false;
}
}
return true;
}

public ImagingObservationEntityCollection getClone()
{
ImagingObservationEntityCollection res = new ImagingObservationEntityCollection();
for (int i = 0; i < this.listImagingObservationEntity.size(); i++) {
if (this.listImagingObservationEntity.get(i) != null) {
res.addImagingObservationEntity(this.listImagingObservationEntity.get(i).getClone());
}
}
return res;
}
}
