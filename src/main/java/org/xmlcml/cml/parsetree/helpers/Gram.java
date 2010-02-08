package org.xmlcml.cml.parsetree.helpers;

import nu.xom.Element;

import org.xmlcml.cml.base.CMLConstants;
import org.xmlcml.cml.element.CMLScalar;

public class Gram {
	private Element element;

	public Gram(Element element) {
		this.element = element;
	}
	
	public CMLScalar getScalar() {
		CMLScalar scalar = new CMLScalar();
		scalar.setUnits("unit:gram");
		scalar.setDataType(CMLConstants.XSD_DOUBLE);
		return scalar;
	}
}
