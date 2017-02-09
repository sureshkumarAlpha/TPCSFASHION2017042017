package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;










import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.Currency;
import com.alpha.tpcsfashion.beans.Customer;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.Group;
import com.alpha.tpcsfashion.beans.GroupMaster;
import com.alpha.tpcsfashion.beans.MaterialMaster;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.gridmaker.CustomerGridMaker;
import com.alpha.tpcsfashion.gridmaker.MaterialGridMaker;
import com.alpha.tpcsfashion.model.ColumnPreferenceManager;
import com.alpha.tpcsfashion.model.CurrencyManager;
import com.alpha.tpcsfashion.model.CustomerManager;
import com.alpha.tpcsfashion.model.GroupMasterManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.UserInfo;
import com.alpha.tpcsfashion.util.Validator;

public class CustomerServlet {
	
	public void doDisplayCustomer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			HttpSession session=request.getSession();
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			PageConfig pc=new PageConfig(request.getParameter("pageno"));

			int  ipageno=1;
			rights =objRight.getUserRights(SubdocumentId.CUSTOMER_MASTER, userInfo);
			session.setAttribute("rights",rights);
			session.setAttribute("subdocument_id", SubdocumentId.CUSTOMER_MASTER);
			String requestType=request.getParameter("request_type");
			String strSearchCriteria=null;
			if("Search".equalsIgnoreCase(requestType)){       
				  strSearchCriteria=getSearchCriteria(request);
		          }
			
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request); 

			int pageCount= objMan.getPageCount(userInfo,pc, strSearchCriteria);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);

		/*	rights =objRight.getUserRights(SubdocumentId.CUSTOMER_MASTER, userInfo);
			session.setAttribute("customer_rights",rights);*/
		doGetUserRights(request,response);
