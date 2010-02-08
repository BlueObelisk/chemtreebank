/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.prep;

import nu.xom.Element;

import org.apache.log4j.Logger;

/**
 *
 * @author pm286
 */
public class POSTo extends POSPrep {
	private static Logger LOG = Logger.getLogger(POSTo.class);

	public final static String TAG = "TO";
	public static final String PREFIX = TAG+"-";

    public POSTo(Element element) {
    	super(element, TAG);
    }

    public POSTo(Element element, String role) {
    	this(element);
    	this.setRole(role);
    }

}
