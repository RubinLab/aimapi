/*******************************************************************************
 * Copyright (c) 2015 The Board of Trustees of the Leland Stanford Junior University
 * BY CLICKING ON "ACCEPT," DOWNLOADING, OR OTHERWISE USING EPAD, YOU AGREE TO THE FOLLOWING TERMS AND CONDITIONS:
 * STANFORD ACADEMIC SOFTWARE SOURCE CODE LICENSE FOR
 * "ePAD Annotation Platform for Radiology Images"
 *
 * This Agreement covers contributions to and downloads from the ePAD project ("ePAD") maintained by The Board of Trustees 
 * of the Leland Stanford Junior University ("Stanford"). 
 *
 * *	Part A applies to downloads of ePAD source code and/or data from ePAD. 
 *
 * *	Part B applies to contributions of software and/or data to ePAD (including making revisions of or additions to code 
 * and/or data already in ePAD), which may include source or object code. 
 *
 * Your download, copying, modifying, displaying, distributing or use of any ePAD software and/or data from ePAD 
 * (collectively, the "Software") is subject to Part A. Your contribution of software and/or data to ePAD (including any 
 * that occurred prior to the first publication of this Agreement) is a "Contribution" subject to Part B. Both Parts A and 
 * B shall be governed by and construed in accordance with the laws of the State of California without regard to principles 
 * of conflicts of law. Any legal action involving this Agreement or the Research Program will be adjudicated in the State 
 * of California. This Agreement shall supersede and replace any license terms that you may have agreed to previously with 
 * respect to ePAD.
 *
 * PART A. DOWNLOADING AGREEMENT - LICENSE FROM STANFORD WITH RIGHT TO SUBLICENSE ("SOFTWARE LICENSE").
 * 1. As used in this Software License, "you" means the individual downloading and/or using, reproducing, modifying, 
 * displaying and/or distributing Software and the institution or entity which employs or is otherwise affiliated with you. 
 * Stanford  hereby grants you, with right to sublicense, with respect to Stanford's rights in the Software, a 
 * royalty-free, non-exclusive license to use, reproduce, make derivative works of, display and distribute the Software, 
 * provided that: (a) you adhere to all of the terms and conditions of this Software License; (b) in connection with any 
 * copy, distribution of, or sublicense of all or any portion of the Software, the terms and conditions in this Software 
 * License shall appear in and shall apply to such copy and such sublicense, including without limitation all source and 
 * executable forms and on any user documentation, prefaced with the following words: "All or portions of this licensed 
 * product  have been obtained under license from The Board of Trustees of the Leland Stanford Junior University. and are 
 * subject to the following terms and conditions" AND any user interface to the Software or the "About" information display 
 * in the Software will display the following: "Powered by ePAD http://epad.stanford.edu;" (c) you preserve and maintain 
 * all applicable attributions, copyright notices and licenses included in or applicable to the Software; (d) modified 
 * versions of the Software must be clearly identified and marked as such, and must not be misrepresented as being the 
 * original Software; and (e) you consider making, but are under no obligation to make, the source code of any of your 
 * modifications to the Software freely available to others on an open source basis.
 *
 * 2. The license granted in this Software License includes without limitation the right to (i) incorporate the Software 
 * into your proprietary programs (subject to any restrictions applicable to such programs), (ii) add your own copyright 
 * statement to your modifications of the Software, and (iii) provide additional or different license terms and conditions 
 * in your sublicenses of modifications of the Software; provided that in each case your use, reproduction or distribution 
 * of such modifications otherwise complies with the conditions stated in this Software License.
 * 3. This Software License does not grant any rights with respect to third party software, except those rights that 
 * Stanford has been authorized by a third party to grant to you, and accordingly you are solely responsible for (i) 
 * obtaining any permissions from third parties that you need to use, reproduce, make derivative works of, display and 
 * distribute the Software, and (ii) informing your sublicensees, including without limitation your end-users, of their 
 * obligations to secure any such required permissions.
 * 4. You agree that you will use the Software in compliance with all applicable laws, policies and regulations including, 
 * but not limited to, those applicable to Personal Health Information ("PHI") and subject to the Institutional Review 
 * Board requirements of the your institution, if applicable. Licensee acknowledges and agrees that the Software is not 
 * FDA-approved, is intended only for research, and may not be used for clinical treatment purposes. Any commercialization 
 * of the Software is at the sole risk of you and the party or parties engaged in such commercialization. You further agree 
 * to use, reproduce, make derivative works of, display and distribute the Software in compliance with all applicable 
 * governmental laws, regulations and orders, including without limitation those relating to export and import control.
 * 5. You or your institution, as applicable, will indemnify, hold harmless, and defend Stanford against any third party 
 * claim of any kind made against Stanford arising out of or related to the exercise of any rights granted under this 
 * Agreement, the provision of Software, or the breach of this Agreement. Stanford provides the Software AS IS and WITH ALL 
 * FAULTS.  Stanford makes no representations and extends no warranties of any kind, either express or implied.  Among 
 * other things, Stanford disclaims any express or implied warranty in the Software:
 * (a)  of merchantability, of fitness for a particular purpose,
 * (b)  of non-infringement or 
 * (c)  arising out of any course of dealing.
 *
 * Title and copyright to the Program and any associated documentation shall at all times remain with Stanford, and 
 * Licensee agrees to preserve same. Stanford reserves the right to license the Program at any time for a fee.
 * 6. None of the names, logos or trademarks of Stanford or any of Stanford's affiliates or any of the Contributors, or any 
 * funding agency, may be used to endorse or promote products produced in whole or in part by operation of the Software or 
 * derived from or based on the Software without specific prior written permission from the applicable party.
 * 7. Any use, reproduction or distribution of the Software which is not in accordance with this Software License shall 
 * automatically revoke all rights granted to you under this Software License and render Paragraphs 1 and 2 of this 
 * Software License null and void.
 * 8. This Software License does not grant any rights in or to any intellectual property owned by Stanford or any 
 * Contributor except those rights expressly granted hereunder.
 *
 * PART B. CONTRIBUTION AGREEMENT - LICENSE TO STANFORD WITH RIGHT TO SUBLICENSE ("CONTRIBUTION AGREEMENT").
 * 1. As used in this Contribution Agreement, "you" means an individual providing a Contribution to ePAD and the 
 * institution or entity which employs or is otherwise affiliated with you.
 * 2. This Contribution Agreement applies to all Contributions made to ePAD at any time. By making a Contribution you 
 * represent that: (i) you are legally authorized and entitled by ownership or license to make such Contribution and to 
 * grant all licenses granted in this Contribution Agreement with respect to such Contribution; (ii) if your Contribution 
 * includes any patient data, all such data is de-identified in accordance with U.S. confidentiality and security laws and 
 * requirements, including but not limited to the Health Insurance Portability and Accountability Act (HIPAA) and its 
 * regulations, and your disclosure of such data for the purposes contemplated by this Agreement is properly authorized and 
 * in compliance with all applicable laws and regulations; and (iii) you have preserved in the Contribution all applicable 
 * attributions, copyright notices and licenses for any third party software or data included in the Contribution.
 * 3. Except for the licenses you grant in this Agreement, you reserve all right, title and interest in your Contribution.
 * 4. You hereby grant to Stanford, with the right to sublicense, a perpetual, worldwide, non-exclusive, no charge, 
 * royalty-free, irrevocable license to use, reproduce, make derivative works of, display and distribute the Contribution. 
 * If your Contribution is protected by patent, you hereby grant to Stanford, with the right to sublicense, a perpetual, 
 * worldwide, non-exclusive, no-charge, royalty-free, irrevocable license under your interest in patent rights embodied in 
 * the Contribution, to make, have made, use, sell and otherwise transfer your Contribution, alone or in combination with 
 * ePAD or otherwise.
 * 5. You acknowledge and agree that Stanford ham may incorporate your Contribution into ePAD and may make your 
 * Contribution as incorporated available to members of the public on an open source basis under terms substantially in 
 * accordance with the Software License set forth in Part A of this Agreement. You further acknowledge and agree that 
 * Stanford shall have no liability arising in connection with claims resulting from your breach of any of the terms of 
 * this Agreement.
 * 6. YOU WARRANT THAT TO THE BEST OF YOUR KNOWLEDGE YOUR CONTRIBUTION DOES NOT CONTAIN ANY CODE OBTAINED BY YOU UNDER AN 
 * OPEN SOURCE LICENSE THAT REQUIRES OR PRESCRIBES DISTRBUTION OF DERIVATIVE WORKS UNDER SUCH OPEN SOURCE LICENSE. (By way 
 * of non-limiting example, you will not contribute any code obtained by you under the GNU General Public License or other 
 * so-called "reciprocal" license.)
 *******************************************************************************/
package edu.stanford.hakan.aim4api.project.epad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import edu.stanford.hakan.aim4api.base.CD;
import edu.stanford.hakan.aim4api.base.CalculationEntity;
import edu.stanford.hakan.aim4api.base.CalculationEntityCollection;
import edu.stanford.hakan.aim4api.base.CalculationEntityReferencesMarkupEntityStatement;
import edu.stanford.hakan.aim4api.base.CalculationEntityReferencesSegmentationEntityStatement;
import edu.stanford.hakan.aim4api.base.CompactCalculationResult;
import edu.stanford.hakan.aim4api.base.DicomImageReferenceEntity;
import edu.stanford.hakan.aim4api.base.DicomSegmentationEntity;
import edu.stanford.hakan.aim4api.base.Enumerations;
import edu.stanford.hakan.aim4api.base.Equipment;
import edu.stanford.hakan.aim4api.base.ExtendedCalculationResult;
import edu.stanford.hakan.aim4api.base.II;
import edu.stanford.hakan.aim4api.base.Image;
import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.base.ImageAnnotationCollection;
import edu.stanford.hakan.aim4api.base.ImageAnnotationStatement;
import edu.stanford.hakan.aim4api.base.ImageCollection;
import edu.stanford.hakan.aim4api.base.ImageReferenceEntity;
import edu.stanford.hakan.aim4api.base.ImageReferenceEntityCollection;
import edu.stanford.hakan.aim4api.base.ImageSeries;
import edu.stanford.hakan.aim4api.base.ImageStudy;
import edu.stanford.hakan.aim4api.base.ImagingObservationCharacteristic;
import edu.stanford.hakan.aim4api.base.ImagingObservationEntity;
import edu.stanford.hakan.aim4api.base.ImagingObservationEntityCollection;
import edu.stanford.hakan.aim4api.base.ImagingPhysicalEntity;
import edu.stanford.hakan.aim4api.base.ImagingPhysicalEntityCharacteristic;
import edu.stanford.hakan.aim4api.base.ImagingPhysicalEntityCollection;
import edu.stanford.hakan.aim4api.base.InferenceEntity;
import edu.stanford.hakan.aim4api.base.InferenceEntityCollection;
import edu.stanford.hakan.aim4api.base.MarkupEntity;
import edu.stanford.hakan.aim4api.base.MarkupEntityCollection;
import edu.stanford.hakan.aim4api.base.Person;
import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.base.SegmentationEntity;
import edu.stanford.hakan.aim4api.base.SegmentationEntityCollection;
import edu.stanford.hakan.aim4api.base.TextAnnotationEntity;
import edu.stanford.hakan.aim4api.base.TwoDimensionCircle;
import edu.stanford.hakan.aim4api.base.TwoDimensionGeometricShapeEntity;
import edu.stanford.hakan.aim4api.base.TwoDimensionMultiPoint;
import edu.stanford.hakan.aim4api.base.TwoDimensionPoint;
import edu.stanford.hakan.aim4api.base.TwoDimensionPolyline;
import edu.stanford.hakan.aim4api.base.TwoDimensionSpatialCoordinate;
import edu.stanford.hakan.aim4api.base.TwoDimensionSpline;
import edu.stanford.hakan.aim4api.base.User;
import edu.stanford.hakan.aim4api.compability.aimv3.Modality;
import edu.stanford.hakan.aim4api.project.epad.Enumerations.ComponentType;
import edu.stanford.hakan.aim4api.project.epad.Enumerations.ShapeType;
import edu.stanford.hakan.aim4api.utility.GenerateId;

