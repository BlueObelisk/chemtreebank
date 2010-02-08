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
public class POSPercent extends POSNounWithUnits {
	private static Logger LOG = Logger.getLogger(POSPercent.class);

	public final static String TAG = "PERCENT";
	public final static String OTHER_STRING = "%";
	
    public POSPercent(Element element) {
    	super(element, TAG);
    	tidy();
    }
    
    private void tidy() {
    	TreeBankUtil.transformChildNumberAndNNRoleToScalar(this, Type.PERCENT, OTHER_STRING);
    }

}
