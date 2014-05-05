/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import edu.stanford.hakan.aim4api.base.ImagingObservationEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class ImagingObservationCollection extends edu.stanford.hakan.aim4api.base.ImagingObservationEntityCollection {

    public void addImagingObservation(ImagingObservation newImagingObservation) {
        super.addImagingObservationEntity(newImagingObservation);
    }

    public List<ImagingObservation> getImagingObservationList() {
        List<ImagingObservationEntity> temp = super.getImagingObservationEntityList();
        List<ImagingObservation> res = new ArrayList<ImagingObservation>();
        for (int i = 0; i < temp.size(); i++) {
            res.add((ImagingObservation) temp.get(i));
        }
        return res;
    }

    @Override
    public ImagingObservation get(int index) {
        if (index >= 0 && index <= super.getImagingObservationEntityList().size() - 1) {
            return (ImagingObservation) super.getImagingObservationEntityList().get(index);
        }
        return null;
    }
}
