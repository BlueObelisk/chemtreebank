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
public class POSInOf extends POSPrep {
	private static Logger LOG = Logger.getLogger(POSInOf.class);

	public final static String TAG = "IN";

    public POSInOf(Element element) {
    	super(element, TAG);
    }

}
