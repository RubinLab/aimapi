/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.utility;

import edu.stanford.hakan.aim4api.resources.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.List;
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
    public static final String flagDeleted = "%~#*deleted*#~%";
    public static final String flagVersion = "%~#*version*#~%";

    public static String getSplineFlag() {
        return splineFlag;
    }

    public static String getAimQLXmlFilePath() {
        List<String> listResources = new ArrayList<>();
        List<String> listConfigVariables = new ArrayList<>();
        listResources.add("AimXPath.xml");
        listConfigVariables.add("baseSchemaDir");
        listConfigVariables.add("aimQLXml");
        return getFile(listResources, listConfigVariables, 1);
    }

    public static String getAnnotationListTxtFilePath() {

        List<String> listResources = new ArrayList<>();
        List<String> listConfigVariables = new ArrayList<>();
        listResources.add("AnnotationsList.txt");
        listConfigVariables.add("baseSchemaDir");
        listConfigVariables.add("annotationsList");
        return getFile(listResources, listConfigVariables, 1);
    }

    private static String createSourceInTheTemp(final String resource) {
        final String[] resourceNameExtension = resource.split("\\.");
        String tempDirPath = System.getProperty("java.io.tmpdir");
        File tempDir = new File(tempDirPath);
        File[] filesFromTemp = tempDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.equals(resource);
            }
        });

        if (filesFromTemp.length > 0) {
            return filesFromTemp[0].getPath();
        }

        File fileResource = null;
        URL res = Resource.class.getResource(resource);
        if (res.toString().startsWith("jar:")) {
            try {
                InputStream input = Resource.class.getResourceAsStream(resource);
                fileResource = File.createTempFile(resourceNameExtension[0], "." + resourceNameExtension[1]);
                OutputStream out = new FileOutputStream(fileResource);
                int read;
                byte[] bytes = new byte[1024];

                while ((read = input.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                fileResource.deleteOnExit();
            } catch (IOException ex) {
                return "";
            }
        } else {
            return "";
        }

        filesFromTemp = tempDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith(resourceNameExtension[0]) && name.endsWith(resourceNameExtension[1]);
            }
        });

        if (filesFromTemp.length > 0) {
            try {
                Files.copy(filesFromTemp[0].toPath(), new File(tempDirPath + "/" + resource).toPath(), REPLACE_EXISTING);
                return tempDirPath + "/" + resource;
            } catch (IOException ex) {
                return ex.getMessage();
            }
        }

        File resFile = new File(tempDirPath + "/" + resource);
        if (resFile.exists() && !resFile.isDirectory()) {
            return resFile.getPath();
        }

        return "";
    }

    public static String getXSDPath() {

        List<String> listResources = new ArrayList<>();
        List<String> listConfigVariables = new ArrayList<>();
        listResources.add("AIM_v4.xsd");
        listResources.add("ISO_datatypes_Narrative.xsd");
        listConfigVariables.add("baseSchemaDir");
        listConfigVariables.add("xsdFileV4");
        return getFile(listResources, listConfigVariables, 1);
    }

    private static String getFile(List<String> listResources, List<String> listConfigVariables, int attempt) {
        String res = "";
        if (attempt <= 0) {
            attempt = 1;
        }
        if (attempt == 1) {
            try {

                String fileNameServer = "";
                for (String str : listConfigVariables) {
                    fileNameServer += EPADConfig.getInstance().getStringPropertyValue(str);
                }
                File f = new File(fileNameServer);
                if (f.exists() && !f.isDirectory()) {
                    res = f.getPath();
                } else {
                    res = getFile(listResources, listConfigVariables, 2);
                }

            } catch (Throwable th) {
                if ("".equals(res)) {
                    res = getFile(listResources, listConfigVariables, 2);
                }
            }
        } else if (attempt == 2) {
            try {

                URL url = Resource.class.getResource(listResources.get(0));
                String temp = url.getFile();
                File f = new File(temp);
                if (f.exists() && !f.isDirectory()) {
                    res = f.getPath();
                } else {
                    res = getFile(listResources, listConfigVariables, 3);
                }
            } catch (Throwable th) {
                if ("".equals(res)) {
                    res = getFile(listResources, listConfigVariables, 3);
                }
            }
        } else if (attempt == 3) {
            try {
                String fileNameServer = System.getProperty("user.home") + "/DicomProxy/";
                for (String str : listConfigVariables) {
                    fileNameServer += EPADConfig.getInstance().getStringPropertyValue(str);
                }
                fileNameServer = fileNameServer.replaceAll("\\.", "").replace("//", "/");
                File f = new File(fileNameServer);
                if (f.exists() && !f.isDirectory()) {
                    res = f.getPath();
                } else {
                    res = getFile(listResources, listConfigVariables, 4);
                }
            } catch (Throwable th) {
                if ("".equals(res)) {
                    res = getFile(listResources, listConfigVariables, 4);
                }
            }
        } else if (attempt == 4) {
            try {
                boolean first = true;
                for (String resource : listResources) {
                    if (first) {
                        res = createSourceInTheTemp(resource);
                        first = false;
                    } else {
                        createSourceInTheTemp(resource);
                    }
                }
            } catch (Throwable th) {
                res = "";
            }
        }
        return res;
    }
    
    
     public static String getAimLogPath() {
        List<String> listResources = new ArrayList<>();
        List<String> listConfigVariables = new ArrayList<>();
        listResources.add("AIMAPI.log");
        listConfigVariables.add("baseSchemaDir");
        listConfigVariables.add("aimApiLog");
        return getFile(listResources, listConfigVariables, 1);
    }
}
