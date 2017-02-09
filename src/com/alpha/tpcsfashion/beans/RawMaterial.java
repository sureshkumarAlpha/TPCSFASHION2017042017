package com.alpha.tpcsfashion.beans;

public class RawMaterial {
	private  int rawMaterialId;
	private  String rawMaterialCode;
	private  String description;
	private  String groupName;
	
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setRawMaterialId(int rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}
	public int getRawMaterialId() {
		return rawMaterialId;
	}
	public void setRawMaterialCode(String rawMaterialCode) {
		this.rawMaterialCode = rawMaterialCode;
	}
	public String getRawMaterialCode() {
		return rawMaterialCode;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	

}
