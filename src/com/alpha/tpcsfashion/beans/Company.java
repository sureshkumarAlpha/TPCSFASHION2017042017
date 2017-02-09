package com.alpha.tpcsfashion.beans;

public class Company {

	public int getCompanyId(){
		return CompanyId;
	}
	public void setCompanyId(int companyId){
		CompanyId=companyId;
	}
	public int getPartyId(){
		return PartyId;
	}
	public void setPartyId(int partyId){
		PartyId=partyId;
	}
	public String getCompanyName()
	{
		return CompanyName;
	}
	public void setCompanyName(String companyName)
	{
		CompanyName = companyName;
	}
	public String getAddress1()
	{
		return Address1;
	}
	public void setAddress1(String address1)
	{
		Address1 = address1;
	}
	public String getAddress2()
	{
		return Address2;
	}
	public void setAddress2(String address2)
	{
		Address2 = address2;
	}
	public String getAddress3()
	{
		return Address3;
	}
	public void setAddress3(String address3)
	{
		Address3 = address3;
	}
	public String getCountry()
	{
		return Country;
	}
	public void setCountry(String country)
	{
		Country = country;
	}
	public String getPhoneNo()
	{
		return PhoneNo;
	}
	public void setPhoneNo(String phoneNo)
	{
		PhoneNo = phoneNo;
	}
	//newly added
	
	 public int getCurrencyId()
	  {
	    return CurrencyId;
	  }
	  public void setCurrencyId(int currencyId)
	  {
		  CurrencyId = currencyId;
	  }
	  public String getCurrencyname()
	  {
	    return Currencyname;
	  }
	  public void setCurrencyname(String currencyname)
	  {
		  Currencyname = currencyname;
	  }
	  public String getFaxNo()
	  {
	    return FaxNo;
	  }
	  public void setFaxNo(String faxNo)
	  {
		  FaxNo = faxNo;
	  }
	  public String getTaxRegNo1()
	  {
	    return TaxRegNo1;
	  }
	  public void setTaxRegNo1(String taxRegNo1)
	  {
		  TaxRegNo1 = taxRegNo1;
	  }
	  public String getTaxRegNo2()
	  {
	    return TaxRegNo2;
	  }
	  public void setTaxRegNo2(String taxRegNo2)
	  {
		  TaxRegNo2 = taxRegNo2;
	  }
	  public String getEmailId()
	  {
	    return EmailId;
	  }
	  public void setEmailId(String emailId)
	  {
		  EmailId = emailId;
	  }

	
	private int CompanyId;
	private int PartyId;
	private String CompanyName;
	private String Address1;
	private String Address2;
	private String Address3;
	private String Country;
	private String PhoneNo;
	  private int CurrencyId;
	  private String Currencyname;
	  private String FaxNo;
		private String TaxRegNo1;
		private String TaxRegNo2;
		private String EmailId;
	
}
