/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.noun;

import java.util.ArrayList;
import java.util.List;

import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Nodes;

import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLConstants;
import org.xmlcml.cml.element.CMLMolecule;
import org.xmlcml.cml.element.CMLMoleculeList;
import org.xmlcml.cml.parsetree.punct.POSPunct;

/**
 * compound reference
 * @author pm286
 */
public class POSOscarCPR extends POSNoun {
	private static Logger LOG = Logger.getLogger(POSOscarCPR.class);

	public final static String TAG = "OSCAR-CPR";

    public POSOscarCPR(Element element) {
    	super(element, TAG);
    	tidy();
    }
    
    /*<OSCAR-CM>
     * <OSCAR-CM>ammonium</OSCAR-CM>
     * <OSCAR-CM>chloride</OSCAR-CM>
     </OSCAR-CM>*/
    
    private void tidy() {
    	processOSCARCMandPunct();
    	concatenateOSCARCM();
    }

    /*
    <NN sf="EtOH / H2O /" role="OSCAR-CM">
      <NN sf="EtOH" role="OSCAR-CM">EtOH</NN>
      <Punct sf="/" role="DASH">/</Punct>
      <NN sf="H2O" role="OSCAR-CM">H2O</NN>
      <Punct sf="/" role="DASH">/</Punct>
    </NN>
*/    
    private void processOSCARCMandPunct() {
    	Elements childElements = this.getChildElements();
		Nodes oscarCMs = this.query("NN[@role='OSCAR-CM']");
		Nodes puncts = this.query("Punct");
		if (puncts.size() > 0) {
			if (this.getChildElements().size() != oscarCMs.size() + puncts.size()) {
//				LOG.error("non-OSCAR-CML/Punct nodes in "+this.toXML());
			} else {
//				CMLUtil.debug(this, "XXXXXXXX ");
				List<POSOscarCPR> oscarList = new ArrayList<POSOscarCPR>();
				oscarList = extractOddNodesAsOSCARCM(childElements, oscarList);
				if (oscarList != null) {
					createAndAddMoleculeList(oscarList);
				}
			}
		}
    }

	private List<POSOscarCPR> extractOddNodesAsOSCARCM(Elements childElements,
			List<POSOscarCPR> oscarList) {
		int i = 0;
		while (i < childElements.size()) {
			if (!(childElements.get(i) instanceof POSOscarCPR)) {
				oscarList = null;
//				LOG.error("Not an OSCAR-CM: "+childElements.get(i)+" "+i);
				break;
			}
			oscarList.add((POSOscarCPR)childElements.get(i));
			i++;
			if (i >= childElements.size()) {
				break;
			}
			if (!(childElements.get(i) instanceof POSPunct)) {
				oscarList = null;
				LOG.error("Not a Punct: "+childElements.get(i)+" "+i);
				break;
			}
			childElements.get(i).detach();
			i++;
		}
		return oscarList;
	}

	private void createAndAddMoleculeList(List<POSOscarCPR> oscarList) {
		CMLMoleculeList moleculeList = new CMLMoleculeList();
		for (POSOscarCPR oscar : oscarList) {
			oscar.detach();
			CMLMolecule molecule = new CMLMolecule();
			molecule.addName(oscar.getValue());
			moleculeList.addMolecule(molecule);
		}
		this.appendChild(moleculeList);
	}
    /*
<NN>
  <NN role="OSCAR-CM">aluminium</NN>
  <NN role="OSCAR-CM">chloride</NN>
</NN>
=><NN>aluminium chloride</NN>
     */
	private void concatenateOSCARCM() {
		Nodes oscarCMs = this.query("NN[@role='OSCAR-CM']");
		if (oscarCMs.size() > 0) {
			if (this.getChildElements().size() > oscarCMs.size()) {
//				LOG.error("non-OSCAR-CML nodes in "+this.toXML());
			} else {
				StringBuilder sb = new StringBuilder(oscarCMs.get(0).getValue());
				oscarCMs.get(0).detach();
				for (int i = 1; i < oscarCMs.size(); i++) {
					sb.append(CMLConstants.S_SPACE);
					sb.append(oscarCMs.get(i).getValue());
					oscarCMs.get(i).detach();
				}
				this.removeChildren();
				this.appendChild(sb.toString());
			}
		}
	}

}
