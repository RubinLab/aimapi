package edu.stanford.hakan.aim4api.project.epad;

//Copyright (c) 2013 The Board of Trustees of the Leland Stanford Junior University
//All rights reserved.
//
//Redistribution and use in source and binary forms, with or without modification, are permitted provided that 
//the following conditions are met:
//
//Redistributions of source code must retain the above copyright notice, this list of conditions and the following 
//disclaimer.
//
//Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the 
//following disclaimer in the documentation and/or other materials provided with the distribution.
//
//Neither the name of The Board of Trustees of the Leland Stanford Junior University nor the names of its 
//contributors (Daniel Rubin, et al) may be used to endorse or promote products derived from this software without 
//specific prior written permission.
//
//THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
//INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
//DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
//SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
//SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
//WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE 
//USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//import com.google.gwt.i18n.client.DateTimeFormat;
import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.compability.aimv3.AimUtility.CalculationResultIdentifier;
import edu.stanford.hakan.aim4api.compability.aimv3.AnatomicEntity;
import edu.stanford.hakan.aim4api.compability.aimv3.AnatomicEntityCharacteristic;
import edu.stanford.hakan.aim4api.compability.aimv3.AnatomicEntityCharacteristicCollection;
import edu.stanford.hakan.aim4api.compability.aimv3.AnatomicEntityCollection;
import edu.stanford.hakan.aim4api.compability.aimv3.Calculation;
import edu.stanford.hakan.aim4api.compability.aimv3.CalculationCollection;
import edu.stanford.hakan.aim4api.compability.aimv3.CalculationData;
import edu.stanford.hakan.aim4api.compability.aimv3.CalculationDataCollection;
import edu.stanford.hakan.aim4api.compability.aimv3.CalculationResult;
import edu.stanford.hakan.aim4api.compability.aimv3.Circle;
import edu.stanford.hakan.aim4api.compability.aimv3.DICOMImageReference;
import edu.stanford.hakan.aim4api.compability.aimv3.Dimension;
import edu.stanford.hakan.aim4api.compability.aimv3.Equipment;
import edu.stanford.hakan.aim4api.compability.aimv3.GeometricShape;
import edu.stanford.hakan.aim4api.compability.aimv3.GeometricShapeCollection;
import edu.stanford.hakan.aim4api.compability.aimv3.Image;
import edu.stanford.hakan.aim4api.compability.aimv3.ImageAnnotation;
import edu.stanford.hakan.aim4api.compability.aimv3.ImageReference;
import edu.stanford.hakan.aim4api.compability.aimv3.ImageReferenceCollection;
import edu.stanford.hakan.aim4api.compability.aimv3.ImageSeries;
import edu.stanford.hakan.aim4api.compability.aimv3.ImageStudy;
import edu.stanford.hakan.aim4api.compability.aimv3.ImagingObservation;
import edu.stanford.hakan.aim4api.compability.aimv3.ImagingObservationCharacteristic;
import edu.stanford.hakan.aim4api.compability.aimv3.ImagingObservationCharacteristicCollection;
import edu.stanford.hakan.aim4api.compability.aimv3.ImagingObservationCollection;
import edu.stanford.hakan.aim4api.compability.aimv3.Inference;
import edu.stanford.hakan.aim4api.compability.aimv3.InferenceCollection;
import edu.stanford.hakan.aim4api.compability.aimv3.MultiPoint;
import edu.stanford.hakan.aim4api.compability.aimv3.Person;
import edu.stanford.hakan.aim4api.compability.aimv3.Point;
import edu.stanford.hakan.aim4api.compability.aimv3.Polyline;
import edu.stanford.hakan.aim4api.compability.aimv3.ReferencedGeometricShape;
import edu.stanford.hakan.aim4api.compability.aimv3.Segmentation;
import edu.stanford.hakan.aim4api.compability.aimv3.SegmentationCollection;
import edu.stanford.hakan.aim4api.compability.aimv3.SpatialCoordinate;
import edu.stanford.hakan.aim4api.compability.aimv3.SpatialCoordinateCollection;
import edu.stanford.hakan.aim4api.compability.aimv3.Spline;
import edu.stanford.hakan.aim4api.compability.aimv3.TwoDimensionSpatialCoordinate;
import edu.stanford.hakan.aim4api.compability.aimv3.User;
import edu.stanford.hakan.aim4api.project.epad.Enumerations.ComponentType;
import edu.stanford.hakan.aim4api.project.epad.Enumerations.ShapeType;
import java.io.Serializable;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Debra Willrett
 *
 */
@SuppressWarnings("serial")
public class Aim extends ImageAnnotation implements Aimapi, Serializable {

    private static final Logger logger = Logger.getLogger("Aim");

    private static final int caGridId = 0;
    private static final String LINE_LENGTH = "LineLength";
    private static final String PRIVATE_DESIGNATOR = "private";
    private static final String LINE_MEASURE = "linear";
    private static final String VERSION = "1.0";
    

    public Aim() {
    }

    // create the aim from the imageAnnotation
    public Aim(ImageAnnotation ia) {

        super();

        setListUser(ia.getListUser());
        setListEquipment(ia.getListEquipment());
        setListAimStatus(ia.getListAimStatus());
        setCagridId(caGridId);
        setAimVersion(ia.getAimVersion(), "al536anhb55555");
        setComment(ia.getComment());
        setDateTime(ia.getDateTime());
        setName(ia.getName());
        setUniqueIdentifier(ia.getUniqueIdentifier(), "al536anhb55555");
        setCodeValue(ia.getCodeValue());
        setCodeMeaning(ia.getCodeMeaning());
        setCodingSchemeDesignator(ia.getCodingSchemeDesignator());
        setCodingSchemeVersion(ia.getCodingSchemeVersion());
        setPrecedentReferencedAnnotationUID(ia
                .getPrecedentReferencedAnnotationUID());
        setXsiType(ia.getXsiType());
        setOntologyPrefix(ia.getOntologyPrefix());

        setInferenceCollection(ia.getInferenceCollection());
        setAnatomicEntityCollection(ia.getAnatomicEntityCollection());
        setImagingObservationCollection(ia.getImagingObservationCollection());

        setCalculationCollection(ia.getCalculationCollection());
        setSegmentationCollection(ia.getSegmentationCollection());
        setImageReferenceCollection(ia.getImageReferenceCollection());
        setGeometricShapeCollection(ia.getGeometricShapeCollection());
        setTextAnnotationCollection(ia.getTextAnnotationCollection());
        setListPerson(ia.getListPerson());
    }

    // build the new imageAnnotation
    public Aim(String name, String modality, String description,
            String patientName, String patientId, String patientSex,
            String patientBirthdate, String manufacturerName, String model,
            String version, int activeImage, LoggedInUser user,
            String imageUid, String seriesUid, String studyUid,
            String studyDate, String studyTime) {

        super();

        setName(name);
        setDateTime(todaysDate());
        setCagridId(caGridId);
        setComment(fillComment(modality, description, activeImage));

        addUser(user);

        addPerson(createPerson(patientName, patientId, patientSex,
                patientBirthdate));
        addEquipment(createEquipment(manufacturerName, model, version));

        // don't add the image reference yet, we don't have any shapes or segs
        addImageReference(createImageReference(studyUid, seriesUid, imageUid,
                studyDate, studyTime));

        addAnatomicEntity(createAnatomicEntity());

    }

    // build the new imageAnnotation
    public Aim(String name, String modality, String description,
            String patientName, String patientId, String patientSex,
            String patientBirthdate, String manufacturerName, String model,
            String version, int activeImage, LoggedInUser user,
            String imageUid, String seriesUid, String studyUid,
            String studyDate, String studyTime, String sopClassUID,
            String referencedSopInstanceUID, int segmentNumber,
            String sopInstanceUID, String dsoSeriesUID) {

        super();

        setName(name);
        setDateTime(todaysDate());
        setCagridId(caGridId);
        setComment(fillComment(modality, description, activeImage));
        addUser(user);
        addPerson(createPerson(patientName, patientId, patientSex,
                patientBirthdate));
        addEquipment(createEquipment(manufacturerName, model, version));

        // add a reference pointing to the source dicom
        addAnatomicEntity(createAnatomicEntity());
        addImageReference(createImageReference(studyUid, seriesUid, imageUid,
                studyDate, studyTime));

        // add the segmentation and an image reference pointing to the
        // segmentation dso
        addSegmentation(new Segmentation(caGridId, sopInstanceUID, sopClassUID,
                referencedSopInstanceUID, segmentNumber));
        addImageReference(createImageReference(studyUid, dsoSeriesUID,
                sopInstanceUID, studyDate, studyTime));

    }

    public Aim(String name, String patientName, String patientId,
            LoggedInUser user, String studyUid, String studyDate,
            String studyTime) {

        super();
        setName(name);
        setDateTime(todaysDate());
        setCagridId(caGridId);
        addUser(user);
        addPerson(createPerson(patientName, patientId, "unknown", todaysDate()));
        addImageReference(createImageReference(studyUid, studyDate, studyTime));

    }

