/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.database.exist;

import edu.stanford.hakan.aim4api.base.AimException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hakan
 */
public class ExistResponderThread extends Thread {

    private Thread t;
    private String params = "";
    private String serverURL = "";
    private String query = "";
    private String dbUserName = "";
    private String dbUserPassword = "";
    int startIndex = -1;
    int pageCount = -1;
    private String respond = "";
    private boolean finished = false;

    public ExistResponderThread(String params) {
        this.params = params;
        String[] paramsArray = params.split("~");
        this.serverURL = paramsArray[0];
        this.query = paramsArray[1];
        this.dbUserName = paramsArray[2];
        this.dbUserPassword = paramsArray[3];
        this.startIndex = Integer.parseInt(paramsArray[4]);
        this.pageCount = Integer.parseInt(paramsArray[5]);
    }

    @Override
    public void run() {
        try {
            this.respond = ExistManager.getXMLStringFromExistWithStartIndexCount(serverURL, query, dbUserName, dbUserPassword, startIndex, pageCount);
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            Calendar cal = Calendar.getInstance();
//            System.out.println("=== Thread ended (" + pageCount + ")" + dateFormat.format(cal.getTime()));
            finished = true;
        } catch (AimException ex) {
            Logger.getLogger(ExistResponderThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Calendar cal = Calendar.getInstance();
//        System.out.println("=== Thread start " + dateFormat.format(cal.getTime()));
        if (t == null) {
            t = new Thread(this, this.params);
            t.start();
        }
    }

    public String getRespond() {
        return respond;
    }

    public void setRespond(String respond) {
        this.respond = respond;
    }

    public boolean isFinished() {
        return finished;
    }

    
    

}
