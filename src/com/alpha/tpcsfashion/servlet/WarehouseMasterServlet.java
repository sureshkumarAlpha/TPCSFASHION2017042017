package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.beans.Warehouse;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.model.WarehouseMasterManager;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.Validator;

public class WarehouseMasterServlet {

public void doDisplayWarehouse(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	
	try {
		HttpSession session=request.getSession();
		TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
		//This is for page no..
		PageConfig pc=new PageConfig(request.getParameter("pageno"));
		String strSearhQuery="";
		
		 if("Search".equalsIgnoreCase(request.getParameter("request_type"))){
		strSearhQuery = getSearchCriteria(request);
		 getAndSetAttributes(request);
		 }
		
		int pageCount= objManager.getPageCount(userInfo,strSearhQuery);
		pc.setPageCount(pageCount);
		request.setAttribute("pc", pc);
		
		List<Warehouse> warehouseList = objManager.getWarehouse(userInfo,pc,strSearhQuery); 
		
		request.setAttribute("warehouse_list", warehouseList);
			
			session.setAttribute("seletedScreenId",1);
			
			doGetUserRights(request,response);
		
		TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_WAREHOUSE, request,response);
		
		warehouseList=null;
		pc=null;
		userInfo=null;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}




public void doNewWarehouse(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	try {
		
//		String mode=request.getParameter("mode");
		//System.out.println("OK");
		 request.setAttribute("mode","n");
		TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_WAREHOUSE, request,response);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}



public void doSaveWarehouse(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	
	try {
		 TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		 ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
	 	  HttpSession session = request.getSession();
	 	  
	            
            
	      
		int storeId=Validator.convertToInteger(request.getParameter("store_id"));
		String storeCode=request.getParameter("store_code");
		String storeName=request.getParameter("store_name");
		
		int storeStatus=Validator.convertToInteger(request.getParameter("store_status"));
		//TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		String mode=request.getParameter("mode");
		
	    System.out.println("storeId:"+storeId);
	    System.out.println("storeCode:"+storeCode);
	    System.out.println("storeName:"+storeName);
	    System.out.println("storeStatus:"+storeStatus);
	    System.out.println("mode:"+mode);
		Warehouse opObj=new Warehouse();
		
		opObj.setStoreId(storeId);
		opObj.setStoreCode(storeCode);
		opObj.setStoreName(storeName);
		opObj.setStoreStatus(storeStatus);
		opObj.setMode(mode);
		
							
	
         int stId=0;
        // obj=objMan.isVariantCodeNameExist(userInfo,obj,mode);
         
         
        	 if(!objManager.isMasterWarehouseCodeNameExist(userInfo,opObj))
         {
			   stId = objManager.insertWarehouse(opObj,userInfo);
			 // System.out.println("stId--->"+stId);
		       if(stId>0)
		         {
		    	   mode="e";
		    	   request.setAttribute("success_message",bundle.getString("Warehouse.WarehouseInserted")); 
		         }
		       else{
		    	    mode="n";
		    	   request.setAttribute("error_message",bundle.getString("Warehouse.WarehouseNotInserted")); 
		       }
         }
		 else
		 {
			 mode="n";
		 request.setAttribute("error_message","Warehouse not inserted.Warehouse Already Exist!"); 
		 }

		request.setAttribute("store_id",stId);
		String saveType=request.getParameter("save_type");
		 
		if(saveType.equals("1"))
		  {
			opObj=objManager.getWarehouseInfo(1,userInfo,stId);
				
				request.setAttribute("warehouse_info", opObj);
				request.setAttribute("mode", mode);
				 
			 	TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_WAREHOUSE, request,response);

		  }
		 else if(saveType.equals("2"))
		 {
			 request.setAttribute("mode","n");
			 doNewWarehouse(request,response);
			
		 }
		 else{
			 doDisplayWarehouse(request,response) ;
		 }
		
		opObj=null;
		userInfo=null;
		bundle=null;
	          } catch (Exception e) {
		// TODO: handle exception 
		e.printStackTrace();
	}
}


public void doEditWarehouse(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
{

	TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
	Warehouse opObj=new Warehouse();
	String mode=request.getParameter("mode");
	opObj.setMode(mode);
	request.setAttribute("mode", mode);
	int storeId=Validator.convertToInteger(request.getParameter("store_id"));

	opObj=objManager.getWarehouseInfo(1,userInfo,storeId);
	request.setAttribute("warehouse_info", opObj);
	

	TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_WAREHOUSE, request,response);
	opObj=null;
	userInfo=null;
}


