package com.alpha.tpcsfashion.beans;

import java.util.List;

public class CommonUtil {
	private String pagePrefixId;
	private String pageAutoNoId;
	private String headerNoColumn;
	private String headerIdColumn;
	private String headerTableName;
	private String autoNoTableName;
	private String headerTypeColumn;
	private String headerTypeValue;
	
	private String prefix;
	private int startingNo;
	
	private List<String> prefixList; 
	private List<String> slnoList;
	private int commonNo;
	private boolean isCommonSlno;
	private boolean isSepSlno;
	private boolean isManSlno;
	private String autoNoString;
	private List<String> deletePrefixList;
	
	private String tableIds;
	private int fixed;
	private int dynamicFieldId;
	private String labelName;
	private String pageFieldName;
	private String dbFieldName;
	private int tableId;
	
	private List<CommonUtil> comUtilList;
	
	private int taxGroupId;
	private String quantity;
	private String priceFcy;
	private String discountPercent;
	
	private double totValAftDis;
	private double totEDVal;
	private double totECESSVal;
	private double totSHECESSVal;
	private double totVATVal;
	private double totCSTVal;
	private double totGSTVal;
	private double totServiceTaxVal;
	private String strTaxGroupIds;
	
	private int purchaseTypeId;
	private String purchaseType;
	
	public String getPagePrefixId() {
		return pagePrefixId;
	}
	public void setPagePrefixId(String pagePrefixId) {
		this.pagePrefixId = pagePrefixId;
	}
	public String getPageAutoNoId() {
		return pageAutoNoId;
	}
	public void setPageAutoNoId(String pageAutoNoId) {
		this.pageAutoNoId = pageAutoNoId;
	}
	public String getHeaderNoColumn() {
		return headerNoColumn;
	}
	public void setHeaderNoColumn(String headerNoColumn) {
		this.headerNoColumn = headerNoColumn;
	}
	public String getHeaderIdColumn() {
		return headerIdColumn;
	}
	public void setHeaderIdColumn(String headerIdColumn) {
		this.headerIdColumn = headerIdColumn;
	}
	public String getHeaderTableName() {
		return headerTableName;
	}
	public void setHeaderTableName(String headerTableName) {
		this.headerTableName = headerTableName;
	}
	public String getAutoNoTableName() {
		return autoNoTableName;
	}
	public void setAutoNoTableName(String autoNoTableName) {
		this.autoNoTableName = autoNoTableName;
	}
	public String getHeaderTypeColumn() {
		return headerTypeColumn;
	}
	public void setHeaderTypeColumn(String headerTypeColumn) {
		this.headerTypeColumn = headerTypeColumn;
	}
	public String getHeaderTypeValue() {
		return headerTypeValue;
	}
	public void setHeaderTypeValue(String headerTypeValue) {
		this.headerTypeValue = headerTypeValue;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public int getStartingNo() {
		return startingNo;
	}
	public void setStartingNo(int startingNo) {
		this.startingNo = startingNo;
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
	public int getCommonNo() {
		return commonNo;
	}
	public void setCommonNo(int commonNo) {
		this.commonNo = commonNo;
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
	public String getTableIds() {
		return tableIds;
	}
	public void setTableIds(String tableIds) {
		this.tableIds = tableIds;
	}
	public int getFixed() {
		return fixed;
	}
	public void setFixed(int fixed) {
		this.fixed = fixed;
	}
	public int getDynamicFieldId() {
		return dynamicFieldId;
	}
	public void setDynamicFieldId(int dynamicFieldId) {
		this.dynamicFieldId = dynamicFieldId;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public String getPageFieldName() {
		return pageFieldName;
	}
	public void setPageFieldName(String pageFieldName) {
		this.pageFieldName = pageFieldName;
	}
	public List<CommonUtil> getComUtilList() {
		return comUtilList;
	}
	public void setComUtilList(List<CommonUtil> comUtilList) {
		this.comUtilList = comUtilList;
	}
	public int getTaxGroupId() {
		return taxGroupId;
	}
	public void setTaxGroupId(int taxGroupId) {
		this.taxGroupId = taxGroupId;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPriceFcy() {
		return priceFcy;
	}
	public void setPriceFcy(String priceFcy) {
		this.priceFcy = priceFcy;
	}
	public double getTotValAftDis() {
		return totValAftDis;
	}
	public void setTotValAftDis(double totValAftDis) {
		this.totValAftDis = totValAftDis;
	}
	public double getTotEDVal() {
		return totEDVal;
	}
	public void setTotEDVal(double totEDVal) {
		this.totEDVal = totEDVal;
	}
	public double getTotECESSVal() {
		return totECESSVal;
	}
	public void setTotECESSVal(double totECESSVal) {
		this.totECESSVal = totECESSVal;
	}
	public double getTotSHECESSVal() {
		return totSHECESSVal;
	}
	public void setTotSHECESSVal(double totSHECESSVal) {
		this.totSHECESSVal = totSHECESSVal;
	}
	public double getTotVATVal() {
		return totVATVal;
	}
	public void setTotVATVal(double totVATVal) {
		this.totVATVal = totVATVal;
	}
	public double getTotCSTVal() {
		return totCSTVal;
	}
	public void setTotCSTVal(double totCSTVal) {
		this.totCSTVal = totCSTVal;
	}
	public double getTotGSTVal() {
		return totGSTVal;
	}
	public void setTotGSTVal(double totGSTVal) {
		this.totGSTVal = totGSTVal;
	}
	public double getTotServiceTaxVal() {
		return totServiceTaxVal;
	}
	public void setTotServiceTaxVal(double totServiceTaxVal) {
		this.totServiceTaxVal = totServiceTaxVal;
	}
	public String getStrTaxGroupIds() {
		return strTaxGroupIds;
	}
	public void setStrTaxGroupIds(String strTaxGroupIds) {
		this.strTaxGroupIds = strTaxGroupIds;
	}
	public String getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}
	public String getDbFieldName() {
		return dbFieldName;
	}
	public void setDbFieldName(String dbFieldName) {
		this.dbFieldName = dbFieldName;
	}
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public int getPurchaseTypeId() {
		return purchaseTypeId;
	}
	public void setPurchaseTypeId(int purchaseTypeId) {
		this.purchaseTypeId = purchaseTypeId;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	
	
}
