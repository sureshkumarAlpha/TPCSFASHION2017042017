package com.alpha.tpcsfashion.beans;

import java.util.List;
import java.util.Map;

import com.alpha.tpcsfashion.beans.SizeRange;

public class SizeMapping {

	private String scheduleNo;
	private int sizeScheduleId;
	private String remark;
	private int isActive;
	
	private int sizeScheduleDetId;
	private int prodSizeRangeId;
	private String prodSizeRangeName;
	private int prodSizeId;
	private String prodSizeName;
	private int rmSizeRangeId;
	private String rmSizeRangeName;
	private int rmSizeId;
	private String rmSizeName;
	
	private int sizeRangeId;
	private String sizeRangeName;
	
	private String mode;
	private String sizeData;
	private String detailMode;
	
	private List<SizeMapping> smappingsizeList;
	private List<String> sizeList;
	private List<SizeRange> sizeDList;
	private List<Map<String,String>> sizeMapList;
	private List<String> sizeHeaderList;
	private List<SizeRange> sizeNameHeaderList;
	private boolean isDetailOnly;
	private List<Map<String,String>> smappingListMap;
	private List<Map<String,String>> sizeDataMapList;
	private List<String> sizeIdList;
	private SizeRange sizeRange; 
	private String valWithId; 
	private List<SizeMapping> sizeMappingDetailList;
	private List<List<SizeMapping>> apRowList;
	private String autoCompleteIdNameString;
	private int sizeRangeGridid;
	private String sizeGrid;
	private String EditDetailsID;
	private String rmSizeRangeIds;
	private String sizeId;
	
	
	
	
	public String getSizeId() {
		return sizeId;
	}

	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}

	public String getRmSizeRangeIds() {
		return rmSizeRangeIds;
	}

	public void setRmSizeRangeIds(String rmSizeRangeIds) {
		this.rmSizeRangeIds = rmSizeRangeIds;
	}

	public String getEditDetailsID() {
		return EditDetailsID;
	}

	public void setEditDetailsID(String editDetailsID) {
		EditDetailsID = editDetailsID;
	}

	public String getSizeGrid() {
		return sizeGrid;
	}

	public void setSizeGrid(String sizeGrid) {
		this.sizeGrid = sizeGrid;
	}

	public int getSizeRangeGridid() {
		return sizeRangeGridid;
	}

	public void setSizeRangeGridid(int sizeRangeGridid) {
		this.sizeRangeGridid = sizeRangeGridid;
	}

	public String getAutoCompleteIdNameString() {
		return autoCompleteIdNameString;
	}

	public void setAutoCompleteIdNameString(String autoCompleteIdNameString) {
		this.autoCompleteIdNameString = autoCompleteIdNameString;
	}

	public List<List<SizeMapping>> getApRowList() {
		return apRowList;
	}

	public void setApRowList(List<List<SizeMapping>> apRowList) {
		this.apRowList = apRowList;
	}

	public List<SizeMapping> getSizeMappingDetailList() {
		return sizeMappingDetailList;
	}

	public void setSizeMappingDetailList(List<SizeMapping> sizeMappingDetailList) {
		this.sizeMappingDetailList = sizeMappingDetailList;
	}

	public String getValWithId() {
		return valWithId;
	}

	public void setValWithId(String valWithId) {
		this.valWithId = valWithId;
	}

	public SizeRange getSizeRange() {
		return sizeRange;
	}

	public void setSizeRange(SizeRange sizeRange) {
		this.sizeRange = sizeRange;
	}

	public List<String> getSizeIdList() {
		return sizeIdList;
	}

	public void setSizeIdList(List<String> sizeIdList) {
		this.sizeIdList = sizeIdList;
	}

	public String getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(String scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public int getSizeScheduleId() {
		return sizeScheduleId;
	}

	public void setSizeScheduleId(int sizeScheduleId) {
		this.sizeScheduleId = sizeScheduleId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getSizeScheduleDetId() {
		return sizeScheduleDetId;
	}

	public void setSizeScheduleDetId(int sizeScheduleDetId) {
		this.sizeScheduleDetId = sizeScheduleDetId;
	}

	public int getProdSizeRangeId() {
		return prodSizeRangeId;
	}

	public void setProdSizeRangeId(int prodSizeRangeId) {
		this.prodSizeRangeId = prodSizeRangeId;
	}

	public String getProdSizeRangeName() {
		return prodSizeRangeName;
	}

	public void setProdSizeRangeName(String prodSizeRangeName) {
		this.prodSizeRangeName = prodSizeRangeName;
	}

	public int getProdSizeId() {
		return prodSizeId;
	}

	public void setProdSizeId(int prodSizeId) {
		this.prodSizeId = prodSizeId;
	}

	public String getProdSizeName() {
		return prodSizeName;
	}

	public void setProdSizeName(String prodSizeName) {
		this.prodSizeName = prodSizeName;
	}

	public int getRmSizeRangeId() {
		return rmSizeRangeId;
	}

	public void setRmSizeRangeId(int rmSizeRangeId) {
		this.rmSizeRangeId = rmSizeRangeId;
	}

	public String getRmSizeRangeName() {
		return rmSizeRangeName;
	}

	public void setRmSizeRangeName(String rmSizeRangeName) {
		this.rmSizeRangeName = rmSizeRangeName;
	}

	public int getRmSizeId() {
		return rmSizeId;
	}

	public void setRmSizeId(int rmSizeId) {
		this.rmSizeId = rmSizeId;
	}

	public String getRmSizeName() {
		return rmSizeName;
	}

	public void setRmSizeName(String rmSizeName) {
		this.rmSizeName = rmSizeName;
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

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	

	public String getSizeData() {
		return sizeData;
	}

	public void setSizeData(String sizeData) {
		this.sizeData = sizeData;
	}

	public String getDetailMode() {
		return detailMode;
	}

	public void setDetailMode(String detailMode) {
		this.detailMode = detailMode;
	}

	public List<SizeMapping> getSmappingsizeList() {
		return smappingsizeList;
	}

	public void setSmappingsizeList(List<SizeMapping> smappingsizeList) {
		this.smappingsizeList = smappingsizeList;
	}

	public List<String> getSizeList() {
		return sizeList;
	}

	public void setSizeList(List<String> sizeList) {
		this.sizeList = sizeList;
	}

	public List<SizeRange> getSizeDList() {
		return sizeDList;
	}

	public void setSizeDList(List<SizeRange> sizeDList) {
		this.sizeDList = sizeDList;
	}

	public List<Map<String, String>> getSizeMapList() {
		return sizeMapList;
	}

	public void setSizeMapList(List<Map<String, String>> sizeMapList) {
		this.sizeMapList = sizeMapList;
	}

	public List<String> getSizeHeaderList() {
		return sizeHeaderList;
	}

	public void setSizeHeaderList(List<String> sizeHeaderList) {
		this.sizeHeaderList = sizeHeaderList;
	}

	public List<SizeRange> getSizeNameHeaderList() {
		return sizeNameHeaderList;
	}

	public void setSizeNameHeaderList(List<SizeRange> sizeNameHeaderList) {
		this.sizeNameHeaderList = sizeNameHeaderList;
	}

	public boolean isDetailOnly() {
		return isDetailOnly;
	}

	public void setDetailOnly(boolean isDetailOnly) {
		this.isDetailOnly = isDetailOnly;
	}

	public List<Map<String, String>> getSmappingListMap() {
		return smappingListMap;
	}

	public void setSmappingListMap(List<Map<String, String>> smappingListMap) {
		this.smappingListMap = smappingListMap;
	}

	public List<Map<String, String>> getSizeDataMapList() {
		return sizeDataMapList;
	}

	public void setSizeDataMapList(List<Map<String, String>> sizeDataMapList) {
		this.sizeDataMapList = sizeDataMapList;
	}
	
	
	
	
	
	
	
}
