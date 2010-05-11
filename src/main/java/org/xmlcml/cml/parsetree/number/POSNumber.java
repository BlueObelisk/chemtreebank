/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.number;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSElement;
import org.xmlcml.cml.parsetree.POSException;
import org.xmlcml.cml.parsetree.helpers.NumberHelper;

/**
 *
 * @author pm286
 */
public class POSNumber extends POSElement {
	private static Logger LOG = Logger.getLogger(POSNumber.class);

	public final static String TAG = "Number";
	private Double d;

    public POSNumber(Element element) {
    	super(TAG, element);
    	tidy();
    }

    public Double getDouble() {
		return d;
	}

	private void tidy() {
    	d = NumberHelper.getDouble(this);
    	if (d == null) {
    		throw new POSException("Cannot interpret number "+this.toXML());
    	}
    }
    @Override
    protected String getTag() {return TAG;}
    
}
