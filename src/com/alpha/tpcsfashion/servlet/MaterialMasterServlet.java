package com.alpha.tpcsfashion.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;

import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.Company;
import com.alpha.tpcsfashion.beans.DynamicFieldEvents;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.DynamicFormStructure;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.MaterialMaster;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
//import com.alpha.tpcsfashion.dao.SalesInvoiceDAO;
import com.alpha.tpcsfashion.gridmaker.MaterialGridMaker;
import com.alpha.tpcsfashion.model.ColumnPreferenceManager;
import com.alpha.tpcsfashion.model.CompanyAndYearSelectionManager;
import com.alpha.tpcsfashion.model.DynamicFieldManager;
import com.alpha.tpcsfashion.model.MaterialMasterManager;
//import com.alpha.tpcsfashion.model.SalesInvoiceManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.UserInfo;
import com.alpha.tpcsfashion.util.Validator;

public class MaterialMasterServlet {


	public void doDisplayMaterialMaster(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session=request.getSession();
			//			int iDivWidth = Validator.convertToInteger(request.getParameter("divWidth")); 
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			PageConfig pc=new PageConfig(request.getParameter("pageno"));

			int  ipageno=1;
			rights =objRight.getUserRights(SubdocumentId.MATERIAL_MASTER, userInfo);
			session.setAttribute("rights",rights);
			session.setAttribute("subdocument_id", SubdocumentId.MATERIAL_MASTER);
			String requestType=request.getParameter("request_type");
			String strSearchCriteria=null;
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request); 

			//
			if("Search".equalsIgnoreCase(requestType)){       
				strSearchCriteria=getSearchCriteria(request);
			}


