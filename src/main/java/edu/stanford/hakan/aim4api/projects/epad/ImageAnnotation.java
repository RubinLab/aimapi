/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.edu.stanford.hakan.aim4api.projects.epad;

import java.util.ArrayList;
import java.util.List;
import main.java.edu.stanford.hakan.aim4api.base.User;

/**
 *
 * @author Hakan
 */
public class ImageAnnotation {

    private String name = "";
    private String modality = "";
    private String description = "";
    private Person patient;
    private Equipment equipment;
    private User user;
    private DICOMImageReference imageReference;
    private List<GeometricShape> listGeometricShapes = new ArrayList<GeometricShape>();
    private List<Calculation> listCalculation = new ArrayList<Calculation>();

    public ImageAnnotation(String name, String modality, String description, String patientName, String patientId,
            String patientSex, String patientBirthdate, String manufacturerName, String model, String version,
            int activeImage, User user, String imageUid, String seriesUid, String studyUid, String studyDate, String studyTime) {
        this.name = name;
        this.modality = modality;
        this.description = description;
        this.patient = new Person(patientName, patientId, patientSex, patientBirthdate);
        this.equipment = new Equipment(manufacturerName, model, version);
        this.user = user;
        this.imageReference = new DICOMImageReference(imageUid, seriesUid, studyUid, activeImage, studyDate, studyTime);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getPatient() {
        return patient;
    }

    public void setPatient(Person patient) {
        this.patient = patient;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public int getActiveImage() {
        return this.imageReference.getImage();
    }

    public void setActiveImage(int activeImage) {
        this.imageReference.setImage(activeImage);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DICOMImageReference getImageReference() {
        return imageReference;
    }

    public void setImageReference(DICOMImageReference imageReference) {
        this.imageReference = imageReference;
    }

    public List<GeometricShape> getListGeometricShapes() {
        return listGeometricShapes;
    }

    public void setListGeometricShapes(List<GeometricShape> listGeometricShapes) {
        this.listGeometricShapes = listGeometricShapes;
    }

}
