package org.xmlcml.cml.parsetree.phrase;

import nu.xom.Element;

import org.xmlcml.cml.parsetree.POSElement;

public abstract class POSPhrase extends POSElement {

	public final static String PHRASE = "phrase";
	public POSPhrase(String tag, Element element) {
		super(tag, element);
	}
}
