package edu.stanford.hakan.aim4api.project.epad;

//Copyright (c) 2013 The Board of Trustees of the Leland Stanford Junior University
//All rights reserved.
//
//Redistribution and use in source and binary forms, with or without modification, are permitted provided that 
//the following conditions are met:
//
//Redistributions of source code must retain the above copyright notice, this list of conditions and the following 
//disclaimer.
//
//Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the 
//following disclaimer in the documentation and/or other materials provided with the distribution.
//
//Neither the name of The Board of Trustees of the Leland Stanford Junior University nor the names of its 
//contributors (Daniel Rubin, et al) may be used to endorse or promote products derived from this software without 
//specific prior written permission.
//
//THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
//INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
//DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
//SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
//SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
//WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE 
//USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
import edu.stanford.hakan.aim4api.addition.AllowedTerm;
import edu.stanford.hakan.aim4api.addition.ValidTerm;
import edu.stanford.hakan.aim4api.compability.aimv3.AnatomicEntity;
import edu.stanford.hakan.aim4api.compability.aimv3.AnatomicEntityCharacteristic;
import edu.stanford.hakan.aim4api.compability.aimv3.CharacteristicQuantification;
import edu.stanford.hakan.aim4api.compability.aimv3.ImagingObservation;
import edu.stanford.hakan.aim4api.compability.aimv3.ImagingObservationCharacteristic;
import edu.stanford.hakan.aim4api.compability.aimv3.Inference;
import edu.stanford.hakan.aim4api.project.epad.Enumerations.ComponentType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Debra Willrett
 *
 */
@SuppressWarnings("serial")
public class Component implements Serializable {

    ComponentType componentType;
    AnatomicEntity anatomicEntity;
    ImagingObservation imagingObservation;
    Inference inference;
    AnatomicEntityCharacteristic anatomicEntityCharacteristic;
    ImagingObservationCharacteristic imagingObservationCharacteristic;

	//
    // anatomic entity
    //
    public Component(AnatomicEntity ae) {

        anatomicEntity = new AnatomicEntity();
        componentType = ComponentType.anatomicEntity;
        anatomicEntity.setAnatomicEntityCharacteristicCollection(ae
                .getAnatomicEntityCharacteristicCollection());
        anatomicEntity.setAnnotatorConfidence(ae.getAnnotatorConfidence());
        anatomicEntity.setCagridId(ae.getCagridId());
        anatomicEntity.setCodeMeaning(ae.getCodeMeaning());
        anatomicEntity.setCodeValue(ae.getCodeValue());
        anatomicEntity.setCodingSchemeDesignator(ae.getCodingSchemeVersion());
        anatomicEntity.setCodingSchemeVersion(ae.getCodingSchemeVersion());
        anatomicEntity.setIsPresent(ae.getIsPresent());
        anatomicEntity.setLabel(ae.getLabel());
    }

    public AnatomicEntityCharacteristic getAnatomicEntityCharacteristicItem(
            int j) {

        AnatomicEntityCharacteristic result = null;
        if (componentType == ComponentType.anatomicEntity) {
            result = anatomicEntity.getAnatomicEntityCharacteristicCollection()
                    .getAnatomicEntityCharacteristicList().get(j);
        }
        return result;
    }

	//
    // imaging observation
    //
    public Component(ImagingObservation io) {

        imagingObservation = new ImagingObservation();
        componentType = ComponentType.imagingObservation;
        imagingObservation.setImagingObservationCharacteristicCollection(io
                .getImagingObservationCharacteristicCollection());
        imagingObservation.setAnnotatorConfidence(io.getAnnotatorConfidence());
        imagingObservation.setCagridId(io.getCagridId());
        imagingObservation.setCodeMeaning(io.getCodeMeaning());
        imagingObservation.setCodeValue(io.getCodeValue());
        imagingObservation.setCodingSchemeDesignator(io
                .getCodingSchemeVersion());
        imagingObservation.setCodingSchemeVersion(io.getCodingSchemeVersion());
        imagingObservation.setIsPresent(io.getIsPresent());
        imagingObservation.setLabel(io.getLabel());
        imagingObservation.setListReferencedGeometricShape(io
                .getListReferencedGeometricShape());
    }

    public ImagingObservationCharacteristic getImagingObservationCharacteristicItem(
            int j) {

        ImagingObservationCharacteristic result = null;
        if (componentType == ComponentType.imagingObservation) {
            result = imagingObservation
                    .getImagingObservationCharacteristicCollection()
                    .getImagingObservationCharacteristicList().get(j);
        }
        return result;

    }

