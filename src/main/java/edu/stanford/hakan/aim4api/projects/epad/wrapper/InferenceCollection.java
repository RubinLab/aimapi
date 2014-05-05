/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import edu.stanford.hakan.aim4api.base.InferenceEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class InferenceCollection extends edu.stanford.hakan.aim4api.base.InferenceEntityCollection {

    public void addInference(Inference newInference) {
        super.addInferenceEntity(newInference);
    }

    public List<Inference> getInferenceList() {
        List<InferenceEntity> temp = super.getInferenceEntityList();
        List<Inference> res = new ArrayList<Inference>();
        for (int i = 0; i < temp.size(); i++) {
            res.add((Inference) temp.get(i));
        }
        return res;
    }

    @Override
    public Inference get(int index) {
        if (index >= 0 && index <= super.getInferenceEntityList().size() - 1) {
            return (Inference) super.getInferenceEntityList().get(index);
        }
        return null;
    }
}