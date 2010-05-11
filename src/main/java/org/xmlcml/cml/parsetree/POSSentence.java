/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree;

import nu.xom.Element;

import org.apache.log4j.Logger;

/**
 *
 * @author pm286
 */
public class POSSentence extends POSElement {
	private static Logger LOG = Logger.getLogger(POSSentence.class);

	public final static String TAG = "Sentence";

    public POSSentence(Element element) {
    	super(TAG, element);
    	tidy();
    }
    
    private void tidy() {
    }

    @Override
    protected String getTag() {return TAG;}
    
}
