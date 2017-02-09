package com.alpha.tpcsfashion.beans;

import java.util.List;



public class TaxGroup {
private int taxGroupId;	
private String taxGroup;
private int taxId;
private String taxName;
private float taxPercent;
private int displayOrder;
private String mode;

private int taxSlno;
private List<TaxGroup> taxGroupList;
private boolean taxGroupExists;
private boolean deleted;
private int Status;






public int getTaxSlno() {
	return taxSlno;
}
public void setTaxSlno(int taxSlno) {
	this.taxSlno = taxSlno;
}
public List<TaxGroup> getTaxGroupList() {
	return taxGroupList;
}
public void setTaxGroupList(List<TaxGroup> taxGroupList) {
	this.taxGroupList = taxGroupList;
}
public boolean isTaxGroupExists() {
	return taxGroupExists;
}
public void setTaxGroupExists(boolean taxGroupExists) {
	this.taxGroupExists = taxGroupExists;
}
public boolean isDeleted() {
	return deleted;
}
public void setDeleted(boolean deleted) {
	this.deleted = deleted;
}
public int getStatus() {
	return Status;
}
public void setStatus(int status) {
	Status = status;
}
public String getMode() {
	return mode;
}
public void setMode(String mode) {
	this.mode = mode;
}
public String getTaxName() {
	return taxName;
}
public void setTaxName(String taxName) {
	this.taxName = taxName;
}
public String getTaxGroup() {
	return taxGroup;
}
public void setTaxGroup(String taxGroup) {
	this.taxGroup = taxGroup;
}
public float getTaxPercent() {
	return taxPercent;
}
public void setTaxPercent(float taxPercent) {
	this.taxPercent = taxPercent;
}
public int getDisplayOrder() {
	return displayOrder;
}
public void setDisplayOrder(int displayOrder) {
	this.displayOrder = displayOrder;
}
public int getTaxGroupId() {
	return taxGroupId;
}
public void setTaxGroupId(int taxGroupId) {
	this.taxGroupId = taxGroupId;
}
public int getTaxId() {
	return taxId;
}
public void setTaxId(int taxId) {
	this.taxId = taxId;
}


}
