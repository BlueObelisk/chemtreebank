/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.punct;

import nu.xom.Element;

import org.apache.log4j.Logger;

/**
 *
 * @author pm286
 */
public class POSLrb extends POSPunct {
	private static Logger LOG = Logger.getLogger(POSPunct.class);

	public final static String TAG = "_-LRB-";

    public POSLrb(Element element) {
    	super(element, TAG);
    }

}
