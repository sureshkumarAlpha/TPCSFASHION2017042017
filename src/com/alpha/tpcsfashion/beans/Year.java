package com.alpha.tpcsfashion.beans;

public class Year {


	public int getYearId(){
		return YearId;
	}
	public void setYearId(int yearId){
		YearId=yearId;
	}
	public String getStartDate()
	{
		return StartDate;
	}
	public void setStartDate(String startDate)
	{
		StartDate = startDate;
	}
	public String getEndDate()
	{
		return EndDate;
	}
	public void setEndDate(String endDate)
	{
		EndDate = endDate;
	}
	
	
	
	private int YearId;
	private String StartDate;
	private String EndDate;
}
