package com.alpha.tpcsfashion.beans;

	
public class UnitMaster {
	private String BasicUnit;
	private String Unit;
	private float Cvalue;
	
	
	
	public void setCvalue(float cvalue){
		Cvalue = cvalue;
	}
	public float getCvalue(){
		return Cvalue;
	}
	
	public void setUnit(String unit){
		Unit = unit;
	}
	public String getUnit(){
		return Unit;
	}
	
	public void setBasicUnit(String basicUnit){
		BasicUnit = basicUnit;
	}
	public String getBasicUnit(){
		return BasicUnit;
	}

}
