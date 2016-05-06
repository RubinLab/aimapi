/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.plugin.v4;

import edu.stanford.hakan.aim4api.plugin.Common;
import edu.stanford.hakan.aim4api.utility.Logger;
import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.IAimXMLOperations;
import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author Hakan
 */
@SuppressWarnings("serial")
public class PluginCollectionV4 implements IAimXMLOperations {
    
    private ImageAnnotation imageAnnotation = null;
    private List<PluginV4> listPlugin = new ArrayList<PluginV4>();
    public PluginCollectionV4() {
        
    }

    public PluginCollectionV4(ImageAnnotation imageAnnotation, String strPlugins) {

        this.imageAnnotation = imageAnnotation;

        if (strPlugins.contains(Common.spliterTwo)) {
            String[] array = strPlugins.split("\\" + Common.spliterTwo);
            for (int i = 0; i < array.length; i++) {
                String strForPlugin = array[i];
                if (!"".equals(strForPlugin) && strForPlugin.contains(Common.spliterThree)) {
                    PluginV4 pluginV4 = new PluginV4(strForPlugin, this);
                    this.addPlugin(pluginV4);
                }
            }
        } else if (strPlugins.contains(Common.spliterThree)) {
        	Logger.write("plugins str "+strPlugins);
            PluginV4 pluginV4 = new PluginV4(strPlugins, this);
            this.addPlugin(pluginV4);
        }
    }

    
    public void addPlugin(PluginV4 newPlugin) {
        newPlugin.setPluginCollection(this);
        
        if(listPlugin.contains(newPlugin))
            listPlugin.remove(newPlugin);
        
        this.listPlugin.add(newPlugin);
    }
    
    public int size() {
        return this.listPlugin.size();
    }
    
    public PluginV4 get(int index) {
        if (index <= this.listPlugin.size() - 1) {
            return this.listPlugin.get(index);
        }
        return null;
    }

    public ImageAnnotation getImageAnnotation() {
        return imageAnnotation;
    }

    public void setImageAnnotation(ImageAnnotation imageAnnotation) {
        this.imageAnnotation = imageAnnotation;
    }

    public List<PluginV4> getListPlugin() {
        return listPlugin;
    }

    public void setListPlugin(List<PluginV4> listPlugin) {
        this.listPlugin = listPlugin;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (PluginV4 plugin : this.listPlugin) {
            sb.append(plugin.toString());
            sb.append(Common.spliterTwo);
        }
        
        String res = sb.toString().trim();
        if (res.endsWith(Common.spliterTwo)) {
            res = res.substring(0, res.length() - Common.spliterTwo.length());
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
        PluginCollectionV4 oth = (PluginCollectionV4) other;
        if (this.listPlugin.size() != oth.listPlugin.size()) {
            return false;
        }
        for (int i = 0; i < this.listPlugin.size(); i++) {
            if (this.listPlugin.get(i) == null ? oth.listPlugin.get(i) != null : !this.listPlugin.get(i).isEqualTo(oth.listPlugin.get(i))) {
                return false;
            }
        }
        return true;
    }

    public PluginCollectionV4 getClone() {
        PluginCollectionV4 res = new PluginCollectionV4();
        for (int i = 0; i < this.listPlugin.size(); i++) {
            if (this.listPlugin.get(i) != null) {
                res.addPlugin(this.listPlugin.get(i).getClone());
            }
        }
        return res;
    }  
    
}
