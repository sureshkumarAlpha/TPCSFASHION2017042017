package com.alpha.tpcsfashion.beans;

import java.util.List;

public class HomePage {

	private String orderValue;
	private String invoiceValue;
	private String cashBankValue;
	private String receivableValue;
	private String payableValue;
	private HomePage keyPerInd;
	
	private List<HomePage> keyPerIndList;
	private List<HomePage> cashInOutList;
	private List<HomePage> profitAnalysisList;
//	private HomePage kpiSales;
//	private HomePage kpiNewBus;
//	private HomePage kpiIncNewBus;
//	private HomePage kpiExpNewBus;
//	private HomePage kpiProfNewBus;
//	private HomePage kpiCashBankNewBus;
//	private HomePage kpiReceivable;
//	private HomePage kpiPayable;
	
	private int slNo;
	private String indicator;
	private String analysisPeriod;
	private String curValue;
	private String prevValue;
	private String diffValue;
	private String cumValue;
	
	private int monhtNumber;
	private String monthName;
	private int year;
	private String yearMonth;
	private String income;
	private String expense;
	private String profit;
	private String debitFcy;
	private String creditFcy;
	private int displayOrder;
	
//	private String salesCurValue;
//	private String salesPrevValue;
//	private String salesDiffValue;
//	private String salesCumValue;
	
//	private String newBusCurValue;
//	private String newBusPrevValue;
//	private String newBusDiffValue;
//	private String newBusCumValue;
//	
//	private String incCurValue;
//	private String incPrevValue;
//	private String incDiffValue;
//	private String incCumValue;
//	
//	private String expCurValue;
//	private String expPrevValue;
//	private String expDiffValue;
//	private String expCumValue;
//	
//	private String profCurValue;
//	private String profPrevValue;
//	private String profDiffValue;
//	private String profCumValue;
//	
//	private String cashBankCurValue;
//	private String cashBankPrevValue;
//	private String cashBankDiffValue;
//	private String cashBankCumValue;
//	
	
	
	
	public String getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(String orderValue) {
		this.orderValue = orderValue;
	}
	public String getInvoiceValue() {
		return invoiceValue;
	}
	public void setInvoiceValue(String invoiceValue) {
		this.invoiceValue = invoiceValue;
	}
	public String getCashBankValue() {
		return cashBankValue;
	}
	public void setCashBankValue(String cashBankValue) {
		this.cashBankValue = cashBankValue;
	}
	public String getReceivableValue() {
		return receivableValue;
	}
	public void setReceivableValue(String receivableValue) {
		this.receivableValue = receivableValue;
	}
	public String getPayableValue() {
		return payableValue;
	}
	public void setPayableValue(String payableValue) {
		this.payableValue = payableValue;
	}
	public HomePage getKeyPerInd() {
		return keyPerInd;
	}
	public void setKeyPerInd(HomePage keyPerInd) {
		this.keyPerInd = keyPerInd;
	}
	public String getCurValue() {
		return curValue;
	}
	public void setCurValue(String curValue) {
		this.curValue = curValue;
	}
	public String getPrevValue() {
		return prevValue;
	}
	public void setPrevValue(String prevValue) {
		this.prevValue = prevValue;
	}
	public String getDiffValue() {
		return diffValue;
	}
	public void setDiffValue(String diffValue) {
		this.diffValue = diffValue;
	}
	public String getCumValue() {
		return cumValue;
	}
	public void setCumValue(String cumValue) {
		this.cumValue = cumValue;
	}
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public String getIndicator() {
		return indicator;
	}
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}
	public String getAnalysisPeriod() {
		return analysisPeriod;
	}
	public void setAnalysisPeriod(String analysisPeriod) {
		this.analysisPeriod = analysisPeriod;
	}
	public List<HomePage> getKeyPerIndList() {
		return keyPerIndList;
	}
	public void setKeyPerIndList(List<HomePage> keyPerIndList) {
		this.keyPerIndList = keyPerIndList;
	}
	public List<HomePage> getCashInOutList() {
		return cashInOutList;
	}
	public void setCashInOutList(List<HomePage> cashInOutList) {
		this.cashInOutList = cashInOutList;
	}
	public int getMonhtNumber() {
		return monhtNumber;
	}
	public void setMonhtNumber(int monhtNumber) {
		this.monhtNumber = monhtNumber;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public String getDebitFcy() {
		return debitFcy;
	}
	public void setDebitFcy(String debitFcy) {
		this.debitFcy = debitFcy;
	}
	public String getCreditFcy() {
		return creditFcy;
	}
	public void setCreditFcy(String creditFcy) {
		this.creditFcy = creditFcy;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	public List<HomePage> getProfitAnalysisList() {
		return profitAnalysisList;
	}
	public void setProfitAnalysisList(List<HomePage> profitAnalysisList) {
		this.profitAnalysisList = profitAnalysisList;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getExpense() {
		return expense;
	}
	public void setExpense(String expense) {
		this.expense = expense;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	
}
