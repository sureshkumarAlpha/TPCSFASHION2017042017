package com.alpha.tpcsfashion.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SalesOrderPage {
//private SalesOrder header;
private SalesOrderDetail detail;
private String headerMode;
private String detMode;
private int quoteId;
private String saveFollower;
private String contactImage;
private String sqlxmlDynamicFields;
private List<DynamicFields> orderDynList = new ArrayList<DynamicFields>();
private List<SalesOrderDetail> salesOrderDetList;
private Map<String,String> soDetMap;
private		String 	urlType;
private		String 	url;
private		String 	fileName;
private int soId;
private int UserId; 
private String loginName;
private String UserFullName;



public String getLoginName() {
	return loginName;
}
public void setLoginName(String loginName) {
	this.loginName = loginName;
}
public int getSoId() {
	return soId;
}
public void setSoId(int soId) {
	this.soId = soId;
}
public String getUserFullName() {
	return UserFullName;
}
public void setUserFullName(String userFullName) {
	UserFullName = userFullName;
}
public int getUserId() {
	return UserId;
}
public void setUserId(int userId) {
	UserId = userId;
}
public String getUrlType() {
	return urlType;
}
public void setUrlType(String urlType) {
	this.urlType = urlType;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public List<SalesOrderDetail> getSalesOrderDetList() {
	return salesOrderDetList;
}
public void setSalesOrderDetList(List<SalesOrderDetail> salesOrderDetList) {
	this.salesOrderDetList = salesOrderDetList;
}
public Map<String, String> getSoDetMap() {
	return soDetMap;
}
public void setSoDetMap(Map<String, String> soDetMap) {
	this.soDetMap = soDetMap;
}
public List<DynamicFields> getOrderDynList() {
	return orderDynList;
}
public void setOrderDynList(List<DynamicFields> orderDynList) {
	this.orderDynList = orderDynList;
}
public String getSaveFollower() {
	return saveFollower;
}
public void setSaveFollower(String saveFollower) {
	this.saveFollower = saveFollower;
}
public String getContactImage() {
	return contactImage;
}
public void setContactImage(String contactImage) {
	this.contactImage = contactImage;
}
public String getSqlxmlDynamicFields() {
	return sqlxmlDynamicFields;
}
public void setSqlxmlDynamicFields(String sqlxmlDynamicFields) {
	this.sqlxmlDynamicFields = sqlxmlDynamicFields;
}
public int getQuoteId() {
	return quoteId;
}
public void setQuoteId(int quoteId) {
	this.quoteId = quoteId;
}
public void setHeaderMode(String headerMode) {
	this.headerMode = headerMode;
}
public String getHeaderMode() {
	return headerMode;
}
public void setDetMode(String detMode) {
	this.detMode = detMode;
}
public String getDetMode() {
	return detMode;
}
public void setDetail(SalesOrderDetail detail) {
	this.detail = detail;
}
public SalesOrderDetail getDetail() {
	return detail;
}
/*public void setHeader(SalesOrder header) {
	this.header = header;
}
public SalesOrder getHeader() {
	return header;
}*/
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}

}
