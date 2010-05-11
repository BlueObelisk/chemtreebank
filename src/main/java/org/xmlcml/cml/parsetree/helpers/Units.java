package org.xmlcml.cml.parsetree.helpers;

import java.util.ArrayList;
import java.util.List;

import org.xmlcml.cml.base.CMLConstants;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

public class Units {

	public final static String NAMESPACE_PREFIX = CMLConstants.CML_UNITS;
	public final static String NS = CMLConstants._UNIT_NS;

	public enum Type {
		ADDITION_RATE,
		AMOUNT,
		CONCENTRATION,
		DIMENSIONLESS,
		FREQUENCY, 
		LENGTH, 
		LENGTH_INVERSE, 
		MASS,
		MASS_PER_CHARGE,
		POWER,
		PRESSURE,
		RATIO,
		TEMPERATURE,
		TEMPERATURE_RATE,
		TIME,
		TIME_INVERSE, 
		VELOCITY,
		VOLUME,
	}

	private static ListMultimap<Type, Units> unitListByTypeMap;
	private static ListMultimap<String, Units> unitListByAbbreviationMap;

/*
[UNITS? K.hr-1]86415 
[UNITS? K/hour]
[UNITS? Kh-1]
[UNITS? atm]
[UNITS? centidegree]
[UNITS? dram]
[UNITS? g/ml]
[UNITS? g/mol):]
[UNITS? kJ]
[UNITS? mTorr]
[UNITS? mbar]  
[UNITS? mol-1]  
[UNITS? mol.L-1]
[UNITS? oC.h-1]
[UNITS? overnight] 130097 
[UNITS? torr]
[UNITS? °C.h-1]
[UNITS? °C/h]
[UNITS? °K/h]
[UNITS? °K/h]  
[UNITS? µS.cm-1]  
mixed content <JJ sf="M"><NN sf="M" role="MOLAR">M</NN></JJ>
bad units (G) <NN sf="6 G" role="MASS"><Number sf="6">6</Number><NN sf="G" role="NN-MASS">G</NN></NN>
bad units (H) <NounPhrase sf="1 H NMR"><Number sf="1">1</Number><NN sf="H" role="TIME">H</NN><NNP sf="NMR">NMR</NNP></NounPhrase>
bad units (H) <NounPhrase sf="1 H"><Number sf="1">1</Number><NN sf="H" role="TIME">H</NN></NounPhrase>
bad units (H) <NounPhrase sf="1H-NMR(CDCl3 , 300 MHz): ?6.98 ( t , 1 H ) , 7.04 ( d , 1 H ) , 7.34(d , 2 H"><NN sf="1H-NMR(CDCl3" role="MOLECULE"><molecule xmlns="http://www.xml-cml.org/schema"><name>1H-NMR(CDCl3</name></molecule></NN><Punct sf="," role="COMMA">,</Punct><Number sf="300">300</Number><NN sf="MHz):">MHz):</NN><NN sf="?6.98" role="UNNAMEDMOLECULE"><molecule xmlns="http://www.xml-cml.org/schema"><name>?6.98</name></molecule><molecule xmlns="http://www.xml-cml.org/schema" ref="?6.98" /></NN><NN sf="( t , 1 H )" role="MIXTURE"><Punct sf="(" role="_-LRB-">(</Punct><NN sf="t">t</NN><Punct sf="," role="COMMA">,</Punct><Number sf="1">1</Number><NN sf="H" role="TIME">H</NN><Punct sf=")" role="_-RRB-">)</Punct></NN><Punct sf="," role="COMMA">,</Punct><NN sf="7.04" role="UNNAMEDMOLECULE"><molecule xmlns="http://www.xml-cml.org/schema"><name>7.04</name></molecule><molecule xmlns="http://www.xml-cml.org/schema" ref="7.04" /></NN><NN sf="( d , 1 H )" role="MIXTURE"><Punct sf="(" role="_-LRB-">(</Punct><NN sf="d" role="TIME">d</NN><Punct sf="," role="COMMA">,</Punct><Number sf="1">1</Number><NN sf="H" role="TIME">H</NN><Punct sf=")" role="_-RRB-">)</Punct></NN><Punct sf="," role="COMMA">,</Punct><NN sf="7.34(d" role="MOLECULE"><molecule xmlns="http://www.xml-cml.org/schema"><name>7.34(d</name></molecule></NN><Punct sf="," role="COMMA">,</Punct><Number sf="2">2</Number><NN sf="H" role="TIME">H</NN></NounPhrase>
bad units (H) <NounPhrase sf="1H-NMR(CDCl3 , 300 MHz): ?6.98 ( t , 1 H ) , 7.08 ( d , 1 H ) , 7.37(t , 2 H"><NN sf="1H-NMR(CDCl3" role="MOLECULE"><molecule xmlns="http://www.xml-cml.org/schema"><name>1H-NMR(CDCl3</name></molecule></NN><Punct sf="," role="COMMA">,</Punct><Number sf="300">300</Number><NN sf="MHz):">MHz):</NN><NN sf="?6.98" role="UNNAMEDMOLECULE"><molecule xmlns="http://www.xml-cml.org/schema"><name>?6.98</name></molecule><molecule xmlns="http://www.xml-cml.org/schema" ref="?6.98" /></NN><NN sf="( t , 1 H )" role="MIXTURE"><Punct sf="(" role="_-LRB-">(</Punct><NN sf="t">t</NN><Punct sf="," role="COMMA">,</Punct><Number sf="1">1</Number><NN sf="H" role="TIME">H</NN><Punct sf=")" role="_-RRB-">)</Punct></NN><Punct sf="," role="COMMA">,</Punct><NN sf="7.08" role="UNNAMEDMOLECULE"><molecule xmlns="http://www.xml-cml.org/schema"><name>7.08</name></molecule><molecule xmlns="http://www.xml-cml.org/schema" ref="7.08" /></NN><NN sf="( d , 1 H )" role="MIXTURE"><Punct sf="(" role="_-LRB-">(</Punct><NN sf="d" role="TIME">d</NN><Punct sf="," role="COMMA">,</Punct><Number sf="1">1</Number><NN sf="H" role="TIME">H</NN><Punct sf=")" role="_-RRB-">)</Punct></NN><Punct sf="," role="COMMA">,</Punct><NN sf="7.37(t" role="MOLECULE"><molecule xmlns="http://www.xml-cml.org/schema"><name>7.37(t</name></molecule></NN><Punct sf="," role="COMMA">,</Punct><Number sf="2">2</Number><NN sf="H" role="TIME">H</NN></NounPhrase>
bad units (H) <NounPhrase sf="2 H"><Number sf="2">2</Number><NN sf="H" role="TIME">H</NN></NounPhrase>
bad units (H) <NounPhrase sf="7.08(tt , 2 H"><NNP sf="7.08(tt">7.08(tt</NNP><Punct sf="," role="COMMA">,</Punct><Number sf="2">2</Number><NN sf="H" role="TIME">H</NN></NounPhrase>
bad units (H) <NounPhrase sf="7.20(tt , 2 H"><NNP sf="7.20(tt">7.20(tt</NNP><Punct sf="," role="COMMA">,</Punct><Number sf="2">2</Number><NN sf="H" role="TIME">H</NN></NounPhrase>
bad units (H) <NounPhrase sf="8.43(d , 2 H"><NN sf="8.43(d" role="MOLECULE"><molecule xmlns="http://www.xml-cml.org/schema"><name>8.43(d</name></molecule></NN><Punct sf="," role="COMMA">,</Punct><Number sf="2">2</Number><NN sf="H" role="TIME">H</NN></NounPhrase>
bad units (H) <NounPhrase sf="Ph )] , 6.47 
bad units (H) <NounPhrase sf="pseudo- tt , J = 7.75 , 1.32 Hz , 1 H , C6H4"><DT sf="pseudo-">pseudo-</DT><NN sf="tt" role="MOLECULE"><molecule xmlns="http://www.xml-cml.org/schema"><name>tt</name></molecule></NN><Punct sf="," role="COMMA">,</Punct><NNP sf="J">J</NNP><Punct sf="=" role="SYM">=</Punct><NN sf="7.75" role="UNNAMEDMOLECULE"><molecule xmlns="http://www.xml-cml.org/schema"><name>7.75</name></molecule><molecule xmlns="http://www.xml-cml.org/schema" ref="7.75" /></NN><Punct sf="," role="COMMA">,</Punct><Number sf="1.32">1.32</Number><NNP sf="Hz">Hz</NNP><Punct sf="," role="COMMA">,</Punct><Number sf="1">1</Number><NN sf="H" role="TIME">H</NN><Punct sf="," role="COMMA">,</Punct><NN sf="C6H4" role="MOLECULE"><molecule xmlns="http://www.xml-cml.org/schema"><formula concise="C 6 H 4"><atomArray elementType="C H" count="6.0 4.0" /></formula><name>C6H4</name></molecule></NN></NounPhrase>
bad units (HRPD) <NounPhrase sf="beamline 8 C2- HRPD"><NN sf="beamline">beamline</NN><Number sf="8">8</Number><NN sf="C2-" role="OSCAR-CPR">C2-</NN><NN sf="HRPD" role="TIME">HRPD</NN></NounPhrase>
bad units (Mmol) <NN sf="2 Mmol" role="AMOUNT"><Number sf="2">2</Number><NN sf="Mmol" role="AMOUNT">Mmol</NN></NN>
bad units (cm3of) <NN sf="5 cm3of" role="VOLUME"><Number sf="5">5</Number><NN sf="cm3of" role="VOL">cm3of</NN></NN>
bad units (hourand) <NounPhrase sf="one hourand"><Number sf="one">one</Number><NN sf="hourand" role="TIME">hourand</NN></NounPhrase>
bad units (mmoL) <NN sf="0.09 mmoL" role="AMOUNT"><Number sf="0.09">0.09</Number><NN sf="mmoL" role="AMOUNT">mmoL</NN></NN>
bad units (mmoL) <NN sf="0.1 mmoL" role="AMOUNT"><Number sf="0.1">0.1</Number><NN sf="mmoL" role="AMOUNT">mmoL</NN></NN>
bad units (mmoL) <NN sf="0.2 mmoL" role="AMOUNT"><Number sf="0.2">0.2</Number><NN sf="mmoL" role="AMOUNT">mmoL</NN></NN>
bad units (mmoL) <NN sf="0.5 mmoL" role="AMOUNT"><Number sf="0.5">0.5</Number><NN sf="mmoL" role="AMOUNT">mmoL</NN></NN>
bad units (mmoL) <NN sf="1 mmoL" role="AMOUNT"><Number sf="1">1</Number><NN sf="mmoL" role="AMOUNT">mmoL</NN></NN>
bad units (mmoL) <NN sf="1.16 mmoL" role="AMOUNT"><Number sf="1.16">1.16</Number><NN sf="mmoL" role="AMOUNT">mmoL</NN></NN>
bad units (mmoL) <NN sf="2 mmoL" role="AMOUNT"><Number sf="2">2</Number><NN sf="mmoL" role="AMOUNT">mmoL</NN></NN>
bad units (mmols) <NN sf="2 mmols" role="AMOUNT"><Number sf="2">2</Number><NN sf="mmols" role="AMOUNT">mmols</NN></NN>
bad units (night) <TimePhrase sf="after one night"><IN sf="after" role="AFTER">after</IN><Number sf="one">one</Number><NN sf="night" role="TIME">night</NN></TimePhrase>
bad units (night) <TimePhrase sf="for one night"><IN sf="for" role="FOR">for</IN><Number sf="one">one</Number><NN sf="night" role="TIME">night</NN></TimePhrase>
bad units (overnight) <NounPhrase sf="195 K overnight"><Number sf="195">195</Number><NNP sf="K">K</NNP><NN sf="overnight" role="TIME">overnight</NN></NounPhrase>
bad units (overnight) <NounPhrase sf="253 K overnight"><Number sf="253">253</Number><NNP sf="K">K</NNP><NN sf="overnight" role="TIME">overnight</NN></NounPhrase>
bad units (overnight) <NounPhrase sf="273 K and then overnight"><Number sf="273">273</Number><NNP sf="K">K</NNP><CC sf="and" role="CC">and</CC><RB sf="then">then</RB><NN sf="overnight" role="TIME">overnight</NN></NounPhrase>
bad units (overnight) <NounPhrase sf="277 K overnight"><Number sf="277">277</Number><NNP sf="K">K</NNP><NN sf="overnight" role="TIME">overnight</NN></NounPhrase>
bad units (overnight) <NounPhrase sf="279 K overnight"><Number sf="279">279</Number><NNP sf="K">K</NNP><NN sf="overnight" role="TIME">overnight</NN></NounPhrase>
bad units (overnight) <NounPhrase sf="298 K overnight"><Number sf="298">298</Number><NNP sf="K">K</NNP><NN sf="overnight" role="TIME">overnight</NN></NounPhrase>
bad units (overnight) <NounPhrase sf="343 K overnight"><Number sf="343">343</Number><NNP sf="K">K</NNP><NN sf="overnight" role="TIME">overnight</NN></NounPhrase>
bad units (overnight) <NounPhrase sf="348 K overnight"><Number sf="348">348</Number><NNP sf="K">K</NNP><NN sf="overnight" role="TIME">overnight</NN></NounPhrase>
bad units (overnight) <NounPhrase sf="373 K overnight"><Number sf="373">373</Number><NNP sf="K">K</NNP><NN sf="overnight" role="TIME">overnight</NN></NounPhrase>
bad units (overnight) <NounPhrase sf="393 K overnight"><Number sf="393">393</Number><NNP sf="K">K</NNP><NN sf="overnight" role="TIME">overnight</NN></NounPhrase>
bad units (overnight) <NounPhrase sf="413 K overnight"><Number sf="413">413</Number><NNP sf="K">K</NNP><NN sf="overnight" role="TIME">overnight</NN></NounPhrase>
bad units (period) <NounPhrase sf="a 30 minute period"><DT sf="a">a</DT><Number sf="30">30</Number><JJ sf="minute">minute</JJ><NN sf="period" role="TIME">period</NN></NounPhrase>
Unknown units: µS.cm-1
Unknown units: ×
 */
	public static Units DROPS_PER_MIN;
	public static Units ML_PER_MIN;
	static {
		
		DROPS_PER_MIN = createEntry("dropspermin", Type.ADDITION_RATE, "drops per min", 
				new String[]{"drops/min"});
		ML_PER_MIN = createEntry("mlpermin", Type.ADDITION_RATE, "ml per min", 
				new String[]{"ml/min"});

	};
	

