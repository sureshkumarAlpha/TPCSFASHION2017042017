package com.alpha.tpcsfashion.beans;

import java.util.List;
import java.util.Map;

public class SalesQuotation {
	private		String 	urlType;
	private		String 	url;
	private		String 	fileName;
	private		String 	trDate;
	private		int 	quoteId;
	private		int 	quoteNo;
	private		String  enquiryNo;
	private		String 	quoteDate;
	private		int 	versionNo;
	private		String 	lastVersionDate;
	private		String 	referenceNo;
	private		int 	partyId;
	private		String	partyName;
	private		int 	enqId;
	private		int 	invToPartyId;
	private		String	invToPartyName;
	private		int 	shipToPartyId;
	private		String	shipToPartyName;
	private		String 	trType;
	private		String 	trCategory;
	private		String 	hRemarks;
	private		int 	currencyId;
	private		String	currencyName;
	private		int 	executiveId;
	private		String	executiveName;
	private		float 	exchangeRate;
	private		int 	authorizedBy;
	private		String 	authorizedDate;
	private		int 	cancelTag;
	private		String 	canceledBy;
	private		int 	closedTag;
	private		String 	closedBy;
	private		String partyAddress1;
	private		String paymentTerms;
	private		int creditDays;
	private		String partyAddress2;
	private		String partyAddress3;
	private boolean isInserted;
	private String emailSentTo;
	private String billToAddress;
	private String shipToAddress;
	private boolean sendLater;
	private String internalMemo;
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

