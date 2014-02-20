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
public class CalculationEntityCollection implements IAimXMLOperations
{

private final List<CalculationEntity> listCalculationEntity = new ArrayList<CalculationEntity>();

public void addCalculationEntity(CalculationEntity newCalculationEntity)
{
this.listCalculationEntity.add(newCalculationEntity);
}

public List<CalculationEntity> getCalculationEntityList()
{
return this.listCalculationEntity;
}

public int size()
{
return this.listCalculationEntity.size();
}

public CalculationEntity get(int index)
{
if (index <= this.listCalculationEntity.size() - 1) {
return this.listCalculationEntity.get(index);
}
return null;
}

@Override
public Node getXMLNode(Document doc) throws AimException
{

Element calculationEntityCollection = doc.createElement("calculationEntityCollection");
for (int i = 0; i < this.listCalculationEntity.size(); i++) {
this.listCalculationEntity.get(i).setTagName("CalculationEntity");
calculationEntityCollection.appendChild(this.listCalculationEntity.get(i).getXMLNode(doc));
}
return calculationEntityCollection;
}

@Override
public void setXMLNode(Node node)
{
this.listCalculationEntity.clear();
NodeList tempList = node.getChildNodes();
for (int j = 0; j < tempList.getLength(); j++) {
Node currentNode = tempList.item(j);
if ("CalculationEntity".equals(currentNode.getNodeName())) {
NamedNodeMap map = currentNode.getAttributes();
if (map.getNamedItem("xsi:type") != null) {
String xsiType = map.getNamedItem("xsi:type").getNodeValue();

} else {
CalculationEntity obj = new CalculationEntity();
obj.setXMLNode(tempList.item(j));
this.addCalculationEntity(obj);
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
CalculationEntityCollection oth = (CalculationEntityCollection)other;
if (this.listCalculationEntity.size() != oth.listCalculationEntity.size()) {
return false;
}
for (int i = 0; i < this.listCalculationEntity.size(); i++) {
if (this.listCalculationEntity.get(i) == null ? oth.listCalculationEntity.get(i) != null : !this.listCalculationEntity.get(i).isEqualTo(oth.listCalculationEntity.get(i))) {
return false;
}
}
return true;
}

public CalculationEntityCollection getClone()
{
CalculationEntityCollection res = new CalculationEntityCollection();
for (int i = 0; i < this.listCalculationEntity.size(); i++) {
if (this.listCalculationEntity.get(i) != null) {
res.addCalculationEntity(this.listCalculationEntity.get(i).getClone());
}
}
return res;
}
}
