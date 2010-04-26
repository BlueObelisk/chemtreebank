/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.adj;

import nu.xom.Element;

import org.apache.log4j.Logger;

/**
 *
 * @author pm286
 */
public class POSMolar extends POSAdj {
	private static Logger LOG = Logger.getLogger(POSMolar.class);

	public final static String TAG = "MOLAR";

    public POSMolar(Element element) {
    	super(element, TAG);
//    	createCMLMolecule();
    }

	public POSMolar(Element element, String role) {
    	this(element);
    	this.setRole(role);
//    	if (role.equals(UNNAMEDMOLECULE)) {
//    		CMLMolecule molecule = new CMLMolecule();
//    		molecule.setRef(this.getValue());
//    		this.appendChild(molecule);
//    	}
    }


}
