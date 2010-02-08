/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.noun;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nu.xom.Element;
import nu.xom.Nodes;

import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLConstants;
import org.xmlcml.cml.element.CMLFormula;
import org.xmlcml.cml.element.CMLMolecule;
import org.xmlcml.cml.element.CMLName;
import org.xmlcml.cml.parsetree.POSElement;
import org.xmlcml.cml.parsetree.POSException;

/**
 *
 * @author pm286
 */
public class POSMolecule extends POSNoun {
	private static Logger LOG = Logger.getLogger(POSMolecule.class);

	public final static String TAG = "MOLECULE";
	public final static String UNNAMEDMOLECULE = "UNNAMEDMOLECULE";	

	private static Map<String, String> nameMap = null;
	static {
		createNameMap();
	}
	
	private static void createNameMap() {
		nameMap = new HashMap<String, String>();
        nameMap.put("1,1,2-trichlorotrifluoroethane", "ClC(Cl)(F)C(F)(F)Cl");
        nameMap.put("1,2-dichlorobenzene", "Clc1cccccc1Cl");
        nameMap.put("1,2-dichloroethane", "ClCCCl");
        nameMap.put("1,4-dioxane", "O1CCOCC1");
        nameMap.put("1-butanol", "OCCCC");
        nameMap.put("1-chlorobutane", "ClCCC");
        nameMap.put("1-methyl-2-pyrrolidinone", "CN1C(=O)CCC1");
        nameMap.put("1-octanol", "OCCCCCCCC");
        nameMap.put("1-propanol", "OCCC");
        nameMap.put("2,2,2-trifluoroethanol", "OCC(F)(F)F");
        nameMap.put("2-butanol", "CC(O)CC");
        nameMap.put("2-ethoxyethyl ether", "CCOCCOCC");
        nameMap.put("2-methoxyethanol", "OCCOC");
        nameMap.put("2-methoxyethyl acetate", "COCCOCOC");
        nameMap.put("acetic acid", "CC(=O)O");
        nameMap.put("acetic anhydride", "O=C(C)OC(C)=O");
        nameMap.put("acetone", "CC(=O)C");
        nameMap.put("acetonitrile", "CC#N");
        nameMap.put("benzene", "c1ccccc1");
        nameMap.put("benzonitrile", "c1ccccc1C#N");
        nameMap.put("butyl acetate", "CCCCOC(=)C");
        nameMap.put("carbon disulfide", "S=C=S");
        nameMap.put("carbon tetrachloride", "ClC(Cl)(Cl)C");
        nameMap.put("chlorobenzene", "Clc1ccccc1");
        nameMap.put("chloroform", "ClC(Cl)Cl");
        nameMap.put("cyclohexane", "C1CCCCC1");
        nameMap.put("cyclopentane", "C1CCCC1");
        nameMap.put("dichloromethane", "ClCCl");
        nameMap.put("diethyl amine", "CCNCC");
        nameMap.put("diethyl ether", "CCOCC");
        nameMap.put("diethyl ketone", "CCC(=O)CC");
        nameMap.put("dimethoxyethane", "COCCOC");
        nameMap.put("dimethyl sulfoxide", "CS(=O)C");
        nameMap.put("dioxane", "O1CCOCC1");
        nameMap.put("ethanol", "OCC");
        nameMap.put("ethyl acetate", "CC(=O)OCC");
        nameMap.put("ethylene dichloride", "ClCCCl");
        nameMap.put("ethylene glycol", "OCCO");
        nameMap.put("formic acid", "O=CO");
        nameMap.put("glycerin", "OCC(O)CO");
        nameMap.put("heptane", "CCCCCCC");
        nameMap.put("hexamethylphosphoramide", "O=P(N(C)C)(N(C)C)(N(C)C)");
        nameMap.put("hexamethylphosphorus triamide", "O=P(N(C)C)(N(C)C)(N(C)C)");
        nameMap.put("hexane", "CCCCCC");
        nameMap.put("isopropanol", "CC(O)C");
        nameMap.put("m-xylene", "Cc1cc(C)ccc1");
        nameMap.put("methanol", "CO");
        nameMap.put("methyl ethyl ketone", "CC(=O)CC");
        nameMap.put("methylene chloride", "ClCCl");
        nameMap.put("n,n-dimethylacetamide", "CC(=O)N(C)C");
        nameMap.put("n,n-dimethylformamide", "C(=O)N(C)C");
        nameMap.put("n-hexane", "CCCCCC");
        nameMap.put("n-pentane", "CCCCC");
        nameMap.put("n-propanol", "CCCO");
        nameMap.put("nitromethane", "CN(=O)=O");
        nameMap.put("o-xylene", "Cc1ccccc1C");
        nameMap.put("p-xylene", "Cc1ccc(C)cc1");
        nameMap.put("pentane", "CCCCC");
        nameMap.put("propanoic acid", "CCC(=O)O");
        nameMap.put("propylene carbonate", "O1C(=O)OCCC1");
        nameMap.put("pyridine", "n1ccccc1");
        nameMap.put("tert-butyl alcohol", "OC(C)(C)C");
        nameMap.put("tert-butyl methyl ether", "COC(C)(C)C");
        nameMap.put("tetrachloroethylene", "ClC(Cl)=C(Cl)Cl");
        nameMap.put("tetrahydrofuran", "O1CCCC1");
        nameMap.put("THF", "O1CCCC1");
        nameMap.put("toluene", "Cc1ccccc1");
        nameMap.put("triethyl amine", "CCN(CC)CC");
        nameMap.put("water", "O");
        
        nameMap.put("acrolein", "C=C=O");
        nameMap.put("aluminum chloride", "ClAl(Cl)Cl");
        nameMap.put("ammonium chloride", "[N+].[Cl-]");
        nameMap.put("benzylamine", "NCc1ccccc1");
        nameMap.put("DEAD", "CCOC(=O)N=NC(=O)OCC");
        nameMap.put("DMF", "O=CN(C)C");
        nameMap.put("DMSO", "CS(=O)C");
        nameMap.put("iron", "[Fe]");
        nameMap.put("nitrogen", "N#N");
        nameMap.put("sodium iodide", "[Na+][I-]");
        nameMap.put("triphenylphosphine", "c1ccccc1P(c1ccccc1)c1ccccc1");
	}

