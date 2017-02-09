package com.alpha.tpcsfashion.beans;

import java.util.List;

public class SalesInvoiceHeader {

	private int companyId;
	private int yearId;
	private int locationId;
	private int invId;
	private int invNo;
	private String prefixInvNo;
	private String invDate;
	private int versionNo;
	private String lastVersionDate;
	private String customerOrderNo;
	private int partyId;
	private String partyName;
	private int invoiceToPartyId;
	private String invoiceToParty;
	private int shipToPartyId;
	private String shipToParty;
	private String trType;	
	private String trCategory;
	private int quoteId;
	private String quoteNo;
	private int blanketOrderId;
	private String hRemarks;	
	private int currencyId;
	private String currency;
	private float exRate;
	private int executiveId;
	private String executive;
	private int entryUserId;
	private String entrydate;
	private int updatedUserId;
	private String updatedDate;
	private String hostName;
	private String ipAddress;
	private int authorizedBy;
	private String authorizedDate;	
	private int cancelTag;
	private int canceledBy;
	private int closedTag;
	private int closedBy;
	private String soNo;
	private int soId;
	private String emailSentTo;
	private String billToAddress;
	private String shipToAddress;
	private boolean sendLater;
	private String internalMemo;
	private String  prefixInvoiceNo;
	private String Name;
	private int Id;
	private String pageFieldName;
	private float chargesFCY1;
	private float chargesFCY2;
	private float chargesFCY3;
	private String chargeName1;
	private String chargeName2;
	private String chargeName3;
	
	private int chargeId1;
	private int chargeId2;
	private int chargeId3;
	
	private int chargeCCId1;
	private String chargeCCName1;
	private int chargeCCId2;
	private String chargeCCName2;
	private int chargeCCId3;
	private String chargeCCName3;
	
	private boolean isInserted;
	private int lastNo;
	private float netFCY;
	private String discountPercent;
	private String prefix;
	private int startingNo;
	private List<String> autoNoPrefixList;
	private String checkAutoNoType;
	private int invIdRtrnd;
	private String invNoRtrnd;
	private String pagePrefixId;
	private String pageAutoNoId;
	private String headerNoColumn;
	private String headerIdColumn;
	private String headerTableName;
	private String autoNoTableName;
	private String headerTypeColumn;
	private String headerTypeValue;
	private int salesAccountId;
	
	private String costCenterDetails; 
	private String costCenterValues;
	
	private List<SalesInvoiceHeader> costCtrRowList;
	private String costCtrRowIds;
	private List<String> costCtrRowIdList;
	private int costCtrRowCnt;
	
	private String costCenterAvailable;
	private int draftTag;
	private String paymentTerms;
	private int creditDays;
	private String paymentConfig;
	private String priceConfig;
	private boolean isAccountConfigured; 
	private int accConfigPermission;
	private boolean isSaveConvertedToDraft;
	
	public String getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	public int getCreditDays() {
		return creditDays;
	}
	public void setCreditDays(int creditDays) {
		this.creditDays = creditDays;
	}
	public String getPaymentConfig() {
		return paymentConfig;
	}
	public void setPaymentConfig(String paymentConfig) {
		this.paymentConfig = paymentConfig;
	}
	public String getPriceConfig() {
		return priceConfig;
	}
	public void setPriceConfig(String priceConfig) {
		this.priceConfig = priceConfig;
	}
	public int getInvIdRtrnd() {
		return invIdRtrnd;
	}
	public void setInvIdRtrnd(int invIdRtrnd) {
		this.invIdRtrnd = invIdRtrnd;
	}
	public String getInvNoRtrnd() {
		return invNoRtrnd;
	}
	public void setInvNoRtrnd(String invNoRtrnd) {
		this.invNoRtrnd = invNoRtrnd;
	}
	public String getPrefixInvoiceNo() {
		return prefixInvoiceNo;
	}
	public void setPrefixInvoiceNo(String prefixInvoiceNo) {
		this.prefixInvoiceNo = prefixInvoiceNo;
	}

	public boolean isInserted() {
		return isInserted;
	}
	public void setInserted(boolean isInserted) {
		this.isInserted = isInserted;
	}
	 
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public int getStartingNo() {
		return startingNo;
	}
	public void setStartingNo(int startingNo) {
		this.startingNo = startingNo;
	}
	public int getLastNo() {
		return lastNo;
	}
	public void setLastNo(int lastNo) {
		this.lastNo = lastNo;
	}
	public int getInvId() {
		return invId;
	}
	public void setInvId(int invId) {
		this.invId = invId;
	}
	
