package com.alpha.tpcsfashion.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SalesEnquiry {

	private String headerMode;
	private String detMode;
	private SalesEnquiryHeader header;
	private SalesEnquiryDetail detail;
	private		String 	urlType;
	private		String 	url;
	private String fileName;
	private String loginName;
	private int enqId;
	private String UserFullName;
	private int userId;
	
	private Map<String,String> seDetMap;
	private String contactImage;
	private String sqlxmlDynamicFields;
	private List<DynamicFields> enquiryDynList = new ArrayList<DynamicFields>();
	private List<SalesEnquiryDetail> salesEnquiryDetList;





	public int getEnqId() {
		return enqId;
	}
	public void setEnqId(int enqId) {
		this.enqId = enqId;
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

	public List<SalesEnquiryDetail> getSalesEnquiryDetList() {
		return salesEnquiryDetList;
	}
	public void setSalesEnquiryDetList(List<SalesEnquiryDetail> salesEnquiryDetList) {
		this.salesEnquiryDetList = salesEnquiryDetList;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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

	public Map<String, String> getSeDetMap() {
		return seDetMap;
	}
	public void setSeDetMap(Map<String, String> seDetMap) {
		this.seDetMap = seDetMap;
	}
	public List<DynamicFields> getEnquiryDynList() {
		return enquiryDynList;
	}
	public void setEnquiryDynList(List<DynamicFields> enquiryDynList) {
		this.enquiryDynList = enquiryDynList;
	}
	public String getUserFullName() {
		return UserFullName;
	}
	public void setUserFullName(String userFullName) {
		UserFullName = userFullName;
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

	public SalesEnquiryHeader getHeader() {
		return header;
	}
	public void setHeader(SalesEnquiryHeader header) {
		this.header = header;
	}
	public SalesEnquiryDetail getDetail() {
		return detail;
	}
	public void setDetail(SalesEnquiryDetail detail) {
		this.detail = detail;
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


}