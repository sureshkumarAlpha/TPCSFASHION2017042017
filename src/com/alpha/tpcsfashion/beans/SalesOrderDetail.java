package com.alpha.tpcsfashion.beans;

public class SalesOrderDetail {	
	private int companyId;
	private int yearId;
	private int locationId;
	private int soId;
	private int soDetId;
	private String soDate;
	private String soNo;
	private int matId;
	private String matCode; 
	private String matName;
	private String uom;
	private String quantity;
	private String priceFCY;
	private String priceLCY;
	private String discountPercent;	
	private String discountFCY;	
	private String valueFCY;
	private String valueLCY;
	private String expectedDate;
	private String remarks;
	private String matDesc;
	private String groupName;
	private int groupId;
	private String currency;
	private int variantId;
	private String variantName;
	private float	edPercentage;
	private float	ecessPercentage;
	private float	shecessPercentage;
	private float	vat;
	private float	cst;
	private float	service;
	private String materialDesc;
	private int slNo;
	private float priceFcy;
	private float valBefDis;
	
	private String materialImage;
	private int taxGroupId;
	private float	edFcy;
	private float	ecessFcy;
	private float	shecessFcy;
	private float	vatFcy;
	private float	cstFcy;
	private float	serviceFcy;
	private float valaftDis;
	private int roundOffRate;
	private int roundOffQty;
	private int roundOffValue;
	
	
	
	
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
	public float getValaftDis() {
		return valaftDis;
	}
	public void setValaftDis(float valaftDis) {
		this.valaftDis = valaftDis;
	}
	public float getEdFcy() {
		return edFcy;
	}
	public void setEdFcy(float edFcy) {
		this.edFcy = edFcy;
	}
	public float getEcessFcy() {
		return ecessFcy;
	}
	public void setEcessFcy(float ecessFcy) {
		this.ecessFcy = ecessFcy;
	}
	public float getShecessFcy() {
		return shecessFcy;
	}
	public void setShecessFcy(float shecessFcy) {
		this.shecessFcy = shecessFcy;
	}
	public float getVatFcy() {
		return vatFcy;
	}
	public void setVatFcy(float vatFcy) {
		this.vatFcy = vatFcy;
	}
	public float getCstFcy() {
		return cstFcy;
	}
	public void setCstFcy(float cstFcy) {
		this.cstFcy = cstFcy;
	}
	public float getServiceFcy() {
		return serviceFcy;
	}
	public void setServiceFcy(float serviceFcy) {
		this.serviceFcy = serviceFcy;
	}
	public int getTaxGroupId() {
		return taxGroupId;
	}
	public void setTaxGroupId(int taxGroupId) {
		this.taxGroupId = taxGroupId;
	}
	public String getSoNo() {
		return soNo;
	}
	public void setSoNo(String soNo) {
		this.soNo = soNo;
	}
	public float getPriceFcy() {
		return priceFcy;
	}
	public void setPriceFcy(float priceFcy) {
		this.priceFcy = priceFcy;
	}
	public float getValBefDis() {
		return valBefDis;
	}
	public void setValBefDis(float valBefDis) {
		this.valBefDis = valBefDis;
	}
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public String getMaterialDesc() {
		return materialDesc;
	}
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}
	public float getEdPercentage() {
		return edPercentage;
	}
	public void setEdPercentage(float edPercentage) {
		this.edPercentage = edPercentage;
	}
	public float getEcessPercentage() {
		return ecessPercentage;
	}
	public void setEcessPercentage(float ecessPercentage) {
		this.ecessPercentage = ecessPercentage;
	}
	public float getShecessPercentage() {
		return shecessPercentage;
	}
	public void setShecessPercentage(float shecessPercentage) {
		this.shecessPercentage = shecessPercentage;
	}
	public float getVat() {
		return vat;
	}
	public void setVat(float vat) {
		this.vat = vat;
	}
	public float getCst() {
		return cst;
	}
	public void setCst(float cst) {
		this.cst = cst;
	}
	public float getService() {
		return service;
	}
	public void setService(float service) {
		this.service = service;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public int getYearId() {
		return yearId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setSoId(int soId) {
		this.soId = soId;
	}
	public int getSoId() {
		return soId;
	}
	public void setSoDetId(int soDetId) {
		this.soDetId = soDetId;
	}
	public int getSoDetId() {
		return soDetId;
	}
	public void setSoDate(String soDate) {
		this.soDate = soDate;
	}
	public String getSoDate() {
		return soDate;
	}
	public void setMatId(int matId) {
		this.matId = matId;
	}
	public int getMatId() {
		return matId;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getUom() {
		return uom;
	}
//	public void setQuantity(float quantity) {
//		this.quantity = quantity;
//	}
//	public float getQuantity() {
//		return quantity;
//	}
//	public void setPriceFCY(float priceFCY) {
//		this.priceFCY = priceFCY;
//	}
//	public float getPriceFCY() {
//		return priceFCY;
//	}
//	public void setPriceLCY(float priceLCY) {
//		this.priceLCY = priceLCY;
//	}
//	public float getPriceLCY() {
//		return priceLCY;
//	}
//	public void setDiscountPercent(float discountPercent) {
//		this.discountPercent = discountPercent;
//	}
//	public float getDiscountPercent() {
//		return discountPercent;
//	}
//	public void setDiscountFCY(float discountFCY) {
//		this.discountFCY = discountFCY;
//	}
//	public float getDiscountFCY() {
//		return discountFCY;
//	}
//	public void setValueFCY(float valueFCY) {
//		this.valueFCY = valueFCY;
//	}
//	public float getValueFCY() {
//		return valueFCY;
//	}
//	public void setValueLCY(float valueLCY) {
//		this.valueLCY = valueLCY;
//	}
//	public float getValueLCY() {
//		return valueLCY;
//	}
	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}
	public String getExpectedDate() {
		return expectedDate;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarks() {
		return remarks;
	}

	public void setMatDesc(String matDesc) {
		this.matDesc = matDesc;
	}
	public String getMatDesc() {
		return matDesc;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrency() {
		return currency;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}
	public String getMatCode() {
		return matCode;
	}
	public void setMatName(String matName) {
		this.matName = matName;
	}
	public String getMatName() {
		return matName;
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
	public String getMaterialImage() {
		return materialImage;
	}
	public void setMaterialImage(String materialImage) {
		this.materialImage = materialImage;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPriceFCY() {
		return priceFCY;
	}
	public void setPriceFCY(String priceFCY) {
		this.priceFCY = priceFCY;
	}
	public String getPriceLCY() {
		return priceLCY;
	}
	public void setPriceLCY(String priceLCY) {
		this.priceLCY = priceLCY;
	}
	public String getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}
	public String getDiscountFCY() {
		return discountFCY;
	}
	public void setDiscountFCY(String discountFCY) {
		this.discountFCY = discountFCY;
	}
	public String getValueFCY() {
		return valueFCY;
	}
	public void setValueFCY(String valueFCY) {
		this.valueFCY = valueFCY;
	}
	public String getValueLCY() {
		return valueLCY;
	}
	public void setValueLCY(String valueLCY) {
		this.valueLCY = valueLCY;
	}

}
