/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.plugin.v4;

import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.CalculationEntity;
import edu.stanford.hakan.aim4api.base.IAimXMLOperations;
import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.base.MarkupEntity;
import edu.stanford.hakan.aim4api.plugin.*;
import edu.stanford.hakan.aim4api.compability.aimv3.Annotation;
import edu.stanford.hakan.aim4api.compability.aimv3.Calculation;
import edu.stanford.hakan.aim4api.compability.aimv3.GeometricShape;
import edu.stanford.hakan.aim4api.plugin.v3.PluginV3;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan
 */
@SuppressWarnings("serial")
public final class PluginV4  implements IAimXMLOperations {
    
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
            String strForParameters = "";
            if(array.length >= 4)
             strForParameters= array[3];

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

    @Override
    public Node getXMLNode(Document doc) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setXMLNode(Node node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualTo(Object other) {
        PluginV4 oth = (PluginV4) other;
        if (this.name == null ? oth.name != null : !this.name.equals(oth.name)) {
            return false;
        }
        if (this.calculationEntity == null ? oth.calculationEntity != null : !this.calculationEntity.isEqualTo(oth.calculationEntity)) {
            return false;
        }
        if (this.markupEntity == null ? oth.markupEntity != null : !this.markupEntity.isEqualTo(oth.markupEntity)) {
            return false;
        }
        if (this.pluginParemeterCollection == null ? oth.pluginParemeterCollection != null : !this.pluginParemeterCollection.isEqualTo(oth.pluginParemeterCollection)) {
            return false;
        }
        return true;
    }
    
    
    public PluginV4 getClone() {
        PluginV4 res = new PluginV4();
        if (this.getName() != null) {
            res.setName(this.getName());
        }
        if (this.getCalculationEntity()!= null) {
            res.setCalculationEntity(this.getCalculationEntity().getClone());
        }
        if (this.getMarkupEntity()!= null) {
            res.setMarkupEntity(this.getMarkupEntity().getClone());
        }
        if (this.getPluginParemeterCollection()!= null) {
            res.setPluginParemeterCollection(this.getPluginParemeterCollection().getClone());
        }
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PluginV4)) {
            return false;
        }
        PluginV4 other = (PluginV4) obj;
        return this.name.equals(other.name);
    }


    
}
