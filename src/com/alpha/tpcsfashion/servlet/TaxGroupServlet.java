package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.TaxGroup;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.model.TaxGroupManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.Validator;

public class TaxGroupServlet {
	
public void doDisplayTaxGroup(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			String strSearchCriteria="";
			String requestType=request.getParameter("request_type");
			if("Search".equalsIgnoreCase(requestType)){       
				  strSearchCriteria=getSearchCriteria(request);
				  getAndSetAttributes(request);
		   }
			
			int pageCount= objMan.getPageCount(userInfo,pc, strSearchCriteria);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);
			
              List<TaxGroup> taxGroupList = objMan.getAllTaxGroup(userInfo,pc,strSearchCriteria); 
			request.setAttribute("tax_group_list", taxGroupList);
			
			if("Search".equalsIgnoreCase(requestType)){       
				getAndSetAttributes(request);
		   }
			
			rights =objRight.getUserRights(SubdocumentId.TAX_GROUP, userInfo);
			request.getSession().setAttribute("tg_rights",rights);
			
			userInfo=null;
			pc=null;
			taxGroupList=null;
			rights=null;
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_TAX_GROUP_MASTER, request,response);

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void doNewTaxGroup(HttpServletRequest request ,HttpServletResponse response)throws ServletException,IOException{
    	try {
			
    		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
    		String mode=request.getParameter("mode");
    		
    		int taxGroupId=Validator.convertToInteger(request.getParameter("tax_group_id"));
    		
    		taxList=objMan.getAllTax(userInfo);
    		request.setAttribute("taxList", taxList);
    		
    		String taxGroup="";
    		int rowCount=4;
    		if(mode.equalsIgnoreCase("e")){
    			
    			List<TaxGroup>	objList = objMan.getTaxGroupInfo(userInfo,taxGroupId);
    			for(TaxGroup obj:objList){
    				taxGroupId=obj.getTaxGroupId();
    				taxGroup=obj.getTaxGroup();
    				break;
    			}
    			request.setAttribute("tax_group_id", taxGroupId);
    			request.setAttribute("tax_group", taxGroup);
    		    request.setAttribute("taxGroupInfo", objList);
    		    rowCount= objList.size();
    		    objList=null;
    		}
    		
    		request.setAttribute("mode",mode);
    		request.setAttribute("row_count", rowCount);
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_TAX_GROUP_MASTER, request,response);

		}
    	catch (Exception e) {
			e.printStackTrace();
		}
}
	
	
	
	
	public void doSaveTaxGroup(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			 List<TaxGroup> objList=getTaxGroupParameter(request);
			 
			 String mode=request.getParameter("mode");
			 int taxGropuId=Validator.convertToInteger(request.getParameter("tax_group_id"));
			 
			 TaxGroup tg=new TaxGroup();
			 tg.setTaxGroup(request.getParameter("tax_group"));
			 tg.setStatus(Validator.convertToInteger(request.getParameter("tax_status")));
			 tg.setTaxGroupList(objList);
			 
			 taxGropuId= objMan.saveTaxGroup(userInfo,tg,mode,taxGropuId);
			
			 if(taxGropuId>0){
				request.setAttribute("success_message", "Tax group Inserted successfully");
			 }
			 else{
				 request.setAttribute("error_message", "Tax group Insertion failed");
			 }
			 
			    
			 request.setAttribute("tax_group_id",taxGropuId);
	     	 String saveType=request.getParameter("save_type");
			String taxGroup="";
			int status=0;
			
			if(saveType.equals("1")){
				
				objList = objMan.getTaxGroupInfo(userInfo,taxGropuId);
				
				taxList=objMan.getAllTax(userInfo);
				
	    		request.setAttribute("taxList", taxList);
		        request.setAttribute("taxGroupInfo", objList);
		        
		        for(TaxGroup obj:objList){
		        	
		        	taxGropuId=obj.getTaxGroupId();
    				taxGroup=obj.getTaxGroup();
    				status=obj.getStatus();
    				
    			}
    			request.setAttribute("tax_group_id", taxGropuId);
    			request.setAttribute("tax_group", taxGroup);
    			request.setAttribute("status", status);
    			
		        request.setAttribute("mode", "e");
		    	request.setAttribute("row_count", objList.size());
		    	
		    	objList=null;
		    	tg=null;
		        TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_TAX_GROUP_MASTER, request,response);
					
			  }
			 else if(saveType.equals("2")){
				 
				 taxList=objMan.getAllTax(userInfo);
				 request.setAttribute("taxList", taxList);
				 request.setAttribute("mode", "n");
				 
		    	TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_TAX_GROUP_MASTER, request,response);
			 }
			
			 else if(saveType.equals("3")){
				 doDisplayTaxGroup(request,response) ;
			 }
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private List<TaxGroup> getTaxGroupParameter(HttpServletRequest request) {

		List<TaxGroup> objList=new ArrayList<TaxGroup>();

		//objT.setTaxGroup(request.getParameter("tax_group"));
		//objT.setMode(request.getParameter("mode"));
		//objT.setTaxGroupId(Validator.convertToInteger(request.getParameter("tax_group_id")));
		String rowIds=request.getParameter("row_ids");
		String[] arRowIds=rowIds.split(",");

		for(int i=0;i<arRowIds.length;i++){

			TaxGroup obj=new TaxGroup();

			if(!arRowIds[i].isEmpty()){

				obj.setTaxGroup(request.getParameter("tax_group"));
				
				obj.setStatus(Validator.convertToInteger(request.getParameter("tax_status")));
				obj.setTaxId(Validator.convertToInteger(request.getParameter("tax_id_"+arRowIds[i])));
				obj.setTaxPercent(Validator.convertToFloat(request.getParameter("tax_percentage_"+arRowIds[i])));
//				obj.setTaxSlno(Validator.convertToInteger(request.getParameter("tax_slno_"+arRowIds[i])));
				/*obj.setDisplayOrder(Validator.convertToInteger(request.getParameter("display_order_"+arRowIds[i])));*/
			}
			objList.add(obj);
			obj=null;
		}
		return objList;
	}

	

