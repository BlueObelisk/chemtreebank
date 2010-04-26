/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.pronoun;

import nu.xom.Element;

import org.apache.log4j.Logger;

/**
 * "whose"
 * @author pm286
 */
public class POSWp extends POSPronoun {
	private static Logger LOG = Logger.getLogger(POSWp.class);

	public final static String TAG = "WP_";

    public POSWp(Element element) {
    	super(element, TAG);
    }

}
