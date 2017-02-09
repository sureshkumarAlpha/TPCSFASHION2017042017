package com.alpha.tpcsfashion.beans;

import java.util.List;
import java.util.Map;

public class BuyerOrder {

	private String headerMode;
	private String detailMode;
	private String editMode;
	private String saveType;

	private BuyerOrderHeader header;
	private BuyerOrderDetail detail;

	private List<BuyerOrderDetail> boDetList;
	private Map<String,String> boDetMap;

	private String sqlxmlDynamicFields;
	private List<DynamicFields> boDynList;

	private List<Integer> detIdList;

	private Map<String,String> editDataMap;
	private Map<String,String> editDynamicDataMap;


	private int screenId;
	private int headerTableId;
	private int detailTableId;

	private String clientPath;

	private int subdocumentId;
	private String attachPath;
	private String searchCriteria;
	private List<String> orderByList;
	private Map<String,String> visColMap;
	private List<String> listSelectedColumns;
	private List<Map<String,String>> boMapList;
	
	private int deletedCount;
	
	private boolean isDeleted;
	private boolean isCancelled;
	private boolean isClosed;
	
 	public BuyerOrderHeader getHeader() {
		return header;
	}
	public void setHeader(BuyerOrderHeader header) {
		this.header = header;
	}
	public BuyerOrderDetail getDetail() {
		return detail;
	}
	public void setDetail(BuyerOrderDetail detail) {
		this.detail = detail;
	}
	public String getHeaderMode() {
		return headerMode;
	}
	public void setHeaderMode(String headerMode) {
		this.headerMode = headerMode;
	}
	public List<BuyerOrderDetail> getBoDetList() {
		return boDetList;
	}
	public void setBoDetList(List<BuyerOrderDetail> boDetList) {
		this.boDetList = boDetList;
	}
	public Map<String, String> getBoDetMap() {
		return boDetMap;
	}
	public void setBoDetMap(Map<String, String> boDetMap) {
		this.boDetMap = boDetMap;
	}
	public String getSqlxmlDynamicFields() {
		return sqlxmlDynamicFields;
	}
	public void setSqlxmlDynamicFields(String sqlxmlDynamicFields) {
		this.sqlxmlDynamicFields = sqlxmlDynamicFields;
	}
	public List<DynamicFields> getBoDynList() {
		return boDynList;
	}
	public void setBoDynList(List<DynamicFields> boDynList) {
		this.boDynList = boDynList;
	}
	public List<Integer> getDetIdList() {
		return detIdList;
	}
	public void setDetIdList(List<Integer> detIdList) {
		this.detIdList = detIdList;
	}
	public Map<String, String> getEditDataMap() {
		return editDataMap;
	}
	public void setEditDataMap(Map<String, String> editDataMap) {
		this.editDataMap = editDataMap;
	}
	public Map<String, String> getEditDynamicDataMap() {
		return editDynamicDataMap;
	}
	public void setEditDynamicDataMap(Map<String, String> editDynamicDataMap) {
		this.editDynamicDataMap = editDynamicDataMap;
	}
	public String getDetailMode() {
		return detailMode;
	}
	public void setDetailMode(String detailMode) {
		this.detailMode = detailMode;
	}
	public String getSaveType() {
		return saveType;
	}
	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
	public String getEditMode() {
		return editMode;
	}
	public void setEditMode(String editMode) {
		this.editMode = editMode;
	}
	 
	public int getDetailTableId() {
		return detailTableId;
	}
	public void setDetailTableId(int detailTableId) {
		this.detailTableId = detailTableId;
	}
	public int getScreenId() {
		return screenId;
	}
	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}
	public int getHeaderTableId() {
		return headerTableId;
	}
	public void setHeaderTableId(int headerTableId) {
		this.headerTableId = headerTableId;
	}
	public String getClientPath() {
		return clientPath;
	}
	public void setClientPath(String clientPath) {
		this.clientPath = clientPath;
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	public int getSubdocumentId() {
		return subdocumentId;
	}
	public void setSubdocumentId(int subdocumentId) {
		this.subdocumentId = subdocumentId;
	}
	public String getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	public List<String> getOrderByList() {
		return orderByList;
	}
	public void setOrderByList(List<String> orderByList) {
		this.orderByList = orderByList;
	}
	public Map<String, String> getVisColMap() {
		return visColMap;
	}
	public void setVisColMap(Map<String, String> visColMap) {
		this.visColMap = visColMap;
	}
	public List<String> getListSelectedColumns() {
		return listSelectedColumns;
	}
	public void setListSelectedColumns(List<String> listSelectedColumns) {
		this.listSelectedColumns = listSelectedColumns;
	}
	public List<Map<String, String>> getBoMapList() {
		return boMapList;
	}
	public void setBoMapList(List<Map<String, String>> boMapList) {
		this.boMapList = boMapList;
	}
	public int getDeletedCount() {
		return deletedCount;
	}
	public void setDeletedCount(int deletedCount) {
		this.deletedCount = deletedCount;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public boolean isCancelled() {
		return isCancelled;
	}
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	public boolean isClosed() {
		return isClosed;
	}
	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}
 
	
	
}
