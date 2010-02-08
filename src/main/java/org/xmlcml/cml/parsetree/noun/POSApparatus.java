/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.noun;


import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.helpers.TreeBankUtil;

/**
 *
 * @author pm286
 */
public class POSApparatus extends POSNoun {
	private static Logger LOG = Logger.getLogger(POSApparatus.class);

	public final static String TAG = "APPARATUS";

    public POSApparatus(Element element) {
    	super(element, TAG);
    	tidy();
    }
    
    private void tidy() {
    	createCompoundNouns();
    	flattenUnnecessaryNesting();
    }

	private void flattenUnnecessaryNesting() {
		/*
        <NN role="APPARATUS" surface="oven">
          <NN role="APPARATUS">oven</NN>
        </NN>
		 */
		if (this.query("self::NN[@role='APPARATUS' and count(*)=1 and count(NN[@role='APPARATUS'])=1]").size() == 1) {
			Element child = this.getFirstChildElement("NN");
			child.detach();
			this.appendChild(child.getValue());
		}
	}

	private void createCompoundNouns() {
/*
          <NN role="PRESSURE">pressure</NN>
          <NN role="APPARATUS">vessel</NN>
 */
		TreeBankUtil.concatenateSiblingChildren(this, new String[] {"NN[.='pressure']", "NN[.='vessel']"});
		TreeBankUtil.concatenateSiblingChildren(this, new String[] {"NN[.='stir']", "NN[.='bar']"});
	}

}
