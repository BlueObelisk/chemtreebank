/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.adj;

import nu.xom.Element;

import org.apache.log4j.Logger;

/**
 *
 * @author pm286
 */
public class POSJjt extends POSAdj {
	private static Logger LOG = Logger.getLogger(POSJjt.class);

	public final static String TAG = "JJT";

    public POSJjt(Element element) {
    	super(element, TAG);
    }

}
