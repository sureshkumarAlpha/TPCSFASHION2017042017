package com.alpha.tpcsfashion.beans;

import java.util.List;

public class BOMHeader{
	private int	companyId;
	private int	yearId;
	private int	locationId;
	
	private String isMultipleBranch;
	private int defaultBranch;
	private int lastNo;
	private List<String> autoNoPrefixList;
	private String checkAutoNoType;
	private boolean isbulkaction;
	private String isbulkexist;
	
	private String primaryImageUrl;
	private	String	bomNo	;
	private	int	bomId	;
	private	int	productId	;
	private	String	product;
	private	int	versionNo	;
	private	int	customerId	;
	private	String	customer	;
	private	int	parentBomId	;
	private	String	parentBomNo;
	private	String	remarks	;
	private	String	bomDate	;
	private int	detTableWidth;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getDetTableWidth() {
		return detTableWidth;
	}
	public void setDetTableWidth(int detTableWidth) {
		this.detTableWidth = detTableWidth;
	}
	public String getPrimaryImageUrl() {
		return primaryImageUrl;
	}
	public void setPrimaryImageUrl(String primaryImageUrl) {
		this.primaryImageUrl = primaryImageUrl;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getParentBomNo() {
		return parentBomNo;
	}
	public void setParentBomNo(String parentBomNo) {
		this.parentBomNo = parentBomNo;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getIsMultipleBranch() {
		return isMultipleBranch;
	}
	public void setIsMultipleBranch(String isMultipleBranch) {
		this.isMultipleBranch = isMultipleBranch;
	}
	public int getDefaultBranch() {
		return defaultBranch;
	}
	public void setDefaultBranch(int defaultBranch) {
		this.defaultBranch = defaultBranch;
	}
	public int getLastNo() {
		return lastNo;
	}
	public void setLastNo(int lastNo) {
		this.lastNo = lastNo;
	}
	public List<String> getAutoNoPrefixList() {
		return autoNoPrefixList;
	}
	public void setAutoNoPrefixList(List<String> autoNoPrefixList) {
		this.autoNoPrefixList = autoNoPrefixList;
	}
	public String getCheckAutoNoType() {
		return checkAutoNoType;
	}
	public void setCheckAutoNoType(String checkAutoNoType) {
		this.checkAutoNoType = checkAutoNoType;
	}
	public boolean isIsbulkaction() {
		return isbulkaction;
	}
	public void setIsbulkaction(boolean isbulkaction) {
		this.isbulkaction = isbulkaction;
	}
	public String getIsbulkexist() {
		return isbulkexist;
	}
	public void setIsbulkexist(String isbulkexist) {
		this.isbulkexist = isbulkexist;
	}
	public String getBomNo() {
		return bomNo;
	}
	public void setBomNo(String bomNo) {
		this.bomNo = bomNo;
	}
	public int getBomId() {
		return bomId;
	}
	public void setBomId(int bomId) {
		this.bomId = bomId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getParentBomId() {
		return parentBomId;
	}
	public void setParentBomId(int parentBomId) {
		this.parentBomId = parentBomId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getBomDate() {
		return bomDate;
	}
	public void setBomDate(String bomDate) {
		this.bomDate = bomDate;
	}

	
	
	
	
	
	
	
	
	
}