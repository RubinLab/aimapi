/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import com.google.gwt.i18n.client.DateTimeFormat;
import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.AnnotationStatement;
import edu.stanford.hakan.aim4api.base.CD;
import edu.stanford.hakan.aim4api.base.Enumerations;
import edu.stanford.hakan.aim4api.base.Equipment;
import edu.stanford.hakan.aim4api.base.II;
import edu.stanford.hakan.aim4api.base.Image;
import edu.stanford.hakan.aim4api.base.ImageCollection;
import edu.stanford.hakan.aim4api.base.ImageReferenceEntity;
import edu.stanford.hakan.aim4api.base.ImageSeries;
import edu.stanford.hakan.aim4api.base.ImageStudy;
import edu.stanford.hakan.aim4api.base.ImagingObservationEntityIsIdentifiedByTwoDimensionGeometricShapeEntityStatement;
import edu.stanford.hakan.aim4api.base.ImagingPhysicalEntityHasCalculationEntityStatement;
import edu.stanford.hakan.aim4api.base.ImagingPhysicalEntityHasTwoDimensionGeometricShapeEntityStatement;
import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.base.SegmentationEntity;
import edu.stanford.hakan.aim4api.base.SegmentationEntityCollection;
import edu.stanford.hakan.aim4api.base.TwoDimensionCircle;
import edu.stanford.hakan.aim4api.base.TwoDimensionGeometricShapeEntity;
import edu.stanford.hakan.aim4api.base.TwoDimensionMultiPoint;
import edu.stanford.hakan.aim4api.base.TwoDimensionPolyline;
import edu.stanford.hakan.aim4api.projects.epad.wrapper.Enumerations.ROIShape;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Hakan
 */
public class ImageAnnotation extends edu.stanford.hakan.aim4api.base.ImageAnnotation {

    private static Logger logger = Logger.getLogger("ImageAnnotation");

    //*** add getter and setters for the following ones.
    private String LINE_LENGTH = "LineLength";
    private String PRIVATE_DESIGNATOR = "private";
    private String LINE_MEASURE = "linear";
    private String EPAD_DESIGNATOR = "ePAD";
    private String VERSION = "1.0";
    private String DIMENSION = "Dimension Label";

    /*
     ImageAnnotation ; Epad = Aim

     + addAnatomicEntity(AnatomicEntity)
     + addCalculation(Calculation)
     + addEquipment(Equipment)
     + addGeometricShape(GeometricShape)
     + addImageReference(DICOMImageReference)
     + addPerson(Person)
     + addUser(User)
     cloneAim(ImageAnnotation, ViewportView, DicomSeries, int)
     + getAimVersion()
     + getAnatomicEntityCollection()

     ? getCagridId()

     + getCalculationCollection()
     + getCodeMeaning()
     + getCodeValue()
     + getCodingSchemeDesignator()
     + getCodingSchemeVersion()
     + getComment()
     + getDateTime()
     + getGeometricShapeCollection()
     getImageReferenceCollection()
     getImagingObservationCollection()
     getInferenceCollection()
     + getListPerson()
     + getListUser()
     + getName()
     */
    
    
      public ImageAnnotation(String name, String modality, String description,
            String patientName, String patientId, String patientSex,
            String patientBirthdate, String manufacturerName, String model,
            String version, int activeImage, User user, String imageUid,
            String seriesUid, String studyUid, String studyDate,
            String studyTime) {

        super();

        super.setName(new ST(name));
        super.setDateTime(todaysDate());
        super.setComment(new ST(fillComment(modality, description, activeImage)));

        addUser(user);

        addPerson(createPerson(patientName, patientId, patientSex,
                formatDateTime(patientBirthdate)));
        addEquipment(createEquipment(manufacturerName, model, version));
        addImageReference(createImageReference(imageUid, seriesUid, studyUid,
                activeImage, formatDateTime(studyDate, studyTime),
                formatTime(studyTime)));

        addAnatomicEntity(createAnatomicEntity());
    }

    public ImageAnnotation() {
    }
        
    public void addAnatomicEntity(AnatomicEntity anatomicEntity) {
        super.addImagingPhysicalEntity(anatomicEntity);
    }

    public void addCalculation(Calculation calculation) {
        super.addCalculationEntity(calculation);
    }

    public void addEquipment(Equipment equipment) {
        super.getImageAnnotationCollection().setEquipment(equipment);
    }

    public void addGeometricShape(GeometricShape geometricShape) {
        this.addMarkupEntity(geometricShape);
    }

