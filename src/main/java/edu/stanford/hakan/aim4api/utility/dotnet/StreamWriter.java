/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.hakan.aim4api.utility.dotnet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class StreamWriter {

    private PrintWriter writer = null;
    private List<String> listLines = new ArrayList<String>();
    private List<String> listExistingLines = new ArrayList<String>();

    public StreamWriter(String path, boolean append) throws IOException {
        writer = new PrintWriter(new FileWriter(path,append));
    }

//    public void WriteLine(String line) throws IOException {
//        this.listLines.add(line);
//    }
//
//    public void Close() throws IOException {
//
//        for (int i = 0; i < this.listLines.size(); i++) {
//            String str = this.listLines.get(i);
////            if (i >= this.listLines.size() - 1) {
////                writer.println(str);.write(str);
////            } else {
////                writer.write(str + "\r\n");
////            }
//            
//            writer.println(str);
//        }
//
//        this.writer.close();
//    }
    
        public void WriteLine(String line) throws IOException {
        writer.println(line);
    }
    public void Close() {
        writer.close();
    }
}