//			ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
//			ColumnPreference DataList=cpm.getVisibleColumnNames(SubdocumentId.CUSTOMER_MASTER, userInfo);
//			 Map<String,String> visColMap=cpm.getVisibleColumnNamesMap(SubdocumentId.CUSTOMER_MASTER,Integer.parseInt((String)request.getSession().getAttribute("login_user_id")),userInfo);
//			List<String> listSelectedColumns=DataList.getVisibleColumns(); 
//			List<String> orderBy=DataList.getOrderBy(); 
//
//			CustomerGridMaker objUtil=new CustomerGridMaker();
//			List<Map<String,String>> objList = objMan.getAllCustomerOnColumnMapping(pc,userInfo,strSearchCriteria,orderBy);
//
//			String Grid = objUtil.formCustomertGrid(visColMap,listSelectedColumns, objList, ipageno, bundle, rights,pc);
//			request.setAttribute("cust_grid", Grid); 
//			
//            
//			request.setAttribute("subdocument_id", SubdocumentId.CUSTOMER_MASTER);  
//			if("Search".equalsIgnoreCase(requestType)){       
//				  getAndSetAttributes(request);
//		          }
			
			
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_CUSTOMER_MASTER, request,response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void doGetUserRights(HttpServletRequest request, HttpServletResponse response)
	{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		HttpSession session=request.getSession();
		
		rights =objRight.getUserRights(SubdocumentId.CUSTOMER_MASTER, userInfo);
		session.setAttribute("customer_rights",rights);
		
	}
	
	public void doDisplayCustomerAfterColumnOrganized(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        try{    
        	HttpSession session=request.getSession();  
        	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
        	PageConfig pc=new PageConfig(request.getParameter("pageno"));
        	String strSelectedColumns = request.getParameter("sel_columns");  
        	String strTotalColumns = request.getParameter("tot_columns");  
        	int iPageno = Integer.parseInt(request.getParameter("pageno"));

        	ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);  
        	String strSearchCriteria="";

        	int subdocumentId =SubdocumentId.CUSTOMER_MASTER;
        	UserRights custRights=(UserRights) session.getAttribute("customer_rights");

        	String strRequestType=request.getParameter("request_type");
        	int  ipageno=1;
        	if("Search".equalsIgnoreCase(strRequestType)){	          
        		strSearchCriteria=(String)request.getSession().getAttribute("search_query");
        	}
        	else{
        		if(request.getSession().getAttribute("search_query")!=null) 
        			request.getSession().removeAttribute("search_query"); 
        	}
        	int pageCount= objMan.getPageCount(userInfo,pc, strSearchCriteria);
        	pc.setPageCount(pageCount);
        	request.setAttribute("pc", pc);
        	request.setAttribute("subdocument_id", SubdocumentId.CUSTOMER_MASTER); 
        	ColumnPreferenceManager cpm=new ColumnPreferenceManager();    
        	Map<Integer, String>  columns = cpm.getAllColumns(subdocumentId,userInfo);

        	boolean bFlag = cpm.updateColumnPreferences(strTotalColumns,subdocumentId,userInfo);
        	CustomerGridMaker objUtil=new CustomerGridMaker();
        	ColumnPreference DataList=cpm.getVisibleColumnNames(subdocumentId, userInfo);
        	Map<String,String> visColMap=cpm.getVisibleColumnNamesMap(SubdocumentId.CUSTOMER_MASTER,Integer.parseInt((String)request.getSession().getAttribute("login_user_id")),userInfo);
        	List<String> listSelectedColumns=DataList.getVisibleColumns(); 
        	List<String> orderBy=DataList.getOrderBy();
        	List<Map<String,String>> objList = objMan.getAllCustomerOnColumnMapping(pc,userInfo,strSearchCriteria,orderBy);


        	String Grid = objUtil.formCustomertGrid(visColMap,listSelectedColumns, objList, ipageno, bundle, custRights,pc);
        	request.setAttribute("cust_grid", Grid); 
        	TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_CUSTOMER_MASTER,request,response);
        }catch(Exception e){ 
          e.printStackTrace();      
        }
      }

	
	public void doNewCustomer(HttpServletRequest request ,HttpServletResponse response)throws ServletException,IOException{
    	try {
			
    		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
    		String Mode=request.getParameter("mode");
    		int custId=Validator.convertToInteger(request.getParameter("party_id"));
    	//	currency_list=curMan.getAllCurrency(userInfo);
    		request.setAttribute("currency_list", currency_list);
    		
    		
    		if(Mode.equalsIgnoreCase("e")){
    			
    			Customer	objC = objMan.getCustomerInfo(userInfo,custId);
		        request.setAttribute("cust_info", objC);
    		}
    		
    		request.setAttribute("mode",Mode);
    		request.setAttribute("party_id",custId);
    		doGetUserRights(request,response);
			
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_CUSTOMER_MASTER, request,response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
}
	
	public void doSaveCustomer(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {

			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");

			Customer objC=getCustomerParameter(request);

			int custId= objMan.saveCustomer(userInfo,objC);
			String mode=request.getParameter("mode");
			String saveType=request.getParameter("save_type");
			if(custId==-1){
				request.setAttribute("error_message", "Code or Company Name already exists.");
				request.setAttribute("cust_info", objC);
				doNewCustomer(request,response);
			}
			else{
				request.setAttribute("party_id",custId);
				if(custId>0){
					
					
					request.setAttribute("success_message", "Inserted successfully");
					
					
					
					if(saveType.equals("1")){

						objC = objMan.getCustomerInfo(userInfo,custId);
						//COMMENTED BELOW BECAUSE OF COMPILE ERROR
//						currency_list=curMan.getAllCurrency(userInfo);
						request.setAttribute("currency_list", currency_list);
						request.setAttribute("cust_info", objC);
						request.setAttribute("mode", "e");
						TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_CUSTOMER_MASTER, request,response);

					}
					else if(saveType.equals("2")){
						//COMMENTED BELOW BECAUSE OF COMPILE ERROR
//						currency_list=curMan.getAllCurrency(userInfo);
						request.setAttribute("currency_list", currency_list);
						request.setAttribute("mode", "n");
						TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_CUSTOMER_MASTER, request,response);
					}

					else if(saveType.equals("3")){
						doDisplayCustomer(request,response) ;
					}
				}
				else{
					request.setAttribute("error_message", "Insertion failed");

//						objC = objMan.getCustomerInfo(userInfo,custId);
					//COMMENTED BELOW BECAUSE OF COMPILE ERROR
//						currency_list=curMan.getAllCurrency(userInfo);
						request.setAttribute("currency_list", currency_list);
						request.setAttribute("cust_info", objC);
						request.setAttribute("mode", request.getParameter("mode"));
						TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_CUSTOMER_MASTER, request,response);

					
				}

			}

		} catch (Exception e) {
			// TODO: handle exception 
			e.printStackTrace();
		}

	}
	
	private Customer getCustomerParameter(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Customer objC=new Customer();
		
		objC.setPartyCode(request.getParameter("customer_code"));
		objC.setPartyName(request.getParameter("company_name"));
		objC.setDisplayName(request.getParameter("display_name"));
		objC.setContactSalutation(request.getParameter("contact_salutation"));
		objC.setContactFirstname(request.getParameter("first_name"));
		objC.setContactLastname(request.getParameter("last_name"));
		objC.setContactEmail(request.getParameter("email"));
		objC.setContactPhone(request.getParameter("phone"));
		objC.setContactDesignation(request.getParameter("contact_desg"));
		objC.setPartyTag(request.getParameter("party_tag"));
		objC.setStatus(Validator.convertToInteger(request.getParameter("status")));
		objC.setCurrencyId(Validator.convertToInteger(request.getParameter("currency_id")));
		objC.setPaymentTerms(request.getParameter("payment_terms"));
		objC.setCreditDays(Validator.convertToInteger(request.getParameter("credit_days")));
		objC.setBillStreet(request.getParameter("bill_street"));
		objC.setBillLocation(request.getParameter("bill_location"));
		objC.setBillCity(request.getParameter("bill_city"));
		objC.setBillState(request.getParameter("bill_state"));
		objC.setBillZip(request.getParameter("bill_zip"));
		objC.setBillCountry(request.getParameter("bill_country"));
		objC.setCompanyPhone(request.getParameter("company_phone"));
		objC.setCompanyEmail(request.getParameter("company_email"));
		objC.setCompanyFax(request.getParameter("company_fax"));
		objC.setShipStreet(request.getParameter("ship_street"));
		objC.setShipLocation(request.getParameter("ship_location"));
		objC.setShipCity(request.getParameter("ship_city"));
		objC.setShipState(request.getParameter("ship_state"));
		objC.setShipZip(request.getParameter("ship_zip"));
		objC.setShipCountry(request.getParameter("ship_country"));
		objC.setShipPhone(request.getParameter("ship_phone"));
		objC.setVatNo(request.getParameter("vat"));
		objC.setServtaxNo(request.getParameter("service_no"));
		objC.setPanNO(request.getParameter("pan_no"));
		objC.setInsuranceDetails(request.getParameter("insurance"));
		objC.setAccountGroupId(Validator.convertToInteger(request.getParameter("account_group_id")));
		objC.setBillTracking(Validator.convertToInteger(request.getParameter("bill_tracking")));
		objC.setNotes(request.getParameter("notes"));
		objC.setMode(request.getParameter("mode"));
		objC.setPartyId(Validator.convertToInteger(request.getParameter("party_id")));
		
		return objC;
	}
	
	public void getAndSetAttributes(HttpServletRequest request){
		request.setAttribute("account_group",request.getParameter("account_group"));
		request.setAttribute("account_group_id",request.getParameter("account_group_id"));
		request.setAttribute("comp_name",request.getParameter("comp_name"));
		request.setAttribute("disp_name",request.getParameter("disp_name"));
		
	}
	
