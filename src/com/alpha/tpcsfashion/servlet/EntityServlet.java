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
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.model.EntityManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.Validator;

public class EntityServlet {
	public void doDisplayEntityRights(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			String strSearchCriteria = "";
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
			int pageCount = objManager.getTotalPages(strSearchCriteria,userInfo);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);
			List<EntityRights> entityrights = objManager.getEntityRights(pc,strSearchCriteria,userInfo,bundle );
			request.setAttribute("entityrights_list", entityrights);

			rights = objRight.getUserRights(SubdocumentId.ENTITY_RIGHTS,userInfo);
			session.setAttribute("rights", rights);
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_ENTITY_RIGHTS,request,response);
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}

	public void doEntitySearch(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			String strRequestType = request.getParameter("request_type");
			String strSearchCriteria = "";
			HttpSession session = request.getSession();
			if("Search".equalsIgnoreCase(strRequestType)){	
				strSearchCriteria = getSearchCriteriaQuery(request);  
				session.setAttribute("search_query",strSearchCriteria);           	
			}
			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			int pageCount = objManager.getTotalPages(strSearchCriteria,userInfo);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);

			List<EntityRights> entityrights = objManager.getEntityRights(pc,strSearchCriteria,userInfo,bundle);         
			request.setAttribute("entityrights_list", entityrights);
			request.setAttribute("type_of_request", strRequestType);
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_ENTITY_RIGHTS,request,response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private String getSearchCriteriaQuery(HttpServletRequest request){
		String strSearchCriteria="";
		String strEntity = request.getParameter("entity_name");

		if(strEntity!=null && strEntity.trim().length()>0){
			strSearchCriteria = strSearchCriteria+"  and e.entity_name like '%"+strEntity+"%'";
		}

		//  if(strSearchCriteria.length()>0)
		//  strSearchCriteria = ""+strSearchCriteria.substring(1,strSearchCriteria.length()-4);
		return strSearchCriteria;

	}
	private void getAndSetAtributes(HttpServletRequest request){
		request.setAttribute("mode", request.getParameter("mode"));
	}
	public void doGetDocuments(HttpServletRequest request, HttpServletResponse response){
		try {
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			int entityId=Validator.convertToInteger(request.getParameter("entityId"));
			int moduleId=Validator.convertToInteger(request.getParameter("module_id"));
			EntityRights rights=new EntityRights();
			rights.setEntityId(entityId);
			rights.setModuleId(moduleId);
			List<Generic> documents=objManager.getDocuments(userInfo, rights);
			StringBuffer buffer=new StringBuffer();
			buffer.append("<documents>");
			for(Generic doc:documents){
				buffer.append("<document>");
				buffer.append("<id>");
				buffer.append(doc.getId());
				buffer.append("</id>");
				buffer.append("<name>");
				buffer.append(doc.getName());
				buffer.append("</name>");
				buffer.append("</document>");	
			}
			buffer.append("</documents>");
			response.setContentType("text/xml");
			response.getWriter().write(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doGetSubDocuments(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{ 
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			List<Integer> list = new ArrayList<Integer>();
			response.setContentType("text/html");  
			int  documentId = Integer.parseInt(request.getParameter("document_id"));    
			int  entityId = Integer.parseInt(request.getParameter("entityId"));    	      
			EntityRights objEntityRights=new EntityRights();
			objEntityRights.setEntityId(entityId);             
			objEntityRights.setDocumentId(documentId);
			if(!objManager.isEntityRightExist(objEntityRights,userInfo)){
				String strDocument = objManager.getSubDocuments(objEntityRights,list,userInfo,bundle);      
				response.getWriter().write(String.valueOf(strDocument));
			}
			else{
				String strDocument ="<div style=\"color:red\"> " +bundle.getString("EntityRights.AlreadyConfigured")+ "</div>";
				response.getWriter().write(String.valueOf(strDocument));

			}
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);    
		}
	}
	public void doSaveEntityRights(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			String strSelectedDocuments = null;
			String strSearchCriteria="";

			int iEntityId = Integer.parseInt(request.getParameter("selectentity"));
			strSelectedDocuments = request.getParameter("selected_documents");
			int documentId= Integer.parseInt(request.getParameter("document_id"));             
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			EntityRights objEntityRights=new EntityRights();
			objEntityRights.setEntityId(iEntityId);
			objEntityRights.setEntityDocuments(strSelectedDocuments);
			objEntityRights.setDocumentId(documentId);
			if(!objManager.isEntityRightExist(objEntityRights,userInfo)){

				EntityRights retVal = objManager.insertEntityRights(objEntityRights,userInfo);
				if(retVal.isSuccess()){

					PageConfig pc=new PageConfig(request.getParameter("pageno"));
					int pageCount = objManager.getTotalPages(strSearchCriteria,userInfo);
					pc.setPageCount(pageCount);
					request.setAttribute("pc", pc);

					List<EntityRights> entityrights = objManager.getEntityRights(pc,strSearchCriteria,userInfo,bundle);
					//entityrights = objManager.getEntityRights(iStart, iEnd,strDataSource,strDataBase,strServerIP,strSqlusername,strSqlpassword); 
					objEntityRights.setModuleId(0);
					request.setAttribute("entityrights_info", objEntityRights);  
					request.setAttribute("entityrights_list", entityrights);          	 
					request.setAttribute("success_message", bundle.getString("EntityRights.CreateSuccess"));
					List<Generic>  entities = objManager.getEntity(userInfo);
					List<Generic> modules = objManager.getModules(userInfo); 
					request.setAttribute("entity_list", entities);
					request.setAttribute("module_list", modules);
					getAndSetAtributes(request);
					TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_ENTITY_RIGHTS,request,response);

				}
				else{

					PageConfig pc=new PageConfig(request.getParameter("pageno"));
					int pageCount = objManager.getTotalPages(strSearchCriteria,userInfo);
					pc.setPageCount(pageCount);
					request.setAttribute("pc", pc);
					request.setAttribute("error_message", bundle.getString("EntityRights.CreateFailed"));
					List<Generic>  entities = objManager.getEntity(userInfo);
					List<Generic> modules = objManager.getModules(userInfo); 
					request.setAttribute("entity_list", entities);
					request.setAttribute("module_list", modules);
					TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_ENTITY_RIGHTS,request,response);
				}
			}else{

				PageConfig pc=new PageConfig(request.getParameter("pageno"));
				int pageCount = objManager.getTotalPages(strSearchCriteria,userInfo);
				pc.setPageCount(pageCount);
				request.setAttribute("pc", pc);
				objEntityRights.setModuleId(0);
				request.setAttribute("entityrights_info", objEntityRights); 
				request.setAttribute("error_message",bundle.getString("EntityRights.AlreadyConfigured"));
				List<Generic>  entities = objManager.getEntity(userInfo);
				List<Generic> modules = objManager.getModules(userInfo); 
				request.setAttribute("entity_list", entities);
				request.setAttribute("module_list", modules);
				getAndSetAtributes(request);
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_ENTITY_RIGHTS,request,response);
			}

		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}
	public void doNewRights(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			List<Generic>  entities = objManager.getEntity(userInfo);
			List<Generic> modules = objManager.getModules(userInfo); 
			request.setAttribute("entity_list", entities);
			request.setAttribute("module_list", modules);
			request.setAttribute("mode", "n");

			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_ENTITY_RIGHTS,request,response);

		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}
	public void doEditEntityRights(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			int iEntityId = Integer.parseInt(request.getParameter("entityrights_id"));
			EntityRights objEntityRights=new EntityRights();// objManager.getEntityInfo(iEntityId);
			objEntityRights.setEntityId(iEntityId);
			request.setAttribute("entityrights_info", objEntityRights);  

			List<Generic>  entities = objManager.getEntity(userInfo);
			List<Generic> modules = objManager.getModules(userInfo); 
			request.setAttribute("entity_list", entities);
			request.setAttribute("module_list", modules);
			request.setAttribute("mode", "e");

			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_ENTITY_RIGHTS,request,response);

		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}
	public void doGetSelectedDocuments(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{  
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			List<Integer> list = new ArrayList<Integer>();
			response.setContentType("text/html");  
			int  documentId = Integer.parseInt(request.getParameter("document_id"));    	      
			int iEntityId = Integer.parseInt(request.getParameter("entityrights_id"));
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			EntityRights objEntityRights=new EntityRights();
			objEntityRights.setEntityId(iEntityId);
			objEntityRights.setDocumentId(documentId);
			String strDocument = objManager.getSelectedDocuments(objEntityRights,list,userInfo,bundle);      
			response.getWriter().write(String.valueOf(strDocument));
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);    
		}
	}
	public void doUpdateEntityRights(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			String strSelectedDocuments = null;
			String strSearchCriteria="";
			int documentId;

			int iEntityId = Integer.parseInt(request.getParameter("entityrights_id"));
			strSelectedDocuments = request.getParameter("selected_documents");
			documentId=Integer.parseInt(request.getParameter("document_id"));


			EntityRights objEntityRights=new EntityRights();
			objEntityRights.setEntityId(iEntityId);
			objEntityRights.setEntityDocuments(strSelectedDocuments);
			objEntityRights.setDocumentId(documentId);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			request.setAttribute("entity_id_for_alert", iEntityId );


			EntityRights retVal = objManager.updateEntityRights(objEntityRights,userInfo);
			if(retVal.isSuccess()){
				PageConfig pc=new PageConfig(request.getParameter("pageno"));
				int pageCount = objManager.getTotalPages(strSearchCriteria,userInfo);
				pc.setPageCount(pageCount);
				request.setAttribute("pc", pc);

				List<EntityRights>     entityrights = objManager.getEntityRights(pc,strSearchCriteria,userInfo,bundle);
				//  entityrights = objManager.getEntityRights(iStart, iEnd,strDataSource,strDataBase,strServerIP,strSqlusername,strSqlpassword);         
				request.setAttribute("entityrights_list", entityrights);          	 
				objEntityRights.setModuleId(0);
				request.setAttribute("entityrights_info", objEntityRights);  
				request.setAttribute("set_success", 1);
				request.setAttribute("success_message", bundle.getString("EntityRights.CreateSuccess")); 
				List<Generic>  entities = objManager.getEntity(userInfo);
				List<Generic> modules = objManager.getModules(userInfo); 
				request.setAttribute("entity_list", entities);
				request.setAttribute("module_list", modules);
				getAndSetAtributes(request);
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_ENTITY_RIGHTS,request,response);
			}

			else{

				PageConfig pc=new PageConfig(request.getParameter("pageno"));
				int pageCount = objManager.getTotalPages(strSearchCriteria,userInfo);
				pc.setPageCount(pageCount);
				request.setAttribute("pc", pc);
				//pageCount = objManager.getTotalPages(iPageSize,strDataSource,strDataBase,strServerIP,strSqlusername,strSqlpassword);
				request.setAttribute("error_message", bundle.getString("EntityRights.CreateFailed"));

				List<Generic>  entities = objManager.getEntity(userInfo);
				List<Generic> modules = objManager.getModules(userInfo); 
				request.setAttribute("entity_list", entities);
				request.setAttribute("module_list", modules);
				getAndSetAtributes(request);
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_ENTITY_RIGHTS,request,response);
			}


		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}
	public void doUpdateProfileAtEntityUpdate(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			int iEntityId  = Integer.parseInt(request.getParameter("entity_id")); 

			boolean bFlag = objManager.updateProfileAtEntityUpdate(iEntityId,userInfo);
			response.setContentType("text");
			if(bFlag){
				response.getWriter().write(iEntityId+"#####true");
			}
			else{
				response.getWriter().write(iEntityId+"#####false");
			}

		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}
	public void doUpdateUserAtEntityUpdate(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");

			int iEntityId  = Integer.parseInt(request.getParameter("entity_id")); 
			boolean bFlag = objManager.updateUserAtEntityUpdate(iEntityId,userInfo);
			response.setContentType("text");
			if(bFlag){
				response.getWriter().write(iEntityId+"#####true");
			}
			else{
				response.getWriter().write(iEntityId+"#####false");
			}

		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}
	private EntityManager objManager=new EntityManager();
	private UserRightsManager objRight = new UserRightsManager();
	private UserRights rights = null;
}
