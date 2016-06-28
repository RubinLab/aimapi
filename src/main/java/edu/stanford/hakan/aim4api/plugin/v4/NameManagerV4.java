/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.plugin.v4;

import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.base.ImageAnnotationCollection;
import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.plugin.Common;
import edu.stanford.hakan.aim4api.questions.QuestionCollection;
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
    private int dsoStartIndex = -1;
    private String dsoColor = "#FFFFFF";
    private QuestionCollection questionCollection = new QuestionCollection();

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

    public int getDsoStartIndex() {
        return dsoStartIndex;
    }

    public void setDsoStartIndex(int dsoStartIndex) {
        this.dsoStartIndex = dsoStartIndex;
    }

    public String getDsoColor() {
        return dsoColor;
    }

    public void setDsoColor(String dsoColor) {
        this.dsoColor = dsoColor;
    } 

    public QuestionCollection getQuestionCollection() {
		return questionCollection;
	}

	public void setQuestionCollection(QuestionCollection questionCollection) {
		this.questionCollection = questionCollection;
	}

	private void parse() {

        if (ia.getName() != null && ia.getName().getValue() != null && !"".equals(ia.getName().getValue())) {
            String nameValue = ia.getName().getValue();
            nameValue = nameValue.replace("*sp5*", "~sp5~");
            if (nameValue.contains(Common.spliterOne)) {
                String[] array = nameValue.split("\\" + Common.spliterOne);
                if (array.length == 2) {
                    this.name = new ST(array[0]);
                    this.pluginCollection = new PluginCollectionV4(ia, array[1]);
                } else if (array.length == 3) {
                    this.name = new ST(array[0]);
                    this.pluginCollection = new PluginCollectionV4(ia, array[1]);
                    this.dsoStartIndex = Integer.parseInt(array[2]);
                } else if (array.length == 4) {
                    this.name = new ST(array[0]);
                    Logger.write("plugin parse item "+array[1]);
                    this.pluginCollection = new PluginCollectionV4(ia, array[1]);
                    this.dsoStartIndex = Integer.parseInt(array[2]);
                    this.dsoColor =  array[3];
                
	            } else if (array.length == 5) {
	                this.name = new ST(array[0]);
	                this.pluginCollection = new PluginCollectionV4(ia, array[1]);
	                this.dsoStartIndex = Integer.parseInt(array[2]);
	                this.dsoColor =  array[3];
	                try {
						this.questionCollection = new QuestionCollection(array[4]);
					} catch (AimException e) {
						Logger.write("Question collection couldn't be retrieved "+ e.getMessage());
					}
	            }
            } else {
                this.name = new ST(nameValue);
            }
        }

        this.ia.setName(this.name);
        this.ia.setPluginCollection(this.pluginCollection);
        this.ia.setDsoStartIndex(this.dsoStartIndex);
        this.ia.setDsoColor(this.dsoColor);
        this.ia.setQuestionCollection(this.questionCollection);
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
        if (this.questionCollection != null && this.questionCollection.size() > 0) {
            sb.append(Common.spliterOne);
            sb.append(this.questionCollection.toString());
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
        } else {
            sb.append(Common.spliterOne);
            sb.append("-");
        }

        sb.append(Common.spliterOne);
        sb.append(imageAnnotation.getDsoStartIndex());
        sb.append(Common.spliterOne);
        sb.append(imageAnnotation.getDsoColor());

        if (imageAnnotation.getQuestionCollection() != null && imageAnnotation.getQuestionCollection().size() > 0) {
            sb.append(Common.spliterOne);
            sb.append(imageAnnotation.getQuestionCollection().toString());
        }
        return sb.toString();
    }
}
