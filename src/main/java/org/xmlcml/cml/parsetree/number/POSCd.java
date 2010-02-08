/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.number;

import nu.xom.Element;

import org.apache.log4j.Logger;

/**
 *
 * @author pm286
 */
public class POSCd extends POSNumber {
	private static Logger LOG = Logger.getLogger(POSCd.class);

	public final static String TAG = "CD";

    public POSCd(Element element) {
    	super(element);
    }

}
