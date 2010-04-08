package org.xmlcml.cml.parsetree.svg;

import nu.xom.Element;
import nu.xom.Elements;

import org.xmlcml.cml.element.CMLAction;
import org.xmlcml.cml.element.CMLCml;
import org.xmlcml.cml.graphics.SVGElement;
import org.xmlcml.cml.graphics.SVGSVG;
import org.xmlcml.cml.tools.ActionTool;

public class Sentence {
	
	private CMLCml cml;
	private SVGElement svg;
	/** constructor.
	 * 
	 * @param name
	 */
	public Sentence() {
	}

	public void readCML(CMLCml cml) {
		this.cml = cml;
	}
	
	public SVGElement createSVG() {
		svg = new SVGSVG();
		Elements childElements = cml.getChildElements();
		for (int i = 0; i < childElements.size(); i++) {
			Element childElement = childElements.get(i);
			if (childElement instanceof CMLAction) {
				ActionTool actionTool = ActionTool.getOrCreateTool((CMLAction)childElement);
				SVGElement gg = actionTool.createGraphicsElement();
				svg.appendChild(gg);
			} else {
				throw new RuntimeException("unknown element: "+childElement.getLocalName());
			}
		}
		return svg;
	}
}
