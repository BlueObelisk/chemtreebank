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
public class POSVbRemove extends POSVerb {
	private static Logger LOG = Logger.getLogger(POSVbRemove.class);

	public final static String TAG = "VB-REMOVE";

    public POSVbRemove(Element element) {
    	super(element, TAG);
    }

}
