/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad;

import edu.stanford.hakan.aim4api.base.ST;

/**
 *
 * @author Hakan
 */
public class Person {

    private String name;
    private String id;
    private String sex;
    private String birthdate;

    public Person() {
        this.name = "";
        this.id = "";
        this.sex = "";
        this.birthdate = "01.01.1900";
    }

    public Person(String name, String id, String sex, String birthdate) {
        this.name = name;
        this.id = id;
        this.sex = sex;
        this.birthdate = birthdate;
    }

    public Person(edu.stanford.hakan.aim4api.base.Person personAimV4) {
        if (personAimV4.getName() != null) {
            this.name = personAimV4.getName().getValue();
        }
        if (personAimV4.getId() != null) {
            this.id = personAimV4.getId().getValue();
        }
        if (personAimV4.getSex() != null) {
            this.sex = personAimV4.getSex().getValue();
        }
        if (personAimV4.getBirthDate() != null) {
            this.birthdate = personAimV4.getBirthDate();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public edu.stanford.hakan.aim4api.base.Person toAimV4() {
        edu.stanford.hakan.aim4api.base.Person res = new edu.stanford.hakan.aim4api.base.Person();
        res.setName(new ST(this.getName()));
        res.setId(new ST(this.getId()));
        res.setSex(new ST(this.getSex()));
        res.setBirthDate(this.getBirthdate());
        return res;
    }
}
