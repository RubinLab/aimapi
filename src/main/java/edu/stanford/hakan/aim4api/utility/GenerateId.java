/*
 * Copyright (c) 2011, The Board of Trustees of the Leland Stanford Junior 
 * University, creator Daniel L. Rubin. 
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this 
 * list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package edu.stanford.hakan.aim4api.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author localadmin
 */
public class GenerateId {
	
		// david clunie's code (modified version for gwt)
	 	static public String getUUIDBasedOID() {
	 		UUIDBasedOID uidgen=new UUIDBasedOID();
	 		return uidgen.getOID();
	 	}
        static public String getUUID() {
        	System.out.println("uid:"+getUUIDBasedOID());
            return getUUIDBasedOID();
            
//            return getDicomUUID();
//        String res = "";
//        int idLenght = 40;
//        Random randomGenerator = new Random();
//        char[] bag = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'y', 'z', 'w', 'x', 'q'};
//        for (int i = 0; i < idLenght; i++) {
//            int randomInt = randomGenerator.nextInt(bag.length);
//            res = res.concat(String.valueOf(bag[randomInt]));
//        }
//        StringBuilder sb = new StringBuilder(res);
//        return sb.toString();
    }
        
      
        static public String getDicomUUID() { 
                    String res = "";
        Random randomGenerator = new Random();
        List<Integer> listComponentLength = new ArrayList<Integer>();
        listComponentLength.add(1);
        listComponentLength.add(2);
        listComponentLength.add(3);
        listComponentLength.add(4);
        listComponentLength.add(5);
        listComponentLength.add(6);
        listComponentLength.add(7);
        listComponentLength.add(8);
        listComponentLength.add(9);
        listComponentLength.add(10);

        while (listComponentLength.size() > 0) {
            int componentLenght = randomGenerator.nextInt(11);
            if (listComponentLength.remove((Integer) componentLenght)) {
                for (int i = 0; i < componentLenght; i++) {
                    if (i == 0) {
                        res = res.concat(String.valueOf(randomGenerator.nextInt(9) + 1));
                    } else {
                        res = res.concat(String.valueOf(randomGenerator.nextInt(10)));
                    }
                }
                if(listComponentLength.size() > 0)
                res = res.concat(".");
            }
        }
        
        return res;
        } 
}
