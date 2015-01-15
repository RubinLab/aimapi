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
    private static final String splineFlag = "###.spline.###";

    public static String getSplineFlag() {
        return splineFlag;
    }

    public static String getAimQLXmlFilePath() {
        try {
            URL xmlPath = Globals.class.getResource("../files/AimXPath.xml");
            String fileName = xmlPath.getFile();
            File f = new File(fileName);
            if (f.exists() && !f.isDirectory()) {
                return fileName;
            }
        } catch (Exception ex1) {
            try {
                String fileNameServer = EPADConfig.getInstance().getStringPropertyValue("baseSchemaDir")
                        + EPADConfig.getInstance().getStringPropertyValue("aimQLXml");
                File f = new File(fileNameServer);
                if (f.exists() && !f.isDirectory()) {
                    return fileNameServer;
                }
            } catch (Exception ex2) {
                String fileName = "AimXPath.xml";
                File f = new File(fileName);
                if (!f.exists()) {
                    try {
                        f.createNewFile();
                        return fileName;
                    } catch (Exception ex3) {
                        return "";
                    }
                }
                return fileName;
            }
        }
        return "";
    }

    public static String getAnnotationListTxtFilePath() {
        try {
            URL xmlPath = Globals.class.getResource("../files/AnnotationsList.txt");
            String fileName = xmlPath.getFile();
            File f = new File(fileName);
            if (f.exists() && !f.isDirectory()) {
                return fileName;
            }
        } catch (Exception ex1) {
            try {
                String fileNameServer = EPADConfig.getInstance().getStringPropertyValue("baseSchemaDir")
                        + "AnnotationsList.txt";
                File f = new File(fileNameServer);
                if (f.exists() && !f.isDirectory()) {
                    return fileNameServer;
                }
            } catch (Exception ex2) {
                String fileName = "AnnotationsList.txt";
                File f = new File(fileName);
                if (!f.exists()) {
                    try {
                        f.createNewFile();
                        return fileName;
                    } catch (Exception ex3) {
                        return "";
                    }
                }
                return fileName;
            }
        }
        return "";
    }

}
