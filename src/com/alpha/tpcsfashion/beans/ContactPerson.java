package com.alpha.tpcsfashion.beans;

public class ContactPerson
{

  public String getContactPersonName()
  {
    return ContactPersonName;
  }
  public void setContactPersonName(String contactPersonName)
  {
    ContactPersonName = contactPersonName;
  }
  public String getDesignation()
  {
    return Designation;
  }
  public void setDesignation(String designation)
  {
    Designation = designation;
  }


  public String getPhone()
  {
    return Phone;
  }
  public void setPhone(String phone)
  {
    Phone = phone;
  }


  
  private String ContactPersonName;
  private String Designation;
  private String Phone;

  
  
}