    public List<ImagingObservationCharacteristic> getImagingObservationCharacteristics() {

        List<ImagingObservationCharacteristic> result = null;
        if (componentType == ComponentType.imagingObservation) {
            result = imagingObservation
                    .getImagingObservationCharacteristicCollection()
                    .getImagingObservationCharacteristicList();
        }
        return result;
    }

	//
    // inference
    //
    public Component(Inference inf) {

        inference = new Inference();
        componentType = ComponentType.inference;

        inference.setAnnotatorConfidence(inf.getAnnotatorConfidence());
        inference.setCagridId(inf.getCagridId());
        inference.setCodeMeaning(inf.getCodeMeaning());
        inference.setCodeValue(inf.getCodeValue());
        inference.setCodingSchemeDesignator(inf.getCodingSchemeVersion());
        inference.setCodingSchemeVersion(inf.getCodingSchemeVersion());
        inference.setImageEvidence(inf.getImageEvidence());
    }

	//
    // anatomic entity characteristic
    //
    public Component(AnatomicEntityCharacteristic aec) {

        anatomicEntityCharacteristic = new AnatomicEntityCharacteristic();
        componentType = ComponentType.anatomicEntityCharacteristic;

        anatomicEntityCharacteristic.setAnnotatorConfidence(aec
                .getAnnotatorConfidence());
        anatomicEntityCharacteristic.setCagridId(aec.getCagridId());
        anatomicEntityCharacteristic.setCodeMeaning(aec.getCodeMeaning());
        anatomicEntityCharacteristic.setCodeValue(aec.getCodeValue());
        anatomicEntityCharacteristic.setCodingSchemeDesignator(aec
                .getCodingSchemeVersion());
        anatomicEntityCharacteristic.setCodingSchemeVersion(aec
                .getCodingSchemeVersion());
        anatomicEntityCharacteristic.setLabel(aec.getLabel());

    }

	//
    // imaging observation characteristic
    //
    public Component(ImagingObservationCharacteristic ioc) {

        imagingObservationCharacteristic = new ImagingObservationCharacteristic();
        componentType = ComponentType.imagingObservationCharacteristic;

        imagingObservationCharacteristic.setAnnotatorConfidence(ioc
                .getAnnotatorConfidence());
        imagingObservationCharacteristic.setCagridId(ioc.getCagridId());
        imagingObservationCharacteristic.setCodeMeaning(ioc.getCodeMeaning());
        imagingObservationCharacteristic.setCodeValue(ioc.getCodeValue());
        imagingObservationCharacteristic.setCodingSchemeDesignator(ioc
                .getCodingSchemeVersion());
        imagingObservationCharacteristic.setCodingSchemeVersion(ioc
                .getCodingSchemeVersion());
        imagingObservationCharacteristic.setLabel(ioc.getLabel());
        imagingObservationCharacteristic.setComment(ioc.getComment());
        imagingObservationCharacteristic
                .setCharacteristicQuantificationCollection(ioc
                        .getCharacteristicQuantificationCollection());
    }

    public Component(ComponentType componentType) {

        this.componentType = componentType;

        switch (componentType) {
            case anatomicEntity:
                anatomicEntity = new AnatomicEntity();
                break;

            case imagingObservation:
                imagingObservation = new ImagingObservation();
                break;

            case inference:
                inference = new Inference();
                break;

            case anatomicEntityCharacteristic:
                anatomicEntityCharacteristic = new AnatomicEntityCharacteristic();
                break;

            case imagingObservationCharacteristic:
                imagingObservationCharacteristic = new ImagingObservationCharacteristic();
                break;
        }
    }

    public void setCagridId(int cagridId) {
        switch (componentType) {
            case anatomicEntity:
                anatomicEntity.setCagridId(cagridId);
                break;

            case imagingObservation:
                imagingObservation.setCagridId(cagridId);
                break;

            case inference:
                inference.setCagridId(cagridId);
                break;

            case anatomicEntityCharacteristic:
                anatomicEntityCharacteristic.setCagridId(cagridId);
                break;

            case imagingObservationCharacteristic:
                imagingObservationCharacteristic.setCagridId(cagridId);
                break;
        }
    }

    public void setLabel(String label) {
        switch (componentType) {
            case anatomicEntity:
                anatomicEntity.setLabel(label);
                break;

            case imagingObservation:
                imagingObservation.setLabel(label);
                break;

            case anatomicEntityCharacteristic:
                anatomicEntityCharacteristic.setLabel(label);
                break;

            case imagingObservationCharacteristic:
                imagingObservationCharacteristic.setLabel(label);
                break;
            default:
                break;
        }
    }

