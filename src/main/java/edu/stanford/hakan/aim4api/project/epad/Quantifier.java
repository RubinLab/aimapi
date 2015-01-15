package edu.stanford.shared.aimapi;

import java.io.Serializable;

import edu.stanford.hakan.aim4api.compability.aimv3.AimUtility.ComparisonOperators;
import edu.stanford.hakan.aim4api.compability.aimv3.CharacteristicQuantification;
import edu.stanford.hakan.aim4api.compability.aimv3.Interval;
import edu.stanford.hakan.aim4api.compability.aimv3.NonQuantifiable;
import edu.stanford.hakan.aim4api.compability.aimv3.Numerical;
import edu.stanford.hakan.aim4api.compability.aimv3.Quantile;
import edu.stanford.hakan.aim4api.compability.aimv3.Scale;
import edu.stanford.shared.aimapi.Enumerations.QuantifierType;

/**
 * 
 * @author Debra Willrett
 * 
 */
@SuppressWarnings("serial")
public class Quantifier implements Serializable {

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

	public void setCagridId(int cagridId) {
		switch (quantifierType) {
		case numerical:
			numerical.setCagridId(cagridId);
			break;
		case quantile:
			quantile.setCagridId(cagridId);
			break;
		case interval:
			interval.setCagridId(cagridId);
			break;
		case nonQuantifiable:
			nonQuantifiable.setCagridId(cagridId);
			break;
		case scale:
			scale.setCagridId(cagridId);
			break;
		}

	}

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

	public void setComparisonOperators(ComparisonOperators comparisonOperator) {
		switch (quantifierType) {
		case numerical:
			numerical.setComparisonOperators(comparisonOperator);
			break;
		default:
			break;
		}

	}

	public void setName(String valueLabel) {
		switch (quantifierType) {
		case numerical:
			numerical.setName(valueLabel);
			break;
		case quantile:
			quantile.setName(valueLabel);
			break;
		case interval:
			interval.setName(valueLabel);
			break;
		case nonQuantifiable:
			nonQuantifiable.setName(valueLabel);
			break;
		case scale:
			scale.setName(valueLabel);
			break;
		}

	}

	public void setUcumString(String ucumString) {
		switch (quantifierType) {
		case numerical:
			numerical.setUcumString(ucumString);
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
			quantile.setBin(bin);
			break;
		default:
			break;

		}

	}

	public void setDescription(String valueDescription) {
		switch (quantifierType) {
		case scale:
			scale.setDescription(valueDescription);
			break;
		default:
			break;
		}

	}

	public void setValue(String value) {
		switch (quantifierType) {
		case scale:
			scale.setValue(value);
			break;
		default:
			break;
		}

	}

	public void setCodingSchemeDesignator(String codingSchemeDesignator) {
		switch (quantifierType) {
		case numerical:

		case nonQuantifiable:
			nonQuantifiable.setCodingSchemeDesignator(codingSchemeDesignator);
			break;
		default:
			break;
		}
	}

	public void setCodingSchemeVersion(String codingSchemeVersion) {
		switch (quantifierType) {
		case numerical:

		case nonQuantifiable:
			nonQuantifiable.setCodingSchemeVersion(codingSchemeVersion);
			break;
		default:
			break;
		}
	}

	public void setCodeMeaning(String value) {
		switch (quantifierType) {

		case nonQuantifiable:
			nonQuantifiable.setCodeMeaning(value);
			break;
		default:
			break;
		}

	}

	public void setCodeValue(String value) {
		switch (quantifierType) {
		case nonQuantifiable:
			nonQuantifiable.setCodeValue(value);
			break;
		default:
			break;

		}

	}

	public void setMaxOperator(ComparisonOperators comparisonOperator) {
		switch (quantifierType) {
		case interval:
			interval.setMaxOperator(comparisonOperator);
			break;
		default:
			break;
		}
	}

	public void setMinOperator(ComparisonOperators comparisonOperator) {
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
			result = numerical.getName();
			break;
		case quantile:
			result = quantile.getName();
			break;
		case interval:
			result = interval.getName();
			break;
		case nonQuantifiable:
			result = nonQuantifiable.getName();
			break;
		case scale:
			result = scale.getName();
			break;
		}
		return result;
	}

	public String getValue() {
		String result = "";
		switch (quantifierType) {
		case scale:
			result = scale.getValue();
			break;
		default:
			break;
		}
		return result;
	}

	public Object getUcumString() {
		String result = "";
		switch (quantifierType) {
		case numerical:
			result = numerical.getUcumString();
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

	public Object getMaxValue() {
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

	public ComparisonOperators getMinOperator() {
		ComparisonOperators result = null;
		switch (quantifierType) {
		case interval:
			result = interval.getMinOperator();
			break;
		default:
			break;
		}
		return result;
	}

	public Object getMaxOperator() {
		ComparisonOperators result = null;
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
			result = quantile.getBin();
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
			result = nonQuantifiable.getCodeValue();
			break;
		default:
			break;

		}
		return result;
	}

	public ComparisonOperators getComparisonOperators() {
		ComparisonOperators result = null;
		switch (quantifierType) {
		case numerical:
			result = numerical.getComparisonOperators();
			break;
		default:
			break;

		}
		return result;

	}

}
