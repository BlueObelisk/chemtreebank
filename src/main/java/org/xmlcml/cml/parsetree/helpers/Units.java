package org.xmlcml.cml.parsetree.helpers;

import java.util.HashMap;
import java.util.Map;

import org.xmlcml.cml.base.CMLConstants;

public class Units {

	public final static String PREFIX = CMLConstants.CML_UNITS;
	public final static String NS = CMLConstants._UNIT_NS;

	public enum Type {
		AMOUNT,
		DIMENSIONLESS,
		MASS,
		PERCENT,
		TEMPERATURE,
		TIME,
		VOLUME, 
	}
	
	public enum Name {
		GRAM,
		MILLIGRAM,
		PARENTH,
		
		LITRE,
		MILLILITRE,
		
		MOL,
		MILLIMOL,
		
		PERCENT,
		
		YEAR,
		MONTH,
		WEEK,
		DAY,
		HOUR,
		MINUTE,
		SECOND,
		
		CELSIUS,
		KELVIN,
		
		STEP,
		TIMES,
		
	}
	
	private static Map<Type, Map<String, Units>> type2UnitsMap;
	
	private static Map<String, Units> amountUnitsMap;
	private static Map<String, Units> dimensionlessUnitsMap;
	private static Map<String, Units> massUnitsMap;
	private static Map<String, Units> percentUnitsMap;
	private static Map<String, Units> temperatureUnitsMap;
	private static Map<String, Units> timeUnitsMap;
	private static Map<String, Units> volumeUnitsMap;

	private static Map<Name, Units> name2UnitsMap;

	private static void makeAmountUnitsMap () {
		amountUnitsMap = new HashMap<String, Units>();
		type2UnitsMap.put(Type.AMOUNT, amountUnitsMap);
		
		name2UnitsMap.put(Name.MOL, new Units(Type.AMOUNT, Name.MOL, PREFIX, "mol", NS));
		name2UnitsMap.put(Name.MILLIMOL, new Units(Type.AMOUNT, Name.MILLIMOL, PREFIX, "mmol", NS));
		
		amountUnitsMap.put("mol", name2UnitsMap.get(Name.MOL));
		amountUnitsMap.put("mmol", name2UnitsMap.get(Name.MILLIMOL));
		amountUnitsMap.put("mmoL", name2UnitsMap.get(Name.MILLIMOL)); // deprecated
	}
	
	private static void makeDimensionlessUnitsMap () {
		dimensionlessUnitsMap = new HashMap<String, Units>();
		type2UnitsMap.put(Type.DIMENSIONLESS, dimensionlessUnitsMap);
		
		name2UnitsMap.put(Name.STEP, new Units(Type.DIMENSIONLESS, Name.STEP, PREFIX, "step", NS));
		name2UnitsMap.put(Name.TIMES, new Units(Type.DIMENSIONLESS, Name.TIMES, PREFIX, "times", NS));
		
		amountUnitsMap.put("step", name2UnitsMap.get(Name.STEP));
		amountUnitsMap.put("times", name2UnitsMap.get(Name.TIMES));
	}
	
	
	
	
	private static void makeMassUnitsMap () {
		massUnitsMap = new HashMap<String, Units>();
		type2UnitsMap.put(Type.MASS, massUnitsMap);
		
		name2UnitsMap.put(Name.GRAM, new Units(Type.MASS, Name.GRAM, PREFIX, "g", NS));
		name2UnitsMap.put(Name.MILLIGRAM, new Units(Type.MASS, Name.MILLIGRAM, PREFIX, "mg", NS));
		name2UnitsMap.put(Name.PARENTH, new Units(Type.MASS, Name.PARENTH, PREFIX, "parentH", NS));
		
		massUnitsMap.put("g",     name2UnitsMap.get(Name.GRAM));
		massUnitsMap.put("gram",  name2UnitsMap.get(Name.GRAM));
		massUnitsMap.put("grams", name2UnitsMap.get(Name.GRAM));
		massUnitsMap.put("mg",    name2UnitsMap.get(Name.MILLIGRAM));
		massUnitsMap.put("MH+",    name2UnitsMap.get(Name.PARENTH));
		
	}
	
	private static void makePercentUnitsMap () {
		percentUnitsMap = new HashMap<String, Units>();
		type2UnitsMap.put(Type.PERCENT, percentUnitsMap);
		
		name2UnitsMap.put(Name.PERCENT, new Units(Type.PERCENT, Name.MILLIMOL, PREFIX, "percent", NS));
		
		percentUnitsMap.put("%", name2UnitsMap.get(Name.PERCENT));
	}
	
	private static void makeTemperatureUnitsMap () {
		temperatureUnitsMap = new HashMap<String, Units>();
		type2UnitsMap.put(Type.TEMPERATURE, temperatureUnitsMap);
		
		name2UnitsMap.put(Name.CELSIUS, new Units(Type.TEMPERATURE, Name.CELSIUS, PREFIX, "celsius", NS));
		name2UnitsMap.put(Name.KELVIN, new Units(Type.TEMPERATURE, Name.KELVIN, PREFIX, "kelvin", NS));
		
		temperatureUnitsMap.put("celsius", name2UnitsMap.get(Name.CELSIUS));
		temperatureUnitsMap.put("C", name2UnitsMap.get(Name.CELSIUS));
		temperatureUnitsMap.put("degC", name2UnitsMap.get(Name.CELSIUS));
		temperatureUnitsMap.put("deg", name2UnitsMap.get(Name.CELSIUS));
		
		temperatureUnitsMap.put("K", name2UnitsMap.get(Name.KELVIN));
		temperatureUnitsMap.put("kelvin", name2UnitsMap.get(Name.KELVIN));
	}
	
