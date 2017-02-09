package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alpha.tpcsfashion.beans.ApprovedPriceList;
import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.DynamicFieldEvents;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.DynamicFormStructure;
import com.alpha.tpcsfashion.beans.GroupMaster;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.gridmaker.ApprovedPriceListGridMaker;
import com.alpha.tpcsfashion.model.ApprovedPriceListManager;
import com.alpha.tpcsfashion.model.ColumnPreferenceManager;
import com.alpha.tpcsfashion.model.DynamicFieldManager;
import com.alpha.tpcsfashion.model.GroupMasterManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.UserInfo;
import com.alpha.tpcsfashion.util.Validator;



public class ApprovedPriceListServlet {

public void doDisplayApprovedPriceList(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	
	try {
		HttpSession session=request.getSession();
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		
//		String strSearchQuery=getDefaultCriteria(request);
		String strSearchQuery="";
		String reqType=request.getParameter("request_type");
		
		if("Search".equalsIgnoreCase(reqType)){	
			strSearchQuery = getSearchCriteria(request);
			getAndSetAttributes(request);
		}
		
		dynamicfields = objManager.getDynamicFields("2",userInfo,1);
		session.setAttribute("fixedfields", dynamicfields);
		dynamicfields = objManager.getDynamicFields("2",userInfo,0);
		session.setAttribute("dynamicfields", dynamicfields);

		doGetUserRights(request,response);
		
		organizeColumns(request,strSearchQuery);

		request.setAttribute("request_type", reqType);
		session.setAttribute("seletedScreenId", 2);
		request.setAttribute("invoke_servlet", "ApprovedPriceListServlet");
		request.setAttribute("invoke_method", "doDisplayApprovedPriceList");
		
		
		dynamicfields=null;
		
		
		TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_APPROVED_PRICELIST, request,response);
		
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
}
public void organizeColumns(HttpServletRequest request,String strSearchQuery)
{
	try{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
//		ExportToPdfTool.setRoundOff(userInfo);
		request.setAttribute("subdocument_id", SubdocumentId.APPROVED_PRICELIST);

		ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
		ColumnPreference DataList=cpm.getVisibleColumnNames(SubdocumentId.APPROVED_PRICELIST, userInfo);
		Map<String,String> visColMap=cpm.getVisibleColumnNamesMap(SubdocumentId.APPROVED_PRICELIST,userInfo.getUserId(),userInfo);

		List<String> listSelectedColumns=DataList.getVisibleColumns(); 
		List<String> orderBy=DataList.getOrderBy(); 

		String strPageNo = request.getParameter("pageno");
		int ipageno =Validator.convertToInteger(strPageNo);

		PageConfig pc=new PageConfig(request.getParameter("pageno"));
		int pageCount= objMan.getPageCount(userInfo,pc, strSearchQuery);
		pc.setPageCount(pageCount);
		request.setAttribute("pc", pc);
		UserRights seRights=(UserRights) request.getSession().getAttribute("se_rights");
		UserRights sqRights=(UserRights) request.getSession().getAttribute("sq_rights");
		ApprovedPriceListGridMaker objUtil=new ApprovedPriceListGridMaker();
		
		List<Map<String,String>> objList =objMan.getAllApprovedPriceListOnColumnMapping(pc,userInfo,strSearchQuery,orderBy,screenId,tableId);
		String Grid = objUtil.formApprovedPriceListGrid(userInfo,visColMap,listSelectedColumns, objList, ipageno, bundle,seRights,sqRights,pc);
		request.setAttribute("ApprovedPriceList_grid", Grid); 
		
		listSelectedColumns=null;
		objList=null;
		cpm=null;
		pc=null;
		objUtil=null;
		Grid=null;
	}catch(Exception e){
		e.printStackTrace();
	}

}
public void doNewApprovedPriceList(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	try {
		String Mode = getMode(request);
		ApprovedPriceList appmode=new ApprovedPriceList();
		appmode.setMode(Mode);

		int supplierId=Validator.convertToInteger(request.getParameter("supplier_id"));
		int priceId=Validator.convertToInteger(request.getParameter("purchase_price_id"));
		appmode.setPurchasePriceId(priceId);
		doGetNewAndEditApprovedPriceList(request,response,supplierId,appmode);

		appmode=null;
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}
public void doGetNewAndEditApprovedPriceList(HttpServletRequest request, HttpServletResponse response,int supplierId,ApprovedPriceList appmode)throws ServletException, IOException
{
	HttpSession session=request.getSession();
	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	String clientId = (String)request.getSession().getAttribute("client_id");

	
	int iUserId=Integer.parseInt((String)request.getSession().getAttribute("login_user_id"));

	
	//DynamicFields

	dynamicFormStructureAppPriceList = dynamicFieldManager.getDynamicFormStructure(screenId, userInfo);
	request.setAttribute("dynamicFormStructureAppPriceList", dynamicFormStructureAppPriceList);
	

	dynamicFieldsListAppPriceList = dynamicFieldManager.getDynamicFields(screenId, tableId,userInfo);
	request.setAttribute("dynamicFieldsListAppPriceList", dynamicFieldsListAppPriceList);
	request.getSession().setAttribute("dynamicFieldsListAppPriceList", dynamicFieldsListAppPriceList);

	dynamicFormEvents =dynamicFieldManager.getDynamicFormEvents(screenId,userInfo);
	request.setAttribute("dynamicFormEvents", dynamicFormEvents);

	dynamicHeaderFieldEvents =dynamicFieldManager.getDynamicFieldEvents(screenId,tableId,userInfo);
	request.setAttribute("dynamicHeaderFieldEvents", dynamicHeaderFieldEvents);

	dynamicHeaderpickListOptions =dynamicFieldManager.getPickListOptions(tableId,userInfo);
	request.setAttribute("dynamicHeaderpickListOptions", dynamicHeaderpickListOptions);

	if( appmode.getMode().equals("e")){

		ApprovedPriceList heatList=objMan.getApprovedPriceheat(supplierId,userInfo);
		ApprovedPriceList detList=objMan.getApprovedPriceDetailsList(supplierId,userInfo);
		
		request.setAttribute("detList", detList.getApprovedPriceDetailList());
		request.setAttribute("detMapList", detList.getAplDetMap());
		request.setAttribute("heat_List", heatList);
		request.setAttribute("purchaseId", appmode.getPurchasePriceId());
		//DynamicFields
//		dynamicFieldsListAppPriceList = appmode.getApprovedPriceListDynList();
		request.getSession().setAttribute("dynamicFieldsListAppPriceList", dynamicFieldsListAppPriceList);
		request.setAttribute("dynamicFieldsListAppPriceList", dynamicFieldsListAppPriceList);
        
		request.setAttribute("purchase_price", detList.getEditDetailsID());

		List<Integer> detIdList=new ArrayList<Integer>();
		for(ApprovedPriceList det:detList.getApprovedPriceDetailList()){
			detIdList.add(det.getPurchasePriceId());
		}
		request.setAttribute("det_id_list",detIdList);
	}
		
	
	
	doGetUserRights(request,response);
	
	dynamicFormEvents=null;
	dynamicHeaderFieldEvents=null;
	dynamicHeaderpickListOptions=null;
	request.setAttribute("mode", appmode.getMode());
	
	
	TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_APPROVED_PRICELIST, request,response);
}

public void doSaveApprovedPriceList(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	try {
		HttpSession session=request.getSession();
		TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
		ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
		ApprovedPriceList appObj=new ApprovedPriceList();
		String saveType=request.getParameter("save_type");
		
		
		dynamicFieldsListAppPriceList = (List<DynamicFields>) session.getAttribute("dynamicFieldsListAppPriceList");

		for(DynamicFields dynamicFields: dynamicFieldsListAppPriceList) {
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
		String sqlxml = generateXMLforDynamicFields(dynamicFieldsListAppPriceList);
		String editedMode=request.getParameter("edited_mode");
		System.out.println("editedMode="+editedMode);
		
		appObj=getApprovedPriceListParameter(request,response,userInfo);
		
		
		//added for dynamic fields 
		appObj.setSqlxmlDynamicFields(sqlxml);
		String editedIds=request.getParameter("edited_ids");
		String dynEditedIds=request.getParameter("dynedited_ids");

		Map<String,String> map= new HashMap<String, String>();
		Map<String,String> mapDyn= new HashMap<String, String>();

		
	
		
		if(editedMode.equalsIgnoreCase("e")) {
			String streditedIds[] = editedIds.split(",");
			for(int i=0; i < streditedIds.length; i++){
				map.put(streditedIds[i], request.getParameter(streditedIds[i]));
			}
			String strDyneditedIds[] = dynEditedIds.split(",");
			for(int i=0; i < strDyneditedIds.length; i++){
				mapDyn.put(strDyneditedIds[i], request.getParameter(strDyneditedIds[i]));
			}
		}
		String sizeInput=doGetSizeInputGrid(request);
		
		appObj=objMan.saveApprovedPriceList(appObj,userInfo,map,mapDyn,editedMode,sizeInput);
		
		int purPriceid=appObj.getPurchasePriceId();
		int supplierid=appObj.getSupplierId();
		 //size range insert begin
		
        String insertMode=appObj.getMode();
//        doUpdateSizeInputGrid(request,purPriceid,insertMode);
       
        //size range insert end
		
		
//		System.out.println("appObj.getMode()="+appObj.getMode());
//		System.out.println("purPriceid==="+purPriceid);
//		System.out.println("supplierid="+supplierid);
		

        if(purPriceid==-1){
			
			request.setAttribute("error_message",bundle.getString("ApprovedPriceList.ApprovedPriceListAlreadyExists"));
			purPriceid=0;
			appObj.setMode("n");
			supplierid=0;
			doGetNewAndEditApprovedPriceList(request,response,supplierid,appObj);
			
		}else if(purPriceid==-2){
			
			request.setAttribute("error_message",bundle.getString("ApprovedPriceList.ApprovedPriceListMatAlreadyExists"));
			doGetNewAndEditApprovedPriceList(request,response,supplierid,appObj);
		}
		else{
		
		if(purPriceid>0) {
			if(appObj.getMode().equals("n")) {
				appObj.setMode("e");	 
			}
			request.setAttribute("success_message",bundle.getString("ApprovedPriceList.ApprovedPriceListInserted")); 
		}
		else {
			request.setAttribute("error_message",bundle.getString("ApprovedPriceList.ApprovedPriceListNotInserted")); 
		}
		}
		
		if(saveType.equals("3")){//Save Type Save & New
			
			appObj=new ApprovedPriceList();
			appObj.setMode("n");
			doGetNewAndEditApprovedPriceList(request,response,supplierid,appObj);
		}
		else  if(saveType.equals("4")){//Save Type Save & Close
			doDisplayApprovedPriceList(request,response) ;
		}
		else{
			appObj.setMode("e");
			doGetNewAndEditApprovedPriceList(request,response,supplierid,appObj);
		}
		
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}

public String doGetSizeInputGrid(HttpServletRequest request){
	 StringBuffer buffer=new StringBuffer();
try {
	 
//	    List<String> sizeIdList=null;
//	    List<String> detailsId=null;
		String detailId=request.getParameter("purchase_price");
		if(!detailId.isEmpty() && detailId!=""){
			
		buffer.append("<purchase_prices>");
		
		 detailId  = detailId.substring(0, detailId.length() - 1);
		 String[] detailIdarr=detailId.split(",");
		 List<String> detIdList=Arrays.asList(detailIdarr);
		 for(String detid:detIdList){
			if(!detid.isEmpty()){
	    String sizeIds=request.getParameter("purchase_price_"+detid+"")!=null && !request.getParameter("purchase_price_"+detid+"").isEmpty()?request.getParameter("purchase_price_"+detid+""):"";
	    if(!sizeIds.isEmpty()){
	    sizeIds  = sizeIds.substring(0, sizeIds.length() - 1);
		 String[] sizeIdarr=sizeIds.split(",");
		 List<String> sizeIdList=Arrays.asList(sizeIdarr);
		 for(String sid:sizeIdList){
	    buffer.append("<purchase_price>");	 
	    buffer.append("<purchase_price_id>");
	    buffer.append(detid);
	    buffer.append("</purchase_price_id>");
	    buffer.append("<size_id>");
	    buffer.append(sid);
	    buffer.append("</size_id>");
	    buffer.append("<size_qty>");
	    buffer.append(request.getParameter("size_"+detid+"_"+sid+""));
	    buffer.append("</size_qty>");
	    buffer.append("</purchase_price>");	 	 
		 }
			}
			}
	} 
		 
	buffer.append("</purchase_prices>");	 
//	System.out.println(buffer.toString());
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}

   return buffer.toString();
}

public void doUpdateSizeInputGrid(HttpServletRequest request, int purPriceid,String insertMode){
	try{
//		System.out.println("purPriceid I method="+purPriceid);
	TPCSUser ui=new UserInfo().getUserInfo(request);
	String rowValue="";
		  rowValue=request.getParameter("update");
    List<String> sizeIdList=null;
    List<String> detailsId=null;
    List<String> qtylist=null;
    String x="";
    if(!rowValue.isEmpty()){
	    rowValue  = rowValue.substring(0, rowValue.length() - 1);
	    
	    sizeIdList=new ArrayList<String>();
	    detailsId=new ArrayList<String>();//details id list
	    qtylist=new ArrayList<String>();// qty list
	    
	    String[] arr=rowValue.split(",");
	    for(String s1:arr){
	    	
	    	String[] ar=s1.split("&sp");
			String sizeId=ar[0];//sizeId
			sizeIdList.add(sizeId);
		
			//-------------- using edit mode-------------
			String sizeQty=ar[1];//--SizeQty
			sizeQty=sizeQty.substring(2);
			
			String[] qt=sizeQty.split("&id");
			
			String qty=qt[0];
			String detId=qt[1];//--edit id
			detailsId.add(detId);
			qtylist.add(qty);
			//--------------------- end-------------------
			
	    	String value=request.getParameter(s1);
	    	x=x+value+"&sp"+s1+","; 
	    	
//	    	System.out.println("x==="+x);
	    }
	    
	    String m=x;
	    
	    String valWithId="";
	    int v=objMan.updateSizeInputGridData(ui,purPriceid,m,valWithId,insertMode,sizeIdList,detailsId,qtylist);
    }
    sizeIdList=null;
    
	}
	catch(Exception e){
		e.printStackTrace();
	}
   
}

public void doDeleteApprovedPriceList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
{

	TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
	int enqId=Validator.convertToInteger(request.getParameter("purchase_price_id"));
	int page=Validator.convertToInteger(request.getParameter("page"));
	ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
	
	boolean isDeleted=objMan.deleteApprovedPriceList(userInfo,enqId);
	
	if(isDeleted)
	{
		request.setAttribute("success_message",bundle.getString("ApprovedPriceList.ApprovedPriceListDelete"));	
	}
	else
	{
		request.setAttribute("error_message",bundle.getString("ApprovedPriceList.ApprovedPriceListNotDelete"));
	}
	if(page>0)
	{
		ApprovedPriceList aplobj=new ApprovedPriceList();
		aplobj.setMode("n");
		doGetNewAndEditApprovedPriceList(request, response, 0, aplobj);

	}else{
		doDisplayApprovedPriceList(request, response); 
	}

	bundle=null;
}

public void doDeleteApprovedPriceListDetail(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {

	try {
		TPCSUser userInfo =(TPCSUser)request.getSession().getAttribute("user_info");
		ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
		ApprovedPriceList appobj=new ApprovedPriceList();
		appobj.setMode(getMode(request));

		

		int supplierId=Validator.convertToInteger(request.getParameter("supplier_id"));
		int priceId=Validator.convertToInteger(request.getParameter("purchase_price_id"));
		int iCount=objMan.deleteApprovedPriceListDetail(userInfo,supplierId,priceId);
		
		
		if(iCount==1)
		{
			request.setAttribute("success_message",bundle.getString("ApprovedPriceList.ApprovedPriceListDetailDeleted"));	
			doGetNewAndEditApprovedPriceList(request, response, supplierId, appobj);
		}
		else if(iCount==2)
		{
			appobj.setMode("n");
			request.setAttribute("success_message",bundle.getString("ApprovedPriceList.ApprovedPriceListDelete"));
			doGetNewAndEditApprovedPriceList(request, response, 0, appobj);
		}
		else
		{
			request.setAttribute("error_message",bundle.getString("ApprovedPriceList.ApprovedPriceListDetailNotDeleted"));
			doGetNewAndEditApprovedPriceList(request, response, supplierId, appobj);
		}
		
		appobj=null;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}

public void doDeleteBulkApprovedPriceList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
{

	TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
	String priceIds[]=request.getParameterValues("SupplierApl_Id");
	ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
	boolean isDeleted=false;
	int priceId=0;
	for(int i=0;i<priceIds.length;i++){
		priceId=Integer.parseInt(priceIds[i]);
		 
	 isDeleted=objMan.deleteApprovedPriceList(userInfo,priceId);
	 
	}
	if(isDeleted)
	{
		request.setAttribute("success_message",bundle.getString("ApprovedPriceList.ApprovedPriceListDelete"));	
	}
	else
	{
		request.setAttribute("error_message",bundle.getString("ApprovedPriceList.ApprovedPriceListNotDelete"));
	}
	
		doDisplayApprovedPriceList(request, response); 
	

	bundle=null;
}

public void doGetUserRights(HttpServletRequest request, HttpServletResponse response) {
	TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
	HttpSession session=request.getSession();
	rights =objRight.getUserRights(SubdocumentId.SALES_ENQUIRY, userInfo);
	session.setAttribute("se_rights",rights);

	
	
	rights=null;

}
private String getMode(HttpServletRequest request)
{
	String Mode= Validator.trim(request.getParameter("mode"));
	return Mode; 	
}
public void doGetSizeGrid(HttpServletRequest request,HttpServletResponse response){
	try {
		int sizeRrangeId=Validator.convertToInteger(request.getParameter("size_Rrange_Id"));
		int supplierId=Validator.convertToInteger(request.getParameter("supplier_id"));
		int purchasePriceId=Validator.convertToInteger(request.getParameter("purchase_price_id"));
		
		System.out.println("purchasePriceId===="+purchasePriceId);
		
//		String sizemode=request.getParameter("mode");
		String sizemode="n";
		TPCSUser userInfo=new UserInfo().getUserInfo(request);
		ApprovedPriceList appprice=new ApprovedPriceList();
		
		appprice.setSizeRangeGridid(sizeRrangeId);
		appprice.setSupplierId(supplierId);
		appprice.setPurchasePriceId(purchasePriceId);
		
		 String sizeGrid=objMan.getSizeGrid(userInfo,appprice,sizemode);
		
		response.setContentType("text/html");
		response.getWriter().write(sizeGrid);
		
		sizeGrid=null;
		userInfo=null;
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private ApprovedPriceList getApprovedPriceListParameter(HttpServletRequest request,HttpServletResponse response,TPCSUser userInfo) {
	ApprovedPriceList apl=new ApprovedPriceList();
	apl.setSupplierId(Validator.convertToInteger(request.getParameter("supplier_id")));
	apl.setSupplierName(request.getParameter("supplier_name"));
	
	int purchasePriceId=Validator.convertToInteger(request.getParameter("purchase_price_id"));
	int newrowid=0;
	String detId="_"+newrowid;
	apl.setMatId(Validator.convertToInteger(request.getParameter("mat_id"+detId)));
	apl.setMatName(request.getParameter("mat"+detId));
	apl.setColorId(Validator.convertToInteger(request.getParameter("color_id"+detId)));
	apl.setColorName(request.getParameter("color_name"+detId));
	apl.setUom(request.getParameter("uom"+detId));
	apl.setRate(request.getParameter("rate"+detId));
	apl.setCurrencyName(request.getParameter("currency_name"+detId));
	apl.setLeadTimeDays(Validator.convertToInteger(request.getParameter("lead_time_days"+detId)));
	apl.setSupplierDesc(request.getParameter("supplier_desc"+detId));
	apl.setMoq(request.getParameter("moq"+detId));
	apl.setSizeRangeId(Validator.convertToInteger(request.getParameter("size_range_id"+detId)));
	apl.setSizeRange(request.getParameter("size_range"+detId));
	String Mode = getMode(request);
	apl.setMode(Mode);
	apl.setPurchasePriceId(purchasePriceId);
	return apl;
}

public String getSearchCriteria(HttpServletRequest request){

	StringBuffer  strQuery=new StringBuffer("");
	TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
	
	int supplier_id=Validator.convertToInteger(request.getParameter("supplier_id"));
	int matId=Validator.convertToInteger(request.getParameter("material_id"));
	/*//For DynamicFields
	int dynamicfieldId_1 = Validator.convertToInteger(request.getParameter("dynamic_field_1"));
	int dynamicfieldId_2 = Validator.convertToInteger(request.getParameter("dynamic_field_2"));
	String dynamicfieldValue_1 =request.getParameter("dynamic_field_1_val");
	String dynamicfieldValue_2 =request.getParameter("dynamic_field_2_val");
	String dynamicdbName_1=null;
	String dynamicdbName_2=null;
	int dynamicTableId_1=0;
	int dynamicTableId_2=0;*/
	
	

	/*if(dynamicfieldId_1!=-1)
	{
		dynamicdbName_1=salesQuoteMan.getDynamicDbName(dynamicfieldId_1,userInfo);
		dynamicTableId_1=salesQuoteMan.getDynamicTableId(dynamicfieldId_1,userInfo);
		if(dynamicTableId_1==3)
		{
			if(dynamicfieldValue_1!=null && dynamicfieldValue_1.trim().length()>0){
				strQuery.append(" AND sedf."+dynamicdbName_1+" LIKE '%"+dynamicfieldValue_1.replace("'","''")+"%'  ");
			}
		}
		else if(dynamicTableId_1==4)
		{
			if(dynamicfieldValue_1!=null && dynamicfieldValue_1.trim().length()>0){
				strQuery.append(" AND seddf."+dynamicdbName_1+" LIKE '%"+dynamicfieldValue_1.replace("'","''")+"%'  ");
			}
		}
	}

	if(dynamicfieldId_2!=-1)
	{
		dynamicdbName_2=salesQuoteMan.getDynamicDbName(dynamicfieldId_2,userInfo);
		dynamicTableId_2=salesQuoteMan.getDynamicTableId(dynamicfieldId_2,userInfo);
		if(dynamicTableId_2==3)
		{
			if(dynamicfieldValue_2!=null && dynamicfieldValue_2.trim().length()>0){
				strQuery.append(" AND sedf."+dynamicdbName_2+" LIKE '%"+dynamicfieldValue_2.replace("'","''")+"%'  ");
			}
		}
		else if(dynamicTableId_2==4)
		{
			if(dynamicfieldValue_2!=null && dynamicfieldValue_2.trim().length()>0){
				strQuery.append(" AND seddf."+dynamicdbName_2+" LIKE '%"+dynamicfieldValue_2.replace("'","''")+"%'  ");
			}
		}
	}

	
	 dynamicdbName_1=null;
	 dynamicdbName_2=null;*/

	
	if(supplier_id!=0 && supplier_id!=-1){
		strQuery.append(" and pr.supplier_id="+supplier_id);
	}
	
	if(matId!=0){
		strQuery.append(" and pr.item_id="+matId);
	}
	
	
	return strQuery.toString();

}	
public void getAndSetAttributes(HttpServletRequest request){
	request.setAttribute("Supplier_name",request.getParameter("Supplier_name"));
	request.setAttribute("supplier_id",request.getParameter("supplier_id"));
	request.setAttribute("material",request.getParameter("material"));
	request.setAttribute("material_id",request.getParameter("material_id"));
			
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
public void doGetItemMasterData(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	try{
		TPCSUser ui=new UserInfo().getUserInfo(request);
		int matId=Validator.convertToInteger(request.getParameter("mat_id"));
		
		String strItemDetails=objMan.doGetItemMasterData(ui,matId);
		response.setContentType("text/xml");
		response.getWriter().write(strItemDetails);

		ui=null;
		strItemDetails=null;
	}catch(Exception e){
		e.printStackTrace();
	}
}



private ApprovedPriceListManager objMan=new ApprovedPriceListManager();
GroupMasterManager objManager=new GroupMasterManager();
private UserRights rights = null;
private UserRightsManager objRight = new UserRightsManager();	
private List<DynamicFieldEvents> dynamicFormEvents = null;
private List<DynamicFieldEvents> dynamicHeaderFieldEvents = null;
private List<DynamicFieldEvents> dynamicHeaderpickListOptions = null;
private DynamicFieldManager  dynamicFieldManager = new DynamicFieldManager();
private DynamicFormStructure dynamicFormStructureAppPriceList = null;
private List<DynamicFields> dynamicFieldsListAppPriceList = null;
private List<GroupMaster> dynamicfields = null; 
private int screenId=2;
private int tableId=2;
}
