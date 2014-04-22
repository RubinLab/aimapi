/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import edu.stanford.hakan.aim4api.base.ST;

/**
 *
 * @author Hakan
 */
public class User extends edu.stanford.hakan.aim4api.base.User {
    
    /*    
        User ; Epad = User
    
        + setName(String)
        + setLoginName(String)
                
        //*** It seems that, it is always set by zero.
        ? setCagridId(int)
        
        + getName()
        + getLoginName()    
    */
    
    public String getNameEpad() {
        if (this.getName() != null) {
            this.getName().getValue();
        }
        return "";
    }

    public void setName(String name) {
        if (this.getName() == null) {
            this.setName(new ST());
        }
        this.getName().setValue(name);
    }

    public String getLoginNameEpad() {
        if (this.getLoginName() != null) {
            this.getLoginName().getValue();
        }
        return "";
    }

    public void setLoginName(String name) {
        if (this.getLoginName() == null) {
            this.setLoginName(new ST());
        }
        this.getLoginName().setValue(name);
    }
}
