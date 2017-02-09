package com.alpha.tpcsfashion.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SalesQuotationDetail {
private int		quoteId;
private int		quoteDetId;
private String	quoteDate;
private String	quoteNo;
private int		groupId;
private String	groupName;
private int		matId;
private String	materialName;
private String	materialCode;
private String	materialDesc;
private String	uom;
private String qty;
private String priceFcy;
//private float	priceLcy;
private String  priceLcy;
private String discountPercent;
private String discountFcy;
private String valueFcy;
private String valueLcy;
private String	requiredDate;
private String	remarks;
private float	valBefDis;
private float	disVal;
private float	valAftDis;
private float	edPercentage;
private float	ecessPercentage;
private float	shecessPercentage;
private float	vat;
private float	cst;
private float	service;
private int variantId;
private String variantName;
private String editedMode;

private String fieldValue;
private int		slNo;

private int taxGroupId;
private float totEDVal;
private float totECESSVal;
private float totSHECESSVal;
private float totVATVal;
private float totCSTVal;
private float totServiceTaxVal;
private String strTaxGroupIds;
private float	edFcy;
private float	ecessFcy;
private float	shecessFcy;
private float	vatFcy;
private float	cstFcy;
private float	serviceFcy;
private int roundOffRate;
private int roundOffQty;
private int roundOffValue;
private Map<String,String> mapDynmicDetField;
private String materialImage;





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
public String getQuoteNo() {
	return quoteNo;
}
public void setQuoteNo(String quoteNo) {
	this.quoteNo = quoteNo;
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
public float getTotEDVal() {
	return totEDVal;
}
public void setTotEDVal(float totEDVal) {
	this.totEDVal = totEDVal;
}
public float getTotECESSVal() {
	return totECESSVal;
}
public void setTotECESSVal(float totECESSVal) {
	this.totECESSVal = totECESSVal;
}
public float getTotSHECESSVal() {
	return totSHECESSVal;
}
public void setTotSHECESSVal(float totSHECESSVal) {
	this.totSHECESSVal = totSHECESSVal;
}
public float getTotVATVal() {
	return totVATVal;
}
public void setTotVATVal(float totVATVal) {
	this.totVATVal = totVATVal;
}
public float getTotCSTVal() {
	return totCSTVal;
}
public void setTotCSTVal(float totCSTVal) {
	this.totCSTVal = totCSTVal;
}
public float getTotServiceTaxVal() {
	return totServiceTaxVal;
}
public void setTotServiceTaxVal(float totServiceTaxVal) {
	this.totServiceTaxVal = totServiceTaxVal;
}
public String getStrTaxGroupIds() {
	return strTaxGroupIds;
}
public void setStrTaxGroupIds(String strTaxGroupIds) {
	this.strTaxGroupIds = strTaxGroupIds;
}
public int getSlNo() {
	return slNo;
}
public void setSlNo(int slNo) {
	this.slNo = slNo;
}
public String getFieldValue() {
	return fieldValue;
}
public void setFieldValue(String fieldValue) {
	this.fieldValue = fieldValue;
}
private List<DynamicFields> quotationDetailDynList = new ArrayList<DynamicFields>();


                                                                       
public List<DynamicFields> getQuotationDetailDynList() {
	return quotationDetailDynList;
}
public void setQuotationDetailDynList(List<DynamicFields> quotationDetailDynList) {
	this.quotationDetailDynList = quotationDetailDynList;
}
public String getMaterialDesc() {
	return materialDesc;
}
public void setMaterialDesc(String materialDesc) {
	this.materialDesc = materialDesc;
}
public String getEditedMode() {
	return editedMode;
}
public void setEditedMode(String editedMode) {
	this.editedMode = editedMode;
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
	
	public int getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}
	public int getQuoteDetId() {
		return quoteDetId;
	}
	public void setQuoteDetId(int quoteDetId) {
		this.quoteDetId = quoteDetId;
	}
	public String getQuoteDate() {
		return quoteDate;
	}
	public void setQuoteDate(String quoteDate) {
		this.quoteDate = quoteDate;
	}
	public int getMatId() {
		return matId;
	}
	public void setMatId(int matId) {
		this.matId = matId;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
//	public float getQty() {
//		return qty;
//	}
//	public void setQty(float qty) {
//		this.qty = qty;
//	}
//	public float getPriceFcy() {
//		return priceFcy;
//	}
//	public void setPriceFcy(float priceFcy) {
//		this.priceFcy = priceFcy;
//	}
//	public float getPriceLcy() {
//		return priceLcy;
//	}
//	public void setPriceLcy(float priceLcy) {
//		this.priceLcy = priceLcy;
//	}
//	public float getDiscountPercent() {
//		return discountPercent;
//	}
//	public void setDiscountPercent(float discountPercent) {
//		this.discountPercent = discountPercent;
//	}
//	public float getDiscountFcy() {
//		return discountFcy;
//	}
//	public void setDiscountFcy(float discountFcy) {
//		this.discountFcy = discountFcy;
//	}
//	public float getValueFcy() {
//		return valueFcy;
//	}
//	public void setValueFcy(float valueFcy) {
//		this.valueFcy = valueFcy;
//	}
//	public float getValueLcy() {
//		return valueLcy;
//	}
//	public void setValueLcy(float valueLcy) {
//		this.valueLcy = valueLcy;
//	}

	public String getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(String requiredDate) {
		this.requiredDate = requiredDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
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
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public float getValBefDis() {
		return valBefDis;
	}
	public void setValBefDis(float valBefDis) {
		this.valBefDis = valBefDis;
	}
	public float getDisVal() {
		return disVal;
	}
	public void setDisVal(float disVal) {
		this.disVal = disVal;
	}
	public float getValAftDis() {
		return valAftDis;
	}
	public void setValAftDis(float valAftDis) {
		this.valAftDis = valAftDis;
	}
	public Map<String, String> getMapDynmicDetField() {
		return mapDynmicDetField;
	}
	public void setMapDynmicDetField(Map<String, String> mapDynmicDetField) {
		this.mapDynmicDetField = mapDynmicDetField;
	}
	public String getMaterialImage() {
		return materialImage;
	}
	public void setMaterialImage(String materialImage) {
		this.materialImage = materialImage;
	}
	public String getPriceFcy() {
		return priceFcy;
	}
	public void setPriceFcy(String priceFcy) {
		this.priceFcy = priceFcy;
	}
	public String getPriceLcy() {
		return priceLcy;
	}
	public void setPriceLcy(String priceLcy) {
		this.priceLcy = priceLcy;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}
	public String getDiscountFcy() {
		return discountFcy;
	}
	public void setDiscountFcy(String discountFcy) {
		this.discountFcy = discountFcy;
	}
	public String getValueFcy() {
		return valueFcy;
	}
	public void setValueFcy(String valueFcy) {
		this.valueFcy = valueFcy;
	}
	public String getValueLcy() {
		return valueLcy;
	}
	public void setValueLcy(String valueLcy) {
		this.valueLcy = valueLcy;
	}

	
}