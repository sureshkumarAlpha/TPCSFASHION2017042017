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

import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.CommonUtil;
import com.alpha.tpcsfashion.beans.DynamicFieldEvents;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.DynamicFormStructure;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.PurchaseOrder;
import com.alpha.tpcsfashion.beans.PurchaseOrderDetail;
import com.alpha.tpcsfashion.beans.PurchaseOrderHeader;
import com.alpha.tpcsfashion.beans.Size;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.gridmaker.PurchaseOrderGridMaker;
import com.alpha.tpcsfashion.model.ColumnPreferenceManager;
import com.alpha.tpcsfashion.model.CommonUtilManager;
import com.alpha.tpcsfashion.model.DynamicFieldManager;
import com.alpha.tpcsfashion.model.PurchaseOrderManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.ExportToPdfTool;
import com.alpha.tpcsfashion.util.ResourceBundleProvider;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.UserInfo;
import com.alpha.tpcsfashion.util.Validator;

public class PurchaseOrderServlet 
{
	public void doDisplayPurchaseOrder(HttpServletRequest request,HttpServletResponse response){
		try {
			
			String ipAddress = request.getHeader("X-FORWARDED-FOR");  
			   if (ipAddress == null) {  
			       ipAddress = request.getRemoteAddr();
			   }
			System.out.println("Client IP :"+ipAddress);

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
			session.setAttribute("seletedScreenId", screenId);
			session.setAttribute("screenId", screenId);
			request.setAttribute("subdocument_id", SubdocumentId.PURCHASE_ORDER);
			request.setAttribute("invoke_servlet", "PurchaseOrderServlet");
			request.setAttribute("invoke_method", "doDisplayPurchaseOrder");		
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_PURCHASE_ORDER, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void organizColumns(HttpServletRequest request,String strSearchQuery){
		try{

		
			TPCSUser userInfo=new UserInfo().getUserInfo(request);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);

			request.setAttribute("subdocument_id", SubdocumentId.PURCHASE_ORDER);
			ExportToPdfTool.setRoundOff(userInfo);
			ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
			ColumnPreference DataList=cpm.getVisibleColumnNames(SubdocumentId.PURCHASE_ORDER, userInfo);

			Map<String,String> visColMap=cpm.getVisibleColumnNamesMap(SubdocumentId.PURCHASE_ORDER,userInfo.getUserId(), userInfo);
			List<String> listSelectedColumns=DataList.getVisibleColumns(); 
			List<String> orderBy=DataList.getOrderBy(); 
			
			String strPageNo = request.getParameter("pageno");
			int ipageno =Validator.convertToInteger(strPageNo);
			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			//String strPageNo = request.getParameter("pageno");
			int pageCount= poMan.getTotalPages(userInfo,pc, strSearchQuery);

			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);
			doGetUserRights(request);
			rights=(UserRights) request.getSession().getAttribute("po_rights");

			//			BuyerOrderGridMaker objUtil=new BuyerOrderGridMaker();
			//			String quotationAttachPath=new UserInfo().getQuotationAttachPath(request);
			//			List<Map<String,String>> orders =boMan.getAllBuyerOrderOnColumnMapping(pc,userInfo,strSearchQuery,orderBy,3,quotationAttachPath);
			//			String Grid = objUtil.formBuyerOrderGrid(visColMap,listSelectedColumns,orders,ipageno,bundle,100,sqRights,pc);
			//			request.setAttribute("bo_grid", Grid); 		      	  


			PurchaseOrder pr=new PurchaseOrder();
			pr.setSubdocumentId(SubdocumentId.PURCHASE_ORDER);
			pr.setAttachPath(new UserInfo().getClientPath(request));
			pr.setSearchCriteria(strSearchQuery);
			pr.setOrderByList(orderBy);
			pr =poMan.getAllPurchaseOrderOnColumnMapping(pc,userInfo,pr);
			pr.setVisColMap(visColMap);
			pr.setListSelectedColumns(listSelectedColumns);

			String Grid = new PurchaseOrderGridMaker().formPurchaseOrderGrid(pr,bundle,rights,pc);
			request.setAttribute("po_grid", Grid);

			request.setAttribute("seperator", seperator);
			
			DataList=null;
			visColMap=null;
			//Grid=null;
			userInfo=null;
			cpm=null;
			listSelectedColumns=null;
			orderBy=null;
			pc=null;
			//			sqRights=null;
			//			soRights=null;
			//			objUtil=null;

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void doDisplayPurchaseOrderAfterColumnOrganized(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{  

			HttpSession session=request.getSession();  
			TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
			String strTotalColumns = request.getParameter("selected_columns_data");
			ColumnPreferenceManager cpm=new ColumnPreferenceManager();    
			boolean bFlag = cpm.updateColumnPreferences(strTotalColumns,SubdocumentId.PURCHASE_ORDER,userInfo);

			doDisplayPurchaseOrder(request, response);
			session=null;
			userInfo=null;
			strTotalColumns=null;
			cpm=null;
			
		}
		catch(Exception e){ 
			e.printStackTrace();      
		}
	}

	public void doGetUserRights(HttpServletRequest request){
		TPCSUser userInfo=new UserInfo().getUserInfo(request);
		HttpSession session=request.getSession();

		if(request.getSession().getAttribute("po_rights")!=null){
			rights =(UserRights)request.getSession().getAttribute("po_rights");
		}
		else{
			rights =objRight.getUserRights(SubdocumentId.PURCHASE_ORDER, userInfo);
			session.setAttribute("po_rights",rights);		
		}

	}
	public void getAndSetAttributes(HttpServletRequest request){
		request.setAttribute("page_size", PageConfig.iPageSize);
		//request.setAttribute("bo_buyer_po_no",request.getParameter("bo_buyer_po_no"));
		//	request.setAttribute("po_customer_plan_no",request.getParameter("bo_customer_plan_no"));

		request.setAttribute("po_po_no",request.getParameter("po_po_no"));
		request.setAttribute("po_buyer_id",request.getParameter("po_buyer_id"));
		request.setAttribute("po_buyer_name",request.getParameter("po_buyer_name"));
		request.setAttribute("po_from_date",request.getParameter("po_from_date"));
		request.setAttribute("po_to_date",request.getParameter("po_to_date"));
		
		request.setAttribute("po_purchase_type",request.getParameter("po_purchase_type"));
		request.setAttribute("po_other_ref",request.getParameter("po_other_ref"));
		request.setAttribute("po_season_id",request.getParameter("po_season_id"));
		request.setAttribute("po_season_name",request.getParameter("po_season_name"));
		request.setAttribute("po_hremark",request.getParameter("po_hremark"));
		request.setAttribute("po_payment_terms",request.getParameter("po_payment_terms"));
		request.setAttribute("po_delivery_terms",request.getParameter("po_delivery_terms"));
		request.setAttribute("po_ship_to_address",request.getParameter("po_ship_to_address"));
		request.setAttribute("po_internal_memo",request.getParameter("po_internal_memo"));
		
	}
	private String getDefaultCriteria(HttpServletRequest request){
		StringBuffer  strQuery=new StringBuffer();
		String quote_type=request.getParameter("order_type");
		if(quote_type== null || quote_type.equalsIgnoreCase("-1")){
			strQuery.append(" and isnull(po.cancel_tag,0)<>1 ");
		}

		return strQuery.toString();
	}

	public String getSearchCriteria(HttpServletRequest request){

		StringBuffer  strQuery=new StringBuffer();
		String poNo= request.getParameter("po_po_no");
		int partyId=Validator.convertToInteger(request.getParameter("po_buyer_id"));
		String poFromDate= request.getParameter("po_from_date");
		String poToDate= request.getParameter("po_to_date");
		String purchaseType= request.getParameter("po_purchase_type");
		String otherRef= request.getParameter("po_other_ref");
		int seasonId= Validator.convertToInteger(request.getParameter("po_season_id"));
		String remark= request.getParameter("po_hremark");
		String paymentTerms= request.getParameter("po_payment_terms");
		String deliveryTerms= request.getParameter("po_delivery_terms");
		String shipToAddress= request.getParameter("po_ship_to_address");
		String internalMemo= request.getParameter("po_internal_memo");

		//For DynamicFields
		String dynField1 = request.getParameter("dynamic_field_1");
		String dynField2 = request.getParameter("dynamic_field_2");
		String dynField3 = request.getParameter("dynamic_field_3");

		String dynFieldVal1 =request.getParameter("dynamic_field_val_1");
		String dynFieldVal2 =request.getParameter("dynamic_field_val_2");
		String dynFieldVal3 =request.getParameter("dynamic_field_val_3");

		String quoteType=request.getParameter("order_type");

		if(quoteType== null || quoteType.equalsIgnoreCase("-1")){
			strQuery.append(" and isnull(po.cancel_tag,0)<>1 ");
		}
		else if(quoteType!=null && quoteType.equalsIgnoreCase("Canceled")){
			strQuery.append(" and isnull(po.cancel_tag,0)=1 ");
		}
		else if(quoteType!=null && quoteType.equalsIgnoreCase("Closed")){
			strQuery.append(" and isnull(po.close_tag,0)=1");
		}

		if(dynField1!=null && !dynField1.equals("-1")){

			String[] arDynField=dynField1.split(seperator);

			if(Validator.convertToInteger(arDynField[1])==10){

				if(dynFieldVal1!=null && dynFieldVal1.trim().length()>0){
					strQuery.append(" and podf."+arDynField[0]+" like '%"+dynFieldVal1.replace("'","''")+"%'  ");
				}
			}
			else if(Validator.convertToInteger(arDynField[1])==11){

				if(dynFieldVal1!=null && dynFieldVal1.trim().length()>0){
					strQuery.append(" and poddf."+arDynField[0]+" like '%"+dynFieldVal1.replace("'","''")+"%'  ");
				}
			}
			arDynField=null;
		}
		
		if(dynField2!=null && !dynField2.equals("-1")){

			String[] arDynField=dynField2.split(seperator);

			if(Validator.convertToInteger(arDynField[1])==10){

				if(dynFieldVal2!=null && dynFieldVal2.trim().length()>0){
					strQuery.append(" and podf."+arDynField[0]+" like '%"+dynFieldVal2.replace("'","''")+"%'  ");
				}
			}
			else if(Validator.convertToInteger(arDynField[1])==11){

				if(dynFieldVal2!=null && dynFieldVal2.trim().length()>0){
					strQuery.append(" and poddf."+arDynField[0]+" like '%"+dynFieldVal2.replace("'","''")+"%'  ");
				}
			}
			arDynField=null;
		}
		
		if(dynField3!=null && !dynField3.equals("-1")){

			String[] arDynField=dynField3.split(seperator);

			if(Validator.convertToInteger(arDynField[1])==10){

				if(dynFieldVal3!=null && dynFieldVal3.trim().length()>0){
					strQuery.append(" and podf."+arDynField[0]+" like '%"+dynFieldVal3.replace("'","''")+"%'  ");
				}
			}
			else if(Validator.convertToInteger(arDynField[1])==11){

				if(dynFieldVal3!=null && dynFieldVal3.trim().length()>0){
					strQuery.append(" and poddf."+arDynField[0]+" like '%"+dynFieldVal3.replace("'","''")+"%'  ");
				}
			}
			arDynField=null;
		}
		
		if(poFromDate!=null && poFromDate.length()>0){
			strQuery.append(" and po.po_date>=convert(datetime,'"+poFromDate+"',105) "); 
		}
		if(poToDate!=null && poToDate.length()>0){
			strQuery.append(" and  po.po_date<=convert(datetime,'"+poToDate+"',105) ");
		}
		if(poNo!=null && !poNo.isEmpty()){
			strQuery.append(" and po.po_prefix+convert(nvarchar,po.po_no) like '%"+poNo.replace("'","''")+"'% ");
		}
		if(purchaseType!=null && !purchaseType.isEmpty()){
			strQuery.append(" and po.purchase_type like '%"+purchaseType.replace("'","''")+"'% ");
		}
		if(otherRef!=null && !otherRef.isEmpty()){
			strQuery.append(" and po.other_ref like '%"+otherRef.replace("'","''")+"'% ");
		}
		if(remark!=null && !remark.isEmpty()){
			strQuery.append(" and po.remark like '%"+remark.replace("'","''")+"'% ");
		}
		if(paymentTerms!=null && !paymentTerms.isEmpty()){
			strQuery.append(" and pom.payment_terms like '%"+paymentTerms.replace("'","''")+"'% ");
		}
		if(deliveryTerms!=null && !deliveryTerms.isEmpty()){
			strQuery.append(" and pom.delivery_terms like '%"+deliveryTerms.replace("'","''")+"'% ");
		}
		if(shipToAddress!=null && !shipToAddress.isEmpty()){
			strQuery.append(" and pom.ship_to_address like '%"+shipToAddress.replace("'","''")+"'% ");
		}
		if(internalMemo!=null && !internalMemo.isEmpty()){
			strQuery.append(" and pom.internal_memo like '%"+internalMemo.replace("'","''")+"'% ");
		}
		if(seasonId>0){
			strQuery.append(" and pom.season_id ="+seasonId);
		}

		if(partyId!=0){
			strQuery.append(" and p.party_id="+partyId);
		}
		
		
		poNo=null;
		poFromDate= null;
		poToDate= null;
		purchaseType= null;
		otherRef= null;
		remark=null;
		paymentTerms=null;
		deliveryTerms=null;
		shipToAddress=null;
		internalMemo=null;

		dynField1 =null;
		dynField2 = null;
		dynField3 = null;

		dynFieldVal1 =null;
		dynFieldVal2 =null;
		dynFieldVal3 =null;

		quoteType=null;

		return strQuery.toString();

	}	


	public void doAddPurchaseOrder(HttpServletRequest request,HttpServletResponse response){

		try {
			Map<String,String> fileMap=new LinkedHashMap<String, String>();
			String headerMode = getHeaderMode(fileMap,request);
			PurchaseOrder objPO=new PurchaseOrder();
			//objPO.setHeaderMode(headerMode);
			objPO.setHeaderMode(headerMode);
			PurchaseOrderHeader poHeader=new PurchaseOrderHeader();

			int poId=Validator.convertToInteger(request.getParameter("po_id"));



			poHeader.setPoId(poId);

			if(objPO.getHeaderMode().equalsIgnoreCase("n")){
				setNewDefaultData(request,objPO);
			}

			objPO.setHeader(poHeader);

			doAddEditPurchaseOrder(request,response,objPO);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doAddEditPurchaseOrder(HttpServletRequest request,HttpServletResponse response, PurchaseOrder objPO){
		try {

			objPO.setScreenId(screenId);
			objPO.setHeaderTableId(headerTableId);
			objPO.setDetailTableId(detailTableId);

			objPO.setClientPath(new UserInfo().getClientPath(request));

			request.setAttribute("purchase_order", objPO);

			TPCSUser ui=new UserInfo().getUserInfo(request);

			ExportToPdfTool.setRoundOff(ui);

			dynamicFieldsListPOHeader = dynamicFieldManager.getDynamicFields(screenId, headerTableId,ui);

			request.setAttribute("dynamicFieldsLisPOHeader", dynamicFieldsListPOHeader);
			request.getSession().setAttribute("dynamicFieldsListPOHeader", dynamicFieldsListPOHeader);

			dynamicFieldsListPODetail = dynamicFieldManager.getDynamicFields(screenId, detailTableId, ui);
			request.setAttribute("dynamicFieldsListPODetail", dynamicFieldsListPODetail);
			request.getSession().setAttribute("dynamicFieldsListPODetail", dynamicFieldsListPODetail);

			dynamicFormStructurePOHeader = dynamicFieldManager.getDynamicFormStructure(screenId, ui);
			request.setAttribute("dynamicFormStructurePOHeader", dynamicFormStructurePOHeader);

			dynamicFormStructurePODetail = dynamicFieldManager.getDynamicFormStructure(screenId, ui);
			request.setAttribute("dynamicFormStructurePODetail", dynamicFormStructurePODetail);

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


			if(objPO.getHeaderMode().equals("a") || objPO.getHeaderMode().equals("e")){

				if(objPO.getHeaderMode().equals("a")){
					//	boolean isAmend=poMan.createAmendment(ui,objPO);
				}

				objPO=poMan.getPurchaseOrderInfo(objPO,ui);
				
				getRoundOff(objPO.getHeader(),ui);
				request.setAttribute("header_info", objPO.getHeader());
				request.setAttribute("detList", objPO.getPoDetList());
				request.setAttribute("detMapList", objPO.getPoDetMap());
				request.setAttribute("attached_files", objPO.getHeader().getAttachFiles());
				request.setAttribute("poAttachPath", objPO.getClientPath());

				dynamicFieldsListPOHeader = objPO.getPoDynList();
				request.setAttribute("dynamicFieldsListPOHeader", dynamicFieldsListPOHeader);
				request.setAttribute("det_id_list",objPO.getDetIdList());
				request.setAttribute("det_ids",objPO.getDetIdList().toString().replace("[", "").replace("]", "").replace(" ",""));

			}
			else{
				objPO=poMan.getPurchaseOrderDefaultData(ui,objPO);
				//String rowIds="0,1,2";
				//int rowCount=3;
				//objPO.getHeader().setPackingRowCount(rowCount);
				//List<String> rowIdList=Arrays.asList(rowIds.split(","));

				//addPackingScheduleEmptyRow(objPO.getHeader());


				//objPO.getHeader().setPackingRowIds(rowIds);
				//objPO.getHeader().setPackingRowCount(rowCount);
				//objPO.getHeader().setPackingRowIdList(rowIdList);

				getRoundOff(objPO.getHeader(),ui);
				request.setAttribute("header_info",objPO.getHeader());
			}
			request.setAttribute("view_mode", request.getParameter("view_mode"));

			dynamicFieldsListPOHeader=null;
			dynamicFieldsListPODetail=null;
			dynamicFormStructurePOHeader=null;
			dynamicFormStructurePODetail=null;
			dynamicFormEvents=null;
			dynamicHeaderFieldEvents=null;
			dynamicDetailFieldEvents=null;
			dynamicHeaderpickListOptions=null;
			dynamicDetailpickListOptions=null;

			ui=null;

			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_PURCHASE_ORDER, request,response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private PurchaseOrderHeader getRoundOff(PurchaseOrderHeader header,TPCSUser userInfo) {
		Map<String,String> locConfigMap=userInfo.getLocatonConfigMap();
		
		String qtyRoundOff="";
		String rateRoundOff="";
		String valRoundOff="";
		
		qtyRoundOff=locConfigMap.get("RoundoffQty");
		rateRoundOff=locConfigMap.get("RoundoffRate");
		valRoundOff=locConfigMap.get("RoundoffValue");
		
		int roundOffQty=Validator.convertToInteger(qtyRoundOff);
		int roundOffRate=Validator.convertToInteger(rateRoundOff);
		int roundOffValue=Validator.convertToInteger(valRoundOff);
		
		header.setRoundOffQty(2);
		if(roundOffQty>=0 && roundOffQty<=5){
			header.setRoundOffQty(roundOffQty);
		}
		
		header.setRoundOffRate(2);
		if(roundOffRate>=0 && roundOffQty<=5){
			header.setRoundOffRate(roundOffRate);
		}
		
		header.setRoundOffValue(2);
		if(roundOffValue>=0 && roundOffQty<=5){
			header.setRoundOffValue(roundOffValue);
		}
		
		return header;
	}
	public void doSavePurchaseOrder(HttpServletRequest request, HttpServletResponse response, FileImport objBean){	

		try{
			HttpSession session=request.getSession();
			TPCSUser ui=new UserInfo().getUserInfo(request);
			//			String clientId = (String)request.getSession().getAttribute("client_id");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);

			//			String[] userid = fileMap.get("selected_users").trim().split(",");
			//			String[] userremarks = fileMap.get("selected_userremarks").split(",");

			//objSQ.setSaveFollower(fileMap.get("remember_followers")!=null?fileMap.get("remember_followers"):"null");

			PurchaseOrder objPO=new PurchaseOrder();

			objPO=getRequestData(request,ui, objPO, objBean);

			objPO= poMan.savePurchaseOrder(ui,objPO,objBean);
			int poId=objPO.getHeader().getPoId();

			if(poId==-1){

				request.setAttribute("error_message",bundle.getString("Purchase.PurchaseOrderAlreadyExists"));

				objPO.getHeader().setPoId(0);
				doAddEditPurchaseOrder(request,response,objPO);
			}
			else{
				if(poId>0){
					if(objPO.getHeaderMode().equals("n")){
						objPO.setHeaderMode("e");	 
					}
					request.setAttribute("success_message",bundle.getString("Purchase.PurchaseOrderInserted")); 
				}
				else{
					request.setAttribute("error_message",bundle.getString("Purchase.PurchaseOrderNotInserted")); 
				}
				if(objPO.getDetailMode().equals("e")){
					objPO.setDetailMode("n"); 
				}

				if(objPO.getSaveType().equals("3")){//Save Type Save & New

					objPO=new PurchaseOrder();
					PurchaseOrderHeader poHeader=new PurchaseOrderHeader();
					objPO.setHeaderMode("n");
					objPO.setDetailMode("n");
					objPO.setHeader(poHeader);
					setNewDefaultData(request,objPO);
					doAddEditPurchaseOrder(request,response,objPO);
				}
				else  if(objPO.getSaveType().equals("4")){//Save Type Save & Close
					doDisplayPurchaseOrder(request,response) ;
				}
				else {

					if(objPO.getSaveType().equals("2")){//button type save&send

						int type=1;
						if(objPO.getEditMode().equalsIgnoreCase("e")){
							type=2;
						}

						if(!objPO.getHeader().isSendLater()){

							//							List<String> boDetail=boMan.getEmailBODetails(boId,ui);
							//							List<String> toIds=boMan.getBOFollowers(boId,ui);
							//							String loginName=boMan.getLoggedInUser(ui);
							//							String strContactImage=boMan.getContactImage(ui);
							//							if(!objBO.getHeader().getEmailSentTo().isEmpty() && !toIds.contains(objBO.getHeader().getEmailSentTo())){
							//								toIds.add(objBO.getHeader().getEmailSentTo());
							//							}
							//							
							//							MailInfo mailInfo=new MailInfo();
							////							MailInfo mailInfo=generateSalesQuotationPdf(request, quoteId);
							//							List<MailInfo> liMailInfo=new ArrayList<MailInfo>();
							//			            	liMailInfo.add(mailInfo);
							//			            	mailInfo.setLiMailInfo(liMailInfo);
							//			            	mailInfo.setLoginName(loginName);
							//							boMan.updateMailTag(boId,ui);
							//							boMan.sendNewBOEmailAlert(boDetail,ui.getUserId(),toIds,loginName,(String)session.getAttribute("path"),strContactImage,clientId,type,mailInfo); 
							//						
							//							boDetails=null;
							//							toIds=null;
						}

					}

					doAddEditPurchaseOrder(request,response,objPO);

				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}

	}
	public PurchaseOrder getRequestData(HttpServletRequest request,TPCSUser ui,PurchaseOrder objPO,FileImport objBean){
		String realPath=(String) request.getSession().getAttribute("path");
		String contextPath=realPath+new UserInfo().getClientPath(request);
		objBean.setFolderPath(contextPath);
		Map<String,String> fileMap=new LinkedHashMap<String, String>();

		dynamicFieldsListPOHeader = (List<DynamicFields>) request.getSession().getAttribute("dynamicFieldsListPOHeader");
		dynamicFieldsListPODetail= (List<DynamicFields>) request.getSession().getAttribute("dynamicFieldsListPODetail");


		List<String> chkHead=new ArrayList<String>();
		List<String> chkDetail=new ArrayList<String>();

		for(DynamicFields dynamicFields: dynamicFieldsListPOHeader) {
			//Checking the condition - if dynamic field i.e., isFixed  =  false
			if(!dynamicFields.isFixed()) {
				chkHead.add(dynamicFields.getPageFieldName());
			}
		}
		int quoteDetId=Validator.convertToInteger(fileMap.get("po_det_id"));
		String detId="_"+quoteDetId;

		for(DynamicFields dynamicFields: dynamicFieldsListPODetail) {
			//Checking the condition - if dynamic field i.e., isFixed  =  false
			if(!dynamicFields.isFixed()) {
				chkDetail.add(dynamicFields.getPageFieldName()+detId);
			}
		}

		// String detId="_"+fileMap.get("quote_det_id");
		FileItem item =null;
		Iterator in = objBean.getFileItems().iterator();
		while ( in.hasNext () ){ 
			item = (FileItem)in.next();

			if ( item.isFormField() ){
				fileMap.put(item.getFieldName(), item.getString());

				if(chkHead.contains(item.getFieldName())){

					for(DynamicFields dynamicFields: dynamicFieldsListPOHeader) {
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

					for(DynamicFields dynamicFields: dynamicFieldsListPODetail) {
						//Checking the condition - if dynamic field i.e., isFixed  =  false
						if(!dynamicFields.isFixed()) {

							String PageFieldName=dynamicFields.getPageFieldName()+detId;

							if (PageFieldName.equals(item.getFieldName())) {
								dynamicFields.setFieldValue((String) StringEscapeUtils.escapeXml(item.getString()));//request.getParameter(dynamicFields.getPageFieldName()));
							}
							//								dynamicFields.setFieldValue((String) StringEscapeUtils.escapeXml(fileMap.get(dynamicFields.getPageFieldName())));
						}
					}
				}

			}
		}
		//calling xml for dynamic fields
		String sqlxml = generateXMLforDynamicFields(dynamicFieldsListPOHeader,dynamicFieldsListPODetail);

		objPO=setPurchaseOrderAttributes(fileMap,request,ui,objPO);

		//added for dynamic fields 
		objPO.setSqlxmlDynamicFields(sqlxml);
		String editedIds=fileMap.get("edited_ids");
		String dynEditedIds=fileMap.get("dynedited_ids");
		
		Map<String,String> editDataMap= new HashMap<String, String>();
		Map<String,String> editDynamicDataMap= new HashMap<String, String>();

		if(objPO.getEditMode().equalsIgnoreCase("e")){

			String streditedIds[] = editedIds.split(",");

			
			for(int i=0; i < streditedIds.length; i++){
				
				
				editDataMap.put(streditedIds[i], fileMap.get(streditedIds[i]));

			}

			String strDyneditedIds[] = dynEditedIds.split(",");
			for(int i=0; i < strDyneditedIds.length; i++){

				editDynamicDataMap.put(strDyneditedIds[i], fileMap.get(strDyneditedIds[i]));

			}

			objPO.setEditDataMap(editDataMap);
			objPO.setEditDynamicDataMap(editDynamicDataMap);

		}
		return objPO;
	}


	private String generateXMLforDynamicFields( List<DynamicFields> dynamicFieldsListPOHeader, List<DynamicFields> dynamicFieldsListPODetail) {

		String strXML = "<screens> ";
		String formPO = "<screen name=\"po\">  <columns> ";
		String formPODetail = "<screen name=\"po_detail\">  <columns> ";

		if(dynamicFieldsListPOHeader!=null) {
			for (DynamicFields dynamicFields : dynamicFieldsListPOHeader) {
				if (!dynamicFields.isFixed()) {
					String column = " <column type=\"dynamic\"> <name>" + dynamicFields.getDbFieldName() + "</name> <value>" + dynamicFields.getFieldValue() + "</value> <datatype>" + dynamicFields.getDataTypeName() + "</datatype> </column> ";
					formPO = formPO.concat(column);
				} 
			}
		}

		formPO = formPO.concat(" </columns> </screen> ");
		formPO = formPO.concat(formPODetail);

		if(dynamicFieldsListPODetail!=null) {
			for (DynamicFields dynamicFields : dynamicFieldsListPODetail) {
				if (!dynamicFields.isFixed()) {	
					String column = " <column type=\"dynamic\"> <name>" + dynamicFields.getDbFieldName() + "</name> <value>" +dynamicFields.getFieldValue()+ "</value> <datatype>" + dynamicFields.getDataTypeName() + "</datatype> </column> ";
					formPO = formPO.concat(column);
				} 
			}
		}

		formPO = formPO.concat(" </columns> </screen> ");
		formPO = formPO.concat(" </screens> ");
		strXML = strXML.concat(formPO);
		return strXML;
	}
	private PurchaseOrder setPurchaseOrderAttributes(Map<String,String> fileMap,HttpServletRequest request,TPCSUser userInfo,PurchaseOrder objPO) {

		PurchaseOrderHeader	header= setHeaderAttributes(fileMap,request,userInfo);
		PurchaseOrderDetail detail= setDetailAttributes(fileMap,request,userInfo);

		objPO.setSaveType(fileMap.get("save_type"));
		objPO.setEditMode(fileMap.get("edited_mode"));

		objPO.setHeader(header);
		objPO.setDetail(detail);
		objPO.setHeaderMode(fileMap.get("header_mode"));
		objPO.setDetailMode(fileMap.get("new_det_mode"));

		header=null;
		detail=null;

		return objPO;
	}
	private PurchaseOrderHeader setHeaderAttributes(Map<String,String> fileMap,HttpServletRequest request,TPCSUser userInfo) {

		PurchaseOrderHeader header= new PurchaseOrderHeader();
		header.setPoId(Validator.convertToInteger(fileMap.get("po_id")));
		header.setPoNo(fileMap.get("po_no"));
		header.setPrefix(fileMap.get("po_prefix"));
		header.setPoPrefix(fileMap.get("po_prefix")+fileMap.get("po_no"));
		header.setPoDate(fileMap.get("po_date"));
		header.setPartyName(fileMap.get("buyer_name"));
		header.setPartyId(Validator.convertToInteger(fileMap.get("party_id")));
		header.setCurrencyName(fileMap.get("currency_name"));
		header.setCurrencyId(Validator.convertToInteger(fileMap.get("currency_id")));
		header.setExRate(fileMap.get("ex_rate"));
		header.setSeasonId(Validator.convertToInteger(fileMap.get("season_id")));
		header.setPoValidTill(fileMap.get("po_valid_till"));
		header.setRemark(fileMap.get("hremark"));
		header.setPaymentTerms(fileMap.get("payment_terms"));
		header.setDeliveryTerms(fileMap.get("delivery_terms"));
		header.setShipToAddress(fileMap.get("ship_to_address"));
		header.setInternalMemo(fileMap.get("internal_memo"));
		header.setOtherRef(fileMap.get("other_ref"));
		header.setPurchaseType(fileMap.get("purchase_type"));
		
//		header.setDeliverTo(fileMap.get("delivery_to"));
		
		getTCInfo(fileMap,header);
		return header;

	}
	private PurchaseOrderDetail setDetailAttributes(Map<String,String> fileMap,HttpServletRequest request,TPCSUser userInfo) {
		PurchaseOrderDetail detail= new PurchaseOrderDetail();

		String tmpDetId="_0";
		detail.setPoDetId(Validator.convertToInteger(fileMap.get("po_det_id")));
		detail.setSlNo(Validator.convertToInteger(fileMap.get("sl_no"+tmpDetId)));
		detail.setItemName(fileMap.get("item_name"+tmpDetId));
		detail.setItemId(Validator.convertToInteger(fileMap.get("item_id"+tmpDetId)));
		detail.setVariantName(fileMap.get("colour_name"+tmpDetId));
		detail.setVariantId(Validator.convertToInteger(fileMap.get("variant_id"+tmpDetId)));
		detail.setSizeRangeName(fileMap.get("size_range"+tmpDetId));
		detail.setSizeRangeId(Validator.convertToInteger(fileMap.get("size_range_id"+tmpDetId)));
		detail.setOtherSpec(fileMap.get("other_spec"+tmpDetId));
		detail.setUom(fileMap.get("uom"+tmpDetId));
		detail.setQty(fileMap.get("qty"+tmpDetId));
		detail.setQtyUom(fileMap.get("qty_uom"+tmpDetId));
		detail.setPriceFcy(Validator.convertToFloat(fileMap.get("price_fcy"+tmpDetId))>0?fileMap.get("price_fcy"+tmpDetId):"0");
		detail.setTaxGroupId(Validator.convertToInteger(fileMap.get("tax_group_id"+tmpDetId)));
		
		Map<String,String> locConfigMap=userInfo.getLocatonConfigMap();

		detail.setDiscountPercent(Validator.convertToFloat(fileMap.get("discount_percent"+tmpDetId))>0?fileMap.get("discount_percent"+tmpDetId):"0");
		if(locConfigMap.get("PODiscount")!=null && locConfigMap.get("PODiscount").equalsIgnoreCase("No")){
			detail.setDiscountPercent(Validator.convertToFloat(fileMap.get("discount_"+tmpDetId))>0?fileMap.get("discount_"+tmpDetId):"0");
		}
		
		
		detail.setRemark1(fileMap.get("remark1"+tmpDetId));
		detail.setRemark2(fileMap.get("remark2"+tmpDetId));
		detail.setRemark3(fileMap.get("remark3"+tmpDetId));
		detail.setRequiredDate(fileMap.get("required_date"+tmpDetId));
		detail.setRemark(fileMap.get("remark"+tmpDetId));
		getDetailSizeData(fileMap, detail);

		return detail;
	}
	private void getTCInfo(Map<String, String> fileMap, PurchaseOrderHeader sih) {
		String tcIds=fileMap.get("po_tc_ids");
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

	private void getDetailSizeData(Map<String, String> fileMap, PurchaseOrderDetail detail){

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

			//			if(!detId.isEmpty()){

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

						sizeBuffer.append("<size_rate>");
						sizeBuffer.append(String.valueOf(Validator.convertToFloat(fileMap.get("size_rate_"+detId+"_"+sizeId))));
						sizeBuffer.append("</size_rate>");

						sizeBuffer.append("</size_detail>");
					}

				}
				//			}

				sizeIds=null;
				arSizeIds=null;
				sizeIdList=null;
			}
		}
		sizeBuffer.append("</size_range_size>");
		editedDetIds=null;
		arEditedDetIds=null;
		editedDetIdList=null;
		hs =null;
		detail.setSizeData(sizeBuffer.toString());

		sizeBuffer=null;
	}
	private String getHeaderMode(Map<String,String> fileMap,HttpServletRequest request){
		String headMode= Validator.trim(request.getParameter("header_mode"));
		return headMode; 	
	}
	private String getDetailMode(Map<String,String> fileMap,HttpServletRequest request) {
		String	detMode=Validator.trim(request.getParameter("det_mode"));
		return detMode; 
	}
	public void setNewDefaultData(HttpServletRequest request, PurchaseOrder objPO){
		request.setAttribute("po_date",TPCSCommonUtil.currenDate("dd-MM-yyyy"));
		request.setAttribute("new_page","yes");
	}
	public void doGetPOSizeRangeSizeGrid(HttpServletRequest request, HttpServletResponse response){
		try {
			int itemId=Validator.convertToInteger(request.getParameter("item_id"));
			int sizeRangeId=Validator.convertToInteger(request.getParameter("size_range_id"));
			int poId=Validator.convertToInteger(request.getParameter("po_id"));
			int poDetId=Validator.convertToInteger(request.getParameter("po_det_id"));

			TPCSUser ui=new UserInfo().getUserInfo(request);
			PurchaseOrder objPO=new PurchaseOrder();
			PurchaseOrderHeader header=new PurchaseOrderHeader();
			PurchaseOrderDetail detail=new PurchaseOrderDetail();



			header.setPoId(poId);
			detail.setPoDetId(poDetId);
			detail.setItemId(itemId);
			detail.setSizeRangeId(sizeRangeId);


			objPO.setHeader(header);
			objPO.setDetail(detail);


			objPO=poMan.getPOSizeRangeSizeGrid(ui,objPO);

			//			StringBuffer parentBuffer=new StringBuffer();
			if(objPO.getDetail().isSizeExists()){

				formSizeData(objPO.getDetail(),ui);

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


			}
			StringBuffer resultXML=new StringBuffer();
			resultXML.append("<size_range_size>");
			resultXML.append("<size>");
			resultXML.append("<size_exists>");
			resultXML.append(objPO.getDetail().isSizeExists()==true?"1":"0");
			resultXML.append("</size_exists>");
			resultXML.append("<size_grid>");
			resultXML.append(StringEscapeUtils.escapeXml(objPO.getDetail().getSizeData()));
			resultXML.append("</size_grid>");
			resultXML.append("</size>");

			resultXML.append("</size_range_size>");

			response.setContentType("text/xml");
			response.getWriter().write(resultXML.toString());

			header=null;
			detail=null;

			resultXML=null;
			objPO=null;
			//			sizeGrid=null;
			ui=null;


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void formSizeData(PurchaseOrderDetail detail,TPCSUser ui){

		Map<String,String> locConfigMap=ui.getLocatonConfigMap();
		int qtyRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffQty"));
		int rateRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffRate"));
		int valRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffValue"));
		
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
		qtyBuffer.append("<input type=\"hidden\" id=\"size_ids_"+detail.getPoDetId()+"\" name=\"size_ids_"+detail.getPoDetId()+"\" value=\""+detail.getSizeIdList().toString().replace("[", "").replaceAll("]", "").replace(" ", "")+"\" />"); 
		qtyBuffer.append("<input type=\"hidden\" id=\"size_tot_qty_"+detail.getPoDetId()+"\" name=\"size_tot_qty_"+detail.getPoDetId()+"\" value=\""+detail.getSizeTotalQty()+"\" />");
		qtyBuffer.append("</td>");


		rateBuffer.append("<tr>");
		rateBuffer.append("<td><div class=\"form-group\">Price</div>");
		qtyBuffer.append("<input type=\"hidden\" id=\"size_tot_price_"+detail.getPoDetId()+"\" name=\"size_tot_price_"+detail.getPoDetId()+"\" value=\""+detail.getSizeTotalPrice()+"\" />");
		rateBuffer.append("</td>");


		for(Size size:sizeList){
			headerBuffer.append("<th>");
			headerBuffer.append(""+size.getSizeName()+"");
			headerBuffer.append("</th>");

			qtyBuffer.append("<td>");
			qtyBuffer.append("<div class=\"form-group\">"
					+ "<input type=\"text\" class=\"form-control\" id=\"size_qty_"+detail.getPoDetId()+"_"+size.getSizeId()+"\" name=\"size_qty_"+detail.getPoDetId()+"_"+size.getSizeId()+"\" value=\""+size.getQty()+"\" onkeyup=\"calculateTotalQty("+detail.getPoDetId()+","+qtyRoundOff+","+rateRoundOff+","+valRoundOff+")\" maxlength=\"8\" placeholder=\"Enter qty\" />"
					+ "</div>");
			qtyBuffer.append("</td>");

			rateBuffer.append("<td>");
			rateBuffer.append("<div class=\"form-group\">"
					+ "<input type=\"text\" class=\"form-control\" id=\"size_rate_"+detail.getPoDetId()+"_"+size.getSizeId()+"\" name=\"size_rate_"+detail.getPoDetId()+"_"+size.getSizeId()+"\" value=\""+size.getRate()+"\"onkeyup=\"calculateTotalQty("+detail.getPoDetId()+","+qtyRoundOff+","+rateRoundOff+","+valRoundOff+")\" maxlength=\"8\" placeholder=\"Enter Rate\" />"
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
	}

	public void doSearchPurchaseOrder(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{  

			String strSearchQuery=getDefaultCriteria(request);
			String reqType=request.getParameter("request_type");

			if("Search".equalsIgnoreCase(reqType)){	         
				strSearchQuery = getSearchCriteria(request);
				getAndSetAttributes(request);
				request.getSession().setAttribute("search_query",strSearchQuery);
			}

			organizColumns(request,strSearchQuery);
			getAndSetAttributes(request);
			request.setAttribute("request_type", reqType);
			TPCSUser ui=new UserInfo().getUserInfo(request);
			request.setAttribute("is_multi_branch_app", ui.getLocatonConfigMap()!=null?ui.getLocatonConfigMap().get("MultiBranch"):"");

			ui=null;
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_PURCHASE_ORDER,request,response);
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}

	public void doDeletePurchaseOrderDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			Map<String,String> fileMap=new LinkedHashMap<String, String>();
			TPCSUser ui=new UserInfo().getUserInfo(request);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);

			PurchaseOrder objPO=new PurchaseOrder();

			objPO.setHeaderMode(getHeaderMode(fileMap,request));
			objPO.setDetailMode(getDetailMode(fileMap,request));

			int poId=Validator.convertToInteger(request.getParameter("po_id"));
			int poDetId=Validator.convertToInteger(request.getParameter("po_det_id"));

			PurchaseOrderHeader header=new PurchaseOrderHeader();
			header.setPoId(poId);
			objPO.setHeader(header);

			PurchaseOrderDetail detail=new PurchaseOrderDetail();
			detail.setPoDetId(poDetId);
			objPO.setDetail(detail);

			objPO= poMan.deletePurchaseOrderDetail(ui,objPO);
			//			SalesQuotationHead sqH=boMan.SalesQuotationHeader(3,quoteId,userInfo);
			//			SalesQuotation detList=boMan.getAllSalesQuotationDetails(quoteId,userInfo);

			//			if(quoteId>0)
			//			{
			//				if(detList==null)
			//				{
			//					objSQ.setDetail(setDetailAttributes(fileMap,request,userInfo));
			//				}

			//			}

			if(objPO.getDeletedCount()==1){
				request.setAttribute("success_message",bundle.getString("Purchase.PurchaseOrderDeleted"));	
				doAddEditPurchaseOrder(request,response,objPO);

			}
			else if(objPO.getDeletedCount()==2){
				objPO=new PurchaseOrder();
				PurchaseOrderHeader sqHeader=new PurchaseOrderHeader();
				objPO.setHeader(sqHeader);
				setNewDefaultData(request,objPO);
				request.setAttribute("success_message",bundle.getString("Purchase.PurchaseOrderDeleted"));
				objPO.setHeaderMode("n");
				objPO.setDetailMode("n");
				doAddEditPurchaseOrder(request, response, objPO);
				sqHeader=null;
			}
			else{
				request.setAttribute("error_message",bundle.getString("Purchase.PurchaseOrderNotDeleted"));
				doAddEditPurchaseOrder(request, response, objPO);
			}

			fileMap=null;
			objPO=null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doDeletePurchaseOrder(HttpServletRequest request,HttpServletResponse response){
		try{
			TPCSUser ui=new UserInfo().getUserInfo(request);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			int poId=Validator.convertToInteger(request.getParameter("po_id"));
			int page=Validator.convertToInteger(request.getParameter("page"));
			//			int boDetId=Validator.convertToInteger(request.getParameter("bo_det_id"));

			PurchaseOrder objPO=new PurchaseOrder();

			PurchaseOrderHeader header=new PurchaseOrderHeader();
			header.setPoId(poId);
			objPO.setHeader(header);

			//			BuyerOrderDetail detail=new BuyerOrderDetail();
			//			detail.setBoDetId(boDetId);
			//			objBO.setDetail(detail);

			objPO=poMan.deletePurchaseOrder(ui,objPO);
			if(objPO.isDeleted()){
				request.setAttribute("success_message",bundle.getString("Purchase.PurchaseOrderDeleted"));

			}
			else{
				request.setAttribute("error_message",bundle.getString("Purchase.PurchaseOrderNotDeleted"));

			}

			//			String strRequestType= request.getParameter("request_type");
			String strSearchQuery="";


			if(page>0){
				objPO=new PurchaseOrder();
				PurchaseOrderHeader sqHeader=new PurchaseOrderHeader();
				objPO.setHeader(sqHeader);
				setNewDefaultData(request,objPO);

				request.setAttribute("success_message",bundle.getString("Purchase.PurchaseOrderDeleted"));

				objPO.setHeaderMode("n");
				objPO.setDetailMode("n");
				doAddEditPurchaseOrder(request, response, objPO);
				sqHeader=null;
			}
			else{
				organizColumns(request,strSearchQuery);

				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_PURCHASE_ORDER,request,response);
			}


		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void doDeleteBulkPurchaseOrder(HttpServletRequest request,HttpServletResponse response) 
	{
		try{
			TPCSUser ui =(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundleProvider rbp=new ResourceBundleProvider();
			ResourceBundle bundle = rbp.getResourceBundle(ui);
			PurchaseOrder objIND=new PurchaseOrder();
			PurchaseOrderHeader header=new PurchaseOrderHeader();

			String indentIds[]=request.getParameterValues("po_chk_id");

			int indId=0;
			//---------------------------------------------------------------------------------------------
			for(int j=0;j<indentIds.length;j++){
				header=new PurchaseOrderHeader();
				indId=Integer.parseInt(indentIds[j]);

				header.setPoId(indId);
				objIND.setHeader(header);

				objIND=poMan.deletePurchaseOrder(ui,objIND);
				header=null;
			}
			//---------------------------------------------------------------------------------------------

			if(objIND.isDeleted()){
				request.setAttribute("success_message",bundle.getString("Purchase.PurchaseOrderDeleted"));
				
			}
			else{
				request.setAttribute("error_message",bundle.getString("Purchase.PurchaseOrderNotDeleted"));
				
			}
			doDisplayPurchaseOrder(request, response);
			rbp=null;
			objIND=null;
			header=null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void doCancelPurchaseOrder(HttpServletRequest request,HttpServletResponse response){
		try{
			TPCSUser ui=new UserInfo().getUserInfo(request);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			int poId=Validator.convertToInteger(request.getParameter("po_id"));
			//			int page=Validator.convertToInteger(request.getParameter("page"));

			PurchaseOrder objPO= new PurchaseOrder();
			PurchaseOrderHeader header=new PurchaseOrderHeader();
			header.setPoId(poId);
			objPO.setHeader(header);

			objPO=poMan.cancelPurchaseOrder(ui,objPO);


			if(objPO.isCancelled()){

				request.setAttribute("success_message",bundle.getString("PurchaseOrder.PurchaseOrderCancelled"));

			}
			else{
				request.setAttribute("error_message",bundle.getString("PurchaseOrder.PurchaseOrderNotCancelled"));

			}
			doDisplayPurchaseOrder(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doCancelBulkPurchaseOrder(HttpServletRequest request,HttpServletResponse response){
		try{
			TPCSUser userInfo =(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundleProvider rbp=new ResourceBundleProvider();
			ResourceBundle bundle = rbp.getResourceBundle(userInfo);
			PurchaseOrder objIND=new PurchaseOrder();
			PurchaseOrderHeader header=new PurchaseOrderHeader();

			String indentIds[]=request.getParameterValues("po_chk_id");

			int indId=0;
			//---------------------------------------------------------------------------------------------
			for(int j=0;j<indentIds.length;j++){
				header=new PurchaseOrderHeader();
				indId=Integer.parseInt(indentIds[j]);

				header.setPoId(indId);
				objIND.setHeader(header);

				objIND=poMan.cancelPurchaseOrder(userInfo,objIND);
				header=null;
			}
			//---------------------------------------------------------------------------------------------

			if(objIND.isCancelled()){
				request.setAttribute("success_message",bundle.getString("PurchaseOrder.PurchaseOrderCancelled"));

			}
			else{
				request.setAttribute("error_message",bundle.getString("PurchaseOrder.PurchaseOrderNotCancelled"));
			}
			doDisplayPurchaseOrder(request, response);
			rbp=null;
			objIND=null;
			header=null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void doClosePurchaseOrder(HttpServletRequest request,HttpServletResponse response){
		try{
			TPCSUser ui=new UserInfo().getUserInfo(request);
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			int poId=Validator.convertToInteger(request.getParameter("po_id"));
			//			int page=Validator.convertToInteger(request.getParameter("page"));

			PurchaseOrder objPO=new PurchaseOrder();
			PurchaseOrderHeader header=new PurchaseOrderHeader();
			header.setPoId(poId);
			objPO.setHeader(header);

			objPO=poMan.closePurchaseOrder(ui,objPO);

			if(objPO.isCancelled()){
				request.setAttribute("success_message",bundle.getString("PurchaseOrder.PurchaseOrderClosed"));

			}
			else{
				request.setAttribute("error_message",bundle.getString("PurchaseOrder.PurchaseOrderNotClosed"));

			}
			doDisplayPurchaseOrder(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doCloseBulkPurchaseOrder(HttpServletRequest request,HttpServletResponse response){
		try{
			TPCSUser ui =(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundleProvider rbp=new ResourceBundleProvider();
			ResourceBundle bundle = rbp.getResourceBundle(ui);
			PurchaseOrder objIND=new PurchaseOrder();
			PurchaseOrderHeader header=new PurchaseOrderHeader();

			String indentIds[]=request.getParameterValues("po_chk_id");

			int indId=0;
			//---------------------------------------------------------------------------------------------
			for(int j=0;j<indentIds.length;j++){
				header=new PurchaseOrderHeader();
				indId=Integer.parseInt(indentIds[j]);

				header.setPoId(indId);
				objIND.setHeader(header);

				objIND=poMan.closePurchaseOrder(ui,objIND);
				header=null;
			}
			//---------------------------------------------------------------------------------------------

			if(objIND.isClosed()){
				request.setAttribute("success_message",bundle.getString("PurchaseOrder.PurchaseOrderClosed"));

			}
			else{
				request.setAttribute("error_message",bundle.getString("PurchaseOrder.PurchaseOrderNotClosed"));
			}
			doDisplayPurchaseOrder(request, response);
			rbp=null;
			objIND=null;
			header=null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}



	private PurchaseOrderManager poMan=new PurchaseOrderManager();
	private DynamicFieldManager  dynamicFieldManager = new DynamicFieldManager();
	private CommonUtilManager  comUtilMan= new CommonUtilManager();
	private UserRights rights = null;
	private UserRightsManager objRight = new UserRightsManager();
	private List<DynamicFieldEvents> dynamicFormEvents = null;
	private List<DynamicFieldEvents> dynamicHeaderFieldEvents = null;
	private List<DynamicFieldEvents> dynamicDetailFieldEvents = null;
	private List<DynamicFieldEvents> dynamicHeaderpickListOptions = null;
	private List<DynamicFieldEvents> dynamicDetailpickListOptions = null;
	private DynamicFormStructure dynamicFormStructurePOHeader = null;
	private DynamicFormStructure  dynamicFormStructurePODetail=null;
	private List<DynamicFields> dynamicFieldsListPOHeader = null;
	private List<DynamicFields> dynamicFieldsListPODetail=null;


	private int screenId=8;
	private int headerTableId=10;
	private int detailTableId=11;
	private String seperator="5*5";
}