    public POSMolecule(Element element) {
    	super(element, TAG);
    	createCMLMolecule();
    }

	public POSMolecule(Element element, String role) {
    	this(element);
    	this.setRole(role);
    	if (role.equals(UNNAMEDMOLECULE)) {
    		CMLMolecule molecule = new CMLMolecule();
    		molecule.setRef(this.getValue());
    		this.appendChild(molecule);
    	}
    }

    private void createCMLMolecule() {
    	List<POSElement> elementList = this.getPOSElementOnlyChildren();
    	/*
            <NN role="MOLECULE">
                <NN role="OSCAR-CM">nitrogen</NN>
            </NN>
    	 */
    	if (elementList.size() == 0) {
    		throw new POSException("Molecule must have element children "+this.toXML());
    	}
    	CMLMolecule molecule = new CMLMolecule();
		addFormulaOrNameFromOscarCMCD(molecule);
		transferQuantitiesToMolecule(molecule);
		removeEmptyQuantities();
		this.appendChild(molecule);
	}

    /**
                <NN role="QUANTITY" surface=""/>
     */
    private void removeEmptyQuantities() {
		Nodes quantites = this.query("NN[@role='QUANTITY' and count(*)=0 and count(text()) = 0]", CMLConstants.CML_XPATH);
		for (int i = 0; i < quantites.size(); i++) {
			quantites.get(i).detach();
		}
	}

	/*
        <NN role="QUANTITY" surface="403.0">
          <amount role="MASS" xmlns="http://www.xml-cml.org/schema">
            <scalar dataType="xsd:double" units="units:g" xmlns:units="http://www.xml-cml.org/units">403.0</scalar>
          </amount>
          <amount role="AMOUNT" xmlns="http://www.xml-cml.org/schema">
            <scalar dataType="xsd:double" units="units:mol" xmlns:units="http://www.xml-cml.org/units">1.5</scalar>
          </amount>
        </NN>
     */
	private void transferQuantitiesToMolecule(CMLMolecule molecule) {
		Nodes amounts = this.query("NN[@role='QUANTITY']/cml:amount", CMLConstants.CML_XPATH);
		for (int i = 0; i < amounts.size(); i++) {
			amounts.get(i).detach();
			molecule.appendChild(amounts.get(i));
		}
	}

	private void addFormulaOrNameFromOscarCMCD(CMLMolecule molecule) {
		Nodes oscars = this.query("NN[@role='OSCAR-CM' or @role='OSCAR-CD']", CMLConstants.CML_XPATH);
		if (oscars.size() == 1) {
			POSElement oscar = (POSElement) oscars.get(0);
			String value = oscar.getValue();
			CMLFormula formula = lookupFormula(value);
			formula = parseAsChemicalFormula(value, formula);
			if (formula != null) {
				molecule.addFormula(formula);
			} 
			CMLName name = new CMLName();
			name.setXMLContent(value);
			molecule.addName(name);
			oscar.detach();
		}
	}

	private CMLFormula parseAsChemicalFormula(String value, CMLFormula formula) {
		if (formula == null && 
			Character.isLetter(value.charAt(0)) &&
			Character.isUpperCase(value.charAt(0))) {
			try {
				formula = CMLFormula.createFormula(value);
			} catch (Exception e) {
//					LOG.error("Cannot parse formula: "+value);
			}
		}
		return formula;
	}

	private static CMLFormula lookupFormula(String value) {
		CMLFormula formula = null;
		String smiles = nameMap.get(value);
		if (smiles != null) {
			formula = new CMLFormula();
			formula.setInline(smiles);
			formula.setConvention("SMILES");
		}
		return formula;
	}

}
