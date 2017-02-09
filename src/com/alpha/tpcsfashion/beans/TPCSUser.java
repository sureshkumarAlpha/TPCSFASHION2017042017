package com.alpha.tpcsfashion.beans;

import java.util.Map;
import java.util.ResourceBundle;

public class TPCSUser {

	private int UserId;
	private String firstName;
	private String lastName;
	private String displayUserName;
	private boolean isUserValid;
	private String userName;
	private String password;
	private String userLocale;
	private String datasource;
	private String database;
	private String serverip;
	private String sqlusername;
	private String sqlpassword;
	private int clientId;
	private boolean isPasswordExpired;
	private String clientName;
	private String companyName;
	private String companyCode;
	private String shortName;
	private String address1;
	private String address2;
	private String address3;
	private String phoneNo;
	private String country;
	private String faxNo;
	private String taxRegNo1;
	private String taxRegNo2;
	private String emailId;
	private String street;
	private String location;
	private String city;
	private String state;
	private String zip;
	private String PANNo;
	private String companyAddress;
	private int companyId;
	private int locationId;
	private int yearId;
	private int currencyId;
	private String currencyName;
	private String currencySymbol;
	private int timeZone;
	private String question;
	private String answer;
	private String propertyFileName;
	private ResourceBundle resourceBundle;
	private String ieCode;
	private String bankDetails;
	private String startDate;
	private String endDate;
	private String booksBegingDate;
	private Map<String,String> locatonConfigMap;
	private String logoPath;
	private String webLogoPath;
	private String logoFilePath;
	//  private String Question;
	//  private String Answer;
	private int alternateCurrencyId;
	private String alternateCurrencyName;

	
	
	
	public String getUserName()
	{
		return userName;
	}
	public String getBankDetails() {
		return bankDetails;
	}
	public void setBankDetails(String bankDetails) {
		this.bankDetails = bankDetails;
	}
	public String getIeCode() {
		return ieCode;
	}
	public void setIeCode(String ieCode) {
		this.ieCode = ieCode;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setUserValid(boolean bFlag){
		this.isUserValid = bFlag;
	}

	public boolean isUserValid(){
		return isUserValid;
	}


	public String getDisplayUserName()
	{
		return displayUserName;
	}
	public void setDisplayUserName(String displayUserName)
	{
		this.displayUserName = displayUserName;
	}

	public int getUserId()
	{
		return UserId;
	}
	public void setUserId(int userId)
	{
		UserId = userId;
	}
	public String getUserLocale()
	{
		return userLocale;
	}
	public void setUserLocale(String userLocale)
	{
		this.userLocale = userLocale;
	}
	public String getDatasource()
	{
		return datasource;
	}
	public void setDatasource(String datasource)
	{
		this.datasource = datasource;
	}
	public String getDatabase()
	{
		return database;
	}
	public void setDatabase(String database)
	{
		this.database = database;
	}
	public String getServerip()
	{
		return serverip;
	}
	public void setServerip(String serverip)
	{
		this.serverip = serverip;
	}
	public String getSqlusername()
	{
		return sqlusername;
	}
	public void setSqlusername(String sqlusername)
	{
		this.sqlusername = sqlusername;
	}
	public String getSqlpassword()
	{
		return sqlpassword;
	}
	public void setSqlpassword(String sqlpassword)
	{
		this.sqlpassword = sqlpassword;
	}
	public int getclientId()
	{
		return clientId;
	}
	public void setclientId(int clientId)
	{
		this.clientId = clientId;
	}

	public void setPasswordExpired(boolean bFlag){
		this.isPasswordExpired = bFlag;
	}

	public boolean isPasswordExpired(){
		return isPasswordExpired;
	}

	public String getCompanyName()
	{
		return companyName;
	}
	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}	 

	//	  public String getQuestion()
	//	  {
	//	    return Question;
	//	  }
	//	  public void setQuestion(String question)
	//	  {
	//	    this.Question = question;
	//	  }
	//	  
	//	  public String getAnswer()
	//	  {
	//	    return Answer;
	//	  }
	//	  public void setAnswer(String answer)
	//	  {
	//	    this.Answer = answer;
	//	  }

	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}



	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getCompanyId() {
		return companyId;
	}



	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getLocationId() {
		return locationId;
	}



	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public int getYearId() {
		return yearId;
	}


	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}
	public int getCurrencyId() {
		return currencyId;
	}

	public int getTimeZone()
	{
		return timeZone;
	}
	public void setTimeZone(int timeZone)
	{
		this.timeZone = timeZone;
	} 

	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQuestion() {
		return question;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswer() {
		return answer;
	}

	public void setPropertyFileName(String propertyFileName) {
		this.propertyFileName = propertyFileName;
	}
	public String getPropertyFileName() {
		return propertyFileName;
	}

	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}



	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public String getTaxRegNo1() {
		return taxRegNo1;
	}
	public void setTaxRegNo1(String taxRegNo1) {
		this.taxRegNo1 = taxRegNo1;
	}
	public String getTaxRegNo2() {
		return taxRegNo2;
	}
	public void setTaxRegNo2(String taxRegNo2) {
		this.taxRegNo2 = taxRegNo2;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	public Map<String, String> getLocatonConfigMap() {
		return locatonConfigMap;
	}
	public void setLocatonConfigMap(Map<String, String> locatonConfigMap) {
		this.locatonConfigMap = locatonConfigMap;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogoFilePath() {
		return logoFilePath;
	}
	public void setLogoFilePath(String logoFilePath) {
		this.logoFilePath = logoFilePath;
	}



	public String getBooksBegingDate() {
		return booksBegingDate;
	}
	public void setBooksBegingDate(String booksBegingDate) {
		this.booksBegingDate = booksBegingDate;
	}



	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPANNo() {
		return PANNo;
	}
	public void setPANNo(String pANNo) {
		PANNo = pANNo;
	}
	public String getWebLogoPath() {
		return webLogoPath;
	}
	public void setWebLogoPath(String webLogoPath) {
		this.webLogoPath = webLogoPath;
	}
	public int getAlternateCurrencyId() {
		return alternateCurrencyId;
	}
	public void setAlternateCurrencyId(int alternateCurrencyId) {
		this.alternateCurrencyId = alternateCurrencyId;
	}
	public String getAlternateCurrencyName() {
		return alternateCurrencyName;
	}
	public void setAlternateCurrencyName(String alternateCurrencyName) {
		this.alternateCurrencyName = alternateCurrencyName;
	}

	
}
