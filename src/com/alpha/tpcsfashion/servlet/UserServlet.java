package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alpha.tpcsfashion.beans.Generic;
import com.alpha.tpcsfashion.beans.Profiles;
import com.alpha.tpcsfashion.beans.Role;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.User;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.model.EntityManager;
import com.alpha.tpcsfashion.model.UserManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.model.ValidateUser;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.UserInfo;
import com.alpha.tpcsfashion.util.Validator;

public class UserServlet {
	public void doDisplayUsers(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{ 
			TPCSUser ui=new UserInfo().getUserInfo(request);
			HttpSession session = request.getSession();
			//	            if(session.getAttribute("factory_list")==null){
			//	            	factories = objManager.getFactory(ui);
			//	            	customers = objManager.getCustomer(ui); 
			entities = objEntManager.getEntity(ui);               
			session.setAttribute("entity_list", entities);
			//	              session.setAttribute("factory_list", factories);
			//	              session.setAttribute("customer_list", customers);             

			//	            }
			int iHOId = Integer.parseInt((String)session.getAttribute("ho_id"));
			int iLCustomerId = Integer.parseInt((String)session.getAttribute("customer_id"));        
			int iLFactoryId = Integer.parseInt((String)session.getAttribute("factory_id"));
			User objUser = new User();
			if(iHOId==1){
				String strEntity = request.getParameter("entity_id");
				int iEntityId = (strEntity!=null&&strEntity!=""?Integer.parseInt(strEntity):-1);
				objUser.setEntityId(iEntityId);           
				String strCustomer = request.getParameter("customer_id");
				int iCustomerId = (strCustomer!=null&&strCustomer!=""?Integer.parseInt(strCustomer):-1); 
				objUser.setCustomerId(iCustomerId);
				String strFactory = request.getParameter("factory_id");
				int iFactoryId = (strFactory!=null&&strFactory!=""?Integer.parseInt(strFactory):-1);  
				objUser.setFactoryId(iFactoryId); 
			}
			else if(iLCustomerId!=-1)
			{
				objUser.setEntityId(iHOId);
				objUser.setCustomerId(iLCustomerId);
				objUser.setFactoryId(iLFactoryId); 
			}
			else if(iLFactoryId!=-1)
			{
				objUser.setEntityId(iHOId);
				objUser.setCustomerId(iLCustomerId);
				objUser.setFactoryId(iLFactoryId); 
			}


			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			pageCount = objManager.getTotalUsers(objUser,ui);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);

			users = objManager.getUsers(objUser,ui,pc);         
			request.setAttribute("user_list", users); 
			request.setAttribute("user_info", objUser); 
			request.setAttribute("ho_id", iHOId);
			request.setAttribute("lcustomer_id", iLCustomerId);
			request.setAttribute("lfactory_id", iLFactoryId);


			int iUserId = Integer.parseInt((String)session.getAttribute("login_user_id"));
			//get rights permission begins
			rights = objRight.getUserRights(SubdocumentId.USER,ui);
			session.setAttribute("rights", rights);
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_USER,request,response);
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}

	public void doNewUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{  
			TPCSUser ui=new UserInfo().getUserInfo(request);
			HttpSession session = request.getSession();
			int iHOId = Integer.parseInt((String)session.getAttribute("ho_id"));
			int iLCustomerId = Integer.parseInt((String)session.getAttribute("customer_id"));        
			int iLFactoryId = Integer.parseInt((String)session.getAttribute("factory_id"));
			User objUser = new User();
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);

			if(iLCustomerId!=-1){
				objUser.setEntityId(iHOId);
				objUser.setCustomerId(iLCustomerId);
				objUser.setFactoryId(iLFactoryId); 
			}
			else if(iLFactoryId!=-1){
				objUser.setEntityId(iHOId);
				objUser.setCustomerId(iLCustomerId);
				objUser.setFactoryId(iLFactoryId); 
			}

			int isSysAdmin= objManager.getIsSysAdmin(ui);
			request.setAttribute("is_sys_admin", isSysAdmin); 

			boolean bFlag = objManager.checkNoOfUsers(ui);   
			if(bFlag){

				languages = objManager.getLanguage();         
				request.setAttribute("language_list", languages); 
				request.setAttribute("user_info", objUser); 
				request.setAttribute("ho_id", iHOId);
				request.setAttribute("lcustomer_id", iLCustomerId);
				request.setAttribute("lfactory_id", iLFactoryId);
				request.setAttribute("mode", "n");
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_USER,request,response);  

			}
			else{
				request.setAttribute("error_message", bundle.getString("User.NoOfPurchasedUsersCreated"));
				doDisplayUsers(request,response);
				/* TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_USERS,request,response);*/

			}


		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);    
		}
	}

	public void doGetEntityRoles(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser ui=new UserInfo().getUserInfo(request);
			int ientityId = Integer.parseInt(request.getParameter("entity_id")); 
			int icustomerId = Integer.parseInt(request.getParameter("customer_id")); 
			int ifactoryId = Integer.parseInt(request.getParameter("factory_id")); 
			response.setContentType("text/xml");        
			String strRoles = objManager.getEntityRoles(ientityId,icustomerId,ifactoryId,ui);
			response.getWriter().write(strRoles);
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);    
		}
	}

	public void doGetEntityProfiles(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{  
			TPCSUser ui=new UserInfo().getUserInfo(request);
			int ientityId = Integer.parseInt(request.getParameter("entity_id"));          
			response.setContentType("text/xml");        
			String strProfiles = objManager.getEntityProfiles(ientityId,ui);
			response.getWriter().write(strProfiles);
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);    
		}
	}

	//	    public void doDisplayProfileDetails(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	//	        try{       	  
	//	        	
	//	        	 int iProfileId = Integer.parseInt(request.getParameter("profile_id")); 
	//	        	 int iType = Integer.parseInt(request.getParameter("type"));  
	//	        	 int userId = Integer.parseInt(request.getParameter("user_id"));   
	//	        	 request.setAttribute("profile_id", iProfileId); 
	//	        	 request.setAttribute("itype", iType);  
	//	        	 request.setAttribute("user_id", userId);  
	//	            TPCSCommonUtil.RedirectURL(TPCSRedirectPage.VIEW_PROFILE_INFO,request,response);
	//	        }catch(Exception e){
	//	            e.printStackTrace();
	//	            //NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
	//	        }
	//	      }

	public void doGetProfileDocuments(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{   
			TPCSUser ui=new UserInfo().getUserInfo(request);
			List<Integer> list = new ArrayList<Integer>();
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			response.setContentType("text/html");       	   
			int  profileid = Integer.parseInt(request.getParameter("profile_id"));  
			int  iType = Integer.parseInt(request.getParameter("itype"));   
			int  userId = Integer.parseInt(request.getParameter("user_id"));   
			//	     	      int iBomWidth = Integer.parseInt(request.getParameter("bomWidth")); 
			String strDocument;
			if(iType==1)
			{
				strDocument = objManager.getProfileDocumentList(bundle,profileid,list,ui);
			}
			else
			{
				strDocument = objManager.getUserProfileDocumentList(bundle,profileid,userId,list,ui);
			}
			response.getWriter().write(String.valueOf(strDocument));
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);    
		}
	}



	public void doSaveUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser ui=new UserInfo().getUserInfo(request);
			String entrySelectedDocuments = null;   
			String editSelectedDocuments = null; 
			String deleteSelectedDocuments = null; 
			String viewSelectedDocuments = null; 
			String excelSelectedDocuments = null; 
			String printSelectedDocuments = null; 
			String apprSelectedDocuments = null; 
			String userName = null;
			String firstName = null;
			String lastName = null;


			userName=request.getParameter("user_name");
			firstName=request.getParameter("first_name");
			lastName=request.getParameter("last_name");
			int iEntityId = Integer.parseInt(request.getParameter("entity_id"));
			int iCustomerId = Integer.parseInt(request.getParameter("customer_id"));
			int iFactoryId = Integer.parseInt(request.getParameter("factory_id"));
			int iRoleId = Integer.parseInt(request.getParameter("role_id"));
			int iProfileId = Integer.parseInt(request.getParameter("profile_id"));
			int iLanguageId = Integer.parseInt(request.getParameter("language_id"));
			entrySelectedDocuments = request.getParameter("entry_documents");
			editSelectedDocuments = request.getParameter("edit_documents");
			deleteSelectedDocuments = request.getParameter("delete_documents");
			viewSelectedDocuments = request.getParameter("view_documents");
			excelSelectedDocuments = request.getParameter("excel_documents");
			printSelectedDocuments = request.getParameter("print_documents");
			apprSelectedDocuments = request.getParameter("appr_documents");

			int isSysAdmin = Validator.convertToInteger(request.getParameter("is_sys_admin"));
			int isActive = Validator.convertToInteger(request.getParameter("is_active"));



			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);

			User objUser=new User();                     
			Role objRole=new Role();
			Profiles objProfiles=new Profiles();
			objUser.setUserName(userName);
			objUser.setFirstName(firstName);
			objUser.setLastName(lastName);
			objUser.setEntityId(iEntityId);
			objUser.setCustomerId(iCustomerId);
			objUser.setFactoryId(iFactoryId);
			objRole.setRoleId(iRoleId);
			objProfiles.setProfileId(iProfileId);
			objProfiles.setEntityEntryDocuments(entrySelectedDocuments);
			objProfiles.setEntityEditDocuments(editSelectedDocuments);
			objProfiles.setEntityDeleteDocuments(deleteSelectedDocuments);
			objProfiles.setEntityViewDocuments(viewSelectedDocuments);
			objProfiles.setEntityExcelDocuments(excelSelectedDocuments);
			objProfiles.setEntityPrintDocuments(printSelectedDocuments);
			objProfiles.setEntityApprDocuments(apprSelectedDocuments);
			objUser.setRoles(objRole);
			objUser.setProfiles(objProfiles);
			objUser.setLanguageId(iLanguageId);
			objUser.setIsSystemAdmin(isSysAdmin);
			objUser.setIsActive(isActive);
			HttpSession session = request.getSession();
			int clientId = Integer.parseInt((String)session.getAttribute("client_id"));
			int loginId = Integer.parseInt((String)session.getAttribute("login_user_id"));

			if(objManager.isSubscriptionExceed(clientId)){       

				if(!objManager.isUserExist(objUser)){       	 

					User retClientVal= objManager.insertClientInfo(objUser,clientId);
					User retVal = objManager.insertUser(objUser,retClientVal.getUserId(),ui);
					objManager.mailPassword(retClientVal,loginId);
					if(retVal.isSuccess()){


						PageConfig pc=new PageConfig(request.getParameter("pageno"));
						pageCount = objManager.getTotalUsers(objUser,ui);
						pc.setPageCount(pageCount);
						request.setAttribute("pc", pc);            

						users = objManager.getUsers(objUser,ui,pc);         
						request.setAttribute("user_list", users); 
						request.setAttribute("user_info", objUser); 
						request.setAttribute("success_message", bundle.getString("User.SavedSuccesfully")); 
						request.setAttribute("mode", request.getParameter("mode"));
						TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_USER,request,response);

					}
					else{

						PageConfig pc=new PageConfig(request.getParameter("pageno"));
						pageCount = objManager.getTotalUsers(objUser,ui);
						pc.setPageCount(pageCount);
						request.setAttribute("pc", pc);            

						users = objManager.getUsers(objUser,ui,pc);         
						request.setAttribute("user_list", users); 
						request.setAttribute("user_info", objUser); 
						request.setAttribute("error_message", bundle.getString("User.SaveFailed"));
						request.setAttribute("mode", request.getParameter("mode"));
						TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_USER,request,response);
					}
				}else{


					PageConfig pc=new PageConfig(request.getParameter("pageno"));
					pageCount = objManager.getTotalUsers(objUser,ui);
					pc.setPageCount(pageCount);
					request.setAttribute("pc", pc);            

					users = objManager.getUsers(objUser,ui,pc);         
					request.setAttribute("user_list", users); 
					request.setAttribute("user_info", objUser); 
					request.setAttribute("error_message", bundle.getString("User.UserNameExist"));
					request.setAttribute("mode", request.getParameter("mode"));
					TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_USER,request,response);
				} 
			}

			else
			{
				PageConfig pc=new PageConfig(request.getParameter("pageno"));
				pageCount = objManager.getTotalUsers(objUser,ui);
				pc.setPageCount(pageCount);
				request.setAttribute("pc", pc);

				users = objManager.getUsers(objUser,ui,pc); 
				int subscriptionCount=objManager.getSubscriptionCount(clientId); 
				request.setAttribute("user_list", users); 
				request.setAttribute("user_info", objUser); 
				request.setAttribute("error_message", subscriptionCount+ bundle.getString("User.UserAlreadyCreated"));
				request.setAttribute("mode", request.getParameter("mode"));
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_USER,request,response);
			}

		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}




	public void doDeleteUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser ui=new UserInfo().getUserInfo(request);
			int iUserId = Integer.parseInt(request.getParameter("user_id"));     
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);

			boolean bFlag = objManager.deleteUser(iUserId,ui);   
			if(bFlag){
				request.setAttribute("success_message", bundle.getString("User.DeleteSuccess"));
			}else{
				request.setAttribute("error_message", bundle.getString("User.DeleteFailed"));
			}

			User objUser = new User();
			String strEntity = request.getParameter("entity_id");
			int iEntityId = (strEntity!=null&&strEntity!=""?Integer.parseInt(strEntity):-1);
			objUser.setEntityId(iEntityId);           
			String strCustomer = request.getParameter("customer_id");
			int iCustomerId = (strCustomer!=null&&strCustomer!=""?Integer.parseInt(strCustomer):-1); 
			objUser.setCustomerId(iCustomerId);
			String strFactory = request.getParameter("factory_id");
			int iFactoryId = (strFactory!=null&&strFactory!=""?Integer.parseInt(strFactory):-1);  
			objUser.setFactoryId(iFactoryId);             
			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			pageCount = objManager.getTotalUsers(objUser,ui);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);            

			users = objManager.getUsers(objUser,ui,pc);         
			request.setAttribute("user_list", users); 
			request.setAttribute("user_info", objUser); 
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_USER,request,response);


		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}






	public void doEditUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser ui=new UserInfo().getUserInfo(request);
			HttpSession session = request.getSession();

			int iHOId = Integer.parseInt((String)session.getAttribute("ho_id"));
			int iLCustomerId = Integer.parseInt((String)session.getAttribute("customer_id"));        
			int iLFactoryId = Integer.parseInt((String)session.getAttribute("factory_id"));
			User objUser = new User();
			int userId = Integer.parseInt(request.getParameter("user_id"));  
			String strPageNo = request.getParameter("pageno");
			int PageNo = (strPageNo!=null&&strPageNo!=""?Integer.parseInt(strPageNo):1);
			if(iHOId==1)
			{

				String strEntity = request.getParameter("entity_id");
				int iEntityId = (strEntity!=null&&strEntity!=""?Integer.parseInt(strEntity):-1);
				objUser.setEntityId(iEntityId);           
				String strCustomer = request.getParameter("customer_id");
				int iCustomerId = (strCustomer!=null&&strCustomer!=""?Integer.parseInt(strCustomer):-1); 
				objUser.setCustomerId(iCustomerId);
				String strFactory = request.getParameter("factory_id");
				int iFactoryId = (strFactory!=null&&strFactory!=""?Integer.parseInt(strFactory):-1);  
				objUser.setFactoryId(iFactoryId);  
				objUser.setUserId(userId); 

			}  
			else if(iLCustomerId!=-1)
			{
				objUser.setEntityId(iHOId);
				objUser.setCustomerId(iLCustomerId);
				objUser.setFactoryId(iLFactoryId); 
				objUser.setUserId(userId); 
			}
			else if(iLFactoryId!=-1)
			{
				objUser.setEntityId(iHOId);
				objUser.setCustomerId(iLCustomerId);
				objUser.setFactoryId(iLFactoryId); 
				objUser.setUserId(userId); 
			}

			User objUserInfo = objManager.getUserInfo(objUser,ui);                 
			request.setAttribute("user_info", objUserInfo);

			profiles = objManager.getEntityProfileInfo(objUser,ui);          
			request.setAttribute("profile_list", profiles);

			objUser.setFactoryId(objUserInfo.getFactoryId());
			objUser.setCustomerId(objUserInfo.getCustomerId());

			roles = objManager.getEntityRolesInfo(objUser,ui);          
			request.setAttribute("role_list", roles); 

			languages = objManager.getLanguage();         
			request.setAttribute("language_list", languages); 
			request.setAttribute("page_no", PageNo);
			request.setAttribute("ho_id", iHOId);
			request.setAttribute("lcustomer_id", iLCustomerId);
			request.setAttribute("lfactory_id", iLFactoryId);

			int isSysAdmin= objManager.getIsSysAdmin(ui);
			request.setAttribute("is_sys_admin", isSysAdmin); 
			request.setAttribute("mode", "e");
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_USER,request,response);

		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}


	public void doUpdateUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			String entrySelectedDocuments = null;   
			String editSelectedDocuments = null; 
			String deleteSelectedDocuments = null; 
			String viewSelectedDocuments = null; 
			String excelSelectedDocuments = null; 
			String printSelectedDocuments = null; 
			String apprSelectedDocuments = null; 
			String userName = null;
			String firstName = null;
			String lastName = null;

			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			TPCSUser ui=new UserInfo().getUserInfo(request);

			userName=request.getParameter("user_name");
			firstName=request.getParameter("first_name");
			lastName=request.getParameter("last_name");
			int iEntityId = Integer.parseInt(request.getParameter("entity_id"));
			int iCustomerId = Integer.parseInt(request.getParameter("customer_id"));
			int iFactoryId = Integer.parseInt(request.getParameter("factory_id"));
			int iRoleId = Integer.parseInt(request.getParameter("role_id"));
			int iProfileId = Integer.parseInt(request.getParameter("profile_id"));
			int iUserId = Integer.parseInt(request.getParameter("user_id"));           
			int iLanguageId = Integer.parseInt(request.getParameter("language_id"));

			entrySelectedDocuments = request.getParameter("entry_documents");
			editSelectedDocuments = request.getParameter("edit_documents");
			deleteSelectedDocuments = request.getParameter("delete_documents");
			viewSelectedDocuments = request.getParameter("view_documents");
			excelSelectedDocuments = request.getParameter("excel_documents");
			printSelectedDocuments = request.getParameter("print_documents");
			apprSelectedDocuments = request.getParameter("appr_documents");

			int isSysAdmin = Validator.convertToInteger(request.getParameter("is_sys_admin"));

			int isActive = Validator.convertToInteger(request.getParameter("is_active"));


			User objUser=new User();                     
			Role objRole=new Role();
			Profiles objProfiles=new Profiles();

			if(request.getParameter("profile_status")=="")
			{            	
				objUser.setProfileStatus("0");
			}
			else
			{
				objUser.setProfileStatus(request.getParameter("profile_status"));
			}
			objUser.setUserId(iUserId);
			objUser.setUserName(userName);
			objUser.setFirstName(firstName);
			objUser.setLastName(lastName);
			objUser.setEntityId(iEntityId);
			objUser.setCustomerId(iCustomerId);
			objUser.setFactoryId(iFactoryId);
			objRole.setRoleId(iRoleId);
			objProfiles.setProfileId(iProfileId);
			objProfiles.setEntityEntryDocuments(entrySelectedDocuments);
			objProfiles.setEntityEditDocuments(editSelectedDocuments);
			objProfiles.setEntityDeleteDocuments(deleteSelectedDocuments);
			objProfiles.setEntityViewDocuments(viewSelectedDocuments);
			objProfiles.setEntityExcelDocuments(excelSelectedDocuments);
			objProfiles.setEntityPrintDocuments(printSelectedDocuments);
			objProfiles.setEntityApprDocuments(apprSelectedDocuments);
			objUser.setRoles(objRole);
			objUser.setProfiles(objProfiles);
			objUser.setLanguageId(iLanguageId);
			objUser.setIsSystemAdmin(isSysAdmin);
			objUser.setIsActive(isActive);
			HttpSession session = request.getSession();
			int clientId = Integer.parseInt((String)session.getAttribute("client_id"));



			boolean bFlag = objManager.checkNoOfUsersToUpdate(ui,objUser);   

			if(!bFlag){
				PageConfig pc=new PageConfig(request.getParameter("pageno"));
				pageCount = objManager.getTotalUsers(objUser,ui);
				pc.setPageCount(pageCount);
				request.setAttribute("pc", pc);            

				users = objManager.getUsers(objUser,ui,pc);         
				request.setAttribute("user_list", users); 
				request.setAttribute("user_info", objUser); 
				request.setAttribute("error_message", "No. of purchased active users created.");
				request.setAttribute("mode", request.getParameter("mode"));
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_USER,request,response);
			}
			else{
				if(!objManager.isEntityUserExist(objUser,clientId)){

					User retVal = objManager.updateUser(objUser,ui);
					if(retVal.isSuccess()){



						User retClientVal= objManager.updateClientInfo(objUser,clientId);

						PageConfig pc=new PageConfig(request.getParameter("pageno"));
						pageCount = objManager.getTotalUsers(objUser,ui);
						pc.setPageCount(pageCount);
						request.setAttribute("pc", pc);            

						users = objManager.getUsers(objUser,ui,pc);         
						request.setAttribute("user_list", users); 
						request.setAttribute("user_info", objUser); 
						request.setAttribute("success_message", "User Details updated successfully."); 
						request.setAttribute("mode", request.getParameter("mode"));
						TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_USER,request,response);

					}else{             
						PageConfig pc=new PageConfig(request.getParameter("pageno"));
						pageCount = objManager.getTotalUsers(objUser,ui);
						pc.setPageCount(pageCount);
						request.setAttribute("pc", pc);            

						users = objManager.getUsers(objUser,ui,pc);         
						request.setAttribute("user_list", users); 
						request.setAttribute("user_info", objUser); 
						request.setAttribute("error_message", "User save failed.");
						request.setAttribute("mode", request.getParameter("mode"));
						TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_USER,request,response);
					}

				}
				else{


					PageConfig pc=new PageConfig(request.getParameter("pageno"));
					pageCount = objManager.getTotalUsers(objUser,ui);
					pc.setPageCount(pageCount);
					request.setAttribute("pc", pc);            

					users = objManager.getUsers(objUser,ui,pc);         
					request.setAttribute("user_list", users); 
					request.setAttribute("user_info", objUser); 
					request.setAttribute("error_message", "User Name exists.");
					request.setAttribute("mode", request.getParameter("mode"));
					TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_USER,request,response);
				} 

			}
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}
	public void doResetUserPassword(HttpServletRequest request,HttpServletResponse response){
		 try {
	          //-----------------------------Password Reset-------------------
	          String strUserName=request.getParameter("user_name");
	          System.out.println("strUserName :"+strUserName);
	          String strMessage = "NotReset";
					ValidateUser objValidateUser = new ValidateUser();
					User objuser = objValidateUser.passwordreset(strUserName);
					if(objuser.isSuccess()){
						strMessage = "Reset";
						objValidateUser.mailresetPassword(objuser);
					}
	          response.getWriter().write(strMessage);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doDisplayChangePassword(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        try{
        	HttpSession session = request.getSession();
        	TPCSUser ui=new UserInfo().getUserInfo(request);        	
            int iUserId = Integer.parseInt((String)session.getAttribute("login_user_id"));
            //get rights permission begins
            rights = objRight.getUserRights(SubdocumentId.CHANGE_PASSWORD,ui);
            session.setAttribute("rights", rights);
        	
            TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_CHANGE_PASSWORD,request,response);
        }catch(Exception e){
            e.printStackTrace();
            //NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
        }
      }
	public void doSavePassword(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        try{
        	ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
        	
        	String oldPassword = request.getParameter("old_password");
        	String newPassword = request.getParameter("new_password");
        	String securityQuestion = request.getParameter("security_question");
        	String securityAnswer= request.getParameter("answer");
        	String expDay= request.getParameter("days_to_exp");
        	int days = Integer.parseInt(expDay);
        	//String expDate= request.getParameter("exp_date");
        	HttpSession session = request.getSession();
        	String strUserName = (String)session.getAttribute("display_name");
            int iUserId = Integer.parseInt((String)session.getAttribute("login_user_id"));
           
            int clientId = Integer.parseInt((String)session.getAttribute("client_id"));
            User retval= objManager.changePassword(clientId,iUserId,oldPassword,newPassword,securityQuestion,securityAnswer,days);
        	 
        	if(retval.isSuccess()){
        		 request.setAttribute("success_message", bundle.getString("Password.ChangedSuccess")); 
        		 objManager.mailchangePassword(retval);
        		
        	}else{
        		request.setAttribute("error_message", bundle.getString("Password.Incorrect"));
        		 request.setAttribute("sec_que", securityQuestion);
        		 request.setAttribute("sec_ans", securityAnswer);
        		 request.setAttribute("no_days", expDay);
        	}
        
        	
            TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_CHANGE_PASSWORD,request,response);
        }catch(Exception e){
            e.printStackTrace();
            //NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
        }
      }
	private UserManager objManager = new UserManager();
	private UserRightsManager objRight = new UserRightsManager();
	private EntityManager objEntManager=new EntityManager();
	private UserRights rights = null;
	private List<Profiles> profiles = null;
	private List<User> users = null;
	private List<Role> roles = null;
	private int pageCount;
	private List<Generic> entities = null;   
	private List<Generic> modules = null;
	private List<Generic> factories = null;
	private List<Generic> customers = null;
	private List<Generic> languages = null;	    
}
