/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.wrappers.epad;

/**
 *
 * @author Hakan
 */
public class Calc extends edu.stanford.hakan.aim4api.projects.epad.wrapper.Calculation {

    public CalcResult getItem(int j) {

        return (CalcResult) this.getCalculationResultCollection().get(j).getClone();

    }
}
