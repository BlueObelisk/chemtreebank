/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.noun;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.helpers.TreeBankUtil;
import org.xmlcml.cml.parsetree.helpers.Units.Type;

/**
 *
 * @author pm286
 */
public class POSAmount extends POSNounWithUnits {
	private static Logger LOG = Logger.getLogger(POSAmount.class);

	public final static String TAG = "AMOUNT";
	public final static String OTHER_STRING = "NN-AMOUNT";

	public Element element;
	public String surface;

    public POSAmount(Element element) {
    	super(element, TAG);
    	tidy();
    }
    
    private void tidy() {
    	TreeBankUtil.transformChildNumberAndNNRoleToScalar(this, Type.AMOUNT, OTHER_STRING);
    }
    
}
