/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.hakan.aim4api.utility.dotnet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class StreamWriter {

    private BufferedWriter writer = null;
    private List<String> listLines = new ArrayList<String>();

    public StreamWriter(String path) throws IOException {
        writer = new BufferedWriter(new FileWriter(path));
    }

    public void WriteLine(String line) throws IOException {
        this.listLines.add(line);
    }

    public void Close() throws IOException {

        for (int i = 0; i < this.listLines.size(); i++) {
            String str = this.listLines.get(i);
            if (i >= this.listLines.size() - 1) {
                writer.write(str);
            } else {
                writer.write(str + "\r\n");
            }
        }

        this.writer.close();
    }
}
