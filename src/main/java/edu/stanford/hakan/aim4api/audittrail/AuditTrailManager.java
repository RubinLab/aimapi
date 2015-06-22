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
import edu.stanford.hakan.aim4api.utility.Globals;
import edu.stanford.hakan.aim4api.utility.Logger;
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


    private ImageAnnotationCollection getIACVersionHandler(ImageAnnotationCollection iac) throws AimException {
        String description = iac.getUniqueIdentifier().getRoot() + Globals.flagVersion;
        List<ImageAnnotationCollection> listRes = AnnotationGetter.getImageAnnotationCollectionVersionHandler(serverURL, namespace, collection, dbUserName, dbUserPassword, description);
        ImageAnnotationCollection iacVersion = null;
        if (listRes.size() > 0) {
            iacVersion = listRes.get(0);
        }
        return iacVersion;
    }

    public ImageAnnotationCollection getIACVersionHandler(String UIDofOriginal) throws AimException {
        String description = UIDofOriginal + Globals.flagVersion;
        Logger.write("==== IAC description: " + description);
        List<ImageAnnotationCollection> listRes = AnnotationGetter.getImageAnnotationCollectionVersionHandler(serverURL, namespace, collection, dbUserName, dbUserPassword, description);
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
//    private void convertToVersion(ImageAnnotation ia, int versionNumber, String PrecedentReferencedAnnotationUid) {
//        AuditTrail auditTrail = new AuditTrail();
//        auditTrail.setStatusCode(new CD("codeValue", "codeMeaning", "codingSchemeDesignator", "codingSchemeVersion"));
//        auditTrail.setDateTime("01.01.1900");
//        auditTrail.setChangeReason(new CD("codeValue", "codeMeaning", "codingSchemeDesignator", "codingSchemeVersion"));
//        auditTrail.setComment(new ST(Integer.toString(versionNumber)));
//        ia.addAuditTrail(auditTrail);
//        ia.setVersion(versionNumber);
//        if (PrecedentReferencedAnnotationUid != "") {
//            ia.setPrecedentReferencedAnnotationUid(new II(PrecedentReferencedAnnotationUid));
//        } else {
//            ia.setPrecedentReferencedAnnotationUid(null);
//        }
//    }
//
//    private void convertToVersion(ImageAnnotationCollection iac, int versionNumber, String PrecedentReferencedAnnotationUid) {
//        iac.refreshUniqueIdentifier();
//        iac.getImageAnnotation().refreshUniqueIdentifier();
//        this.convertToVersion(iac.getImageAnnotation(), versionNumber, PrecedentReferencedAnnotationUid);
//        iac.setVersion(versionNumber);
//    }
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
//    public List<ImageAnnotationCollection> performV2(ImageAnnotationCollection iac) throws AimException {
//        List<ImageAnnotationCollection> res = new ArrayList<>();
//
//        //Logger.write("0");
//        //if (!iac.getIsEdited()) {
//        if (!isEdited(iac)) {
//
//            //Logger.write("0.1");
//            return res;
//        }
//        ImageAnnotationCollection iacCloneInitialState = iac.getClone();
//        iacCloneInitialState.getImageAnnotations().clear();
//        iacCloneInitialState.addImageAnnotation(iac.getImageAnnotation().getInitialState().getClone());
//        iacCloneInitialState.getImageAnnotation().refreshUniqueIdentifier();
//
//        ImageAnnotationCollection iacVersionHandler = null;
//        ImageAnnotationCollection lastVersion = this.getMaxVersionNumber(iac);
//        List<ImageAnnotationCollection> listAllVersions = this.getListAllVersions(iac);
//
////        for (ImageAnnotationCollection temp : listAllVersions) {
////            try {
////                String uid = temp.getImageAnnotation().getUniqueIdentifier().getRoot();
////                String commentA = temp.getImageAnnotation().getComment().getValue();
////                String preUid = temp.getImageAnnotation().getPrecedentReferencedAnnotationUid().getRoot();
////                if (preUid != null && !"".equals(preUid.trim())) {
////                    for (ImageAnnotationCollection temp2 : listAllVersions) {
////                        if (temp2.getImageAnnotation().getUniqueIdentifier().getRoot().equals(preUid)) {
////
////                            String commentB = temp2.getImageAnnotation().getComment().getValue();
////                            String abc = "abc";
////                        }
////                    }
////                }
////            } catch (Throwable th) {
////
////            }
////        }
//        //Logger.write("1");
//        //*** iac is the current state of the IAC
//        if (iac.getVersion() == -1) {
//            //Logger.write("2");
//            //*** it will be the first version of the iac
//            if ("".equals(getPreviousUID(iac)) || lastVersion == null) {
//                //Logger.write("3");
//                this.convertToVersion(iacCloneInitialState, 1, "");
//                this.setPreviousUID(iacCloneInitialState, null);
//                this.setPreviousUID(iac, iacCloneInitialState.getImageAnnotation().getUniqueIdentifier().getRoot());
//                listAllVersions.add(iacCloneInitialState);
//            }//*** I will append its initial state as the new version.
//            else {
//                //Logger.write("4");
//                //*** just its initial state will be the next version
//                //this.convertToVersion(iacCloneInitialState, lastVersion.getVersion() + 1, lastVersion.getImageAnnotation().getUniqueIdentifier().getRoot());
//                this.convertToVersion(iacCloneInitialState, lastVersion.getVersion() + 1, getPreviousUID(iac));
//                this.setPreviousUID(iac, iacCloneInitialState.getImageAnnotation().getUniqueIdentifier().getRoot());
//                listAllVersions.add(iacCloneInitialState);
//            }
//        }//*** iac is one of the version of IAC
//        else {
//            //Logger.write("5");
//            ImageAnnotationCollection iacCurrent = this.getCurrentVersion(iac);// AnnotationGetter.getImageAnnotationCollectionByUniqueIdentifier(serverURL, namespace, collection, dbUserName, dbUserPassword, iac.getUniqueIdentifier().getRoot());
//            String originalUID_IAC = iacCurrent.getUniqueIdentifier().getRoot();
//            String originalUID_IA = iacCurrent.getImageAnnotation().getUniqueIdentifier().getRoot();
//            this.convertToVersion(iacCurrent, lastVersion.getVersion() + 1, this.getPreviousUID(iacCurrent));
//            iac.setUniqueIdentifier(new II(originalUID_IAC));
//            listAllVersions.add(iacCurrent);
//            //res.add(iacCurrent);
//
//            iac = iac.getClone();
//            iac.getImageAnnotation().getAuditTrailCollection().getAuditTrailList().clear();
//            iac.setVersion(-1);
//            this.setPreviousUID(iac, iac.getImageAnnotation().getUniqueIdentifier().getRoot());
//            iac.getImageAnnotation().setUniqueIdentifier(new II(originalUID_IA));
//        }
//        iacVersionHandler = this.buildIACVersionHandler(listAllVersions, iac);
//        res.add(iac);
//        res.add(iacVersionHandler);
//        return res;
//    }
//    private ImageAnnotationCollection buildIACVersionHandler(List<ImageAnnotationCollection> listIACVersions, ImageAnnotationCollection iacCurrent) throws AimException {
//        ImageAnnotationCollection res = this.getIACVersionHandler(iacCurrent);
//        if (res == null) {
//            res = iacCurrent.getClone();
//            res.refreshUniqueIdentifier();
//            res.setDescription(new ST(iacCurrent.getUniqueIdentifier().getRoot() + this.key));
//        }
//        res.getImageAnnotations().clear();
//        for (ImageAnnotationCollection iacVersion : listIACVersions) {
//            res.addImageAnnotation(iacVersion.getImageAnnotation().getClone());
//        }
//        return res;
//    }
//    private ImageAnnotationCollection buildIACVersionHandler(ImageAnnotationCollection listIACVersion, ImageAnnotationCollection iacCurrent) throws AimException {
//        List<ImageAnnotationCollection> temp = new ArrayList<>();
//        temp.add(listIACVersion);
//        return buildIACVersionHandler(temp, iacCurrent);
//    }
//    private boolean isEdited(ImageAnnotationCollection iac) throws AimException {
//        if (iac.getImageAnnotation().getVersion() > 0) {
//            List<ImageAnnotationCollection> listVersions = this.getListAllVersions(iac);
//            for (ImageAnnotationCollection other : listVersions) {
//                if (other.getImageAnnotation().getVersion() == iac.getImageAnnotation().getVersion()) {
//                    return !iac.isEqualTo(other);
//                }
//            }
//        } else {
//            ImageAnnotationCollection other = this.getCurrentVersion(iac);
//            return !iac.isEqualTo(other);
//        }
//
//        return false;
//    }
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
    public List<ImageAnnotationCollection> getListAllVersions(ImageAnnotationCollection iacCurrent) throws AimException {
        List<ImageAnnotationCollection> temp = new ArrayList<>();
        ImageAnnotationCollection iacVersionHandler = this.getIACVersionHandler(iacCurrent);
        if (iacVersionHandler == null) {
            return temp;
        }

        for (ImageAnnotation iaVersion : iacVersionHandler.getImageAnnotations()) {
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

//    private String getPreviousUID(ImageAnnotationCollection iac) {
//        if (iac.getImageAnnotations() == null || iac.getImageAnnotations().size() <= 0) {
//            return "";
//        }
//        ImageAnnotation ia = iac.getImageAnnotation();
//        if (ia.getPrecedentReferencedAnnotationUid() == null) {
//            return "";
//        }
//        return ia.getPrecedentReferencedAnnotationUid().getRoot();
//    }
//    private void setPreviousUID(ImageAnnotationCollection iac, String UID) {
//        if (iac.getImageAnnotations() == null || iac.getImageAnnotations().size() <= 0) {
//            return;
//        }
//        ImageAnnotation ia = iac.getImageAnnotation();
//        if (UID == null) {
//            ia.setPrecedentReferencedAnnotationUid(null);
//        } else {
//            ia.setPrecedentReferencedAnnotationUid(new II(UID));
//        }
//    }
//    private ImageAnnotationCollection getMaxVersionNumber(ImageAnnotationCollection iacCurrent) throws AimException {
//        int maxVersion = -1;
//        ImageAnnotationCollection res = null;
//        List<ImageAnnotationCollection> listAllVersions = getListAllVersions(iacCurrent);
//        if (listAllVersions.size() > 0) {
//            return listAllVersions.get(listAllVersions.size() - 1);
//        }
//        return res;
//    }
//    public ImageAnnotationCollection getCurrentVersion(ImageAnnotationCollection iac) throws AimException {
//        return AnnotationGetter.getImageAnnotationCollectionByUniqueIdentifier(serverURL, namespace, collection, dbUserName, dbUserPassword, iac.getUniqueIdentifier().getRoot());
//    }
//    public ImageAnnotationCollection getPreviousVersion(ImageAnnotationCollection iac) throws AimException {
////        if("comment 0".equals(iac.getImageAnnotation().getComment().getValue()))
////            iac= iac;
//        ImageAnnotationCollection iacCurrent = null;
//        if (iac.getVersion() != -1) {
//            iacCurrent = this.getCurrentVersion(iac);
//        } else {
//            iacCurrent = iac;
//        }
//        List<ImageAnnotationCollection> listVersions = this.getListAllVersions(iacCurrent);
//        String preUID = "";
//        if (iac.getImageAnnotation().getPrecedentReferencedAnnotationUid() != null && iac.getImageAnnotation().getPrecedentReferencedAnnotationUid().getRoot() != null) {
//            preUID = iac.getImageAnnotation().getPrecedentReferencedAnnotationUid().getRoot();
//        } else {
//            return null;
//        }
//        for (ImageAnnotationCollection temp : listVersions) {
//            if (temp.getImageAnnotation().getUniqueIdentifier().getRoot() == null ? preUID == null : temp.getImageAnnotation().getUniqueIdentifier().getRoot().equals(preUID)) {
//                ImageAnnotationCollection iacCurrentClone = iacCurrent.getClone();
//                iacCurrentClone.getImageAnnotations().clear();
//                iacCurrentClone.addImageAnnotation(temp.getImageAnnotation());
//                return iacCurrentClone;
//            }
//        }
//        return null;
//    }
//
//    public List<ImageAnnotationCollection> getPreviousVersions(ImageAnnotationCollection iac) throws AimException {
//        List<ImageAnnotationCollection> res = new ArrayList<>();
//        ImageAnnotationCollection preVersion = this.getPreviousVersion(iac);
//        while (preVersion != null) {
//            res.add(preVersion);
//            preVersion = this.getPreviousVersion(preVersion);
//        }
//        return res;
//    }
//    public List<ImageAnnotationCollection> performV3(ImageAnnotationCollection iac) throws AimException {
//        //Logger.write("=== performV3 = starting");
//        List<ImageAnnotationCollection> res = new ArrayList<>();
//        ImageAnnotation iaComming = iac.getImageAnnotation();
//        //***  setting required variables
//        ImageAnnotationCollection iacCurrentDB = AnnotationGetter.getImageAnnotationCollectionByUniqueIdentifier(serverURL, namespace, collection, dbUserName, dbUserPassword, iac.getUniqueIdentifier().getRoot());
//        ImageAnnotationCollection iacVersionHandlerDB = null;
//        String description = iac.getUniqueIdentifier().getRoot() + key;
//        List<ImageAnnotationCollection> listTemp = AnnotationGetter.getImageAnnotationCollectionByDescriptionEqual(serverURL, namespace, collection, dbUserName, dbUserPassword, description);
//        if (listTemp.size() > 0) {
//            iacVersionHandlerDB = listTemp.get(0);
//        }
//        //*** ia from current iac in the db
//        ImageAnnotation iaCurrentDB = null;
//        //*** ia from version handler iac in the db
//        ImageAnnotation iaVersionDB = null;
//        if (iacCurrentDB != null) {
//            //Logger.write("=== iacCurrentDB is not null");
//            if (iacCurrentDB.getImageAnnotation().getUniqueIdentifier().getRoot().equals(iaComming.getUniqueIdentifier().getRoot())) {
//                iaCurrentDB = iacCurrentDB.getImageAnnotation();
//            }
//
//        } else //Logger.write("=== iacCurrentDB is null ???");
//        //*** I couldn't fing the comming ia as the current ia, so I will shearch it in the version handler
//        if (iaCurrentDB == null && iacVersionHandlerDB != null) {
//            for (ImageAnnotation temp : iacVersionHandlerDB.getImageAnnotations()) {
//                if (temp.getUniqueIdentifier().getRoot().equals(iaComming.getUniqueIdentifier().getRoot())) {
//                    iaVersionDB = temp;
//                }
//            }
//        }
//
//        //Logger.write("=== iacVersionHandlerDB " + iacVersionHandlerDB);
//        //*** I found or not found the comming ia in the db.
//        //*** the comming iac is not exist in the db. 
//        //*** which means that, it is a new iac and we just save it.
//        if (iacCurrentDB == null) {
//            res.add(iac);
//        } //*** which means that, the iac exist in the db but it has no version before.
//        else if (iacCurrentDB != null && iacVersionHandlerDB == null) {
//            //Logger.write("=== performV3 = 1");
//            //*** so, i should see the comming ia in the iacCurrentDB.
//            //*** I assume that, user won't update any UID.
//            if (iaCurrentDB != null) {
//                // Logger.write("=== performV3 = 2");
//                if (!iaComming.isEqualTo(iaCurrentDB)) {
//                    //Logger.write("=== performV3 = 3");
//                    //*** firt, I should create a version handler for the iac
//                    //*** so, at this point. i already pushed the ia-db to version handler.
//                    ImageAnnotationCollection iacVersionHandler = iac.getClone();
//                    iacVersionHandler.refreshUniqueIdentifier();
//                    iacVersionHandler.setDescription(new ST(iac.getUniqueIdentifier().getRoot() + this.key));
//                    res.add(iacVersionHandler);
//                    iaComming.setPrecedentReferencedAnnotationUid(new II(iaCurrentDB.getUniqueIdentifier().getRoot()));
//                    iaComming.refreshUniqueIdentifier();
//                    res.add(iac);
//                } else {
//                    //*** the comming ia is not edited (same as ia in db)
//                    //*** so, we don't need to make anything.
//                }
//            } else {
//                //*** we have a problem...
//            }
//        }
//
//        return res;
//    }
    public ImageAnnotationCollection performV4(ImageAnnotationCollection iac) throws AimException {
        ImageAnnotationCollection iaC_comming = null;
        ImageAnnotation ia_comming = null;
        ImageAnnotationCollection iaC_db = null;
        ImageAnnotation ia_db = null;
        ImageAnnotationCollection iaC_version = null;
        ImageAnnotation ia_version = null;
        String description = iac.getUniqueIdentifier().getRoot() + Globals.flagVersion;

        List<ImageAnnotationCollection> res = new ArrayList<>();

        //*** initialization
        iaC_comming = iac;
        ia_comming = iaC_comming.getImageAnnotation();
        iaC_db = AnnotationGetter.getImageAnnotationCollectionByUniqueIdentifier(serverURL, namespace, collection, dbUserName, dbUserPassword, iac.getUniqueIdentifier().getRoot());
        if (iaC_db != null) {
            ia_db = iaC_db.getImageAnnotation();
            List<ImageAnnotationCollection> listTemp = AnnotationGetter.getImageAnnotationCollectionVersionHandler(serverURL, namespace, collection, dbUserName, dbUserPassword, description);
            if (listTemp.size() > 0) {
                iaC_version = listTemp.get(0);
                for (ImageAnnotation iaTemp : iaC_version.getImageAnnotations()) {
                    if (iaTemp.getUniqueIdentifier().getRoot().equals(ia_comming.getUniqueIdentifier().getRoot())) {
                        ia_version = iaTemp;
                        break;
                    }
                }
            }
        }
        
        String uid_ia_comming = "";
        String comment_ia_comming = "";
        String uid_ia_db = "";
        String comment_ia_db = "";
        String uid_ia_version= "";
        String comment_ia_version = "";
        
        if(ia_comming != null){
            uid_ia_comming = ia_comming.getUniqueIdentifier().getRoot();
            comment_ia_comming = ia_comming.getComment().getValue();
        }
        if(ia_db != null){
            uid_ia_db = ia_db.getUniqueIdentifier().getRoot();
            comment_ia_db = ia_db.getComment().getValue();
        }
        if(ia_version != null){
            uid_ia_version = ia_version.getUniqueIdentifier().getRoot();
            comment_ia_version = ia_version.getComment().getValue();
        }

        //*** decision
        if (iaC_db == null) {
            res.add(iaC_comming);
        } else if (ia_version != null) {
            String uidBeforeRefresh = ia_db.getUniqueIdentifier().getRoot();
            ia_db.refreshUniqueIdentifier();
            iaC_version.addImageAnnotation(ia_db.getClone());
            
            
//            ia_version.refreshUniqueIdentifier();
//            String uidAfterRefresh = ia_version.getUniqueIdentifier().getRoot();
            
            ia_comming.setUniqueIdentifier(new II(uidBeforeRefresh));
            ia_comming.setPrecedentReferencedAnnotationUid(ia_version.getUniqueIdentifier().getClone());
            iaC_db.getImageAnnotations().clear();
            iaC_db.addImageAnnotation(ia_comming);

//            for (ImageAnnotation ia : iaC_version.getImageAnnotations()) {
//                if (ia.getPrecedentReferencedAnnotationUid() != null && ia.getPrecedentReferencedAnnotationUid().getRoot().equals(uidBeforeRefresh)) {
//                    ia.setPrecedentReferencedAnnotationUid(new II(uidAfterRefresh));
//                }
//            }

            res.add(iaC_version);
            res.add(iaC_db);

        } else if (iaC_db != null && iaC_version == null) {
            if (ia_db.isEqualTo(ia_comming)) {
                return iaC_comming;
            }

            iaC_version = iaC_comming.getClone();
            iaC_version.refreshUniqueIdentifier();
            iaC_version.getImageAnnotations().clear();
            iaC_version.setDescription(new ST(description));

            ImageAnnotation ia_temp = ia_db.getClone();
            ia_temp.refreshUniqueIdentifier();
            ia_temp.setPrecedentReferencedAnnotationUid(null);
            iaC_version.addImageAnnotation(ia_temp);
            ia_comming.setPrecedentReferencedAnnotationUid(ia_temp.getUniqueIdentifier().getClone());
            Logger.write("====== ia_comming UID: " + ia_comming.getUniqueIdentifier().getRoot());
            ia_db = ia_comming.getClone();
            Logger.write("====== ia_db (clone of ia_comming) UID: " + ia_db.getUniqueIdentifier().getRoot());
            iaC_db.getImageAnnotations().clear();
            iaC_db.addImageAnnotation(ia_db);

            res.add(iaC_version);
            res.add(iaC_db);
            iaC_comming = iaC_db.getClone();
        } else if (iaC_db != null && iaC_version != null) {
            if (ia_db.isEqualTo(ia_comming)) {
                return iaC_comming;
            }

            ImageAnnotation ia_temp = ia_db.getClone();
            ia_temp.refreshUniqueIdentifier();
            iaC_version.addImageAnnotation(ia_temp);
            ia_comming.setPrecedentReferencedAnnotationUid(ia_temp.getUniqueIdentifier().getClone());
            ia_db = ia_comming.getClone();
            iaC_db.getImageAnnotations().clear();
            iaC_db.addImageAnnotation(ia_db);

            res.add(iaC_version);
            res.add(iaC_db);
        }

        if (iaC_version != null) {
            this.setVersionNumbers(iaC_db, iaC_version);
        }

        Logger.write("==== performV4-1");
        for (ImageAnnotationCollection temp : res) {
            II uid = temp.getImageAnnotation().getUniqueIdentifier();
            Logger.write("==== performV4-2: version:" + temp.getVersion());
            Logger.write(temp.getUniqueIdentifier().getRoot());

            AnnotationBuilder.performUploadExist(temp, serverURL, collection, "AIM_" + temp.getUniqueIdentifier().getRoot() + ".xml", Globals.getXSDPath(),
                    dbUserName, dbUserPassword);
            Logger.write("==== performV4-3");
            temp.getImageAnnotation().setUniqueIdentifier(uid);
            Logger.write("==== performV4-4");
        }

        Logger.write("==== performV4-5");
        return iaC_comming;
    }

    private void setVersionNumbers(ImageAnnotationCollection iaC_db, ImageAnnotationCollection iaC_version) {
        Logger.write("==== setVersionNumbers-1");
        iaC_db.getImageAnnotation().getAuditTrailCollection().getAuditTrailList().clear();
        Logger.write("==== setVersionNumbers-2");
        iaC_db.setVersion(-1);
        Logger.write("==== setVersionNumbers-3");

        int maxVersion = 0;
        for (ImageAnnotation ia_version : iaC_version.getImageAnnotations()) {
            if (ia_version.getVersion() > maxVersion) {
                Logger.write("==== setVersionNumbers-4");
                maxVersion = ia_version.getVersion();
            }
        }
        for (ImageAnnotation ia_version : iaC_version.getImageAnnotations()) {
            if (ia_version.getVersion() <= 0) {
                Logger.write("==== setVersionNumbers-5");
                AuditTrail auditTrail = new AuditTrail();
                auditTrail.setStatusCode(new CD("codeValue", "codeMeaning", "codingSchemeDesignator", "codingSchemeVersion"));
                auditTrail.setDateTime("01.01.1900");
                auditTrail.setChangeReason(new CD("codeValue", "codeMeaning", "codingSchemeDesignator", "codingSchemeVersion"));
                auditTrail.setComment(new ST(Integer.toString(maxVersion + 1)));
                ia_version.getAuditTrailCollection().addAuditTrail(auditTrail);
                ia_version.setVersion(maxVersion + 1);
                Logger.write("==== setVersionNumbers-6");
            }
        }
    }

    private boolean areTheyEqualExceptUID(ImageAnnotation ia_1, ImageAnnotation ia_2) {
        ImageAnnotation ia_1_Clone = ia_1.getClone();
        ImageAnnotation ia_2_Clone = ia_2.getClone();
        ia_1_Clone.setUniqueIdentifier(new II("none"));
        ia_2_Clone.setUniqueIdentifier(new II("none"));
        ia_1_Clone.setPrecedentReferencedAnnotationUid(null);
        ia_2_Clone.setPrecedentReferencedAnnotationUid(null);
        ia_1_Clone.setAuditTrailCollection(null);
        ia_2_Clone.setAuditTrailCollection(null);
        ia_1_Clone.setDateTime("");
        ia_2_Clone.setDateTime("");

        return ia_1_Clone.isEqualTo(ia_2_Clone);
    }

    private ImageAnnotationCollection getUndoReal(ImageAnnotationCollection iac) throws AimException {
       
        String uid_ia_Comming = "";
        String comment_ia_Comming = "";
        uid_ia_Comming = iac.getImageAnnotation().getUniqueIdentifier().getRoot();
        comment_ia_Comming = iac.getImageAnnotation().getComment().getValue();
            
            
        //String comment = iac.getImageAnnotation().getComment().getValue();
        ImageAnnotationCollection iaC_version = null;
        ImageAnnotation ia_Comming = iac.getImageAnnotation();
        String description = iac.getUniqueIdentifier().getRoot() + Globals.flagVersion;
        List<ImageAnnotationCollection> listTemp = AnnotationGetter.getImageAnnotationCollectionVersionHandler(serverURL, namespace, collection, dbUserName, dbUserPassword, description);
        if (listTemp.size() > 0) {
            iaC_version = listTemp.get(0);
        }

        if (iaC_version == null || iaC_version.getImageAnnotations().size() <= 0) {
            return null;
        }

        if (ia_Comming.getPrecedentReferencedAnnotationUid() == null || "".equals(ia_Comming.getPrecedentReferencedAnnotationUid().getRoot())) {
            return null;
        }

        for (ImageAnnotation ia : iaC_version.getImageAnnotations()) {
            if (ia.getUniqueIdentifier().getRoot().equals(ia_Comming.getPrecedentReferencedAnnotationUid().getRoot())) {
//                if (!this.areTheyEqualExceptUID(ia, ia_Comming)) {
                iac.getImageAnnotations().clear();
                iac.addImageAnnotation(ia);
                return iac;
//                } else {
//                    iac.getImageAnnotations().clear();
//                    iac.addImageAnnotation(ia);
//                    return getUndo(iac);
//                }
            }
        }
        return null;
    }

    public ImageAnnotationCollection getUndo(ImageAnnotationCollection iac) throws AimException {
        if (iac == null) {
            return null;
        }
        ImageAnnotation ia_Comming = iac.getImageAnnotation();
        ImageAnnotationCollection undoIAC = getUndoReal(iac);

        if (undoIAC != null) {
            ImageAnnotation undoIA = undoIAC.getImageAnnotation();

            if (undoIA.getUniqueIdentifier().getRoot().equals(ia_Comming.getPrecedentReferencedAnnotationUid().getRoot())) {
                if (!this.areTheyEqualExceptUID(undoIA, ia_Comming)) {
                    iac.getImageAnnotations().clear();
                    iac.addImageAnnotation(undoIA);
                    return iac;
                } else {
                    iac.getImageAnnotations().clear();
                    iac.addImageAnnotation(undoIA);
                    return getUndo(iac);
                }
            }
        }

        return null;
    }

    public List<ImageAnnotationCollection> getUndoList(ImageAnnotationCollection iac) throws AimException {
        List<ImageAnnotationCollection> res = new ArrayList<>();
        ImageAnnotationCollection preVersion = this.getUndo(iac);
        while (preVersion != null) {
            res.add(preVersion.getClone());
            preVersion = this.getUndo(preVersion);
        }
        return res;
    }

    public ImageAnnotationCollection getRedo(ImageAnnotationCollection iac) throws AimException {
        if (iac == null) {
            return null;
        }
        
        ImageAnnotationCollection iacUndoReal = getUndoReal(iac.getClone());
        
        
        String uid_ia_UndoReal= "";
        String comment_ia_UndoReal = "";
        String uid_ia_Comming= "";
        String comment_ia_Comming = "";
        if (iacUndoReal != null) {
            uid_ia_UndoReal = iacUndoReal.getImageAnnotation().getUniqueIdentifier().getRoot();
            comment_ia_UndoReal = iacUndoReal.getImageAnnotation().getComment().getValue();
        }
        uid_ia_Comming = iac.getImageAnnotation().getUniqueIdentifier().getRoot();
        comment_ia_Comming = iac.getImageAnnotation().getComment().getValue();
        
        if (iacUndoReal != null && this.areTheyEqualExceptUID(iac.getImageAnnotation(), iacUndoReal.getImageAnnotation())) {
            return getRedo(iacUndoReal);
        }

        ImageAnnotationCollection iaC_version = null;
        ImageAnnotation ia_Comming = iac.getImageAnnotation();
        String description = iac.getUniqueIdentifier().getRoot() + Globals.flagVersion;
        List<ImageAnnotationCollection> listTemp = AnnotationGetter.getImageAnnotationCollectionVersionHandler(serverURL, namespace, collection, dbUserName, dbUserPassword, description);
        if (listTemp.size() > 0) {
            iaC_version = listTemp.get(0);
        }

        if (iaC_version == null || iaC_version.getImageAnnotations().size() <= 0) {
            return null;
        }

        for (ImageAnnotation ia : iaC_version.getImageAnnotations()) {
            if (ia.getPrecedentReferencedAnnotationUid() == null || "".equals(ia.getPrecedentReferencedAnnotationUid().getRoot())) {
                continue;
            }
            if (ia_Comming.getUniqueIdentifier().getRoot().equals(ia.getPrecedentReferencedAnnotationUid().getRoot())) {
                if (!this.areTheyEqualExceptUID(ia, ia_Comming)) {
                    iac.getImageAnnotations().clear();
                    iac.addImageAnnotation(ia);
                    return iac;
                } else {
                    iac.getImageAnnotations().clear();
                    iac.addImageAnnotation(ia);
                    return getRedo(iac);
                }
            }
        }
        return null;
    }

    public List<ImageAnnotationCollection> getRedoList(ImageAnnotationCollection iac) throws AimException {
        List<ImageAnnotationCollection> res = new ArrayList<>();
        ImageAnnotationCollection preVersion = this.getRedo(iac);
        while (preVersion != null) {
            res.add(preVersion.getClone());
            preVersion = this.getRedo(preVersion);
        }
        return res;
    }

    public ImageAnnotationCollection makeCurrent(ImageAnnotationCollection iac) throws AimException {
        return this.performV4(iac);
    }
}

//        Date now = new Date();
//        DateTimeFormat dateFormat = DateTimeFormat.getFormat("MM.dd.yyyy hh:mm:ss");
//        dateFormat.format(now, createTimeZone(0));