	public static Units EQUIVALENT;
	public static Units MOLE;
	public static Units MILLIMOLE;
	public static Units MICROMOLE;
	public static Units PORTION;
	public static Units DROP;
	static {
		EQUIVALENT = createEntry("equivalent", Type.AMOUNT, "equivalent", 
				new String[]{"eq", "equiv", "equivalent", "equivalents"});
		MOLE = createEntry("mol", Type.AMOUNT, "mole", 
				new String[]{"mol", "mole"});
		MILLIMOLE = createEntry("millimol", Type.AMOUNT, "millimole", 
				new String[]{"mmol", "mmole"});
		MICROMOLE = createEntry("micromol", Type.AMOUNT, "micromole", 
				new String[]{"µmol", "micromole"});
		PORTION = createEntry("portion", Type.AMOUNT, "portion", 
				new String[]{"portion"});
		DROP = createEntry("portion", Type.AMOUNT, "drop", 
				new String[]{"drop", "drops"});
		
	};
	
	
	public static Units NORMAL;
	public static Units MOLAR;
	public static Units MILLIMOLAR;
	public static Units MICROMOLAR;
	static {
//		Unknown units: N HCl
		NORMAL = createEntry("normal", Type.CONCENTRATION, "normal", 
				new String[]{"N"});
		MOLAR = createEntry("molar", Type.CONCENTRATION, "molar", 
				new String[]{"M", "molar", "mol/L", "mol/l"});
		MILLIMOLAR = createEntry("millimolar", Type.AMOUNT, "millimolar", 
				new String[]{"mM", "mmolar", "mmol/L", "mmol/l"});
		MICROMOLAR = createEntry("micromolar", Type.AMOUNT, "micromolar", 
				new String[]{"µM", "micromolar", "µmol/L", "µmol/l"});
		
	};
	
