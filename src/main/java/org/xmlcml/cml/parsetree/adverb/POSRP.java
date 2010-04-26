/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.adverb;

import nu.xom.Element;

import org.apache.log4j.Logger;

/**
 * RP adverb/particle (about, off, up) 
 * @author pm286
 */
public class POSRP extends POSAdverb {
	private static Logger LOG = Logger.getLogger(POSRP.class);

	public final static String TAG = "RP";

    public POSRP(Element element) {
    	super(element, TAG);
    }

}
