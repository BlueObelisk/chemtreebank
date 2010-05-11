/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.phrase;

import java.util.List;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Nodes;

import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLElement;
import org.xmlcml.cml.base.CMLUtil;
import org.xmlcml.cml.element.CMLMolecule;
import org.xmlcml.cml.element.CMLName;
import org.xmlcml.cml.parsetree.POSElement;
import org.xmlcml.cml.parsetree.helpers.TreeBankUtil;

/**
 *
 * @author pm286
 */
public class POSVerbPhrase extends POSElement {
	private static Logger LOG = Logger.getLogger(POSVerbPhrase.class);

	public final static String TAG = "VerbPhrase";
	public static final String TENSE = "tense";
	public enum Tense {
		PAST,
		PRESENT,
		FUTURE
	}

    public POSVerbPhrase(Element element) {
    	super(TAG, element);
    	tidy();
    }

	private void tidy() {
		concatenateVBD();
	}

    @Override
    protected String getTag() {return TAG;}
    
	/*
      <VB role="VBD">was</VB>
      <VB role="VBN">carried</VB>
      ==>
      <VB tense="past" role="VBD">was carried</VB>
	 */
	private void concatenateVBD() {
		List<Element> vbElements = TreeBankUtil.findChildElementAndImmediatelyFollowingSibling(this, "VB[@role='VBD']", "VB");
		if (vbElements != null) {
			vbElements.get(0).addAttribute(new Attribute(TENSE, Tense.PAST.toString()));
			((POSElement)vbElements.get(0)).setRole(((POSElement)vbElements.get(1)).getRole());
			TreeBankUtil.concatenateSiblingContent(this, vbElements);
		}
	}

    
}
