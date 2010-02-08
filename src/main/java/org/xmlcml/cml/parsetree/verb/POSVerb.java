/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.verb;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSElement;

/**
 *
 * @author pm286
 */
public class POSVerb extends POSElement {
	private static Logger LOG = Logger.getLogger(POSVerb.class);

	public final static String TAG = "VB";
	public static final String PREFIX = "VB"+"-";

    public POSVerb(Element element) {
    	super(TAG, element);
    }

    public POSVerb(Element element, String role) {
    	this(element);
    	this.setRole(role);
    }

}
