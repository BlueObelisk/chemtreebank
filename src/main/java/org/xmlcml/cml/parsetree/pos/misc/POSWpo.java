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
public class POSWpo extends POSElement {
	private static Logger LOG = Logger.getLogger(POSWpo.class);

	public final static String TAG = "WPO";

    public POSWpo(Element element) {
    	super(TAG, element);
    }

}
