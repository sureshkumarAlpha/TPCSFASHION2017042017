package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.DynamicFieldEvents;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.DynamicFormStructure;
import com.alpha.tpcsfashion.beans.GroupMaster;
import com.alpha.tpcsfashion.beans.GroupType;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.gridmaker.ItemGroupGridMaker;
import com.alpha.tpcsfashion.model.ColumnPreferenceManager;
import com.alpha.tpcsfashion.model.DynamicFieldManager;
import com.alpha.tpcsfashion.model.GroupMasterManager;
import com.alpha.tpcsfashion.model.GroupTypeManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.UserInfo;
import com.alpha.tpcsfashion.util.Validator;

public class GroupMasterServlet {

	public void doDisplayGroupMaster(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			HttpSession session=request.getSession();
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			//This is for page no..
			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			String requestType=request.getParameter("request_type");
			session.setAttribute("subdocument_id", SubdocumentId.ITEM_GROUPS);
			int  ipageno=1;
			String strSearhQuery="";
			if("Search".equalsIgnoreCase(requestType)){       
				strSearhQuery = getSearchCriteria(request);
				getAndSetAttributes(request);
			}
			GroupMasterManager objManager=new GroupMasterManager();
			int pageCount= objManager.getPageCount(userInfo,strSearhQuery);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);
			//---------------------

			ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
			ColumnPreference DataList=cpm.getVisibleColumnNames(SubdocumentId.ITEM_GROUPS, userInfo);
			Map<String,String> visColMap=cpm.getVisibleColumnNamesMap(SubdocumentId.ITEM_GROUPS,Integer.parseInt((String)request.getSession().getAttribute("login_user_id")),userInfo);
			List<String> listSelectedColumns=DataList.getVisibleColumns(); 
			List<String> orderBy=DataList.getOrderBy(); 

			ItemGroupGridMaker objUtil=new ItemGroupGridMaker();
			String materialAttachPath=new UserInfo().getMaterialAttachPath(request);

			GroupMaster mat= objManager.getAllItemGroupsOnColumnMapping(pc,userInfo,strSearhQuery,orderBy,screenId,tableId,materialAttachPath);
			List<Map<String,String>> objList =mat.getItemGroupMapList();

			doGetUserRights( request,  response);
			String Grid = objUtil.formItemGroupGrid(visColMap,listSelectedColumns,objList,ipageno,bundle,rights,pc);
			request.setAttribute("itemGroup_grid", Grid);

			dynamicfields = objManager.getDynamicFields("1",userInfo,1);
			session.setAttribute("fixedfields", dynamicfields);

			dynamicfields = objManager.getDynamicFields("1",userInfo,0);
			session.setAttribute("dynamicfields", dynamicfields);


			//-------------

			session.setAttribute("seletedScreenId",screenId);
			request.setAttribute("invoke_servlet", "GroupMasterServlet");
			request.setAttribute("invoke_method", "doDisplayGroupMaster");

