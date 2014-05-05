/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import java.util.ArrayList;
import java.util.List;
import edu.stanford.hakan.aim4api.base.ImagingPhysicalEntityCharacteristic;
import edu.stanford.hakan.aim4api.base.ImagingPhysicalEntityCharacteristicCollection;

/**
 *
 * @author Hakan
 */
public class AnatomicEntityCharacteristicCollection extends ImagingPhysicalEntityCharacteristicCollection {

    public void AddAnatomicEntityCharacteristic(AnatomicEntityCharacteristic newAnatomicEntityCharacteristic) {
        super.addImagingPhysicalEntityCharacteristic(newAnatomicEntityCharacteristic);
    }

    public List<AnatomicEntityCharacteristic> getAnatomicEntityCharacteristicList() {
        List<ImagingPhysicalEntityCharacteristic> temp = super.getImagingPhysicalEntityCharacteristicList();
        List<AnatomicEntityCharacteristic> res = new ArrayList<AnatomicEntityCharacteristic>();
        for (int i = 0; i < temp.size(); i++) {
            res.add((AnatomicEntityCharacteristic) temp.get(i));
        }
        return res;
    }

    public AnatomicEntityCharacteristic get(int index) {
        if (index >= 0 && index <= super.getImagingPhysicalEntityCharacteristicList().size() - 1) {
            return (AnatomicEntityCharacteristic) super.getImagingPhysicalEntityCharacteristicList().get(index);
        }
        return null;
    }
}
