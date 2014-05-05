/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class AnatomicEntityCharacteristic extends ImagingPhysicalEntityCharacteristic {

    @Override
    public void setTagName(String tagName) {
        super.setTagName(tagName);
    }

    @Override
    public AnatomicEntityCharacteristic getClone() {        
       return (AnatomicEntityCharacteristic) super.getClone();
        
//        AnatomicEntityCharacteristic res = new AnatomicEntityCharacteristic();
//        for (int i = 0; i < this.getListTypeCode().size(); i++) {
//            if (this.getListTypeCode().get(i) != null) {
//                res.addTypeCode(this.getListTypeCode().get(i).getClone());
//            }
//        }
//        for (int i = 0; i < this.getListQuestionTypeCode().size(); i++) {
//            if (this.getListQuestionTypeCode().get(i) != null) {
//                res.addQuestionTypeCode(this.getListQuestionTypeCode().get(i).getClone());
//            }
//        }
//        if (this.getAnnotatorConfidence() != null) {
//            res.setAnnotatorConfidence(this.getAnnotatorConfidence());
//        }
//        if (this.getLabel() != null) {
//            res.setLabel(this.getLabel().getClone());
//        }
//        if (this.getQuestionIndex() != null) {
//            res.setQuestionIndex(this.getQuestionIndex());
//        }
//        if (this.getComment() != null) {
//            res.setComment(this.getComment().getClone());
//        }
//        if (this.getCharacteristicQuantificationCollection() != null) {
//            res.setCharacteristicQuantificationCollection(this.getCharacteristicQuantificationCollection().getClone());
//        }
//        if (this.getTagName() != null) {
//            res.setTagName(this.getTagName());
//        }
//        return res;
    }

    public CharacteristicQuantificationCollection getCharacteristicQuantificationCollectionEpad() {
        return (CharacteristicQuantificationCollection) super.getCharacteristicQuantificationCollection();
    }



    public List<CharacteristicQuantification> getQuantificationList() {
        return getCharacteristicQuantificationCollectionEpad().getCharacteristicQuantificationListEpad();

    }
}
