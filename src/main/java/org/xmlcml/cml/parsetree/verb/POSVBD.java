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
public class POSVBD extends POSVerb {
	private static Logger LOG = Logger.getLogger(POSVBD.class);

	public final static String TAG = "VBD";

    public POSVBD(Element element) {
    	super(element, TAG);
    }

}
