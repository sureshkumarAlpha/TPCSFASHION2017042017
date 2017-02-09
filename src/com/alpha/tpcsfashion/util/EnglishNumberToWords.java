package com.alpha.tpcsfashion.util;

import java.text.DecimalFormat;

public class EnglishNumberToWords {

	private static final String[] tensNames = { "", " Ten", " Twenty",
			" Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty",
			" Ninety" };

	private static final String[] numNames = { "", " One", " Two", " Three",
			" Four", " Five", " Six", " Seven", " Eight", " Nine", " Ten",
			" Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen",
			" Sixteen", " Seventeen", " Eighteen", " Nineteen" };

	private static String convertLessThanOneThousand(int number) {
		String soFar;

		if (number % 100 < 20) {
			soFar = numNames[number % 100];
			number /= 100;
		} else {
			soFar = numNames[number % 10];
			number /= 10;

			soFar = tensNames[number % 10] + soFar;
			number /= 10;
		}
		if (number == 0)
			return soFar;
		return numNames[number] + " Hundred" + soFar;
	}

	public static String convertNotINR(long number) {
		// 0 to 999 999 999 999
		if (number == 0) {
			return "Zero";
		}

		String snumber = Long.toString(number);

		// pad with "0"
		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);

		// XXXnnnnnnnnn
		int Billions = Integer.parseInt(snumber.substring(0, 3));
		// nnnXXXnnnnnn
		int Millions = Integer.parseInt(snumber.substring(3, 6));
		// nnnnnnXXXnnn
		int HundredThousands = Integer.parseInt(snumber.substring(6, 9));
		// nnnnnnnnnXXX
		int Thousands = Integer.parseInt(snumber.substring(9, 12));

		String tradBillions;
		switch (Billions) {
		case 0:
			tradBillions = "";
			break;
		case 1:
			tradBillions = convertLessThanOneThousand(Billions) + " Billion ";
			break;
		default:
			tradBillions = convertLessThanOneThousand(Billions) + " Billion ";
		}
		String result = tradBillions;

		String tradMillions;
		switch (Millions) {
		case 0:
			tradMillions = "";
			break;
		case 1:
			tradMillions = convertLessThanOneThousand(Millions) + " Million ";
			break;
		default:
			tradMillions = convertLessThanOneThousand(Millions) + " Million ";
		}
		result = result + tradMillions;

		String tradHundredThousands;
		switch (HundredThousands) {
		case 0:
			tradHundredThousands = "";
			break;
		case 1:
			tradHundredThousands = "One Thousand ";
			break;
		default:
			tradHundredThousands = convertLessThanOneThousand(HundredThousands)
					+ " Thousand ";
		}
		result = result + tradHundredThousands;

		String tradThousand;
		tradThousand = convertLessThanOneThousand(Thousands);
		result = result + tradThousand;

