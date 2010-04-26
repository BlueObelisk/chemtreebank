/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.pos.misc;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSElement;

/**
 *
 * @author pm286
 */
public class POSUnknown extends POSElement {
	private static Logger LOG = Logger.getLogger(POSUnknown.class);

	public final static String TAG = "UNKNOWN";
	public static final String PREFIX = TAG+"-";

    public POSUnknown(Element element) {
    	super(TAG, element);
    	this.setRole(element.getLocalName());
    }
    
}
