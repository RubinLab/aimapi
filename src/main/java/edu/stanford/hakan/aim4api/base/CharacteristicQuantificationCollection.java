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
public class CharacteristicQuantificationCollection implements IAimXMLOperations
{

private final List<CharacteristicQuantification> listCharacteristicQuantification = new ArrayList<CharacteristicQuantification>();

public void addCharacteristicQuantification(CharacteristicQuantification newCharacteristicQuantification)
{
this.listCharacteristicQuantification.add(newCharacteristicQuantification);
}

public List<CharacteristicQuantification> getCharacteristicQuantificationList()
{
return this.listCharacteristicQuantification;
}

public int size()
{
return this.listCharacteristicQuantification.size();
}

public CharacteristicQuantification get(int index)
{
if (index <= this.listCharacteristicQuantification.size() - 1) {
return this.listCharacteristicQuantification.get(index);
}
return null;
}

@Override
public Node getXMLNode(Document doc) throws AimException
{

Element characteristicQuantificationCollection = doc.createElement("characteristicQuantificationCollection");
for (int i = 0; i < this.listCharacteristicQuantification.size(); i++) {
this.listCharacteristicQuantification.get(i).setTagName("CharacteristicQuantification");
characteristicQuantificationCollection.appendChild(this.listCharacteristicQuantification.get(i).getXMLNode(doc));
}
return characteristicQuantificationCollection;
}

@Override
public void setXMLNode(Node node)
{
this.listCharacteristicQuantification.clear();
NodeList tempList = node.getChildNodes();
for (int j = 0; j < tempList.getLength(); j++) {
Node currentNode = tempList.item(j);
if ("CharacteristicQuantification".equals(currentNode.getNodeName())) {
NamedNodeMap map = currentNode.getAttributes();
if (map.getNamedItem("xsi:type") != null) {
String xsiType = map.getNamedItem("xsi:type").getNodeValue();
if ("Quantile".equals(xsiType)) {
Quantile obj = new Quantile();
obj.setXMLNode(currentNode);
this.addCharacteristicQuantification(obj);
}
if ("NonQuantifiable".equals(xsiType)) {
NonQuantifiable obj = new NonQuantifiable();
obj.setXMLNode(currentNode);
this.addCharacteristicQuantification(obj);
}
if ("Scale".equals(xsiType)) {
Scale obj = new Scale();
obj.setXMLNode(currentNode);
this.addCharacteristicQuantification(obj);
}
if ("Numerical".equals(xsiType)) {
Numerical obj = new Numerical();
obj.setXMLNode(currentNode);
this.addCharacteristicQuantification(obj);
}
if ("Interval".equals(xsiType)) {
Interval obj = new Interval();
obj.setXMLNode(currentNode);
this.addCharacteristicQuantification(obj);
}

} else {
CharacteristicQuantification obj = new CharacteristicQuantification();
obj.setXMLNode(tempList.item(j));
this.addCharacteristicQuantification(obj);
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
CharacteristicQuantificationCollection oth = (CharacteristicQuantificationCollection)other;
if (this.listCharacteristicQuantification.size() != oth.listCharacteristicQuantification.size()) {
return false;
}
for (int i = 0; i < this.listCharacteristicQuantification.size(); i++) {
if (this.listCharacteristicQuantification.get(i) == null ? oth.listCharacteristicQuantification.get(i) != null : !this.listCharacteristicQuantification.get(i).isEqualTo(oth.listCharacteristicQuantification.get(i))) {
return false;
}
}
return true;
}

public CharacteristicQuantificationCollection getClone()
{
CharacteristicQuantificationCollection res = new CharacteristicQuantificationCollection();
for (int i = 0; i < this.listCharacteristicQuantification.size(); i++) {
if (this.listCharacteristicQuantification.get(i) != null) {
res.addCharacteristicQuantification(this.listCharacteristicQuantification.get(i).getClone());
}
}
return res;
}
}
