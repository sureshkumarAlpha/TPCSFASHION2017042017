package com.alpha.tpcsfashion.beans;

import java.util.List;

public class BuyerOrderHeader {

	private 	int	companyId;
	private 	int	locationId;
	private 	int	yearId;
	private 	int	boId;
	private 	String boPrefix;
	private 	int boNo;
	private 	String boPrefixNo;
	private 	String boDate;
	private 	int	customerId;
	private 	String customerName;
	private 	String buyerPO;
	private 	int	currencyId;
	private 	String currencyName;
	private 	String exRate;
	private 	int	seasonId;
	private 	String seasonName;
	private 	int	agentId;
	private 	String agentName;
	private 	String	customerPlanNo;
	private 	String	paymentTerms;
	private 	String	insurenceTerms;
	private 	String	deliveryTerms;
	private 	String	deliveryTo;
	private 	String	specialInstruction;
	private 	String	packingLabeling;
	private 	String buyerPoNo;
	private		String piNo;
	private		String piDate;
	private		String modeOfShip;
	private int	detTableWidth;
	private String attachType;
	private String attachPath;
	private		String 	urlType;
	private		String 	url;
	private		String 	fileName;
	private		List<BuyerOrderHeader> attachFiles;
	
	private int userId;
	private String userFullName;
	private String loginName;
	
	private int slNo;
	private String instruction;
	private String particulars;
	
	private boolean sendLater;
	
	private List<String> autoNoPrefixList;
	private String checkAutoNoType;
	
	private List<BuyerOrderHeader> boUsers;
	private List<BuyerOrderHeader> defUsers;
	private String contactImage;
	
	private int packingRowCount;
	private String packingRowIds;
	private List<String> packingRowIdList;
	
	private String tcValues;
	private int sl;
	private String slno;
	private String term;
	private String particular;
	private String tcIds;
	private int tcCnt;
	private List<BuyerOrderHeader> tcList;
	
	private int detailVisFieldCnt;
	
	private String packingScheduleData;
	
	
	
	
	
	
	
	
	
	
	public int getDetTableWidth() {
		return detTableWidth;
	}
	public void setDetTableWidth(int detTableWidth) {
		this.detTableWidth = detTableWidth;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public int getBoId() {
		return boId;
	}
	public void setBoId(int boId) {
		this.boId = boId;
	}
 
	public String getBoDate() {
		return boDate;
	}
	public void setBoDate(String boDate) {
		this.boDate = boDate;
	}
 
	public String getBuyerPO() {
		return buyerPO;
	}
	public void setBuyerPO(String buyerPO) {
		this.buyerPO = buyerPO;
	}
	public int getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}
	
	public String getExRate() {
		return exRate;
	}
	public void setExRate(String exRate) {
		this.exRate = exRate;
	}
	public int getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public String getCustomerPlanNo() {
		return customerPlanNo;
	}
	public void setCustomerPlanNo(String customerPlanNo) {
		this.customerPlanNo = customerPlanNo;
	}
	public String getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	public String getInsurenceTerms() {
		return insurenceTerms;
	}
	public void setInsurenceTerms(String insurenceTerms) {
		this.insurenceTerms = insurenceTerms;
	}
	public String getDeliveryTerms() {
		return deliveryTerms;
	}
	public void setDeliveryTerms(String deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
	}
	public String getSpecialInstruction() {
		return specialInstruction;
	}
	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}
	public String getPackingLabeling() {
		return packingLabeling;
	}
	public void setPackingLabeling(String packingLabeling) {
		this.packingLabeling = packingLabeling;
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
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public String getParticulars() {
		return particulars;
	}
	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}
	public boolean isSendLater() {
		return sendLater;
	}
	public void setSendLater(boolean sendLater) {
		this.sendLater = sendLater;
	}
	public String getBoPrefix() {
		return boPrefix;
	}
	public void setBoPrefix(String boPrefix) {
		this.boPrefix = boPrefix;
	}
	public int getBoNo() {
		return boNo;
	}
	public void setBoNo(int boNo) {
		this.boNo = boNo;
	}
	public String getBoPrefixNo() {
		return boPrefixNo;
	}
	public void setBoPrefixNo(String boPrefixNo) {
		this.boPrefixNo = boPrefixNo;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getSeasonName() {
		return seasonName;
	}
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getDeliveryTo() {
		return deliveryTo;
	}
	public void setDeliveryTo(String deliveryTo) {
		this.deliveryTo = deliveryTo;
	}
	public String getBuyerPoNo() {
		return buyerPoNo;
	}
	public void setBuyerPoNo(String buyerPoNo) {
		this.buyerPoNo = buyerPoNo;
	}
	public String getPiNo() {
		return piNo;
	}
	public void setPiNo(String piNo) {
		this.piNo = piNo;
	}
	public String getPiDate() {
		return piDate;
	}
	public void setPiDate(String piDate) {
		this.piDate = piDate;
	}
	public String getModeOfShip() {
		return modeOfShip;
	}
	public void setModeOfShip(String modeOfShip) {
		this.modeOfShip = modeOfShip;
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
	public List<BuyerOrderHeader> getAttachFiles() {
		return attachFiles;
	}
	public void setAttachFiles(List<BuyerOrderHeader> attachFiles) {
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
	public List<BuyerOrderHeader> getBoUsers() {
		return boUsers;
	}
	public void setBoUsers(List<BuyerOrderHeader> boUsers) {
		this.boUsers = boUsers;
	}
	public String getContactImage() {
		return contactImage;
	}
	public void setContactImage(String contactImage) {
		this.contactImage = contactImage;
	}
	public List<BuyerOrderHeader> getDefUsers() {
		return defUsers;
	}
	public void setDefUsers(List<BuyerOrderHeader> defUsers) {
		this.defUsers = defUsers;
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
	public List<BuyerOrderHeader> getTcList() {
		return tcList;
	}
	public void setTcList(List<BuyerOrderHeader> tcList) {
		this.tcList = tcList;
	}
	public String getParticular() {
		return particular;
	}
	public void setParticular(String particular) {
		this.particular = particular;
	}
	public int getDetailVisFieldCnt() {
		return detailVisFieldCnt;
	}
	public void setDetailVisFieldCnt(int detailVisFieldCnt) {
		this.detailVisFieldCnt = detailVisFieldCnt;
	}
	public int getPackingRowCount() {
		return packingRowCount;
	}
	public void setPackingRowCount(int packingRowCount) {
		this.packingRowCount = packingRowCount;
	}
	public String getPackingRowIds() {
		return packingRowIds;
	}
	public void setPackingRowIds(String packingRowIds) {
		this.packingRowIds = packingRowIds;
	}
	public List<String> getPackingRowIdList() {
		return packingRowIdList;
	}
	public void setPackingRowIdList(List<String> packingRowIdList) {
		this.packingRowIdList = packingRowIdList;
	}
	public String getPackingScheduleData() {
		return packingScheduleData;
	}
	public void setPackingScheduleData(String packingScheduleData) {
		this.packingScheduleData = packingScheduleData;
	}
 
	
}

