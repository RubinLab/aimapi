/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.utility;

import edu.stanford.hakan.aim4api.resources.Resource;
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
            URL xmlPath = Resource.class.getResource("AimXPath.xml");
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

//    public static String getAimQLXmlFilePath() {
//
//        try {
//            URL xmlPath = Globals.class.getResource("../resources/AimXPath.xml");
//            String fileName = xmlPath.getFile();
//            File f = new File(fileName);
//            if (f.exists() && !f.isDirectory()) {
//                return fileName;
//            }
//        } catch (Exception ex) {
//
//            try {
//                String fileName = EPADConfig.getInstance().getStringPropertyValue("baseSchemaDir")
//                        + EPADConfig.getInstance().getStringPropertyValue("aimQLXml");
//                File f = new File(fileName);
//                if (f.exists() && !f.isDirectory()) {
//                    return fileName;
//                }
//            } catch (Exception ex1) {
//
//                String fileName = "AimXPath.xml";
//                File f = new File(fileName);
//                if (f.exists() && !f.isDirectory()) {
//                    return fileName;
//                }
//
//                fileName = "C:\\Temp\\AimXPath.xml";
//                f = new File(fileName);
//                if (f.exists() && !f.isDirectory()) {
//                    return fileName;
//                }
//            }
//        }
//
//        return "";
//    }

    public static String getAnnotationListTxtFilePath() {
        try {
            URL xmlPath = Resource.class.getResource("AnnotationsList.txt");
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

    public static String getXSDPath() {
        URL xsdPath = Resource.class.getResource("AIM_v4.xsd");
        if (xsdPath != null) {
            File f = new File(xsdPath.getFile());
            if (f.exists() && !f.isDirectory()) {
                return xsdPath.getFile();
            }

        }
        return "";
    }
}
