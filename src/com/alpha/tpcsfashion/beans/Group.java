package com.alpha.tpcsfashion.beans;

public class Group {
	
	private int GroupId;
	private String GroupName;
	private String GroupCode;
	private String ShortName ;
	private int IsBarCodeRequired;
	private int IsTrackRequired;
	private int GroupTypeId;
	private int GroupUnder;
	private int PrimaryGroup;
	private int GroupLevel;
	private int CompanyId;
	private int EnteredBy;
	private String EnteredOn;
	private int UpdatedBy;
	private String UpdatedOn;
	private String GroupTypeName;
	private String GroupedUnderName;
	
	
	public void setGroupId(int groupId){
		GroupId = groupId;
	}
	public int getGroupId(){
		return GroupId;
	}
	
	public void setGroupName(String groupName){
		GroupName = groupName;
	}
	public String getGroupName(){
		return GroupName;
	}
	
	public void setGroupCode(String groupCode){
		GroupCode = groupCode;
	}
	public String getGroupCode(){
		return GroupCode;
	}
	
	public void setShortName(String shortName){
		ShortName = shortName;
	}
	public String getShortName(){
		return ShortName;
	}
	
	public void setIsBarCodeRequired(int isBarCodeRequired){
		IsBarCodeRequired = isBarCodeRequired;
	}
	public int getIsBarCodeRequired(){
		return IsBarCodeRequired;
	}
	
	public void setIsTrackRequired(int isTrackRequired){
		IsTrackRequired = isTrackRequired;
	}
	public int getIsTrackRequired(){
		return IsTrackRequired;
	}
	
	public void setGroupTypeId(int groupTypeId){
		GroupTypeId = groupTypeId;
	}
	public int getGroupTypeId(){
		return GroupTypeId;
	}
	
	public void setGroupUnder(int groupUnder){
		GroupUnder = groupUnder;
	}
	public int getGroupUnder(){
		return GroupUnder;
	}
	
	public void setPrimaryGroup(int primaryGroup){
		PrimaryGroup = primaryGroup;
	}
	public int getPrimaryGroup(){
		return PrimaryGroup;
	}
	
	public void setGroupLevel(int groupLevel){
		GroupLevel = groupLevel;
	}
	public int getGroupLevel(){
		return GroupLevel;
	}
	
	public void setCompanyId(int companyId){
		CompanyId = companyId;
	}
	public int getCompanyId(){
		return CompanyId;
	}
	
	public void setEnteredBy(int enteredBy){
		EnteredBy = enteredBy;
	}
	public int getEnteredBy(){
		return EnteredBy;
	}
	
	public void setEnteredOn(String enteredOn){
		EnteredOn = enteredOn;
	}
	public String getEnteredOn(){
		return EnteredOn;
	}
	
	public void setUpdatedBy(int updatedBy){
		UpdatedBy = updatedBy;
	}
	public int getUpdatedBy(){
		return UpdatedBy;
	}
	
	public void setUpdatedOn(String updatedOn){
		UpdatedOn = updatedOn;
	}
	public String getUpdatedOn(){
		return UpdatedOn;
	}
	
	public void setGroupTypeName(String groupTypeName){
		GroupTypeName = groupTypeName;
	}
	public String getGroupTypeName(){
		return GroupTypeName;
	}
	
	public void setGroupedUnderName(String groupedUnderName){
		GroupedUnderName = groupedUnderName;
	}
	public String getGroupedUnderName(){
		return GroupedUnderName;
	}

}
