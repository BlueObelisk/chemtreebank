/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.verb;

import nu.xom.Element;

import org.apache.log4j.Logger;

/**
 *
 * @author pm286
 */
public class POSVbYield extends POSVerb {
	private static Logger LOG = Logger.getLogger(POSVbYield.class);

	public final static String TAG = "VB-YIELD";

    public POSVbYield(Element element) {
    	super(element, TAG);
    }

}
