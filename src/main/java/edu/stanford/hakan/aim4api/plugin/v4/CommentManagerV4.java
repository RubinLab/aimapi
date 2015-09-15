/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.plugin.v4;

import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.plugin.Common;

/**
 *
 * @author Hakan
 */
public class CommentManagerV4 {

    private ST comment = null;
    private PluginCollectionV4 pluginCollection = null;

    public ST getComment() {
        return comment;
    }

    public PluginCollectionV4 getPluginCollection() {
        return pluginCollection;
    }

    public CommentManagerV4(ImageAnnotation imageAnnotation) {
        if (imageAnnotation.getComment() == null || imageAnnotation.getComment().getValue() == null || "".equals(imageAnnotation.getComment().getValue())) {
            return;
        }

        String commentValue = imageAnnotation.getComment().getValue();
        if (commentValue.contains(Common.spliterOne)) {
            String[] array = commentValue.split("\\" + Common.spliterOne);
            this.comment = new ST(array[0]);
            this.pluginCollection = new PluginCollectionV4(imageAnnotation, array[1]);
        } else {
            this.comment = new ST(commentValue);
        }
    }

    public CommentManagerV4(ST comment, PluginCollectionV4 pluginCollection) {
        this.comment = comment;
        this.pluginCollection = pluginCollection;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        if (this.comment == null || this.comment.getValue() == null || "".equals(this.comment.getValue())) {
            sb.append("-");
        } else {
            sb.append(this.comment.getValue());
        }

        if (this.pluginCollection != null && this.pluginCollection.size() > 0) {
            sb.append(Common.spliterOne);
            sb.append(this.pluginCollection.toString());
        }
        return sb.toString();
    }

}
