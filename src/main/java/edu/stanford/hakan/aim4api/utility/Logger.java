/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.hakan.aim4api.utility;

import edu.stanford.hakan.aim4api.utility.dotnet.StreamWriter;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 *
 * @author Hakan
 */
public class Logger {
 
    public static void write(String line) {
        File f = new File(Globals.getAimLogPath());
        if (f.exists() && !f.isDirectory()) {
            try {
                StreamWriter sw = new StreamWriter(Globals.getAimLogPath(), true);
                sw.WriteLine(line);
                sw.Close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
