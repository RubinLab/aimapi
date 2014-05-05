/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.wrappers.epad.old;

/**
 *
 * @author Hakan
 */
public class CalcResult extends edu.stanford.hakan.aim4api.projects.epad.wrapper.CalculationResult {

    public CalcData getItem(int k) {
        return (CalcData) super.getCalculationDataCollection().getCalculationDataList().get(k).getClone();
    }

    public CalcDimension getDimItem(int k) {
        return (CalcDimension) super.getDimensionCollection().getDimensionList().get(k).getClone();
    }
}
