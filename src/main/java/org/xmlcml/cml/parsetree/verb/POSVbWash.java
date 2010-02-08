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
public class POSVbWash extends POSVerb {
	private static Logger LOG = Logger.getLogger(POSVbWash.class);

	public final static String TAG = "VB-WASH";

    public POSVbWash(Element element) {
    	super(element, TAG);
    }

}
