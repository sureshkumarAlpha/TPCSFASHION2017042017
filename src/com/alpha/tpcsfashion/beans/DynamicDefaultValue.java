package com.alpha.tpcsfashion.beans;

import java.io.Serializable;

public class DynamicDefaultValue implements Serializable {
	/**
	 * uid
	 */
	private static final long serialVersionUID = 1L;
	
	private int fieldId;
    private String defaultValue;
    private int sortOrder;
    
	public int getFieldId() {
		return fieldId;
	}
	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	

   
}
