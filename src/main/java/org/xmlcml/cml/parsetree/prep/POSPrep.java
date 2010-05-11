/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.prep;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSElement;

/**
 *
 * @author pm286
 */
public class POSPrep extends POSElement {
	private static Logger LOG = Logger.getLogger(POSPrep.class);

	public final static String TAG = "IN";

    public POSPrep(Element element, String role) {
    	super(TAG, element);
    	this.setRole(role);
    }

    @Override
    protected String getTag() {return TAG;}
    
}
