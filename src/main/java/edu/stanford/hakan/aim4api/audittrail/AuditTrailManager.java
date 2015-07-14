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
        List<ImageAnnotationCollection> listRes = AnnotationGetter.getImageAnnotationCollectionVersionHandler(serverURL, namespace, collection, dbUserName, dbUserPassword, description);
        ImageAnnotationCollection iacVersion = null;
        if (listRes.size() > 0) {
            iacVersion = listRes.get(0);
        }
        return iacVersion;
    }

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
//        if(ia_comming.getComment() == null)
//        {
//            ia_comming.setComment(new ST(""));
//            ia_comming.getInitialState().setComment(new ST(""));
//        }
//        ia_comming.setComment(new ST(new STUser(ia_comming.getComment(),iaC_comming.getUser()).toString()));
//        ia_comming.getInitialState().setComment(new ST(new STUser( ia_comming.getInitialState().getComment(),iaC_comming.getUser()).toString()));
        String comment_1 = ia_comming.getComment().getValue();
        String comment_2 = ia_comming.getInitialState().getComment().getValue();      

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
            ia_db = ia_comming.getClone();
            iaC_db.getImageAnnotations().clear();
            iaC_db.addImageAnnotation(ia_db);

            String comment_iaC_version = iaC_version.getImageAnnotation().getComment().getValue();
            String comment_iaC_db = iaC_db.getImageAnnotation().getComment().getValue();
            String userName_iaC_version = iaC_version.getUser().getName().getValue();
            String userName_iaC_db = iaC_db.getUser().getName().getValue();
            iaC_version.getImageAnnotation().setComment(new ST(new STUser(iaC_version.getImageAnnotation().getComment(),iaC_version.getUser()).toString()));
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

        for (ImageAnnotationCollection temp : res) {
            II uid = temp.getImageAnnotation().getUniqueIdentifier();
            AnnotationBuilder.performUploadExist(temp, serverURL, collection, "AIM_" + temp.getUniqueIdentifier().getRoot() + ".xml", Globals.getXSDPath(),
                    dbUserName, dbUserPassword);
            temp.getImageAnnotation().setUniqueIdentifier(uid);
        }
        return iaC_comming;
    }

    private void setVersionNumbers(ImageAnnotationCollection iaC_db, ImageAnnotationCollection iaC_version) {
        iaC_db.getImageAnnotation().getAuditTrailCollection().getAuditTrailList().clear();
        iaC_db.setVersion(-1);

        int maxVersion = 0;
        for (ImageAnnotation ia_version : iaC_version.getImageAnnotations()) {
            if (ia_version.getVersion() > maxVersion) {
                maxVersion = ia_version.getVersion();
            }
        }
        for (ImageAnnotation ia_version : iaC_version.getImageAnnotations()) {
            if (ia_version.getVersion() <= 0) {
                AuditTrail auditTrail = new AuditTrail();
                auditTrail.setStatusCode(new CD("codeValue", "codeMeaning", "codingSchemeDesignator", "codingSchemeVersion"));
                auditTrail.setDateTime("01.01.1900");
                auditTrail.setChangeReason(new CD("codeValue", "codeMeaning", "codingSchemeDesignator", "codingSchemeVersion"));
                auditTrail.setComment(new ST(Integer.toString(maxVersion + 1)));
                ia_version.getAuditTrailCollection().addAuditTrail(auditTrail);
                ia_version.setVersion(maxVersion + 1);
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
