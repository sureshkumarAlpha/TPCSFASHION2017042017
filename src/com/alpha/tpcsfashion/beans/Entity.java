package com.alpha.tpcsfashion.beans;

public class Entity
{

  

  public int getEntityCustomerId()
  {
    return EntityCustomerId;
  }
  public void setEntityCustomerId(int entityCustomerId)
  {
	  EntityCustomerId = entityCustomerId;
  }
  
  
  public int getEntityFactoryId()
  {
    return EntityFactoryId;
  }
  public void setEntityFactoryId(int entityFactoryId)
  {
	  EntityFactoryId = entityFactoryId;
  }
  
  
  public int getEntityHOId()
  {
    return EntityHOId;
  }
  public void setEntityHOId(int entityHOId)
  {
	  EntityHOId = entityHOId;
  }
  
  
  public boolean isSuccess()
  {
    return Success;
  }
  public void setSuccess(boolean success)
  {
    Success = success;
  }
  
  public void setFactoryName(String factoryName)
  {
	  FactoryName = factoryName;
  }
  public String getFactoryName()
  {
   return FactoryName;
  }
  
  public void setCustomerName(String customerName)
  {
	  CustomerName = customerName;
  }
  public String getCustomerName()
  {
   return CustomerName;
  }
  
  private int EntityCustomerId;
  private int EntityFactoryId;
  private int EntityHOId;
  
  private String FactoryName;
  private String CustomerName;
  
  
  private boolean Success;

}
