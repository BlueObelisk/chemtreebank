/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.phrase;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Nodes;

import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLConstants;
import org.xmlcml.cml.base.CMLUtil;
import org.xmlcml.cml.element.CMLFormula;
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
    	elementalAnalysis();
    	concatenateCompoundNouns();
    	concatenateAdjectiveNoun();
    	concatenateDegrees();
    	createScalars();
    	createMoleculeList();
    }

    @Override
    protected String getTag() {return TAG;}
    
    String ELEMENTAL_PATTERN0_S = "([A-Z][a-z]?)\\s+(\\d+\\.\\d+)\\s*\\%?\\s*\\,?\\s*";
    Pattern ELEMENTAL_PATTERN0 = Pattern.compile("("+ELEMENTAL_PATTERN0_S+")(.*)");
    Pattern ELEMENTAL_PATTERN = Pattern.compile("(found|calc(ulate)?d?):?\\s+(("+ELEMENTAL_PATTERN0_S+")+)\\s*");
	private void elementalAnalysis() {
		String sf = this.getAttributeValue(SURFACE).trim();
		if (sf.startsWith("found:") && sf.endsWith("%")) {
			Matcher matcher = ELEMENTAL_PATTERN.matcher(sf);
			if (matcher.matches()) {
				String elements = matcher.group(3).trim();
				String found = matcher.group(1).trim();
				StringBuilder conciseSb = new StringBuilder();
				while (elements.length() > 0) {
					Matcher matcher0 = ELEMENTAL_PATTERN0.matcher(elements);
					if (!matcher0.matches()) {
						throw new RuntimeException("bad match: "+elements);
					}
//					String elementNumber = matcher0.group(1).trim();
					String element = matcher0.group(2).trim();
					String count = matcher0.group(3).trim();
					conciseSb.append(element);
					conciseSb.append(" ");
					conciseSb.append(count);
					conciseSb.append(" ");
					elements = matcher0.group(4).trim();
				}
				String concise = conciseSb.substring(0, conciseSb.length()-1).toString();
				CMLFormula formula = new CMLFormula();
				formula.setInline(concise);
				formula.setConvention("cmlx:element_"+found);
				this.removeChildren();
				this.appendChild(formula);
			}
		}
	}

	private void interpretNumberedQuantity() {
		/*
        <Number surface="18">18</Number>
        <NN surface="hrs." role="TIME">hrs.</NN>
        ==>
        <scalar dataType="xsd:double" units="unit:hour">18</scalar>
		 */
		List<Element> neighbours = TreeBankUtil.getConsecutiveSiblings(this, new String[] {"Number", "NN"});
		if (neighbours != null) {
			POSElement number = (POSElement) neighbours.get(0);
			POSElement unit = (POSElement) neighbours.get(1);
			Double d = NumberHelper.getDouble(number);
			if (d == null) {
				throw new POSException("Unknown number: "+number.getValue());
			}
			List<Units> unitList = Units.getPossibleUnits(unit.getValue());
			if (unitList.size() == 0) {
				Double dd = null;
				try {
					dd = new Double(unit.getValue());
				} catch (Exception e) {
					//
				}
				if (dd != null) {
					// bug in element percentages
				} else {
//					CMLUtil.debug(this, "UNIT");
					System.out.print(" [UNITS? "+unit.getValue()+"] ");
				}
			} else if (unitList.size() > 1) {
				LOG.error("Ambiguous units: "+unit.getValue());
			} else {
				Units units = unitList.get(0);
				Type type = units.getType();
				CMLScalar scalar = TreeBankUtil.createScalar(d, units);
				this.appendChild(scalar);
				number.detach();
				unit.detach();
				this.addAttribute(new Attribute(PHRASE, type.toString()));
			}
		}
	}


//	private void copyChildrenAndReplaceParent(POSElement phrase) {
//		Elements childElements = this.getChildElements();
//		for (int i = 0; i < childElements.size(); i++) {
//			childElements.get(i).detach();
//			phrase.appendChild(childElements.get(i));
//		}
//		this.getParent().replaceChild(this, phrase);
//	}

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

	// elemental analysis
	/*
<NounPhrase sf="found: C 53.58 , H 5.55 , N 23.51 %">
 <Number sf="found:">found:</Number>
 <NNP sf="C">C</NNP>
 <NN sf="53.58" role="UNNAMEDMOLECULE">
   <molecule xmlns="http://www.xml-cml.org/schema">
     <name>53.58</name>
   </molecule>
   <molecule ref="53.58" xmlns="http://www.xml-cml.org/schema"/>
 </NN>
 <Punct sf="," role="COMMA">,</Punct>
 <NN sf="H" role="MOLECULE">
   <molecule xmlns="http://www.xml-cml.org/schema">
     <formula concise="H 1">
       <atomArray elementType="H" count="1.0"/>
     </formula>
     <name>H</name>
   </molecule>
 </NN>
 <NN sf="5.55" role="UNNAMEDMOLECULE">
   <molecule xmlns="http://www.xml-cml.org/schema">
     <name>5.55</name>
   </molecule>
   <molecule ref="5.55" xmlns="http://www.xml-cml.org/schema"/>
 </NN>
 <Punct sf="," role="COMMA">,</Punct>
 <NN sf="N 23.51 %" role="MOLECULE">
   <NN sf="23.51 %" role="QUANTITY">
     <NN sf="23.51 %" role="PERCENT">
       <Number sf="23.51">23.51</Number>
       <NN sf="%" role="PERCENT">%</NN>
     </NN>
   </NN>
   <molecule xmlns="http://www.xml-cml.org/schema">
     <formula concise="N 1">
       <atomArray elementType="N" count="1.0"/>
     </formula>
     <name>N</name>
   </molecule>
 </NN>
</NounPhrase>

<?xml version="1.0" encoding="UTF-8"?>
<NounPhrase sf="found: C 41.10 , H 3.90 %">
 <Number sf="found:">found:</Number>
 <NNP sf="C">C</NNP>
 <NN sf="41.10" role="UNNAMEDMOLECULE">
   <molecule xmlns="http://www.xml-cml.org/schema">
     <name>41.10</name>
   </molecule>
   <molecule ref="41.10" xmlns="http://www.xml-cml.org/schema"/>
 </NN>
 <Punct sf="," role="COMMA">,</Punct>
 <NN sf="H 3.90 %" role="MOLECULE">
   <NN sf="3.90 %" role="QUANTITY">
     <NN sf="3.90 %" role="PERCENT">
       <Number sf="3.90">3.90</Number>
       <NN sf="%" role="PERCENT">%</NN>
     </NN>
   </NN>
   <molecule xmlns="http://www.xml-cml.org/schema">
     <formula concise="H 1">
       <atomArray elementType="H" count="1.0"/>
     </formula>
     <name>H</name>
   </molecule>
 </NN>
</NounPhrase>
	 */
}
