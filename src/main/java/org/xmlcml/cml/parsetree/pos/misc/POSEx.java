/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.pos.misc;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSElement;

/**
 * "there" (??)
 * @author pm286
 */
public class POSEx extends POSElement {
	private static Logger LOG = Logger.getLogger(POSEx.class);

	public final static String TAG = "EX";

    public POSEx(Element element) {
    	super(TAG, element);
    	this.setRole(TAG);
    }

}
