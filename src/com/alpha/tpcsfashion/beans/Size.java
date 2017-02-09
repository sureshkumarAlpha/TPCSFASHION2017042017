package com.alpha.tpcsfashion.beans;

import java.util.List;

public class Size {
	private int sizeId;
	private String sizeName;
	private String qty;
	private String shippedQty;
	private String rate;
	private int slNo;
	private int isActive;
	private List<Size> sizeList;
	
	public int getSizeId() {
		return sizeId;
	}
	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getShippedQty() {
		return shippedQty;
	}
	public void setShippedQty(String shippedQty) {
		this.shippedQty = shippedQty;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public List<Size> getSizeList() {
		return sizeList;
	}
	public void setSizeList(List<Size> sizeList) {
		this.sizeList = sizeList;
	}
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}

	
}
