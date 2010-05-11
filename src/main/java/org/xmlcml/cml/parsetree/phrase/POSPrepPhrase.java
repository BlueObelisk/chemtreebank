/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.phrase;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSElement;

/**
 *
 * @author pm286
 */
public class POSPrepPhrase extends POSElement {
	private static Logger LOG = Logger.getLogger(POSPrepPhrase.class);

	public final static String TAG = "PrepPhrase";

    public POSPrepPhrase(Element element) {
    	super(TAG, element);
    }

    @Override
    protected String getTag() {return TAG;}
    
}