	public static Units STEP;
	public static Units TIMES;
	static {
		STEP = createEntry("step", Type.DIMENSIONLESS, "step", 
				new String[]{"step"});
		TIMES = createEntry("times", Type.DIMENSIONLESS, "times", 
				new String[]{"times", "x", "×",});
		
	};
	
	public static Units HERTZ;
	public static Units KILO_HERTZ;
	public static Units MEGA_HERTZ;
	static {
		HERTZ = createEntry("hertz", Type.FREQUENCY, "hertz", 
				new String[]{"Hz", } );
		KILO_HERTZ = createEntry("kilohertz", Type.FREQUENCY, "kilohertz", 
				new String[]{"kHz", } );
		MEGA_HERTZ = createEntry("megahertz", Type.FREQUENCY, "megahertz", 
				new String[]{"MHz", } );
		
	};
	
	public static Units KILOMETRE;
	public static Units METRE;
	public static Units CENTIMETRE;
	public static Units MILLIMETRE;
	public static Units MICROMETRE;
	public static Units NANOMETRE;
	public static Units ANGSTROM;

	static {
		KILOMETRE = createEntry("kilometre", Type.LENGTH, "kilometre", 
				new String[]{"kilometre", "kilometer", "km"} );
		METRE = createEntry("metre", Type.LENGTH, "metre", 
				new String[]{"metre", "meter", "m"} );
		CENTIMETRE = createEntry("centimetre", Type.LENGTH, "centimetre", 
				new String[]{"cm", "centimeter", "centimetre"} );
		MILLIMETRE = createEntry("millimetre", Type.LENGTH, "millimetre", 
				new String[]{"mm", "millimeter", "millimetre"} );
		MICROMETRE = createEntry("micrometre", Type.LENGTH, "micrometre", 
				new String[]{"micron", "µm", "micrometre"} );
		NANOMETRE = createEntry("nanometre", Type.LENGTH, "nanometre", 
				new String[]{"nanometre", "nm", "nanometer"} );
		ANGSTROM = createEntry("angstrom", Type.LENGTH, "angstrom", 
				new String[]{"Å", "Angstrom", "A"} );
		
	};
	
