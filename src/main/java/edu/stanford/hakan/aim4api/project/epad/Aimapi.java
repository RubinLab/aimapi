package edu.stanford.hakan.aim4api.project.epad;

import java.util.Date;
import java.util.List;

import edu.stanford.hakan.aim4api.project.epad.Aim;
import edu.stanford.hakan.aim4api.project.epad.Component;
import edu.stanford.hakan.aim4api.project.epad.Enumerations.ComponentType;
import edu.stanford.hakan.aim4api.project.epad.Enumerations.ShapeType;
import edu.stanford.hakan.aim4api.project.epad.LoggedInUser;
import edu.stanford.hakan.aim4api.project.epad.Patient;
import edu.stanford.hakan.aim4api.project.epad.Shape;
import edu.stanford.hakan.aim4api.project.epad.TwoDCoordinate;

/**
 * Interface for aimapi which will be implemented in aim3 and aim4.
 * 
 * @author Debra Willrett
 * 
 */
public interface Aimapi {

	Patient getPatient();

	void setPatient(Patient patient);

	String getPatientID();

	String getPatientName();

	String cleanPatientName();

	LoggedInUser getLoggedInUser();

	void setLoggedInUser(LoggedInUser loggedInUser);

	void clearValues();

	String exportCalculations();

	//
	// image reference
	//

	String getFirstImageID();

	String getFirstSeriesID();

	String getSeriesID(String imageID);

	String getStudyID(String seriesID);

	String getFirstStudyID();

	Date getFirstStudyDate();

	boolean hasSeries(String seriesID);

	boolean hasImage(String imageID);

	boolean hasImage();

	//
	// segmentations
	//

	boolean hasSegmentation();

	boolean hasSegmentationImage(String imageID);

	String getFirstSegmentationImageID();

	//
	// shapes
	//

	boolean hasShapes();

	List<Shape> getShapes();

	Shape getShape(int shapeID);

	ShapeType getShapeType(int shapeID);

	void setShapeCalculation(int shapeID, String algorithmName, double value);

	int addShapes(String studyID, String seriesID, String imageID,
			int activeImage, String studyDate, String studyTime,
			ShapeType shapeType, List<TwoDCoordinate> coords,
			double pixelSpacingX, double pixelSpacingY,  String imageClassUID); //ml classuid added

	boolean makeNameUnique(List<Aim> aims);

	void setShapeCoords(int shapeID, List<TwoDCoordinate> coords);

	List<TwoDCoordinate> getFirstShapeCoords();

	void setShapeLabelOffset(int shapeID, double dx, double dy);

	boolean isNormal(int shapeID);

	boolean isSpline(int shapeID);

	boolean isEllipse(int shapeID);

	boolean isCircle(int shapeID);

	boolean isRectangle(int shapeID);

	boolean hasPolyLine();

	boolean hasMultiPoint();

	boolean isPoint(int shapeID);

	boolean isPolygon(int shapeID);

	boolean isClosedShape(int shapeID);

	//
	// components
	//

	void addComponents(List<Component> components);

	List<Component> getComponents(ComponentType componentType);

	//
	// looking at particular labels and code values
	//

	String getIOCodeValue(String label);

	boolean hasIOCLabel(String IOlabel, String IOClabel);

	boolean hasAELabel(String label);

	String getAECodeMeaning(String label);

	String getIOCCodeValue(String IOlabel, String IOClabel);

	boolean hasIOLabel(String label);

	

	
	/**
	 * 3D version of addshapes
	 * @param studyID
	 * @param seriesID
	 * @param imageID
	 * @param activeImage
	 * @param studyDate
	 * @param studyTime
	 * @param shapeType
	 * @param coords
	 * @param pixelSpacingX
	 * @param pixelSpacingY
	 * @param imageClassUID
	 * @return
	 */
	int add3DShapes(String studyID, String seriesID, String imageID, String studyDate, String studyTime,
			ShapeType shapeType, List<ThreeDCoordinate> coords, String imageClassUID, String frameOfRefUID,
			String fiducialUID);

}