			doGetUserRights(request,response);
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.GROUP_MASTER, request,response);

			pc=null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doDisplayMaterialAfterColumnOrganized(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{    
			HttpSession session=request.getSession();  
			TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
			String strTotalColumns = request.getParameter("tot_columns"); 

			ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
			boolean bFlag = cpm.updateColumnPreferences(strTotalColumns,SubdocumentId.ITEM_GROUPS,userInfo);

			doDisplayGroupMaster(request,response);
		}catch(Exception e){ 
			e.printStackTrace();      
		}
	}
	public void doGetUserRights(HttpServletRequest request, HttpServletResponse response)
	{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		HttpSession session=request.getSession();

		rights =objRight.getUserRights(SubdocumentId.ITEM_GROUPS, userInfo);
		session.setAttribute("group_rights",rights);

	}


	public void doNewGroupMaster(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{

			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			String grouptype=request.getParameter("grouptype");
			int grouptypeid=Validator.convertToInteger(request.getParameter("grouptypeid"));
			GroupType gt=new GroupType();
			gt.setGroupType(grouptype);
			gt.setGroupTypeId(grouptypeid);
			List<GroupType>  grouptype_list=objGroupTypeMan.getAllGroupType(userInfo);
			request.setAttribute("grouptype_list", grouptype_list);

			//DynamicFields

			dynamicFieldsListOrderHeader = dynamicFieldManager.getDynamicFields(screenId, tableId,userInfo);
			request.setAttribute("dynamicFieldsListOrderHeader", dynamicFieldsListOrderHeader);
			request.getSession().setAttribute("dynamicFieldsListOrderHeader", dynamicFieldsListOrderHeader);

			dynamicFormStructureOrderHeader = dynamicFieldManager.getDynamicFormStructure(screenId, userInfo);
			request.setAttribute("dynamicFormStructureOrderHeader", dynamicFormStructureOrderHeader);

			dynamicFormEvents =dynamicFieldManager.getDynamicFormEvents(screenId,userInfo);
			request.setAttribute("dynamicFormEvents", dynamicFormEvents);

			dynamicHeaderFieldEvents =dynamicFieldManager.getDynamicFieldEvents(screenId,tableId,userInfo);
			request.setAttribute("dynamicHeaderFieldEvents", dynamicHeaderFieldEvents);

			dynamicHeaderpickListOptions =dynamicFieldManager.getPickListOptions(tableId,userInfo);
			request.setAttribute("dynamicHeaderpickListOptions", dynamicHeaderpickListOptions);

			request.setAttribute("mode","n");
			doGetUserRights(request,response);
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_GROUP_MASTER,request,response);

			grouptype_list=null;
			gt=null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	public void doSaveGroupMaster(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
					} else {
						dynamicFields.setFieldValue((String) request.getParameter(dynamicFields.getPageFieldName()));
					}
				}
			}

			//calling xml for dynamic fields
			String sqlxml = generateXMLforDynamicFields(dynamicFieldsListOrderHeader);




			int grouptypeid=Validator.convertToInteger(request.getParameter("group_type_id"));
			String groupcode=request.getParameter("groupcode");

			String groupname=request.getParameter("groupname");
			int subgroupid=Validator.convertToInteger(request.getParameter("subgroup_id"));
			int itemtracking=Validator.convertToInteger(request.getParameter("itemtracking"));
			int barcodetracking=Validator.convertToInteger(request.getParameter("barcodetracking"));
			int status=Validator.convertToInteger(request.getParameter("group_status"));
			String mode=request.getParameter("mode");

			int iGroupId=Validator.convertToInteger(request.getParameter("group_id"));

			int lot_no_tracking=Validator.convertToInteger(request.getParameter("lot_no_tracking"));
			int date_expiry=Validator.convertToInteger(request.getParameter("date_expiry"));
			int negative_stock=Validator.convertToInteger(request.getParameter("negative_stock"));
			int issue_with_io=Validator.convertToInteger(request.getParameter("issue_with_io"));
			String tax_category=request.getParameter("tax_category");
			int colourApplicable=Validator.convertToInteger(request.getParameter("colour_applicable"));
			int sizeApplicable=Validator.convertToInteger(request.getParameter("size_applicable"));
			int stockReservation=Validator.convertToInteger(request.getParameter("stock_reservation"));
			int inspectionRequired=Validator.convertToInteger(request.getParameter("inspection_required"));
			int barcodeRequired=Validator.convertToInteger(request.getParameter("barcode_required"));

			GroupMaster gm=new GroupMaster();
			gm.setGroupId(iGroupId);
			gm.setGroupTypeId(grouptypeid);
			gm.setGroupcode(groupcode);
			gm.setGroupname(groupname);
			gm.setSubgroupid(subgroupid);
			gm.setItemtracking(itemtracking);
			gm.setBarcodetracking(barcodetracking);
			gm.setEditmode(mode);
			gm.setStatus(status);
			gm.setLotNoTracking(lot_no_tracking);
			gm.setDateExpiry(date_expiry);
			gm.setNegativeStock(negative_stock);
			gm.setIssueWithIO(issue_with_io);
			gm.setTaxCategory(tax_category);
			gm.setColorApplicable(colourApplicable);
			gm.setSizeApplicable(sizeApplicable);
			gm.setStockReservation(stockReservation);
			gm.setInspectionRequired(inspectionRequired);
			gm.setBarcodeRequired(barcodeRequired);
			

			//added for dynamic fields 
			gm.setSqlxmlDynamicFields(sqlxml);

			int trId=0;
			GroupMasterManager objManager=new GroupMasterManager();
			if(!objManager.isMasterGroupCodeNameExist(userInfo,gm))
			{
				trId = objManager.insertGroupMaster(gm,userInfo);
				if(trId>0)
				{
					mode="e";
					request.setAttribute("success_message",bundle.getString("Group.GroupInserted")); 
				}
				else{
					mode="n";
					request.setAttribute("error_message",bundle.getString("Group.GroupNotInserted")); 
				}
			}
			else
			{
				mode="n";
				request.setAttribute("error_message","Group not inserted.Group Already Exist"); 
			}

			List<GroupType>  grouptype_list=objGroupTypeMan.getAllGroupType(userInfo);
			request.setAttribute("grouptype_list", grouptype_list);


			dynamicFormStructureOrderHeader = dynamicFieldManager.getDynamicFormStructure(screenId, userInfo);
			request.setAttribute("dynamicFormStructureOrderHeader", dynamicFormStructureOrderHeader);

			dynamicFieldsListOrderHeader = dynamicFieldManager.getDynamicFields(screenId, tableId,userInfo);
			request.setAttribute("dynamicFieldsListOrderHeader", dynamicFieldsListOrderHeader);

			dynamicFormEvents =dynamicFieldManager.getDynamicFormEvents(screenId,userInfo);
			request.setAttribute("dynamicFormEvents", dynamicFormEvents);

			dynamicHeaderFieldEvents =dynamicFieldManager.getDynamicFieldEvents(screenId,tableId,userInfo);
			request.setAttribute("dynamicHeaderFieldEvents", dynamicHeaderFieldEvents);

			dynamicHeaderpickListOptions =dynamicFieldManager.getPickListOptions(tableId,userInfo);
			request.setAttribute("dynamicHeaderpickListOptions", dynamicHeaderpickListOptions);




			String saveType=request.getParameter("save_type");

			if(saveType.equals("1"))
			{
				gm=objManager.getGroupMasterInfo(screenId,tableId,userInfo,trId);

				request.setAttribute("group_info", gm);
				request.setAttribute("mode", mode);
				dynamicFieldsListOrderHeader = gm.getOrderDynList();
				request.getSession().setAttribute("dynamicFieldsListOrderHeader", dynamicFieldsListOrderHeader);
				request.setAttribute("dynamicFieldsListOrderHeader", dynamicFieldsListOrderHeader);
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_GROUP_MASTER, request,response);

			}
			else if(saveType.equals("3"))
			{
				doDisplayGroupMaster(request,response) ;
			}
			else{
				doNewGroupMaster(request,response);
			}

			gm=null;
		} catch (Exception e) {
			// TODO: handle exception 
			e.printStackTrace();
		}
	}



	public void doSaveGroupFromPage(HttpServletRequest request , HttpServletResponse response){
		try {
			TPCSUser ui=new UserInfo().getUserInfo(request);

			int grouptypeid=Validator.convertToInteger(request.getParameter("group_type"));
			String groupcode=request.getParameter("group_code");
			String groupname=request.getParameter("group_name");
			int subgroupid=Validator.convertToInteger(request.getParameter("sub_group_id"));
			int status=Validator.convertToInteger(request.getParameter("group_status"));

			int itemtracking=Validator.convertToInteger(request.getParameter("itemtracking"));
			int barcodetracking=Validator.convertToInteger(request.getParameter("barcodetracking"));

			GroupMaster objG=new GroupMaster();
			objG.setGroupTypeId(grouptypeid);
			objG.setGroupcode(groupcode);
			objG.setGroupname(groupname);
			objG.setSubgroupid(subgroupid);
			objG.setStatus(status);
			objG.setItemtracking(itemtracking);
			objG.setBarcodetracking(barcodetracking);
			objG.setEditmode("n");

			GroupMasterManager objMan=new GroupMasterManager();

			// existence checking
			boolean isExist=false;
			String strInsertDetails="";
			isExist=objMan.isMasterGroupCodeNameExist(ui, objG);
			if(!isExist)
			{
				objG=objMan.saveGroup(objG,ui);

				if(objG.isInserted()){
					strInsertDetails="1";
				}
				else{
					strInsertDetails="0";
				}
			}
			else 
			{
				strInsertDetails="2";
			}



			response.setContentType("text/html");
			response.getWriter().write(strInsertDetails);

			objG=null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public String generateXMLforDynamicFields(
			List<DynamicFields> dynamicFieldsListOrderHeader) {
		String strXML = "<screens> ";
		String formSampleOrder = "<screen name=\"Groups\">  <columns> ";

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

	public void doDeleteGroupMaster(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
		boolean isDeleted=false;
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		int soId=Validator.convertToInteger(request.getParameter("group_id"));
		GroupMasterManager objManager= new GroupMasterManager();
		isDeleted=objManager.deleteGroupMaster(userInfo,soId);
		if (isDeleted==true)
		{
			request.setAttribute("success_message", bundle.getString("Group.GroupDeleted"));
		}

		if (isDeleted== false){
			request.setAttribute("error_message",bundle.getString("Group.GroupNotDeleted") );
		}


		doDisplayGroupMaster(request, response);

	}

	public void doEditGroupMAster(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{

		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		GroupMaster gm=new GroupMaster();
		String mode=request.getParameter("mode");
		gm.setEditmode(mode);
		request.setAttribute("mode", mode);
		int groupid=Validator.convertToInteger(request.getParameter("group_id"));
		GroupMasterManager objManager= new GroupMasterManager();

		gm=objManager.getGroupMasterInfo(screenId,tableId,userInfo,groupid);
		request.setAttribute("group_info", gm);

		List<GroupType>  grouptype_list=objGroupTypeMan.getAllGroupType(userInfo);
		request.setAttribute("grouptype_list", grouptype_list);



		dynamicFieldsListOrderHeader = gm.getOrderDynList();
		request.getSession().setAttribute("dynamicFieldsListOrderHeader", dynamicFieldsListOrderHeader);

		dynamicFormStructureOrderHeader = dynamicFieldManager.getDynamicFormStructure(screenId, userInfo);
		request.setAttribute("dynamicFormStructureOrderHeader", dynamicFormStructureOrderHeader);

		dynamicFormEvents =dynamicFieldManager.getDynamicFormEvents(screenId,userInfo);
		request.setAttribute("dynamicFormEvents", dynamicFormEvents);

		dynamicHeaderFieldEvents =dynamicFieldManager.getDynamicFieldEvents(screenId,tableId,userInfo);
		request.setAttribute("dynamicHeaderFieldEvents", dynamicHeaderFieldEvents);

		dynamicHeaderpickListOptions =dynamicFieldManager.getPickListOptions(tableId,userInfo);
		request.setAttribute("dynamicHeaderpickListOptions", dynamicHeaderpickListOptions);


		TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_GROUP_MASTER, request,
				response);

	}
	public void getAndSetAttributes(HttpServletRequest request){
		request.setAttribute("page_size", PageConfig.iPageSize);
		request.setAttribute("group",request.getParameter("group"));
		request.setAttribute("group_id",request.getParameter("group_id"));
		request.setAttribute("group_type_id",request.getParameter("group_type_id"));
		request.setAttribute("group_type_name",request.getParameter("group_type_name"));
		request.setAttribute("g_status",request.getParameter("g_status"));
		request.setAttribute("groupcode",request.getParameter("groupcode"));


		request.setAttribute("dynamic_field_1",request.getParameter("dynamic_field_1"));
		request.setAttribute("dynamic_field_2",request.getParameter("dynamic_field_2"));
		request.setAttribute("dynamic_field_1_val",request.getParameter("dynamic_field_1_val"));
		request.setAttribute("dynamic_field_2_val",request.getParameter("dynamic_field_2_val"));
	}
	
	public String getSearchCriteria(HttpServletRequest request){

		StringBuffer  strQuery=new StringBuffer();
		String retString="";

		int groupType=Validator.convertToInteger(request.getParameter("group_type_id"));
		int groupId=Validator.convertToInteger(request.getParameter("group_id"));
		String status=request.getParameter("g_status");
		String groupcode=request.getParameter("groupcode");


		//For DynamicFields
		String dynamicDBField1 = request.getParameter("dynamic_field_1");
		String dynamicDBField2 = request.getParameter("dynamic_field_2");
		String dynamicDBFieldVal1 =request.getParameter("dynamic_field_1_val");
		String dynamicDBFieldVal2 =request.getParameter("dynamic_field_2_val");


		if(dynamicDBField1!=null && !dynamicDBField1.equals("-1")){
			if(dynamicDBFieldVal1!=null && dynamicDBFieldVal1.trim().length()>0){
				strQuery.append(" gdf."+dynamicDBField1+" LIKE '%"+dynamicDBFieldVal1.replace("'","''")+"%'  and ");
			}
		}

		if(dynamicDBField2!=null && !dynamicDBField2.equals("-1")){
			if(dynamicDBFieldVal2!=null && dynamicDBFieldVal2.trim().length()>0){
				strQuery.append(" gdf."+dynamicDBField2+" LIKE '%"+dynamicDBFieldVal2.replace("'","''")+"%' and ");
			}
		}

		if(groupType!=0 && groupType!=-1){
			strQuery.append("  gt.group_type_id="+groupType+" and ");
		}
		if(groupId!=0){
			strQuery.append(" g1.group_id="+groupId+" and ");
		}
		if(status!=null && !status.equals("-1")){
			strQuery.append("  g1.status="+Validator.convertToInteger(status)+" and ");
		}
		if(groupcode!=null && !groupcode.isEmpty()){
			strQuery.append("  g1.group_code='"+groupcode+"' and ");
		}
		if(!strQuery.toString().isEmpty() && strQuery.toString().length()>4){
			retString=strQuery.toString().substring(0, strQuery.toString().length()-4);
		}

		strQuery=null;
		return retString;

	}

	public void doGetModalGroupType(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{ 
			TPCSUser ui=new UserInfo().getUserInfo(request);
			GroupMasterManager objManager= new GroupMasterManager();
			String retGroup= objManager.getGroupType(ui);

			response.setContentType("text/xml");
			response.getWriter().write(retGroup);
			ui=null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void doSaveGroupTypeFromPage(HttpServletRequest request , HttpServletResponse response){
		try {
			TPCSUser ui=new UserInfo().getUserInfo(request);

			GroupMaster gm=new GroupMaster();

			gm.setGrouptype(request.getParameter("grouptype_name"));
			gm.setParantGroupTypeName(request.getParameter("parentgroup_type_name"));
			gm.setParantGroupTypeId(Validator.convertToInteger(request.getParameter("parentgroup_type_id")));
			gm.setSeqNo(Validator.convertToInteger(request.getParameter("seqno")));

			GroupMasterManager objManager= new GroupMasterManager();
			// existence checking And Save
			gm=objManager.savegrouptype(gm,ui);


			String strInsertDetails="";

			if(gm.isGrouptypeExists()){
				strInsertDetails="2";
			}
			else if(gm.isInserted()){
				strInsertDetails="1";
			}
			else{
				strInsertDetails="0";
			}

			response.setContentType("text/html");
			response.getWriter().write(strInsertDetails);
			gm=null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doGetPopupGroupTypeDisplaySlNo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{ 
			TPCSUser ui=new UserInfo().getUserInfo(request);
			System.out.println("--------Gl TYPE-----");
			GroupMasterManager objManager= new GroupMasterManager();
			String retGroupslno= objManager.getGroupTypeSlno(ui);
			response.setContentType("text/xml");
			response.getWriter().write(retGroupslno);
			ui=null;
			retGroupslno=null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void doBulkActiveGroupMaster(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		boolean isBulkAction=false;
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		String[] groupmaster_id=request.getParameterValues("groupmaster_id");
		String active_mode=request.getParameter("active_mode");
		GroupMasterManager objManager= new GroupMasterManager();
		isBulkAction=objManager.bulkActiveGroupMaster(userInfo,groupmaster_id,active_mode);
		if (isBulkAction==true)
		{
			if(active_mode.equals("1")){
				request.setAttribute("success_message","Group Activated!");
			}
			else{
				request.setAttribute("success_message","Group Inactivated!");
			}
		}

		if (isBulkAction== false){

			if(active_mode.equals("1")){
				request.setAttribute("error_message","Group Not Activated!");
			}
			else{
				request.setAttribute("error_message","Group Not Inactivated!");
			}
		}

		doDisplayGroupMaster(request, response);
		userInfo=null;
		objManager=null;
	}
	public void doDeleteBulkGroupMaster(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		String[] groupmaster_id=request.getParameterValues("groupmaster_id");
		boolean isDeleted=false;

		GroupMaster objG=new GroupMaster();
		GroupMasterManager objManager= new GroupMasterManager();
		int groupId;
		for(int i=0;i<groupmaster_id.length;i++){

			groupId=Integer.parseInt(groupmaster_id[i]);	

			objG.setGroupId(groupId);

			isDeleted=objManager.deleteGroupMaster(userInfo,groupId);


		}

		if (isDeleted==true)
		{
			request.setAttribute("success_message", bundle.getString("Group.GroupDeleted"));
		}

		if (isDeleted== false){
			request.setAttribute("error_message",bundle.getString("Group.GroupNotDeleted") );
		}

		doDisplayGroupMaster(request, response);
		objG=null;
		objManager=null;
	}

	UserRights rights=null;
	private UserRightsManager objRight = new UserRightsManager();
	GroupTypeManager objGroupTypeMan=new GroupTypeManager();	
	private List<DynamicFieldEvents> dynamicFormEvents = null;
	private List<DynamicFieldEvents> dynamicHeaderFieldEvents = null;
	private List<DynamicFieldEvents> dynamicHeaderpickListOptions = null;
	private DynamicFormStructure dynamicFormStructureOrderHeader = null;
	private DynamicFieldManager  dynamicFieldManager = new DynamicFieldManager();
	private List<DynamicFields> dynamicFieldsListOrderHeader = null;
	private List<GroupMaster> dynamicfields = null; 
	private int screenId=1;
	private int tableId=1;
}

