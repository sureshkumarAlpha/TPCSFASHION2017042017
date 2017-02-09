package com.alpha.tpcsfashion.beans;

import java.util.List;

public class IndentDetail {

	private 	int	indentDetId;
	private 	int	indentId;
	private 	String indentLineNo;
	private 	int	itemId;
	private 	String itemName;
	private 	String soItemName;

	private 	int	colourId;
	private 	String colourName;
	private 	String soColourName;
	private 	String	sizeRange;
	private 	String	soSizeRange;
	private 	int sizeRangeId;
	private 	String indentQty;

	private 	String requiredDate;
	private 	String soRequiredDate;

	private 	String	remark;
	
	private String uom;
	private String uomId;
	
	private String partyId;
	private String partyName;
	private String soPartyName;


private int groupId;
	private String groupName;
	private List<Size> sizeList;
	private List<Integer> sizeIdList;
	private boolean sizeExists;
	private boolean indsizeExists;
	private String sizeData;
	private String sizeTotalQty;
	private int slNo;
	private String soNo;
	private String soDate;
	private 	int	soDetId;
	
	
	
	
	public int getIndentId() {
		return indentId;
	}
	public void setIndentId(int indentId) {
		this.indentId = indentId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getSoItemName() {
		return soItemName;
	}
	public void setSoItemName(String soItemName) {
		this.soItemName = soItemName;
	}
	public String getSoColourName() {
		return soColourName;
	}
	public void setSoColourName(String soColourName) {
		this.soColourName = soColourName;
	}
	public String getSoSizeRange() {
		return soSizeRange;
	}
	public void setSoSizeRange(String soSizeRange) {
		this.soSizeRange = soSizeRange;
	}
	public String getSoRequiredDate() {
		return soRequiredDate;
	}
	public void setSoRequiredDate(String soRequiredDate) {
		this.soRequiredDate = soRequiredDate;
	}
	public String getSoPartyName() {
		return soPartyName;
	}
	public void setSoPartyName(String soPartyName) {
		this.soPartyName = soPartyName;
	}
	public String getSoDate() {
		return soDate;
	}
	public void setSoDate(String soDate) {
		this.soDate = soDate;
	}
	public String getSoNo() {
		return soNo;
	}
	public void setSoNo(String soNo) {
		this.soNo = soNo;
	}
	public int getSoDetId() {
		return soDetId;
	}
	public void setSoDetId(int soDetId) {
		this.soDetId = soDetId;
	}
	
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public String getUomId() {
		return uomId;
	}
	public void setUomId(String uomId) {
		this.uomId = uomId;
	}
	
	
	
	
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}


	public int getIndentDetId() {
		return indentDetId;
	}
	public void setIndentDetId(int indentDetId) {
		this.indentDetId = indentDetId;
	}
	

	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getColourName() {
		return colourName;
	}
	public void setColourName(String colourName) {
		this.colourName = colourName;
	}
	public String getSizeRange() {
		return sizeRange;
	}
	public void setSizeRange(String sizeRange) {
		this.sizeRange = sizeRange;
	}
	public int getSizeRangeId() {
		return sizeRangeId;
	}
	public void setSizeRangeId(int sizeRangeId) {
		this.sizeRangeId = sizeRangeId;
	}
	public String getIndentQty() {
		return indentQty;
	}
	public void setIndentQty(String indentQty) {
		this.indentQty = indentQty;
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
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	
	public String getIndentLineNo() {
		return indentLineNo;
	}
	public void setIndentLineNo(String indentLineNo) {
		this.indentLineNo = indentLineNo;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getColourId() {
		return colourId;
	}
	public void setColourId(int colourId) {
		this.colourId = colourId;
	}
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
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
	
	
	public boolean isIndsizeExists() {
		return indsizeExists;
	}
	public void setIndsizeExists(boolean indsizeExists) {
		this.indsizeExists = indsizeExists;
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

	
	
	

}