	public static Units KILOMETRE_INVERSE;
	public static Units METRE_INVERSE;
	public static Units CENTIMETRE_INVERSE;
	public static Units MILLIMETRE_INVERSE;
	public static Units MICROMETRE_INVERSE;
	public static Units NANOMETRE_INVERSE;
	public static Units ANGSTROM_INVERSE;
	static {
		KILOMETRE_INVERSE = createEntry("inverse_kilometre", Type.LENGTH_INVERSE, "inverse_kilometre", 
				new String[]{"kilometre-1", "kilometer-1", "km-1"} );
		METRE_INVERSE = createEntry("inverse_metre", Type.LENGTH_INVERSE, "inverse_metre", 
				new String[]{"metre-1", "meter-1", "m-1"} );
		CENTIMETRE_INVERSE = createEntry("inverse_centimetre", Type.LENGTH_INVERSE, "inverse_centimetre", 
				new String[]{"cm-1", "centimeter-1", "centimetre-1"} );
		MILLIMETRE_INVERSE = createEntry("inverse_millimetre", Type.LENGTH_INVERSE, "inverse_millimetre", 
				new String[]{"mm-1", "millimeter-1", "millimetre-1"} );
		MICROMETRE_INVERSE = createEntry("inverse_micrometre", Type.LENGTH_INVERSE, "inverse_micrometre", 
				new String[]{"micron-1", "µm-1", "micrometre-1"} );
		NANOMETRE_INVERSE = createEntry("inverse_nanometre", Type.LENGTH_INVERSE, "inverse_nanometre", 
				new String[]{"nanometre-1", "nm-1", "nanometer-1"} );
		ANGSTROM_INVERSE = createEntry("inverse_angstrom", Type.LENGTH_INVERSE, "inverse_angstrom", 
				new String[]{"Å-1", "Angstrom-1", "A-1"} );
		
	};
	
