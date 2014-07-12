/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.utility.dotnet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class StreamReader {

    private final BufferedReader br;
    List<String> listLines = new ArrayList<String>();
    private int currentLineIndex = 0;

    public StreamReader(String path) throws FileNotFoundException, IOException {
        this.br = new BufferedReader(new FileReader(path));
        String line = this.br.readLine();
        while (line != null) {
            listLines.add(line);
            line = this.br.readLine();
        }
        this.br.close();
    }

    public int Peek() {
        return this.listLines.size() - this.currentLineIndex - 1;
    }

    public String ReadLine() {
        String res = this.listLines.get(this.currentLineIndex);
        this.currentLineIndex++;
        return res;
    }

    public void Close() throws IOException {
        this.br.close();
    }

    public String ReadToEnd() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.listLines.size(); i++) {
            String str = this.listLines.get(i);
            if (i >= this.listLines.size() - 1) {
                sb.append(str);
            } else {
                sb.append(str).append("\r\n");
            }

        }
        return sb.toString();
    }
}
