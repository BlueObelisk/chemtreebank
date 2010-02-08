/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.phrase;


import java.util.List;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Nodes;

import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLConstants;
import org.xmlcml.cml.element.CMLMolecule;
import org.xmlcml.cml.element.CMLMoleculeList;
import org.xmlcml.cml.element.CMLScalar;
import org.xmlcml.cml.parsetree.POSElement;
import org.xmlcml.cml.parsetree.POSException;
import org.xmlcml.cml.parsetree.adj.POSAdj;
import org.xmlcml.cml.parsetree.helpers.NumberHelper;
import org.xmlcml.cml.parsetree.helpers.TreeBankUtil;
import org.xmlcml.cml.parsetree.helpers.Units;
import org.xmlcml.cml.parsetree.helpers.Units.Type;
import org.xmlcml.cml.parsetree.noun.POSNoun;
import org.xmlcml.cml.parsetree.pos.misc.POSCc;
import org.xmlcml.cml.parsetree.punct.POSPunct;

/**
 *
 * @author pm286
 */
public class POSNounPhrase extends POSPhrase {
	private static Logger LOG = Logger.getLogger(POSNounPhrase.class);

	public final static String TAG = "NounPhrase";

	public static final String PHASE = "phase";
	public static final String PHASES = "phases";


    public POSNounPhrase(Element element) {
    	super(TAG, element);
    	tidy();
    }

    /*
        <NounPhrase>
          <DT>the</DT>
          <NN role="CHEMENTITY">reaction</NN>
          <NN role="MIXTURE">mixture</NN>
        </NounPhrase>
     */
    private void tidy() {
    	interpretNumberedQuantity();
    	concatenateCompoundNouns();
    	concatenateAdjectiveNoun();
    	concatenateDegrees();
    	createScalars();
    	createMoleculeList();
    }

	private void interpretNumberedQuantity() {
		/*
        <Number surface="18">18</Number>
        <NN surface="hrs." role="TIME">hrs.</NN>
		 */
		List<Element> neighbours = TreeBankUtil.getConsecutiveSiblings(this, new String[] {"Number", "NN"});
		if (neighbours != null) {
			POSElement number = (POSElement) neighbours.get(0);
			POSElement unit = (POSElement) neighbours.get(1);
			Double d = NumberHelper.getDouble(number);
			if (d == null) {
				throw new POSException("Unknown number: "+number.getValue());
			}
			Units units = Units.guessUnits(unit.getValue());
			if (units == null) {
				throw new POSException("Unknown units: "+unit.getValue());
			}
			Type type = units.getType();
			CMLScalar scalar = TreeBankUtil.createScalar(d, units);
			this.appendChild(scalar);
			number.detach();
			unit.detach();
			this.addAttribute(new Attribute(PHRASE, type.toString()));
//			if (type.equals(Type.TIME)) {
//				copyChildrenAndReplaceParent(new POSTimePhrase(this.rawElement));
//			} else if (type.equals(Type.TEMPERATURE)) {
//				copyChildrenAndReplaceParent(new POSTempPhrase(this.rawElement));
//			}
		}
	}


	private void copyChildrenAndReplaceParent(POSElement phrase) {
		Elements childElements = this.getChildElements();
		for (int i = 0; i < childElements.size(); i++) {
			childElements.get(i).detach();
			phrase.appendChild(childElements.get(i));
		}
		this.getParent().replaceChild(this, phrase);
	}

	private void createMoleculeList() {
		/*
       <NounPhrase>
          <NN role="MOLECULE">
            <molecule xmlns="http://www.xml-cml.org/schema">
              <name>124D</name>
              <amount role="MASS">
                <scalar dataType="xsd:double" units="units:g">0.75</scalar>
              </amount>
              <amount role="AMOUNT">
                <scalar dataType="xsd:double" units="units:mmol">2.9</scalar>
              </amount>
            </molecule>
          </NN>
          <Punct role="COMMA">,</Punct>
          <NN role="MOLECULE">
            <molecule xmlns="http://www.xml-cml.org/schema">
              <name>iron</name>
              <amount role="MASS">
                <scalar dataType="xsd:double" units="units:g">0.48</scalar>
              </amount>
              <amount role="AMOUNT">
                <scalar dataType="xsd:double" units="units:mmol">8.6</scalar>
              </amount>
            </molecule>
          </NN>
          <CC role="CC">and</CC>
          <NN role="MOLECULE">
            <molecule xmlns="http://www.xml-cml.org/schema">
              <name>ammoniumchloride</name>
              <amount role="MASS">
                <scalar dataType="xsd:double" units="units:g">0.46</scalar>
              </amount>
              <amount role="AMOUNT">
                <scalar dataType="xsd:double" units="units:mmol">8.6</scalar>
              </amount>
            </molecule>
          </NN>
          <PrepPhrase>
            <IN role="IN">in</IN>
            <NN role="MOLECULE">
              <NN role="MIXTURE">
                <Punct role="_-LRB-">(</Punct>
                <Number>10</Number>
                <JJ>mL/3</JJ>
                <NN role="VOL">mL</NN>
                <Punct role="_-RRB-">)</Punct>
              </NN>
              <molecule xmlns="http://www.xml-cml.org/schema">
                <name>EtOH/H2O/</name>
              </molecule>
            </NN>
          </PrepPhrase>
        </NounPhrase>
      </PrepPhrase>
    </NounPhrase>
		 */
		String molQuery = "NN[@role='MOLECULE' and cml:molecule]";
		Nodes nodes = this.query(molQuery + 
				" | Punct[@role='COMMA'] | CC[.='and'] | PrepPhrase", CMLConstants.CML_XPATH);
		if (nodes.size() == this.getChildElements().size() &&
			this.query(molQuery, CMLConstants.CML_XPATH).size() > 1) {
			CMLMoleculeList cmlMoleculeList = new CMLMoleculeList();
			for (int i = 0; i < nodes.size(); i++) {
				POSElement pos = (POSElement) nodes.get(i);
				if (pos instanceof POSPunct || pos instanceof POSCc) {
					pos.detach();
				} else if (pos.query("self::"+molQuery, CMLConstants.CML_XPATH).size() > 0) {
					CMLMolecule molecule = (CMLMolecule) pos.query("cml:molecule", CMLConstants.CML_XPATH).get(0);
					molecule.detach();
					cmlMoleculeList.addMolecule(molecule);
					pos.detach();
				}
			}
			this.insertChild(cmlMoleculeList, 0);
		}
	}

