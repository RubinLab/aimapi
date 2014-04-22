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
public class Equipment {

    private String name;
    private String model;
    private String version;

    public Equipment() {
    }

    public Equipment(String name, String model, String version) {
        this.name = name;
        this.model = model;
        this.version = version;
    }

    public Equipment(edu.stanford.hakan.aim4api.base.Equipment equipmentAimV4) {
        if (equipmentAimV4.getManufacturerName() != null) {
            this.name = equipmentAimV4.getManufacturerName().getValue();
        }
        if (equipmentAimV4.getManufacturerModelName() != null) {
            this.model = equipmentAimV4.getManufacturerModelName().getValue();
        }
        if (equipmentAimV4.getSoftwareVersion() != null) {
            this.version = equipmentAimV4.getSoftwareVersion().getValue();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public edu.stanford.hakan.aim4api.base.Equipment toAimV4() {
        edu.stanford.hakan.aim4api.base.Equipment res = new edu.stanford.hakan.aim4api.base.Equipment();
        res.setManufacturerName(new ST(this.getName()));
        res.setManufacturerModelName(new ST(this.getModel()));
        res.setSoftwareVersion(new ST(this.getVersion()));
        return res;
    }
}
