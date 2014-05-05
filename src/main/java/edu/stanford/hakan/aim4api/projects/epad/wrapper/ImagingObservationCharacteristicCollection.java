/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class ImagingObservationCharacteristicCollection extends edu.stanford.hakan.aim4api.base.ImagingObservationCharacteristicCollection {

    public void addImagingObservationCharacteristic(ImagingObservationCharacteristic newImagingObservationCharacteristic) {
        super.addImagingObservationCharacteristic(newImagingObservationCharacteristic);
    }

    public List<ImagingObservationCharacteristic> getImagingObservationCharacteristicListEpad() {
        List<edu.stanford.hakan.aim4api.base.ImagingObservationCharacteristic> temp = super.getImagingObservationCharacteristicList();
        List<ImagingObservationCharacteristic> res = new ArrayList<ImagingObservationCharacteristic>();
        for (int i = 0; i < temp.size(); i++) {
            res.add((ImagingObservationCharacteristic) temp.get(i));
        }
        return res;
    }

    @Override
    public ImagingObservationCharacteristic get(int index) {
        if (index >= 0 && index <= super.getImagingObservationCharacteristicList().size() - 1) {
            return (ImagingObservationCharacteristic) super.getImagingObservationCharacteristicList().get(index);
        }
        return null;
    }
}
