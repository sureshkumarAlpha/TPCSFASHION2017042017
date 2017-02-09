package com.alpha.tpcsfashion.beans;

import java.util.List;
import java.util.Map;

public class BankReceipt {
	
	private int subdocumentId;
	private String headerMode;
	private	int	finTrId;
	private	int	finTrDetId;
	private	String	finTrType;
	private	int	finTrNo;
	private	String	finTrDate	;
	private	String	currency	;
	private	int	currencyId	;
	private	float	exRate	;
	private	String	reference	;
	private	int	accountId;
	private	String	accountName	;
	private	int	depositId;
	private	String	depositName	;
	private	String	debit	;
	private	String credit	;
	private	String	deductionAmt	;
	private	int	deductionHeadId;
	private	String	deductionHeadName	;
	private	String	remarks	;
	private	String	mode	;
	private BankReceipt header;
	private int receivedFromId;
	private String receivedFromName;
	private int depositToId;
	private String depositToName;
//	private float amtReceived;
	private String amtReceived;
	private String receivedOn;
	private String billReference;
	private String billReferenceTrType;
	private String dueDate;
	private String originalAmount;
	private String balAmount;
	private String paidAmount;
	private String groupType;
	private String accountCategory;
	private int paidToId;
	private String paidToName;
	private int paidFromId;
	private String paidFromName;
	private String amtPaid;
	private String paidOn;
	private int paymentMethod;
	private String referenceNo;
	private float totalLessDeduction;
//	private float totalDeduction;
	private String totalDeduction;
	private String date;
	private String billDetails;
	private String deductionDetails;
	private String amtRecdCostCenterDetails;
	private int billExist;
	
	
	private List<BankReceipt> deductionList;
	private List<BankReceipt> billsList;
	//private List<Finance> billList;
	private List<Map<String,String>> bankReceiptMapList;
	private List<Map<String,String>> bankPaymentMapList;
	private String searchCriteria;
	private List<String> orderByList;
	

	private Map<String,String> visColMap;
	 
	private String prefix;
	private String prefixFinTrNo;
	private int lastNo;
	
	private boolean inserted;
	private boolean deleted;
	private String lastprefix;
	private boolean autoNumExists;
	
	private String balanceDue;
	private String excessCredit;
	
	private List<BankReceipt> bankReceiptList;
	private List<BankReceipt> bankPaymentList;
	
	private int pageCnt;
	private int dedRowCnt;	
	private String dedRowIds;
	private List<String> dedRowIdList;
	private String type;
	
	private String internalMemo;
	private int fromInvoice;
	private String prefixInvoiceNo;
	
	private String urlType;
	private String url;
	private String fileName;
	private String attachPath;
	
	private List<BankReceipt> attachedFiles;
	
	private List<String> toIds;
	private String AccountCustomerEmail;
	private String loggedInUserName;
	private String contactImage;
	
	private List<String> autoNoPrefixList;
	private String checkAutoNoType;
	
	List<String> listSelectedColumns;
	
	private List<BankReceipt> costCtrRowList;
	private int dedId;
	private String costCtrRowIds;
	private List<String> costCtrRowIdList;
	private int costCtrRowCnt;
	private String amtRecdRowIds;
	private int amtRecdRowCnt;
	
	private boolean deductionAvailable;
	
