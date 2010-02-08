/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.adverb;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.adj.POSAdj;

/**
 *
 * @author pm286
 */
public class POSRbr extends POSAdj {
	private static Logger LOG = Logger.getLogger(POSRbr.class);

	public final static String TAG = "RBR";

    public POSRbr(Element element) {
    	super(element, TAG);
    }

}