		// remove extra spaces!
		return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	}
	public static String convertINR(long number) {
	    // 0 to 999 999 999 999
	    if (number == 0) { return "Zero"; }

	    String snumber = Long.toString(number);

	    // pad with "0"
	    String mask = "000000000000";
	    DecimalFormat df = new DecimalFormat(mask);
	    snumber = df.format(number);

	    // XXXnnnnnnnnn 
//	    int Billions = Integer.parseInt(snumber.substring(0,3));
	    // nnnXXXnnnnnn
	  //  int Millions  = Integer.parseInt(snumber.substring(3,6)); 
	    // nnnnnnXXXnnn
//	    int HundredThousands = Integer.parseInt(snumber.substring(6,9)); 
	    // nnnnnnnnnXXX
	    int TenThousandCrores = Integer.parseInt(snumber.substring(0,3));

	    int HundredCrores = Integer.parseInt(snumber.substring(3,5));

	    int Crores = Integer.parseInt(snumber.substring(5,7));

	    int thousand = Integer.parseInt(snumber.substring(7,9));
	    int Thousands = Integer.parseInt(snumber.substring(9,12));    
	    

	    String tradTenThousandCrores;
	    switch (TenThousandCrores) {
	    case 0:
	    	tradTenThousandCrores = "";
	      break;
	    case 1 :
	    	tradTenThousandCrores = convertLessThanOneThousand(TenThousandCrores) 
	      + " Hundred ";
	      break;
	    default :
	    	tradTenThousandCrores = convertLessThanOneThousand(TenThousandCrores) 
	      + " Hundred ";
	    }
	    String result =  tradTenThousandCrores;

	    String tradHundredCrores;
	    switch (HundredCrores) {
	    case 0:
	    	tradHundredCrores = "";
	      break;
	    case 1 :
	    	tradHundredCrores = convertLessThanOneThousand(HundredCrores) 
	      + " Crore ";
	      break;
	    default :
	    	tradHundredCrores = convertLessThanOneThousand(HundredCrores) 
	      + " Crore ";
	    }
	    result =  result + tradHundredCrores;

	    String tradCrores;
	    switch (Crores) {
	    case 0:
	    	tradCrores = "";
	      break;
	    case 1 :
	    	tradCrores = convertLessThanOneThousand(Crores) 
	      + " Lakh ";
	      break;
	    default :
	    	tradCrores = convertLessThanOneThousand(Crores) 
	      + " Lakh ";
	    }
	    result =  result + tradCrores;
	    
//	    String tradLakhs;
//	    switch (Lakhs) {
//	    case 0:
//	    	tradLakhs = "";
//	      break;
//	    case 1 :
//	    	tradLakhs = convertLessThanOneThousand(Lakhs) 
//	      + " Lakh ";
//	      break;
//	    default :
//	    	tradLakhs = convertLessThanOneThousand(Lakhs) 
//	      + " Lakh ";
//	    }
//	    result =  result + tradLakhs;

//	    String tradThousand;
//	    switch (Thousand) {
//	    case 0:
//	    	tradThousand = "";
//	      break;
//	    case 1 :
//	    	tradThousand = convertLessThanOneThousand(Thousand) 
//	      + " Thousand ";
//	      break;
//	    default :
//	    	tradThousand = convertLessThanOneThousand(Thousand) 
//	      + " Thousand ";
//	    }
//	    result =  result + tradThousand;
	    
	    String tradLakhs;
	    switch (thousand) {
	    case 0:
	    	tradLakhs = "";
	      break;
	    case 1 :
	    	tradLakhs = "One Thousand ";
	      break;
	    default :
	    	tradLakhs = convertLessThanOneThousand(thousand) 
	      + " Thousand ";
	    }
	    result =  result + tradLakhs;
	    
	    
	   
	    String tradThousand;
	    tradThousand = convertLessThanOneThousand(Thousands);
	    result =  result + tradThousand;

	    // remove extra spaces!
	    return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	  }
	public static String convert(long number) {
		// 0 to 999 999 999 999
		if (number == 0) {
			return "Zero";
		}

		String snumber = Long.toString(number);

		// pad with "0"
		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);

		// XXXnnnnnnnnn
		int Billions = Integer.parseInt(snumber.substring(0, 3));
		// nnnXXXnnnnnn
		int Millions = Integer.parseInt(snumber.substring(3, 6));
		// nnnnnnXXXnnn
		int HundredThousands = Integer.parseInt(snumber.substring(6, 9));
		// nnnnnnnnnXXX
		int Thousands = Integer.parseInt(snumber.substring(9, 12));

		String tradBillions;
		switch (Billions) {
		case 0:
			tradBillions = "";
			break;
		case 1:
			tradBillions = convertLessThanOneThousand(Billions) + " Billion ";
			break;
		default:
			tradBillions = convertLessThanOneThousand(Billions) + " Billion ";
		}
		String result = tradBillions;

		String tradMillions;
		switch (Millions) {
		case 0:
			tradMillions = "";
			break;
		case 1:
			tradMillions = convertLessThanOneThousand(Millions) + " Million ";
			break;
		default:
			tradMillions = convertLessThanOneThousand(Millions) + " Million ";
		}
		result = result + tradMillions;

		String tradHundredThousands;
		switch (HundredThousands) {
		case 0:
			tradHundredThousands = "";
			break;
		case 1:
			tradHundredThousands = "One Thousand ";
			break;
		default:
			tradHundredThousands = convertLessThanOneThousand(HundredThousands)
					+ " Thousand ";
		}
		result = result + tradHundredThousands;

		String tradThousand;
		tradThousand = convertLessThanOneThousand(Thousands);
		result = result + tradThousand;

		// remove extra spaces!
		return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	}
	/**
	 * testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/*System.out.println("*** " + EnglishNumberToWords.convert(0));
		System.out.println("*** " + EnglishNumberToWords.convert(1));
		System.out.println("*** " + EnglishNumberToWords.convert(16));
		System.out.println("*** " + EnglishNumberToWords.convert(100));
		System.out.println("*** " + EnglishNumberToWords.convert(118));
		System.out.println("*** " + EnglishNumberToWords.convert(200));
		System.out.println("*** " + EnglishNumberToWords.convert(219));
		System.out.println("*** " + EnglishNumberToWords.convert(800));
		System.out.println("*** " + EnglishNumberToWords.convert(801));
		System.out.println("*** " + EnglishNumberToWords.convert(1316));
		System.out.println("*** " + EnglishNumberToWords.convert(1000000));
		System.out.println("*** " + EnglishNumberToWords.convert(2000000));
		System.out.println("*** " + EnglishNumberToWords.convert(3000200));
		System.out.println("*** " + EnglishNumberToWords.convert(700000));
		System.out.println("*** " + EnglishNumberToWords.convert(9000000));
		System.out.println("*** " + EnglishNumberToWords.convert(9001000));
		System.out.println("*** " + EnglishNumberToWords.convert(123456789));
		System.out.println("*** " + EnglishNumberToWords.convert(2147483647));
		System.out.println("*** " + EnglishNumberToWords.convert(3000000010L));*/

		/*
		 * ** zero** one** sixteen** one hundred** one hundred eighteen** two
		 * hundred** two hundred nineteen** eight hundred** eight hundred one**
		 * one thousand three hundred sixteen** one million** two millions**
		 * three millions two hundred** seven hundred thousand** nine millions**
		 * nine millions one thousand** one hundred twenty three millions four
		 * hundred* fifty six thousand seven hundred eighty nine** two billion
		 * one hundred forty seven millions* four hundred eighty three thousand
		 * six hundred forty seven** three billion ten
		 */
	}
}