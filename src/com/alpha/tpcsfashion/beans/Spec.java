package com.alpha.tpcsfashion.beans;

public class Spec {
	
	private String Specification;
	private String SpecValue;
	private String SpecCode;
	private GroupType GroupType;
	
	public void setSpecValue(String specValue){
		SpecValue = specValue;
	}
	public String getSpecValue(){
		return SpecValue;
	}
	
	public void setSpecCode(String specCode){
		SpecCode = specCode;
	}
	public String getSpecCode(){
		return SpecCode;
	}
	public void setSpecification(String specification){
		Specification = specification;
	}
	public String getSpecification(){
		return Specification;
	}
	
	public void setGroupType(GroupType groupType){
		GroupType = groupType;
	}
	public GroupType getGroupType(){
		return GroupType;
	}

	
}
