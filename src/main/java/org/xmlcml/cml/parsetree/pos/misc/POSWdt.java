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
public class POSWdt extends POSElement {
	private static Logger LOG = Logger.getLogger(POSWdt.class);

	public final static String TAG = "WDT";

    public POSWdt(Element element) {
    	super(TAG, element);
    }

    @Override
    protected String getTag() {return TAG;}
    
}
