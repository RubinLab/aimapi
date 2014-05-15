/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.wrappers.epad.old;

import com.google.gwt.i18n.client.DateTimeFormat;
import edu.stanford.hakan.aim4api.base.II;
import edu.stanford.hakan.aim4api.base.ImageSeries;
import edu.stanford.hakan.aim4api.base.ImageStudy;
import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.projects.epad.wrapper.AnatomicEntity;
import edu.stanford.hakan.aim4api.projects.epad.wrapper.Calculation;
import edu.stanford.hakan.aim4api.projects.epad.wrapper.DICOMImageReference;
import edu.stanford.hakan.aim4api.projects.epad.wrapper.Equipment;
import edu.stanford.hakan.aim4api.projects.epad.wrapper.ImagingObservation;
import edu.stanford.hakan.aim4api.projects.epad.wrapper.Inference;
import edu.stanford.hakan.aim4api.projects.epad.wrapper.Person;
import edu.stanford.hakan.aim4api.projects.epad.wrapper.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class Aim extends edu.stanford.hakan.aim4api.projects.epad.wrapper.ImageAnnotation {

    public Aim(String name, String modality, String description, String patientName, String patientId, String patientSex, String patientBirthdate, String manufacturerName, String model, String version, int activeImage, User user, String imageUid, String seriesUid, String studyUid, String studyDate, String studyTime) {
          super(name, modality, description, patientName, patientId, patientSex, patientBirthdate, manufacturerName, model, version, activeImage, user, imageUid, seriesUid, studyUid, studyDate, studyTime);
    }

//    public Aim(String name, String modality, String description,
//            String patientName, String patientId, String patientSex,
//            String patientBirthdate, String manufacturerName, String model,
//            String version, int activeImage, User user, String imageUid,
//            String seriesUid, String studyUid, String studyDate,
//            String studyTime) {
//
//        super();
//
//        this.setName(new ST(name));
//        this.setDateTime(todaysDate());
//        this.setComment(new ST(fillComment(modality, description, activeImage)));
//
//        addUser(user);
//
//        addPerson(createPerson(patientName, patientId, patientSex,
//                formatDateTime(patientBirthdate)));
//        addEquipment(createEquipment(manufacturerName, model, version));
//        addImageReference(createImageReference(imageUid, seriesUid, studyUid,
//                activeImage, formatDateTime(studyDate, studyTime),
//                formatTime(studyTime)));
//
//        addAnatomicEntity(createAnatomicEntity());
//    }

//    public List<Calc> getCalcs() {
//        List<Calc> result = new ArrayList<Calc>();
//        for (Calculation calculation : getCalculationCollection()
//                .getCalculationList()) {
//            result.add((Calc) calculation.getClone());
//        }
//        return result;
//    }

//    public List<AE> getAEs() {
//        List<AE> result = new ArrayList<AE>();
//        for (AnatomicEntity anatomicEntity : getAnatomicEntityCollection().getAnatomicEntityList()) {
//            result.add((AE) anatomicEntity.getClone());
//        }
//        return result;
//    }

//    public List<IO> getIOs() {
//        List<IO> result = new ArrayList<IO>();
//        for (ImagingObservation imagingObservation : getImagingObservationCollection().getImagingObservationList()) {
//            result.add((IO) imagingObservation.getClone());
//        }
//        return result;
//
//    }

//    public List<I> getIs() {
//        List<I> result = new ArrayList<I>();
//        for (Inference inference : getInferenceCollection().getInferenceList()) {
//            result.add((I) inference.getClone());
//        }
//        return result;
//    }
    
}