    public void addImageReference(DICOMImageReference dICOMImageReference) {
        this.addImageReferenceEntity(dICOMImageReference);
    }

    public void addPerson(Person person) {
        this.getImageAnnotationCollection().setPerson(person);
    }

    public void addUser(User user) {
        this.getImageAnnotationCollection().setUser(user);
    }

    public String getAimVersion() {
        return this.getImageAnnotationCollection().getAimVersion().toString();
    }

    public AnatomicEntityCollection getAnatomicEntityCollection() {
        return (AnatomicEntityCollection) super.getImagingPhysicalEntityCollection();
    }

    public CalculationCollection getCalculationCollection() {
        return (CalculationCollection) super.getCalculationEntityCollection();
    }

    public String getCodeValue() {
        if (this.getListTypeCode() != null && this.getListTypeCode().size() > 0) {
            return this.getListTypeCode().get(0).getCode();
        }
        return "";
    }

    public void setCodeValue(String codeValue) {
        if (this.getListTypeCode() == null && this.getListTypeCode().size() <= 0) {
            this.getListTypeCode().add(new CD());
        }
        this.getListTypeCode().get(0).setCode(codeValue);
    }

    public String getCodeMeaning() {
        if (this.getListTypeCode() != null && this.getListTypeCode().size() > 0) {
            return this.getListTypeCode().get(0).getCodeSystem();
        }
        return "";
    }

    public void setCodeMeaning(String codeMeaning) {
        if (this.getListTypeCode() == null && this.getListTypeCode().size() <= 0) {
            this.getListTypeCode().add(new CD());
        }
        this.getListTypeCode().get(0).setCodeSystem(codeMeaning);

    }

    public String getCodingSchemeVersion() {
        if (this.getListTypeCode() != null && this.getListTypeCode().size() > 0) {
            return this.getListTypeCode().get(0).getCodeSystemVersion();
        }
        return "";
    }

    public void setCodingSchemeVersion(String codingSchemeVersion) {
        if (this.getListTypeCode() == null && this.getListTypeCode().size() <= 0) {
            this.getListTypeCode().add(new CD());
        }
        this.getListTypeCode().get(0).setCodeSystemVersion(codingSchemeVersion);
    }

    public String getCodingSchemeDesignator() {
        if (this.getListTypeCode() != null && this.getListTypeCode().size() > 0) {
            return this.getListTypeCode().get(0).getCodeSystemName();
        }
        return "";
    }

    public void setCodingSchemeDesignator(String codingSchemeDesignator) {
        if (this.getListTypeCode() == null && this.getListTypeCode().size() <= 0) {
            this.getListTypeCode().add(new CD());
        }
        this.getListTypeCode().get(0).setCodeSystemName(codingSchemeDesignator);
    }

    public String getCommentEpad() {
        return this.getComment().getValue();
    }

    public List<Person> getListPerson() {
        List<Person> res = new ArrayList<Person>();
        Person item = (Person) this.getImageAnnotationCollection().getPerson();
        if (item != null) {
            res.add(item);
        }
        return res;
    }

    public List<User> getListUser() {
        List<User> res = new ArrayList<User>();
        User item = (User) this.getImageAnnotationCollection().getUser();
        if (item != null) {
            res.add(item);
        }
        return res;

    }

    public String getNameEpad() {
        return this.getName().getValue();
    }
    
    public void setName(String name)
    {
        super.setName(new ST(name));
    }

    public GeometricShapeCollection getGeometricShapeCollection() {
        return (GeometricShapeCollection) super.getMarkupEntityCollection();
    }

    public String getUniqueIdentifierEpad() {
        return this.getUniqueIdentifier().getRoot();
    }

    public String getPrecedentReferencedAnnotationUID() {
        return this.getPrecedentReferencedAnnotationUid().getRoot();
    }

    public ImagingObservationCollection getImagingObservationCollection() {
        return (ImagingObservationCollection) super.getImagingObservationEntityCollection();
    }

    public InferenceCollection getInferenceCollection() {
        return (InferenceCollection) super.getInferenceEntityCollection();
    }

    public String getSeriesId() {
        try {
            List<ImageReferenceEntity> imageList = this.getImageReferenceEntityCollection().getImageReferenceEntityList();
            if (imageList.size() > 0) {
                ImageReferenceEntity x = imageList.get(0);
                DICOMImageReference y = (DICOMImageReference) x;
                ImageStudy study = y.getImageStudy();
                ImageSeries series = study.getImageSeries();
                return series.getInstanceUid().getRoot();
            }
        } catch (Exception e) {
            logger.info("ImageAnnotation getSeriesId exception " + e.getMessage());
//            throw new AimException(e.getMessage());
        }

        return "";
    }

