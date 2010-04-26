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

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

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

    public ListMultimap<String, String> mapLeafContentToNodeName(POSElement posElement) {
        ListMultimap<String, String> nodeNameContentMap = ArrayListMultimap.create();
        Nodes nodes = posElement.query(".//*[count(*)=0]");
        for (int i = 0; i < nodes.size(); i++) {
            Element element = (Element) nodes.get(i);
            String matchedString = caseInsensitive ? element.getValue().toLowerCase() : element.getValue();
            nodeNameContentMap.put(element.getLocalName(), matchedString);
        }
        return nodeNameContentMap;
    }

    public ListMultimap<String, String> mapLeafContentToNodeName(List<POSElement> elementList) {
        ListMultimap<String, String> nodeNameContentMap = ArrayListMultimap.create();
        for (POSElement element : elementList) {
            ListMultimap<String, String> elementMap = this.mapLeafContentToNodeName(element);
            nodeNameContentMap.putAll(elementMap);
        }
        return nodeNameContentMap;
    }

    public ListMultimap<String, Element> mapNonLeafContentToNodeName(POSElement posElement) {
        ListMultimap<String, Element> nodeNameElementMap = ArrayListMultimap.create();
        Nodes nodes = posElement.query(".//*[not(count(*)=0)]");
        for (int i = 0; i < nodes.size(); i++) {
            Element element = new Element((Element) nodes.get(i));
            if (stripSf) {
//            	stripNodes(element, ".//@sf");
            	stripNodes(element, ".//@*");
            }
            nodeNameElementMap.put(element.getLocalName(), element);
        }
        return nodeNameElementMap;
    }

    private void stripNodes(Element element, String xpath) {
    	Nodes nodesToStrip = element.query(xpath);
    	for (int i = 0; i < nodesToStrip.size(); i++) {
    		nodesToStrip.get(i).detach();
    	}
	}

	public ListMultimap<String, Element> mapNonLeafContentToNodeName(List<POSElement> posElements) {
        ListMultimap<String, Element> nodeNameElementMap = ArrayListMultimap.create();
        for (POSElement posElement : posElements) {
            ListMultimap<String, Element> elementMap = mapNonLeafContentToNodeName(posElement);
            nodeNameElementMap.putAll(elementMap);
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
