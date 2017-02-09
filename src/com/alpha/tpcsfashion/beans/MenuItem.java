package com.alpha.tpcsfashion.beans;

import java.io.Serializable;

public class MenuItem implements Serializable
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  public MenuItem(int menuId,String menuName,String url,int parentMenuId,String desc,int parentId){
      this.menuId = menuId;
      this.menuName = menuName;
      this.menuURL = url;
//      this.parentMenuId = parentMenuId;
      this.menuDocId= parentMenuId;
      this.desc=desc;
      this.parentId=parentId;
    }
  
  public MenuItem() {
	// TODO Auto-generated constructor stub
}

public int getMenuId()
  {
    return menuId;
  }
  public void setMenuId(int menuId)
  {
	  this.menuId = menuId;
  }
  public String getMenuName()
  {
    return menuName;
  }
  public void setMenuName(String menuName)
  {
	  this.menuName = menuName;
  }
  public String getMenuURL()
  {
    return menuURL;
  }
  public void setMenuURL(String menuURL)
  {
	  this.menuURL = menuURL;
  }
  public void setMenuDocId(int menuDocId){
	  this.menuDocId = menuDocId;
  }
  public int getMenuDocId(){
	  return menuDocId;
  }
public String getDesc() {
	return desc;
}

public void setDesc(String desc) {
	this.desc = desc;
}

public int getParentId() {
	return parentId;
}

public void setParentId(int parentId) {
	this.parentId = parentId;
}





private int menuId;
  private String menuName;
  private String menuURL;
  private int menuDocId;
  private String desc;
  private int parentId;
}
