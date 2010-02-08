/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.adj;

import java.util.HashSet;
import java.util.Set;

import nu.xom.Attribute;
import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSElement;
import org.xmlcml.cml.parsetree.helpers.TreeBankUtil;

/**
 *
 * @author pm286
 */
public class POSAdj extends POSElement {
	private static Logger LOG = Logger.getLogger(POSAdj.class);

	public final static String TAG = "JJ";
	public static final String QUALIFIER = "qualifier";

	private static Set<String> chemicalSet = null;
	private static Set<String> colorSet = null;
	private static Set<String> miscSet = null;
	private static Set<String> purificationSet = null;
	private static Set<String> sizeSet = null;
	static {
		createChemicalSet();
		createColorSet();
		createMiscSet();
		createPurificationSet();
		createSizeSet();
	}
	
	public static Set<String> getChemicalSet() {
		return chemicalSet;
	}
	public static final String CHEMICAL = "chemical";
	private static void createChemicalSet() {
		chemicalSet = new HashSet<String>();
		chemicalSet.add("aqueous");
		chemicalSet.add("organic");
	}

	public static Set<String> getColorSet() {
		return colorSet;
	}
	public static final String COLOR = "color";
	private static void createColorSet() {
		colorSet = new HashSet<String>();
		colorSet.add("white");
		colorSet.add("yellowish");
		colorSet.add("yellow");
		colorSet.add("reddish");
		colorSet.add("reddish-orange");
		colorSet.add("red");
		colorSet.add("greenish");
		colorSet.add("green");
		colorSet.add("brownish");
		colorSet.add("brown");
		colorSet.add("black");
		colorSet.add("colourless");
		colorSet.add("colorless");
		colorSet.add("blueish");
		colorSet.add("blue");
	};
	
	public static Set<String> getMiscSet() {
		return miscSet;
	}
	
	public static final String MISC = "misc";
	private static void createMiscSet() {
		miscSet = new HashSet<String>();
		miscSet.add("appropriate");
		miscSet.add("complete");
		miscSet.add("desired");
		miscSet.add("exact");
		miscSet.add("example");
		miscSet.add("flush");
		miscSet.add("further");
		miscSet.add("magnetic");
		miscSet.add("major");
		miscSet.add("ml/3");
		miscSet.add("mobile");
		miscSet.add("more");
		miscSet.add("most");
		miscSet.add("next");
		miscSet.add("preparative");
		miscSet.add("present");
		miscSet.add("reverse");
		miscSet.add("saturated");
		miscSet.add("starting");
		miscSet.add("stationary");
		miscSet.add("subsequent");
	}

	public static Set<String> getPurificationSet() {
		return purificationSet;
	}
	public static final String PURIFICATION = "purification";
	private static void createPurificationSet() {
		purificationSet = new HashSet<String>();
		purificationSet.add("aqueous");
		purificationSet.add("combined");
		purificationSet.add("crude");
		purificationSet.add("dry");
		purificationSet.add("organic");
		purificationSet.add("oven-dried");
	}

	public static Set<String> getSizeSet() {
		return sizeSet;
	}
	public static final String SIZE = "size";
	private static void createSizeSet() {
		sizeSet = new HashSet<String>();
		sizeSet.add("small");
		sizeSet.add("large");
		sizeSet.add("medium");
	}

    public POSAdj(Element element) {
    	super(TAG, element);
    	tidy();
    }

    public POSAdj(Element element, String role) {
    	this(element);
    	this.setRole(role);
    }

    private void tidy() {
    	
    	if (TreeBankUtil.isTextOnly(this)) {
    		String text = this.getValue().toLowerCase();
    		if (annotateColor(text)) {
    			
    		} else {
//    			LOG.info("adjective "+text+"; "+this.getParent());
    		}
    	} else {
    		LOG.error("mixed content "+this.toXML());
    	}
    }

	private boolean annotateColor(String text) {
		if (colorSet.contains(text)) {
			this.addAttribute(new Attribute(COLOR, text));
			return true;
		}
		return false;
	}
}
