/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import edu.stanford.hakan.aim4api.base.ST;

/**
 *
 * @author Hakan
 */
public class Equipment  extends edu.stanford.hakan.aim4api.base.Equipment {
    
    public void setManufacturerName(String manufacturerName) {
        super.setManufacturerName(new ST(manufacturerName));
    }
    public void setManufacturerModelName(String manufacturerModelName) {
        super.setManufacturerModelName(new ST(manufacturerModelName));
    }
    public void setSoftwareVersion(String softwareVersion) {
        super.setSoftwareVersion(new ST(softwareVersion));
    }
    
}
