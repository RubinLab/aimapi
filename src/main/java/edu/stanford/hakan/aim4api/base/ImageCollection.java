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
public class ImageCollection implements IAimXMLOperations
{

private final List<Image> listImage = new ArrayList<Image>();

public void addImage(Image newImage)
{
this.listImage.add(newImage);
}

public List<Image> getImageList()
{
return this.listImage;
}

public int size()
{
return this.listImage.size();
}

public Image get(int index)
{
if (index <= this.listImage.size() - 1) {
return this.listImage.get(index);
}
return null;
}

@Override
public Node getXMLNode(Document doc) throws AimException
{

Element imageCollection = doc.createElement("imageCollection");
for (int i = 0; i < this.listImage.size(); i++) {
this.listImage.get(i).setTagName("Image");
imageCollection.appendChild(this.listImage.get(i).getXMLNode(doc));
}
return imageCollection;
}

@Override
public void setXMLNode(Node node)
{
this.listImage.clear();
NodeList tempList = node.getChildNodes();
for (int j = 0; j < tempList.getLength(); j++) {
Node currentNode = tempList.item(j);
if ("Image".equals(currentNode.getNodeName())) {
NamedNodeMap map = currentNode.getAttributes();
if (map.getNamedItem("xsi:type") != null) {
String xsiType = map.getNamedItem("xsi:type").getNodeValue();

} else {
Image obj = new Image();
obj.setXMLNode(tempList.item(j));
this.addImage(obj);
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
ImageCollection oth = (ImageCollection)other;
if (this.listImage.size() != oth.listImage.size()) {
return false;
}
for (int i = 0; i < this.listImage.size(); i++) {
if (this.listImage.get(i) == null ? oth.listImage.get(i) != null : !this.listImage.get(i).isEqualTo(oth.listImage.get(i))) {
return false;
}
}
return true;
}

public ImageCollection getClone()
{
ImageCollection res = new ImageCollection();
for (int i = 0; i < this.listImage.size(); i++) {
if (this.listImage.get(i) != null) {
res.addImage(this.listImage.get(i).getClone());
}
}
return res;
}
}