public String getSearchCriteria(HttpServletRequest request){
	    
		TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
		int accountGroupId=Validator.convertToInteger(request.getParameter("account_group_id"));
		String compName=request.getParameter("comp_name");
		String displayName=request.getParameter("disp_name");
		
		 String custSearchCriteria="";
	
		if(accountGroupId>0)
		{
			custSearchCriteria=custSearchCriteria+"   p.account_group_id="+accountGroupId+"  AND";
		}
		if(compName!=null && !compName.isEmpty())
		{
			custSearchCriteria=custSearchCriteria+"   p.party_name='"+compName.replace("'", "''")+"'  AND";
		}
		if(displayName!=null && !displayName.isEmpty())
		{
			custSearchCriteria=custSearchCriteria+"   p.Display_name='"+displayName.replace("'", "''")+"'  AND";
		}
	    if(custSearchCriteria!=null && !custSearchCriteria.isEmpty()){
	    	custSearchCriteria = custSearchCriteria.substring(1,custSearchCriteria.length()-4);
	    }

		return custSearchCriteria;
	     
	   }

	public void doDeleteCustomer(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		int custId=Validator.convertToInteger(request.getParameter("party_id"));
		
		Customer objC=new Customer();
		objC.setPartyId(custId);
		
		objC=objMan.deleteCustomer(objC,userInfo);
		//System.out.println("isDeleted------>"+isDeleted);
		
		 if(objC.isCustExists()){
				request.setAttribute("error_message","Enquiry/order/invoice/financial Transactions are entered for this associate, we should not delete this associate"); 
			}
			else{
				
				if(objC.isDeleted())
				{
					request.setAttribute("success_message","Customer deleted Successfully");
				}
				else
				{
					request.setAttribute("error_message","failed");
				}
			}
		
		doDisplayCustomer(request, response);
	}
	
	
	 public void doGetModalCurrency(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	        try{ 
	        		TPCSUser ui=new UserInfo().getUserInfo(request);
	    	  	  	String retCurrency= objMan.getCurrency(ui);
	    	  	  	response.setContentType("text/xml");
	    	  	  	response.getWriter().write(retCurrency);
	      	  	
	        }catch(Exception e){
	          e.printStackTrace();
	        }
	      }
	 
	 public void doSaveCustomerFromPage(HttpServletRequest request , HttpServletResponse response){
			try {
				TPCSUser ui=new UserInfo().getUserInfo(request);
				
				Customer objC=new Customer();
				objC.setPartyCode(request.getParameter("customer_code"));
				objC.setPartyName(request.getParameter("company_name"));
				objC.setDisplayName(request.getParameter("display_name"));
				objC.setContactSalutation(request.getParameter("contact_salutation"));
				objC.setContactFirstname(request.getParameter("first_name"));
				objC.setContactLastname(request.getParameter("last_name"));
				objC.setContactEmail(request.getParameter("email"));
				objC.setContactPhone(request.getParameter("phone"));
				objC.setContactDesignation(request.getParameter("contact_desg"));
				objC.setPartyTag(request.getParameter("party_tag"));
				objC.setStatus(Validator.convertToInteger(request.getParameter("status")));
				objC.setCurrencyId(Validator.convertToInteger(request.getParameter("currency")));
				objC.setPaymentTerms(request.getParameter("payment_terms"));
				objC.setCreditDays(Validator.convertToInteger(request.getParameter("credit_days")));
				objC.setBillStreet(request.getParameter("bill_street"));
				objC.setBillLocation(request.getParameter("bill_location"));
				objC.setBillCity(request.getParameter("bill_city"));
				objC.setBillState(request.getParameter("bill_state"));
				objC.setBillZip(request.getParameter("bill_zip"));
				objC.setBillCountry(request.getParameter("bill_country"));
				objC.setCompanyPhone(request.getParameter("company_phone"));
				objC.setCompanyEmail(request.getParameter("company_email"));
				objC.setCompanyFax(request.getParameter("company_fax"));
				objC.setShipStreet(request.getParameter("ship_street"));
				objC.setShipLocation(request.getParameter("ship_location"));
				objC.setShipCity(request.getParameter("ship_city"));
				objC.setShipState(request.getParameter("ship_state"));
				objC.setShipZip(request.getParameter("ship_zip"));
				objC.setShipCountry(request.getParameter("ship_country"));
				objC.setShipPhone(request.getParameter("ship_phone"));
				objC.setVatNo(request.getParameter("vat"));
				objC.setServtaxNo(request.getParameter("service_no"));
				objC.setPanNO(request.getParameter("pan_no"));
				objC.setInsuranceDetails(request.getParameter("insurance"));
				objC.setAccountGroupId(Validator.convertToInteger(request.getParameter("account_group_id")));
				objC.setBillTracking(Validator.convertToInteger(request.getParameter("bill_tracking")));
				objC.setNotes(request.getParameter("notes"));
				objC.setMode(request.getParameter("customer_mode"));
				objC.setPartyId(Validator.convertToInteger(request.getParameter("customer_id")));
				String strInsertDetails="";
					
				//System.out.println("customer_made=="+request.getParameter("customer_mode"));
			//	System.out.println("customer_made=="+objC.getMode());
				objC=objMan.saveCustomer(objC,ui);
					
					if(objC.isInserted()){
						strInsertDetails="1";
					}
					else{
						strInsertDetails="0";
					}
						
				
				
				
				response.setContentType("text/html");
				response.getWriter().write(strInsertDetails);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 public void doGetAccountBillTracking(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	        try{ 
	        		TPCSUser ui=new UserInfo().getUserInfo(request);
	        		int groupId=Validator.convertToInteger(request.getParameter("account_group_id"));
	        		String strBill="";
	    	  	  	boolean billExist= objMan.getAccountBillTracking(ui,groupId);
	    	  	  	
	    	  	  if(billExist){
	    	  		strBill="1";
					}
					else{
						strBill="0";
					}
	    	  	  
	    	  	  response.setContentType("text/html");
	    	  	  	response.getWriter().write(strBill);
	      	  	
	        }catch(Exception e){
	          e.printStackTrace();
	        }
	      }
	    
	 public void doSaveCurrencyFromPage(HttpServletRequest request , HttpServletResponse response){
			try {
				TPCSUser ui=new UserInfo().getUserInfo(request);
				Currency objCur=new Currency();
				objCur.setCurrencyName(request.getParameter("new_currency_name"));
				objCur=objMan.saveCurrency(objCur,ui);

				String strInsertDetails="";

				if(objCur.isCurrencyExists()){
					strInsertDetails="2";
				}
				else if(objCur.isInserted()){
					strInsertDetails="1";
				}
				else{
					strInsertDetails="0";
				}
				response.setContentType("text/html");
				response.getWriter().write(strInsertDetails);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		} 
	private CustomerManager objMan=new CustomerManager();
	public static CurrencyManager curMan=new CurrencyManager();
	List<Currency> currency_list;
	UserRights rights=null;
	private UserRightsManager objRight = new UserRightsManager();
}
