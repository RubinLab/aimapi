/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.edu.stanford.hakan.aim4api.compability.aimv3;

import main.java.edu.stanford.hakan.aim4api.base.ST;

/**
 *
 * @author Hakan
 */
public class User extends edu.stanford.hakan.aim3api.base.User {

//    public User(edu.stanford.hakan.aim3api.base.User user) {
//        this.setCagridId(user.getCagridId());
//        this.setLoginName(user.getLoginName());
//        this.setName(user.getName());
//        this.setNumberWithinRoleOfClinicalTrial(user.getNumberWithinRoleOfClinicalTrial());
//        this.setRoleInTrial(user.getRoleInTrial());
//    }

    public main.java.edu.stanford.hakan.aim4api.base.User toAimV4() {
        main.java.edu.stanford.hakan.aim4api.base.User res = new main.java.edu.stanford.hakan.aim4api.base.User();
        res.setLoginName(new ST(this.getLoginName()));
        res.setName(new ST(this.getName()));
        res.setNumberWithinRoleOfClinicalTrial(this.getNumberWithinRoleOfClinicalTrial());
        res.setRoleInTrial(new ST(this.getRoleInTrial()));
        return res;
    }
}