	private String prefix;
	private String prefixQuoteNo;
	private int lastNo;
	private int prefixCount;
	private float discountPercent;
	private List<String> autoNoPrefixList;
	private String checkAutoNoType;
	private String paymentConfig;
	private String priceConfig;
	
	
public int getPrefixCount() {
		return prefixCount;
	}
	public void setPrefixCount(int prefixCount) {
		this.prefixCount = prefixCount;
	}
public float getDiscountPercent() {
	return discountPercent;
}
public void setDiscountPercent(float discountPercent) {
	this.discountPercent = discountPercent;
}
public boolean isInserted() {
	return isInserted;
}
public void setInserted(boolean isInserted) {
	this.isInserted = isInserted;
}
 

public int getLastNo() {
	return lastNo;
}
public void setLastNo(int lastNo) {
	this.lastNo = lastNo;
}
private List<SalesQuotationDetail> salesQuotationDetList;
private Map<String,String> sqDetMap;

public Map<String, String> getSqDetMap() {
	return sqDetMap;
}
public void setSqDetMap(Map<String, String> sqDetMap) {
	this.sqDetMap = sqDetMap;
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
public String getTrDate() {
	return trDate;
}
public void setTrDate(String trDate) {
	this.trDate = trDate;
}
public int getQuoteId() {
	return quoteId;
}
public void setQuoteId(int quoteId) {
	this.quoteId = quoteId;
}
 
public String getQuoteDate() {
	return quoteDate;
}
public void setQuoteDate(String quoteDate) {
	this.quoteDate = quoteDate;
}
public int getVersionNo() {
	return versionNo;
}
public void setVersionNo(int versionNo) {
	this.versionNo = versionNo;
}
public String getLastVersionDate() {
	return lastVersionDate;
}
public void setLastVersionDate(String lastVersionDate) {
	this.lastVersionDate = lastVersionDate;
}
public String getReferenceNo() {
	return referenceNo;
}
public void setReferenceNo(String referenceNo) {
	this.referenceNo = referenceNo;
}
public int getPartyId() {
	return partyId;
}
public void setPartyId(int partyId) {
	this.partyId = partyId;
}
public int getEnqId() {
	return enqId;
}
public void setEnqId(int enqId) {
	this.enqId = enqId;
}
public int getShipToPartyId() {
	return shipToPartyId;
}
public void setShipToPartyId(int shipToPartyId) {
	this.shipToPartyId = shipToPartyId;
}

public String getTrType() {
	return trType;
}
public void setTrType(String trType) {
	this.trType = trType;
}
public String getTrCategory() {
	return trCategory;
}
public void setTrCategory(String trCategory) {
	this.trCategory = trCategory;
}

public String gethRemarks() {
	return hRemarks;
}
public void sethRemarks(String hRemarks) {
	this.hRemarks = hRemarks;
}
public int getCurrencyId() {
	return currencyId;
}
public void setCurrencyId(int currencyId) {
	this.currencyId = currencyId;
}
public int getExecutiveId() {
	return executiveId;
}
public void setExecutiveId(int executiveId) {
	this.executiveId = executiveId;
}
public float getExchangeRate() {
	return exchangeRate;
}
public void setExchangeRate(float exchangeRate) {
	this.exchangeRate = exchangeRate;
}
public int getAuthorizedBy() {
	return authorizedBy;
}
public void setAuthorizedBy(int authorizedBy) {
	this.authorizedBy = authorizedBy;
}
public String getAuthorizedDate() {
	return authorizedDate;
}
public void setAuthorizedDate(String authorizedDate) {
	this.authorizedDate = authorizedDate;
}
public int getCancelTag() {
	return cancelTag;
}
public void setCancelTag(int cancelTag) {
	this.cancelTag = cancelTag;
}
public int getClosedTag() {
	return closedTag;
}
public void setClosedTag(int closedTag) {
	this.closedTag = closedTag;
}

public String getCanceledBy() {
	return canceledBy;
}
public void setCanceledBy(String canceledBy) {
	this.canceledBy = canceledBy;
}
public String getClosedBy() {
	return closedBy;
}
public void setClosedBy(String closedBy) {
	this.closedBy = closedBy;
}
public String getPartyName() {
	return partyName;
}
public void setPartyName(String partyName) {
	this.partyName = partyName;
}

public int getInvToPartyId() {
	return invToPartyId;
}
public void setInvToPartyId(int invToPartyId) {
	this.invToPartyId = invToPartyId;
}
public String getInvToPartyName() {
	return invToPartyName;
}
public void setInvToPartyName(String invToPartyName) {
	this.invToPartyName = invToPartyName;
}
public String getShipToPartyName() {
	return shipToPartyName;
}
public void setShipToPartyName(String shipToPartyName) {
	this.shipToPartyName = shipToPartyName;
}
public String getCurrencyName() {
	return currencyName;
}
public void setCurrencyName(String currencyName) {
	this.currencyName = currencyName;
}
public String getExecutiveName() {
	return executiveName;
}
public void setExecutiveName(String executiveName) {
	this.executiveName = executiveName;
}
public String getEnquiryNo() {
	return enquiryNo;
}
public void setEnquiryNo(String enquiryNo) {
	this.enquiryNo = enquiryNo;
}
public String getPartyAddress1() {
	return partyAddress1;
}
public void setPartyAddress1(String partyAddress1) {
	this.partyAddress1 = partyAddress1;
}
public String getPartyAddress2() {
	return partyAddress2;
}
public void setPartyAddress2(String partyAddress2) {
	this.partyAddress2 = partyAddress2;
}
public String getPartyAddress3() {
	return partyAddress3;
}
public void setPartyAddress3(String partyAddress3) {
	this.partyAddress3 = partyAddress3;
}
public List<SalesQuotationDetail> getSalesQuotationDetList() {
	return salesQuotationDetList;
}
public void setSalesQuotationDetList(
		List<SalesQuotationDetail> salesQuotationDetList) {
	this.salesQuotationDetList = salesQuotationDetList;
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
public String getPageFieldName() {
	return pageFieldName;
}
public void setPageFieldName(String pageFieldName) {
	this.pageFieldName = pageFieldName;
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
public int getQuoteNo() {
	return quoteNo;
}
public void setQuoteNo(int quoteNo) {
	this.quoteNo = quoteNo;
}
public String getPrefixQuoteNo() {
	return prefixQuoteNo;
}
public void setPrefixQuoteNo(String prefixQuoteNo) {
	this.prefixQuoteNo = prefixQuoteNo;
}
public String getPrefix() {
	return prefix;
}
public void setPrefix(String prefix) {
	this.prefix = prefix;
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
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
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


}