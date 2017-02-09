package com.alpha.tpcsfashion.beans;

public class Currency
{
	private int CurrencyId;
	private String CurrencyName;
	private String Symbol;

	private String FreightCurrencyName;
	private String InsuranceCurrencyName;
	private String PackingCurrencyName;
	private String OtherCurrencyName;
	private String CustomCurrencyName;
	private String ServicetaxCurrencyName;
	private boolean currencyExists;
	private boolean inserted;
	private String  currencySymbol;
	private boolean NameExists;
	private boolean CoinExists;
	private String CoinName;
	private int isActive;
	private String mode;
	private boolean deleted;
	
	
	
	
	
	
	
	
	
	
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	public boolean isCurrencyExists() {
		return currencyExists;
	}
	public void setCurrencyExists(boolean currencyExists) {
		this.currencyExists = currencyExists;
	}
	public boolean isInserted() {
		return inserted;
	}
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}
	public String getCurrencyName()
	{
		return CurrencyName;
	}
	public void setCurrencyName(String currencyName)
	{
		CurrencyName = currencyName;
	}


	public int getCurrencyId()
	{
		return CurrencyId;
	}
	public void setCurrencyId(int currencyId)
	{
		CurrencyId = currencyId;
	}


	//new




	public String getFreightCurrencyName()
	{
		return FreightCurrencyName;
	}
	public void setFreightCurrencyName(String freightCurrencyName)
	{
		FreightCurrencyName = freightCurrencyName;
	}

	public String getInsuranceCurrencyName()
	{
		return InsuranceCurrencyName;
	}
	public void setInsuranceCurrencyName(String insuranceCurrencyName)
	{
		InsuranceCurrencyName = insuranceCurrencyName;
	}

	public String getPackingCurrencyName()
	{
		return PackingCurrencyName;
	}
	public void setPackingCurrencyName(String packingCurrencyName)
	{
		PackingCurrencyName = packingCurrencyName;
	}

	public String getOtherCurrencyName()
	{
		return OtherCurrencyName;
	}
	public void setOtherCurrencyName(String otherCurrencyName)
	{
		OtherCurrencyName = otherCurrencyName;
	}

	public String getCustomCurrencyName()
	{
		return CustomCurrencyName;
	}
	public void setCustomCurrencyName(String customCurrencyName)
	{
		CustomCurrencyName = customCurrencyName;
	}
	public String getServicetaxCurrencyName()
	{
		return ServicetaxCurrencyName;
	}
	public void setServicetaxCurrencyName(String servicetaxCurrencyName)
	{
		ServicetaxCurrencyName = servicetaxCurrencyName;
	}
	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return Symbol;
	}
	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		Symbol = symbol;
	}
	/**
	 * @return the coinName
	 */
	public String getCoinName() {
		return CoinName;
	}
	/**
	 * @param coinName the coinName to set
	 */
	public void setCoinName(String coinName) {
		CoinName = coinName;
	}
	/**
	 * @return the isActive
	 */
	public int getIsActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}
	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	/**
	 * @return the nameExists
	 */
	public boolean isNameExists() {
		return NameExists;
	}
	/**
	 * @param nameExists the nameExists to set
	 */
	public void setNameExists(boolean nameExists) {
		NameExists = nameExists;
	}
	/**
	 * @return the coinExists
	 */
	public boolean isCoinExists() {
		return CoinExists;
	}
	/**
	 * @param coinExists the coinExists to set
	 */
	public void setCoinExists(boolean coinExists) {
		CoinExists = coinExists;
	}
	public boolean isDeleted() {
		// TODO Auto-generated method stub
		return deleted;
	}
	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	




}