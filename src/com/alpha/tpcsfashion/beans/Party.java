package com.alpha.tpcsfashion.beans;

public class Party
{

  
  public String getPartyName()
  {
    return PartyName;
  }
  public void setPartyName(String partyName)
  {
	  PartyName = partyName;
  }
  public String getPartyCode()
  {
    return PartyCode;
  }
  public void setPartyCode(String partyCode)
  {
	  PartyCode = partyCode;
  }
  public Address getAddress1()
  {
    return Address1;
  }
  public void setAddress1(Address address1)
  {
    Address1 = address1;
  }
  
  public ContactPerson getPerson()
  {
    return Person;
  }
  public void setPerson(ContactPerson person)
  {
    Person = person;
  }
  
  public int getPartyId()
  {
    return PartyId;
  }
  public void setPartyId(int partyId)
  {
	  PartyId = partyId;
  }

  public String getShortName()
  {
    return ShortName;
  }
  public void setShortName(String shortName)
  {
	  ShortName = shortName;
  }
  public int getCurrencyId()
  {
    return CurrencyId;
  }
  public void setCurrencyId(int currencyId)
  {
	  CurrencyId = currencyId;
  }
  public String getPartyTag()
  {
    return PartyTag;
  }
  public void setPartyTag(String partyTag)
  {
	  PartyTag = partyTag;
  }
  
  public String getContactNo()
  {
    return ContactNo;
  }
  public void setContactNo(String contactNo)
  {
	  ContactNo = contactNo;
  }
  public Order getOrder()
  {
    return Order;
  }
  public void setOrder(Order order)
  {
	  Order = order;
  }
  
  public String getCurrencyname()
  {
    return Currencyname;
  }
  public void setCurrencyname(String currencyname)
  {
	  Currencyname = currencyname;
  }
  
  private int PartyId;
  private String PartyName;
  private String PartyCode;
  private Address Address1;
  private ContactPerson Person;
  private String ContactNo;
  private String ShortName;
  private String PartyTag;
  private int CurrencyId;
  private Order Order;
  private String Currencyname;
}
