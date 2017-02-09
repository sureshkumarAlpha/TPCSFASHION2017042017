package com.alpha.tpcsfashion.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SalesInvoice {
private SalesInvoiceHeader header;
private SalesInvoiceDetail detail;
private String headerMode;
private String detMode;
private String saveFollower;
private String contactImage;
private String sqlxmlDynamicFields;
private List<DynamicFields> invoiceDynList = new ArrayList<DynamicFields>();
private List<SalesInvoiceDetail> salesInvoiceDetList;
private Map<String,String> siDetMap;
private		String 	urlType;
private		String 	url;
private		String 	fileName;
private int invId;
private String UserFullName;
private int UserId;
private String loginName;
private List<String> prefixList; 
private List<String> slnoList;
private int commonNo;
private boolean isCommonSlno;
private boolean isSepSlno;
private boolean isManSlno;
private String autoNoString;
private List<String> deletePrefixList;
//private SalesAccountConfiguration salesAccConfig;
private String salesAccInfo;

public String getLoginName() {
	return loginName;
}
public void setLoginName(String loginName) {
	this.loginName = loginName;
}
public Map<String, String> getSiDetMap() {
	return siDetMap;
}
public void setSiDetMap(Map<String, String> siDetMap) {
	this.siDetMap = siDetMap;
}
public SalesInvoiceHeader getHeader() {
	return header;
}
public void setHeader(SalesInvoiceHeader header) {
	this.header = header;
}
public SalesInvoiceDetail getDetail() {
	return detail;
}
public void setDetail(SalesInvoiceDetail detail) {
	this.detail = detail;
}
public String getHeaderMode() {
	return headerMode;
}
public void setHeaderMode(String headerMode) {
	this.headerMode = headerMode;
}
public String getDetMode() {
	return detMode;
}
public void setDetMode(String detMode) {
	this.detMode = detMode;
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
public List<DynamicFields> getInvoiceDynList() {
	return invoiceDynList;
}
public void setInvoiceDynList(List<DynamicFields> invoiceDynList) {
	this.invoiceDynList = invoiceDynList;
}
public List<SalesInvoiceDetail> getSalesInvoiceDetList() {
	return salesInvoiceDetList;
}
public void setSalesInvoiceDetList(List<SalesInvoiceDetail> salesInvoiceDetList) {
	this.salesInvoiceDetList = salesInvoiceDetList;
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
public int getInvId() {
	return invId;
}
public void setInvId(int invId) {
	this.invId = invId;
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
public List<String> getPrefixList() {
	return prefixList;
}
public void setPrefixList(List<String> prefixList) {
	this.prefixList = prefixList;
}
public List<String> getSlnoList() {
	return slnoList;
}
public void setSlnoList(List<String> slnoList) {
	this.slnoList = slnoList;
}
public boolean isCommonSlno() {
	return isCommonSlno;
}
public void setCommonSlno(boolean isCommonSlno) {
	this.isCommonSlno = isCommonSlno;
}
public boolean isSepSlno() {
	return isSepSlno;
}
public void setSepSlno(boolean isSepSlno) {
	this.isSepSlno = isSepSlno;
}
public boolean isManSlno() {
	return isManSlno;
}
public void setManSlno(boolean isManSlno) {
	this.isManSlno = isManSlno;
}
public int getCommonNo() {
	return commonNo;
}
public void setCommonNo(int commonNo) {
	this.commonNo = commonNo;
}
public String getAutoNoString() {
	return autoNoString;
}
public void setAutoNoString(String autoNoString) {
	this.autoNoString = autoNoString;
}
public List<String> getDeletePrefixList() {
	return deletePrefixList;
}
public void setDeletePrefixList(List<String> deletePrefixList) {
	this.deletePrefixList = deletePrefixList;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
/*public SalesAccountConfiguration getSalesAccConfig() {
	return salesAccConfig;
}
public void setSalesAccConfig(SalesAccountConfiguration salesAccConfig) {
	this.salesAccConfig = salesAccConfig;
}*/
public String getSalesAccInfo() {
	return salesAccInfo;
}
public void setSalesAccInfo(String salesAccInfo) {
	this.salesAccInfo = salesAccInfo;
}
 
}