    // add the shape to this aim
    // one shape can turn into multiple geometric shapes
    // also the line length calculation is added
    @Override
    public int addShapes(String studyID, String seriesID, String imageID,
            int activeImage, String studyDate, String studyTime,
            ShapeType shapeType, List<TwoDCoordinate> coords,
            double pixelSpacingX, double pixelSpacingY) {

        int frameID = 1;
        int shapeID = getNextShapeID();

        List<GeometricShape> shapes = createShapes(imageID, frameID, shapeType,
                coords, pixelSpacingX, pixelSpacingY, shapeID);

        // could have created multiple shapes
        for (GeometricShape shape : shapes) {

            shape.setIncludeFlag(true);

            addCalculation(addlengthCalculation(
                    coords,
                    calculateLineLength(getCoords(shape), pixelSpacingX,
                            pixelSpacingY), shape.getShapeIdentifier()));

            addGeometricShape(shape);
        }

        if (!hasImage(imageID)) {
            updateImageID(studyID, seriesID, imageID, activeImage, studyDate,
                    studyTime);
        }

        return shapeID;
    }

    // get the coordinates for a geometric shape
    private List<TwoDCoordinate> getCoords(GeometricShape shape) {
        List<TwoDCoordinate> result = new ArrayList<TwoDCoordinate>();
        for (SpatialCoordinate coord : shape.getSpatialCoordinateCollection()
                .getSpatialCoordinateList()) {
            if (coord instanceof TwoDimensionSpatialCoordinate) {
                result.add(new TwoDCoordinate(
                        (TwoDimensionSpatialCoordinate) coord));
            }
        }
        return result;
    }

    // give me the next available shape identifier
    private int getNextShapeID() {

        int max = 0;
        try {
            for (GeometricShape shape : getGeometricShapeCollection()
                    .getGeometricShapeList()) {
                max = Math.max(max, shape.getShapeIdentifier());
            }
        } catch (Exception e) {
            logger.info("error: has no geometric shape collection");
        }
        max++;
        return max;
    }

    @Override
    public List<Shape> getShapes() {

        List<Shape> result = new ArrayList<Shape>();
        for (GeometricShape shape : getGeometricShapeCollection()
                .getGeometricShapeList()) {
            result.add(new Shape(shape));
        }
        return result;
    }

    // create a default name for an annotation
    private String todaysDate() {
//        Date today = new Date();
//        
//        
//        DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd'T'HH:mm:ss");
//        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return getFormatedDateTime();
    }

    // create a person object for this aim
    public Person createPerson(String name, String id, String sex,
            String birthdate) {

        Person person = new Person();
        person.setBirthDate(birthdate);
        person.setCagridId(caGridId);
        person.setName(name);
        person.setId(id);
        person.setSex(sex);
        return person;
    }

    // create the equipment object for this series
    private Equipment createEquipment(String name, String model, String version) {

        Equipment equipment = new Equipment();
        equipment.setCagridId(caGridId);
        equipment.setManufacturerName(name);
        equipment.setManufacturerModelName(model);
        equipment.setSoftwareVersion(version);
        return equipment;
    }

    // create the image reference
    private DICOMImageReference createImageReference(String studyUid,
            String studyDate, String studyTime) {

        // series reference
        ImageSeries imageSeries = new ImageSeries();
        imageSeries.setCagridId(caGridId);
        imageSeries.setInstanceUID("");
        Image img = new Image(caGridId, "", "");
        imageSeries.addImage(img);

        // study reference
        ImageStudy study = new ImageStudy();
        study.setCagridId(caGridId);
        study.setStartDate(studyDate);
        study.setStartTime(studyTime);
        study.setImageSeries(imageSeries);
        study.setInstanceUID(studyUid);

        // image reference
        DICOMImageReference imageReference = new DICOMImageReference();
        imageReference.setImageStudy(study);
        imageReference.setCagridId(caGridId);

        return imageReference;

    }

