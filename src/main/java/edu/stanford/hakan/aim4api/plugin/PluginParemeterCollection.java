/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.plugin;

import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.IAimXMLOperations;
import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.plugin.v4.PluginV4;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan
 */
@SuppressWarnings("serial")
public class PluginParemeterCollection   implements IAimXMLOperations {

    private List<PluginParameter> listPluginParameter = new ArrayList<PluginParameter>();
    public PluginParemeterCollection() {

    }

    public PluginParemeterCollection(String str) {
        if (str == null || "".equals(str)) {
            return;
        }

        if (str.contains(Common.spliterFour)) {
            String[] array = str.split("\\" + Common.spliterFour);
            for (int i = 0; i < array.length; i++) {
                String strForPluginParam = array[i];
                if (!"".equals(strForPluginParam) && strForPluginParam.contains(Common.spliterFive)) {
                    PluginParameter pluginParam = new PluginParameter(strForPluginParam);
                    this.addPluginParameter(pluginParam);
                }
            }
        } else if (str.contains(Common.spliterFive)) {
            PluginParameter pluginParam = new PluginParameter(str);
            this.addPluginParameter(pluginParam);
        }
    }


    public void addPluginParameter(PluginParameter newPluginParameter) {
        this.listPluginParameter.add(newPluginParameter);
    }

    public int size() {
        return this.listPluginParameter.size();
    }

    public PluginParameter get(int index) {
        if (index <= this.listPluginParameter.size() - 1) {
            return this.listPluginParameter.get(index);
        }
        return null;
    }

    public List<PluginParameter> getListPluginParameter() {
        return listPluginParameter;
    }

    public void setListPluginParameter(List<PluginParameter> listPluginParameter) {
        this.listPluginParameter = listPluginParameter;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PluginParameter pluginParameter : this.listPluginParameter) {
            sb.append(pluginParameter.toString());
            sb.append(Common.spliterFour);
        }
        String res = sb.toString().trim();
        if (res.endsWith(Common.spliterFour)) {
            res = res.substring(0, res.length() - Common.spliterFour.length());
        }
        return res;
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
        PluginParemeterCollection oth = (PluginParemeterCollection) other;
        if (this.listPluginParameter.size() != oth.listPluginParameter.size()) {
            return false;
        }
        for (int i = 0; i < this.listPluginParameter.size(); i++) {
            if (this.listPluginParameter.get(i) == null ? oth.listPluginParameter.get(i) != null : !this.listPluginParameter.get(i).isEqualTo(oth.listPluginParameter.get(i))) {
                return false;
            }
        }
        return true;
    }

    public PluginParemeterCollection getClone() {
        PluginParemeterCollection res = new PluginParemeterCollection();
        for (int i = 0; i < this.listPluginParameter.size(); i++) {
            if (this.listPluginParameter.get(i) != null) {
                res.addPluginParameter(this.listPluginParameter.get(i).getClone());
            }
        }
        return res;
    }
}
