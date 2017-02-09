package com.alpha.tpcsfashion.beans;

public class Warehouse {

	  public int getStoreId()
	  {
	    return storeId;
	  }
	  public void setStoreId(int storeId)
	  {
	    this.storeId = storeId;
	  }
	  public String getStoreCode()
	  {
	    return storeCode;
	  }
	  public void setStoreCode(String storeCode)
	  {
	    this.storeCode = storeCode;
	  }
	  public String getStoreName()
	  {
	    return storeName;
	  }
	  public void setStoreName(String storeName)
	  {
	    this.storeName = storeName;
	  }
	 

	public int getStoreStatus() {
		return storeStatus;
	}
	public void setStoreStatus(int storeStatus) {
		this.storeStatus = storeStatus;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}


	private int storeId;
	  private String storeCode;
	  private String storeName;
	  private int storeStatus;
	  private String mode;
	  private int userId;
	  private boolean warehouseExists;
	  private boolean deleted;
	public boolean isWarehouseExists() {
		return warehouseExists;
	}
	public void setWarehouseExists(boolean warehouseExists) {
		this.warehouseExists = warehouseExists;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
}
