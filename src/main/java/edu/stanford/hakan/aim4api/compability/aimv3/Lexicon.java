package edu.stanford.hakan.aim4api.compability.aimv3;

//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;


import edu.stanford.hakan.aim4api.base.CD;


/* author Emel Alkim
 * holds lexicon for epad
 */

public class Lexicon extends HashMap<String, CD> {

	
	private static final long serialVersionUID = -8907800188313433052L;
	private static Lexicon ourInstance = null;
	private static final Logger logger = Logger.getLogger("Aim");
	private static String FILE_NAME="lexicon.csv";
	private final String ePadDesignator="99EPAD",ePadCodeSystem="99EPAD", ePadLexVersion="1.0";
	
	public static Lexicon getInstance()
	{
		if (ourInstance == null)
			ourInstance = new Lexicon();
		return ourInstance;
	}
	
	public CD getDefaultAlgorithType(){
		return new CD("99EPADA0","NA",this.ePadDesignator);
	}
	public CD getDefaultDataType(){
		return new CD("99EPADD0","NA",this.ePadDesignator);
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
		
		//use this instead
//		loadFile(EPADResources.getEPADWebServerConfigFilePath()+"/"+FILE_NAME);
		
		
		String algPrefix="99EPADA";
		String dtPrefix="99EPADD";
		
		
		//RID12780 calculation jjvector!!!
		
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
//	public void loadFile(String csvFile) {
//		BufferedReader br;
//		try {
//			br = new BufferedReader(new FileReader(csvFile));
//		
//			String line="";
//			String csvSplitBy = ",";
//			while ((line = br.readLine()) != null) {
//				line = line.trim(); // process the line.
//				// use comma as separator
//				if (!line.equals("")) {
//					String[] lex = line.split(csvSplitBy);
//		
//					this.put(lex[1], new CD(lex[1],lex[0],lex[2]));
//				}
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//			
//	}
	
	
//	public void loadFile(String csvFile ) {
//		StreamReader br = null;
//		String line = "";
//		String cvsSplitBy = ",";
//
//		try {
//			br = new StreamReader(csvFile);
//			while ((line = br.ReadLine()) != null) {
//
//				// use comma as separator
//				String[] lex = line.split(cvsSplitBy);
//
//				this.put(lex[1], new CD(lex[1],lex[0],lex[2]));
//
//			}
//
//		
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (br != null) {
//				try {
//					br.Close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

}
