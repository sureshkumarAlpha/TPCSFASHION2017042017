package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alpha.tpcsfashion.beans.DynamicDataType;
import com.alpha.tpcsfashion.beans.DynamicEventType;
import com.alpha.tpcsfashion.beans.DynamicField;
import com.alpha.tpcsfashion.beans.DynamicFieldType;
import com.alpha.tpcsfashion.beans.DynamicForm;
import com.alpha.tpcsfashion.beans.DynamicFormScreens;
import com.alpha.tpcsfashion.beans.DynamicJavaScript;
import com.alpha.tpcsfashion.beans.DynamicRefTable;
import com.alpha.tpcsfashion.beans.DynamicTable;
import com.alpha.tpcsfashion.beans.MenuItem;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.framework.RequestHandlerServlet;
import com.alpha.tpcsfashion.model.DynamicOrderManager;
import com.alpha.tpcsfashion.model.MenuManager;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.UserInfo;
import com.alpha.tpcsfashion.util.Validator;

/**
 * Servlet implementation class MasterColorServlet
 */
public class DynamicOrderConfig extends RequestHandlerServlet {
	private static final long serialVersionUID = 1L;


	private DynamicOrderManager objManager = new DynamicOrderManager();
	private List<DynamicField> structureList = null;
	private List<DynamicFieldType> fieldTypesList = null;
	private List<DynamicDataType> dataTypesList = null;
	private List<DynamicEventType> eventsList = null;
	private DynamicField dynamicField = null;	
	private DynamicForm dynamicForm = new DynamicForm();
	
	private static String COMP_TYPE_TEXTBOX = "textbox";
	
	private DynamicFormScreens dynamicFormScreens = new DynamicFormScreens();
	private List<DynamicRefTable> refTableList = null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DynamicOrderConfig() {
		
		super();		
		// TODO Auto-generated constructor stub
	}

