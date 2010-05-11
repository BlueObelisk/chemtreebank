/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.adj;

import java.util.HashSet;
import java.util.Set;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Nodes;

import org.apache.log4j.Logger;
import org.xmlcml.cml.element.CMLProperty;
import org.xmlcml.cml.element.CMLScalar;
import org.xmlcml.cml.parsetree.POSElement;
import org.xmlcml.cml.parsetree.helpers.TreeBankUtil;
import org.xmlcml.cml.parsetree.helpers.Units;

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
/*
<JJ sf="1.0 M">
  <Number sf="1.0">1.0</Number>
  <NN sf="M" role="MOLAR">M</NN>
</JJ> */
    		
    		Nodes number = this.query("Number");
    		Nodes molar = this.query("NN[@role='MOLAR']");
    		if (number.size() == 1 && molar.size() == 1) {
    			convertMisparsedJJToMolar((Element) number.get(0), (Element) molar.get(0));
    		} else {
    			LOG.error("mixed content "+this.toXML());
    		}
    	}
    }
    
    @Override
    protected String getTag() {return TAG;}
    

	private void convertMisparsedJJToMolar(Element number, Element molar) {
		String value = number.getValue();
		Double molarValue = null;
		try {
			molarValue = new Double(value);
		} catch (Exception e) {
			throw new RuntimeException("not a number: "+value);
		}
		String molarType = molar.getValue();
		CMLProperty property = new CMLProperty();
		property.setDictRef("cmlx:molar");
		CMLScalar scalar = new CMLScalar();
		scalar.setValue(molarValue);
		Units units = Units.getSingleUnits(molarType);
		scalar.setUnits(units.getPrefix(), units.getId(), units.getNamespace());
		property.addScalar(scalar);
		this.removeChildren();
		this.appendChild(property);
		System.out.println(" [FIXED MOLAR] ");
	}

	private boolean annotateColor(String text) {
		if (colorSet.contains(text)) {
			this.addAttribute(new Attribute(COLOR, text));
			return true;
		}
		return false;
	}
}
