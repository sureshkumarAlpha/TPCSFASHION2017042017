package com.alpha.tpcsfashion.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alpha.tpcsfashion.beans.DynamicFields;

public class Variant {

private String variantCode;	
private int variantId;
private String variantName;
private String remarks;
private int status;
private String enteredBy;
private String updatedBy;
private boolean codeExists;
private boolean DescExists;
private String mode;
private String pageFieldName;
private String name;
private int id;

private String sqlxmlDynamicFields;
private List<DynamicFields> variantDynList = new ArrayList<DynamicFields>();	

private List<Map<String,String>> variantMapList;


public String getVariantName() {
	return variantName;
}
public void setVariantName(String variantName) {
	this.variantName = variantName;
}


public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
/**
 * @return the updatedBy
 */
public String getUpdatedBy() {
	return updatedBy;
}
/**
 * @param updatedBy the updatedBy to set
 */
public void setUpdatedBy(String updatedBy) {
	this.updatedBy = updatedBy;
}
/**
 * @return the enteredBy
 */
public String getEnteredBy() {
	return enteredBy;
}
/**
 * @param enteredBy the enteredBy to set
 */
public void setEnteredBy(String enteredBy) {
	this.enteredBy = enteredBy;
}
/**
 * @return the mode
 */
public String getMode() {
	return mode;
}
/**
 * @param mode the mode to set
 */
public void setMode(String mode) {
	this.mode = mode;
}
/**
 * @return the codeExists
 */
public boolean isCodeExists() {
	return codeExists;
}
/**
 * @param codeExists the codeExists to set
 */
public void setCodeExists(boolean codeExists) {
	this.codeExists = codeExists;
}
/**
 * @return the descExists
 */
public boolean isDescExists() {
	return DescExists;
}
/**
 * @param descExists the descExists to set
 */
public void setDescExists(boolean descExists) {
	DescExists = descExists;
}

/**
 * @return the sqlxmlDynamicFields
 */
public String getSqlxmlDynamicFields() {
	return sqlxmlDynamicFields;
}
/**
 * @param sqlxmlDynamicFields the sqlxmlDynamicFields to set
 */
public void setSqlxmlDynamicFields(String sqlxmlDynamicFields) {
	this.sqlxmlDynamicFields = sqlxmlDynamicFields;
}


public String getPageFieldName() {
	return pageFieldName;
}
public void setPageFieldName(String pageFieldName) {
	this.pageFieldName = pageFieldName;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getVariantCode() {
	return variantCode;
}
public void setVariantCode(String variantCode) {
	this.variantCode = variantCode;
}
public int getVariantId() {
	return variantId;
}
public void setVariantId(int variantId) {
	this.variantId = variantId;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public List<DynamicFields> getVariantDynList() {
	return variantDynList;
}
public void setVariantDynList(List<DynamicFields> variantDynList) {
	this.variantDynList = variantDynList;
}
public List<Map<String, String>> getVariantMapList() {
	return variantMapList;
}
public void setVariantMapList(List<Map<String, String>> variantMapList) {
	this.variantMapList = variantMapList;
}





}