	private void concatenateAdjectiveNoun() {
		concatenatePhase();
		concatenatePhases();
		annotateColours();
		
	}

	private void annotateColours() {
		/*
      <JJ>yellowish</JJ>
      <NN role="STATE">solid</NN>
		 */
		List<Element> neighbours = TreeBankUtil.getConsecutiveSiblings(
				this, new String[] {"DT", "JJ", "NN"});
		if (neighbours != null) {
			String jjValue = neighbours.get(1).getValue();
			String jjValueLower = jjValue.toLowerCase();
			if (POSAdj.getColorSet().contains(jjValueLower)) {
				neighbours.get(2).addAttribute(new Attribute(POSAdj.COLOR, jjValueLower));
				TreeBankUtil.concatenateContent(jjValue+" ", neighbours.get(2));
				neighbours.get(1).detach();
				neighbours.remove(0);
				neighbours.remove(1);
			}
		}
	}
	
	private void concatenatePhase() {
		/*
	      <JJ role="OSCAR-CJ">organic</JJ>
	      <NN>phase</NN>
		 */
		List<Element> neighbours = TreeBankUtil.getConsecutiveSiblings(
				this, new String[] {"DT", "JJ", "NN[.='"+PHASE+"']"});
		if (neighbours != null) {
			((POSElement)neighbours.get(2)).setRole(PHASE);
			((POSElement)neighbours.get(2)).addAttribute(
					new Attribute(POSAdj.TAG, neighbours.get(1).getValue()));
			neighbours.get(1).detach();
		}
	}

	private void concatenatePhases() {
		/*
		  <DT>the</DT>
	      <JJ role="OSCAR-CJ">combined</JJ>
	      <JJ role="OSCAR-CJ">organic</JJ>
	      <NNS>phases</NNS>
		 */
		List<Element> neighbours = TreeBankUtil.getConsecutiveSiblings(
				this, new String[] {"DT", "JJ[.='combined']", "JJ", "NNS[.='"+PHASES+"']"});
		if (neighbours != null) {
			((POSElement)neighbours.get(3)).setRole(PHASE);
			((POSElement)neighbours.get(3)).addAttribute(
					new Attribute(POSAdj.TAG, neighbours.get(2).getValue()));
			((POSElement)neighbours.get(3)).addAttribute(
					new Attribute(POSAdj.QUALIFIER, neighbours.get(1).getValue()));
			((POSElement)neighbours.get(3)).addAttribute(
					new Attribute(POSNoun.NUMBER, POSNoun.PLURAL));
			neighbours.get(1).detach();
			neighbours.get(2).detach();
		}
	}

	private void createScalars() {
		TreeBankUtil.transformChildNumberAndNNRoleToScalar(this, Type.TIME, "TIME");
	}

	private void concatenateCompoundNouns() {
		// reaction mixture
		TreeBankUtil.concatenateSiblingContent(
				this, "NN[@role='CHEMENTITY' and .='reaction']", "NN[@role='MIXTURE' and .='mixture']");
		// reverse phase
		TreeBankUtil.concatenateSiblingContent(
				this, "JJ[.='reverse']", "NN[.='phase']");
	}
    
	private void concatenateDegrees() {
	    /*
        <NounPhrase>
          <Number>120</Number>
          <NN>deg</NN>
          <NNP>C</NNP>
        </NounPhrase>
     */
		List<Element> neighbours = TreeBankUtil.getConsecutiveSiblings(
				this, new String[] {"Number", "NN[.='deg']", "NNP[.='C']"});
		if (neighbours != null) {			
			Double d = NumberHelper.getDouble(neighbours.get(0));
			Units units = Units.getUnits(Type.TEMPERATURE, "celsius");
			CMLScalar scalar = TreeBankUtil.createScalar(d, units);
			this.appendChild(scalar);
			TreeBankUtil.detachElements(neighbours);
			this.setRole(POSTempPhrase.TAG);
		}
	}
}
