package com.alpha.tpcsfashion.beans;

public class User
{
	
	private int UserId;
	private String UserName;
	private String Password;	
	private String FirstName;
	private String LastName;
	private boolean Success;
	private int CustomerId;
	private int FactoryId;
	private int EntityId;
	private Role Role;
	private Profiles Profile;
	private String ProfileStatus;
	private String ClientUserName;
	private int LanguageId;
	private String Language;
	private int IsSystemAdmin;
	private int isActive;
private String entityName;
	
	
public int getIsSystemAdmin() {
	return IsSystemAdmin;
}

public void setIsSystemAdmin(int IsSystemAdmin) {
	this.IsSystemAdmin= IsSystemAdmin;
}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	
	
	public void setUserId(int userId )
	{
		UserId = userId;
	}
	
	public int getUserId()
	{
		return UserId;
	}
	
	public void setUserName(String userName)
	{
		UserName = userName;
	}
	
	public String getUserName()
	{
		return UserName;
	}	
	
	public void setClientUserName(String clientuserName)
	{
		ClientUserName = clientuserName;
	}
	
	public String getClientUserName()
	{
		return ClientUserName;
	}	
	
	public void setFirstName(String firstName)
	{
		FirstName = firstName;
	}
	
	public String getPassword()
	{
		return Password;
	}	
	
	public void setPassword(String password)
	{
		Password = password;
	}
	
	public String getFirstName()
	{
		return FirstName;
	}	
	
	public void setLastName(String lastName)
	{
		LastName = lastName;
	}
	
	public String getLastName()
	{
		return LastName;
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
	  
	  public Role getRoles()
	  {
	    return Role;
	  }
	  public void setRoles(Role roles)
	  {
		  Role = roles;
	  }
	  
	  public Profiles getProfiles()
	  {
	    return Profile;
	  }
	  public void setProfiles(Profiles profiles)
	  {
		  Profile = profiles;
	  }
	  
	  public void setProfileStatus(String profileStatus )
		{
		  ProfileStatus = profileStatus;
		}
		
		public String getProfileStatus()
		{
			return ProfileStatus;
		}
		public int getLanguageId()
		  {
		    return LanguageId;
		  }
		  public void setLanguageId(int languageId)
		  {
			  LanguageId = languageId;
		  }
		  
		  public String getLanguage()
		  {
			  return Language;
		  }
		  
		  public void setLanguage(String language )
			{
			  Language = language;
			}

		public int getIsActive() {
			return isActive;
		}

		public void setIsActive(int isActive) {
			this.isActive = isActive;
		}
					
		  
		  
}