/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.hakan.aim4api.plugin;

/**
 *
 * @author Hakan
 */
public class Parameter {

    String name;
    String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Parameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Parameter() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Parameter)) {
            return false;
        }
        Parameter c = (Parameter) o;
        if (c.name.toLowerCase().trim().equals(this.name.toLowerCase().trim())) {
            return true;
        }
        return false;
    }
}
