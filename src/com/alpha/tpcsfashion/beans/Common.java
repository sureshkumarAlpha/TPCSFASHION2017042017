package com.alpha.tpcsfashion.beans;

public class Common
{

  
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
  public String getServerIp()
  {
    return serverIp;
  }
  public void setServerIp(String serverIp)
  {
    this.serverIp = serverIp;
  }
  public String getUserName()
  {
    return userName;
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
  public int getCompanyId()
  {
    return companyId;
  }
  public void setCompanyId(int companyId)
  {
    this.companyId = companyId;
  }
  public int getLocationId()
  {
    return locationId;
  }
  public void setLocationId(int locationId)
  {
    this.locationId = locationId;
  }
  public int getYearId()
  {
    return yearId;
  }
  public void setYearId(int yearId)
  {
    this.yearId = yearId;
  }
  
  
  public int getTimeZone()
  {
    return timeZone;
  }
  public void setTimeZone(int timeZone)
  {
    this.timeZone = timeZone;
  }
  public void setUserId(int userId) {
	this.userId = userId;
}
public int getUserId() {
	return userId;
}
private String datasource;
  private String database;
  private String serverIp;
  private String userName;
  private String password;
  private int companyId;
  private int locationId;
  private int yearId;
  private int timeZone;
  private int userId;


}
