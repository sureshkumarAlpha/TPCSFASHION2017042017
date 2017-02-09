package com.alpha.tpcsfashion.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BOM {

	private String headerMode;
	private String detMode;
	private BOMHeader header;
	private BOMDetail detail;
	private		String 	urlType;
	private		String 	url;
	private String fileName;
	private String loginName;
	private int bomId;
	private int bomDetId;
	private String UserFullName;
	private int userId;
	private String altMatDetails;
	private Map<String,String> bomDetMap;
	private String contactImage;
	private String sqlxmlDynamicFields;
	private List<DynamicFields> bomDynList = new ArrayList<DynamicFields>();
	private List<BOMDetail> bomDetList;
	private int altMatId;
	private boolean inserted;
	private String bomNo;
	private int lockStatus;
	private String lockRemarks;
	private boolean deleted;
	private String pdfFileName;
	private List<BOMDetail> compList;
	private Map<String,String> dynHeadeDataMap;
	
	
	
	
	
	
	

	public Map<String, String> getDynHeadeDataMap() {
		return dynHeadeDataMap;
	}
	public void setDynHeadeDataMap(Map<String, String> dynHeadeDataMap) {
		this.dynHeadeDataMap = dynHeadeDataMap;
	}
	public List<BOMDetail> getCompList() {
		return compList;
	}
	public void setCompList(List<BOMDetail> compList) {
		this.compList = compList;
	}
	public String getPdfFileName() {
		return pdfFileName;
	}
	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}
	public int getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(int lockStatus) {
		this.lockStatus = lockStatus;
	}
	
	public String getBomNo() {
		return bomNo;
	}
	public void setBomNo(String bomNo) {
		this.bomNo = bomNo;
	}
	public String getLockRemarks() {
		return lockRemarks;
	}
	public void setLockRemarks(String lockRemarks) {
		this.lockRemarks = lockRemarks;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public int getAltMatId() {
		return altMatId;
	}
	public void setAltMatId(int altMatId) {
		this.altMatId = altMatId;
	}
	public boolean isInserted() {
		return inserted;
	}
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}
	public String getAltMatDetails() {
		return altMatDetails;
	}
	public void setAltMatDetails(String altMatDetails) {
		this.altMatDetails = altMatDetails;
	}
	public int getBomDetId() {
		return bomDetId;
	}
	public void setBomDetId(int bomDetId) {
		this.bomDetId = bomDetId;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public int getBomId() {
		return bomId;
	}
	public void setBomId(int bomId) {
		this.bomId = bomId;
	}
	public String getUserFullName() {
		return UserFullName;
	}
	public void setUserFullName(String userFullName) {
		UserFullName = userFullName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Map<String, String> getBomDetMap() {
		return bomDetMap;
	}
	public void setBomDetMap(Map<String, String> bomDetMap) {
		this.bomDetMap = bomDetMap;
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
	public List<DynamicFields> getBomDynList() {
		return bomDynList;
	}
	public void setBomDynList(List<DynamicFields> bomDynList) {
		this.bomDynList = bomDynList;
	}
	public BOMHeader getHeader() {
		return header;
	}
	public void setHeader(BOMHeader header) {
		this.header = header;
	}
	public BOMDetail getDetail() {
		return detail;
	}
	public void setDetail(BOMDetail detail) {
		this.detail = detail;
	}
	public List<BOMDetail> getBomDetList() {
		return bomDetList;
	}
	public void setBomDetList(List<BOMDetail> bomDetList) {
		this.bomDetList = bomDetList;
	}

	
	
}