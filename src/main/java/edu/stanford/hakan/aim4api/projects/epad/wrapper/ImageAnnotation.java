/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.edu.stanford.hakan.aim4api.projects.epad.wrapper;

import java.util.ArrayList;
import java.util.List;
import main.java.edu.stanford.hakan.aim4api.base.CD;
import main.java.edu.stanford.hakan.aim4api.base.Equipment;

/**
 *
 * @author Hakan
 */
public class ImageAnnotation extends main.java.edu.stanford.hakan.aim4api.base.ImageAnnotation {

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
        return (AnatomicEntityCollection) this.getImagingPhysicalEntityCollection();
    }

    public CalculationCollection getCalculationCollection() {
        return (CalculationCollection) this.getCalculationEntityCollection();
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
        List<Person> res = new ArrayList<>();
        Person item = (Person) this.getImageAnnotationCollection().getPerson();
        if (item != null) {
            res.add(item);
        }
        return res;
    }

    public List<User> getListUser() {
        List<User> res = new ArrayList<>();
        User item = (User) this.getImageAnnotationCollection().getUser();
        if (item != null) {
            res.add(item);
        }
        return res;

    }

    public String getNameEpad() {
        return this.getName().getValue();
    }

    public GeometricShapeCollection getGeometricShapeCollection() {
        return (GeometricShapeCollection) this.getMarkupEntityCollection();
    }
}
