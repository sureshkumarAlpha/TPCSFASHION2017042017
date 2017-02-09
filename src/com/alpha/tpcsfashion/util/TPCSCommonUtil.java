package com.alpha.tpcsfashion.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alpha.tpcsfashion.beans.TPCSUser;

public class TPCSCommonUtil {
	
	

	public static String HOME_PAGE = "HomePageServlet";
	public static String COMPANY_YEAR_SELECTION = "CompanyAndYearSelectionServlet";
	public static String ENTITY_RIGHTS_SERVLET="EntityServlet";
	public static String PROFILE_SERVLET="ProfileServlet";
	public static String USER_SERVLET="UserServlet";
	public static String JENIXCLOUD_AUTOCOMPLETE_SERVLET="AutoCompleteServlet";
	public static String COLUM_PREFERENCES_SERVLET="ColumnPreferenceServlet";
	public static String GROUP_MASTER_SERVLET="GroupMasterServlet";
	public static String MATERIAL_MASTER_SERVLET="MaterialMasterServlet";
	public static String TAX_GROUP_SERVLET ="TaxGroupServlet";
	public static String CUSTOMER_SERVLET="CustomerServlet";
	public static String DYNAMIC_ORDER ="DynamicOrderConfig";
	public static String SPECIFICATION_SETTING="SpecificationSettingServlet";
	public static String APPROVED_PRICELIST_SERVLET="ApprovedPriceListServlet";
	public static String WAREHOUSE="WarehouseMasterServlet";
	public static String BOM_PAGE = "BOMServlet";
	public static String OPERATION_STAGE="OperationOrStageServlet";
	public static String MASTER_PRODUCTS_VARIANT="VariantServlet";
	public static String MASTER_PRODUCTS_SIZE_RANGE="SizeRangeServlet";
	public static String MASTER_PRODUCTS_SEASON="SeasonServlet";
	public static String MASTER_PRODUCTS_CURRENCY="CurrencyServlet";
	public static String MASTER_SIZE_MAPPING="SizeMappingServlet";
	
	//ORDER
	//BUYER_ORDER
	public static String BUYER_ORDER="BuyerOrderServlet";

	//COMMON_UTIL
	public static String COMMON_UTIL="CommonUtilServlet";

	//PURCHASE_ORDER
	public static String PURCHASE_ORDER="PurchaseOrderServlet";
	//INDENT
	public static String INDENT = "Indentservlet";
	
	public static String CUSTOMIZE_REPORT="CustomizeReportServlet";
	public static String INDENT_APPROVAL_REGISTER="IndentApprovalRegisterServlet";
	
	public static void RedirectURL(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher requestDisp = null;
		requestDisp = request.getRequestDispatcher(url);    
		requestDisp.forward(request,response);
	}
	
	public static String currenDate(String format) {
		Calendar currentDate = Calendar.getInstance();
  	 	SimpleDateFormat formatter = new SimpleDateFormat(format);
  	 	String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	
	public static String formatDate(String fromFormat,String toFormat,String date){
		SimpleDateFormat fromUser = new SimpleDateFormat(fromFormat);//"dd-MM-yyyy");
		SimpleDateFormat myFormat = new SimpleDateFormat(toFormat);//"dd-MMM-yyyy");
		String reformattedStr =date;
		try {

			reformattedStr = myFormat.format(fromUser.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reformattedStr;
	}
	 public static int countLines(String str){
		   String[] lines = str.split("\r\n|\r|\n");
		   return  lines.length;
		}
	public static ResourceBundle getResourceBundle(HttpServletRequest request){
		
		ResourceBundle bundle=null;
		  TPCSUser userInfo = (TPCSUser)request.getSession().getAttribute("user_info");
		  
		  try {
		  String propertyFileName=userInfo.getPropertyFileName();
		  
		  if("en".equalsIgnoreCase(userInfo.getUserLocale())){
		    bundle = ResourceBundle.getBundle(propertyFileName);
		  }
		  else{
		    bundle = ResourceBundle.getBundle(propertyFileName+"_"+userInfo.getUserLocale());
		  }
		  
		  }
		  catch(MissingResourceException mre) {
			  bundle = ResourceBundle.getBundle("app");
		  }
		  
		  return bundle;
		}
 

}
