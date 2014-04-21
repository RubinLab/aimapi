/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.edu.stanford.hakan.aim4api.projects.epad.wrapper;

/**
 *
 * @author Hakan
 */
public class Person extends main.java.edu.stanford.hakan.aim4api.base.Person {

    /*    
        Person ; Epad = Person
        
        + getName()
        + getId()    
    */
    
    public String getNameEpad() {
        if (this.getName() != null) {
            return this.getName().getValue();
        }
        return "";
    }

    public String getIdEpad() {
        if (this.getId() != null) {
            return this.getId().getValue();
        }
        return "";
    }
}
