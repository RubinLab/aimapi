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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import edu.stanford.hakan.aim4api.addition.AllowedTerm;
import edu.stanford.hakan.aim4api.addition.ValidTerm;
import edu.stanford.hakan.aim4api.base.CD;
import edu.stanford.hakan.aim4api.base.CharacteristicQuantification;
import edu.stanford.hakan.aim4api.base.ImagingObservationCharacteristic;
import edu.stanford.hakan.aim4api.base.ImagingObservationEntity;
import edu.stanford.hakan.aim4api.base.ImagingPhysicalEntity;
import edu.stanford.hakan.aim4api.base.ImagingPhysicalEntityCharacteristic;
import edu.stanford.hakan.aim4api.base.InferenceEntity;
import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.project.epad.Enumerations.ComponentType;

/**
 * 
 * @author Debra Willrett
 * 
 */
@SuppressWarnings("serial")
public class Component implements Serializable {

	ComponentType componentType;
	ImagingPhysicalEntity anatomicEntity;
	ImagingObservationEntity imagingObservation;
	InferenceEntity inference;
	ImagingPhysicalEntityCharacteristic anatomicEntityCharacteristic;
	ImagingObservationCharacteristic imagingObservationCharacteristic;

	private static final Logger logger = Logger.getLogger("Component");

	//
	// anatomic entity
	//
	public Component(ImagingPhysicalEntity ae) {

		componentType = ComponentType.anatomicEntity;
		
		anatomicEntity = new ImagingPhysicalEntity();
		anatomicEntity.setImagingPhysicalEntityCharacteristicCollection(ae
				.getImagingPhysicalEntityCharacteristicCollection());
		anatomicEntity.setAnnotatorConfidence(ae.getAnnotatorConfidence());
//		anatomicEntity.setCagridId(ae.getCagridId());

		if (ae.getListQuestionTypeCode()!=null){
			anatomicEntity.setQuestionTypeCode(ae.getListQuestionTypeCode());
		}
		if (ae.getListTypeCode()!=null){
			anatomicEntity.setTypeCode(ae.getListTypeCode());
		}
		anatomicEntity.setIsPresent(ae.getIsPresent());
		anatomicEntity.setLabel(ae.getLabel());
	}

	public ImagingPhysicalEntityCharacteristic getAnatomicEntityCharacteristicItem(
			int j) {

		ImagingPhysicalEntityCharacteristic result = null;
		if (componentType == ComponentType.anatomicEntity) {
			result = anatomicEntity.getImagingPhysicalEntityCharacteristicCollection()
					.getImagingPhysicalEntityCharacteristicList().get(j);
		}
		return result;
	}

