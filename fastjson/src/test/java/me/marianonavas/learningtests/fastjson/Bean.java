package me.marianonavas.learningtests.fastjson;

import java.util.Date;

public class Bean {
	private String str;
	private Date date;
	private int integerPrimitive;
	private Integer integerObject;
	private boolean booleanPrimitive;
	private Boolean booleanObject;
	
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getIntegerPrimitive() {
		return integerPrimitive;
	}
	public void setIntegerPrimitive(int integerPrimitive) {
		this.integerPrimitive = integerPrimitive;
	}
	public Integer getIntegerObject() {
		return integerObject;
	}
	public void setIntegerObject(Integer integerObject) {
		this.integerObject = integerObject;
	}
	public boolean isBooleanPrimitive() {
		return booleanPrimitive;
	}
	public void setBooleanPrimitive(boolean booleanPrimitive) {
		this.booleanPrimitive = booleanPrimitive;
	}
	public Boolean getBooleanObject() {
		return booleanObject;
	}
	public void setBooleanObject(Boolean booleanObject) {
		this.booleanObject = booleanObject;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((booleanObject == null) ? 0 : booleanObject.hashCode());
		result = prime * result + (booleanPrimitive ? 1231 : 1237);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((integerObject == null) ? 0 : integerObject.hashCode());
		result = prime * result + integerPrimitive;
		result = prime * result + ((str == null) ? 0 : str.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bean other = (Bean) obj;
		if (booleanObject == null) {
			if (other.booleanObject != null)
				return false;
		} else if (!booleanObject.equals(other.booleanObject))
			return false;
		if (booleanPrimitive != other.booleanPrimitive)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (integerObject == null) {
			if (other.integerObject != null)
				return false;
		} else if (!integerObject.equals(other.integerObject))
			return false;
		if (integerPrimitive != other.integerPrimitive)
			return false;
		if (str == null) {
			if (other.str != null)
				return false;
		} else if (!str.equals(other.str))
			return false;
		return true;
	}
}
