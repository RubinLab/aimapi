package edu.stanford.hakan.aim4api.project.epad;

import java.io.Serializable;

import edu.stanford.hakan.aim4api.base.CD;
import edu.stanford.hakan.aim4api.base.CharacteristicQuantification;
import edu.stanford.hakan.aim4api.base.Enumerations;
import edu.stanford.hakan.aim4api.base.Enumerations.ComparisonOperator;
import edu.stanford.hakan.aim4api.base.Interval;
import edu.stanford.hakan.aim4api.base.NonQuantifiable;
import edu.stanford.hakan.aim4api.base.Numerical;
import edu.stanford.hakan.aim4api.base.Quantile;
import edu.stanford.hakan.aim4api.base.ST;
import edu.stanford.hakan.aim4api.base.Scale;
import edu.stanford.hakan.aim4api.project.epad.Enumerations.QuantifierType;
import java.util.logging.Logger;

/**
 * 
 * @author Debra Willrett
 * 
 */
@SuppressWarnings("serial")
public class Quantifier implements Serializable {

	private static final Logger logger = Logger.getLogger("Quantifier");
        
	public QuantifierType quantifierType;
	public Numerical numerical;
	public Quantile quantile;
	public Scale scale;
	public Interval interval;
	public NonQuantifiable nonQuantifiable;

	public Quantifier(QuantifierType quantifierType) {
		this.quantifierType = quantifierType;
		switch (quantifierType) {
		case numerical:
			numerical = new Numerical();
			break;
		case quantile:
			quantile = new Quantile();
			break;
		case interval:
			interval = new Interval();
			break;
		case nonQuantifiable:
			nonQuantifiable = new NonQuantifiable();
			break;
		case scale:
			scale = new Scale();
			//TODO aimv4 conversion code was like this but this is not correct
			scale.setType(Enumerations.ScaleType.Nominal);
			break;
		}
	}

	@SuppressWarnings("static-access")
	public Quantifier(CharacteristicQuantification characteristicQuantification) {

		if (characteristicQuantification instanceof Numerical) {
			quantifierType = quantifierType.numerical;
			numerical = (Numerical) characteristicQuantification;
		} else if (characteristicQuantification instanceof Interval) {
			quantifierType = quantifierType.interval;
			interval = (Interval) characteristicQuantification;
		} else if (characteristicQuantification instanceof Scale) {
			quantifierType = quantifierType.scale;
			scale = (Scale) characteristicQuantification;
		} else if (characteristicQuantification instanceof Quantile) {
			quantifierType = quantifierType.quantile;
			quantile = (Quantile) characteristicQuantification;
		} else if (characteristicQuantification instanceof NonQuantifiable) {
			quantifierType = quantifierType.nonQuantifiable;
			nonQuantifiable = (NonQuantifiable) characteristicQuantification;
		}

	}

//	public void setCagridId(int cagridId) {
//		switch (quantifierType) {
//		case numerical:
//			numerical.setCagridId(cagridId);
//			break;
//		case quantile:
//			quantile.setCagridId(cagridId);
//			break;
//		case interval:
//			interval.setCagridId(cagridId);
//			break;
//		case nonQuantifiable:
//			nonQuantifiable.setCagridId(cagridId);
//			break;
//		case scale:
//			scale.setCagridId(cagridId);
//			break;
//		}
//
//	}

	public void setAnnotatorConfidence(double d) {
		switch (quantifierType) {
		case numerical:
			numerical.setAnnotatorConfidence(d);
			break;
		case quantile:
			quantile.setAnnotatorConfidence(d);
			break;
		case interval:
			interval.setAnnotatorConfidence(d);
			break;
		case nonQuantifiable:
			nonQuantifiable.setAnnotatorConfidence(d);
			break;
		case scale:
			scale.setAnnotatorConfidence(d);
			break;
		}

	}

	public void setComparisonOperators(ComparisonOperator comparisonOperator) {
		switch (quantifierType) {
		case numerical:
			numerical.setOperator(comparisonOperator);
			break;
		default:
			break;
		}

	}

	public void setName(String valueLabel) {
		switch (quantifierType) {
		case numerical:
			numerical.setLabel(new ST(valueLabel));
			break;
		case quantile:
			quantile.setLabel(new ST(valueLabel));
			break;
		case interval:
			interval.setLabel(new ST(valueLabel));
			break;
		case nonQuantifiable:
			nonQuantifiable.setLabel(new ST(valueLabel));
			break;
		case scale:
			scale.setLabel(new ST(valueLabel));
			break;
		}

	}

	public void setUcumString(String ucumString) {
		switch (quantifierType) {
		case numerical:
			numerical.setUcumString(new ST(ucumString));
			break;
		case interval:
			interval.setUcumString(new ST(ucumString));
                        
			break;
		default:
			break;
		}

	}

	public void setValue(Double value) {
		switch (quantifierType) {
		case numerical:
			numerical.setValue(value);
			break;
		default:
			break;

		}

	}

	public void setBin(int bin) {
		switch (quantifierType) {
		case quantile:
			quantile.setBins(bin);
			break;
		default:
			break;

		}

	}

