/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.adverb;

import nu.xom.Element;

import org.apache.log4j.Logger;

/**
 *
 * @author pm286
 */
public class POSRbConj extends POSAdverb {
	private static Logger LOG = Logger.getLogger(POSRbConj.class);

	public final static String TAG = "RB-CONJ";

    public POSRbConj(Element element) {
    	super(element, TAG);
    }

}
