package org.xmlcml.cml.parsetree.helpers;

import java.util.ArrayList;
import java.util.List;

import nu.xom.Element;
import nu.xom.Nodes;

import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLConstants;
import org.xmlcml.cml.element.CMLScalar;
import org.xmlcml.cml.parsetree.POSElement;
import org.xmlcml.cml.parsetree.adj.POSAdj;
import org.xmlcml.cml.parsetree.helpers.Units.Name;
import org.xmlcml.cml.parsetree.helpers.Units.Type;
import org.xmlcml.cml.parsetree.number.POSNumber;

public class TreeBankUtil {

	private static Logger LOG = Logger.getLogger(TreeBankUtil.class);
	
	public static List<Element> findNodesRelatedByXPathAndAxes(Element parent,
			String xpath1, String xpath2, String axes1, String axes2) {
		List<Element> elementList = null;
		Nodes nodes1 = parent.query(xpath1);
		if (nodes1.size() > 0) {
			Element child1 = (Element) nodes1.get(0);
			String fullXpath2 = xpath1+axes1+xpath2+axes2;
			Nodes nodes2 = parent.query(fullXpath2);
			if (nodes2.size() > 0) {
				Element child2 = (Element) nodes2.get(0);
				elementList = new ArrayList<Element>();
				elementList.add(child1);
				elementList.add(child2);
			}
		}
		return elementList;
	}

	public static void cannotInterpret(Type type, POSElement element) {
		LOG.error("Cannot interpret ("+type+") "+element.toXML());
	}

	public static void concatenateSiblingContent(Element parent, Element first, Element second) {
		if (parent.indexOf(second) - parent.indexOf(first) == 1) {
			String firstVal = first.getValue();
			first.removeChildren();
			first.appendChild(firstVal+" "+second.getValue());
			second.detach();
		}
	}

	public static List<Element> findChildElementAndImmediatelyFollowingSibling(
			Element parent, String xpath1, String xpath2) {
		return findNodesRelatedByXPathAndAxes(
				parent, xpath1, xpath2, "/following-sibling::*[self::", "]");
	}

    /*
    <Number>4</Number>
    <NN role="TIME">h</NN>
    ==>
    <cml:scalar units='units:hour' dataType='xsd:double'>4</cml:scalar>
*/
	public static void transformChildNumberAndNNRoleToScalar(
			POSElement posElement, Type type, String otherString) {
		List<Element> neighbours = TreeBankUtil.findChildElementAndImmediatelyFollowingSibling(
    			posElement, "Number", "NN[@role='"+type+
    			"' or @role='"+otherString+"']");
    	if (neighbours != null) {
    		String unitValue = neighbours.get(1).getValue();
    		Units units = Units.getUnits(type, unitValue);
    		if (units == null) {
    			LOG.error("bad units ("+type+", "+unitValue+") "+posElement.toXML());
    			return;
    		}
    		Double d = ((POSNumber)neighbours.get(0)).getDouble();
    		if (d != null) {
	    		CMLScalar scalar = createScalar(d, units);
	    		posElement.appendChild(scalar);
	    		neighbours.get(0).detach();
	    		neighbours.get(1).detach();
    		}
    	}
	}

	public static Element concatenateSiblingContent(Element parent, String xpath1, String xpath2) {
		Element remainingElement = null;
		List<Element> neighbours = findChildElementAndImmediatelyFollowingSibling(
				parent, xpath1, xpath2);
		if (neighbours != null) {
			concatenateSiblingContent(parent, neighbours.get(0), neighbours.get(1));
			remainingElement = neighbours.get(0);
		}
		return remainingElement;
	}

	public static void concatenateSiblingContent(Element element, List<Element> neighbours) {
		if (neighbours != null && neighbours.size() == 2) {
			concatenateSiblingContent(element, neighbours.get(0), neighbours.get(1));
		}
	}

	public static CMLScalar createScalar(double d, Units units) {
		CMLScalar scalar = new CMLScalar(d);
		scalar.setUnits(units.getPrefix(), units.getId(), units.getNamespace());
		return scalar;
	}

	public static List<Element> getConsecutiveSiblings(POSElement parent, String[] xpaths) {
		List<Element> neighbours = null;
		for (int i = 0; i < xpaths.length-1; i++) {
			List<Element> neighbourPair = findChildElementAndImmediatelyFollowingSibling(
					parent, xpaths[i], xpaths[i+1]);
			if (neighbourPair != null) {
				if (neighbours == null) {
					neighbours = neighbourPair;
				} else if (neighbourPair.get(0).equals(neighbours.get(neighbours.size()-1))) {
					neighbours.add(neighbourPair.get(1));
				} else {
					neighbours = null;
					break;
				}
			} else {
				neighbours = null;
				break;
			}
		}
		return neighbours;
	}


	public static String concatenateConsecutiveSiblings(int retainedElement, POSElement parent, String[] xpaths) {
		List<Element> siblings = getConsecutiveSiblings(parent, xpaths);
		String value = null;
		if (siblings != null) {
			StringBuilder sb = new StringBuilder(siblings.get(0).getValue());
			if (retainedElement != 0) {
				siblings.get(0).detach();
			}
			for (int i = 1; i < siblings.size(); i++) {
				sb.append(CMLConstants.S_SPACE);
				sb.append(siblings.get(i).getValue());
				if (retainedElement != i) {
					siblings.get(i).detach();
				}
			}
			value = sb.toString();
			if (retainedElement >= 0 && retainedElement < siblings.size()) {
				siblings.get(retainedElement).removeChildren();
				siblings.get(retainedElement).appendChild(value);
			}
		}
		return value;
	}
	
	public static void detachElements(List<Element> neighbours) {
		for (int i = 0; i < neighbours.size(); i++) {
			neighbours.get(i).detach();
		}
	}

	public static void concatenateSiblingChildren(POSElement parent, String[] xpaths) {
		List<Element> elements = getConsecutiveSiblings(
				parent, xpaths);
		if (elements != null) {
			concatenateSiblingContent(parent, elements);
		}
	}

	public static void concatenateContent(String preValue, Element element) {
		String value = element.getValue();
		value = preValue+value;
		element.removeChildren();
		element.appendChild(value);
	}

	public static boolean isTextOnly(POSElement element) {
		return (element.query("text()").size() == 1 && element.getChildCount() == 1);
	}

	public static CMLScalar replaceStringContainerByScalar(Element stringContainer, double doubleValue, Name unitName) {
		CMLScalar scalar = new CMLScalar(doubleValue);
		Units units = Units.getUnits(unitName);
		scalar.setUnits(units.getPrefix(), units.getId(), units.getNamespace());
		stringContainer.getParent().replaceChild(stringContainer, scalar);
		return scalar;
	}
	
}
