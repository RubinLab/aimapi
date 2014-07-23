/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.utility;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hakan
 */
public class Globals {

    private static final String aimQLXmlFilePath = "";
    private static final String annotationListTxtFilePath = "";

    public static String getAimQLXmlFilePath() {
        try {
            URL xmlPath = Globals.class.getResource("../files/AimXPath.xml");
            String fileName = xmlPath.getFile();
            File f = new File(fileName);
            if (f.exists() && !f.isDirectory()) {
                return fileName;
            }
        } catch (Exception ex) {
            String fileNameServer = EPADConfig.getInstance().getStringPropertyValue("baseSchemaDir")
                    + EPADConfig.getInstance().getStringPropertyValue("aimQLXml");
            File f = new File(fileNameServer);
            if (f.exists() && !f.isDirectory()) {
                return fileNameServer;
            }
            String fileName = "AimXPath.xml";
            f = new File(fileName);
            if (!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException exIO) {
                }
                return fileName;
            }
        }
        return "";
    }

    public static String getAnnotationListTxtFilePath() {
        try {
            URL xmlPath = Globals.class
                    .getResource("../files/AnnotationsList.txt");
            String fileName = xmlPath.getFile();
            File f = new File(fileName);
            if (f.exists() && !f.isDirectory()) {
                return fileName;
            }
        } catch (Exception ex) {
            String fileNameServer = EPADConfig.getInstance().getStringPropertyValue("baseSchemaDir")
                + "AnnotationsList.txt";
            File f = new File(fileNameServer);
            if (f.exists() && !f.isDirectory()) {
                return fileNameServer;
            }
            String fileName = "AnnotationsList.txt";
            f = new File(fileName);
            if (!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException exIO) {
                }
                return fileName;
            }
        }
        return "";
    }

}
