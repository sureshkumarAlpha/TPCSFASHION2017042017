package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alpha.tpcsfashion.beans.OperationOrStage;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.model.OperationOrStageManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.Validator;

public class OperationOrStageServlet {

	public void doDisplayOperationOrStage(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

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

			List<OperationOrStage> operationList = objManager.getOperationOrStage(userInfo,pc,strSearhQuery); 

			request.setAttribute("operation_list", operationList);

			session.setAttribute("seletedScreenId",1);

			doGetUserRights(request,response);

			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_OPERATION, request,response);

			operationList=null;
			pc=null;
			userInfo=null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void doNewOperationOrStage(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {

			//		String mode=request.getParameter("mode");

			request.setAttribute("mode","n");
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_OPERATION, request,response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private OperationOrStage setAndGetParametter(HttpServletRequest request,HttpServletResponse response){
		int operation_id=Validator.convertToInteger(request.getParameter("operation_id"));
		String operation=request.getParameter("operation");
		int status=Validator.convertToInteger(request.getParameter("op_status"));
		int seq_no=Validator.convertToInteger(request.getParameter("seq_no"));
		String description=request.getParameter("description");
		int displayOrder=Validator.convertToInteger(request.getParameter("display_order"));
		int inspectionRequired=Validator.convertToInteger(request.getParameter("inspection_Required"));
		int production=Validator.convertToInteger(request.getParameter("production"));
		String mainGroup=request.getParameter("main_group");
		String subGroup=request.getParameter("sub_group");

		OperationOrStage opObj=new OperationOrStage();

		opObj.setOperationId(operation_id);
		opObj.setOperationName(operation);
		opObj.setOperationStatus(status);
		opObj.setOperationSeqNo(seq_no);
		opObj.setOperationDesc(description);
		opObj.setDisplayOrder(displayOrder);
		opObj.setInspectionRequired(inspectionRequired);
		opObj.setProduction(production);
		opObj.setMainGroup(mainGroup);
		opObj.setSubGroup(subGroup);
		return opObj;
	}

	public void doSaveOperationOrStage(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

		try {
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			//		 	  HttpSession session = request.getSession();




			int operation_id=Validator.convertToInteger(request.getParameter("operation_id"));
			String operation=request.getParameter("operation");
			int status=Validator.convertToInteger(request.getParameter("op_status"));
			int seq_no=Validator.convertToInteger(request.getParameter("seq_no"));
			String description=request.getParameter("description");

			int displayOrder=Validator.convertToInteger(request.getParameter("display_order"));
			int inspectionRequired=Validator.convertToInteger(request.getParameter("inspection_Required"));
			int production=Validator.convertToInteger(request.getParameter("production"));
			String mainGroup=request.getParameter("main_group");
			String subGroup=request.getParameter("sub_group");
			//TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			String mode=request.getParameter("mode");


			OperationOrStage opObj=new OperationOrStage();

			opObj.setOperationId(operation_id);
			opObj.setOperationName(operation);
			opObj.setOperationStatus(status);
			opObj.setOperationSeqNo(seq_no);
			opObj.setOperationDesc(description);

			opObj.setDisplayOrder(displayOrder);
			opObj.setInspectionRequired(inspectionRequired);
			opObj.setProduction(production);
			opObj.setMainGroup(mainGroup);
			opObj.setSubGroup(subGroup);
			opObj.setMode(mode);



			int opId=0;

			if(!objManager.isMasterOperationOrStageExist(userInfo,opObj))
			{
				opId = objManager.insertOperationOrStage(opObj,userInfo);
				//				  System.out.println("trId--->"+trId);
				if(opId>0)
				{
					mode="e";
					request.setAttribute("success_message",bundle.getString("Operation.OperationInserted")); 
				}
				else{
					mode="n";
					request.setAttribute("error_message",bundle.getString("Operation.OperationNotInserted")); 
				}
			}
			else
			{
				mode="n";
				request.setAttribute("error_message","Operation not inserted.Operation Already Exist!"); 
			}


			String saveType=request.getParameter("save_type");

			if(saveType.equals("1")){
				if(opId>0){
					opObj=objManager.getOperationOrStageInfo(1,userInfo,opId);
					request.setAttribute("operation_info", opObj);
					request.setAttribute("mode", mode);
				}
				else{
					OperationOrStage coObj1=new OperationOrStage();
					coObj1=setAndGetParametter(request,response);
					request.setAttribute("operation_info",coObj1);
				}

				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_OPERATION, request,response);
			}

			else if(saveType.equals("3"))
			{
				doDisplayOperationOrStage(request,response) ;
			}
			else{
				request.setAttribute("mode","n");
				doNewOperationOrStage(request,response);
			}

			opObj=null;
			userInfo=null;
			bundle=null;
		} catch (Exception e) {
			// TODO: handle exception 
			e.printStackTrace();
		}
	}
	public void doEditOperationOrStage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{

		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		OperationOrStage opObj=new OperationOrStage();
		String mode=request.getParameter("mode");
		opObj.setMode(mode);
		request.setAttribute("mode", mode);
		int operation_id=Validator.convertToInteger(request.getParameter("operation_id"));

		opObj=objManager.getOperationOrStageInfo(1,userInfo,operation_id);
		request.setAttribute("operation_info", opObj);


		TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_OPERATION, request,response);
		opObj=null;
		userInfo=null;
	}
	public void doDeleteOperationOrStageMaster(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
		boolean isDeleted=false;
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		int operation_id=Validator.convertToInteger(request.getParameter("operation_id"));

		isDeleted=objManager.deleteOperationOrStageMaster(userInfo,operation_id);
		if (isDeleted==true)
		{
			request.setAttribute("success_message", bundle.getString("Operation.OperationDeleted"));
		}

		if (isDeleted== false){
			request.setAttribute("error_message",bundle.getString("Operation.OperationNotDeleted") );
		}

		doDisplayOperationOrStage(request, response);
		bundle=null;
		userInfo=null;
	}

