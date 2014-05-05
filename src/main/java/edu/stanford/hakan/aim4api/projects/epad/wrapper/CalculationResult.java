/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import edu.stanford.hakan.aim4api.base.ST;

/**
 *
 * @author Hakan
 */
public class CalculationResult extends edu.stanford.hakan.aim4api.base.ExtendedCalculationResult {
    
    /*
        CalculationResult ; Epad = CalcResult
        
        + setUnitOfMeasure(String)
        + setType(AimUtility.CalculationResultIdentifier)
        + setNumberOfDimensions(Integer)
        + setDimensionCollection(DimensionCollection)
        + setCalculationDataCollection(CalculationDataCollection)
        
        //*** It seems that, it is always set by zero. 
        ? setCagridId(Integer) 
        
        + getUnitOfMeasure()
        + getType()
        + getNumberOfDimensions()
        + getDimensionCollection()
        + getCalculationDataCollection()
     */
    
    
    public String getUnitOfMeasureEpad() {
        if (this.getUnitOfMeasure() != null) {
            return this.getUnitOfMeasure().getValue();
        }
        return "";
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        if (this.getUnitOfMeasure() == null) {
            this.setUnitOfMeasure(new ST());
        }
        this.getUnitOfMeasure().setValue(unitOfMeasure);
    }

    public int getNumberOfDimensions() {
        if (this.getCalculationDataCollection() == null) {
            return 0;
        }
        return this.getCalculationDataCollection().size();
    }

    public CalculationDataCollection getCalculationDataCollectionEpad() {
        return (CalculationDataCollection) super.getCalculationDataCollection();
    }

    public CalculationData getItem(int k) {
        return (CalculationData) super.getCalculationDataCollection().getCalculationDataList().get(k).getClone();
    }

    public Dimension getDimItem(int k) {
        return (Dimension) super.getDimensionCollection().getDimensionList().get(k).getClone();
    }

}
