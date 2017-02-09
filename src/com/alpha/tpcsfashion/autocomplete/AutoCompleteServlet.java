package com.alpha.tpcsfashion.autocomplete;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.util.UserInfo;
import com.alpha.tpcsfashion.util.Validator;


/**
 * 
 *  This class will be used for auto complete all common fields.
 *
 * @author Narayana Swamy
 * @creation Oct 6, 2013
 *
 */
public class AutoCompleteServlet extends HttpServlet
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public AutoCompleteServlet() {
      super();
      // TODO Auto-generated constructor stub
  }
  
  /**
   * This method will be used to get all the suppliers/parties from database.
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
 
  public void doGetGroup(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
    try{
      String input = request.getParameter("q");
      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
      System.out.println("input :"+input);
      PrintWriter writer = response.getWriter();
      response.setContentType("text/json;charset=utf-8");
      response.setHeader("Cache-Control", "no-cache");
      TPCSUser ui=new UserInfo().getUserInfo(request);
      String data = objAutoComplete.getGroups(input,createNew, ui);     
      writer.print(data);
    }catch(Exception ex){
      ex.printStackTrace();
    }
  }
  
  public void doGetTempGroup(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getTempGroups(input,createNew, ui);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  public void doGetUOM(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getUOM(input,createNew, ui);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  public void doGetIndentType(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	    	
	    	
	      String input = request.getParameter("q");
	      int createNew=Validator.convertUndefinedToInteger(request.getParameter("create_new"));
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getIndentType(input,createNew, ui);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }

  
  public void doGetCustomer(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getCustomer(input,createNew, ui);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetAgent(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getAgent(input,createNew, ui);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }

public void doGetSeason(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getSeason(input,createNew, ui);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetSupplier(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getSupplier(input,createNew, ui);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetInventoryAccount(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getInventoryAccount(input, ui,createNew);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetPurchaseAccount(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getPurchaseAccount(input, ui,createNew);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetMaterial(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertUndefinedToInteger(request.getParameter("create_new"));
	      int groupId=Validator.convertUndefinedToInteger(request.getParameter("group_id"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getMaterial(input,createNew, ui,groupId);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  public void doGetMaterialFG(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertUndefinedToInteger(request.getParameter("create_new"));
	      int groupId=Validator.convertUndefinedToInteger(request.getParameter("group_id"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getMaterialFG(input,createNew, ui,groupId);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  public void doGetMaterialComponent(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertUndefinedToInteger(request.getParameter("create_new"));
	      int trId=Validator.convertUndefinedToInteger(request.getParameter("tr_id"));
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getMaterialComponent(input,createNew, ui,trId);
	      ui=null;
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }

public void doGetAltMaterial(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertUndefinedToInteger(request.getParameter("create_new"));
	      int matId=Validator.convertUndefinedToInteger(request.getParameter("det_mat_id"));
	      int componentId=Validator.convertUndefinedToInteger(request.getParameter("det_comp_id"));
	      
	      
	      System.out.println("----doGetAltMaterial--------");
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getAltMaterial(input,createNew, ui,matId,componentId);
	      ui=null;
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }



public void doGetBOMCOMP(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertUndefinedToInteger(request.getParameter("create_new"));
	      int detId=Validator.convertUndefinedToInteger(request.getParameter("bom_det_id"));
	      
	      
	      System.out.println("----doGetBOMCOMP--------"+detId);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getBOMComponents(input,createNew, ui,detId);
	      ui=null;
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }

public void doGetBOM(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertUndefinedToInteger(request.getParameter("create_new"));
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getBOM(input,createNew, ui);
	      ui=null;
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }





public void doGetFGMaterial(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertUndefinedToInteger(request.getParameter("create_new"));
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getFGMaterial(input,createNew, ui);
	      ui=null;
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetMaterialForInvoice(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertUndefinedToInteger(request.getParameter("create_new"));
	      int enqId=Validator.convertUndefinedToInteger(request.getParameter("enq_id"));
	      int soId=Validator.convertUndefinedToInteger(request.getParameter("so_id"));
	      int quoteId=Validator.convertUndefinedToInteger(request.getParameter("quote_id"));
	      int poId=Validator.convertUndefinedToInteger(request.getParameter("po_id"));
	      int invId=Validator.convertUndefinedToInteger(request.getParameter("invoice_id"));
	      String trTag=request.getParameter("tr_tag");
	      System.out.println("trTag :"+trTag);
	      System.out.println("doGetMaterial soId :"+soId);
	      System.out.println("doGetMaterial poId :"+poId);
	      System.out.println("doGetMaterial invId :"+invId);
	      System.out.println("doGetMaterial enqId :"+enqId);
	      System.out.println("doGetMaterial quoteId :"+quoteId);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getMaterialForInvoice(input,createNew, ui,soId,poId,invId,enqId,quoteId,trTag);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  
  public void doGetAccounts(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      int subId=Validator.convertToInteger(request.getParameter("subdocument_id"));
		if(subId==71){
			doGetAllAccount(request,response);
		}
		else if(subId==72){//bank Accounts
			doGetBankAccount(request,response);
		}
		else if(subId==73){//Cash Accounts
			doGetCashAccount(request,response);
		}
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetBankAccount(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getBankAccount(input, ui,createNew);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetCashAccount(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getCashAccount(input, ui,createNew);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  public void doGetAllAccount(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getAllAccount(input, ui,createNew);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  
  public void doGetOpeningAccount(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getOpeningAccount(input, ui,createNew);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  
  public void doGetAccountGroup(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getAccountGroup(input, ui);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetCurrency(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      System.out.println("doGetCurrency input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getCurrency(input, ui);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  
  public void doGetParentGroup(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getParentGroup(input, ui);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetAccountReceivedFrom(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertUndefinedToInteger(request.getParameter("create_new"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getAccountReceivedFrom(input, ui,createNew);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetAccountDepositTo(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertUndefinedToInteger(request.getParameter("create_new"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getAccountDepositTo(input, ui,createNew);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetAccountPaidTo(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertUndefinedToInteger(request.getParameter("create_new"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getAccountPaidTo(input, ui,createNew);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetJournalAccount(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertUndefinedToInteger(request.getParameter("create_new"));
	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getJournalAccount(input, ui,createNew);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  public void doGetGroupForAccount(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertUndefinedToInteger(request.getParameter("create_new"));
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getGroupForAccount(input, ui,createNew);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetAccountOfDutiesAndTax(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getAccountOfDutiesAndTax(input,createNew, ui);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  public void doGetAccountOfSales(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getAccountOfSales(input,createNew, ui);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  public void doGetAccountOfPurchase(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getAccountOfPurchase(input,createNew, ui);
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetAccountOfExpenditureDirExpense(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getAccountOfExpenditureDirExpense(input,createNew, ui);
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  public void doGetAccountOfExpenseLiability(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getAccountOfExpenseLiability(input,createNew, ui);
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  public void doGetCostCenter(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getCostCenter(input,createNew, ui);
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  public void doGetProfitCenter(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getProfitCenter(input,createNew, ui);
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetBSAccountGroupType(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getBSAccountGroupType(input,createNew, ui);
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
   
  public void doGetPLAccountGroupType(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getPLAccountGroupType(input,createNew, ui);
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  
  public void doGetOperation(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		try{
			String input = request.getParameter("q");
			int createNew=Validator.convertToInteger(request.getParameter("create_new"));

			PrintWriter writer = response.getWriter();
			response.setContentType("text/json;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			TPCSUser ui=new UserInfo().getUserInfo(request);

			String data = objAutoComplete.getOperation(input,createNew, ui);

			writer.print(data);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
  public void doGetGroupType(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		try{
			String input = request.getParameter("q");
			int createNew=Validator.convertToInteger(request.getParameter("create_new"));

			PrintWriter writer = response.getWriter();
			response.setContentType("text/json;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			TPCSUser ui=new UserInfo().getUserInfo(request);

			String data = objAutoComplete.getgrouptype(input,createNew, ui);

			writer.print(data);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
  public void doGetParentGroupType(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		try{
			String input = request.getParameter("q");
			int createNew=Validator.convertToInteger(request.getParameter("create_new"));

			PrintWriter writer = response.getWriter();
			response.setContentType("text/json;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			TPCSUser ui=new UserInfo().getUserInfo(request);

			String data = objAutoComplete.getparentgrouptype(input,createNew, ui);

			writer.print(data);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
  public void doGetSizeRange(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		try{
			String input = request.getParameter("q");
			int createNew=Validator.convertToInteger(request.getParameter("create_new"));

			PrintWriter writer = response.getWriter();
			response.setContentType("text/json;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			TPCSUser ui=new UserInfo().getUserInfo(request);

			String data = objAutoComplete.getSizeRange(input,createNew, ui);

			writer.print(data);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
  public void doGetSizeRangeSize(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		try{
			String input = request.getParameter("q");
			int createNew=Validator.convertToInteger(request.getParameter("create_new"));
			
			int dependId=Validator.convertToInteger(request.getParameter("depend_id"));
			
			
//			System.out.println("dependId :"+dependId);
			
			PrintWriter writer = response.getWriter();
			response.setContentType("text/json;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			TPCSUser ui=new UserInfo().getUserInfo(request);

			String data = objAutoComplete.getSizeRangeSize(input,createNew, ui,dependId);

			writer.print(data);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
  
  public void doGetSize(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		try{
			String input = request.getParameter("q");
			int createNew=Validator.convertToInteger(request.getParameter("create_new"));
			
			PrintWriter writer = response.getWriter();
			response.setContentType("text/json;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			TPCSUser ui=new UserInfo().getUserInfo(request);

			String data = objAutoComplete.getSize(input,createNew, ui);

			writer.print(data);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
  
  public void doGetColor(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		try{
			String input = request.getParameter("q");
			int createNew=Validator.convertToInteger(request.getParameter("create_new"));

			PrintWriter writer = response.getWriter();
			response.setContentType("text/json;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			TPCSUser ui=new UserInfo().getUserInfo(request);

			String data = objAutoComplete.getColor(input,createNew, ui);

			writer.print(data);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
  
  public void doGetVariant(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertUndefinedToInteger(request.getParameter("create_new"));
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getVariant(input,createNew, ui);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  public void doGetSizeShedule(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	    try{
	      String input = request.getParameter("q");
	      int createNew=Validator.convertToInteger(request.getParameter("create_new"));
//	      System.out.println("input :"+input);
	      PrintWriter writer = response.getWriter();
	      response.setContentType("text/json;charset=utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      TPCSUser ui=new UserInfo().getUserInfo(request);
	      String data = objAutoComplete.getSizeShedule(input,createNew, ui);     
	      writer.print(data);
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
  public void doGetAllReports(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		try{

			String input = request.getParameter("q");
			int createNew=Validator.convertToInteger(request.getParameter("create_new"));
			PrintWriter writer = response.getWriter();
			response.setContentType("text/json;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			TPCSUser ui=new UserInfo().getUserInfo(request);

			String data = objAutoComplete.getAllReports(input,createNew, ui);
			ui=null;
			writer.print(data);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
  
  public static AutoCompleteModel objAutoComplete = new AutoCompleteModel();
}