	private List<String> amtRecdRowIdList;
	private String costCenterAvailable;
	
	
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public BankReceipt getHeader() {
		return header;
	}
	public void setHeader(BankReceipt header) {
		this.header = header;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
//	public float getDeductionAmt() {
//		return deductionAmt;
//	}
//	public void setDeductionAmt(float deductionAmt) {
//		this.deductionAmt = deductionAmt;
//	}
	public int getDeductionHeadId() {
		return deductionHeadId;
	}
	public void setDeductionHeadId(int deductionHeadId) {
		this.deductionHeadId = deductionHeadId;
	}
	public String getDeductionHeadName() {
		return deductionHeadName;
	}
	public void setDeductionHeadName(String deductionHeadName) {
		this.deductionHeadName = deductionHeadName;
	}
	public int getDepositId() {
		return depositId;
	}
	public void setDepositId(int depositId) {
		this.depositId = depositId;
	}
	public String getDepositName() {
		return depositName;
	}
	public void setDepositName(String depositName) {
		this.depositName = depositName;
	}
	public int getFinTrId() {
		return finTrId;
	}
	public void setFinTrId(int finTrId) {
		this.finTrId = finTrId;
	}
	public String getFinTrType() {
		return finTrType;
	}
	public void setFinTrType(String finTrType) {
		this.finTrType = finTrType;
	}
 
	public int getBillExist() {
		return billExist;
	}
	public void setBillExist(int billExist) {
		this.billExist = billExist;
	}
	public String getFinTrDate() {
		return finTrDate;
	}
	public void setFinTrDate(String finTrDate) {
		this.finTrDate = finTrDate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public int getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}
	public float getExRate() {
		return exRate;
	}
	public void setExRate(float exRate) {
		this.exRate = exRate;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
//	public float getDebit() {
//		return debit;
//	}
//	public void setDebit(float debit) {
//		this.debit = debit;
//	}
//	public float getCredit() {
//		return credit;
//	}
//	public void setCredit(float credit) {
//		this.credit = credit;
//	}
	public int getReceivedFromId() {
		return receivedFromId;
	}
	public void setReceivedFromId(int receivedFromId) {
		this.receivedFromId = receivedFromId;
	}
	public String getBillDetails() {
		return billDetails;
	}
	public void setBillDetails(String billDetails) {
		this.billDetails = billDetails;
	}
	public String getReceivedFromName() {
		return receivedFromName;
	}
	public void setReceivedFromName(String receivedFromName) {
		this.receivedFromName = receivedFromName;
	}
	public int getDepositToId() {
		return depositToId;
	}
	public void setDepositToId(int depositToId) {
		this.depositToId = depositToId;
	}
	public String getDepositToName() {
		return depositToName;
	}
	public void setDepositToName(String depositToName) {
		this.depositToName = depositToName;
	}
//	public float getAmtReceived() {
//		return amtReceived;
//	}
//	public void setAmtReceived(float amtReceived) {
//		this.amtReceived = amtReceived;
//	}
	
	public String getReceivedOn() {
		return receivedOn;
	}
	public String getAmtReceived() {
		return amtReceived;
	}
	public void setAmtReceived(String amtReceived) {
		this.amtReceived = amtReceived;
	}
	public void setReceivedOn(String receivedOn) {
		this.receivedOn = receivedOn;
	}
	public int getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public float getTotalLessDeduction() {
		return totalLessDeduction;
	}
	public void setTotalLessDeduction(float totalLessDeduction) {
		this.totalLessDeduction = totalLessDeduction;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<BankReceipt> getDeductionList() {
		return deductionList;
	}
	public void setDeductionList(List<BankReceipt> deductionList) {
		this.deductionList = deductionList;
	}
	/*public List<Finance> getBillList() {
		return billList;
	}
	public void setBillList(List<Finance> billList) {
		this.billList = billList;
	}*/
	 
	public int getLastNo() {
		return lastNo;
	}
	public void setLastNo(int lastNo) {
		this.lastNo = lastNo;
	}
	public boolean isInserted() {
		return inserted;
	}
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}
	public String getLastprefix() {
		return lastprefix;
	}
	public void setLastprefix(String lastprefix) {
		this.lastprefix = lastprefix;
	}
	public String getHeaderMode() {
		return headerMode;
	}
	public void setHeaderMode(String headerMode) {
		this.headerMode = headerMode;
	}
	public String getDeductionDetails() {
		return deductionDetails;
	}
	public void setDeductionDetails(String deductionDetails) {
		this.deductionDetails = deductionDetails;
	}
//	public float getTotalDeduction() {
//		return totalDeduction;
//	}
//	public void setTotalDeduction(float totalDeduction) {
//		this.totalDeduction = totalDeduction;
//	}
//	public float getBalanceDue() {
//		return balanceDue;
//	}
//	public void setBalanceDue(float balanceDue) {
//		this.balanceDue = balanceDue;
//	}
//	public float getExcessCredit() {
//		return excessCredit;
//	}
//	public void setExcessCredit(float excessCredit) {
//		this.excessCredit = excessCredit;
//	}
	public List<BankReceipt> getBankReceiptList() {
		return bankReceiptList;
	}
	public void setBankReceiptList(List<BankReceipt> bankReceiptList) {
		this.bankReceiptList = bankReceiptList;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public int getDedRowCnt() {
		return dedRowCnt;
	}
	public void setDedRowCnt(int dedRowCnt) {
		this.dedRowCnt = dedRowCnt;
	}
	public String getDedRowIds() {
		return dedRowIds;
	}
	public void setDedRowIds(String dedRowIds) {
		this.dedRowIds = dedRowIds;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPaidToId() {
		return paidToId;
	}
	public void setPaidToId(int paidToId) {
		this.paidToId = paidToId;
	}
	public String getPaidToName() {
		return paidToName;
	}
	public void setPaidToName(String paidToName) {
		this.paidToName = paidToName;
	}
	public int getPaidFromId() {
		return paidFromId;
	}
	public void setPaidFromId(int paidFromId) {
		this.paidFromId = paidFromId;
	}
	public String getPaidFromName() {
		return paidFromName;
	}
	public void setPaidFromName(String paidFromName) {
		this.paidFromName = paidFromName;
	}
//	public float getAmtPaid() {
//		return amtPaid;
//	}
//	public void setAmtPaid(float amtPaid) {
//		this.amtPaid = amtPaid;
//	}
	
	public String getPaidOn() {
		return paidOn;
	}
	public String getAmtPaid() {
		return amtPaid;
	}
	public void setAmtPaid(String amtPaid) {
		this.amtPaid = amtPaid;
	}
	public void setPaidOn(String paidOn) {
		this.paidOn = paidOn;
	}
	public String getInternalMemo() {
		return internalMemo;
	}
	public void setInternalMemo(String internalMemo) {
		this.internalMemo = internalMemo;
	}
	public List<BankReceipt> getBankPaymentList() {
		return bankPaymentList;
	}
	public void setBankPaymentList(List<BankReceipt> bankPaymentList) {
		this.bankPaymentList = bankPaymentList;
	}
	public int getFromInvoice() {
		return fromInvoice;
	}
	public void setFromInvoice(int fromInvoice) {
		this.fromInvoice = fromInvoice;
	}

	public String getTotalDeduction() {
		return totalDeduction;
	}
	public void setTotalDeduction(String totalDeduction) {
		this.totalDeduction = totalDeduction;
	}
	public String getDebit() {
		return debit;
	}
	public void setDebit(String debit) {
		this.debit = debit;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getDeductionAmt() {
		return deductionAmt;
	}
	public void setDeductionAmt(String deductionAmt) {
		this.deductionAmt = deductionAmt;
	}
	public String getBalanceDue() {
		return balanceDue;
	}
	public void setBalanceDue(String balanceDue) {
		this.balanceDue = balanceDue;
	}
	public String getExcessCredit() {
		return excessCredit;
	}
	public void setExcessCredit(String excessCredit) {
		this.excessCredit = excessCredit;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlType() {
		return urlType;
	}
	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
	public List<BankReceipt> getAttachedFiles() {
		return attachedFiles;
	}
	public void setAttachedFiles(List<BankReceipt> attachedFiles) {
		this.attachedFiles = attachedFiles;
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	public List<String> getToIds() {
		return toIds;
	}
	public void setToIds(List<String> toIds) {
		this.toIds = toIds;
	}
	public String getAccountCustomerEmail() {
		return AccountCustomerEmail;
	}
	public void setAccountCustomerEmail(String accountCustomerEmail) {
		AccountCustomerEmail = accountCustomerEmail;
	}
	public String getLoggedInUserName() {
		return loggedInUserName;
	}
	public void setLoggedInUserName(String loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
	}
	public String getContactImage() {
		return contactImage;
	}
	public void setContactImage(String contactImage) {
		this.contactImage = contactImage;
	}
	public String getBillReference() {
		return billReference;
	}
	public void setBillReference(String billReference) {
		this.billReference = billReference;
	}
	public String getBillReferenceTrType() {
		return billReferenceTrType;
	}
	public void setBillReferenceTrType(String billReferenceTrType) {
		this.billReferenceTrType = billReferenceTrType;
	}
	public String getBalAmount() {
		return balAmount;
	}
	public void setBalAmount(String balAmount) {
		this.balAmount = balAmount;
	}
	public String getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public List<BankReceipt> getBillsList() {
		return billsList;
	}
	public void setBillsList(List<BankReceipt> billsList) {
		this.billsList = billsList;
	}
	public String getOriginalAmount() {
		return originalAmount;
	}
	public void setOriginalAmount(String originalAmount) {
		this.originalAmount = originalAmount;
	}
	public List<Map<String, String>> getBankReceiptMapList() {
		return bankReceiptMapList;
	}
	public void setBankReceiptMapList(List<Map<String, String>> bankReceiptMapList) {
		this.bankReceiptMapList = bankReceiptMapList;
	}
	public int getSubdocumentId() {
		return subdocumentId;
	}
	public void setSubdocumentId(int subdocumentId) {
		this.subdocumentId = subdocumentId;
	}
	public String getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	public List<String> getOrderByList() {
		return orderByList;
	}
	public void setOrderByList(List<String> orderByList) {
		this.orderByList = orderByList;
	}
	public Map<String, String> getVisColMap() {
		return visColMap;
	}
	public void setVisColMap(Map<String, String> visColMap) {
		this.visColMap = visColMap;
	}
	public List<Map<String, String>> getBankPaymentMapList() {
		return bankPaymentMapList;
	}
	public void setBankPaymentMapList(List<Map<String, String>> bankPaymentMapList) {
		this.bankPaymentMapList = bankPaymentMapList;
	}
	public int getFinTrNo() {
		return finTrNo;
	}
	public void setFinTrNo(int finTrNo) {
		this.finTrNo = finTrNo;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getPrefixFinTrNo() {
		return prefixFinTrNo;
	}
	public void setPrefixFinTrNo(String prefixFinTrNo) {
		this.prefixFinTrNo = prefixFinTrNo;
	}
	public List<String> getAutoNoPrefixList() {
		return autoNoPrefixList;
	}
	public void setAutoNoPrefixList(List<String> autoNoPrefixList) {
		this.autoNoPrefixList = autoNoPrefixList;
	}
	public String getCheckAutoNoType() {
		return checkAutoNoType;
	}
	public void setCheckAutoNoType(String checkAutoNoType) {
		this.checkAutoNoType = checkAutoNoType;
	}
	public List<String> getListSelectedColumns() {
		return listSelectedColumns;
	}
	public void setListSelectedColumns(List<String> listSelectedColumns) {
		this.listSelectedColumns = listSelectedColumns;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getPrefixInvoiceNo() {
		return prefixInvoiceNo;
	}
	public void setPrefixInvoiceNo(String prefixInvoiceNo) {
		this.prefixInvoiceNo = prefixInvoiceNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<String> getDedRowIdList() {
		return dedRowIdList;
	}
	public void setDedRowIdList(List<String> dedRowIdList) {
		this.dedRowIdList = dedRowIdList;
	}
	public List<BankReceipt> getCostCtrRowList() {
		return costCtrRowList;
	}
	public void setCostCtrRowList(List<BankReceipt> costCtrRowList) {
		this.costCtrRowList = costCtrRowList;
	}
	public int getDedId() {
		return dedId;
	}
	public void setDedId(int dedId) {
		this.dedId = dedId;
	}
	
	public String getCostCtrRowIds() {
		return costCtrRowIds;
	}
	public void setCostCtrRowIds(String costCtrRowIds) {
		this.costCtrRowIds = costCtrRowIds;
	}
	public int getCostCtrRowCnt() {
		return costCtrRowCnt;
	}
	public void setCostCtrRowCnt(int costCtrRowCnt) {
		this.costCtrRowCnt = costCtrRowCnt;
	}
	public String getAmtRecdRowIds() {
		return amtRecdRowIds;
	}
	public void setAmtRecdRowIds(String amtRecdRowIds) {
		this.amtRecdRowIds = amtRecdRowIds;
	}
	public int getAmtRecdRowCnt() {
		return amtRecdRowCnt;
	}
	public void setAmtRecdRowCnt(int amtRecdRowCnt) {
		this.amtRecdRowCnt = amtRecdRowCnt;
	}
	public List<String> getCostCtrRowIdList() {
		return costCtrRowIdList;
	}
	public void setCostCtrRowIdList(List<String> costCtrRowIdList) {
		this.costCtrRowIdList = costCtrRowIdList;
	}
	public List<String> getAmtRecdRowIdList() {
		return amtRecdRowIdList;
	}
	public void setAmtRecdRowIdList(List<String> amtRecdRowIdList) {
		this.amtRecdRowIdList = amtRecdRowIdList;
	}
	public String getAmtRecdCostCenterDetails() {
		return amtRecdCostCenterDetails;
	}
	public void setAmtRecdCostCenterDetails(String amtRecdCostCenterDetails) {
		this.amtRecdCostCenterDetails = amtRecdCostCenterDetails;
	}
	public int getFinTrDetId() {
		return finTrDetId;
	}
	public void setFinTrDetId(int finTrDetId) {
		this.finTrDetId = finTrDetId;
	}
	public boolean isDeductionAvailable() {
		return deductionAvailable;
	}
	public void setDeductionAvailable(boolean deductionAvailable) {
		this.deductionAvailable = deductionAvailable;
	}
	public String getAccountCategory() {
		return accountCategory;
	}
	public void setAccountCategory(String accountCategory) {
		this.accountCategory = accountCategory;
	}
	public String getCostCenterAvailable() {
		return costCenterAvailable;
	}
	public void setCostCenterAvailable(String costCenterAvailable) {
		this.costCenterAvailable = costCenterAvailable;
	}
	public boolean isAutoNumExists() {
		return autoNumExists;
	}
	public void setAutoNumExists(boolean autoNumExists) {
		this.autoNumExists = autoNumExists;
	}
	
}
