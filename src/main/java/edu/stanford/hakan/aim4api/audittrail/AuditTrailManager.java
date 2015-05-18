/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.audittrail;

//import com.google.gwt.i18n.client.DateTimeFormat;
//import static com.google.gwt.i18n.client.TimeZone.createTimeZone;
import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.AuditTrail;
import edu.stanford.hakan.aim4api.base.CD;
import edu.stanford.hakan.aim4api.base.II;
import edu.stanford.hakan.aim4api.base.ImageAnnotation;
import edu.stanford.hakan.aim4api.base.ImageAnnotationCollection;
import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.usage.AnnotationBuilder;
import edu.stanford.hakan.aim4api.usage.AnnotationGetter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class AuditTrailManager {

    private String serverURL = "";
    private String namespace = "";
    private String collection = "";
    private String dbUserName = "";
    private String dbUserPassword = "";
    private String pathXSD = "";
    private String key = "~#*version*#~";

    public AuditTrailManager(String serverURL, String namespace, String collection, String dbUserName, String dbUserPassword, String pathXSD) {
        this.setCollection(collection);
        this.setDbUserName(dbUserName);
        this.setDbUserPassword(dbUserPassword);
        this.setNamespace(namespace);
        this.setServerURL(serverURL);
        this.setServerURL(serverURL);
        this.setPathXSD(pathXSD);
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbUserPassword() {
        return dbUserPassword;
    }

    public void setDbUserPassword(String dbUserPassword) {
        this.dbUserPassword = dbUserPassword;
    }    

    public String getPathXSD() {
        return pathXSD;
    }

    public void setPathXSD(String pathXSD) {
        this.pathXSD = pathXSD;
    }

    public String getKey() {
        return key;
    }

    private ImageAnnotationCollection getIACVersionHandler(ImageAnnotationCollection iac) throws AimException {
        List<ImageAnnotationCollection> listRes = AnnotationGetter.getImageAnnotationCollectionByDescriptionContains(serverURL, namespace, collection, dbUserName, dbUserPassword, iac.getUniqueIdentifier().getRoot());
        ImageAnnotationCollection iacVersion = null;
        if (listRes.size() > 0) {
            iacVersion = listRes.get(0);
        }
        return iacVersion;
    }

//    private ImageAnnotationCollection createIACVersionHandler(ImageAnnotationCollection iac) {
//        ImageAnnotation iaOriginalInitialState = iac.getImageAnnotation().getInitialState().getClone();
//        ImageAnnotationCollection iacVersionHandler = iac.getClone();
//        iacVersionHandler.refreshUniqueIdentifier();
//        iacVersionHandler.setDescription(new ST(iac.getUniqueIdentifier().getRoot() + this.key));
//        iacVersionHandler.getImageAnnotations().clear();
//
//        String newUID = iaOriginalInitialState.refreshUniqueIdentifier();
//        iac.getImageAnnotation().setPrecedentReferencedAnnotationUid(new II(newUID));
//        
//        
//        this.convertToVersion(iaOriginalInitialState,  1,null);
//        
//        
//        iacVersionHandler.addImageAnnotation(iaOriginalInitialState);
//        return iacVersionHandler;
//    }

//    private void pushVersionToHandler(ImageAnnotationCollection iac, ImageAnnotationCollection iacHandler) {
//        List<ImageAnnotation> sorted = getSortedVersions(iacHandler);
//        if (sorted.size() <= 0) {
//            return;
//        }
//        ImageAnnotation lastVersion = sorted.get(sorted.size() - 1);
//
//        ImageAnnotation iaOriginalInitialState = iac.getImageAnnotation().getInitialState().getClone();
//        String newUID = iaOriginalInitialState.refreshUniqueIdentifier();
//        iac.getImageAnnotation().setPrecedentReferencedAnnotationUid(new II(newUID));
//        this.convertToVersion(iaOriginalInitialState, sorted.size() + 1, lastVersion.getUniqueIdentifier().getRoot());
//        iacHandler.addImageAnnotation(iaOriginalInitialState);
//    }

    private void convertToVersion(ImageAnnotation ia, int versionNumber, String PrecedentReferencedAnnotationUid) {
        AuditTrail auditTrail = new AuditTrail();
        auditTrail.setStatusCode(new CD("codeValue", "codeMeaning", "codingSchemeDesignator", "codingSchemeVersion"));
        auditTrail.setDateTime("01.01.1900");
        auditTrail.setChangeReason(new CD("codeValue", "codeMeaning", "codingSchemeDesignator", "codingSchemeVersion"));
        auditTrail.setComment(new ST(Integer.toString(versionNumber)));
        ia.addAuditTrail(auditTrail);
        ia.setVersion(versionNumber);
        if (PrecedentReferencedAnnotationUid != "") {
            ia.setPrecedentReferencedAnnotationUid(new II(PrecedentReferencedAnnotationUid));
        } else {
            ia.setPrecedentReferencedAnnotationUid(null);
        }
    }

    
    private void convertToVersion(ImageAnnotationCollection iac, int versionNumber,String PrecedentReferencedAnnotationUid) {
        iac.refreshUniqueIdentifier();
        iac.getImageAnnotation().refreshUniqueIdentifier();
        this.convertToVersion(iac.getImageAnnotation(), versionNumber, PrecedentReferencedAnnotationUid);
        iac.setVersion(versionNumber);
    }

//    public ImageAnnotationCollection perform(ImageAnnotationCollection iac) throws AimException {
//        
////        //*** Kontrol
////        ImageAnnotation iaOriginalInitialState = iac.getImageAnnotation().getInitialState().getClone();
////        if(iaOriginalInitialState.getVersion() != -1)
////            System.out.println("KRIZZZZZZZZZZZZZZ");
//        if (!iac.getIsEdited()) {
//            return null;
//        }
//        ImageAnnotationCollection iacHandler = this.getIACVersionHandler(iac);
//        if (iacHandler == null) {
//            iacHandler = this.createIACVersionHandler(iac);
//        } else {
//            this.pushVersionToHandler(iac, iacHandler);
//        }
//        return iacHandler;
//    }

//    private List<ImageAnnotation> getSortedVersions(ImageAnnotationCollection iacVersinHandler) {
//        List<ImageAnnotation> res = new ArrayList<>();
//        List<ImageAnnotation> input = iacVersinHandler.getClone().getImageAnnotations();
//        String UID = "";
//        while (input.size() > 0) {
//            ImageAnnotation ia = null;
//            for (int i = 0; i < input.size(); i++) {
//                ia = input.get(i);
//                if (res.isEmpty() && (ia.getPrecedentReferencedAnnotationUid() == null || ia.getPrecedentReferencedAnnotationUid().getRoot() == null || "".equals(ia.getPrecedentReferencedAnnotationUid().getRoot()))) {
//                    UID = ia.getUniqueIdentifier().getRoot();
//                    res.add(ia);
//                    break;
//                } else {
//                    if (ia.getPrecedentReferencedAnnotationUid().getRoot().equals(UID)) {
//                        UID = ia.getUniqueIdentifier().getRoot();
//                        res.add(ia);
//                        break;
//                    }
//                }
//            }
//            if (ia != null) {
//                input.remove(ia);
//            }
//        }
//        return res;
//    }

//    private boolean isImageAnnotationCollectionVersionHandler(ImageAnnotationCollection iac)
//    {
//        if(iac.getDescription() == null)
//            return false;
//        return iac.getDescription().getValue().contains(this.key);
//    }
    
//    private boolean isImageAnnotatonCollectionVersion(ImageAnnotationCollection iac)
//    {
//        if(iac.getImageAnnotation().getAuditTrailCollection().size() > 0 || iac.getVersion() != -1)
//            return true;
//        return false;
//    }
//    
//    public boolean isImageAnnotatonVersion(ImageAnnotation ia)
//    {
//        return this.isImageAnnotatonCollectionVersion(ia.getImageAnnotationCollection());
//    }
//    
//    private ImageAnnotationCollection getCurrentImageAnnotationCollection(ImageAnnotationCollection iacVersionHandler) throws AimException {
//        String uid = iacVersionHandler.getDescription().getValue().replace(key, "");
//        return AnnotationGetter.getImageAnnotationCollectionByUniqueIdentifier(serverURL, namespace, collection, dbUserName, dbUserPassword, uid);
//    }
//
//    private ImageAnnotation getCurrentImageAnnotation(ImageAnnotationCollection iacVersionHandler) throws AimException {
//        return this.getCurrentImageAnnotationCollection(iacVersionHandler).getImageAnnotation();
//    }
//
//    private ImageAnnotationCollection getCurrentImageAnnotationCollection(ImageAnnotation iaVersion) throws AimException {
//        return this.getCurrentImageAnnotationCollection(iaVersion.getImageAnnotationCollection());
//    }
//
//    private ImageAnnotation getCurrentImageAnnotation(ImageAnnotation iaVersion) throws AimException {
//        return this.getCurrentImageAnnotationCollection(iaVersion).getImageAnnotation();
//    }
    
//    private ImageAnnotationCollection convertVersionToCurrent(ImageAnnotation iaVersion) throws AimException {
//        ImageAnnotationCollection iacCurrent = this.getCurrentImageAnnotationCollection(iaVersion).getClone();
//        iacCurrent.getImageAnnotations().clear();
//        iaVersion.setVersion(Integer.parseInt(iaVersion.getAuditTrailCollection().getAuditTrailList().get(0).getComment().getValue()));
//        iaVersion.getAuditTrailCollection().getAuditTrailList().clear();
//        iaVersion.setInitialState();
//        iacCurrent.addImageAnnotation(iaVersion);
//        return iacCurrent;
//    }

//    public List<ImageAnnotationCollection> getListVersions(ImageAnnotationCollection iac) throws AimException {
//        List<ImageAnnotationCollection> res = new ArrayList<>();
//        ImageAnnotationCollection iacHandler = this.getIACVersionHandler(iac);
//        if (iacHandler == null) {
//            return res;
//        }
//        List<ImageAnnotation> sortedIAs = getSortedVersions(iacHandler);
//        for (ImageAnnotation iaVersion : sortedIAs) {
//            res.add(this.convertVersionToCurrent(iaVersion));
//        }
//        return res;
//    }

    public List<ImageAnnotationCollection> performV2(ImageAnnotationCollection iac) throws AimException {
        List<ImageAnnotationCollection> res = new ArrayList<>();
        if (!iac.getIsEdited()) {
            return res;
        }
        ImageAnnotationCollection iacCloneInitialState = iac.getClone();
        iacCloneInitialState.getImageAnnotations().clear();
        iacCloneInitialState.addImageAnnotation(iac.getImageAnnotation().getInitialState().getClone());
        iacCloneInitialState.getImageAnnotation().refreshUniqueIdentifier();

        ImageAnnotationCollection iacVersionHandler = null;
            ImageAnnotationCollection lastVersion = this.getMaxVersionNumber(iac);
            List<ImageAnnotationCollection> listAllVersions = this.getListAllVersions(iac);
        //*** iac is the current state of the IAC
        if (iac.getVersion() == -1) {
            //*** it will be the first version of the iac
            if ("".equals(getPreviousUID(iac)) || lastVersion == null) {
                this.convertToVersion(iacCloneInitialState, 1, "");
                this.setPreviousUID(iacCloneInitialState, null);
                this.setPreviousUID(iac, iacCloneInitialState.getImageAnnotation().getUniqueIdentifier().getRoot());
                listAllVersions.add(iacCloneInitialState);
            }//*** I will append its initial state as the new version.
            else {
                //*** just its initial state will be the next version
                this.convertToVersion(iacCloneInitialState, lastVersion.getVersion() + 1, lastVersion.getImageAnnotation().getUniqueIdentifier().getRoot());
                this.setPreviousUID(iac, iacCloneInitialState.getImageAnnotation().getUniqueIdentifier().getRoot());
                listAllVersions.add(iacCloneInitialState);
            }
        }//*** iac is one of the version of IAC
        else {
            ImageAnnotationCollection iacCurrent = this.getCurrentVersion(iac);// AnnotationGetter.getImageAnnotationCollectionByUniqueIdentifier(serverURL, namespace, collection, dbUserName, dbUserPassword, iac.getUniqueIdentifier().getRoot());
            String originalUID_IAC = iacCurrent.getUniqueIdentifier().getRoot();
            String originalUID_IA = iacCurrent.getImageAnnotation().getUniqueIdentifier().getRoot();
            this.convertToVersion(iacCurrent, lastVersion.getVersion() + 1, this.getPreviousUID(iacCurrent));
            iac.setUniqueIdentifier(new II(originalUID_IAC));
            listAllVersions.add(iacCurrent);
            //res.add(iacCurrent);
            
            iac = iac.getClone();
            iac.getImageAnnotation().getAuditTrailCollection().getAuditTrailList().clear();
            iac.setVersion(-1);
            this.setPreviousUID(iac, iac.getImageAnnotation().getUniqueIdentifier().getRoot());
            iac.getImageAnnotation().setUniqueIdentifier(new II(originalUID_IA));
        }
        iacVersionHandler = this.buildIACVersionHandler(listAllVersions, iac);
        res.add(iac);
        res.add(iacVersionHandler);
        return res;
    }

    private ImageAnnotationCollection buildIACVersionHandler(List<ImageAnnotationCollection> listIACVersions,ImageAnnotationCollection iacCurrent) throws AimException
    {
        ImageAnnotationCollection res = this.getIACVersionHandler(iacCurrent);
        if(res == null)
        {
            res = iacCurrent.getClone();
            res.refreshUniqueIdentifier();
            res.setDescription(new ST(iacCurrent.getUniqueIdentifier().getRoot() + this.key));
        }
        res.getImageAnnotations().clear();
        for(ImageAnnotationCollection iacVersion:listIACVersions)
        {
            res.addImageAnnotation(iacVersion.getImageAnnotation().getClone());
        }
        return res;
    }
    
    private ImageAnnotationCollection buildIACVersionHandler(ImageAnnotationCollection listIACVersion,ImageAnnotationCollection iacCurrent) throws AimException
    {
        List<ImageAnnotationCollection>  temp = new ArrayList<>();
        temp.add(listIACVersion);
        return buildIACVersionHandler(temp,iacCurrent);
    }

//    public List<ImageAnnotationCollection> getListOfVersions(ImageAnnotationCollection iacCurrent) throws AimException
//    {
//        List<ImageAnnotationCollection> res = new ArrayList<>();
//        ImageAnnotationCollection iacVersionHandler = this.getIACVersionHandler(iacCurrent);
//        if(iacVersionHandler == null)
//            return res;
//        for(ImageAnnotation iaVersion:iacVersionHandler.getImageAnnotations())
//        {
//            ImageAnnotationCollection iacClone = iacCurrent.getClone();
//            iacClone.getImageAnnotations().clear();
//            iacClone.addImageAnnotation(iaVersion);
//            res.add(iacClone);
//        }      
//        return res;
//    }
    
    //*** returns all versions of the annotation in order
    public List<ImageAnnotationCollection> getListAllVersions(ImageAnnotationCollection iacCurrent) throws AimException
    {
        List<ImageAnnotationCollection> temp = new ArrayList<>();
        ImageAnnotationCollection iacVersionHandler = this.getIACVersionHandler(iacCurrent);
        if(iacVersionHandler == null)
            return temp;
        for(ImageAnnotation iaVersion:iacVersionHandler.getImageAnnotations())
        {
            ImageAnnotationCollection iacClone = iacCurrent.getClone();
            iacClone.getImageAnnotations().clear();
            iacClone.addImageAnnotation(iaVersion);
            temp.add(iacClone);
        }

        int maxVersion = -1;
        for (ImageAnnotationCollection iacVersion : temp) {
            if (iacVersion.getVersion() > maxVersion) {
                maxVersion = iacVersion.getVersion();
            }
        }

        List<ImageAnnotationCollection> res = new ArrayList<>();
        for (int i = 1; i <= maxVersion; i++) {
            for (ImageAnnotationCollection iacVersion : temp) {
                if (iacVersion.getVersion() == i) {
                    res.add(iacVersion);
                    break;
                }
            }
        }
        return res;
    }
    
    private String getPreviousUID(ImageAnnotationCollection iac)
    {
        if(iac.getImageAnnotations() == null || iac.getImageAnnotations().size() <=0)
            return "";
        ImageAnnotation ia = iac.getImageAnnotation();
        if(ia.getPrecedentReferencedAnnotationUid() == null)
            return "";
        return ia.getPrecedentReferencedAnnotationUid().getRoot();
    }

    private void setPreviousUID(ImageAnnotationCollection iac, String UID) {
        if (iac.getImageAnnotations() == null || iac.getImageAnnotations().size() <= 0) {
            return;
        }
        ImageAnnotation ia = iac.getImageAnnotation();
        if (UID == null) {
            ia.setPrecedentReferencedAnnotationUid(null);
        } else {
            ia.setPrecedentReferencedAnnotationUid(new II(UID));
        }
    }

    private ImageAnnotationCollection getMaxVersionNumber(ImageAnnotationCollection iacCurrent) throws AimException {
        int maxVersion = -1;
        ImageAnnotationCollection res = null;
        List<ImageAnnotationCollection> listAllVersions = getListAllVersions(iacCurrent);
        if (listAllVersions.size() > 0) {
            return listAllVersions.get(listAllVersions.size() - 1);
        }
        return res;
    }
    
    public ImageAnnotationCollection getCurrentVersion(ImageAnnotationCollection iac) throws AimException
    {
        return AnnotationGetter.getImageAnnotationCollectionByUniqueIdentifier(serverURL, namespace, collection, dbUserName, dbUserPassword, iac.getUniqueIdentifier().getRoot());
    }
}


//        Date now = new Date();
//        DateTimeFormat dateFormat = DateTimeFormat.getFormat("MM.dd.yyyy hh:mm:ss");
//        dateFormat.format(now, createTimeZone(0));