    public String getStudyId() {
        try {
            List<ImageReferenceEntity> imageList = this.getImageReferenceEntityCollection().getImageReferenceEntityList();
            if (imageList.size() > 0) {
                ImageReferenceEntity x = imageList.get(0);
                DICOMImageReference y = (DICOMImageReference) x;
                ImageStudy study = y.getImageStudy();
                return study.getInstanceUid().getRoot();
            }
        } catch (Exception e) {
            logger.info("ImageAnnotation getStudyId exception " + e.getMessage());
            //throw new AimException(e.getMessage());
        }
        return "";
    }

    public void setLineLength(double length) throws AimException {
        try {
            List<Calculation> calculationList = this.getCalculationCollection().getCalculationList();
            List<edu.stanford.hakan.aim4api.projects.epad.wrapper.CalculationResult> calculationResultList = calculationList.get(0).getCalculationResultCollection().getCalculationResultListEpad();
            if (calculationResultList.size() > 0) {
                edu.stanford.hakan.aim4api.projects.epad.wrapper.CalculationResult calcResult = calculationResultList.get(0);
                List<CalculationData> dataList = calcResult.getCalculationDataCollectionEpad().getCalculationDataListEpad();
                if (dataList.size() > 0) {
                    dataList.get(0).setValue(String.valueOf(length));
                }
            }

        } catch (Exception e) {
            throw new AimException(e.getMessage());
        }
    }

    public double getLineLength() throws AimException {

        double length = 0;

        try {
            List<Calculation> calculationList = this.getCalculationCollection().getCalculationList();
            List<edu.stanford.hakan.aim4api.projects.epad.wrapper.CalculationResult> calculationResultList = calculationList.get(0).getCalculationResultCollection().getCalculationResultListEpad();
            if (calculationResultList.size() > 0) {
                edu.stanford.hakan.aim4api.projects.epad.wrapper.CalculationResult calcResult = calculationResultList.get(0);
                List<CalculationData> dataList = calcResult.getCalculationDataCollectionEpad().getCalculationDataListEpad();
                if (dataList.size() > 0) {
                    length = Double.parseDouble(dataList.get(0).getValueEpad());
                }
            }

        } catch (NumberFormatException e) {
            throw new AimException(e.getMessage());
        }

        return length;
    }

    public GeometricShape getShape() {
        List<GeometricShape> shapes = this.getGeometricShapeCollection().getGeometricShapeList();
        if (shapes.size() > 0) {
            return shapes.get(0);
        }
        return null;
    }

