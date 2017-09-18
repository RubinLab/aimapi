package edu.stanford.hakan.aim4api.compability.aimv3;


import java.util.HashMap;
import java.util.Map;
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
		String pluginPrefix="99EPADP";
		//removelist
		//RID39224
		//99EPADA5
		//99EPADA6
		//99EPADA7
		
		try {
			//lexicon items that are used specificly by aimapi should be placed in the dictionary 
			//this is used to achieve independence from epad-ws and db
			this.put("G-D7FE", new CD("G-D7FE","Length","SRT"));
			this.put("G-A186", new CD("G-A186","ShortAxis","SRT"));
			this.put("G-A185", new CD("G-A185","LongAxis","SRT"));
			
			this.put("RID12780", new CD("RID12780","Calculation","RadLex","3.2"));
			//update with Andrey's list
			this.put("R-00317", new CD("R-00317","Mean","SRT"));
			this.put("R-404FB", new CD("R-404FB","Minimum","SRT"));
			this.put("G-A437", new CD("G-A437","Maximum","SRT"));
			this.put("R-10047", new CD("R-10047","Standard Deviation","SRT"));
			
			
			//algorithms, start with 99EPADA
			this.put(algPrefix+"2", new CD(algPrefix+"2","Plugin",ePadDesignator,ePadLexVersion));
			this.put(algPrefix+"4", new CD(algPrefix+"4","Area",ePadDesignator,ePadLexVersion));
			
			//removed as epad terms and added as srt terms
//			this.put(algPrefix+"5", new CD(algPrefix+"5","StandardDeviation",ePadDesignator,ePadLexVersion));
//			this.put(algPrefix+"6", new CD(algPrefix+"6","Min",ePadDesignator,ePadLexVersion));
//			this.put(algPrefix+"7", new CD(algPrefix+"7","Max",ePadDesignator,ePadLexVersion));
			
			
			//datatypes, start with 99EPADD
			this.put(dtPrefix+"1", new CD(dtPrefix+"1","Double",ePadDesignator,ePadLexVersion));
			
			//plugins,  start with 99EPADP
			this.put(pluginPrefix+"1", new CD(pluginPrefix+"1","JJVector",ePadDesignator,ePadLexVersion));
			this.put(pluginPrefix+"2", new CD(pluginPrefix+"2","Qifp3D",ePadDesignator,ePadLexVersion));
			
			//UCUM lookup
			this.put("SUV", new CD("{SUVbw}g/ml","Standardized Uptake Value body weight","UCUM"));
			this.put("HU", new CD("[hnsf'U]","Hounsfield Unit","UCUM"));
			this.put("cm", new CD("cm","Centimeter","UCUM"));

			
		} catch (Exception e) {
			logger.info("Error getting lexicon "+ e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	public String reverseGet(CD value){
		String key=null;
		
		for (Map.Entry<String, CD> entry : this.entrySet())
		{	
			//code should be unique
			if (entry.getValue().getCode()!=null && value.getCode()!=null && entry.getValue().getCode().equals(value.getCode()) ){
				key=entry.getKey();
			}
		}
		
		return key;
	}


}
