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
public class POSNNP extends POSElement {
	private static Logger LOG = Logger.getLogger(POSNNP.class);

	public final static String TAG = "NNP";

    public POSNNP(Element element) {
    	super(TAG, element);
    }

}
