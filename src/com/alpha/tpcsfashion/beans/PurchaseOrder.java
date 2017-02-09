package com.alpha.tpcsfashion.beans;

import java.util.List;
import java.util.Map;

public class PurchaseOrder 
{

	private PurchaseOrderHeader header;
	private PurchaseOrderDetail detail;
	private List<Map<String,String>> boMapList;
	private List<PurchaseOrderDetail> poDetList;
	private Map<String,String> poDetMap;
	private List<Map<String,String>> poMapList;
	
	private String sqlxmlDynamicFields;
	private List<DynamicFields> poDynList;
	private List<Integer> detIdList;
	
	private Map<String,String> editDataMap;
	private Map<String,String> editDynamicDataMap;
	
	private String headerMode;
	private String detailMode;
	private String editMode;
	private String saveType;
	
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
	
	private int deletedCount;
	private boolean isDeleted;
	private boolean isCancelled;
	private boolean isClosed;
	
	public List<Map<String, String>> getPoMapList() {
		return poMapList;
	}
	public void setPoMapList(List<Map<String, String>> poMapList) {
		this.poMapList = poMapList;
	}
	
	public List<Map<String, String>> getBoMapList() {
		return boMapList;
	}
	public void setBoMapList(List<Map<String, String>> boMapList) {
		this.boMapList = boMapList;
	}
	
	public String getHeaderMode() {
		return headerMode;
	}
	public void setHeaderMode(String headerMode) {
		this.headerMode = headerMode;
	}

	public PurchaseOrderHeader getHeader() {
		return header;
	}
	public void setHeader(PurchaseOrderHeader header) {
		this.header = header;
	}
	public PurchaseOrderDetail getDetail() {
		return detail;
	}
	public void setDetail(PurchaseOrderDetail detail) {
		this.detail = detail;
	}
	public void setPoDetMap(Map<String, String> poDetMap) {
		this.poDetMap = poDetMap;
	}
	public void setPoDynList(List<DynamicFields> poDynList) {
		this.poDynList = poDynList;
	}
 	
	public List<PurchaseOrderDetail> getPoDetList() {
		return poDetList;
	}
	public void setPoDetList(List<PurchaseOrderDetail> poDetList) {
		this.poDetList = poDetList;
	}
	public Map<String, String> getPoDetMap() {
		return poDetMap;
	}
	public void setBoDetMap(Map<String, String> poDetMap) {
		this.poDetMap = poDetMap;
	}
	public String getSqlxmlDynamicFields() {
		return sqlxmlDynamicFields;
	}
	public void setSqlxmlDynamicFields(String sqlxmlDynamicFields) {
		this.sqlxmlDynamicFields = sqlxmlDynamicFields;
	}
	public List<DynamicFields> getPoDynList() {
		return poDynList;
	}
	public void setBoDynList(List<DynamicFields> poDynList) {
		this.poDynList = poDynList;
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

