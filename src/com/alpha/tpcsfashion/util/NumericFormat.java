package com.alpha.tpcsfashion.util;

import java.text.DecimalFormat;

import com.alpha.tpcsfashion.beans.TPCSUser;

public class NumericFormat {
	
	public static int valueRoundOff;
	public static int priceRoundOff;
	public static int qtyRoundOff;
	
	public static String valFormat;
	public static String priceFormat;
	public static String qtyFormat;
	public static String valEmptyFormat;
	
	public static String valSepFractFormat;
	
	public static String valSepFormat;
	public static String priceSepFormat;
	public static String qtySepFormat;
	
	public static DecimalFormat valDf;//= new DecimalFormat("#.0");
	public static DecimalFormat priceDf;//= new DecimalFormat("#.0");
	public static DecimalFormat qtyDf;//= new DecimalFormat("#.0");
	public static DecimalFormat tempDf;

	public static Double valDbRoundoff;//=Math.pow(10.00, 2);
	public static Double priceDbRoundoff;//=Math.pow(10.00, 2);
	public static Double qtyDbRoundoff;//=Math.pow(10.00, 2);
	
	public static void setRoundOff(TPCSUser userInfo){
//		int valueRoundOff=2;
//		int priceRoundOff=2;
//		int qtyRoundOff=2;
		if(userInfo.getLocatonConfigMap()!=null){
			valueRoundOff=userInfo.getLocatonConfigMap().get("RoundoffValue")!=null?Validator.convertToInteger(userInfo.getLocatonConfigMap().get("RoundoffValue")):2;
			priceRoundOff=userInfo.getLocatonConfigMap().get("RoundoffRate")!=null?Validator.convertToInteger(userInfo.getLocatonConfigMap().get("RoundoffRate")):2;
			qtyRoundOff=userInfo.getLocatonConfigMap().get("RoundoffQty")!=null?Validator.convertToInteger(userInfo.getLocatonConfigMap().get("RoundoffQty")):2;
		}
		valDbRoundoff=Math.pow(10.00, valueRoundOff);
		priceDbRoundoff=Math.pow(10.00, priceRoundOff);
		qtyDbRoundoff=Math.pow(10.00, priceRoundOff);

		
		String sepStr="#,##,###";
		if(!userInfo.getCurrencyName().equalsIgnoreCase("INR")){
			sepStr="###,###";
		}
		
		String sepFractStr="#,##,#";
		if(!userInfo.getCurrencyName().equalsIgnoreCase("INR")){
			sepFractStr="###,#";
		}

		StringBuffer buf=new StringBuffer("#0.0");
		StringBuffer emptyValbuf=new StringBuffer("0.0");
		for(int i=2;i<=valueRoundOff;i++){
			buf.append("0");
			emptyValbuf.append("0");
		}
		valSepFormat=sepStr;
		valSepFractFormat=sepFractStr+buf.toString();
		valFormat=buf.toString();
		valEmptyFormat=emptyValbuf.toString();
		valDf = new DecimalFormat(buf.toString());
		
		buf=null;
		buf=new StringBuffer("#0.0");
		for(int i=2;i<=priceRoundOff;i++){
			buf.append("0");
		}
		priceSepFormat=sepStr;
		priceFormat=buf.toString();
		priceDf = new DecimalFormat(buf.toString()); 

		buf=null;
		buf=new StringBuffer("#0.0");
		for(int i=2;i<=qtyRoundOff;i++){
			buf.append("0");
		}
		
		qtySepFormat=sepStr;
		qtyFormat=buf.toString();
		qtyDf = new DecimalFormat(buf.toString()); 
		buf=null;
	}
	
}
