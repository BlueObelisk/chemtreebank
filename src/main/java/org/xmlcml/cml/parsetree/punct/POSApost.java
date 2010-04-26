/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.punct;

import nu.xom.Element;

import org.apache.log4j.Logger;

/**
 * apostrophe
 * @author pm286
 */
public class POSApost extends POSPunct {
	private static Logger LOG = Logger.getLogger(POSPunct.class);

	public final static String TAG = "APOST";

    public POSApost(Element element) {
    	super(element, TAG);
    }

}
