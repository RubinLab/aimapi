/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.audittrail;

import edu.stanford.hakan.aim4api.base.AimException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan
 */
public class AuditTrailPatch {

    private int versionNumber = 0;
    private String tagName;
    private String dateTime;
    private String previosUid;

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPreviosUid() {
        return previosUid;
    }

    public void setPreviosUid(String previosUid) {
        this.previosUid = previosUid;
    }
    
    

    public Node getXMLNode(Document doc)  throws AimException {
        Element res = doc.createElement(this.getTagName());
        res.setAttribute("Version", Integer.toString(this.versionNumber));
        res.setAttribute("DateTime", this.dateTime);
        res.setAttribute("PreviousUID", this.previosUid);
        return res;
    }
}
