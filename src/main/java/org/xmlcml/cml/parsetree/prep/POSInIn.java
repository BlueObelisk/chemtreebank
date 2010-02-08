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
public class POSInIn extends POSPrep {
	private static Logger LOG = Logger.getLogger(POSInIn.class);

	public final static String TAG = "IN-IN";

    public POSInIn(Element element) {
    	super(element, TAG);
    }

}
