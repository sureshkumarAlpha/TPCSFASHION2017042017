package com.alpha.tpcsfashion.beans;

public class OperationOrStage {

	private int operationId;
	private String operationName;
	private String operationDesc;
	private int operationSeqNo;
	private int operationStatus;
	private String mode;
	 private int displayOrder;
	 private int inspectionRequired;
	 private int production;
	 private String mainGroup;
	 private String subGroup;
	 private boolean deleted;
	
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getOperationId() {
		return operationId;
	}
	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getOperationDesc() {
		return operationDesc;
	}
	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}
	public int getOperationSeqNo() {
		return operationSeqNo;
	}
	public void setOperationSeqNo(int operationSeqNo) {
		this.operationSeqNo = operationSeqNo;
	}
	public int getOperationStatus() {
		return operationStatus;
	}
	public void setOperationStatus(int operationStatus) {
		this.operationStatus = operationStatus;
	}
	/**
	 * @return the inspectionRequired
	 */
	public int getInspectionRequired() {
		return inspectionRequired;
	}
	/**
	 * @param inspectionRequired the inspectionRequired to set
	 */
	public void setInspectionRequired(int inspectionRequired) {
		this.inspectionRequired = inspectionRequired;
	}
	/**
	 * @return the displayOrder
	 */
	public int getDisplayOrder() {
		return displayOrder;
	}
	/**
	 * @param displayOrder the displayOrder to set
	 */
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	/**
	 * @return the production
	 */
	public int getProduction() {
		return production;
	}
	/**
	 * @param production the production to set
	 */
	public void setProduction(int production) {
		this.production = production;
	}
	/**
	 * @return the mainGroup
	 */
	public String getMainGroup() {
		return mainGroup;
	}
	/**
	 * @param mainGroup the mainGroup to set
	 */
	public void setMainGroup(String mainGroup) {
		this.mainGroup = mainGroup;
	}
	/**
	 * @return the subGroup
	 */
	public String getSubGroup() {
		return subGroup;
	}
	/**
	 * @param subGroup the subGroup to set
	 */
	public void setSubGroup(String subGroup) {
		this.subGroup = subGroup;
	}
	/**
	 * @return the deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}
	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