    public void setAnnotatorConfidence(Double confidence) {
        switch (componentType) {
            case anatomicEntity:
                anatomicEntity.setAnnotatorConfidence(confidence);
                break;

            case imagingObservation:
                imagingObservation.setAnnotatorConfidence(confidence);
                break;

            case inference:
                inference.setAnnotatorConfidence(confidence);
                break;

            case anatomicEntityCharacteristic:
                anatomicEntityCharacteristic.setAnnotatorConfidence(confidence);
                break;

            case imagingObservationCharacteristic:
                imagingObservationCharacteristic.setAnnotatorConfidence(confidence);
                break;
        }
    }

    public void setCodeValue(String codeValue) {
        switch (componentType) {
            case anatomicEntity:
                anatomicEntity.setCodeValue(codeValue);
                break;

            case imagingObservation:
                imagingObservation.setCodeValue(codeValue);
                break;

            case inference:
                inference.setCodeValue(codeValue);
                break;

            case anatomicEntityCharacteristic:
                anatomicEntityCharacteristic.setCodeValue(codeValue);
                break;

            case imagingObservationCharacteristic:
                imagingObservationCharacteristic.setCodeValue(codeValue);
                break;
        }
    }

    public void setCodeMeaning(String codeMeaning) {
        switch (componentType) {
            case anatomicEntity:
                anatomicEntity.setCodeMeaning(codeMeaning);
                break;

            case imagingObservation:
                imagingObservation.setCodeMeaning(codeMeaning);
                break;

            case inference:
                inference.setCodeMeaning(codeMeaning);
                break;

            case anatomicEntityCharacteristic:
                anatomicEntityCharacteristic.setCodeMeaning(codeMeaning);
                break;

            case imagingObservationCharacteristic:
                imagingObservationCharacteristic.setCodeMeaning(codeMeaning);
                break;
        }
    }

    public void setCodingSchemeDesignator(String codingSchemeDesignator) {
        switch (componentType) {
            case anatomicEntity:
                anatomicEntity.setCodingSchemeDesignator(codingSchemeDesignator);
                break;

            case imagingObservation:
                imagingObservation
                        .setCodingSchemeDesignator(codingSchemeDesignator);
                break;

            case inference:
                inference.setCodingSchemeDesignator(codingSchemeDesignator);
                break;

            case anatomicEntityCharacteristic:
                anatomicEntityCharacteristic
                        .setCodingSchemeDesignator(codingSchemeDesignator);
                break;

            case imagingObservationCharacteristic:
                imagingObservationCharacteristic
                        .setCodingSchemeDesignator(codingSchemeDesignator);
                break;
        }
    }

