/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.plugin.v4;

import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.plugin.Common;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class PluginCollectionV4 {
    
    private ImageAnnotation imageAnnotation = null;
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
            PluginV4 pluginV4 = new PluginV4(strPlugins, this);
            this.addPlugin(pluginV4);
        }
    }

    private List<PluginV4> listPlugin = new ArrayList<PluginV4>();
    
    public void addPlugin(PluginV4 newPlugin) {
        newPlugin.setPluginCollection(this);
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

}
