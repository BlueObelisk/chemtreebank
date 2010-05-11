/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Nodes;

import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLConstants;
import org.xmlcml.cml.base.CMLElement;
import org.xmlcml.cml.base.CMLUtil;

/**
 *
 * @author pm286
 */
public class POSDocument extends POSElement {
	private static Logger LOG = Logger.getLogger(POSDocument.class);

	public final static String TAG = "Document";

	private static final String ID = "id";
    public POSDocument() {
    	super(TAG);
    	addCMLPrefixAndNamespaceDeclaration(this);
    	addCMLUnitsPrefixAndNamespaceDeclaration(this);
    }

    public POSDocument(Element documentElement) {
    	this();
    	this.rawElement = documentElement;
    	CMLUtil.removeWhitespaceNodes(rawElement);
    	addChildren(this);
    	tidy();
    }
    
    private void tidy() {
		Elements childElements = this.getChildElements();
    	if (childElements.size() == 0) {
    		System.out.print(" [NO DOCUMENT CONTENT] ");
    	} else if (this.query(POSSentence.TAG).size() == 0) {
    		insertSentence(childElements);
    	} else if (this.query(POSSentence.TAG).size() != childElements.size()) {
//    		CMLUtil.debug(this, "CHILDREN?");
//    		throw new POSException("Only Sentence children allowed in Document");
    		LOG.error("Only Sentence children allowed in Document");
    	}
    	addIds();
    }

    @Override()
    protected String getTag() {return TAG;}
    
	private void addIds() {
		Set<String> idSet = new HashSet<String>();
		Nodes nodes = this.query("//*[not(@"+ID+")]");
		for (int i = 0; i < nodes.size(); i++) {
			Element element = (Element) nodes.get(i);
			if (element.getAttributeValue(ID) != null) {
				throw new RuntimeException("BUG, should not have id");
			}
			generateAndAddUniqueAttribute(idSet, element, i);
		}
	}

	private void generateAndAddUniqueAttribute(Set<String> idSet, Element element,
			int i) {
		String id = null;
		if (element instanceof POSElement) {
			id = ((POSElement) element).getIdPrefix()+i;
		} else if (element instanceof CMLElement) {
			id = ((CMLElement) element).getLocalName()+i;
		} else {
			throw new RuntimeException("unknown element: "+element.getLocalName());
		}
		// avoid duplicates
		while (idSet.contains(id)) {
			id += "a";
		}
		idSet.add(id);
		element.addAttribute(new Attribute(ID, id));
	}

	private void insertSentence(Elements childElements) {
		POSSentence sentence = new POSSentence(new Element(POSSentence.TAG));
		for (int i = 0; i < childElements.size(); i++) {
			Element child = childElements.get(i);
			child.detach();
			sentence.appendChild(child);
		}
		this.removeChildren();
		this.appendChild(sentence);
		System.out.print(" [SENTENCE INSERTED] ");
	}

	private void addCMLPrefixAndNamespaceDeclaration(POSElement element) {
		element.addNamespaceDeclaration(CMLConstants.CML_PREFIX, CMLConstants.CML_NS);
	}

	private void addCMLUnitsPrefixAndNamespaceDeclaration(POSElement element) {
		element.addNamespaceDeclaration(CMLConstants.CML_UNITS, CMLConstants._UNIT_NS);
	}

	public static POSDocument parseDocument(String filename) {
		POSDocument document = null;
		try {
			document = parseDocument(new FileInputStream(filename));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return document;
	}

	public static POSDocument parseDocument(InputStream inputStream) {
		Element documentElement = null;
		try {
			documentElement = new Builder().build(inputStream).getRootElement();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
        String name = documentElement.getLocalName();
        if (!POSDocument.TAG.equals(name)) {
        	throw new RuntimeException("Must start with Document; found: "+name);
        }
        return new POSDocument(documentElement);
	}

	public List<POSElement> getSentenceList() {
		List<POSElement> sentenceList = new ArrayList<POSElement>();
		Elements childElements = this.getChildElements();
		for (int i = 0; i < childElements.size(); i++) {
			sentenceList.add((POSSentence)childElements.get(i)); 
		}
		return sentenceList;
	}

}
