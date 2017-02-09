package com.alpha.tpcsfashion.beans;

public class Season {
	private String seasonName;	
	private int seasonId;
	private int isActive;
	private String updatedBy;
	private String enteredBy;
	private String fromDate;
	private String toDate;
	private String mode;
	private boolean SeasonExists;
	private boolean deleted;
	/**
	 * @return the seasonId
	 */
	public int getSeasonId() {
		return seasonId;
	}
	/**
	 * @param seasonId the seasonId to set
	 */
	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}
	/**
	 * @return the seasonName
	 */
	public String getSeasonName() {
		return seasonName;
	}
	/**
	 * @param seasonName the seasonName to set
	 */
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the isActive
	 */
	public int getIsActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(int isActive) {
		this.isActive = isActive;
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
	 * @return the seasonExists
	 */
	public boolean isSeasonExists() {
		return SeasonExists;
	}
	/**
	 * @param seasonExists the seasonExists to set
	 */
	public void setSeasonExists(boolean seasonExists) {
		SeasonExists = seasonExists;
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
