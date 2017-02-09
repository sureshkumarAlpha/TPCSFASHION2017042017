package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alpha.tpcsfashion.dao.ColumnPreferenceDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.gridmaker.VariantGridMaker;
import com.alpha.tpcsfashion.model.ColumnPreferenceManager;
import com.alpha.tpcsfashion.util.UserInfo;
import com.alpha.tpcsfashion.beans.DynamicFieldEvents;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.DynamicFormStructure;
import com.alpha.tpcsfashion.model.DynamicFieldManager;
import com.alpha.tpcsfashion.beans.Variant;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.model.VariantManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.Validator;

public class VariantServlet {
	public void doDisplayVariant(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {

			HttpSession session=request.getSession();
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");

			//This is for page no..
			PageConfig pc=new PageConfig(request.getParameter("pageno"));

			int  ipageno=1;
			rights =objRight.getUserRights(SubdocumentId.VARIANT_MASTER, userInfo);
			session.setAttribute("rights",rights);

			session.setAttribute("subdocument_id", SubdocumentId.VARIANT_MASTER);
			String requestType=request.getParameter("request_type");

			String strSearhQuery=null;
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request); 

			if("Search".equalsIgnoreCase(request.getParameter("request_type"))){
				strSearhQuery = getSearchCriteria(request);
				getAndSetAttributes(request);
			}

			int pageCount= objManager.getPageCount(userInfo,strSearhQuery);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);

			doGetUserRights(request,response);

			ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
			ColumnPreference DataList=cpm.getVisibleColumnNames(SubdocumentId.VARIANT_MASTER, userInfo);

			Map<String,String> visColMap=cpm.getVisibleColumnNamesMap(SubdocumentId.VARIANT_MASTER,userInfo.getUserId(),userInfo);
			List<String> listSelectedColumns=DataList.getVisibleColumns(); 
			List<String> orderBy=DataList.getOrderBy(); 

			VariantGridMaker objUtil=new VariantGridMaker();
			String variantAttachPath=new UserInfo().getVariantAttachPath(request);

			Variant col= objManager.getAllVariantOnColumnMapping(pc,userInfo,strSearhQuery,orderBy,screenId,tableId,variantAttachPath);
			List<Map<String,String>> objList =col.getVariantMapList();

			String Grid = objUtil.formVariantGrid(visColMap,listSelectedColumns, objList, ipageno, bundle, rights,pc);
			request.setAttribute("col_grid", Grid);

			dynamicfields = objManager.getDynamicFields(String.valueOf(tableId),userInfo,1);
			session.setAttribute("fixedfields", dynamicfields);

			dynamicfields = objManager.getDynamicFields(String.valueOf(tableId),userInfo,0);
			session.setAttribute("dynamicfields", dynamicfields);

			request.setAttribute("subdocument_id", SubdocumentId.VARIANT_MASTER);  

			if("Search".equalsIgnoreCase(requestType)){       
				getAndSetAttributes(request);
			}

			session.setAttribute("seletedScreenId",1);

