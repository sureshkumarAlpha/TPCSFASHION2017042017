package com.alpha.tpcsfashion.beans;

import java.util.List;

public class PurchaseOrderDetail 
{
	private int	poDetId;
	private int	slNo;
	private int	itemId;
	private String itemName;
	private int	variantId;
	private String variantName;
	private int	sizeRangeId;
	private String sizeRangeName;
	private int poId;
	private int uomId;
	private String uom;
	private String sizeRange;
	private String otherSpec;
	private String qty;
	private String price;
	private String qtyUom;
	private String priceFcy;
	private String valueFcy;
	private String remark1;
	private String remark2;
	private String remark3;
	private String requiredDate;
	private String remark;
	private int taxGroupId;
	private String discountPercent;

	private List<Size> sizeList;
	private List<Integer> sizeIdList;
	private boolean sizeExists;
	private String sizeData;
	private String sizeTotalQty;
	private String cartonData;
	private String cortanLocIds;
	private int cortanLocCnt;
	private boolean cortanExists;
	private boolean poSizeExists;
	private String sizeTotalPrice;
	
	
	

	public String getSizeTotalPrice() {
		return sizeTotalPrice;
	}
	public void setSizeTotalPrice(String sizeTotalPrice) {
		this.sizeTotalPrice = sizeTotalPrice;
	}
	public void setPoSizeExists(boolean poSizeExists) {
		this.poSizeExists = poSizeExists;
	}
	public boolean isPoSizeExists() {
		return poSizeExists;
	}
	public String getSizeRange() {
		return sizeRange;
	}
	public void setSizeRange(String sizeRange) {
		this.sizeRange = sizeRange;
	}
	public int getUomId() {
		return uomId;
	}
	public void setUomId(int uomId) {
		this.uomId = uomId;
	}
	public int getPoId() {
		return poId;
	}
	public void setPoId(int poId) {
		this.poId = poId;
	}
	public int getSizeRangeId() {
		return sizeRangeId;
	}
	public void setSizeRangeId(int sizeRangeId) {
		this.sizeRangeId = sizeRangeId;
	}
	public String getSizeRangeName() {
		return sizeRangeName;
	}
	public void setSizeRangeName(String sizeRangeName) {
		this.sizeRangeName = sizeRangeName;
	}
	public int getTaxGroupId() {
		return taxGroupId;
	}
	public void setTaxGroupId(int taxGroupId) {
		this.taxGroupId = taxGroupId;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getPoDetId() {
		return poDetId;
	}
	public void setPoDetId(int poDetId) {
		this.poDetId = poDetId;
	}
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getOtherSpec() {
		return otherSpec;
	}
	public void setOtherSpec(String otherSpec) {
		this.otherSpec = otherSpec;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
 
	public String getQtyUom() {
		return qtyUom;
	}
	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}
 
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPriceFcy() {
		return priceFcy;
	}
	public void setPriceFcy(String priceFcy) {
		this.priceFcy = priceFcy;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public String getRemark3() {
		return remark3;
	}
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	public String getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(String requiredDate) {
		this.requiredDate = requiredDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getSizeData() {
		return sizeData;
	}
	public void setSizeData(String sizeData) {
		this.sizeData = sizeData;
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
	public String getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}
	public String getValueFcy() {
		return valueFcy;
	}
	public void setValueFcy(String valueFcy) {
		this.valueFcy = valueFcy;
	}


}
