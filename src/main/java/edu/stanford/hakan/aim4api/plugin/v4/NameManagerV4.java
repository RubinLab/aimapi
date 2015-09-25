/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.plugin.v4;

import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.base.ImageAnnotationCollection;
import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.plugin.Common;
import edu.stanford.hakan.aim4api.utility.Logger;

/**
 *
 * @author Hakan
 */
@SuppressWarnings("serial")
public class NameManagerV4 {

    private ST name = new ST("");
    private PluginCollectionV4 pluginCollection = new PluginCollectionV4();
    private ImageAnnotation ia;

     public NameManagerV4() {
    }
     
    public NameManagerV4(ImageAnnotation imageAnnotation) {
        ia = imageAnnotation;
        parse();
    }
    
    
    public NameManagerV4(ImageAnnotationCollection imageAnnotationCollection) {
        ia = imageAnnotationCollection.getImageAnnotation();
        parse();
    }
    
    
    public NameManagerV4(ST name, PluginCollectionV4 pluginCollection, ImageAnnotation imageAnnotation) {
        ia = imageAnnotation;
        this.name = name;
        this.pluginCollection = pluginCollection;
    }


    public ST getName() {
        if (this.name.getValue().contains(Common.spliterOne)) {
            parse();
        }
        return name;
    }

    public PluginCollectionV4 getPluginCollection() {
        return pluginCollection;
    }

    private void parse() {

        if (ia.getName() != null && ia.getName().getValue() != null && !"".equals(ia.getName().getValue())) {
            String nameValue = ia.getName().getValue();
            nameValue = nameValue.replace("*sp5*", "~sp5~");
            if (nameValue.contains(Common.spliterOne)) {
                String[] array = nameValue.split("\\" + Common.spliterOne);
                this.name = new ST(array[0]);
                this.pluginCollection = new PluginCollectionV4(ia, array[1]);
            } else {
                this.name = new ST(nameValue);
            }
        }

        this.ia.setName(this.name);
        this.ia.setPluginCollection(this.pluginCollection);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.getName()== null || this.getName().getValue() == null || "".equals(this.getName().getValue())) {
            sb.append("-");
        } else {
            sb.append(this.getName().getValue());
        }

        if (this.pluginCollection != null && this.pluginCollection.size() > 0) {
            sb.append(Common.spliterOne);
            sb.append(this.pluginCollection.toString());
        }
        return sb.toString();
    }
    
    
    public String toString(ImageAnnotation imageAnnotation) {
        StringBuilder sb = new StringBuilder();
        if (imageAnnotation.getName()== null || imageAnnotation.getName().getValue() == null || "".equals(imageAnnotation.getName().getValue())) {
            sb.append("-");
        } else {
            String nameValue = imageAnnotation.getName().getValue();
            if (nameValue.contains(Common.spliterOne)) {
                String[] array = nameValue.split("\\" + Common.spliterOne);
                nameValue = array[0];
            }
            sb.append(nameValue);
        }

        if (imageAnnotation.getPluginCollection() != null && imageAnnotation.getPluginCollection().size() > 0) {
            sb.append(Common.spliterOne);
            sb.append(imageAnnotation.getPluginCollection().toString());
        }
        return sb.toString();
    }
}
