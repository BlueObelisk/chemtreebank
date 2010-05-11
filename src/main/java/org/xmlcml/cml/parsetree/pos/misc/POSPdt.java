/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.pos.misc;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSElement;

/**
 * "half"
 * @author pm286
 */
public class POSPdt extends POSElement {
	private static Logger LOG = Logger.getLogger(POSPdt.class);

	public final static String TAG = "PDT";

    public POSPdt(Element element) {
    	super(TAG, element);
    }

    @Override
    protected String getTag() {return TAG;}
    
}
