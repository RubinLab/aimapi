/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import edu.stanford.hakan.aim4api.base.ST;
import java.util.logging.Logger;

/**
 *
 * @author Hakan
 */
public class User extends edu.stanford.hakan.aim4api.base.User {
    
	private static final Logger logger = Logger.getLogger("aim4api.base.User");
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
        if (super.getName() != null) {
            super.getName().getValue();
        }
        return "";
    }

    public void setName(String name) {
        if (super.getName() == null) {
            super.setName(new ST());
        }
        super.getName().setValue(name);
    }

    public String getLoginNameEpad() {
        if (super.getLoginName() != null) {
            super.getLoginName().getValue();
        }
        return "";
    }

    public void setLoginName(String name) {
        if (super.getLoginName() == null) {
            super.setLoginName(new ST());
        }
        logger.info("[Hakan] User.java setLoginName - input param = " + name + " [Hakan]");
        super.getLoginName().setValue(name);
        logger.info("[Hakan] User.java setLoginName - super.getLoginName().getValue() =  " + super.getLoginName().getValue() + " [Hakan]");
    }
}
