/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.plugin.v3;

import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.IAimXMLOperations;
import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.plugin.v3.*;
import edu.stanford.hakan.aim4api.plugin.v4.PluginCollectionV4;
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
public class PluginCollectionV3  implements IAimXMLOperations {

    private List<PluginV3> listPlugin = new ArrayList<PluginV3>();

    public void addPlugin(PluginV3 newPlugin) {
        this.listPlugin.add(newPlugin);
    }

    public List<PluginV3> getPlugin() {
        return this.listPlugin;
    }

    public int size() {
        return this.listPlugin.size();
    }

    public PluginV3 get(int index) {
        if (index <= this.listPlugin.size() - 1) {
            return this.listPlugin.get(index);
        }
        return null;
    }

    public PluginCollectionV3(PluginCollectionV4 v4) {
        for (PluginV4 pluginV4 : v4.getListPlugin()) {
            this.addPlugin(new PluginV3(pluginV4));
        }
    }

    public PluginCollectionV3() {

    }

    public PluginCollectionV4 toAimV4(ImageAnnotation imageAnnotation) {
        PluginCollectionV4 res = new PluginCollectionV4();
        for (PluginV3 pluginV3 : this.listPlugin) {
            res.addPlugin(pluginV3.toAimV4(imageAnnotation));
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
        PluginCollectionV3 oth = (PluginCollectionV3) other;
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

    public PluginCollectionV3 getClone() {
        PluginCollectionV3 res = new PluginCollectionV3();
        for (int i = 0; i < this.listPlugin.size(); i++) {
            if (this.listPlugin.get(i) != null) {
                res.addPlugin(this.listPlugin.get(i).getClone());
            }
        }
        return res;
    }
}
