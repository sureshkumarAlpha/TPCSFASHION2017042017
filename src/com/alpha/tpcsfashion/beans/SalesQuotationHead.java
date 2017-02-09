package com.alpha.tpcsfashion.beans;

import java.util.ArrayList;
import java.util.List;

public class SalesQuotationHead {

private String headerMode;
    private String detMode;
    
	private SalesQuotation header;
	private String saveFollower;
	private String contactImage;
    private String sqlxmlDynamicFields;
	private List<DynamicFields> quotationDynList = new ArrayList<DynamicFields>();
    private int UserId;
    private boolean isExists;
    private int quoteId;
	private		String 	urlType;
	private		String 	url;
	private String fileName;
	private String loginName;
	private String UserFullName;
	private List<SalesQuotationDetail> salesQuotationDetList;
    
	
	
	
	public List<SalesQuotationDetail> getSalesQuotationDetList() {
		return salesQuotationDetList;
	}
	public void setSalesQuotationDetList(
			List<SalesQuotationDetail> salesQuotationDetList) {
		this.salesQuotationDetList = salesQuotationDetList;
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
	public int getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}
	public String getContactImage() {
		return contactImage;
	}
	public void setContactImage(String contactImage) {
		this.contactImage = contactImage;
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
	public String getSaveFollower() {
		return saveFollower;
	}
	public void setSaveFollower(String saveFollower) {
		this.saveFollower = saveFollower;
	}
	private SalesQuotationDetail detail;
    

	public List<DynamicFields> getQuotationDynList() {
		return quotationDynList;
	}
	public void setQuotationDynList(List<DynamicFields> quotationDynList) {
		this.quotationDynList = quotationDynList;
	}
	public String getSqlxmlDynamicFields() {
		return sqlxmlDynamicFields;
	}
	public void setSqlxmlDynamicFields(String sqlxmlDynamicFields) {
		this.sqlxmlDynamicFields = sqlxmlDynamicFields;
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
	public SalesQuotation getHeader() {
		return header;
	}
	public void setHeader(SalesQuotation header) {
		this.header = header;
	}
	public SalesQuotationDetail getDetail() {
		return detail;
	}
	public void setDetail(SalesQuotationDetail detail) {
		this.detail = detail;
	}
	public boolean isExists() {
		return isExists;
	}
	public void setExists(boolean isExists) {
		this.isExists = isExists;
	}

}