	public static Units KILOGRAM;
	public static Units GRAM;
	public static Units MILLIGRAM;
	public static Units MICROGRAM;
	public static Units PARENTH;
	static {
		KILOGRAM = createEntry("kilogram", Type.MASS, "kilogram", 
				new String[]{"kg", "kilogram", "kilograms"} );
		GRAM = createEntry("gram", Type.MASS, "gram", 
				new String[]{"g", "gram", "grams", "gr", "gm"} );
		MILLIGRAM = createEntry("milligram", Type.MASS, "milligram", 
				new String[]{"mg", "milligram", "mgs"} );
		MICROGRAM = createEntry("microgram", Type.MASS, "microgram", 
				new String[]{"µg", "microgram", "µgs"} );
		PARENTH = createEntry("parentH", Type.MASS, "parent mass with H+", 
				new String[]{"parentH"} );
		
	};
	
	public static Units M_PER_E;
	static {
		M_PER_E = createEntry("mpere", Type.MASS_PER_CHARGE, "atomic mass per electron", 
				new String[]{"m/e", "m/z", } );
	}
	
	public static Units KW;
	static {
		KW = createEntry("kw", Type.POWER, "kilowatt", 
				new String[]{"kW",} );

	};
	
	public static Units BAR;
	static {
		BAR = createEntry("bar", Type.PRESSURE, "bar", 
				new String[]{"bar",} );

	};
	
