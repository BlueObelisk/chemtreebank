/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.noun;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSElement;

/**
 *
 * @author pm286
 */
public class POSNouns extends POSElement {
	private static Logger LOG = Logger.getLogger(POSNouns.class);

	public final static String TAG = "NNS";
	public static final String PREFIX = TAG+"-";

    public POSNouns(Element element) {
    	super(TAG, element);
    }
    
    public POSNouns(Element element, String role) {
    	this(element);
    	this.setRole(role);
    }

    @Override
    protected String getTag() {return TAG;}
    
}
