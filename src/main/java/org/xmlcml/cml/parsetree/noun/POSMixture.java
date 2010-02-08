/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.noun;

import nu.xom.Element;

import org.apache.log4j.Logger;

/**
 *
 * @author pm286
 */
public class POSMixture extends POSNoun {
	private static Logger LOG = Logger.getLogger(POSMixture.class);

	public final static String TAG = "MIXTURE";

    public POSMixture(Element element) {
    	super(element, TAG);
    }

}
