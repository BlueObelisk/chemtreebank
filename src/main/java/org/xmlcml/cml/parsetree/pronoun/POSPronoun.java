/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.pronoun;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSElement;

/**
 *
 * @author pm286
 */
public class POSPronoun extends POSElement {
	private static Logger LOG = Logger.getLogger(POSPronoun.class);

	public final static String TAG = "PN";
	public static final String PREFIX = TAG+"-";

	public POSPronoun(Element element) {
    	super(TAG, element);
    	tidy();
    }

    public POSPronoun(Element element, String role) {
    	this(element);
    	this.setRole(role);
    }

	private void tidy() {
	}
    
}
