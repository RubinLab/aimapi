/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.questions;

import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.IAimXMLOperations;
import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.plugin.Common;
import edu.stanford.hakan.aim4api.plugin.v4.PluginV4;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author Emel Alkim
 */
@SuppressWarnings("serial")
public class QuestionCollection   implements IAimXMLOperations {

    private List<Question> listQuestion = new ArrayList<Question>();
    public QuestionCollection() {

    }

    public QuestionCollection(String str) throws AimException {
        if (str == null || "".equals(str)) {
            return;
        }

        if (str.contains(Common.spliterFour)) {
            String[] array = str.split("\\" + Common.spliterFour);
            for (int i = 0; i < array.length; i++) {
                String strForPluginParam = array[i];
                if (!"".equals(strForPluginParam) && strForPluginParam.contains(Common.spliterFive)) {
                    Question pluginParam = new Question(strForPluginParam);
                    this.addQuestion(pluginParam);
                }
            }
        } else if (str.contains(Common.spliterFive)) {
            Question pluginParam = new Question(str);
            this.addQuestion(pluginParam);
        }
    }


    public void addQuestion(Question newQuestion) throws AimException {
    	if (this.listQuestion.size()>=4) 
    		throw new AimException("You cannot add any more questions"); 
        this.listQuestion.add(newQuestion);
    }

    public int size() {
        return this.listQuestion.size();
    }

    public Question get(int index) {
        if (index <= this.listQuestion.size() - 1) {
            return this.listQuestion.get(index);
        }
        return null;
    }

    public List<Question> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<Question> listQuestion) {
        this.listQuestion = listQuestion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Question pluginParameter : this.listQuestion) {
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
        QuestionCollection oth = (QuestionCollection) other;
        if (this.listQuestion.size() != oth.listQuestion.size()) {
            return false;
        }
        for (int i = 0; i < this.listQuestion.size(); i++) {
            if (this.listQuestion.get(i) == null ? oth.listQuestion.get(i) != null : !this.listQuestion.get(i).isEqualTo(oth.listQuestion.get(i))) {
                return false;
            }
        }
        return true;
    }

    public QuestionCollection getClone() throws AimException {
        QuestionCollection res = new QuestionCollection();
        for (int i = 0; i < this.listQuestion.size(); i++) {
            if (this.listQuestion.get(i) != null) {
                res.addQuestion(this.listQuestion.get(i).getClone());
            }
        }
        return res;
    }
}
