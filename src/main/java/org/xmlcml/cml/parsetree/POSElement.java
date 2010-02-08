/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree;

import java.util.ArrayList;

import java.util.List;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Node;
import nu.xom.Nodes;

import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLConstants;
import org.xmlcml.cml.parsetree.adj.POSAdj;
import org.xmlcml.cml.parsetree.adj.POSJjr;
import org.xmlcml.cml.parsetree.adj.POSJjt;
import org.xmlcml.cml.parsetree.adj.POSOscarCJ;
import org.xmlcml.cml.parsetree.adverb.POSAdverb;
import org.xmlcml.cml.parsetree.adverb.POSRbr;
import org.xmlcml.cml.parsetree.adverb.POSRbs;
import org.xmlcml.cml.parsetree.helpers.ListMap;
import org.xmlcml.cml.parsetree.noun.POSAmount;
import org.xmlcml.cml.parsetree.noun.POSApparatus;
import org.xmlcml.cml.parsetree.noun.POSMass;
import org.xmlcml.cml.parsetree.noun.POSMixture;
import org.xmlcml.cml.parsetree.noun.POSMolecule;
import org.xmlcml.cml.parsetree.noun.POSNNP;
import org.xmlcml.cml.parsetree.noun.POSNnMass;
import org.xmlcml.cml.parsetree.noun.POSNoun;
import org.xmlcml.cml.parsetree.noun.POSNouns;
import org.xmlcml.cml.parsetree.noun.POSOscarCD;
import org.xmlcml.cml.parsetree.noun.POSOscarCM;
import org.xmlcml.cml.parsetree.noun.POSPercent;
import org.xmlcml.cml.parsetree.noun.POSQuantity;
import org.xmlcml.cml.parsetree.noun.POSVolume;
import org.xmlcml.cml.parsetree.number.POSCd;
import org.xmlcml.cml.parsetree.phrase.POSAtmospherePhrase;
import org.xmlcml.cml.parsetree.phrase.POSNounPhrase;
import org.xmlcml.cml.parsetree.phrase.POSPrepPhrase;
import org.xmlcml.cml.parsetree.phrase.POSTempPhrase;
import org.xmlcml.cml.parsetree.phrase.POSTimePhrase;
import org.xmlcml.cml.parsetree.phrase.POSVerbPhrase;
import org.xmlcml.cml.parsetree.pos.misc.POSCc;
import org.xmlcml.cml.parsetree.pos.misc.POSDt;
import org.xmlcml.cml.parsetree.pos.misc.POSFw;
import org.xmlcml.cml.parsetree.pos.misc.POSUnknown;
import org.xmlcml.cml.parsetree.pos.misc.POSUnmatched;
import org.xmlcml.cml.parsetree.pos.misc.POSWdt;
import org.xmlcml.cml.parsetree.prep.POSIn;
import org.xmlcml.cml.parsetree.prep.POSTo;
import org.xmlcml.cml.parsetree.punct.POSColon;
import org.xmlcml.cml.parsetree.punct.POSComma;
import org.xmlcml.cml.parsetree.punct.POSDash;
import org.xmlcml.cml.parsetree.punct.POSLrb;
import org.xmlcml.cml.parsetree.punct.POSRrb;
import org.xmlcml.cml.parsetree.punct.POSStop;
import org.xmlcml.cml.parsetree.verb.POSVBD;
import org.xmlcml.cml.parsetree.verb.POSVBG;
import org.xmlcml.cml.parsetree.verb.POSVBN;
import org.xmlcml.cml.parsetree.verb.POSVerb;


