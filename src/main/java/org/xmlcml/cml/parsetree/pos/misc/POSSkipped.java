/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.pos.misc;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSElement;

/**
 *
 * @author pm286
 */
public class POSSkipped extends POSElement {
	private static Logger LOG = Logger.getLogger(POSSkipped.class);

	public final static String TAG = "Skipped";

    public POSSkipped(String role) {
    	super(TAG);
    	this.setRole(role);
//    	addChildren(this, element);
    }
    
}
