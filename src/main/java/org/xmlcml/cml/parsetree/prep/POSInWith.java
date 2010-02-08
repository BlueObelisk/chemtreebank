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
public class POSInWith extends POSPrep {
	private static Logger LOG = Logger.getLogger(POSInWith.class);

	public final static String TAG = "IN-WITH";

    public POSInWith(Element element) {
    	super(element, TAG);
    }

}
