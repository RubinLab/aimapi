package edu.stanford.hakan.aim4api.compability.aimv3;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;


import edu.stanford.hakan.aim4api.base.CD;


/* author Emel Alkim
 * holds lexicon for epad
 */

public class Lexicon extends HashMap<String, CD> {

	
	private static final long serialVersionUID = -8907800188313433052L;
	private static Lexicon ourInstance = null;
	private static final Logger logger = Logger.getLogger("Aim");
	
	
	public static Lexicon getInstance()
	{
		if (ourInstance == null)
			ourInstance = new Lexicon();
		return ourInstance;
	}
	
//	public String getName(CD cdIn) {
//		for (Entry<String, CD> entry : this.entrySet()) {
//	        if (Objects.equals(cdIn, entry.getValue())) {
//	            return entry.getKey();
//	        }
//		}
//		return null;
//
//	}
	
	
//	ml just made up for displaying smt at least (jjvector and riesz) AnnotationExtender ln.58. It was meaningless things like description, etc.
//	 calculation.setDescription("Feature Extraction");
//     calculation.setCodeValue("99EPADC0");
//     calculation.setCodeMeaning("Feature Extraction");
//     calculation.setCodingSchemeDesignator("99EPAD");

	private Lexicon()
	{
		String ePadDesignator="99EPAD",ePadCodeSystem="99EPAD", ePadLexVersion="1.0";
		String algPrefix="99EPADA";
		String dtPrefix="99EPADD";
		
		try {
			this.put("G-D7FE", new CD("G-D7FE","Length","SRT"));
			//algorithms, start with 99EPADA
			this.put(algPrefix+"1", new CD(algPrefix+"1","Calculation",ePadDesignator));
			this.put(algPrefix+"2", new CD(algPrefix+"2","Plugin",ePadDesignator));
			this.put(algPrefix+"3", new CD(algPrefix+"3","JJVector",ePadDesignator));
			
			
			//datatypes, start with 99EPADD
			this.put(dtPrefix+"1", new CD(dtPrefix+"1","Double",ePadDesignator));
			
		} catch (Exception e) {
			logger.info("Error getting lexicon "+ e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
	
	

}
