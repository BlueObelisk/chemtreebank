/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.pos.misc;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSElement;

/**
 * negative
 * @author pm286
 */
public class POSNeg extends POSElement {
	private static Logger LOG = Logger.getLogger(POSNeg.class);

	public final static String TAG = "NEG";

    public POSNeg(Element element) {
    	super(TAG, element);
    	this.setRole(TAG);
    }

    @Override
    protected String getTag() {return TAG;}
    
}
