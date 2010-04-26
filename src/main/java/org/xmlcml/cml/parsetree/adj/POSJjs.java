/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.adj;

import nu.xom.Element;

import org.apache.log4j.Logger;

/**
 * superlative
 * @author pm286
 */
public class POSJjs extends POSAdj {
	private static Logger LOG = Logger.getLogger(POSJjs.class);

	public final static String TAG = "JJS";

    public POSJjs(Element element) {
    	super(element, TAG);
    }

}