	//
	// imaging observation
	//
	public Component(ImagingObservationEntity io) {
		componentType = ComponentType.imagingObservation;

		imagingObservation = new ImagingObservationEntity();
		imagingObservation.setImagingObservationCharacteristicCollection(io
				.getImagingObservationCharacteristicCollection());
		imagingObservation.setAnnotatorConfidence(io.getAnnotatorConfidence());
//		imagingObservation.setCagridId(io.getCagridId());
		if (io.getListQuestionTypeCode()!=null){
			imagingObservation.setQuestionTypeCode(io.getListQuestionTypeCode());
		}
		if (io.getListTypeCode()!=null){
			imagingObservation.setTypeCode(io.getListTypeCode());
		}
		imagingObservation.setIsPresent(io.getIsPresent());
		imagingObservation.setLabel(io.getLabel());
		//TODO needs ImagingObservationEntityIsIdentifiedByGeometricShapeEntityStatement addition??
//		imagingObservation.setListReferencedGeometricShape(io
//				.getListReferencedGeometricShape());
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
	public Component(InferenceEntity inf) {
		
		componentType = ComponentType.inference;

		inference = new InferenceEntity();
		

		inference.setAnnotatorConfidence(inf.getAnnotatorConfidence());
//		inference.setCagridId(inf.getCagridId());
		if (inf.getListQuestionTypeCode()!=null){
			inference.setQuestionTypeCode(inf.getListQuestionTypeCode());
		}
		if (inf.getListTypeCode()!=null){
			inference.setTypeCode(inf.getListTypeCode());
		}
		inference.setImageEvidence(inf.getImageEvidence());
	}

	//
	// anatomic entity characteristic
	//
	public Component(ImagingPhysicalEntityCharacteristic aec) {

		componentType = ComponentType.anatomicEntityCharacteristic;
		
		anatomicEntityCharacteristic = new ImagingPhysicalEntityCharacteristic();

		anatomicEntityCharacteristic.setAnnotatorConfidence(aec
				.getAnnotatorConfidence());
//		anatomicEntityCharacteristic.setCagridId(aec.getCagridId());
		if (aec.getListQuestionTypeCode()!=null){
			anatomicEntityCharacteristic.setQuestionTypeCode(aec.getListQuestionTypeCode());
		}
		if (aec.getListTypeCode()!=null){
			anatomicEntityCharacteristic.setTypeCode(aec.getListTypeCode());
		}

		anatomicEntityCharacteristic.setLabel(aec.getLabel());

	}

	//
	// imaging observation characteristic
	//
	public Component(ImagingObservationCharacteristic ioc) {

		componentType = ComponentType.imagingObservationCharacteristic;
		
		imagingObservationCharacteristic = new ImagingObservationCharacteristic();

		imagingObservationCharacteristic.setAnnotatorConfidence(ioc
				.getAnnotatorConfidence());
//		imagingObservationCharacteristic.setCagridId(ioc.getCagridId());
		if (ioc.getListQuestionTypeCode()!=null){
			imagingObservationCharacteristic.setQuestionTypeCode(ioc.getListQuestionTypeCode());
		}
		if (ioc.getListTypeCode()!=null){
			imagingObservationCharacteristic.setTypeCode(ioc.getListTypeCode());
		}
		imagingObservationCharacteristic.setLabel(ioc.getLabel());
		if (ioc.getComment()!=null) imagingObservationCharacteristic.setComment(ioc.getComment());
		imagingObservationCharacteristic
				.setCharacteristicQuantificationCollection(ioc
						.getCharacteristicQuantificationCollection());
	}

	public Component(ComponentType componentType) {

		this.componentType = componentType;

		switch (componentType) {
		case anatomicEntity:
			anatomicEntity = new ImagingPhysicalEntity();
			break;

		case imagingObservation:
			imagingObservation = new ImagingObservationEntity();
			break;

		case inference:
			inference = new InferenceEntity();
			break;

		case anatomicEntityCharacteristic:
			anatomicEntityCharacteristic = new ImagingPhysicalEntityCharacteristic();
			break;

		case imagingObservationCharacteristic:
			imagingObservationCharacteristic = new ImagingObservationCharacteristic();
			break;
		}
	}

	public void setCagridId(int cagridId) {
		logger.warning("no cagriid in v4");
//		switch (componentType) {
//		case anatomicEntity:
//			anatomicEntity.setCagridId(cagridId);
//			break;
//
//		case imagingObservation:
//			imagingObservation.setCagridId(cagridId);
//			break;
//
//		case inference:
//			inference.setCagridId(cagridId);
//			break;
//
//		case anatomicEntityCharacteristic:
//			anatomicEntityCharacteristic.setCagridId(cagridId);
//			break;
//
//		case imagingObservationCharacteristic:
//			imagingObservationCharacteristic.setCagridId(cagridId);
//			break;
//		}
	}

	public void setLabel(String label) {
		switch (componentType) {
		case anatomicEntity:
			anatomicEntity.setLabel(new ST(label));
			break;

		case imagingObservation:
			imagingObservation.setLabel(new ST(label));
			break;

		case anatomicEntityCharacteristic:
			anatomicEntityCharacteristic.setLabel(new ST(label));
			break;

		case imagingObservationCharacteristic:
			imagingObservationCharacteristic.setLabel(new ST(label));
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
	
	public void addTypeCode(CD tc) {
		switch (componentType) {
		case anatomicEntity:
			anatomicEntity.addTypeCode(tc);
			break;

		case imagingObservation:
			imagingObservation.addTypeCode(tc);
			break;

		case inference:
			inference.addTypeCode(tc);
			break;

		case anatomicEntityCharacteristic:
			anatomicEntityCharacteristic.addTypeCode(tc);
			break;

		case imagingObservationCharacteristic:
			imagingObservationCharacteristic.addTypeCode(tc);
			break;
		}
	}

//	public void setCodeValue(String codeValue) {
//		switch (componentType) {
//		case anatomicEntity:
//			anatomicEntity.setCodeValue(codeValue);
//			break;
//
//		case imagingObservation:
//			imagingObservation.setCodeValue(codeValue);
//			break;
//
//		case inference:
//			inference.setCodeValue(codeValue);
//			break;
//
//		case anatomicEntityCharacteristic:
//			anatomicEntityCharacteristic.setCodeValue(codeValue);
//			break;
//
//		case imagingObservationCharacteristic:
//			imagingObservationCharacteristic.setCodeValue(codeValue);
//			break;
//		}
//	}
//
//	public void setCodeMeaning(String codeMeaning) {
//		switch (componentType) {
//		case anatomicEntity:
//			anatomicEntity.setCodeMeaning(codeMeaning);
//			break;
//
//		case imagingObservation:
//			imagingObservation.setCodeMeaning(codeMeaning);
//			break;
//
//		case inference:
//			inference.setCodeMeaning(codeMeaning);
//			break;
//
//		case anatomicEntityCharacteristic:
//			anatomicEntityCharacteristic.setCodeMeaning(codeMeaning);
//			break;
//
//		case imagingObservationCharacteristic:
//			imagingObservationCharacteristic.setCodeMeaning(codeMeaning);
//			break;
//		}
//	}
//
//	public void setCodingSchemeDesignator(String codingSchemeDesignator) {
//		switch (componentType) {
//		case anatomicEntity:
//			anatomicEntity.setCodingSchemeDesignator(codingSchemeDesignator);
//			break;
//
//		case imagingObservation:
//			imagingObservation
//					.setCodingSchemeDesignator(codingSchemeDesignator);
//			break;
//
//		case inference:
//			inference.setCodingSchemeDesignator(codingSchemeDesignator);
//			break;
//
//		case anatomicEntityCharacteristic:
//			anatomicEntityCharacteristic
//					.setCodingSchemeDesignator(codingSchemeDesignator);
//			break;
//
//		case imagingObservationCharacteristic:
//			imagingObservationCharacteristic
//					.setCodingSchemeDesignator(codingSchemeDesignator);
//			break;
//		}
//	}

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

	public String getCoordinatedCodeValue() {
		String result = "";
		switch (componentType) {
		case anatomicEntity:
			result = getCoordinatedValue(anatomicEntity.getListTypeCode());
			break;

		case imagingObservation:
			result = getCoordinatedValue(imagingObservation.getListTypeCode());
			break;

		case inference:
			result = getCoordinatedValue(inference.getListTypeCode());
			break;

		case anatomicEntityCharacteristic:
			result = getCoordinatedValue(
					anatomicEntityCharacteristic.getListTypeCode());
			break;

		case imagingObservationCharacteristic:
			result = getCoordinatedValue(
					imagingObservationCharacteristic.getListTypeCode());
			break;
		}
		return result;
	}

	private String getCoordinatedValue(List<CD> typeCodes) {
		String result="";
		if (typeCodes==null)
			return "";
		if (typeCodes.size()==1){
			result =typeCodes.get(0).getCode();
		}
		if (typeCodes.size()>1){
			result="";
			for (int i = 1; i < typeCodes.size(); i++) {
				result += " " + typeCodes.get(i).getCode();
	
			}
		}
		return result.trim();
	}
	
	private String getCoordinatedMeaning(List<CD> typeCodes) {
		String result="";
		if (typeCodes==null)
			return "";
		if (typeCodes.size()==1){
			result =typeCodes.get(0).getDisplayName().getValue();
		}
		if (typeCodes.size()>1){
			result="";
			for (int i = 1; i < typeCodes.size(); i++) {
				result += typeCodes.get(i).getDisplayName().getValue() + " ";
	
			}
		}
		return result.trim();
	}
	
//	private String getCoordinatedValue(String codeValue, AllowedTerm term) {
//		String result = codeValue;
//		if (term != null) {
//			result = term.getCodeValue();
//			if (term.getListValidTerm() != null) {
//				for (ValidTerm validTerm : term.getListValidTerm()) {
//					result += " " + validTerm.getCodeValue();
//				}
//			}
//		}
//		return result;
//	}
//
//	private String getCoordinatedMeaning(String codeMeaning, AllowedTerm term) {
//		String result = codeMeaning;
//		if (term != null) {
//			result = term.getCodeMeaning();
//			if (term.getListValidTerm() != null) {
//				for (ValidTerm validTerm : term.getListValidTerm()) {
//					result += " " + validTerm.getCodeMeaning();
//				}
//			}
//		}
//		return result;
//	}

	public String getCoordinatedCodeMeaning() {
		String result = "";
		switch (componentType) {
		case anatomicEntity:
			result = getCoordinatedMeaning(anatomicEntity.getListTypeCode());
			break;

		case imagingObservation:
			result = getCoordinatedMeaning(imagingObservation.getListTypeCode());
			break;

		case inference:
			result = getCoordinatedMeaning(inference.getListTypeCode());
			break;

		case anatomicEntityCharacteristic:
			result = getCoordinatedMeaning(
					anatomicEntityCharacteristic.getListTypeCode());
			break;

		case imagingObservationCharacteristic:
			result = getCoordinatedMeaning(
					imagingObservationCharacteristic.getListTypeCode());
			break;
		}
		return result;
	}

	public String getLabel() {
		String result = "";
		switch (componentType) {
		case anatomicEntity:
			result = anatomicEntity.getLabel().getValue();
			break;

		case imagingObservation:
			result = imagingObservation.getLabel().getValue();
			break;

		case anatomicEntityCharacteristic:
			result = anatomicEntityCharacteristic.getLabel().getValue();
			break;

		case imagingObservationCharacteristic:
			result = imagingObservationCharacteristic.getLabel().getValue();
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
		//TODO gets the first type issue??
		String result = "";
		switch (componentType) {
		case anatomicEntity:
			result = anatomicEntity.getListTypeCode().get(0).getCode();
			break;

		case imagingObservation:
			result = imagingObservation.getListTypeCode().get(0).getCode();
			break;

		case inference:
			result = inference.getListTypeCode().get(0).getCode();
			break;

		case anatomicEntityCharacteristic:
			result = anatomicEntityCharacteristic.getListTypeCode().get(0).getCode();
			break;

		case imagingObservationCharacteristic:
			result = imagingObservationCharacteristic.getListTypeCode().get(0).getCode();
			break;
		}
		return result;
	}

	public void addCharacteristic(Component characteristic) {
		switch (componentType) {
		case anatomicEntity:
			anatomicEntity.getImagingPhysicalEntityCharacteristicCollection()
					.addImagingPhysicalEntityCharacteristic(
							characteristic.anatomicEntityCharacteristic);

			break;

		case imagingObservation:
			imagingObservation.getImagingObservationCharacteristicCollection()
					.addImagingObservationCharacteristic(
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

			for (ImagingPhysicalEntityCharacteristic characteristic : anatomicEntity
					.getImagingPhysicalEntityCharacteristicCollection()
					.getImagingPhysicalEntityCharacteristicList()) {
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
		if (term==null)
			return;
		switch (componentType) {
		case anatomicEntity:
			anatomicEntity.getListTypeCode().clear();
			anatomicEntity.addTypeCode(term.toCD());
            for (ValidTerm validTerm : term.getListValidTerm()) {
            	anatomicEntity.addTypeCode(validTerm.toCD());
            }
	        
			break;
		case imagingObservation:
			imagingObservation.getListTypeCode().clear();
			imagingObservation.addTypeCode(term.toCD());
            for (ValidTerm validTerm : term.getListValidTerm()) {
            	imagingObservation.addTypeCode(validTerm.toCD());
            }
        
			break;
		case inference:
			inference.getListTypeCode().clear();
			inference.addTypeCode(term.toCD());
            for (ValidTerm validTerm : term.getListValidTerm()) {
            	inference.addTypeCode(validTerm.toCD());
            }
			break;
		case anatomicEntityCharacteristic:
			anatomicEntityCharacteristic.getListTypeCode().clear();
			anatomicEntityCharacteristic.addTypeCode(term.toCD());
            for (ValidTerm validTerm : term.getListValidTerm()) {
            	anatomicEntityCharacteristic.addTypeCode(validTerm.toCD());
            }
			break;
		case imagingObservationCharacteristic:
			imagingObservationCharacteristic.getListTypeCode().clear();
			imagingObservationCharacteristic.addTypeCode(term.toCD());
            for (ValidTerm validTerm : term.getListValidTerm()) {
            	imagingObservationCharacteristic.addTypeCode(validTerm.toCD());
            }
			break;
		}
	}

	public void printComponent() {

		logger.info("component  label=" + getLabel() + " codeValue="
				+ getCodeValue() + " " + getComponentType()
				+ getComponentType() + " coordinatedValue="
				+ getCoordinatedCodeValue() + " " + " coordinatedMeaning="
				+ getCoordinatedCodeMeaning());

	}

}
