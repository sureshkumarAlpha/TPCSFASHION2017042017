package com.alpha.tpcsfashion.beans;

public class GroupSetting {
	
	private int GroupId;
	//private Group GroupName;
	private String GroupName;
	
	private int IsBarCodeApplicable;
	private int IsTrackApplicable;
	private int CompanyId;
	
	
	public void setGroupId(int groupId){
		GroupId = groupId;
	}
	public int getGroupId(){
		return GroupId;
	}
	
//	public void setGroupName(Group groupName){
//		GroupName = groupName;
//	}
//	public Group getGroupName(){
//		return GroupName;
//	}

	public void setGroupName(String groupName){
		GroupName = groupName;
	}
	public String getGroupName(){
		return GroupName;
	}
	
	public void setIsBarCodeApplicable(int isBarCodeApplicable){
		IsBarCodeApplicable = isBarCodeApplicable;
	}
	public int getIsBarCodeApplicable(){
		return IsBarCodeApplicable;
	}
	
	public void setIsTrackApplicable(int isTrackApplicable){
		IsTrackApplicable = isTrackApplicable;
	}
	public int getIsTrackApplicable(){
		return IsTrackApplicable;
	}
	
	public void setCompanyId(int companyId){
		CompanyId = companyId;
	}
	public int getCompanyId(){
		return CompanyId;
	}
}
