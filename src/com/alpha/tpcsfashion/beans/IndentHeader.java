package com.alpha.tpcsfashion.beans;

import java.util.List;

public class IndentHeader {

	private 	int	companyId;
	private 	int	locationId;
	private 	int	yearId;
	private 	int	indentId;
	private 	String indentPrefix;
	private 	int indentNo;
	private 	String indentPrefixNo;
	private 	String indentDate;
	
	private 	String indentType;
	private String dept;
	private String remarks;
	private String otherRef; 
	private int	detTableWidth;
	private String attachType;
	private String attachPath;
	private		String 	urlType;
	private		String 	url;
	private		String 	fileName;
	private		List<IndentHeader> attachFiles;
	
	private int userId;
	private String userFullName;
	private String loginName;
	
	private int slNo;
	private String instruction;
	private String particulars;
	
	private boolean sendLater;
	
	private List<String> autoNoPrefixList;
	private String checkAutoNoType;
	
	private List<IndentHeader> indentUsers;
	private List<IndentHeader> defUsers;
	private String contactImage;
	
	
	private String tcValues;
	private int sl;
	private String slno;
	private String term;
	private String particular;
	private String tcIds;
	private int tcCnt;
	private List<IndentHeader> tcList;
	
	private int detailVisFieldCnt;
	
	


public int getDetTableWidth() {
		return detTableWidth;
	}

	public void setDetTableWidth(int detTableWidth) {
		this.detTableWidth = detTableWidth;
	}

public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getYearId() {
		return yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}

	public int getIndentId() {
		return indentId;
	}

	public void setIndentId(int indentId) {
		this.indentId = indentId;
	}

	public String getIndentPrefix() {
		return indentPrefix;
	}

	public void setIndentPrefix(String indentPrefix) {
		this.indentPrefix = indentPrefix;
	}

	public int getIndentNo() {
		return indentNo;
	}

	public void setIndentNo(int indentNo) {
		this.indentNo = indentNo;
	}

	public String getIndentPrefixNo() {
		return indentPrefixNo;
	}

	public void setIndentPrefixNo(String indentPrefixNo) {
		this.indentPrefixNo = indentPrefixNo;
	}

	public String getIndentDate() {
		return indentDate;
	}

	public void setIndentDate(String indentDate) {
		this.indentDate = indentDate;
	}

	public String getIndentType() {
		return indentType;
	}

	public void setIndentType(String indentType) {
		this.indentType = indentType;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOtherRef() {
		return otherRef;
	}

	public void setOtherRef(String otherRef) {
		this.otherRef = otherRef;
	}

	public String getAttachType() {
		return attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}

	public String getAttachPath() {
		return attachPath;
	}

	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
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

	public List<IndentHeader> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(List<IndentHeader> attachFiles) {
		this.attachFiles = attachFiles;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getSlNo() {
		return slNo;
	}

	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public boolean isSendLater() {
		return sendLater;
	}

	public void setSendLater(boolean sendLater) {
		this.sendLater = sendLater;
	}

	public List<String> getAutoNoPrefixList() {
		return autoNoPrefixList;
	}

	public void setAutoNoPrefixList(List<String> autoNoPrefixList) {
		this.autoNoPrefixList = autoNoPrefixList;
	}

	public String getCheckAutoNoType() {
		return checkAutoNoType;
	}

	public void setCheckAutoNoType(String checkAutoNoType) {
		this.checkAutoNoType = checkAutoNoType;
	}

	public List<IndentHeader> getIndentUsers() {
		return indentUsers;
	}

	public void setIndentUsers(List<IndentHeader> indentUsers) {
		this.indentUsers = indentUsers;
	}

	public List<IndentHeader> getDefUsers() {
		return defUsers;
	}

	public void setDefUsers(List<IndentHeader> defUsers) {
		this.defUsers = defUsers;
	}

	public String getContactImage() {
		return contactImage;
	}

	public void setContactImage(String contactImage) {
		this.contactImage = contactImage;
	}

	public String getTcValues() {
		return tcValues;
	}

	public void setTcValues(String tcValues) {
		this.tcValues = tcValues;
	}

	public int getSl() {
		return sl;
	}

	public void setSl(int sl) {
		this.sl = sl;
	}

	public String getSlno() {
		return slno;
	}

	public void setSlno(String slno) {
		this.slno = slno;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getParticular() {
		return particular;
	}

	public void setParticular(String particular) {
		this.particular = particular;
	}

	public String getTcIds() {
		return tcIds;
	}

	public void setTcIds(String tcIds) {
		this.tcIds = tcIds;
	}

	public int getTcCnt() {
		return tcCnt;
	}

	public void setTcCnt(int tcCnt) {
		this.tcCnt = tcCnt;
	}

	public List<IndentHeader> getTcList() {
		return tcList;
	}

	public void setTcList(List<IndentHeader> tcList) {
		this.tcList = tcList;
	}

	public int getDetailVisFieldCnt() {
		return detailVisFieldCnt;
	}

	public void setDetailVisFieldCnt(int detailVisFieldCnt) {
		this.detailVisFieldCnt = detailVisFieldCnt;
	}

	
}

