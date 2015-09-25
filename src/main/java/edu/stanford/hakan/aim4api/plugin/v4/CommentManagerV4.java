/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.plugin.v4;

import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.plugin.Common;
import edu.stanford.hakan.aim4api.utility.Logger;

/**
 *
 * @author Hakan
 */
@SuppressWarnings("serial")
public class CommentManagerV4 {

    private ST comment = null;
    private PluginCollectionV4 pluginCollection = null;
    private ImageAnnotation ia;

    public CommentManagerV4(ImageAnnotation imageAnnotation) {
        ia = imageAnnotation;
        parse();
    }
    public CommentManagerV4(ST comment, PluginCollectionV4 pluginCollection, ImageAnnotation imageAnnotation) {
        ia = imageAnnotation;
        this.comment = comment;
        this.pluginCollection = pluginCollection;
    }


    public ST getComment() {
        if (this.comment.getValue().contains(Common.spliterOne)) {
            parse();
        }
        return comment;
    }

    public PluginCollectionV4 getPluginCollection() {
        return pluginCollection;
    }

    private void parse() {
        if (ia.getComment() == null || ia.getComment().getValue() == null || "".equals(ia.getComment().getValue())) {
            this.comment = new ST("");
            return;
        }

        String commentValue = ia.getComment().getValue();
        commentValue = commentValue.replace("*sp5*", "~sp5~");
        if (commentValue.contains(Common.spliterOne)) {
            String[] array = commentValue.split("\\" + Common.spliterOne);
            this.comment = new ST(array[0]);
            this.pluginCollection = new PluginCollectionV4(ia, array[1]);
        } else {
            this.comment = new ST(commentValue);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.getComment() == null || this.getComment().getValue() == null || "".equals(this.getComment().getValue())) {
            sb.append("-");
        } else {
            sb.append(this.getComment().getValue());
        }

        if (this.pluginCollection != null && this.pluginCollection.size() > 0) {
            sb.append(Common.spliterOne);
            sb.append(this.pluginCollection.toString());
        }
        return sb.toString();
    }
}
