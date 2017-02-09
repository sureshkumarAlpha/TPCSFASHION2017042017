package com.alpha.tpcsfashion.beans;

public class Selection {

	private int selectionId;
	private String selectionName;
	private int isRejection;
	private String commonName;
	private String leatherIds;
	private int leatherId;
	private int displayOrder;
	private float rate;
	private int lotTrackingYes;
	private int lotTrackingNo;
	private int stageId;
	private float totGRNpcs;
	private float totAssortedPcs; 
	private float balPcs;
	private String rateOn;



	
	public int getStageId() {
	return stageId;
	}
	public void setStageId(int stageId) {
		this.stageId = stageId;
	}
	public float getTotGRNpcs() {
		return totGRNpcs;
	}
	public void setTotGRNpcs(float totGRNpcs) {
		this.totGRNpcs = totGRNpcs;
	}
	public float getTotAssortedPcs() {
		return totAssortedPcs;
	}
	public void setTotAssortedPcs(float totAssortedPcs) {
		this.totAssortedPcs = totAssortedPcs;
	}
	public float getBalPcs() {
		return balPcs;
	}
	public void setBalPcs(float balPcs) {
		this.balPcs = balPcs;
	}
	public String getRateOn() {
		return rateOn;
	}
	public void setRateOn(String rateOn) {
		this.rateOn = rateOn;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	public void setSelectionId(int selectionId) {
		this.selectionId = selectionId;
	}
	public int getSelectionId() {
		return selectionId;
	}
	public void setSelectionName(String selectionName) {
		this.selectionName = selectionName;
	}
	public String getSelectionName() {
		return selectionName;
	}
	public void setIsRejection(int isRejection) {
		this.isRejection = isRejection;
	}
	public int getIsRejection() {
		return isRejection;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public String getCommonName() {
		return commonName;
	}
	public void setLeatherIds(String leatherIds) {
		this.leatherIds = leatherIds;
	}
	public String getLeatherIds() {
		return leatherIds;
	}
	public int getLeatherId() {
		return leatherId;
	}
	public void setLeatherId(int leatherId) {
		this.leatherId = leatherId;
	}
	public int getLotTrackingYes() {
		return lotTrackingYes;
	}
	public void setLotTrackingYes(int lotTrackingYes) {
		this.lotTrackingYes = lotTrackingYes;
	}
	public int getLotTrackingNo() {
		return lotTrackingNo;
	}
	public void setLotTrackingNo(int lotTrackingNo) {
		this.lotTrackingNo = lotTrackingNo;
	}
	
}