	public static Units PERCENT;
	public static Units PPM;
	public static Units RATIO;
	public static Units VV;
	public static Units WW;
	static {
		PERCENT = createEntry("percent", Type.RATIO, "percent", 
				new String[]{"percent", "%"} );
		PPM = createEntry("ppm", Type.RATIO, "parts per million", 
				new String[]{"ppm", "p.p.m.", "p.p.m"} );
		RATIO = createEntry("ratio", Type.RATIO, "ratio", 
				new String[]{"ratio", } );
		VV = createEntry("vv", Type.RATIO, "v/v", 
				new String[]{"v/v", } );
		WW = createEntry("ww", Type.RATIO, "w/w", 
				new String[]{"w/w", } );
		
	};
	
	public static Units KELVIN;
	public static Units CELSIUS;
	static {
		KELVIN = createEntry("kelvin", Type.TEMPERATURE, "K", 
				new String[]{"K", "oK"} );
		CELSIUS = createEntry("celsius", Type.TEMPERATURE, "degC", 
				new String[]{"degC", "C", "deg", "celsius", "°", "°C", "degree", "degrees", "centigrade", "oC", "C°"} );
		
	};
	
	public static Units K_PER_HOUR;
	static {
		
		K_PER_HOUR = createEntry("mlpermin", Type.TEMPERATURE_RATE, "kelvin per hour", 
			new String[]{"K.h-1", "K/h", "K/hour", "K.hr-1", "Kh-1"});		
	};
	
	public static Units YEAR;
	public static Units MONTH;
	public static Units WEEK;
	public static Units DAY;
	public static Units HOUR;
	public static Units MINUTE;
	public static Units SECOND;
	static {
		YEAR = createEntry("year", Type.TIME, "terrestrial year", 
				new String[]{"years", "year", "yrs", "yr", "y"} );
		MONTH = createEntry("month", Type.TIME, "month", 
				new String[]{"months", "month"} );
		WEEK = createEntry("week", Type.TIME, "week", 
				new String[]{"weeks", "week", "wks", "wk"} );
		DAY = createEntry("day", Type.TIME, "day", 
				new String[]{"days", "day", "d"} );
		HOUR = createEntry("hour", Type.TIME, "hour", 
				new String[]{"hours", "hour", "hr", "hrs", "h"} );
		MINUTE = createEntry("minute", Type.TIME, "minute", 
				new String[]{"minutes", "minute", "mins", "min", "m"} );
		SECOND = createEntry("second", Type.TIME, "second", 
				new String[]{"seconds", "second", "secs", "sec", "s"} );
		
	};
	
	public static Units YEAR_INVERSE;
	public static Units MONTH_INVERSE;
	public static Units WEEK_INVERSE;
	public static Units DAY_INVERSE;
	public static Units HOUR_INVERSE;
	public static Units MINUTE_INVERSE;
	public static Units SECOND_INVERSE;
	static {
		YEAR_INVERSE = createEntry("inverse_year", Type.TIME_INVERSE, "inverse terrestrial year", 
				new String[]{"year-1",  "yr-1", "y-1", "/year"} );
		MONTH_INVERSE = createEntry("inverse_month", Type.TIME_INVERSE, "inverse month", 
				new String[]{"month-1"} );
		WEEK_INVERSE = createEntry("inverse_week", Type.TIME_INVERSE, "inverse week", 
				new String[]{"week-1", "wk-1"} );
		DAY_INVERSE = createEntry("inverse_day", Type.TIME_INVERSE, "inverse day", 
				new String[]{"day-1", "d-1"} );
		HOUR_INVERSE = createEntry("inverse_hour", Type.TIME_INVERSE, "inverse hour", 
				new String[]{"hour-1", "hr-1", "h-1"} );
		MINUTE_INVERSE = createEntry("inverse_minute", Type.TIME_INVERSE, "inverse minute", 
				new String[]{"minute-1", "min-1", "m-1", "/minute"} );
		SECOND_INVERSE = createEntry("inverse_second", Type.TIME_INVERSE, "inverse second", 
				new String[]{"second-1", "sec-1", "s-1", "/second", "/sec", "/s"} );
		
	};
	
