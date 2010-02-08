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
public class POSIn extends POSPrep {
	private static Logger LOG = Logger.getLogger(POSIn.class);

	public final static String TAG = "IN";
	public static final String PREFIX = TAG+"-";

    public POSIn(Element element) {
    	super(element, TAG);
    }

    public POSIn(Element element, String role) {
    	this(element);
    	this.setRole(role);
    }

}
