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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author localadmin
 */
public class TaskContextEntity extends Entity {

    public TaskContextEntity() {
        setXsiType("TaskContextEntity");
    }
    private II worklistTaskUid;
    private ST worklistTaskName;
    private ST worklistTaskDescription;
    private CD worklistTaskCategory;
    private CD worklistTaskLevel;
    private CD worklistTaskType;
    private CD worklistTaskRepeatType;
    private CD worklistTaskVariabilityType;
    private ST worklistTaskVersion;
    private II worklistSubtaskUid;
    private ST worklistSubtaskName;
    private String worklistSubtaskStartDateTime;
    private String worklistSubtaskClosedDateTime;
    private TaskContextEntityCollection taskContextEntityCollection = new TaskContextEntityCollection();

    public II getWorklistTaskUid() {
        return worklistTaskUid;
    }

    public void setWorklistTaskUid(II worklistTaskUid) {
        worklistTaskUid.setTagName("worklistTaskUid");
        this.worklistTaskUid = worklistTaskUid;
    }

    public ST getWorklistTaskName() {
        return worklistTaskName;
    }

    public void setWorklistTaskName(ST worklistTaskName) {
        worklistTaskName.setTagName("worklistTaskName");
        this.worklistTaskName = worklistTaskName;
    }

    public ST getWorklistTaskDescription() {
        return worklistTaskDescription;
    }

    public void setWorklistTaskDescription(ST worklistTaskDescription) {
        worklistTaskDescription.setTagName("worklistTaskDescription");
        this.worklistTaskDescription = worklistTaskDescription;
    }

    public CD getWorklistTaskCategory() {
        return worklistTaskCategory;
    }

    public void setWorklistTaskCategory(CD worklistTaskCategory) {
        worklistTaskCategory.setTagName("worklistTaskCategory");
        this.worklistTaskCategory = worklistTaskCategory;
    }

    public CD getWorklistTaskLevel() {
        return worklistTaskLevel;
    }

    public void setWorklistTaskLevel(CD worklistTaskLevel) {
        worklistTaskLevel.setTagName("worklistTaskLevel");
        this.worklistTaskLevel = worklistTaskLevel;
    }

    public CD getWorklistTaskType() {
        return worklistTaskType;
    }

    public void setWorklistTaskType(CD worklistTaskType) {
        worklistTaskType.setTagName("worklistTaskType");
        this.worklistTaskType = worklistTaskType;
    }

    public CD getWorklistTaskRepeatType() {
        return worklistTaskRepeatType;
    }

    public void setWorklistTaskRepeatType(CD worklistTaskRepeatType) {
        worklistTaskRepeatType.setTagName("worklistTaskRepeatType");
        this.worklistTaskRepeatType = worklistTaskRepeatType;
    }

    public CD getWorklistTaskVariabilityType() {
        return worklistTaskVariabilityType;
    }

    public void setWorklistTaskVariabilityType(CD worklistTaskVariabilityType) {
        worklistTaskVariabilityType.setTagName("worklistTaskVariabilityType");
        this.worklistTaskVariabilityType = worklistTaskVariabilityType;
    }

    public ST getWorklistTaskVersion() {
        return worklistTaskVersion;
    }

    public void setWorklistTaskVersion(ST worklistTaskVersion) {
        worklistTaskVersion.setTagName("worklistTaskVersion");
        this.worklistTaskVersion = worklistTaskVersion;
    }

    public II getWorklistSubtaskUid() {
        return worklistSubtaskUid;
    }

    public void setWorklistSubtaskUid(II worklistSubtaskUid) {
        worklistSubtaskUid.setTagName("worklistSubtaskUid");
        this.worklistSubtaskUid = worklistSubtaskUid;
    }

    public ST getWorklistSubtaskName() {
        return worklistSubtaskName;
    }

    public void setWorklistSubtaskName(ST worklistSubtaskName) {
        worklistSubtaskName.setTagName("worklistSubtaskName");
        this.worklistSubtaskName = worklistSubtaskName;
    }

    public String getWorklistSubtaskStartDateTime() {
        return worklistSubtaskStartDateTime;
    }

    public void setWorklistSubtaskStartDateTime(String worklistSubtaskStartDateTime) {
        this.worklistSubtaskStartDateTime = worklistSubtaskStartDateTime;
    }

    public String getWorklistSubtaskClosedDateTime() {
        return worklistSubtaskClosedDateTime;
    }

    public void setWorklistSubtaskClosedDateTime(String worklistSubtaskClosedDateTime) {
        this.worklistSubtaskClosedDateTime = worklistSubtaskClosedDateTime;
    }

    public TaskContextEntityCollection getTaskContextEntityCollection() {
        return taskContextEntityCollection;
    }

