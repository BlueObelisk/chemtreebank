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
public class POSDt extends POSElement {
	private static Logger LOG = Logger.getLogger(POSDt.class);

	public final static String TAG = "DT";

    public POSDt(Element element) {
    	super(TAG, element);
    }

}
