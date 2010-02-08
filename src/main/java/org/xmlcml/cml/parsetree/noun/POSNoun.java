/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.noun;

import java.util.List;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSElement;
import org.xmlcml.cml.parsetree.helpers.NumberHelper;
import org.xmlcml.cml.parsetree.helpers.TreeBankUtil;
import org.xmlcml.cml.parsetree.helpers.Units;
import org.xmlcml.cml.parsetree.helpers.Units.Type;

/**
 *
 * @author pm286
 */
public class POSNoun extends POSElement {
	private static Logger LOG = Logger.getLogger(POSNoun.class);

	public final static String TAG = "NN";
	public static final String PREFIX = TAG+"-";

	public static final String NUMBER = "NUMBER";

	public static final String PLURAL = "PLURAL";

	public POSNoun(Element element) {
    	super(TAG, element);
    	tidy();
    }

    public POSNoun(Element element, String role) {
    	this(element);
    	this.setRole(role);
    }

	private void tidy() {
	}

    
}