/**
 *
 * @author pm286
 * 
 * element representing part of speech 
 * 
 * As the Brown tagger is used we could expect:
 * ABL 	pre-qualifier (quite, rather)
ABN 	pre-quantifier (half, all)
ABX 	pre-quantifier (both)
AP 	post-determiner (many, several, next)
AT 	article (a, the, no)
BE 	be
BED 	were
BEDZ 	was
BEG 	being
BEM 	am
BEN 	been
BER 	are, art
BEZ 	is
CC 	coordinating conjunction (and, or)
CD 	cardinal numeral (one, two, 2, etc.)
CS 	subordinating conjunction (if, although)
DO 	do
DOD 	did
DOZ 	does
DT 	singular determiner/quantifier (this, that)
DTI 	singular or plural determiner/quantifier (some, any)
DTS 	plural determiner (these, those)
DTX 	determiner/double conjunction (either)
EX 	existential there
FW 	foreign word (hyphenated before regular tag)
HV 	have
HVD 	had (past tense)
HVG 	having
HVN 	had (past participle)
IN 	preposition
JJ 	adjective
JJR 	comparative adjective
JJS 	semantically superlative adjective (chief, top)
JJT 	morphologically superlative adjective (biggest)
MD 	modal auxiliary (can, should, will)
NC 	cited word (hyphenated after regular tag)
NN 	singular or mass noun
NN$ 	possessive singular noun
NNS 	plural noun
NNS$ 	possessive plural noun
NP 	proper noun or part of name phrase
NP$ 	possessive proper noun
NPS 	plural proper noun
NPS$ 	possessive plural proper noun
NR 	adverbial noun (home, today, west)
OD 	ordinal numeral (first, 2nd)
PN 	nominal pronoun (everybody, nothing)
PN$ 	possessive nominal pronoun
PP$ 	possessive personal pronoun (my, our)
PP$$ 	second (nominal) possessive pronoun (mine, ours)
PPL 	singular reflexive/intensive personal pronoun (myself)
PPLS 	plural reflexive/intensive personal pronoun (ourselves)
PPO 	objective personal pronoun (me, him, it, them)
PPS 	3rd. singular nominative pronoun (he, she, it, one)
PPSS 	other nominative personal pronoun (I, we, they, you)
QL 	qualifier (very, fairly)
QLP 	post-qualifier (enough, indeed)
RB 	adverb
RBR 	comparative adverb
RBT 	superlative adverb
RN 	nominal adverb (here, then, indoors)
RP 	adverb/particle (about, off, up)
TO 	infinitive marker to
UH 	interjection, exclamation
VB 	verb, base form
VBD 	verb, past tense
VBG 	verb, present participle/gerund
VBN 	verb, past participle
VBZ 	verb, 3rd. singular present
WDT 	wh- determiner (what, which)
WP$ 	possessive wh- pronoun (whose)
WPO 	objective wh- pronoun (whom, which, that)
WPS 	nominative wh- pronoun (who, which, that)
WQL 	wh- qualifier (how)
WRB 	wh- adverb (how, where, when)
 */
public abstract class POSElement extends Element {
	private static final Logger LOG = Logger.getLogger(POSElement.class);

	public static final String ROLE = "role";
	public static final String SURFACE = "sf";
	
	protected Element rawElement;
	
	public POSElement(String tag, Element rawElement) {
		super(tag);
		this.rawElement = rawElement;
		setSurface(rawElement);
		addChildren(this);
	}

	protected POSElement(String tag) {
		super(tag);
	}

	public void setRole(String role) {
		this.addAttribute(new Attribute(ROLE, role));
	}
	
	public String getRole() {
		return this.getAttributeValue(ROLE);
	}
	
	public void setSurface(String surface) {
		this.addAttribute(new Attribute(SURFACE, surface));
	}