	private static void makeTimeUnitsMap () {
		timeUnitsMap = new HashMap<String, Units>();
		type2UnitsMap.put(Type.TIME, timeUnitsMap);
		
		name2UnitsMap.put(Name.YEAR, new Units(Type.TIME, Name.YEAR, PREFIX, "year", NS));
		name2UnitsMap.put(Name.MONTH, new Units(Type.TIME, Name.MONTH, PREFIX, "month", NS));
		name2UnitsMap.put(Name.WEEK, new Units(Type.TIME, Name.WEEK, PREFIX, "week", NS));
		name2UnitsMap.put(Name.DAY, new Units(Type.TIME, Name.DAY, PREFIX, "day", NS));
		name2UnitsMap.put(Name.HOUR, new Units(Type.TIME, Name.HOUR, PREFIX, "hour", NS));
		name2UnitsMap.put(Name.MINUTE, new Units(Type.TIME, Name.MINUTE, PREFIX, "minute", NS));
		name2UnitsMap.put(Name.SECOND, new Units(Type.TIME, Name.SECOND, PREFIX, "second", NS));
		
		timeUnitsMap.put("years", name2UnitsMap.get(Name.YEAR));
		timeUnitsMap.put("year", name2UnitsMap.get(Name.YEAR));
		timeUnitsMap.put("yrs", name2UnitsMap.get(Name.YEAR));
		timeUnitsMap.put("yr", name2UnitsMap.get(Name.YEAR));
		timeUnitsMap.put("y", name2UnitsMap.get(Name.YEAR));
		timeUnitsMap.put("months", name2UnitsMap.get(Name.MONTH));
		timeUnitsMap.put("month", name2UnitsMap.get(Name.MONTH));
		timeUnitsMap.put("weeks", name2UnitsMap.get(Name.WEEK));
		timeUnitsMap.put("week", name2UnitsMap.get(Name.WEEK));
		timeUnitsMap.put("wks", name2UnitsMap.get(Name.WEEK));
		timeUnitsMap.put("wk", name2UnitsMap.get(Name.WEEK));
		timeUnitsMap.put("days", name2UnitsMap.get(Name.DAY));
		timeUnitsMap.put("day", name2UnitsMap.get(Name.DAY));
		timeUnitsMap.put("d", name2UnitsMap.get(Name.DAY));
		timeUnitsMap.put("hours", name2UnitsMap.get(Name.HOUR));
		timeUnitsMap.put("hour", name2UnitsMap.get(Name.HOUR));
		timeUnitsMap.put("hrs", name2UnitsMap.get(Name.HOUR));
		timeUnitsMap.put("hr", name2UnitsMap.get(Name.HOUR));
		timeUnitsMap.put("h", name2UnitsMap.get(Name.HOUR));
		timeUnitsMap.put("minutes", name2UnitsMap.get(Name.MINUTE));
		timeUnitsMap.put("minute", name2UnitsMap.get(Name.MINUTE));
		timeUnitsMap.put("mins", name2UnitsMap.get(Name.MINUTE));
		timeUnitsMap.put("min", name2UnitsMap.get(Name.MINUTE));
		timeUnitsMap.put("m", name2UnitsMap.get(Name.MINUTE));
		timeUnitsMap.put("seconds", name2UnitsMap.get(Name.SECOND));
		timeUnitsMap.put("second", name2UnitsMap.get(Name.SECOND));
		timeUnitsMap.put("secs", name2UnitsMap.get(Name.SECOND));
		timeUnitsMap.put("sec", name2UnitsMap.get(Name.SECOND));
		timeUnitsMap.put("s", name2UnitsMap.get(Name.SECOND));
	}
	
	private static void makeVolumeUnitsMap () {
		volumeUnitsMap = new HashMap<String, Units>();
		type2UnitsMap.put(Type.VOLUME, volumeUnitsMap);
		
		name2UnitsMap.put(Name.LITRE, new Units(Type.VOLUME, Name.LITRE, PREFIX, "L", NS));
		name2UnitsMap.put(Name.MILLILITRE, new Units(Type.VOLUME, Name.MILLILITRE, PREFIX, "mL", NS));
		
		volumeUnitsMap.put("L", name2UnitsMap.get(Name.LITRE));
		volumeUnitsMap.put("mL", name2UnitsMap.get(Name.MILLILITRE));
		volumeUnitsMap.put("ml", name2UnitsMap.get(Name.MILLILITRE)); // deprecated
	}
	
	static {
		name2UnitsMap = new HashMap<Name, Units>();
		type2UnitsMap = new HashMap<Type, Map<String, Units>>();
		makeAmountUnitsMap();
		makeDimensionlessUnitsMap();
		makeMassUnitsMap();
		makePercentUnitsMap();
		makeTemperatureUnitsMap();
		makeTimeUnitsMap();
		makeVolumeUnitsMap();
	};
	
	
	private Type type;
	private Name name;
	private String prefix;
	private String id;
	private String namespace;
	
	private Units(Type type, Name name, String prefix, String id, String namespace) {
		this.type = type;
		this.name = name;
		this.prefix = prefix;
		this.id = id;
		this.namespace = namespace;
	}

	public Name getName() {
		return name;
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

	public static Units getUnits(Type type, String unitsValue) {
		Units units = null;
		if (unitsValue != null) {
			unitsValue = removeBrackets(unitsValue);
			unitsValue = removeTrailingPeriod(unitsValue);
			Map<String, Units> unitsMap = type2UnitsMap.get(type);
			units = (unitsMap == null) ? null : unitsMap.get(unitsValue);
		}
		return units;
	}

	public static Units getUnits(Name name) {
		return name2UnitsMap.get(name);
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
}
