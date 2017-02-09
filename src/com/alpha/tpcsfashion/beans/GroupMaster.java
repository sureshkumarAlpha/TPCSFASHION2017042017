package com.alpha.tpcsfashion.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

public class GroupMaster extends SerialException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int groupId;
	private String grouptype;
	private int grouptypeid;
	private String groupcode;
	private String groupname;
	private String shortname;
	private int subgroupid;
	private int itemtracking;
	private int barcodetracking;
	private int groupUnder;
	private String editmode;
	private int groupTypeId;
	private int groupUnderId;
	private String groupUnderName;
	private boolean inserted;
	private boolean groupExists;
	private int status;
	private String statusName;
	
	private String parantGroupTypeName;
	private int parantGroupTypeId;
	private int seqNo;
	private boolean grouptypeExists;
	
	private int lotNoTracking;
	private int dateExpiry;
	private int negativeStock;
	private int issueWithIO;
	private String taxCategory;
	private List<Map<String,String>> itemGroupMapList;
	private String pageFieldName;
	private String name;
	private int id;
	
	private int colorApplicable;
	private int sizeApplicable;
	private int stockReservation;
	private int inspectionRequired;
	private int barcodeRequired;
	
	
	

	public List<Map<String, String>> getItemGroupMapList() {
		return itemGroupMapList;
	}
	public void setItemGroupMapList(List<Map<String, String>> itemGroupMapList) {
		this.itemGroupMapList = itemGroupMapList;
	}
	public String getTaxCategory() {
		return taxCategory;
	}
	public void setTaxCategory(String taxCategory) {
		this.taxCategory = taxCategory;
	}
	public int getLotNoTracking() {
		return lotNoTracking;
	}
	public void setLotNoTracking(int lotNoTracking) {
		this.lotNoTracking = lotNoTracking;
	}
	public int getDateExpiry() {
		return dateExpiry;
	}
	public void setDateExpiry(int dateExpiry) {
		this.dateExpiry = dateExpiry;
	}
	public int getNegativeStock() {
		return negativeStock;
	}
	public void setNegativeStock(int negativeStock) {
		this.negativeStock = negativeStock;
	}
	public int getIssueWithIO() {
		return issueWithIO;
	}
	public void setIssueWithIO(int issueWithIO) {
		this.issueWithIO = issueWithIO;
	}
	public boolean isGrouptypeExists() {
		return grouptypeExists;
	}
	public void setGrouptypeExists(boolean grouptypeExists) {
		this.grouptypeExists = grouptypeExists;
	}
	public String getParantGroupTypeName() {
		return parantGroupTypeName;
	}
	public void setParantGroupTypeName(String parantGroupTypeName) {
		this.parantGroupTypeName = parantGroupTypeName;
	}
	public int getParantGroupTypeId() {
		return parantGroupTypeId;
	}
	public void setParantGroupTypeId(int parantGroupTypeId) {
		this.parantGroupTypeId = parantGroupTypeId;
	}
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	public boolean isGroupExists() {
		return groupExists;
	}
	public void setGroupExists(boolean groupExists) {
		this.groupExists = groupExists;
	}
	public boolean isInserted() {
		return inserted;
	}
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}
	private String sqlxmlDynamicFields;
	private List<DynamicFields> orderDynList = new ArrayList<DynamicFields>();


	public List<DynamicFields> getOrderDynList() {
		return orderDynList;
	}
	public void setOrderDynList(List<DynamicFields> orderDynList) {
		this.orderDynList = orderDynList;
	}
	public String getSqlxmlDynamicFields() {
		return sqlxmlDynamicFields;
	}
	public void setSqlxmlDynamicFields(String sqlxmlDynamicFields) {
		this.sqlxmlDynamicFields = sqlxmlDynamicFields;
	}
	public int getGroupUnderId() {
		return groupUnderId;
	}
	public void setGroupUnderId(int groupUnderId) {
		this.groupUnderId = groupUnderId;
	}
	public String getGroupUnderName() {
		return groupUnderName;
	}
	public void setGroupUnderName(String groupUnderName) {
		this.groupUnderName = groupUnderName;
	}
	public int getGroupTypeId() {
		return groupTypeId;
	}
	public void setGroupTypeId(int groupTypeId) {
		this.groupTypeId = groupTypeId;
	}
	public String getEditmode() {
		return editmode;
	}
	public void setEditmode(String editmode) {
		this.editmode = editmode;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGrouptype() {
		return grouptype;
	}
	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}
	public String getGroupcode() {
		return groupcode;
	}
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getShortnmae() {
		return shortname;
	}
	public void setShortnmae(String shortname) {
		this.shortname = shortname;
	}

	public int getItemtracking() {
		return itemtracking;
	}
	public void setItemtracking(int itemtracking) {
		this.itemtracking = itemtracking;
	}
	public int getBarcodetracking() {
		return barcodetracking;
	}
	public void setBarcodetracking(int barcodetracking) {
		this.barcodetracking = barcodetracking;
	}
	public int getGrouptypeid() {
		return grouptypeid;
	}
	public void setGrouptypeid(int grouptypeid) {
		this.grouptypeid = grouptypeid;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public int getSubgroupid() {
		return subgroupid;
	}
	public void setSubgroupid(int subgroupid) {
		this.subgroupid = subgroupid;
	}
	public int getGroupUnder() {
		return groupUnder;
	}
	public void setGroupUnder(int groupUnder) {
		this.groupUnder = groupUnder;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getPageFieldName() {
		return pageFieldName;
	}
	public void setPageFieldName(String pageFieldName) {
		this.pageFieldName = pageFieldName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getColorApplicable() {
		return colorApplicable;
	}
	public void setColorApplicable(int colorApplicable) {
		this.colorApplicable = colorApplicable;
	}
	public int getSizeApplicable() {
		return sizeApplicable;
	}
	public void setSizeApplicable(int sizeApplicable) {
		this.sizeApplicable = sizeApplicable;
	}
	public int getStockReservation() {
		return stockReservation;
	}
	public void setStockReservation(int stockReservation) {
		this.stockReservation = stockReservation;
	}
	public int getInspectionRequired() {
		return inspectionRequired;
	}
	public void setInspectionRequired(int inspectionRequired) {
		this.inspectionRequired = inspectionRequired;
	}
	public int getBarcodeRequired() {
		return barcodeRequired;
	}
	public void setBarcodeRequired(int barcodeRequired) {
		this.barcodeRequired = barcodeRequired;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}

