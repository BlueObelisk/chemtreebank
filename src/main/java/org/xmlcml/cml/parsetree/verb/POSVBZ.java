/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.verb;

import nu.xom.Element;

import org.apache.log4j.Logger;

/**
 * verb, 3rd. singular present
 * @author pm286
 */
public class POSVBZ extends POSVerb {
	private static Logger LOG = Logger.getLogger(POSVBZ.class);

	public final static String TAG = "VBZ";

    public POSVBZ(Element element) {
    	super(element, TAG);
    }

}
