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
public class SegmentationEntityCollection implements IAimXMLOperations
{

private List<SegmentationEntity> listSegmentationEntity = new ArrayList<SegmentationEntity>();

public void addSegmentationEntity(SegmentationEntity newSegmentationEntity)
{
this.listSegmentationEntity.add(newSegmentationEntity);
}

public List<SegmentationEntity> getSegmentationEntityList()
{
return this.listSegmentationEntity;
}

public int size()
{
return this.listSegmentationEntity.size();
}

public SegmentationEntity get(int index)
{
if (index <= this.listSegmentationEntity.size() - 1) {
return this.listSegmentationEntity.get(index);
}
return null;
}

@Override
public Node getXMLNode(Document doc) throws AimException
{

Element segmentationEntityCollection = doc.createElement("segmentationEntityCollection");
for (int i = 0; i < this.listSegmentationEntity.size(); i++) {
this.listSegmentationEntity.get(i).setTagName("SegmentationEntity");
segmentationEntityCollection.appendChild(this.listSegmentationEntity.get(i).getXMLNode(doc));
}
return segmentationEntityCollection;
}

@Override
public void setXMLNode(Node node)
{
this.listSegmentationEntity.clear();
NodeList tempList = node.getChildNodes();
for (int j = 0; j < tempList.getLength(); j++) {
Node currentNode = tempList.item(j);
if ("SegmentationEntity".equals(currentNode.getNodeName())) {
NamedNodeMap map = currentNode.getAttributes();
if (map.getNamedItem("xsi:type") != null) {
String xsiType = map.getNamedItem("xsi:type").getNodeValue();
if ("DicomSegmentationEntity".equals(xsiType)) {
DicomSegmentationEntity obj = new DicomSegmentationEntity();
obj.setXMLNode(currentNode);
this.addSegmentationEntity(obj);
}

} else {
SegmentationEntity obj = new SegmentationEntity();
obj.setXMLNode(tempList.item(j));
this.addSegmentationEntity(obj);
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
SegmentationEntityCollection oth = (SegmentationEntityCollection)other;
if (this.listSegmentationEntity.size() != oth.listSegmentationEntity.size()) {
return false;
}
for (int i = 0; i < this.listSegmentationEntity.size(); i++) {
if (this.listSegmentationEntity.get(i) == null ? oth.listSegmentationEntity.get(i) != null : !this.listSegmentationEntity.get(i).isEqualTo(oth.listSegmentationEntity.get(i))) {
return false;
}
}
return true;
}

public SegmentationEntityCollection getClone()
{
SegmentationEntityCollection res = new SegmentationEntityCollection();
for (int i = 0; i < this.listSegmentationEntity.size(); i++) {
if (this.listSegmentationEntity.get(i) != null) {
res.addSegmentationEntity(this.listSegmentationEntity.get(i).getClone());
}
}
return res;
}
}