	public void doDeActiveOperationOrStage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		boolean isDeActive=false;
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		int operation_id=Validator.convertToInteger(request.getParameter("operation_id"));

		isDeActive=objManager.deActiveOperationOrStage(userInfo,operation_id);
		if (isDeActive==true)
		{
			request.setAttribute("success_message","Operation Inactivated!");
		}

		if (isDeActive== false){
			request.setAttribute("error_message","Operation Not Inactivated!");
		}

		doDisplayOperationOrStage(request, response);
		userInfo=null;
	}

	public void doBulkActionOperationOrStage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		boolean isBulkAction=false;
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		String[] operation_id=request.getParameterValues("bulkoperatonId");
		String active_mode=request.getParameter("active_mode");

		isBulkAction=objManager.bulkActionOperationOrStage(userInfo,operation_id,active_mode);
		if (isBulkAction==true)
		{
			if(active_mode.equals("1")){
				request.setAttribute("success_message","Operation Activated!");
			}
			else{
				request.setAttribute("success_message","Operation Inactivated!");
			}
		}

		if (isBulkAction== false){

			if(active_mode.equals("1")){
				request.setAttribute("error_message","Operation Not Activated!");
			}
			else{
				request.setAttribute("error_message","Operation Not Inactivated!");
			}
		}

		doDisplayOperationOrStage(request, response);
		userInfo=null;
	}

	public void doDeleteBulkOperation(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		String[] optId=request.getParameterValues("bulkoperatonId");
		OperationOrStage sr=new OperationOrStage();
		int operationId=0;

		for(int i=0;i<optId.length;i++){

			operationId=Integer.parseInt(optId[i]);	
			sr.setOperationId(operationId);
			sr=objManager.deleteBulkOperation(userInfo,sr);
		}
		if(sr.isDeleted()){
			request.setAttribute("success_message","Operation deleted successfully");
		}
		else{
			request.setAttribute("error_message","failed");
		}

		doDisplayOperationOrStage(request, response);
		sr=null;
	}	
	public String getSearchCriteria(HttpServletRequest request){

		String strSearchCriteria="";
		int operation_id=Validator.convertToInteger(request.getParameter("operation_id"));
		int seq_no=Validator.convertToInteger(request.getParameter("seq_no"));

		if(operation_id>0){
			strSearchCriteria=strSearchCriteria+" Operation_id="+operation_id+" and ";
		}
		if(seq_no>0){
			strSearchCriteria=strSearchCriteria+" Seqno="+seq_no+" and ";
		}
		if(!strSearchCriteria.isEmpty()){
			strSearchCriteria=strSearchCriteria.substring(0,strSearchCriteria.length()-4);
		}

		return strSearchCriteria;
	}
	public void getAndSetAttributes(HttpServletRequest request){

		request.setAttribute("operation",request.getParameter("operation"));
		request.setAttribute("operation_id",request.getParameter("operation_id"));
		request.setAttribute("seq_no",request.getParameter("seq_no"));
	}
	public void doGetUserRights(HttpServletRequest request, HttpServletResponse response)
	{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		HttpSession session=request.getSession();

		rights =objRight.getUserRights(SubdocumentId.OPERATION_MASTER, userInfo);
		session.setAttribute("operation_rights",rights);

	}

	OperationOrStageManager objManager=new OperationOrStageManager();
	UserRights rights=null;
	private UserRightsManager objRight = new UserRightsManager();

}
