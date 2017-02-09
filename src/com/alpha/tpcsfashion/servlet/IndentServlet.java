package com.alpha.tpcsfashion.servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringEscapeUtils;

import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.CommonUtil;
import com.alpha.tpcsfashion.beans.Company;
import com.alpha.tpcsfashion.beans.DynamicFieldEvents;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.DynamicFormStructure;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.Indent;
import com.alpha.tpcsfashion.beans.IndentDetail;
import com.alpha.tpcsfashion.beans.IndentHeader;
import com.alpha.tpcsfashion.beans.Size;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.gridmaker.IndentGridMaker;
import com.alpha.tpcsfashion.model.ColumnPreferenceManager;
import com.alpha.tpcsfashion.model.CommonUtilManager;
import com.alpha.tpcsfashion.model.CompanyAndYearSelectionManager;
import com.alpha.tpcsfashion.model.DynamicFieldManager;
import com.alpha.tpcsfashion.model.IndentManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.ExportToPdfTool;
import com.alpha.tpcsfashion.util.ResourceBundleProvider;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.UserInfo;
import com.alpha.tpcsfashion.util.Validator;

/**
 * Servlet implementation class IndentServlet
 */
@WebServlet("/IndentServlet")
public class IndentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	public void doDisplayIndent(HttpServletRequest request,HttpServletResponse response){
		try {

			TPCSUser ui=new UserInfo().getUserInfo(request);
			String strSearchQuery=getDefaultCriteria(request);
			String reqType=request.getParameter("request_type");
			if("Search".equalsIgnoreCase(reqType)){	
				strSearchQuery = getSearchCriteria(request);
				getAndSetAttributes(request);
			}
			CommonUtil comUtil=new CommonUtil();
			comUtil.setTableIds(headerTableId+","+detailTableId);
			comUtil.setFixed(1);
			comUtilMan.getDynamicFields(ui,comUtil);
			request.setAttribute("fixedfields", comUtil.getComUtilList());
			comUtil.setFixed(0);
			comUtilMan.getDynamicFields(ui,comUtil);
			request.setAttribute("dynamicfields", comUtil.getComUtilList());
			organizColumns(request,strSearchQuery);
			request.setAttribute("request_type", reqType);
			//session.setAttribute("selectedScreenId", 7);
			request.setAttribute("screenId", screenId);
			request.setAttribute("invoke_servlet", "IndentServlet");
			request.setAttribute("invoke_method", "doDisplayIndent");		
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_INDENT, request,response);
			ui=null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//-------------------add indent--------
	public void doAddIndent(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String,String> fileMap=new LinkedHashMap<String, String>();
			String headerMode = getHeaderMode(fileMap,request);
			Indent objIND=new Indent();
			objIND.setHeaderMode(headerMode);
			IndentHeader indHeader=new IndentHeader();
			int indentId=Validator.convertToInteger(request.getParameter("indent_id"));
			indHeader.setIndentId(indentId);

			if(objIND.getHeaderMode().equalsIgnoreCase("n")){
				setNewDefaultData(request,objIND);
			}

			objIND.setHeader(indHeader);

			doAddEditIndent(request,response,objIND);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doAddEditIndent(HttpServletRequest request,HttpServletResponse response, Indent objIND){
		try {
			
			objIND.setScreenId(screenId);
			objIND.setHeaderTableId(headerTableId);
			objIND.setDetailTableId(detailTableId);
			
			objIND.setClientPath(new UserInfo().getClientPath(request));
			
			request.setAttribute("indent", objIND);
			
			TPCSUser ui=new UserInfo().getUserInfo(request);
			
			ExportToPdfTool.setRoundOff(ui);
			
			dynamicFieldsListINDHeader = dynamicFieldManager.getDynamicFields(screenId, headerTableId,ui);
			request.setAttribute("dynamicFieldsListINDHeader", dynamicFieldsListINDHeader);
			request.getSession().setAttribute("dynamicFieldsListINDHeader", dynamicFieldsListINDHeader);
			
			dynamicFieldsListINDDetail = dynamicFieldManager.getDynamicFields(screenId, detailTableId, ui);
			request.setAttribute("dynamicFieldsListINDDetail", dynamicFieldsListINDDetail);
			request.getSession().setAttribute("dynamicFieldsListINDDetail", dynamicFieldsListINDDetail);

			dynamicFormStructureINDHeader = dynamicFieldManager.getDynamicFormStructure(screenId, ui);
			request.setAttribute("dynamicFormStructureINDHeader", dynamicFormStructureINDHeader);

			dynamicFormStructureINDDetail = dynamicFieldManager.getDynamicFormStructure(screenId, ui);
			request.setAttribute("dynamicFormStructureINDDetail", dynamicFormStructureINDDetail);

			dynamicFormEvents =dynamicFieldManager.getDynamicFormEvents(screenId,ui);
			request.setAttribute("dynamicFormEvents", dynamicFormEvents);

			dynamicHeaderFieldEvents =dynamicFieldManager.getDynamicFieldEvents(screenId,headerTableId,ui);
			request.setAttribute("dynamicHeaderFieldEvents", dynamicHeaderFieldEvents);

			dynamicDetailFieldEvents =dynamicFieldManager.getDynamicFieldEvents(screenId,detailTableId,ui);
			request.setAttribute("dynamicDetailFieldEvents", dynamicDetailFieldEvents);

			dynamicHeaderpickListOptions =dynamicFieldManager.getPickListOptions(headerTableId,ui);
			request.setAttribute("dynamicHeaderpickListOptions", dynamicHeaderpickListOptions);


			dynamicDetailpickListOptions =dynamicFieldManager.getPickListOptions(detailTableId,ui);
			request.setAttribute("dynamicDetailpickListOptions", dynamicDetailpickListOptions);
			
			
//			System.out.println("objIND.getSaveType() :"+objIND.getSaveType());
//			System.out.println("objIND.getHeaderMode() :"+objIND.getHeaderMode());
		
			
			if(objIND.getHeaderMode().equals("a") || objIND.getHeaderMode().equals("e")){

				if(objIND.getHeaderMode().equals("a")){
				}
				
				objIND=indMan.getIndentInfo(objIND,ui);
				
				request.setAttribute("header_info", objIND.getHeader());
				request.setAttribute("detList", objIND.getIndentDetList());
				request.setAttribute("detMapList", objIND.getIndentDetMap());
				
				request.setAttribute("attached_files", objIND.getHeader().getAttachFiles());
				request.setAttribute("indAttachPath", objIND.getClientPath());


				//DynamicFields
				
				dynamicFieldsListINDHeader = objIND.getIndentDynList();
				request.setAttribute("dynamicFieldsListINDHeader", dynamicFieldsListINDHeader);

				request.setAttribute("det_id_list",objIND.getDetIdList());
				
			}
			else{
				objIND=indMan.getIndentDefaultData(ui,objIND);
 			
//				System.out.println("else part");
				
				request.setAttribute("header_info",objIND.getHeader());
			}
			
			request.setAttribute("view_mode", request.getParameter("view_mode"));
			
			dynamicFieldsListINDHeader=null;
			dynamicFieldsListINDDetail=null;
			dynamicFormStructureINDHeader=null;
			dynamicFormStructureINDDetail=null;
			dynamicFormEvents=null;
			dynamicHeaderFieldEvents=null;
			dynamicDetailFieldEvents=null;
			dynamicHeaderpickListOptions=null;
			dynamicDetailpickListOptions=null;
			
			ui=null;
			
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_INDENT, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	 public void doGetSoNoDetails(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	    	
	    	try {
	    		
	    		TPCSUser userInfo = new UserInfo().getUserInfo(request);
	    		
	    		int indDetId=Validator.convertToInteger(request.getParameter("indent_det_id"));
	    		
	    		Indent obj=indMan.getSoNoDetailList(userInfo);
	    		String enqNoList = buildSoNoList(obj.getIndentDetList(),userInfo,indDetId);
	    		response.setContentType("text/html");
				response.getWriter().write(enqNoList);
				
				
				enqNoList=null;
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace(); 
			}
	    
			} 
	 
	 
	 public String buildSoNoList(List<IndentDetail> objList,TPCSUser userInfo,int indDetId)
		{
		  
		  
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<div class=\"row table-responsive\">");
		buffer.append("<table class=\"table table-bordered table-condensed\">");

		buffer.append("<thead>");

		buffer.append("<tr class=\"header\">");	 						
		buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini first\">Action</th>");
		buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini\">SO No</th>");
	    buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini\">SO Date</th>");
	    buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini\">Customer</th>");
	    buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini\">Product</th>");
	    buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini\">Color</th>");
	    buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini\">Size Range</th>");
	    buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini\">Required Date</th>");
		
		buffer.append("</tr>");
		buffer.append("</thead>");
		buffer.append("<tbody>");
		 String previous_no ="" ; 
		for(IndentDetail objInd:objList){
			buffer.append("<tr>");
			buffer.append("<td valign=\"middle\"  nowrap=\"nowrap\" align=\"center\" width=\"60px;\">");
			 boolean equal = previous_no.equals(objInd.getSoNo());
//			 if(!equal)
//		        {
				 buffer.append("<a href=\"javascript:selectedSONo('"+indDetId+"','"+objInd.getSoNo()+"','"+objInd.getSoDetId()+"','"+objInd.getSoItemName()+"','"+objInd.getSoColourName()+"','"+objInd.getSoSizeRange()+"','"+objInd.getSoRequiredDate()+"')\">select</a>  "); 
//		        }
//			 else{
//				 buffer.append("&nbsp;");  
//			 }
			
			buffer.append("</td>");
			
			buffer.append("<td>");
			buffer.append(objInd.getSoNo());
			buffer.append("</td>");
			buffer.append("<td nowrap=\"nowrap\">");
			buffer.append(objInd.getSoDate());
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append(objInd.getSoPartyName());
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append(objInd.getSoItemName());
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append(objInd.getSoColourName());
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append(objInd.getSoSizeRange());
			buffer.append("</td>");
			buffer.append("<td nowrap=\"nowrap\">");
			buffer.append(objInd.getSoRequiredDate());
			buffer.append("</td>");
			buffer.append("</tr>");
			
			 previous_no=objInd.getSoNo();
		}
			buffer.append("</tbody>");
			buffer.append("</table>");
			buffer.append("</div>");
		 return buffer.toString();
	
}
//do save indent
	
	public void doSaveIndent(HttpServletRequest request, HttpServletResponse response, FileImport objBean){
		
		
		TPCSUser ui=new UserInfo().getUserInfo(request);
		ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);

		Indent objIND=new Indent();

		objIND=getRequestData(request,ui, objIND, objBean);

		objIND= indMan.saveIndent(ui,objIND,objBean);
		int indId=objIND.getHeader().getIndentId();
		if(indId==-1){

			request.setAttribute("error_message",bundle.getString("Indent.IndentAlreadyExists"));

			objIND.getHeader().setIndentId(0);
			doAddEditIndent(request,response,objIND);

		}
		else{

			if(indId>0){
				if(objIND.getHeaderMode().equals("n")){
					objIND.setHeaderMode("e");	 
				}
				request.setAttribute("success_message",bundle.getString("Indent.IndentInserted")); 
			}
			else{
				request.setAttribute("error_message",bundle.getString("Indent.IndentNotInserted")); 
			}
			if(objIND.getDetailMode().equals("e")){
				objIND.setDetailMode("n"); 
			}

			if(objIND.getSaveType().equals("3")){//Save Type Save & New

				objIND=new Indent();
				IndentHeader indHeader=new IndentHeader();
				objIND.setHeaderMode("n");
				objIND.setDetailMode("n");
				objIND.setHeader(indHeader);
				setNewDefaultData(request,objIND);
				doAddEditIndent(request,response,objIND);
			}
			else  if(objIND.getSaveType().equals("4")){//Save Type Save & Close
				doDisplayIndent(request,response) ;
			}
			else {

				if(objIND.getSaveType().equals("2")){//button type save&send

					int type=1;
					if(objIND.getEditMode().equalsIgnoreCase("e")){
						type=2;
					}

					if(!objIND.getHeader().isSendLater()){


					}

				}

				doAddEditIndent(request,response,objIND);

			}
		}

	}

	
	public void doUpdateIndentColumnPreferences(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{  

			HttpSession session=request.getSession();  
			TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
			String strTotalColumns = request.getParameter("total_columns");  
			String strRequestType=request.getParameter("request_type");
			String strSearchQuery="";

			ColumnPreferenceManager cpm=new ColumnPreferenceManager();   
			//System.out.println("strTotalColumns :"+strTotalColumns);
			boolean bFlag = cpm.updateColumnPreferences(strTotalColumns,SubdocumentId.INDENT,userInfo);

			organizColumns(request,strSearchQuery);
			request.setAttribute("request_type", strRequestType);
			cpm=null;
//			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_INDENT,request,response);
			
			doDisplayIndent(request, response);
			
		}
		catch(Exception e){ 
			e.printStackTrace();      
		}
	}


	
	
	public void doDeleteIndentDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Map<String,String> fileMap=new LinkedHashMap<String, String>();
			TPCSUser ui=new UserInfo().getUserInfo(request);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			Indent objIND=new Indent();
			objIND.setHeaderMode(getHeaderMode(fileMap,request));
			objIND.setDetailMode(getDetailMode(fileMap,request));

			int indentId=Validator.convertToInteger(request.getParameter("indent_id"));
			int indentDetId=Validator.convertToInteger(request.getParameter("indent_det_id"));
			
			IndentHeader header=new IndentHeader();
			header.setIndentId(indentId);
			objIND.setHeader(header);
			
			IndentDetail detail=new IndentDetail();
			detail.setIndentDetId(indentDetId);
			objIND.setDetail(detail);
			detail=null;
			
			objIND= indMan.deleteIndentDetail(ui,objIND);
			
			if(objIND.getDeletedCount()==1){
				request.setAttribute("success_message",bundle.getString("Indent.IndentDetailDeleted"));	
				doAddEditIndent(request,response,objIND);
			}
			else if(objIND.getDeletedCount()==2){
				objIND=new Indent();
				IndentHeader sqHeader=new IndentHeader();
				objIND.setHeader(sqHeader);
				setNewDefaultData(request,objIND);
				request.setAttribute("success_message",bundle.getString("Indent.IndentDeleted"));
				objIND.setHeaderMode("n");
				objIND.setDetailMode("n");
				doAddEditIndent(request,response,objIND);
				sqHeader=null;
			}
			else{
				request.setAttribute("error_message",bundle.getString("Indent.IndentDetailNotDeleted"));
				doAddEditIndent(request,response,objIND);
			}
		
			fileMap=null;
			objIND=null;
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void doDeleteBulkIndent(HttpServletRequest request,HttpServletResponse response) 
	{
		try{
			TPCSUser ui =(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundleProvider rbp=new ResourceBundleProvider();
			ResourceBundle bundle = rbp.getResourceBundle(ui);
			Indent objIND=new Indent();
			IndentHeader header=new IndentHeader();

			String indentIds[]=request.getParameterValues("indent_chk_id");

			int indId=0;
			//---------------------------------------------------------------------------------------------
			for(int j=0;j<indentIds.length;j++){
				header=new IndentHeader();
				indId=Integer.parseInt(indentIds[j]);

				header.setIndentId(indId);
				objIND.setHeader(header);

				objIND=indMan.deleteIndent(ui,objIND);
				header=null;
			}
			//---------------------------------------------------------------------------------------------

			if(objIND.isDeleted()){
				request.setAttribute("success_message",bundle.getString("Indent.IndentDeleted"));
				
			}
			else{
				request.setAttribute("error_message",bundle.getString("Indent.IndentNotDeleted"));
				
			}
			doDisplayIndent(request, response);
			rbp=null;
			objIND=null;
			header=null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void doDeleteIndent(HttpServletRequest request,HttpServletResponse response){
		try{
			TPCSUser ui=new UserInfo().getUserInfo(request);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			int indentId=Validator.convertToInteger(request.getParameter("indent_id"));
			int page=Validator.convertToInteger(request.getParameter("page"));
			
			Indent objIND=new Indent();
			
			IndentHeader header=new IndentHeader();
			header.setIndentId(indentId);
			objIND.setHeader(header);
			
			objIND=indMan.deleteIndent(ui,objIND);
			if(objIND.isDeleted()){
				request.setAttribute("success_message",bundle.getString("Indent.IndentDeleted"));
				
			}
			else{
				request.setAttribute("error_message",bundle.getString("Indent.IndentNotDeleted"));
				
			}

			String strSearchQuery="";
			
			 if(page>0){
				 objIND=null;
				 objIND=new Indent();
				    IndentHeader sqHeader=new IndentHeader();
				    objIND.setHeader(sqHeader);
					setNewDefaultData(request,objIND);
					request.setAttribute("success_message",bundle.getString("Indent.IndentDeleted"));
					objIND.setHeaderMode("n");
					objIND.setDetailMode("n");
					doAddEditIndent(request,response,objIND);
					sqHeader=null;
		     }
			 else{
		    	 organizColumns(request,strSearchQuery);
		    	 
		    	 TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_INDENT,request,response);
		     }

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}	

	
	
	
	
	public void doCancelBulkIndent(HttpServletRequest request,HttpServletResponse response) 
	{
		try{
			TPCSUser userInfo =(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundleProvider rbp=new ResourceBundleProvider();
			ResourceBundle bundle = rbp.getResourceBundle(userInfo);
			Indent objIND=new Indent();
			IndentHeader header=new IndentHeader();

			String indentIds[]=request.getParameterValues("indent_chk_id");

			int indId=0;
			//---------------------------------------------------------------------------------------------
			for(int j=0;j<indentIds.length;j++){
				header=new IndentHeader();
				indId=Integer.parseInt(indentIds[j]);

				header.setIndentId(indId);
				objIND.setHeader(header);

				objIND=indMan.cancelIndent(userInfo,objIND);
				header=null;
			}
			//---------------------------------------------------------------------------------------------

			if(objIND.isCancelled()){
				request.setAttribute("success_message",bundle.getString("Indent.IndentCancelled"));

			}
			else{
				request.setAttribute("error_message",bundle.getString("Indent.IndentNotCancelled"));
			}
			doDisplayIndent(request, response);
			rbp=null;
			objIND=null;
			header=null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void doCancelIndent(HttpServletRequest request,HttpServletResponse response){
		try{
//		System.out.println("doCancelIndent Servlet");
			
			TPCSUser ui=new UserInfo().getUserInfo(request);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			int indentId=Validator.convertToInteger(request.getParameter("indent_id"));
			
			Indent objIND=new Indent();
			
			IndentHeader header=new IndentHeader();
			header.setIndentId(indentId);
			objIND.setHeader(header);
			
			objIND=indMan.cancelIndent(ui,objIND);
			
			if(objIND.isCancelled()){
				request.setAttribute("success_message",bundle.getString("Indent.IndentCancelled"));
		
			}
			else{
				request.setAttribute("error_message",bundle.getString("Indent.IndentNotCancelled"));
			}
			doDisplayIndent(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public void doCloseBulkIndent(HttpServletRequest request,HttpServletResponse response) 
	{
		try{
			TPCSUser ui =(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundleProvider rbp=new ResourceBundleProvider();
			ResourceBundle bundle = rbp.getResourceBundle(ui);
			Indent objIND=new Indent();
			IndentHeader header=new IndentHeader();

			String indentIds[]=request.getParameterValues("indent_chk_id");

			int indId=0;
			//---------------------------------------------------------------------------------------------
			for(int j=0;j<indentIds.length;j++){
				header=new IndentHeader();
				indId=Integer.parseInt(indentIds[j]);

				header.setIndentId(indId);
				objIND.setHeader(header);

				objIND=indMan.closeIndent(ui,objIND);
				header=null;
			}
			//---------------------------------------------------------------------------------------------

			if(objIND.isClosed()){
				request.setAttribute("success_message",bundle.getString("Indent.IndentClosed"));

			}
			else{
				request.setAttribute("error_message",bundle.getString("Indent.IndentNotClosed"));
			}
			doDisplayIndent(request, response);
			rbp=null;
			objIND=null;
			header=null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void doCloseIndent(HttpServletRequest request,HttpServletResponse response){
		try{
			TPCSUser ui=new UserInfo().getUserInfo(request);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			int indentId=Validator.convertToInteger(request.getParameter("indent_id"));
			
			Indent objIND=new Indent();

			IndentHeader header=new IndentHeader();
			header.setIndentId(indentId);
			objIND.setHeader(header);
					
			objIND=indMan.closeIndent(ui,objIND);
			
//			System.out.println("isClosed==== " +objIND.isClosed());
			
			if(objIND.isClosed()){
				request.setAttribute("success_message",bundle.getString("Indent.IndentClosed"));
			}
			else{
				request.setAttribute("error_message",bundle.getString("Indent.IndentNotClosed"));
			}
			doDisplayIndent(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private String getHeaderMode(Map<String,String> fileMap,HttpServletRequest request){
		String headMode= Validator.trim(request.getParameter("header_mode"));
		return headMode; 	
	}
	
	private String getDetailMode(Map<String,String> fileMap,HttpServletRequest request) {
		String	detMode=Validator.trim(request.getParameter("det_mode"));
		return detMode; 
	}
	
	public void setNewDefaultData(HttpServletRequest request, Indent objIND){
		request.setAttribute("indent_date",TPCSCommonUtil.currenDate("dd-MM-yyyy"));
		request.setAttribute("new_page","yes");
	}
	
	
	public void organizColumns(HttpServletRequest request,String strSearchQuery){
		try{
			TPCSUser userInfo=new UserInfo().getUserInfo(request);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);

			request.setAttribute("subdocument_id", SubdocumentId.INDENT);
			
			ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
			ColumnPreference DataList=cpm.getVisibleColumnNames(SubdocumentId.INDENT, userInfo);
			
			ExportToPdfTool.setRoundOff(userInfo);
			
			Map<String,String> visColMap=cpm.getVisibleColumnNamesMap(SubdocumentId.INDENT,userInfo.getUserId(), userInfo);
			List<String> listSelectedColumns=DataList.getVisibleColumns(); 
			
			List<String> orderBy=DataList.getOrderBy(); 

			String strPageNo = request.getParameter("pageno");
			
			int ipageno =Validator.convertToInteger(strPageNo);

			PageConfig pc=new PageConfig(request.getParameter("pageno"));
		    int pageCount= indMan.getTotalPages(userInfo,pc, strSearchQuery);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);
			
			doGetUserRights(request);
			rights=(UserRights) request.getSession().getAttribute("indent_rights");

			Indent ind=new Indent();
			
			ind.setScreenId(screenId);
			ind.setHeaderTableId(headerTableId);
			ind.setDetailTableId(detailTableId);
			ind.setSubdocumentId(SubdocumentId.INDENT);
			ind.setAttachPath(new UserInfo().getClientPath(request));
			ind.setSearchCriteria(strSearchQuery);
			ind.setOrderByList(orderBy);
			ind =indMan.getAllIndentOnColumnMapping(pc,userInfo,ind);
			
			ind.setVisColMap(visColMap);
			ind.setListSelectedColumns(listSelectedColumns);
			String Grid = new IndentGridMaker().formIndentGrid(ind,bundle,rights,pc);
			request.setAttribute("ind_grid", Grid);

			DataList=null;
			visColMap=null;
			Grid=null;
			userInfo=null;
			cpm=null;
			listSelectedColumns=null;
			orderBy=null;
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void doGetUserRights(HttpServletRequest request){
		TPCSUser userInfo=new UserInfo().getUserInfo(request);
		HttpSession session=request.getSession();
		
		if(request.getSession().getAttribute("indent_rights")!=null){
			rights =(UserRights)request.getSession().getAttribute("indent_rights");
		}
		else{
			rights =objRight.getUserRights(SubdocumentId.INDENT, userInfo);
			session.setAttribute("indent_rights",rights);		
		}
		
	}
	
	
	private String getDefaultCriteria(HttpServletRequest request){
		StringBuffer  strQuery=new StringBuffer();
		
		String indType = request.getParameter("ind_type");
//		strQuery.append(" and isnull(ind.cancel_tag,0)<>1");
		
		if(indType==null || indType.equalsIgnoreCase("-1")){
			
		}
		
		return strQuery.toString();
	}
	
	
	public Indent getRequestData(HttpServletRequest request,TPCSUser ui,Indent objIND,FileImport objBean){
		String realPath=(String) request.getSession().getAttribute("path");
		String contextPath=realPath+new UserInfo().getClientPath(request);
		objBean.setFolderPath(contextPath);
		Map<String,String> fileMap=new LinkedHashMap<String, String>();

		dynamicFieldsListINDHeader = (List<DynamicFields>) request.getSession().getAttribute("dynamicFieldsListINDHeader");
		dynamicFieldsListINDDetail= (List<DynamicFields>) request.getSession().getAttribute("dynamicFieldsListINDDetail");


		List<String> chkHead=new ArrayList<String>();
		List<String> chkDetail=new ArrayList<String>();

		for(DynamicFields dynamicFields: dynamicFieldsListINDHeader) {
			//Checking the condition - if dynamic field i.e., isFixed  =  false
			if(!dynamicFields.isFixed()) {
				chkHead.add(dynamicFields.getPageFieldName());
			}
		}
		int quoteDetId=Validator.convertToInteger(fileMap.get("indent_det_id"));
		String detId="_"+quoteDetId;

		for(DynamicFields dynamicFields: dynamicFieldsListINDDetail) {
			//Checking the condition - if dynamic field i.e., isFixed  =  false
			if(!dynamicFields.isFixed()) {
				chkDetail.add(dynamicFields.getPageFieldName()+detId);
			}
		}

	
		FileItem item =null;
		Iterator in = objBean.getFileItems().iterator();
		while ( in.hasNext () ){ 
			item = (FileItem)in.next();

			if ( item.isFormField() ){
				fileMap.put(item.getFieldName(), item.getString());

				if(chkHead.contains(item.getFieldName())){

					for(DynamicFields dynamicFields: dynamicFieldsListINDHeader) {
						//Checking the condition - if dynamic field i.e., isFixed  =  false
								if(!dynamicFields.isFixed()) {
									if(dynamicFields.getFieldTypeName().equalsIgnoreCase("checkbox")){
										List<String> checkboxValues = new ArrayList<String>();

										if (dynamicFields.getPageFieldName().equals(item.getFieldName())) {
											checkboxValues.add(item.getString());
										}
										String checkBoxValueArray[] = checkboxValues.toArray(new String[checkboxValues.size()]);

										String checkBoxValues = "";
										if(checkBoxValueArray!=null) {
											for(String checkBoxValue : checkBoxValueArray){
												checkBoxValues = checkBoxValues + checkBoxValue + ",";
											}
											dynamicFields.setFieldValue(checkBoxValues);
										}
										checkboxValues=null;
									} else {

										dynamicFields.setFieldValue((String) StringEscapeUtils.escapeXml(fileMap.get(dynamicFields.getPageFieldName())));
									}
								}
					}
				}
				if(chkDetail.contains(item.getFieldName())){

					for(DynamicFields dynamicFields: dynamicFieldsListINDDetail) {
						//Checking the condition - if dynamic field i.e., isFixed  =  false
						if(!dynamicFields.isFixed()) {

							String PageFieldName=dynamicFields.getPageFieldName()+detId;
							
							if (PageFieldName.equals(item.getFieldName())) {
								dynamicFields.setFieldValue((String) StringEscapeUtils.escapeXml(item.getString()));//request.getParameter(dynamicFields.getPageFieldName()));
							}
						}
					}
				}

			}
		}
		//calling xml for dynamic fields
		String sqlxml = generateXMLforDynamicFields(dynamicFieldsListINDHeader,dynamicFieldsListINDDetail);
		

		objIND=setIndentAttributes(fileMap,request,ui,objIND);

		//added for dynamic fields 
		objIND.setSqlxmlDynamicFields(sqlxml);
		
		String editedIds=fileMap.get("edited_ids");
		String dynEditedIds=fileMap.get("dynedited_ids");

		Map<String,String> editDataMap= new HashMap<String, String>();
		Map<String,String> editDynamicDataMap= new HashMap<String, String>();

		if(objIND.getEditMode().equalsIgnoreCase("e")){
			
			String streditedIds[] = editedIds.split(",");
			for(int i=0; i < streditedIds.length; i++){

				editDataMap.put(streditedIds[i], fileMap.get(streditedIds[i]));

			}

			String strDyneditedIds[] = dynEditedIds.split(",");
			for(int i=0; i < strDyneditedIds.length; i++){

				editDynamicDataMap.put(strDyneditedIds[i], fileMap.get(strDyneditedIds[i]));

			}
			
			objIND.setEditDataMap(editDataMap);
			objIND.setEditDynamicDataMap(editDynamicDataMap);

		}
		return objIND;
	}
	
	
	private String generateXMLforDynamicFields( List<DynamicFields> dynamicFieldsListINDHeader, List<DynamicFields> dynamicFieldsListINDDetail) {
		
		String strXML = "<screens> ";
		String formIND = "<screen name=\"indent\">  <columns> ";
		String formINDDetail = "<screen name=\"indent_detail\">  <columns> ";

		if(dynamicFieldsListINDHeader!=null) {
			for (DynamicFields dynamicFields : dynamicFieldsListINDHeader) {
				if (!dynamicFields.isFixed()) {
					
//					System.out.println("In generateXMLforDynamicFields ==== " + !dynamicFields.isFixed());
					
					String column = " <column type=\"dynamic\"> <name>" + dynamicFields.getDbFieldName() + "</name> <value>" + dynamicFields.getFieldValue() + "</value> <datatype>" + dynamicFields.getDataTypeName() + "</datatype> </column> ";
					formIND = formIND.concat(column);
				} 
			}
		}
		
		formIND = formIND.concat(" </columns> </screen> ");
		formIND = formIND.concat(formINDDetail);
		
		if(dynamicFieldsListINDDetail!=null) {
			for (DynamicFields dynamicFields : dynamicFieldsListINDDetail) {
				if (!dynamicFields.isFixed()) {	
					String column = " <column type=\"dynamic\"> <name>" + dynamicFields.getDbFieldName() + "</name> <value>" +dynamicFields.getFieldValue()+ "</value> <datatype>" + dynamicFields.getDataTypeName() + "</datatype> </column> ";
					formIND = formIND.concat(column);
				} 
			}
		}

		formIND = formIND.concat(" </columns> </screen> ");
		formIND = formIND.concat(" </screens> ");
		strXML = strXML.concat(formIND);
		
		return strXML;
	}
	
	
	private Indent setIndentAttributes(Map<String,String> fileMap,HttpServletRequest request,TPCSUser userInfo,Indent objIND) {
		IndentHeader header= setHeaderAttributes(fileMap,request,userInfo);
		IndentDetail detail= setDetailAttributes(fileMap,request,userInfo);
		
		objIND.setSaveType(fileMap.get("save_type"));
		objIND.setEditMode(fileMap.get("edited_mode"));
		
		objIND.setHeader(header);
		objIND.setDetail(detail);
		objIND.setHeaderMode(fileMap.get("header_mode"));
		objIND.setDetailMode(fileMap.get("new_det_mode"));
		
		header=null;
		detail=null;
		
		return objIND;
	}
	
	
	private IndentHeader setHeaderAttributes(Map<String,String> fileMap,HttpServletRequest request,TPCSUser userInfo) {
		IndentHeader header= new IndentHeader();
		
		header.setIndentId(Validator.convertToInteger(fileMap.get("indent_id")));
		header.setIndentNo(Validator.convertToInteger(fileMap.get("indent_no")));
		header.setIndentPrefix(fileMap.get("prefix"));
		header.setIndentPrefixNo(header.getIndentPrefix()+header.getIndentNo());
		header.setIndentDate(fileMap.get("indent_date"));
		header.setIndentType(fileMap.get("indent_type"));
		header.setDept(fileMap.get("department"));
		header.setRemarks(fileMap.get("remarks"));
		header.setOtherRef(fileMap.get("other_ref"));
		
		

		getTCInfo(fileMap,header);
		
		return header;
		
	}
	
	
	private IndentDetail setDetailAttributes(Map<String,String> fileMap,HttpServletRequest request,TPCSUser userInfo) {
		IndentDetail detail= new IndentDetail();
		
		String tmpDetId="_0";
		
		detail.setSoDetId(Validator.convertToInteger(fileMap.get("so_det_id"+tmpDetId)));
		
		
		detail.setIndentDetId(Validator.convertToInteger(fileMap.get("indent_det_id")));
	//	detail.setIndentLineNo((fileMap.get("indent_line_no"+tmpDetId)));
		detail.setItemName(fileMap.get("item_name"+tmpDetId));
		detail.setItemId(Validator.convertToInteger(fileMap.get("item_id"+tmpDetId)));
		detail.setColourName(fileMap.get("variant_name"+tmpDetId));
		detail.setColourId(Validator.convertToInteger(fileMap.get("variant_id"+tmpDetId)));
		detail.setSizeRange(fileMap.get("size_range"+tmpDetId));
		detail.setSizeRangeId(Validator.convertToInteger(fileMap.get("size_range_id"+tmpDetId)));
		detail.setIndentQty(fileMap.get("indent_qty"+tmpDetId));
		detail.setUom(fileMap.get("uom"+tmpDetId));
		detail.setRequiredDate(fileMap.get("required_date"+tmpDetId));
		detail.setPartyName((fileMap.get("party_name"+tmpDetId)));
		detail.setPartyId((fileMap.get("party_id"+tmpDetId)));
		detail.setRemark(fileMap.get("remark"+tmpDetId));
	
		getDetailSizeData(fileMap, detail);
		
		return detail;
	}
	
	
	private void getTCInfo(Map<String, String> fileMap, IndentHeader sih) {
		String tcIds=fileMap.get("ind_tc_ids");
		String[] arTcIds=tcIds.split(",");
		List<String> listTcIds = new ArrayList<String>(Arrays.asList(arTcIds));
		listTcIds.removeAll(Arrays.asList("", null));
		StringBuffer buffer=new StringBuffer();
		buffer.append("<tc_ifo>");
		for(String rowId:listTcIds){
			
			if(!fileMap.get("tc_text_"+rowId).isEmpty()){
			
				buffer.append("<tc>");
				
				buffer.append("<slno>");
				buffer.append(Validator.convertToInteger(fileMap.get("tc_slno_"+rowId)));
				buffer.append("</slno>");
				
				buffer.append("<terms>");
				buffer.append(StringEscapeUtils.escapeXml(fileMap.get("tc_text_"+rowId)!=null?fileMap.get("tc_text_"+rowId):""));
				buffer.append("</terms>");
				
				buffer.append("<particulars>");
				buffer.append(StringEscapeUtils.escapeXml(fileMap.get("tc_particular_"+rowId)!=null?fileMap.get("tc_particular_"+rowId):""));
				buffer.append("</particulars>");
				
				buffer.append("</tc>");
			}
		}
		buffer.append("</tc_ifo>");
		sih.setTcValues(buffer.toString());
		
		buffer=null;
		listTcIds=null;
	}
	
	
	
	public String getSearchCriteria(HttpServletRequest request){

		StringBuffer  strQuery=new StringBuffer();
		TPCSUser ui=new UserInfo().getUserInfo(request);
		int partyId=Validator.convertToInteger(request.getParameter("supplier_id"));
		String indentFromDate= request.getParameter("indent_from_date");
		String indentToDate= request.getParameter("indent_to_date");
		String indentNo= request.getParameter("indent_no");
		String indenType= request.getParameter("indent_type");
		String dept=request.getParameter("deptartment");
		String remarks= request.getParameter("remarks");
		String otherRef=request.getParameter("other_ref");

		String indType=request.getParameter("ind_type");


		if(indentFromDate!=null && indentFromDate.length()>0){
			strQuery.append(" and ind.indent_date>=convert(datetime,'"+indentFromDate+"',105) "); 
		}
		if(indentToDate!=null && indentToDate.length()>0){
			strQuery.append(" and ind.indent_date<=convert(datetime,'"+indentToDate+"',105)");
		}
		if(partyId!=0){
			strQuery.append("and p.party_id="+partyId);
		}
		if(indentNo!=null  && !indentNo.isEmpty()){
			strQuery.append(" and ind.prefix+convert(nvarchar,ind.Indent_No)='"+indentNo.replace("'","''")+"'");
		}
		if(indenType!=null  && !indenType.isEmpty()){
			strQuery.append("and ind.indent_type='"+indenType.replace("'","''")+"'");
		}
		if(dept!=null && !dept.isEmpty()){
			strQuery.append("and ind.Department='"+dept.replace("'","''")+"'");
		}
		if(remarks!=null && !remarks.isEmpty()){
			strQuery.append(" and ind.remarks LIKE '%"+remarks.replace("'","''")+"%'");
		}
		if(otherRef!=null && !otherRef.isEmpty()){
			strQuery.append(" and ind.other_ref LIKE '%"+otherRef.replace("'","''")+"%'");
		}
		if(indType== null || indType.equalsIgnoreCase("-1")){
			strQuery.append(" and isnull(ind.cancel_tag,0)<>1 ");
		}
		
		else if(indType!= null && indType.equalsIgnoreCase("Cancelled")){
			strQuery.append(" and isnull(ind.cancel_tag,0)=1 ");
		}
		else if(indType!= null && indType.equalsIgnoreCase("Closed")){
			strQuery.append(" and isnull(ind.close_tag,0)=1 ");
		}
		
		/*else if(indType!= null && indType.equalsIgnoreCase("Open")){
			strQuery.append(" and isnull(ind.open_tag,0)=1 ");
		}*/
		
		
		return strQuery.toString();

	}	
	
	
	public void getAndSetAttributes(HttpServletRequest request){
		request.setAttribute("page_size", PageConfig.iPageSize);
		request.setAttribute("indent_name",request.getParameter("indent_name"));
		request.setAttribute("indent_id",request.getParameter("indent_id"));
		request.setAttribute("indent_type",request.getParameter("indent_type"));
		request.setAttribute("dept",request.getParameter("dept"));
		request.setAttribute("indent_from_date",request.getParameter("indent_from_date"));
		request.setAttribute("indent_to_date",request.getParameter("indent_to_date"));
		request.setAttribute("remarks",request.getParameter("remarks"));
		request.setAttribute("other_ref",request.getParameter("other_ref"));
		request.setAttribute("ind_type",request.getParameter("ind_type"));
	}
	


	public void doGetINDSizeRangeSizeGrid(HttpServletRequest request, HttpServletResponse response){
		try {

			int itemId=Validator.convertToInteger(request.getParameter("item_id"));
			int sizeRangeId=Validator.convertToInteger(request.getParameter("size_range_id"));
			int indentId=Validator.convertToInteger(request.getParameter("indent_id"));
			int indentDetId=Validator.convertToInteger(request.getParameter("indent_det_id"));

			
			System.out.println("itemId :"+itemId);
			System.out.println("sizeRangeId :"+sizeRangeId);
			System.out.println("indentId :"+indentId);
			System.out.println("indentDetId :"+indentDetId);
			
			TPCSUser ui=new UserInfo().getUserInfo(request);
			Indent objIND=new Indent();
			IndentHeader header=new IndentHeader();
			IndentDetail detail=new IndentDetail();

			header.setIndentId(indentId);
			detail.setIndentDetId(indentDetId);
			detail.setItemId(itemId);
			detail.setSizeRangeId(sizeRangeId);
		
		
			objIND.setHeader(header);
			objIND.setDetail(detail);


			objIND=indMan.getINDSizeRangeSizeGrid(ui,objIND);

			if(objIND.getDetail().isSizeExists()){

				formSizeData(objIND.getDetail());

			}
			StringBuffer resultXML=new StringBuffer();
			resultXML.append("<size_range_size>");
			
			resultXML.append("<size>");
			resultXML.append("<size_exists>");
			resultXML.append(objIND.getDetail().isSizeExists()==true?"1":"0");
			resultXML.append("</size_exists>");
			resultXML.append("<size_grid>");
			resultXML.append(StringEscapeUtils.escapeXml(objIND.getDetail().getSizeData()));
			resultXML.append("</size_grid>");
			resultXML.append("</size>");

			resultXML.append("</size_range_size>");

			response.setContentType("text/xml");
			response.getWriter().write(resultXML.toString());

			header=null;
			detail=null;
			resultXML=null;
			objIND=null;
			ui=null;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	public void formSizeData(IndentDetail detail){
		
		StringBuffer parentBuffer=new StringBuffer();
		List<Size> sizeList=detail.getSizeList();
		
		
		StringBuffer headerBuffer=new StringBuffer();
		StringBuffer qtyBuffer=new StringBuffer();
		//StringBuffer rateBuffer=new StringBuffer();
		parentBuffer.append("<table class=\"table-bordered table-condensed  size-table\" id=\"grid_table\">");
		
		headerBuffer.append("<tr class=\"header-det\">");
		headerBuffer.append("<th>");
		headerBuffer.append("Size");
		headerBuffer.append("</th>");
		
		qtyBuffer.append("<tr>");
		qtyBuffer.append("<td><div class=\"form-group\">Qty</div>");
		qtyBuffer.append("<input type=\"hidden\" id=\"size_ids_"+detail.getIndentDetId()+"\" name=\"size_ids_"+detail.getIndentDetId()+"\" value=\""+detail.getSizeIdList().toString().replace("[", "").replaceAll("]", "").replace(" ", "")+"\" />"); 
		qtyBuffer.append("<input type=\"hidden\" id=\"size_tot_qty_"+detail.getIndentDetId()+"\" name=\"size_tot_qty_"+detail.getIndentDetId()+"\" value=\""+detail.getSizeTotalQty()+"\" />");
		qtyBuffer.append("</td>");
		
//		rateBuffer.append("<tr>");
//		rateBuffer.append("<td><div class=\"form-group\">Price</div>");
//		rateBuffer.append("</td>");
		
		
		for(Size size:sizeList){
			headerBuffer.append("<th>");
			headerBuffer.append(""+size.getSizeName()+"");
			headerBuffer.append("</th>");
			
			qtyBuffer.append("<td>");
			qtyBuffer.append("<div class=\"form-group\">"
					+ "<input type=\"text\" class=\"form-control\" id=\"size_qty_"+detail.getIndentDetId()+"_"+size.getSizeId()+"\" name=\"size_qty_"+detail.getIndentDetId()+"_"+size.getSizeId()+"\" value=\""+size.getQty()+"\" onkeyup=\"calculateTotalQty("+detail.getIndentDetId()+")\" maxlength=\"8\" placeholder=\"Enter qty\" />"
					+ "</div>");
			qtyBuffer.append("</td>");
			
//			rateBuffer.append("<td>");
//			rateBuffer.append("<div class=\"form-group\">"
//					+ "<input type=\"text\" class=\"form-control\" id=\"size_rate_"+detail.getIndentDetId()+"_"+size.getSizeId()+"\" name=\"size_rate_"+detail.getIndentDetId()+"_"+size.getSizeId()+"\" value=\""+size.getRate()+"\"onkeyup=\"calculateTotalQty("+detail.getIndentDetId()+")\" maxlength=\"8\" placeholder=\"Enter Rate\" />"
//					+ "</div>");
//			rateBuffer.append("</td>");
			
			
		}
		qtyBuffer.append("</tr>");
		//rateBuffer.append("</tr>");
		headerBuffer.append("</tr>");
		
		
		parentBuffer.append(headerBuffer.toString());
		parentBuffer.append(qtyBuffer.toString());
//		parentBuffer.append(rateBuffer.toString());
		
		headerBuffer=null;
		qtyBuffer=null;
		//rateBuffer=null;
		parentBuffer.append("</table>");
		
		
		detail.setSizeData(parentBuffer.toString());
		
	}
	
	
	
	
	private void getDetailSizeData(Map<String, String> fileMap, IndentDetail detail){
	

		String editedDetIds=fileMap.get("edited_size_det_ids");
		String[] arEditedDetIds=editedDetIds.split(",");
		List<String> editedDetIdList=Arrays.asList(arEditedDetIds);

		Set<String> hs = new HashSet<String>();
		hs.addAll(editedDetIdList);
		editedDetIdList=new ArrayList<String>();
		editedDetIdList.addAll(hs);

		StringBuffer sizeBuffer=new StringBuffer();
		sizeBuffer.append("<size_range_size>");

		for(String detId:editedDetIdList){

			//		if(!detId.isEmpty()){

			//		System.out.println("getDetailSizeData==== detId=== " +detId);




			String sizeIds=fileMap.get("size_ids_"+detId);
			if(sizeIds!=null && !sizeIds.isEmpty()){
				String[] arSizeIds=sizeIds.split(",");
				List<String> sizeIdList=Arrays.asList(arSizeIds);

				for(String sizeId:sizeIdList){

					if(Validator.convertToFloat(fileMap.get("size_qty_"+detId+"_"+sizeId))>0){
						sizeBuffer.append("<size_detail>");

						sizeBuffer.append("<detail_id>");
						sizeBuffer.append(detId);
						sizeBuffer.append("</detail_id>");

						sizeBuffer.append("<size_id>");
						sizeBuffer.append(sizeId);
						sizeBuffer.append("</size_id>");

						sizeBuffer.append("<size_qty>");
						sizeBuffer.append(String.valueOf(Validator.convertToFloat(fileMap.get("size_qty_"+detId+"_"+sizeId))));
						sizeBuffer.append("</size_qty>");

						//System.out.println("getDetailSizeData==== sizeId=== " +sizeId);


						//sizeBuffer.append("<size_rate>");
						//sizeBuffer.append(String.valueOf(Validator.convertToFloat(fileMap.get("size_rate_"+detId+"_"+sizeId))));
						//sizeBuffer.append("</size_rate>");

						sizeBuffer.append("</size_detail>");


					}

				}
				//}

				sizeIds=null;
				arSizeIds=null;
				sizeIdList=null;
			}
		}
		sizeBuffer.append("</size_range_size>");

		System.out.println("getDetailSizeData=== sizeBuffer===  " +sizeBuffer.toString());

		editedDetIds=null;
		arEditedDetIds=null;
		editedDetIdList=null;
		hs =null;

		detail.setSizeData(sizeBuffer.toString());
		sizeBuffer=null;
	}




	public void doPrintIndent(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{  
			HttpSession session=request.getSession();
			TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
			int subId=SubdocumentId.INDENT;

			int indentId=Validator.convertToInteger(request.getParameter("indent_id"));
			Indent header=indMan.IndentPrintHeader(7,indentId,userInfo);

			String materialAttachPath=new UserInfo().getMaterialAttachPath(request);
			List<IndentDetail> printdetList=indMan.getIndentDetailsForPrint(indentId,userInfo,materialAttachPath);
			CompanyAndYearSelectionManager objCompany=new CompanyAndYearSelectionManager();
			Company objCompanyDet = objCompany.getCompanyInfo(userInfo);
			request.setAttribute("company_det", objCompanyDet);
			request.setAttribute("header_info", header.getHeader());
			request.setAttribute("detList", printdetList);

			dynamicFieldsListINDHeader = dynamicFieldManager.getDynamicFields(7, 8,userInfo);
			request.setAttribute("dynamicFieldsListINDHeader", dynamicFieldsListINDHeader);
			request.getSession().setAttribute("dynamicFieldsListINDHeader", dynamicFieldsListINDHeader);
			dynamicFieldsListINDDetail = dynamicFieldManager.getDynamicFields(7, 9, userInfo);
			request.setAttribute("dynamicFieldsListINDDetail", dynamicFieldsListINDDetail);
			request.getSession().setAttribute("dynamicFieldsListINDDetail", dynamicFieldsListINDDetail);

			request.setAttribute("indent_id", indentId);
			request.setAttribute("subdocument_id", subId);

			header=null;
			dynamicFieldsListINDHeader=null;
			dynamicFieldsListINDDetail=null;
			printdetList=null;
			objCompanyDet=null;
			objCompany=null;
			materialAttachPath=null;
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.PRINT_INDENT,request,response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	public void doGetItemData(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try{
//			HttpSession session=request.getSession();
			TPCSUser ui=new UserInfo().getUserInfo(request);
			int itemId=Validator.convertToInteger(request.getParameter("item_id"));
			String trTag=request.getParameter("tr_tag");
			System.out.println("itemId :"+itemId);
			String strTechDetails=indMan.doGetItemData(ui,itemId,trTag);
			response.setContentType("text/xml");
			response.getWriter().write(strTechDetails);

			
			ui=null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	/*

public void doExportBulkIndentPdf(HttpServletRequest request,HttpServletResponse response){
	try
	{
		String indentIds[]=request.getParameterValues("indent_id");

		ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request); 
		HttpSession session=request.getSession();
		TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");




		Map<Integer,String> subdocument=(Map<Integer,String>)session.getAttribute("subdocuments");

	//	String enq_type=request.getParameter("enq_type");
		String screenName;

		if(enq_type!=null && enq_type.equals("Cancelled"))
		{
		  screenName="Cancelled Enquiry";
		}
//		else{
		  screenName=bundle.containsKey(subdocument.get(SubdocumentId.INDENT))?bundle.getString(subdocument.get(SubdocumentId.INDENT)):subdocument.get(SubdocumentId.INDENT); 
//		}


		float pageMarginLeft=Validator.convertToFloat(bundle.getString("ExportToPdf.MarginLeft"));
		float pageMarginRight=Validator.convertToFloat(bundle.getString("ExportToPdf.MarginRight"));
		float pageMarginTop=Validator.convertToFloat(bundle.getString("ExportToPdf.MarginTop"));
		float pageMarginBottom=Validator.convertToFloat(bundle.getString("ExportToPdf.MarginBottom"));

		Document document = new Document(PageSize.A4, pageMarginLeft, pageMarginRight, pageMarginTop, pageMarginBottom);
		ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, pdfReport);
		HeaderFooterPageEvent event = new HeaderFooterPageEvent();
		writer.setPageEvent(event);


		document.open();

		int enqId;
		//---------------------------------------------------------------------------------------------
		for(int j=0;j<indentIds.length;j++){

			 enqId=Integer.parseInt(indentIds[j]);

		doCreateIndentPdf(request,bundle,userInfo,screenName,document,enqId);

		document.newPage();
		}
		//---------------------------------------------------------------------------------------------
		document.close();
		byte[] pdfBytes = pdfReport.toByteArray();
		event=null;
		pdfReport=null;
		document=null;
		subdocument=null;

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\""+screenName+bundle.getString("ExportToPdf.Transaction")+ new java.util.Date()+".pdf" + "\"");
		response.setContentLength(pdfBytes.length);
		response.getOutputStream().write(pdfBytes);
		response.getOutputStream().flush(); 
	}
	catch(Exception e){
		e.printStackTrace();
	}
}




public void doCreateIndentPdf(HttpServletRequest request,ResourceBundle bundle,TPCSUser userInfo,String screenName,Document document,int indentId)
{
	try
	{


		Indent ind=indMan.IndentPrintHeader(2,indentId,userInfo);
		IndentHeader headerInfo=ind.getHeader();

		String materialAttachPath=new UserInfo().getMaterialAttachPath(request);
		List<IndentDetail> printdetList=indMan.getIndentDetailsForPrint(indentId,userInfo,materialAttachPath);

		dynamicFieldsListINDHeader = dynamicFieldManager.getDynamicFields(2, 3,userInfo);
		dynamicFieldsListINDDetail = dynamicFieldManager.getDynamicFields(2, 4, userInfo);


int pageDataHeight=Validator.convertToInteger(bundle.getString("ExportToPdf.PageDataHeight"));  
String fontName=bundle.getString("ExportToPdf.FontName");
short compNameFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.CompanyNameFontSize"));
short addFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.AddressFontSize"));
short titleFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.TitleFontSize"));
short headTableLblFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.HeadTableLblFontSize"));
short headTableDataFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.HeadTableDataFontSize"));
short headTableInfoFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.HeadTableInfoFontSize"));
short headerFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.HeaderFontSize"));
short dataFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.DataFontSize"));
short tableWidthPer=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.TableWidthPer"));

FontFactory.registerDirectories();
BaseColor baseColor=new BaseColor(0,0,0);
Font compNameFont= FontFactory.getFont(fontName,compNameFontSize,Font.BOLD, baseColor);
Font addFont= FontFactory.getFont(fontName,addFontSize,Font.NORMAL, baseColor);
Font titleFont= FontFactory.getFont(fontName,titleFontSize,Font.BOLD, baseColor);

Font headTableLblFont= FontFactory.getFont(fontName,headTableLblFontSize,Font.BOLD, baseColor);
Font headTableDataFont= FontFactory.getFont(fontName,headTableDataFontSize,Font.NORMAL, baseColor);
Font headTableInfoFont= FontFactory.getFont(fontName,headTableInfoFontSize,Font.NORMAL, baseColor);

Font detTableHeaderFont= FontFactory.getFont(fontName,headerFontSize,Font.BOLD, baseColor);
Font detTableDataFont= FontFactory.getFont(fontName,dataFontSize,Font.NORMAL, baseColor);




PdfPTable companyTable=new PdfPTable(1);
//			 companyTable.setSpacingBefore(5f);
//			 companyTable.setSpacingAfter(5f);
companyTable.setWidthPercentage(tableWidthPer); 
companyTable.getDefaultCell().setUseAscender(true);
companyTable.getDefaultCell().setUseDescender(true);

PdfPCell cell = new PdfPCell(new Phrase(userInfo.getCompanyName(), compNameFont));
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
cell.setColspan(1);
cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
cell.setBorder(Rectangle.NO_BORDER);
companyTable.addCell(cell);
cell=null;

String multipleBranch="";
String companyAddress="";

multipleBranch=userInfo.getLocatonConfigMap()!=null?userInfo.getLocatonConfigMap().get("MultiBranch"):"";

if(multipleBranch.equalsIgnoreCase("Yes"))
{
	companyAddress=headerInfo.getBranchAddress();
}
else{
	 companyAddress=!userInfo.getCompanyAddress().isEmpty() && userInfo.getCompanyAddress()!=null ?userInfo.getCompanyAddress():""; 
}


if(!companyAddress.isEmpty() ){
	cell = new PdfPCell(new Phrase(companyAddress, addFont));
	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	cell.setColspan(1);
	cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
	cell.setBorder(Rectangle.NO_BORDER);
	companyTable.addCell(cell);
	cell=null;
}


String webLogo=new UserInfo().getSchemeSever(request)+userInfo.getLogoPath();

File logoFile=new File(userInfo.getLogoFilePath());

Image img=null;

if(logoFile.isFile() && !userInfo.getLogoPath().isEmpty())
{

	img = Image.getInstance(webLogo);
	img.setAlignment(Image.RIGHT);
	webLogo=null;
}


PdfPTable detailTable=new PdfPTable(6);
detailTable.setHeaderRows(8); //To repeat table header in every page
detailTable.setWidthPercentage(tableWidthPer);
detailTable.getDefaultCell().setUseAscender(true);
detailTable.getDefaultCell().setUseDescender(true);

PdfCell pdfCell=new PdfCell();
pdfCell.setTable(detailTable);
pdfCell.setBorder1(Rectangle.LEFT);
pdfCell.setBorder2(Rectangle.RIGHT);
pdfCell.setBorder3(Rectangle.BOTTOM);
pdfCell.setBorderWidth(1f);
pdfCell.setCellHeight(17);
pdfCell.setFont(detTableDataFont);

float[] columnWidths = new float[] {5f, 35f, 5f, 10f,10f,10f};
detailTable.setWidths(columnWidths);
cell = new PdfPCell(); 
cell.setHorizontalAlignment(Element.ALIGN_CENTER);

if(logoFile.isFile() && img!=null){
	cell.setBorder(Rectangle.TOP | Rectangle.LEFT| Rectangle.BOTTOM);
	 cell.setBorderWidth(1f); 
	cell.setColspan(4);	
}
else{

	cell.setColspan(6);
}

cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
cell.addElement(companyTable);
cell.setBorderWidth(1f); 
detailTable.addCell(cell);
cell=null;


if(logoFile.isFile() && img!=null){
	cell = new PdfPCell(); 
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
	cell.setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM);
	 cell.setBorderWidth(1f); 
	cell.addElement(img);
	cell.setColspan(2);
	detailTable.addCell(cell);
	cell=null;
}


cell = new PdfPCell(new Phrase(screenName, titleFont)); 
cell.setHorizontalAlignment(Element.ALIGN_CENTER);
cell.setColspan(6);
cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
cell.setBorderWidth(1f); 
detailTable.addCell(cell);
cell=null;



//Filter Area

ExportToPdfTool.insertBorderCell(detailTable,"To", Element.ALIGN_LEFT, 1, headTableLblFont,Rectangle.LEFT,0,0);
ExportToPdfTool.insertNoBorderCell(detailTable,": "+headerInfo.getPartyName(), Element.ALIGN_LEFT, 2, headTableDataFont);

ExportToPdfTool.insertBorderCell(detailTable,"Enquiry No.", Element.ALIGN_LEFT, 1, headTableLblFont,Rectangle.LEFT,0,0);
ExportToPdfTool.insertBorderCell(detailTable,": "+headerInfo.getPrefixEnqNo(), Element.ALIGN_LEFT, 2, headTableDataFont,Rectangle.RIGHT,0,0);

ExportToPdfTool.insertCellWithRowSpan(detailTable,"", Element.ALIGN_LEFT, 1,4, headTableDataFont,Rectangle.LEFT,0);
ExportToPdfTool.insertCellWithRowSpan(detailTable,headerInfo.getBillToAddress(), Element.ALIGN_LEFT, 2,4, headTableDataFont,0,0);

ExportToPdfTool.insertBorderCell(detailTable,"Enquiry Date", Element.ALIGN_LEFT, 1, headTableLblFont,Rectangle.LEFT,0,0);
ExportToPdfTool.insertBorderCell(detailTable,": "+headerInfo.getEnqDate(), Element.ALIGN_LEFT, 2, headTableDataFont,Rectangle.RIGHT,0,0);


if(!headerInfo.getShipToAddress().equalsIgnoreCase(headerInfo.getBillToAddress())){

	ExportToPdfTool.insertBorderCell(detailTable,"Deliver To :", Element.ALIGN_LEFT, 3, headTableLblFont,Rectangle.LEFT,Rectangle.TOP,Rectangle.RIGHT);

	ExportToPdfTool.insertCellWithRowSpan(detailTable,headerInfo.getShipToAddress(), Element.ALIGN_LEFT, 3,2, headTableDataFont,Rectangle.LEFT,Rectangle.RIGHT);
}
else{
	ExportToPdfTool.insertBorderCell(detailTable,"", Element.ALIGN_LEFT, 3, headTableLblFont,Rectangle.LEFT,0,Rectangle.RIGHT);
	ExportToPdfTool.insertCellWithRowSpan(detailTable,"", Element.ALIGN_LEFT, 3,2, headTableDataFont,Rectangle.LEFT,Rectangle.RIGHT);
}
if(headerInfo.gethRemarks()!=null && !headerInfo.gethRemarks().isEmpty()){
	cell = new PdfPCell(new Phrase(headerInfo.gethRemarks() , headTableDataFont)); 
	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	cell.setColspan(6);
	cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
	cell.setBorderWidth(1f); 
	detailTable.addCell(cell);
	cell=null;
}




//data area

String descLabel="";
String qtyLabel="";
String uomLabel="";
String priceLabel="";
for(DynamicFields detfields : dynamicFieldsListINDDetail){

	if(detfields.isFixed()){     
		if("material_description".equalsIgnoreCase(detfields.getPageFieldName())){
			descLabel=detfields.getDisplayName();
		} 
		if("sku".equalsIgnoreCase(detfields.getPageFieldName())){
			uomLabel=detfields.getDisplayName();
		} 
		if("quantity".equalsIgnoreCase(detfields.getPageFieldName())){
			qtyLabel=detfields.getDisplayName();
		} 
		if("price_fcy".equalsIgnoreCase(detfields.getPageFieldName())){
			priceLabel=detfields.getDisplayName();
		} 

	}
}
String tH1[]={bundle.getString("ExportToPdf.SlNo")
		,descLabel
		,uomLabel
		,qtyLabel
		,priceLabel
		,bundle.getString("ExportToPdf.Amount")
};





List<String> totColor=new ArrayList<String>();

 totColor.add("#"+bundle.getString("ExportToExcel.Total"));
 totColor.add("#"+bundle.getString("ExportToExcel.SubTotal"));
 totColor.add("#"+bundle.getString("ExportToExcel.TotalAmt"));

	for (int i=0;i<tH1.length;i++) {
		ExportToPdfTool.insertCell(detailTable, tH1[i], Element.ALIGN_CENTER, 1, detTableHeaderFont);	

	}
int count=1;
double totamount=0;


double valAftDis=0;
double valAftTax=0;
BaseColor objColor=null;
for(IndentDetail list:printdetList){


	if(!list.getMaterialDesc().isEmpty()){

		pdfCell.setCellVal(String.valueOf(count));
		pdfCell.setCellHeight(17);
		pdfCell.sethAlign(Element.ALIGN_CENTER);
		pdfCell.setRowSpan(2);
		pdfCell.setColSpan(1);
		pdfCell.setvAlign(Element.ALIGN_BOTTOM);
		ExportToPdfTool.insertCell(pdfCell);


				pdfCell.setCellVal(list.getMatName()+" :");
				pdfCell.setCellHeight(17);
				pdfCell.sethAlign(Element.ALIGN_LEFT);
				pdfCell.setRowSpan(1);
				pdfCell.setColSpan(5);
				pdfCell.setvAlign(Element.ALIGN_BOTTOM);
				ExportToPdfTool.insertCell(pdfCell);

				ExportToPdfTool.insertBorderCell(detailTable,list.getMaterialDesc().isEmpty()?list.getMatName():list.getMaterialDesc(), Element.ALIGN_LEFT, 1, detTableDataFont,Rectangle.LEFT,Rectangle.RIGHT,Rectangle.BOTTOM);
				ExportToPdfTool.insertBorderCell(detailTable,list.getUom(), Element.ALIGN_CENTER, 1, detTableDataFont,Rectangle.LEFT,Rectangle.RIGHT,Rectangle.BOTTOM);
				ExportToPdfTool.insertBorderCell(detailTable,list.getQuantity(), Element.ALIGN_RIGHT, 1, detTableDataFont,Rectangle.LEFT,Rectangle.RIGHT,Rectangle.BOTTOM);
				ExportToPdfTool.insertBorderCell(detailTable,list.getPriceFcy(), Element.ALIGN_RIGHT, 1, detTableDataFont,Rectangle.LEFT,Rectangle.RIGHT,Rectangle.BOTTOM);
				ExportToPdfTool.insertBorderCell(detailTable,list.getValBefDis(), Element.ALIGN_RIGHT, 1, detTableDataFont,Rectangle.LEFT,Rectangle.RIGHT,Rectangle.BOTTOM);


	}
	else{

		pdfCell.setCellVal(String.valueOf(count));
		pdfCell.setCellHeight(17);
		pdfCell.sethAlign(Element.ALIGN_CENTER);
		pdfCell.setRowSpan(1);
		pdfCell.setColSpan(1);
		pdfCell.setvAlign(Element.ALIGN_BOTTOM);
		ExportToPdfTool.insertCell(pdfCell);

					ExportToPdfTool.insertBorderCell(detailTable,list.getMatName(), Element.ALIGN_LEFT, 1, detTableDataFont,Rectangle.LEFT,Rectangle.RIGHT,Rectangle.BOTTOM);
					ExportToPdfTool.insertBorderCell(detailTable,list.getUom(), Element.ALIGN_CENTER, 1, detTableDataFont,Rectangle.LEFT,Rectangle.RIGHT,Rectangle.BOTTOM);
					ExportToPdfTool.insertBorderCell(detailTable,list.getQuantity(), Element.ALIGN_RIGHT, 1, detTableDataFont,Rectangle.LEFT,Rectangle.RIGHT,Rectangle.BOTTOM);
					ExportToPdfTool.insertBorderCell(detailTable,list.getPriceFcy(), Element.ALIGN_RIGHT, 1, detTableDataFont,Rectangle.LEFT,Rectangle.RIGHT,Rectangle.BOTTOM);
		ExportToPdfTool.insertBorderCell(detailTable,list.getValBefDis(), Element.ALIGN_RIGHT, 1, detTableDataFont,Rectangle.LEFT,Rectangle.RIGHT,Rectangle.BOTTOM);

	}


		totamount=totamount+Validator.convertToDouble(list.getValBefDis());

	count=count+1;

	 valAftDis=valAftDis+Validator.convertToDouble(list.getValAfterDis());
	 valAftTax=valAftTax+Validator.convertToDouble(list.getValueFcy());

}

objColor=ExportToPdfTool.hex2Rgb(totColor.get(2));

document.add(detailTable);

PdfPTable footTable=new PdfPTable(6);
footTable.setWidthPercentage(100);
footTable.setWidths(columnWidths);

if(detailTable.getTotalHeight()<pageDataHeight){
	cell = new PdfPCell(new Paragraph("", detTableDataFont));
	cell.setMinimumHeight(pageDataHeight-detailTable.getTotalHeight());
	cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
	cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT); 
	cell.setBorderWidth(1f); 
	footTable.addCell(cell);
	cell=null;

	cell = new PdfPCell(new Paragraph("", detTableDataFont));
	cell.setMinimumHeight(pageDataHeight-detailTable.getTotalHeight());
	cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
	cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT); 
	cell.setBorderWidth(1f); 
	footTable.addCell(cell);
	cell=null;

	cell = new PdfPCell(new Paragraph("", detTableDataFont));
	cell.setMinimumHeight(pageDataHeight-detailTable.getTotalHeight());
	cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
	cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT); 
	cell.setBorderWidth(1f); 
	footTable.addCell(cell);
	cell=null;

	cell = new PdfPCell(new Paragraph("", detTableDataFont));
	cell.setMinimumHeight(pageDataHeight-detailTable.getTotalHeight());
	cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
	cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT); 
	cell.setBorderWidth(1f); 
	footTable.addCell(cell);
	cell=null;

	cell = new PdfPCell(new Paragraph("", detTableDataFont));
	cell.setMinimumHeight(pageDataHeight-detailTable.getTotalHeight());
	cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
	cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT); 
	cell.setBorderWidth(1f); 
	footTable.addCell(cell);
	cell=null;

	cell = new PdfPCell(new Paragraph("", detTableDataFont));
	cell.setMinimumHeight(pageDataHeight-detailTable.getTotalHeight());
	cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
	cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT ); 
	cell.setBorderWidth(1f); 
	footTable.addCell(cell);
	cell=null;
}
 ExportToPdfTool.insertCell(footTable,"", Element.ALIGN_RIGHT, 1, detTableDataFont);
 ExportToPdfTool.insertCell(footTable, bundle.getString("ExportToPdf.TotalAmount"), Element.ALIGN_RIGHT, 1, detTableDataFont);
 ExportToPdfTool.insertCell(footTable, "", Element.ALIGN_RIGHT, 1, detTableDataFont);
 ExportToPdfTool.insertCell(footTable, "", Element.ALIGN_RIGHT, 1, detTableDataFont);
 ExportToPdfTool.insertCell(footTable, "", Element.ALIGN_RIGHT, 1, detTableDataFont);
 ExportToPdfTool.insertCell(footTable,ExportToPdfTool.valDf.format(totamount), Element.ALIGN_RIGHT, 1, detTableDataFont);


String headerRemarks="";
String paymentTerms="";
int creditDays=0;

 if(totamount>0)
 {
	 String grandTotInWords=new ExportToPdfTool().getNumberInWords(headerInfo.getCurrencyName(),bundle,totamount);

	 cell = new PdfPCell(new Paragraph("( "+headerInfo.getCurrencyName()+" "+grandTotInWords+")", detTableDataFont));
	 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 cell.setColspan(6);
	 cell.setMinimumHeight(22);
	 cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
	 cell.setBorderWidth(1f); 
	 footTable.addCell(cell);
	 cell=null;
	 grandTotInWords=null;
 }


	//-----------------------

	if((paymentTerms!=null && !paymentTerms.isEmpty()) || creditDays>0 ) 
	{
		ExportToPdfTool.insertCell(footTable,"Payment Terms :"+paymentTerms, Element.ALIGN_LEFT, 4, detTableDataFont);
		ExportToPdfTool.insertCell(footTable, "Credit Days :", Element.ALIGN_LEFT, 1, detTableDataFont);
		ExportToPdfTool.insertCell(footTable, String.valueOf(creditDays), Element.ALIGN_RIGHT, 1, detTableDataFont);
	}



	//Footer Autorized Area

	cell = new PdfPCell(new Paragraph("", detTableDataFont));
	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	cell.setColspan(3);
	cell.setMinimumHeight(22);
	cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
	cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
	cell.setBorderWidth(1f); 
	footTable.addCell(cell);
	cell=null;

	cell = new PdfPCell(new Paragraph("For "+userInfo.getCompanyName(), detTableDataFont));
	cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	cell.setColspan(3);
	cell.setMinimumHeight(22);
	cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
	cell.setBorder(Rectangle.RIGHT | Rectangle.TOP);
	cell.setBorderWidth(1f); 
	footTable.addCell(cell);
	cell=null;

	//for space

	cell = new PdfPCell(new Paragraph("", detTableDataFont));
	cell.setColspan(6);
	cell.setMinimumHeight(22);
	cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT ); 
	cell.setBorderWidth(1f); 
	footTable.addCell(cell);
	cell=null;

	cell = new PdfPCell(new Paragraph(bundle.getString("Common.PreparedBy"), detTableDataFont));
	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	cell.setColspan(3);
	cell.setMinimumHeight(22);
	cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
	cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM );
	cell.setBorderWidth(1f); 
	footTable.addCell(cell);
	cell=null;


	cell = new PdfPCell(new Paragraph(bundle.getString("Common.AutorisedSignatory"), detTableDataFont));
	cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	cell.setColspan(3);
	cell.setMinimumHeight(22);
	cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
	cell.setBorder( Rectangle.RIGHT| Rectangle.BOTTOM );
	cell.setBorderWidth(1f); 
	footTable.addCell(cell);
	cell=null;

document.add(footTable);


baseColor=null;
companyTable=null;
logoFile=null;
detailTable=null;
pdfCell=null;
totColor=null;
footTable=null;
headerInfo=null;
ind=null;
materialAttachPath=null;
printdetList=null;
columnWidths=null;
//dynamicFieldManager=null;
dynamicFieldsListINDHeader=null;
dynamicFieldsListINDDetail=null;
}
catch(Exception e)
{
e.printStackTrace();
}
}



	 */












	/*

public void doSendBulkIndentPdf(HttpServletRequest request,HttpServletResponse response) 
{
	try {
		HttpSession session=request.getSession();
		TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
		int iUserId=Integer.parseInt((String)request.getSession().getAttribute("login_user_id"));
		String indentIds[]=request.getParameterValues("indent_id");
		int type=3;
		int enqId;
		for(int j=0;j<indentIds.length;j++){

			enqId=Integer.parseInt(indentIds[j]);

			List<String> sqDetails=seMan.getEmailSEDetails(enqId,userInfo);
			List<String> toIds=seMan.getSEFollowers(enqId,userInfo);
			SalesEnquiryHeader objSE=seMan.getCustomerEmailId(enqId,userInfo);

			if(!objSE.getEmailSentTo().isEmpty() && !toIds.contains(objSE.getEmailSentTo())){
				toIds.add(objSE.getEmailSentTo());
			}

			seMan.updateMailTag(enqId,userInfo);
			//System.out.println("toIds :"+toIds);
			String loginName=salesQuoteMan.getLoggedInUser(iUserId,userInfo);
			String strContactImage=salesQuoteMan.getContactImage(iUserId,userInfo);


			MailInfo mailInfo=generateSalesEnquiryPdf(request, enqId);
			List<MailInfo> liMailInfo=new ArrayList<MailInfo>();
        	liMailInfo.add(mailInfo);
        	mailInfo.setLiMailInfo(liMailInfo);

			mailInfo.setToIdList(toIds);
			mailInfo.setUserId(iUserId);
			mailInfo.setLoginName(loginName);
			mailInfo.setSessionPath((String)session.getAttribute("path"));
			mailInfo.setContactImageUrl(strContactImage);
			mailInfo.setSchemeSeverContext(new UserInfo().getSchemeSeverContext(request));
			mailInfo.setClientId(userInfo.getClientId());
			mailInfo.setType(type);
			mailInfo.setOurWebLogoPath(new UserInfo().getOurWebLogoPath(request));

			seMan.sendNewSEEmailAlert(sqDetails,mailInfo);

			liMailInfo=null;
			mailInfo=null;
			sqDetails=null;
			toIds=null;
			loginName=null;
			strContactImage=null;
		}

		request.setAttribute("success_message","Send Successfully");

		doDisplayIndent(request,response);


	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}

}
	 */






	
	
	
	///
	
	
	
	private IndentManager  indMan= new IndentManager();
	private DynamicFieldManager  dynamicFieldManager = new DynamicFieldManager();
	private CommonUtilManager  comUtilMan= new CommonUtilManager();
	
	private List<DynamicFieldEvents> dynamicFormEvents = null;
	private List<DynamicFieldEvents> dynamicHeaderFieldEvents = null;
	private List<DynamicFieldEvents> dynamicDetailFieldEvents = null;
	private List<DynamicFieldEvents> dynamicHeaderpickListOptions = null;
	private List<DynamicFieldEvents> dynamicDetailpickListOptions = null;
	private DynamicFormStructure dynamicFormStructureINDHeader = null;
	private DynamicFormStructure  dynamicFormStructureINDDetail=null;
	private List<DynamicFields> dynamicFieldsListINDHeader = null;
	private List<DynamicFields> dynamicFieldsListINDDetail=null;
	
	private UserRights rights = null;
	private UserRightsManager objRight = new UserRightsManager();

	private int screenId=7;
	private int headerTableId=8;
	private int detailTableId=9;
	
	
	
	
	
	
	

}