	public String getInvDate() {
		return invDate;
	}
	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPageFieldName() {
		return pageFieldName;
	}
	public void setPageFieldName(String pageFieldName) {
		this.pageFieldName = pageFieldName;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setClosedBy(int closedBy) {
		this.closedBy = closedBy;
	}
	public int getClosedBy() {
		return closedBy;
	}
	public void setClosedTag(int closedTag) {
		this.closedTag = closedTag;
	}
	public int getClosedTag() {
		return closedTag;
	}
	public void setCanceledBy(int canceledBy) {
		this.canceledBy = canceledBy;
	}
	public int getCanceledBy() {
		return canceledBy;
	}
	public void setCancelTag(int cancelTag) {
		this.cancelTag = cancelTag;
	}
	public int getCancelTag() {
		return cancelTag;
	}
	public void setAuthorizedDate(String authorizedDate) {
		this.authorizedDate = authorizedDate;
	}
	public String getAuthorizedDate() {
		return authorizedDate;
	}
	public void setAuthorizedBy(int authorizedBy) {
		this.authorizedBy = authorizedBy;
	}
	public int getAuthorizedBy() {
		return authorizedBy;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getHostName() {
		return hostName;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedUserId(int updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
	public int getUpdatedUserId() {
		return updatedUserId;
	}
	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}
	public String getEntrydate() {
		return entrydate;
	}
	public void setEntryUserId(int entryUserId) {
		this.entryUserId = entryUserId;
	}
	public int getEntryUserId() {
		return entryUserId;
	}
	public void setExecutiveId(int executiveId) {
		this.executiveId = executiveId;
	}
	public int getExecutiveId() {
		return executiveId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}
	public int getCurrencyId() {
		return currencyId;
	}

	public String gethRemarks() {
		return hRemarks;
	}
	public void sethRemarks(String hRemarks) {
		this.hRemarks = hRemarks;
	}
	public void setBlanketOrderId(int blanketOrderId) {
		this.blanketOrderId = blanketOrderId;
	}
	public int getBlanketOrderId() {
		return blanketOrderId;
	}
	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}
	public int getQuoteId() {
		return quoteId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public int getYearId() {
		return yearId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getLocationId() {
		return locationId;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
	public int getVersionNo() {
		return versionNo;
	}
	public void setLastVersionDate(String lastVersionDate) {
		this.lastVersionDate = lastVersionDate;
	}
	public String getLastVersionDate() {
		return lastVersionDate;
	}

	public void setInvoiceToPartyId(int invoiceToPartyId) {
		this.invoiceToPartyId = invoiceToPartyId;
	}
	public int getInvoiceToPartyId() {
		return invoiceToPartyId;
	}
	public void setShipToPartyId(int shipToPartyId) {
		this.shipToPartyId = shipToPartyId;
	}
	public int getShipToPartyId() {
		return shipToPartyId;
	}
	public void setTrType(String trType) {
		this.trType = trType;
	}
	public String getTrType() {
		return trType;
	}
	public void setTrCategory(String trCategory) {
		this.trCategory = trCategory;
	}
	public String getTrCategory() {
		return trCategory;
	}
	public void setCustomerOrderNo(String customerOrderNo) {
		this.customerOrderNo = customerOrderNo;
	}
	public String getCustomerOrderNo() {
		return customerOrderNo;
	}
	public void setExRate(float exRate) {
		this.exRate = exRate;
	}
	public float getExRate() {
		return exRate;
	}
	public void setExecutive(String executive) {
		this.executive = executive;
	}
	public String getExecutive() {
		return executive;
	}
	public void setInvoiceToParty(String invoiceToParty) {
		this.invoiceToParty = invoiceToParty;
	}
	public String getInvoiceToParty() {
		return invoiceToParty;
	}
	public void setShipToParty(String shipToParty) {
		this.shipToParty = shipToParty;
	}
	public String getShipToParty() {
		return shipToParty;
	}
	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}
	public String getQuoteNo() {
		return quoteNo;
	}
	public void setPartyId(int partyId) {
		this.partyId = partyId;
	}
	public int getPartyId() {
		return partyId;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getPartyName() {
		return partyName;
	}
	public String getSoNo() {
		return soNo;
	}
	public void setSoNo(String soNo) {
		this.soNo = soNo;
	}
	public int getSoId() {
		return soId;
	}
	public void setSoId(int soId) {
		this.soId = soId;
	}
	public String getEmailSentTo() {
		return emailSentTo;
	}
	public void setEmailSentTo(String emailSentTo) {
		this.emailSentTo = emailSentTo;
	}
	public String getBillToAddress() {
		return billToAddress;
	}
	public void setBillToAddress(String billToAddress) {
		this.billToAddress = billToAddress;
	}
	public String getShipToAddress() {
		return shipToAddress;
	}
	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}
	public boolean isSendLater() {
		return sendLater;
	}
	public void setSendLater(boolean sendLater) {
		this.sendLater = sendLater;
	}

	public String getInternalMemo() {
		return internalMemo;
	}
	public void setInternalMemo(String internalMemo) {
		this.internalMemo = internalMemo;
	}
	public float getChargesFCY1() {
		return chargesFCY1;
	}
	public void setChargesFCY1(float chargesFCY1) {
		this.chargesFCY1 = chargesFCY1;
	}
	public float getChargesFCY2() {
		return chargesFCY2;
	}
	public void setChargesFCY2(float chargesFCY2) {
		this.chargesFCY2 = chargesFCY2;
	}
	public float getChargesFCY3() {
		return chargesFCY3;
	}
	public void setChargesFCY3(float chargesFCY3) {
		this.chargesFCY3 = chargesFCY3;
	}
	public String getChargeName1() {
		return chargeName1;
	}
	public void setChargeName1(String chargeName1) {
		this.chargeName1 = chargeName1;
	}
	public String getChargeName2() {
		return chargeName2;
	}
	public void setChargeName2(String chargeName2) {
		this.chargeName2 = chargeName2;
	}
	public String getChargeName3() {
		return chargeName3;
	}
	public void setChargeName3(String chargeName3) {
		this.chargeName3 = chargeName3;
	}
	public float getNetFCY() {
		return netFCY;
	}
	public void setNetFCY(float netFCY) {
		this.netFCY = netFCY;
	}

 
	public List<String> getAutoNoPrefixList() {
		return autoNoPrefixList;
	}
	public void setAutoNoPrefixList(List<String> autoNoPrefixList) {
		this.autoNoPrefixList = autoNoPrefixList;
	}
	public int getInvNo() {
		return invNo;
	}
	public void setInvNo(int invNo) {
		this.invNo = invNo;
	}
	public String getPrefixInvNo() {
		return prefixInvNo;
	}
	public void setPrefixInvNo(String prefixInvNo) {
		this.prefixInvNo = prefixInvNo;
	}
	public String getPagePrefixId() {
		return pagePrefixId;
	}
	public void setPagePrefixId(String pagePrefixId) {
		this.pagePrefixId = pagePrefixId;
	}
	public String getPageAutoNoId() {
		return pageAutoNoId;
	}
	public void setPageAutoNoId(String pageAutoNoId) {
		this.pageAutoNoId = pageAutoNoId;
	}
	public String getHeaderNoColumn() {
		return headerNoColumn;
	}
	public void setHeaderNoColumn(String headerNoColumn) {
		this.headerNoColumn = headerNoColumn;
	}
	public String getHeaderIdColumn() {
		return headerIdColumn;
	}
	public void setHeaderIdColumn(String headerIdColumn) {
		this.headerIdColumn = headerIdColumn;
	}
	public String getHeaderTableName() {
		return headerTableName;
	}
	public void setHeaderTableName(String headerTableName) {
		this.headerTableName = headerTableName;
	}
	public String getAutoNoTableName() {
		return autoNoTableName;
	}
	public void setAutoNoTableName(String autoNoTableName) {
		this.autoNoTableName = autoNoTableName;
	}
	public String getCheckAutoNoType() {
		return checkAutoNoType;
	}
	public void setCheckAutoNoType(String checkAutoNoType) {
		this.checkAutoNoType = checkAutoNoType;
	}
	public String getHeaderTypeColumn() {
		return headerTypeColumn;
	}
	public void setHeaderTypeColumn(String headerTypeColumn) {
		this.headerTypeColumn = headerTypeColumn;
	}
	public String getHeaderTypeValue() {
		return headerTypeValue;
	}
	public void setHeaderTypeValue(String headerTypeValue) {
		this.headerTypeValue = headerTypeValue;
	}
	public int getChargeId1() {
		return chargeId1;
	}
	public void setChargeId1(int chargeId1) {
		this.chargeId1 = chargeId1;
	}
	public int getChargeId2() {
		return chargeId2;
	}
	public void setChargeId2(int chargeId2) {
		this.chargeId2 = chargeId2;
	}
	public int getChargeId3() {
		return chargeId3;
	}
	public void setChargeId3(int chargeId3) {
		this.chargeId3 = chargeId3;
	}
	public int getSalesAccountId() {
		return salesAccountId;
	}
	public void setSalesAccountId(int salesAccountId) {
		this.salesAccountId = salesAccountId;
	}
	public String getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}
	public List<SalesInvoiceHeader> getCostCtrRowList() {
		return costCtrRowList;
	}
	public void setCostCtrRowList(List<SalesInvoiceHeader> costCtrRowList) {
		this.costCtrRowList = costCtrRowList;
	}
	public String getCostCtrRowIds() {
		return costCtrRowIds;
	}
	public void setCostCtrRowIds(String costCtrRowIds) {
		this.costCtrRowIds = costCtrRowIds;
	}
	public List<String> getCostCtrRowIdList() {
		return costCtrRowIdList;
	}
	public void setCostCtrRowIdList(List<String> costCtrRowIdList) {
		this.costCtrRowIdList = costCtrRowIdList;
	}
	public int getCostCtrRowCnt() {
		return costCtrRowCnt;
	}
	public void setCostCtrRowCnt(int costCtrRowCnt) {
		this.costCtrRowCnt = costCtrRowCnt;
	}
	public String getCostCenterDetails() {
		return costCenterDetails;
	}
	public void setCostCenterDetails(String costCenterDetails) {
		this.costCenterDetails = costCenterDetails;
	}
	public String getCostCenterAvailable() {
		return costCenterAvailable;
	}
	public void setCostCenterAvailable(String costCenterAvailable) {
		this.costCenterAvailable = costCenterAvailable;
	}
	public String getCostCenterValues() {
		return costCenterValues;
	}
	public void setCostCenterValues(String costCenterValues) {
		this.costCenterValues = costCenterValues;
	}
	public int getChargeCCId1() {
		return chargeCCId1;
	}
	public void setChargeCCId1(int chargeCCId1) {
		this.chargeCCId1 = chargeCCId1;
	}
	public int getChargeCCId2() {
		return chargeCCId2;
	}
	public void setChargeCCId2(int chargeCCId2) {
		this.chargeCCId2 = chargeCCId2;
	}
	public int getChargeCCId3() {
		return chargeCCId3;
	}
	public void setChargeCCId3(int chargeCCId3) {
		this.chargeCCId3 = chargeCCId3;
	}
	public int getDraftTag() {
		return draftTag;
	}
	public void setDraftTag(int draftTag) {
		this.draftTag = draftTag;
	}
	public String getChargeCCName1() {
		return chargeCCName1;
	}
	public void setChargeCCName1(String chargeCCName1) {
		this.chargeCCName1 = chargeCCName1;
	}
	public String getChargeCCName2() {
		return chargeCCName2;
	}
	public void setChargeCCName2(String chargeCCName2) {
		this.chargeCCName2 = chargeCCName2;
	}
	public String getChargeCCName3() {
		return chargeCCName3;
	}
	public void setChargeCCName3(String chargeCCName3) {
		this.chargeCCName3 = chargeCCName3;
	}
	public boolean isAccountConfigured() {
		return isAccountConfigured;
	}
	public void setAccountConfigured(boolean isAccountConfigured) {
		this.isAccountConfigured = isAccountConfigured;
	}
	public int getAccConfigPermission() {
		return accConfigPermission;
	}
	public void setAccConfigPermission(int accConfigPermission) {
		this.accConfigPermission = accConfigPermission;
	}
	public boolean isSaveConvertedToDraft() {
		return isSaveConvertedToDraft;
	}
	public void setSaveConvertedToDraft(boolean isSaveConvertedToDraft) {
		this.isSaveConvertedToDraft = isSaveConvertedToDraft;
	}


}
