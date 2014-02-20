/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.edu.stanford.hakan.aim4api.projects.epad;

/**
 *
 * @author localadmin
 */
public class AnnotateTableRow {
    
    private String Owner;
    private String PatientName;
    private String StudyDate;
    private String Lesion;
    private String ModalitySeriesSlice;
    private String Template;
    private String AimIdentifier;

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String Owner) {
        this.Owner = Owner;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String PatientName) {
        this.PatientName = PatientName;
    }

    public String getStudyDate() {
        return StudyDate;
    }

    public void setStudyDate(String StudyDate) {
        this.StudyDate = StudyDate;
    }

    public String getLesion() {
        return Lesion;
    }

    public void setLesion(String Lesion) {
        this.Lesion = Lesion;
    }

    public String getModalitySeriesSlice() {
        return ModalitySeriesSlice;
    }

    public void setModalitySeriesSlice(String ModalitySeriesSlice) {
        this.ModalitySeriesSlice = ModalitySeriesSlice;
    }

    public String getTemplate() {
        return Template;
    }

    public void setTemplate(String Template) {
        this.Template = Template;
    }

    public String getAimIdentifier() {
        return AimIdentifier;
    }

    public void setAimIdentifier(String AimIdentifier) {
        this.AimIdentifier = AimIdentifier;
    } 
}
