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
			this.put("RID28668", new CD("RID28668","Volume","Radlex","3.14"));
			this.put("99EPADA4", new CD("99EPADA4","Area","99EPAD"));
			
			//algorithms, start with 99EPADA
			this.put(algPrefix+"2", new CD(algPrefix+"2","Plugin",ePadDesignator,ePadLexVersion));
			this.put(algPrefix+"4", new CD(algPrefix+"4","Area",ePadDesignator,ePadLexVersion));
			
			//removed as epad terms and added as srt terms
//			this.put(algPrefix+"5", new CD(algPrefix+"5","StandardDeviation",ePadDesignator,ePadLexVersion));
//			this.put(algPrefix+"6", new CD(algPrefix+"6","Min",ePadDesignator,ePadLexVersion));
//			this.put(algPrefix+"7", new CD(algPrefix+"7","Max",ePadDesignator,ePadLexVersion));
			
			
			//datatypes, start with 99EPADD
			this.put("C48870", new CD("C48870","Double","NCI"));
			
			//plugins,  start with 99EPADP
			this.put(pluginPrefix+"2", new CD(pluginPrefix+"2","Qifp3D",ePadDesignator,ePadLexVersion));
			
			this.put("jjv-5", new CD("jjv-5","jjvector",ePadDesignator,ePadLexVersion));
			this.put("vanderbilt", new CD("vanderbilt","vanderbilt",ePadDesignator,ePadLexVersion));
			this.put("smp-1", new CD("smp-1","sampleplugin",ePadDesignator,ePadLexVersion));
			this.put("rsz-1", new CD("rsz-1","riesz",ePadDesignator,ePadLexVersion));
			this.put("lseg-1", new CD("lseg-1","lesionseg",ePadDesignator,ePadLexVersion));
			this.put("ts-1", new CD("ts-1","tedseg",ePadDesignator,ePadLexVersion));

			//others
			
			this.put("SEG", new CD("SEG","SEG Only",ePadDesignator,ePadLexVersion));
			this.put("ROI", new CD("ROI","ROI Only",ePadDesignator,ePadLexVersion));
			
			this.put("jjv-1", new CD("jjv-1","Lung CT",ePadDesignator,ePadLexVersion));
			this.put("jjv-2", new CD("jjv-2","Liver CT",ePadDesignator,ePadLexVersion));
			this.put("jjv-3", new CD("jjv-3","Breast MRI",ePadDesignator,ePadLexVersion));
			this.put("jjv-4", new CD("jjv-4","Bone X-ray",ePadDesignator,ePadLexVersion));
			
			this.put("RECIST", new CD("RECIST","Tumor assessment",ePadDesignator,ePadLexVersion));
			this.put("S71", new CD("S71","Target Lesion",ePadDesignator,ePadLexVersion));
			this.put("S72", new CD("S72","Non-Target Lesion",ePadDesignator,ePadLexVersion));
			this.put("S73", new CD("S73","New Lesion",ePadDesignator,ePadLexVersion));
			this.put("S74", new CD("S74","Resolved Lesion",ePadDesignator,ePadLexVersion));
			this.put("S81", new CD("S81","Lesion Baseline Evaluation",ePadDesignator,ePadLexVersion));
			this.put("S82", new CD("S82","Lesion Followup Evaluation",ePadDesignator,ePadLexVersion));
			this.put("S83", new CD("S83","tracked",ePadDesignator,ePadLexVersion));
			this.put("S84", new CD("S84","not tracked",ePadDesignator,ePadLexVersion));
			
			this.put("RECIST_v2", new CD("RECIST_v2","Tumor assessment",ePadDesignator,ePadLexVersion));
			this.put("S99", new CD("S99","undefined organ",ePadDesignator,ePadLexVersion));
			this.put("S90", new CD("S90","FU Number (0=Baseline)",ePadDesignator,ePadLexVersion));
			this.put("S75", new CD("S75","Non-cancer Lesion",ePadDesignator,ePadLexVersion));
			this.put("S86", new CD("S86","Evaluable",ePadDesignator,ePadLexVersion));
			
			this.put("rsz-2", new CD("rsz-2","energie",ePadDesignator,ePadLexVersion));
			
		} catch (Exception e) {
			logger.info("Error getting lexicon "+ e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}



}
