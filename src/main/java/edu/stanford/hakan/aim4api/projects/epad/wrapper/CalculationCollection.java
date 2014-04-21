/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.edu.stanford.hakan.aim4api.projects.epad.wrapper;

import java.util.ArrayList;
import java.util.List;
import main.java.edu.stanford.hakan.aim4api.base.CalculationEntity;

/**
 *
 * @author Hakan
 */
public class CalculationCollection extends main.java.edu.stanford.hakan.aim4api.base.CalculationEntityCollection {

    public void addCalculation(Calculation newCalculation) {
        super.addCalculationEntity(newCalculation);
    }

    public List<Calculation> getCalculationList() {
        List<CalculationEntity> temp = super.getCalculationEntityList();
        List<Calculation> res = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            res.add((Calculation) temp.get(i));
        }
        return res;
    }

    @Override
    public Calculation get(int index) {
        if (index >= 0 && index <= super.getCalculationEntityList().size() - 1) {
            return (Calculation) super.getCalculationEntityList().get(index);
        }
        return null;
    }
}
