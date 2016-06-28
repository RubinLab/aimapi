/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.questions;

import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.IAimXMLOperations;
import edu.stanford.hakan.aim4api.plugin.Common;
import org.w3c.dom.Document;
import org.w3c.dom.Node;


/**
 *
 * @author Emel Alkim
 */
@SuppressWarnings("serial")
public class Question  implements IAimXMLOperations {

    private String question;
    private String answer;

    public Question() {
    }

    public Question(String question, String answer) throws AimException {
        setQuestion(question);
        setAnswer(answer);
    }

    public Question(String questionAnswerPair) throws AimException {
        if (questionAnswerPair.contains(Common.spliterFive)) {
            String[] res = questionAnswerPair.split("\\" + Common.spliterFive);
            setQuestion(res[0]);
            setAnswer(res[1]);
        }
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) throws AimException {
    	if (question.length()>128)
    		throw new AimException("Question too long");
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) throws AimException {
    	if (answer.length()>512)
    		throw new AimException("Answer too long");
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Question)) {
            return false;
        }
        Question c = (Question) o;
        if (c.question.toLowerCase().trim().equals(this.question.toLowerCase().trim())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getQuestion());
        sb.append(Common.spliterFive);
        sb.append(this.getAnswer());
        return sb.toString().trim();
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
    	Question oth = (Question) other;
        if (this.question == null ? oth.question != null : !this.question.equals(oth.question)) {
            return false;
        }
        if (this.answer == null ? oth.answer != null : !this.answer.equals(oth.answer)) {
            return false;
        }
        return true;
    }

    public Question getClone() throws AimException {
    	Question res = new Question();
        if (this.getQuestion() != null) {
            res.setQuestion(this.getQuestion());
        }
        if (this.getAnswer()!= null) {
            res.setAnswer(this.getAnswer());
        }
        return res;
    }

}
