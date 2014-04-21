/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.edu.stanford.hakan.aim4api.projects.epad;

import main.java.edu.stanford.hakan.aim4api.base.DicomImageReferenceEntity;
import main.java.edu.stanford.hakan.aim4api.base.II;
import main.java.edu.stanford.hakan.aim4api.base.Image;
import main.java.edu.stanford.hakan.aim4api.base.ImageSeries;
import main.java.edu.stanford.hakan.aim4api.base.ImageStudy;

/**
 *
 * @author Hakan
 */
public class DICOMImageReference {
    
    private String imageUid;
    private String seriesUid;
    private String studyUid;
    private int image;
    private String studyDate;
    private String studyTime;

    public DICOMImageReference() {
    }

    public DICOMImageReference(String imageUid, String seriesUid, String studyUid, int image, String studyDate, String studyTime) {
        this.imageUid = imageUid;
        this.seriesUid = seriesUid;
        this.studyUid = studyUid;
        this.image = image;
        this.studyDate = studyDate;
        this.studyTime = studyTime;
    }

    public DICOMImageReference(DicomImageReferenceEntity dicomImageReferenceEntity) {
        this.studyUid = dicomImageReferenceEntity.getImageStudy().getInstanceUid().getRoot();
        this.studyDate = dicomImageReferenceEntity.getImageStudy().getStartDate();
        this.studyTime = dicomImageReferenceEntity.getImageStudy().getStartTime();
        this.seriesUid = dicomImageReferenceEntity.getImageStudy().getImageSeries().getInstanceUid().getRoot();
        this.imageUid = dicomImageReferenceEntity.getImageStudy().getImageSeries().getImageCollection().getImageList().get(0).getSopClassUid().getRoot();
    }

    public String getImageUid() {
        return imageUid;
    }

    public void setImageUid(String imageUid) {
        this.imageUid = imageUid;
    }

    public String getSeriesUid() {
        return seriesUid;
    }

    public void setSeriesUid(String seriesUid) {
        this.seriesUid = seriesUid;
    }

    public String getStudyUid() {
        return studyUid;
    }

    public void setStudyUid(String studyUid) {
        this.studyUid = studyUid;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(String studyDate) {
        this.studyDate = studyDate;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }
    
    public DicomImageReferenceEntity toAimV4()
    {
        DicomImageReferenceEntity  res = new DicomImageReferenceEntity();
        ImageStudy imageStudy = new ImageStudy();
        imageStudy.setInstanceUid(new II(this.getStudyUid()));
        imageStudy.setStartDate(this.getStudyDate());
        imageStudy.setStartTime(this.getStudyTime());
        
        ImageSeries imageSeries = new ImageSeries();
        imageSeries.setInstanceUid(new II(this.getSeriesUid()));
        
        Image imageAimV4 = new Image();
        imageAimV4.setSopClassUid(new II(this.getImageUid()));

        imageSeries.addImage(imageAimV4);
        imageStudy.setImageSeries(imageSeries);
        res.setImageStudy(imageStudy);
        
        return res;
    }    
}