	public static Units LITRE;
	public static Units MILLILITRE;
	public static Units MICROLITRE;
			
	static {
		LITRE = createEntry("litre", Type.VOLUME, "litre", 
				new String[]{"litre", "liter",  "L", "l", "dm^3"} );
		MILLILITRE = createEntry("millilitre", Type.VOLUME, "millilitre", 
				new String[]{"millilitre", "milliliter", "mL", "ml", "cm3", "cm^3"} );
		MICROLITRE = createEntry("microlitre", Type.VOLUME, "microlitre", 
				new String[]{"microlitre", "microliter", "µL", "µl", } );
		
	};
	
	public static Units MM_PER_MIN;
	public static Units MM_PER_HOUR;
	static {
		MM_PER_HOUR = createEntry("mm_per_hour", Type.VELOCITY, "mm per hour", 
				new String[]{"mm/hr", "mm/h", "mm/hour", "mm.hr-1", "mm.h-1", "mm.hour-1", } );
	};

	private static void printUnits() {
		for (String key : unitListByAbbreviationMap.keys()) {
			List<Units> unitList = unitListByAbbreviationMap.get(key);
			System.out.println(key+": "+unitList.get(0).getName());
		}
	};
	
	private Type type;
	private String name;
	private String prefix = NAMESPACE_PREFIX;
	private String namespace = NS;
	private List<String> nameList = null;
	private String id;
	
	private Units(String id, Type type, String name, String[] names) {
		this.name = name;
		this.type = type;
		nameList = new ArrayList<String>();
		for (String n : names) {
			nameList.add(n);
		}
		this.id = id;
	}

	private static Units createEntry(String id, Type type, String name, String[] abbreviations) {
		ensureUnitListByTypeMap();
		ensureUnitListByAbbreviationMap();
		Units units = new Units(id, type, name, abbreviations);
		unitListByTypeMap.put(type, units);
		for (String abbreviation : abbreviations) {
			if (unitListByAbbreviationMap.containsKey(abbreviation)) {
				System.out.println("Ambiguous unit: "+abbreviation);
			}
			unitListByAbbreviationMap.put(abbreviation, units);
		}
		return units;
	}

	public String getName() {
		return name;
	}

	private static void ensureUnitListByTypeMap() {
		if (unitListByTypeMap == null) {
			unitListByTypeMap = ArrayListMultimap.create();
		}
	}

	private static void ensureUnitListByAbbreviationMap() {
		if (unitListByAbbreviationMap == null) {
			unitListByAbbreviationMap = ArrayListMultimap.create();
		}
	}

	public String getPrefix() {
		return prefix;
	}

	public String getId() {
		return id;
	}

	public String getNamespace() {
		return namespace;
	}

	public static List<Units> getPossibleUnits(String unitsValue) {
		List<Units> unitList = new ArrayList<Units>();
		if (unitsValue != null) {
			unitsValue = removeBrackets(unitsValue);
			unitsValue = removeTrailingPeriod(unitsValue);
			unitList = unitListByAbbreviationMap.get(unitsValue);
		}
		return unitList;
	}

	public static Units getUnits(Type type, String unitsValue) {
		Units units = null;
		List<Units> unitList = getPossibleUnits(unitsValue);
		for (Units unit : unitList) {
			if (unit.type.equals(type)) {
				units = unit;
				break;
			}
		}
		return units;
	}

	public static Units getSingleUnits(String unitsValue) {
		List<Units> unitList = getPossibleUnits(unitsValue);
		return (unitList.size() == 1) ? unitList.get(0) : null;
	}

	private static String removeBrackets(String unitsValue) {
		if (unitsValue.startsWith(CMLConstants.S_LBRAK) &&
			unitsValue.endsWith(CMLConstants.S_RBRAK)) {
			unitsValue = unitsValue.substring(1, unitsValue.length()-1);
		}
		return unitsValue;
	}

	private static String removeTrailingPeriod(String unitsValue) {
		if (unitsValue.endsWith(CMLConstants.S_PERIOD)) {
			unitsValue = unitsValue.substring(0, unitsValue.length()-1);
		}
		return unitsValue;
	}

	public static Units guessUnits(String unitsValue) {
		Units units = null;
		for (Type type : Type.values()) {
			units = getUnits(type, unitsValue);
			if (units != null) {
				break;
			}
		}
		return units;
	}

	public Type getType() {
		return type;
	}

	public String toString() {
		return name;
	}
}