	public void doConfigDynamicOrder(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			if(request.getSession().getAttribute("dynamic_order_menus")==null || request.getSession().getAttribute("tableId")==null || (Integer)request.getSession().getAttribute("tableId") == -1){
	              MenuManager objMenuManager = new MenuManager();
	             /* List<MenuItem> dynaordersubmenus = objMenuManager.getSubMenuItems(Integer.parseInt((String)request.getSession().getAttribute("login_user_id")),13,strDataSource,strDataBase,strServerIP,strSqlusername,strSqlpassword);
	              request.getSession().setAttribute("dynamic_order_menus", dynaordersubmenus);
	              */
	              refTableList = (List<DynamicRefTable>)request.getSession().getAttribute("allTableList");
	              if(request.getSession().getAttribute("allTableList")== null){
	  				refTableList = objManager.getAllTables(userInfo);
	  				request.getSession().setAttribute("allTableList", refTableList);
	  				}
	              
	              if(refTableList.size() > 0){
	            	  List<MenuItem> submenuitems=new ArrayList<MenuItem>();
	            	  for(DynamicRefTable dyTable : refTableList){
	            		  MenuItem mItem = new MenuItem();
	            		  mItem.setMenuId(dyTable.getTableId());
	            		  mItem.setMenuName(dyTable.getTableName());
	            		  mItem.setMenuURL("displayOrderTableData("+dyTable.getTableId()+")");
	            		  submenuitems.add(mItem);
	            		  mItem=null;
	            	  }
	            	  request.getSession().setAttribute("dynamic_order_menus", submenuitems);
	            	  if (request.getSession().getAttribute("tableId") == null || (Integer)request.getSession().getAttribute("tableId") == -1) {
	      				request.getSession().setAttribute("tableId", submenuitems.get(0).getMenuId()); 
	      			}
	              }
	         }
			
			// fetch all default datatypes and fieldtypes
			if(request.getSession().getAttribute("fieldTypesList")== null){
				fieldTypesList = objManager.getFieldTypes(userInfo);
				request.getSession().setAttribute("fieldTypesList", fieldTypesList);
			}
			if(request.getSession().getAttribute("dataTypesList")== null){
				dataTypesList = objManager.getDataTypes(userInfo);
				request.getSession().setAttribute("dataTypesList", dataTypesList);
			}
			if(request.getSession().getAttribute("eventsList")== null){
				eventsList = objManager.getEvents(0,userInfo);
				request.getSession().setAttribute("eventsList", eventsList);
			}
			if(request.getSession().getAttribute("formEventsList")== null){
				eventsList = objManager.getEvents(1,userInfo);
				request.getSession().setAttribute("formEventsList", eventsList);
			}
			// fetch the structure for a table	
			//dynamicForm.setFormId(1);
			dynamicForm.setTableId((Integer) request.getSession().getAttribute("tableId"));
			dynamicForm = objManager.getDynamicFieldStructure(dynamicForm,userInfo);
			
			structureList = dynamicForm.getStructureList();
			if(structureList != null){
				Collections.sort(structureList);
			}else{
				structureList = new ArrayList<DynamicField>();
			}

			request.getSession().setAttribute("dynamicForm", dynamicForm);
			
			
			refTableList=null;
			fieldTypesList=null;
			dataTypesList=null;
			eventsList=null;
			dynamicForm=null;
			structureList=null;
			/*request.getSession().setAttribute("dynamicForm", dynamicForm);
			request.getSession().setAttribute("defaultValueList", new ArrayList<String>());
			dynamicField = new DynamicField();
			dynamicField.setFieldId(-1);		
			dynamicField.setFloatPosition(structureList.size()+1);
			
			request.getSession().setAttribute("numOfColumns", dynamicForm.getNumOfSections());	
			request.getSession().setAttribute("formJavaScriptList",dynamicForm.getFormScript());
			request.getSession().setAttribute("formAlignment",dynamicForm.getAlignment());
			request.setAttribute("dynamicField", dynamicField);			
			
			request.getSession().setAttribute("structureList", structureList);		
			request.getSession().setAttribute("dynamicForm", dynamicForm);
			
			request.getSession().setAttribute("compEventTypeId", -1);
			request.getSession().setAttribute("formEventTypeId", -1);
			request.getSession().setAttribute("javaScriptList", new ArrayList<DynamicJavaScript>());
			request.getSession().setAttribute("defaultValueList", new ArrayList<String>());
			request.setAttribute("error_message", "");

			request.getSession().setAttribute("dataSavedFlag", true);
			request.getSession().setAttribute("isEditField", false);
			*/
			
			
		/*	TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
					request, response);
*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	public void doDisplayDynamicFieldScript(HttpServletRequest request,HttpServletResponse response){
//		try {
//			
//			HttpSession session=request.getSession();
//			StringBuffer buffer=new StringBuffer();
//			buffer.append("<table >");
//			buffer.append("class=\"javascript-edit\">");
//			buffer.append("<tr>");
//			buffer.append("<td align=\"left\" valign=\"top\">");
//						if(request.getParameter("isFormScript") != null && request.getParameter("isFormScript").equalsIgnoreCase("true")){ 
//							buffer.append("<select id=\"formEventTypeId\" name=\"formEventTypeId\" onchange=\"updateCompEventType();\">");	
//							if(session.getAttribute("formEventTypeId") == null || (Integer)session.getAttribute("formEventTypeId") == -1) {
//								buffer.append("<option value=\"select\" >Select</option>");
//							} 
//								 			 
//									List<DynamicEventType> scriptEvent = (List<DynamicEventType>)session.getAttribute("formEventsList");
//		                		    for (int i = 0; i < scriptEvent.size(); i++) {
//		                		    	DynamicEventType dynaScript = scriptEvent.get(i);
//		                        		 if (session.getAttribute("formEventTypeId") != null && (Integer) session.getAttribute("formEventTypeId") == dynaScript.getEventId()) {		                                   	 
//		                    			                    
//		                        			 buffer.append("<option value=\""+dynaScript.getEventId()+"\" selected>"+ dynaScript.getEventName()+"</option>");
//			                    } else {
//			                    	buffer.append("<option value=\""+dynaScript.getEventId()+"\"> >"+dynaScript.getEventName()+"</option>");
//			                     }
//			                     }							
//		                		    buffer.append("</select>");								
//							} else { 
//								buffer.append("<select id=\"compEventTypeId\" name=\"compEventTypeId\" onchange=\"updateCompEventType();\">");	
//							 if(session.getAttribute("compEventTypeId") == null || (Integer)session.getAttribute("compEventTypeId") == -1) {
//								 buffer.append("<option value=\"select\" >Select</option> "); }
//								 			 
//									List<DynamicEventType> scriptEvent = (List<DynamicEventType>)session.getAttribute("eventsList");
//		                		    for (int i = 0; i < scriptEvent.size(); i++) {
//		                		    	DynamicEventType dynaScript = scriptEvent.get(i);
//		                        		 if (session.getAttribute("compEventTypeId") != null && (Integer) session.getAttribute("compEventTypeId") == dynaScript.getEventId()) {		                                   	 
//		                    			                    
//		                        			 buffer.append("<option value=\""+ dynaScript.getEventId()+"\" selected>"+dynaScript.getEventName()+"</option>");
//			                    } else {
//			                    	buffer.append("<option value=\""+dynaScript.getEventId()+"\">"+dynaScript.getEventName()+"</option>");
//			                    }
//			                     }							
//		                		    buffer.append("</select>");
//							} 
//							
//							 buffer.append("<span style=\"color: red; font-weight: bold; margin-left: 100px;\">");
//							if(request.getAttribute("scriptMsg") !=null) {
//								request.getAttribute("scriptMsg"); 
//							} buffer.append("</span>");
//							buffer.append("</td>");
//							buffer.append("</tr>");
//					
//							buffer.append("<tr>");						
//							buffer.append("<td align=\"left\" valign=\"top\"><br/>");		
//						
//						List<DynamicJavaScript> dynamicJavaScriptList = null;
//						String sessionParamName = "";
//						if(request.getParameter("isFormScript") != null && request.getParameter("isFormScript").equalsIgnoreCase("true")){
//							dynamicJavaScriptList =(List<DynamicJavaScript>) request.getSession().getAttribute("formJavaScriptList");
//							sessionParamName = "formEventTypeId";
//							
//						}else{
//						 	dynamicJavaScriptList =(List<DynamicJavaScript>) request.getSession().getAttribute("javaScriptList");
//						 	sessionParamName = "compEventTypeId";
//						}
//						boolean showDefault= false;
//						if(dynamicJavaScriptList != null && dynamicJavaScriptList.size()>0){
//							for(DynamicJavaScript dyna :dynamicJavaScriptList){		
//								if(session.getAttribute(sessionParamName) != null &&  (Integer)session.getAttribute(sessionParamName) == dyna.getJavaScriptEventId()){
//									showDefault = true;
//						
//										
//									buffer.append("<textarea id=\"scriptArea\" name=\"scriptArea\" cols=\"60\" rows=\"20\" style=\"text-align: left; vertical-align: top;\">"+dyna.getJavaScript() +"</textarea>");	
//						} } }	
//						 if(!showDefault) {
//							 buffer.append("<textarea id=\"scriptArea\" name=\"scriptArea\" cols=\"60\" rows=\"20\" style=\"text-align: left; vertical-align: top;\"></textarea>");
//						} 
//						 buffer.append("</td>");
//						
//						
//						 buffer.append("</tr>");
//						 buffer.append("<tr>");
//
//						 buffer.append("<td align=\"right\" valign=\"top\">");
//						 buffer.append("<input type=\"button\" id=\"saveScriptFieldBtn\"value=\"Submit\" onclick=\"saveFieldLevelScript();\" class=\"btns\" style=\"padding: 3px\" />"); 
//						 buffer.append("<input type=\"button\" value=\"Close\" id=\"cancelButton\" class=\"btns\" onclick=\"window.close();\" style=\"padding: 3px; margin-left: 10px\" /></td>");
//						 buffer.append("<td>");	
//						 buffer.append("</tr>");
//
//						 buffer.append("</table>");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	public void displayOrderTableData(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// BSR: servlet for display Order and Details Table Data	
			request.getSession().setAttribute("tableId", Integer.parseInt(request.getParameter("attrTableName"))); // 1 for Order Table			
			doConfigDynamicOrder(request, response);
			doRefreshFieldsListOnScreen(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	/*
	 * To add field into structure
	 */
	public void editDynamicFormField(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {				
			String selFieldName = request.getParameter("rowSelected");
			dynamicField = new DynamicField();		
			structureList = (List<DynamicField>) request.getSession().getAttribute("structureList");
			
			for(DynamicField structureField : structureList){
				if(structureField.getFieldName().equalsIgnoreCase(selFieldName)){
					dynamicField.setFieldId(structureField.getFieldId());
					dynamicField.setFieldName(structureField.getFieldName());
					dynamicField.setLabelName(structureField.getLabelName());	
					dynamicField.setDbfieldName(structureField.getDbfieldName());
					dynamicField.setLength(structureField.getLength());
					dynamicField.setColumnWidth(structureField.getColumnWidth());
					dynamicField.setControlTypeId(structureField.getControlTypeId());
					dynamicField.setControlType(structureField.getControlType());
					dynamicField.setDataTypeId(structureField.getDataTypeId());
					dynamicField.setDataType(structureField.getDataType());
					dynamicField.setRequired(structureField.isRequired());
					dynamicField.setActive(structureField.isActive());
					dynamicField.setFixed(structureField.isFixed());	
					dynamicField.setFloatPosition(structureField.getFloatPosition());	
					dynamicField.setFieldAlignment(structureField.getFieldAlignment());
					List<DynamicJavaScript> resultJavaScriptList = new ArrayList<DynamicJavaScript>();
					DynamicJavaScript cloneJavaScriptObj = null;
					//to avoid direct update to the main list, add new object references
					for(DynamicJavaScript dyna : structureField.getDynamicJavaScriptList()){
						cloneJavaScriptObj = new DynamicJavaScript();
						cloneJavaScriptObj.setFieldId(dyna.getFieldId());
						cloneJavaScriptObj.setJavaScript(dyna.getJavaScript());
						cloneJavaScriptObj.setJavaScriptEvent(dyna.getJavaScriptEvent());
						cloneJavaScriptObj.setJavaScriptEventId(dyna.getJavaScriptEventId());
						cloneJavaScriptObj.setMethodName(dyna.getMethodName());
						cloneJavaScriptObj.setPageFieldName(dyna.getPageFieldName());
						resultJavaScriptList.add(cloneJavaScriptObj);
					}
					dynamicField.setDynamicJavaScriptList(resultJavaScriptList);
					
					List<String> dValueList = new ArrayList<String>();
					String cloneDValue = null;
					for(String dyna : structureField.getDefaultValuesList()){
						cloneDValue = dyna;
						dValueList.add(cloneDValue);
					}
					dynamicField.setDefaultValuesList(dValueList);					
					
				}
			}
			
			if(structureList != null){
				Collections.sort(structureList);
			}else{
				structureList = new ArrayList<DynamicField>();
			}	
			
			request.setAttribute("dynamicField", dynamicField);
			request.getSession().setAttribute("structureList", structureList);
			//javascript 
			request.getSession().setAttribute("javaScriptList", dynamicField.getDynamicJavaScriptList());
			request.getSession().setAttribute("defaultValueList", dynamicField.getDefaultValuesList());
			request.getSession().setAttribute("compEventTypeId", -1); //reset the session selectedEvent value			
		//	request.setAttribute("success_message", "Edit the Form Field.");
			
			//request.getSession().setAttribute("dataSavedFlag", false);
			request.getSession().setAttribute("isEditField", true);
			request.setAttribute("invoke_servlet", request.getParameter("invoke_servlet"));
			request.setAttribute("invoke_method", request.getParameter("invoke_method"));
			
			
		//	dynamicField=null;
		//	structureList=null;
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
					request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/*
	 * To delete field from structure
	 */
	public void doDeleteDynamicFormField(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {		
			String selFieldName = request.getParameter("rowSelected");
			structureList = (List<DynamicField>) request.getSession().getAttribute("structureList");
			String msg = "Form Field Not Found.";
			Iterator ite = structureList.iterator();
			while(ite.hasNext()){
				DynamicField dynaDeleteField = (DynamicField) ite.next();
				if(dynaDeleteField.getFieldName().equalsIgnoreCase(selFieldName)){
					ite.remove();
					msg = "Form Field Deleted.";
				}
			}
			if(structureList != null){
				Collections.sort(structureList);
			}else{
				structureList = new ArrayList<DynamicField>();
			}
			request.getSession().setAttribute("structureList", structureList);			
			request.getSession().setAttribute("compEventTypeId", -1);
			request.getSession().setAttribute("javaScriptList", new ArrayList<DynamicJavaScript>());
			request.getSession().setAttribute("defaultValueList", new ArrayList<String>());
			request.setAttribute("success_message", msg);
			request.getSession().setAttribute("isEditField", false);
			request.getSession().setAttribute("dataSavedFlag", false);
			doSaveFieldStructure(request, response);
			doRefreshFieldsListOnScreen(request, response);
			
			structureList=null;
			/*TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
					request, response);*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/*
	 * To save field into structure
	 */
	public void doSaveDynamicFormField(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {			
			dynamicField = new DynamicField();	
			structureList = (List<DynamicField>) request.getSession().getAttribute("structureList");
			// validation rule for existing field name
			
			if(request.getSession().getAttribute("isEditField") != null && !(Boolean)request.getSession().getAttribute("isEditField")){
					for(DynamicField structureField : structureList){
						
						/*if(structureField.getFieldName().equalsIgnoreCase(request.getParameter("attributeName"))){
							getTheFieldAttributes(request);
							request.getSession().setAttribute("structureList", structureList);
							request.setAttribute("error_message", "Error : Field Name Already Exists.");
							request.setAttribute("dynamicField", dynamicField);
							TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,request, response);							
							return;
						}*/
						if(structureField.getDbfieldName().equalsIgnoreCase(request.getParameter("attributeDBName"))){
							getTheFieldAttributes(request);
							request.getSession().setAttribute("structureList", structureList);
							request.setAttribute("error_message", "Error : DB Name Already Exists.");
							request.setAttribute("dynamicField", dynamicField);
							request.setAttribute("invoke_servlet", request.getParameter("invoke_servlet"));
							request.setAttribute("invoke_method", request.getParameter("invoke_method"));
							TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
									request, response);							
							return;
						}
					}
					
			}			
			
			if(Boolean.parseBoolean(request.getParameter("fixed"))){
					String fieldName = request.getParameter("attributeDBName");
					for(DynamicField structureField : structureList){
						if(structureField.getFieldName().equalsIgnoreCase(fieldName)){
							structureField.setFieldAlignment(request.getParameter("attributeFieldAlignment"));
							structureField.setActive(Boolean.parseBoolean(request.getParameter("active")));							
							if(checkFloatPosition(request.getParameter("attributePosition"))){
								structureField.setFloatPosition(Float.parseFloat(request.getParameter("attributePosition")));
							}else{
								structureField.setFloatPosition(structureList.size()+ 1);
							}
						}
					}
					//Begin:Included by Jinu 27-Aug-2012
					getTheCoreFieldAttributes(request);
					boolean updated = false;
					for(DynamicField structureField : structureList){
						
						if(structureField.getDbfieldName().equalsIgnoreCase(dynamicField.getDbfieldName())){
							
							
//							System.out.println("dynamicField.getLabelName() :"+dynamicField.getLabelName());
//							System.out.println("dynamicField.getColumnWidth() :"+dynamicField.getColumnWidth());
							
							 structureField.setLabelName(dynamicField.getLabelName());	
							 structureField.setColumnWidth(dynamicField.getColumnWidth());
							 structureField.setDbfieldName(dynamicField.getDbfieldName());	
							 updated = true;
						} 				
					}
					
					//End: Included by Jinu 27-Aug-2012
					request.setAttribute("dynamicField", request.getAttribute("dynamicField"));
			} else {
					//Dynamic Field Save
					getTheFieldAttributes(request);
					boolean updated = false;
					for(DynamicField structureField : structureList){
						if(structureField.getDbfieldName().equalsIgnoreCase(dynamicField.getDbfieldName())){
							 structureField.setFieldId(dynamicField.getFieldId());
							 structureField.setFieldName(dynamicField.getFieldName());
							 structureField.setLabelName(dynamicField.getLabelName());	
							 structureField.setDbfieldName(dynamicField.getDbfieldName());	
							 structureField.setLength(dynamicField.getLength());
							 structureField.setColumnWidth(dynamicField.getColumnWidth());
							 structureField.setControlTypeId(dynamicField.getControlTypeId());
							 structureField.setControlType(dynamicField.getControlType());
							 structureField.setDataTypeId(dynamicField.getDataTypeId());
							 structureField.setDataType(dynamicField.getDataType());
							 structureField.setRequired(dynamicField.isRequired());
							 structureField.setActive(dynamicField.isActive());
							 structureField.setFixed(dynamicField.isFixed());	
							 structureField.setFloatPosition(dynamicField.getFloatPosition());
							 structureField.setFieldAlignment(dynamicField.getFieldAlignment());
							 
							 //structureField.setDynamicJavaScriptList(dynamicField.getDynamicJavaScriptList());	
							 structureField.setDefaultValuesList(dynamicField.getDefaultValuesList());		
							 updated = true;
						} 				
					}
					if(!updated){
						DynamicField cloneDyna = new DynamicField();
						cloneDyna.setFieldId(-1);
						cloneDyna.setFieldName(dynamicField.getFieldName());
						cloneDyna.setLabelName(dynamicField.getLabelName());	
						cloneDyna.setDbfieldName(dynamicField.getDbfieldName());	
						cloneDyna.setLength(dynamicField.getLength());
						cloneDyna.setColumnWidth(dynamicField.getColumnWidth());
						cloneDyna.setControlTypeId(dynamicField.getControlTypeId());
						cloneDyna.setControlType(dynamicField.getControlType());
						cloneDyna.setDataTypeId(dynamicField.getDataTypeId());
						cloneDyna.setDataType(dynamicField.getDataType());
						cloneDyna.setRequired(dynamicField.isRequired());
						cloneDyna.setActive(dynamicField.isActive());
						cloneDyna.setFixed(dynamicField.isFixed());	
						cloneDyna.setFloatPosition(dynamicField.getFloatPosition());
						cloneDyna.setFieldAlignment(dynamicField.getFieldAlignment());
						
						//cloneDyna.setDynamicJavaScriptList(dynamicField.getDynamicJavaScriptList());	
						cloneDyna.setDefaultValuesList(dynamicField.getDefaultValuesList());
						structureList.add(cloneDyna);
						
						cloneDyna=null;
					}	
					request.setAttribute("dynamicField", dynamicField);
			}
			if(structureList != null){
				Collections.sort(structureList);
			}else{
				structureList = new ArrayList<DynamicField>();
			}
			
			request.getSession().setAttribute("structureList", structureList);
			
			/*request.getSession().setAttribute("structureList", structureList);
			request.setAttribute("success_message", "Form Field Saved.");
			request.getSession().setAttribute("dataSavedFlag", false);*/
			//clearAllFields(request, response);
			
			//-------------------------------------------------
			if(doSaveFieldStructure(request, response))
			{
				
				doSaveScreenFormField(request, response);
				
//				System.out.println("----DynamicRefScreenFields  IN0SERTED----");
				
			}
			else{
					request.setAttribute("error_message", "Details are not Saved. ");
				displayOrderTableData(request,response);
			}
			
			
//			dynamicField=null;
//			structureList=null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	public void clearAllFields(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		
		dynamicField = new DynamicField();
		dynamicField.setFieldId(-1);
		
		request.getSession().setAttribute("compEventTypeId", -1);
		request.getSession().setAttribute("javaScriptList", new ArrayList<DynamicJavaScript>());
		request.getSession().setAttribute("defaultValueList", new ArrayList<String>());
		request.setAttribute("dynamicField", dynamicField);
		
		request.getSession().setAttribute("isEditField", false);
		
		//dynamicField=null;
		TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
				request, response);
	}
	
	/*
	 * To delete field from structure
	 */
	public boolean doSaveFieldStructure(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean bFlag=false;
		try {
			
			
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			dynamicForm = (DynamicForm) request.getSession().getAttribute("dynamicForm");
			
			//request.getSession().setAttribute("tableId", Integer.parseInt(request.getParameter("attrTableName"))); // 1 for Order Table		
			//dynamicForm.setTableId(Integer.parseInt(request.getParameter("attrTableName")));
			dynamicForm.setTableId((Integer) request.getSession().getAttribute("tableId"));
			dynamicForm.setNumOfSections((Integer) request.getSession().getAttribute("numOfColumns"));
			dynamicForm.setAlignment((String)request.getSession().getAttribute("formAlignment"));
			dynamicForm.setFormScript((List<DynamicJavaScript>) request.getSession().getAttribute("formJavaScriptList"));
			dynamicForm.setStructureList((List<DynamicField>) request.getSession().getAttribute("structureList"));
			
			
			 bFlag=objManager.insertFieldStructureForTable(dynamicForm, userInfo);
			
			dynamicForm = new DynamicForm();
			//pnj/dynamicForm.setFormId(1);
			dynamicForm.setTableId((Integer) request.getSession().getAttribute("tableId"));
			// fetch the structure for a table
			dynamicForm = objManager.getDynamicFieldStructure(dynamicForm,userInfo);			
			
			
			//--------------Venu include these two numOfColumns,formAlignment set to new dynamicForm from session
			dynamicForm.setNumOfSections((Integer) request.getSession().getAttribute("numOfColumns"));
			dynamicForm.setAlignment((String)request.getSession().getAttribute("formAlignment"));
			
			structureList = dynamicForm.getStructureList();
			if(structureList != null){
				Collections.sort(structureList);
			}else{
				structureList = new ArrayList<DynamicField>();
			}

			dynamicField = new DynamicField();
			dynamicField.setFieldId(-1);		
			dynamicField.setFloatPosition(structureList.size()+1);
			
			structureList = dynamicForm.getStructureList();
			if(structureList != null){
				Collections.sort(structureList);
			}else{
				structureList = new ArrayList<DynamicField>();
			}

			dynamicField = new DynamicField();
			dynamicField.setFieldId(-1);		
			dynamicField.setFloatPosition(structureList.size()+1);
			
			
			
			
			request.getSession().setAttribute("numOfColumns", dynamicForm.getNumOfSections());	
			request.getSession().setAttribute("formJavaScriptList",dynamicForm.getFormScript());
			request.getSession().setAttribute("formAlignment",dynamicForm.getAlignment());
			request.setAttribute("dynamicField", dynamicField);			
			
			request.getSession().setAttribute("structureList", structureList);		
			request.getSession().setAttribute("dynamicForm", dynamicForm);
			
			request.getSession().setAttribute("compEventTypeId", -1);
			request.getSession().setAttribute("formEventTypeId", -1);
			request.getSession().setAttribute("javaScriptList", new ArrayList<DynamicJavaScript>());
			request.getSession().setAttribute("defaultValueList", new ArrayList<String>());
			
//			dynamicForm=null;
		//	structureList=null;
		//	dynamicField=null;
			         		
			//request.setAttribute("success_message", "Structure has been Saved.");
			/*request.getSession().setAttribute("isEditField", false);
			request.getSession().setAttribute("dataSavedFlag", true);*/
			
			/*TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
					request, response);*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFlag;
		
	}	
	
	public void updateNumberOfColumns(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		
		request.getSession().setAttribute("numOfColumns", Integer.parseInt(request.getParameter("numOfColumns")));
		request.getSession().setAttribute("formAlignment", null);
		request.getSession().setAttribute("dataSavedFlag", false);
		TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_ORDER_CONFIG,
				request, response);
	}
	public void updateAlignment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		
		request.getSession().setAttribute("formAlignment", request.getParameter("attributeAlignment"));
		
		request.getSession().setAttribute("dataSavedFlag", false);
		TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_ORDER_CONFIG,
				request, response);
	}
	
	public void updateCompEventType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		boolean isFormScript = false;
		if(request.getParameter("isFormScript") != null){			
			isFormScript = Boolean.parseBoolean(request.getParameter("isFormScript"));
		}
		if(isFormScript){
			request.getSession().setAttribute("formEventTypeId", Integer.parseInt(request.getParameter("formEventTypeId")));	
		}else {
			request.getSession().setAttribute("compEventTypeId", Integer.parseInt(request.getParameter("compEventTypeId")));	
		}
		request.setAttribute("scriptMsg", " ");
		TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_ORDER_FIELD_JAVASCRIPT,
				request, response);
	}
	
	public void saveFieldLevelScript(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		int eventId = -1;
		int fieldId =-1;
		boolean isFormScript = false;
		String script = request.getParameter("scriptArea");
		String fieldName = request.getParameter("fieldName");
		
		
		if(!request.getParameter("fieldId").equalsIgnoreCase("-1")){
			fieldId = Integer.parseInt(request.getParameter("fieldId"));
		}
		if(request.getParameter("isFormScript") != null){			
			isFormScript = Boolean.parseBoolean(request.getParameter("isFormScript"));
		}
		
		
		List<DynamicJavaScript> dynamicJavaScriptList = null;
		List<DynamicJavaScript> dynamicFormScriptList = null;
		List<DynamicJavaScript> commonJavaScriptList = new ArrayList<DynamicJavaScript>();
			if(isFormScript){
				if(!request.getParameter("formEventTypeId").equalsIgnoreCase("-1")){
					eventId = Integer.parseInt(request.getParameter("formEventTypeId"));
					//fieldName = ((DynamicForm)request.getSession().getAttribute("dynamicForm")).getTableName(); 					
				}	
				eventsList = (List<DynamicEventType>) request.getSession().getAttribute("formEventsList");
				dynamicFormScriptList = (List<DynamicJavaScript>) request.getSession().getAttribute("formJavaScriptList");
				if(dynamicFormScriptList == null){
					dynamicFormScriptList = new ArrayList<DynamicJavaScript>();
				}				
				commonJavaScriptList.addAll(dynamicFormScriptList);
			}else {
				if(!request.getParameter("compEventTypeId").equalsIgnoreCase("-1")){
					eventId = Integer.parseInt(request.getParameter("compEventTypeId"));
				}	
				dynamicJavaScriptList = (List<DynamicJavaScript>) request.getSession().getAttribute("javaScriptList");
				eventsList = (List<DynamicEventType>) request.getSession().getAttribute("eventsList");
				if(dynamicJavaScriptList == null){
					dynamicJavaScriptList = new ArrayList<DynamicJavaScript>();
				}
				commonJavaScriptList.addAll(dynamicJavaScriptList);
			}
		
		
		
		boolean updated = false;
		for(DynamicJavaScript dynScr : commonJavaScriptList){
			if(dynScr.getJavaScriptEventId() == eventId){
				dynScr.setPageFieldName(fieldName);
				dynScr.setJavaScript(script);
				dynScr.setJavaScriptEvent(getEventTypeNameForId(eventId,eventsList));				
				dynScr.setMethodName(fieldName+"_"+dynScr.getJavaScriptEvent()+"()");				
				updated = true;
				break;
			}
		}
		if(!updated){
			DynamicJavaScript dynaScript =new DynamicJavaScript();
			dynaScript.setFieldId(fieldId);
			dynaScript.setJavaScript(script);
			dynaScript.setJavaScriptEvent(getEventTypeNameForId(eventId,eventsList));		
			dynaScript.setJavaScriptEventId(eventId);
			dynaScript.setMethodName(fieldName+"_"+dynaScript.getJavaScriptEvent()+"()");	
			dynaScript.setPageFieldName(fieldName);
			commonJavaScriptList.add(dynaScript);			
		}	
		if(isFormScript){			
			request.getSession().setAttribute("formJavaScriptList", commonJavaScriptList);
			request.getSession().setAttribute("dataSavedFlag", false);
		}else {
			request.getSession().setAttribute("javaScriptList", commonJavaScriptList);
		}
		
		request.setAttribute("scriptMsg", "Script Saved/Updated");
		commonJavaScriptList=null;
		TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_ORDER_FIELD_JAVASCRIPT,
				request, response);		
	}
	
	public void editDefaultValue(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			int selIndex = Integer.parseInt(request.getParameter("selectedIndex"));		
			List<String> dList = (List<String>) request.getSession().getAttribute("defaultValueList");
			if (selIndex < dList.size()) {				
				request.setAttribute("selectedIndex", selIndex);				
				request.setAttribute("scriptMsg", "Edit the Value");	
			}
			request.setAttribute("length", request.getParameter("length"));
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_ORDER_DEFAULT_VALUE,
					request, response);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void deleteDefaultValue(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			int selIndex = Integer.parseInt(request.getParameter("selectedIndex"));		
			List<String> dList = (List<String>) request.getSession().getAttribute("defaultValueList");
			if (selIndex < dList.size()) {				
				dList.remove(selIndex);				
				request.setAttribute("scriptMsg", "Value is Deleted");		
				request.getSession().setAttribute("defaultValueList", dList);				
			}
			request.setAttribute("length", request.getParameter("length"));
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_ORDER_DEFAULT_VALUE,
					request, response);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void saveDefaultValues(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			List<String> dList = (List<String>) request.getSession().getAttribute("defaultValueList");
			fieldTypesList = (List<DynamicFieldType>) request.getSession().getAttribute("fieldTypesList");
			String compType = getFieldNameForId(Integer.parseInt(request.getParameter("compType")),fieldTypesList);
			
			if(dList == null){
				dList = new ArrayList<String>();
			}
			String dvalue = request.getParameter("attrDefaultValue");
			int displayOrder =Validator.convertToInteger(request.getParameter("display_order"));
			if(!compType.equalsIgnoreCase(COMP_TYPE_TEXTBOX)){
				if(!request.getParameter("selectedIndex").equalsIgnoreCase("null")&&!request.getParameter("selectedIndex").equalsIgnoreCase("")){
					int selIndex = Integer.parseInt(request.getParameter("selectedIndex"));					
					if (selIndex < dList.size()) {				
//						dList.set(selIndex,dvalue);				
						dList.set(selIndex,dvalue+"&1&"+String.valueOf(displayOrder));//concotenated display order value
						request.setAttribute("scriptMsg", "Value is Updated");	
					}
				}else{
//					dList.add(dvalue);
					dList.add(dvalue+"&1&"+String.valueOf(displayOrder));//concotenated display order value
					request.setAttribute("scriptMsg", "Value is Added");
				}
			}
			else{
					dList.clear();			
//					dList.add(dvalue);
					dList.add(dvalue+"&1&"+String.valueOf(displayOrder));//concotenated display order value
				
			}
			request.setAttribute("compType", compType);
			request.setAttribute("length", request.getParameter("length"));
			request.getSession().setAttribute("defaultValueList", dList);
			dList=null;
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_ORDER_DEFAULT_VALUE,
					request, response);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/*
	 * Dependent methods starts
	 */
	
	private void getTheFieldAttributes(HttpServletRequest request){	
		//eventsList = (List<DynamicEventType>) request.getSession().getAttribute("eventsList");
		fieldTypesList = (List<DynamicFieldType>) request.getSession().getAttribute("fieldTypesList");
		dataTypesList = (List<DynamicDataType>) request.getSession().getAttribute("dataTypesList");
		dynamicField.setFieldId(Integer.parseInt(request.getParameter("fieldId")));		
		dynamicField.setFieldName(request.getParameter("attributeDBName"));		
		dynamicField.setLabelName(request.getParameter("attributeLabel"));
		dynamicField.setDbfieldName(request.getParameter("attributeDBName"));
		dynamicField.setLength(request.getParameter("attributeLength"));
		dynamicField.setColumnWidth(request.getParameter("attributeColumnWidth"));
	//	System.out.println("request.getParameter(attributeColumnWidth) :"+request.getParameter("attributeColumnWidth"));
		dynamicField.setControlTypeId(Integer.parseInt(request.getParameter("componentType")));
		dynamicField.setControlType(getFieldNameForId(dynamicField.getControlTypeId(),fieldTypesList));
		
		if(request.getParameter("attributeDataType") != null){
		dynamicField.setDataTypeId(Integer.parseInt(request.getParameter("attributeDataType")));
		}else {
			dynamicField.setDataTypeId(1);  // 1 for String Data type
		}
		dynamicField.setDataType(getDataTypeNameForId(dynamicField.getDataTypeId(),dataTypesList));
		if(checkFloatPosition(request.getParameter("attributePosition"))){
			dynamicField.setFloatPosition(Float.parseFloat(request.getParameter("attributePosition")));
		}else{
			dynamicField.setFloatPosition(structureList.size()+ 1);
		}
		dynamicField.setRequired(Boolean.parseBoolean(request.getParameter("required")));
		dynamicField.setActive(Boolean.parseBoolean(request.getParameter("active")));
		dynamicField.setFixed(Boolean.parseBoolean(request.getParameter("fixed")));
		
		//javascript 
		dynamicField.setDynamicJavaScriptList((List<DynamicJavaScript>) request.getSession().getAttribute("javaScriptList"));	
		dynamicField.setDefaultValuesList( (List<String>) request.getSession().getAttribute("defaultValueList"));
		/*dynamicField.setJavaScript(request.getParameter("compJavaScript"));
		dynamicField.setJavaScriptEvent(request.getParameter("compJavaScriptMethod"));*/
		dynamicField.setFieldValue("");
		dynamicField.setFieldAlignment(request.getParameter("attributeFieldAlignment"));
		
		fieldTypesList=null;
		//dynamicField=null;
	}
	

	
	private boolean checkFloatPosition(String pos){
		try{
			Float.parseFloat(pos);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}

	private String getFieldNameForId(int id, List<DynamicFieldType> fieldTypeList){
		if(fieldTypeList != null && fieldTypeList.size()>0){
			for(DynamicFieldType dynaFieldType : fieldTypeList){
				if(dynaFieldType.getFieldTypeId() == id){
					return dynaFieldType.getFieldTypeName();
				}
			}
		
		}
		return "";
	}
	
	private String getDataTypeNameForId(int id, List<DynamicDataType> dataTypeList){
		if(dataTypeList != null && dataTypeList.size()>0){
			for(DynamicDataType dynaDataType : dataTypeList){
				if(dynaDataType.getDataTypeId() == id){
					return dynaDataType.getDataTypeName();
				}
			}
		
		}
		return "";
	}
	
	private String getEventTypeNameForId(int id, List<DynamicEventType> eventTypeList){
		if(eventTypeList != null && eventTypeList.size()>0){
			for(DynamicEventType dynaEventType : eventTypeList){
				if(dynaEventType.getEventId()== id){
					return dynaEventType.getEventName();
				}
			}
		
		}
		return "";
	}

	/**
	 * split screen methods
	 */
	public void doConfigDynamicScreens(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			
		//	System.out.println("screens_config_menus :"+request.getSession().getAttribute("screens_config_menus"));
			
			if(request.getSession().getAttribute("screens_config_menus")==null){	              
	              List<MenuItem> dynaordersubmenus = objManager.getAllScreenNames("I",userInfo);
	              request.getSession().setAttribute("screens_config_menus", dynaordersubmenus);
	         }			
			
			// fetch all default datatypes and fieldtypes
			if(request.getSession().getAttribute("fieldTypesList")== null){
				fieldTypesList = objManager.getFieldTypes(userInfo);
				request.getSession().setAttribute("fieldTypesList", fieldTypesList);
			}
			if(request.getSession().getAttribute("dataTypesList")== null){
				dataTypesList = objManager.getDataTypes(userInfo);
				request.getSession().setAttribute("dataTypesList", dataTypesList);
			}
			if(request.getSession().getAttribute("eventsList")== null){
				eventsList = objManager.getEvents(0,userInfo);
				request.getSession().setAttribute("eventsList", eventsList);
			}
			if(request.getSession().getAttribute("formEventsList")== null){
				eventsList = objManager.getEvents(1,userInfo);
				request.getSession().setAttribute("formEventsList", eventsList);
			}
			if(request.getSession().getAttribute("allTableList")== null){
				refTableList = objManager.getAllTables(userInfo);
				request.getSession().setAttribute("allTableList", refTableList);
			}
			// fetch the structure for a table	
			if(request.getSession().getAttribute("seletedScreenId")== null || request.getParameter("screenId") == null){	
				List<MenuItem> menuItem = (List<MenuItem>) request.getSession().getAttribute("screens_config_menus");
				if(menuItem.size()>0){
				request.getSession().setAttribute("seletedScreenId", menuItem.get(0).getMenuId());
				}
			}else {	
				//request.getSession().setAttribute("seletedScreenId", Integer.parseInt(request.getParameter("screenId")));
				request.getSession().setAttribute("seletedScreenId", Validator.convertToInteger(request.getParameter("screenId")));
			}
			
			dynamicFormScreens.setScreenId((Integer)request.getSession().getAttribute("seletedScreenId"));				
			dynamicFormScreens = objManager.getOrderStructureForTable(dynamicFormScreens,
					userInfo,"I");
			/*if (request.getSession().getAttribute("tableId") != null) {
				
					boolean found = false;
					for(DynamicTable dTable :dynamicForm.getTables()){
						if(dTable.getTableId() ==(Integer)request.getSession().getAttribute("tableId")){
							structureList = dTable.getStructureList();
							found = true;
						}
					}
					if(!found){		
							if(dynamicForm.getTables().size()>0){
							structureList = dynamicForm.getTables().get(0).getStructureList();
							request.getSession().setAttribute("tableId", dynamicForm.getTables().get(0).getTableId());
							}else{
								structureList = new ArrayList<DynamicField>();
								request.getSession().setAttribute("tableId", null);
							}
					}
					Collections.sort(structureList);
			}else{
				structureList = new ArrayList<DynamicField>();
			}*/
			structureList = new ArrayList<DynamicField>();
			dynamicField = new DynamicField();
			dynamicField.setFieldId(-1);		
			
			request.getSession().setAttribute("numOfColumns", dynamicFormScreens.getNumOfSections());	
			request.getSession().setAttribute("formJavaScriptList",dynamicFormScreens.getFormScript());
			request.getSession().setAttribute("formAlignment",dynamicFormScreens.getAlignment());
			request.setAttribute("dynamicField", dynamicField);			
			
			request.getSession().setAttribute("structureList", structureList);	
			request.getSession().setAttribute("dynamicFormScreens", dynamicFormScreens);
			
			request.getSession().setAttribute("compEventTypeId", -1);
			request.getSession().setAttribute("formEventTypeId", -1);
			request.getSession().setAttribute("javaScriptList", new ArrayList<DynamicJavaScript>());
			request.getSession().setAttribute("defaultValueList", new ArrayList<String>());
			request.setAttribute("error_message", "");

			request.getSession().setAttribute("dataSavedFlag", true);
			request.getSession().setAttribute("isEditField", false); 
			request.getSession().setAttribute("tableId", -1);
			
			request.getSession().setAttribute("screenFlow", "screens");
			request.setAttribute("invoke_servlet", request.getParameter("invoke_servlet"));
			request.setAttribute("invoke_method", request.getParameter("invoke_method"));
			
			
			fieldTypesList=null;
			dataTypesList=null;
			eventsList=null;
			refTableList=null;
			dynamicFormScreens=null;
			//structureList=null;
		//	dynamicField=null;
			
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
					request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doRefreshFieldsListOnScreen(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			
			String tableId = request.getParameter("attrTableName");
			request.getSession().setAttribute("tableId", Integer.parseInt(tableId));			
			
			dynamicFormScreens = (DynamicFormScreens) request.getSession().getAttribute("dynamicFormScreens");
			
			List<DynamicTable> dynTables = dynamicFormScreens.getTables();
			boolean found = false;
			for(DynamicTable dynTable :dynTables){
				if(dynTable.getTableId() == (Integer)request.getSession().getAttribute("tableId")){
					structureList = dynTable.getStructureList();
					found = true;
				}
			}	
			if(!found){
				String strType ="R";
				if(!request.getSession().getAttribute("screenFlow").toString().equalsIgnoreCase("reports")){
					strType = "I";
				}
				DynamicTable dyTable =(DynamicTable) objManager.getTableDetailsForTableId(dynamicFormScreens.getScreenId(),(Integer)request.getSession().getAttribute("tableId"),
						userInfo, strType);
				
				if(dyTable != null && dyTable.getStructureList() != null){
					structureList = dyTable.getStructureList();
					DynamicTable dynaTable = new DynamicTable();
					dynaTable.setTableId((Integer) request.getSession().getAttribute("tableId"));
					dynaTable.setStructureList(structureList);
					dynamicFormScreens.getTables().add(dynaTable);
				}
			}
			
			if(structureList != null){
				Collections.sort(structureList);
			}else{
				structureList = new ArrayList<DynamicField>();
			}
			dynamicField = new DynamicField();
			dynamicField.setFieldId(-1);		
			//dynamicField.setPosition(structureList.size()+1);
			
			
			request.getSession().setAttribute("numOfColumns", dynamicFormScreens.getNumOfSections());	
			request.getSession().setAttribute("formJavaScriptList",dynamicFormScreens.getFormScript());
			request.getSession().setAttribute("formAlignment",dynamicFormScreens.getAlignment());
			request.setAttribute("dynamicField", dynamicField);			
			
			request.getSession().setAttribute("structureList", structureList);		
			request.getSession().setAttribute("dynamicFormScreens", dynamicFormScreens);
			
			request.getSession().setAttribute("compEventTypeId", -1);
			request.getSession().setAttribute("formEventTypeId", -1);
			request.getSession().setAttribute("javaScriptList", new ArrayList<DynamicJavaScript>());
			request.getSession().setAttribute("defaultValueList", new ArrayList<String>());
			request.setAttribute("error_message", "");

			request.getSession().setAttribute("dataSavedFlag", true);
			request.getSession().setAttribute("isEditField", false);
			
			request.setAttribute("invoke_servlet", request.getParameter("invoke_servlet"));
			request.setAttribute("invoke_method", request.getParameter("invoke_method"));
			
			
			dynamicFormScreens=null;
			structureList=null;
			
			if(!request.getSession().getAttribute("screenFlow").toString().equalsIgnoreCase("reports")){
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
						request, response);
				}else {
					TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_REPORTS_CONFIG,
							request, response);
				}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doAddTableToScreen(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		dynamicFormScreens = (DynamicFormScreens) request.getSession().getAttribute("dynamicFormScreens");
		structureList = (List<DynamicField>) request.getSession().getAttribute("structureList");
		List<DynamicTable> dynaTableList = dynamicFormScreens.getTables();
		if(request.getSession().getAttribute("tableId") == null || request.getSession().getAttribute("tableId") == ""){
			request.setAttribute("error_message", "Please select a Table to Add.");
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
					request, response);
			return;
		}
		boolean updated = false;
		for(DynamicTable dynaTable : dynaTableList){
			if(dynaTable.getTableId() == (Integer) request.getSession().getAttribute("tableId")){
				dynaTable.setStructureList(structureList);
				updated = true;
			}
		}
		
		if(!updated){
			DynamicTable dynaTable = new DynamicTable();
			dynaTable.setTableId((Integer) request.getSession().getAttribute("tableId"));
			dynaTable.setStructureList(structureList);
			dynaTableList.add(dynaTable);
		}
		request.getSession().setAttribute("dynamicFormScreens", dynamicFormScreens);
		request.getSession().setAttribute("dataSavedFlag", false);
		
		dynamicFormScreens=null;
		structureList=null;
		if(!request.getSession().getAttribute("screenFlow").toString().equalsIgnoreCase("reports")){
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
					request, response);
			}else {
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_REPORTS_CONFIG,
						request, response);
			}
	}
	
	public void removeTableFromScreen(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		dynamicFormScreens = (DynamicFormScreens) request.getSession().getAttribute("dynamicFormScreens");
		//structureList = (List<DynamicField>) request.getSession().getAttribute("structureList");			
		Iterator ite = dynamicFormScreens.getTables().iterator();
		if(request.getParameter("selectedStructTableIds") != null && !request.getParameter("selectedStructTableIds").equalsIgnoreCase("")){
			while(ite.hasNext()){
				DynamicTable dynaTable =(DynamicTable) ite.next();
				if(dynaTable.getTableId() == (Integer.parseInt(request.getParameter("selectedStructTableIds")))){
					ite.remove();				
					request.setAttribute("success_message", "Table is Removed.");
				}
			}
		}else{
			request.setAttribute("error_message", "Please select a Table.");
		}
		request.getSession().setAttribute("structureList", new ArrayList<DynamicField>());
		request.getSession().setAttribute("dynamicFormScreens", dynamicFormScreens);
		request.getSession().setAttribute("dataSavedFlag", false);
		request.getSession().setAttribute("tableId", -1);
		
		dynamicFormScreens=null;
		structureList=null;
		if(!request.getSession().getAttribute("screenFlow").toString().equalsIgnoreCase("reports")){
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
					request, response);
			}else {
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_REPORTS_CONFIG,
						request, response);
			}
	}
	
	
	/*
	 * To save field into structure
	 */
	public void doSaveScreenFormField(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {			
			
			
			dynamicField = new DynamicField();	
			//int oldPosition = dynamicField.getPosition(); //session object value			
			structureList = (List<DynamicField>) request.getSession().getAttribute("structureList");
			// validation rule for existing field name
			if(Boolean.parseBoolean(request.getParameter("fixed"))){
					String fieldName = request.getParameter("attributeDBName");
					for(DynamicField structureField : structureList){
						if(structureField.getFieldName().equalsIgnoreCase(fieldName)){
							structureField.setFieldAlignment(request.getParameter("attributeFieldAlignment"));
							//structureField.setFieldAlignment("L");//request.getParameter("attributeFieldAlignment"));
							structureField.setActive(Boolean.parseBoolean(request.getParameter("active")));
							
							if(checkFloatPosition(request.getParameter("attributePosition"))){
								structureField.setFloatPosition(Float.parseFloat(request.getParameter("attributePosition")));
							}
							else{
								structureField.setFloatPosition(structureList.size()+ 1);
							}
						}
					}
					//request.setAttribute("success_message", "Form Field Saved.");
					request.setAttribute("dynamicField", request.getAttribute("dynamicField"));
			} 
			else {
					//Dynamic Field Save					
					for(DynamicField structureField : structureList){
						if(structureField.getFieldName().equalsIgnoreCase(request.getParameter("attributeDBName"))){
							 structureField.setLabelName(request.getParameter("attributeLabel"));	
							 structureField.setColumnWidth(request.getParameter("attributeColumnWidth"));	
							 structureField.setActive(Boolean.parseBoolean(request.getParameter("active")));								
							 structureField.setFloatPosition(Float.parseFloat(request.getParameter("attributePosition")));
							 structureField.setFieldAlignment(request.getParameter("attributeFieldAlignment")); 	
							// structureField.setFieldAlignment("L");//request.getParameter("attributeFieldAlignment")); 							 
							 structureField.setDynamicJavaScriptList((List<DynamicJavaScript>) request.getSession().getAttribute("javaScriptList"));	
							//request.setAttribute("success_message", "Form Field Saved.");
						} 				
					}
			}
			if(structureList != null){
				Collections.sort(structureList);
			}else{
				structureList = new ArrayList<DynamicField>();
			}
			
			//request.getSession().setAttribute("structureList", structureList);			
			//request.getSession().setAttribute("dataSavedFlag", false);
		//	clearAllScreenFields(request, response);
			
			doSaveFieldStructureForScreens(request, response);
			
		} catch (Exception e) {   
			e.printStackTrace();
		}
		
	}	
	
	/*
	 * To add field into structure
	 */
	public void editScreenFormField(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {				
			String selFieldName = request.getParameter("rowSelected");
			dynamicField = new DynamicField();		
			structureList = (List<DynamicField>) request.getSession().getAttribute("structureList");
			
			for(DynamicField structureField : structureList){
				if(structureField.getFieldName().equalsIgnoreCase(selFieldName)){
					dynamicField.setFieldId(structureField.getFieldId());
					dynamicField.setFieldName(structureField.getFieldName());
					dynamicField.setLabelName(structureField.getLabelName());	
					dynamicField.setDbfieldName(structureField.getDbfieldName());
					dynamicField.setLength(structureField.getLength());
					dynamicField.setColumnWidth(structureField.getColumnWidth());
					dynamicField.setControlTypeId(structureField.getControlTypeId());
					dynamicField.setControlType(structureField.getControlType());
					dynamicField.setDataTypeId(structureField.getDataTypeId());
					dynamicField.setDataType(structureField.getDataType());
					dynamicField.setRequired(structureField.isRequired());
					dynamicField.setActive(structureField.isActive());
					dynamicField.setFixed(structureField.isFixed());	
					dynamicField.setFloatPosition(structureField.getFloatPosition());	
					dynamicField.setFieldAlignment(structureField.getFieldAlignment());
					List<DynamicJavaScript> resultJavaScriptList = new ArrayList<DynamicJavaScript>();
					DynamicJavaScript cloneJavaScriptObj = null;
					//to avoid direct update to the main list, add new object references
					for(DynamicJavaScript dyna : structureField.getDynamicJavaScriptList()){
						cloneJavaScriptObj = new DynamicJavaScript();
						cloneJavaScriptObj.setFieldId(dyna.getFieldId());
						cloneJavaScriptObj.setJavaScript(dyna.getJavaScript());
						cloneJavaScriptObj.setJavaScriptEvent(dyna.getJavaScriptEvent());
						cloneJavaScriptObj.setJavaScriptEventId(dyna.getJavaScriptEventId());
						cloneJavaScriptObj.setMethodName(dyna.getMethodName());
						cloneJavaScriptObj.setPageFieldName(dyna.getPageFieldName());
						resultJavaScriptList.add(cloneJavaScriptObj);
					}
					dynamicField.setDynamicJavaScriptList(resultJavaScriptList);
					
					List<String> dValueList = new ArrayList<String>();
					String cloneDValue = null;
					for(String dyna : structureField.getDefaultValuesList()){
						cloneDValue = dyna;
						dValueList.add(cloneDValue);
					}
					dynamicField.setDefaultValuesList(dValueList);					
					
				}
			}
			if(structureList != null){
				Collections.sort(structureList);
			}else{
				structureList = new ArrayList<DynamicField>();
			}	
			
			request.setAttribute("dynamicField", dynamicField);
			request.getSession().setAttribute("structureList", structureList);
			//javascript 
			request.getSession().setAttribute("javaScriptList", dynamicField.getDynamicJavaScriptList());
			request.getSession().setAttribute("defaultValueList", dynamicField.getDefaultValuesList());
			request.getSession().setAttribute("compEventTypeId", -1); //reset the session selectedEvent value			
			request.setAttribute("success_message", "Edit the Form Field.");
			
			//request.getSession().setAttribute("dataSavedFlag", false);
			request.getSession().setAttribute("isEditField", true);
			dynamicFormScreens=null;
		//	structureList=null;
		//	dynamicField=null;
			if(!request.getSession().getAttribute("screenFlow").toString().equalsIgnoreCase("reports")){
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
						request, response);
				}else {
					TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_REPORTS_CONFIG,
							request, response);
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void clearAllScreenFields(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		
		dynamicField = new DynamicField();
		dynamicField.setFieldId(-1);
		
		request.getSession().setAttribute("compEventTypeId", -1);
		request.getSession().setAttribute("javaScriptList", new ArrayList<DynamicJavaScript>());
		request.getSession().setAttribute("defaultValueList", new ArrayList<String>());
		request.setAttribute("dynamicField", dynamicField); 
		
		request.getSession().setAttribute("isEditField", false);
		
//		dynamicField=null;
		if(!request.getSession().getAttribute("screenFlow").toString().equalsIgnoreCase("reports")){
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
					request, response);
			}else {
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_REPORTS_CONFIG,
						request, response);
			}
	}
	
	public void updateNumberOfColumnsForScreens(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		
		request.getSession().setAttribute("numOfColumns", Integer.parseInt(request.getParameter("numOfColumns")));
		request.getSession().setAttribute("formAlignment", null);
		request.getSession().setAttribute("dataSavedFlag", false);
		if(!request.getSession().getAttribute("screenFlow").toString().equalsIgnoreCase("reports")){
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
					request, response);
			}else {
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_REPORTS_CONFIG,
						request, response);
			}
	}
	public void updateAlignmentForScreens(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		
		request.getSession().setAttribute("formAlignment", request.getParameter("attributeAlignment"));
		
		request.getSession().setAttribute("dataSavedFlag", false);
		if(!request.getSession().getAttribute("screenFlow").toString().equalsIgnoreCase("reports")){
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
					request, response);
			}else {
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_REPORTS_CONFIG,
						request, response);
			}
	}
	
	/*
	 * To delete field from structure
	 */
	public void doSaveFieldStructureForScreens(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			
			
			
			boolean saveResult = false;
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			structureList = (List<DynamicField>) request.getSession().getAttribute("structureList");
			
			dynamicFormScreens = (DynamicFormScreens) request.getSession().getAttribute("dynamicFormScreens");			
			dynamicFormScreens.setNumOfSections((Integer) request.getSession().getAttribute("numOfColumns"));
			dynamicFormScreens.setAlignment((String)request.getSession().getAttribute("formAlignment"));
			dynamicFormScreens.setFormScript((List<DynamicJavaScript>) request.getSession().getAttribute("formJavaScriptList"));		
			TPCSUser ui=new UserInfo().getUserInfo(request);
			int userId=ui.getUserId();
			int screenId=0;
			if(!request.getSession().getAttribute("screenFlow").toString().equalsIgnoreCase("reports")){
				screenId=(Integer)request.getSession().getAttribute("seletedScreenId");
			}
			
	      int fieldId=Validator.convertToInteger(request.getParameter("fieldId"));
	      //System.out.println("fieldId--->"+fieldId);
			saveResult = objManager.insertOrderStructureForTable(dynamicFormScreens, userInfo,userId,screenId,fieldId);
			
			dynamicFormScreens = new DynamicFormScreens();
			if(!request.getSession().getAttribute("screenFlow").toString().equalsIgnoreCase("reports")){
				dynamicFormScreens.setScreenId((Integer)request.getSession().getAttribute("seletedScreenId"));	
			}
			else{
				dynamicFormScreens.setScreenId((Integer)request.getSession().getAttribute("seletedScreenIdForReports"));
			}
							
			// fetch the structure for a table
			String strType ="R";
			if(!request.getSession().getAttribute("screenFlow").toString().equalsIgnoreCase("reports")){
				strType = "I";
			}
			dynamicFormScreens = objManager.getOrderStructureForTable(dynamicFormScreens,userInfo, strType);			
			

			dynamicField = new DynamicField();
			dynamicField.setFieldId(-1);		
			dynamicField.setFloatPosition(structureList.size()+1);
			
			
			
			request.getSession().setAttribute("numOfColumns", dynamicFormScreens.getNumOfSections());	
			request.getSession().setAttribute("formJavaScriptList",dynamicFormScreens.getFormScript());
			request.getSession().setAttribute("formAlignment",dynamicFormScreens.getAlignment());
			request.setAttribute("dynamicField", dynamicField);			
			
		//	request.getSession().setAttribute("structureList", structureList);		
			request.getSession().setAttribute("dynamicFormScreens", dynamicFormScreens);
			
			request.getSession().setAttribute("compEventTypeId", -1);
			request.getSession().setAttribute("formEventTypeId", -1);
			request.getSession().setAttribute("javaScriptList", new ArrayList<DynamicJavaScript>());
			request.getSession().setAttribute("defaultValueList", new ArrayList<String>());
			request.getSession().setAttribute("dataSavedFlag", true);
			request.getSession().setAttribute("isEditField", false);
		
		
		
			if(saveResult){         		
				request.setAttribute("success_message", "Details are saved.");
			}else{
				request.setAttribute("error_message", "Details are not Saved. ");
			}
			displayOrderTableData(request,response);
			
			
//			structureList=null;
			dynamicFormScreens=null;
//			dynamicField=null;
			
			/*request.getSession().setAttribute("isEditField", false);
			request.getSession().setAttribute("dataSavedFlag", true);
			request.getSession().setAttribute("tableId", -1);*/
			//if(!request.getSession().getAttribute("screenFlow").toString().equalsIgnoreCase("reports")){
			/*TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
					request, response);*/
			/*}else {
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_REPORTS_CONFIG,
						request, response);
			}*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * split screen methods
	 */
	public void doConfigDynamicReports(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			if(request.getSession().getAttribute("reports_config_menus")==null){	              
	              List<MenuItem> dynaordersubmenus = objManager.getAllScreenNames("R",userInfo);
	              request.getSession().setAttribute("reports_config_menus", dynaordersubmenus);
	         }			
			
			// fetch all default datatypes and fieldtypes
			if(request.getSession().getAttribute("fieldTypesList")== null){
				fieldTypesList = objManager.getFieldTypes(userInfo);
				request.getSession().setAttribute("fieldTypesList", fieldTypesList);
			}
			if(request.getSession().getAttribute("dataTypesList")== null){
				dataTypesList = objManager.getDataTypes(userInfo);
				request.getSession().setAttribute("dataTypesList", dataTypesList);
			}
			if(request.getSession().getAttribute("eventsList")== null){
				eventsList = objManager.getEvents(0,userInfo);
				request.getSession().setAttribute("eventsList", eventsList);
			}
			if(request.getSession().getAttribute("formEventsList")== null){
				eventsList = objManager.getEvents(1,userInfo);
				request.getSession().setAttribute("formEventsList", eventsList); 
			}
			if(request.getSession().getAttribute("allTableList")== null){
				refTableList = objManager.getAllTables(userInfo);
				request.getSession().setAttribute("allTableList", refTableList);
			}
			// fetch the structure for a table	
			if(request.getSession().getAttribute("seletedScreenIdForReports")== null || request.getParameter("screenIdForReports") == null){	
				List<MenuItem> menuItem = (List<MenuItem>) request.getSession().getAttribute("reports_config_menus");
				if(menuItem.size()>0)
				request.getSession().setAttribute("seletedScreenIdForReports", menuItem.get(0).getMenuId());
			}else{				
				request.getSession().setAttribute("seletedScreenIdForReports", Integer.parseInt(request.getParameter("screenIdForReports")));
			}
			
			dynamicFormScreens.setScreenId((Integer)request.getSession().getAttribute("seletedScreenIdForReports"));	 			
			
			dynamicFormScreens = objManager.getOrderStructureForTable(dynamicFormScreens,userInfo, "R");
			/*if (request.getSession().getAttribute("tableId") != null) {
				
					boolean found = false;
					for(DynamicTable dTable :dynamicForm.getTables()){
						if(dTable.getTableId() ==(Integer)request.getSession().getAttribute("tableId")){
							structureList = dTable.getStructureList();
							found = true;
						}
					}
					if(!found){		
							if(dynamicForm.getTables().size()>0){
							structureList = dynamicForm.getTables().get(0).getStructureList();
							request.getSession().setAttribute("tableId", dynamicForm.getTables().get(0).getTableId());
							}else{
								structureList = new ArrayList<DynamicField>();
								request.getSession().setAttribute("tableId", null);
							}
					}
					Collections.sort(structureList);
			}else{
				structureList = new ArrayList<DynamicField>();
			}*/
			structureList = new ArrayList<DynamicField>();
			dynamicField = new DynamicField();
			dynamicField.setFieldId(-1);		
			
			
			request.getSession().setAttribute("numOfColumns", dynamicFormScreens.getNumOfSections());	
			request.getSession().setAttribute("formJavaScriptList",dynamicFormScreens.getFormScript());
			request.getSession().setAttribute("formAlignment",dynamicFormScreens.getAlignment());
			request.setAttribute("dynamicField", dynamicField);			
			
			request.getSession().setAttribute("structureList", structureList);		
			request.getSession().setAttribute("dynamicFormScreens", dynamicFormScreens);
			
			request.getSession().setAttribute("compEventTypeId", -1);
			request.getSession().setAttribute("formEventTypeId", -1);
			request.getSession().setAttribute("javaScriptList", new ArrayList<DynamicJavaScript>());
			request.getSession().setAttribute("defaultValueList", new ArrayList<String>());
			request.setAttribute("error_message", "" );

			request.getSession().setAttribute("dataSavedFlag", true);
			request.getSession().setAttribute("isEditField", false); 
			request.getSession().setAttribute("tableId", -1);
			
			request.getSession().setAttribute("screenFlow", "reports");
			
			fieldTypesList=null;
			dataTypesList=null;
			eventsList=null;
			refTableList=null;
			dynamicFormScreens=null;
//			structureList=null;
//			dynamicField=null;
			
			
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_REPORTS_CONFIG,
					request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * To save field into structure
	 */
	public void doSaveScreenFormFieldForReports(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {			
			dynamicField = new DynamicField();	
			//int oldPosition = dynamicField.getPosition(); //session object value			
			structureList = (List<DynamicField>) request.getSession().getAttribute("structureList");
			// validation rule for existing field name
			if(request.getSession().getAttribute("isEditField") == null || !(Boolean)request.getSession().getAttribute("isEditField")){		
				
							request.setAttribute("error_message", "You are only allowed to Edit.");
							TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DYNAMIC_SCREENS_CONFIG,
									request, response);							
							return;
			}	
			if(Boolean.parseBoolean(request.getParameter("fixed"))){
					String fieldName = request.getParameter("attributeDBName");
					for(DynamicField structureField : structureList){
						if(structureField.getFieldName().equalsIgnoreCase(fieldName)){
							structureField.setFieldAlignment(request.getParameter("attributeFieldAlignment"));
							structureField.setActive(Boolean.parseBoolean(request.getParameter("active")));							
							if(checkFloatPosition(request.getParameter("attributePosition"))){
								structureField.setFloatPosition(Float.parseFloat(request.getParameter("attributePosition")));
							}else{
								structureField.setFloatPosition(structureList.size()+ 1);
							}
						}
					}
					request.setAttribute("success_message", "Form Field Saved.");
					request.setAttribute("dynamicField", request.getAttribute("dynamicField"));
			} else {
					//Dynamic Field Save					
					for(DynamicField structureField : structureList){
						if(structureField.getFieldName().equalsIgnoreCase(request.getParameter("attributeDBName"))){
							 structureField.setLabelName(request.getParameter("attributeLabel"));	
							 structureField.setColumnWidth(request.getParameter("attributeColumnWidth"));	
							 structureField.setActive(Boolean.parseBoolean(request.getParameter("active")));								
							 structureField.setFloatPosition(Float.parseFloat(request.getParameter("attributePosition")));
							 structureField.setFieldAlignment(request.getParameter("attributeFieldAlignment")); 							 
							 //structureField.setDynamicJavaScriptList((List<DynamicJavaScript>) request.getSession().getAttribute("javaScriptList"));	
							request.setAttribute("success_message", "Form Field Saved.");
						} 				
					}
			}
			if(structureList != null){
				Collections.sort(structureList);
			}else{
				structureList = new ArrayList<DynamicField>();
			}
			
			request.getSession().setAttribute("structureList", structureList);			
			request.getSession().setAttribute("dataSavedFlag", false);
			clearAllScreenFields(request, response);
			
//			dynamicField=null;
//			structureList=null;
		} catch (Exception e) {   
			e.printStackTrace();
		}
		
	}
	
	

	private void getTheCoreFieldAttributes(HttpServletRequest request){	
			//eventsList = (List<DynamicEventType>) request.getSession().getAttribute("eventsList");
			
			dynamicField.setLabelName(request.getParameter("attributeLabel"));
			dynamicField.setColumnWidth(request.getParameter("attributeColumnWidth"));	
			dynamicField.setDbfieldName(request.getParameter("attributeDBName"));
			
		}
	

}