public void doDeleteWarehouse(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
{
	ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
	boolean isDeleted=false;
	Warehouse opObj=new Warehouse();
	TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
	int store_id=Validator.convertToInteger(request.getParameter("store_id"));
	System.out.println("store_id"+store_id);
	opObj.setStoreId(store_id);
	opObj=objManager.deleteWarehouse(userInfo,opObj);
	
	if (opObj.isDeleted())
	{
		request.setAttribute("success_message", bundle.getString("Warehouse.WarehouseDeleted"));
	}

	else{
		request.setAttribute("error_message",bundle.getString("Warehouse.WarehouseNotDeleted") );
	}
		
	doDisplayWarehouse(request, response);
	bundle=null;
	userInfo=null;
}


public void doDeleteBulkWarehouse(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
{
	
	ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
	boolean isDeleted=false;
	Warehouse opObj=new Warehouse();
	TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
//	int store_id=Validator.convertToInteger(request.getParameter("store_id"));
	String[] store_ids=request.getParameterValues("store_id");
	
	for(int i=0;i<store_ids.length;i++){
		
		int storeId=Integer.parseInt(store_ids[i]);
	opObj.setStoreId(storeId);
	opObj=objManager.deleteWarehouse(userInfo,opObj);
	}
	
	if (opObj.isDeleted())
	{
		request.setAttribute("success_message", bundle.getString("Warehouse.WarehouseDeleted"));
	}

	else{
		request.setAttribute("error_message",bundle.getString("Warehouse.WarehouseNotDeleted") );
	}
		
	doDisplayWarehouse(request, response);
	bundle=null;
	userInfo=null;
	
	
	
}




public String getSearchCriteria(HttpServletRequest request){

	String strSearchCriteria="";
	int store_id=Validator.convertToInteger(request.getParameter("store_id"));
    String store_code=request.getParameter("store_code");
	
	if(store_id>0){
		strSearchCriteria=strSearchCriteria+" store_id="+store_id+" and ";
		}
	
	if(!store_code.isEmpty()){
		strSearchCriteria=strSearchCriteria+" store_code='"+store_code+"' and ";
		}
	if(!strSearchCriteria.isEmpty()){
   	 strSearchCriteria=strSearchCriteria.substring(0,strSearchCriteria.length()-4);
    }

	return strSearchCriteria;
}

public void doDeActiveWarehouse(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
{
	boolean isDeActive=false;
	TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
	int store_id=Validator.convertToInteger(request.getParameter("store_id"));
	
	isDeActive=objManager.deActiveWarehouse(userInfo,store_id);
	if (isDeActive==true)
	{
		 request.setAttribute("success_message","Warehouse Inactivated!");
	}

	if (isDeActive== false){
		 request.setAttribute("error_message","Warehouse Not Inactivated!");
	}
		
	doDisplayWarehouse(request, response);
	userInfo=null;
}


public void doBulkActionWarehouse(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
{
	boolean isBulkAction=false;
	TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
	String[] store_id=request.getParameterValues("bulkoperatonId");
	String active_mode=request.getParameter("active_mode");
	
	isBulkAction=objManager.bulkActionWarehouse(userInfo,store_id,active_mode);
	if (isBulkAction==true)
	{
		if(active_mode.equals("1")){
		 request.setAttribute("success_message","Warehouse Activated!");
		}
		else{
		 request.setAttribute("success_message","Warehouse Inactivated!");
		}
	}

	if (isBulkAction== false){
		
		if(active_mode.equals("1")){
		 request.setAttribute("error_message","Warehouse Not Activated!");
		}
		else{
		 request.setAttribute("error_message","Warehouse Not Inactivated!");
		}
	}
		
	doDisplayWarehouse(request, response);
	userInfo=null;
}





public void getAndSetAttributes(HttpServletRequest request){
	
	request.setAttribute("store_name",request.getParameter("store_name"));
	request.setAttribute("store_id",request.getParameter("store_id"));
	request.setAttribute("store_code",request.getParameter("store_code"));
}


 public void doGetUserRights(HttpServletRequest request, HttpServletResponse response)
{
	TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
	HttpSession session=request.getSession();
	
	rights =objRight.getUserRights(SubdocumentId.WAREHOUSE, userInfo);
	session.setAttribute("warehouse_rights",rights);
	
}
	 
    WarehouseMasterManager objManager=new WarehouseMasterManager();
    UserRights rights=null;
	private UserRightsManager objRight = new UserRightsManager();

}
