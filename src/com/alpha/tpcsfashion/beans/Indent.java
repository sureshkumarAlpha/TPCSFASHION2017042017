package com.alpha.tpcsfashion.beans;

import java.util.List;
import java.util.Map;

public class Indent {
	private String Name;
	private int Id;
	private String pageFieldName;
	private String headerMode;
	private String detailMode;
	private String editMode;
	private String saveType;
	private IndentDetail detail;
	private IndentHeader header;
	
	private List<IndentDetail> indentDetList;
	private Map<String,String> indentDetMap;
	
	private String sqlxmlDynamicFields;
	private List<DynamicFields> indentDynList;
	
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
	private List<Map<String,String>> indentMapList;
	
	private int deletedCount;
	private int cancelledCount;
	private int closedCount;
	

	private boolean isDeleted;
	private boolean isCancelled;
	private boolean isClosed;
	
	private boolean isbulkaction;
	private String isbulkexist;
	private List<IndentHeader> isbulkexistLst;
	private List<IndentDetail> soDetList;
	
	
	
	public List<IndentDetail> getSoDetList() {
		return soDetList;
	}

	public void setSoDetList(List<IndentDetail> soDetList) {
		this.soDetList = soDetList;
	}

	public int getClosedCount() {
		return closedCount;
	}

	public void setClosedCount(int closedCount) {
		this.closedCount = closedCount;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}
	
	
	
	
	public int getCancelledCount() {
		return cancelledCount;
	}

	public void setCancelledCount(int cancelledCount) {
		this.cancelledCount = cancelledCount;
	}
	
	
	
public boolean isIsbulkaction() {
		return isbulkaction;
	}

	public void setIsbulkaction(boolean isbulkaction) {
		this.isbulkaction = isbulkaction;
	}

	public String getIsbulkexist() {
		return isbulkexist;
	}

	public void setIsbulkexist(String isbulkexist) {
		this.isbulkexist = isbulkexist;
	}

	public List<IndentHeader> getIsbulkexistLst() {
		return isbulkexistLst;
	}

	public void setIsbulkexistLst(List<IndentHeader> isbulkexistLst) {
		this.isbulkexistLst = isbulkexistLst;
	}
	
	
	
	
	
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

	public String getPageFieldName() {
		return pageFieldName;
	}

	public void setPageFieldName(String pageFieldName) {
		this.pageFieldName = pageFieldName;
	}

	public String getHeaderMode() {
		return headerMode;
	}
	public void setHeaderMode(String headerMode) {
		this.headerMode = headerMode;
	}
	public String getDetailMode() {
		return detailMode;
	}
	public void setDetailMode(String detailMode) {
		this.detailMode = detailMode;
	}
	public String getEditMode() {
		return editMode;
	}
	public void setEditMode(String editMode) {
		this.editMode = editMode;
	}
	public String getSaveType() {
		return saveType;
	}
	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
	public IndentHeader getHeader() {
		return header;
	}
	public void setHeader(IndentHeader header) {
		this.header = header;
	}
	public IndentDetail getDetail() {
		return detail;
	}
	public void setDetail(IndentDetail detail) {
		this.detail = detail;
	}
	public List<IndentDetail> getIndentDetList() {
		return indentDetList;
	}
	public void setIndentDetList(List<IndentDetail> indentDetList) {
		this.indentDetList = indentDetList;
	}
	public Map<String, String> getIndentDetMap() {
		return indentDetMap;
	}
	public void setIndentDetMap(Map<String, String> indentDetMap) {
		this.indentDetMap = indentDetMap;
	}
	public String getSqlxmlDynamicFields() {
		return sqlxmlDynamicFields;
	}
	public void setSqlxmlDynamicFields(String sqlxmlDynamicFields) {
		this.sqlxmlDynamicFields = sqlxmlDynamicFields;
	}
	public List<DynamicFields> getIndentDynList() {
		return indentDynList;
	}
	public void setIndentDynList(List<DynamicFields> indentDynList) {
		this.indentDynList = indentDynList;
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
	public int getDetailTableId() {
		return detailTableId;
	}
	public void setDetailTableId(int detailTableId) {
		this.detailTableId = detailTableId;
	}
	public String getClientPath() {
		return clientPath;
	}
	public void setClientPath(String clientPath) {
		this.clientPath = clientPath;
	}
	public int getSubdocumentId() {
		return subdocumentId;
	}
	public void setSubdocumentId(int subdocumentId) {
		this.subdocumentId = subdocumentId;
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
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
	public List<Map<String, String>> getIndentMapList() {
		return indentMapList;
	}
	public void setIndentMapList(List<Map<String, String>> indentMapList) {
		this.indentMapList = indentMapList;
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
	
	
	
	
	
	
	}