    public boolean hasShape() {
        try {
            for (GeometricShape gs : this.getGeometricShapeCollection().getGeometricShapeList()) {
                String shape = gs.getXsiType();
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getFirstShapeName() {
        List<GeometricShape> shapeList = this.getGeometricShapeCollection().getGeometricShapeList();
        if (shapeList != null && shapeList.size() > 0) {
            GeometricShape shape = shapeList.get(0);
            logger.info("ImageAnnotation getFirstShapeName " + shape.getShapeIdentifier());
            return shape.getXsiType();
        }
        return null;
    }

    public boolean isRectangle() {
        try {
            for (GeometricShape gs : this.getGeometricShapeCollection().getGeometricShapeList()) {
                if (gs.isRectangle()) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean isCircle() {
        try {
            for (GeometricShape gs : this.getGeometricShapeCollection().getGeometricShapeList()) {
                if (gs.isCircle()) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean isPolyline() {
        try {
            for (GeometricShape gs : this.getGeometricShapeCollection().getGeometricShapeList()) {
                if (gs.isPolyline()) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean isMultiPoint() {
        try {
            for (GeometricShape gs : this.getGeometricShapeCollection().getGeometricShapeList()) {
                if (gs.isMultiPoint()) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean isSpline() {
        try {
            for (GeometricShape gs : this.getGeometricShapeCollection().getGeometricShapeList()) {
                if (gs.isMultiPoint()) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public Date getStudyDate() {
        try {
            List<ImageReferenceEntity> imageList = this.getImageReferenceEntityCollection().getImageReferenceEntityList();
            ImageReferenceEntity imageReference = imageList.get(0);
            DICOMImageReference dicomImageReference = (DICOMImageReference) imageReference;
            ImageStudy study = dicomImageReference.getImageStudy();
            DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd");
            Date date = new Date();
            date = fmt.parse(study.getStartDate().substring(0, 10));
            return date;
        } catch (Exception e) {
            logger.info("Error: ImageAnnotation.getStudyDate " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public String formatStudyDate() {
        try {
            Date studyDate = this.getStudyDate();
            DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd");
            return fmt.format(studyDate);
        } catch (Exception e) {
            logger.info("Error: ImageAnnotation.formatStudyDate " + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    public String getPatientId() {
        try {
            String id = this.getListPerson().get(0).getId().getValue();
            return id;

        } catch (Exception e) {
            logger.info("Error: getPatientId " + e.getMessage());
        }
        return "";
    }

    public String getOwner() {
        if (this.getListUser() != null) {
            if (!this.getListUser().isEmpty()) {
                return this.getListUser().get(0).getLoginName().getValue();
            }
        }
        return "none";
    }

    public String getCodeValueEpad() {
        String result = null;
        try {
            result = this.getImagingObservationCollection()
                    .getImagingObservationList().get(0)
                    .getImagingObservationCharacteristicCollectionEpad()
                    .getImagingObservationCharacteristicListEpad().get(0)
                    .getCodeValue();
        } catch (Exception e) {
            logger.info("Error: AimUtils getCodeValue " + e.getMessage());
        }
        return result;
    }

    public String getCodeMeaningEpad() {
        String result = null;
        try {
            result = this.getImagingObservationCollection()
                    .getImagingObservationList().get(0)
                    .getImagingObservationCharacteristicCollectionEpad()
                    .getImagingObservationCharacteristicListEpad().get(0)
                    .getCodeMeaning();
        } catch (Exception e) {
            logger.info("Error: AimUtils getCodeValue " + e.getMessage());
        }
        return result;
    }

    public String getLesionLabel() {
        String result = null;
        try {
            result = this.getImagingObservationCollection()
                    .getImagingObservationList().get(0)
                    .getImagingObservationCharacteristicCollectionEpad()
                    .getImagingObservationCharacteristicListEpad().get(0)
                    .getLabelEpad();
        } catch (Exception e) {
            logger.info("Error: AimUtils getCodeValue " + e.getMessage());
        }
        return result;
    }

    public boolean isBaseline() {
        boolean result = false;
        try {
            result = this.getImagingObservationCollection()
                    .getImagingObservationList().get(0).getCodeValue()
                    .equals("S81");
        } catch (Exception e) {
            logger.info("Error: AimUtils isBaseline " + e.getMessage());
        }
        return result;
    }

    public boolean isFollowUp() {
        boolean result = false;

        try {
            result = this.getImagingObservationCollection()
                    .getImagingObservationList().get(0).getCodeValue()
                    .equals("S82");
        } catch (Exception e) {
            logger.info("Error: AimUtils isFollowUp " + e.getMessage());
        }

        return result;
    }

    public boolean isTarget() {
        String value = "";
        try {
            value = this.getCodeValueEpad();
        } catch (Exception e) {
            logger.info("Error: AimUtils isTarget " + e.getMessage());
        }
        return (value.equalsIgnoreCase("S71"));
    }

    public boolean isNonTarget() {
        String value = "";
        try {
            value = this.getCodeValueEpad();
        } catch (Exception e) {
            logger.info("Error: AimUtils isTarget " + e.getMessage());
        }
        return (value.equalsIgnoreCase("S72"));
    }

    public boolean isResolved() {
        String value = "";
        try {
            value = this.getCodeValueEpad();
        } catch (Exception e) {
            logger.info("Error: AimUtils isTarget " + e.getMessage());
        }
        return (value.equalsIgnoreCase("S74"));
    }

    public boolean isNew() {
        String value = "";
        try {
            value = this.getCodeValueEpad();
        } catch (Exception e) {
            logger.info("Error: AimUtils isTarget " + e.getMessage());
        }
        return (value.equalsIgnoreCase("S73"));
    }

    public String getLesionStatus() {
        try {
            Iterator<AnatomicEntity> it = this.getAnatomicEntityCollection()
                    .getAnatomicEntityList().iterator();
            while (it.hasNext()) {
                AnatomicEntity entity = it.next();
                if (entity.getLabel().equals("Status")) {
                    return entity.getCodeMeaning();
                }
            }
        } catch (Exception e) {
            logger.info("Error: AimUtils getLesionStatus " + e.getMessage());
        }
        return "";
    }

    public String getLesionLocation() {
        String result = "";

        try {
            result = (((List) this.getAnatomicEntityCollection()
                    .getAnatomicEntityList()).size() > 0 ? this
                    .getAnatomicEntityCollection().getAnatomicEntityList()
                    .get(0).getCodeMeaning() : "");
        } catch (Exception e) {
            logger.info("Error: AimUtils getLesionLocation " + e.getMessage());
        }
        return result;
    }

    public String getPatientName() {
        if (this.getListPerson() != null) {
            if (!this.getListPerson().isEmpty()) {
                return this.getListPerson().get(0).getNameEpad().replace("^", " ");
            }
        }
        return "";
    }

    public String cleanPatientName(ImageAnnotation aim) {
        return this.getPatientName().replace("^", " ");
    }

    public String getImageId() {
        String result = "";
        try {
            List<ImageReferenceEntity> imageReferenceList = this
                    .getImageReferenceEntityCollection().getImageReferenceEntityList();
            if (imageReferenceList.size() > 0) {

                ImageReferenceEntity imageReference = imageReferenceList.get(0);
                DICOMImageReference dicomImageReference = (DICOMImageReference) imageReference;
                ImageStudy study = dicomImageReference.getImageStudy();
                ImageSeries series = study.getImageSeries();
                ImageCollection imageCollection = series.getImageCollection();
                List<Image> imageList = imageCollection.getImageList();
                Image image = imageList.get(0);
                result = image.getSopInstanceUid().getRoot().replaceAll("_", ".");
            }
        } catch (Exception e) {
            logger.info("Error: getImageId " + e.getMessage());
        }
        return result;
    }

    public boolean hasSegmentation() {
        try {
            SegmentationEntityCollection segmentationCollection = this
                    .getSegmentationEntityCollection();
            List<SegmentationEntity> segmentationList = segmentationCollection.getSegmentationEntityList();
            SegmentationEntity segmentation = segmentationList.get(0);
            //String id = segmentation.getSopInstanceUID();
            String id = segmentation.getUniqueIdentifier().getRoot();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getSegmentationId() {
        String id = "";
        if (this.hasSegmentation()) {
            try {
                SegmentationEntityCollection segmentationCollection = this.getSegmentationEntityCollection();
                List<SegmentationEntity> segmentationList = segmentationCollection.getSegmentationEntityList();
                SegmentationEntity segmentation = segmentationList.get(0);
                //id = segmentation.getSopInstanceUID();
                id = segmentation.getUniqueIdentifier().getRoot();
            } catch (Exception e) {
                logger.info("Error: getSegmentationId exception "
                        + e.getMessage());
            }
        }
        return id;
    }

    // create a default name for an annotation
    public String todaysDate() {
        Date today = new Date();
        DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd'T'HH:mm:ss");
        return fmt.format(today);
    }

    // create a default name for an annotation
    public String fillComment(String modality, String description, int image) {
        return modality + " / " + description + " / " + image;
    }

    // create a person(patient) for this aim
    public Person createPerson(String name, String id, String sex,
            String birthdate) {

        Person person = new Person();
        person.setBirthDate(birthdate);
        person.setName(name);
        person.setId(id);
        person.setSex(sex);
        return person;
    }

    // format date in the aim format
    protected String formatDateTime(String d) {

        String date = ((d.length() >= 4) ? d.substring(0, 4) : "00") + "-"
                + ((d.length() >= 6) ? d.substring(4, 6) : "00") + "-"
                + ((d.length() >= 8) ? d.substring(6, 8) : "00") + "T"
                + formatTime("");
        return date;
    }

    // format time in the aim format
    protected String formatTime(String t) {

        String time = ((t.length() >= 2) ? t.substring(0, 2) : "00") + ":"
                + ((t.length() >= 4) ? t.substring(2, 4) : "00") + ":"
                + ((t.length() >= 6) ? t.substring(4, 6) : "00");
        return time;
    }

    // create the equipment object for this series
    public edu.stanford.hakan.aim4api.projects.epad.wrapper.Equipment createEquipment(String name, String model, String version) {

        edu.stanford.hakan.aim4api.projects.epad.wrapper.Equipment equipment = new edu.stanford.hakan.aim4api.projects.epad.wrapper.Equipment();
        equipment.setManufacturerName(name);
        equipment.setManufacturerModelName(model);
        equipment.setSoftwareVersion(version);
        return equipment;
    }

    // create the image reference
    public DICOMImageReference createImageReference(String imageUid,
            String seriesUid, String studyUid, int image, String studyDate,
            String studyTime) {

        // series reference
        ImageSeries imageSeries = new ImageSeries();
        imageSeries.setInstanceUid(new II(seriesUid));
        imageSeries.addImage(new edu.stanford.hakan.aim4api.base.Image("",
                imageUid));

        // study reference
        ImageStudy study = new ImageStudy();
        study.setStartDate(studyDate);
        study.setStartTime(studyTime);
        study.setImageSeries(imageSeries);
        study.setInstanceUid(new II(studyUid));

        // image reference
        DICOMImageReference imageReference = new DICOMImageReference();
        imageReference.setImageStudy(study);

        return imageReference;

    }

    // create a date time in the aim format
    protected String formatDateTime(String d, String t) {

        String date = ((d.length() >= 4) ? d.substring(0, 4) : "00") + "-"
                + ((d.length() >= 6) ? d.substring(4, 6) : "00") + "-"
                + ((d.length() >= 8) ? d.substring(6, 8) : "00") + "T"
                + formatTime(t);
        return date;
    }

    // create the image reference
    public AnatomicEntity createAnatomicEntity() {

        AnatomicEntity entity = new AnatomicEntity();
        entity.setAnnotatorConfidence(0.0);
        entity.setCodeMeaning("background");
        entity.setCodeValue("0");
        entity.setCodingSchemeDesignator("ePAD");
        entity.setLabel("background");
        return entity;
    }

    public String getLINE_LENGTH() {
        return LINE_LENGTH;
    }

    public void setLINE_LENGTH(String LINE_LENGTH) {
        this.LINE_LENGTH = LINE_LENGTH;
    }

    public String getPRIVATE_DESIGNATOR() {
        return PRIVATE_DESIGNATOR;
    }

    public void setPRIVATE_DESIGNATOR(String PRIVATE_DESIGNATOR) {
        this.PRIVATE_DESIGNATOR = PRIVATE_DESIGNATOR;
    }

    public String getLINE_MEASURE() {
        return LINE_MEASURE;
    }

    public void setLINE_MEASURE(String LINE_MEASURE) {
        this.LINE_MEASURE = LINE_MEASURE;
    }

    public String getEPAD_DESIGNATOR() {
        return EPAD_DESIGNATOR;
    }

    public void setEPAD_DESIGNATOR(String EPAD_DESIGNATOR) {
        this.EPAD_DESIGNATOR = EPAD_DESIGNATOR;
    }

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public String getDIMENSION() {
        return DIMENSION;
    }

    public void setDIMENSION(String DIMENSION) {
        this.DIMENSION = DIMENSION;
    }

    public double getLineLenghtCalculation() {
        return -1;
    }

    



    private List<GeometricShape> createShapes(String imageUid, int frame,
			ROIShape roiShape, List<TwoDCoordinate> coords, double pixelSpacingX, double pixelSpacingY) {

		// build the shapes
		List<GeometricShape> shapes = new ArrayList<GeometricShape>();

		switch (roiShape) {
		case LINE:
		case OPENPOLY:
			MultiPoint multiPoint = new MultiPoint();
			multiPoint.setShapeIdentifier(0);
			GeometricShape shape = createShape(multiPoint, coords, imageUid, frame);
			shapes.add(shape);
			addlengthCalculation(coords, getLength(coords, pixelSpacingX, pixelSpacingY), 0); 
			break;
		case POLY:
		case SPLINE:
		case OPENSPLINE:
		case RECTANGLE:
			Polyline polyline = new Polyline();
			polyline.setShapeIdentifier(0);
			shapes.add(createShape(polyline, coords, imageUid, frame));
			break;
		case CIRCLE:
			Circle circle = new Circle();
			circle.setShapeIdentifier(0);
			shapes.add(createShape(circle, coords, imageUid, frame));
			break;
		case NORMAL:
			// add the long axis line
			List<TwoDCoordinate> longAxis = new ArrayList<TwoDCoordinate>();
			longAxis.add(coords.get(0));
			longAxis.add(coords.get(1));
			MultiPoint longShape = new MultiPoint();
			longShape.setShapeIdentifier(0);
			shapes.add(createShape(longShape, longAxis, imageUid, frame));
			
			
			// and its length calculation
			addlengthCalculation(longAxis, getLength(longAxis, pixelSpacingX, pixelSpacingY), 0);

			// add the short axis line
			List<TwoDCoordinate> shortAxis = new ArrayList<TwoDCoordinate>();
			shortAxis.add(coords.get(2));
			shortAxis.add(coords.get(3));
			MultiPoint shortShape = new MultiPoint();
			shortShape.setShapeIdentifier(1);
			shapes.add(createShape(shortShape, shortAxis, imageUid, frame));
			
			// and its length calculation
			addlengthCalculation(shortAxis, getLength(shortAxis, pixelSpacingX, pixelSpacingY), 1);
			break;
		}

		return shapes;
	}

    private GeometricShape createShape(GeometricShape shape, List<TwoDCoordinate> coords, String imageUid, int frame) {
        // put the coords into the shape
        for (int i = 0; i < coords.size(); i++) {
            TwoDimensionSpatialCoordinate coord = (TwoDimensionSpatialCoordinate) coords.get(i);
            coord.setImageReferenceUID(imageUid);
            coord.setReferencedFrameNumber(frame);
            coord.setCoordinateIndex(i);
            shape.addSpatialCoordinate(new TwoDimensionSpatialCoordinate(i, imageUid, frame, coord.getX(), coord.getY()));
        }
        return shape;
    }
    
    	
//    
//       private void addCoordsToShape(TwoDimensionGeometricShapeEntity shape, List<TwoDCoordinate> coords, String imageUid, int frame) {
//        // put the coords into the shape
//        for (int i = 0; i < coords.size(); i++) {
//            TwoDimensionSpatialCoordinate coord = coords.get(i);
//            coord.setImageReferenceUID(imageUid);
//            coord.setReferencedFrameNumber(frame);
//            coord.setCoordinateIndex(i);
//            shape.addTwoDimensionSpatialCoordinate(new TwoDimensionSpatialCoordinate(i, imageUid, frame, coord.getX(), coord.getY()));
//        }
//    }
       // add the shape to this aim
    public void addShapes(String imageUid, int frame, edu.stanford.hakan.aim4api.projects.epad.wrapper.Enumerations.ROIShape roiShape,
            List<TwoDCoordinate> coords, int activeImage, double pixelSpacingX, double pixelSpacingY) {

        List<GeometricShape> shapes = createShapes(imageUid, frame, roiShape, coords, pixelSpacingX, pixelSpacingY);
        for (GeometricShape shape : shapes) {
            // create the geometric shape
            shape.setIncludeFlag(true);
            shape.setLineColor(new ST(""));
            shape.setLineOpacity(new ST(""));
            shape.setLineStyle(new ST(""));
            shape.setLineThickness(new ST(""));
            addGeometricShape(shape);
        }
    }

    private double getLength(List<TwoDCoordinate> coords, double pixelSpacingX,
            double pixelSpacingY) {
        double result = 0.0;
        if (coords.size() == 2) {
            double length = Math.abs(coords.get(0).getX()
                    - coords.get(1).getX());
            double width = Math
                    .abs(coords.get(0).getY() - coords.get(1).getY());
            return Math.sqrt(length * length + width * width) * pixelSpacingX
                    / 10.0;
        }
        logger.info("getLength " + result);
        return result;
    }

    public void clearValues() {
        getAnatomicEntityCollection().getAnatomicEntityList().clear();
        getImagingObservationCollection().getImagingObservationList().clear();
        getInferenceCollection().getInferenceList().clear();

    }
    
    // TODO not filling out the calculation correctly yet
    private void addlengthCalculation(List<TwoDCoordinate> coords, double length, int shapeId) {
        logger.info("addLengthCalculation " + length);

        // Create a Calculation instance
        Calculation calculation = new Calculation();
        calculation.setAlgorithmVersion(VERSION);
        calculation.setAlgorithmName(LINE_LENGTH);
        calculation.setUid(LINE_LENGTH);  // TODO this is a problem
        calculation.setDescription(LINE_LENGTH);
        calculation.setCodeValue(LINE_LENGTH);
        calculation.setCodeMeaning(LINE_LENGTH);
        calculation.setCodingSchemeDesignator(PRIVATE_DESIGNATOR);
        // Create a CalculationResult instance
        CalculationResult calculationResult = new CalculationResult();
        calculationResult.setType(Enumerations.CalculationResultIdentifier.Scalar);
        calculationResult.setUnitOfMeasure(new ST(LINE_MEASURE));
        // Create a CalculationData instance
        CalculationData calculationData = new CalculationData();
        calculationData.setValue(Double.toString(length));
        calculationData.addCoordinate(0, 0);  //TODO what goes here?
        // Create a Dimension instance
        Dimension dimension = new Dimension(0, 0, DIMENSION);
        // Add calculationData to calculationResult
        calculationResult.addCalculationData(calculationData);
        // Add dimension to calculationResult
        calculationResult.addDimension(dimension);
        // Add calculationResult to calculation
        calculation.addCalculationResult(calculationResult);
        // add the shape reference to the calculation

        ImagingPhysicalEntity imagingPhysicalEntity = new ImagingPhysicalEntity();
        imagingPhysicalEntity.addTypeCode(new CD("codeValue", "codeMeaning", "codingSchemeDesignator", "codingSchemeVersion"));
        imagingPhysicalEntity.setUniqueIdentifier(LINE_LENGTH);
        this.addImagingPhysicalEntity(imagingPhysicalEntity);

        ImagingPhysicalEntityHasTwoDimensionGeometricShapeEntityStatement connectGeoShapeWithImagingPhysicalEntity = new ImagingPhysicalEntityHasTwoDimensionGeometricShapeEntityStatement();
        connectGeoShapeWithImagingPhysicalEntity.setSubjectUniqueIdentifier(new II(String.valueOf(shapeId)));
        connectGeoShapeWithImagingPhysicalEntity.setObjectUniqueIdentifier(new II(imagingPhysicalEntity.getUniqueIdentifier().getRoot()));
        this.addImageAnnotationStatement(connectGeoShapeWithImagingPhysicalEntity);

        ImagingPhysicalEntityHasCalculationEntityStatement connectCalculationWithImagingPhysicalEntity = new ImagingPhysicalEntityHasCalculationEntityStatement();
        connectCalculationWithImagingPhysicalEntity.setSubjectUniqueIdentifier(new II(calculation.getUid()));
        connectCalculationWithImagingPhysicalEntity.setObjectUniqueIdentifier(new II(imagingPhysicalEntity.getUniqueIdentifier().getRoot()));
        this.addImageAnnotationStatement(connectCalculationWithImagingPhysicalEntity);

        this.addCalculation(calculation);
    }
    
    
    // put the modified shape length calculation back into the aim
    public boolean setShapeLength(int shapeId, double shapeLength, double pixelSpacingX, double pixelSpacingY) {

        List<Calculation> calculations = this.getCalculationCollection().getCalculationList();
        if (calculations != null) {
            for (Calculation calculation : calculations) {
                if ((calculation.getAlgorithmName() != null) && (calculation.getAlgorithmName().equals(LINE_LENGTH))) {

                    List<AnnotationStatement> annotationStatements = this.getImageAnnotationStatementCollection().getImageAnnotationStatementList();
                    for (AnnotationStatement annotationStatement : annotationStatements) {
                        if (annotationStatement.getSubjectUniqueIdentifier().getRoot().equals(Integer.toString(shapeId))) {

                        }
                    }
//
//                    List<ReferencedGeometricShape> shapes = calculation.getReferencedGeometricShapeCollection().getReferencedGeometricShapeList();
//                    for (ReferencedGeometricShape shape : shapes) {
//                        if (shape.getReferencedShapeIdentifier() == shapeId) {
//                            // update this calculation and return
//                            List<CalculationResult> results = calculation.getCalculationResultCollection().getCalculationResultList();
//                            for (CalculationResult result : results) {
//
//                                CalculationDataCollection dataCollection = new CalculationDataCollection();
//                                List<CalculationData> data = new ArrayList<CalculationData>();
//                                CalculationData calculationData = new CalculationData();
//                                //calculationData.setCagridId(0);
//                                calculationData.setValue(shapeLength);
//                                calculationData.addCoordinate(0, 0, 0);
//                                data.add(calculationData);
//
//                                dataCollection.AddCalculationData(calculationData);
//                                result.setCalculationDataCollection(dataCollection);
//                                return true;
//                            }
//                        }
//                    }
                }
            }
        }

        return false;
    }
    


//    public void testFunction(TwoDimensionGeometricShapeEntity GeoShapeEntity)
//    {
//       
//    }
//    
//    private void test2()
//    {
//        TwoDimensionPolyline poli = new TwoDimensionPolyline();
//        GeometricShape geoShape = new GeometricShape();
//        testFunction(poli);
//        testFunction(geoShape);
//    }
}

//    <ImageAnnotationStatement xsi:type="ImagingPhysicalEntityHasTwoDimensionGeometricShapeEntityStatement">
//        <subjectUniqueIdentifier root="TwoDimensionCircle-123"/>
//        <objectUniqueIdentifier root="ImagingPhysicalEntity-123"/>
//    </ImageAnnotationStatement>
//
//    <ImageAnnotationStatement xsi:type="ImagingPhysicalEntityHasCalculationEntityStatement">
//        <subjectUniqueIdentifier root="CalculationEntity-123"/>
//        <objectUniqueIdentifier root="ImagingPhysicalEntity-123"/>
//    </ImageAnnotationStatement>
