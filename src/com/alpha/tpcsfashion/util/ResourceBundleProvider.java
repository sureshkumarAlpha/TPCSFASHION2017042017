package com.alpha.tpcsfashion.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.alpha.tpcsfashion.beans.TPCSUser;

public class ResourceBundleProvider {
public  ResourceBundle getResourceBundle(TPCSUser userInfo){
		ResourceBundle bundle=null;
		  bundle = ResourceBundle.getBundle(getBundleName(userInfo));
		  return bundle;
		}

public  ResourceBundle getResourceBundle(String bundleName){
	
	ResourceBundle bundle=null;
	  bundle = ResourceBundle.getBundle(bundleName);
	  return bundle;
	}


public String getBundleName(TPCSUser userInfo)
{

	String propertyFileName=userInfo.getPropertyFileName();
	String userLocale=userInfo.getUserLocale();
	return getBundleName(propertyFileName,userLocale);
	
}
public String getBundleName(String propertyFileName, String userLocale) {
	// TODO Auto-generated method stub
	String bundleName=null;
	try
	{
	 if("en".equalsIgnoreCase(userLocale))
		    bundleName = propertyFileName;
		  else
			 bundleName = propertyFileName+"_"+userLocale;
	}
	 catch(MissingResourceException mre)
	  {
		 bundleName = "app";
	  }
	 return bundleName; 
}

}
