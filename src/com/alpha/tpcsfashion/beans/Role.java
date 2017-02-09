package com.alpha.tpcsfashion.beans;

public class Role
{
	
	private int RoleId;
	private String RoleName;
	private int RoleLevel;
	private int ReportToRoleId;
	private String ReportToRoleName;
	private boolean Success;
	private int CustomerId;
	private int FactoryId;
	private int EntityId;
	
	

	public void setRoleId(int roleId )
	{
		 RoleId = roleId;
	}
	
	public int getRoleId()
	{
		return RoleId;
	}
	
	public void setRoleName(String roleName)
	{
		 RoleName = roleName;
	}
	
	public String getRoleName()
	{
		return RoleName;
	}

	public void setRoleLevel( int roleLevel)
	{
		 RoleLevel = roleLevel;
	}
	
	public int getRoleLevel()
	{
		return RoleLevel;
	}
	
	public void setReportToRoleId( int reportToRoleId)
	{
		 ReportToRoleId =reportToRoleId;
	}
	
	public int getReportToRoleId()
	{
		return ReportToRoleId;
	}
	
	public void setReportToRoleName( String reportToRoleName)
	{
		 ReportToRoleName = reportToRoleName;
	}
	
	public String getReportToRoleName()
	{
		return ReportToRoleName;
	}
	public boolean isSuccess()
	  {
	    return Success;
	  }
	  public void setSuccess(boolean success)
	  {
	    Success = success;
	  }
	  
	  public int getFactoryId()
	  {
	    return FactoryId;
	  }
	  public void setFactoryId(int factoryId)
	  {
	    FactoryId = factoryId;
	  }
	  
	  public int getCustomerId()
	  {
	    return CustomerId;
	  }
	  public void setCustomerId(int customerId)
	  {
	    CustomerId = customerId;
	  }
	  public int getEntityId()
	  {
	    return EntityId;
	  }
	  public void setEntityId(int entityId)
	  {
		  EntityId = entityId;
	  }
	  
	  
	  
}