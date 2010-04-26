/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.pronoun;

import nu.xom.Element;

import org.apache.log4j.Logger;

/**
 *
 * @author pm286
 */
public class POSPrp extends POSPronoun {
	private static Logger LOG = Logger.getLogger(POSPrp.class);

	public final static String TAG = "PRP";

    public POSPrp(Element element) {
    	super(element, TAG);
    }

}
