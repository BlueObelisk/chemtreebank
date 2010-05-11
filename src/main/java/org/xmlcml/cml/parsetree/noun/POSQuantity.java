/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.noun;

import java.util.List;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Nodes;
import nu.xom.XPathTypeException;

import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLConstants;
import org.xmlcml.cml.base.CMLUtil;
import org.xmlcml.cml.element.CMLAmount;
import org.xmlcml.cml.element.CMLScalar;
import org.xmlcml.cml.parsetree.POSElement;
import org.xmlcml.cml.parsetree.POSException;
import org.xmlcml.cml.parsetree.helpers.Units;
import org.xmlcml.cml.parsetree.helpers.Units.Type;
import org.xmlcml.cml.parsetree.punct.POSLrb;
import org.xmlcml.cml.parsetree.punct.POSPunct;
import org.xmlcml.cml.parsetree.punct.POSRrb;

/**
 *
 * @author pm286
 */
public class POSQuantity extends POSNoun {
	private static final String NN = "NN";
	private static final String ROLE = "role";
	private static final String NUMBER = "Number";
	private static final String PERCENT = "PERCENT";

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
//					throw new POSException("no role on NN "+this.toXML());
					LOG.error("no role on NN "+this.toXML());
				} else {
					createCMLAmount(pos);
				}
				if (brackets) {
					pos = childElements.get(i++);
					if (!(pos instanceof POSPunct) && this.getAttributeValue("times") == null) {
//						throw new POSException("expected punct "+i+" "+this.toXML());
						LOG.error("*********** expected punct "+i+" "+this.toXML()+" ... "+pos.toXML());
					}
					pos.detach();
				}
			}
		}
	}

	private void createCMLAmount(POSElement pos) {
		Nodes scalars = pos.query("./cml:scalar", CMLConstants.CML_XPATH);
		if (scalars.size() != 1) {
			if (processNestedNN()) {
			} else if (processTimes()) {
			} else {
				CMLUtil.debug((Element)this, "THIS");
				LOG.error("Quantity must have scalar children "+this.toXML());
			}
		} else {
			CMLAmount amount = new CMLAmount();
			amount.appendChild(scalars.get(0));
			amount.addAttribute(new Attribute(POSElement.ROLE, pos.getRole()));
			this.setSurface(this.getValue());
			pos.detach();
			this.appendChild(amount);
		}
	}

	private boolean processNestedNN() {
		/*
		<NN sf="64 %" role="QUANTITY">
		  <NN sf="64 %" role="PERCENT">
		    <Number sf="64">64</Number>
		    <NN sf="%" role="PERCENT">%</NN>
		  </NN>
		</NN>
		*/
		/*
		<NN sf="( 0.42 cm3 )" role="QUANTITY">
		  <NN sf="0.42 cm3" role="VOLUME">
		    <Number sf="0.42">0.42</Number>
		    <NN sf="cm3" role="VOL">cm3</NN>
		  </NN>
		</NN>
		 */
		
		Nodes nn = this.query("NN[@role and Number and NN[@role]]");
		if (nn.size() == 1) {
			Element element = (Element)nn.get(0);
			String roleType = element.getAttributeValue(ROLE);
			String value = element.getChildElements(NUMBER).get(0).getValue();
			String unitsValue = element.getChildElements(NN).get(0).getValue();
			CMLScalar scalar = new CMLScalar();
			Double doubleValue = null;
			try {
				doubleValue = new Double(value);
				scalar.setValue(doubleValue);
			} catch (Exception e) {
				value = "??"+value+"??";
				scalar.setValue(value);
			}
			List<Units> unitList = Units.getPossibleUnits(unitsValue);
			if (unitList.size() == 1) {
				Units units = unitList.get(0);
				Type type = units.getType();
				if (units.getType().equals(roleType)) {
					scalar.setUnits(units.getPrefix(), units.getId(), units.getNamespace());
				} else if (units.getType().toString().equals(Type.RATIO.toString()) && roleType.equals(PERCENT)) {
				} else {
					LOG.warn("Type inconsistent: "+units.getType()+" != "+roleType);
				}
				element.getParent().replaceChild(element, scalar);
			}
		}
		return nn.size() == 1;
	}
/*	
	<NN sf="( 3 × 50 ml )" role="QUANTITY">
	  <Number sf="3">3</Number>
	  <NN sf="×" role="times">×</NN>
	  <NN sf="50 ml" role="VOLUME">
	    <scalar xmlns="http://www.xml-cml.org/schema" xmlns:units="http://www.xml-cml.org/units" dataType="xsd:double" units="units:millilitre">50.0</scalar>
	  </NN>
	</NN>
	
<NN sf="( 3 Ã— 50 ml )" role="QUANTITY">
  <Number sf="3">3</Number>
  <NN sf="Ã—" role="times">Ã—</NN>
  <NN sf="50 ml" role="VOLUME">
    <scalar dataType="xsd:double" units="units:millilitre" xmlns="http://www.xml-cml.org/schema" xmlns:units="http://www.xml-cml.org/units">50.0</scalar>
  </NN>
</NN>


<NN sf="2 × 15 ml" role="QUANTITY" times="2">
  <scalar xmlns="http://www.xml-cml.org/schema" xmlns:units="http://www.xml-cml.org/units" dataType="xsd:double" units="units:millilitre" role="VOLUME">15.0</scalar>
</NN>

<NN sf="( 3 × 60 ml )" role="QUANTITY">
  <Number sf="3">3</Number>
  <NN sf="×" role="times">×</NN>
  <NN sf="60 ml" role="VOLUME">
    <scalar xmlns="http://www.xml-cml.org/schema" xmlns:units="http://www.xml-cml.org/units" dataType="xsd:double" units="units:millilitre">60.0</scalar></NN></NN> ... <NN sf="60 ml" role="VOLUME"><scalar xmlns="http://www.xml-cml.org/schema" xmlns:units="http://www.xml-cml.org/units" dataType="xsd:double" units="units:millilitre">60.0</scalar>
  </NN>

*/
	private boolean processTimes() {
		Nodes nn = null;
		boolean ok = false;
		try {
			nn = this.query("self::NN[*[local-name()='"+CMLScalar.TAG+"']]");
//			nn = this.query("self::NN");
			ok = nn.size() > 0;
		} catch (XPathTypeException e) {
			CMLUtil.debug(this, "XXXXXXXXXXXXXX");
		}
		if (!ok) {
			nn = this.query(
				"self::NN" +
				"  [@role='QUANTITY' and" +
				"    Number and" +
				"    NN[@role='times'] and " +
				"    NN[@role and " +
				"      *[local-name()='"+CMLScalar.TAG+"']]" +
				"  ]");
			ok = nn.size() > 0;
			if (ok) {
				Elements elements = this.getChildElements();
				this.addAttribute(new Attribute("times", elements.get(0).getValue()));
				elements.get(0).detach();
				elements.get(1).detach();
				Element element2 = elements.get(2);
				CMLScalar scalar = (CMLScalar) element2.getChildElements().get(0);
				element2.detach();
				scalar.detach();
				scalar.addAttribute(new Attribute("role", element2.getAttributeValue("role")));
				this.appendChild(scalar);
			}
		}
		return ok;
	}
}
