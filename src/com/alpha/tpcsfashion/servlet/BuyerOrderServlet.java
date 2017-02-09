
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringEscapeUtils;

import com.alpha.tpcsfashion.beans.BuyerOrder;
import com.alpha.tpcsfashion.beans.BuyerOrderDetail;
import com.alpha.tpcsfashion.beans.BuyerOrderHeader;
import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.CommonUtil;
import com.alpha.tpcsfashion.beans.DynamicFieldEvents;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.DynamicFormStructure;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.Indent;
import com.alpha.tpcsfashion.beans.IndentHeader;
import com.alpha.tpcsfashion.beans.Size;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.gridmaker.BuyerOrderGridMaker;
import com.alpha.tpcsfashion.model.BuyerOrderManager;
import com.alpha.tpcsfashion.model.ColumnPreferenceManager;
import com.alpha.tpcsfashion.model.CommonUtilManager;
import com.alpha.tpcsfashion.model.DynamicFieldManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.ExportToPdfTool;
import com.alpha.tpcsfashion.util.ResourceBundleProvider;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.UserInfo;
import com.alpha.tpcsfashion.util.Validator;

public class BuyerOrderServlet {

	@SuppressWarnings("unused")
	public void doDisplayBuyerOrder(HttpServletRequest request,HttpServletResponse response){
		try {
			HttpSession session=request.getSession();
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
//			session.setAttribute("seletedScreenId", 3);
			
			request.setAttribute("invoke_servlet", "BuyerOrderServlet");
			request.setAttribute("invoke_method", "doDisplayBuyerOrder");		
			
			comUtil=null;
			strSearchQuery=null;
			ui=null;
			reqType=null;
			
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_BUYER_ORDER, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void organizColumns(HttpServletRequest request,String strSearchQuery){
						
		try{
			TPCSUser userInfo=new UserInfo().getUserInfo(request);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);

			request.setAttribute("subdocument_id", SubdocumentId.BUYER_ORDER);
			request.setAttribute("screen_id", screenId);
			
			ExportToPdfTool.setRoundOff(userInfo);
			ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
			ColumnPreference DataList=cpm.getVisibleColumnNames(SubdocumentId.BUYER_ORDER, userInfo);
			Map<String,String> visColMap=cpm.getVisibleColumnNamesMap(SubdocumentId.BUYER_ORDER,userInfo.getUserId(), userInfo);

			List<String> listSelectedColumns=DataList.getVisibleColumns(); 
			List<String> orderBy=DataList.getOrderBy(); 

			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			int pageCount= boMan.getTotalPages(userInfo,pc, strSearchQuery);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);
			
			doGetUserRights(request);
			rights=(UserRights) request.getSession().getAttribute("bo_rights");

			BuyerOrder objBO=new BuyerOrder();
			objBO.setScreenId(screenId);
			objBO.setHeaderTableId(headerTableId);
			objBO.setDetailTableId(detailTableId);
			objBO.setSubdocumentId(SubdocumentId.BUYER_ORDER);
			objBO.setAttachPath(new UserInfo().getClientPath(request));
			objBO.setSearchCriteria(strSearchQuery);
			objBO.setOrderByList(orderBy);
			objBO =boMan.getAllBuyerOrderOnColumnMapping(pc,userInfo,objBO);
			objBO.setVisColMap(visColMap);
			objBO.setListSelectedColumns(listSelectedColumns);
			String grid = new BuyerOrderGridMaker().formBuyerOrderGrid(objBO,bundle,rights,pc);
			request.setAttribute("bo_grid", grid);

			
			DataList=null;
			visColMap=null;
			grid=null;
			userInfo=null;
			cpm=null;
			listSelectedColumns=null;
			orderBy=null;
			pc=null;
			bundle=null;
			objBO=null;
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void doUpdateBuyerOrderColumnPreferences(HttpServletRequest request, HttpServletResponse response){
 
			HttpSession session=request.getSession();  
			TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
			String strTotalColumns = request.getParameter("selected_columns_data");
			ColumnPreferenceManager cpm=new ColumnPreferenceManager();    
			boolean bFlag = cpm.updateColumnPreferences(strTotalColumns,SubdocumentId.BUYER_ORDER,userInfo);

			doDisplayBuyerOrder(request, response);
			session=null;
			userInfo=null;
			strTotalColumns=null;
			cpm=null;
			
		}

//	public void doSearchBuyerOrder(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
//		try{  
//			String strSearchQuery=getDefaultCriteria(request);
//			
//			String reqType=request.getParameter("request_type");
//			
//			if("Search".equalsIgnoreCase(reqType)){	         
//				strSearchQuery = getSearchCriteria(request);
//				getAndSetAttributes(request);
////				request.getSession().setAttribute("search_query",strSearchQuery);
//			}
//			
//			organizColumns(request,strSearchQuery);
//			getAndSetAttributes(request);
//			request.setAttribute("request_type", reqType);
////			TPCSUser ui=new UserInfo().getUserInfo(request);
////			request.setAttribute("is_multi_branch_app", ui.getLocatonConfigMap()!=null?ui.getLocatonConfigMap().get("MultiBranch"):"");
//			
////			ui=null;
//			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_BUYER_ORDER,request,response);
//		}catch(Exception e){
//			e.printStackTrace();
//			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
//		}
//	}
	public void doGetUserRights(HttpServletRequest request){
		TPCSUser userInfo=new UserInfo().getUserInfo(request);
		HttpSession session=request.getSession();
		
		if(request.getSession().getAttribute("bo_rights")!=null){
			rights =(UserRights)request.getSession().getAttribute("bo_rights");
		}
		else{
			rights =objRight.getUserRights(SubdocumentId.BUYER_ORDER, userInfo);
			session.setAttribute("bo_rights",rights);		
		}
		userInfo=null;
		session=null;
		
	}
	public void getAndSetAttributes(HttpServletRequest request){
		request.setAttribute("page_size", PageConfig.iPageSize);
		request.setAttribute("bo_buyer_po_no",request.getParameter("bo_buyer_po_no"));
		request.setAttribute("bo_customer_plan_no",request.getParameter("bo_customer_plan_no"));
		request.setAttribute("bo_buyer_id",request.getParameter("bo_buyer_id"));
		request.setAttribute("bo_buyer_name",request.getParameter("bo_buyer_name"));
		request.setAttribute("bo_season_id",request.getParameter("bo_season_id"));
		request.setAttribute("bo_season_name",request.getParameter("bo_season_name"));
		request.setAttribute("bo_agent_id",request.getParameter("bo_agent_id"));
		request.setAttribute("bo_agent_name",request.getParameter("bo_agent_name"));
		request.setAttribute("bo_from_date",request.getParameter("bo_from_date"));
		request.setAttribute("bo_to_date",request.getParameter("bo_to_date"));
		request.setAttribute("bo_special_instruction",request.getParameter("bo_special_instruction"));
		request.setAttribute("bo_packing_labeling",request.getParameter("bo_packing_labeling"));
	}
	private String getDefaultCriteria(HttpServletRequest request){
		StringBuffer  strQuery=new StringBuffer();
//		   String quote_type=request.getParameter("quote_type");
//		if(quote_type== null || quote_type.equalsIgnoreCase("-1")){
//			strQuery.append(" and isnull(sq.cancel_tag,0)<>1 ");
//		}
		
		return strQuery.toString();
	}
	
	public String getSearchCriteria(HttpServletRequest request){

		StringBuffer  strQuery=new StringBuffer();
		String buyerPONo= request.getParameter("bo_buyer_po_no");
//		int salesVal=Validator.convertToInteger(request.getParameter("bo_val"));
		String custPlanNo=request.getParameter("bo_customer_plan_no");
		int partyId=Validator.convertToInteger(request.getParameter("bo_buyer_id"));
		int seasonId= Validator.convertToInteger(request.getParameter("bo_season_id"));
		int agentId=Validator.convertToInteger(request.getParameter("bo_agent_id"));
		String boFromDate= request.getParameter("bo_from_date");
		String boToDate= request.getParameter("bo_to_date");
		String specialInst= request.getParameter("bo_special_instruction");
		String packingLabeling=request.getParameter("bo_packing_labeling");

		//For DynamicFields
//	    int dynamicfieldId_1 = Validator.convertToInteger(request.getParameter("dynamic_field_1"));
//	    int dynamicfieldId_2 = Validator.convertToInteger(request.getParameter("dynamic_field_2"));
//	    int dynamicfieldId_3 = Validator.convertToInteger(request.getParameter("dynamic_field_3"));
//	    String dynamicfieldValue_1 =request.getParameter("dynamic_field_1_val");
//	    String dynamicfieldValue_2 =request.getParameter("dynamic_field_2_val");
//	    String dynamicfieldValue_3 =request.getParameter("dynamic_field_3_val");
//	    String dynamicdbName_1=null;
//	    String dynamicdbName_2=null;
//	    String dynamicdbName_3=null;
//	    int dynamicTableId_1=0;
//	    int dynamicTableId_2=0;
//	    int dynamicTableId_3=0;
//	    
//	    String quoteType=request.getParameter("bo_type");
	 
//	    if(quoteType== null || quoteType.equalsIgnoreCase("-1")){
//			strQuery.append(" and isnull(sq.cancel_tag,0)<>1 ");
//		}
//	    else if(quoteType!=null && quoteType.equalsIgnoreCase("Awaiting")){
//			strQuery.append(" and isnull(sq.authorized_tag,0)=0 ");
//		}
//	    else if(quoteType!=null && quoteType.equalsIgnoreCase("Canceled")){
//			strQuery.append(" and isnull(sq.cancel_tag,0)=1 ");
//		}
//	    else if(quoteType!=null && quoteType.equalsIgnoreCase("Closed")){
//			strQuery.append(" and isnull(sq.closed_tag,0)=2 ");
//		}
//	    else if(quoteType!=null && quoteType.equalsIgnoreCase("Authorized")){
//			strQuery.append(" and isnull(sq.authorized_tag,0)=1 ");
//		}
//	    else if(quoteType!=null && quoteType.equalsIgnoreCase("Recd")){
//			strQuery.append(" and isnull(sq.closed_tag,0)=1 ");
//		}
//	    else if(quoteType!=null && quoteType.equalsIgnoreCase("Sent")){
//			strQuery.append(" and isnull(sq.mail_tag,0)=1 ");
//		}
//	    else if(quoteType!=null && quoteType.equalsIgnoreCase("NotSent")){
//			strQuery.append(" and isnull(sq.mail_tag,0)=0 ");
//		}
//	    else if(quoteType!=null&& quoteType.equalsIgnoreCase("OrderPending")){
//			strQuery.append(" and isnull(sq.closed_tag,0)=0 ");
//		}
//		
//	    if(dynamicfieldId_1!=-1)
//	    {
//	    dynamicdbName_1=salesQuoteMan.getDynamicDbName(dynamicfieldId_1,userInfo);
//	    dynamicTableId_1=salesQuoteMan.getDynamicTableId(dynamicfieldId_1,userInfo);
//	    if(dynamicTableId_1==6)
//	    {
//	    	if(dynamicfieldValue_1!=null && dynamicfieldValue_1.trim().length()>0){
//	    		strQuery.append(" AND sqdf."+dynamicdbName_1+" LIKE '%"+dynamicfieldValue_1.replace("'","''")+"%'  ");
//	    	    }
//	    }
//	    else if(dynamicTableId_1==8)
//	    {
//	    	if(dynamicfieldValue_1!=null && dynamicfieldValue_1.trim().length()>0){
//	    		strQuery.append(" AND sqddf."+dynamicdbName_1+" LIKE '%"+dynamicfieldValue_1.replace("'","''")+"%'  ");
//	    	    }
//	    }
//	    }
//	    
//	    if(dynamicfieldId_2!=-1)
//	    {
//	    dynamicdbName_2=salesQuoteMan.getDynamicDbName(dynamicfieldId_2,userInfo);
//	    dynamicTableId_2=salesQuoteMan.getDynamicTableId(dynamicfieldId_2,userInfo);
//	    if(dynamicTableId_2==6)
//	    {
//	    	if(dynamicfieldValue_2!=null && dynamicfieldValue_2.trim().length()>0){
//	    		strQuery.append(" AND sqdf."+dynamicdbName_2+" LIKE '%"+dynamicfieldValue_2.replace("'","''")+"%'  ");
//	    	    }
//	    }
//	    else if(dynamicTableId_2==8)
//	    {
//	    	if(dynamicfieldValue_2!=null && dynamicfieldValue_2.trim().length()>0){
//	    		strQuery.append(" AND sqddf."+dynamicdbName_2+" LIKE '%"+dynamicfieldValue_2.replace("'","''")+"%'  ");
//	    	    }
//	    }
//	    }
//	    
//	    if(dynamicfieldId_3!=-1)
//	    {
//	    dynamicdbName_3=salesQuoteMan.getDynamicDbName(dynamicfieldId_3,userInfo);
//	    dynamicTableId_3=salesQuoteMan.getDynamicTableId(dynamicfieldId_3,userInfo);
//	    if(dynamicTableId_3==6)
//	    {
//	    	if(dynamicfieldValue_3!=null && dynamicfieldValue_3.trim().length()>0){
//	    		strQuery.append(" AND sqdf."+dynamicdbName_3+" LIKE '%"+dynamicfieldValue_3.replace("'","''")+"%'  ");
//	    	    }
//	    }
//	    else if(dynamicTableId_3==8)
//	    {
//	    	if(dynamicfieldValue_3!=null && dynamicfieldValue_3.trim().length()>0){
//	    		strQuery.append( " AND sqddf."+dynamicdbName_3+" LIKE '%"+dynamicfieldValue_3.replace("'","''")+"%'  ");
//	    	    }
//	    }
//	    }
	    
//	     dynamicdbName_1=null;
//	     dynamicdbName_2=null;
//	     dynamicdbName_3=null;
	    if(boFromDate!=null && boFromDate.length()>0){
			strQuery.append(" and so.so_date>=convert(datetime,'"+boFromDate+"',105) "); 
		}
		if(boToDate!=null && boToDate.length()>0){
			strQuery.append(" and so.so_date<=convert(datetime,'"+boToDate+"',105)");
		}
		if(buyerPONo!=null && !buyerPONo.isEmpty()){
			strQuery.append("and so.buyer_po='"+buyerPONo.replace("'","''")+"'");
		}
		if(custPlanNo!=null && !custPlanNo.isEmpty()){
			strQuery.append("and so.customer_plan_no='"+custPlanNo.replace("'","''")+"'");
		}
		if(partyId!=0){
			strQuery.append("and p.party_id="+partyId);
		}
		if(seasonId!=0){
			strQuery.append("and so.season_id="+seasonId);
		}
		if(agentId!=0){
			strQuery.append("and so.agent_id="+agentId);
		}
		if(specialInst!=null && !specialInst.isEmpty()){
			strQuery.append(" and so.special_instruction LIKE '%"+specialInst.replace("'","''")+"%'");
		}
		if(packingLabeling!=null && !packingLabeling.isEmpty()){
			strQuery.append(" and so.packing_labeling LIKE '%"+packingLabeling.replace("'","''")+"%'");
		}
		buyerPONo=null;
		custPlanNo=null;
		boFromDate=null;
		boToDate=null;
		specialInst=null;
		packingLabeling=null;
		return strQuery.toString();

	}			  
	public void doAddBuyerOrder(HttpServletRequest request,HttpServletResponse response){
		try {
			
			Map<String,String> fileMap=new LinkedHashMap<String, String>();
			String headerMode = getHeaderMode(fileMap,request);
			BuyerOrder objBO=new BuyerOrder();
			objBO.setHeaderMode(headerMode);
			
			BuyerOrderHeader boHeader=new BuyerOrderHeader();
			
			int boId=Validator.convertToInteger(request.getParameter("bo_id"));
			boHeader.setBoId(boId);
			
			if(objBO.getHeaderMode().equalsIgnoreCase("n")){
				setNewDefaultData(request,objBO);
			}

			objBO.setHeader(boHeader);
			
			doAddEditBuyerOrder(request,response,objBO);
			
			fileMap=null;
			headerMode=null;
			objBO=null;
			boHeader=null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doAddEditBuyerOrder(HttpServletRequest request,HttpServletResponse response, BuyerOrder objBO){
		try {
			
			objBO.setScreenId(screenId);
			objBO.setHeaderTableId(headerTableId);
			objBO.setDetailTableId(detailTableId);
			
			objBO.setClientPath(new UserInfo().getClientPath(request));
			
			request.setAttribute("buyer_order", objBO);
			
			TPCSUser ui=new UserInfo().getUserInfo(request);
			
			ExportToPdfTool.setRoundOff(ui);
			
			dynamicFieldsListBOHeader = dynamicFieldManager.getDynamicFields(screenId, headerTableId,ui);
			request.setAttribute("dynamicFieldsListBOHeader", dynamicFieldsListBOHeader);
			request.getSession().setAttribute("dynamicFieldsListBOHeader", dynamicFieldsListBOHeader);
			
			dynamicFieldsListBODetail = dynamicFieldManager.getDynamicFields(screenId, detailTableId, ui);
			request.setAttribute("dynamicFieldsListBODetail", dynamicFieldsListBODetail);
			request.getSession().setAttribute("dynamicFieldsListBODetail", dynamicFieldsListBODetail);

			dynamicFormStructureBOHeader = dynamicFieldManager.getDynamicFormStructure(screenId, ui);
			request.setAttribute("dynamicFormStructureBOHeader", dynamicFormStructureBOHeader);

			dynamicFormStructureBODetail = dynamicFieldManager.getDynamicFormStructure(screenId, ui);
			request.setAttribute("dynamicFormStructureBODetail", dynamicFormStructureBODetail);

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
			
			
//			System.out.println("objBO.getSaveType() :"+objBO.getSaveType());
//			System.out.println("objBO.getHeaderMode() :"+objBO.getHeaderMode());
			if(objBO.getHeaderMode().equals("a") || objBO.getHeaderMode().equals("e")){

				if(objBO.getHeaderMode().equals("a")){
//					boolean isAmend=boMan.createAmendment(ui,objBO);
				}
				
				
				objBO=boMan.getBuyerOrderInfo(objBO,ui);
//				System.out.println("objBO.getBoDetList().size() "+objBO.getBoDetList().size());
				
				request.setAttribute("header_info", objBO.getHeader());
				request.setAttribute("detList", objBO.getBoDetList());
				request.setAttribute("detMapList", objBO.getBoDetMap());
				
				
//				List<SalesQuotation> attachFiles = salesQuoteMan.getAttachFiles(quoteId,userInfo);
//				String quotationAttachPath=new UserInfo().getQuotationAttachPath(request);
				request.setAttribute("attached_files", objBO.getHeader().getAttachFiles());
				request.setAttribute("boAttachPath", objBO.getClientPath());


//				sqfollowers = salesQuoteMan.getSQFollowers(request.getContextPath(),clientId,quoteId,iUserId,userInfo);
//				request.setAttribute("bo_followers_list", objBO.getHeader().getBoUsers());
//				squsers = salesQuoteMan.getSQDefUsers(request.getContextPath(),clientId,quoteId,iUserId,userInfo);
//				request.setAttribute("bo_user_list", objBO.getHeader().getDefUsers());
				//DynamicFields
				
				dynamicFieldsListBOHeader = objBO.getBoDynList();
				request.setAttribute("dynamicFieldsListBOHeader", dynamicFieldsListBOHeader);

				request.setAttribute("det_id_list",objBO.getDetIdList());
				request.setAttribute("det_ids",objBO.getDetIdList().toString().replace("[","").replace("]", "").replace(" ",""));
				
			}
			else{
				objBO=boMan.getBuyerOrderDefaultData(ui,objBO);
				 
				
				
				String rowIds="0,1,2";
				int rowCount=3;
				objBO.getHeader().setPackingRowCount(rowCount);
				List<String> rowIdList=Arrays.asList(rowIds.split(","));
				
				addPackingScheduleEmptyRow(objBO.getHeader());
				
				
				objBO.getHeader().setPackingRowIds(rowIds);
				rowIds=null;
				objBO.getHeader().setPackingRowCount(rowCount);
				objBO.getHeader().setPackingRowIdList(rowIdList);
				rowIdList=null;
				
				request.setAttribute("header_info",objBO.getHeader());
			}
			
			request.setAttribute("view_mode", request.getParameter("view_mode"));
			
			dynamicFieldsListBOHeader=null;
			dynamicFieldsListBODetail=null;
			dynamicFormStructureBOHeader=null;
			dynamicFormStructureBODetail=null;
			dynamicFormEvents=null;
			dynamicHeaderFieldEvents=null;
			dynamicDetailFieldEvents=null;
			dynamicHeaderpickListOptions=null;
			dynamicDetailpickListOptions=null;
			
			ui=null;
			
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_BUYER_ORDER, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doSaveBuyerOrder(HttpServletRequest request, HttpServletResponse response, FileImport objBean){
		  
//		HttpSession session=request.getSession();
		TPCSUser ui=new UserInfo().getUserInfo(request);
//		String clientId = (String)request.getSession().getAttribute("client_id");
		ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
		
//		String[] userid = fileMap.get("selected_users").trim().split(",");
//		String[] userremarks = fileMap.get("selected_userremarks").split(",");

		//objSQ.setSaveFollower(fileMap.get("remember_followers")!=null?fileMap.get("remember_followers"):"null");

		BuyerOrder objBO=new BuyerOrder();

		objBO=getRequestData(request,ui, objBO, objBean);

		objBO= boMan.saveBuyerOrder(ui,objBO,objBean);
		int boId=objBO.getHeader().getBoId();
		if(boId==-1){

			request.setAttribute("error_message",bundle.getString("BuyerOrder.BuyerOrderAlreadyExists"));

			objBO.getHeader().setBoId(0);
			doAddEditBuyerOrder(request,response,objBO);

		}
		else{

			if(boId>0){
				if(objBO.getHeaderMode().equals("n")){
					objBO.setHeaderMode("e");	 
				}
					request.setAttribute("success_message",bundle.getString("BuyerOrder.BuyerOrderInserted")); 
			}
			else{
				request.setAttribute("error_message",bundle.getString("BuyerOrder.BuyerOrderNotInserted")); 
			}
			if(objBO.getDetailMode().equals("e")){
				objBO.setDetailMode("n"); 
			}

			if(objBO.getSaveType().equals("3")){//Save Type Save & New

				objBO=new BuyerOrder();
				BuyerOrderHeader boHeader=new BuyerOrderHeader();
				objBO.setHeaderMode("n");
				objBO.setDetailMode("n");
				objBO.setHeader(boHeader);
				boHeader=null;
				setNewDefaultData(request,objBO);
				doAddEditBuyerOrder(request,response,objBO);
			}
			else  if(objBO.getSaveType().equals("4")){//Save Type Save & Close
				doDisplayBuyerOrder(request,response) ;
			}
			else {

				if(objBO.getSaveType().equals("2")){//button type save&send

					int type=1;
					if(objBO.getEditMode().equalsIgnoreCase("e")){
						type=2;
					}

					if(!objBO.getHeader().isSendLater()){

//						List<String> boDetail=boMan.getEmailBODetails(boId,ui);
//						List<String> toIds=boMan.getBOFollowers(boId,ui);
//						String loginName=boMan.getLoggedInUser(ui);
//						String strContactImage=boMan.getContactImage(ui);
//						if(!objBO.getHeader().getEmailSentTo().isEmpty() && !toIds.contains(objBO.getHeader().getEmailSentTo())){
//							toIds.add(objBO.getHeader().getEmailSentTo());
//						}
//						
//						MailInfo mailInfo=new MailInfo();
////						MailInfo mailInfo=generateSalesQuotationPdf(request, quoteId);
//						List<MailInfo> liMailInfo=new ArrayList<MailInfo>();
//		            	liMailInfo.add(mailInfo);
//		            	mailInfo.setLiMailInfo(liMailInfo);
//		            	mailInfo.setLoginName(loginName);
//						boMan.updateMailTag(boId,ui);
//						boMan.sendNewBOEmailAlert(boDetail,ui.getUserId(),toIds,loginName,(String)session.getAttribute("path"),strContactImage,clientId,type,mailInfo); 
//					
//						boDetails=null;
//						toIds=null;
					}

				}

				doAddEditBuyerOrder(request,response,objBO);

			}
		}
		ui=null;
		bundle=null;
		objBO=null;
	
	}
	
	public BuyerOrder getRequestData(HttpServletRequest request,TPCSUser ui,BuyerOrder objBO,FileImport objBean){
		String realPath=(String) request.getSession().getAttribute("path");
		String contextPath=realPath+new UserInfo().getClientPath(request);
		objBean.setFolderPath(contextPath);
		realPath=null;
		contextPath=null;
		Map<String,String> fileMap=new LinkedHashMap<String, String>();

		dynamicFieldsListBOHeader = (List<DynamicFields>) request.getSession().getAttribute("dynamicFieldsListBOHeader");
		dynamicFieldsListBODetail= (List<DynamicFields>) request.getSession().getAttribute("dynamicFieldsListBODetail");


		List<String> chkHead=new ArrayList<String>();
		List<String> chkDetail=new ArrayList<String>();

		for(DynamicFields dynamicFields: dynamicFieldsListBOHeader) {
			//Checking the condition - if dynamic field i.e., isFixed  =  false
			if(!dynamicFields.isFixed()) {
				chkHead.add(dynamicFields.getPageFieldName());
			}
			dynamicFields=null;
		}
		int quoteDetId=Validator.convertToInteger(fileMap.get("bo_det_id"));
		String detId="_"+quoteDetId;

		for(DynamicFields dynamicFields: dynamicFieldsListBODetail) {
			//Checking the condition - if dynamic field i.e., isFixed  =  false
			if(!dynamicFields.isFixed()) {
				chkDetail.add(dynamicFields.getPageFieldName()+detId);
			}
			dynamicFields=null;
		}

		// String detId="_"+fileMap.get("quote_det_id");
		FileItem item =null;
		Iterator in = objBean.getFileItems().iterator();
		while ( in.hasNext () ){ 
			item = (FileItem)in.next();

			if ( item.isFormField() ){
				fileMap.put(item.getFieldName(), item.getString());

				if(chkHead.contains(item.getFieldName())){

					for(DynamicFields dynamicFields: dynamicFieldsListBOHeader) {
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

					for(DynamicFields dynamicFields: dynamicFieldsListBODetail) {
						//Checking the condition - if dynamic field i.e., isFixed  =  false
						if(!dynamicFields.isFixed()) {

							String PageFieldName=dynamicFields.getPageFieldName()+detId;
							
							if (PageFieldName.equals(item.getFieldName())) {
								dynamicFields.setFieldValue((String) StringEscapeUtils.escapeXml(item.getString()));//request.getParameter(dynamicFields.getPageFieldName()));
							}
							PageFieldName=null;
//							dynamicFields.setFieldValue((String) StringEscapeUtils.escapeXml(fileMap.get(dynamicFields.getPageFieldName())));
						}
					}
				}

			}
		}
		//calling xml for dynamic fields
		String sqlxml = generateXMLforDynamicFields(dynamicFieldsListBOHeader,dynamicFieldsListBODetail);

		objBO=setBuyerOrderAttributes(fileMap,request,ui,objBO);

		//added for dynamic fields 
		objBO.setSqlxmlDynamicFields(sqlxml);
		sqlxml=null;
		String editedIds=fileMap.get("edited_ids");
		String dynEditedIds=fileMap.get("dynedited_ids");

		Map<String,String> editDataMap= new HashMap<String, String>();
		Map<String,String> editDynamicDataMap= new HashMap<String, String>();

		if(objBO.getEditMode().equalsIgnoreCase("e")){
			
			String streditedIds[] = editedIds.split(",");
			for(int i=0; i < streditedIds.length; i++){

				editDataMap.put(streditedIds[i], fileMap.get(streditedIds[i]));

			}

			String strDyneditedIds[] = dynEditedIds.split(",");
			for(int i=0; i < strDyneditedIds.length; i++){

				editDynamicDataMap.put(strDyneditedIds[i], fileMap.get(strDyneditedIds[i]));

			}
			
			objBO.setEditDataMap(editDataMap);
			editDataMap=null;
			objBO.setEditDynamicDataMap(editDynamicDataMap);
			editDynamicDataMap=null;
			
			realPath=null;
			chkHead=null;
			chkDetail=null;
		}
		editedIds=null;
		dynEditedIds=null;
		
		return objBO;
	}
	private String generateXMLforDynamicFields( List<DynamicFields> dynamicFieldsListBOHeader, List<DynamicFields> dynamicFieldsListBODetail) {
		
		String strXML = "<screens> ";
		String formBO = "<screen name=\"buyer_order\">  <columns> ";
		String formBODetail = "<screen name=\"buyer_order_detail\">  <columns> ";

		if(dynamicFieldsListBOHeader!=null) {
			for (DynamicFields dynamicFields : dynamicFieldsListBOHeader) {
				if (!dynamicFields.isFixed()) {
					String column = " <column type=\"dynamic\"> <name>" + dynamicFields.getDbFieldName() + "</name> <value>" + dynamicFields.getFieldValue() + "</value> <datatype>" + dynamicFields.getDataTypeName() + "</datatype> </column> ";
					formBO = formBO.concat(column);
				} 
				dynamicFields=null;
			}
		}
		
		formBO = formBO.concat(" </columns> </screen> ");
		formBO = formBO.concat(formBODetail);
		
		if(dynamicFieldsListBODetail!=null) {
			for (DynamicFields dynamicFields : dynamicFieldsListBODetail) {
				if (!dynamicFields.isFixed()) {	
					String column = " <column type=\"dynamic\"> <name>" + dynamicFields.getDbFieldName() + "</name> <value>" +dynamicFields.getFieldValue()+ "</value> <datatype>" + dynamicFields.getDataTypeName() + "</datatype> </column> ";
					formBO = formBO.concat(column);
				}
				dynamicFields=null;
			}
		}

		formBO = formBO.concat(" </columns> </screen> ");
		formBO = formBO.concat(" </screens> ");
		strXML = strXML.concat(formBO);
		formBO=null;
		formBODetail=null;
		return strXML;
	}
	private BuyerOrder setBuyerOrderAttributes(Map<String,String> fileMap,HttpServletRequest request,TPCSUser userInfo,BuyerOrder objBO) {
		BuyerOrderHeader header= setHeaderAttributes(fileMap,request,userInfo);
		BuyerOrderDetail detail= setDetailAttributes(fileMap,request,userInfo);
		
		objBO.setSaveType(fileMap.get("save_type"));
		objBO.setEditMode(fileMap.get("edited_mode"));
		
		objBO.setHeader(header);
		objBO.setDetail(detail);
		objBO.setHeaderMode(fileMap.get("header_mode"));
		objBO.setDetailMode(fileMap.get("new_det_mode"));
		
		header=null;
		detail=null;
		
		return objBO;
	}
	private BuyerOrderHeader setHeaderAttributes(Map<String,String> fileMap,HttpServletRequest request,TPCSUser userInfo) {
		BuyerOrderHeader header= new BuyerOrderHeader();
		
		header.setBoId(Validator.convertToInteger(fileMap.get("bo_id")));
		header.setBoPrefix(fileMap.get("bo_prefix"));
		header.setBoNo(Validator.convertToInteger(fileMap.get("bo_no")));
//		System.out.println("fileMap.get(bo_prefix) :"+fileMap.get("bo_prefix"));
		header.setBoPrefix(fileMap.get("bo_prefix"));
		header.setBoPrefixNo(header.getBoPrefix()+header.getBoNo());
		header.setBoDate(fileMap.get("bo_date"));
		header.setCustomerName(fileMap.get("buyer_name"));
		header.setCustomerId(Validator.convertToInteger(fileMap.get("buyer_id")));
		header.setBuyerPoNo(fileMap.get("buyer_po_no"));
		header.setCurrencyName(fileMap.get("currency_name"));
		header.setCurrencyId(Validator.convertToInteger(fileMap.get("currency_id")));
		header.setExRate(Validator.convertToFloat(fileMap.get("ex_rate"))>0?fileMap.get("ex_rate"):"1");
		header.setModeOfShip(fileMap.get("mode_of_ship"));
		header.setSeasonName(fileMap.get("season_name"));
		header.setSeasonId(Validator.convertToInteger(fileMap.get("season_id")));
		header.setPiNo(fileMap.get("pi_no"));
		header.setPiDate(fileMap.get("pi_date"));
		header.setAgentName(fileMap.get("agent_name"));
		header.setAgentId(Validator.convertToInteger(fileMap.get("agent_id")));
		header.setCustomerPlanNo(fileMap.get("customer_plan_no"));
		header.setPaymentTerms(fileMap.get("payment_terms"));
		header.setInsurenceTerms(fileMap.get("insurance_terms"));
		header.setDeliveryTerms(fileMap.get("delivery_terms"));
		header.setDeliveryTo(fileMap.get("delivery_to"));
		header.setSpecialInstruction(fileMap.get("special_instruction"));
		header.setPackingLabeling(fileMap.get("packing_labeling"));
		getTCInfo(fileMap,header);
		getPackingScheduleData(fileMap, header);
		return header;
		
	}
	private BuyerOrderDetail setDetailAttributes(Map<String,String> fileMap,HttpServletRequest request,TPCSUser userInfo) {
		BuyerOrderDetail detail= new BuyerOrderDetail();
		
		String tmpDetId="_0";
		
		detail.setBoDetId(Validator.convertToInteger(fileMap.get("bo_det_id")));
		detail.setLineNo(fileMap.get("line_no"+tmpDetId));
		detail.setItemName(fileMap.get("item_name"+tmpDetId));
		detail.setItemId(Validator.convertToInteger(fileMap.get("item_id"+tmpDetId)));
		detail.setVariantName(fileMap.get("variant_name"+tmpDetId));
		detail.setVariantId(Validator.convertToInteger(fileMap.get("variant_id"+tmpDetId)));
		detail.setBuyerStyleNo(fileMap.get("buyer_style_no"+tmpDetId));
		detail.setSizeRange(fileMap.get("size_range"+tmpDetId));
		detail.setSizeRangeId(Validator.convertToInteger(fileMap.get("size_range_id"+tmpDetId)));
		detail.setQty(fileMap.get("qty"+tmpDetId));
		detail.setRate(fileMap.get("rate_fcy"+tmpDetId));
		detail.setRequiredDate(fileMap.get("required_date"+tmpDetId));
		detail.setPossibleDate(fileMap.get("possible_date"+tmpDetId));
		detail.setMrp(fileMap.get("mrp"+tmpDetId));
		detail.setBarcode(fileMap.get("barcode"+tmpDetId));
		detail.setRemark(fileMap.get("remark"+tmpDetId));
		
		
		getDetailSizeData(fileMap, detail);
		
		getDetailPackingSizeData(fileMap, detail);
		tmpDetId=null;
		return detail;
	}
	private void getTCInfo(Map<String, String> fileMap, BuyerOrderHeader sih) {
		String tcIds=fileMap.get("bo_tc_ids");
		String[] arTcIds=tcIds.split(",");
		tcIds=null;
		List<String> listTcIds = new ArrayList<String>(Arrays.asList(arTcIds));
		arTcIds=null;
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
			rowId=null;
		}
		buffer.append("</tc_ifo>");
		sih.setTcValues(buffer.toString());
		
		buffer=null;
		listTcIds=null;
	}
	private void getPackingScheduleData(Map<String, String> fileMap, BuyerOrderHeader header){
		StringBuffer packingBuffer=new StringBuffer();
		packingBuffer.append("<packing_data>");

		String packingRowIds=fileMap.get("packing_row_ids");

//		System.out.println("packingRowIds:"+packingRowIds);

		String[] arPackingRowIds=packingRowIds.split(",");
		packingRowIds=null;
		List<String> packingRowIdList=Arrays.asList(arPackingRowIds);
		arPackingRowIds=null;

		for(String packingRow:packingRowIdList){
			String packingType=fileMap.get("packing_type_"+packingRow);
			//			String packingSizeRange=fileMap.get("packing_size_range_"+packingRow);
			String packingSizeRangeId=fileMap.get("packing_size_range_id_"+packingRow);

			if(packingSizeRangeId!=null && Validator.convertToInteger(packingSizeRangeId)>0){

				String packingSizeIds=fileMap.get("packing_size_ids_"+packingRow);
//				System.out.println("packingSizeIds :"+packingSizeIds);

				String[] arPackingSizeIds=packingSizeIds.split(",");
				packingSizeIds=null;
				List<String> packingSizeIdList=Arrays.asList(arPackingSizeIds);
				arPackingSizeIds=null;

				packingBuffer.append("<packing_row>");

				for(String sizeId:packingSizeIdList){
					
					if(Validator.convertToFloat(fileMap.get("packing_size_qty_"+packingRow+"_"+sizeId))>0){
						String sizeQty=fileMap.get("packing_size_qty_"+packingRow+"_"+sizeId);

						packingBuffer.append("<size_row>");

						packingBuffer.append("<packing_type>");
						packingBuffer.append(packingType);
						packingBuffer.append("</packing_type>");

						packingBuffer.append("<size_range_id>");
						packingBuffer.append(packingSizeRangeId);
						packingBuffer.append("</size_range_id>");

						packingBuffer.append("<size_id>");
						packingBuffer.append(sizeId);
						packingBuffer.append("</size_id>");

						packingBuffer.append("<size_qty>");
						packingBuffer.append(sizeQty);
						packingBuffer.append("</size_qty>");

						packingBuffer.append("</size_row>");
						sizeQty=null;
					}
					sizeId=null;
				}

				packingBuffer.append("</packing_row>");

				packingSizeIds=null;
				arPackingSizeIds=null;
				packingSizeIdList=null;
			}
			packingType=null;
			//			packingSizeRange=null;
			packingSizeRangeId=null;

			packingRow=null;
		}
		packingRowIds=null;
		arPackingRowIds=null;
		packingRowIdList=null;

		packingBuffer.append("</packing_data>");

//		System.out.println("packingBuffer.toString() :"+packingBuffer.toString());

		header.setPackingScheduleData(packingBuffer.toString());
		packingBuffer=null;
	}
	private void getDetailSizeData(Map<String, String> fileMap, BuyerOrderDetail detail){
		
		String editedDetIds=fileMap.get("edited_size_det_ids");
		String[] arEditedDetIds=editedDetIds.split(",");
		editedDetIds=null;
		List<String> editedDetIdList=Arrays.asList(arEditedDetIds);
		arEditedDetIds=null;
		
		Set<String> hs = new HashSet<String>();
		hs.addAll(editedDetIdList);
		editedDetIdList=new ArrayList<String>();
		editedDetIdList.addAll(hs);
		hs=null;
		
		StringBuffer sizeBuffer=new StringBuffer();
		sizeBuffer.append("<size_range_size>");
		
		for(String detId:editedDetIdList){

//			if(!detId.isEmpty()){

				String sizeIds=fileMap.get("size_ids_"+detId);
				if(sizeIds!=null && !sizeIds.isEmpty()){
				String[] arSizeIds=sizeIds.split(",");
				sizeIds=null;
				List<String> sizeIdList=Arrays.asList(arSizeIds);
				arSizeIds=null;
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

						sizeBuffer.append("<size_rate>");
						sizeBuffer.append(String.valueOf(Validator.convertToFloat(fileMap.get("size_rate_"+detId+"_"+sizeId))));
						sizeBuffer.append("</size_rate>");

						sizeBuffer.append("</size_detail>");
					}
					sizeId=null;
				}
//			}
				
				sizeIds=null;
				arSizeIds=null;
				sizeIdList=null;
				}
				detId=null;
		}
		sizeBuffer.append("</size_range_size>");
		
//		System.out.println("Size xml :"+sizeBuffer.toString());
		
		editedDetIds=null;
		arEditedDetIds=null;
		editedDetIdList=null;
		hs =null;
		
		detail.setSizeData(sizeBuffer.toString());
		sizeBuffer=null;
	}
	
	private void getDetailPackingSizeData(Map<String, String> fileMap, BuyerOrderDetail detail){
		
		StringBuffer locBuffer=new StringBuffer();
		locBuffer.append("<location_data>");
		locBuffer.append("<location>");
		
		String detIds="0,"+fileMap.get("det_ids");
		String[] arDetIds=detIds.split(",");
		detIds=null;
		List<String> detIdList=Arrays.asList(arDetIds);
		
		for(String detId:detIdList){
//		int detId=detail.getBoDetId();
		String cortanLocIds=fileMap.get("cortan_loc_ids_"+detId);
		
//		System.out.println("cortanLocIds:"+cortanLocIds);
		
		if(cortanLocIds!=null && !cortanLocIds.isEmpty()){
		
		String[] arCortanLocIds=cortanLocIds.split(",");
		cortanLocIds=null;
		List<String> cortanLocIdList=Arrays.asList(arCortanLocIds);
		arCortanLocIds=null;
		
//		System.out.println("cortanLocIdList:"+cortanLocIdList.toString());
		
		for(String locId:cortanLocIdList){
			String cortanLoc=fileMap.get("cortan_location_"+detId+"_"+locId);
			
			if(cortanLoc!=null && !cortanLoc.isEmpty()){
			
			String packingRowIds=fileMap.get("packing_row_ids");
			
//			System.out.println("packingRowIds :"+packingRowIds);
			
			String[] arPackingRowIds=packingRowIds.split(",");
			packingRowIds=null;
			List<String> packingRowIdList=Arrays.asList(arPackingRowIds);
			arPackingRowIds=null;

			for(String packingRow:packingRowIdList){

				String packingType=fileMap.get("packing_type_"+packingRow);
				if(packingType!=null && !packingType.isEmpty()){
					String cortanQty=Validator.convertToInteger(fileMap.get("cortan_qty_"+detId+"_"+locId+"_"+packingRow))>0?fileMap.get("cortan_qty_"+detId+"_"+locId+"_"+packingRow):"0";

					locBuffer.append("<location_row>");

					locBuffer.append("<detail_id>");
					locBuffer.append(detId);
					locBuffer.append("</detail_id>");

					locBuffer.append("<location>");
					locBuffer.append(cortanLoc);
					locBuffer.append("</location>");

					locBuffer.append("<packing_type>");
					locBuffer.append(packingType);
					locBuffer.append("</packing_type>");

					locBuffer.append("<cortan_qty>");
					locBuffer.append(cortanQty);
					locBuffer.append("</cortan_qty>");

					locBuffer.append("</location_row>");

					cortanQty=null;
				}
				packingRow=null;
				packingType=null;
			}
			
			packingRowIds=null;
			arPackingRowIds=null;
			packingRowIdList=null;
			
			
			}
			cortanLoc=null;
			locId=null;
			
		}
		
		cortanLocIds=null;
		arCortanLocIds=null;
		cortanLocIdList=null;
		}
		detId=null;
		
		}
		detIdList=null;
		
		locBuffer.append("</location>");
		locBuffer.append("</location_data>");
		
//		System.out.println("locBuffer.toString() :"+locBuffer.toString());
		
		detail.setCartonData(locBuffer.toString());
		locBuffer=null;
	}
	private String getHeaderMode(Map<String,String> fileMap,HttpServletRequest request){
		String headMode= Validator.trim(request.getParameter("header_mode"));
		return headMode; 	
	}
	private String getDetailMode(Map<String,String> fileMap,HttpServletRequest request) {
		String	detMode=Validator.trim(request.getParameter("det_mode"));
		return detMode; 
	}
	public void setNewDefaultData(HttpServletRequest request, BuyerOrder objBO){
		request.setAttribute("bo_date",TPCSCommonUtil.currenDate("dd-MM-yyyy"));
		request.setAttribute("new_page","yes");
	}
	
	public void doGetSizeRangeSizeGrid(HttpServletRequest request, HttpServletResponse response){
		try {
			
			int itemId=Validator.convertToInteger(request.getParameter("item_id"));
			int sizeRangeId=Validator.convertToInteger(request.getParameter("size_range_id"));
			int boId=Validator.convertToInteger(request.getParameter("bo_id"));
			int boDetId=Validator.convertToInteger(request.getParameter("bo_det_id"));
			
			TPCSUser ui=new UserInfo().getUserInfo(request);
			BuyerOrder objBO=new BuyerOrder();
			BuyerOrderHeader header=new BuyerOrderHeader();
			BuyerOrderDetail detail=new BuyerOrderDetail();
			
			header.setBoId(boId);
			detail.setBoDetId(boDetId);
			detail.setItemId(itemId);
			detail.setSizeRangeId(sizeRangeId);
			
			
			objBO.setHeader(header);
			objBO.setDetail(detail);
			
			
			objBO=boMan.getBOSizeRangeSizeGrid(ui,objBO);
			
//			StringBuffer parentBuffer=new StringBuffer();
			if(objBO.getDetail().isSizeExists()){
			
				formSizeData(objBO.getDetail());
				
//			List<Size> sizeList=objBO.getDetail().getSizeList();
//			
//			
//			StringBuffer headerBuffer=new StringBuffer();
//			StringBuffer qtyBuffer=new StringBuffer();
//			StringBuffer rateBuffer=new StringBuffer();
//			parentBuffer.append("<table class=\"table-bordered table-condensed table-hover size-table\" id=\"grid_table\">");
//			
//			headerBuffer.append("<tr class=\"header-det\">");
//			headerBuffer.append("<th>");
//			headerBuffer.append("<label>Size</label>");
//			headerBuffer.append("</th>");
//			
//			qtyBuffer.append("<tr>");
//			qtyBuffer.append("<td><div class=\"form-group\">Qty</div>");
//			qtyBuffer.append("<input type=\"hidden\" id=\"size_ids_"+objBO.getDetail().getBoDetId()+"\" id=\"size_ids_"+objBO.getDetail().getBoDetId()+"\" value=\""+objBO.getDetail().getSizeIdList().toString().replace("[", "").replaceAll("]", "").replace(" ", "")+"\" />"); 
//			qtyBuffer.append("</td>");
//			
//			rateBuffer.append("<tr>");
//			rateBuffer.append("<td><div class=\"form-group\">Price</div>");
//			rateBuffer.append("</td>");
//			
//			
//			for(Size size:sizeList){
//				headerBuffer.append("<th>");
//				headerBuffer.append("<label>"+size.getSizeName()+"</label>");
//				headerBuffer.append("</th>");
//				
//				qtyBuffer.append("<td>");
//				qtyBuffer.append("<div class=\"form-group\">"
//						+ "<input type=\"text\" class=\"form-control\" id=\"size_qty_"+objBO.getDetail().getBoDetId()+"_"+size.getSizeId()+"\" name=\"size_qty_"+objBO.getDetail().getBoDetId()+"_"+size.getSizeId()+"\" value=\""+size.getQty()+"\" maxlength=\"8\" placeholder=\"Enter qty\" />"
//						+ "</div>");
//				qtyBuffer.append("</td>");
//				
//				rateBuffer.append("<td>");
//				rateBuffer.append("<div class=\"form-group\">"
//						+ "<input type=\"text\" class=\"form-control\" id=\"size_rate_"+objBO.getDetail().getBoDetId()+"_"+size.getSizeId()+"\" name=\"size_rate_"+objBO.getDetail().getBoDetId()+"_"+size.getSizeId()+"\" value=\""+size.getRate()+"\" maxlength=\"8\" placeholder=\"Enter qty\" />"
//						+ "</div>");
//				rateBuffer.append("</td>");
//				
//				
//			}
//			qtyBuffer.append("</tr>");
//			rateBuffer.append("</tr>");
//			headerBuffer.append("</tr>");
//			
//			
//			parentBuffer.append(headerBuffer.toString());
//			parentBuffer.append(qtyBuffer.toString());
//			parentBuffer.append(rateBuffer.toString());
//			headerBuffer=null;
//			qtyBuffer=null;
//			rateBuffer=null;
//			parentBuffer.append("</table>");
			
			
//			System.out.println("size_data :"+objBO.getDetail().getSizeData().toString());
			}
			StringBuffer resultXML=new StringBuffer();
			resultXML.append("<size_range_size>");
			resultXML.append("<size>");
			resultXML.append("<size_exists>");
			resultXML.append(objBO.getDetail().isSizeExists()==true?"1":"0");
			resultXML.append("</size_exists>");
			resultXML.append("<size_grid>");
			resultXML.append(StringEscapeUtils.escapeXml(objBO.getDetail().getSizeData()));
			resultXML.append("</size_grid>");
			resultXML.append("</size>");
			
			resultXML.append("</size_range_size>");
			
			response.setContentType("text/xml");
			response.getWriter().write(resultXML.toString());
			
			header=null;
			detail=null;
			
			resultXML=null;
			objBO=null;
//			sizeGrid=null;
			ui=null;

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void formSizeData(BuyerOrderDetail detail){
		
		StringBuffer parentBuffer=new StringBuffer();
		List<Size> sizeList=detail.getSizeList();
		
		
		StringBuffer headerBuffer=new StringBuffer();
		StringBuffer qtyBuffer=new StringBuffer();
		StringBuffer rateBuffer=new StringBuffer();
		parentBuffer.append("<div class=\"col-sm-12\">");
		parentBuffer.append("<table class=\"table-bordered table-condensed table-hover size-grid size-table\" id=\"grid_table\">");
		
		headerBuffer.append("<tr class=\"header-det\">");
		headerBuffer.append("<th>");
		headerBuffer.append("Size");
		headerBuffer.append("</th>");
		
		qtyBuffer.append("<tr>");
		qtyBuffer.append("<td><div class=\"form-group\">Qty</div>");
		qtyBuffer.append("<input type=\"hidden\" id=\"size_ids_"+detail.getBoDetId()+"\" name=\"size_ids_"+detail.getBoDetId()+"\" value=\""+detail.getSizeIdList().toString().replace("[", "").replaceAll("]", "").replace(" ", "")+"\" />"); 
		qtyBuffer.append("<input type=\"hidden\" id=\"size_tot_qty_"+detail.getBoDetId()+"\" name=\"size_tot_qty_"+detail.getBoDetId()+"\" value=\""+detail.getSizeTotalQty()+"\" />");
		qtyBuffer.append("</td>");
		
		
		rateBuffer.append("<tr>");
		rateBuffer.append("<td><div class=\"form-group\">Price</div>");
		rateBuffer.append("</td>");
		
		
		for(Size size:sizeList){
			headerBuffer.append("<th>");
			headerBuffer.append(""+size.getSizeName()+"");
			headerBuffer.append("</th>");
			
			qtyBuffer.append("<td>");
			qtyBuffer.append("<div class=\"form-group\">"
					+ "<input type=\"text\" class=\"form-control\" id=\"size_qty_"+detail.getBoDetId()+"_"+size.getSizeId()+"\" name=\"size_qty_"+detail.getBoDetId()+"_"+size.getSizeId()+"\" value=\""+size.getQty()+"\" onkeyup=\"calculateTotalQty("+detail.getBoDetId()+")\" maxlength=\"8\" placeholder=\"Enter qty\" />"
					+ "</div>");
			qtyBuffer.append("</td>");
			
			rateBuffer.append("<td>");
			rateBuffer.append("<div class=\"form-group\">"
					+ "<input type=\"text\" class=\"form-control\" id=\"size_rate_"+detail.getBoDetId()+"_"+size.getSizeId()+"\" name=\"size_rate_"+detail.getBoDetId()+"_"+size.getSizeId()+"\" value=\""+size.getRate()+"\"onkeyup=\"calculateTotalQty("+detail.getBoDetId()+")\" maxlength=\"8\" placeholder=\"Enter Rate\" />"
					+ "</div>");
			rateBuffer.append("</td>");
		}
		qtyBuffer.append("</tr>");
		rateBuffer.append("</tr>");
		headerBuffer.append("</tr>");
		
		parentBuffer.append("<thead>"+headerBuffer.toString()+"</thead>");
		parentBuffer.append("<tbody>"+qtyBuffer.toString());
		parentBuffer.append(rateBuffer.toString()+"</tbody>");
		headerBuffer=null;
		qtyBuffer=null;
		rateBuffer=null;
		parentBuffer.append("</table>");
		parentBuffer.append("</div>");
		
		detail.setSizeData(parentBuffer.toString());
		parentBuffer=null;
		sizeList=null;
	}
	public void doDeleteBuyerOrderDetail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		try {
			Map<String,String> fileMap=new LinkedHashMap<String, String>();
			TPCSUser ui=new UserInfo().getUserInfo(request);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			BuyerOrder objBO=new BuyerOrder();
			objBO.setHeaderMode(getHeaderMode(fileMap,request));
			objBO.setDetailMode(getDetailMode(fileMap,request));

			int boId=Validator.convertToInteger(request.getParameter("bo_id"));
			int boDetId=Validator.convertToInteger(request.getParameter("bo_det_id"));
			
			BuyerOrderHeader header=new BuyerOrderHeader();
			header.setBoId(boId);
			objBO.setHeader(header);
			header=null;
			
			BuyerOrderDetail detail=new BuyerOrderDetail();
			detail.setBoDetId(boDetId);
			objBO.setDetail(detail);
			detail=null;
			
			objBO= boMan.deleteBuyerOrderDetail(ui,objBO);
//			SalesQuotationHead sqH=boMan.SalesQuotationHeader(3,quoteId,userInfo);
//			SalesQuotation detList=boMan.getAllSalesQuotationDetails(quoteId,userInfo);
			
//			if(quoteId>0)
//			{
//				if(detList==null)
//				{
//					objSQ.setDetail(setDetailAttributes(fileMap,request,userInfo));
//				}

//			}
			
			
			if(objBO.getDeletedCount()==1){
				request.setAttribute("success_message",bundle.getString("BuyerOrder.BuyerOrderDetailDeleted"));	
				doAddEditBuyerOrder(request,response,objBO);
				
			}
			else if(objBO.getDeletedCount()==2){
				objBO=new BuyerOrder();
				BuyerOrderHeader sqHeader=new BuyerOrderHeader();
				objBO.setHeader(sqHeader);
				setNewDefaultData(request,objBO);
				request.setAttribute("success_message",bundle.getString("BuyerOrder.BuyerOrderDeleted"));
				objBO.setHeaderMode("n");
				objBO.setDetailMode("n");
				doAddEditBuyerOrder(request,response,objBO);
				sqHeader=null;
				objBO=null;
			}
			else{
				request.setAttribute("error_message",bundle.getString("BuyerOrder.BuyerOrderDetailNotDeleted"));
				doAddEditBuyerOrder(request,response,objBO);
			}
		
			fileMap=null;
			objBO=null;
			ui=null;
			bundle=null;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doDeleteBuyerOrder(HttpServletRequest request,HttpServletResponse response){
		try{
			TPCSUser ui=new UserInfo().getUserInfo(request);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			int boId=Validator.convertToInteger(request.getParameter("bo_id"));
			int page=Validator.convertToInteger(request.getParameter("page"));
//			int boDetId=Validator.convertToInteger(request.getParameter("bo_det_id"));
			
			BuyerOrder objBO=new BuyerOrder();
			
			BuyerOrderHeader header=new BuyerOrderHeader();
			header.setBoId(boId);
			objBO.setHeader(header);
			
//			BuyerOrderDetail detail=new BuyerOrderDetail();
//			detail.setBoDetId(boDetId);
//			objBO.setDetail(detail);
			
			objBO=boMan.deleteBuyerOrder(ui,objBO);
			if(objBO.isDeleted()){
				request.setAttribute("success_message",bundle.getString("BuyerOrder.BuyerOrderDeleted"));
				
			}
			else{
				request.setAttribute("error_message",bundle.getString("BuyerOrder.BuyerOrderNotDeleted"));
				
			}

//			String strRequestType= request.getParameter("request_type");
			String strSearchQuery="";
			
			
			 if(page>0){
				 objBO=new BuyerOrder();
					BuyerOrderHeader sqHeader=new BuyerOrderHeader();
					objBO.setHeader(sqHeader);
					setNewDefaultData(request,objBO);
					request.setAttribute("success_message",bundle.getString("BuyerOrder.BuyerOrderDeleted"));
					objBO.setHeaderMode("n");
					objBO.setDetailMode("n");
					doAddEditBuyerOrder(request,response,objBO);
					sqHeader=null;
					objBO=null;
		     }
			 else{
		    	 organizColumns(request,strSearchQuery);
		    	 
		    	 TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_BUYER_ORDER,request,response);
		     }

			 ui=null;
			 bundle =null;
			 objBO=null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}	  
	public void doDeleteBulkBuyerOrder(HttpServletRequest request,HttpServletResponse response) 
	{
		try{
			TPCSUser ui =(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundleProvider rbp=new ResourceBundleProvider();
			ResourceBundle bundle = rbp.getResourceBundle(ui);
			BuyerOrder objBO=new BuyerOrder();
			BuyerOrderHeader header=new BuyerOrderHeader();

			String boIds[]=request.getParameterValues("bo_chk_id");

			int indId=0;
			//---------------------------------------------------------------------------------------------
			for(int j=0;j<boIds.length;j++){
				header=new BuyerOrderHeader();
				indId=Integer.parseInt(boIds[j]);

				header.setBoId(indId);
				objBO.setHeader(header);

				objBO=boMan.deleteBuyerOrder(ui,objBO);
				header=null;
			}
			//---------------------------------------------------------------------------------------------

			if(objBO.isDeleted()){
				request.setAttribute("success_message",bundle.getString("BuyerOrder.BuyerOrderDeleted"));
				
			}
			else{
				request.setAttribute("error_message",bundle.getString("BuyerOrder.BuyerOrderNotDeleted"));
				
			}
			doDisplayBuyerOrder(request, response);
			rbp=null;
			objBO=null;
			header=null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void doCancelBuyerOrder(HttpServletRequest request,HttpServletResponse response){
		try{
			TPCSUser ui=new UserInfo().getUserInfo(request);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			int boId=Validator.convertToInteger(request.getParameter("bo_id"));
//			int page=Validator.convertToInteger(request.getParameter("page"));
			
			BuyerOrder objBO=new BuyerOrder();
			
			BuyerOrderHeader header=new BuyerOrderHeader();
			header.setBoId(boId);
			objBO.setHeader(header);
			header=null;
			
			objBO=boMan.cancelBuyerOrder(ui,objBO);
			if(objBO.isDeleted()){
				request.setAttribute("success_message",bundle.getString("BuyerOrder.BuyerOrderCancelled"));
		
			}
			else{
				request.setAttribute("error_message",bundle.getString("BuyerOrder.BuyerOrderNotCancelled"));
				
			}
			doDisplayBuyerOrder(request, response);
			ui=null;
			bundle =null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doCancelBulkBuyerOrder(HttpServletRequest request,HttpServletResponse response) 
	{
		try{
			TPCSUser userInfo =(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundleProvider rbp=new ResourceBundleProvider();
			ResourceBundle bundle = rbp.getResourceBundle(userInfo);
			BuyerOrder objBO=new BuyerOrder();
			BuyerOrderHeader header=new BuyerOrderHeader();

			String boIds[]=request.getParameterValues("bo_chk_id");

			int indId=0;
			//---------------------------------------------------------------------------------------------
			for(int j=0;j<boIds.length;j++){
				header=new BuyerOrderHeader();
				indId=Integer.parseInt(boIds[j]);

				header.setBoId(indId);
				objBO.setHeader(header);

				objBO=boMan.cancelBuyerOrder(userInfo,objBO);
				header=null;
			}
			//---------------------------------------------------------------------------------------------

			if(objBO.isCancelled()){
				request.setAttribute("success_message",bundle.getString("BuyerOrder.BuyerOrderCancelled"));

			}
			else{
				request.setAttribute("error_message",bundle.getString("BuyerOrder.BuyerOrderNotCancelled"));
			}
			doDisplayBuyerOrder(request, response);
			rbp=null;
			objBO=null;
			header=null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void doCloseBuyerOrder(HttpServletRequest request,HttpServletResponse response){
		try{
			TPCSUser ui=new UserInfo().getUserInfo(request);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			int boId=Validator.convertToInteger(request.getParameter("bo_id"));
//			int page=Validator.convertToInteger(request.getParameter("page"));
			
			BuyerOrder objBO=new BuyerOrder();
			
			BuyerOrderHeader header=new BuyerOrderHeader();
			header.setBoId(boId);
			objBO.setHeader(header);
			
			objBO=boMan.closeBuyerOrder(ui,objBO);
			if(objBO.isDeleted()){
				request.setAttribute("success_message",bundle.getString("BuyerOrder.BuyerOrderClosed"));
		
			}
			else{
				request.setAttribute("error_message",bundle.getString("BuyerOrder.BuyerOrderNotClosed"));
				
			}
			doDisplayBuyerOrder(request, response);
			ui=null;
			bundle=null;
			objBO=null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doCloseBulkBuyerOrder(HttpServletRequest request,HttpServletResponse response) 
	{
		try{
			TPCSUser ui =(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundleProvider rbp=new ResourceBundleProvider();
			ResourceBundle bundle = rbp.getResourceBundle(ui);
			BuyerOrder objBO=new BuyerOrder();
			BuyerOrderHeader header=new BuyerOrderHeader();

			String boIds[]=request.getParameterValues("bo_chk_id");

			int indId=0;
			//---------------------------------------------------------------------------------------------
			for(int j=0;j<boIds.length;j++){
				header=new BuyerOrderHeader();
				indId=Integer.parseInt(boIds[j]);

				header.setBoId(indId);
				objBO.setHeader(header);

				objBO=boMan.closeBuyerOrder(ui,objBO);
				header=null;
			}
			//---------------------------------------------------------------------------------------------

			if(objBO.isClosed()){
				request.setAttribute("success_message",bundle.getString("BuyerOrder.BuyerOrderClosed"));

			}
			else{
				request.setAttribute("error_message",bundle.getString("BuyerOrder.BuyerOrderNotClosed"));
			}
			doDisplayBuyerOrder(request, response);
			rbp=null;
			objBO=null;
			header=null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void addPackingScheduleEmptyRow(BuyerOrderHeader br){
		
		StringBuffer buffer =new StringBuffer();
		
		
//		buffer.append("<div class=\"row row-no-margin\" id=\"packing_row\">");
//		buffer.append("<div class=\"col-sm-4\" id=\"header\"><label>"+(type.equalsIgnoreCase("multiple_payment")?"Paid to":"Account Head")+"</label></div>");
//		buffer.append("<div class=\"col-sm-3\" id=\"header\"><label>"+(type.equalsIgnoreCase("receipt")?"Amount (Dr/-Cr)":type.equalsIgnoreCase("payment")?"Amount (Cr/-Dr)":type.equalsIgnoreCase("multiple_payment")?"Amount":"")+"</label></div>");
//		buffer.append("<div class=\"col-sm-4\" id=\"header\"><label>Remarks</label></div>");
//		buffer.append("<div class=\"col-sm-1\" id=\"header\"><label>&nbsp;</label></div>");
//		buffer.append("</div>");
		
	 	int dedRowCnt=br.getPackingRowCount();
//		int ccRowCnt=1;
		
		for(int i=0;i<dedRowCnt;i++){
			getPackingRow(buffer,i,dedRowCnt==(i+1)?true:false,dedRowCnt==(i+1)?false:true);
		}
		 
		 br.setPackingScheduleData(buffer.toString());
		 
		 buffer=null;
	}
	public void getPackingRow(StringBuffer buffer,int rowCnt,boolean isRowAdd,boolean isRowDelete){
	
	buffer.append("<div class=\"row\">");
	buffer.append("<div class=\"packing_row_"+rowCnt+"\" id=\"packing_row\">");
	
	buffer.append("<div class=\"col-sm-3\">");
	buffer.append("<div class=\"col-sm-12\">");
	buffer.append("<div class=\"form-group\">");
	buffer.append("<input type=\"text\" name=\"packing_type_"+rowCnt+"\" id=\"packing_type_"+rowCnt+"\" class=\"form-control\" placeholder=\"Enter Packing Type\"/>");
	buffer.append("</div>");
	buffer.append("</div>");
	buffer.append("</div>");
	
	buffer.append("<div class=\"col-sm-3\">");
	buffer.append("<div class=\"col-sm-12\">");
	buffer.append("<div class=\"form-group\">");
	buffer.append("<input type=\"text\" name=\"packing_size_range_"+rowCnt+"\" id=\"packing_size_range_"+rowCnt+"\" onblur=\"getSizeRangeSizeData("+rowCnt+")\" class=\"form-control\" placeholder=\"Type & Select Size Range\"/>");
	buffer.append("<input type=\"hidden\" name=\"packing_size_range_id_"+rowCnt+"\" id=\"packing_size_range_id_"+rowCnt+"\" />");
	buffer.append("</div>");
	buffer.append("</div>");
	buffer.append("</div>");
	
	buffer.append("<div class=\"col-sm-3\">");
	buffer.append("<div class=\"col-sm-3 \" >");
	if(isRowAdd){
		buffer.append("<a href=\"javascript:addPackingType("+rowCnt+")\" data-toggle=\"tooltip\" title=\"Add\"><span class=\"span-icon glyphicon glyphicon-plus text-primary\"></span></a>&nbsp;");
	}
	if(isRowDelete){	
		buffer.append("<a href=\"javascript:deletePackingType("+rowCnt+")\" data-toggle=\"tooltip\" title=\"Remove\" ><span class=\"span-icon glyphicon glyphicon-remove icon-delete\"></span></a>");
	}
	buffer.append("</div>");
	buffer.append("</div>");

	buffer.append("</div>");
	buffer.append("</div>");
	
	buffer.append("<div class=\"row\">");
	buffer.append("<div class=\"col-sm-12\">");
	buffer.append("<div class=\"size_row\" id=\"size_row_"+rowCnt+"\">");
	
	buffer.append("</div>");
	buffer.append("</div>");
	buffer.append("</div>");
}
	
	public void doGetPackingRow(HttpServletRequest request,HttpServletResponse response){
		try {
//			int rowCnt=Validator.convertToInteger(request.getParameter("ded_row"));
			int costCenterCnt=Validator.convertToInteger(request.getParameter("packing_row_count"));
			
			StringBuffer buffer=new StringBuffer();
			
			getPackingRow(buffer, costCenterCnt,true,true);
			
			
			StringBuffer outputBuffer=new StringBuffer();
			outputBuffer.append("<packing_rows>");
			outputBuffer.append("<packing_row>");
			outputBuffer.append("<data>");
			outputBuffer.append(StringEscapeUtils.escapeXml(buffer.toString()));
			outputBuffer.append("</data>");
			outputBuffer.append("</packing_row>");
			outputBuffer.append("</packing_rows>");
			response.setContentType("text/xml");
			response.getWriter().write(outputBuffer.toString());
			buffer=null;
			outputBuffer=null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doGetSizeRangeSize(HttpServletRequest request,HttpServletResponse response){
		try {
			
			TPCSUser ui=new UserInfo().getUserInfo(request);
			int rowId=Validator.convertToInteger(request.getParameter("packing_row_id"));
			int sizeRangeId=Validator.convertToInteger(request.getParameter("size_range_id"));
			
			BuyerOrder objBO=new BuyerOrder();
			
			BuyerOrderDetail detail=new BuyerOrderDetail();
			detail.setBoDetId(rowId);
			detail.setSizeRangeId(sizeRangeId);
			objBO.setDetail(detail);
			detail=null;
			
			objBO=boMan.getSizeRangeSizeGrid(ui,objBO);
			
			formPackingSizeData(objBO.getDetail());
			
			StringBuffer resultXML=new StringBuffer();
			resultXML.append("<size_range_size>");
			resultXML.append("<size>");
			resultXML.append("<size_grid>");
			resultXML.append(StringEscapeUtils.escapeXml(objBO.getDetail().getSizeData()));
			resultXML.append("</size_grid>");
			resultXML.append("</size>");
			
			resultXML.append("</size_range_size>");
			
//			System.out.println("resultXML :"+resultXML.toString());
			
			response.setContentType("text/xml");
			response.getWriter().write(resultXML.toString()); 
			
			ui=null;
			objBO=null;
			resultXML=null;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void formPackingSizeData(BuyerOrderDetail detail){
		
		StringBuffer parentBuffer=new StringBuffer();
		List<Size> sizeList=detail.getSizeList();
		
		
		StringBuffer headerBuffer=new StringBuffer();
		StringBuffer qtyBuffer=new StringBuffer();
		parentBuffer.append("<div class=\"col-sm-12\">");
		parentBuffer.append("<table class=\"table-bordered table-condensed table-hover size-grid size-table\" id=\"grid_table\">");
		
		headerBuffer.append("<tr class=\"header-det\">");
		headerBuffer.append("<th>");
		headerBuffer.append("Size");
		headerBuffer.append("</th>");
		
		qtyBuffer.append("<tr>");
		qtyBuffer.append("<td><div class=\"form-group\">Qty</div>");
		qtyBuffer.append("<input type=\"hidden\" id=\"packing_size_ids_"+detail.getBoDetId()+"\" name=\"packing_size_ids_"+detail.getBoDetId()+"\" value=\""+detail.getSizeIdList().toString().replace("[", "").replaceAll("]", "").replace(" ", "")+"\" />"); 
		qtyBuffer.append("<input type=\"hidden\" id=\"packing_size_tot_qty_"+detail.getBoDetId()+"\" name=\"packing_size_tot_qty_"+detail.getBoDetId()+"\" value=\""+detail.getSizeTotalQty()+"\" />");
		qtyBuffer.append("</td>");
		
		
		for(Size size:sizeList){
			headerBuffer.append("<th>");
			headerBuffer.append(size.getSizeName());
			headerBuffer.append("</th>");
			
			qtyBuffer.append("<td>");
			qtyBuffer.append("<div class=\"form-group\">"
					+ "<input type=\"text\" class=\"form-control\" id=\"packing_size_qty_"+detail.getBoDetId()+"_"+size.getSizeId()+"\" name=\"packing_size_qty_"+detail.getBoDetId()+"_"+size.getSizeId()+"\" value=\""+size.getQty()+"\" onkeyup=\"calculateTotalPackingSizeQty("+detail.getBoDetId()+")\" maxlength=\"8\" placeholder=\"Enter qty\" />"
					+ "</div>");
			qtyBuffer.append("</td>");
			
		}
		qtyBuffer.append("</tr>");
		headerBuffer.append("</tr>");
		
		parentBuffer.append("<thead>"+headerBuffer.toString()+"</thead>");
		parentBuffer.append("<tbody>"+qtyBuffer.toString()+"</tbody>");
		headerBuffer=null;
		qtyBuffer=null;
		parentBuffer.append("</table>");
		parentBuffer.append("</div>");
		
		detail.setSizeData(parentBuffer.toString());
		parentBuffer=null;
		sizeList=null;
	}
	
	private BuyerOrderManager  boMan= new BuyerOrderManager();
	private DynamicFieldManager  dynamicFieldManager = new DynamicFieldManager();
	private CommonUtilManager  comUtilMan= new CommonUtilManager();
	
	private List<DynamicFieldEvents> dynamicFormEvents = null;
	private List<DynamicFieldEvents> dynamicHeaderFieldEvents = null;
	private List<DynamicFieldEvents> dynamicDetailFieldEvents = null;
	private List<DynamicFieldEvents> dynamicHeaderpickListOptions = null;
	private List<DynamicFieldEvents> dynamicDetailpickListOptions = null;
	private DynamicFormStructure dynamicFormStructureBOHeader = null;
	private DynamicFormStructure  dynamicFormStructureBODetail=null;
	private List<DynamicFields> dynamicFieldsListBOHeader = null;
	private List<DynamicFields> dynamicFieldsListBODetail=null;
	
	private UserRights rights = null;
	private UserRightsManager objRight = new UserRightsManager();

	private int screenId=6;
	private int headerTableId=6;
	private int detailTableId=7;
}
