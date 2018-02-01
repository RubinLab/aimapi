package edu.stanford.hakan.aim4api.compability.aimv3;

import java.util.HashMap;
import java.util.logging.Logger;

import edu.stanford.hakan.aim4api.base.CD;

public class Modality extends HashMap<String, CD> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2347532011411890309L;
	private static Modality ourInstance = null;
	private static final Logger logger = Logger.getLogger("Aim");
	
	
	public static Modality getInstance()
	{
		if (ourInstance == null)
			ourInstance = new Modality();
		return ourInstance;
	}
	public CD getDefaultModality(){
		return new CD("99EPADM0","NA","99EPAD");
	}
	
	private Modality()
	{
		
		
		String dcmDesignator="DCM", lexVersion="20121129"; //from the link below
		//http://dicom.nema.org/medical/dicom/current/output/chtml/part16/sect_CID_29.html
		//http://www.dicomlibrary.com/dicom/sop/
		
		try {
			this.put("1.2.840.10008.5.1.4.1.1.2", new CD("CT","Computed Tomography",dcmDesignator,lexVersion));
			this.put("1.2.840.10008.5.1.4.1.1.1", new CD("CR","Computed Radiography",dcmDesignator,lexVersion));
			this.put("1.2.840.10008.5.1.4.1.1.128", new CD("PT","Positron emission tomography",dcmDesignator,lexVersion));
			this.put("1.2.840.10008.5.1.4.1.1.4", new CD("MR","Magnetic Resonance",dcmDesignator,lexVersion));
			this.put("1.2.840.10008.5.1.4.1.1.6.1", new CD("US","Ultrasound",dcmDesignator,lexVersion));
			this.put("1.2.840.10008.5.1.4.1.1.1.2", new CD("MG","Mammography",dcmDesignator,lexVersion));
			this.put("1.2.840.10008.5.1.4.1.1.1.2.1", new CD("MG","Mammography",dcmDesignator,lexVersion));
			
			//put entities also for lookup from modality. putting extra records is faster
			this.put("CT", new CD("CT","Computed Tomography",dcmDesignator,lexVersion));
			this.put("CR", new CD("CR","Computed Radiography",dcmDesignator,lexVersion));
			this.put("PT", new CD("PT","Positron emission tomography",dcmDesignator,lexVersion));
			this.put("MR", new CD("MR","Magnetic Resonance",dcmDesignator,lexVersion));
			this.put("US", new CD("US","Ultrasound",dcmDesignator,lexVersion));
			this.put("MG", new CD("MG","Mammography",dcmDesignator,lexVersion));
			
			
		} catch (Exception e) {
			logger.info("Error getting lexicon "+ e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