    public void setImageEvidence(boolean evidence) {
        switch (componentType) {

            case inference:
                inference.setImageEvidence(evidence);
                break;
            default:
                break;

        }

    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public boolean hasCharacteristics() {
        boolean result = false;
        switch (componentType) {
            case anatomicEntity:
            case imagingObservation:
                result = true;
                break;
            default:
                break;
        }
        return result;
    }

    public String getLabel() {
        String result = "";
        switch (componentType) {
            case anatomicEntity:
                result = anatomicEntity.getLabel();
                break;

            case imagingObservation:
                result = imagingObservation.getLabel();
                break;

            case anatomicEntityCharacteristic:
                result = anatomicEntityCharacteristic.getLabel();
                break;

            case imagingObservationCharacteristic:
                result = imagingObservationCharacteristic.getLabel();
                break;

            case inference:
                break;
        }
        return result;
    }

    public Double getAnnotatorConfidence() {
        Double result = 0.0;
        switch (componentType) {
            case anatomicEntity:
                result = anatomicEntity.getAnnotatorConfidence();
                break;

            case imagingObservation:
                result = imagingObservation.getAnnotatorConfidence();
                break;

            case inference:
                result = inference.getAnnotatorConfidence();
                break;

            case anatomicEntityCharacteristic:
                result = anatomicEntityCharacteristic.getAnnotatorConfidence();
                break;

            case imagingObservationCharacteristic:
                result = imagingObservationCharacteristic.getAnnotatorConfidence();
                break;
        }
        return result;
    }

    public String getCodeValue() {
        String result = "";
        switch (componentType) {
            case anatomicEntity:
                result = anatomicEntity.getCodeValue();
                break;

            case imagingObservation:
                result = imagingObservation.getCodeValue();
                break;

            case inference:
                result = inference.getCodeValue();
                break;

            case anatomicEntityCharacteristic:
                result = anatomicEntityCharacteristic.getCodeValue();
                break;

            case imagingObservationCharacteristic:
                result = imagingObservationCharacteristic.getCodeValue();
                break;
        }
        return result;
    }

    public void addCharacteristic(
            edu.stanford.hakan.aim4api.project.epad.Component characteristic) {
        switch (componentType) {
            case anatomicEntity:
                anatomicEntity.getAnatomicEntityCharacteristicCollection()
                        .AddAnatomicEntityCharacteristic(
                                characteristic.anatomicEntityCharacteristic);
                break;

            case imagingObservation:
                imagingObservation.getImagingObservationCharacteristicCollection()
                        .AddImagingObservationCharacteristic(
                                characteristic.imagingObservationCharacteristic);
                break;
            default:
                break;
        }
    }

    public List<Component> getCharacteristics() {
        List<Component> result = new ArrayList<Component>();
        switch (componentType) {
            case anatomicEntity:

                for (AnatomicEntityCharacteristic characteristic : anatomicEntity
                        .getAnatomicEntityCharacteristicCollection()
                        .getAnatomicEntityCharacteristicList()) {
                    result.add(new Component(characteristic));
                }
                break;

            case imagingObservation:
                for (ImagingObservationCharacteristic characteristic : imagingObservation
                        .getImagingObservationCharacteristicCollection()
                        .getImagingObservationCharacteristicList()) {
                    result.add(new Component(characteristic));
                }
                break;
            default:
                break;
        }
        return result;
    }

    public List<Quantifier> getQuantifiers() {
        List<Quantifier> result = new ArrayList<Quantifier>();
        switch (componentType) {
            case anatomicEntityCharacteristic:

                for (CharacteristicQuantification characteristicQuantification : anatomicEntityCharacteristic
                        .getCharacteristicQuantificationCollection()
                        .getCharacteristicQuantificationList()) {
                    result.add(new Quantifier(characteristicQuantification));
                }
                break;

            case imagingObservationCharacteristic:
                for (CharacteristicQuantification characteristicQuantification : imagingObservationCharacteristic
                        .getCharacteristicQuantificationCollection()
                        .getCharacteristicQuantificationList()) {
                    result.add(new Quantifier(characteristicQuantification));
                }
                break;
            default:
                break;
        }
        return result;
    }

    public void addQuantifier(Quantifier quantifier) {
        switch (componentType) {
            case anatomicEntityCharacteristic:
                switch (quantifier.quantifierType) {
                    case numerical:
                        anatomicEntityCharacteristic
                                .addCharacteristicQuantification(quantifier.interval);
                        break;
                    case quantile:
                        anatomicEntityCharacteristic
                                .addCharacteristicQuantification(quantifier.quantile);
                        break;
                    case scale:
                        anatomicEntityCharacteristic
                                .addCharacteristicQuantification(quantifier.scale);
                        break;
                    case interval:
                        anatomicEntityCharacteristic
                                .addCharacteristicQuantification(quantifier.interval);
                        break;
                    case nonQuantifiable:
                        anatomicEntityCharacteristic
                                .addCharacteristicQuantification(quantifier.nonQuantifiable);
                        break;
                }
                break;

            case imagingObservationCharacteristic:
                switch (quantifier.quantifierType) {
                    case numerical:
                        imagingObservationCharacteristic
                                .addCharacteristicQuantification(quantifier.interval);
                        break;
                    case quantile:
                        imagingObservationCharacteristic
                                .addCharacteristicQuantification(quantifier.quantile);
                        break;
                    case scale:
                        imagingObservationCharacteristic
                                .addCharacteristicQuantification(quantifier.scale);
                        break;
                    case interval:
                        imagingObservationCharacteristic
                                .addCharacteristicQuantification(quantifier.interval);
                        break;
                    case nonQuantifiable:
                        imagingObservationCharacteristic
                                .addCharacteristicQuantification(quantifier.nonQuantifiable);
                        break;
                }
                break;
            default:
                break;
        }
    }

    public void setAllowedTerm(AllowedTerm term) {
        switch (componentType) {
            case anatomicEntity:
                anatomicEntity.setAllowedTerm(term);
                break;
            case imagingObservation:
                imagingObservation.setAllowedTerm(term);
                break;
            case inference:
                inference.setAllowedTerm(term);
                break;
            case anatomicEntityCharacteristic:
                anatomicEntityCharacteristic.setAllowedTerm(term);
                break;
            case imagingObservationCharacteristic:
                imagingObservationCharacteristic.setAllowedTerm(term);
                break;
        }
    }
}
