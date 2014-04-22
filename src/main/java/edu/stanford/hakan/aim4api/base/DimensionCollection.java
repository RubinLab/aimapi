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
public class DimensionCollection implements IAimXMLOperations
{

private final List<Dimension> listDimension = new ArrayList<Dimension>();

public void addDimension(Dimension newDimension)
{
this.listDimension.add(newDimension);
}

public List<Dimension> getDimensionList()
{
return this.listDimension;
}

public int size()
{
return this.listDimension.size();
}

public Dimension get(int index)
{
if (index <= this.listDimension.size() - 1) {
return this.listDimension.get(index);
}
return null;
}

@Override
public Node getXMLNode(Document doc) throws AimException
{

Element dimensionCollection = doc.createElement("dimensionCollection");
for (int i = 0; i < this.listDimension.size(); i++) {
this.listDimension.get(i).setTagName("Dimension");
dimensionCollection.appendChild(this.listDimension.get(i).getXMLNode(doc));
}
return dimensionCollection;
}

@Override
public void setXMLNode(Node node)
{
this.listDimension.clear();
NodeList tempList = node.getChildNodes();
for (int j = 0; j < tempList.getLength(); j++) {
Node currentNode = tempList.item(j);
if ("Dimension".equals(currentNode.getNodeName())) {
NamedNodeMap map = currentNode.getAttributes();
if (map.getNamedItem("xsi:type") != null) {
String xsiType = map.getNamedItem("xsi:type").getNodeValue();

} else {
Dimension obj = new Dimension();
obj.setXMLNode(tempList.item(j));
this.addDimension(obj);
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
DimensionCollection oth = (DimensionCollection)other;
if (this.listDimension.size() != oth.listDimension.size()) {
return false;
}
for (int i = 0; i < this.listDimension.size(); i++) {
if (this.listDimension.get(i) == null ? oth.listDimension.get(i) != null : !this.listDimension.get(i).isEqualTo(oth.listDimension.get(i))) {
return false;
}
}
return true;
}

public DimensionCollection getClone()
{
DimensionCollection res = new DimensionCollection();
for (int i = 0; i < this.listDimension.size(); i++) {
if (this.listDimension.get(i) != null) {
res.addDimension(this.listDimension.get(i).getClone());
}
}
return res;
}
}
