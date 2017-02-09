package com.alpha.tpcsfashion.beans;

import java.util.List;

public class PurchaseOrderHeader 
{	

	private List<PurchaseOrderHeader> poUsers;
	private List<PurchaseOrderHeader> defUsers;
	private List<String> autoNoPrefixList;

	private int CompanyId;
	private int LocationId;
	private int YearId;
	private int	poId;
	private String poPrefix ;
	private String poNo ;
	private String poDate;
	private int	partyId;
	private String partyName;
	private String otherRef;
	private int currencyId;
	private String currencyName;
	private int seasonId;
	private String seasonName;
	private String exRate;
	private String remark;
	private String deliverTo;
	private String purchaseType;
	private String poValidTill;
	private String paymentTerms;
	private String deliveryTerms;
	private String shipToAddress;
	private String internalMemo;
	
	private String attachType;
	private String attachPath;
	private	String urlType;
	private	String url;
	private	String fileName;
	private	int detTableWidth;
	
	private int userId;
	private String userFullName;
	private String loginName;
	private boolean sendLater;
	private String checkAutoNoType;
	private String contactImage;
	
	private String tcValues;
	private int sl;
	private String slno;
	private String term;
	private String prefix;
	private String particular;
	private String tcIds;
	private int tcCnt;

	private int roundOffRate;
	private int roundOffQty;
	private int roundOffValue;

	private	List<PurchaseOrderHeader> attachFiles;
	private List<PurchaseOrderHeader> tcList;
	
	private String discountPercent;
	private String totalValueFcy;
	private List<TaxGroup> taxGroupList;
	
	List<CommonUtil> purchaseTypeList;
	 
	
	public int getDetTableWidth() {
		return detTableWidth;
	}

	public void setDetTableWidth(int detTableWidth) {
		this.detTableWidth = detTableWidth;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	private int detailVisFieldCnt;

	public List<PurchaseOrderHeader> getPoUsers() {
		return poUsers;
	}

	public void setPoUsers(List<PurchaseOrderHeader> poUsers) {
		this.poUsers = poUsers;
	}

	public List<PurchaseOrderHeader> getDefUsers() {
		return defUsers;
	}

	public void setDefUsers(List<PurchaseOrderHeader> defUsers) {
		this.defUsers = defUsers;
	}

	public int getCompanyId() {
		return CompanyId;
	}

	public void setCompanyId(int companyId) {
		CompanyId = companyId;
	}

	public int getLocationId() {
		return LocationId;
	}

	public void setLocationId(int locationId) {
		LocationId = locationId;
	}

	public int getYearId() {
		return YearId;
	}

	public void setYearId(int yearId) {
		YearId = yearId;
	}

	public int getPoId() {
		return poId;
	}

	public void setPoId(int poId) {
		this.poId = poId;
	}

	public String getPoPrefix() {
		return poPrefix;
	}

	public void setPoPrefix(String poPrefix) {
		this.poPrefix = poPrefix;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getPoDate() {
		return poDate;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}

	public int getPartyId() {
		return partyId;
	}

	public void setPartyId(int partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getOtherRef() {
		return otherRef;
	}

	public void setOtherRef(String otherRef) {
		this.otherRef = otherRef;
	}

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getExRate() {
		return exRate;
	}

	public void setExRate(String exRate) {
		this.exRate = exRate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeliverTo() {
		return deliverTo;
	}

	public void setDeliverTo(String deliverTo) {
		this.deliverTo = deliverTo;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getAttachType() {
		return attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}

	public String getAttachPath() {
		return attachPath;
	}

	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}

	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<PurchaseOrderHeader> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(List<PurchaseOrderHeader> attachFiles) {
		this.attachFiles = attachFiles;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public boolean isSendLater() {
		return sendLater;
	}

	public void setSendLater(boolean sendLater) {
		this.sendLater = sendLater;
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

	public String getContactImage() {
		return contactImage;
	}

	public void setContactImage(String contactImage) {
		this.contactImage = contactImage;
	}

	public String getTcValues() {
		return tcValues;
	}

	public void setTcValues(String tcValues) {
		this.tcValues = tcValues;
	}

	public int getSl() {
		return sl;
	}

	public void setSl(int sl) {
		this.sl = sl;
	}

	public String getSlno() {
		return slno;
	}

	public void setSlno(String slno) {
		this.slno = slno;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getParticular() {
		return particular;
	}

	public void setParticular(String particular) {
		this.particular = particular;
	}

	public String getTcIds() {
		return tcIds;
	}

	public void setTcIds(String tcIds) {
		this.tcIds = tcIds;
	}

	public int getTcCnt() {
		return tcCnt;
	}

	public void setTcCnt(int tcCnt) {
		this.tcCnt = tcCnt;
	}

	public List<PurchaseOrderHeader> getTcList() {
		return tcList;
	}

	public void setTcList(List<PurchaseOrderHeader> tcList) {
		this.tcList = tcList;
	}

	public int getDetailVisFieldCnt() {
		return detailVisFieldCnt;
	}

	public void setDetailVisFieldCnt(int detailVisFieldCnt) {
		this.detailVisFieldCnt = detailVisFieldCnt;
	}

	public String getPoValidTill() {
		return poValidTill;
	}

	public void setPoValidTill(String poValidTill) {
		this.poValidTill = poValidTill;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public String getDeliveryTerms() {
		return deliveryTerms;
	}

	public void setDeliveryTerms(String deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
	}

	public String getShipToAddress() {
		return shipToAddress;
	}

	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

	public String getInternalMemo() {
		return internalMemo;
	}

	public void setInternalMemo(String internalMemo) {
		this.internalMemo = internalMemo;
	}

	public int getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public int getRoundOffRate() {
		return roundOffRate;
	}

	public void setRoundOffRate(int roundOffRate) {
		this.roundOffRate = roundOffRate;
	}

	public int getRoundOffQty() {
		return roundOffQty;
	}

	public void setRoundOffQty(int roundOffQty) {
		this.roundOffQty = roundOffQty;
	}

	public int getRoundOffValue() {
		return roundOffValue;
	}

	public void setRoundOffValue(int roundOffValue) {
		this.roundOffValue = roundOffValue;
	}

	public List<TaxGroup> getTaxGroupList() {
		return taxGroupList;
	}

	public void setTaxGroupList(List<TaxGroup> taxGroupList) {
		this.taxGroupList = taxGroupList;
	}

	public String getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getTotalValueFcy() {
		return totalValueFcy;
	}

	public void setTotalValueFcy(String totalValueFcy) {
		this.totalValueFcy = totalValueFcy;
	}

	public List<CommonUtil> getPurchaseTypeList() {
		return purchaseTypeList;
	}

	public void setPurchaseTypeList(List<CommonUtil> purchaseTypeList) {
		this.purchaseTypeList = purchaseTypeList;
	}


	

}
