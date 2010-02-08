/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.phrase;

import nu.xom.Element;
import nu.xom.Nodes;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSElement;
import org.xmlcml.cml.parsetree.helpers.TreeBankUtil;
import org.xmlcml.cml.parsetree.helpers.Units;
import org.xmlcml.cml.parsetree.helpers.Units.Name;

/**
 *
 * @author pm286
 */
public class POSTimePhrase extends POSElement {
	private static Logger LOG = Logger.getLogger(POSTimePhrase.class);

	public final static String TAG = "TimePhrase";
	public final static String TIME_ROLE = "TIME";

	private static final double OVERNIGHT = 16.0;

    public POSTimePhrase(Element element) {
    	super(TAG, element);
    	tidy();
    }

    /*
        <TimePhrase>
            <IN role="FOR">for</IN>
            <Number>4</Number>
            <NN role="TIME">h</NN>
        </TimePhrase>
     */
    private void tidy() {
    	TreeBankUtil.transformChildNumberAndNNRoleToScalar(this, Units.Type.TIME, TIME_ROLE);
		replaceNonNumericPhrases();
	}

	private void replaceNonNumericPhrases() {
		Nodes rt = this.query("NN[.='overnight']");
		if (rt.size() == 1) {
			TreeBankUtil.replaceStringContainerByScalar((Element)rt.get(0), OVERNIGHT, Name.HOUR);
		}
	}

}
