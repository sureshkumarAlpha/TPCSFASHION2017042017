
package com.alpha.tpcsfashion.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;
public class MaterialMaster extends SerialException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int matId;
	private String matCode;	
	private String shortName;
	private float stdCost;
	
	private String costCurrency;
	private String spec1;
	private String spec2;
	private String spec3;
	private String spec4;
	private String spec5;	
	private String spec6;
	private String spec7;
	private String spec8;
	private String spec9;
	private String spec10;
	private String bomUom;
	private String purchaseUOM;
	private String stockKeepingUnit;
	private String purchaseUnit;
	private String bomUnit;
	private int leadTime;
	private float minLevel;
	private float maxLevel;
	private float minOrderQty;
	private int negativeStock;
	private int issueWithIO;
	private String taxCategory;
	private int domestic;

	private int colorApplicable;
	private int sizeApplicable;
	private int stockReservation;
	private int inspectionRequired;
	private int barcodeRequired;
	
	
	public int getDomestic() {
		return domestic;
	}
	public void setDomestic(int domestic) {
		this.domestic = domestic;
	}
    
	private int importMat;
	private float excessAllowance ;	
	
	private float sellingPrice;
	private float costPrice;
	private int isKey;
	private String hostName;
	private String ipAddress;
	private int enteredBy;
	private String enteredOn;
	private int updatedBy;
	private String updatedOn;
	private int status;
	private String lastditDate;
	private String creationDate;
	private String groupName;
	private String groupType;
	private int groupTypeId;

	/*Images*/
	private String firstImageUrl;
	private String secondImageUrl;
	private String thirdImageUrl;
	private String fourthImageUrl;
	private String firstImageSuffix;
	private String secondImageSuffix;
	private String thirdImageSuffix;
	private String fourthImageSuffix;
	private String PdfOne;
	private String PdfTwo;
	private String PdfThree;
	private String PdfFour;
	private int trackInventory;
	private String inventoryAccountName;
	private int inventoryAccountId;
	private int sellMaterial;
	private int includeTax;
	private int taxGroupId;
	private int purchaseMaterial;
	private int purchaseAccountId;
	private String purchaseAccountName;
	private String primaryImageUrl;
	private int currencyId;
	private String currencyName;
	private int isActive;
	private boolean materialExists;
	private boolean UOMExists;
	private boolean inserted;
	private boolean isDeleted;
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	private List<Map<String,String>> materialMapList;
	private List<TaxGroup> taxGroupList;
	private int expiryApplicable;
	private String bomNumber;
	private String defaultSizeRange;
	private int defaultLeatherId;
	private int defaultColourId;
	private String buyerStyleNo;
	private String sampleStyleNo;
	private String abcClass;
	private String xyzClass;
	private String defaultColourName;
	

	private String basicUnit ;
	private String salesDesc ;
	private String purchaseDesc ;
	private int groupId;

	private String pageFieldName;
	private String name;
	private int id;

	
	private String stockLocation;
	private String binNumber;
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	private String mode;
	private String urlType;
	private String url;
	private String attachFileName;
	List<MaterialMaster> attachments;
	List<MaterialMaster> images;
	private String picPath;

	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	private float reorderLevel;
	private float reorderQty;
	
	public String getTaxCategory() {
		return taxCategory;
	}
	public void setTaxCategory(String taxCategory) {
		this.taxCategory = taxCategory;
	}

	private int itemTrackingApplicable;
	public int getIssueWithIO() {
		return issueWithIO;
	}
	public void setIssueWithIO(int issueWithIO) {
		this.issueWithIO = issueWithIO;
	}
	public String getAbcClass() {
		return abcClass;
	}
	public void setAbcClass(String abcClass) {
		this.abcClass = abcClass;
	}
	public String getXyzClass() {
		return xyzClass;
	}
	public void setXyzClass(String xyzClass) {
		this.xyzClass = xyzClass;
	}

	private String routeRef;
	
	public int getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(int leadTime) {
		this.leadTime = leadTime;
	}
	public float getMinLevel() {
		return minLevel;
	}
	public void setMinLevel(float minLevel) {
		this.minLevel = minLevel;
	}
	public float getMaxLevel() {
		return maxLevel;
	}
	public void setMaxLevel(float maxLevel) {
		this.maxLevel = maxLevel;
	}
	public float getMinOrderQty() {
		return minOrderQty;
	}
	public void setMinOrderQty(float minOrderQty) {
		this.minOrderQty = minOrderQty;
	}
	public int getNegativeStock() {
		return negativeStock;
	}
	public void setNegativeStock(int negativeStock) {
		this.negativeStock = negativeStock;
	}
	public int getItemTrackingApplicable() {
		return itemTrackingApplicable;
	}
	public void setItemTrackingApplicable(int itemTrackingApplicable) {
		this.itemTrackingApplicable = itemTrackingApplicable;
	}
	public int getExpiryApplicable() {
		return expiryApplicable;
	}
	public void setExpiryApplicable(int expiryApplicable) {
		this.expiryApplicable = expiryApplicable;
	}
	public String getBomNumber() {
		return bomNumber;
	}
	public void setBomNumber(String bomNumber) {
		this.bomNumber = bomNumber;
	}
	public String getDefaultSizeRange() {
		return defaultSizeRange;
	}
	public void setDefaultSizeRange(String defaultSizeRange) {
		this.defaultSizeRange = defaultSizeRange;
	}
	public int getDefaultLeatherId() {
		return defaultLeatherId;
	}
	public void setDefaultLeatherId(int defaultLeatherId) {
		this.defaultLeatherId = defaultLeatherId;
	}
	public int getDefaultColourId() {
		return defaultColourId;
	}
	public void setDefaultColourId(int defaultColourId) {
		this.defaultColourId = defaultColourId;
	}
	public String getBuyerStyleNo() {
		return buyerStyleNo;
	}
	public void setBuyerStyleNo(String buyerStyleNo) {
		this.buyerStyleNo = buyerStyleNo;
	}
	public String getSampleStyleNo() {
		return sampleStyleNo;
	}
	public void setSampleStyleNo(String sampleStyleNo) {
		this.sampleStyleNo = sampleStyleNo;
	}
	public String getRouteRef() {
		return routeRef;
	}
	public void setRouteRef(String routeRef) {
		this.routeRef = routeRef;
	}
	public String getStockKeepingUnit() {
		return stockKeepingUnit;
	}
	public void setStockKeepingUnit(String stockKeepingUnit) {
		this.stockKeepingUnit = stockKeepingUnit;
	}
	public String getPurchaseUnit() {
		return purchaseUnit;
	}
	public void setPurchaseUnit(String purchaseUnit) {
		this.purchaseUnit = purchaseUnit;
	}
	public String getBomUnit() {
		return bomUnit;
	}
	public void setBomUnit(String bomUnit) {
		this.bomUnit = bomUnit;
	}
	
	public String getBomUom() {
		return bomUom;
	}
	public void setBomUom(String bomUom) {
		this.bomUom = bomUom;
	}
	public String getPurchaseUOM() {
		return purchaseUOM;
	}
	public void setPurchaseUOM(String purchaseUOM) {
		this.purchaseUOM = purchaseUOM;
	}

	public float getReorderQty() {
		return reorderQty;
	}
	public void setReorderQty(float reorderQty) {
		this.reorderQty = reorderQty;
	}


	public boolean isInserted() {
		return inserted;
	}
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}
	public boolean isMaterialExists() {
		return materialExists;
	}
	public void setMaterialExists(boolean materialExists) {
		this.materialExists = materialExists;
	}
	public String getSalesDesc() {
		return salesDesc;
	}
	public void setSalesDesc(String salesDesc) {
		this.salesDesc = salesDesc;
	}
	public String getPurchaseDesc() {
		return purchaseDesc;
	}
	public void setPurchaseDesc(String purchaseDesc) {
		this.purchaseDesc = purchaseDesc;
	}
	public String getAttachFileName() {
		return attachFileName;
	}
	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}
	public String getUrlType() {
		return urlType;
	}
	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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

	private String sqlxmlDynamicFields;
	private List<DynamicFields> matDynList = new ArrayList<DynamicFields>();


	public String getSqlxmlDynamicFields() {
		return sqlxmlDynamicFields;
	}
	public void setSqlxmlDynamicFields(String sqlxmlDynamicFields) {
		this.sqlxmlDynamicFields = sqlxmlDynamicFields;
	}
	public List<DynamicFields> getMatDynList() {
		return matDynList;
	}
	public void setMatDynList(List<DynamicFields> matDynList) {
		this.matDynList = matDynList;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getFirstImageUrl() {
		return firstImageUrl;
	}
	public void setFirstImageUrl(String firstImageUrl) {
		this.firstImageUrl = firstImageUrl;
	}
	public String getSecondImageUrl() {
		return secondImageUrl;
	}
	public void setSecondImageUrl(String secondImageUrl) {
		this.secondImageUrl = secondImageUrl;
	}
	public String getThirdImageUrl() {
		return thirdImageUrl;
	}
	public void setThirdImageUrl(String thirdImageUrl) {
		this.thirdImageUrl = thirdImageUrl;
	}
	public String getFourthImageUrl() {
		return fourthImageUrl;
	}
	public void setFourthImageUrl(String fourthImageUrl) {
		this.fourthImageUrl = fourthImageUrl;
	}


	public int getMatId() {
		return matId;
	}
	public void setMatId(int matId) {
		this.matId = matId;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getSpec1() {
		return spec1;
	}
	public void setSpec1(String spec1) {
		this.spec1 = spec1;
	}
	public String getSpec2() {
		return spec2;
	}
	public void setSpec2(String spec2) {
		this.spec2 = spec2;
	}
	public String getSpec3() {
		return spec3;
	}
	public void setSpec3(String spec3) {
		this.spec3 = spec3;
	}
	public String getSpec4() {
		return spec4;
	}
	public void setSpec4(String spec4) {
		this.spec4 = spec4;
	}
	public String getSpec5() {
		return spec5;
	}
	public void setSpec5(String spec5) {
		this.spec5 = spec5;
	}
	public String getSpec6() {
		return spec6;
	}
	public void setSpec6(String spec6) {
		this.spec6 = spec6;
	}
	public String getSpec7() {
		return spec7;
	}
	public void setSpec7(String spec7) {
		this.spec7 = spec7;
	}
	public String getBasicUnit() {
		return basicUnit;
	}
	public void setBasicUnit(String basicUnit) {
		this.basicUnit = basicUnit;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getMatCode() {
		return matCode;
	}
	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}
	public String getStockLocation() {
		return stockLocation;
	}
	public void setStockLocation(String stockLocation) {
		this.stockLocation = stockLocation;
	}
	public String getBinNumber() {
		return binNumber;
	}
	public void setBinNumber(String binNumber) {
		this.binNumber = binNumber;
	}
	public float getReorderLevel() {
		return reorderLevel;
	}
	public void setReorderLevel(float reoderLevel) {
		this.reorderLevel = reoderLevel;
	}
	public float getExcessAllowance() {
		return excessAllowance;
	}
	public void setExcessAllowance(float excessAllowance) {
		this.excessAllowance = excessAllowance;
	}
	public int getImportMat() {
		return importMat;
	}
	public void setImportMat(int importMat) {
		this.importMat = importMat;
	}
	public float getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public float getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(float costPrice) {
		this.costPrice = costPrice;
	}
	public int getIsKey() {
		return isKey;
	}
	public void setIsKey(int isKey) {
		this.isKey = isKey;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public int getEnteredBy() {
		return enteredBy;
	}
	public void setEnteredBy(int enteredBy) {
		this.enteredBy = enteredBy;
	}
	public String getEnteredOn() {
		return enteredOn;
	}
	public void setEnteredOn(String enteredOn) {
		this.enteredOn = enteredOn;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
	public float getStdCost() {
		return stdCost;
	}
	public void setStdCost(float stdCost) {
		this.stdCost = stdCost;
	}
	public String getCostCurrency() {
		return costCurrency;
	}
	public void setCostCurrency(String costCurrency) {
		this.costCurrency = costCurrency;
	}
	public String getSpec8() {
		return spec8;
	}
	public void setSpec8(String spec8) {
		this.spec8 = spec8;
	}
	public String getSpec9() {
		return spec9;
	}
	public void setSpec9(String spec9) {
		this.spec9 = spec9;
	}
	public String getSpec10() {
		return spec10;
	}
	public void setSpec10(String spec10) {
		this.spec10 = spec10;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLastditDate() {
		return lastditDate;
	}
	public void setLastditDate(String lastditDate) {
		this.lastditDate = lastditDate;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}


	public String getFirstImageSuffix() {
		return firstImageSuffix;
	}
	public void setFirstImageSuffix(String firstImageSuffix) {
		this.firstImageSuffix = firstImageSuffix;
	}
	public String getSecondImageSuffix() {
		return secondImageSuffix;
	}
	public void setSecondImageSuffix(String secondImageSuffix) {
		this.secondImageSuffix = secondImageSuffix;
	}
	public String getThirdImageSuffix() {
		return thirdImageSuffix;
	}
	public void setThirdImageSuffix(String thirdImageSuffix) {
		this.thirdImageSuffix = thirdImageSuffix;
	}
	public String getFourthImageSuffix() {
		return fourthImageSuffix;
	}
	public void setFourthImageSuffix(String fourthImageSuffix) {
		this.fourthImageSuffix = fourthImageSuffix;
	}
	public String getPdfOne() {
		return PdfOne;
	}
	public void setPdfOne(String pdfOne) {
		PdfOne = pdfOne;
	}
	public String getPdfTwo() {
		return PdfTwo;
	}
	public void setPdfTwo(String pdfTwo) {
		PdfTwo = pdfTwo;
	}
	public String getPdfThree() {
		return PdfThree;
	}
	public void setPdfThree(String pdfThree) {
		PdfThree = pdfThree;
	}
	public String getPdfFour() {
		return PdfFour;
	}
	public void setPdfFour(String pdfFour) {
		PdfFour = pdfFour;
	}
	public int getGroupTypeId() {
		return groupTypeId;
	}
	public void setGroupTypeId(int groupTypeId) {
		this.groupTypeId = groupTypeId;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<MaterialMaster> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<MaterialMaster> attachments) {
		this.attachments = attachments;
	}
	public List<MaterialMaster> getImages() {
		return images;
	}
	public void setImages(List<MaterialMaster> images) {
		this.images = images;
	}
	public boolean isUOMExists() {
		return UOMExists;
	}
	public void setUOMExists(boolean uOMExists) {
		UOMExists = uOMExists;
	}
	public int getTrackInventory() {
		return trackInventory;
	}
	public void setTrackInventory(int trackInventory) {
		this.trackInventory = trackInventory;
	}
	public String getInventoryAccountName() {
		return inventoryAccountName;
	}
	public void setInventoryAccountName(String inventoryAccountName) {
		this.inventoryAccountName = inventoryAccountName;
	}
	public int getInventoryAccountId() {
		return inventoryAccountId;
	}
	public void setInventoryAccountId(int inventoryAccountId) {
		this.inventoryAccountId = inventoryAccountId;
	}
	public int getSellMaterial() {
		return sellMaterial;
	}
	public void setSellMaterial(int sellMaterial) {
		this.sellMaterial = sellMaterial;
	}
	public int getIncludeTax() {
		return includeTax;
	}
	public void setIncludeTax(int includeTax) {
		this.includeTax = includeTax;
	}
	public int getPurchaseMaterial() {
		return purchaseMaterial;
	}
	public void setPurchaseMaterial(int purchaseMaterial) {
		this.purchaseMaterial = purchaseMaterial;
	}
	public int getPurchaseAccountId() {
		return purchaseAccountId;
	}
	public void setPurchaseAccountId(int purchaseAccountId) {
		this.purchaseAccountId = purchaseAccountId;
	}
	public String getPurchaseAccountName() {
		return purchaseAccountName;
	}
	public void setPurchaseAccountName(String purchaseAccountName) {
		this.purchaseAccountName = purchaseAccountName;
	}
	
	public List<Map<String, String>> getMaterialMapList() {
		return materialMapList;
	}
	public void setMaterialMapList(List<Map<String, String>> materialMapList) {
		this.materialMapList = materialMapList;
	}
	public List<TaxGroup> getTaxGroupList() {
		return taxGroupList;
	}
	public void setTaxGroupList(List<TaxGroup> taxGroupList) {
		this.taxGroupList = taxGroupList;
	}
	public int getTaxGroupId() {
		return taxGroupId;
	}
	public void setTaxGroupId(int taxGroupId) {
		this.taxGroupId = taxGroupId;
	}
	public String getPrimaryImageUrl() {
		return primaryImageUrl;
	}
	public void setPrimaryImageUrl(String primaryImageUrl) {
		this.primaryImageUrl = primaryImageUrl;
	}
	public int getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getDefaultColourName() {
		return defaultColourName;
	}
	public void setDefaultColourName(String defaultColourName) {
		this.defaultColourName = defaultColourName;
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