    public void setTaskContextEntityCollection(TaskContextEntityCollection taskContextEntityCollection) {
        this.taskContextEntityCollection = taskContextEntityCollection;
    }

    public void addTaskContextEntity(TaskContextEntity newTaskContextEntity) {
        this.taskContextEntityCollection.addTaskContextEntity(newTaskContextEntity);
    }

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        if (getTagName() == null || "".equals(getTagName())) {
            setTagName("TaskContextEntity");
        }
        Element res = (Element) super.getXMLNode(doc);
        if (this.worklistTaskUid != null) {
            res.appendChild(this.worklistTaskUid.getXMLNode(doc));
        }
        if (this.worklistTaskName != null) {
            res.appendChild(this.worklistTaskName.getXMLNode(doc));
        }
        if (this.worklistTaskDescription != null) {
            res.appendChild(this.worklistTaskDescription.getXMLNode(doc));
        }
        if (this.worklistTaskCategory != null) {
            res.appendChild(this.worklistTaskCategory.getXMLNode(doc));
        }
        if (this.worklistTaskLevel != null) {
            res.appendChild(this.worklistTaskLevel.getXMLNode(doc));
        }
        if (this.worklistTaskType != null) {
            res.appendChild(this.worklistTaskType.getXMLNode(doc));
        }
        if (this.worklistTaskRepeatType != null) {
            res.appendChild(this.worklistTaskRepeatType.getXMLNode(doc));
        }
        if (this.worklistTaskVariabilityType != null) {
            res.appendChild(this.worklistTaskVariabilityType.getXMLNode(doc));
        }
        if (this.worklistTaskVersion != null) {
            res.appendChild(this.worklistTaskVersion.getXMLNode(doc));
        }
        if (this.worklistSubtaskUid != null) {
            res.appendChild(this.worklistSubtaskUid.getXMLNode(doc));
        }
        if (this.worklistSubtaskName != null) {
            res.appendChild(this.worklistSubtaskName.getXMLNode(doc));
        }
        if (this.worklistSubtaskStartDateTime != null) {
            Element el_worklistSubtaskStartDateTime = doc.createElement("worklistSubtaskStartDateTime");
            el_worklistSubtaskStartDateTime.setAttribute("value", this.worklistSubtaskStartDateTime.toString());
            res.appendChild(el_worklistSubtaskStartDateTime);
        }
        if (this.worklistSubtaskClosedDateTime != null) {
            Element el_worklistSubtaskClosedDateTime = doc.createElement("worklistSubtaskClosedDateTime");
            el_worklistSubtaskClosedDateTime.setAttribute("value", this.worklistSubtaskClosedDateTime.toString());
            res.appendChild(el_worklistSubtaskClosedDateTime);
        }
        if (this.taskContextEntityCollection.size() > 0) {
            res.appendChild(this.taskContextEntityCollection.getXMLNode(doc));
        }
        return res;
    }

    @Override
    public void setXMLNode(Node node) {
        super.setXMLNode(node);
        NodeList listChilds = node.getChildNodes();
        for (int i = 0; i < listChilds.getLength(); i++) {
            Node currentNode = listChilds.item(i);
            if ("worklistTaskUid".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setWorklistTaskUid(obj);
            }
            if ("worklistTaskName".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setWorklistTaskName(obj);
            }
            if ("worklistTaskDescription".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setWorklistTaskDescription(obj);
            }
            if ("worklistTaskCategory".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.setWorklistTaskCategory(obj);
            }
            if ("worklistTaskLevel".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.setWorklistTaskLevel(obj);
            }
            if ("worklistTaskType".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.setWorklistTaskType(obj);
            }
            if ("worklistTaskRepeatType".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.setWorklistTaskRepeatType(obj);
            }
            if ("worklistTaskVariabilityType".equals(currentNode.getNodeName())) {
                CD obj = new CD();
                obj.setXMLNode(currentNode);
                this.setWorklistTaskVariabilityType(obj);
            }
            if ("worklistTaskVersion".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setWorklistTaskVersion(obj);
            }
            if ("worklistSubtaskUid".equals(currentNode.getNodeName())) {
                II obj = new II();
                obj.setXMLNode(currentNode);
                this.setWorklistSubtaskUid(obj);
            }
            if ("worklistSubtaskName".equals(currentNode.getNodeName())) {
                ST obj = new ST();
                obj.setXMLNode(currentNode);
                this.setWorklistSubtaskName(obj);
            }
            if ("worklistSubtaskStartDateTime".equals(currentNode.getNodeName())) {
                this.worklistSubtaskStartDateTime = currentNode.getAttributes().getNamedItem("value").getNodeValue();
            }
            if ("worklistSubtaskClosedDateTime".equals(currentNode.getNodeName())) {
                this.worklistSubtaskClosedDateTime = currentNode.getAttributes().getNamedItem("value").getNodeValue();
            }
            if ("taskContextEntityCollection".equals(listChilds.item(i).getNodeName())) {
                this.taskContextEntityCollection.setXMLNode(listChilds.item(i));
            }
        }
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        TaskContextEntity oth = (TaskContextEntity) other;
        if (!this.worklistTaskUid.isEqualTo(oth.worklistTaskUid)) {
            return false;
        }
        if (!this.worklistTaskName.isEqualTo(oth.worklistTaskName)) {
            return false;
        }
        if (!this.worklistTaskDescription.isEqualTo(oth.worklistTaskDescription)) {
            return false;
        }
        if (!this.worklistTaskCategory.isEqualTo(oth.worklistTaskCategory)) {
            return false;
        }
        if (!this.worklistTaskLevel.isEqualTo(oth.worklistTaskLevel)) {
            return false;
        }
        if (!this.worklistTaskType.isEqualTo(oth.worklistTaskType)) {
            return false;
        }
        if (!this.worklistTaskRepeatType.isEqualTo(oth.worklistTaskRepeatType)) {
            return false;
        }
        if (!this.worklistTaskVariabilityType.isEqualTo(oth.worklistTaskVariabilityType)) {
            return false;
        }
        if (!this.worklistTaskVersion.isEqualTo(oth.worklistTaskVersion)) {
            return false;
        }
        if (!this.worklistSubtaskUid.isEqualTo(oth.worklistSubtaskUid)) {
            return false;
        }
        if (!this.worklistSubtaskName.isEqualTo(oth.worklistSubtaskName)) {
            return false;
        }
        if (this.worklistSubtaskStartDateTime == null ? oth.worklistSubtaskStartDateTime != null : !this.worklistSubtaskStartDateTime.equals(oth.worklistSubtaskStartDateTime)) {
            return false;
        }
        if (this.worklistSubtaskClosedDateTime == null ? oth.worklistSubtaskClosedDateTime != null : !this.worklistSubtaskClosedDateTime.equals(oth.worklistSubtaskClosedDateTime)) {
            return false;
        }
        if (!this.taskContextEntityCollection.isEqualTo(oth.taskContextEntityCollection)) {
            return false;
        }
        return super.isEqualTo(other);
    }

    @Override
    public TaskContextEntity getClone() {
        TaskContextEntity res = new TaskContextEntity();
        if (this.getWorklistTaskUid() != null) {
            res.setWorklistTaskUid(this.getWorklistTaskUid().getClone());
        }
        if (this.getWorklistTaskName() != null) {
            res.setWorklistTaskName(this.getWorklistTaskName().getClone());
        }
        if (this.getWorklistTaskDescription() != null) {
            res.setWorklistTaskDescription(this.getWorklistTaskDescription().getClone());
        }
        if (this.getWorklistTaskCategory() != null) {
            res.setWorklistTaskCategory(this.getWorklistTaskCategory().getClone());
        }
        if (this.getWorklistTaskLevel() != null) {
            res.setWorklistTaskLevel(this.getWorklistTaskLevel().getClone());
        }
        if (this.getWorklistTaskType() != null) {
            res.setWorklistTaskType(this.getWorklistTaskType().getClone());
        }
        if (this.getWorklistTaskRepeatType() != null) {
            res.setWorklistTaskRepeatType(this.getWorklistTaskRepeatType().getClone());
        }
        if (this.getWorklistTaskVariabilityType() != null) {
            res.setWorklistTaskVariabilityType(this.getWorklistTaskVariabilityType().getClone());
        }
        if (this.getWorklistTaskVersion() != null) {
            res.setWorklistTaskVersion(this.getWorklistTaskVersion().getClone());
        }
        if (this.getWorklistSubtaskUid() != null) {
            res.setWorklistSubtaskUid(this.getWorklistSubtaskUid().getClone());
        }
        if (this.getWorklistSubtaskName() != null) {
            res.setWorklistSubtaskName(this.getWorklistSubtaskName().getClone());
        }
        if (this.getWorklistSubtaskStartDateTime() != null) {
            res.setWorklistSubtaskStartDateTime(this.getWorklistSubtaskStartDateTime());
        }
        if (this.getWorklistSubtaskClosedDateTime() != null) {
            res.setWorklistSubtaskClosedDateTime(this.getWorklistSubtaskClosedDateTime());
        }
        if (this.getTaskContextEntityCollection() != null) {
            res.setTaskContextEntityCollection(this.getTaskContextEntityCollection().getClone());
        }
        if (this.getUniqueIdentifier() != null) {
            res.setUniqueIdentifier(this.getUniqueIdentifier().getClone());
        }
        if (this.getTagName() != null) {
            res.setTagName(this.getTagName());
        }
        if (this.getXsiType() != null) {
            res.setXsiType(this.getXsiType());
        }
        return res;
    }
}
