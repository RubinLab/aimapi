/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.plugin;

import edu.stanford.hakan.aim4api.compability.aimv3.Annotation;
import edu.stanford.hakan.aim4api.compability.aimv3.Calculation;
import edu.stanford.hakan.aim4api.compability.aimv3.GeometricShape;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakan
 */
public final class Plugin {
    
    private final String spliterNP = "*sp2*";
    
    private String name = "";
    private String calculationEntityID = "";
    private String markupEntityID = "";
    private List<PluginParameter> listPluginParameter = new ArrayList<PluginParameter>();
    
    private GeometricShape geometricShape = null;
    private Calculation calculation = null;
    private Annotation annotation  = null;
 
    

    public Plugin() {
    }

    public Plugin(String str) {
        String strParams = "";
        if (str.contains(this.spliterNP)) {
            String[] nameParams = str.split("\\"+this.spliterNP);
            this.setName(nameParams[0]);
            strParams = nameParams[1];
        } else if (str.contains(this.spliterNP)) {
            strParams = str;
        }
        
        String[] params = strParams.split("\\"+this.spliterNP);
        for (String strParam : params) {
            if (strParam.contains(PluginParameter.spliterNV)) {
                this.listPluginParameter.add(new PluginParameter(strParam));
            }
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public List<PluginParameter> getListPluginParameter() {
        return listPluginParameter;
    }

    public void setListPluginParameter(List<PluginParameter> listPluginParameter) {
        this.listPluginParameter = listPluginParameter;
    }

    public void addParameter(PluginParameter pluginParameter) {
        this.listPluginParameter.add(pluginParameter);
    }

    public void addParameter(String name, String value) {
        this.listPluginParameter.add(new PluginParameter(name, value));
    }

    public GeometricShape getGeometricShape() {
        return geometricShape;
    }

    public void setGeometricShape(GeometricShape geometricShape) {
        this.geometricShape = geometricShape;
    }

    public Calculation getCalculation() {
        return calculation;
    }

    public void setCalculation(Calculation calculation) {
        this.calculation = calculation;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public String getCalculationEntityID() {
        return calculationEntityID;
    }

    public void setCalculationEntityID(String calculationEntityID) {
        this.calculationEntityID = calculationEntityID;
    }

    public String getMarkupEntityID() {
        return markupEntityID;
    }

    public void setMarkupEntityID(String markupEntityID) {
        this.markupEntityID = markupEntityID;
    }
    
    @Override
    public String toString() {
        if ("".equals(this.name.trim())) {
            this.name = "-";
        }
        if ("".equals(this.markupEntityID.trim())) {
            this.markupEntityID = "-";
        }
        if ("".equals(this.calculationEntityID.trim())) {
            this.calculationEntityID = "-";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append(this.spliterNP);
        sb.append(this.markupEntityID).append(this.spliterNP);
        sb.append(this.calculationEntityID).append(this.spliterNP);
        
        for (PluginParameter pluginParameter : listPluginParameter) {
            sb.append(pluginParameter.toString());
            sb.append(this.spliterNP);
        }
        return sb.toString().trim();
    }

}