public void getAndSetAttributes(HttpServletRequest request){
	request.setAttribute("tax_group",request.getParameter("tax_group"));
	request.setAttribute("tax",request.getParameter("tax"));
	request.setAttribute("tg_status",request.getParameter("tg_status"));
	
}
public String getSearchCriteria(HttpServletRequest request){
    
	String taxGroup=request.getParameter("tax_group");
	String tax=request.getParameter("tax");
	int status=Validator.convertToInteger(request.getParameter("tg_status"));
	
	 String searchCriteria="";

	if(taxGroup!=null && !taxGroup.isEmpty())
	{
		searchCriteria=searchCriteria+"   tg.tax_group='"+taxGroup+"'  AND";
	}
	if(tax!=null && !tax.isEmpty())
	{
		searchCriteria=searchCriteria+"   tm.Tax_Name ='"+tax+"'  AND";
	}
	if(status!=-1){
		searchCriteria=searchCriteria+"   tg.status ="+status+"  AND";
	}
    if(searchCriteria!=null && !searchCriteria.isEmpty()){
    	searchCriteria = searchCriteria.substring(1,searchCriteria.length()-4);
    }
   
	return searchCriteria;
     
   }
public void doDeleteTaxGroup(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
{
	
	TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
	int taxGroupId=Validator.convertToInteger(request.getParameter("tax_group_id"));
	
	
	TaxGroup objT=new TaxGroup();
	objT.setTaxGroupId(taxGroupId);
	
	 objT=objMan.deleteTaxGroup(userInfo,objT);
	
	
	   /*if(objT.isTaxGroupExists()){
			request.setAttribute("error_message","order/invoice Transactions are entered for this tax group, we should not delete this tax group"); 
		}
		else*/
			
			if(objT.isDeleted())
			{
				request.setAttribute("success_message","Tax Group deleted successfully");
			}
			else
			{
				request.setAttribute("error_message","failed");
			}
		
	 
	doDisplayTaxGroup(request, response);
	objT=null;
}

public void doDeleteTaxGroupRow(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
{

	TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
	int taxGroupId=Validator.convertToInteger(request.getParameter("tax_group_id"));
	int taxId=Validator.convertToInteger(request.getParameter("tax_id"));
	boolean isDeleted=objMan.deleteTaxGroupRow(userInfo,taxGroupId,taxId);
	
	taxList=objMan.getAllTax(userInfo);
	request.setAttribute("taxList", taxList);
	String taxGroup="";
	if(isDeleted){
		request.setAttribute("success_message","Tax deleted successfully");
	}
	else{
		request.setAttribute("error_message","failed");
	}
	List<TaxGroup>	objList = objMan.getTaxGroupInfo(userInfo,taxGroupId);
	for(TaxGroup obj:objList){
		taxGroupId=obj.getTaxGroupId();
		taxGroup=obj.getTaxGroup();

	}
	request.setAttribute("tax_group_id", taxGroupId);
	request.setAttribute("tax_group", taxGroup);
	request.setAttribute("taxGroupInfo", objList);
	request.setAttribute("mode", "e");
	request.setAttribute("row_count", objList.size());
	
	userInfo=null;
	objList=null;
	TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_TAX_GROUP_MASTER, request,response);
}
public void doGetTax(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	try{ 
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");

		taxList=objMan.getAllTax(userInfo);
		
		StringBuffer buffer=new StringBuffer();
		buffer.append("<taxes>");
		for(TaxGroup tg:taxList){
			buffer.append("<tax>");

			buffer.append("<taxid>");
			buffer.append(tg.getTaxId());
			buffer.append("</taxid>");
			buffer.append("<taxname>");
			buffer.append(tg.getTaxName());
			buffer.append("</taxname>");
			
			buffer.append("</tax>");
		}
		buffer.append("</taxes>");

		response.setContentType("text/xml");
		response.getWriter().write(buffer.toString());
		buffer=null;

	}
	catch(Exception e){
		e.printStackTrace();
	}
}
public void doDeleteBulkTaxGroup(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
{
	
	TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
//	int taxGroupId=Validator.convertToInteger(request.getParameter("tax_group_id"));
	String[] taxgroup_id=request.getParameterValues("taxgroup_id");
	
	TaxGroup objT=new TaxGroup();
	
	int taxGroupId;
	for(int i=0;i<taxgroup_id.length;i++){
		
		taxGroupId=Integer.parseInt(taxgroup_id[i]);	
		
		objT.setTaxGroupId(taxGroupId);
		
		objT=objMan.deleteTaxGroup(userInfo,objT);
	
	}
	
	
	   if(objT.isTaxGroupExists()){
			request.setAttribute("error_message","order/invoice Transactions are entered for this tax group, we should not delete this tax group"); 
		}
		else{
			
			if(objT.isDeleted())
			{
				request.setAttribute("success_message","Tax Group deleted successfully");
			}
			else
			{
				request.setAttribute("error_message","failed");
			}
		}
	 
	doDisplayTaxGroup(request, response);
	objT=null;
}	 

public void doBulkActiveTaxgroup(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
{
	boolean isBulkAction=false;
	TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
	String[] taxgroup_id=request.getParameterValues("taxgroup_id");
	String active_mode=request.getParameter("active_mode");
	isBulkAction=objMan.bulkActiveTaxgroup(userInfo,taxgroup_id,active_mode);
	if (isBulkAction==true)
	{
		if(active_mode.equals("1")){
		 request.setAttribute("success_message","TaxGroup Activated!");
		}
		else{
		 request.setAttribute("success_message","TaxGroup Inactivated!");
		}
	}

	if (isBulkAction== false){
		
		if(active_mode.equals("1")){
		 request.setAttribute("error_message","TaxGroup Not Activated!");
		}
		else{
		 request.setAttribute("error_message","TaxGroup Not Inactivated!");
		}
	}
		
	doDisplayTaxGroup(request, response);
	userInfo=null;
}	
	 
	private TaxGroupManager objMan=new TaxGroupManager();
	List<TaxGroup> taxList;
	UserRights rights=null;
	private UserRightsManager objRight = new UserRightsManager();
}
