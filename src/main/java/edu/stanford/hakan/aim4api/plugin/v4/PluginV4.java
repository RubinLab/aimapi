/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.plugin.v4;

import edu.stanford.hakan.aim4api.base.CalculationEntity;
import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.base.MarkupEntity;
import edu.stanford.hakan.aim4api.plugin.*;
import edu.stanford.hakan.aim4api.compability.aimv3.Annotation;
import edu.stanford.hakan.aim4api.compability.aimv3.Calculation;
import edu.stanford.hakan.aim4api.compability.aimv3.GeometricShape;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakan
 */
public final class PluginV4 {
    
    private PluginCollectionV4 pluginCollection = null;
    private String name = "";
    private CalculationEntity calculationEntity = null;
    private MarkupEntity  markupEntity  = null;
    private PluginParemeterCollection pluginParemeterCollection = new PluginParemeterCollection();

    public PluginV4() {
    }

    public PluginV4(String strPlugin, PluginCollectionV4 pluginCollection) {
        this.pluginCollection = pluginCollection;

        if (strPlugin.contains(Common.spliterThree)) {
            String[] array = strPlugin.split("\\" + Common.spliterThree);

            String strName = array[0];
            String markupEntityID = array[1];
            String calculationEntityID = array[2];
            String strForParameters = array[3];

            if (!"".equals(strForParameters)) {
                PluginParemeterCollection pluginParemeterCollection = new PluginParemeterCollection(strForParameters);
                this.pluginParemeterCollection = pluginParemeterCollection;
            }

            this.setName(strName);
            ImageAnnotation ia = this.pluginCollection.getImageAnnotation();
            for (MarkupEntity markupEntity : ia.getMarkupEntityCollection().getMarkupEntityList()) {
                if (markupEntity.getUniqueIdentifier().getRoot().equals(markupEntityID)) {
                    this.markupEntity = markupEntity;
                    break;
                }
            }
            for (CalculationEntity calculationEntity : ia.getCalculationEntityCollection().getCalculationEntityList()) {
                if (calculationEntity.getUniqueIdentifier().getRoot().equals(calculationEntityID)) {
                    this.calculationEntity = calculationEntity;
                    break;
                }
            }
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CalculationEntity getCalculationEntity() {
        return calculationEntity;
    }

    public void setCalculationEntity(CalculationEntity calculationEntity) {
        this.calculationEntity = calculationEntity;
    }

    public MarkupEntity getMarkupEntity() {
        return markupEntity;
    }

    public void setMarkupEntity(MarkupEntity markupEntity) {
        this.markupEntity = markupEntity;
    }

    public PluginParemeterCollection getPluginParemeterCollection() {
        return pluginParemeterCollection;
    }

    public void setPluginParemeterCollection(PluginParemeterCollection pluginParemeterCollection) {
        this.pluginParemeterCollection = pluginParemeterCollection;
    }

    public void addPluginParameter(PluginParameter newPluginParameter) {
        if(this.pluginParemeterCollection == null)
        {
            this.pluginParemeterCollection = new PluginParemeterCollection();
        }
        this.pluginParemeterCollection.addPluginParameter(newPluginParameter);
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append(Common.spliterThree);
        if (this.markupEntity != null) {
            sb.append(this.markupEntity.getUniqueIdentifier().getRoot());
        } else {
            sb.append("-");
        }
        sb.append(Common.spliterThree);
        if (this.calculationEntity != null) {
            sb.append(this.calculationEntity.getUniqueIdentifier().getRoot());
        } else {
            sb.append("-");
        }
        sb.append(Common.spliterThree);
        sb.append(this.pluginParemeterCollection.toString());

        return sb.toString().trim();
    }

//    
//    @Override
//    public String toString() {
//        if ("".equals(this.name.trim())) {
//            this.name = "-";
//        }
//        if ("".equals(this.markupEntityID.trim())) {
//            this.markupEntityID = "-";
//        }
//        if ("".equals(this.calculationEntityID.trim())) {
//            this.calculationEntityID = "-";
//        }
//        StringBuilder sb = new StringBuilder();
//        sb.append(this.name).append(this.spliterNP);
//        sb.append(this.markupEntityID).append(this.spliterNP);
//        sb.append(this.calculationEntityID).append(this.spliterNP);
//        
//        for (PluginParameter pluginParameter : listPluginParameter) {
//            sb.append(pluginParameter.toString());
//            sb.append(this.spliterNP);
//        }
//        return sb.toString().trim();
//    }

    public void setPluginCollection(PluginCollectionV4 pluginCollection) {
        this.pluginCollection = pluginCollection;
    }

    public PluginCollectionV4 getPluginCollection() {
        return pluginCollection;
    }

    
}
