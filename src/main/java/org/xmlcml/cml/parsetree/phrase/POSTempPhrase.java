/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.phrase;

import nu.xom.Element;
import nu.xom.Nodes;

import org.apache.log4j.Logger;
import org.xmlcml.cml.element.CMLScalar;
import org.xmlcml.cml.parsetree.POSElement;
import org.xmlcml.cml.parsetree.helpers.TreeBankUtil;
import org.xmlcml.cml.parsetree.helpers.Units;
import org.xmlcml.cml.parsetree.helpers.Units.Name;
import org.xmlcml.cml.parsetree.helpers.Units.Type;

/**
 *
 * @author pm286
 */
public class POSTempPhrase extends POSElement {
	private static Logger LOG = Logger.getLogger(POSTempPhrase.class);

	public final static String TAG = "TempPhrase";

	private static final double ROOMTEMP = 20.0;

    public POSTempPhrase(Element element) {
    	super(TAG, element);
    	tidy();
    }

	private void tidy() {
		TreeBankUtil.concatenateConsecutiveSiblings(
			1, this, new String[] {"NN[.='room']", "NN[.='temperature']"});
		replaceNonNumericPhrases();
	}

	private void replaceNonNumericPhrases() {
		Nodes rt = this.query("NN[.='room temperature']");
		if (rt.size() == 1) {
			TreeBankUtil.replaceStringContainerByScalar((Element)rt.get(0), ROOMTEMP, Name.CELSIUS);
		}
	}

}
