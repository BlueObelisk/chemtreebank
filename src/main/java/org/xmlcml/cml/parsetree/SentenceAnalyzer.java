/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree;

import java.util.List;

import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Nodes;

import org.xmlcml.cml.parsetree.helpers.CounterMap;
import org.xmlcml.cml.parsetree.helpers.ListMap;

/**
 *
 * @author pm286
 */
public class SentenceAnalyzer {

	private boolean caseInsensitive = true;
	private boolean stripSf = true;

	public SentenceAnalyzer() {
    }
	
	public boolean isCaseInsensitive() {
		return caseInsensitive;
	}

	public void setCaseInsensitive(boolean caseInsensitive) {
		this.caseInsensitive = caseInsensitive;
	}

	public boolean isStripSf() {
		return stripSf;
	}

	public void setStripSf(boolean stripSf) {
		this.stripSf = stripSf;
	}

    public ListMap<String, String> mapLeafContentToNodeName(POSElement posElement) {
        ListMap<String, String> nodeNameContentMap = new ListMap<String, String>();
        Nodes nodes = posElement.query(".//*[count(*)=0]");
        for (int i = 0; i < nodes.size(); i++) {
            Element element = (Element) nodes.get(i);
            String matchedString = caseInsensitive ? element.getValue().toLowerCase() : element.getValue();
            nodeNameContentMap.add(element.getLocalName(), matchedString);
        }
        return nodeNameContentMap;
    }

    public ListMap<String, String> mapLeafContentToNodeName(List<POSElement> elementList) {
        ListMap<String, String> nodeNameContentMap = new ListMap<String, String>();
        for (POSElement element : elementList) {
            ListMap<String, String> elementMap = this.mapLeafContentToNodeName(element);
            nodeNameContentMap.add(elementMap);
        }
        return nodeNameContentMap;
    }

    public ListMap<String, Element> mapNonLeafContentToNodeName(POSElement posElement) {
        ListMap<String, Element> nodeNameElementMap = new ListMap<String, Element>();
        Nodes nodes = posElement.query(".//*[not(count(*)=0)]");
        for (int i = 0; i < nodes.size(); i++) {
            Element element = new Element((Element) nodes.get(i));
            if (stripSf) {
//            	stripNodes(element, ".//@sf");
            	stripNodes(element, ".//@*");
            }
            nodeNameElementMap.add(element.getLocalName(), element);
        }
        return nodeNameElementMap;
    }

    private void stripNodes(Element element, String xpath) {
    	Nodes nodesToStrip = element.query(xpath);
    	for (int i = 0; i < nodesToStrip.size(); i++) {
    		nodesToStrip.get(i).detach();
    	}
	}

	public ListMap<String, Element> mapNonLeafContentToNodeName(List<POSElement> posElements) {
        ListMap<String, Element> nodeNameElementMap = new ListMap<String, Element>();
        for (POSElement posElement : posElements) {
            ListMap<String, Element> elementMap = mapNonLeafContentToNodeName(posElement);
            nodeNameElementMap.add(elementMap);
        }
        return nodeNameElementMap;
    }
    
    
    public CounterMap<POSElement> getNodeIndex(POSElement posElement) {
        CounterMap<POSElement> counterMap = new CounterMap<POSElement>();
        counterMap.add(posElement, 1);
        Elements childElements = posElement.getChildElements();
        for (int i = 0; i < childElements.size(); i++) {
        	Element childElement = childElements.get(i);
        	if (childElement instanceof POSElement) {
	            POSElement childNode = (POSElement)childElement;
	            CounterMap<POSElement> cmap = getNodeIndex(childNode);
	            for (POSElement pn : cmap.keySet()) {
	                Integer count = cmap.getCount(pn);
	                counterMap.add(pn, count);
	            }
        	}
        }
        return counterMap;
    }
}
