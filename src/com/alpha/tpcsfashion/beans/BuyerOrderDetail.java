package com.alpha.tpcsfashion.beans;

import java.util.List;

public class BuyerOrderDetail {

	private 	int	boDetId;
	private 	String	lineNo;
	private 	int	itemId;
	private 	String itemName;
	private 	String buyerStyleNo;
	private 	int	variantId;
	private 	String variantName;
	private 	String	sizeRange;
	private 	int sizeRangeId;
	private 	String qty;
	private 	String shippedQty;
	private 	String rate;
	private 	String rateFcy;
	private 	String requiredDate;
	private 	String possibleDate;
	private 	String	mrp;
	private 	String	barcode;
	private 	String	remark;
	
	private List<Size> sizeList;
	private List<Integer> sizeIdList;
	private boolean sizeExists;
	private boolean boSizeExists;
	private 	String sizeData;
	private String sizeTotalQty;
	private String cartonData;
	private String cortanLocIds;
	private int cortanLocCnt;
	private boolean cortanExists;
	
	public int getBoDetId() {
		return boDetId;
	}
	public void setBoDetId(int boDetId) {
		this.boDetId = boDetId;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
 
	public String getSizeRange() {
		return sizeRange;
	}
	public void setSizeRange(String sizeRange) {
		this.sizeRange = sizeRange;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getRateFcy() {
		return rateFcy;
	}
	public void setRateFcy(String rateFcy) {
		this.rateFcy = rateFcy;
	}
	public String getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(String requiredDate) {
		this.requiredDate = requiredDate;
	}
	public String getPossibleDate() {
		return possibleDate;
	}
	public void setPossibleDate(String possibleDate) {
		this.possibleDate = possibleDate;
	}
	public String getMrp() {
		return mrp;
	}
	public void setMrp(String mrp) {
		this.mrp = mrp;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getBuyerStyleNo() {
		return buyerStyleNo;
	}
	public void setBuyerStyleNo(String buyerStyleNo) {
		this.buyerStyleNo = buyerStyleNo;
	}
 
	public int getVariantId() {
		return variantId;
	}
	public void setVariantId(int variantId) {
		this.variantId = variantId;
	}
	public String getVariantName() {
		return variantName;
	}
	public void setVariantName(String variantName) {
		this.variantName = variantName;
	}
	public int getSizeRangeId() {
		return sizeRangeId;
	}
	public void setSizeRangeId(int sizeRangeId) {
		this.sizeRangeId = sizeRangeId;
	}
	public String getShippedQty() {
		return shippedQty;
	}
	public void setShippedQty(String shippedQty) {
		this.shippedQty = shippedQty;
	}
	public String getSizeData() {
		return sizeData;
	}
	public void setSizeData(String sizeData) {
		this.sizeData = sizeData;
	}
	public List<Size> getSizeList() {
		return sizeList;
	}
	public void setSizeList(List<Size> sizeList) {
		this.sizeList = sizeList;
	}
	public List<Integer> getSizeIdList() {
		return sizeIdList;
	}
	public void setSizeIdList(List<Integer> sizeIdList) {
		this.sizeIdList = sizeIdList;
	}
	public boolean isSizeExists() {
		return sizeExists;
	}
	public void setSizeExists(boolean sizeExists) {
		this.sizeExists = sizeExists;
	}
	public String getSizeTotalQty() {
		return sizeTotalQty;
	}
	public void setSizeTotalQty(String sizeTotalQty) {
		this.sizeTotalQty = sizeTotalQty;
	}
	public String getCartonData() {
		return cartonData;
	}
	public void setCartonData(String cartonData) {
		this.cartonData = cartonData;
	}
	public String getCortanLocIds() {
		return cortanLocIds;
	}
	public void setCortanLocIds(String cortanLocIds) {
		this.cortanLocIds = cortanLocIds;
	}
	public int getCortanLocCnt() {
		return cortanLocCnt;
	}
	public void setCortanLocCnt(int cortanLocCnt) {
		this.cortanLocCnt = cortanLocCnt;
	}
	public boolean isCortanExists() {
		return cortanExists;
	}
	public void setCortanExists(boolean cortanExists) {
		this.cortanExists = cortanExists;
	}
	public boolean isBoSizeExists() {
		return boSizeExists;
	}
	public void setBoSizeExists(boolean boSizeExists) {
		this.boSizeExists = boSizeExists;
	}
 	

}
