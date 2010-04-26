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
				new String[]{"K.h-1", "K/h"});
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
