package com.alpha.tpcsfashion.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ColumnPreference
{
	  List<String> visibleColumns;
	  List<String> columnNameList;
	  List<String> queryColumnList;
	  List<String> OrderBy;
	  List<String> groupByList;
	  List<String> dbFieldNameList;
	  private String columnId;
	  private String columnDisplayName;
	  private String orderByColumn;
	  private String columnName;
	  private String queryColumn;
	  private String dbFieldName;
	  private String groupBy;
	  private String groupByOrder;
	  private List<ColumnPreference> cpList;
	  private List<ColumnPreference> groupByColumnList;
	  private List<ColumnPreference> columnToTotalList;
	  private List<ColumnPreference> operatorList;
	  private List<ColumnPreference> columnList;
	  
	  private String firstOrderBy;
	  private String secondOrderBy;
	  private String thirdOrderBy;
	  
	  private int groupCnt;
	  private List<String> columnsToTotal;
	  private List<String> columnsToAvg;
	  private List<String> columnsToMin;
	  private List<String> columnsToMax;
	  private int total;
	  private int average;
  	  private int minimum;
  	  private int maximum;
  	  
  	  private int operatorId;
  	  private int dataType;
  	  private String operator;
  	  private String operatorDb;
  	  private String visColumns;
  	  private Map<Integer,String> mapGroupByOrder;
  	  private Map<String,String> mapColumnToTotal;
  	  private Map<String,String> mapCriteriaQuery;
  	  private int criteriaId;
  	  private String finalQuery;
  	  private String finalDbQuery;
  	  private String finalDbHavingQuery;
  	  private List<Integer> columnToTotalColId;
  	  private int reportId;
  	  private List<String> criteriaIds;
  	  private List<ColumnPreference> criteriaQuery;
  	  private int criteriaRowCnt;
  	  private String reportName;
  	  private String reportUrl;
  	  private String reportDesc;
  	  private boolean isSaved;
  	  private boolean isGroupPaging;
  	  private int pageCnt;
  	  private String groupPagingName;
  	  private int firstGroupDataTypeId;
  	  private boolean isDescending;
  	  private ColumnPreference paging;
  	  
  	  private String startDate;
  	  private String endDate;
  	  private int filterType;
  	  
	public List<String> getVisibleColumns() {
		return visibleColumns;
	}
	public void setVisibleColumns(List<String> visibleColumns) {
		this.visibleColumns = visibleColumns;
	}
	public List<String> getOrderBy() {
		return OrderBy;
	}
	public void setOrderBy(List<String> orderBy) {
		OrderBy = orderBy;
	}
	public List<String> getColumnNameList() {
		return columnNameList;
	}
	public void setColumnNameList(List<String> columnNameList) {
		this.columnNameList = columnNameList;
	}
//	public List<String> getFieldName() {
//		return fieldName;
//	}
//	public void setFieldName(List<String> fieldName) {
//		this.fieldName = fieldName;
//	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getColumnDisplayName() {
		return columnDisplayName;
	}
	public void setColumnDisplayName(String columnDisplayName) {
		this.columnDisplayName = columnDisplayName;
	}
	public String getOrderByColumn() {
		return orderByColumn;
	}
	public void setOrderByColumn(String orderByColumn) {
		this.orderByColumn = orderByColumn;
	}
	public String getDbFieldName() {
		return dbFieldName;
	}
	public void setDbFieldName(String dbFieldName) {
		this.dbFieldName = dbFieldName;
	}
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}
	public String getGroupByOrder() {
		return groupByOrder;
	}
	public void setGroupByOrder(String groupByOrder) {
		this.groupByOrder = groupByOrder;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public List<ColumnPreference> getCpList() {
		return cpList;
	}
	public void setCpList(List<ColumnPreference> cpList) {
		this.cpList = cpList;
	}
	public List<String> getGroupByList() {
		return groupByList;
	}
	public void setGroupByList(List<String> groupByList) {
		this.groupByList = groupByList;
	}
	public int getGroupCnt() {
		return groupCnt;
	}
	public void setGroupCnt(int groupCnt) {
		this.groupCnt = groupCnt;
	}
	public List<String> getColumnsToTotal() {
		return columnsToTotal;
	}
	public void setColumnsToTotal(List<String> columnsToTotal) {
		this.columnsToTotal = columnsToTotal;
	}
	public List<String> getColumnsToAvg() {
		return columnsToAvg;
	}
	public void setColumnsToAvg(List<String> columnsToAvg) {
		this.columnsToAvg = columnsToAvg;
	}
	public List<String> getColumnsToMin() {
		return columnsToMin;
	}
	public void setColumnsToMin(List<String> columnsToMin) {
		this.columnsToMin = columnsToMin;
	}
	public List<String> getColumnsToMax() {
		return columnsToMax;
	}
	public void setColumnsToMax(List<String> columnsToMax) {
		this.columnsToMax = columnsToMax;
	}
	public List<ColumnPreference> getGroupByColumnList() {
		return groupByColumnList;
	}
	public void setGroupByColumnList(List<ColumnPreference> groupByColumnList) {
		this.groupByColumnList = groupByColumnList;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getAverage() {
		return average;
	}
	public void setAverage(int average) {
		this.average = average;
	}
	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	public List<ColumnPreference> getColumnToTotalList() {
		return columnToTotalList;
	}
	public void setColumnToTotalList(List<ColumnPreference> columnToTotalList) {
		this.columnToTotalList = columnToTotalList;
	}
	public int getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
	public int getDataType() {
		return dataType;
	}
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperatorDb() {
		return operatorDb;
	}
	public void setOperatorDb(String operatorDb) {
		this.operatorDb = operatorDb;
	}
	public List<ColumnPreference> getOperatorList() {
		return operatorList;
	}
	public void setOperatorList(List<ColumnPreference> operatorList) {
		this.operatorList = operatorList;
	}
	public List<ColumnPreference> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<ColumnPreference> columnList) {
		this.columnList = columnList;
	}
	public String getVisColumns() {
		return visColumns;
	}
	public void setVisColumns(String visColumns) {
		this.visColumns = visColumns;
	}
	public Map<Integer, String> getMapGroupByOrder() {
		return mapGroupByOrder;
	}
	public void setMapGroupByOrder(Map<Integer, String> mapGroupByOrder) {
		this.mapGroupByOrder = mapGroupByOrder;
	}
	public Map<String, String> getMapColumnToTotal() {
		return mapColumnToTotal;
	}
	public void setMapColumnToTotal(Map<String, String> mapColumnToTotal) {
		this.mapColumnToTotal = mapColumnToTotal;
	}
	public String getFinalQuery() {
		return finalQuery;
	}
	public void setFinalQuery(String finalQuery) {
		this.finalQuery = finalQuery;
	}
	public String getFinalDbQuery() {
		return finalDbQuery;
	}
	public void setFinalDbQuery(String finalDbQuery) {
		this.finalDbQuery = finalDbQuery;
	}
	public List<Integer> getColumnToTotalColId() {
		return columnToTotalColId;
	}
	public void setColumnToTotalColId(List<Integer> columnToTotalColId) {
		this.columnToTotalColId = columnToTotalColId;
	}
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public String getFinalDbHavingQuery() {
		return finalDbHavingQuery;
	}
	public void setFinalDbHavingQuery(String finalDbHavingQuery) {
		this.finalDbHavingQuery = finalDbHavingQuery;
	}
	public Map<String, String> getMapCriteriaQuery() {
		return mapCriteriaQuery;
	}
	public void setMapCriteriaQuery(Map<String, String> mapCriteriaQuery) {
		this.mapCriteriaQuery = mapCriteriaQuery;
	}
	public List<String> getCriteriaIds() {
		return criteriaIds;
	}
	public void setCriteriaIds(List<String> criteriaIds) {
		this.criteriaIds = criteriaIds;
	}
	public List<ColumnPreference> getCriteriaQuery() {
		return criteriaQuery;
	}
	public void setCriteriaQuery(List<ColumnPreference> criteriaQuery) {
		this.criteriaQuery = criteriaQuery;
	}
	public int getCriteriaId() {
		return criteriaId;
	}
	public void setCriteriaId(int criteriaId) {
		this.criteriaId = criteriaId;
	}
	public int getCriteriaRowCnt() {
		return criteriaRowCnt;
	}
	public void setCriteriaRowCnt(int criteriaRowCnt) {
		this.criteriaRowCnt = criteriaRowCnt;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getReportDesc() {
		return reportDesc;
	}
	public void setReportDesc(String reportDesc) {
		this.reportDesc = reportDesc;
	}
	public boolean isSaved() {
		return isSaved;
	}
	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}
	public boolean isGroupPaging() {
		return isGroupPaging;
	}
	public void setGroupPaging(boolean isGroupPaging) {
		this.isGroupPaging = isGroupPaging;
	}

	public String getGroupPagingName() {
		return groupPagingName;
	}
	public void setGroupPagingName(String groupPagingName) {
		this.groupPagingName = groupPagingName;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public ColumnPreference getPaging() {
		return paging;
	}
	public void setPaging(ColumnPreference paging) {
		this.paging = paging;
	}
	public int getFirstGroupDataTypeId() {
		return firstGroupDataTypeId;
	}
	public void setFirstGroupDataTypeId(int firstGroupDataTypeId) {
		this.firstGroupDataTypeId = firstGroupDataTypeId;
	}
	public String getQueryColumn() {
		return queryColumn;
	}
	public void setQueryColumn(String queryColumn) {
		this.queryColumn = queryColumn;
	}
	public List<String> getQueryColumnList() {
		return queryColumnList;
	}
	public void setQueryColumnList(List<String> queryColumnList) {
		this.queryColumnList = queryColumnList;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getFilterType() {
		return filterType;
	}
	public void setFilterType(int filterType) {
		this.filterType = filterType;
	}
	public String getReportUrl() {
		return reportUrl;
	}
	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}
	public List<String> getDbFieldNameList() {
		return dbFieldNameList;
	}
	public void setDbFieldNameList(List<String> dbFieldNameList) {
		this.dbFieldNameList = dbFieldNameList;
	}
	public boolean isDescending() {
		return isDescending;
	}
	public void setDescending(boolean isDescending) {
		this.isDescending = isDescending;
	}
	public String getFirstOrderBy() {
		return firstOrderBy;
	}
	public void setFirstOrderBy(String firstOrderBy) {
		this.firstOrderBy = firstOrderBy;
	}
	public String getSecondOrderBy() {
		return secondOrderBy;
	}
	public void setSecondOrderBy(String secondOrderBy) {
		this.secondOrderBy = secondOrderBy;
	}
	public String getThirdOrderBy() {
		return thirdOrderBy;
	}
	public void setThirdOrderBy(String thirdOrderBy) {
		this.thirdOrderBy = thirdOrderBy;
	}
	
}