package com.alpha.tpcsfashion.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApprovedPriceList {

	private int	purchasePriceId;
	private int	supplierId;
	private String supplierName;
	private int	matId;
	private String matName;
	private int	colorId;
	private String colorName;
	private String	rate;
	private String	uom;
	private String	currencyName;
	private int	leadTimeDays;
	private String	supplierDesc;
	private String moq;
	private int	sizeRangeId;
	private String sizeRange;
	private int	sizeId;
	private String mode;
	private int sizeRangeGridid;
	private StringBuffer buffer;
	private String sqlxmlDynamicFields;
	private List<DynamicFields> ApprovedPriceListDynList = new ArrayList<DynamicFields>();
	private List<ApprovedPriceList> approvedPriceDetailList;
	private Map<String,String> aplDetMap;
	private String sizeGrid;
	private List<List<ApprovedPriceList>> apRowList;
	
	private String EditDetailsID;
	
	
	public String getEditDetailsID() {
		return EditDetailsID;
	}
	public void setEditDetailsID(String editDetailsID) {
		EditDetailsID = editDetailsID;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getMatName() {
		return matName;
	}
	public void setMatName(String matName) {
		this.matName = matName;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public int getPurchasePriceId() {
		return purchasePriceId;
	}
	public void setPurchasePriceId(int purchasePriceId) {
		this.purchasePriceId = purchasePriceId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public int getMatId() {
		return matId;
	}
	public void setMatId(int matId) {
		this.matId = matId;
	}
	public int getColorId() {
		return colorId;
	}
	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public int getLeadTimeDays() {
		return leadTimeDays;
	}
	public void setLeadTimeDays(int leadTimeDays) {
		this.leadTimeDays = leadTimeDays;
	}
	public String getSupplierDesc() {
		return supplierDesc;
	}
	public void setSupplierDesc(String supplierDesc) {
		this.supplierDesc = supplierDesc;
	}
	public String getMoq() {
		return moq;
	}
	public void setMoq(String moq) {
		this.moq = moq;
	}
	public int getSizeRangeId() {
		return sizeRangeId;
	}
	public void setSizeRangeId(int sizeRangeId) {
		this.sizeRangeId = sizeRangeId;
	}
	public int getSizeId() {
		return sizeId;
	}
	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getSizeRangeGridid() {
		return sizeRangeGridid;
	}
	public void setSizeRangeGridid(int sizeRangeGridid) {
		this.sizeRangeGridid = sizeRangeGridid;
	}
	public StringBuffer getBuffer() {
		return buffer;
	}
	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}
	public String getSqlxmlDynamicFields() {
		return sqlxmlDynamicFields;
	}
	public void setSqlxmlDynamicFields(String sqlxmlDynamicFields) {
		this.sqlxmlDynamicFields = sqlxmlDynamicFields;
	}
	public List<DynamicFields> getApprovedPriceListDynList() {
		return ApprovedPriceListDynList;
	}
	public void setApprovedPriceListDynList(
			List<DynamicFields> approvedPriceListDynList) {
		ApprovedPriceListDynList = approvedPriceListDynList;
	}
	public String getSizeRange() {
		return sizeRange;
	}
	public void setSizeRange(String sizeRange) {
		this.sizeRange = sizeRange;
	}
	public List<ApprovedPriceList> getApprovedPriceDetailList() {
		return approvedPriceDetailList;
	}
	public void setApprovedPriceDetailList(
			List<ApprovedPriceList> approvedPriceDetailList) {
		this.approvedPriceDetailList = approvedPriceDetailList;
	}
	public Map<String, String> getAplDetMap() {
		return aplDetMap;
	}
	public void setAplDetMap(Map<String, String> aplDetMap) {
		this.aplDetMap = aplDetMap;
	}
	public String getSizeGrid() {
		return sizeGrid;
	}
	public void setSizeGrid(String sizeGrid) {
		this.sizeGrid = sizeGrid;
	}
	public List<List<ApprovedPriceList>> getApRowList() {
		return apRowList;
	}
	public void setApRowList(List<List<ApprovedPriceList>> apRowList) {
		this.apRowList = apRowList;
	}
	
	
	
	
	
	
	
}
