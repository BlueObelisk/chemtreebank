/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.adverb;

import nu.xom.Element;

import org.apache.log4j.Logger;

/**
 * WRB wh- adverb (how, where, when) 
 * @author pm286
 */
public class POSWRB extends POSAdverb {
	private static Logger LOG = Logger.getLogger(POSWRB.class);

	public final static String TAG = "WRB";

    public POSWRB(Element element) {
    	super(element, TAG);
    }

}
