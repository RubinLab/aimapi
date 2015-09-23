/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.plugin;

import edu.stanford.hakan.aim4api.utility.Logger;


/**
 *
 * @author Hakan
 */
@SuppressWarnings("serial")
public class PluginParameter {

    private String name;
    private String value;

    public PluginParameter() {
    }

    public PluginParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public PluginParameter(String nameValuePair) {
        Logger.write("PluginParameter-s");
        if (nameValuePair.contains(Common.spliterFive)) {
            String[] res = nameValuePair.split("\\" + Common.spliterFive);
            this.name = res[0];
            this.value = res[1];
        }
        Logger.write("PluginParameter-e");
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PluginParameter)) {
            return false;
        }
        PluginParameter c = (PluginParameter) o;
        if (c.name.toLowerCase().trim().equals(this.name.toLowerCase().trim())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append(Common.spliterFive);
        sb.append(this.getValue());
        return sb.toString().trim();
    }
}
