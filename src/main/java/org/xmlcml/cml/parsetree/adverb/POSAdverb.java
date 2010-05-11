/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.adverb;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSElement;

/**
 *
 * @author pm286
 */
public class POSAdverb extends POSElement {
	private static Logger LOG = Logger.getLogger(POSAdverb.class);

	public final static String TAG = "RB";

    public POSAdverb(Element element) {
    	super(TAG, element);
    }

    public POSAdverb(Element element, String role) {
    	this(element);
    	this.setRole(role);
    }

    @Override
    protected String getTag() {return TAG;}
    
}
