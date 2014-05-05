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
public class ImageReferenceEntityCollection implements IAimXMLOperations
{

private List<ImageReferenceEntity> listImageReferenceEntity = new ArrayList<ImageReferenceEntity>();

public void addImageReferenceEntity(ImageReferenceEntity newImageReferenceEntity)
{
this.listImageReferenceEntity.add(newImageReferenceEntity);
}

public List<ImageReferenceEntity> getImageReferenceEntityList()
{
return this.listImageReferenceEntity;
}

public int size()
{
return this.listImageReferenceEntity.size();
}

public ImageReferenceEntity get(int index)
{
if (index <= this.listImageReferenceEntity.size() - 1) {
return this.listImageReferenceEntity.get(index);
}
return null;
}

@Override
public Node getXMLNode(Document doc) throws AimException
{

Element imageReferenceEntityCollection = doc.createElement("imageReferenceEntityCollection");
for (int i = 0; i < this.listImageReferenceEntity.size(); i++) {
this.listImageReferenceEntity.get(i).setTagName("ImageReferenceEntity");
imageReferenceEntityCollection.appendChild(this.listImageReferenceEntity.get(i).getXMLNode(doc));
}
return imageReferenceEntityCollection;
}

@Override
public void setXMLNode(Node node)
{
this.listImageReferenceEntity.clear();
NodeList tempList = node.getChildNodes();
for (int j = 0; j < tempList.getLength(); j++) {
Node currentNode = tempList.item(j);
if ("ImageReferenceEntity".equals(currentNode.getNodeName())) {
NamedNodeMap map = currentNode.getAttributes();
if (map.getNamedItem("xsi:type") != null) {
String xsiType = map.getNamedItem("xsi:type").getNodeValue();
if ("DicomImageReferenceEntity".equals(xsiType)) {
DicomImageReferenceEntity obj = new DicomImageReferenceEntity();
obj.setXMLNode(currentNode);
this.addImageReferenceEntity(obj);
}
if ("UriImageReferenceEntity".equals(xsiType)) {
UriImageReferenceEntity obj = new UriImageReferenceEntity();
obj.setXMLNode(currentNode);
this.addImageReferenceEntity(obj);
}

} else {
ImageReferenceEntity obj = new ImageReferenceEntity();
obj.setXMLNode(tempList.item(j));
this.addImageReferenceEntity(obj);
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
ImageReferenceEntityCollection oth = (ImageReferenceEntityCollection)other;
if (this.listImageReferenceEntity.size() != oth.listImageReferenceEntity.size()) {
return false;
}
for (int i = 0; i < this.listImageReferenceEntity.size(); i++) {
if (this.listImageReferenceEntity.get(i) == null ? oth.listImageReferenceEntity.get(i) != null : !this.listImageReferenceEntity.get(i).isEqualTo(oth.listImageReferenceEntity.get(i))) {
return false;
}
}
return true;
}

public ImageReferenceEntityCollection getClone()
{
ImageReferenceEntityCollection res = new ImageReferenceEntityCollection();
for (int i = 0; i < this.listImageReferenceEntity.size(); i++) {
if (this.listImageReferenceEntity.get(i) != null) {
res.addImageReferenceEntity(this.listImageReferenceEntity.get(i).getClone());
}
}
return res;
}
}
