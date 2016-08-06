package edu.stanford.hakan.aim4api.compability.aimv3;


import java.util.HashMap;
import java.util.logging.Logger;

import edu.stanford.hakan.aim4api.base.CD;


/* author Emel Alkim
 * holds lexicon for epad (just the hash the original full version with db access is in epad-common)
 * this is just for the standart calculations in aim (like length, mean, etc.)
 */

public class Lexicon extends HashMap<String, CD> {

	
	private static final long serialVersionUID = -8907800188313233052L;
	private static Lexicon ourInstance = null;
	private static final Logger logger = Logger.getLogger("Aim");
	private final String ePadDesignator="99EPAD", ePadLexVersion="1";
	
	public static Lexicon getInstance()
	{
		if (ourInstance == null)
			ourInstance = new Lexicon();
		return ourInstance;
	}
	
	public CD getDefaultAlgorithType(){
		return new CD("99EPADA0","NA",this.ePadDesignator, ePadLexVersion);
	}
	public CD getDefaultDataType(){
		return new CD("99EPADD0","NA",this.ePadDesignator, ePadLexVersion);
	}

	
	
//	ml just made up for displaying smt at least (jjvector and riesz) AnnotationExtender ln.58. It was meaningless things like description, etc.
//	 calculation.setDescription("Feature Extraction");
//     calculation.setCodeValue("99EPADF0");
//     calculation.setCodeMeaning("Feature Extraction");
//     calculation.setCodingSchemeDesignator("99EPAD");
	
	private Lexicon()
	{
		
		String algPrefix="99EPADA";
		String dtPrefix="99EPADD";
		
		
		try {
			this.put("G-D7FE", new CD("G-D7FE","Length","SRT"));
			this.put("RID12780", new CD("RID12780","Calculation","RadLex","3.2"));
			this.put("RID39224", new CD("RID39224","Mean value calculation","RadLex","3.12"));
			
			//algorithms, start with 99EPADA
			this.put(algPrefix+"2", new CD(algPrefix+"2","Plugin",ePadDesignator,ePadLexVersion));
			this.put(algPrefix+"3", new CD(algPrefix+"3","JJVector",ePadDesignator,ePadLexVersion));
			this.put(algPrefix+"4", new CD(algPrefix+"4","Area",ePadDesignator,ePadLexVersion));
			this.put(algPrefix+"5", new CD(algPrefix+"5","StandardDeviation",ePadDesignator,ePadLexVersion));
			this.put(algPrefix+"6", new CD(algPrefix+"6","Min",ePadDesignator,ePadLexVersion));
			this.put(algPrefix+"7", new CD(algPrefix+"7","Max",ePadDesignator,ePadLexVersion));
			
			
			//datatypes, start with 99EPADD
			this.put(dtPrefix+"1", new CD(dtPrefix+"1","Double",ePadDesignator,ePadLexVersion));
			
			
		} catch (Exception e) {
			logger.info("Error getting lexicon "+ e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}



}