	public void setDescription(String valueDescription) {
		switch (quantifierType) {
		case scale:
			scale.setValueLabel(new ST(valueDescription));
			break;
		default:
			break;
		}

	}

	public void setValue(String value) {
		switch (quantifierType) {
		case scale:
			scale.setValue(new ST(value));
			break;
		default:
			break;
		}

	}

	public void setCodingSchemeDesignator(String codingSchemeDesignator) {
		switch (quantifierType) {
		case numerical:

		case nonQuantifiable:
			if (nonQuantifiable.getTypeCode()==null)
				nonQuantifiable.setTypeCode(new CD());
			nonQuantifiable.getTypeCode().setCodeSystemName(codingSchemeDesignator);
			break;
		default:
			break;
		}
	}

	public void setCodingSchemeVersion(String codingSchemeVersion) {
		switch (quantifierType) {
		case numerical:

		case nonQuantifiable:
			if (nonQuantifiable.getTypeCode()==null)
				nonQuantifiable.setTypeCode(new CD());
			nonQuantifiable.getTypeCode().setCodeSystemVersion(codingSchemeVersion);
			break;
		default:
			break;
		}
	}

	public void setCodeMeaning(String value) {
		switch (quantifierType) {

		case nonQuantifiable:
			if (nonQuantifiable.getTypeCode()==null)
				nonQuantifiable.setTypeCode(new CD());
			nonQuantifiable.getTypeCode().setCodeSystem(value);
			break;
		default:
			break;
		}

	}

	public void setCodeValue(String value) {
		switch (quantifierType) {
		case nonQuantifiable:
			if (nonQuantifiable.getTypeCode()==null)
				nonQuantifiable.setTypeCode(new CD());
			nonQuantifiable.getTypeCode().setCode(value);
			break;
		default:
			break;

		}

	}

	public void setMaxOperator(ComparisonOperator comparisonOperator) {
		switch (quantifierType) {
		case interval:
			interval.setMaxOperator(comparisonOperator);
			break;
		default:
			break;
		}
	}

	public void setMinOperator(ComparisonOperator comparisonOperator) {
		switch (quantifierType) {
		case interval:
			interval.setMinOperator(comparisonOperator);
			break;
		default:
			break;
		}

	}

	public void setMaxValue(Double maxValue) {
		switch (quantifierType) {
		case interval:
			interval.setMaxValue(maxValue);
			break;
		default:
			break;
		}

	}

	public void setMinValue(Double minValue) {
		switch (quantifierType) {
		case interval:
			interval.setMinValue(minValue);
			break;
		default:
			break;
		}

	}

	public String getName() {
		String result = "";
		switch (quantifierType) {
		case numerical:
			result = numerical.getLabel().getValue();
			break;
		case quantile:
			result = quantile.getLabel().getValue();
			break;
		case interval:
			result = interval.getLabel().getValue();
			break;
		case nonQuantifiable:
			result = nonQuantifiable.getLabel().getValue();
			break;
		case scale:
			result = scale.getLabel().getValue();
			break;
		}
		return result;
	}

	public String getValue() {
		String result = "";
		switch (quantifierType) {
		case scale:
			result = scale.getValue().getValue();
			break;
		default:
			break;
		}
		return result;
	}

	public String getUcumString() {
		String result = "";
		switch (quantifierType) {
		case numerical:
			result = numerical.getUcumString().getValue();
			break;
		case interval:
			result = interval.getUcumString().getValue();
			break;
		default:
			break;
		}
		return result;
	}

	public Double getMinValue() {

		Double result = 0.0;
		switch (quantifierType) {
		case interval:
			result = interval.getMinValue();
			break;
		default:
			break;
		}
		return result;
	}

	public Double getMaxValue() {
		Double result = 0.0;
		switch (quantifierType) {
		case interval:
			result = interval.getMaxValue();
			break;
		default:
			break;
		}
		return result;
	}

	public ComparisonOperator getMinOperator() {
		ComparisonOperator result = null;
		switch (quantifierType) {
		case interval:
			result = interval.getMinOperator();
			break;
		default:
			break;
		}
		return result;
	}

	public ComparisonOperator getMaxOperator() {
		ComparisonOperator result = null;
		switch (quantifierType) {
		case interval:
			result = interval.getMaxOperator();
			break;
		default:
			break;
		}
		return result;
	}

	public int getBin() {
		Integer result = 0;
		switch (quantifierType) {
		case quantile:
			result = quantile.getBins();
			break;
		default:
			break;
		}
		return result;
	}

	public String getCodeValue() {
		String result = "";
		switch (quantifierType) {
		case nonQuantifiable:
			if (nonQuantifiable.getTypeCode()!=null)
				return nonQuantifiable.getTypeCode().getCode();
			break;
		default:
			break;

		}
		return result;
	}

	public ComparisonOperator getComparisonOperators() {
		ComparisonOperator result = null;
		switch (quantifierType) {
		case numerical:
			result = numerical.getOperator();
			break;
		default:
			break;

		}
		return result;

	}

}
