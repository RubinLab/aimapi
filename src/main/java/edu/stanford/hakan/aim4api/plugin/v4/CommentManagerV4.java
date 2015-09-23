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
        Logger.write("CommentManagerV4-s");
        ia = imageAnnotation;
        parse();
        Logger.write("CommentManagerV4-e");
    }

    public ST getComment() {
        Logger.write("CommentManagerV4-getComment-s");
        if (true) {
            return new ST("-test5-");
        }

        if (this.comment.getValue().contains(Common.spliterOne)) {
            parse();
        }
        
        Logger.write("CommentManagerV4-getComment-e");
        return comment;
    }

    public PluginCollectionV4 getPluginCollection() {
        return pluginCollection;
    }

    private void parse() {
        Logger.write("CommentManagerV4-parse-s");
        if (true) {
            this.comment = new ST("-test2-");
            return;
        }

        if (ia.getComment() == null || ia.getComment().getValue() == null || "".equals(ia.getComment().getValue())) {
            Logger.write("CommentManagerV4-parse-returning null");
            return;
        }

        String commentValue = ia.getComment().getValue();
        commentValue = commentValue.replace("*sp5*", "~sp5~");

        Logger.write("CommentManagerV4-parse-commentValue = " + commentValue);

        if (commentValue.contains(Common.spliterOne)) {
            Logger.write("CommentManagerV4-parse-in the if");
            String[] array = commentValue.split("\\" + Common.spliterOne);
            this.comment = new ST(array[0]);
            this.pluginCollection = new PluginCollectionV4(ia, array[1]);
            Logger.write("CommentManagerV4-parse-in the if-end");
        } else {
            Logger.write("CommentManagerV4-parse-in the else");
            this.comment = new ST(commentValue);
        }
        Logger.write("CommentManagerV4-parse-e");
    }

    public CommentManagerV4(ST comment, PluginCollectionV4 pluginCollection) {
        if (true) {
            this.comment = new ST("-test3-");
        }
        Logger.write("CommentManagerV4-CommentManagerV4-s");
        this.comment = comment;
        this.pluginCollection = pluginCollection;
        Logger.write("CommentManagerV4-CommentManagerV4-e");
    }

    @Override
    public String toString() {
        if (true) {
            return "-test2-";
        }

        Logger.write("CommentManagerV4-toString-s");
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
        Logger.write("CommentManagerV4-toString-e");
        return sb.toString();
    }
}
