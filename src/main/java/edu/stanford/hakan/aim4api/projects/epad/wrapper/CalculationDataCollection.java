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
public class CalculationDataCollection  extends edu.stanford.hakan.aim4api.base.CalculationDataCollection {
    

    public void addCalculationData(CalculationData newCalculationData) {
        super.addCalculationData(newCalculationData);
    }

    public List<CalculationData> getCalculationDataListEpad() {
        List<edu.stanford.hakan.aim4api.base.CalculationData> temp = super.getCalculationDataList();
        List<CalculationData> res = new ArrayList<CalculationData>();
        for (int i = 0; i < temp.size(); i++) {
            res.add((CalculationData) temp.get(i));
        }
        return res;
    }

    public CalculationData get(int index) {
        if (index >= 0 && index <= super.getCalculationDataList().size() - 1) {
            return (CalculationData) super.getCalculationDataList().get(index);
        }
        return null;
    }
}
