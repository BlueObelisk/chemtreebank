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
public class POSRbs extends POSAdj {
	private static Logger LOG = Logger.getLogger(POSRbs.class);

	public final static String TAG = "RBS";

    public POSRbs(Element element) {
    	super(element, TAG);
    }

}