			int pageCount= objMan.getPageCount(userInfo,pc, strSearchCriteria);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);

			rights =objRight.getUserRights(SubdocumentId.MATERIAL_MASTER, userInfo);
			session.setAttribute("material_rights",rights);
			doGetUserRights(request,response);
			session.setAttribute("subdocument_id", SubdocumentId.MATERIAL_MASTER);

			ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
			ColumnPreference DataList=cpm.getVisibleColumnNames(SubdocumentId.MATERIAL_MASTER, userInfo);
			Map<String,String> visColMap=cpm.getVisibleColumnNamesMap(SubdocumentId.MATERIAL_MASTER,Integer.parseInt((String)request.getSession().getAttribute("login_user_id")),userInfo);
			List<String> listSelectedColumns=DataList.getVisibleColumns(); 
			List<String> orderBy=DataList.getOrderBy(); 

			MaterialGridMaker objUtil=new MaterialGridMaker();
			String materialAttachPath=new UserInfo().getMaterialAttachPath(request);

			MaterialMaster mat= objMan.getAllMaterialOnColumnMapping(pc,userInfo,strSearchCriteria,orderBy,3,materialAttachPath);
			List<Map<String,String>> objList =mat.getMaterialMapList();

			String Grid = objUtil.formMaterialGrid(visColMap,listSelectedColumns, objList, ipageno, bundle, rights,pc);
			request.setAttribute("mat_grid", Grid);

			dynamicfields = objMan.getDynamicFields("3",userInfo,1);
			session.setAttribute("fixedfields", dynamicfields);

			dynamicfields = objMan.getDynamicFields("3",userInfo,0);
			session.setAttribute("dynamicfields", dynamicfields);

			request.setAttribute("subdocument_id", SubdocumentId.MATERIAL_MASTER);  

			if("Search".equalsIgnoreCase(requestType)){       
				getAndSetAttributes(request);
			}
			session.setAttribute("seletedScreenId",3);
			
			request.setAttribute("invoke_servlet", "MaterialMasterServlet");
			request.setAttribute("invoke_method", "doDisplayMaterialMaster");

			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_MATERIAL_MASTER, request,response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void doGetUserRights(HttpServletRequest request, HttpServletResponse response)
	{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		HttpSession session=request.getSession();
		
		rights =objRight.getUserRights(SubdocumentId.MATERIAL_MASTER, userInfo);
		session.setAttribute("material_rights",rights);
		
	}
	
	public void getAndSetAttributes(HttpServletRequest request){
		request.setAttribute("group_name",request.getParameter("group_name"));
		request.setAttribute("group_id",request.getParameter("group_id"));
		request.setAttribute("mat_code",request.getParameter("mat_code"));
		request.setAttribute("mat_name",request.getParameter("mat_name"));
		request.setAttribute("mat_id",request.getParameter("mat_id"));
		request.setAttribute("material_status",request.getParameter("material_status"));
//		request.setAttribute("material_status",request.getParameter("material_status"));
//		request.setAttribute("stock_location",request.getParameter("stock_location"));
//		request.setAttribute("bin_no",request.getParameter("bin_no"));
//		request.setAttribute("material_type",request.getParameter("material_type"));
	}


	public String getSearchCriteria(HttpServletRequest request){

		
		TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
		int matId=Validator.convertToInteger(request.getParameter("mat_id"));
		int groupId=Validator.convertToInteger(request.getParameter("group_id"));
		String matCode=request.getParameter("mat_code");
		String matName=request.getParameter("mat_name");
//		String stockLocation=request.getParameter("stock_location");
//		String binNo=request.getParameter("bin_no");
//		String materialType=request.getParameter("material_type");
		String materialStatus=request.getParameter("material_status");
		
        
		/*	int dynamicfieldId_1 = Integer.parseInt(request.getParameter("dynamic_field_1"));
		int dynamicfieldId_2 = Integer.parseInt(request.getParameter("dynamic_field_2"));
		int dynamicfieldId_3 = Integer.parseInt(request.getParameter("dynamic_field_3"));
		String dynamicfieldValue_1 =request.getParameter("dynamic_field_1_val");
		String dynamicfieldValue_2 =request.getParameter("dynamic_field_2_val");
		String dynamicfieldValue_3 =request.getParameter("dynamic_field_3_val");
		String dynamicdbName_1=null;
		String dynamicdbName_2=null;
		String dynamicdbName_3=null;
		int dynamicTableId_1=0;
		int dynamicTableId_2=0;
		int dynamicTableId_3=0;*/
		String matSearchCriteria="";

		/*	if(dynamicfieldId_1!=-1)
	    {
	    dynamicdbName_1=objMan.getDynamicDbName(dynamicfieldId_1,userInfo);
	    dynamicTableId_1=objMan.getDynamicTableId(dynamicfieldId_1,userInfo);
	    if(dynamicTableId_1==2)
	    {
	    	if(dynamicfieldValue_1!=null && dynamicfieldValue_1.trim().length()>0){
	    		matSearchCriteria = " mdf."+dynamicdbName_1+" LIKE '%"+dynamicfieldValue_1+"%' AND ";
	    	    }
	    }
	    }

	    if(dynamicfieldId_2!=-1)
	    {
	    dynamicdbName_2=objMan.getDynamicDbName(dynamicfieldId_2,userInfo);
	    dynamicTableId_2=objMan.getDynamicTableId(dynamicfieldId_2,userInfo);

	     if(dynamicTableId_2==2)
	    {
	    	if(dynamicfieldValue_2!=null && dynamicfieldValue_2.trim().length()>0){
	    		matSearchCriteria = " mdf."+dynamicdbName_2+" LIKE '%"+dynamicfieldValue_2+"%' AND ";
	    	    }
	    }
	    }

	    if(dynamicfieldId_3!=-1)
	    {
	    dynamicdbName_3=objMan.getDynamicDbName(dynamicfieldId_3,userInfo);
	    dynamicTableId_3=objMan.getDynamicTableId(dynamicfieldId_3,userInfo);

	     if(dynamicTableId_3==2)
	    {
	    	if(dynamicfieldValue_3!=null && dynamicfieldValue_3.trim().length()>0){
	    		matSearchCriteria = " mdf."+dynamicdbName_3+" LIKE '%"+dynamicfieldValue_3+"%' AND ";
	           	    }
	    }*/
		// }
		
		
		
		if(matId>0)
		{
			matSearchCriteria=matSearchCriteria+" i.item_id="+matId+"  and";
			
		}
		
		if(matCode!=null && !matCode.isEmpty())
		{
			matSearchCriteria=matSearchCriteria+" i.item_code='"+matCode+"'  and";
		}
		if(matName!=null && !matName.isEmpty())
		{
			matSearchCriteria=matSearchCriteria+" i.item_name='"+matName+"' and";
		}
//		   if(!materialStatus.isEmpty() && materialStatus.equals("Active")){
//			   matSearchCriteria=matSearchCriteria+" i.is_active="+materialStatus;
//			}
//		if(stockLocation!=null && !stockLocation.isEmpty())
//		{
//			matSearchCriteria=matSearchCriteria+"   m.stock_location='"+stockLocation.replace("'", "''")+"'  and";
//		}
//		if(binNo!=null && !binNo.isEmpty())
//		{
//			matSearchCriteria=matSearchCriteria+"   m.bin_no='"+binNo.replace("'", "''")+"'  and";
//		}
//		if(materialType!=null && !materialType.isEmpty() && materialType.equals("TrackInventory"))
//		{
//			matSearchCriteria=matSearchCriteria+"   m.track_inventory=1  and";
//		}
//		if(materialType!=null && !materialType.isEmpty() && materialType.equals("PurchaseMaterial"))
//		{
//			matSearchCriteria=matSearchCriteria+"   m.purchase_applicable=1  and";
//		}
//		if(materialType!=null && !materialType.isEmpty() && materialType.equals("SellMaterial"))
//		{
//			matSearchCriteria=matSearchCriteria+"   m.sales_applicable=1  and";
//		}
		if(materialStatus!=null && !materialStatus.isEmpty() && materialStatus.equals("Active"))
		{
			matSearchCriteria=matSearchCriteria+"   i.is_active=1  and";
		}
		
		if(materialStatus!=null && !materialStatus.isEmpty() && materialStatus.equals("Closed"))
		{
			matSearchCriteria=matSearchCriteria+"   i.is_active=0  and";
		}
		if(matSearchCriteria!=null && !matSearchCriteria.isEmpty()){
			matSearchCriteria = matSearchCriteria.substring(1,matSearchCriteria.length()-4);
		}
		
		System.out.println("Search criteria:" +matSearchCriteria.toString());

		return matSearchCriteria;
		
	}
	public void doDisplayMaterialAfterColumnOrganized(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{    
			HttpSession session=request.getSession();  
			TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			String strSelectedColumns = request.getParameter("sel_columns");  
			String strTotalColumns = request.getParameter("tot_columns");  
			int iPageno = Integer.parseInt(request.getParameter("pageno"));

			//          int iDivWidth = Validator.convertToInteger((request.getParameter("divWidth"))); 
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);  
			String strSearchCriteria="";
			int subdocumentId =SubdocumentId.MATERIAL_MASTER;
			UserRights matRights=(UserRights) session.getAttribute("material_rights");


			String strRequestType=request.getParameter("request_type");
			int  ipageno=1;
			if("Search".equalsIgnoreCase(strRequestType)){	          
				strSearchCriteria=(String)request.getSession().getAttribute("search_query");
			}
			else{
				if(request.getSession().getAttribute("search_query")!=null) 
					request.getSession().removeAttribute("search_query"); 
			}
			int pageCount= objMan.getPageCount(userInfo,pc, strSearchCriteria);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);
			request.setAttribute("subdocument_id", SubdocumentId.MATERIAL_MASTER); 
			ColumnPreferenceManager cpm=new ColumnPreferenceManager();    
			Map<Integer, String>  columns = cpm.getAllColumns(subdocumentId,userInfo);

			boolean bFlag = cpm.updateColumnPreferences(strTotalColumns,subdocumentId,userInfo);
			MaterialGridMaker objUtil=new MaterialGridMaker();
			ColumnPreference DataList=cpm.getVisibleColumnNames(subdocumentId, userInfo);
			Map<String,String> visColMap=cpm.getVisibleColumnNamesMap(SubdocumentId.MATERIAL_MASTER,Integer.parseInt((String)request.getSession().getAttribute("login_user_id")),userInfo);
			List<String> listSelectedColumns=DataList.getVisibleColumns(); 
			List<String> orderBy=DataList.getOrderBy();
			String materialAttachPath=new UserInfo().getMaterialAttachPath(request);
			MaterialMaster mat= objMan.getAllMaterialOnColumnMapping(pc,userInfo,strSearchCriteria,orderBy,3,materialAttachPath);
			List<Map<String,String>> objList =mat.getMaterialMapList();

			String Grid = objUtil.formMaterialGrid(visColMap,listSelectedColumns, objList, ipageno, bundle, matRights,pc);
			request.setAttribute("mat_grid", Grid); 
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_MATERIAL_MASTER,request,response);
		}catch(Exception e){ 
			e.printStackTrace();      
		}
	}

	public void doNewMaterialMaster(HttpServletRequest request ,HttpServletResponse response)throws ServletException,IOException{
		try {
			request.setAttribute("mode",request.getParameter("mode"));
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");

//			Map<String,String> locationConfigMap=userInfo.getLocatonConfigMap();
//			String multipleCurrency=locationConfigMap.get("1");
//			request.setAttribute("multiple_currency", multipleCurrency);
//			
//			List<Currency> curList=objMan.getCurrencyList(userInfo);
//			request.setAttribute("currency_list", curList);

			dynamicFieldsListMaterial = dynamicFieldManager.getDynamicFields(3, 3,userInfo);

        	request.setAttribute("dynamicFieldsListMaterial", dynamicFieldsListMaterial);
        	request.getSession().setAttribute("dynamicFieldsListMaterial", dynamicFieldsListMaterial);

	        dynamicFormStructureMaterial = dynamicFieldManager.getDynamicFormStructure(3, userInfo);
	        request.setAttribute("dynamicFormStructureMaterial", dynamicFormStructureMaterial);

	        dynamicFormEvents =dynamicFieldManager.getDynamicFormEvents(3,userInfo);
	        request.setAttribute("dynamicFormEvents", dynamicFormEvents);

	        dynamicMaterialfieldEvents =dynamicFieldManager.getDynamicFieldEvents(3,3,userInfo);
	        request.setAttribute("dynamicMaterialfieldEvents", dynamicMaterialfieldEvents);

	        dynamicMaterialpickListOptions =dynamicFieldManager.getPickListOptions(3,userInfo);
	        request.setAttribute("dynamicMaterialpickListOptions", dynamicMaterialpickListOptions);
			
			doGetMaterialMasterFields(request,response);

//			List<TaxGroup> salesGroupName=siMan.getTaxGroupName(userInfo);
//			request.setAttribute("tax_group_list", salesGroupName);



			//			JsonFileCreatorServlet obj=new JsonFileCreatorServlet();
			//			obj.createJsonFileFromAddMaster(request,"material"," select Material_id,Material_name from material order by Material_id ");//For page JSON
			//			obj.createJsonFileFromAddMaster(request,"view_material","select Material_id,Material_name from material order by Material_id");//For view page JSON Without Add New Option
			
			doGetUserRights(request,response);
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_MATERIAL_MASTER, request,response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void  doGetAddNewMaterialMaster(HttpServletRequest request ,HttpServletResponse response)throws ServletException,IOException{
		try {
			doGetMaterialMasterFields(request,response);

			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.MATERIAL_CONTROLS, request,response);

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public void doGetMaterialMasterFields(HttpServletRequest request ,HttpServletResponse response)throws ServletException,IOException{
		try {

			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			dynamicFieldsListMaterial = dynamicFieldManager.getDynamicFields(3, 3,userInfo);

			request.setAttribute("dynamicFieldsListMaterial", dynamicFieldsListMaterial);
			request.getSession().setAttribute("dynamicFieldsListMaterial", dynamicFieldsListMaterial);

			dynamicFormStructureMaterial = dynamicFieldManager.getDynamicFormStructure(3, userInfo);
			request.setAttribute("dynamicFormStructureMaterial", dynamicFormStructureMaterial);

			dynamicFormEvents =dynamicFieldManager.getDynamicFormEvents(3,userInfo);
			request.setAttribute("dynamicFormEvents", dynamicFormEvents);

			dynamicMaterialfieldEvents =dynamicFieldManager.getDynamicFieldEvents(3,3,userInfo);
			request.setAttribute("dynamicMaterialfieldEvents", dynamicMaterialfieldEvents);

			dynamicMaterialpickListOptions =dynamicFieldManager.getPickListOptions(3,userInfo);
			request.setAttribute("dynamicMaterialpickListOptions", dynamicMaterialpickListOptions);


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void doEditMaterialMaster(HttpServletRequest request ,HttpServletResponse response)throws ServletException,IOException{
		try{
			
			
			
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			String mode=request.getParameter("mode");
			MaterialMaster objMatInfo=new MaterialMaster();	
			request.setAttribute("mode", mode);
			int matId = Validator.convertToInteger(request.getParameter("mat_id"));
//			System.out.println("Mat id:"+matId);
			MaterialMaster attachFiles = objMan.getAttachFiles(matId,userInfo);
			String materialAttachPath=new UserInfo().getMaterialAttachPath(request);
			request.setAttribute("attached_files", attachFiles.getAttachments());
			request.setAttribute("images", attachFiles.getImages());
			request.setAttribute("materialAttachPath", materialAttachPath);
//			System.out.println("materialAttachPath="+materialAttachPath);

			objMatInfo = objMan.getMaterialMasterInfo(userInfo,matId,3);
  
			request.setAttribute("material_info", objMatInfo);
			request.setAttribute("mat_id",matId);
			request.setAttribute("groupType",objMatInfo.getGroupType());
			
			dynamicFieldsListMaterial = objMatInfo.getMatDynList();
			request.getSession().setAttribute("dynamicFieldsListMaterial", dynamicFieldsListMaterial);
			request.setAttribute("dynamicFieldsListMaterial", dynamicFieldsListMaterial);

			dynamicFormEvents =dynamicFieldManager.getDynamicFormEvents(3,userInfo);
			request.setAttribute("dynamicFormEvents", dynamicFormEvents);

			dynamicMaterialfieldEvents =dynamicFieldManager.getDynamicFieldEvents(3,3,userInfo);
			request.setAttribute("dynamicMaterialfieldEvents", dynamicMaterialfieldEvents);

			dynamicMaterialpickListOptions =dynamicFieldManager.getPickListOptions(3,userInfo);
			request.setAttribute("dynamicMaterialpickListOptions", dynamicMaterialpickListOptions);

			dynamicFormStructureMaterial = dynamicFieldManager.getDynamicFormStructure(3, userInfo);
			request.setAttribute("dynamicFormStructureMaterial", dynamicFormStructureMaterial);

			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_MATERIAL_MASTER,request,response);
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}


	public void doSaveMaterialMaster(HttpServletRequest request,HttpServletResponse response,FileImport objBean) throws ServletException, IOException {
		try {

			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			String realPath=(String) request.getSession().getAttribute("path");
			String folderPath=realPath+new UserInfo().getMaterialAttachPath(request);
			objBean.setFolderPath(folderPath);

			HttpSession session = request.getSession();

			dynamicFieldsListMaterial = (List<DynamicFields>) session.getAttribute("dynamicFieldsListMaterial");

			List<String> chkDetail=new ArrayList<String>();
			for(DynamicFields dynamicFields: dynamicFieldsListMaterial) {
				//Checking the condition - if dynamic field i.e., isFixed  =  false
				if(!dynamicFields.isFixed()) {
					chkDetail.add(dynamicFields.getPageFieldName());
				}

			}

			Map<String,String> fileMap=new LinkedHashMap<String, String>();
			FileItem item =null;
			Iterator in = objBean.getFileItems().iterator();
			while ( in.hasNext () ){ 
				item = (FileItem)in.next();
				

				if ( item.isFormField() ){

					fileMap.put(item.getFieldName(), item.getString());

					if(chkDetail.contains(item.getFieldName())){

						for(DynamicFields dynamicFields: dynamicFieldsListMaterial) {
							//Checking the condition - if dynamic field i.e., isFixed  =  false
									if(!dynamicFields.isFixed()) {
										if(dynamicFields.getFieldTypeName().equalsIgnoreCase("checkbox")){


											List<String> checkboxValues = new ArrayList<String>();

											if (dynamicFields.getPageFieldName().equals(item.getFieldName())) {
												checkboxValues.add(item.getString());

												String checkBoxValueArray[] = checkboxValues.toArray(new String[checkboxValues.size()]);

												String checkBoxValues = "";
												if(checkBoxValueArray!=null) {
													for(String checkBoxValue : checkBoxValueArray){
														checkBoxValues = checkBoxValues + checkBoxValue + ",";
													}
													dynamicFields.setFieldValue(checkBoxValues);
												}
											}
										} 
										else {
											if (dynamicFields.getPageFieldName().equals(item.getFieldName())) {
												dynamicFields.setFieldValue((String) StringEscapeUtils.escapeXml(item.getString()));//request.getParameter(dynamicFields.getPageFieldName()));
											}
										}
									}
						}
					}

				}
			}

			//calling xml for dynamic fields
			String sqlxml = generateXMLforDynamicFields(dynamicFieldsListMaterial);

			MaterialMaster objMatP=getMaterialMasterParameter(fileMap,request);
			//added for dynamic fields 
			objMatP.setSqlxmlDynamicFields(sqlxml);
			//	      boolean isExist=false;
			int matId=0;
			String mode=fileMap.get("mode");
			String saveType=fileMap.get("save_type");
			// existence checking
//				      isExist=objMan.isMaterialExist(userInfo, objMatP);

			MaterialMaster mat= objMan.insertMaterialMaster(userInfo,objMatP,objBean);

			matId=mat.getMatId();

		if(!mat.isMaterialExists()) {
				if(matId>0) {
					if(saveType.equals("1")){
						request.setAttribute("mode", "e");
					}
					else if(saveType.equals("2")){
						request.setAttribute("mode", "n");
					}
					request.setAttribute("success_message", "inserted successfully");
				}
				else{
					request.setAttribute("mode", mode);
					request.setAttribute("error_message", "insertion failed");
				}
			}
			else {
				request.setAttribute("mode", mode);
				request.setAttribute("error_message","Specifications or Item code or Item Name is already Exsist.");
			}

			request.setAttribute("mat_id",matId);

			dynamicFieldsListMaterial = dynamicFieldManager.getDynamicFields(3, 3,userInfo);
			request.setAttribute("dynamicFieldsListMaterial", dynamicFieldsListMaterial);
			request.getSession().setAttribute("dynamicFieldsListMaterial", dynamicFieldsListMaterial);

			dynamicFormStructureMaterial = dynamicFieldManager.getDynamicFormStructure(3, userInfo);
			request.setAttribute("dynamicFormStructureMaterial", dynamicFormStructureMaterial);

			dynamicFormEvents =dynamicFieldManager.getDynamicFormEvents(3,userInfo);
			request.setAttribute("dynamicFormEvents", dynamicFormEvents);

			dynamicMaterialfieldEvents =dynamicFieldManager.getDynamicFieldEvents(3,3,userInfo);
			request.setAttribute("dynamicMaterialfieldEvents", dynamicMaterialfieldEvents);

			dynamicMaterialpickListOptions =dynamicFieldManager.getPickListOptions(3,userInfo);
			request.setAttribute("dynamicMaterialpickListOptions", dynamicMaterialpickListOptions);

		

			if(saveType.equals("1")){

				MaterialMaster attachFiles = objMan.getAttachFiles(matId,userInfo);
				request.setAttribute("attached_files", attachFiles.getAttachments());
				request.setAttribute("images", attachFiles.getImages());
				objMatP = objMan.getMaterialMasterInfo(userInfo,matId,3);

				String materialAttachPath=new UserInfo().getMaterialAttachPath(request);
				request.setAttribute("materialAttachPath", materialAttachPath);

				request.setAttribute("material_info", objMatP);
				request.setAttribute("groupType",objMatP.getGroupType());
				dynamicFieldsListMaterial = objMatP.getMatDynList();
				request.getSession().setAttribute("dynamicFieldsListMaterial", dynamicFieldsListMaterial);
				request.setAttribute("dynamicFieldsListMaterial", dynamicFieldsListMaterial);

			}
			else if(matId>0 && saveType.equals("3")){
				doDisplayMaterialMaster(request,response) ;
			}

			if(!saveType.equals("3")){
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_MATERIAL_MASTER, request,response);
			}


		} catch (Exception e) {
			// TODO: handle exception 
			e.printStackTrace();
		}


	}

	public void doGetMaterialModalData(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        try{ 
        		TPCSUser ui=new UserInfo().getUserInfo(request);
    	  	  	String retGroup= objMan.doGetMaterialModalData(ui);
    	  	  	response.setContentType("text/xml");
    	  	  	response.getWriter().write(retGroup);
      	  	
        }catch(Exception e){
          e.printStackTrace();
        }
      }

	public void doSaveMaterialFromPage(HttpServletRequest request , HttpServletResponse response){
		try {
			TPCSUser ui=new UserInfo().getUserInfo(request);

			MaterialMaster objMatP=new MaterialMaster();
			objMatP.setMatCode(request.getParameter("material_code"));
			objMatP.setShortName(request.getParameter("material_name"));
			//objMatP.setStdCost(Validator.convertToFloat(request.getParameter("standard_cost")));
			//objMatP.setCostCurrency(request.getParameter("cost_currency"));
			objMatP.setStatus(Validator.convertToInteger(request.getParameter("material_status")));
			objMatP.setSpec1(request.getParameter("spec1"));
			objMatP.setSpec2(request.getParameter("spec2"));
			objMatP.setSpec3(request.getParameter("spec3")); 
			objMatP.setSpec4(request.getParameter("spec4"));
			objMatP.setSpec5(request.getParameter("spec5"));
			objMatP.setSpec6(request.getParameter("spec6"));
			objMatP.setSpec7(request.getParameter("spec7"));
//			objMatP.setSpec8(request.getParameter("spec8"));
//			objMatP.setSpec9(request.getParameter("spec9"));
//			objMatP.setSpec10(request.getParameter("spec10"));
//			objMatP.setStockKeepingUnit(request.getParameter("stockKeepingUnit"));
//			objMatP.setPurchaseUnit(request.getParameter("purchaseUnit"));
//			objMatP.setBomUnit(request.getParameter("bomUnit"));
//			objMatP.setLeadTime(Validator.convertToInteger(request.getParameter("leadTime")));
//			objMatP.setMinLevel(Validator.convertToFloat(request.getParameter("minLevel")));
//			objMatP.setMaxLevel(Validator.convertToFloat(request.getParameter("maxLevel")));
//			objMatP.setMinOrderQty(Validator.convertToFloat(request.getParameter("minOrderQty")));
//			objMatP.setNegativeStock(Validator.convertToInteger(request.getParameter("negativeStock")));
//			objMatP.setItemTrackingApplicable(Validator.convertToInteger(request.getParameter("itemTrackingApplicable")));
//			objMatP.setExpiryApplicable(Validator.convertToInteger(request.getParameter("expiryApplicable")));
//			objMatP.setBomNumber(request.getParameter("bomNumber"));
//			objMatP.setDefaultSizeRange(request.getParameter("defaultSizeRange"));
//			objMatP.setDefaultLeatherId(Validator.convertToInteger("defaultLeatherId"));
//			objMatP.setDefaultColourId(Validator.convertToInteger("defaultColourId"));
//			objMatP.setBuyerStyleNo(request.getParameter("buyerStyleNo"));
//			objMatP.setSampleStyleNo(request.getParameter("sampleStyleNo"));
//			objMatP.setRouteRef(request.getParameter("routeRef"));
			objMatP.setBasicUnit(request.getParameter("basic_unit"));
//			objMatP.setBomUom(request.getParameter("bom_uom"));
//            objMatP.setPurchaseUOM(request.getParameter("purchase_uom"));
			objMatP.setStockLocation(request.getParameter("stock_location"));
			objMatP.setBinNumber((request.getParameter("bin_no")));
			objMatP.setReorderLevel(Validator.convertToFloat(request.getParameter("reorder_level")));
			objMatP.setCostPrice(Validator.convertToFloat(request.getParameter("standard_rate")));
			objMatP.setSellingPrice(Validator.convertToFloat(request.getParameter("selling_price")));
			objMatP.setExcessAllowance(Validator.convertToFloat(request.getParameter("excess_allowance")));
			objMatP.setGroupId(Validator.convertToInteger(request.getParameter("sub_group_id")));
			objMatP.setPurchaseDesc(request.getParameter("purchase_desc"));
			objMatP.setSalesDesc(request.getParameter("sales_desc"));
			
			objMatP.setTrackInventory(Validator.convertToInteger(request.getParameter("track_inventory")));
			objMatP.setInventoryAccountId(Validator.convertToInteger(request.getParameter("inventory_account_id")));
			objMatP.setSellMaterial(Validator.convertToInteger(request.getParameter("sell_material")));
			objMatP.setIncludeTax(Validator.convertToInteger(request.getParameter("include_tax")));
//			objMatP.setTaxGroupId(Validator.convertToInteger(request.getParameter("tax_group")));
			objMatP.setPurchaseMaterial(Validator.convertToInteger(request.getParameter("purchase_material")));
			objMatP.setPurchaseAccountId(Validator.convertToInteger(request.getParameter("mat_purchase_account_id")));
			
			objMatP.setMode("n");
			/*System.out.println("material_code :"+request.getParameter("material_code"));
		System.out.println("group_id :"+request.getParameter("sub_group_id"));
		System.out.println("material_name :"+request.getParameter("material_name"));
		System.out.println("basic_unit :"+request.getParameter("basic_unit"));*/


			// existence checking And Save
			objMatP=objMan.saveMaterial(objMatP,ui);


			String strInsertDetails="";

			if(objMatP.isMaterialExists()){
				strInsertDetails="2";
			}
			else if(objMatP.isInserted()){
				strInsertDetails="1";
			}
			else{
				strInsertDetails="0";
			}

			response.setContentType("text/html");
			response.getWriter().write(strInsertDetails);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doSaveUOMFromPage(HttpServletRequest request , HttpServletResponse response){
		try {
			TPCSUser ui=new UserInfo().getUserInfo(request);
			MaterialMaster objMatP=new MaterialMaster();
			objMatP.setBasicUnit(request.getParameter("uom_name"));
			objMatP=objMan.saveUOM(objMatP,ui);

			String strInsertDetails="";

			if(objMatP.isUOMExists()){
				strInsertDetails="2";
			}
			else if(objMatP.isInserted()){
				strInsertDetails="1";
			}
			else{
				strInsertDetails="0";
			}
			response.setContentType("text/html");
			response.getWriter().write(strInsertDetails);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String generateXMLforDynamicFields(
			List<DynamicFields> dynamicFieldsListMaterial) {
		String strXML = "<screens> ";
		String formMaterial = "<screen name=\"Items\">  <columns> ";

		if(dynamicFieldsListMaterial!=null) {
			for (DynamicFields dynamicFields : dynamicFieldsListMaterial) {
				if (!dynamicFields.isFixed()) {
					String column = " <column type=\"dynamic\"> <name>" + dynamicFields.getDbFieldName() + "</name> <value>" + dynamicFields.getFieldValue() + "</value> <datatype>" + dynamicFields.getDataTypeName() + "</datatype> </column> ";
					formMaterial = formMaterial.concat(column);
				} 
			}
		}
		formMaterial = formMaterial.concat(" </columns> </screen> ");
		formMaterial = formMaterial.concat(" </screens> ");
		strXML = strXML.concat(formMaterial);
		//	System.out.println(strXML);
		return strXML;
	}

	private MaterialMaster getMaterialMasterParameter(Map<String,String> fileMap,HttpServletRequest request) {
		// TODO Auto-generated method stub

		MaterialMaster objMatP=new MaterialMaster();
	
        objMatP.setGroupName(fileMap.get("group_name"));
        objMatP.setMatId(Validator.convertToInteger(fileMap.get("mat_id")));
		objMatP.setMatCode(fileMap.get("material_code"));
		objMatP.setShortName(fileMap.get("material_name"));
		objMatP.setStdCost(Validator.convertToFloat(fileMap.get("standard_cost")));
		objMatP.setCostCurrency(fileMap.get("currency_name"));
		objMatP.setSpec1(fileMap.get("spec1"));
		objMatP.setSpec2(fileMap.get("spec2"));
		objMatP.setSpec3(fileMap.get("spec3"));
		objMatP.setSpec4(fileMap.get("spec4"));
		objMatP.setSpec5(fileMap.get("spec5"));
		objMatP.setSpec6(fileMap.get("spec6"));
		objMatP.setSpec7(fileMap.get("spec7"));
		objMatP.setSpec8(fileMap.get("spec8"));
		objMatP.setSpec9(fileMap.get("spec9"));
		objMatP.setSpec10(fileMap.get("spec10"));
		objMatP.setStockKeepingUnit(fileMap.get("stack_keepin_unit"));
		objMatP.setPurchaseUnit(fileMap.get("purchase_unit"));
		objMatP.setBomUnit(fileMap.get("BOM_unit"));
//		objMatP.setBomUom(fileMap.get("bom_uom"));
//		objMatP.setPurchaseUOM(fileMap.get("purchase_uom"));
		objMatP.setLeadTime(Validator.convertToInteger(fileMap.get("standerd_leadtime")));
		objMatP.setMinLevel(Validator.convertToFloat(fileMap.get("min_level")));
		objMatP.setMaxLevel(Validator.convertToFloat(fileMap.get("max_level")));
		objMatP.setMinOrderQty(Validator.convertToFloat(fileMap.get("min_order_qty")));
		objMatP.setNegativeStock(Validator.convertToInteger(fileMap.get("negative_stock")));
		objMatP.setItemTrackingApplicable(Validator.convertToInteger(fileMap.get("item_traking")));
		objMatP.setExpiryApplicable(Validator.convertToInteger(fileMap.get("expiry")));
		objMatP.setBomNumber(fileMap.get("bom_number"));
		objMatP.setDefaultSizeRange(fileMap.get("size_range"));
//		objMatP.setDefaultLeatherId(Validator.convertToInteger(fileMap.get("leather_id")));
		objMatP.setDefaultColourId(Validator.convertToInteger(fileMap.get("color_id")));
		objMatP.setBuyerStyleNo(fileMap.get("style_id"));
		objMatP.setSampleStyleNo(fileMap.get("sample_id"));
		objMatP.setAbcClass(fileMap.get("ABC_class"));
		objMatP.setXyzClass(fileMap.get("XYZ_class"));
		objMatP.setRouteRef(fileMap.get("route_ref"));
		objMatP.setExcessAllowance(Validator.convertToFloat(fileMap.get("excess_allowance")));
		objMatP.setBasicUnit(fileMap.get("basic_unit"));
		objMatP.setGroupId(Validator.convertToInteger(fileMap.get("group_id")));
		objMatP.setStockLocation(fileMap.get("stock_location"));
		objMatP.setBinNumber((fileMap.get("bin_no")));
		objMatP.setTrackInventory(Validator.convertToInteger(fileMap.get("track_inventory")));
		objMatP.setInventoryAccountId(Validator.convertToInteger(fileMap.get("inventory_account_id")));
		objMatP.setSellMaterial(Validator.convertToInteger(fileMap.get("sell_material")));
		objMatP.setSalesDesc(fileMap.get("sales_desc"));
		objMatP.setSellingPrice(Validator.convertToFloat(fileMap.get("selling_price")));
		objMatP.setIncludeTax(Validator.convertToInteger(fileMap.get("include_tax")));
		objMatP.setTaxCategory(fileMap.get("tax_category"));
		objMatP.setImportMat(Validator.convertToInteger(fileMap.get("import_mat")));
		objMatP.setTaxGroupId(Validator.convertToInteger(fileMap.get("tax_group")));
		objMatP.setPurchaseMaterial(Validator.convertToInteger(fileMap.get("purchase_material")));
		objMatP.setPurchaseDesc(fileMap.get("purchase_desc"));	
		objMatP.setPurchaseAccountId(Validator.convertToInteger(fileMap.get("purchase_account_id")));
		objMatP.setCostPrice(Validator.convertToFloat(fileMap.get("cost_price")));
		objMatP.setReorderLevel(Validator.convertToFloat(fileMap.get("reorder_point")));
		objMatP.setReorderQty(Validator.convertToFloat(fileMap.get("reorder_quantity")));
		objMatP.setStatus(Validator.convertToInteger(fileMap.get("material_status")));
		objMatP.setMode(fileMap.get("mode"));
		objMatP.setMatId(Validator.convertToInteger(fileMap.get("mat_id")));

		objMatP.setIssueWithIO(Validator.convertToInteger(fileMap.get("issueWithIo")));
		objMatP.setSizeApplicable(Validator.convertToInteger(fileMap.get("size_applicble")));
		objMatP.setColorApplicable(Validator.convertToInteger(fileMap.get("colorapplicble")));
		objMatP.setStockReservation(Validator.convertToInteger(fileMap.get("reservation_applicble")));
		objMatP.setInspectionRequired(Validator.convertToInteger(fileMap.get("inspection_required")));
		objMatP.setBarcodeRequired(Validator.convertToInteger(fileMap.get("barcode_required")));
		
		
		return objMatP;
		
	}
	public void doDeleteMaterialMaster(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		
		

		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		boolean isDeleted=false;
		MaterialMaster objM=new MaterialMaster();
		int mmId=Validator.convertToInteger(request.getParameter("mat_id"));
		System.out.println("Mat:"+objM.getMatId());
		objM.setMatId(mmId);
		
		isDeleted=objMan.deleteMaterialMaster(userInfo,mmId);
	     System.out.println("Delete:"+isDeleted);
		System.out.println("isDeleted------>"+isDeleted);
		if(isDeleted)
		{
			request.setAttribute("success_message","Item deleted Successfully");

		}
		
		else
		{
			request.setAttribute("error_message","Item deleted failed");
		}
		
		doDisplayMaterialMaster(request, response);
		userInfo=null;
	}
	
	
	public void doBulkActionActiveMaterialMaster(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		boolean isBulkAction=false;
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		String[] matId=request.getParameterValues("bulkmatId");
		String activeMode=request.getParameter("active_mode");

		isBulkAction=objMan.bulkActionMaterialMaster(userInfo,matId,activeMode);
		if (isBulkAction==true)	{
			if(activeMode.equals("1")){
				request.setAttribute("success_message","Item Activated!");
			}
			else{
				request.setAttribute("success_message","Item Inactivated!");
			}
		}
		if (isBulkAction== false){

			if(activeMode.equals("1")){
				request.setAttribute("error_message","Item not Activated!");
			}
			else{
				request.setAttribute("error_message","Item not Inactivated!");
			}
		}

		doDisplayMaterialMaster(request, response);
		userInfo=null;
	}
	
	
	
	
	public void doDeleteBulkMaterialMaster(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		String[] matId=request.getParameterValues("bulkmatId");

		MaterialMaster mat=new MaterialMaster();

		//int matId=0;
		for(int i=0;i<matId.length;i++){

			int matIds=Integer.parseInt(matId[i]);	

			mat.setMatId(matIds);

			mat=objMan.deleteBulkMaterialMaster(userInfo,mat);

		}
		if(mat.isDeleted())
		{
			request.setAttribute("success_message","Item deleted successfully");
		}
		else
		{
			request.setAttribute("error_message","Item deleted failed");
		}


		doDisplayMaterialMaster(request, response);
		mat=null;
	}	 
	
	
	
	
	
	
      

	/*public String getSearchCriteria(HttpServletRequest request){

	StringBuffer  strQuery=new StringBuffer("");

		    int groupId=Validator.convertToInteger(request.getParameter("group_id"));
			int matId=Validator.convertToInteger(request.getParameter("material_id"));
			int groupTypeId=Validator.convertToInteger(request.getParameter("group_type_id"));
		 System.out.println("groupId :"+groupId);
		System.out.println("matId ;"+matId);
		System.out.println("grouptypeId ;"+groupTypeId);
		 if(groupId!=0){strQuery.append(" and g.group_id="+groupId);}
		if(matId!=0){strQuery.append(" and m.material_id="+matId);}
		if(groupTypeId!=0){strQuery.append(" and g1.group_type_id="+groupTypeId);}

		 System.out.println("strQueryservlet:="+strQuery);
		 return strQuery.toString();

   }*/		


	public void doDeleteAttachment(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		try{

			TPCSUser ui=new UserInfo().getUserInfo(request);
			String realPath=(String) request.getSession().getAttribute("path");
			String folderPath=realPath+new UserInfo().getMaterialAttachPath(request);
			int matId=Validator.convertToInteger(request.getParameter("material_id"));
			String fileName=request.getParameter("file_name");
			String idx=request.getParameter("idx");

			boolean bFlag=objMan.deleteAttachment(matId,fileName,ui);
			String msg="0";
			if(bFlag){
				File file=new File(folderPath+fileName);
				file.delete();
				msg="1";
			}
			msg=msg+"&1"+idx;
			response.getWriter().write(msg);

		}
		catch(Exception e)
		
		{
			e.printStackTrace();

		}
	}

	public void doPrintMaterial(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{ 
			HttpSession session = request.getSession();
			TPCSUser ui=new UserInfo().getUserInfo(request);
			int iDivWidth = Validator.convertToInteger(request.getParameter("divWidth")); 
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			PageConfig pc=new PageConfig(request.getParameter("pageno"));

			int  ipageno=1;
			rights =objRight.getUserRights(SubdocumentId.MATERIAL_MASTER, userInfo);
			session.setAttribute("rights",rights);
			session.setAttribute("subdocument_id", SubdocumentId.MATERIAL_MASTER);
			String requestType=request.getParameter("request_type");
			String strSearchCriteria=null;
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request); 

			/* if("Search".equalsIgnoreCase(requestType)){       
			  strSearchCriteria=getSearchCriteria(request);
	          }*/


			int pageCount= objMan.getPageCount(userInfo,pc, strSearchCriteria);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);

			rights =objRight.getUserRights(SubdocumentId.MATERIAL_MASTER, userInfo);
			session.setAttribute("material_rights",rights);

			session.setAttribute("subdocument_id", SubdocumentId.MATERIAL_MASTER);

			ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
			ColumnPreference DataList=cpm.getVisibleColumnNames(SubdocumentId.MATERIAL_MASTER, userInfo);
			Map<String,String> visColMap=cpm.getVisibleColumnNamesMap(SubdocumentId.MATERIAL_MASTER,Integer.parseInt((String)request.getSession().getAttribute("login_user_id")),userInfo);
			List<String> listSelectedColumns=DataList.getVisibleColumns(); 
			List<String> orderBy=DataList.getOrderBy(); 

			MaterialGridMaker objUtil=new MaterialGridMaker();
			String materialAttachPath=new UserInfo().getMaterialAttachPath(request);
			MaterialMaster mat= objMan.getAllMaterialOnColumnMapping(pc,userInfo,strSearchCriteria,orderBy,5,materialAttachPath);
			List<Map<String,String>> objList =mat.getMaterialMapList();
			String Grid = objUtil.formMaterialPrintGrid(visColMap,listSelectedColumns, objList, ipageno, bundle, iDivWidth,rights);
			request.setAttribute("report_data", Grid);

			CompanyAndYearSelectionManager objCompany=new CompanyAndYearSelectionManager();
			Company objCompanyDet = objCompany.getCompanyInfo(userInfo);
			request.setAttribute("company_det", objCompanyDet);
			request.setAttribute("report_name", "MATERIAL REPORT");

			/*	String strLotNo=request.getParameter("lot_no")!= null && !request.getParameter("lot_no").isEmpty() ? request.getParameter("lot_no"):"";
		String strRawMaterial=request.getParameter("raw_material_id")!= null && !request.getParameter("raw_material_id").isEmpty() ? request.getParameter("raw_material"):"";
		String strArticle=request.getParameter("article_id")!= null && !request.getParameter("article_id").isEmpty() ? request.getParameter("article"):"";
		String strStore=request.getParameter("warehouse_id")!= null && !request.getParameter("warehouse_id").isEmpty() ? request.getParameter("warehouse_name"):"";
		String strOrigin=request.getParameter("sub_orgin_id")!= null && !request.getParameter("sub_orgin_id").isEmpty() ? request.getParameter("sub_orgin"):"";
		String strSelection=request.getParameter("sel_id")!= null && !request.getParameter("sel_id").isEmpty() ? request.getParameter("sel"):"";
		String strParty=request.getParameter("party_id")!= null && !request.getParameter("party_id").isEmpty() ? request.getParameter("party"):"";
			String fromDate=request.getParameter("start_date");
			String endDate=request.getParameter("end_date");*/

			/*		Map<String, String> objMap = new HashMap<String,String>();

 		    objMap.put("From : ",fromDate);
			objMap.put("To : ",endDate);
			objMap.put("Lot No. : ",strLotNo);
			objMap.put("Leather : ",strRawMaterial);
			objMap.put("Article : ",strArticle);
			objMap.put("Store : ",strStore);
			objMap.put("Origin : ",strOrigin);
			objMap.put("Selection : ",strSelection);
			objMap.put("Transfer To : ",strParty);
			request.setAttribute("filter_list", objMap);*/


			// TPCSCommonUtil.RedirectURL(TPCSRedirectPage.PRINT_REPORT,request,response);
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}


	public void doExportMaterial(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			//int iCompanyId = Integer.parseInt((String) request.getSession().getAttribute("g_companyid"));
			TPCSUser ui=new UserInfo().getUserInfo(request);
			HttpSession session = request.getSession();
			int iDivWidth = Validator.convertToInteger(request.getParameter("divWidth")); 
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			PageConfig pc=new PageConfig(request.getParameter("pageno"));

			int  ipageno=1;
			rights =objRight.getUserRights(SubdocumentId.MATERIAL_MASTER, userInfo);
			session.setAttribute("rights",rights);
			session.setAttribute("subdocument_id", SubdocumentId.MATERIAL_MASTER);
			String requestType=request.getParameter("request_type");
			String strSearchCriteria=null;
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request); 

			/* if("Search".equalsIgnoreCase(requestType)){       
			  strSearchCriteria=getSearchCriteria(request);
	          }*/


			int pageCount= objMan.getPageCount(userInfo,pc, strSearchCriteria);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);

			rights =objRight.getUserRights(SubdocumentId.MATERIAL_MASTER, userInfo);
			session.setAttribute("material_rights",rights);

			session.setAttribute("subdocument_id", SubdocumentId.MATERIAL_MASTER);

			ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
			ColumnPreference DataList=cpm.getVisibleColumnNames(SubdocumentId.MATERIAL_MASTER, userInfo);
			Map<String,String> visColMap=cpm.getVisibleColumnNamesMap(SubdocumentId.MATERIAL_MASTER,Integer.parseInt((String)request.getSession().getAttribute("login_user_id")),userInfo);
			List<String> listSelectedColumns=DataList.getVisibleColumns(); 
			List<String> orderBy=DataList.getOrderBy(); 

			String materialAttachPath=new UserInfo().getMaterialAttachPath(request);
			MaterialMaster mat= objMan.getAllMaterialOnColumnMapping(pc,userInfo,strSearchCriteria,orderBy,5,materialAttachPath);
			List<Map<String,String>> objList =mat.getMaterialMapList();

			Company objCompanyDet = objCompany.getCompanyInfo(userInfo);
			createMaterialExcel(visColMap,listSelectedColumns, objList," MATERIAL REPORT ",objCompanyDet,request,response);
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}finally{

		}
	}  

	public void createMaterialExcel(Map<String, String> visColMap,List<String> listSelectedColumns,List<Map<String,String>> data,String title,Company objCompany,HttpServletRequest request, HttpServletResponse response)throws IOException{


		ServletOutputStream out = null;
		response.setContentType("application/vnd.ms-excel");    
		response.setHeader("Content-Disposition", "attachment; filename=\""+ new java.util.Date()+".xls" + "\"");
		out = response.getOutputStream();
		try{
			HSSFWorkbook hwb = new HSSFWorkbook();
			HSSFSheet sheet = hwb.createSheet("Report");
			HSSFFont font = hwb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			HSSFCellStyle cellStyle = hwb.createCellStyle();
			cellStyle.setFont(font);
			HSSFCellStyle alignStyle1 = hwb.createCellStyle();
			alignStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFRow rowhead=null ;
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
			sheet.addMergedRegion(new CellRangeAddress(1,1,0,3));
			HSSFRow row = sheet.createRow((short)1);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue(new HSSFRichTextString(objCompany.getCompanyName()));
			sheet.addMergedRegion(new CellRangeAddress(2,2,0,3));
			row = sheet.createRow((short)2);
			cell= row.createCell(0);
			cell.setCellValue(new HSSFRichTextString(objCompany.getAddress1()));
			sheet.addMergedRegion(new CellRangeAddress(3,3,0,3));
			row = sheet.createRow((short)3);
			cell= row.createCell(0);
			cell.setCellValue(new HSSFRichTextString(objCompany.getAddress2()));
			sheet.addMergedRegion(new CellRangeAddress(4,4,0,3));
			row = sheet.createRow((short)4);
			cell= row.createCell(0);
			cell.setCellValue(new HSSFRichTextString(objCompany.getAddress3()));
			sheet.addMergedRegion(new CellRangeAddress(5,5,0,3));
			row = sheet.createRow((short)5);
			cell= row.createCell(0);
			cell.setCellValue(new HSSFRichTextString(objCompany.getCountry()));
			sheet.addMergedRegion(new CellRangeAddress(6,6,0,3));
			row = sheet.createRow((short)6);
			cell= row.createCell(0);
			cell.setCellValue(new HSSFRichTextString("Phone : "+objCompany.getPhoneNo()));
			sheet.addMergedRegion(new CellRangeAddress(7,7,0,4));
			row = sheet.createRow((short)7);

			cell= row.createCell(0);
			cell.setCellValue(new HSSFRichTextString("Fax No: "+objCompany.getFaxNo()+","+" Email Id: "+objCompany.getEmailId()));

			sheet.addMergedRegion(new CellRangeAddress(8,8,0,4));
			row = sheet.createRow((short)8);	  	      

			cell= row.createCell(0);
			cell.setCellValue(new HSSFRichTextString("TIN No: "+objCompany.getTaxRegNo1()+","+" CST: "+objCompany.getTaxRegNo2()+"."));

			sheet.addMergedRegion(new CellRangeAddress(10,10,0,3));
			row= sheet.createRow((short)10); 
			cell= row.createCell(0);
			cell.setCellValue(new HSSFRichTextString(title));
			cell.setCellStyle(cellStyle);


			int iCount=0;
			int k=1;
			rowhead= sheet.createRow((short)11); 
			cell=rowhead.createCell(0);
			cell.setCellValue(new HSSFRichTextString("S.No"));
			cell.setCellStyle(cellStyle); 

			Iterator it = visColMap.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry mVisCol= (Map.Entry)it.next();

				if(mVisCol.getKey()==null ){
					continue;
				}
				cell=rowhead.createCell(k);
				cell.setCellValue(new HSSFRichTextString(mVisCol.getValue().toString()));
				cell.setCellStyle(cellStyle); 
				k++;
			}


			String strPath =(String)request.getSession().getAttribute("path");
			int index=12; int sno=1;
			for(int i=0,j=1,size=data.size();i<size;i++,j++){ 
				row = sheet.createRow((short)index);
				row.createCell(0).setCellValue(sno); 	 
				Map<String, String> map = data.get(i);
				int m=1;
				Iterator it2 = visColMap.entrySet().iterator();
				while(it2.hasNext()){
					Map.Entry mVisCol= (Map.Entry)it2.next();
					if(mVisCol.getKey()==null ){
						continue;
					}
					// row.createCell(m).setCellValue(new HSSFRichTextString(map.get(mVisCol.getValue())));

					System.out.println("mVisCol.getKey()------"+mVisCol.getKey());
					if(mVisCol.getKey().equals("Material.Attachment")){
						if(map.get(mVisCol.getValue())!=null && !map.get(mVisCol.getValue()).equals("No Image")){

							
							
							String imagePath=strPath+map.get(mVisCol.getValue());
							doWriteStyleImageToExcelCell(sheet, imagePath, m, index);
						}
						else{
							row.createCell(m).setCellValue(new HSSFRichTextString(map.get(mVisCol.getValue())));
						}

					}
					else{
						row.setHeightInPoints((3*sheet.getDefaultRowHeightInPoints()));
						cell=row.createCell(m);
						CellStyle cs = hwb.createCellStyle();
						cs.setWrapText(true);   //Wrapping text
						cell.setCellValue(new HSSFRichTextString(map.get(mVisCol.getValue())));
						cell.setCellStyle(cs);         
						//			      		}
						m++;
					}



				} 
				sno++;
				index++;
			}
			sheet.autoSizeColumn((short)0);
			sheet.autoSizeColumn((short)1);
			sheet.autoSizeColumn((short)2);
			sheet.autoSizeColumn((short)3);
			sheet.autoSizeColumn((short)4);
			sheet.autoSizeColumn((short)5);
			sheet.autoSizeColumn((short)6);
			sheet.autoSizeColumn((short)7);
			sheet.autoSizeColumn((short)8);
			sheet.autoSizeColumn((short)9);
			sheet.autoSizeColumn((short)10);
			sheet.autoSizeColumn((short)11);
			sheet.autoSizeColumn((short)12);
			sheet.autoSizeColumn((short)13);
			sheet.autoSizeColumn((short)14);
			sheet.autoSizeColumn((short)15);
			sheet.autoSizeColumn((short)16);
			sheet.autoSizeColumn((short)17);
			sheet.autoSizeColumn((short)18);
			sheet.autoSizeColumn((short)19);
			hwb.write(out);
		}
		catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();
		}catch (Exception ex)
		{ex.printStackTrace();}finally{}
	}

	public void doWriteStyleImageToExcelCell(HSSFSheet sheet,String imageFile,int cellNumber,int rowNumber){
		try {
			File f=new File (imageFile);
			if(f.exists()){
				new AddDimensionedImage().addImageToSheet(cellNumber,rowNumber, sheet, sheet.createDrawingPatriarch(),
						new File(imageFile).toURI().toURL(), 10, 7,AddDimensionedImage.EXPAND_ROW_AND_COLUMN);//Style Image Size
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	 public void doGetItemGroupData(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
			try{
				TPCSUser ui=new UserInfo().getUserInfo(request);
				int groupId=Validator.convertToInteger(request.getParameter("group_id"));
				
				
				String strItemGroupDetails=objMan.doGetItemGroupData(ui,groupId);
				response.setContentType("text/xml");
				response.getWriter().write(strItemGroupDetails);

				ui=null;
				strItemGroupDetails=null;
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
	
	
	
	
	public static class AddDimensionedImage {

		public static final int EXPAND_ROW = 1;
		public static final int EXPAND_COLUMN = 1;
		public static final int EXPAND_ROW_AND_COLUMN = 1;
		public static final int OVERLAY_ROW_AND_COLUMN = 1;

		// Modified to support EMU - English Metric Units - used within the OOXML
		// workbooks, this multoplier is used to convert between measurements in
		// millimetres and in EMUs
		private static final int EMU_PER_MM = 36000;


		public void addImageToSheet(Integer cellNumber,Integer rowNumber, Sheet sheet, Drawing drawing,
				URL imageFile, double reqImageWidthMM, double reqImageHeightMM,
				int resizeBehaviour) throws IOException, IllegalArgumentException {
			// Convert the String into column and row indices then chain the
			// call to the overridden addImageToSheet() method.
			//        CellReference cellRef = new CellReference(cellNumber);
			this.addImageToSheet(cellNumber, rowNumber, sheet, drawing,
					imageFile, reqImageWidthMM, reqImageHeightMM,resizeBehaviour);
		}


		public void addImageToSheet(int colNumber, int rowNumber, Sheet sheet, Drawing drawing,
				URL imageFile, double reqImageWidthMM, double reqImageHeightMM,
				int resizeBehaviour) throws IOException,
				IllegalArgumentException {
			ClientAnchor anchor = null;
			ClientAnchorDetail rowClientAnchorDetail = null;
			ClientAnchorDetail colClientAnchorDetail = null;
			int imageType = 0;

			// Validate the resizeBehaviour parameter.
			if((resizeBehaviour != AddDimensionedImage.EXPAND_COLUMN) &&
					(resizeBehaviour != AddDimensionedImage.EXPAND_ROW) &&
					(resizeBehaviour != AddDimensionedImage.EXPAND_ROW_AND_COLUMN) &&
					(resizeBehaviour != AddDimensionedImage.OVERLAY_ROW_AND_COLUMN)) {
				throw new IllegalArgumentException("Invalid value passed to the " +
						"resizeBehaviour parameter of AddDimensionedImage.addImageToSheet()");
			}

			// Call methods to calculate how the image and sheet should be
			// manipulated to accomodate the image; columns and then rows.
			colClientAnchorDetail = this.fitImageToColumns(sheet, colNumber,
					reqImageWidthMM, resizeBehaviour);
			rowClientAnchorDetail = this.fitImageToRows(sheet, rowNumber,
					reqImageHeightMM, resizeBehaviour);

			// Having determined if and how to resize the rows, columns and/or the
			// image, create the ClientAnchor object to position the image on
			// the worksheet. Note how the two ClientAnchorDetail records are
			// interrogated to recover the row/column co-ordinates and any insets.
			// The first two parameters are not used currently but could be if the
			// need arose to extend the functionality of this code by adding the
			// ability to specify that a clear 'border' be placed around the image.
			anchor = sheet.getWorkbook().getCreationHelper().createClientAnchor();

			anchor.setDx1(0);
			anchor.setDy1(0);
			anchor.setDx2(colClientAnchorDetail.getInset());
			anchor.setDy2(rowClientAnchorDetail.getInset());
			anchor.setCol1(colClientAnchorDetail.getFromIndex());
			anchor.setRow1(rowClientAnchorDetail.getFromIndex());
			anchor.setCol2(colClientAnchorDetail.getToIndex());
			anchor.setRow2(rowClientAnchorDetail.getToIndex());

			// For now, set the anchor type to do not move or resize the
			// image as the size of the row/column is adjusted. This could easilly
			// become another parameter passed to the method. Please read the note
			// above regarding the behaviour of image resizing.
			anchor.setAnchorType(ClientAnchor.MOVE_AND_RESIZE);

			// Now, add the picture to the workbook. Note that unlike the similar
			// method in the HSSF Examples section, the image type is checked. First,
			// the image files location is identified by interrogating the URL passed
			// to the method, the images type is identified before it is added to the
			// sheet.
			String sURL = imageFile.toString().toLowerCase();
			if( sURL.endsWith(".png") ) {
				imageType = Workbook.PICTURE_TYPE_PNG;
			}
			else if( sURL.endsWith("jpg") || sURL.endsWith(".jpeg") ) {
				imageType = Workbook.PICTURE_TYPE_JPEG;
			}
			else  {
				throw new IllegalArgumentException("Invalid Image file : " +sURL);
			}
			int index = sheet.getWorkbook().addPicture(IOUtils.toByteArray(imageFile.openStream()), imageType);
			drawing.createPicture(anchor, index);
		}


		private ClientAnchorDetail fitImageToColumns(Sheet sheet, int colNumber,
				double reqImageWidthMM, int resizeBehaviour) {

			double colWidthMM = 0.0D;
			double colCoordinatesPerMM = 0.0D;
			int pictureWidthCoordinates = 0;
			ClientAnchorDetail colClientAnchorDetail = null;

			// Get the colum's width in millimetres
			colWidthMM = ConvertImageUnits.widthUnits2Millimetres(
					(short)sheet.getColumnWidth(colNumber));

			// Check that the column's width will accomodate the image at the
			// required dimension. If the width of the column is LESS than the
			// required width of the image, decide how the application should
			// respond - resize the column or overlay the image across one or more
			// columns.
			if(colWidthMM < reqImageWidthMM) {

				// Should the column's width simply be expanded?
				if((resizeBehaviour == AddDimensionedImage.EXPAND_COLUMN) ||
						(resizeBehaviour == AddDimensionedImage.EXPAND_ROW_AND_COLUMN)) {
					// Set the width of the column by converting the required image
					// width from millimetres into Excel's column width units.
					sheet.setColumnWidth(colNumber,
							ConvertImageUnits.millimetres2WidthUnits(reqImageWidthMM));
					// To make the image occupy the full width of the column, convert
					// the required width of the image into co-ordinates. This value
					// will become the inset for the ClientAnchorDetail class that
					// is then instantiated.
					if(sheet instanceof HSSFSheet) {
						colWidthMM = reqImageWidthMM;
						colCoordinatesPerMM = ConvertImageUnits.TOTAL_COLUMN_COORDINATE_POSITIONS /
								colWidthMM;
						pictureWidthCoordinates = (int)(reqImageWidthMM * colCoordinatesPerMM);

					}
					else {
						pictureWidthCoordinates = (int)reqImageWidthMM * AddDimensionedImage.EMU_PER_MM;
					}
					colClientAnchorDetail = new ClientAnchorDetail(colNumber,
							colNumber, pictureWidthCoordinates);
				}
				// If the user has chosen to overlay both rows and columns or just
				// to expand ONLY the size of the rows, then calculate how to lay
				// the image out across one or more columns.
				else if ((resizeBehaviour == AddDimensionedImage.OVERLAY_ROW_AND_COLUMN) ||
						(resizeBehaviour == AddDimensionedImage.EXPAND_ROW)) {
					colClientAnchorDetail = this.calculateColumnLocation(sheet,
							colNumber, reqImageWidthMM);
				}
			}
			// If the column is wider than the image.
			else {
				if(sheet instanceof HSSFSheet) {
					// Mow many co-ordinate positions are there per millimetre?
					colCoordinatesPerMM = ConvertImageUnits.TOTAL_COLUMN_COORDINATE_POSITIONS /
							colWidthMM;
					// Given the width of the image, what should be it's co-ordinate?
					pictureWidthCoordinates = (int)(reqImageWidthMM * colCoordinatesPerMM);
				}
				else {
					pictureWidthCoordinates = (int)reqImageWidthMM *
							AddDimensionedImage.EMU_PER_MM;
				}
				colClientAnchorDetail = new ClientAnchorDetail(colNumber,
						colNumber, pictureWidthCoordinates);
			}
			return(colClientAnchorDetail);
		}

		private ClientAnchorDetail fitImageToRows(Sheet sheet, int rowNumber,
				double reqImageHeightMM, int resizeBehaviour) {
			Row row = null;
			double rowHeightMM = 0.0D;
			double rowCoordinatesPerMM = 0.0D;
			int pictureHeightCoordinates = 0;
			ClientAnchorDetail rowClientAnchorDetail = null;

			// Get the row and it's height
			row = sheet.getRow(rowNumber);
			if(row == null) {
				// Create row if it does not exist.
				row = sheet.createRow(rowNumber);
			}

			// Get the row's height in millimetres
			rowHeightMM = row.getHeightInPoints() / ConvertImageUnits.POINTS_PER_MILLIMETRE;

			// Check that the row's height will accomodate the image at the required
			// dimensions. If the height of the row is LESS than the required height
			// of the image, decide how the application should respond - resize the
			// row or overlay the image across a series of rows.
			if(rowHeightMM < reqImageHeightMM) {
				if((resizeBehaviour == AddDimensionedImage.EXPAND_ROW) ||
						(resizeBehaviour == AddDimensionedImage.EXPAND_ROW_AND_COLUMN)) {
					row.setHeightInPoints((float)(reqImageHeightMM *
							ConvertImageUnits.POINTS_PER_MILLIMETRE));
					if(sheet instanceof HSSFSheet) {                    
						rowHeightMM = reqImageHeightMM;
						rowCoordinatesPerMM = ConvertImageUnits.TOTAL_ROW_COORDINATE_POSITIONS /
								rowHeightMM;
						pictureHeightCoordinates = (int)(reqImageHeightMM *
								rowCoordinatesPerMM);
					}
					else {
						pictureHeightCoordinates = (int)(reqImageHeightMM *
								AddDimensionedImage.EMU_PER_MM);
					}
					rowClientAnchorDetail = new ClientAnchorDetail(rowNumber,
							rowNumber, pictureHeightCoordinates);
				}
				// If the user has chosen to overlay both rows and columns or just
				// to expand ONLY the size of the columns, then calculate how to lay
				// the image out ver one or more rows.
				else if((resizeBehaviour == AddDimensionedImage.OVERLAY_ROW_AND_COLUMN) ||
						(resizeBehaviour == AddDimensionedImage.EXPAND_COLUMN)) {
					rowClientAnchorDetail = this.calculateRowLocation(sheet,
							rowNumber, reqImageHeightMM);
				}
			}
			// Else, if the image is smaller than the space available
			else {
				if(sheet instanceof HSSFSheet) {
					rowCoordinatesPerMM = ConvertImageUnits.TOTAL_ROW_COORDINATE_POSITIONS /
							rowHeightMM;
					pictureHeightCoordinates = (int)(reqImageHeightMM * rowCoordinatesPerMM);
				}
				else {
					pictureHeightCoordinates = (int)(reqImageHeightMM *
							AddDimensionedImage.EMU_PER_MM);
				}
				rowClientAnchorDetail = new ClientAnchorDetail(rowNumber,
						rowNumber, pictureHeightCoordinates);
			}
			return(rowClientAnchorDetail);
		}


		private ClientAnchorDetail calculateColumnLocation(Sheet sheet,
				int startingColumn,
				double reqImageWidthMM) {
			ClientAnchorDetail anchorDetail = null;
			double totalWidthMM = 0.0D;
			double colWidthMM = 0.0D;
			double overlapMM = 0.0D;
			double coordinatePositionsPerMM = 0.0D;
			int toColumn = startingColumn;
			int inset = 0;

			// Calculate how many columns the image will have to
			// span in order to be presented at the required size.
			while(totalWidthMM < reqImageWidthMM) {
				colWidthMM = ConvertImageUnits.widthUnits2Millimetres(
						(short)(sheet.getColumnWidth(toColumn)));
				// Note use of the cell border width constant. Testing with an image
				// declared to fit exactly into one column demonstrated that it's
				// width was greater than the width of the column the POI returned.
				// Further, this difference was a constant value that I am assuming
				// related to the cell's borders. Either way, that difference needs
				// to be allowed for in this calculation.
				totalWidthMM += (colWidthMM + ConvertImageUnits.CELL_BORDER_WIDTH_MILLIMETRES);
				toColumn++;
			}
			// De-crement by one the last column value.
			toColumn--;
			// Highly unlikely that this will be true but, if the width of a series
			// of columns is exactly equal to the required width of the image, then
			// simply build a ClientAnchorDetail object with an inset equal to the
			// total number of co-ordinate positions available in a column, a
			// from column co-ordinate (top left hand corner) equal to the value
			// of the startingColumn parameter and a to column co-ordinate equal
			// to the toColumn variable.
			//
			// Convert both values to ints to perform the test.
			if((int)totalWidthMM == (int)reqImageWidthMM) {
				// A problem could occur if the image is sized to fit into one or
				// more columns. If that occurs, the value in the toColumn variable
				// will be in error. To overcome this, there are two options, to
				// ibcrement the toColumn variable's value by one or to pass the
				// total number of co-ordinate positions to the third paramater
				// of the ClientAnchorDetail constructor. For no sepcific reason,
				// the latter option is used below.
				if(sheet instanceof HSSFSheet) {
					anchorDetail = new ClientAnchorDetail(startingColumn,
							toColumn, ConvertImageUnits.TOTAL_COLUMN_COORDINATE_POSITIONS);
				}
				else {
					anchorDetail = new ClientAnchorDetail(startingColumn,
							toColumn, (int)reqImageWidthMM * AddDimensionedImage.EMU_PER_MM);
				}
			}
			// In this case, the image will overlap part of another column and it is
			// necessary to calculate just how much - this will become the inset
			// for the ClientAnchorDetail object.
			else {
				// Firstly, claculate how much of the image should overlap into
				// the next column.
				overlapMM = reqImageWidthMM - (totalWidthMM - colWidthMM);

				// When the required size is very close indded to the column size,
				// the calcaulation above can produce a negative value. To prevent
				// problems occuring in later caculations, this is simply removed
				// be setting the overlapMM value to zero.
				if(overlapMM < 0) {
					overlapMM = 0.0D;
				}

				if(sheet instanceof HSSFSheet) {
					// Next, from the columns width, calculate how many co-ordinate
					// positons there are per millimetre
					coordinatePositionsPerMM = ConvertImageUnits.TOTAL_COLUMN_COORDINATE_POSITIONS /
							colWidthMM;
					// From this figure, determine how many co-ordinat positions to
					// inset the left hand or bottom edge of the image.
					inset = (int)(coordinatePositionsPerMM * overlapMM);
				}
				else {
					inset = (int)overlapMM * AddDimensionedImage.EMU_PER_MM;
				}

				// Now create the ClientAnchorDetail object, setting the from and to
				// columns and the inset.
				anchorDetail = new ClientAnchorDetail(startingColumn, toColumn, inset);
			}
			return(anchorDetail);
		}

		private ClientAnchorDetail calculateRowLocation(Sheet sheet,
				int startingRow, double reqImageHeightMM) {
			ClientAnchorDetail clientAnchorDetail = null;
			Row row = null;
			double rowHeightMM = 0.0D;
			double totalRowHeightMM = 0.0D;
			double overlapMM = 0.0D;
			double rowCoordinatesPerMM = 0.0D;
			int toRow = startingRow;
			int inset = 0;

			// Step through the rows in the sheet and accumulate a total of their
			// heights.
			while(totalRowHeightMM < reqImageHeightMM) {
				row = sheet.getRow(toRow);
				// Note, if the row does not already exist on the sheet then create
				// it here.
				if(row == null) {
					row = sheet.createRow(toRow);
				}
				// Get the row's height in millimetres and add to the running total.
				rowHeightMM = row.getHeightInPoints() /
						ConvertImageUnits.POINTS_PER_MILLIMETRE;
				totalRowHeightMM += rowHeightMM;
				toRow++;
			}
			// Owing to the way the loop above works, the rowNumber will have been
			// incremented one row too far. Undo that here.
			toRow--;
			// Check to see whether the image should occupy an exact number of
			// rows. If so, build the ClientAnchorDetail record to point
			// to those rows and with an inset of the total number of co-ordinate
			// position in the row.
			//
			// To overcome problems that can occur with comparing double values for
			// equality, cast both to int(s) to truncate the value; VERY crude and
			// I do not really like it!!
			if((int)totalRowHeightMM == (int)reqImageHeightMM) {
				if(sheet instanceof HSSFSheet) {
					clientAnchorDetail = new ClientAnchorDetail(startingRow, toRow,
							ConvertImageUnits.TOTAL_ROW_COORDINATE_POSITIONS);
				}
				else {
					clientAnchorDetail = new ClientAnchorDetail(startingRow, toRow,
							(int)reqImageHeightMM * AddDimensionedImage.EMU_PER_MM);
				}
			}
			else {
				// Calculate how far the image will project into the next row. Note
				// that the height of the last row assessed is subtracted from the
				// total height of all rows assessed so far.
				overlapMM = reqImageHeightMM - (totalRowHeightMM - rowHeightMM);

				// To prevent an exception being thrown when the required width of
				// the image is very close indeed to the column size.
				if(overlapMM < 0) {
					overlapMM = 0.0D;
				}

				if(sheet instanceof HSSFSheet) {
					rowCoordinatesPerMM = ConvertImageUnits.TOTAL_ROW_COORDINATE_POSITIONS /
							rowHeightMM;
					inset = (int)(overlapMM * rowCoordinatesPerMM);
				}
				else {
					inset = (int)overlapMM * AddDimensionedImage.EMU_PER_MM;
				}
				clientAnchorDetail = new ClientAnchorDetail(startingRow,
						toRow, inset);
			}
			return(clientAnchorDetail);
		}


		public class ClientAnchorDetail {

			public int fromIndex = 0;
			public int toIndex = 0;
			public int inset = 0;


			public ClientAnchorDetail(int fromIndex, int toIndex, int inset) {
				this.fromIndex = fromIndex;
				this.toIndex = toIndex;
				this.inset = inset;
			}

			public int getFromIndex() {
				return(this.fromIndex);
			}

			public int getToIndex() {
				return(this.toIndex);
			}

			public int getInset() {
				return(this.inset);
			}
		}

		public static class ConvertImageUnits {

			// Each cell conatins a fixed number of co-ordinate points; this number
			// does not vary with row height or column width or with font. These two
			// constants are defined below.
			public static final int TOTAL_COLUMN_COORDINATE_POSITIONS = 1023; // MB
			public static final int TOTAL_ROW_COORDINATE_POSITIONS = 255;     // MB
			// The resoultion of an image can be expressed as a specific number
			// of pixels per inch. Displays and printers differ but 96 pixels per
			// inch is an acceptable standard to beging with.
			public static final int PIXELS_PER_INCH = 96;                     // MB
			// Cnstants that defines how many pixels and points there are in a
			// millimetre. These values are required for the conversion algorithm.
			public static final double PIXELS_PER_MILLIMETRES = 3.78;         // MB
			public static final double POINTS_PER_MILLIMETRE = 2.83;          // MB
			// The column width returned by HSSF and the width of a picture when
			// positioned to exactly cover one cell are different by almost exactly
			// 2mm - give or take rounding errors. This constant allows that
			// additional amount to be accounted for when calculating how many
			// celles the image ought to overlie.
			public static final double CELL_BORDER_WIDTH_MILLIMETRES = 2.0D;  // MB
			public static final short EXCEL_COLUMN_WIDTH_FACTOR = 256;
			public static final int UNIT_OFFSET_LENGTH = 7;
			public static final int[] UNIT_OFFSET_MAP = new int[]
					{ 0, 36, 73, 109, 146, 182, 219 };

			/**
			 * pixel units to excel width units(units of 1/256th of a character width)
			 * @param pxs
			 * @return
			 */
			public static short pixel2WidthUnits(int pxs) {
				short widthUnits = (short) (EXCEL_COLUMN_WIDTH_FACTOR *
						(pxs / UNIT_OFFSET_LENGTH));
				widthUnits += UNIT_OFFSET_MAP[(pxs % UNIT_OFFSET_LENGTH)];
				return widthUnits;
			}

			/**
			 * excel width units(units of 1/256th of a character width) to pixel
			 * units.
			 *
			 * @param widthUnits
			 * @return
			 */
			public static int widthUnits2Pixel(short widthUnits) {
				int pixels = (widthUnits / EXCEL_COLUMN_WIDTH_FACTOR)
						* UNIT_OFFSET_LENGTH;
				int offsetWidthUnits = widthUnits % EXCEL_COLUMN_WIDTH_FACTOR;
				pixels += Math.round(offsetWidthUnits /
						((float) EXCEL_COLUMN_WIDTH_FACTOR / UNIT_OFFSET_LENGTH));
				return pixels;
			}

			/**
			 * Convert Excels width units into millimetres.
			 *
			 * @param widthUnits The width of the column or the height of the
			 *                   row in Excels units.
			 * @return A primitive double that contains the columns width or rows
			 *         height in millimetres.
			 */
			public static double widthUnits2Millimetres(short widthUnits) {
				return(ConvertImageUnits.widthUnits2Pixel(widthUnits) /
						ConvertImageUnits.PIXELS_PER_MILLIMETRES);
			}

			/**
			 * Convert into millimetres Excels width units..
			 *
			 * @param millimetres A primitive double that contains the columns
			 *                    width or rows height in millimetres.
			 * @return A primitive int that contains the columns width or rows
			 *         height in Excels units.
			 */
			public static int millimetres2WidthUnits(double millimetres) {
				return(ConvertImageUnits.pixel2WidthUnits((int)(millimetres *
						ConvertImageUnits.PIXELS_PER_MILLIMETRES)));
			}

			public static int pointsToPixels(double points) {
				return (int) Math.round(points / 72D * PIXELS_PER_INCH);
			}

			public static double pointsToMillimeters(double points) {
				return points / 72D * 25.4;
			}
		}
	}
	private MaterialMasterManager objMan=new MaterialMasterManager();

	private List<DynamicFieldEvents> dynamicFormEvents = null;
	private List<DynamicFieldEvents> dynamicMaterialfieldEvents = null;
	private List<DynamicFieldEvents> dynamicMaterialpickListOptions = null;
	private DynamicFormStructure dynamicFormStructureMaterial = null;
	private DynamicFieldManager  dynamicFieldManager = new DynamicFieldManager();
	private List<DynamicFields> dynamicFieldsListMaterial = null;
	UserRights rights=null;
	private UserRightsManager objRight = new UserRightsManager();
	private List<MaterialMaster> dynamicfields = null; 
	private CompanyAndYearSelectionManager objCompany = new CompanyAndYearSelectionManager();
	//private SalesInvoiceManager siMan=new SalesInvoiceManager();
}