	private void setSurface(Element element) {
		Nodes texts = element.query(".//text()");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < texts.size(); i++) {
			sb.append(texts.get(i).getValue());
			if (i < texts.size()-1) {
				sb.append(CMLConstants.S_SPACE);
			}
		}
		this.setSurface(sb.toString());
	}

	public String getSurface() {
		return this.getAttributeValue(SURFACE);
	}

	protected void addChildren(POSElement newParent) {
    	Elements childRawElements = rawElement.getChildElements();
    	Nodes childTexts = rawElement.query("./text()");
    	if (childTexts.size() == 1 && childRawElements.size() == 0) {
    		processText(childTexts.get(0));
    	} else if (childTexts.size() == 0) {
	    	for (int i = 0; i < childRawElements.size(); i++) {
	    		Element delegateChild = childRawElements.get(i);
	    		POSElement parseNode = POSElement.createNode(delegateChild);
	    		if (parseNode != null) {
	    			newParent.appendChild(parseNode);
	    		}
	    	}
    	}
    }

	protected void processText(Node text) {
		String textS = text.getValue();
		if (this.getValue().equals(CMLConstants.S_EMPTY)) {
			this.appendChild(textS);
		}
	}
	
	public static POSElement createNode(Element rawElement) {
		POSElement posElement = null;
		String tag = rawElement.getLocalName();
		if (tag == null) {
			throw new RuntimeException("Null tag");
		} else if (tag.equals(POSAdj.TAG)) {
			posElement = new POSAdj(rawElement);
		} else if (tag.equals(POSAdverb.TAG)) {
			posElement = new POSAdverb(rawElement);
		} else if (tag.equals(POSAmount.TAG)) {
			posElement = new POSAmount(rawElement);
		} else if (tag.equals(POSApparatus.TAG)) {
			posElement = new POSApparatus(rawElement);
		} else if (tag.equals(POSAtmospherePhrase.TAG)) {
			posElement = new POSAtmospherePhrase(rawElement);
		} else if (tag.equals(POSCc.TAG)) {
			posElement = new POSCc(rawElement);
		} else if (tag.equals(POSCd.TAG)) {
			posElement = new POSCd(rawElement);
		} else if (tag.equals(POSColon.TAG)) {
			posElement = new POSColon(rawElement);
		} else if (tag.equals(POSComma.TAG)) {
			posElement = new POSComma(rawElement);
		} else if (tag.equals(POSDash.TAG)) {
			posElement = new POSDash(rawElement);
		} else if (tag.equals(POSDocument.TAG)) {
			posElement = new POSDocument(rawElement);
		} else if (tag.equals(POSDt.TAG)) {
			posElement = new POSDt(rawElement);
		} else if (tag.equals(POSFw.TAG)) {
			posElement = new POSFw(rawElement);
		} else if (tag.equals(POSIn.TAG)) {
			posElement = new POSIn(rawElement);
		} else if (tag.equals(POSJjr.TAG)) {
			posElement = new POSJjr(rawElement);
		} else if (tag.equals(POSJjt.TAG)) {
			posElement = new POSJjt(rawElement);
		} else if (tag.equals(POSLrb.TAG)) {
			posElement = new POSLrb(rawElement);
		} else if (tag.equals(POSMass.TAG)) {
			posElement = new POSMass(rawElement);
		} else if (tag.equals(POSMixture.TAG)) {
			posElement = new POSMixture(rawElement);
		} else if (tag.equals(POSMolecule.TAG)) {
			posElement = new POSMolecule(rawElement);
		} else if (tag.equals(POSOscarCD.TAG)) {
			posElement = new POSOscarCD(rawElement);
		} else if (tag.equals(POSOscarCJ.TAG)) {
			posElement = new POSOscarCJ(rawElement);
		} else if (tag.equals(POSOscarCM.TAG) ||
				tag.equals(POSOscarCM.TAG1)) {
			posElement = new POSOscarCM(rawElement);
		} else if (tag.equals(POSNNP.TAG)) {
			posElement = new POSNNP(rawElement);
		} else if (tag.equals(POSNoun.TAG)) {
			posElement = new POSNoun(rawElement);
		} else if (tag.equals(POSNouns.TAG)) {
			posElement = new POSNouns(rawElement);
		} else if (tag.equals(POSNnMass.TAG)) {
			posElement = new POSNnMass(rawElement);
		} else if (tag.equals(POSNounPhrase.TAG)) {
			posElement = new POSNounPhrase(rawElement);
		} else if (tag.equals(POSPercent.TAG)) {
			posElement = new POSPercent(rawElement);
		} else if (tag.equals(POSPrepPhrase.TAG)) {
			posElement = new POSPrepPhrase(rawElement);
		} else if (tag.equals(POSQuantity.TAG)) {
			posElement = new POSQuantity(rawElement);
		} else if (tag.equals(POSRbr.TAG)) {
			posElement = new POSRbr(rawElement);
		} else if (tag.equals(POSRbs.TAG)) {
			posElement = new POSRbs(rawElement);
		} else if (tag.equals(POSRrb.TAG)) {
			posElement = new POSRrb(rawElement);
		} else if (tag.equals(POSSentence.TAG)) {
			posElement = new POSSentence(rawElement);
		} else if (tag.equals(POSStop.TAG)) {
			posElement = new POSStop(rawElement);
		} else if (tag.equals(POSTempPhrase.TAG)) {
			posElement = new POSTempPhrase(rawElement);
		} else if (tag.equals(POSTimePhrase.TAG)) {
			posElement = new POSTimePhrase(rawElement);
		} else if (tag.equals(POSTo.TAG)) {
			posElement = new POSTo(rawElement);
		} else if (tag.equals(POSVBD.TAG)) {
			posElement = new POSVBD(rawElement);
		} else if (tag.equals(POSVBG.TAG)) {
			posElement = new POSVBG(rawElement);
		} else if (tag.equals(POSVBN.TAG)) {
			posElement = new POSVBN(rawElement);
		} else if (tag.equals(POSVerb.TAG)) {
			posElement = new POSVerb(rawElement);
		} else if (tag.equals(POSVerbPhrase.TAG)) {
			posElement = new POSVerbPhrase(rawElement);
		} else if (tag.equals(POSVolume.TAG)) {
			posElement = new POSVolume(rawElement);
		} else if (tag.equals(POSWdt.TAG)) {
			posElement = new POSWdt(rawElement);
			
		} else if (tag.equals(POSUnmatched.TAG)) {
			posElement = new POSUnmatched(rawElement);
			
		} else if (tag.startsWith(POSIn.PREFIX)) {
			posElement = new POSIn(rawElement, behead(tag, POSIn.PREFIX));
		} else if (tag.startsWith(POSNoun.PREFIX)) {
			posElement = new POSNoun(rawElement, behead(tag, POSNoun.PREFIX));
		} else if (tag.startsWith(POSVerb.PREFIX)) {
			posElement = new POSVerb(rawElement, behead(tag, POSVerb.PREFIX));
			
		} else if (tag.equals(POSMolecule.UNNAMEDMOLECULE)) {
			posElement = new POSMolecule(rawElement, tag);
			
		} else if (tag.equals("INMolecule")) {
			posElement = new POSUnknown(rawElement);
			
		} else {
			throw new POSException("Unknown element: "+tag);
//			LOG.error("Unknown element: "+tag);
//			posElement = new POSUnknown(rawElement);
		}
		return posElement;
	}

	private static String behead(String tag, String prefix) {
		return tag.substring(prefix.length());
	}

	public void setSkipped() {
		this.addAttribute(new Attribute("SKIPPED", "SKIPPED"));
	}

	protected List<Object> processChildren(Element element) {
		List<Object> list = new ArrayList<Object>();
		Elements childElements = element.getChildElements();
		for (int i = 0; i < childElements.size(); i++) {
			Element child = childElements.get(i);
			Object object = processChild(child);
			if (object == null) {
				throw new POSException("Cannot process child: "+child.toXML());
			}
			list.add(object);
		}
		return list;
	}

	protected Object processChild(Element child) {
		return null;
	}

	protected List<POSElement> getPOSElementOnlyChildren() {
		Elements elements = this.getChildElements();
		if (this.getChildCount() != elements.size()) {
			throw new POSException("mixed content not allowed: "+this.toXML());
		}
		List<POSElement> elementList = new ArrayList<POSElement>();
		for (int i = 0; i < elements.size(); i++) {
			if (!(elements.get(i) instanceof POSElement)) {
				throw new POSException("non-POS element children: "+this.toXML());
			}
			elementList.add((POSElement)elements.get(i));
		}
		return elementList;
	}

}
