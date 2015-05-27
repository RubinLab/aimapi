/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.hakan.aim4api.utility;

import edu.stanford.hakan.aim4api.utility.dotnet.StreamWriter;
import java.io.IOException;

/**
 *
 * @author Hakan
 */
public class Logger {
    
    public static void write(String line) throws IOException
    {
        StreamWriter sw = new StreamWriter(Globals.getAimLogPath(),true);
        sw.WriteLine(line);
        sw.Close();
    }
}
