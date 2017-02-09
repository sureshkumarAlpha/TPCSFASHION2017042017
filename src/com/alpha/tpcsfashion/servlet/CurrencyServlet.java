package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.alpha.tpcsfashion.beans.Currency;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.model.CurrencyManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.Validator;

public class CurrencyServlet {
	public void doDisplayCurrency(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			//This is for page no..
			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			String strSearhQuery="";

			if("Search".equalsIgnoreCase(request.getParameter("request_type"))){
				strSearhQuery = getSearchCriteria(request);
				getAndSetAttributes(request);
			}
			int pageCount= objMan.getPageCount(userInfo,strSearhQuery);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);
			//			getAndSetAttributes(request);

			List<Currency> currencyList = objMan.getCurrencyList(userInfo,pc,strSearhQuery); 
			request.setAttribute("currency_list", currencyList);

			doGetUserRights(request,response);

			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_CURRENCY, request,response);

			currencyList=null;
			pc=null;
			userInfo=null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doNewCurrency(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {
			request.setAttribute("mode","n");
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_CURRENCY, request,response);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	private Currency setAndGetParametter(HttpServletRequest request,HttpServletResponse response){
		String currencyName=request.getParameter("currency_name");
		int currencyId=Validator.convertToInteger(request.getParameter("currency_id"));
		String coinName=request.getParameter("coin_name");
		String symbol=request.getParameter("symbol");
		int status=Validator.convertToInteger(request.getParameter("is_active"));
		String mode=request.getParameter("mode");
		System.out.println("mode="+mode);

		Currency cuObj=new Currency();

		cuObj.setCurrencyId(currencyId);
		cuObj.setCurrencyName(currencyName);
		cuObj.setCoinName(coinName);
		cuObj.setSymbol(symbol);
		cuObj.setIsActive(status);
		cuObj.setMode(mode);
		return cuObj;
	}
	public void doSaveCurrency(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			int currencyId=Validator.convertToInteger(request.getParameter("currency_id"));
			String currencyName=request.getParameter("currency_name");
			String coinName=request.getParameter("coin_name");
			String symbol=request.getParameter("symbol");
			int status=Validator.convertToInteger(request.getParameter("is_active"));
			String mode=request.getParameter("mode");

			Currency cuObj=new Currency();

			cuObj.setCurrencyId(currencyId);
			cuObj.setCurrencyName(currencyName);
			cuObj.setCoinName(coinName);
			cuObj.setSymbol(symbol);
			cuObj.setIsActive(status);
			cuObj.setMode(mode);

			int cuId=0;

			cuObj=objMan.isMasterCurrencyExist(userInfo,cuObj);

			if(!cuObj.isNameExists() && !cuObj.isCoinExists()){			 
				cuId = objMan.insertCurrency(cuObj,userInfo);

				if(cuId>0){
					mode="e";
					request.setAttribute("success_message",bundle.getString("Currency.CurrencyInserted")); 
				}
				else{
					mode="n";
					request.setAttribute("error_message",bundle.getString("Currency.CurrencyNotInserted")); 
				}
			}
			else if(cuObj.isNameExists()&& cuObj.isCoinExists()) 
			{
				request.setAttribute("mode", mode);
				request.setAttribute("warning_message","Currency not inserted.\n Currency name ("+cuObj.getCurrencyName()+") & Coin name ("+cuObj.getCoinName()+") already exists"); 

			}
			else if(cuObj.isNameExists())
			{
				request.setAttribute("mode", mode);
				request.setAttribute("warning_message","Currency not inserted.\n Currency name ("+cuObj.getCurrencyName()+") already exists");

			}
			else if(cuObj.isCoinExists())
			{
				request.setAttribute("mode", mode);
				request.setAttribute("warning_message","Coin Name not inserted.\n Coin name ("+cuObj.getCoinName()+") already exists");

			}
			String saveType=request.getParameter("save_type");

			if(saveType.equals("1")){
				if(cuId>0){
					cuObj=objMan.getCurrencyInfo(1,userInfo,cuId);
					request.setAttribute("currency_info", cuObj);
					request.setAttribute("mode", mode);
				}
				else{
					Currency cuObj1=new Currency();
					cuObj1=setAndGetParametter(request,response);
					request.setAttribute("currency_info",cuObj1);
				}
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_CURRENCY, request,response);
			}

			else if(saveType.equals("3")){
				doDisplayCurrency(request,response) ;
			}
			else{
				request.setAttribute("mode","n");
				doNewCurrency(request,response);
			}
			cuObj=null;
			userInfo=null;
			bundle=null;
		}
		catch (Exception e) {
			// TODO: handle exception 
			e.printStackTrace();
		}
	}
	public void getAndSetAttributes(HttpServletRequest request){
		request.setAttribute("currency_name",request.getParameter("currency_name"));
		request.setAttribute("coin_name",request.getParameter("coin_name"));
		request.setAttribute("currency_id",request.getParameter("currency_id"));
	}
	public String getSearchCriteria(HttpServletRequest request){
		String strSearchCriteria="";
		String currencyName=request.getParameter("currency_name");
		String coinName=request.getParameter("coin_name");
		if(currencyName!=null && !currencyName.isEmpty()){
			strSearchCriteria=strSearchCriteria+"  Currency_Name like '%"+currencyName+"%' and ";
		}
		if(coinName!=null && !coinName.isEmpty()){
			strSearchCriteria=strSearchCriteria+"  Coin_Name like '%"+coinName+"%' and ";
		}
		if(!strSearchCriteria.isEmpty()){
			strSearchCriteria=strSearchCriteria.substring(0,strSearchCriteria.length()-4);
		}
		return strSearchCriteria;
	}
	public void doEditCurrencyMaster(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		Currency cuObj=new Currency();
		String mode=request.getParameter("mode");
		cuObj.setMode(mode);
		request.setAttribute("mode", mode);
		int currencyId=Validator.convertToInteger(request.getParameter("currency_id"));

		cuObj=objMan.getCurrencyInfo(1,userInfo,currencyId);
		request.setAttribute("currency_info", cuObj);

		TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_CURRENCY, request,response);

		cuObj=null;
		userInfo=null;
	}
	public void doDeleteCurrencyMaster(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
		boolean isDeleted=false;
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		int currencyId=Validator.convertToInteger(request.getParameter("currency_id"));

		isDeleted=objMan.deleteCurrency(userInfo,currencyId);
		if (isDeleted)
		{
			request.setAttribute("success_message", bundle.getString("Currency.CurrencyDeleted"));
		}

		else if (!isDeleted){
			request.setAttribute("error_message",bundle.getString("Currency.CurrencyNotDeleted") );
		}

		doDisplayCurrency(request, response);
		bundle=null;
		userInfo=null;
	}
	public void doBulkActionCurrency(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		boolean isBulkAction=false;
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		String[] currencyId=request.getParameterValues("bulkcurrencyId");
		String active_mode=request.getParameter("active_mode");

		isBulkAction=objMan.bulkActionCurrency(userInfo,currencyId,active_mode);
		if (isBulkAction==true)	{
			if(active_mode.equals("1")){
				request.setAttribute("success_message","Currency Activated!");
			}
			else{
				request.setAttribute("success_message","Currency Inactivated!");
			}
		}

		if (isBulkAction== false){
			if(active_mode.equals("1")){
				request.setAttribute("error_message","Currency Not Activated!");
			}
			else{
				request.setAttribute("error_message","Currency Not Inactivated!");
			}
		}

		doDisplayCurrency(request, response);
		userInfo=null;
	}
	public void doDeleteBulkCurrency(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		String[] curId=request.getParameterValues("bulkcurrencyid");
		Currency sr=new Currency();
		int currencyId=0;

		for(int i=0;i<curId.length;i++){

			currencyId=Integer.parseInt(curId[i]);	
			sr.setCurrencyId(currencyId);
			sr=objMan.deleteBulkCurrency(userInfo,sr);
		}
		if(sr.isDeleted()){
			request.setAttribute("success_message","Currency deleted successfully");
		}
		else{
			request.setAttribute("error_message","failed");
		}

		doDisplayCurrency(request, response);
		sr=null;
	}	
	public void doGetUserRights(HttpServletRequest request, HttpServletResponse response)
	{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		HttpSession session=request.getSession();

		rights =objRight.getUserRights(SubdocumentId.CURRENCY_MASTER, userInfo);
		session.setAttribute("currency_rights",rights);

	}

	CurrencyManager objMan=new CurrencyManager();
	UserRights rights=null;
	private UserRightsManager objRight = new UserRightsManager();

}
