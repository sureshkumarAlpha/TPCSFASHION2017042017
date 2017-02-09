package com.alpha.tpcsfashion.beans;

import java.io.Serializable;
import java.util.List;

public class Generic implements Serializable
{
  private static final long serialVersionUID = 1L;
 
  
  public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public String getRemark() {
	return Remark;
}
public void setRemark(String remark) {
	Remark = remark;
}
public String getContactImage() {
	return contactImage;
}
public void setContactImage(String contactImage) {
	this.contactImage = contactImage;
}
private String Name;
  private int Id;
  private String Remark;
  private String contactImage;

}
