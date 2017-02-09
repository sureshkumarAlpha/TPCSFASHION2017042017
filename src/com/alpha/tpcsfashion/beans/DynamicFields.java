package com.alpha.tpcsfashion.beans;

import java.io.Serializable;
import java.util.Map;

public class DynamicFields implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3590529118310243930L;
	private int dynamicFieldId;
	private String dbFieldName;
	private String displayName;
	private String fieldTypeName;
	private String dataTypeName;
	private int sortOrder;
	private int length;
	private boolean required;
	private boolean fixed;
	private String fieldValue;
	private int noOfCheckBoxs;
	private String defaultValue;
	private String alignment;
	private Map<String,String> dbFieldMap;
	private String columnWidth;
	private String Name;
	private int Id;
	private String pageFieldName;
	


	public String getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(String columnWidth) {
		this.columnWidth = columnWidth;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Map<String, String> getDbFieldMap() {
		return dbFieldMap;
	}

	public void setDbFieldMap(Map<String, String> dbFieldMap) {
		this.dbFieldMap = dbFieldMap;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getDynamicFieldId() {
		return dynamicFieldId;
	}

	public void setDynamicFieldId(int dynamicFieldId) {
		this.dynamicFieldId = dynamicFieldId;
	}

	public String getDbFieldName() {
		return dbFieldName;
	}

	public void setDbFieldName(String dbFieldName) {
		this.dbFieldName = dbFieldName;
	}

	public String getPageFieldName() {
		return pageFieldName;
	}

	public void setPageFieldName(String pageFieldName) {
		this.pageFieldName = pageFieldName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public String getFieldTypeName() {
		return fieldTypeName;
	}

	public void setFieldTypeName(String fieldTypeName) {
		this.fieldTypeName = fieldTypeName;
	}

	public String getDataTypeName() {
		return dataTypeName;
	}

	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public int getNoOfCheckBoxs() {
		return noOfCheckBoxs;
	}

	public void setNoOfCheckBoxs(int noOfCheckBoxs) {
		this.noOfCheckBoxs = noOfCheckBoxs;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	

}