			request.setAttribute("invoke_servlet", "VariantServlet");
			request.setAttribute("invoke_method", "doDisplayVariant");
			doGetUserRights(request,response);

			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_VARIANT, request,response);

			pc=null;
			cpm=null;
			DataList=null;
			visColMap=null;
			listSelectedColumns=null;
			orderBy=null;
			objUtil=null;
			variantAttachPath=null;
			col=null;
			objList=null;
			Grid=null;
			dynamicfields=null;
			rights=null;

			pc=null;
			userInfo=null;

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public Map<String,String> getVisibleColumnNamesMap(int iDocumentId, int iUserId,TPCSUser userInfo){
		Connection con=null;
		Map<String,String> visComMap=new LinkedHashMap<String,String>();
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			ColumnPreferenceDAO objColumn = new ColumnPreferenceDAO();
			con.setAutoCommit(false);      
			visComMap = objColumn.getVisibleColumnNamesMap(con,iDocumentId,iUserId);      
			con.commit();
			objColumn=null;
		}catch(Exception sqe){
			sqe.printStackTrace();    
			//DatabaseConnection.connectionRollBack(con);      
		}finally{
			try{
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return visComMap;
	}
	public void doDisplayVariantAfterColumnOrganized(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		HttpSession session=request.getSession();  
		TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
		String strTotalColumns = request.getParameter("tot_columns"); 
		ColumnPreferenceManager cpm=new ColumnPreferenceManager();  
		boolean bFlag = cpm.updateColumnPreferences(strTotalColumns,SubdocumentId.VARIANT_MASTER,userInfo);
		doDisplayVariant(request ,response);
	}

	public void doNewVariant(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			request.setAttribute("mode","n");

			//			
			dynamicFieldsListOrderHeader = dynamicFieldManager.getDynamicFields(screenId, tableId,userInfo);
			request.getSession().setAttribute("dynamicFieldsListOrderHeader", dynamicFieldsListOrderHeader);
			request.setAttribute("dynamicFieldsListOrderHeader", dynamicFieldsListOrderHeader);

			dynamicFormStructureOrderHeader = dynamicFieldManager.getDynamicFormStructure(screenId, userInfo);
			request.setAttribute("dynamicFormStructureOrderHeader", dynamicFormStructureOrderHeader);

			dynamicFormEvents =dynamicFieldManager.getDynamicFormEvents(screenId,userInfo);
			request.setAttribute("dynamicFormEvents", dynamicFormEvents);

			dynamicHeaderFieldEvents =dynamicFieldManager.getDynamicFieldEvents(screenId,tableId,userInfo);
			request.setAttribute("dynamicHeaderFieldEvents", dynamicHeaderFieldEvents);

			dynamicHeaderpickListOptions =dynamicFieldManager.getPickListOptions(tableId,userInfo);
			request.setAttribute("dynamicHeaderpickListOptions", dynamicHeaderpickListOptions);

			doGetUserRights(request,response);

			dynamicFieldsListOrderHeader=null;
			dynamicFormEvents=null;
			dynamicHeaderFieldEvents=null;
			dynamicHeaderpickListOptions=null;
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_VARIANT, request,response);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private Variant setAndGetParametter(HttpServletRequest request,HttpServletResponse response){
		String variantCode=request.getParameter("variant_code");
		int variantId=Validator.convertToInteger(request.getParameter("variant_id"));
		String description=request.getParameter("variant_name");
		String remarks=request.getParameter("remarks");
		int status=Validator.convertToInteger(request.getParameter("status"));
		String mode=request.getParameter("mode");
		System.out.println("mode="+mode);

		Variant coObj=new Variant();

		coObj.setVariantCode(variantCode);
		coObj.setVariantId(variantId);
		coObj.setVariantName(description);
		coObj.setRemarks(remarks);
		coObj.setStatus(status);
		coObj.setMode(mode);
		return coObj;
	}

	public void doSaveVariant(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			HttpSession session = request.getSession();

			dynamicFieldsListOrderHeader = (List<DynamicFields>) session.getAttribute("dynamicFieldsListOrderHeader");

			for(DynamicFields dynamicFields: dynamicFieldsListOrderHeader) {
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

						checkBoxValues=null;
					} else {
						dynamicFields.setFieldValue((String) request.getParameter(dynamicFields.getPageFieldName()));
					}
				}
			}

			//calling xml for dynamic fields
			String sqlxml = generateXMLforDynamicFields(dynamicFieldsListOrderHeader);



			String variantCode=request.getParameter("variant_code");
			int variantId=Validator.convertToInteger(request.getParameter("variant_id"));
			String variantName=request.getParameter("variant_name");
			String remarks=request.getParameter("remarks");
			int status=Validator.convertToInteger(request.getParameter("status"));
			String mode=request.getParameter("mode");

			Variant coObj=new Variant();

			coObj.setVariantCode(variantCode);
			coObj.setVariantId(variantId);
			coObj.setVariantName(variantName);
			coObj.setRemarks(remarks);
			coObj.setStatus(status);
			coObj.setMode(mode);
			coObj.setSqlxmlDynamicFields(sqlxml);

			int coId=0;

			coObj=objManager.isMasterVariantExist(userInfo,coObj);

			if(!coObj.isCodeExists() && !coObj.isDescExists()){			 
				coId = objManager.insertVariant(coObj,userInfo);

				if(coId>0){
					mode="e";
					request.setAttribute("success_message",bundle.getString("Variant.VariantInserted")); 
				}
				else{
					mode="n";
					request.setAttribute("error_message",bundle.getString("Variant.VariantNotInserted")); 
				}
			}
			else if(coObj.isCodeExists()&& coObj.isDescExists()) 
			{
				request.setAttribute("mode", mode);
				request.setAttribute("warning_message","variant not inserted.\nvariant code ("+coObj.getVariantCode()+") & Variant Name ("+coObj.getVariantName()+") already exists"); 

			}
			else if(coObj.isCodeExists())
			{
				request.setAttribute("mode", mode);

				request.setAttribute("warning_message","variant not inserted.\nvariant code ("+coObj.getVariantCode()+") already exists");

			}
			else if(coObj.isDescExists())
			{
				request.setAttribute("mode", mode);
				request.setAttribute("warning_message","Description not inserted.\n Description ("+coObj.getVariantName()+") already exists");

			}
			dynamicFieldsListOrderHeader = dynamicFieldManager.getDynamicFields(screenId, tableId,userInfo);
			request.setAttribute("dynamicFieldsListOrderHeader", dynamicFieldsListOrderHeader);

			dynamicFormEvents =dynamicFieldManager.getDynamicFormEvents(screenId,userInfo);
			request.setAttribute("dynamicFormEvents", dynamicFormEvents);

			dynamicHeaderFieldEvents =dynamicFieldManager.getDynamicFieldEvents(screenId,tableId,userInfo);
			request.setAttribute("dynamicHeaderFieldEvents", dynamicHeaderFieldEvents);

			dynamicHeaderpickListOptions =dynamicFieldManager.getPickListOptions(tableId,userInfo);
			request.setAttribute("dynamicHeaderpickListOptions", dynamicHeaderpickListOptions);

			String saveType=request.getParameter("save_type");

			if(saveType.equals("1")){
				if(coId>0){

					coObj=objManager.getVariantInfo(screenId,tableId,userInfo,coId);
					request.setAttribute("variant_info", coObj);
					request.setAttribute("mode", mode);
					System.out.println("mode1===="+mode);
				}
				else{

					Variant coObj1=new Variant();
					coObj1=setAndGetParametter(request,response);
					request.setAttribute("variant_info",coObj1);
				}

				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_VARIANT, request,response);
			}

			else if(saveType.equals("3")){

				doDisplayVariant(request,response) ;
			}
			else{

				request.setAttribute("mode","n");
				doNewVariant(request,response);
		
			}

			coObj=null;
			userInfo=null;
			bundle=null;
		}
		catch (Exception e) {
			// TODO: handle exception 
			e.printStackTrace();
		}
	}
	/*--dynamic--*/
	public String generateXMLforDynamicFields(
			List<DynamicFields> dynamicFieldsListOrderHeader) {
		String strXML = "<screens> ";
		String formSampleOrder = "<screen name=\"Variant\">  <columns> ";

		if(dynamicFieldsListOrderHeader!=null) {
			for (DynamicFields dynamicFields : dynamicFieldsListOrderHeader) {
				if (!dynamicFields.isFixed()) {
					String column = " <column type=\"dynamic\"> <name>" + dynamicFields.getDbFieldName() + "</name> <value>" + dynamicFields.getFieldValue() + "</value> <datatype>" + dynamicFields.getDataTypeName() + "</datatype> </column> ";
					formSampleOrder = formSampleOrder.concat(column);
				} 
			}
		}
		formSampleOrder = formSampleOrder.concat(" </columns> </screen> ");
		formSampleOrder = formSampleOrder.concat(" </screens> ");
		strXML = strXML.concat(formSampleOrder);
		return strXML;
	}	

	public void getAndSetAttributes(HttpServletRequest request){
		request.setAttribute("variant_code",request.getParameter("variant_code"));
		request.setAttribute("variant_name",request.getParameter("variant_name"));
	}

	public String getSearchCriteria(HttpServletRequest request){
		String strSearchCriteria="";
		String variantName=request.getParameter("variant_name");
		String variantCode=request.getParameter("variant_code");
		if(variantCode!=null && !variantCode.isEmpty()){
			strSearchCriteria=strSearchCriteria+"  variant_Code like '%"+variantCode+"%' and ";
		}
		if(variantName!=null && !variantName.isEmpty()){
			strSearchCriteria=strSearchCriteria+"  variant_Name like '%"+variantName+"%' and ";
		}
		if(!strSearchCriteria.isEmpty()){
			strSearchCriteria=strSearchCriteria.substring(0,strSearchCriteria.length()-4);

		}
		return strSearchCriteria;

	}
	public void doEditvariant(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		Variant coObj=new Variant();
		String mode=request.getParameter("mode");
		coObj.setMode(mode);
		System.out.println("modeedit===="+mode);
		request.setAttribute("mode", mode);
		
		int variantId=Validator.convertToInteger(request.getParameter("variant_id"));
		System.out.println("variantId===="+variantId);
		
		coObj=objManager.getVariantInfo(screenId,tableId,userInfo,variantId);
		request.setAttribute("variant_info", coObj);
System.out.println("coObj====="+coObj);
		dynamicFieldsListOrderHeader = coObj.getVariantDynList();
		request.getSession().setAttribute("dynamicFieldsListOrderHeader", dynamicFieldsListOrderHeader);

		dynamicFormEvents =dynamicFieldManager.getDynamicFormEvents(screenId,userInfo);
		request.setAttribute("dynamicFormEvents", dynamicFormEvents);

		dynamicHeaderFieldEvents =dynamicFieldManager.getDynamicFieldEvents(screenId,tableId,userInfo);
		request.setAttribute("dynamicHeaderFieldEvents", dynamicHeaderFieldEvents);

		dynamicHeaderpickListOptions =dynamicFieldManager.getPickListOptions(tableId,userInfo);
		request.setAttribute("dynamicHeaderpickListOptions", dynamicHeaderpickListOptions);

		dynamicFormStructureOrderHeader = dynamicFieldManager.getDynamicFormStructure(screenId, userInfo);
		request.setAttribute("dynamicFormStructureOrderHeader", dynamicFormStructureOrderHeader);


		TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_VARIANT, request,response);

		coObj=null;
		dynamicFieldsListOrderHeader=null;
		dynamicFormEvents=null;
		dynamicHeaderFieldEvents=null;
		dynamicHeaderpickListOptions=null;
		dynamicFormStructureOrderHeader=null;
		userInfo=null;
	}
	public void doDeleteVariantMaster(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
		boolean isDeleted=false;
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		int variantId=Validator.convertToInteger(request.getParameter("variant_id"));
		isDeleted=objManager.deleteVariant(userInfo,variantId);
		if (isDeleted)
		{
			request.setAttribute("success_message", bundle.getString("Variant.VariantDeleted"));
		}

		else if (!isDeleted){
			request.setAttribute("error_message",bundle.getString("Variant.VariantNotDeleted") );
		}

		doDisplayVariant(request, response);
		bundle=null;
		userInfo=null;
	}
	public void doBulkActionVariant(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		boolean isBulkAction=false;
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		String[] VariantId=request.getParameterValues("bulkvariantId");
		String active_mode=request.getParameter("active_mode");

		isBulkAction=objManager.bulkActionVariant(userInfo,VariantId,active_mode);
		if (isBulkAction==true)	{
			if(active_mode.equals("1")){
				request.setAttribute("success_message","Variant Activated!");
			}
			else{
				request.setAttribute("success_message","Variant Inactivated!");
			}
		}

		if (isBulkAction== false){

			if(active_mode.equals("1")){
				request.setAttribute("error_message","Variant Not Activated!");
			}
			else{
				request.setAttribute("error_message","Variant Not Inactivated!");
			}
		}

		doDisplayVariant(request, response);
		userInfo=null;
	}

	public void doGetUserRights(HttpServletRequest request, HttpServletResponse response)
	{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		HttpSession session=request.getSession();

		rights =objRight.getUserRights(SubdocumentId.VARIANT_MASTER, userInfo);
		session.setAttribute("Variant_rights",rights);

	}

	VariantManager objManager=new VariantManager();
	UserRights rights=null;
	private UserRightsManager objRight = new UserRightsManager();
	private List<DynamicFieldEvents> dynamicFormEvents = null;
	private List<DynamicFieldEvents> dynamicHeaderFieldEvents = null;
	private List<DynamicFieldEvents> dynamicHeaderpickListOptions = null;
	private DynamicFormStructure dynamicFormStructureOrderHeader = null;
	private DynamicFieldManager  dynamicFieldManager = new DynamicFieldManager();
	private List<DynamicFields> dynamicFieldsListOrderHeader = null;
	private int screenId=4;
	private int tableId=4;

	private List<Variant> dynamicfields = null; 

}