//gwt 2.8
//import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * 
 * @author debra willrett
 * 
 *         Aim4 wraps ImageAnnotation and calls methods: addGeometricShape,
 *         addImageReference, addPerson, addSegmentation, addTextAnnotation,
 *         setName, setDateTime, setCagridId, setComment, addUser,createUser, ,
 *         addEquipment, createEquipment, addImageReference,
 *         createImageReference, addAnatomicEntity, createAnatomicEntity,
 *         addCalculation, setCagridId, setCodingSchemeDesignator,
 *         setCodeMeaning,setCodeValue(result.getCodeValue());
 * 
 */
@SuppressWarnings("serial")
public class Aim4 extends ImageAnnotationCollection implements  Serializable {

	private static final Logger logger = Logger.getLogger("Aim4");
	public static final String commentSeperator = "~~"; //some special chars need escape in string.contains function, changing the separator needs attention 

	String DSO_SOP_CLASSUID = "1.2.840.10008.5.1.4.1.1.66.4";

    //this is the shapes list for epad version of shapes
    transient private List<Shape> aimShapes=null;

	public Aim4() {
	}
	
	public Aim4(String username,String userFullName, String pName, String pId, String pBirthDate, String pSex, CD template, String lesionName, String comment, String imageUID,String sopClassUID,String studyDate, String studyTime,String studyUID, String sourceSeriesUID, String accessionNumber, String modality, String annotationDate){
		try {
			this.setUser(createUser(username,userFullName));
			
			logger.warning("annotationDate "+annotationDate);
			logger.warning("formatted "+getFormattedDateTime(annotationDate));
			//set the date 
			this.setDateTime(getFormattedDateTime(annotationDate));

			//put the patient information in aim
			this.setPerson(setPerson(pName, pId, pBirthDate, pSex));
			addImageAnnotation(createImageAnnotationFromProperties(username, template, lesionName, comment, imageUID, sopClassUID, studyDate, studyTime.replace(":", ""), studyUID, sourceSeriesUID, accessionNumber, modality, annotationDate));
			setAimStudyInstanceUid(new II(studyUID));
			setAccessionNumber(new ST(accessionNumber));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Aim4(ImageAnnotationCollection iac)
	{

		super();
		setPerson(iac.getPerson());
		setUser(iac.getUser());
		if (iac.getEquipment()!=null) setEquipment(iac.getEquipment());
		setAimVersion(iac.getAimVersion());
		this.setImageAnnotations(iac.getImageAnnotations());
		this.setPluginCollection(iac.getPluginCollection());
		String date=iac.getDateTime();
//		if (iac!=null && !iac.getDateTime().contains("-")) {//new format change to old
//			if (date.length()==14)
//				date=date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8)+"T"+date.substring(8,10)+":"+date.substring(10,12)+":"+date.substring(12,14);
//			else
//				date=date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
//		}
		setDateTime(date);
		
		setUniqueIdentifier(iac.getUniqueIdentifier().getClone(), "al536anhb55555");
	       
		setXsiType(iac.getXsiType());

	       
		//ml TODO 
		getImageAnnotation().setPluginCollection(iac.getImageAnnotation().getPluginCollection());
		getImageAnnotation().setDsoStartIndex(iac.getImageAnnotation().getDsoStartIndex());
		
		//try and get from image reference
		try{
			setAimStudyInstanceUid((((DicomImageReferenceEntity)iac.getImageAnnotation().getImageReferenceEntityCollection().getImageReferenceEntityList().get(0)).getImageStudy().getInstanceUid().getClone()));
		}catch(Exception e){
			logger.warning("Couldn't read study uid from imageannotationcollection, see if the iac has one, generating one if not");
			setAimStudyInstanceUid(iac.getStudyInstanceUid());
		}
		
		
		 //try and get from image reference			
		try{
			setAccessionNumber((((DicomImageReferenceEntity)iac.getImageAnnotation().getImageReferenceEntityCollection().getImageReferenceEntityList().get(0)).getImageStudy().getAccessionNumber().getClone()));
		}catch(Exception e){
			logger.warning("Couldn't read accession number from imageannotationcollection, see if the iac has one, not required don't put if not");
			if (iac.getAccessionNumber()!=null) setAccessionNumber(iac.getAccessionNumber());
		}
		
		setAimSeriesInstanceUid(iac.getSeriesInstanceUid());
	}

	
	// build the new imageAnnotation
    public Aim4(String name, String modality, String description,
            String patientName, String patientId, String patientSex,
            String patientBirthdate, String manufacturerName, String model,
            String version, int activeImage, String seriesNumber, LoggedInUser user,
            String imageUid, String seriesUid, String studyUid,
            String studyDate, String studyTime, String imageClassUid) { //ml imageclassuid added

        super();

        setName(name);
        setDateTime(todaysDate());
        setComment(fillComment(modality, description, activeImage, seriesNumber));

        setUser(user);

        setPerson(createPerson(patientName, patientId, patientSex,
                patientBirthdate));
        setEquipment(createEquipment(manufacturerName, model, version));

        // don't add the image reference yet, we don't have any shapes or segs
        addImageReferenceEntity(createImageReference(studyUid, seriesUid, imageUid,
                studyDate, studyTime.replace(":", ""), imageClassUid, null)); //ml imageclassuid added
        addImagingPhysicalEntity(createImagingPhysicalEntity());
        setAimStudyInstanceUid(new II(studyUid));

    }
    
    public Aim4(String name, String modality, String description,
            String patientName, String patientId, String patientSex,
            String patientBirthdate, String manufacturerName, String model,
            String version, int activeImage, String seriesNumber, LoggedInUser user,
            String imageUid, String seriesUid, String studyUid,
            String studyDate, String studyTime, String imageClassUid, String originalPatientId, String accessionNumber) { //ml imageclassuid added

        super();

        setName(name);
        setDateTime(todaysDate());
        setComment(fillComment(modality, description, activeImage, seriesNumber));

        setUser(user);

        setPerson(createPerson(patientName, patientId, patientSex,
                patientBirthdate, originalPatientId));
        setEquipment(createEquipment(manufacturerName, model, version));

        // don't add the image reference yet, we don't have any shapes or segs
        addImageReferenceEntity(createImageReference(studyUid, seriesUid, imageUid,
                studyDate, studyTime.replace(":", ""), imageClassUid,accessionNumber)); //ml imageclassuid added
        addImagingPhysicalEntity(createImagingPhysicalEntity());
        
        setAimStudyInstanceUid(new II(studyUid));
		setAccessionNumber(new ST(accessionNumber));

    }


	public Aim4(String name, String patientName, String patientId,
            LoggedInUser user, String studyUid, String studyDate,
            String studyTime) {

        super();
        setName(name);
        setDateTime(todaysDate());
        setUser(user);
        setPerson(createPerson(patientName, patientId, "unknown", todaysDate()));
        addImageReferenceEntity(createImageReference(studyUid, studyDate, studyTime.replace(":", "")));
        setAimStudyInstanceUid(new II(studyUid));

    }
    
	@Override
	public String getDateTime(){
		if (getDateTimeAsDate()!=null){
			Date d=getDateTimeAsDate();
//			return (d.getYear()+1900)+""+addLeadingZeros(d.getMonth()+1)+"-"+addLeadingZeros(d.getDate())+ "T"+addLeadingZeros(d.getHours())+":"+addLeadingZeros(d.getMinutes())+":"+addLeadingZeros(d.getSeconds());
			return (d.getYear()+1900)+""+addLeadingZeros(d.getMonth()+1)+""+addLeadingZeros(d.getDate())+ " "+addLeadingZeros(d.getHours())+":"+addLeadingZeros(d.getMinutes())+":"+addLeadingZeros(d.getSeconds());

		}
		return "";
	}
	public Date getDateTimeAsDate(){
		int year ;
		int month ;
		int day;
		int hours=0;
		int minutes=0;
		int seconds=0;
		String strStartDate= super.getDateTime();
		Date date;
		try {
			if (strStartDate.contains("-")) {
				year = Integer.parseInt(strStartDate.substring(0, 4));
				month = Integer.parseInt(strStartDate.substring(5, 7));
				day = Integer.parseInt(strStartDate.substring(8, 10));
				if (strStartDate.length()>10){
					hours = Integer.parseInt(strStartDate.substring(11, 13));
					minutes = Integer.parseInt(strStartDate.substring(14, 16));
					seconds = Integer.parseInt(strStartDate.substring(17, 19));
				}
			}
			else {
				year = Integer.parseInt(strStartDate.substring(0, 4));
				month = Integer.parseInt(strStartDate.substring(4, 6));
				day = Integer.parseInt(strStartDate.substring(6, 8));
				if (strStartDate.length()>8){
					hours = Integer.parseInt(strStartDate.substring(8, 10));
					minutes = Integer.parseInt(strStartDate.substring(10, 12));
					seconds = Integer.parseInt(strStartDate.substring(12, 14));
				}
			}
			System.out.println(year + " " + month + " " + day);
			if (strStartDate.length()>8){
				date = new Date(year-1900, month - 1, day,hours,minutes,seconds);
			}else {
				date = new Date(year-1900, month - 1, day);
			}
			return date;
		}catch(Exception e){
			logger.info("Couldn't parse date "+ strStartDate+ " " + e.getMessage());
		}
		return null;
	}
    private ImagingPhysicalEntity createImagingPhysicalEntity() {

    	ImagingPhysicalEntity entity = new ImagingPhysicalEntity();
    	entity.setAnnotatorConfidence(0.0);
    	entity.addTypeCode(new CD("0","background","ePAD"));
    	entity.setLabel(new ST("background"));
    	return entity;
    }
    
 // create the equipment object for this series
    private Equipment createEquipment(String name, String model, String version) {

        Equipment equipment = new Equipment();
        equipment.setManufacturerName(new ST(name));
        equipment.setManufacturerModelName(new ST(model));
        equipment.setSoftwareVersion(new ST(version));
        return equipment;
    }

    private String todaysDate() {
      return getFormatedDateTime();
  }
    public static String getFormatedDateTime() {
        Date date = new Date();

        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;
        int day = date.getDate();
        int hour = date.getHours();
        int minute = date.getMinutes();
        int second = date.getSeconds();

        String strMount = Integer.toString(month);
        if (month < 10) {
            strMount = "0" + strMount;
        }
        String strDay = Integer.toString(day);
        if (day < 10) {
            strDay = "0" + strDay;
        }
        String strHour = Integer.toString(hour);
        if (hour < 10) {
            strHour = "0" + strHour;
        }
        String strMinute = Integer.toString(minute);
        if (minute < 10) {
            strMinute = "0" + strMinute;
        }
        String strSecond = Integer.toString(second);
        if (second < 10) {
            strSecond = "0" + strSecond;
        }

        StringBuilder builder = new StringBuilder();
//        builder.append(Integer.toString(year)).append("-").append(strMount).append("-").append(strDay).append("T").append(strHour).append(":").append(strMinute).append(":").append(strSecond);
        ///ml change for proper aim format (clunie)
        
        builder.append(Integer.toString(year)).append(strMount).append(strDay).append(strHour).append(strMinute).append(strSecond);
        return builder.toString();
    }
	public boolean hasSeries(String seriesID) {
		boolean result = false;
		try {

			for (ImageReferenceEntity reference : getImageAnnotation().getImageReferenceEntityCollection()
					.getImageReferenceEntityList()) {

				DicomImageReferenceEntity dicomImageReference = (DicomImageReferenceEntity) reference;

				if (dicomImageReference.getImageStudy().getImageSeries()
						.getInstanceUid().getRoot().equals(seriesID)) {
					result = true;
					break;
				}
			}
		} finally {
		}
		return result;
	}

	
	public boolean hasImage(String imageID) {
		boolean result = false;
		try {

			for (ImageReferenceEntity reference : getImageAnnotation().getImageReferenceEntityCollection()
					.getImageReferenceEntityList()) {

				DicomImageReferenceEntity dicomImageReference = (DicomImageReferenceEntity) reference;

				for (Image image : dicomImageReference.getImageStudy()
						.getImageSeries().getImageCollection().getImageList()) {
					if (image.getSopInstanceUid().getRoot().equals(imageID)) {
						result = true;
						break;
					}
				}
			}
		} finally {
		}
		return result;
	}
	
	/**
	 * gets the modality from aim
	 * tries to get it from the modality in series first.
	 * if it is not there tries to get from the sopclassuid 
	 * @return
	 */
	public String getModality() {
		String result = "";

		try {
			List<ImageReferenceEntity> imageList = getImageAnnotation().getImageReferenceEntityCollection()
					.getImageReferenceEntityList();
			if (imageList.size() > 0) {
				ImageReferenceEntity imageReference = imageList.get(0);
				DicomImageReferenceEntity dicomImageReference = (DicomImageReferenceEntity) imageReference;
				ImageStudy imageStudy = dicomImageReference.getImageStudy();
				ImageSeries imageSeries = imageStudy.getImageSeries();
				result =imageSeries.getModality().getCode();
				if (result.equals("NA")){
					CD modality=Modality.getInstance().get(imageSeries.getImageCollection().get(0).getSopClassUid().getRoot());
					if (modality!=null)
						result = modality.getCode();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// get the series id for this image from the image reference
	
	public String getFirstSeriesID() {
		String result = "";

		try {
			List<ImageReferenceEntity> imageList = getImageAnnotation().getImageReferenceEntityCollection()
					.getImageReferenceEntityList();
			if (imageList.size() > 0) {
				ImageReferenceEntity imageReference = imageList.get(0);
				DicomImageReferenceEntity dicomImageReference = (DicomImageReferenceEntity) imageReference;
				ImageStudy imageStudy = dicomImageReference.getImageStudy();
				ImageSeries imageSeries = imageStudy.getImageSeries();
				result = imageSeries.getInstanceUid().getRoot();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<String> getSeriesIDs() {
		List<String> results = new ArrayList<String>();

		try {
			List<ImageReferenceEntity> imageList = getImageAnnotation().getImageReferenceEntityCollection()
					.getImageReferenceEntityList();
			for (ImageReferenceEntity imageReference: imageList) {
				DicomImageReferenceEntity dicomImageReference = (DicomImageReferenceEntity) imageReference;
				ImageStudy imageStudy = dicomImageReference.getImageStudy();
				ImageSeries imageSeries = imageStudy.getImageSeries();
				results.add(imageSeries.getInstanceUid().getRoot());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	
	public String getSeriesID(String imageID) {

		String result = "";
		try {

			// look for the reference to this image
			for (ImageReferenceEntity reference : getImageAnnotation().getImageReferenceEntityCollection()
					.getImageReferenceEntityList()) {

				if (reference.getXsiType().equals("DicomImageReferenceEntity")) {

					DicomImageReferenceEntity dicomReference = (DicomImageReferenceEntity) reference;
					for (Image image : dicomReference.getImageStudy()
							.getImageSeries().getImageCollection()
							.getImageList()) {

						if (image.getSopInstanceUid().getRoot().equals(imageID)) {

							result = dicomReference.getImageStudy()
									.getImageSeries().getInstanceUid().getRoot();
							break;

						}
					}
				}
			}
		} finally {
		}
		return result;
	}

	
	public String getFirstStudyID() {

		String studyID = "";
		try {

			DicomImageReferenceEntity reference = (DicomImageReferenceEntity) getImageAnnotation().getImageReferenceEntityCollection()
					.getImageReferenceEntityList().get(0);
			studyID = reference.getImageStudy().getInstanceUid().getRoot();

		} finally {
		}

		return studyID;
	}

	
	public String getStudyID(String seriesID) {

		String result = "";
		try {

			for (ImageReferenceEntity reference : getImageAnnotation().getImageReferenceEntityCollection()
					.getImageReferenceEntityList()) {

				DicomImageReferenceEntity dicomImageReference = (DicomImageReferenceEntity) reference;

				if (dicomImageReference.getImageStudy().getImageSeries()
						.getInstanceUid().getRoot().equals(seriesID)) {
					result = dicomImageReference.getImageStudy()
							.getInstanceUid().getRoot();
					break;
				}
			}
		} finally {
		}
		return result;
	}
	
	public Date getFirstStudyDate() {

		try {
			List<ImageReferenceEntity> imageList = getImageAnnotation().getImageReferenceEntityCollection()
					.getImageReferenceEntityList();

			ImageReferenceEntity imageReference = imageList.get(0);
			DicomImageReferenceEntity dicomImageReference = (DicomImageReferenceEntity) imageReference;
			ImageStudy study = dicomImageReference.getImageStudy();

			//SimpleDateFormat not working in client. DateTimeFormat is in gwt 2.8
//			DateTimeFormat fmt ;
//			Date date = new Date();
//			logger.warning("date is:" + study.getStartDate());
//			if (study.getStartDate().contains("-")) {
//				fmt = DateTimeFormat.getFormat("yyyy-MM-dd");
//				date = fmt.parse(study.getStartDate().substring(0, 10));
//			}else
//			{
//				fmt = new DateTimeFormat.getFormat("yyyyMMdd");
//				date = fmt.parse(study.getStartDate().substring(0, 8));	
//			}
//			return date;
			
			int year ;
            int month ;
            int day;
            //ml dateformat change
            String strStartDate= study.getStartDate();
        	if (strStartDate.contains("-")) {
				
                year = Integer.parseInt(strStartDate.substring(0, 4));
                month = Integer.parseInt(strStartDate.substring(5, 7));
                day = Integer.parseInt(strStartDate.substring(8, 10));
        	}
        	else {
        		year = Integer.parseInt(strStartDate.substring(0, 4));
                month = Integer.parseInt(strStartDate.substring(4, 6));
                day = Integer.parseInt(strStartDate.substring(6, 8));
        	}
            System.out.println(year + " " + month + " " + day);
            Date date = new Date(year-1900, month - 1, day);
            return date;

		} catch (Exception e) {
			logger.info("Error: aimApi.getStudyDate " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	
	public String getPatientID() {
		String result = "";
		try {
			return getPerson().getId().getValue();

		} catch (Exception e) {
			logger.info("Error: Aim4 getPatientId " + e.getMessage());
		}
		return result;

	}

	 public String getOriginalPatientID() {
	        String result = "";
	        try {
	            if (getPerson().getSourcePatientGroupId()!=null) 
	            	result= getPerson().getSourcePatientGroupId().getValue();
	            else
	            	return getPatientID();
	        } catch (Exception e) {
	            logger.info("Error: Aim4 getOriginalPatientId " + e.getMessage());
	            return getPatientID();
	        }
	        return result;

	    }

	

	
	public String getPatientName() {
		if (getPerson() != null) {
			return getPerson().getName().getValue().replace("^", " ");
			
		}
		return "";
	}

	
	
	public String getFirstImageID() {
		String result = "";
		try {

			DicomImageReferenceEntity dicomImageReference = ((DicomImageReferenceEntity) getImageAnnotation().getImageReferenceEntityCollection()
					.getImageReferenceEntityList().get(0));
			result = dicomImageReference.getImageStudy().getImageSeries()
	                .getImageCollection().getImageList().get(0).getSopInstanceUid().getRoot();

		} finally {
		}
		return result;

	}

	
	// does this annotation contain an image reference?
	public boolean hasImage() {
		try {
			getImageAnnotation().getImageReferenceEntityCollection()
			.getImageReferenceEntityList().get(0);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// does this annotation contain a segmentation component?
	
	public boolean hasSegmentation() {
		try {
			getImageAnnotation().getSegmentationEntityCollection().getSegmentationEntityList().get(0);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	
	public boolean hasSegmentationImage(String imageID) {
		boolean found = false;
		try {
			for (SegmentationEntity segmentation : getImageAnnotation().getSegmentationEntityCollection()
					.getSegmentationEntityList()) {

				if (((DicomSegmentationEntity)segmentation).getSopInstanceUid().getRoot().equals(imageID)) {
					found = true;
					break;
				}
			}
		} finally {
		}
		return found;

	}

	
	public String getFirstSegmentationImageID() {
		String id = "";

		try {
			id = ((DicomSegmentationEntity)getImageAnnotation().getSegmentationEntityCollection()
					.getSegmentationEntityList().get(0))
					.getSopInstanceUid().getRoot();
		} catch (Exception e) {
			logger.info("Error: has no segmentation " + hasSegmentation());
		}
		return id;
	}
	public String getComment(){
		if (getImageAnnotation().getComment()==null)
			return "";
		return getImageAnnotation().getComment().getValue();
	}


	public void setPatient(Patient patient) {
		Person person = getPerson();
        person.setName(new ST(patient.getName()));
        person.setId(new ST(patient.getId()));
        //ml originalid
        person.setSourcePatientGroupId(new ST(patient.getSourcePatientGroupId()));
		person.setSex(new ST(patient.getSex()));
        person.setBirthDate(patient.getBirthDate());
		
	}
	
	public String setStudyID(String seriesID, String studyID, String startDate, String startTime) {

        String result = "";
        try {

            for (ImageReferenceEntity imageReference : getImageAnnotation().getImageReferenceEntityCollection()
					.getImageReferenceEntityList()) {

            	DicomImageReferenceEntity dicomImageReference = (DicomImageReferenceEntity) imageReference;

                if (dicomImageReference.getImageStudy().getImageSeries()
                        .getInstanceUid().getRoot().equals(seriesID)) {
                    dicomImageReference.getImageStudy()
                    	.getInstanceUid().setRoot(studyID);
                    dicomImageReference.getImageStudy().setStartDate(startDate);
                    dicomImageReference.getImageStudy().setStartTime(startTime);
                    break;
                }
            }
        } finally {
        }
        return result;
    }
	
	
	public void addFreeText(String value){
		TextAnnotationEntity ta=new TextAnnotationEntity();
		ta.setText(new ST(value));
		getImageAnnotation().addMarkupEntity(ta);
		
	}
	
	
	/**
	 * the code retrieved from edu.stanford.hakan.aim4api.project.epad.Aim4 and converted to aim4 classes
	 * to keep a v4 copy of them. currently not in use
	 * @param length
	 * @return
	 */
	//values from edu.stanford.hakan.aim4api.project.epad.Aim4 use Aim4 classes values if exists
	private static final String LINE_LENGTH = "LineLength";
	private static final String VERSION = "1.0";
	private static final String PRIVATE_DESIGNATOR = "private";
    public static final String LONG_AXIS = "LongAxis";
    public static final String SHORT_AXIS = "ShortAxis";
	private static final String MEAN = "Mean"; 
	private static final String AREA = "Area";
	private static final String STD_DEV = "Standard Deviation";
	private static final String MIN = "Minimum";
	private static final String MAX = "Maximum";
	private static final String VOLUME = "Volume";
    public static final String LINE_MEASURE = "cm";
    public static final String VOLUME_MEASURE = "mm3";
		
    
    //TODO all should be added with addCalculationEntityWithRef
	public static CalculationEntity createMeanCalculation(double value, Integer shapeId, String units) {
		return createCalculation(String.valueOf(value),shapeId,units,Aim4.MEAN, "R-00317");
	}
	public static CalculationEntity createAreaCalculation(double value, Integer shapeId, String units) {
		return createCalculation(String.valueOf(value),shapeId,units,Aim4.AREA, "99EPADA4");
	}
	public static CalculationEntity createStdDevCalculation(double value, Integer shapeId, String units) {
		return createCalculation(String.valueOf(value),shapeId,units,Aim4.STD_DEV, "R-10047");
	}
	public static CalculationEntity createMinCalculation(double value, Integer shapeId, String units) {
		return createCalculation(String.valueOf(value),shapeId,units,Aim4.MIN, "R-404FB");
	}
	public static CalculationEntity createMaxCalculation(double value, Integer shapeId, String units) {
		return createCalculation(String.valueOf(value),shapeId,units,Aim4.MAX, "G-A437");
	}
	public static CalculationEntity createLengthCalculation(double value, Integer shapeId, String units) {
		return createCalculation(String.valueOf(value),shapeId,units,Aim4.LINE_LENGTH, "G-D7FE");
	}
	public static CalculationEntity createVolumeCalculation(double value, Integer shapeId, String units) {
		return createCalculation(String.valueOf(value),shapeId,VOLUME_MEASURE,VOLUME, "RID28668");
	}
	
	public static CalculationEntity createLongAxisCalculation(double value, Integer shapeId, String units) {
    	return createCalculation(String.valueOf(value),shapeId,units,LONG_AXIS, "G-A185");
    }
    public static CalculationEntity createShortAxisCalculation(double value, Integer shapeId, String units) {
    	return createCalculation(String.valueOf(value),shapeId,units,SHORT_AXIS, "G-A186");
    }
    //get the unit from line_measure constant
    public static CalculationEntity createLongAxisCalculation(double value, Integer shapeId) {
    	return createCalculation(String.valueOf(value),shapeId,LINE_MEASURE,LONG_AXIS, "G-A185");
    }
    public static CalculationEntity createShortAxisCalculation(double value, Integer shapeId) {
    	return createCalculation(String.valueOf(value),shapeId,LINE_MEASURE,SHORT_AXIS, "G-A186");
    }
    
    public static CalculationEntity createLongAxisMeanCalculation(double value, Integer shapeId, String units) {
    	return createCalculation(String.valueOf(value),shapeId,units,LONG_AXIS+"_"+MEAN, "R-00317");
    }
    public static CalculationEntity createLongAxisStdDevCalculation(double value, Integer shapeId, String units) {
    	return createCalculation(String.valueOf(value),shapeId,units,LONG_AXIS+"_"+STD_DEV, "R-10047");
    }
    public static CalculationEntity createLongAxisMinCalculation(double value, Integer shapeId, String units) {
    	return createCalculation(String.valueOf(value),shapeId,units,LONG_AXIS+"_"+MIN, "R-404FB");
    }
    public static CalculationEntity createLongAxisMaxCalculation(double value, Integer shapeId, String units) {
    	return createCalculation(String.valueOf(value),shapeId,units,LONG_AXIS+"_"+MAX, "G-A437");
    }
    
    public static CalculationEntity createShortAxisMeanCalculation(double value, Integer shapeId, String units) {
    	return createCalculation(String.valueOf(value),shapeId,units,SHORT_AXIS+"_"+MEAN, "R-00317");
    }
    public static CalculationEntity createShortAxisStdDevCalculation(double value, Integer shapeId, String units) {
    	return createCalculation(String.valueOf(value),shapeId,units,SHORT_AXIS+"_"+STD_DEV, "R-10047");
    }
    public static CalculationEntity createShortAxisMinCalculation(double value, Integer shapeId, String units) {
    	return createCalculation(String.valueOf(value),shapeId,units,SHORT_AXIS+"_"+MIN, "R-404FB");
    }
    public static CalculationEntity createShortAxisMaxCalculation(double value, Integer shapeId, String units) {
    	return createCalculation(String.valueOf(value),shapeId,units,SHORT_AXIS+"_"+MAX, "G-A437");
    }
    
    public static CalculationEntity createLengthCalculation(double value, Integer shapeId) {
    	return createCalculation(String.valueOf(value),shapeId,LINE_MEASURE,LINE_LENGTH, "G-D7FE");
    }
    
	/**
	 * aimapi's addcalculation. it can only save controlled terms that are in aimapi's static lexicon
	 * use epad-ws's AimUtil.addCalculation for being able to access the dynamic lexicon
	 * @param value
	 * @param shapeId
	 * @param units
	 * @param name
	 * @param code
	 * @return
	 */
	public static CalculationEntity createCalculation(String value, Integer shapeId, String units, String name, String code) {

		CalculationEntity cal =new CalculationEntity();
		cal.setUniqueIdentifier();
		CD calcCD= edu.stanford.hakan.aim4api.compability.aimv3.Lexicon.getInstance().get(code);
		String desc="";
		if (calcCD!=null) {
			if (units.equals("SUV") || units.equals("{SUVbw}g/ml")) {
				CD typeCode = new CD("126401","SUVbw","DCM");
				cal.addTypeCode(typeCode);
			}
			if (units.equals("HU") || units.equals("[hnsf'U]")) {
				CD typeCode = new CD("112031","Attenuation Coefficient","DCM");
				cal.addTypeCode(typeCode);
			}
			cal.addTypeCode(new CD(calcCD.getCode(),calcCD.getDisplayName().getValue(),calcCD.getCodeSystemName()));
			cal.setDescription(new ST(calcCD.getDisplayName().getValue()));
			desc=calcCD.getDisplayName().getValue();
		}else {

			cal.addTypeCode(new CD(name,name,Aim4.PRIVATE_DESIGNATOR));
			cal.setDescription(new ST(name));
			desc=name;

		}
		CompactCalculationResult calculationResult=new CompactCalculationResult();

		calculationResult.setType(Enumerations.CalculationResultIdentifier.Scalar);
		calculationResult.setUnitOfMeasure(new ST(Aim4.getUCUMUnit(units)));
		if (units.equals(""))
			calculationResult.setDataType(new CD("99EPADD2","String","99EPAD"));
		else
			calculationResult.setDataType(new CD("C48870","Double","NCI"));
		calculationResult.setValue(new ST(value));
		
		
//		// Create a CalculationData instance
//		edu.stanford.hakan.aim4api.base.CalculationData calculationData = new edu.stanford.hakan.aim4api.base.CalculationData();
//		calculationData.setValue(new ST(value));
//		calculationData.addCoordinate(0, 0);
//
		// Create a Dimension instance
		edu.stanford.hakan.aim4api.base.Dimension dimension = new edu.stanford.hakan.aim4api.base.Dimension(0, 1, name);
//
//		// Add calculationData to calculationResult
//		calculationResult.addCalculationData(calculationData);
//
		// Add dimension to calculationResult
		calculationResult.addDimension(dimension);

		//this should be rdf removing for now. do not have shape id. and do not see it in the recist aim.
		//                    // add the shape reference to the calculation
		//                    ReferencedGeometricShape reference = new ReferencedGeometricShape();
		//                    reference.setCagridId(0);
		//                    reference.setReferencedShapeIdentifier(shapeId);
		//                    calculation.addReferencedGeometricShape(reference);

		// Add calculationResult to calculation
		cal.addCalculationResult(calculationResult);

//		Algorithm alg=new Algorithm();
//		alg.setName(new ST(desc));
//		alg.setVersion(new ST(Aim4.VERSION));
//		ArrayList<CD> types=new ArrayList<>();
//		types.add(new CD("RID12780","Calculation","RadLex","3.2"));
//		alg.setType(types);
//		cal.setAlgorithm(alg);

		return cal;

	}
	
	public static CalculationEntityReferencesSegmentationEntityStatement createCalcRefSegStatement(CalculationEntity calc,SegmentationEntity seg) {
		CalculationEntityReferencesSegmentationEntityStatement calcSegRef= new CalculationEntityReferencesSegmentationEntityStatement();
		calcSegRef.setSubjectUniqueIdentifier(calc.getUniqueIdentifier().getClone());
		calcSegRef.setObjectUniqueIdentifier(seg.getUniqueIdentifier().getClone());
		return calcSegRef;
		
	}
	
	public static CalculationEntityReferencesMarkupEntityStatement createCalcRefMarkupStatement(CalculationEntity calc,MarkupEntity markup) {
		CalculationEntityReferencesMarkupEntityStatement calcMarkupRef= new CalculationEntityReferencesMarkupEntityStatement();
		calcMarkupRef.setSubjectUniqueIdentifier(calc.getUniqueIdentifier().getClone());
		calcMarkupRef.setObjectUniqueIdentifier(markup.getUniqueIdentifier().getClone());
		return calcMarkupRef;
		
	}
	
	public void addImageAnnotationStatement(ImageAnnotationStatement ias) {
        this.getImageAnnotation().addImageAnnotationStatement(ias);
    }
	
	public void addCalculationEntity(CalculationEntity newCalculation) {
        this.getImageAnnotation().getCalculationEntityCollection().addCalculationEntity(newCalculation);
    }
	
	// comment involves prorammedComment and userComment so parse it and assign to variables
    public String getUserComment() {
    		String comment = this.getComment();
    		if(comment.contains(Aim4.commentSeperator)) {
    			String[] comments = this.getComment().split(Aim4.commentSeperator);
    			return comments[1];
    		}
    		return "";
    }
    
    public String getProgrammedComment() {
		String comment = this.getComment();
		if(comment.contains(Aim4.commentSeperator)) {
			String[] comments = this.getComment().split(Aim4.commentSeperator);
			return comments[0];
		}
		return comment;
    }
  
	public void addCalculationEntityWithRef(CalculationEntity newCalculation,SegmentationEntity seg) {
        this.getImageAnnotation().getCalculationEntityCollection().addCalculationEntity(newCalculation);
        this.getImageAnnotation().addImageAnnotationStatement(createCalcRefSegStatement(newCalculation, seg));
    }
	
	public void addCalculationEntityWithRef(CalculationEntity newCalculation,MarkupEntity markup) {
        this.getImageAnnotation().getCalculationEntityCollection().addCalculationEntity(newCalculation);
        this.getImageAnnotation().addImageAnnotationStatement(createCalcRefMarkupStatement(newCalculation, markup));
    }
	
	
	/**
	 * create an ImageAnnotationCollection object using the properties
	 * @param username
	 * @param pName
	 * @param pId
	 * @param pBirthDate
	 * @param pSex
	 * @return
	 */
	public static edu.stanford.hakan.aim4api.base.ImageAnnotationCollection createImageAnnotationColectionFromProperties(String username, String pName, String pId, String pBirthDate, String pSex, String userFullName){
		ImageAnnotationCollection iac = new ImageAnnotationCollection();
		iac.setUser(createUser(username,userFullName));
		
		//set the date to current date
		iac.setDateTime(getFormattedDateTime(null));

		//put the patient information in aim
		iac.setPerson(setPerson(pName, pId, pBirthDate, pSex));

		return iac;
	}
	
	public static User createUser(String username, String userFullName){
		User user=new User();
		//format the full name in dicom format
		user.setName(new ST(userFullName.replace(" ", "^")));
		user.setLoginName(new ST(username));
		return user;
	}
	
	public static Person setPerson(String pName, String pId, String pBirthDate, String pSex){
		Person p=new Person();
		p.setBirthDate(formatDate(pBirthDate));
		p.setId(new ST(pId));
		p.setName(new ST(pName));
		if (pSex!=null)
			pSex=pSex.trim();
		p.setSex(new ST(pSex));
		return p;
	}
	
	/**
	 * gets the formatted datetime for the given string
	 * returns the current datetime if the input is null 
	 * and default datetime if couldn't be parsed 
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getFormattedDateTime(String dateStr){
		if (dateStr!=null){ //set to now
			return formatDate(dateStr);
		}
		Date now=new Date();
		
//		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return getFormattedDateTimeString(now);
	}
	
	public static String getFormattedDateTimeString(Date date){
		return ""+((date.getYear()+1900))+addLeadingZeros(date.getMonth()+1)+addLeadingZeros(date.getDate())+addLeadingZeros(date.getHours())+addLeadingZeros(date.getMinutes())+addLeadingZeros(date.getSeconds());

	}
	
	public static String addLeadingZeros(Integer val){
		return (val>9?val.toString():"0"+val.toString());
	}
	/**
	 * puts 19000101000000 if null or empty
	 * @param d
	 * @return
	 */
	public static String formatDate(String d) {
		if (d==null) d="";
		String date = ((d.length() >= 4) ? d.substring(0, 4) : "1900") 
				+ ((d.length() >= 6) ? d.substring(4, 6) : "01") 
				+ ((d.length() >= 8) ? d.substring(6, 8) : "01") 
				+ ((d.length() >= 10) ? d.substring(8, 10) : "00")
				+ ((d.length() >= 12) ? d.substring(10, 12) : "00")
				+ ((d.length() >= 14) ? d.substring(12, 14) : "00");
		return date;

	}
	
	
	/**
	 * puts 19000101000000 if null or empty
	 * @param d
	 * @return
	 */
	public static String formatTimeForAim(String d) {
		if (d==null) d="000000";
		String date = 
				 ((d.length() >= 2) ? d.substring(0, 2) : "00")
				+ ((d.length() >= 4) ? d.substring(2, 4) : "00")
				+ ((d.length() >= 6) ? d.substring(4, 6) : "00");
		return date;

	}


	/**
	 * create an ImageAnnotation object using the properties
	 * @param username
	 * @param templateCode
	 * @param lesionName
	 * @param comment
	 * @param imageUID
	 * @param sopClassUID
	 * @param studyDate
	 * @param studyTime
	 * @param studyUID
	 * @param sourceSeriesUID
	 * @return
	 * @throws Exception
	 */
	
	public static edu.stanford.hakan.aim4api.base.ImageAnnotation createImageAnnotationFromProperties(String username, CD template, String lesionName, String comment, String imageUID,String sopClassUID,String studyDate, String studyTime,String studyUID, String sourceSeriesUID) throws Exception {
		return createImageAnnotationFromProperties(username, template, lesionName, comment, imageUID, sopClassUID, studyDate, studyTime, studyUID, sourceSeriesUID, "");
	}
	public static edu.stanford.hakan.aim4api.base.ImageAnnotation createImageAnnotationFromProperties(String username, CD template, String lesionName, String comment, String imageUID,String sopClassUID,String studyDate, String studyTime,String studyUID, String sourceSeriesUID, String accessionNumber) throws Exception {
		return createImageAnnotationFromProperties(username, template, lesionName, comment, imageUID, sopClassUID, studyDate, studyTime, studyUID, sourceSeriesUID, accessionNumber,"");

	}	
	public static edu.stanford.hakan.aim4api.base.ImageAnnotation createImageAnnotationFromProperties(String username, CD template, String lesionName, String comment, String imageUID,String sopClassUID,String studyDate, String studyTime,String studyUID, String sourceSeriesUID, String accessionNumber, String modality) throws Exception {
		return createImageAnnotationFromProperties(username, template, lesionName, comment, imageUID, sopClassUID, studyDate, studyTime, studyUID, sourceSeriesUID, accessionNumber, modality, null);
	}
	
	public static edu.stanford.hakan.aim4api.base.ImageAnnotation createImageAnnotationFromProperties(String username, CD template, String lesionName, String comment, String imageUID,String sopClassUID,String studyDate, String studyTime,String studyUID, String sourceSeriesUID, String accessionNumber, String modality, String annotationDateTime) throws Exception {
		
		edu.stanford.hakan.aim4api.base.ImageAnnotation ia=new edu.stanford.hakan.aim4api.base.ImageAnnotation();
		ia.refreshUniqueIdentifier();
		ia.setDateTime(getFormattedDateTime(annotationDateTime));
		
		ia.setName(new ST(lesionName));
		if (template!=null){
			ArrayList<CD> types=new ArrayList<>();
			types.add(template);
			ia.setTypeCode(types);

		}
		ia.setComment(new ST(comment));

		//add the image reference entity
		DicomImageReferenceEntity dicomImageReferenceEntity  = new DicomImageReferenceEntity();
		ImageStudy study = new ImageStudy();
		study.setInstanceUid(new II(studyUID));
		ImageSeries series=new ImageSeries();
		series.setInstanceUid(new II(sourceSeriesUID));
		Image image=new Image();
		image.setSopInstanceUid(new II(imageUID));
		image.setSopClassUid(new II(sopClassUID));
		ImageCollection imageCol=new ImageCollection();
		imageCol.addImage(image);
		series.setImageCollection(imageCol);
		Modality mod=Modality.getInstance();
		if (modality!=null && mod.get(modality)!=null )
			series.setModality(mod.get(modality));
		else if ((mod.get(sopClassUID))!=null)
			series.setModality(mod.get(sopClassUID));
		else 
			series.setModality(mod.getDefaultModality());
		
		study.setImageSeries(series);
		study.setStartDate(studyDate);
		study.setStartTime(formatTimeForAim(studyTime));
		if (accessionNumber!=null && !accessionNumber.equals("")) {
			study.setAccessionNumber(new ST(accessionNumber));
		}
		dicomImageReferenceEntity.setImageStudy(study);
		ia.addImageReferenceEntity(dicomImageReferenceEntity);

		return ia;

	}

	/**
	 * ignores the existing one, overwrites.
	 * assumes one annotation
	 * @param dc
	 */
	public void setSegmentationEntity(DicomSegmentationEntity dc){
		SegmentationEntityCollection sc = new SegmentationEntityCollection();
		sc.addSegmentationEntity(dc);
		this.getImageAnnotation().setSegmentationEntityCollection(sc);
		
	}
	
	/**
	 * sets the tracking unique id of the FIRST annotation
	 * if the parameter is null generates a uid
	 * @param trackingUniqueIdentifier
	 */
	public void setTrackingUniqueIdentifier(II trackingUniqueIdentifier){
		if (trackingUniqueIdentifier==null) {//if it is null create a unique uid
			this.getImageAnnotation().setTrackingUniqueIdentifier(new II(GenerateId.getUUID()));
		}else {
			this.getImageAnnotation().setTrackingUniqueIdentifier(trackingUniqueIdentifier);
		}
	}
	
	public II getTrackingUniqueIdentifier(){
		return this.getImageAnnotation().getTrackingUniqueIdentifier();
	}
	
	/**
	 * sets the study uid of the aim
	 * if the parameter is null generates a uid
	 * @param studyInstanceUid
	 */
	public void setAimStudyInstanceUid(II studyInstanceUid){
		if (studyInstanceUid==null) {//if it is null create a unique uid
			this.setStudyInstanceUid(new II(GenerateId.getUUID()));
		}else {
			this.setStudyInstanceUid(studyInstanceUid);
		}
	}
	
	public II getAimStudyInstanceUid(){
		return this.getStudyInstanceUid();
	}
	
	
	/**
	 * sets the series uid of the aim
	 * if the parameter is null generates a uid
	 * @param seriesInstanceUid
	 */
	public void setAimSeriesInstanceUid(II seriesInstanceUid){
		if (seriesInstanceUid==null) {//if it is null create a unique uid
			this.setSeriesInstanceUid(new II(GenerateId.getUUID()));
		}else {
			this.setSeriesInstanceUid(seriesInstanceUid);
		}
	}
	
	public II getAimSeriesInstanceUid(){
		return this.getSeriesInstanceUid();
	}
	
	/**
	 * sets the accessionNumber of the aim
	 * if the parameter is null it doesn't do anything
	 * @param accessionNumber
	 */
	public void setAimAccessionNumber(ST accessionNumber){
		if (accessionNumber!=null) {
			this.setAccessionNumber(accessionNumber);
		}
	}
	
	public ST getAimAccessionNumber(){
		return this.getAccessionNumber();
	}

	public void setUniqueIdentifier(II uniqueIdentifier, String string) {
		logger.info("ignoring accesskey. no accesskey in v4");
		this.setUniqueIdentifier(uniqueIdentifier);
		
	}

	public String fillComment(String modality, String seriesDescription, int i, String seriesNumber) {
		return fillComment(modality, seriesDescription, i, seriesNumber, null);
	}
	public String fillComment(String modality, String seriesDescription, int i, String seriesNumber, String userId) {
		String SEPARATOR = " / ";
		String comment = modality + SEPARATOR + seriesDescription +SEPARATOR + i + SEPARATOR + seriesNumber;
		if (userId!=null)
			comment+=SEPARATOR+"USER:"+userId;
		return comment;
	}

	public void setComment(String comment) {
		this.getImageAnnotation().setComment(new ST(comment));
		
	}

	public Aim4 cloneAim() {
		Aim4 aim = new Aim4(this.getClone());
        return aim;
	}

	public void addImagingPhysicalEntity(ImagingPhysicalEntity newImagingPhysicalEntity) {
		this.getImageAnnotation().addImagingPhysicalEntity(newImagingPhysicalEntity);
	}
	
	public void addImageReferenceEntity(DicomImageReferenceEntity newImageReferenceEntity) {
		this.getImageAnnotation().addImageReferenceEntity(newImageReferenceEntity);
		
	}

	public void clearImageReferenceEntityCollection() {
		this.getImageAnnotation().getImageReferenceEntityCollection().getImageReferenceEntityList().clear();
		
	}

	public int addShapes(String studyID, String seriesID, String imageID,
            int activeImage, String studyDate, String studyTime,
            ShapeType shapeType, List<TwoDCoordinate> coords,
            double pixelSpacingX, double pixelSpacingY,  String imageClassUID) {
        return addShapes(studyID, seriesID, imageID, activeImage, studyDate, studyTime, shapeType, coords, pixelSpacingX, pixelSpacingY, imageClassUID, null, false);
    }
	
	public int addShapes(String studyID, String seriesID, String imageID,
            int activeImage, String studyDate, String studyTime,
            ShapeType shapeType, List<TwoDCoordinate> coords,
            double pixelSpacingX, double pixelSpacingY,  String imageClassUID, boolean multiframe) {
        return addShapes(studyID, seriesID, imageID, activeImage, studyDate, studyTime, shapeType, coords, pixelSpacingX, pixelSpacingY, imageClassUID, null, multiframe);
    }
	
	// give me the next available shape identifier
    private int getNextShapeID() {

        int max = 0;
        try {
            for (MarkupEntity shape : getMarkupEntityCollection()
                    .getMarkupEntityList()) {
            	if (shape instanceof TwoDimensionGeometricShapeEntity)
            		max = Math.max(max, ((TwoDimensionGeometricShapeEntity)shape).getShapeIdentifier());
            }
        } catch (Exception e) {
            logger.info("error: has no geometric shape collection");
        }
        max++;
        return max;
    }

	
	public int addShapes(String studyID, String seriesID, String imageID,
            int activeImage, String studyDate, String studyTime,
            ShapeType shapeType, List<TwoDCoordinate> coords,
            double pixelSpacingX, double pixelSpacingY,  String imageClassUID, String accessionNumber, boolean multiframe) {
        int frameID = 1;
        int shapeID = getNextShapeID();
        if (multiframe){
        	frameID=activeImage+1;
        }
        List<TwoDimensionGeometricShapeEntity> shapes = create2DShapes(imageID, frameID, shapeType,
                coords, pixelSpacingX, pixelSpacingY, shapeID);

        // could have created multiple shapes
        for (TwoDimensionGeometricShapeEntity shape : shapes) {

            shape.setIncludeFlag(true);
            addMarkupEntity(shape);
        }
        //we should send all shapes so it can check if normal
        addAimShapes(shapes);
        
        if (!hasImage(imageID)) {
            updateImageID(studyID, seriesID, imageID, activeImage, studyDate,
                    studyTime, imageClassUID,accessionNumber);
        }
        

        return shapeID;
    }
	
	private List<TwoDimensionGeometricShapeEntity> create2DShapes(String imageID, int frameID,
            ShapeType shapeType, List<TwoDCoordinate> coords,
            double pixelSpacingX, double pixelSpacingY, int shapeID) {

        // logger.info("createShapes " + shapeType);
        // build the shapes
        List<TwoDimensionGeometricShapeEntity> shapes = new ArrayList<TwoDimensionGeometricShapeEntity>();

        if (shapeType != null) {

            switch (shapeType) {

                case POINT:
                    TwoDimensionPoint point = new TwoDimensionPoint();
                    point.setShapeIdentifier(shapeID);
//                    point.setCagridId(caGridId);
                    shapes.add(create2DShape(point, coords, imageID, frameID));
                    break;

                case LINE:
                case OPENPOLY:
                	TwoDimensionMultiPoint multiPoint = new TwoDimensionMultiPoint();
                    multiPoint.setShapeIdentifier(shapeID);
//                    multiPoint.setCagridId(caGridId);
                    TwoDimensionGeometricShapeEntity shape = create2DShape(multiPoint, coords, imageID,
                            frameID);
                    shapes.add(shape);
                    shape.setShapeIdentifier(shapeID);

                    break;
                case POLY:
                case RECTANGLE:
                	TwoDimensionPolyline polyline = new TwoDimensionPolyline();
                    polyline.setShapeIdentifier(shapeID);
//                    polyline.setCagridId(caGridId);
                    shapes.add(create2DShape(polyline, coords, imageID, frameID));
                    break;
                case SPLINE:
//                case OPENSPLINE:
                	TwoDimensionSpline spline = new TwoDimensionSpline();
                    spline.setShapeIdentifier(shapeID);
//                    spline.setCagridId(caGridId);
                    shapes.add(create2DShape(spline, coords, imageID, frameID));
                    break;
                case CIRCLE:
                	TwoDimensionCircle circle = new TwoDimensionCircle();
                    circle.setShapeIdentifier(shapeID);
//                    circle.setCagridId(caGridId);
                    shapes.add(create2DShape(circle, coords, imageID, frameID));
                    break;
                case NORMAL:
                	//add the coorinates to the ellipse shape
//                	Ellipse ellipse = new Ellipse();
//                	ellipse.setShapeIdentifier(shapeID);;
//                	ellipse.setCagridId(caGridId);
//                	shapes.add(createShape(ellipse, coords, imageID, frameID));
                	logger.info("adding two lines ");
                    // add the long axis line
                    List<TwoDCoordinate> longAxis = new ArrayList<TwoDCoordinate>();
                    longAxis.add(coords.get(0));
                    longAxis.add(coords.get(1));
                    TwoDimensionMultiPoint longShape = new TwoDimensionMultiPoint();
                    longShape.setShapeIdentifier(shapeID++);
//                    longShape.setCagridId(caGridId);
                    TwoDimensionGeometricShapeEntity l = create2DShape(longShape, longAxis, imageID,
                            frameID);
                    shapes.add(l);

                    // add the short axis line
                    List<TwoDCoordinate> shortAxis = new ArrayList<TwoDCoordinate>();
                    shortAxis.add(coords.get(2));
                    shortAxis.add(coords.get(3));
                    TwoDimensionMultiPoint shortShape = new TwoDimensionMultiPoint();
                    shortShape.setShapeIdentifier(shapeID);
//                    shortShape.setCagridId(caGridId);
                    TwoDimensionGeometricShapeEntity s = create2DShape(shortShape, shortAxis, imageID,
                            frameID);
                    shapes.add(s);

                    break;
                default:
                    break;
            }
        }

        return shapes;
    }

    private TwoDimensionGeometricShapeEntity create2DShape(TwoDimensionGeometricShapeEntity shape,
            List<TwoDCoordinate> coords, String imageUid, int frame) {

        logger.info("createShape " + shape.getXsiType());
        shape.setImageReferenceUid(new II(imageUid));
        shape.setReferencedFrameNumber(frame);
        // put the coords into the shape
        for (int i = 0; i < coords.size(); i++) {
            TwoDimensionSpatialCoordinate coord = new TwoDimensionSpatialCoordinate(coords.get(i).getX(),coords.get(i).getY(),i);
            shape.setImageReferenceUid(new II(imageUid));
            shape.setReferencedFrameNumber(frame);
            shape.addTwoDimensionSpatialCoordinate(coord);
        }
        shape.setUniqueIdentifier();
        return shape;

    }
	
	private void updateImageID(String studyID, String seriesID, String imageID,
            int activeImage, String studyDate, String studyTime,  String imageClassUID, String accessionNumber) {

        for (ImageReferenceEntity imageReference : getImageReferenceEntityCollection()
                .getImageReferenceEntityList()) {

        	DicomImageReferenceEntity dicomImageReference = (DicomImageReferenceEntity) imageReference;

            String studyInstanceUID = dicomImageReference.getImageStudy()
                    .getInstanceUid().getRoot();
            String seriesInstanceUID = dicomImageReference.getImageStudy()
                    .getImageSeries().getInstanceUid().getRoot();

            if (studyInstanceUID.equals(studyID)) {

                // found the study
                if (seriesInstanceUID.isEmpty()) {

                    // turn a study reference into an image reference
                    // logger.info("turn a study reference into an image reference");
                    ImageSeries imageSeries = new ImageSeries();
                    imageSeries.setInstanceUid(new II(seriesID));
                    Image img = new Image( "", imageID);
                    imageSeries.addImage(img);

                    dicomImageReference.getImageStudy().setImageSeries(
                            imageSeries);

                    return;

                } else if (seriesInstanceUID.equals(seriesID)) {

                    // turn a series reference into an image reference
                    Image image = new Image( "", imageID);

                    List<Image> images = dicomImageReference.getImageStudy()
                            .getImageSeries().getImageCollection()
                            .getImageList();

                    if (images.size() > 0) {
                        images.clear();
                    }

                    images.add(image);
                    return;

                }
            }
        }

        // didn't find it, so create a new one
        addImageReferenceEntity(createImageReference(studyID, seriesID, imageID,
                studyDate, studyTime, imageClassUID, accessionNumber));

    }

	public boolean hasShapes() {
		boolean result = true;
        try {
			result= this.getMarkupEntityCollection()!=null && this.getMarkupEntityCollection().getMarkupEntityList().size()>0;
        } catch (Exception e) {
            result = false;
        }
        return result;
	}

	public String getName() {
		return this.getImageAnnotation().getName().getValue();
	}

	/**
	 * returns the first type of the first annotation
	 * @return
	 */
	public String getCodeValue() {
		if (this.getImageAnnotation().getListTypeCode()==null || this.getImageAnnotation().getListTypeCode().isEmpty())
			return null;
		return this.getImageAnnotation().getListTypeCode().get(0).getCode();
	}

	public String getCodeMeaning() {
		if (this.getImageAnnotation().getListTypeCode()==null || this.getImageAnnotation().getListTypeCode().isEmpty())
			return null;
		return this.getImageAnnotation().getListTypeCode().get(0).getDisplayName().getValue();
	}
	
	public String getCodingSchemeDesignator() {
		if (this.getImageAnnotation().getListTypeCode()==null || this.getImageAnnotation().getListTypeCode().isEmpty())
			return null;
		return this.getImageAnnotation().getListTypeCode().get(0).getCodeSystemName();
	}
	
	public String getCodingSchemeVersion() {
		if (this.getImageAnnotation().getListTypeCode()==null || this.getImageAnnotation().getListTypeCode().isEmpty())
			return null;
		return this.getImageAnnotation().getListTypeCode().get(0).getCodeSystemVersion();
	}

	public void setCodingSchemeDesignator(String val) {
		this.getImageAnnotation().getListTypeCode().get(0).setCodeSystemName(val);
	}
	
	public void setTemplate(CD val) {
		this.getImageAnnotation().getListTypeCode().clear();
		this.getImageAnnotation().addTypeCode(val);
		
	}
	

	public void setCodeMeaning(String val) {
		this.getImageAnnotation().getListTypeCode().get(0).setDisplayName(new ST(val));
		
	}

	public void setCodeValue(String val) {
		this.getImageAnnotation().getListTypeCode().get(0).setCode(val);
		
	}
	
	public void setCodingSchemeVersion(String val) {
		this.getImageAnnotation().getListTypeCode().get(0).setCodeSystemVersion(val);
		
	}
	
	public void addAimShapes(List<TwoDimensionGeometricShapeEntity> shapes){
		if (aimShapes==null){
			aimShapes = new ArrayList<Shape>();
		}
		List<Shape> result = new ArrayList<Shape>();
        for (TwoDimensionGeometricShapeEntity shape : shapes) {
            result.add(new Shape(shape));
        }
        aimShapes.addAll(organizeNormals(result));
		
	}
	
	public void removeAimShape(int shapeId){
		if (aimShapes!=null){
			for (Shape s:aimShapes){
				if (s.getShapeIdentifier()==shapeId){
					aimShapes.remove(s);
				}
					
			}
			for (MarkupEntity shape : getMarkupEntityCollection().getMarkupEntityList()) {
	            if (shape instanceof TwoDimensionGeometricShapeEntity)
	            	if (((TwoDimensionGeometricShapeEntity)shape).getShapeIdentifier()==shapeId){
	            		getMarkupEntityCollection().getMarkupEntityList().remove(shape);
	            	}

	        }
		}
	}
	
	public Normal getNormalIfPerpendicular(Shape s1, Shape s2){
		if (s1.getShapeType()==ShapeType.LINE && s2.getShapeType()==ShapeType.LINE){
			//see if the lines are perpendicular
//        	by computing the dot product of their vectors and
//        	determining that it is zero (within an appropriate floating point
//        	precision related tolerance)

        	Double x0=s1.getCoords().get(0).getX();
        	Double y0=s1.getCoords().get(0).getY();
        	Double x1=s1.getCoords().get(1).getX();
        	Double y1=s1.getCoords().get(1).getY();
        	Double x2=s2.getCoords().get(0).getX();
        	Double y2=s2.getCoords().get(0).getY();
        	Double x3=s2.getCoords().get(1).getX();
        	Double y3=s2.getCoords().get(1).getY();
        	logger.info("cross product is "+roundDouble((x1-x0)*(x3-x2) + (y1-y0)*(y3-y2)));
        	if (roundDouble((x1-x0)*(x3-x2) + (y1-y0)*(y3-y2)) == 0) {
        		//it is orthogonal
        		logger.info("lines are orthogonal. create a normal shape instead");
        		Normal n=new Normal(s1,s2);
        		//put the smallest shape id of the lines to the normal shape
        		int si=(s1.getShapeIdentifier()<s2.getShapeIdentifier()?s1.getShapeIdentifier():s2.getShapeIdentifier());
        		n.setShapeIdentifier(si);
        		return n;
        	}
		}
        	
		return null;
	}
	
	public List<Shape> organizeNormals(List<Shape> result){
		List<Shape> result2 = new ArrayList<Shape>();
        //see if there are 2 lines and if they are orthogonal put a normal shape instead
        if (result.size()>=2) {
        	for (int i=0;i<result.size();i++){
        		for (int j=i+1;j<result.size();j++){
        			Normal n=null;
        			if ((n=getNormalIfPerpendicular(result.get(i),result.get(j)))!=null){
        				result2.add(n);
        	        	result.remove(j);
        	        	result.remove(i);
        	        	i--;
        	        		
        	        	break;
        	        	
        			}
        		}
        	}
        }
        if (!result.isEmpty())
    		result2.addAll(result);

        logger.info("result is "+ result.size());
        return result2;
	}

	public List<Shape> getShapes() {
		if (aimShapes!=null){
    		logger.info("returning aimshapes. size "+aimShapes.size());
    		return aimShapes;
    	}
        List<Shape> result = new ArrayList<Shape>();
        for (MarkupEntity shape : getMarkupEntityCollection().getMarkupEntityList()) {
            if (shape instanceof TwoDimensionGeometricShapeEntity)
            	result.add(new Shape((TwoDimensionGeometricShapeEntity)shape));
        }
        List<Shape> result2 =organizeNormals(result);
        aimShapes=result2;
        return result2;
	}
	
	private Double roundDouble(Double val){
	    if (val!=null)
	        return ((double)Math.round(val*1000))/1000;
	    return val;
	}

	public User getLoggedInUser() {
		LoggedInUser result = null;

        User user = getUser();
        if (user!=null) {
            User firstUser = user;
//            logger.info("new loggedInUser " + firstUser.getLoginName()
//                    + firstUser.getName());
            result = new LoggedInUser(firstUser.getLoginName().getValue(),
                    firstUser.getName().getValue());
        }

        return result;
	}

	public int getDsoStartIndex() {
		return getImageAnnotation().getDsoStartIndex();
	}

	public Shape getShape(int shapeID) {
		 // logger.info("getShape " + shapeID);
        Shape result = null;
        for (MarkupEntity shape : getMarkupEntityCollection().getMarkupEntityList()) {
            if (shape instanceof TwoDimensionGeometricShapeEntity && ((TwoDimensionGeometricShapeEntity)shape).getShapeIdentifier() == shapeID) {
                result = new Shape((TwoDimensionGeometricShapeEntity)shape);
            }
        }
        return result;
	}
	
	public void removeShape(int shapeID) {
	   for (MarkupEntity shape : getMarkupEntityCollection().getMarkupEntityList()) {
           if (shape instanceof TwoDimensionGeometricShapeEntity && ((TwoDimensionGeometricShapeEntity)shape).getShapeIdentifier() == shapeID) {
        	   getMarkupEntityCollection().getMarkupEntityList().remove(shape);
           }
       }
	   removeAimShape(shapeID);
	}

	public void setShapeCoords(int shapeID, List<TwoDCoordinate> coords) {

        for (Shape shape : this.getShapes()) {
            if (shape.getShapeIdentifier() == shapeID) {
                List<TwoDimensionSpatialCoordinate> coordList = shape
                        .getTwoDimensionSpatialCoordinateCollection()
                        .getTwoDimensionSpatialCoordinateList();

                for (int i = 0; i < coordList.size(); i++) {
                    if (i < coords.size()) {
                        TwoDimensionSpatialCoordinate c = coordList
                                .get(i);
                        c.setX(coords.get(i).getX());
                        c.setY(coords.get(i).getY());
                    }
                }
                updateIAShapes();
                return;
            }
        }
    }
	
	/**
     * update the actual image annotation shapes to match the shape of this class 
     * extra measure to handle the specific classes with multiple shapes like normal
     */
    private void updateIAShapes(){
    	//if there are no aimshapes nothing to do
    	if (aimShapes==null) 
    		return;
    	for (Shape aimShape : this.aimShapes) {
	    	for (MarkupEntity meshape : this.getMarkupEntityCollection()
	                .getMarkupEntityList()) {
	    		if (meshape instanceof TwoDimensionGeometricShapeEntity){
	    			TwoDimensionGeometricShapeEntity shape =((TwoDimensionGeometricShapeEntity)meshape);
			    		if (aimShape instanceof Normal) {
			    			 if (shape instanceof TwoDimensionGeometricShapeEntity && ((TwoDimensionGeometricShapeEntity)shape).getShapeIdentifier() == aimShape.getShapeIdentifier()+1) {
			 	                updateCoords(shape
			 	                        .getTwoDimensionSpatialCoordinateCollection()
			 	                        .getTwoDimensionSpatialCoordinateList(),  aimShape
			 	                        .getTwoDimensionSpatialCoordinateCollection()
			 	                        .getTwoDimensionSpatialCoordinateList().subList(2, 4));
			 	            }
			    		}
			            if (shape.getShapeIdentifier() == aimShape.getShapeIdentifier()) {
			                updateCoords(shape
			                        .getTwoDimensionSpatialCoordinateCollection()
			                        .getTwoDimensionSpatialCoordinateList(),  aimShape
			                        .getTwoDimensionSpatialCoordinateCollection()
			                        .getTwoDimensionSpatialCoordinateList());
			            }
	    		}
	        }
    	}
    }
    
    /**
     * copy the aimshapecoords to xmlcoords
     * @param xmlCoords
     * @param aimShapeCoords
     */
    private void updateCoords(List<TwoDimensionSpatialCoordinate> xmlCoords, List<TwoDimensionSpatialCoordinate> aimShapeCoords){
    	for (int i = 0; i < xmlCoords.size(); i++) {
            if (i < aimShapeCoords.size()) {
                TwoDimensionSpatialCoordinate c = xmlCoords
                        .get(i);
                c.setX(((TwoDimensionSpatialCoordinate) aimShapeCoords.get(i)).getX());
                c.setY(((TwoDimensionSpatialCoordinate) aimShapeCoords.get(i)).getY());
            }
        }
    }
    @Override
    public ImageAnnotation getImageAnnotation(){
    	if (getImageAnnotations().isEmpty()){
    		this.addImageAnnotation(new ImageAnnotation());
    	}
    	return super.getImageAnnotation();
    }
	public void setName(String name) {
		this.getImageAnnotation().setName(new ST(name));
		
	}

	public void setPerson(String patientSex, String patientBirthDate) {
		this.getPerson().setBirthDate(patientBirthDate);
        this.getPerson().setSex(new ST(patientSex));
		
	}

	public void setShapeLabelOffset(int shapeID, double dx, double dy) {
		 List<Shape> shapes = this.getShapes();
		 for (Shape shape : shapes) {
			 if (shape.getShapeIdentifier() == shapeID) {
				 shape.setLabelOffsetX(dx);
				 shape.setLabelOffsetY(dy);
			 }
		 }
		
	}

	public String getAECodeMeaning(String label) {
		try {
			List<ImagingPhysicalEntity> listAnatomicEntity = getImagingPhysicalEntityCollection().getImagingPhysicalEntityList();
			for (ImagingPhysicalEntity anatomicEntity : listAnatomicEntity) {
				if (anatomicEntity.getLabel().getValue().equalsIgnoreCase(label)) {
					if (anatomicEntity.getListTypeCode()!=null) {
						if (anatomicEntity.getListTypeCode().size()>1)
							anatomicEntity.getListTypeCode().get(1).getCodeSystem();
						else if (anatomicEntity.getListTypeCode().size()>0)
							anatomicEntity.getListTypeCode().get(0).getCodeSystem();
							//TODO
//							getAllowedTerm() != null) {
//						return anatomicEntity.getAllowedTerm().getCodeMeaning();
//					} else if (anatomicEntity.getCodeMeaning() != null && !"".equals(anatomicEntity.getCodeMeaning())) {
//						return anatomicEntity.getCodeMeaning();
					}

				}
			}

		} catch (Exception e) {
			logger.info("Error: Aim getAECodeMeaning " + e.getMessage());
		}

		return "";
	}

	public String getIOCCodeValue(String IOlabel, String IOClabel) {
        try {
            List<ImagingObservationEntity> listImagingObservation = getImagingObservationEntityCollection().getImagingObservationEntityList();
            for (ImagingObservationEntity imagingObservation : listImagingObservation) {
                if (imagingObservation.getLabel().getValue().equalsIgnoreCase(IOlabel)) {
                    List<ImagingObservationCharacteristic> listImagingObservationCharacteristic = imagingObservation.getImagingObservationCharacteristicCollection().getImagingObservationCharacteristicList();
                    for (ImagingObservationCharacteristic imagingObservationCharacteristic : listImagingObservationCharacteristic) {
                        if (imagingObservationCharacteristic.getLabel().getValue().equalsIgnoreCase(IOClabel)) {
                            if (imagingObservationCharacteristic.getListTypeCode()!= null) {
                            	if (imagingObservationCharacteristic.getListTypeCode().size()>1)
                            		return imagingObservationCharacteristic.getListTypeCode().get(1).getCode();
        						else 
        							return imagingObservationCharacteristic.getListTypeCode().get(0).getCode();
                                //TODO what is this!!!!
//	                            } else if (imagingObservationCharacteristic.getCodeValue() != null && !"".equals(imagingObservationCharacteristic.getCodeValue())) {
//	                                return imagingObservationCharacteristic.getCodeValue();
                            }

                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.info("Error: Aim getObservationCodeValue " + e.getMessage());
        }

        return "";
    }
	

	public boolean hasMultiPoint() {
		for (MarkupEntity shape : getMarkupEntityCollection()
                .getMarkupEntityList()) {
            if (shape.getXsiType().toLowerCase().contains("multipoint")) {
                return true;
            }
        }
        return false;
	}

	public boolean hasAELabel(String label) {
		try {

            if (!"".equals(getAECodeMeaning(label))) {
                return true;
            }
            return false;         
        } catch (Exception e) {
            logger.info("Error: Aim hasAELabel " + e.getMessage());
        }

        return false;
	}

	public String getIOCodeValue(String label) {
       try {
            List<ImagingObservationEntity> listImagingObservation = getImagingObservationEntityCollection().getImagingObservationEntityList();
            for (ImagingObservationEntity imagingObservation : listImagingObservation) {
                if (imagingObservation.getLabel().getValue().equalsIgnoreCase(label)) {
                    if (imagingObservation.getListTypeCode() != null) {
                    	if (imagingObservation.getListTypeCode().size()>1)
                    		return imagingObservation.getListTypeCode().get(1).getCode();
						else 
							return imagingObservation.getListTypeCode().get(0).getCode();
                        //TODO what is this 
//                    } else if (imagingObservation.getCodeValue() != null && !"".equals(imagingObservation.getCodeValue())) {
//                        return imagingObservation.getCodeValue();
                    }

                }
            }

        } catch (Exception e) {
            logger.info("Error: Aim getObservationCodeValue " + e.getMessage());
        }

        return "";
	}

	public boolean hasIOCLabel(String IOlabel, String IOClabel) {
		 try {

	            if (!"".equals(getIOCCodeValue(IOlabel, IOClabel))) {
	                return true;
	            }
	            return false;
	        } catch (Exception e) {
	            logger.info("Error: Aim getObservationCodeValue " + e.getMessage());
	        }

	        return false;
	}


	public List<Component> getComponents(ComponentType componentType) {
		
		List<Component> result = new ArrayList<Component>();
		switch (componentType) {
		case anatomicEntity:
			for (ImagingPhysicalEntity anatomicEntity : getImagingPhysicalEntityCollection().getImagingPhysicalEntityList()) {
				result.add(new Component(anatomicEntity));
				for (ImagingPhysicalEntityCharacteristic anatomicEntityCharacteristic : anatomicEntity.getImagingPhysicalEntityCharacteristicCollection().getImagingPhysicalEntityCharacteristicList()) {
					result.add(new Component(anatomicEntityCharacteristic));
				}
			}
			break;
		case imagingObservation:
			for (ImagingObservationEntity imagingObservation : getImagingObservationEntityCollection().getImagingObservationEntityList()) {
				result.add(new Component(imagingObservation));
				for (ImagingObservationCharacteristic imagingObservationCharacteristic : imagingObservation.getImagingObservationCharacteristicCollection().getImagingObservationCharacteristicList()) {
					result.add(new Component(imagingObservationCharacteristic));
				}
			}
			break;
		case inference:
			for (InferenceEntity infernce : getInferenceEntityCollection().getInferenceEntityList()) {
				result.add(new Component(infernce));
			}
			break;
		default:
			break;

		}
		return result;
	}

	public void updateDateAndTime() {
		Date date = new Date();
        int year = date.getYear() + 1900;// cal.get(Calendar.YEAR);
        int month = date.getMonth() + 1;// cal.get(Calendar.MONTH) + 1;
        int day = date.getDate();// cal.get(Calendar.DAY_OF_MONTH);
        int hour = date.getHours();// cal.get(Calendar.HOUR_OF_DAY);
        int minute = date.getMinutes();// cal.get(Calendar.MINUTE);
        int second = date.getSeconds();//cal.get(Calendar.SECOND);

        String strMount = Integer.toString(month);
        if (month < 10) {
            strMount = "0" + strMount;
        }
        String strDay = Integer.toString(day);
        if (day < 10) {
            strDay = "0" + strDay;
        }
        String strHour = Integer.toString(hour);
        if (hour < 10) {
            strHour = "0" + strHour;
        }
        String strMinute = Integer.toString(minute);
        if (minute < 10) {
            strMinute = "0" + strMinute;
        }
        String strSecond = Integer.toString(second);
        if (second < 10) {
            strSecond = "0" + strSecond;
        }

        StringBuilder builder = new StringBuilder();
      ///ml change for proper aim format (clunie)
//        builder.append(Integer.toString(year)).append("-").append(strMount).append("-").append(strDay).append("T").append(strHour).append(":").append(strMinute).append(":").append(strSecond);
        builder.append(Integer.toString(year)).append(strMount).append(strDay).append(strHour).append(strMinute).append(strSecond);
        this.setDateTime(builder.toString());
        //ml set also the annotation's date time
        this.getImageAnnotation().setDateTime(builder.toString());
		
	}


	
    
	public ShapeType getShapeType(int shapeID) {

		ShapeType result = ShapeType.NONE;

		Shape shape = getShape(shapeID);

		if (shape == null) return result;

		String xsiType = shape.getXsiType();

		if (xsiType.toLowerCase().contains("multipoint")) {
			result = ShapeType.LINE;
		} else if (xsiType.toLowerCase().contains("polyline")) {
			result = ShapeType.POLY;
		} else if (xsiType.toLowerCase().contains("spline")) {
			result = ShapeType.SPLINE;
		} else if (xsiType.toLowerCase().contains("circle")) {
			result = ShapeType.CIRCLE;
		} else if (xsiType.toLowerCase().contains("point")) {
			result = ShapeType.POINT;
		} else {
			result = ShapeType.NONE;
		}
		return result;

	}

	public boolean isClosedShape(int shapeID) {
		return isPolygon(shapeID) || isCircle(shapeID) || isSpline(shapeID);
	}
	public boolean isCircle(int shapeID) {
		return getShapeType(shapeID).equals(ShapeType.CIRCLE);
	}
	
	public boolean isEllipse(int shapeID) {
		return getShapeType(shapeID).equals(ShapeType.ELLIPSE);
	}

	public boolean isSpline(int shapeID) {
		return getShapeType(shapeID).equals(ShapeType.SPLINE);
	}

	public boolean isNormal(int shapeID) {
		return getShapeType(shapeID).equals(ShapeType.NORMAL);
	}

	public boolean isPoint(int shapeID) {
		return getShapeType(shapeID).equals(ShapeType.POINT);
	}

	public boolean isPolygon(int shapeID) {
		return getShapeType(shapeID).equals(ShapeType.POLY);
	}

	public boolean isLine(int shapeID) {
		return getShapeType(shapeID).equals(ShapeType.LINE);
	}

	
	public Person createPerson(String name, String id, String sex,
            String birthdate) {
		Person person = new Person();
        person.setBirthDate(birthdate);
        person.setName(new ST(name));
        person.setId(new ST(id));
        person.setSex(new ST(sex));
        return person;
	}

	public Person createPerson(String name, String id, String sex,
            String birthdate, String sourcePatientGroupId) {

        Person person = new Person();
        person.setBirthDate(birthdate);
        person.setName(new ST(name));
        person.setId(new ST(id));
        if (!id.equalsIgnoreCase(sourcePatientGroupId))
        	person.setSourcePatientGroupId(new ST(sourcePatientGroupId));

        person.setSex(new ST(sex));
        return person;
    }

	public void clearValues() {
		getImagingPhysicalEntityCollection().getImagingPhysicalEntityList().clear();
        getImagingObservationEntityCollection().getImagingObservationEntityList().clear();;
        getInferenceEntityCollection().getInferenceEntityList().clear();;
	}

	public void addComponents(List<Component> components) {

		for (Component component : components) {
			switch (component.componentType) {
			case anatomicEntity:
				getImagingPhysicalEntityCollection().getImagingPhysicalEntityList().add(
						component.anatomicEntity);
				break;
			case imagingObservation:
				getImagingObservationEntityCollection().getImagingObservationEntityList()
				.add(component.imagingObservation);
				break;
			case inference:
				getInferenceEntityCollection().getInferenceEntityList().add(
						component.inference);
				break;
			default:
				break;
			}
		}
	}

	public static String getUCUMUnit(String units) {
		if (units==null || units.equalsIgnoreCase("pixels")) 
			return "1";
		if (units.equalsIgnoreCase("HU")) {
			return "[hnsf'U]";
		}else if (units.equalsIgnoreCase("SUV")) {
			return "{SUVbw}g/ml";
		}else if (units.equalsIgnoreCase("ratio")) {
			return "{ratio}";
		}
		return units;
    }

	//says static
	//selectedAim.addMeanCalculation(pixelMap.getMean(), shapeId, units);
//	selectedAim.addStdDevCalculation(pixelMap.getStdDev(), shapeId, units);
//	selectedAim.addMinCalculation(pixelMap.getMin(), shapeId, units);
//	selectedAim.addMaxCalculation(pixelMap.getMax(), shapeId, units);
	
	
	public void clearCalculationEntityCollection(){
		this.getImageAnnotation().getCalculationEntityCollection().getCalculationEntityList().clear();
		this.getImageAnnotation().getImageAnnotationStatementCollection().getImageAnnotationStatementList().clear();
	}
	
	//get the first annotation
	public CalculationEntityCollection getCalculationEntityCollection(){
		return this.getImageAnnotation().getCalculationEntityCollection();
	}

	
	public ImageReferenceEntityCollection getImageReferenceEntityCollection(){
		return this.getImageAnnotation().getImageReferenceEntityCollection();
	}

	public SegmentationEntityCollection getSegmentationEntityCollection(){
		return this.getImageAnnotation().getSegmentationEntityCollection();
	}
	
	public void clearSegmentationEntityCollection(){
		this.getImageAnnotation().getSegmentationEntityCollection().getSegmentationEntityList().clear();
		
	}

	public void addSegmentationEntity(DicomSegmentationEntity dicomSegmentationEntity) {
		this.getImageAnnotation().addSegmentationEntity(dicomSegmentationEntity);
		
	}
	
	 // create the image reference
    private DicomImageReferenceEntity createImageReference(String studyID,
            String studyDate, String studyTime) {
        return createImageReference(studyID, "", "", studyDate, studyTime, "",null);

    }
	
	//should't happen unless study annotation
	public static DicomImageReferenceEntity createImageReference(String studyID,
			String seriesID, String imageID, String studyDate, String studyTime) {
		return createImageReference(studyID, seriesID, imageID, studyDate, studyTime, "",null);
	}
	
	// ml create the image reference
	public static DicomImageReferenceEntity createImageReference(String studyID,
			String seriesID, String imageID, String studyDate, String studyTime, String sopClassUID) {
		return createImageReference(studyID, seriesID, imageID, studyDate, studyTime, sopClassUID,null);
	}
	//ml added accession number
	public static DicomImageReferenceEntity createImageReference(String studyID,
			String seriesID, String imageID, String studyDate, String studyTime, String sopClassUID, String accessionNumber) {

		// series reference
		ImageSeries imageSeries = new ImageSeries();
		imageSeries.setInstanceUid(new II(seriesID));
		imageSeries
		.addImage(new Image(new II(sopClassUID), new II(imageID))); //ml  soplclassuid added it was ""
		Modality mod=Modality.getInstance();
		if ((mod.get(sopClassUID))!=null)
			imageSeries.setModality(mod.get(sopClassUID));
		else 
			imageSeries.setModality(mod.getDefaultModality());

		// study reference
		ImageStudy study = new ImageStudy();
		study.setStartDate(studyDate);
		study.setStartTime(studyTime);
		study.setImageSeries(imageSeries);
		study.setInstanceUid(new II(studyID));
		if (accessionNumber!=null)
			study.setAccessionNumber(new ST(accessionNumber));

		// image reference
		DicomImageReferenceEntity imageReference = new DicomImageReferenceEntity();
		imageReference.setImageStudy(study);

		return imageReference;

	}


	public void printImageReference() {
		for (ImageReferenceEntity imageReference :  this.getImageAnnotation().getImageReferenceEntityCollection()
				.getImageReferenceEntityList()) {
			DicomImageReferenceEntity dicomImageReference = (DicomImageReferenceEntity) imageReference;

			ImageStudy imageStudy = dicomImageReference.getImageStudy();

			ImageSeries imageSeries = imageStudy.getImageSeries();

			for (Image image : imageSeries
					.getImageCollection().getImageList()) {
				logger.info("image reference " + imageStudy.getInstanceUid()
				+ " " + imageSeries.getInstanceUid() + " "
				+ image.getSopClassUid() + " | ");

			}
		}
	}

	/**
	 * debug output for the segmentation part of the aim
	 * 
	 * @param aim
	 */
	public void printSegmentation() {
		String result = "segmentation ";

		for (SegmentationEntity segmentation : this.getImageAnnotation().getSegmentationEntityCollection()
				.getSegmentationEntityList()) {
			if (segmentation instanceof DicomSegmentationEntity) {
				result += ((DicomSegmentationEntity)segmentation).getReferencedSopInstanceUid().getRoot() + " "
						+ ((DicomSegmentationEntity)segmentation).getSopClassUid().getRoot()
						+ ((DicomSegmentationEntity)segmentation).getStudyInstanceUid().getRoot() + " "
						+ ((DicomSegmentationEntity)segmentation).getSeriesInstanceUid().getRoot() + " "
						+ ((DicomSegmentationEntity)segmentation).getSopInstanceUid().getRoot() + " "
						+ ((DicomSegmentationEntity)segmentation).getSegmentNumber();
			}else {
				logger.warning("not a dicom segmentation entity");
			}
		}
		logger.info(result);

	}


	public InferenceEntityCollection getInferenceEntityCollection() {
		return this.getImageAnnotation().getInferenceEntityCollection();
	}

	public ImagingObservationEntityCollection getImagingObservationEntityCollection() {
		return this.getImageAnnotation().getImagingObservationEntityCollection();
	}

	public ImagingPhysicalEntityCollection getImagingPhysicalEntityCollection() {
		return this.getImageAnnotation().getImagingPhysicalEntityCollection();
	}
	
	public MarkupEntityCollection getMarkupEntityCollection() {
		return this.getImageAnnotation().getMarkupEntityCollection();
	}

	public void addMarkupEntity(MarkupEntity me){
		this.getImageAnnotation().addMarkupEntity(me);
	}
}