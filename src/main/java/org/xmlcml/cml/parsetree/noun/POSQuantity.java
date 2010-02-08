/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.noun;

import java.util.List;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Nodes;

import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLConstants;
import org.xmlcml.cml.element.CMLAmount;
import org.xmlcml.cml.parsetree.POSElement;
import org.xmlcml.cml.parsetree.POSException;
import org.xmlcml.cml.parsetree.punct.POSLrb;
import org.xmlcml.cml.parsetree.punct.POSPunct;
import org.xmlcml.cml.parsetree.punct.POSRrb;

/**
 *
 * @author pm286
 */
public class POSQuantity extends POSNoun {
	private static Logger LOG = Logger.getLogger(POSQuantity.class);

	public final static String TAG = "QUANTITY";

    public POSQuantity(Element element) {
    	super(element, TAG);
    	createAmountChildren(element);
    }

	private void createAmountChildren(Element element) {
		List<POSElement> childElements = this.getPOSElementOnlyChildren();
		int nchild = childElements.size();
		int i = 0;
		int end = nchild;
		boolean brackets = false;
		// may need to skip brackets
		if (childElements.get(0) instanceof POSLrb && 
				childElements.get(nchild-1) instanceof POSRrb) {
			i = 1;
			end = nchild - 1;
			brackets = true;
			childElements.get(0).detach();
			childElements.get(nchild-1).detach();
		}
		while (i < end) {
			POSElement pos = childElements.get(i++);
			if (pos instanceof POSNoun) {
				String role = pos.getAttributeValue(POSElement.ROLE);
				if (role == null) {
					throw new POSException("no role on NN "+this.toXML());
				} else {
					createCMLAmount(pos);
				}
				if (brackets) {
					pos = childElements.get(i++);
					if (!(pos instanceof POSPunct)) {
						throw new POSException("expected punct "+i+" "+this.toXML());
					}
					pos.detach();
				}
			}
		}
	}

	private void createCMLAmount(POSElement pos) {
		Nodes scalars = pos.query("./cml:scalar", CMLConstants.CML_XPATH);
		if (scalars.size() != 1) {
			throw new POSException("Quantity must have scalar children "+this.toXML());
		}
		CMLAmount amount = new CMLAmount();
		amount.appendChild(scalars.get(0));
		amount.addAttribute(new Attribute(POSElement.ROLE, pos.getRole()));
		this.setSurface(this.getValue());
		pos.detach();
		this.appendChild(amount);
	}
}
