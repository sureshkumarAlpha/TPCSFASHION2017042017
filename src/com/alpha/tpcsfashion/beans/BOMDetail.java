package com.alpha.tpcsfashion.beans;

import java.util.List;
import java.util.Map;


public class BOMDetail{
	
	private int	companyId;
	private int	yearId;
	private int	locationId;
	private	int	bomId	;
	private	int	bomDetailId	;
	private	String	material	;
	private	int	materialId	;
	private	int	variantId	;
	private	String	variant	;
	private	String	UOM	;
	private	int	operationId	;
	private	int	uomId	;
	private	String	requiredQty	;
	private	float	price	;
	private	float	value	;
	private	int	wastagePer	;
	private	int	costingPer	;
	private	int	purchasePer	;
	private	int	issuePer	;
	private	int	supplierId	;
	private	String	usageArea	;
	private	String	remarks	;
	private	String	operation	;
	private int slNo;
	private String materialImage;
	private int taxGroupId;
	private int roundOffRate;
	private int roundOffQty;
	private int roundOffValue;
	private	String	component	;
	private	int	componentId	;
	private	String	supplier	;
	private int sizeSheduleId;
	private	String	sizeShedule	;
	private String compLength;
	private String compWidth;
	private String noOfParts;
	private String reqQty;
	private boolean inserted;
	private boolean deleted;
	private	String	mainUom	;
	private	String	heading	;
	private	String	part	;
	private List<BOMDetail> bomDetList;
	private	String	bomNo	;
	private	String	product	;
	private	String	customer	;	
	private int	customerId;
	private Map<String,String> bomDetDetMap;
	private	int	groupId	;
	private	String	groupName	;
	

	
	
	
	
	
	public Map<String, String> getBomDetDetMap() {
		return bomDetDetMap;
	}
	public void setBomDetDetMap(Map<String, String> bomDetDetMap) {
		this.bomDetDetMap = bomDetDetMap;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getBomNo() {
		return bomNo;
	}
	public void setBomNo(String bomNo) {
		this.bomNo = bomNo;
	}
	public List<BOMDetail> getBomDetList() {
		return bomDetList;
	}
	public void setBomDetList(List<BOMDetail> bomDetList) {
		this.bomDetList = bomDetList;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getMainUom() {
		return mainUom;
	}
	public void setMainUom(String mainUom) {
		this.mainUom = mainUom;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getSizeShedule() {
		return sizeShedule;
	}
	public void setSizeShedule(String sizeShedule) {
		this.sizeShedule = sizeShedule;
	}
	public String getReqQty() {
		return reqQty;
	}
	public void setReqQty(String reqQty) {
		this.reqQty = reqQty;
	}
	public int getSizeSheduleId() {
		return sizeSheduleId;
	}
	public void setSizeSheduleId(int sizeSheduleId) {
		this.sizeSheduleId = sizeSheduleId;
	}
	public String getCompLength() {
		return compLength;
	}
	public void setCompLength(String compLength) {
		this.compLength = compLength;
	}
	public String getCompWidth() {
		return compWidth;
	}
	public void setCompWidth(String compWidth) {
		this.compWidth = compWidth;
	}
	public String getNoOfParts() {
		return noOfParts;
	}
	public void setNoOfParts(String noOfParts) {
		this.noOfParts = noOfParts;
	}
	public boolean isInserted() {
		return inserted;
	}
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public int getComponentId() {
		return componentId;
	}
	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getUomId() {
		return uomId;
	}
	public void setUomId(int uomId) {
		this.uomId = uomId;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getVariant() {
		return variant;
	}
	public void setVariant(String variant) {
		this.variant = variant;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getBomId() {
		return bomId;
	}
	public void setBomId(int bomId) {
		this.bomId = bomId;
	}
	public int getBomDetailId() {
		return bomDetailId;
	}
	public void setBomDetailId(int bomDetailId) {
		this.bomDetailId = bomDetailId;
	}
	public int getMaterialId() {
		return materialId;
	}
	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}
	public int getVariantId() {
		return variantId;
	}
	public void setVariantId(int variantId) {
		this.variantId = variantId;
	}
	public String getUOM() {
		return UOM;
	}
	public void setUOM(String uOM) {
		UOM = uOM;
	}
	public int getOperationId() {
		return operationId;
	}
	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}
	
	public String getRequiredQty() {
		return requiredQty;
	}
	public void setRequiredQty(String requiredQty) {
		this.requiredQty = requiredQty;
	}
	public int getWastagePer() {
		return wastagePer;
	}
	public void setWastagePer(int wastagePer) {
		this.wastagePer = wastagePer;
	}
	public int getCostingPer() {
		return costingPer;
	}
	public void setCostingPer(int costingPer) {
		this.costingPer = costingPer;
	}
	public int getPurchasePer() {
		return purchasePer;
	}
	public void setPurchasePer(int purchasePer) {
		this.purchasePer = purchasePer;
	}
	public int getIssuePer() {
		return issuePer;
	}
	public void setIssuePer(int issuePer) {
		this.issuePer = issuePer;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getUsageArea() {
		return usageArea;
	}
	public void setUsageArea(String usageArea) {
		this.usageArea = usageArea;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public String getMaterialImage() {
		return materialImage;
	}
	public void setMaterialImage(String materialImage) {
		this.materialImage = materialImage;
	}
	public int getTaxGroupId() {
		return taxGroupId;
	}
	public void setTaxGroupId(int taxGroupId) {
		this.taxGroupId = taxGroupId;
	}
	public int getRoundOffRate() {
		return roundOffRate;
	}
	public void setRoundOffRate(int roundOffRate) {
		this.roundOffRate = roundOffRate;
	}
	public int getRoundOffQty() {
		return roundOffQty;
	}
	public void setRoundOffQty(int roundOffQty) {
		this.roundOffQty = roundOffQty;
	}
	public int getRoundOffValue() {
		return roundOffValue;
	}
	public void setRoundOffValue(int roundOffValue) {
		this.roundOffValue = roundOffValue;
	}
	
	
	
	
	
	
	
}