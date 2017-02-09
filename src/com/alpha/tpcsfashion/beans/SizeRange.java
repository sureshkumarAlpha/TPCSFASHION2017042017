package com.alpha.tpcsfashion.beans;

import java.util.List;
public class SizeRange {
	private int sizeRangeId;	
	private String sizeRange;
	private int applicableTo ;
	private int isActive;
	private int isActivedet;
	private int slNo;
	private int sizeId;
	private String sizeName;
	private String mode;
	private boolean sizeRangeExists;
	private boolean deleted;
	private List<SizeRange> sizeRangeList;
	private String rowIds;
	private int rowCount;
	private boolean sizeExists;
	private boolean inserted;
	private int enteredBy;
	private int updatedBy;
	private String enteredOn;
	private String updatedOn;	
	private String enterDate;
	private String updateDate;
	
	
	
	
	
	
	
	
	
	


	public String getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the sizerangeId
	 */
	public int getSizeRangeId() {
		return sizeRangeId;
	}
	/**
	 * @param sizerangeId the sizerangeId to set
	 */
	public void setSizeRangeId(int sizeRangeId) {
		this.sizeRangeId = sizeRangeId;
	}
	/**
	 * @return the isactive
	 */
	public int getIsactive() {
		return isActive;
	}
	public int getEnteredBy() {
		return enteredBy;
	}
	public void setEnteredBy(int enteredBy) {
		this.enteredBy = enteredBy;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getEnteredOn() {
		return enteredOn;
	}
	public void setEnteredOn(String enteredOn) {
		this.enteredOn = enteredOn;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	/**
	 * @param isactive the isactive to set
	 */
	public void setIsActive(int isactive) {
		this.isActive = isactive;
	}
	/**
	 * @return the applicableTo
	 */
	public int getApplicableTo() {
		return applicableTo;
	}
	/**
	 * @param applicableTo the applicableTo to set
	 */
	public void setApplicableTo(int applicableTo) {
		this.applicableTo = applicableTo;
	}
	/**
	 * @return the isActivedet
	 */
	public int getIsActivedet() {
		return isActivedet;
	}
	/**
	 * @param isActivedet the isActivedet to set
	 */
	public void setIsActivedet(int isActivedet) {
		this.isActivedet = isActivedet;
	}
	/**
	 * @return the sizeId
	 */
	public int getSizeId() {
		return sizeId;
	}
	/**
	 * @param sizeId the sizeId to set
	 */
	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}
	/**
	 * @return the sizeRange
	 */
	public String getSizeRange() {
		return sizeRange;
	}
	/**
	 * @param sizeRange the sizeRange to set
	 */
	public void setSizeRange(String sizeRange) {
		this.sizeRange = sizeRange;
	}
	/**
	 * @return the slNo
	 */
	public int getSlNo() {
		return slNo;
	}
	/**
	 * @param slNo the slNo to set
	 */
	public void setSlNo(int slNo) {
		this.slNo = slNo;
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
	 * @return the sizeRangeList
	 */
	public List<SizeRange> getSizeRangeList() {
		return sizeRangeList;
	}
	/**
	 * @param sizeRangeList the sizeRangeList to set
	 */
	public void setSizeRangeList(List<SizeRange> sizeRangeList) {
		this.sizeRangeList = sizeRangeList;
	}
	/**
	 * @param isactive the isactive to set
	 */
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
	/**
	 * @return the sizeRangeExists
	 */


	public boolean isSizeRangeExists() {
		// TODO Auto-generated method stub
		return sizeRangeExists;
	}
	/**
	 * @param sizeRangeExists the sizeRangeExists to set
	 */
	public void setSizeRangeExists(boolean sizeRangeExists) {
		this.sizeRangeExists = sizeRangeExists;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public String getRowIds() {
		return rowIds;
	}
	public void setRowIds(String rowIds) {
		this.rowIds = rowIds;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public boolean isSizeExists() {
		return sizeExists;
	}
	public void setSizeExists(boolean sizeExists) {
		this.sizeExists = sizeExists;
	}
	public boolean isInserted() {
		return inserted;
	}
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

}
