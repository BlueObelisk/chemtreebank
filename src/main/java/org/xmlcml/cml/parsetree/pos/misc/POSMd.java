/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.pos.misc;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSElement;

/**
 * modifier? ("ca")
 * @author pm286
 */
public class POSMd extends POSElement {
	private static Logger LOG = Logger.getLogger(POSMd.class);

	public final static String TAG = "MD";

    public POSMd(Element element) {
    	super(TAG, element);
    	this.setRole(TAG);
    }

}
