/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad.wrapper;
import java.util.ArrayList;
import java.util.List;
import edu.stanford.hakan.aim4api.base.ImagingPhysicalEntityCollection;
import edu.stanford.hakan.aim4api.base.ImagingPhysicalEntity;

/**
 *
 * @author Hakan
 */
public class AnatomicEntityCollection  extends ImagingPhysicalEntityCollection {
    
     public void addAnatomicEntity(AnatomicEntity newAnatomicEntity) {
        super.addImagingPhysicalEntity(newAnatomicEntity);
    }

    public List<AnatomicEntity> getAnatomicEntityList() {
        List<ImagingPhysicalEntity> temp = super.getImagingPhysicalEntityList();
        List<AnatomicEntity> res = new ArrayList<AnatomicEntity>();
        for (int i = 0; i < temp.size(); i++) {
            res.add((AnatomicEntity) temp.get(i));
        }
        return res;
    }

     @Override
    public AnatomicEntity get(int index) {
        if (index >= 0 && index <= super.getImagingPhysicalEntityList().size() - 1) {
            return (AnatomicEntity) super.getImagingPhysicalEntityList().get(index);
        }
        return null;
    }
}
