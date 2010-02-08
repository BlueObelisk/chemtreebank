package org.xmlcml.cml.parsetree.helpers;

import nu.xom.Element;

import org.xmlcml.cml.parsetree.POSException;

public class NnAmount extends Helper {
	private Object object;
	public NnAmount(Element element) {
		this.element = element;
	}
	
	public Object getObject() {
		if (element.getChildElements().size() == 0) {
			String content = element.getValue();
			if ("mmol".equals(content)) {
				object = new Mol("mmol");
			} else if ("M".equals(content)) {
				object = new Mol("M");
			} else if ("mol".equals(content)) {
				object = new Mol(content);
			} else if ("mmoL".equals(content)) {
					object = new Mol("mmol");
			} else {
				throw new POSException("Cannot process NN-AMOUNT "+element.toXML());
			}
		}
		return object;
	}
}
