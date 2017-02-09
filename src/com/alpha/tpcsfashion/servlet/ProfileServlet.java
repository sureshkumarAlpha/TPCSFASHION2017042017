package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alpha.tpcsfashion.beans.EntityRights;
import com.alpha.tpcsfashion.beans.Generic;
import com.alpha.tpcsfashion.beans.Profiles;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.model.EntityManager;
import com.alpha.tpcsfashion.model.ProfileManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.Validator;

public class ProfileServlet {
	public void doDisplayProfile(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			String strSearchCriteria="";
			String strRequestType = request.getParameter("request_type");
			HttpSession session = request.getSession();
			if("Search".equalsIgnoreCase(strRequestType)){	
				strSearchCriteria = (String)session.getAttribute("search_query"); 
			}
			else{
				if(session.getAttribute("search_query")!=null)
					session.removeAttribute("search_query");
			}          
			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			int pageCount = objManager.getProfileCount(strSearchCriteria,userInfo);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			List<Profiles> profiles = objManager.getProfiles(pc,strSearchCriteria,userInfo,bundle);
			// profiles = objManager.getProfiles(iStart, iEnd,strDataSource,strDataBase,strServerIP,strSqlusername,strSqlpassword);         
			request.setAttribute("profile_list", profiles);

			// HttpSession session = request.getSession();
			//             if(session.getAttribute("entity_list")==null){
			//               entities = objManager.getEntity(userInfo);               
			//               session.setAttribute("entity_list", entities);
			//             } 

			//get rights permission begins
			rights = objRight.getUserRights(SubdocumentId.PROFILE,userInfo);
			session.setAttribute("rights", rights);
			getAndSetAttributes(request);
			request.setAttribute("type_of_request", strRequestType);
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_PROFILE,request,response);
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}
	public void getAndSetAttributes(HttpServletRequest request){
		request.setAttribute("profile", request.getParameter("profile"));
		request.setAttribute("entity", request.getParameter("entity"));
	}
	public void doProfileSearch(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			String strPageNo = request.getParameter("pageno");  
			String strRequestType = request.getParameter("request_type");
			String strSearchCriteria = "";
			HttpSession session = request.getSession();
			int PageNo = (strPageNo!=null?Integer.parseInt(strPageNo):1);  
			if("Search".equalsIgnoreCase(strRequestType)){	
				strSearchCriteria = getProfileSearchCriteriaQuery(request);  
				session.setAttribute("search_query",strSearchCriteria);           	
			}	  


			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			int pageCount = objManager.getProfileCount(strSearchCriteria,userInfo);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);       
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			List<Profiles>  profiles = objManager.getProfiles(pc,strSearchCriteria,userInfo,bundle);         
			request.setAttribute("profile_list", profiles);
			request.setAttribute("page_count", pageCount);
			request.setAttribute("page_no", PageNo);


			request.setAttribute("type_of_request", strRequestType);
			getAndSetAttributes(request);
			
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_PROFILE,request,response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private String getProfileSearchCriteriaQuery(HttpServletRequest request){
		String strSearchCriteria="";
		String strProfile = request.getParameter("profile");
		String strEntity = request.getParameter("entity");
		if(strProfile!=null && strProfile.trim().length()>0){
			strSearchCriteria = strSearchCriteria+"  and p.profile_name like '%"+strProfile+"%'";

		}
		if(strEntity!=null && strEntity.trim().length()>0){
			strSearchCriteria = strSearchCriteria+"  and e.entity_name like '%"+strEntity+"%'";
		}

		//  if(strSearchCriteria.length()>0)
		//  strSearchCriteria = ""+strSearchCriteria.substring(1,strSearchCriteria.length()-4);
		return strSearchCriteria;

	}
	private void getAndSetAttributes(HttpServletRequest request,HttpServletResponse response){
		String strProfile = request.getParameter("profile");
		String strEntity = request.getParameter("entity");
		request.setAttribute("profile", strProfile);
		request.setAttribute("entity", strEntity);
		
	}
	public void doNewProfile(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			List<Generic> entities = objEntManager.getEntity(userInfo);               
			request.setAttribute("entity_list", entities);
			request.setAttribute("mode", "n");
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_PROFILE,request,response);
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);    
		}
	}
	public void doGetEntityDocuments(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{  
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			List<Integer> list = new ArrayList<Integer>();
			response.setContentType("text/html");  
			int  entityid = Integer.parseInt(request.getParameter("entity_id"));    	
			int  profileId = Validator.convertToInteger(request.getParameter("profile_id"));
			
			String strDocument = objManager.getEntitySelectedDocuments(bundle,entityid,profileId,list,userInfo);      
			response.getWriter().write(String.valueOf(strDocument));
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);    
		}
	}
	public void doSaveProfile(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			String strSearchCriteria="";
			String entrySelectedDocuments = null;   
			String editSelectedDocuments = null; 
			String deleteSelectedDocuments = null; 
			String viewSelectedDocuments = null; 
			String excelSelectedDocuments = null; 
			String printSelectedDocuments = null; 
			String apprSelectedDocuments = null; 
			String profileName = null;

			int iEntityId = Integer.parseInt(request.getParameter("entity_id"));
			profileName=request.getParameter("profile_name");
			entrySelectedDocuments = request.getParameter("entry_documents");
			editSelectedDocuments = request.getParameter("edit_documents");
			deleteSelectedDocuments = request.getParameter("delete_documents");
			viewSelectedDocuments = request.getParameter("view_documents");
			excelSelectedDocuments = request.getParameter("excel_documents");
			printSelectedDocuments = request.getParameter("print_documents");
			apprSelectedDocuments = request.getParameter("appr_documents");


			EntityRights entity=new EntityRights();
			Profiles objProfiles=new Profiles();
			entity.setEntityId(iEntityId);
			objProfiles.setEntity(entity);
			objProfiles.setProfileName(profileName);
			objProfiles.setEntityEntryDocuments(entrySelectedDocuments);
			objProfiles.setEntityEditDocuments(editSelectedDocuments);
			objProfiles.setEntityDeleteDocuments(deleteSelectedDocuments);
			objProfiles.setEntityViewDocuments(viewSelectedDocuments);
			objProfiles.setEntityExcelDocuments(excelSelectedDocuments);
			objProfiles.setEntityPrintDocuments(printSelectedDocuments);
			objProfiles.setEntityApprDocuments(apprSelectedDocuments);

			if(!objManager.isProfileExist(objProfiles,userInfo)){

				Profiles retVal = objManager.insertProfile(objProfiles,userInfo);
				if(retVal.isSuccess()){

					PageConfig pc=new PageConfig(request.getParameter("pageno"));
					int pageCount = objManager.getProfileCount(strSearchCriteria,userInfo);
					pc.setPageCount(pageCount);
					request.setAttribute("pc", pc);
					List<Profiles>  profiles = objManager.getProfiles(pc,strSearchCriteria,userInfo,bundle);
					//profiles = objManager.getProfiles(iStart, iEnd,strDataSource,strDataBase,strServerIP,strSqlusername,strSqlpassword);         
					request.setAttribute("profile_list", profiles);
					request.setAttribute("success_message", bundle.getString("Profile.SaveSuccess"));
					List<Generic> entities = objEntManager.getEntity(userInfo);               
					request.setAttribute("entity_list", entities);
					request.setAttribute("mode", request.getParameter("mode"));
					TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_PROFILE,request,response);

				}
				else{

					PageConfig pc=new PageConfig(request.getParameter("pageno"));
					int pageCount = objManager.getProfileCount(strSearchCriteria,userInfo);
					pc.setPageCount(pageCount);
					request.setAttribute("pc", pc);
					request.setAttribute("error_message", bundle.getString("Profile.SaveFailed"));
					List<Generic> entities = objEntManager.getEntity(userInfo);               
					request.setAttribute("entity_list", entities);
					request.setAttribute("mode", request.getParameter("mode"));
					TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_PROFILE,request,response);
				}
			}else{

				PageConfig pc=new PageConfig(request.getParameter("pageno"));
				int pageCount = objManager.getProfileCount(strSearchCriteria,userInfo);
				pc.setPageCount(pageCount);
				request.setAttribute("pc", pc);
				request.setAttribute("error_message", bundle.getString("Profile.AlreadyExist"));
				List<Generic> entities = objEntManager.getEntity(userInfo);               
				request.setAttribute("entity_list", entities);
				request.setAttribute("mode", request.getParameter("mode"));
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_PROFILE,request,response);
			}

		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}
	public void doDeleteProfile(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			String strSearchCriteria="";
			int iProfileId = Integer.parseInt(request.getParameter("profile_id"));
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			boolean bFlag = objManager.deleteProfile(iProfileId,userInfo);
			if(bFlag){
				request.setAttribute("success_message", bundle.getString("Profile.DeleteSuccess"));            
			}else{
				request.setAttribute("error_message", bundle.getString("Profile.DeleteFailed"));
			}                  
			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			int pageCount = objManager.getProfileCount(strSearchCriteria,userInfo);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);
			//   pageCount = objManager.getProfileCount(iPageSize,strDataSource,strDataBase,strServerIP,strSqlusername,strSqlpassword);
			List<Profiles>  profiles = objManager.getProfiles(pc,strSearchCriteria,userInfo,bundle);    
			// profiles = objManager.getProfiles(iStart, iEnd,strDataSource,strDataBase,strServerIP,strSqlusername,strSqlpassword);         
			request.setAttribute("profile_list", profiles);     

			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_PROFILE,request,response);
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}
	public void doGetEntitySelectedDocuments(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			List<Integer> list = new ArrayList<Integer>();
			response.setContentType("text/html");  
			int iType=Integer.parseInt(request.getParameter("iType"));
			int  entityid ;  
			int  profileid;
			int iBomWidth;
			int pageno;
			HttpSession session = request.getSession();
			if(iType==1)
			{
				entityid = Integer.parseInt(request.getParameter("entity_id"));  
				profileid = Integer.parseInt(request.getParameter("profile_id"));

				session = request.getSession();

				session.setAttribute("entity_id", entityid);
				session.setAttribute("profile_id", profileid);


			}
			else
			{
				entityid = Integer.parseInt(request.getParameter("entity_id"));  
				profileid =(Integer)session.getAttribute("profile_id");
				pageno = (Integer)session.getAttribute("pageno");
			}

			String strDocument = objManager.getEntitySelectedDocumentsList(bundle,entityid,profileid,list,userInfo);      	      
			response.getWriter().write(String.valueOf(strDocument));
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);    
		}
	}
	public void doEditProfile(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			List<Integer> list = new ArrayList<Integer>();
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			HttpSession session = request.getSession();  
//			int  profileid =(Integer)session.getAttribute("profile_id");
//			int  entityid =(Integer)session.getAttribute("entity_id");
			
			int iType=Integer.parseInt(request.getParameter("iType"));
			int  entityid ;  
			int  profileid;
			int iBomWidth;
			int pageno;
			if(iType==1)
			{
				entityid = Integer.parseInt(request.getParameter("entity_id"));  
				profileid = Integer.parseInt(request.getParameter("profile_id"));

				session = request.getSession();

				session.setAttribute("entity_id", entityid);
				session.setAttribute("profile_id", profileid);

			}
			else
			{
				entityid = Integer.parseInt(request.getParameter("entity_id"));  
				profileid =(Integer)session.getAttribute("profile_id");
				pageno = (Integer)session.getAttribute("pageno");
			}

			String strDocument = objManager.getEntitySelectedDocumentsList(bundle,entityid,profileid,list,userInfo);   
			request.setAttribute("entity_document_list", strDocument);
			
			Profiles objProfiles=new Profiles();
			objProfiles=objManager.getProfileInfo(profileid,userInfo);          
			request.setAttribute("profile_info", objProfiles);  

//			String profileInfo = request.getParameter("entity_document_list");
//			request.setAttribute("entity_document_list", profileInfo);

			List<Generic> entities = objEntManager.getEntity(userInfo);               
			request.setAttribute("entity_list", entities);
			request.setAttribute("mode", "e");
			
			
			
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_PROFILE,request,response);

		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}
	public void doUpdateProfile(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			String strSearchCriteria="";
			String entrySelectedDocuments = null;   
			String editSelectedDocuments = null; 
			String deleteSelectedDocuments = null; 
			String viewSelectedDocuments = null; 
			String excelSelectedDocuments = null; 
			String printSelectedDocuments = null; 
			String apprSelectedDocuments = null; 
			String profileName = null;
			HttpSession session = request.getSession();    
			int  profileId =(Integer)session.getAttribute("profile_id");
			int  iEntityId =Integer.parseInt(request.getParameter("selectentity"));
			profileName=request.getParameter("profile_name");

			entrySelectedDocuments = request.getParameter("entry_documents");
			editSelectedDocuments = request.getParameter("edit_documents");
			deleteSelectedDocuments = request.getParameter("delete_documents");
			viewSelectedDocuments = request.getParameter("view_documents");
			excelSelectedDocuments = request.getParameter("excel_documents");
			printSelectedDocuments = request.getParameter("print_documents");
			apprSelectedDocuments = request.getParameter("appr_documents");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);

			EntityRights entity=new EntityRights();
			Profiles objProfiles=new Profiles();
			entity.setEntityId(iEntityId);
			objProfiles.setEntity(entity);
			objProfiles.setProfileName(profileName);
			objProfiles.setProfileId(profileId);
			objProfiles.setEntityEntryDocuments(entrySelectedDocuments);
			objProfiles.setEntityEditDocuments(editSelectedDocuments);
			objProfiles.setEntityDeleteDocuments(deleteSelectedDocuments);
			objProfiles.setEntityViewDocuments(viewSelectedDocuments);
			objProfiles.setEntityExcelDocuments(excelSelectedDocuments);
			objProfiles.setEntityPrintDocuments(printSelectedDocuments);
			objProfiles.setEntityApprDocuments(apprSelectedDocuments);

			if(!objManager.isEntityProfileExist(objProfiles,userInfo)){

				Profiles retVal = objManager.updateProfile(objProfiles,userInfo);
				if(retVal.isSuccess()){
					PageConfig pc=new PageConfig(request.getParameter("pageno"));
					int pageCount = objManager.getProfileCount(strSearchCriteria,userInfo);
					pc.setPageCount(pageCount);
					request.setAttribute("pc", pc);                	 
					List<Profiles>  profiles = objManager.getProfiles(pc,strSearchCriteria,userInfo,bundle);
					// profiles = objManager.getProfiles(iStart, iEnd,strDataSource,strDataBase,strServerIP,strSqlusername,strSqlpassword);         
					request.setAttribute("profile_list", profiles);
					request.setAttribute("success_message", bundle.getString("Profile.UpdateSuccess")); 
					List<Generic> entities = objEntManager.getEntity(userInfo);               
					request.setAttribute("entity_list", entities);
					request.setAttribute("mode", request.getParameter("mode"));
					TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_PROFILE,request,response);

				}
				else{

					PageConfig pc=new PageConfig(request.getParameter("pageno"));
					int pageCount = objManager.getProfileCount(strSearchCriteria,userInfo);
					pc.setPageCount(pageCount);
					request.setAttribute("pc", pc);
					//    pageCount = objManager.getProfileCount(iPageSize,strDataSource,strDataBase,strServerIP,strSqlusername,strSqlpassword);
					request.setAttribute("error_message", bundle.getString("Profile.SaveFailed"));
					List<Generic> entities = objEntManager.getEntity(userInfo);               
					request.setAttribute("entity_list", entities);
					request.setAttribute("mode", request.getParameter("mode"));
					TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_PROFILE,request,response);
				}
			}else{

				PageConfig pc=new PageConfig(request.getParameter("pageno"));
				int pageCount = objManager.getProfileCount(strSearchCriteria,userInfo);
				pc.setPageCount(pageCount);
				request.setAttribute("pc", pc);
				request.setAttribute("error_message", bundle.getString("Profile.AlreadyExist"));
				List<Generic> entities = objEntManager.getEntity(userInfo);               
				request.setAttribute("entity_list", entities);
				request.setAttribute("mode", request.getParameter("mode"));
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_PROFILE,request,response);
			}

		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	} 
	private ProfileManager objManager=new ProfileManager();
	private EntityManager objEntManager=new EntityManager();
	private UserRightsManager objRight = new UserRightsManager();
	private UserRights rights = null;
}