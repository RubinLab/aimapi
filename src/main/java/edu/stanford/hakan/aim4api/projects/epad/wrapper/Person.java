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
public class Person extends edu.stanford.hakan.aim4api.base.Person {

    /*    
     Person ; Epad = Person
        
     + getName()
     + getId()    
     */
    public String getNameEpad() {
        if (this.getName() != null) {
            return super.getName().getValue();
        }
        return "";
    }

    public String getIdEpad() {
        if (this.getId() != null) {
            return this.getId().getValue();
        }
        return "";
    }

    public void setName(String name) {
        super.setName(new ST(name));
    }

    public void setId(String id) {
        super.setId(new ST(id));
    }

    public void setSex(String sex) {
        super.setSex(new ST(sex));
    }
}
