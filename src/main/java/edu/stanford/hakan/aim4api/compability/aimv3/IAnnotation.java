/*
 * Copyright (c) 2011, The Board of Trustees of the Leland Stanford Junior 
 * University, creator Daniel L. Rubin. 
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this 
 * list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package edu.stanford.hakan.aim4api.compability.aimv3;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Hakan BULU
 */
public interface IAnnotation extends Serializable {

    public void addCalculation(Calculation newCalculation);

    public void addInference(Inference newInference);

    public void addUser(User newUser);

    public void addEquipment(Equipment newEquipment);

    public void addAnatomicEntity(AnatomicEntity newAnatomicEntity);

    public void addImagingObservation(ImagingObservation newImagingObservation);

    public void addAimStatus(AimStatus newAimStatus);

    public String getAimVersion();
   
    public void setAimVersion(String aimVersion,String accessKey);

    public AnatomicEntityCollection getAnatomicEntityCollection();

    public void setAnatomicEntityCollection(AnatomicEntityCollection anatomicEntityCollection);

    public Integer getCagridId();

    public void setCagridId(Integer cagridId);

    public CalculationCollection getCalculationCollection();

    public void setCalculationCollection(CalculationCollection calculationCollection);

    public String getCodeMeaning();

    public void setCodeMeaning(String codeMeaning);

    public String getCodeValue();

    public void setCodeValue(String codeValue);

    public String getCodingSchemeDesignator();

    public void setCodingSchemeDesignator(String codingSchemeDesignator);

    public String getCodingSchemeVersion();

    public void setCodingSchemeVersion(String codingSchemeVersion);

    public String getComment();

    public void setComment(String comment);

    public String getDateTime();

    public void setDateTime(String dateTime);

    public ImagingObservationCollection getImagingObservationCollection();

    public void setImagingObservationCollection(ImagingObservationCollection imagingObservationCollection);

    public InferenceCollection getInferenceCollection();

    public void setInferenceCollection(InferenceCollection inferenceCollection);

    public List<AimStatus> getListAimStatus();

    public void setListAimStatus(List<AimStatus> listAimStatus);

    public List<Equipment> getListEquipment();

    public void setListEquipment(List<Equipment> listEquipment);

    public List<User> getListUser();

    public void setListUser(List<User> listUser);

    public String getName();

    public void setName(String name);

    public String getPrecedentReferencedAnnotationUID();

    public void setPrecedentReferencedAnnotationUID(String precedentReferencedAnnotationUID);

    public String getUniqueIdentifier();

    public void setUniqueIdentifier(String uniqueIdentifier,String accessKey);

    public void setOntologyPrefix(String Prefix);

    public String getOntologyPrefix();

	void clearCalculation();
}
