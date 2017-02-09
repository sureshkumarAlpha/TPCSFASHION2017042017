package com.alpha.tpcsfashion.util;

public class Validator {
   public static String trim(String str)
   {
	   if(str!=null && !(str.isEmpty()) )
	   {
		   str=str.trim();
	   }
	   return str;
   }
   public static int convertToInteger(String str) 
   {
	   int value=0;
	    
	   if(str!=null && !(str.isEmpty()) )
	   {
		  value=Integer.parseInt(str);
	   }
	   return value;
   }
   public static int convertUndefinedToInteger(String str) 
   {
	   int value=0;
	    
	   if(str!=null && !(str.isEmpty()) && !(str.equals("undefined")) )
	   {
		  value=Integer.parseInt(str);
	   }
	   return value;
   }
   public static float convertToFloat(String str)
   {
	   float value=0.0F;
	    
	   if(str!=null && !(str.isEmpty()) )
	   {
		  value=Float.parseFloat(str);
	   }
	   return value;
   }
   public static double convertToDouble(String str)
   {
	   double value=0.0d;
	    
	   if(str!=null && !(str.isEmpty()) )
	   {
		  value=Double.parseDouble(str);
	   }
	   return value;
   }
   public static int convertStringToInteger(String str) 
   {
     int value=0;
      
     if(str!=null && !(str.isEmpty()) )
     {
      value="on".equalsIgnoreCase(str)?1:0;
     }
     return value;
   }
}
