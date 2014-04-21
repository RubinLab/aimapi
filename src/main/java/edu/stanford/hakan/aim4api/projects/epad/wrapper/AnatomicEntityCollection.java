/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.edu.stanford.hakan.aim4api.projects.epad.wrapper;
import java.util.ArrayList;
import java.util.List;
import main.java.edu.stanford.hakan.aim4api.base.ImagingPhysicalEntityCollection;
import main.java.edu.stanford.hakan.aim4api.base.ImagingPhysicalEntity;

/**
 *
 * @author Hakan
 */
public class AnatomicEntityCollection  extends ImagingPhysicalEntityCollection {
    
     public void addAnatomicEntity(AnatomicEntity newAnatomicEntity) {
        super.addImagingPhysicalEntity(newAnatomicEntity);
    }

    public List<AnatomicEntity> getAnatomicEntityCharacteristicList() {
        List<ImagingPhysicalEntity> temp = super.getImagingPhysicalEntityList();
        List<AnatomicEntity> res = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            res.add((AnatomicEntity) temp.get(i));
        }
        return res;
    }

    public AnatomicEntity get(int index) {
        if (index >= 0 && index <= super.getImagingPhysicalEntityList().size() - 1) {
            return (AnatomicEntity) super.getImagingPhysicalEntityList().get(index);
        }
        return null;
    }
}
