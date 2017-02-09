package com.alpha.tpcsfashion.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringEscapeUtils;

import com.alpha.tpcsfashion.beans.BOM;
import com.alpha.tpcsfashion.beans.BOMDetail;
import com.alpha.tpcsfashion.beans.BOMHeader;
import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.Currency;
import com.alpha.tpcsfashion.beans.DynamicFieldEvents;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.DynamicFormStructure;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.gridmaker.BOMGridMaker;
import com.alpha.tpcsfashion.model.BOMManager;
import com.alpha.tpcsfashion.model.ColumnPreferenceManager;
import com.alpha.tpcsfashion.model.CurrencyManager;
import com.alpha.tpcsfashion.model.DynamicFieldManager;
import com.alpha.tpcsfashion.model.DynamicOrderManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.ExportToPdfTool;
import com.alpha.tpcsfashion.util.JasperReportPrinter;
import com.alpha.tpcsfashion.util.ResourceBundleProvider;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.TableHeader;
import com.alpha.tpcsfashion.util.UserInfo;
import com.alpha.tpcsfashion.util.Validator;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class BOMServlet {

	public void doDisplayBOM(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			
			
			HttpSession session=request.getSession();
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			String strSearchCriteria=null;
			String reqType=request.getParameter("request_type");
			if("Search".equalsIgnoreCase(reqType)){	
				//strSearchCriteria = getSearchCriteria(request);
//				getAndSetAttributes(request);
			}
			dynamicfields = DynMan.getDynamicFields("29,30",userInfo,1);
			session.setAttribute("fixedfields", dynamicfields);
			dynamicfields = DynMan.getDynamicFields("29,30",userInfo,0);
			session.setAttribute("dynamicfields", dynamicfields);

			doGetUserRights(request,response);
			
			organizeColumns(request,strSearchCriteria);

			request.setAttribute("request_type", reqType);
	        session.setAttribute("seletedScreenId",14);
			request.setAttribute("invoke_servlet", "BOMServlet");
			request.setAttribute("invoke_method", "doDisplayBOM");
			
			
			dynamicfields=null;
			
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_BOM_MASTER, request,response);
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	
	
	public void organizeColumns(HttpServletRequest request,String strSearchQuery)
	{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			request.setAttribute("subdocument_id", SubdocumentId.BOM_PRODUCTION);

			ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
			ColumnPreference DataList=cpm.getVisibleColumnNames(SubdocumentId.BOM_PRODUCTION, userInfo);
			Map<String,String> visColMap=cpm.getVisibleColumnNamesMap(SubdocumentId.BOM_PRODUCTION,userInfo.getUserId(),userInfo);

			List<String> listSelectedColumns=DataList.getVisibleColumns(); 
			List<String> orderBy=DataList.getOrderBy(); 

			String strPageNo = request.getParameter("pageno");
			int ipageno =Validator.convertToInteger(strPageNo);

			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			int pageCount= objMan.getPageCount(userInfo,pc, strSearchQuery);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);
			BOMGridMaker objUtil=new BOMGridMaker();
			String bomAttachPath=new UserInfo().getBOMAttachPath(request);
			List<Map<String,String>> objList= objMan.getAllBomColumnMapping(pc,userInfo,strSearchQuery,orderBy,14,bomAttachPath);
			String Grid = objUtil.formBomGrid(userInfo,visColMap,listSelectedColumns, objList, ipageno, bundle, rights,pc);
			
			
			request.setAttribute("bom_grid", Grid);
			//getAndSetAttributes(request);

			
			listSelectedColumns=null;
			objList=null;
			cpm=null;
			pc=null;
			objUtil=null;
			bomAttachPath=null;
			Grid=null;
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	
	public void doDisplayBOMAfterColumnOrganized(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{  

			HttpSession session=request.getSession();  
			TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
			String strTotalColumns = request.getParameter("tot_columns");  
			String strRequestType=request.getParameter("request_type");
			String strSearchQuery="";
			doGetUserRights(request,response);
//			if("Search".equalsIgnoreCase(strRequestType)){	          
//				strSearchQuery=(String)request.getSession().getAttribute("search_query");
//			}	     
//			else{
//				if(request.getSession().getAttribute("search_query")!=null) 
//					request.getSession().removeAttribute("search_query"); 
//			}
			ColumnPreferenceManager cpm=new ColumnPreferenceManager();   
			//System.out.println("strTotalColumns :"+strTotalColumns);
			boolean bFlag = cpm.updateColumnPreferences(strTotalColumns,SubdocumentId.BOM_PRODUCTION,userInfo);

			organizeColumns(request,strSearchQuery);
			request.setAttribute("request_type", strRequestType);
			cpm=null;
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_BOM_MASTER,request,response);
		}
		catch(Exception e){ 
			e.printStackTrace();      
		}
	}

	public void doSearchBOM(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{  
			String strSearchQuery="";
			String reqType=request.getParameter("request_type");

			if("Search".equalsIgnoreCase(reqType)){	         
				strSearchQuery = getSearchCriteria(request);
//				request.getSession().setAttribute("search_query",strSearchQuery);
				getAndSetAttributes(request);
			}
			doGetUserRights(request,response);
			organizeColumns(request,strSearchQuery);
			
			request.setAttribute("request_type", reqType);
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_BOM_MASTER,request,response);
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}
	
	public void getAndSetAttributes(HttpServletRequest request){
		request.setAttribute("bom_customer",request.getParameter("bom_customer"));
		request.setAttribute("bom_customer_id",request.getParameter("bom_customer_id"));
		request.setAttribute("material",request.getParameter("material"));
		request.setAttribute("material_id",request.getParameter("material_id"));
		request.setAttribute("bom_from_date",request.getParameter("bom_from_date"));
		request.setAttribute("bom_to_date",request.getParameter("bom_to_date"));
		request.setAttribute("product_id",request.getParameter("product_id"));
		request.setAttribute("product",request.getParameter("product"));
		request.setAttribute("bom_no",request.getParameter("bom_no"));
		request.setAttribute("operation_id",request.getParameter("operation_id"));
		request.setAttribute("operation",request.getParameter("operation"));
		request.setAttribute("group",request.getParameter("group"));
		request.setAttribute("group_id",request.getParameter("group_id"));
		
	}

	
	
	public String getSearchCriteria(HttpServletRequest request){

		StringBuffer  strQuery=new StringBuffer("");
		TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
		int partyId=Validator.convertToInteger(request.getParameter("bom_customer_id"));
		int matId=Validator.convertToInteger(request.getParameter("material_id"));
		int grpId=Validator.convertToInteger(request.getParameter("group_id"));
		String bomFromDate= request.getParameter("bom_from_date");
		String bomToDate= request.getParameter("bom_to_date");
		int productID=Validator.convertToInteger(request.getParameter("product_id"));
		String bomNo= request.getParameter("bom_no");
		int operationId=Validator.convertToInteger(request.getParameter("operation_id"));
		//For DynamicFields
		int dynamicfieldId_1 = Validator.convertToInteger(request.getParameter("dynamic_field_1"));
		int dynamicfieldId_2 = Validator.convertToInteger(request.getParameter("dynamic_field_2"));
		int dynamicfieldId_3 = Validator.convertToInteger(request.getParameter("dynamic_field_3"));
		String dynamicfieldValue_1 =request.getParameter("dynamic_field_1_val");
		String dynamicfieldValue_2 =request.getParameter("dynamic_field_2_val");
		String dynamicfieldValue_3 =request.getParameter("dynamic_field_3_val");
		String dynamicdbName_1=null;
		String dynamicdbName_2=null;
		String dynamicdbName_3=null;
		int dynamicTableId_1=0;
		int dynamicTableId_2=0;
		int dynamicTableId_3=0;
		
//		String enqType=request.getParameter("bom_type");
		
		
		/* if(enqType!= null && enqType.equalsIgnoreCase("Awaiting")){
			strQuery.append(" and isnull(se.authorized_tag,0)=0 ");
		}
		else if(enqType!= null && enqType.equalsIgnoreCase("Cancelled")){
			strQuery.append(" and se.cancel_tag=1 ");
		}
	*/
		if(dynamicfieldId_1!=-1)
		{
			dynamicdbName_1=DynMan.getDynamicDbName(dynamicfieldId_1,userInfo);
			dynamicTableId_1=DynMan.getDynamicTableId(dynamicfieldId_1,userInfo);
			if(dynamicTableId_1==3)
			{
				if(dynamicfieldValue_1!=null && dynamicfieldValue_1.trim().length()>0){
					strQuery.append("  sedf."+dynamicdbName_1+" LIKE '%"+dynamicfieldValue_1.replace("'","''")+"%'  AND ");
				}
			}
			else if(dynamicTableId_1==4)
			{
				if(dynamicfieldValue_1!=null && dynamicfieldValue_1.trim().length()>0){
					strQuery.append("  seddf."+dynamicdbName_1+" LIKE '%"+dynamicfieldValue_1.replace("'","''")+"%'  AND ");
				}
			}
		}

		if(dynamicfieldId_2!=-1)
		{
			dynamicdbName_2=DynMan.getDynamicDbName(dynamicfieldId_2,userInfo);
			dynamicTableId_2=DynMan.getDynamicTableId(dynamicfieldId_2,userInfo);
			if(dynamicTableId_2==3)
			{
				if(dynamicfieldValue_2!=null && dynamicfieldValue_2.trim().length()>0){
					strQuery.append("  sedf."+dynamicdbName_2+" LIKE '%"+dynamicfieldValue_2.replace("'","''")+"%' AND  ");
				}
			}
			else if(dynamicTableId_2==4)
			{
				if(dynamicfieldValue_2!=null && dynamicfieldValue_2.trim().length()>0){
					strQuery.append("  seddf."+dynamicdbName_2+" LIKE '%"+dynamicfieldValue_2.replace("'","''")+"%'  AND ");
				}
			}
		}

		if(dynamicfieldId_3!=-1)
		{
			dynamicdbName_3=DynMan.getDynamicDbName(dynamicfieldId_3,userInfo);
			dynamicTableId_3=DynMan.getDynamicTableId(dynamicfieldId_3,userInfo);
			if(dynamicTableId_3==3)
			{
				if(dynamicfieldValue_3!=null && dynamicfieldValue_3.trim().length()>0){
					strQuery.append("  sedf."+dynamicdbName_3+" LIKE '%"+dynamicfieldValue_3.replace("'","''")+"%'  AND ");
				}
			}
			else if(dynamicTableId_3==4)
			{
				if(dynamicfieldValue_3!=null && dynamicfieldValue_3.trim().length()>0){
					strQuery.append( "  seddf."+dynamicdbName_3+" LIKE '%"+dynamicfieldValue_3.replace("'","''")+"%'  AND ");
				}
			}
		}
		 dynamicdbName_1=null;
		 dynamicdbName_2=null;
		 dynamicdbName_3=null;

		if(bomFromDate!=null && bomFromDate.length()>0){
			strQuery.append("  b.bom_date>=convert(datetime,'"+bomFromDate+"',105) and "); 
		}
		if(bomToDate!=null && bomToDate.length()>0){
			strQuery.append("  b.bom_date<=convert(datetime,'"+bomToDate+"',105) and ");
		}
		if(bomNo!=null && !bomNo.isEmpty()){
			strQuery.append("  b.bom_no='"+bomNo.replace("'","''")+"'  and ");
		}
		
		if(partyId!=0 && partyId!=-1){
			strQuery.append("  p.party_id="+partyId+" and ");
		}
		
		if(matId!=0){
			strQuery.append("  m.item_id="+matId+" and ");
		}
		
		if(grpId!=0){
			strQuery.append("  m.group_id="+grpId+" and ");
		}
		
		if(productID!=0){
			strQuery.append("  prod.material_id="+productID+" and ");
		}
	
		if(operationId>0){
			strQuery.append("  op.operation_id="+operationId+" and ");
		}
		
		String searchQuery="";
		searchQuery=strQuery.toString();
		if(!searchQuery.isEmpty())
		{
			searchQuery=searchQuery.substring(0,searchQuery.length()-4);
		}
	
		
		return searchQuery;

	}	
	public void doDeleteBOM(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{

		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		int bomId=Validator.convertToInteger(request.getParameter("bom_id"));
		int page=Validator.convertToInteger(request.getParameter("page"));
		boolean isDeleted=objMan.deleteBOM(userInfo,bomId);
		ResourceBundleProvider rbp=new ResourceBundleProvider();
		ResourceBundle resBundle = rbp.getResourceBundle(userInfo);
		if(isDeleted)
		{
			request.setAttribute("success_message",resBundle.getString("BOM.BOMDeleted"));	
		}
		else
		{
			request.setAttribute("error_message",resBundle.getString("BOM.BOMNotDeleted"));
		}
		if(page>0)
		{
			BOM objB=new BOM();
			objB.setHeaderMode("n");
			objB.setDetMode("n");
			doGetNewAndEditBOM(request,response,0,objB);

		}else{
			doDisplayBOM(request, response); 
		}

		rbp=null;
	}

	
	
	public void doNewBOM(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{  

			Map<String,String> fileMap=new LinkedHashMap<String, String>();
			String headerMode = getHeaderMode(fileMap,request);
			BOM bom=new BOM();
			
			BOMHeader bomHeader=new BOMHeader();
			bom.setHeader(bomHeader);
			
			bom.setHeaderMode(headerMode);

			int bomId=Validator.convertToInteger(request.getParameter("bom_id"));
			if(bom.getHeaderMode().equalsIgnoreCase("n")){
				request.setAttribute("tr_date",TPCSCommonUtil.currenDate("dd-MM-yyyy"));
				request.setAttribute("new_page","yes");
			}

			doGetNewAndEditBOM(request,response,bomId,bom);

			fileMap=null;
			bom=null;
	
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}
	
	

	public void doGetNewAndEditBOM(HttpServletRequest request, HttpServletResponse response,int bomId,BOM bom)throws ServletException, IOException
	{
		HttpSession session=request.getSession();
		TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");

//		ExportToPdfTool.setRoundOff(userInfo);
		
		request.setAttribute("bom", bom);
//		currency_list=curMan.getAllCurrency(userInfo);
//		request.setAttribute("currency_list", currency_list);


		
		//DynamicFields
		dynamicFieldsListBOMHeader = dynamicFieldManager.getDynamicFields(14, 29,userInfo);
		request.setAttribute("dynamicFieldsListBOMHeader", dynamicFieldsListBOMHeader);
		request.getSession().setAttribute("dynamicFieldsListBOMHeader", dynamicFieldsListBOMHeader);

		
		

		dynamicFieldsListBOMDetails = dynamicFieldManager.getDynamicFields(14, 30, userInfo);
		request.setAttribute("dynamicFieldsListBOMDetails", dynamicFieldsListBOMDetails);
		request.getSession().setAttribute("dynamicFieldsListBOMDetails", dynamicFieldsListBOMDetails);

		dynamicFormStructureBOMHeader = dynamicFieldManager.getDynamicFormStructure(14, userInfo);
		request.setAttribute("dynamicFormStructureBOMHeader", dynamicFormStructureBOMHeader);


		dynamicFormStructureBOMDetails = dynamicFieldManager.getDynamicFormStructure(14, userInfo);
		request.setAttribute("dynamicFormStructureBOMDetails", dynamicFormStructureBOMDetails);

		dynamicFormEvents =dynamicFieldManager.getDynamicFormEvents(14,userInfo);
		request.setAttribute("dynamicFormEvents", dynamicFormEvents);

		dynamicHeaderFieldEvents =dynamicFieldManager.getDynamicFieldEvents(14,29,userInfo);
		request.setAttribute("dynamicHeaderFieldEvents", dynamicHeaderFieldEvents);


		dynamicDetailFieldEvents =dynamicFieldManager.getDynamicFieldEvents(14,30,userInfo);
		request.setAttribute("dynamicDetailFieldEvents", dynamicDetailFieldEvents);

		dynamicHeaderpickListOptions =dynamicFieldManager.getPickListOptions(29,userInfo);
		request.setAttribute("dynamicHeaderpickListOptions", dynamicHeaderpickListOptions);


		dynamicDetailpickListOptions =dynamicFieldManager.getPickListOptions(30,userInfo);
		request.setAttribute("dynamicDetailpickListOptions", dynamicDetailpickListOptions);
		
		
		String viewMode=request.getParameter("view_mode");

		if(bom.getHeaderMode().equals("a") || bom.getHeaderMode().equals("e")){

			if(bom.getHeaderMode().equals("a")){
				 boolean isAmend=objMan.createAmendment(userInfo,bom,bomId);
			}

			BOM bomHeader=objMan.getBOMHeader(14,bomId,userInfo);
			
			BOM detList=objMan.getBOMDetailList(bomId,userInfo);

			
			List<BOM> attachFiles = objMan.getAttachFiles(bomId,userInfo);
			String bomAttachPath=new UserInfo().getBOMAttachPath(request);
			request.setAttribute("attached_files", attachFiles);
			request.setAttribute("bomAttachPath", bomAttachPath);


//			seusers = seMan.getSEDefUsers(request.getContextPath(),clientId,enqId,iUserId,userInfo);
//			request.setAttribute("se_user_list", seusers);
//
//			sefollowers = seMan.getSEFollowers(request.getContextPath(),clientId,enqId,iUserId,userInfo,viewMode);
//			request.setAttribute("se_followers_list", sefollowers);

			request.setAttribute("header_info", bomHeader.getHeader());
			//DynamicFields
			dynamicFieldsListBOMHeader = bomHeader.getBomDynList();
			request.getSession().setAttribute("dynamicFieldsListBOMHeader", dynamicFieldsListBOMHeader);
			request.setAttribute("dynamicFieldsListBOMHeader", dynamicFieldsListBOMHeader);

			request.setAttribute("detList", detList.getBomDetList());
			request.setAttribute("detMapList", detList.getBomDetMap());

			request.setAttribute("dynamicFieldsListBOMDetails", dynamicFieldsListBOMDetails);
			request.getSession().setAttribute("dynamicFieldsListBOMDetails", dynamicFieldsListBOMDetails);

			List<Integer> detIdList=new ArrayList<Integer>();
			for(BOMDetail det:detList.getBomDetList()){
				detIdList.add(det.getBomDetailId());
			}
			request.setAttribute("det_id_list",detIdList);
			
			bomHeader=null;
			detIdList=null;
//			attachFiles=null;
//			enquiryAttachPath=null;
		}
		else{
			BOMHeader bomHeader=bom.getHeader();
			objMan.getBOMInfo(userInfo,bomHeader);
			request.setAttribute("header_info",bomHeader);
			bomHeader=null;
		}
		doGetUserRights(request,response);
	
		request.setAttribute("view_mode",viewMode);
		//SalesEnquiryDetail roundOff=getRoundOff(fileMap,request,userInfo);
		
		//request.setAttribute("roundoff_info",roundOff);
		
		
//		roundOff=null;
		currency_list=null;
		dynamicFieldsListBOMHeader=null;
		dynamicFieldsListBOMDetails=null;
		dynamicFormStructureBOMHeader=null;
		dynamicFormEvents=null;
		dynamicHeaderFieldEvents=null;
		dynamicDetailFieldEvents=null;
		dynamicHeaderpickListOptions=null;
		dynamicDetailpickListOptions=null;
		TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_BOM_MASTER,request,response);
	}

	@SuppressWarnings("unchecked")
	public void doSaveBOM(HttpServletRequest request, HttpServletResponse response,FileImport objBean)throws ServletException, IOException{
		try{  
			
			
			System.out.println(" SAVE SERVELE");
			HttpSession session=request.getSession();
			TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
//			String clientId = (String)request.getSession().getAttribute("client_id");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			String realPath=(String) request.getSession().getAttribute("path");
			String folderPath=realPath+new UserInfo().getEnquiryAttachPath(request);
			objBean.setFolderPath(folderPath);
			BOM objBOM=new BOM();
			Map<String,String> fileMap=new LinkedHashMap<String, String>();

			dynamicFieldsListBOMHeader = (List<DynamicFields>) session.getAttribute("dynamicFieldsListBOMHeader");
			dynamicFieldsListBOMDetails = (List<DynamicFields>) session.getAttribute("dynamicFieldsListBOMDetails");

		
			List<String> chkHead=new ArrayList<String>();
			List<String> chkDetail=new ArrayList<String>();
			
		
			

			for(DynamicFields dynamicFields: dynamicFieldsListBOMHeader) {
				//Checking the condition - if dynamic field i.e., isFixed  =  false
				if(!dynamicFields.isFixed()) {
					chkHead.add(dynamicFields.getPageFieldName());
				}
			}
			int bomDetId=Validator.convertToInteger(fileMap.get("bom_det_id"));
			
			System.out.println("bomDetId :"+bomDetId);
			String detId="_"+bomDetId;

			for(DynamicFields dynamicFields: dynamicFieldsListBOMDetails) {
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

						for(DynamicFields dynamicFields: dynamicFieldsListBOMHeader) {
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
									checkBoxValueArray=null;
								} else {

									dynamicFields.setFieldValue((String) StringEscapeUtils.escapeXml(fileMap.get(dynamicFields.getPageFieldName())));
								}
							}
						}
					}
					if(chkDetail.contains(item.getFieldName())){

						for(DynamicFields dynamicFields: dynamicFieldsListBOMDetails) {
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
			String sqlxml = generateXMLforDynamicFields(dynamicFieldsListBOMHeader,dynamicFieldsListBOMDetails);
			String editedMode=fileMap.get("edited_mode");
			String saveType=fileMap.get("save_type");
			
			
			request.setAttribute("det_mat_id",Validator.convertToInteger(fileMap.get("material_id"+detId)));
			request.setAttribute("det_comp_id",Validator.convertToInteger(fileMap.get("component_id"+detId)));
			request.setAttribute("save_type",saveType);
			

			//	            if(!editedMode.equalsIgnoreCase("e"))
			//		    	  {
			objBOM=getBOMParameter(fileMap,request,response,userInfo);
			//		    	  }


			//added for dynamic fields 
			objBOM.setSqlxmlDynamicFields(sqlxml);
			String editedIds=fileMap.get("edited_ids");
			String dynEditedIds=fileMap.get("dynedited_ids");

			Map<String,String> map= new HashMap<String, String>();
			Map<String,String> mapDyn= new HashMap<String, String>();

			
		
			
			if(editedMode.equalsIgnoreCase("e")) {
				String streditedIds[] = editedIds.split(",");
				for(int i=0; i < streditedIds.length; i++){
					map.put(streditedIds[i], fileMap.get(streditedIds[i]));
				}
				String strDyneditedIds[] = dynEditedIds.split(",");
				for(int i=0; i < strDyneditedIds.length; i++){
					mapDyn.put(strDyneditedIds[i], fileMap.get(strDyneditedIds[i]));
				}
				
				
			}
			String[] userid = fileMap.get("selected_users").trim().split(",");
			String[] userremarks = fileMap.get("selected_userremarks").split(",");


			//objSE.setSaveFollower(fileMap.get("remember_followers")!=null?fileMap.get("remember_followers"):"null");

			 objBOM = objMan.saveBOM(userInfo,objBOM,map,mapDyn,editedMode,objBean,userid,userremarks,Integer.parseInt((String)request.getSession().getAttribute("login_user_id")));
			
			 
			int bomId=objBOM.getHeader().getBomId();
			
		/*	if(bomDetId>0){
				request.setAttribute("bom_det_id",bomDetId);
				request.setAttribute("new_alt_mat","0");
			}else{
				
				request.setAttribute("new_alt_mat","1");
			}*/
			
			request.setAttribute("bom_det_id",objBOM.getDetail().getBomDetailId());
			
			
//			int bomDetId=objBOM.getDetail().getBomDetailId();
			
//			System.out.println("Mode :"+objBOM.getHeaderMode());
//			System.out.println("Saved :"+objBOM.getDetail().getBomDetailId());
//			System.out.println("Edit :"+bomDetId);
	
			
			if(bomId==-1){
				
				request.setAttribute("error_message",bundle.getString("BOM.BOMAlreadyExists"));
				
				bomId=0;
				doGetNewAndEditBOM(request,response,bomId,objBOM);
				
			}
			else{
			



			if(bomId>0) {
				if(objBOM.getHeaderMode().equals("n")) {
					objBOM.setHeaderMode("e");	 
				}
				/*request.setAttribute("success_message",bundle.getString("SalesEnquiry.SalesEnquiryInserted")); */
			}
			else {
				request.setAttribute("error_message",bundle.getString("BOM.BOMNotInserted")); 
			}
			if(objBOM.getDetMode().equals("e")) {
				objBOM.setDetMode("n"); 
			}

			
			
			
			
        //System.out.println("saveType :"+saveType);

			if(saveType.equals("3")){//Save Type Save & New
				
				objBOM.setHeaderMode("n");
				objBOM.setDetMode("n");
				bomId=0;
				BOMHeader seHeader=new BOMHeader();
				objBOM.setHeader(seHeader);
				request.setAttribute("tr_date",TPCSCommonUtil.currenDate("dd-MM-yyyy"));
				request.setAttribute("new_page","yes");
				seHeader=null;
				doGetNewAndEditBOM(request,response,bomId,objBOM);
			}
			else  if(saveType.equals("4")){//Save Type Save & Close
				doDisplayBOM(request,response) ;
			}
			else {
				if(saveType.equals("2")){//button type save&send

				}
				doGetNewAndEditBOM(request,response,bomId,objBOM);
				
				objBOM=null;
				fileMap=null;
				dynamicFieldsListBOMHeader=null;
				dynamicFieldsListBOMDetails=null;
				chkDetail=null;
				chkHead=null;
				map=null;
				mapDyn=null;
				
			}
			}

		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}
	
	
	 public String generateXMLforDynamicFields(
				List<DynamicFields> dynamicFieldsListGRNHeader) {
			String strXML = "<screens> ";
			String formPurchaseOrder = "<screen name=\"BOM\">  <columns> ";
			
			if(dynamicFieldsListGRNHeader!=null) {
				for (DynamicFields dynamicFields : dynamicFieldsListGRNHeader) {
					if (!dynamicFields.isFixed()) {
						String column = " <column type=\"dynamic\"> <name>" + dynamicFields.getDbFieldName() + "</name> <value>" + dynamicFields.getFieldValue() + "</value> <datatype>" + dynamicFields.getDataTypeName() + "</datatype> </column> ";
						formPurchaseOrder = formPurchaseOrder.concat(column);
					} 
				}
			}
			formPurchaseOrder = formPurchaseOrder.concat(" </columns> </screen> ");
			formPurchaseOrder = formPurchaseOrder.concat(" </screens> ");
			strXML = strXML.concat(formPurchaseOrder);
			return strXML;
		}	
	
	public String generateXMLforDynamicFields(
			List<DynamicFields> dynamicFieldsListBOMHeader,
			List<DynamicFields> dynamicFieldsListBOMDetails) {
		String strXML = "<screens> ";
		String formBOM = "<screen name=\"BOM\">  <columns> ";
		String formBOMDetail = "<screen name=\"BOMDetail\">  <columns> ";

		if(dynamicFieldsListBOMHeader!=null) {
			for (DynamicFields dynamicFields : dynamicFieldsListBOMHeader) {
				if (!dynamicFields.isFixed()) {
					String column = " <column type=\"dynamic\"> <name>" + dynamicFields.getDbFieldName() + "</name> <value>" + dynamicFields.getFieldValue() + "</value> <datatype>" + dynamicFields.getDataTypeName() + "</datatype> </column> ";
					formBOM = formBOM.concat(column);
				} 
			}
		}
		formBOM = formBOM.concat(" </columns> </screen> ");
		formBOM = formBOM.concat(formBOMDetail);
		if(dynamicFieldsListBOMDetails!=null) {
			for (DynamicFields dynamicFields : dynamicFieldsListBOMDetails) {
				if (!dynamicFields.isFixed()) {	
					String column = " <column type=\"dynamic\"> <name>" + dynamicFields.getDbFieldName() + "</name> <value>" +dynamicFields.getFieldValue()+ "</value> <datatype>" + dynamicFields.getDataTypeName() + "</datatype> </column> ";
					formBOM = formBOM.concat(column);
				} 
			}
		}

		formBOM = formBOM.concat(" </columns> </screen> ");
		formBOM = formBOM.concat(" </screens> ");
		strXML = strXML.concat(formBOM);
		return strXML;
	}


	private BOM getBOMParameter(Map<String,String> fileMap,HttpServletRequest request,HttpServletResponse response,TPCSUser userInfo) {
		BOM bom=new BOM();
		BOMHeader bomh=getHeader(fileMap,request,userInfo);
		BOMDetail bomd=getDetail(fileMap,request,userInfo);
		bom.setHeader(bomh);
		bom.setDetail(bomd);
		bom.setHeaderMode(fileMap.get("head_mode"));
		bomh=null;
		bomd=null;
//		System.out.println("fileMap.get new_det_mode :"+fileMap.get("new_det_mode"));
		bom.setDetMode(fileMap.get("new_det_mode"));
		return bom;
	}
	private BOMDetail getDetail(Map<String,String> fileMap,HttpServletRequest request,TPCSUser userInfo) {
		// TODO Auto-generated method stub
		BOMDetail bomd=new BOMDetail();
		int bomDetId=Validator.convertToInteger(fileMap.get("bom_det_id"));
		String detId="_"+bomDetId;
		bomd.setBomDetailId(bomDetId);
		bomd.setMaterialId(Validator.convertToInteger(fileMap.get("material_id"+detId)));
		bomd.setComponentId(Validator.convertToInteger(fileMap.get("component_id"+detId)));
		bomd.setOperationId(Validator.convertToInteger(fileMap.get("operation_id"+detId)));
		bomd.setSupplierId(Validator.convertToInteger(fileMap.get("supplier_id"+detId)));
		bomd.setUOM(fileMap.get("uom"+detId));
		bomd.setRequiredQty(fileMap.get("required_qty"+detId));
		bomd.setWastagePer(Validator.convertToInteger(fileMap.get("wastage_per"+detId)));
		bomd.setPurchasePer(Validator.convertToInteger(fileMap.get("purchase_per"+detId)));
		bomd.setIssuePer(Validator.convertToInteger(fileMap.get("issue_per"+detId)));
		bomd.setCostingPer(Validator.convertToInteger(fileMap.get("costing_per"+detId)));
		bomd.setSizeSheduleId(Validator.convertToInteger(fileMap.get("size_schedule_id"+detId)));
		
		bomd.setVariantId(Validator.convertToInteger(fileMap.get("variant_id"+detId)));
		bomd.setVariant(fileMap.get("variant_name"+detId));
		
		System.out.println("1 :"+Validator.convertToInteger(fileMap.get("material_id"+detId)));
		System.out.println("2 :"+Validator.convertToInteger(fileMap.get("operation_id"+detId)));
		System.out.println("3 :"+fileMap.get("required_qty"+detId));
		return bomd;
	}
	
	private BOMHeader getHeader(Map<String,String> fileMap,HttpServletRequest request,TPCSUser userInfo) {
		// TODO Auto-generated method stub
		BOMHeader bomh=new BOMHeader();
		bomh.setBomId(Validator.convertToInteger(fileMap.get("bom_id")));


		bomh.setBomNo(fileMap.get("bom_no"));
		bomh.setBomDate(fileMap.get("tr_date"));
		bomh.setCustomer(fileMap.get("customer"));
		bomh.setCustomerId(Validator.convertToInteger(fileMap.get("customer_id")));
		
		bomh.setParentBomNo(fileMap.get("parent_bom_no"));
		bomh.setParentBomId(Validator.convertToInteger(fileMap.get("parent_bom_id")));
		bomh.setProductId(Validator.convertToInteger(fileMap.get("product_id")));
		bomh.setProduct(fileMap.get("product"));

		bomh.setRemarks(fileMap.get("bom_msg"));

		return bomh;
	}
	
	
	public void doGetUserRights(HttpServletRequest request, HttpServletResponse response)
	{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		HttpSession session=request.getSession();
		
		rights =objRight.getUserRights(SubdocumentId.BOM_PRODUCTION, userInfo);
		session.setAttribute("bom_rights",rights);
		
		
	}
	
	
	public void doDeleteBOMDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			Map<String,String> fileMap=new LinkedHashMap<String, String>();
			TPCSUser userInfo =(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			BOM objB=new BOM();
			objB.setHeaderMode(getHeaderMode(fileMap,request));
			objB.setDetMode(getDetailMode(fileMap,request));

			

			int bomId=Validator.convertToInteger(request.getParameter("bom_id"));
			int bomDetId=Validator.convertToInteger(request.getParameter("bom_det_id"));
			int iCount=objMan.deleteBOMDetail(userInfo,bomId,bomDetId);
			BOM detList=objMan.getBOMDetailList(bomId,userInfo);
			
			if(bomId>0)
			{
				if(detList==null)
				{
					objB.setDetail(getDetail(fileMap,request,userInfo));
				}

			}
			
			if(iCount==1)
			{
				request.setAttribute("success_message",bundle.getString("BOM.BOMDetailDeleted"));	
				
			}
			else if(iCount==2)
			{
				objB=new BOM();
				BOMHeader objHeader=new BOMHeader();
				objB.setHeader(objHeader);
				request.setAttribute("tr_date",TPCSCommonUtil.currenDate("dd-MM-yyyy"));
				request.setAttribute("new_page","yes");
				request.setAttribute("success_message",bundle.getString("BOM.BOMDeleted"));
				objB.setHeaderMode("n");
				objB.setDetMode("n");
				objHeader=null;
			}
			else
			{
				request.setAttribute("error_message",bundle.getString("BOM.BOMNotDeleted"));
			}
		
			doGetNewAndEditBOM(request,response,bomId,objB);
			
			
			fileMap=null;
			objB=null;
			detList=null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	 public void doGetAltMaterialModalData(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	        try{ 
	        		TPCSUser ui=new UserInfo().getUserInfo(request);
//	        		ExportToPdfTool.setRoundOff(ui);
	        		int bomDetID=Validator.convertToInteger(request.getParameter("bom_det_id"));
	    	  	  	String retData= objMan.getAltMaterialModalData(ui,bomDetID);
	    	  	  	response.setContentType("text/xml");
	    	  	  	
	    	  	  //	System.out.println("retData--->"+retData);
	    	  	  	response.getWriter().write(retData);
	    	  	  ui=null;
	    	  	retData=null;
	        }catch(Exception e){
	          e.printStackTrace();
	        }
	      }
	 
	 
	 public void doGetBOMCompModalData(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	        try{ 
	        		TPCSUser ui=new UserInfo().getUserInfo(request);
//	        		ExportToPdfTool.setRoundOff(ui);
	        		int bomDetID=Validator.convertToInteger(request.getParameter("bom_det_id"));
	    	  	  	String retData= objMan.getBOMCompModalData(ui,bomDetID);
	    	  	  	response.setContentType("text/xml");
	    	  	  	
	    	  	  //	System.out.println("retData--->"+retData);
	    	  	  	response.getWriter().write(retData);
	    	  	  ui=null;
	    	  	retData=null;
	        }catch(Exception e){
	          e.printStackTrace();
	        }
	      }
	private String getHeaderMode(Map<String,String> fileMap,HttpServletRequest request)
	{
		String headMode= Validator.trim(request.getParameter("head_mode"));
		return headMode; 	
	}
	private String getDetailMode(Map<String,String> fileMap,HttpServletRequest request) {
		String	detMode=Validator.trim(request.getParameter("det_mode"));
		return detMode; 
	}

	 public void doSaveAlternateMaterial(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			try {
				
				TPCSUser userInfo=new UserInfo().getUserInfo(request);
				int bomDetId=Validator.convertToInteger(request.getParameter("bom_det_id"));
				String matDetails="";
				
				matDetails=getAltMatParameter(request,bomDetId);
				
				BOM objB=new BOM();
				objB.setBomDetId(bomDetId);
				objB.setAltMatDetails(matDetails);
				objB= objMan.saveAlternateMaterials(userInfo,objB);

				
				String msg="0";
				
				if(objB.isInserted()){
					msg="1";
				}
				 
				
				System.out.println("msg :"+msg);
			 	response.setContentType("text/html");
			 	response.getWriter().write(msg);
			 	
				userInfo=null;
				matDetails=null;
				objB=null;
				
				
			} 
			catch (Exception e) {
				e.printStackTrace();
			}

		}
	 
	 private String getAltMatParameter(HttpServletRequest request,int bomDetId){

		 StringBuffer buffer=new StringBuffer();
		 buffer.append("<alt_mat>");

		 String rowIds=request.getParameter("row_ids");
		 
		 System.out.println("rowIds :"+rowIds);

		 if(!rowIds.isEmpty()){

			 rowIds=rowIds.substring(0,rowIds.length()-1);
			 String[] arCheckedRows=rowIds.split(",");

			 for(String str:arCheckedRows){

				 buffer.append("<mat_details>");
				 buffer.append("<detail_id>");
				 buffer.append(bomDetId);
				 buffer.append("</detail_id>");

				 buffer.append("<alt_mat_id>");
				 buffer.append(StringEscapeUtils.escapeXml(request.getParameter("alt_mat_id_"+str)));
				 buffer.append("</alt_mat_id>");

				
				 
				 buffer.append("<alt_per>");
				 buffer.append(StringEscapeUtils.escapeXml(request.getParameter("alt_per_"+str)));
				 buffer.append("</alt_per>");
				

				 buffer.append("</mat_details>");
			 }
			 arCheckedRows=null;
			 rowIds=null;
		 }
		 rowIds=null;

		 buffer.append("</alt_mat>");
		 return buffer.toString();
	 }
	 
	 
	  public void doDeleteAltMaterial(HttpServletRequest request,HttpServletResponse response){
	    	try {
	    				TPCSUser ui=new UserInfo().getUserInfo(request);
	    				int matId=Validator.convertToInteger(request.getParameter("mat_id"));
	    				int detId=Validator.convertToInteger(request.getParameter("det_id"));
	    				
	    				
	    				BOM obj=new BOM();
	    				obj.setBomDetId(detId);
	    				obj.setAltMatId(matId);
	    				obj=objMan.deleteAltMaterial(ui,obj);;
	    			
	    				String status="0";
	    					if(obj.isDeleted()) {
	    						 
	    						status="1";
	    						
	    					}
	    				
	    				
	    				
	    				response.setContentType("text/html");
	    				response.getWriter().write(status);
	    				
	    				
	    				ui=null;
	    				obj=null;
	    		
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	  
	  public void doDeleteBOMComponet(HttpServletRequest request,HttpServletResponse response){
	    	try {
	    				TPCSUser ui=new UserInfo().getUserInfo(request);
	    				int compId=Validator.convertToInteger(request.getParameter("component_id"));
	    				int detId=Validator.convertToInteger(request.getParameter("det_id"));
	    				
	    				
	    				BOMDetail obj=new BOMDetail();
	    				obj.setBomDetailId(detId);
	    				obj.setComponentId(compId);
	    				obj=objMan.deleteBOMComponet(ui,obj);;
	    			
	    				String status="0";
	    					if(obj.isDeleted()) {
	    						 
	    						status="1";
	    						
	    					}
	    				
	    				
	    				
	    				response.setContentType("text/html");
	    				response.getWriter().write(status);
	    				
	    				
	    				ui=null;
	    				obj=null;
	    		
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
		public void doGetMatData(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
			try{
//				HttpSession session=request.getSession();
				TPCSUser ui=new UserInfo().getUserInfo(request);
				int matId=Validator.convertToInteger(request.getParameter("material_id"));
				
						
//				
//				System.out.println("trRefId :"+trRefId);
//				System.out.println("trTag :"+trTag);
				
				String strTechDetails=objMan.doGetMatData(ui,matId);
				response.setContentType("text/xml");
				response.getWriter().write(strTechDetails);

				
				ui=null;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	  
		
		public void doDeleteAttachment(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
		{
			try{

				TPCSUser ui=new UserInfo().getUserInfo(request);
				String realPath=(String) request.getSession().getAttribute("path");
				String folderPath=realPath+new UserInfo().getMaterialAttachPath(request);
				int bomId=Validator.convertToInteger(request.getParameter("bom_id"));
				String fileName=request.getParameter("file_name");
				String idx=request.getParameter("idx");

				boolean bFlag=objMan.deleteAttachment(bomId,fileName,ui);
				String msg="0";
				if(bFlag){
					File file=new File(folderPath+fileName);
					file.delete();
					msg="1";
					file=null;
				}
				msg=msg+"&1"+idx;
				response.getWriter().write(msg);

				
				ui=null;
				
			}
			catch(Exception e)
			{
				e.printStackTrace();

			}
		}

		public void doDisplayBOMLock(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
			try{  
				int bomId=Validator.convertToInteger(request.getParameter("bom_id"));
				int lockStatus=Validator.convertToInteger(request.getParameter("lock_status"));
				String bomNo= request.getParameter("bom_no");
				TPCSUser ui=(TPCSUser)request.getSession().getAttribute("user_info");
				request.setAttribute("bom_id", bomId);
				request.setAttribute("bom_no", bomNo);
				request.setAttribute("lock_status", lockStatus);
				
				BOM detList=objMan.getBOMDetailList(bomId,ui);
				request.setAttribute("detList", detList.getBomDetList());
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_BOM_LOCK, request,response);
		
			}catch(Exception e){
				e.printStackTrace();
				//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
			}
		}

		
		public void doSaveBOMLock(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
			try {
				TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
				ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
				int bomId=Validator.convertToInteger(request.getParameter("bom_id"));
				String bomNo= request.getParameter("bom_no");
				int lockStatus=Validator.convertToInteger(request.getParameter("lock_status"));
				String lockRemarks=request.getParameter("lock_remarks");

				
				System.out.println("BOM Lock "+lockRemarks);
				System.out.println("bomId :"+bomId);
				BOM objB=new BOM();
				objB.setBomId(bomId);
				objB.setBomNo(bomNo);
				objB.setLockStatus(lockStatus);
				objB.setLockRemarks(lockRemarks);

				int id = objMan.saveBomLock(objB,userInfo);

				if(id>0){
					
					if(lockStatus==1){
						request.setAttribute("success_message",bundle.getString("BOM.Lock"));	
					}else{
						request.setAttribute("success_message",bundle.getString("BOM.UnLock"));
					}
					 
				}
				else{
					
					if(lockStatus==1){
						request.setAttribute("error_message",bundle.getString("BOM.LockFailed"));	
					}else{
						request.setAttribute("error_message",bundle.getString("BOM.UnLockFailed"));
					}
				}

				doDisplayBOM(request, response); 
				objB=null;
				userInfo=null;
				bundle=null;
			}
			catch (Exception e) {
				// TODO: handle exception 
				e.printStackTrace();
			}
		}
		 public void doExportBOMTransactionToPDF(HttpServletRequest request,HttpServletResponse response){
				try{
					
				  ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request); 
		  
				  ExportToPdfTool.setRectangleMargin(30,40,800,760,0,0,0,Rectangle.BOTTOM);//Set page Margin for border in TableHeader class
					HttpSession session=request.getSession();
					TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
					//ExportToPdfTool.setRoundOff(userInfo);
					int bomId=Validator.convertToInteger(request.getParameter("bom_id"));

//					System.out.println("iOrderId S:"+iOrderId);
					Map<Integer,String> subdocument=(Map<Integer,String>)session.getAttribute("subdocuments");

					String screenName="BOM";
					
					float pageMarginLeft=Validator.convertToFloat(bundle.getString("ExportToPdf.MarginLeft"));
					float pageMarginRight=Validator.convertToFloat(bundle.getString("ExportToPdf.MarginRight"));
					float pageMarginTop=Validator.convertToFloat(bundle.getString("ExportToPdf.MarginTop"));
					float pageMarginBottom=Validator.convertToFloat(bundle.getString("ExportToPdf.MarginBottom"));
					
				//	Document document = new Document(PageSize.A4.rotate(), pageMarginLeft, pageMarginRight, pageMarginTop, pageMarginBottom);
					Document document = new Document(PageSize.LETTER.rotate(), pageMarginLeft, pageMarginRight, pageMarginTop, pageMarginBottom);
					ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
					PdfWriter writer = PdfWriter.getInstance(document, pdfReport);
					TableHeader event = new TableHeader();
					writer.setPageEvent(event);
					event=null;  
					
					BOM objB=new BOM();
					document.open();
			      	String fileName="";
			      	
			      	objB.setPdfFileName(fileName);
			      	
			        objB = objMan.getBOMPrintInfo(bomId,objB,userInfo); 
					
				      doCreateBOMPdf(request,bundle,userInfo,screenName,document,bomId,objB);
				      
				      document.close();
						
//						System.out.println("fileName :"+fileName);
						byte[] pdfBytes = pdfReport.toByteArray();
						
						
						event=null;
						pdfReport=null;
						document=null;
						subdocument=null;
						response.setContentType("application/pdf");
						response.setHeader("Content-Disposition", "attachment; filename=\""+screenName+"_"+objB.getHeader().getBomNo().replace("/", "_")+".pdf" + "\"");
						response.setContentLength(pdfBytes.length);
						response.getOutputStream().write(pdfBytes);
						response.getOutputStream().flush(); 
						objB=null;
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		 public void doCreateBOMPdf(HttpServletRequest request,ResourceBundle bundle,TPCSUser userInfo,String screenName,Document document,int bomId,BOM objB)
			{
				try
				{
					
					
					  
						BOM attachedFile = objMan.getFirstAttachFile(bomId,userInfo);
						String bomAttachPath=new UserInfo().getBOMAttachPath(request);
						String bomImagePath="";
						if(attachedFile.getUrl()!=null && !attachedFile.getUrl().isEmpty()){
							bomImagePath=bomAttachPath.concat(attachedFile.getUrl());
						}
						
						String clientBomFolder=request.getServletContext().getRealPath("/").concat(bomImagePath);
						
						
					 
						dynamicFieldsListBOMHeader = dynamicFieldManager.getDynamicFields(14, 29,userInfo);
						dynamicFieldsListBOMDetails = dynamicFieldManager.getDynamicFields(14, 30, userInfo);

			        
			        DynamicFields objDynFieldInfo = dynamicFieldManager.getDynamicFieldsInfo(14, 29,userInfo);
			        
			        
			        dynamicFieldsListBOMHeader = objB.getBomDynList();
					
//					String fontName=bundle.getString("ExportToPdf.FontName");
//					String addFontName=bundle.getString("ExportToPdf.FontAddr");
//					String courierFontName=bundle.getString("ExportToPdf.CourierFontName");
					String ArialFontName=bundle.getString("ExportToPdf.ArialFontName");
					String verdanaFontName=bundle.getString("ExportToPdf.VerdanaFontName");
					
					
//					short compNameFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.CompanyNameFontSize"));
//					short addFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.AddressFontSize"));
//					short titleFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.TitleFontSize"));
//					short headTableLblFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.HeadTableLblFontSize"));
//					short headTableDataFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.HeadTableDataFontSize"));
				//	short headTableInfoFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.HeadTableInfoFontSize"));
				//	short headerFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.HeaderFontSize"));
				//	short dataFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.DataFontSize"));
				//	short termsFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.TermsFontSize"));
				//	short dataHeadFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.DataHeaderFontSize"));
					//short dataSizeFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.DataSizeFontSize"));
				//	short dataCompFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.DataCompFontSize"));
					short tableWidthPer=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.TableWidthPer"));

					FontFactory.registerDirectories();
					BaseColor baseColor=new BaseColor(0,0,0);
				//	Font compNameFont= FontFactory.getFont(addFontName,compNameFontSize,Font.BOLD, baseColor);
				//	Font addFont= FontFactory.getFont(addFontName,addFontSize,Font.NORMAL, baseColor);
					Font titleFont= FontFactory.getFont(ArialFontName,12,Font.BOLD, baseColor);
					//Font titleFont1= FontFactory.getFont(fontName,13,Font.BOLD, baseColor);

					//Font headTableLblFont= FontFactory.getFont(fontName,headTableLblFontSize,Font.BOLD, baseColor);
					Font headTableDataFont= FontFactory.getFont(ArialFontName,9,Font.NORMAL, baseColor);
					
					Font detTableHeaderFont= FontFactory.getFont(verdanaFontName,9,Font.BOLD, baseColor);
				//	Font ItalicFont= FontFactory.getFont(fontName,headerFontSize,Font.BOLDITALIC, baseColor);
					Font detTableDataFont= FontFactory.getFont(ArialFontName,9,Font.NORMAL, baseColor);
//					Font detTableDataBoldFont= FontFactory.getFont(fontName,dataFontSize,Font.BOLD, baseColor);
//					Font detTableDataSizeFont= FontFactory.getFont(courierFontName,dataSizeFontSize,Font.NORMAL, baseColor);
//					Font detTableDataSizeBoldFont= FontFactory.getFont(courierFontName,dataSizeFontSize,Font.BOLD, baseColor);
//					Font detTableTermsDataFont= FontFactory.getFont(courierFontName,termsFontSize,Font.NORMAL, baseColor);
//					Font detTableTermsDataBoldFont= FontFactory.getFont(fontName,termsFontSize,Font.BOLD, baseColor);
//					Font detTableCompFont= FontFactory.getFont(courierFontName,dataCompFontSize,Font.NORMAL, baseColor);
//					Font detTablArailFontBold= FontFactory.getFont(ArialFontName,8,Font.BOLD, baseColor);
					
					
					       

					//-------------------------Company Area	
					float[] companyColumnWidths = new float[] {20f,50f,20f};
					
					PdfPTable companyTable=new PdfPTable(3);
					companyTable.setWidthPercentage(tableWidthPer); 
					companyTable.getDefaultCell().setUseAscender(true);
					companyTable.getDefaultCell().setUseDescender(true);
					companyTable.setWidths(companyColumnWidths);

					PdfPCell cell = null;
					if(attachedFile.getUrl()!=null && !attachedFile.getUrl().isEmpty()){
						Image image1 = Image.getInstance(clientBomFolder);
						cell = new PdfPCell();
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
						cell.addElement(image1);
						cell.setColspan(1);
						cell.setRowspan(2);
					    cell.setMinimumHeight(5);
						cell.setBorder(Rectangle.NO_BORDER);
						companyTable.addCell(cell);
						cell=null;
					}
					
					//Tittle Cell-------------------------------------------------
					Phrase ph=new Phrase(objB.getHeader().getCustomer(),titleFont);
					cell = new PdfPCell(ph); 
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setColspan(1);
					cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
					cell.setBorder(Rectangle.NO_BORDER);
					companyTable.addCell(cell);
					cell=null;
					
					ph=new Phrase("");
					cell = new PdfPCell(ph); 
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setColspan(1);
					cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setBorderWidth(0.5f);
					companyTable.addCell(cell);


					 ph=new Phrase("BOM SPECIFICATION SHEET",titleFont);
					cell = new PdfPCell(ph); 
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setColspan(1);
					cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
					 cell.setMinimumHeight(5);
					cell.setBorder(Rectangle.NO_BORDER);
					companyTable.addCell(cell);

					ph=new Phrase("");
					cell = new PdfPCell(ph); 
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setColspan(1);
					cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setBorderWidth(0.5f);
					companyTable.addCell(cell);
					ph=null;
					cell=null;
					
					
					
					String season="";
					
              	if(objDynFieldInfo.getDbFieldMap().containsKey("season")){
						
              		season=objB.getDynHeadeDataMap().get("season");
					}
	
	
					   ph=new Phrase(objB.getHeader().getBomNo()+"-"+season,headTableDataFont);
						cell = new PdfPCell(ph); 
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setColspan(2);
						cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
						cell.setBorder(Rectangle.NO_BORDER);
						companyTable.addCell(cell);
						ph=null;
						cell=null;

					
						
						 ph=new Phrase(season,headTableDataFont);
						cell = new PdfPCell(ph); 
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setColspan(1);
						cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
						cell.setBorder(Rectangle.NO_BORDER);
						companyTable.addCell(cell);
						ph=null;
						cell=null;
					

					
					
					//-----------------------Filter Area
					//Header Table 
					PdfPTable headerFilterTable=new PdfPTable(2);
					headerFilterTable.setWidthPercentage(100);
					headerFilterTable.getDefaultCell().setUseAscender(true);
					headerFilterTable.getDefaultCell().setUseDescender(true);
					float[] filterTbcolumnWidths = new float[] {40f,60f};
					headerFilterTable.setWidths(filterTbcolumnWidths);
					
					for(BOMDetail list:objB.getCompList()){
						
						
//						ExportToPdfTool.insertBorderCell(headerFilterTable,list.getComponent(), Element.ALIGN_LEFT, 1, headTableLblFont,Rectangle.TOP,Rectangle.RIGHT,0);
						
//						ExportToPdfTool.insertBorderCell(headerFilterTable,list.getMaterial(), Element.ALIGN_LEFT,1, headTableLblFont,Rectangle.TOP,Rectangle.RIGHT,0);
						
						 cell = new PdfPCell(new Phrase(list.getComponent(),headTableDataFont));
						 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						 cell.setColspan(1);
						 cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
						 cell.setBorder( Rectangle.BOTTOM | Rectangle.RIGHT);
						 cell.setBorderWidth(0.5f); 
						 headerFilterTable.addCell(cell);
						 cell=null;
						 
						 cell = new PdfPCell(new Phrase(list.getMaterial(),headTableDataFont));
						 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						 cell.setColspan(1);
						 cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
						 cell.setBorder( Rectangle.BOTTOM );
						 cell.setBorderWidth(0.5f); 
						 headerFilterTable.addCell(cell);
						 cell=null;
						
					}
					
				
					String sizeRange="";
					String construction="";
					
	              	if(objDynFieldInfo.getDbFieldMap().containsKey("size_range")){
							
	              		sizeRange=objB.getDynHeadeDataMap().get("size_range");
						}
		
	              	if(objDynFieldInfo.getDbFieldMap().containsKey("construction")){
						
	              		construction=objB.getDynHeadeDataMap().get("construction");
						}
	              	
	              	
					 cell = new PdfPCell(new Phrase("Size Ranges",headTableDataFont));
					 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					 cell.setColspan(1);
					 cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
					 cell.setBorder( Rectangle.BOTTOM  | Rectangle.RIGHT);
					 cell.setBorderWidth(0.5f); 
					 headerFilterTable.addCell(cell);
					 cell=null;
					 
					 cell = new PdfPCell(new Phrase(sizeRange,headTableDataFont));
					 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					 cell.setColspan(1);
					 cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
					 cell.setBorder(Rectangle.BOTTOM );
					 cell.setBorderWidth(0.5f); 
					 headerFilterTable.addCell(cell);
					 cell=null;

					 
					 cell = new PdfPCell(new Phrase("Construction",headTableDataFont));
					 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					 cell.setColspan(1);
					 cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
					 cell.setBorder(Rectangle.BOTTOM  | Rectangle.RIGHT);
					 cell.setBorderWidth(0.5f); 
					 headerFilterTable.addCell(cell);
					 cell=null;
					 
					 cell = new PdfPCell(new Phrase(construction,headTableDataFont));
					 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					 cell.setColspan(1);
					 cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
					 cell.setBorder( Rectangle.BOTTOM);
					 cell.setBorderWidth(0.5f); 
					 headerFilterTable.addCell(cell);
					 cell=null;
					 
					 
					 cell = new PdfPCell(new Phrase("Remarks: "+objB.getHeader().getRemarks(),headTableDataFont));
					 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					 cell.setColspan(2);
					 cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
					 cell.setBorder(Rectangle.NO_BORDER);
					 cell.setBorderWidth(0.5f); 
					 headerFilterTable.addCell(cell);
					 cell=null;
					 
					
					 if(attachedFile.getUrl()!=null && !attachedFile.getUrl().isEmpty()){
							Image image1 = Image.getInstance(clientBomFolder);
							cell = new PdfPCell();
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
							cell.addElement(image1);
							cell.setColspan(2);
							cell.setMinimumHeight(120);
							cell.setBorder(Rectangle.NO_BORDER);
							headerFilterTable.addCell(cell);
							cell=null;
							
							
							cell = new PdfPCell();
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
							cell.setColspan(2);
							cell.setMinimumHeight(65);
							cell.setBorder(Rectangle.NO_BORDER);
							headerFilterTable.addCell(cell);
							cell=null;
							
//							cell = new PdfPCell();
//							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//							cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
//							cell.setColspan(2);
//							cell.setMinimumHeight(120);
//							//cell.setBorder(Rectangle.NO_BORDER);
//							headerFilterTable.addCell(cell);
//							cell=null;
//							
//							cell = new PdfPCell();
//							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//							cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
//							cell.setColspan(2);
//							cell.setMinimumHeight(120);
//							//cell.setBorder(Rectangle.NO_BORDER);
//							headerFilterTable.addCell(cell);
//							cell=null;
						}
					 
					 
					//DAta Area
					 
				String tH1[]={"Material"
						,"Description"
						,"Part/spec"
						,"Supplier"
						,"Norms"
						,"Unit"
				};


				 
				//-----------------------DET Header Table 
				short repeatRows=1;
				float[] columnWidths = new float[] {18f, 36f, 17f, 17f,6f,6f};
				PdfPTable detailTable=new PdfPTable(6);
				detailTable.setWidthPercentage(tableWidthPer);
				detailTable.getDefaultCell().setUseAscender(true);
				detailTable.getDefaultCell().setUseDescender(true);
				detailTable.setWidths(columnWidths);
				
				detailTable.setHeaderRows(repeatRows); //To repeat table header in every page
				
				detailTable.setWidthPercentage(tableWidthPer);

				
					
			
				
				//------------------ADD companyTable to detTable
				cell = new PdfPCell(); 
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(6);
				cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
				cell.addElement(companyTable);
				cell.setBorder(Rectangle.TOP | 	Rectangle.LEFT  | 	Rectangle.RIGHT );
				cell.setBorderWidth(0.5f); 
				detailTable.addCell(cell);
				cell=null;
				
				//------------------ADD headerFilterTable to detTable
				cell = new PdfPCell(); 
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(6);
				cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
				cell.setBorder( 	Rectangle.TOP | Rectangle.LEFT  | 	Rectangle.RIGHT);
				cell.addElement(headerFilterTable);
				cell.setBorderWidth(0.5f); 
				detailTable.addCell(cell);
				cell=null;

					
					String heading="";
					String part="";

				 
				// ExportToPdfTool.insertNoBorderCell(detailTable,fobCif+" "+shipmentFrom, Element.ALIGN_RIGHT, 2, ItalicFont);
				 
				 
					//---------------------DaTA started
					for(BOMDetail list:objB.getBomDetList()){
						
						if(!heading.equalsIgnoreCase(list.getHeading())){
							 cell = new PdfPCell(new Phrase(list.getHeading(), detTableHeaderFont));
							 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							 cell.setColspan(6);
							 cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
							 cell.setBorderWidth(0.5f); 
							 detailTable.addCell(cell);
							 cell=null;
							 
								
							 
							 ExportToPdfTool.insertCell(detailTable,tH1[0], Element.ALIGN_CENTER, 1, detTableHeaderFont);
							 ExportToPdfTool.insertCell(detailTable,tH1[1], Element.ALIGN_CENTER, 1, detTableHeaderFont);
							 ExportToPdfTool.insertCell(detailTable,tH1[2], Element.ALIGN_CENTER, 1, detTableHeaderFont);
							 ExportToPdfTool.insertCell(detailTable,tH1[3], Element.ALIGN_CENTER, 1, detTableHeaderFont);
							 ExportToPdfTool.insertCell(detailTable,tH1[4], Element.ALIGN_CENTER, 1, detTableHeaderFont);
							 ExportToPdfTool.insertCell(detailTable,tH1[5], Element.ALIGN_CENTER, 1, detTableHeaderFont);
							 
//								ExportToPdfTool.insertBorderCellAlign(detailTable,tH1[1], Element.ALIGN_LEFT, Element.ALIGN_MIDDLE,1, detTableHeaderFont,0,0,0);
//								ExportToPdfTool.insertBorderCellAlign(detailTable,tH1[2], Element.ALIGN_LEFT,Element.ALIGN_MIDDLE, 1, detTableHeaderFont,0,0,0);
//								ExportToPdfTool.insertBorderCellAlign(detailTable,tH1[3], Element.ALIGN_LEFT, Element.ALIGN_MIDDLE,1, detTableHeaderFont,0,0,0);
//								ExportToPdfTool.insertBorderCellAlign(detailTable,tH1[4], Element.ALIGN_RIGHT,Element.ALIGN_MIDDLE, 1, detTableHeaderFont,0,0,0);
//								ExportToPdfTool.insertBorderCellAlign(detailTable,tH1[5], Element.ALIGN_RIGHT,Element.ALIGN_MIDDLE, 1, detTableHeaderFont,0,0,0);
						}
					
							
							
						 ExportToPdfTool.insertCell(detailTable,list.getGroupName(), Element.ALIGN_LEFT, 1, detTableDataFont);
						 ExportToPdfTool.insertCell(detailTable,list.getMaterial(), Element.ALIGN_LEFT, 1, detTableDataFont);
						 ExportToPdfTool.insertCell(detailTable,list.getPart(), Element.ALIGN_LEFT, 1, detTableDataFont);
						 ExportToPdfTool.insertCell(detailTable,list.getSupplier(), Element.ALIGN_LEFT, 1, detTableDataFont);
						 ExportToPdfTool.insertCell(detailTable,list.getRequiredQty(), Element.ALIGN_LEFT, 1, detTableDataFont);
						 ExportToPdfTool.insertCell(detailTable,list.getUOM(), Element.ALIGN_LEFT, 1, detTableDataFont);
						 
						 
//							ExportToPdfTool.insertBorderCellAlign(detailTable,list.getGroupName(), Element.ALIGN_LEFT, Element.ALIGN_TOP,1, detTableHeaderFont,0,0,0);
//							ExportToPdfTool.insertBorderCellAlign(detailTable,list.getMaterial(), Element.ALIGN_LEFT, Element.ALIGN_TOP,1, detTableHeaderFont,0,0,0);
//							ExportToPdfTool.insertBorderCellAlign(detailTable,list.getPart(), Element.ALIGN_LEFT,Element.ALIGN_TOP, 1, detTableHeaderFont,0,0,0);
//							ExportToPdfTool.insertBorderCellAlign(detailTable,list.getSupplier(), Element.ALIGN_LEFT, Element.ALIGN_TOP,2, detTableHeaderFont,0,0,0);
//							ExportToPdfTool.insertBorderCellAlign(detailTable,list.getRequiredQty(), Element.ALIGN_RIGHT,Element.ALIGN_TOP, 1, detTableHeaderFont,0,0,0);
//							ExportToPdfTool.insertBorderCellAlign(detailTable,list.getUOM(), Element.ALIGN_RIGHT,Element.ALIGN_TOP, 1, detTableHeaderFont,0,0,0);
							
						 
								
								
							heading=list.getHeading();
							}



					 
					 
					
						//document.add(detailTable);
						
						PdfPTable footTable=new PdfPTable(3);
						footTable.setWidthPercentage(100);
						float[] footcolumnWidths = new float[] {40f, 20f, 40f};
						footTable.setWidths(footcolumnWidths);
						
				

	                 
					//----------------------BOTTOM TABLE
					
					PdfPTable bottomTable=new PdfPTable(3);
					bottomTable.setWidthPercentage(100);
					float[] bottomcolumnWidths = new float[] {40f, 20f, 40f};
					bottomTable.setWidths(bottomcolumnWidths);
					bottomcolumnWidths=null;
				 
				
					
					 detailTable.setSplitLate(false);//To print large cell in current page if space exists
					document.add(detailTable);
					
				
					
					
					
					bottomTable=null;
					document=null;
					companyTable=null;
					headerFilterTable=null;
					filterTbcolumnWidths=null;
					columnWidths=null;
					detailTable=null;
					footTable=null;
					footcolumnWidths=null;
					companyColumnWidths=null;
					objB=null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		 
		public void doPrintBOMTransactionTOJasperPDF(HttpServletRequest request,HttpServletResponse response){
			try {

				TPCSUser ui=new UserInfo().getUserInfo(request);
//				ExportToPdfTool.setRoundOff(ui);
				int bomId=Validator.convertToInteger(request.getParameter("bom_id"));
				String screenName="BOM";

				
				System.out.println("bomId :"+bomId);
				BOM attachedFile = objMan.getFirstAttachFile(bomId,ui);
				String bomAttachPath=new UserInfo().getBOMAttachPath(request);
				
	

				
				String bomImagePath="";
				if(attachedFile.getUrl()!=null && !attachedFile.getUrl().isEmpty()){
					bomImagePath=bomAttachPath.concat(attachedFile.getUrl());
				}
				
				String clientBomFolder=request.getServletContext().getRealPath("/").concat(bomImagePath);
				
				 String componentJrxmlUrl=request.getSession().getServletContext().getRealPath("/report/Component.jasper");
				 String bomJrxmlUrl=request.getSession().getServletContext().getRealPath("/report/BOMDetail.jasper");
				 
				Map<String, String> param = new HashMap<String, String>();
				param.put("imagepath", clientBomFolder);
				param.put("componentReportpath", componentJrxmlUrl);
				param.put("bomDetailsReportpath", bomJrxmlUrl);
				
				System.out.println(clientBomFolder);
				System.out.println(componentJrxmlUrl);
				System.out.println(bomJrxmlUrl);
			
//				param.put("ValDf",ExportToPdfTool.valFormat);
				JasperReportPrinter jrp=new JasperReportPrinter();

//				Strisng jrxmlUrl=request.getSession().getServletContext().getRealPath("/report/ProformaInvoice.jrxml");

				
//				Connection con = new DatabaseConnection().getConnection(ui);
				jrp.printJasperReport(request,response,ui,"BOM.jrxml",param,screenName);
				
				
//				 String jrxmlUrl=request.getSession().getServletContext().getRealPath("/report/Component.jrxml");
//				 
//				 System.out.println(jrxmlUrl);
//			     JasperDesign jasperDesign = JRXmlLoader.load(jrxmlUrl);
//				  JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//				  
//					 String jrxmlUrl1=request.getSession().getServletContext().getRealPath("/report/BOMDetail.jrxml");
//					 
//				     JasperDesign jasperDesign1 = JRXmlLoader.load(jrxmlUrl1);
//					  JasperReport jasperReport1 = JasperCompileManager.compileReport(jasperDesign1);
//				byte buffer[]=jrp.readJasperReport(jrxmlUrl,con,param);   
	//
//				response.setContentType("application/pdf");
//				response.setContentLength(buffer.length);
//				ServletOutputStream servletOS=response.getOutputStream();
//				servletOS.write(buffer);
//				servletOS.flush();
//				servletOS.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		  public void doSaveBOMCopmponent(HttpServletRequest request,HttpServletResponse response){
		    	try {
		    		TPCSUser ui=new UserInfo().getUserInfo(request);
		    		
		    		int detId=Validator.convertToInteger(request.getParameter("bom_det_id"));
		    		int compId=Validator.convertToInteger(request.getParameter("component_id"));
		    		
		    		
		  			String bomComp=request.getParameter("bom_comp");
		  			String compLength=request.getParameter("comp_length")!=null&&!request.getParameter("comp_length").isEmpty()?request.getParameter("comp_length"):"0";
		  			String compWidth=request.getParameter("comp_width")!=null&&!request.getParameter("comp_width").isEmpty()?request.getParameter("comp_width"):"0";
		  			String noOfParts=request.getParameter("no_of_parts")!=null&&!request.getParameter("no_of_parts").isEmpty()?request.getParameter("no_of_parts"):"0";
		  			String reqQty=request.getParameter("reqd_qty")!=null&&!request.getParameter("reqd_qty").isEmpty()?request.getParameter("reqd_qty"):"0";
		  			
		  			String uom=request.getParameter("meas_uom");
		  			String mainUom=request.getParameter("main_uom");
		  			
		  			
		  			BOMDetail objB=new BOMDetail();
		  			objB.setComponent(bomComp);
		  			objB.setComponentId(compId);
		  			objB.setCompLength(compLength);
		  			objB.setCompWidth(compWidth);
		  			objB.setNoOfParts(noOfParts);
		  			objB.setRequiredQty(reqQty);
		  			objB.setUOM(uom);
		  			objB.setMainUom(mainUom);
		  			objB.setBomDetailId(detId);
		  	      	
		  			String status="";
		  			
		  		
		  			objB=objMan.saveBOMComponets(objB,ui);
		  				
		  				
		  				if(objB.isInserted()) {

							status="1";

						}
						else {
							status="0";
						}
		  				
		  			
		  				StringBuffer outputBuffer=new StringBuffer();
		  				outputBuffer.append("<bom_save>");
		  				outputBuffer.append("<bom_status>");
		  				outputBuffer.append("<msg>");
		  				outputBuffer.append(StringEscapeUtils.escapeXml(status));
		  				outputBuffer.append("</msg>");
		  				outputBuffer.append("<req_qty>");
		  				outputBuffer.append(StringEscapeUtils.escapeXml(objB.getReqQty()));
		  				outputBuffer.append("</req_qty>");
		  				outputBuffer.append("<bom_comp_id>");
		  				outputBuffer.append(StringEscapeUtils.escapeXml(String.valueOf(objB.getComponentId())));
		  				outputBuffer.append("</bom_comp_id>");
		  				outputBuffer.append("</bom_status>");
		  				outputBuffer.append("</bom_save>");
		  				
		  				
		  				response.setContentType("text/xml");
					response.getWriter().write(outputBuffer.toString());
					ui=null;
					objB=null;
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		  
		  public void doGetBOMNoDetails(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		    	
		    	try {
		    		
		    		TPCSUser userInfo = new UserInfo().getUserInfo(request);
		    		int custId=Validator.convertToInteger(request.getParameter("customer_id"));
		    		BOMDetail bodList=objMan.getBOMNoDetailList(userInfo,custId);
		    		String bomNoList = buildBOMNoList(bodList.getBomDetList(),userInfo);
		    		response.setContentType("text/html");
					response.getWriter().write(bomNoList);
					
					
					userInfo=null;
					bomNoList=null;
					bodList=null;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace(); 
				}
		    
				} 
		  public String buildBOMNoList(List<BOMDetail> bodList,TPCSUser userInfo)
			{
			  
			  
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("<div class=\"row table-responsive\">");
			buffer.append("<table class=\"table table-bordered table-condensed\">");

			buffer.append("<thead>");

			buffer.append("<tr class=\"header\">");	 						
			buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini first\">Action</th>");
			buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini\">BOM No</th>");
			buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini\">Customer</th>");
		    buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini\">Product</th>");
		    buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini\">Material</th>");
		    buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini\">Component</th>");
			buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini\">Variant</th>");
			buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini\">UOM</th>");
			buffer.append("<th valign=\"middle\" nowrap=\"nowrap\" align=\"center\" class=\"w-mini\">Req Qty</th>");
			
			buffer.append("</tr>");
			buffer.append("</thead>");
			buffer.append("<tbody>");
			 String previous_no ="" ; 
			for(BOMDetail objBOD:bodList){
				buffer.append("<tr>");
				buffer.append("<td valign=\"middle\"  nowrap=\"nowrap\" align=\"left\">");
				 boolean equal = previous_no.equals(String.valueOf(objBOD.getBomId()));
				 if(!equal)
			        {
					 buffer.append("<a href=\"javascript:selectedBOMNo('"+objBOD.getBomNo()+"','"+objBOD.getBomId()+"','"+objBOD.getCustomerId()+"','"+objBOD.getCustomer()+"')\">select</a>  "); 
			        }
				 else{
					 buffer.append("&nbsp;");  
				 }
				
				buffer.append("</td>");
				
				buffer.append("<td>");
				buffer.append(objBOD.getBomNo());
				buffer.append("</td>");
				buffer.append("<td>");
				buffer.append(objBOD.getCustomer());
				buffer.append("</td>");
				buffer.append("<td>");
				buffer.append(objBOD.getProduct());
				buffer.append("</td>");
				buffer.append("<td>");
				buffer.append(objBOD.getMaterial());
				buffer.append("</td>");
				buffer.append("<td>");
				buffer.append(objBOD.getComponent());
				buffer.append("</td>");
				buffer.append("<td>");
				buffer.append(objBOD.getVariant());
				buffer.append("</td>");
				buffer.append("<td>");
				buffer.append(objBOD.getUOM());
				buffer.append("</td>");
				buffer.append("<td>");
				buffer.append(objBOD.getRequiredQty());
				buffer.append("</td>");
				buffer.append("</tr>");
				
				 previous_no=String.valueOf(objBOD.getBomId());
			}
				buffer.append("</tbody>");
				buffer.append("</table>");
				buffer.append("</div>");
			 return buffer.toString();
		
	}
		  
		  public void doAddBOMDetail(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
				try{  
					HttpSession session=request.getSession();
					TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
					BOM objG=new BOM();
					Map<String,String> fileMap=new LinkedHashMap<String, String>();
					ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
					dynamicFieldsListBOMHeader  = (List<DynamicFields>) session.getAttribute("dynamicFieldsListBOMHeader");
			           
			            for(DynamicFields dynamicFields: dynamicFieldsListBOMHeader ) {
			          	  //Checking the condition - if dynamic field i.e., isFixed  =  false
			          	  if(!dynamicFields.isFixed()) {
			          		  if(dynamicFields.getFieldTypeName().equalsIgnoreCase("checkbox")){
			          			  String checkBoxValueArray[] = request.getParameterValues(dynamicFields.getPageFieldName());
			          			  String checkBoxValues = "";
			          			  if(checkBoxValueArray!=null) {
			          				  for(String checkBoxValue : checkBoxValueArray){
			              				  checkBoxValues = checkBoxValues + checkBoxValue + ",";
			              			  }
			              			  dynamicFields.setFieldValue(checkBoxValues);
			          			  }
			          		  } else {
			          			  dynamicFields.setFieldValue((String) request.getParameter(dynamicFields.getPageFieldName()));
			          		  }
			          	  }
			            }
					//calling xml for dynamic fields
			            String sqlxml = generateXMLforDynamicFields(dynamicFieldsListBOMHeader );

			            objG=getHeader(request,userInfo);


					//added for dynamic fields 
			            objG.setSqlxmlDynamicFields(sqlxml);
					int trId = objMan.insertBOMFromParentBOM(userInfo,objG);

					

					if(trId>0){
						request.setAttribute("success_message",bundle.getString("BOM.BOMInserted"));
			
					}
					else{
						request.setAttribute("error_message",bundle.getString("BOM.BOMNotInserted"));
					}
					
					objG.setHeaderMode("e");
					doGetNewAndEditBOM(request,response,trId,objG);
						 
						userInfo=null;
						objG=null;
						dynamicFieldsListBOMHeader=null;

				}catch(Exception e){
					e.printStackTrace();
					//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
				}
			}
			
		  private BOM getHeader(HttpServletRequest request,TPCSUser ui) {
				// TODO Auto-generated method stub
			  BOM objG=new BOM();
				BOMHeader bomh=new BOMHeader();
				bomh.setBomId(Validator.convertToInteger(request.getParameter("bom_id")));


				bomh.setBomNo(request.getParameter("bom_no"));
				bomh.setBomDate(request.getParameter("tr_date"));
				bomh.setCustomer(request.getParameter("customer"));
				bomh.setCustomerId(Validator.convertToInteger(request.getParameter("customer_id")));
				
				bomh.setParentBomNo(request.getParameter("parent_bom_no"));
				bomh.setParentBomId(Validator.convertToInteger(request.getParameter("parent_bom_id")));
				bomh.setProductId(Validator.convertToInteger(request.getParameter("product_id")));
				bomh.setProduct(request.getParameter("product"));

				bomh.setRemarks(request.getParameter("bom_msg"));
				
				
				objG.setHeader(bomh);
				objG.setHeaderMode(request.getParameter("head_mode"));
				return objG;
			}	
		  
		  public void doChkBOMNoExist(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
				try{
//					HttpSession session=request.getSession();
					TPCSUser userInfo = new UserInfo().getUserInfo(request);
			      	  
			      	  String bomNo=request.getParameter("bom_no");
			      	int bomId=Validator.convertToInteger(request.getParameter("bom_id"));
			      	BOM objB=new BOM();
			      	BOMHeader bomH=new BOMHeader();
			      	bomH.setBomNo(bomNo);
			      	bomH.setBomId(bomId);
			      	
			      	objB.setHeader(bomH);
			            
			            boolean isExists=objMan.isBOMNoExist(objB,userInfo);
					
					String msg="";
					if(isExists){
						msg="1";
					}else{
						msg="2";
					}
					response.setContentType("text/html");
					response.getWriter().write(msg);

					userInfo=null;
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		  public void doshowBOMDetails(HttpServletRequest request,HttpServletResponse response){
				try {
					int bomId=Validator.convertToInteger(request.getParameter("bom_id"));
					TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
					
					String coDetailsList=objMan.getBOMDetailsgrid(userInfo,bomId);
					response.setContentType("text/html");
					response.getWriter().write(coDetailsList.toString());
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}	  
		  	 
		  
		 
		  
	public static CurrencyManager curMan=new CurrencyManager();
	List<Currency> currency_list;
	private List<DynamicFieldEvents> dynamicFormEvents = null;
	private List<DynamicFieldEvents> dynamicHeaderFieldEvents = null;
	private List<DynamicFieldEvents> dynamicDetailFieldEvents = null;
	private List<DynamicFieldEvents> dynamicHeaderpickListOptions = null;
	private List<DynamicFieldEvents> dynamicDetailpickListOptions = null;
	private DynamicFormStructure dynamicFormStructureBOMHeader = null;
	private DynamicFormStructure dynamicFormStructureBOMDetails= null;
	private DynamicFieldManager  dynamicFieldManager = new DynamicFieldManager();
	private List<DynamicFields> dynamicFieldsListBOMHeader = null;
	private List<DynamicFields> dynamicFieldsListBOMDetails=null;
	private List<DynamicFields> dynamicfields = null; 
	DynamicOrderManager DynMan=new DynamicOrderManager();
	private UserRightsManager objRight = new UserRightsManager();
	BOMManager objMan=new BOMManager();
	UserRights rights=null;	
}