    // update the image reference for the study and series to point to this
    // imageID
    // turn a study or series reference into the image reference if possible
    private void updateImageID(String studyID, String seriesID, String imageID,
            int activeImage, String studyDate, String studyTime) {

        for (ImageReference imageReference : getImageReferenceCollection()
                .getImageReferenceList()) {

            DICOMImageReference dicomImageReference = (DICOMImageReference) imageReference;

            String studyInstanceUID = dicomImageReference.getImageStudy()
                    .getInstanceUID();
            String seriesInstanceUID = dicomImageReference.getImageStudy()
                    .getImageSeries().getInstanceUID();

            if (studyInstanceUID.equals(studyID)) {

                // found the study
                if (seriesInstanceUID.isEmpty()) {

                    // turn a study reference into an image reference
                    // logger.info("turn a study reference into an image reference");
                    ImageSeries imageSeries = new ImageSeries();
                    imageSeries.setCagridId(caGridId);
                    imageSeries.setInstanceUID(seriesID);
                    Image img = new Image(caGridId, "", imageID);
                    imageSeries.addImage(img);

                    dicomImageReference.getImageStudy().setImageSeries(
                            imageSeries);

                    return;

                } else if (seriesInstanceUID.equals(seriesID)) {

                    // turn a series reference into an image reference
                    Image image = new Image(caGridId, "", imageID);

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
        addImageReference(createImageReference(studyID, seriesID, imageID,
                studyDate, studyTime));

    }

    private AnatomicEntity createAnatomicEntity() {

        AnatomicEntity entity = new AnatomicEntity();
        entity.setAnnotatorConfidence(0.0);
        entity.setCagridId(caGridId);
        entity.setCodeMeaning("background");
        entity.setCodeValue("0");
        entity.setCodingSchemeDesignator("ePAD");
        entity.setLabel("background");
        return entity;
    }

    private List<GeometricShape> createShapes(String imageID, int frameID,
            ShapeType shapeType, List<TwoDCoordinate> coords,
            double pixelSpacingX, double pixelSpacingY, int shapeID) {

        // logger.info("createShapes " + shapeType);
        // build the shapes
        List<GeometricShape> shapes = new ArrayList<GeometricShape>();

        if (shapeType != null) {

            switch (shapeType) {

                case POINT:
                    Point point = new Point();
                    point.setShapeIdentifier(shapeID);
                    point.setCagridId(caGridId);
                    shapes.add(createShape(point, coords, imageID, frameID));
                    break;

                case LINE:
                case OPENPOLY:
                    MultiPoint multiPoint = new MultiPoint();
                    multiPoint.setShapeIdentifier(shapeID);
                    multiPoint.setCagridId(caGridId);
                    GeometricShape shape = createShape(multiPoint, coords, imageID,
                            frameID);
                    shapes.add(shape);
                    shape.setShapeIdentifier(shapeID);

                    break;
                case POLY:
                case RECTANGLE:
                    Polyline polyline = new Polyline();
                    polyline.setShapeIdentifier(shapeID);
                    polyline.setCagridId(caGridId);
                    shapes.add(createShape(polyline, coords, imageID, frameID));
                    break;
                case SPLINE:
//                case OPENSPLINE:
                    Spline spline = new Spline();
                    spline.setShapeIdentifier(shapeID);
                    spline.setCagridId(caGridId);
                    shapes.add(createShape(spline, coords, imageID, frameID));
                    break;
                case CIRCLE:
                    Circle circle = new Circle();
                    circle.setShapeIdentifier(shapeID);
                    circle.setCagridId(caGridId);
                    shapes.add(createShape(circle, coords, imageID, frameID));
                    break;
                case NORMAL:
                    // add the long axis line
                    List<TwoDCoordinate> longAxis = new ArrayList<TwoDCoordinate>();
                    longAxis.add(coords.get(0));
                    longAxis.add(coords.get(1));
                    MultiPoint longShape = new MultiPoint();
                    longShape.setShapeIdentifier(shapeID++);
                    longShape.setCagridId(caGridId);
                    GeometricShape l = createShape(longShape, longAxis, imageID,
                            frameID);
                    shapes.add(l);

                    // add the short axis line
                    List<TwoDCoordinate> shortAxis = new ArrayList<TwoDCoordinate>();
                    shortAxis.add(coords.get(2));
                    shortAxis.add(coords.get(3));
                    MultiPoint shortShape = new MultiPoint();
                    shortShape.setShapeIdentifier(shapeID);
                    shortShape.setCagridId(caGridId);
                    GeometricShape s = createShape(shortShape, shortAxis, imageID,
                            frameID);
                    shapes.add(s);

                    break;
                default:
                    break;
            }
        }

        return shapes;
    }

    private GeometricShape createShape(GeometricShape shape,
            List<TwoDCoordinate> coords, String imageUid, int frame) {

        // logger.info("createShape " + shape.getXsiType());
        // put the coords into the shape
        for (int i = 0; i < coords.size(); i++) {
            TwoDimensionSpatialCoordinate coord = coords.get(i);
            coord.setImageReferenceUID(imageUid);
            coord.setReferencedFrameNumber(frame);
            coord.setCoordinateIndex(i);
            shape.addSpatialCoordinate(new TwoDimensionSpatialCoordinate(
                    caGridId, i, imageUid, frame, coord.getX(), coord.getY()));
        }
        return shape;

    }

    @Override
    public boolean hasShapes() {

        boolean result = true;
        try {
            getGeometricShapeCollection().getGeometricShapeList().get(0);
        } catch (Exception e) {
            result = false;
        }
        return result;

    }

    @Override
    public boolean isRectangle(int shapeID) {
        return getShapeType(shapeID).equals(ShapeType.RECTANGLE);
    }

    @Override
    public boolean isClosedShape(int shapeID) {
        return isPolygon(shapeID) || isCircle(shapeID) || isSpline(shapeID);
    }

    @Override
    public boolean isCircle(int shapeID) {
        return getShapeType(shapeID).equals(ShapeType.CIRCLE);
    }

    @Override
    public boolean isEllipse(int shapeID) {
        return getShapeType(shapeID).equals(ShapeType.ELLIPSE);
    }

    @Override
    public boolean isSpline(int shapeID) {
        return getShapeType(shapeID).equals(ShapeType.SPLINE);
    }

    @Override
    public boolean isNormal(int shapeID) {
        return getShapeType(shapeID).equals(ShapeType.NORMAL);
    }

    @Override
    public boolean isPoint(int shapeID) {
        return getShapeType(shapeID).equals(ShapeType.POINT);
    }

    @Override
    public boolean isPolygon(int shapeID) {
        return getShapeType(shapeID).equals(ShapeType.POLY);
    }

    @Override
    public boolean hasPolyLine() {
        for (GeometricShape shape : getGeometricShapeCollection()
                .getGeometricShapeList()) {
            if (shape.getXsiType().equalsIgnoreCase("Polyline")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasMultiPoint() {

        for (GeometricShape shape : getGeometricShapeCollection()
                .getGeometricShapeList()) {
            if (shape.getXsiType().equalsIgnoreCase("Multipoint")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasSeries(String seriesID) {
        boolean result = false;
        try {

            for (ImageReference imageReference : getImageReferenceCollection()
                    .getImageReferenceList()) {

                DICOMImageReference dicomImageReference = (DICOMImageReference) imageReference;

                if (dicomImageReference.getImageStudy().getImageSeries()
                        .getInstanceUID().equals(seriesID)) {
                    result = true;
                    break;
                }
            }
        } finally {
        }
        return result;
    }

    @Override
    public boolean hasImage(String imageID) {
        boolean result = false;
        try {

            for (ImageReference imageReference : getImageReferenceCollection()
                    .getImageReferenceList()) {

                DICOMImageReference dicomImageReference = (DICOMImageReference) imageReference;

                for (Image image : dicomImageReference.getImageStudy()
                        .getImageSeries().getImageCollection().getImageList()) {
                    if (image.getSopInstanceUID().equals(imageID)) {
                        result = true;
                        break;
                    }
                }
            }
        } finally {
        }
        return result;
    }

    // get the series id for this image from the image reference
    @Override
    public String getFirstSeriesID() {
        String result = "";

        try {
            List<ImageReference> imageList = getImageReferenceCollection()
                    .getImageReferenceList();
            if (imageList.size() > 0) {
                ImageReference imageReference = imageList.get(0);
                DICOMImageReference dicomImageReference = (DICOMImageReference) imageReference;
                ImageStudy imageStudy = dicomImageReference.getImageStudy();
                ImageSeries imageSeries = imageStudy.getImageSeries();
                result = imageSeries.getInstanceUID();
            }
        } catch (Exception e) {

            logger.info("Error:" + e.getMessage());

        }
        return result;
    }

    @Override
    public String getSeriesID(String imageID) {

        String result = "";
        try {

            // look for the reference to this image
            for (ImageReference reference : getImageReferenceCollection()
                    .getImageReferenceList()) {

                if (reference.getXsiType().equals("DICOMImageReference")) {

                    DICOMImageReference dicomReference = (DICOMImageReference) reference;
                    for (Image image : dicomReference.getImageStudy()
                            .getImageSeries().getImageCollection()
                            .getImageList()) {

                        if (image.getSopInstanceUID().equals(imageID)) {

                            result = dicomReference.getImageStudy()
                                    .getImageSeries().getInstanceUID();
                            break;

                        }
                    }
                }
            }
        } finally {
        }
        return result;
    }

    @Override
    public String getFirstStudyID() {

        String studyID = "";
        try {

            DICOMImageReference reference = (DICOMImageReference) getImageReferenceCollection()
                    .getImageReferenceList().get(0);
            studyID = reference.getImageStudy().getInstanceUID();

        } finally {
        }

        return studyID;
    }

    @Override
    public String getStudyID(String seriesID) {

        String result = "";
        try {

            for (ImageReference imageReference : getImageReferenceCollection()
                    .getImageReferenceList()) {

                DICOMImageReference dicomImageReference = (DICOMImageReference) imageReference;

                if (dicomImageReference.getImageStudy().getImageSeries()
                        .getInstanceUID().equals(seriesID)) {
                    result = dicomImageReference.getImageStudy()
                            .getInstanceUID();
                    break;
                }
            }
        } finally {
        }
        return result;
    }

    // @Override
    // public double getLineLength() {
    //
    // double length = 0;
    //
    // try {
    // List<Calculation> calculationList = getCalculationCollection()
    // .getCalculationList();
    //
    // List<CalculationResult> calculationResultList = calculationList
    // .get(0).getCalculationResultCollection()
    // .getCalculationResultList();
    //
    // List<CalculationData> dataList = calculationResultList.get(0)
    // .getCalculationDataCollection().getCalculationDataList();
    //
    // length = dataList.get(0).getValue();
    //
    // } catch (Exception e) {
    // logger.info("Error: Aimapi.getLineLength " + e.getMessage());
    // }
    //
    // return length;
    // }
    @Override
    public Date getFirstStudyDate() {

        try {
            List<ImageReference> imageList = getImageReferenceCollection()
                    .getImageReferenceList();

            ImageReference imageReference = imageList.get(0);
            DICOMImageReference dicomImageReference = (DICOMImageReference) imageReference;
            ImageStudy study = dicomImageReference.getImageStudy();
            String strStartDate = study.getStartDate();
            try {
                int year = Integer.parseInt(strStartDate.substring(0, 4));
                int month = Integer.parseInt(strStartDate.substring(5, 7));
                int day = Integer.parseInt(strStartDate.substring(8, 10));

                System.out.println(year + " " + month + " " + day);
                Date date = new Date(year, month - 1, day);
                return date;
            } catch (NumberFormatException ex) {
                throw new AimException("Dateformat of the ImageStudy must be started with 'yyyy-MM-dd'");
            }

        } catch (Exception e) {

            logger.info("Error: aimApi.getStudyDate " + e.getMessage());

        }

        return null;
    }

    // @Override
    // public String formatStudyDate() {
    //
    // try {
    // Date studyDate = getStudyDate();
    // DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd");
    // return fmt.format(studyDate);
    // } catch (Exception e) {
    // logger.info("Error: Aimapi.formatStudyDate " + e.getMessage());
    // e.printStackTrace();
    // }
    // return "";
    // }
    @Override
    public boolean makeNameUnique(List<Aim> aims) {

        boolean duplicate = false;
        duplicateloop:
        for (Aim ia : aims) {
            if (!ia.equals(this)) {
                if (ia.getName().equals(getName())) {
                    duplicate = true;
                    break duplicateloop;
                }
            }
        }

        if (duplicate) {
            // Window.alert("Duplicate name for this Annotation.");
            boolean foundReplacement = false;
            int modifier = 1;
            while (!foundReplacement) {
                String possible = getName().replaceAll("\\([0-9]+\\)", "")
                        + "(" + modifier++ + ")";
                duplicate = false;
                loop:
                for (ImageAnnotation ia : aims) {
                    if (ia.getName().equals(possible)) {
                        duplicate = true;
                        break loop;
                    }
                }
                if (!duplicate) {
                    setName(possible);
                    return true;
                }
            }
        }
        return false;
    }

    // @Override
    // public String getOwner() {
    // if (getListUser() != null) {
    // if (!getListUser().isEmpty()) {
    // return getListUser().get(0).getLoginName();
    // }
    // }
    // return "none";
    // }
    @Override
    public String getPatientID() {
        String result = "";
        try {
            return getListPerson().get(0).getId();

        } catch (Exception e) {
            logger.info("Error: Aim getPatientId " + e.getMessage());
        }
        return result;

    }

    @Override
    public String getIOCodeValue(String label) {
        try {
            List<ImagingObservation> listImagingObservation = getImagingObservationCollection().getImagingObservationList();
            for (ImagingObservation imagingObservation : listImagingObservation) {
                if (imagingObservation.getLabel().equalsIgnoreCase(label)) {
                    if (imagingObservation.getAllowedTerm() != null) {
                        return imagingObservation.getAllowedTerm().getCodeValue();
                    } else if (imagingObservation.getCodeValue() != null && !"".equals(imagingObservation.getCodeValue())) {
                        return imagingObservation.getCodeValue();
                    }

                }
            }

//            Iterator<ImagingObservation> it = getImagingObservationCollection()
//                    .getImagingObservationList().iterator();
//            while (it.hasNext()) {
//                ImagingObservation entity = it.next();
//                if (entity.getLabel().equalsIgnoreCase(label)) {
//                    
//                    return entity.getCodeValue();
//                }
//            }
        } catch (Exception e) {
            logger.info("Error: Aim getObservationCodeValue " + e.getMessage());
        }

        return "";
    }

    @Override
    public boolean hasIOLabel(String label) {

        try {

            if (!"".equals(getIOCodeValue(label))) {
                return true;
            }
            return false;
//            Iterator<ImagingObservation> it = getImagingObservationCollection()
//                    .getImagingObservationList().iterator();
//            while (it.hasNext()) {
//                ImagingObservation entity = it.next();
//                if (entity.getLabel().equalsIgnoreCase(label)) {
//                    return true;
//                }
//            }
        } catch (Exception e) {
            logger.info("Error: Aim getObservationCodeValue " + e.getMessage());
        }

        return false;
    }

    @Override
    public String getIOCCodeValue(String IOlabel, String IOClabel) {
        try {
            List<ImagingObservation> listImagingObservation = getImagingObservationCollection().getImagingObservationList();
            for (ImagingObservation imagingObservation : listImagingObservation) {
                if (imagingObservation.getLabel().equalsIgnoreCase(IOlabel)) {
                    List<ImagingObservationCharacteristic> listImagingObservationCharacteristic = imagingObservation.getImagingObservationCharacteristicCollection().getImagingObservationCharacteristicList();
                    for (ImagingObservationCharacteristic imagingObservationCharacteristic : listImagingObservationCharacteristic) {
                        if (imagingObservationCharacteristic.getLabel().equalsIgnoreCase(IOClabel)) {
                            if (imagingObservationCharacteristic.getAllowedTerm() != null) {
                                return imagingObservationCharacteristic.getAllowedTerm().getCodeValue();
                            } else if (imagingObservationCharacteristic.getCodeValue() != null && !"".equals(imagingObservationCharacteristic.getCodeValue())) {
                                return imagingObservationCharacteristic.getCodeValue();
                            }

                        }
                    }
                }
            }

//            Iterator<ImagingObservation> it = getImagingObservationCollection()
//                    .getImagingObservationList().iterator();
//            while (it.hasNext()) {
//                ImagingObservation entity = it.next();
//                if (entity.getLabel().equalsIgnoreCase(IOlabel)) {
//
//                    // find the ioc with the right label in this io
//                    Iterator<ImagingObservationCharacteristic> itIOC = entity
//                            .getImagingObservationCharacteristicCollection()
//                            .getImagingObservationCharacteristicList()
//                            .iterator();
//                    while (itIOC.hasNext()) {
//                        ImagingObservationCharacteristic IOCentity = itIOC
//                                .next();
//                        if (IOCentity.getLabel().equalsIgnoreCase(IOClabel)) {
//
//                            return IOCentity.getCodeValue();
//                        }
//                    }
//                }
//            }
        } catch (Exception e) {
            logger.info("Error: Aim getObservationCodeValue " + e.getMessage());
        }

        return "";
    }

    @Override
    public boolean hasIOCLabel(String IOlabel, String IOClabel) {

        try {

            if (!"".equals(getIOCCodeValue(IOlabel, IOClabel))) {
                return true;
            }
            return false;

//            Iterator<ImagingObservation> it = getImagingObservationCollection()
//                    .getImagingObservationList().iterator();
//            while (it.hasNext()) {
//                ImagingObservation entity = it.next();
//                if (entity.getLabel().equalsIgnoreCase(IOlabel)) {
//
//                    // find the ioc with the right label in this io
//                    Iterator<ImagingObservationCharacteristic> itIOC = entity
//                            .getImagingObservationCharacteristicCollection()
//                            .getImagingObservationCharacteristicList()
//                            .iterator();
//                    while (itIOC.hasNext()) {
//                        ImagingObservationCharacteristic IOCentity = itIOC
//                                .next();
//                        if (IOCentity.getLabel().equalsIgnoreCase(IOClabel)) {
//
//                            return true;
//                        }
//                    }
//
//                }
//            }
        } catch (Exception e) {
            logger.info("Error: Aim getObservationCodeValue " + e.getMessage());
        }

        return false;
    }

    @Override
    public String getAECodeMeaning(String label) {

        try {

            List<AnatomicEntity> listAnatomicEntity = getAnatomicEntityCollection().getAnatomicEntityList();
            for (AnatomicEntity anatomicEntity : listAnatomicEntity) {
                if (anatomicEntity.getLabel().equalsIgnoreCase(label)) {
                    if (anatomicEntity.getAllowedTerm() != null) {
                        return anatomicEntity.getAllowedTerm().getCodeMeaning();
                    } else if (anatomicEntity.getCodeMeaning() != null && !"".equals(anatomicEntity.getCodeMeaning())) {
                        return anatomicEntity.getCodeMeaning();
                    }

                }
            }

//            Iterator<AnatomicEntity> it = getAnatomicEntityCollection()
//                    .getAnatomicEntityList().iterator();
//            while (it.hasNext()) {
//                AnatomicEntity entity = it.next();
//                if (entity.getLabel().equalsIgnoreCase(label)) {
//                    return entity.getCodeMeaning();
//                }
//            }
        } catch (Exception e) {
            logger.info("Error: Aim getAECodeMeaning " + e.getMessage());
        }

        return "";
    }

    @Override
    public boolean hasAELabel(String label) {

        try {

            if (!"".equals(getAECodeMeaning(label))) {
                return true;
            }
            return false;
//            Iterator<AnatomicEntity> it = getAnatomicEntityCollection()
//                    .getAnatomicEntityList().iterator();
//            while (it.hasNext()) {
//                AnatomicEntity entity = it.next();
//                if (entity.getLabel().equalsIgnoreCase(label)) {
//                    return true;
//                }
//            }
        } catch (Exception e) {
            // logger.info("Error: Aim hasAELabel " + e.getMessage());
        }

        return false;
    }

    // @Override
    // public boolean isRecist() {
    // return (hasAELabel("status") && hasAELabel("location") && hasIOCLabel(
    // "lesion", "type"));
    // }
    //
    // @Override
    // public boolean isBaseline() {
    // return (getIOCodeValue("lesion").equals("S81"));
    // }
    //
    // @Override
    // public boolean isFollowUp() {
    // return (getIOCodeValue("lesion").equals("S82"));
    // }
    //
    // @Override
    // public boolean isTarget() {
    // boolean result = (getIOCCodeValue("lesion", "type")
    // .equalsIgnoreCase("S71"));
    // if (result) {
    // logger.info("isTarget  " + getName() + " " + getUniqueIdentifier()
    // + " " + getCodeValue() + " " + getCodeMeaning());
    // }
    //
    // return (getIOCCodeValue("lesion", "type").equalsIgnoreCase("S71"));
    // }
    //
    // @Override
    // public boolean isNonTarget() {
    // return (getIOCCodeValue("lesion", "type").equalsIgnoreCase("S72"));
    // }
    //
    // @Override
    // public boolean isResolved() {
    // return (getIOCCodeValue("lesion", "type").equalsIgnoreCase("S74"));
    // }
    //
    // @Override
    // public boolean isNew() {
    // return (getIOCCodeValue("lesion", "type").equalsIgnoreCase("S73"));
    // }
    //
    // @Override
    // public String getLesionStatus() {
    // return getAECodeMeaning("status");
    // }
    //
    // @Override
    // public String getLesionLocation() {
    // return getAECodeMeaning("location");
    // }
    @Override
    public String getPatientName() {
        if (getListPerson() != null) {
            if (!getListPerson().isEmpty()) {
                return getListPerson().get(0).getName().replace("^", " ");
            }
        }
        return "";
    }

    @Override
    public String cleanPatientName() {
        return getPatientName().replace("^", " ");
    }

    @Override
    public String getFirstImageID() {
        // logger.info("getFirstImageID " + toString());
        String result = "";
        try {

            DICOMImageReference dicomImageReference = (DICOMImageReference) getImageReferenceCollection()
                    .getImageReferenceList().get(0);
            result = dicomImageReference.getImageStudy().getImageSeries()
                    .getImageCollection().getImageList().get(0)
                    .getSopInstanceUID();

        } catch (Exception e) {
            // logger.info("error: getFirstImageID aim has no imageReference");
        }
        return result;

    }

    @Override
    // does this annotation contain an image reference?
    public boolean hasImage() {
        try {
            getImageReferenceCollection().getImageReferenceList().get(0);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // // does this annotation contain an image reference to this study, series,
    // // image?
    // @Override
    // public boolean hasImageReference(String studyUID, String seriesUID,
    // String imageUID) {
    //
    // if (hasImageReference()) {
    //
    // List<ImageReference> imageRefrences = getImageReferenceCollection()
    // .getImageReferenceList();
    // for (ImageReference imageReference : imageRefrences) {
    //
    // DICOMImageReference dicomImageReference = (DICOMImageReference)
    // imageReference;
    // ImageStudy study = dicomImageReference.getImageStudy();
    // ImageSeries series = study.getImageSeries();
    //
    // if (study.getInstanceUID().equals(studyUID)
    // && series.getInstanceUID().equals(seriesUID)) {
    //
    // ImageCollection imageCollection = series
    // .getImageCollection();
    // List<Image> imageList = imageCollection.getImageList();
    //
    // for (Image image : imageList) {
    // if (image.getSopInstanceUID().equals(imageUID)) {
    // return true;
    // }
    // }
    // }
    // }
    // }
    //
    // return false;
    // }
    // does this annotation contain a segmentation component?
    @Override
    public boolean hasSegmentation() {
        try {
            getSegmentationCollection().getSegmentationList().get(0);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hasSegmentationImage(String imageID) {
        boolean found = false;
        try {
            for (Segmentation segmentation : getSegmentationCollection()
                    .getSegmentationList()) {

                if (segmentation.getSopInstanceUID().equals(imageID)) {
                    found = true;
                    break;
                }
            }
        } finally {
        }
        return found;

    }

    @Override
    public String getFirstSegmentationImageID() {
        String id = "";

        try {
            id = getSegmentationCollection().getSegmentationList().get(0)
                    .getSopInstanceUID();
        } catch (Exception e) {
            // logger.info("Error: has no segmentation " + hasSegmentation());
        }
        return id;
    }

    // get the coords out of the aim
    @Override
    public List<TwoDCoordinate> getFirstShapeCoords() {

        List<SpatialCoordinate> coordList = this.getGeometricShapeCollection()
                .getGeometricShapeList().get(0)
                .getSpatialCoordinateCollection().getSpatialCoordinateList();

        List<TwoDCoordinate> coords = new ArrayList<TwoDCoordinate>();
        for (int i = 0; i < coordList.size(); i++) {
            TwoDimensionSpatialCoordinate twoD = (TwoDimensionSpatialCoordinate) coordList
                    .get(i);
            coords.add(i, new TwoDCoordinate(twoD));
        }

        return coords;

    }

    // put the modified shape coords back into the aim
    @Override
    public void setShapeCoords(int shapeID, List<TwoDCoordinate> coords) {

        for (GeometricShape shape : this.getGeometricShapeCollection()
                .getGeometricShapeList()) {
            if (shape.getShapeIdentifier() == shapeID) {
                List<SpatialCoordinate> coordList = shape
                        .getSpatialCoordinateCollection()
                        .getSpatialCoordinateList();

                for (int i = 0; i < coordList.size(); i++) {
                    if (i < coords.size()) {
                        TwoDimensionSpatialCoordinate c = (TwoDimensionSpatialCoordinate) coordList
                                .get(i);
                        c.setX(coords.get(i).getX());
                        c.setY(coords.get(i).getY());
                    }
                }
                return;
            }
        }
    }

    @Override
    public Shape getShape(int shapeID) {

        // logger.info("getShape " + shapeID);
        Shape result = null;
        for (GeometricShape shape : getGeometricShapeCollection()
                .getGeometricShapeList()) {
            // logger.info("getShapeIdentifier " + shape.getShapeIdentifier()
            // + " " + shapeID);
            if (shape.getShapeIdentifier() == shapeID) {
                result = new Shape(shape);
            }
        }
        return result;
    }

    @Override
    public ShapeType getShapeType(int shapeID) {

        ShapeType result = ShapeType.NONE;

        Shape shape = getShape(shapeID);

        String xsiType = shape.getXsiType();

        if (xsiType.equals("MultiPoint")) {
            result = ShapeType.LINE;
        } else if (xsiType.equals("Polyline")) {
            result = ShapeType.POLY;
        } else if (xsiType.equals("Spline")) {
            result = ShapeType.SPLINE;
        } else if (xsiType.equals("Circle")) {
            result = ShapeType.CIRCLE;
        } else if (xsiType.equals("Point")) {
            result = ShapeType.POINT;
        } else {
            result = ShapeType.NONE;
        }
        return result;

    }

    // TODO this is odd, setting a single result multiple times.
    // what if the calculation is not there?
    // set the shape calculation in the aim for a single result value
    @Override
    public void setShapeCalculation(int shapeID, String algorithmName,
            double value) {

        try {

            // find the calculation for this algorithm
            for (Calculation calculation : getCalculationCollection()
                    .getCalculationList()) {
                if (calculation.getAlgorithmName().equals(algorithmName)) {

                    // find the shape
                    for (ReferencedGeometricShape shape : calculation
                            .getReferencedGeometricShapeCollection()
                            .getReferencedGeometricShapeList()) {

                        if (shape.getReferencedShapeIdentifier() == shapeID) {

                            // for each calculation result
                            for (CalculationResult result : calculation
                                    .getCalculationResultCollection()
                                    .getCalculationResultList()) {

                                CalculationDataCollection dataCollection = new CalculationDataCollection();
                                List<CalculationData> data = new ArrayList<CalculationData>();
                                CalculationData calculationData = new CalculationData();

                                calculationData.setValue(value);
                                calculationData.setCagridId(0);
                                calculationData.addCoordinate(0, 0, 0);
                                data.add(calculationData);

                                dataCollection
                                        .AddCalculationData(calculationData);
                                result.setCalculationDataCollection(dataCollection);

                            }
                        }
                    }
                }
            }
        } finally {
        }

    }

    private double calculateLineLength(List<TwoDCoordinate> coords,
            double pixelSpacingX, double pixelSpacingY) {

        double result = 0.0;
        if (coords.size() == 2) {
            double length = Math.abs(coords.get(0).getX()
                    - coords.get(1).getX());
            double width = Math
                    .abs(coords.get(0).getY() - coords.get(1).getY());
            return Math.sqrt(length * length + width * width) * pixelSpacingX
                    / 10.0;
        }
        return result;
    }

//	public Aim cloneAim(SeriesView seriesView, DicomSeries activeSeries,
//			int index) {
//
//		ImageAnnotation newAim = cloneAim(this, seriesView, activeSeries, index);
//		Aim aim = new Aim(newAim);
//		return aim;
//	}
    public Aim cloneAim() {

        //ImageAnnotation newAim = cloneAim(this);
        Aim aim = new Aim(this.getClone());
        return aim;
    }

    @Override
    public void clearValues() {
        getAnatomicEntityCollection().getAnatomicEntityList().clear();
        getImagingObservationCollection().getImagingObservationList().clear();
        getInferenceCollection().getInferenceList().clear();

    }

    @Override
    public void addComponents(List<Component> components) {

        for (Component component : components) {
            switch (component.componentType) {
                case anatomicEntity:
                    getAnatomicEntityCollection().getAnatomicEntityList().add(
                            component.anatomicEntity);
                    break;
                case imagingObservation:
                    getImagingObservationCollection().getImagingObservationList()
                            .add(component.imagingObservation);
                    break;
                case inference:
                    getInferenceCollection().getInferenceList().add(
                            component.inference);
                    break;
                default:
                    break;
            }
        }
    }

    // private int addIOs(List<IO> ios) {
    // getImagingObservationCollection().getImagingObservationList().addAll(
    // ios);
    // return getImagingObservationCollection().getImagingObservationList()
    // .size() - 1;
    // }
    // public List<AE> getAEs() {
    // List<AE> result = new ArrayList<AE>();
    // for (AnatomicEntity anatomicEntity : getAnatomicEntityCollection()
    // .getAnatomicEntityList()) {
    //
    // AE ae = new AE(anatomicEntity);
    // result.add(ae);
    // }
    // return result;
    // }
    // public List<IO> getIOs() {
    // List<IO> result = new ArrayList<IO>();
    // for (ImagingObservation imagingObservation :
    // getImagingObservationCollection()
    // .getImagingObservationList()) {
    //
    // IO io = new IO(imagingObservation);
    // result.add(io);
    // }
    // return result;
    //
    // }
    // public List<I> getIs() {
    // List<I> result = new ArrayList<I>();
    // for (Inference inference : getInferenceCollection().getInferenceList()) {
    //
    // I i = new I(inference);
    // result.add(i);
    // }
    // return result;
    // }
    // private List<Calc> getCalcs() {
    // List<Calc> result = new ArrayList<Calc>();
    // for (Calculation calculation : getCalculationCollection()
    // .getCalculationList()) {
    //
    // Calc c = new Calc(calculation);
    // result.add(c);
    // }
    // return result;
    // }
    // TODO not filling out the calculation correctly yet
    private Calculation addlengthCalculation(List<TwoDCoordinate> coords,
            double length, int shapeId) {

        // Create a Calculation instance
        Calculation calculation = new Calculation();
        calculation.setCagridId(0);
        calculation.setAlgorithmVersion(VERSION);
        calculation.setAlgorithmName(LINE_LENGTH);
        calculation.setUid("0");
        calculation.setDescription(LINE_LENGTH);
        calculation.setCodeValue(LINE_LENGTH);
        calculation.setCodeMeaning(LINE_LENGTH);
        calculation.setCodingSchemeDesignator(PRIVATE_DESIGNATOR);

        // Create a CalculationResult instance
        CalculationResult calculationResult = new CalculationResult();
        calculationResult.setCagridId(0);
        calculationResult.setType(CalculationResultIdentifier.Scalar);
        calculationResult.setUnitOfMeasure(LINE_MEASURE);
        calculationResult.setNumberOfDimensions(0);

        // Create a CalculationData instance
        CalculationData calculationData = new CalculationData();
        calculationData.setCagridId(0);
        calculationData.setValue(length);
        calculationData.addCoordinate(0, 0, 0); // TODO what goes here?

        // Create a Dimension instance
        Dimension dimension = new Dimension(0, 0, 1, LINE_LENGTH);

        // Add calculationData to calculationResult
        calculationResult.addCalculationData(calculationData);

        // Add dimension to calculationResult
        calculationResult.addDimension(dimension);

        // add the shape reference to the calculation
        ReferencedGeometricShape reference = new ReferencedGeometricShape();
        reference.setCagridId(0);
        reference.setReferencedShapeIdentifier(shapeId);
        calculation.addReferencedGeometricShape(reference);

        // Add calculationResult to calculation
        calculation.addCalculationResult(calculationResult);

        return calculation;
    }

//    public ImageAnnotation cloneAim(ImageAnnotation fromAim) {
//
//        try {
//
//            // create the new aim from the old one
//            ImageAnnotation aim = new ImageAnnotation();
//            aim.setName(fromAim.getName());
//            aim.setCagridId(0);
//            aim.setCodeValue(fromAim.getCodeValue());
//            aim.setCodeMeaning(fromAim.getCodeMeaning());
//            aim.setCodingSchemeDesignator(fromAim.getCodingSchemeDesignator());
//            aim.setCodingSchemeVersion(fromAim.getCodingSchemeVersion());
//
//            aim.setComment(fromAim.getComment());
//
//            if (fromAim.getListUser().size() > 0) {
//                aim.addUser(fromAim.getListUser().get(0));
//            }
//            aim.setDateTime(todaysDate());
//
//            for (Equipment e : fromAim.getListEquipment()) {
//                aim.addEquipment(cloneEquipment(e));
//            }
//            for (@SuppressWarnings("unused") Person p : fromAim.getListPerson()) {
//                aim.addPerson(createPerson(p.getName(), p.getId(), p.getSex(),
//                        p.getBirthDate()));
//            }
//
//            // this points to the aim that it was created from
//            aim.setPrecedentReferencedAnnotationUID(fromAim
//                    .getUniqueIdentifier());
//
//			// copy over the geometric shapes and modify the image reference to
//            // point to this image
//            for (GeometricShape fromShape : fromAim
//                    .getGeometricShapeCollection().getGeometricShapeList()) {
//
//                GeometricShape newShape = new GeometricShape();
//                if (fromShape.getXsiType().equals("MultiPoint")) {
//                    newShape = new MultiPoint();
//                } else if (fromShape.getXsiType().equals("Polyline")) {
//                    newShape = new Polyline();
//                } else if (fromShape.getXsiType().equals("Circle")) {
//                    newShape = new Circle();
//                } else if (fromShape.getXsiType().equals("Point")) {
//                    newShape = new Point();
//                } else {
//                    // logger.info("error: not a supported shape");
//                    return null;
//                }
//
//                newShape.setCagridId(fromShape.getCagridId());
//                newShape.setShapeIdentifier(fromShape.getShapeIdentifier());
//                newShape.setIncludeFlag(fromShape.getIncludeFlag());
//                newShape.setLineColor(fromShape.getLineColor());
//                newShape.setLineOpacity(fromShape.getLineOpacity());
//                newShape.setLineStyle(fromShape.getLineStyle());
//                newShape.setLineThickness(fromShape.getLineThickness());
//
//                SpatialCoordinateCollection newCoords = new SpatialCoordinateCollection();
//                for (SpatialCoordinate fromCoord : fromShape
//                        .getSpatialCoordinateCollection()
//                        .getSpatialCoordinateList()) {
//
//                    TwoDimensionSpatialCoordinate d2 = (TwoDimensionSpatialCoordinate) fromCoord;
//                    
//                    TwoDimensionSpatialCoordinate newCoord = new TwoDimensionSpatialCoordinate();
//                    newCoord.setCagridId(d2.getCagridId());
//                    newCoord.setCoordinateIndex(d2.getCoordinateIndex());
//                    newCoord.setImageReferenceUID(d2.getImageReferenceUID());
//                    newCoord.setReferencedFrameNumber(0);
//                    newCoord.setX(d2.getX());
//                    newCoord.setY(d2.getY());
//                    newCoords.AddSpatialCoordinate(newCoord);
//                }
//                newShape.setSpatialCoordinateCollection(newCoords);
//                aim.addGeometricShape(newShape);
//            }
//
//            // create a new image refence to this series and image
//            
//            
//            aim.addImageReference(createImageReference(series, image));
//
//            // add the questions and answers, calculations and equipment
//            for (AnatomicEntity ae : fromAim.getAnatomicEntityCollection()
//                    .getAnatomicEntityList()) {
//                aim.addAnatomicEntity(cloneAE(ae));
//            }
//            for (ImagingObservation io : fromAim
//                    .getImagingObservationCollection()
//                    .getImagingObservationList()) {
//                aim.addImagingObservation(cloneIO(io));
//            }
//            for (Inference i : fromAim.getInferenceCollection()
//                    .getInferenceList()) {
//                aim.addInference(cloneInference(i));
//            }
//            for (Calculation c : fromAim.getCalculationCollection()
//                    .getCalculationList()) {
//                aim.addCalculation(cloneCalculation(c));
//            }
//
//            return aim;
//
//        } catch (Exception e) {
//			// logger.info("Error: Exception on newAimAnnotation" +
//            // e.getMessage());
//            return null;
//        }
//    }
    // create a comment for an annotation
    public String fillComment(String modality, String description, int image) {
        return modality + " / " + description + " / " + image;
    }

    // create the image reference
    private DICOMImageReference createImageReference(String studyID,
            String seriesID, String imageID, String studyDate, String studyTime) {

        // series reference
        ImageSeries imageSeries = new ImageSeries();
        imageSeries.setCagridId(0);
        imageSeries.setInstanceUID(seriesID);
        imageSeries.addImage(new Image(caGridId, "", imageID));

        // study reference
        ImageStudy study = new ImageStudy();
        study.setCagridId(0);
        study.setStartDate(studyDate);
        study.setStartTime(studyTime);
        study.setImageSeries(imageSeries);
        study.setInstanceUID(studyID);

        // image reference
        DICOMImageReference imageReference = new DICOMImageReference();
        imageReference.setImageStudy(study);
        imageReference.setCagridId(caGridId);

        return imageReference;

    }

//    // create the image reference
//    private DICOMImageReference createImageReference(DicomSeries series,
//            int image) {
//
//        DICOMImageReference from = new DICOMImageReference();
//        from.get
//        
//        
//        // image uid
//        String imageUid = series.getDicomImages().get(image).getUid();
//
//        // series reference
//        ImageSeries imageSeries = new ImageSeries();
//        imageSeries.setCagridId(0);
//        imageSeries.setInstanceUID(series.getSeriesUID());
//        imageSeries.addImage(new Image(caGridId, "", imageUid));
//
//        // study reference
//        ImageStudy study = new ImageStudy();
//        study.setCagridId(0);
//        study.setStartDate(series.getStudyDate());
//        study.setStartTime(series.getStudyTime());
//        study.setImageSeries(imageSeries);
//        study.setInstanceUID(series.getStudyUID());
//
//        // image reference
//        DICOMImageReference imageReference = new DICOMImageReference();
//        imageReference.setImageStudy(study);
//        imageReference.setCagridId(caGridId);
//
//        return imageReference;
//
//    }
//    private Equipment cloneEquipment(Equipment from) {
//        Equipment to = new Equipment();
//
//        to.setCagridId(from.getCagridId());
//        to.setManufacturerModelName(from.getManufacturerModelName());
//        to.setManufacturerName(from.getManufacturerName());
//        to.setSoftwareVersion(from.getSoftwareVersion());
//
//        return to;
//    }
//    private Inference cloneInference(Inference from) {
//
//        Inference to = new Inference();
//
//        to.setAnnotatorConfidence(from.getAnnotatorConfidence());
//        to.setCagridId(from.getCagridId());
//        to.setCodeMeaning(from.getCodeMeaning());
//        to.setCodeValue(from.getCodeValue());
//        to.setCodingSchemeDesignator(from.getCodingSchemeDesignator());
//        to.setCodingSchemeVersion(from.getCodingSchemeVersion());
//        to.setImageEvidence(from.getImageEvidence());
//
//        return to;
//    }
//    private ImagingObservation cloneIO(ImagingObservation from) {
//
//        ImagingObservation to = new ImagingObservation();
//
//        to.setCagridId(from.getCagridId());
//        to.setLabel(from.getLabel());
//        to.setAnnotatorConfidence(from.getAnnotatorConfidence());
//        to.setCodeValue(from.getCodeValue());
//        to.setCodeMeaning(from.getCodeMeaning());
//        to.setCodingSchemeDesignator(from.getCodingSchemeDesignator());
//        to.setCodingSchemeVersion(from.getCodingSchemeVersion());
//
//        if (from.getImagingObservationCharacteristicCollection() != null) {
//            for (ImagingObservationCharacteristic ioc : from
//                    .getImagingObservationCharacteristicCollection()
//                    .getImagingObservationCharacteristicList()) {
//                to.addImagingObservationCharacteristic(cloneIOC(ioc));
//            }
//        }
//
//        return to;
//    }
//    private ImagingObservationCharacteristic cloneIOC(
//            ImagingObservationCharacteristic from) {
//
//        ImagingObservationCharacteristic to = new ImagingObservationCharacteristic();
//
//        to.setCagridId(from.getCagridId());
//        to.setLabel(from.getLabel());
//        to.setAnnotatorConfidence(from.getAnnotatorConfidence());
//        to.setCodeValue(from.getCodeValue());
//        to.setCodeMeaning(from.getCodeMeaning());
//        to.setCodingSchemeDesignator(from.getCodingSchemeDesignator());
//        to.setCodingSchemeVersion(from.getCodingSchemeVersion());
//        to.setComment(from.getComment());
//
//        if (from.getCharacteristicQuantificationCollection() != null) {
//            for (CharacteristicQuantification cq : from
//                    .getCharacteristicQuantificationCollection()
//                    .getCharacteristicQuantificationList()) {
//                to.addCharacteristicQuantification(cloneCQ(cq));
//            }
//        }
//
//        return to;
//    }
//    private AnatomicEntityCharacteristic cloneAEC(
//            AnatomicEntityCharacteristic from) {
//
//        AnatomicEntityCharacteristic to = new AnatomicEntityCharacteristic();
//
//        to.setCagridId(from.getCagridId());
//        to.setLabel(from.getLabel());
//        to.setAnnotatorConfidence(from.getAnnotatorConfidence());
//        to.setCodeValue(from.getCodeValue());
//        to.setCodeMeaning(from.getCodeMeaning());
//        to.setCodingSchemeDesignator(from.getCodingSchemeDesignator());
//        to.setCodingSchemeVersion(from.getCodingSchemeVersion());
//
//        if (from.getCharacteristicQuantificationCollection() != null) {
//            for (CharacteristicQuantification cq : from
//                    .getCharacteristicQuantificationCollection()
//                    .getCharacteristicQuantificationList()) {
//                to.addCharacteristicQuantification(cloneCQ(cq));
//            }
//        }
//
//        return to;
//    }
//    private CharacteristicQuantification cloneCQ(
//            CharacteristicQuantification from) {
//
//        if (from.getXsiType() == "Numerical") {
//
//            Numerical numericalFrom = (Numerical) from;
//            Numerical to = new Numerical();
//            to.setAnnotatorConfidence(from.getAnnotatorConfidence());
//            to.setCagridId(from.getCagridId());
//            to.setName(from.getName());
//            to.setUcumString(numericalFrom.getUcumString());
//            to.setValue(numericalFrom.getValue());
//            to.setComparisonOperators(numericalFrom.getComparisonOperators());
//            return to;
//
//        } else if (from.getXsiType() == "Scalar") {
//
//            Scale scaleFrom = (Scale) from;
//            Scale to = new Scale();
//            to.setAnnotatorConfidence(from.getAnnotatorConfidence());
//            to.setCagridId(from.getCagridId());
//            to.setName(from.getName());
//            to.setComment(scaleFrom.getComment());
//            to.setDescription(scaleFrom.getDescription());
//            to.setValue(scaleFrom.getValue());
//            return to;
//
//        } else if (from.getXsiType() == "NonQuantifiable") {
//
//            NonQuantifiable nonQuantifiableFrom = (NonQuantifiable) from;
//            NonQuantifiable to = new NonQuantifiable();
//            to.setAnnotatorConfidence(from.getAnnotatorConfidence());
//            to.setCagridId(from.getCagridId());
//            to.setName(from.getName());
//            to.setCodeValue(nonQuantifiableFrom.getCodeValue());
//            to.setCodeMeaning(to.getCodeMeaning());
//            to.setCodingSchemeDesignator(to.getCodingSchemeDesignator());
//            to.setCodingSchemeVersion(to.getCodingSchemeVersion());
//            return to;
//
//        } else if (from.getXsiType() == "Interval") {
//
//            Interval intervalFrom = (Interval) from;
//            Interval to = new Interval();
//            to.setAnnotatorConfidence(from.getAnnotatorConfidence());
//            to.setCagridId(from.getCagridId());
//            to.setName(from.getName());
//            to.setMaxOperator(intervalFrom.getMaxOperator());
//            to.setMinOperator(intervalFrom.getMinOperator());
//            to.setMaxValue(intervalFrom.getMaxValue());
//            to.setMinValue(intervalFrom.getMinValue());
//            to.setUcumString(intervalFrom.getUcumString());
//            return to;
//
//        } else if (from.getXsiType() == "Quantile") {
//
//            Quantile quantileFrom = (Quantile) from;
//            Quantile to = new Quantile();
//            to.setAnnotatorConfidence(from.getAnnotatorConfidence());
//            to.setCagridId(from.getCagridId());
//            to.setName(from.getName());
//            to.setBin(quantileFrom.getBin());
//            return to;
//
//        }
//
//        return null;
//    }
//    private AnatomicEntity cloneAE(AnatomicEntity from) {
//
//        AnatomicEntity to = new AnatomicEntity();
//
//        to.setAnnotatorConfidence(from.getAnnotatorConfidence());
//        to.setCagridId(from.getCagridId());
//        to.setCodeMeaning(from.getCodeMeaning());
//        to.setCodeValue(from.getCodeValue());
//        to.setCodingSchemeDesignator(from.getCodingSchemeDesignator());
//        to.setCodingSchemeVersion(from.getCodingSchemeVersion());
//        to.setLabel(from.getLabel());
//
//        if (from.getAnatomicEntityCharacteristicCollection() != null) {
//            for (AnatomicEntityCharacteristic aec : from
//                    .getAnatomicEntityCharacteristicCollection()
//                    .getAnatomicEntityCharacteristicList()) {
//                to.addAnatomicEntityCharacteristic(cloneAEC(aec));
//            }
//        }
//
//        return to;
//    }
//    private Calculation cloneCalculation(Calculation from) {
//
//        Calculation to = new Calculation();
//
//        to.setCagridId(from.getCagridId());
//        to.setAlgorithmVersion(from.getAlgorithmVersion());
//        to.setAlgorithmName(from.getAlgorithmName());
//        to.setCodeMeaning(from.getCodeMeaning());
//        to.setCodeValue(from.getCodeValue());
//        to.setCodingSchemeDesignator(from.getCodingSchemeDesignator());
//        to.setCodingSchemeVersion(from.getCodingSchemeVersion());
//        to.setDescription(from.getDescription());
//        to.setMathML(from.getMathML());
//        to.setUid(from.getUid());
//        if (from.getCalculationResultCollection() != null) {
//            for (CalculationResult fromResult : from
//                    .getCalculationResultCollection()
//                    .getCalculationResultList()) {
//                to.addCalculationResult(cloneCalculationResult(fromResult));
//            }
//        }
//        if (from.getReferencedCalculationCollection() != null) {
//            for (ReferencedCalculation fromRef : from
//                    .getReferencedCalculationCollection()
//                    .getReferencedCalculationList()) {
//                to.addReferencedCalculation(cloneCalculationRef(fromRef));
//            }
//        }
//        if (from.getReferencedGeometricShapeCollection() != null) {
//            for (ReferencedGeometricShape fromShape : from
//                    .getReferencedGeometricShapeCollection()
//                    .getReferencedGeometricShapeList()) {
//                to.addReferencedGeometricShape(cloneRefShape(fromShape));
//            }
//        }
//
//        return to;
//    }
//    private ReferencedGeometricShape cloneRefShape(ReferencedGeometricShape from) {
//
//        ReferencedGeometricShape to = new ReferencedGeometricShape();
//        to.setCagridId(from.getCagridId());
//        to.setReferencedShapeIdentifier(from.getReferencedShapeIdentifier());
//        return to;
//    }
//
//    private ReferencedCalculation cloneCalculationRef(ReferencedCalculation from) {
//
//        ReferencedCalculation to = new ReferencedCalculation();
//        to.setCagridId(from.getCagridId());
//        to.setUniqueIdentifier(from.getUniqueIdentifier());
//        return to;
//    }
//    private CalculationResult cloneCalculationResult(
//            CalculationResult fromResult) {
//
//        CalculationResult toResult = new CalculationResult();
//        toResult.setCagridId(fromResult.getCagridId());
//        toResult.setType(fromResult.getType());
//        toResult.setUnitOfMeasure(fromResult.getUnitOfMeasure());
//        toResult.setNumberOfDimensions(fromResult.getNumberOfDimensions());
//
//        // Create a Dimension instance
//        if (fromResult.getDimensionCollection() != null) {
//            for (Dimension fromDimension : fromResult.getDimensionCollection()
//                    .getDimensionList()) {
//                Dimension toDimension = new Dimension(
//                        fromDimension.getCagridId(), fromDimension.getIndex(),
//                        fromDimension.getSize(), fromDimension.getLabel());
//                toResult.addDimension(toDimension);
//            }
//        }
//
//        if (fromResult.getCalculationDataCollection() != null) {
//            for (CalculationData fromData : fromResult
//                    .getCalculationDataCollection().getCalculationDataList()) {
//                CalculationData toData = new CalculationData();
//                toData.setCagridId(fromData.getCagridId());
//                toData.setValue(fromData.getValue());
//                if (fromData.getCoordinateCollection() != null) {
//                    for (Coordinate fromCoordinate : fromData
//                            .getCoordinateCollection().getCoordinateList()) {
//                        toData.addCoordinate(fromCoordinate.getCagridId(),
//                                fromCoordinate.getDimensionIndex(),
//                                fromCoordinate.getPosition());
//                    }
//                }
//                toResult.addCalculationData(toData);
//            }
//        }
//        return toResult;
//
//    }
    // put the label offset (the user moved the label) back into the shape
    @Override
    public void setShapeLabelOffset(int shapeID, double dx, double dy) {

        List<Shape> shapes = this.getShapes();
        for (Shape shape : shapes) {
            if (shape.getShapeIdentifier() == shapeID) {
                shape.setLabelOffsetX(dx);
                shape.setLabelOffsetY(dy);
            }
        }
    }

    // exporting aim calculations to one string
    @Override
    public String exportCalculations() {
        String result = "";

        for (Calculation calc : getCalculationCollection().getCalculationList()) {

            // put all the results for this calc into one value
            result += concatenateCalcs(calc.getCalculationResultCollection()
                    .getCalculationResultList()) + " ";
        }
        return result;
    }

    public List<Calculation> getCalculations() {
        List<Calculation> result = null;
        try {

            result = getCalculationCollection().getCalculationList();

        } catch (Exception e) {
        }

        return result;
    }

    private String concatenateCalcs(List<CalculationResult> calcs) {
        String answer = "";
        for (int i = 0; i < calcs.size(); i++) {
            List<CalculationData> data = calcs.get(i)
                    .getCalculationDataCollection().getCalculationDataList();

            for (int j = 0; j < data.size(); j++) {

                try {

                    answer += data.get(j).getValue();
                    answer += ((i + 1) < data.size()) ? " " : "";
                } catch (Exception e) {
                    logger.info(e.getMessage());
                }
            }

            answer += ((i + 1) < calcs.size()) ? " " : "";
        }

        return answer;
    }

    private Person getFirstPerson() {
        return getListPerson().get(0);
    }

    @Override
    public Patient getPatient() {
        return new Patient(getFirstPerson());
    }

    @Override
    public void setPatient(Patient patient) {
        Person person = getFirstPerson();
        person.setName(patient.getName());
        person.setId(patient.getId());
        person.setSex(patient.getSex());
        person.setBirthDate(patient.getBirthDate());

    }

    @Override
    public LoggedInUser getLoggedInUser() {
        LoggedInUser result = null;

        List<User> users = getListUser();
        if (users.size() > 0) {
            User firstUser = users.get(0);
            logger.info("new loggedInUser " + firstUser.getLoginName()
                    + firstUser.getName());
            result = new LoggedInUser(firstUser.getLoginName(),
                    firstUser.getName());
        }

        return result;
    }

    @Override
    public void setLoggedInUser(LoggedInUser loggedInUser) {

        // create a new user
        User user = new User();
        user.setLoginName(loggedInUser.getLoginName());
        user.setName(loggedInUser.getName());

        // and a list of users with this user
        ArrayList<User> users = new ArrayList<User>();
        users.add(user);

        // put this one user in the aim
        setListUser(users);
    }

    @Override
    public List<Component> getComponents(ComponentType componentType) {

        List<Component> result = new ArrayList<Component>();
        switch (componentType) {
            case anatomicEntity:
                for (AnatomicEntity anatomicEntity : getAnatomicEntityCollection().getAnatomicEntityList()) {
                    result.add(new Component(anatomicEntity));
                    for (AnatomicEntityCharacteristic anatomicEntityCharacteristic : anatomicEntity.getAnatomicEntityCharacteristicCollection().getAnatomicEntityCharacteristicList()) {
                        result.add(new Component(anatomicEntityCharacteristic));
                    }
                }
                break;
            case imagingObservation:
                for (ImagingObservation imagingObservation : getImagingObservationCollection().getImagingObservationList()) {
                    result.add(new Component(imagingObservation));
                    for (ImagingObservationCharacteristic imagingObservationCharacteristic : imagingObservation.getImagingObservationCharacteristicCollection().getImagingObservationCharacteristicList()) {
                        result.add(new Component(imagingObservationCharacteristic));
                    }
                }
                break;
            case inference:
                for (Inference infernce : getInferenceCollection().getInferenceList()) {
                    result.add(new Component(infernce));
                }
                break;
            default:
                break;

        }
        return result;
    }

    @Override
    public String toString() {

        String result = getXsiType() + ", " + getCagridId() + ", ";

        ImageReferenceCollection imageReferenceCollection = getImageReferenceCollection();
        List<ImageReference> imageReferenceList = imageReferenceCollection
                .getImageReferenceList();
        for (ImageReference imageReference : imageReferenceList) {
            DICOMImageReference dicomImageReference = (DICOMImageReference) imageReference;
            result += "imageReference "
                    + ", study="
                    + dicomImageReference.getImageStudy().getInstanceUID()
                    + ", series="
                    + dicomImageReference.getImageStudy().getImageSeries()
                    .getInstanceUID();
            for (Image img : dicomImageReference.getImageStudy()
                    .getImageSeries().getImageCollection().getImageList()) {
                result += ", image=" + img.getCagridId() + ", "
                        + img.getSopInstanceUID();

            }
        }

        GeometricShapeCollection geometricShapeCollection = getGeometricShapeCollection();
        List<GeometricShape> geometricShapeList = geometricShapeCollection
                .getGeometricShapeList();
        for (GeometricShape geometricShape : geometricShapeList) {
            result += "geometricShape=" + geometricShape.getCagridId() + ", ";

            SpatialCoordinateCollection x = geometricShape
                    .getSpatialCoordinateCollection();
            List<SpatialCoordinate> y = x.getSpatialCoordinateList();
            for (SpatialCoordinate z : y) {
                result += "SpatialCoordinate=" + z.getCagridId() + ", ";
            }
        }

        CalculationCollection calculationCollection = getCalculationCollection();
        List<Calculation> calculationList = calculationCollection
                .getCalculationList();
        for (Calculation calculation : calculationList) {
            result += "calculation=" + calculation.getCagridId() + ", ";
        }

        SegmentationCollection segmentationCollection = getSegmentationCollection();
        List<Segmentation> segmentationList = segmentationCollection
                .getSegmentationList();
        for (Segmentation segmentation : segmentationList) {
            result += "segmentation" + ", " + segmentation.getCagridId() + ", ";
        }

        List<Person> personList = getListPerson();
        for (Person person : personList) {
            result += "person" + ", " + person.getCagridId() + person.getId()
                    + person.getName() + person.getBirthDate()
                    + person.getSex() + ", ";
        }

        AnatomicEntityCollection aeCollection = getAnatomicEntityCollection();
        List<AnatomicEntity> aeList = aeCollection.getAnatomicEntityList();
        for (AnatomicEntity ae : aeList) {
            result += "AnatomicEntity" + ", " + ae.getCagridId() + ", ";

            AnatomicEntityCharacteristicCollection aecCollection = ae
                    .getAnatomicEntityCharacteristicCollection();
            List<AnatomicEntityCharacteristic> aecList = aecCollection
                    .getAnatomicEntityCharacteristicList();
            for (AnatomicEntityCharacteristic aec : aecList) {
                result += "AnatomicEntityCharacteristic" + ", "
                        + aec.getCagridId() + ", ";
            }
        }

        ImagingObservationCollection ioCollection = getImagingObservationCollection();
        List<ImagingObservation> ioList = ioCollection
                .getImagingObservationList();
        for (ImagingObservation io : ioList) {
            result += "ImagingObservation" + ", " + io.getCagridId() + ", ";

            ImagingObservationCharacteristicCollection iocCollection = io
                    .getImagingObservationCharacteristicCollection();
            List<ImagingObservationCharacteristic> iocList = iocCollection
                    .getImagingObservationCharacteristicList();
            for (ImagingObservationCharacteristic ioc : iocList) {
                result += "ImagingObservationCharacteristic" + ", "
                        + ioc.getCagridId() + ", ";
            }

        }

        InferenceCollection inferenceCollection = getInferenceCollection();
        List<Inference> inferenceList = inferenceCollection.getInferenceList();
        for (Inference inf : inferenceList) {
            result += "Inference" + ", " + inf.getCagridId() + ", ";

        }

        // setCalculationCollection(ia.getCalculationCollection());
        // setSegmentationCollection(ia.getSegmentationCollection());
        // setImageReferenceCollection(ia.getImageReferenceCollection());
        // setGeometricShapeCollection(ia.getGeometricShapeCollection());
        // setTextAnnotationCollection(ia.getTextAnnotationCollection());
        // setListPerson(ia.getListPerson());
        return result;
    }

    // // hakan
    // public List<TwoDCoordinate> parseTheTransformationResult(
    // String transformationResult) {
    // List<TwoDCoordinate> res = new ArrayList<TwoDCoordinate>();
    // transformationResult = transformationResult.replace("M", "");
    // List<String> listPoints = new ArrayList<String>();
    // listPoints.addAll(Arrays.asList(transformationResult.split("L")));
    // for (String pointStr : listPoints) {
    // String[] xy = pointStr.split(",");
    // TwoDCoordinate twoDCoord = new TwoDCoordinate();
    // twoDCoord.setX(Double.parseDouble(xy[0]));
    // twoDCoord.setY(Double.parseDouble(xy[1]));
    // res.add(twoDCoord);
    // }
    // return res;
    // }
    //
    // // hakan
    // public List<TwoDCoordinate> getPixelsOfCircle(TwoDCoordinate center,
    // double radius) {
    // List<TwoDCoordinate> res = new ArrayList<TwoDCoordinate>();
    // Ellipse2D.Double ellipse = new Ellipse2D.Double(center.getX() - radius,
    // center.getY() - radius, 2 * radius, 2 * radius);
    // Rectangle bounds = ellipse.getBounds();
    // for (int x = bounds.x; x < bounds.width; x++) {
    // for (int y = bounds.y; y < bounds.height; y++) {
    // if (ellipse.contains(x, y)) {
    // TwoDCoordinate twoDCoord = new TwoDCoordinate();
    // twoDCoord.setX((double) x);
    // twoDCoord.setY((double) y);
    // res.add(twoDCoord);
    // }
    // }
    // }
    // return res;
    // }
    //
    // // hakan
    // public List<TwoDCoordinate> getPixelsOfClosedShape(
    // List<TwoDCoordinate> points) {
    // List<TwoDCoordinate> res = new ArrayList<TwoDCoordinate>();
    // Path2D path = new Path2D.Double();
    // path.moveTo(points.get(0).getX(), points.get(0).getY());
    // for (int i = 1; i < points.size(); ++i) {
    // path.lineTo(points.get(i).getX(), points.get(i).getY());
    // }
    // path.closePath();
    //
    // Rectangle bounds = path.getBounds();
    // for (int x = bounds.x; x < bounds.width; x++) {
    // for (int y = bounds.y; y < bounds.height; y++) {
    // if (path.contains(x, y)) {
    // TwoDCoordinate twoDCoord = new TwoDCoordinate();
    // twoDCoord.setX((double) x);
    // twoDCoord.setY((double) y);
    // res.add(twoDCoord);
    // }
    // }
    // }
    //
    // logger.info("getPixelsOfClosedShape " + points.size() + " "
    // + res.size());
    // return res;
    // }
    public void setPerson(String patientSex, String patientBirthDate) {
        this.getPatient().setBirthDate(patientBirthDate);
        this.getPatient().setSex(patientSex);

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
        builder.append(Integer.toString(year)).append("-").append(strMount).append("-").append(strDay).append("T").append(strHour).append(":").append(strMinute).append(":").append(strSecond);
        return builder.toString();
    }

    public static String getFormatedDate() {
        Date date = new Date();

        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;
        int day = date.getDate();

        String strMount = Integer.toString(month);
        if (month < 10) {
            strMount = "0" + strMount;
        }
        String strDay = Integer.toString(day);
        if (day < 10) {
            strDay = "0" + strDay;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(Integer.toString(year)).append("-").append(strMount).append("-").append(strDay);
        return builder.toString();
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
        builder.append(Integer.toString(year)).append("-").append(strMount).append("-").append(strDay).append("T").append(strHour).append(":").append(strMinute).append(":").append(strSecond);
        this.setDateTime(builder.toString());
    }
}
