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
public class POSVolume extends POSNounWithUnits {
	private static Logger LOG = Logger.getLogger(POSVolume.class);

	public final static String TAG = "VOLUME";
	public final static String OTHER_STRING = "VOL";

    public POSVolume(Element element) {
    	super(element, TAG);
    	tidy();
    }
    
    private void tidy() {
    	TreeBankUtil.transformChildNumberAndNNRoleToScalar(this, Type.VOLUME, OTHER_STRING);
    }

}
