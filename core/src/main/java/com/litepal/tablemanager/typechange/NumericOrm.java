package com.litepal.tablemanager.typechange;/**
 * This class deals with numeric type.
 * 
 * @author Tony Green
 * @since 1.0
 */
public class NumericOrm extends OrmChange {/**
	 * If the field type passed in is int, long or short, it will change it into
	 * integer as column type.
	 */
	@Override
	public String object2Relation(String fieldType) {
		if (fieldType != null) {
			if (fieldType.equals("int") || fieldType.equals("java.lang.Integer")) {
				return "integer";
			}
			if (fieldType.equals("long") || fieldType.equals("java.lang.Long")) {
				return "integer";
			}
			if (fieldType.equals("short") || fieldType.equals("java.lang.Short")) {
				return "integer";
			}
		}
		return null;
	}}
