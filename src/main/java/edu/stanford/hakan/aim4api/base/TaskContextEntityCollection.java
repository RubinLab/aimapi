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
public class TaskContextEntityCollection implements IAimXMLOperations
{

private final List<TaskContextEntity> listTaskContextEntity = new ArrayList<TaskContextEntity>();

public void addTaskContextEntity(TaskContextEntity newTaskContextEntity)
{
this.listTaskContextEntity.add(newTaskContextEntity);
}

public List<TaskContextEntity> getTaskContextEntityList()
{
return this.listTaskContextEntity;
}

public int size()
{
return this.listTaskContextEntity.size();
}

public TaskContextEntity get(int index)
{
if (index <= this.listTaskContextEntity.size() - 1) {
return this.listTaskContextEntity.get(index);
}
return null;
}

@Override
public Node getXMLNode(Document doc) throws AimException
{

Element taskContextEntityCollection = doc.createElement("taskContextEntityCollection");
for (int i = 0; i < this.listTaskContextEntity.size(); i++) {
this.listTaskContextEntity.get(i).setTagName("TaskContextEntity");
taskContextEntityCollection.appendChild(this.listTaskContextEntity.get(i).getXMLNode(doc));
}
return taskContextEntityCollection;
}

@Override
public void setXMLNode(Node node)
{
this.listTaskContextEntity.clear();
NodeList tempList = node.getChildNodes();
for (int j = 0; j < tempList.getLength(); j++) {
Node currentNode = tempList.item(j);
if ("TaskContextEntity".equals(currentNode.getNodeName())) {
NamedNodeMap map = currentNode.getAttributes();
if (map.getNamedItem("xsi:type") != null) {
String xsiType = map.getNamedItem("xsi:type").getNodeValue();

} else {
TaskContextEntity obj = new TaskContextEntity();
obj.setXMLNode(tempList.item(j));
this.addTaskContextEntity(obj);
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
TaskContextEntityCollection oth = (TaskContextEntityCollection)other;
if (this.listTaskContextEntity.size() != oth.listTaskContextEntity.size()) {
return false;
}
for (int i = 0; i < this.listTaskContextEntity.size(); i++) {
if (this.listTaskContextEntity.get(i) == null ? oth.listTaskContextEntity.get(i) != null : !this.listTaskContextEntity.get(i).isEqualTo(oth.listTaskContextEntity.get(i))) {
return false;
}
}
return true;
}

public TaskContextEntityCollection getClone()
{
TaskContextEntityCollection res = new TaskContextEntityCollection();
for (int i = 0; i < this.listTaskContextEntity.size(); i++) {
if (this.listTaskContextEntity.get(i) != null) {
res.addTaskContextEntity(this.listTaskContextEntity.get(i).getClone());
}
}
return res;
}
}
