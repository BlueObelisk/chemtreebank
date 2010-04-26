/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import nu.xom.Builder;
import nu.xom.Element;
import nu.xom.Elements;

import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLConstants;
import org.xmlcml.cml.base.CMLUtil;

/**
 *
 * @author pm286
 */
public class POSDocument extends POSElement {
	private static Logger LOG = Logger.getLogger(POSDocument.class);

	public final static String TAG = "Document";
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
    	if (this.query(POSSentence.TAG).size() != this.getChildElements().size()) {
//    		CMLUtil.debug(this, "CHILDREN?");
//    		throw new POSException("Only Sentence children allowed in Document");
    		LOG.error("Only Sentence children allowed in Document");
    	}
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
