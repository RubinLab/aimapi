//package edu.stanford.shared;
//
//import java.util.Date;
//import java.util.List;
//
//import edu.stanford.client.model.DicomSeries;
//import edu.stanford.client.view.SeriesView;
//import edu.stanford.hakan.aim4api.compability.aimv3.ImageAnnotation;
//import edu.stanford.shared.aimapi.Aim;
//import edu.stanford.shared.aimapi.Component;
//import edu.stanford.shared.aimapi.Enumerations.ComponentType;
//import edu.stanford.shared.aimapi.Enumerations.ShapeType;
//import edu.stanford.shared.aimapi.LoggedInUser;
//import edu.stanford.shared.aimapi.Patient;
//import edu.stanford.shared.aimapi.Shape;
//import edu.stanford.shared.aimapi.TwoDCoordinate;
//
///**
// * Interface for aimapi which will be implemented in using aim3 and aim4.
// * 
// * @author debra willrett
// * 
// */
//public interface Aimapi {
//
//	/**
//	 * Returns a Patient object. The patient is similar to the aimapi's person
//	 * object.
//	 * 
//	 * @return patient the patient object.
//	 */
//	Patient getPatient();
//
//	/**
//	 * Set the patient into the Aim object.
//	 * 
//	 * @param patient
//	 *            the Patient object.
//	 */
//	void setPatient(Patient patient);
//
//	/**
//	 * Get the patient identifier.
//	 * 
//	 * @return patient identifier
//	 */
//	String getPatientID();
//
//	/**
//	 * Get the patient name.
//	 * 
//	 * @return patient name
//	 */
//	String getPatientName();
//
//	/**
//	 * Clean the hats from the patient name.
//	 * 
//	 * @return patient name
//	 */
//	String cleanPatientName();
//
//	/**
//	 * Returns a loggedInUser object.
//	 * 
//	 * @return user the logged in user
//	 */
//	LoggedInUser getLoggedInUser();
//
//	/**
//	 * Set the user in the Aim object.
//	 * 
//	 * @param loggedInUser
//	 *            user to be added to aim.
//	 */
//	void setLoggedInUser(LoggedInUser loggedInUser);
//
//	/**
//	 * Clear the component values in the Aim object.
//	 */
//	void clearValues();
//
//	/**
//	 * Turn all the calculations from the Aim object into a string that can be
//	 * exported.
//	 * 
//	 * @return the exported calculation string.
//	 */
//	String exportCalculations();
//
//	//
//	// image reference
//	//
//
//	/**
//	 * Get the first image reference object imageID
//	 * 
//	 * @return imageID
//	 */
//	String getFirstImageID();
//
//	/**
//	 * Get the first image reference object seriesID
//	 * 
//	 * @return seriesID
//	 */
//	String getFirstSeriesID();
//
//	/**
//	 * Is there an image reference that contains this image id? If so, return
//	 * the series id.
//	 * 
//	 * @param imageID
//	 * @return
//	 */
//	String getSeriesID(String imageID);
//
//	/**
//	 * Return the studyID from the image reference list for this seriesID
//	 * 
//	 * @param seriesID
//	 *            uid of series
//	 * @return studyID
//	 */
//	String getStudyID(String seriesID);
//
//	/**
//	 * the first image reference study uid
//	 * 
//	 * @return study uid
//	 */
//	String getFirstStudyID();
//
//	/**
//	 * the first study date
//	 * 
//	 * @return
//	 */
//	Date getFirstStudyDate();
//
//	/**
//	 * Does the aim contain a reference to this series.
//	 * 
//	 * @param seriesUID
//	 *            The UID of the series.
//	 * @return <code>true</code> if there is a reference to this series in the
//	 *         Aim object; <code>false</code> otherwise.
//	 */
//	boolean hasSeries(String seriesID);
//
//	/**
//	 * Does the aim contain a reference to this image?
//	 * 
//	 * @param imageID
//	 * @return <code>true</code> if there is a reference to this image in the
//	 *         Aim object; <code>false</code> otherwise.
//	 */
//	boolean hasImage(String imageID);
//
//	/**
//	 * Does it have any image references?
//	 * 
//	 * @return
//	 */
//	boolean hasImage();
//
//	//
//	// segmentations
//	//
//
//	/**
//	 * Does the Aim object contain any segmentations?
//	 * 
//	 * @return <code>true</code> if there are segmentations in the Aim object;
//	 *         <code>false</code> otherwise.
//	 */
//	boolean hasSegmentation();
//
//	/**
//	 * Does the Aim object contain a segmentation with this imageID?
//	 * 
//	 * @param instanceUID
//	 *            the image instance UID to check.
//	 * @return <code>true</code> if it exists; <code>false</code> otherwise.
//	 */
//	boolean hasSegmentationImage(String imageID);
//
//	/**
//	 * find the first segmentation object and return its imageID
//	 * 
//	 * @return imageID
//	 */
//	String getFirstSegmentationImageID();
//
//	/**
//	 * TODO not sure I need this one.
//	 * 
//	 * @param oldSopInstanceUID
//	 * @param dsoImageUID
//	 * @param referencedSopInstanceUID
//	 * @param dsoSeriesUID
//	 * @param dsoStudyUID
//	 * @param frame
//	 * @param studyDate
//	 * @param studyTime
//	 */
//	void updateSegmentation(String oldSopInstanceUID, String dsoImageUID,
//			String referencedSopInstanceUID, String dsoSeriesUID,
//			String dsoStudyUID, int frame, String studyDate, String studyTime);
//
//	//
//	// shapes
//	//
//
//	/**
//	 * Does the Aim object contain geometric shapes?
//	 * 
//	 * @return <code>true</code> if there are geometric shapes in the Aim
//	 *         object; <code>false</code> otherwise.
//	 */
//	boolean hasShapes();
//
//	/**
//	 * Get the list of geometric shapes.
//	 * 
//	 * @return the list of geometric shapes.
//	 */
//	List<Shape> getShapes();
//
//	/**
//	 * Get the shape with with this shapeID.
//	 * 
//	 * @param int shapeID
//	 * @return Shape
//	 */
//	Shape getShape(int shapeID);
//
//	/**
//	 * Tell me what type of shape it is.
//	 * 
//	 * @param int shapeID
//	 * @return ShapeType
//	 */
//	ShapeType getShapeType(int shapeID);
//
//	/**
//	 * add or update a particular calculation for a shape
//	 * 
//	 * @param shapeID
//	 *            the shapeID
//	 * @param algorithmName
//	 *            the name of the algorithm for the calculation
//	 * @param value
//	 *            the value of the calculation
//	 * @return <code>true</code> if updated an existing calculation for this
//	 *         algorithm; <code>false</code> added a new calculation.
//	 */
//	void setShapeCalculation(int shapeID, String algorithmName, double value);
//
//	/**
//	 * Add these shapes to the list of shapes for the aim.
//	 * 
//	 * @param studyID
//	 *            study uid for image reference
//	 * @param seriesID
//	 *            series uid for image reference
//	 * @param imageID
//	 *            image uid for image reference
//	 * @param frameID
//	 *            frame id geometricShape
//	 * @param shapeType
//	 *            type of shape
//	 * @param coords
//	 *            the two or three dimension coordinates
//	 * @param pixelSpacingX
//	 *            pixel spacing in x used for calculations
//	 * @param pixelSpacingY
//	 *            pixel spacing in y used for calculations
//	 * @return
//	 */
//	// int addShapes(String studyID, String seriesID, String imageID, int
//	// frameID,
//	// ShapeType shapeType, List<TwoDCoordinate> coords,
//	// double pixelSpacingX, double pixelSpacingY);
//	int addShapes(DicomSeries series, int activeImage, ShapeType shapeType,
//			List<TwoDCoordinate> coords, double pixelSpacingX,
//			double pixelSpacingY);
//
//	boolean makeNameUnique(List<Aim> aims);
//
//	/**
//	 * Set the coordinates of the shape.
//	 * 
//	 * @param shapeID
//	 * @param coords
//	 */
//	void setShapeCoords(int shapeID, List<TwoDCoordinate> coords);
//
//	List<TwoDCoordinate> getFirstShapeCoords();
//
//	void setShapeLabelOffset(int shapeID, double dx, double dy);
//
//	boolean isNormal(int shapeID);
//
//	boolean isSpline(int shapeID);
//
//	boolean isEllipse(int shapeID);
//
//	boolean isCircle(int shapeID);
//
//	boolean isRectangle(int shapeID);
//
//	boolean hasPolyLine();
//
//	boolean hasMultiPoint();
//
//	boolean isPoint(int shapeID);
//
//	boolean isPolygon(int shapeID);
//
//	boolean isClosedShape(int shapeID);
//
//	//
//	// components
//	//
//
//	/**
//	 * Add these components to the existing components in the aim.
//	 * 
//	 * @param components
//	 */
//	void addComponents(List<Component> components);
//
//	/**
//	 * Get the list of components of a particular type from the aim.
//	 * 
//	 * @param componentType
//	 *            The type of component.
//	 * @return The list of components
//	 */
//	List<Component> getComponents(ComponentType componentType);
//
//	//
//	// cloning
//	//
//
//	ImageAnnotation cloneAim(ImageAnnotation fromAim, SeriesView view,
//			DicomSeries series, int image);
//
//	Aim cloneAim(SeriesView thisView, DicomSeries activeSeries, int index);
//
//	//
//	// looking at particular labels and code values
//	//
//
//	String getIOCodeValue(String label);
//
//	boolean hasIOCLabel(String IOlabel, String IOClabel);
//
//	boolean hasAELabel(String label);
//
//	String getAECodeMeaning(String label);
//
//	String getIOCCodeValue(String IOlabel, String IOClabel);
//
//	boolean hasIOLabel(String label);
//
//}
