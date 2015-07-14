/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.audittrail;

import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.base.User;

/**
 *
 * @author Hakan
 */
public class STUser {

    private ST st = new ST();
    private User user = new User();
    public static String seperator = "|~*$%%$*~|";

    public STUser() {

    }

    public STUser(ST st, User user) {
        this.st = st;
        this.user = user;
    }

    public STUser(String str) {
        String[] array = str.split("[" + seperator + "]+");
        this.st = new ST(array[0]);
        if (array.length > 1) {
            this.user = new User();
            this.user.setName(new ST(array[1]));
            this.user.setLoginName(new ST(array[2]));
            this.user.setRoleInTrial(new ST(array[3]));
        }
    }

    public ST getSt() {
        return st;
    }

    public void setSt(ST st) {
        this.st = st;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        String res = "";
        res += this.st.getValue() + seperator;
        if (this.user.getName() != null && this.user.getName().getValue() != null) {
            res += this.user.getName().getValue() + seperator;
        } else {
            res += " " + seperator;
        }
        if (this.user.getLoginName() != null && this.user.getLoginName().getValue() != null) {
            res += this.user.getLoginName().getValue() + seperator;
        } else {
            res += " " + seperator;
        }
        if (this.user.getRoleInTrial() != null && this.user.getRoleInTrial().getValue() != null) {
            res += this.user.getRoleInTrial().getValue() + seperator;
        } else {
            res += " " + seperator;
        }
        return res;
    }
}
