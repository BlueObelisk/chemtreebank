/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.helpers;

import java.util.HashMap;
import java.util.Map;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.parsetree.POSException;

/**
 * represents a number
 * @author pm286
 */
public class NumberHelper  extends Helper {
	private static Logger LOG = Logger.getLogger(Helper.class);
	
    public final static String TAG = "cd";
    public final static String UNICODE = "cd-unicode";

    private Number number;
	private NumberHelper(Element element) {
    	this.element = element;
		createNumber();
    }
    
    private void createNumber() {
		String numberString = element.getValue();
		// of form 3x200
		if (numberString.matches("\\d+x\\d+")) {
			String[] numberStrings = numberString.split("x");
			number = new Double(numberStrings[1]) * new Double(numberStrings[0]);
		} else {
	    	if (element.getChildElements().size() == 0) {
	    		try {
	    			number = new Double(numberString);
	    		} catch (NumberFormatException nfe) {
	    			
	    		}
	    		if (number == null) {
	    			number = translateWordToInteger(numberString);
	    		}
	    	}
	    	if (number == null) {
	    		throw new POSException("Cannot parse number: "+numberString);
	    	}
		}
	    
    }
    
    public static Double getDouble(Element element) {
    	NumberHelper helper = new NumberHelper(element);
    	return helper.getDouble();
    }

//    public static void normalizeUnicode(Element element) {
////<cd>4.5</cd><cd-unicode>×</cd-unicode><cd>10</cd><cd>-2</cd>
//        Nodes nodes = element.query(".//*[local-name()='"+UNICODE+"']");
//        for (int i = 0; i < nodes.size(); i++) {
//            new Cd((Element)nodes.get(i)).normalizeUnicode();
//        }
//    }

//    private void normalizeUnicode() {
//        ParentNode parent = this.delegateElement.getParent();
//        if (parent != null) {
//            int idx = parent.indexOf(this.delegateElement);
//            if (idx > 0 && parent.getChildCount() >= idx+2) {
//                System.out.println("UUUUUUUUUU"+parent.toXML());
//                Element node0 = (Element) parent.getChild(idx-1);
//                Element node2 = (Element) parent.getChild(idx+1);
//                Element node3 = (Element) parent.getChild(idx+2);
//                if ("×".equals(delegateElement.getValue()) &&
//                    Cd.TAG.equals(node0.getLocalName()) &&
//                    Cd.TAG.equals(node2.getLocalName()) &&
//                    "10".equals(node2.getValue()) &&
//                    Cd.TAG.equals(node3.getLocalName())) {
//                    try {
//                        double d = new Double(node0.getValue()).doubleValue();
//                        int power = Integer.parseInt(node3.getValue());
//                        d = d * Math.pow(10.0, power);
//                        setText(node0, ""+d);
//                        delegateElement.detach();
//                        node2.detach();
//                        node3.detach();
//                    } catch (NumberFormatException nfe) {
//                        throw new RuntimeException("Cannot parse as unicode power: "+delegateElement.toXML(), nfe);
//                    }
//                } else {
//                    printDelegate("UNICODE-FAIL");
//                }
//            } else {
//                printDelegate("UNICODE-FAIL");
//            }
//
//        }
//    }

//    public static void normalizeNumber(Element element) {
//        Nodes nodes = element.query(".//*[local-name()='"+TAG+"']");
//        for (int i = 0; i < nodes.size(); i++) {
//            new Cd((Element)nodes.get(i)).normalizeNumber();
//        }
//    }

    private static Map<String, Integer> number2IntMap = new HashMap<String, Integer>();
    static {
    	number2IntMap.put("zero",            new Integer(0));
    	number2IntMap.put("one",             new Integer(1));
    	number2IntMap.put("two",             new Integer(2));
    	number2IntMap.put("three",           new Integer(3));
    	number2IntMap.put("four",            new Integer(4));
    	number2IntMap.put("five",            new Integer(5));
    	number2IntMap.put("six",             new Integer(6));
    	number2IntMap.put("seven",           new Integer(7));
    	number2IntMap.put("eight",           new Integer(8));
    	number2IntMap.put("nine",            new Integer(9));
    	number2IntMap.put("ten",             new Integer(10));
    	number2IntMap.put("eleven",          new Integer(11)); 
    	number2IntMap.put("twelve",          new Integer(12));
    	number2IntMap.put("thirteen",        new Integer(13));
    	number2IntMap.put("fourteen",        new Integer(14));
    	number2IntMap.put("fifteen",         new Integer(15));
    	number2IntMap.put("sixteen",         new Integer(16));
    	number2IntMap.put("seventeen",       new Integer(17));
    	number2IntMap.put("eighteen",        new Integer(18));
    	number2IntMap.put("nineteen",        new Integer(19));
    	number2IntMap.put("twenty",          new Integer(20));
    	number2IntMap.put("twentyone",       new Integer(21));
    	number2IntMap.put("twentytwo",       new Integer(22));
    	number2IntMap.put("twentythree",     new Integer(23));
    	number2IntMap.put("twentyfour",      new Integer(24));
    	number2IntMap.put("twentyfive",      new Integer(25));
    	number2IntMap.put("twentysix",       new Integer(26));
    	number2IntMap.put("twentyseven",     new Integer(27));
    	number2IntMap.put("twentyeight",     new Integer(28));
    	number2IntMap.put("twentynine",      new Integer(29));
    	number2IntMap.put("thirty",          new Integer(30));
    	number2IntMap.put("thirtyone",       new Integer(31));
    	number2IntMap.put("thirtytwo",       new Integer(32));
    	number2IntMap.put("thirtythree",     new Integer(33));
    	number2IntMap.put("thirtyfour",      new Integer(34));
    	number2IntMap.put("thirtyfive",      new Integer(35));
    	number2IntMap.put("thirtysix",       new Integer(36));
    	number2IntMap.put("thirtyseven",     new Integer(37));
    	number2IntMap.put("thirtyeight",     new Integer(38));
    	number2IntMap.put("thirtynine",      new Integer(39));
    	number2IntMap.put("forty",           new Integer(40));
    	number2IntMap.put("fortyone",        new Integer(41));
    	number2IntMap.put("fortytwo",        new Integer(42));
    	number2IntMap.put("fortythree",      new Integer(43));
    	number2IntMap.put("fortyfour",       new Integer(44));
    	number2IntMap.put("fortyfive",       new Integer(45));
    	number2IntMap.put("fortysix",        new Integer(46));
    	number2IntMap.put("fortyseven",      new Integer(47));
    	number2IntMap.put("fortyeight",      new Integer(48));
    	number2IntMap.put("fortynine",       new Integer(49));
    	number2IntMap.put("fifty",           new Integer(50));
    }
    
    public static Integer translateWordToInteger(String numberString) {
    	return number2IntMap.get(numberString);
    }

	public Number getNumber() {
		return number;
	}

	public Double getDouble() {
		return (number == null) ? null : number.doubleValue();
	}
}
