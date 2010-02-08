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
public class POSOscarCJ extends POSAdj {
	private static Logger LOG = Logger.getLogger(POSOscarCJ.class);

	public final static String TAG = "OSCAR-CJ";

    public POSOscarCJ(Element element) {
    	super(element, TAG);
    }

}
