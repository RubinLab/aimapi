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
public class CalculationResultCollection extends edu.stanford.hakan.aim4api.base.CalculationResultCollection {

    public void addCalculationResult(CalculationResult newCalculationResult) {
        super.addCalculationResult(newCalculationResult);
    }

    public List<CalculationResult> getCalculationResultListEpad() {
        List<edu.stanford.hakan.aim4api.base.CalculationResult> temp = super.getCalculationResultList();
        List<CalculationResult> res = new ArrayList<CalculationResult>();
        for (int i = 0; i < temp.size(); i++) {
            res.add((CalculationResult) temp.get(i));
        }
        return res;
    }

    public CalculationResult get(int index) {
        if (index >= 0 && index <= super.getCalculationResultList().size() - 1) {
            return (CalculationResult) super.getCalculationResultList().get(index);
        }
        return null;
    }
}
