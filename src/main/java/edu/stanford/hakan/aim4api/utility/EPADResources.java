/*
 * Copyright 2012 The Board of Trustees of the Leland Stanford Junior University.
 * Author: Daniel Rubin, Alan Snyder, Debra Willrett. All rights reserved. Possession
 * or use of this program is subject to the terms and conditions of the Academic
 * Software License Agreement available at:
 *   http://epad.stanford.edu/license/
 */
package edu.stanford.hakan.aim4api.utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 *
 * @author martin
 */
public class EPADResources {

    private EPADResources() {
    }

    public static String getEPADWebServerConfigFilePath() {
        return getEPADWebServerEtcDir() + "proxy-config.properties";
    }

    public static String getEPADWebServerEtcDir() {
        return getEPADWebServerBaseDir() + "etc/";
    }

    public static String getEPADWebServerBaseDir() {
        return System.getProperty("user.home") + "/DicomProxy/";
    }

}
