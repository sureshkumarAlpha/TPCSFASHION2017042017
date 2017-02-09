package com.alpha.tpcsfashion.beans;

public class Customer {

	
	private	int	partyId	;
	private	int	identityId	;
	private	String	partyCode	;
	private	String	partyName	;
	private	String	displayName	;
	private	String	partyTag	;
	private	String	contactSalutation	;
	private	String	contactFirstname	;
	private	String	contactLastname	;
	private	String	contactDesignation	;
	private	String	contactPhone	;
	private	String	contactEmail	;
	private	String	companyPhone	;
	private	String	companyFax	;
	private	String	companyEmail	;
	private	int	Status	;
	private	int	currencyId	;
	private	String	paymentTerms	;
	private	int	creditDays	;
	private	String	billStreet	;
	private	String	billLocation	;
	private	String	billCity	;
	private	String	billState	;
	private	String	billCountry	;
	private	String	billZip	;
	private	String	shipStreet	;
	private	String	shipLocation	;
	private	String	shipCity	;
	private	String	shipState	;
	private	String	shipCountry	;
	private	String	shipZip	;
	private	String	shipPhone	;
	private	String	panNO	;
	private	String	vatNo	;
	private	String	servtaxNo	;
	private	String	insuranceDetails	;
	private	int	accountGroupId	;
	private	String	accountGroup	;
	private	int	billTracking	;
	private	String	Notes	;
	private	String	mode	;
	private boolean inserted;
	private	String	groupType	;
	private	String	currency	;
	private boolean isCustExists;
	private boolean deleted;
	
	
	
	public int getIdentityId() {
		return identityId;
	}
	public void setIdentityId(int identityId) {
		this.identityId = identityId;
	}
	public boolean isCustExists() {
		return isCustExists;
	}
	public void setCustExists(boolean isCustExists) {
		this.isCustExists = isCustExists;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	public boolean isInserted() {
		return inserted;
	}
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}
	public String getAccountGroup() {
		return accountGroup;
	}
	public void setAccountGroup(String accountGroup) {
		this.accountGroup = accountGroup;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getPartyId() {
		return partyId;
	}
	public void setPartyId(int partyId) {
		this.partyId = partyId;
	}
	public String getPartyCode() {
		return partyCode;
	}
	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getPartyTag() {
		return partyTag;
	}
	public void setPartyTag(String partyTag) {
		this.partyTag = partyTag;
	}
	public String getContactSalutation() {
		return contactSalutation;
	}
	public void setContactSalutation(String contactSalutation) {
		this.contactSalutation = contactSalutation;
	}
	public String getContactFirstname() {
		return contactFirstname;
	}
	public void setContactFirstname(String contactFirstname) {
		this.contactFirstname = contactFirstname;
	}
	public String getContactLastname() {
		return contactLastname;
	}
	public void setContactLastname(String contactLastname) {
		this.contactLastname = contactLastname;
	}
	public String getContactDesignation() {
		return contactDesignation;
	}
	public void setContactDesignation(String contactDesignation) {
		this.contactDesignation = contactDesignation;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getCompanyFax() {
		return companyFax;
	}
	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}
	public String getCompanyEmail() {
		return companyEmail;
	}
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public int getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}
	public int getCreditDays() {
		return creditDays;
	}
	public void setCreditDays(int creditDays) {
		this.creditDays = creditDays;
	}
	public String getBillStreet() {
		return billStreet;
	}
	public void setBillStreet(String billStreet) {
		this.billStreet = billStreet;
	}
	public String getBillLocation() {
		return billLocation;
	}
	public void setBillLocation(String billLocation) {
		this.billLocation = billLocation;
	}
	public String getBillCity() {
		return billCity;
	}
	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}
	public String getBillState() {
		return billState;
	}
	public void setBillState(String billState) {
		this.billState = billState;
	}
	public String getBillCountry() {
		return billCountry;
	}
	public void setBillCountry(String billCountry) {
		this.billCountry = billCountry;
	}
	public String getBillZip() {
		return billZip;
	}
	public void setBillZip(String billZip) {
		this.billZip = billZip;
	}
	public String getShipStreet() {
		return shipStreet;
	}
	public void setShipStreet(String shipStreet) {
		this.shipStreet = shipStreet;
	}
	public String getShipLocation() {
		return shipLocation;
	}
	public void setShipLocation(String shipLocation) {
		this.shipLocation = shipLocation;
	}
	public String getShipCity() {
		return shipCity;
	}
	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}
	public String getShipState() {
		return shipState;
	}
	public void setShipState(String shipState) {
		this.shipState = shipState;
	}
	public String getShipCountry() {
		return shipCountry;
	}
	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}
	public String getShipZip() {
		return shipZip;
	}
	public void setShipZip(String shipZip) {
		this.shipZip = shipZip;
	}
	public String getShipPhone() {
		return shipPhone;
	}
	public void setShipPhone(String shipPhone) {
		this.shipPhone = shipPhone;
	}
	public String getPanNO() {
		return panNO;
	}
	public void setPanNO(String panNO) {
		this.panNO = panNO;
	}
	public String getVatNo() {
		return vatNo;
	}
	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
	}
	public String getServtaxNo() {
		return servtaxNo;
	}
	public void setServtaxNo(String servtaxNo) {
		this.servtaxNo = servtaxNo;
	}
	public String getInsuranceDetails() {
		return insuranceDetails;
	}
	public void setInsuranceDetails(String insuranceDetails) {
		this.insuranceDetails = insuranceDetails;
	}
	public int getAccountGroupId() {
		return accountGroupId;
	}
	public void setAccountGroupId(int accountGroupId) {
		this.accountGroupId = accountGroupId;
	}
	public int getBillTracking() {
		return billTracking;
	}
	public void setBillTracking(int billTracking) {
		this.billTracking = billTracking;
	}
	public String getNotes() {
		return Notes;
	}
	public void setNotes(String notes) {
		Notes = notes;
	}

	
	
}
