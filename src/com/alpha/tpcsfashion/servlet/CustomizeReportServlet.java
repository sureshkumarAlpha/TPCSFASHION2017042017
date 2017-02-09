package com.alpha.tpcsfashion.servlet;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.model.ColumnPreferenceManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.Validator;

public class CustomizeReportServlet {
	
	public void doShowCustomizeReport(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_ALL_REPORTS,request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void doDisplayCustomizeReport(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{  
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			int subdocumentId=Validator.convertToInteger(request.getParameter("subdocument_id"));
			ColumnPreferenceManager cpm=new ColumnPreferenceManager();
    		List<Integer> visibleColumns = cpm.getVisibleColumns(subdocumentId,userInfo);
    		Map<Integer, String> columns = cpm.getAllColumns(subdocumentId,userInfo);
			
    		String column_pref =objColPreServlet.buildOrganizePopup(visibleColumns, columns, subdocumentId, bundle, userInfo);
    		request.setAttribute("column_pref", column_pref);
    		ColumnPreference colPre=cpm.getDataForReportCustomize(subdocumentId, userInfo);
    		
    		request.setAttribute("first_order_by", colPre.getFirstOrderBy());
    		request.setAttribute("second_order_by", colPre.getSecondOrderBy());
    		request.setAttribute("third_order_by", colPre.getThirdOrderBy());
    		request.setAttribute("group_by_column_list", colPre.getGroupByColumnList());
    		request.setAttribute("column_to_total_list", colPre.getColumnToTotalList());
//    		request.setAttribute("column_to_total_col_id", colPre.getColumnToTotalColId());
    		ServletContext context = request.getServletContext();
			context.setAttribute("column_to_total_col_id", colPre.getColumnToTotalColId());
//    		request.setAttribute("operator_list", colPre.getOperatorList());
//    		request.setAttribute("column_list", colPre.getColumnList());
			request.setAttribute("report_name", colPre.getReportName());
			request.setAttribute("report_url", colPre.getReportUrl());
			request.setAttribute("report_desc", colPre.getReportDesc());
    		int rowNo=1;
    		String criteriaRow=formCriteriaCode(bundle,colPre.getColumnList(),colPre.getOperatorList(),rowNo,subdocumentId);
    		request.setAttribute("criteria_row", criteriaRow);
    		request.setAttribute("criteria_row_no", rowNo);
    		request.setAttribute("subdocument_id", subdocumentId);
    		request.setAttribute("citeria_query_list", colPre.getCriteriaQuery());
    		request.setAttribute("row_count", colPre.getCriteriaRowCnt()>0?colPre.getCriteriaRowCnt():1);
    		request.setAttribute("group_cnt", colPre.getGroupCnt());
    		
    		request.setAttribute("invoke_servlet", request.getParameter("invoke_servlet"));
    		request.setAttribute("invoke_method", request.getParameter("invoke_method"));
			
    		if(request.getSession().getAttribute("rights")!=null ){
    			request.getSession().removeAttribute("rights");
			}
    		if(request.getSession().getAttribute("rights")==null){
				rights =objRight.getUserRights(subdocumentId, userInfo);
				request.getSession().setAttribute("rights",rights);
    		}
    		
			
			cpm=null;
			visibleColumns=null;
			columns=null;
			column_pref=null;
			colPre=null;
			criteriaRow=null;
			rights=null;
			userInfo=null;
			bundle=null;
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_CUSTOMIZE_REPORT,request,response);
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}
	public void doSaveCustomizeReport(HttpServletRequest request,HttpServletResponse response){
		try {
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			int type=Validator.convertToInteger(request.getParameter("type"));
			
			int subdocumentId=Validator.convertToInteger(request.getParameter("subdocument_id"));
			ColumnPreference cp=new ColumnPreference();
			if(type==1){
				String reportName=request.getParameter("new_report_name");
				String reportDesc=request.getParameter("new_report_description");
				cp.setReportName(reportName);
				cp.setReportDesc(reportDesc);
				reportName=null;
				reportDesc=null;
			}
			
//			String totalColumns=request.getParameter("total_columns");
			String visibileColumns=request.getParameter("selected_columns");
			cp.setVisColumns(visibileColumns);
			visibileColumns=null;
			
			int groupByCol1 =Validator.convertToInteger(request.getParameter("group_by_column1"));
			int groupByCol2 =Validator.convertToInteger(request.getParameter("group_by_column2"));
			int groupByCol3 =Validator.convertToInteger(request.getParameter("group_by_column3"));
			
			String groupByColOrder1 =request.getParameter("group_by_column_order1");
			String groupByColOrder2 =request.getParameter("group_by_column_order2");
			String groupByColOrder3 =request.getParameter("group_by_column_order3");
			
			Map<Integer,String> groupByOrder=new LinkedHashMap<Integer,String>();
			if(groupByCol1!=-1){
				groupByOrder.put(groupByCol1, groupByColOrder1);
			}
			if(groupByCol2!=-1){
				groupByOrder.put(groupByCol2, groupByColOrder2);
			}
			if(groupByCol3!=-1){
				groupByOrder.put(groupByCol3, groupByColOrder3);
			}
			cp.setMapGroupByOrder(groupByOrder);
			groupByOrder=null;
			groupByColOrder1=null;
			groupByColOrder2=null;
			groupByColOrder3=null;
					
//			ColumnPreference colPre=cpm.getDataForReportCustomize(subdocumentId, userInfo);
			
			Map<String,String> mapColumnToTotal=new LinkedHashMap<String,String>();
//			List<Integer> columnToTotalColId=new ArrayList<Integer>();
			
			
//			List<ColumnPreference> columnToTotalList=colPre.getColumnToTotalList();
			ServletContext context = request.getServletContext();
			List<Integer> columnToTotalColId=(List<Integer>)context.getAttribute("column_to_total_col_id");
			for(int colId:columnToTotalColId){
				mapColumnToTotal.put("chk_"+colId+"_1", request.getParameter("chk_"+colId+"_1"));
				mapColumnToTotal.put("chk_"+colId+"_2", request.getParameter("chk_"+colId+"_2"));
				mapColumnToTotal.put("chk_"+colId+"_3", request.getParameter("chk_"+colId+"_3"));
				mapColumnToTotal.put("chk_"+colId+"_4", request.getParameter("chk_"+colId+"_4"));
//				columnToTotalColId.add(Integer.parseInt(ct.getColumnId()));
			}
			cp.setColumnToTotalColId(columnToTotalColId);
			cp.setMapColumnToTotal(mapColumnToTotal);
			columnToTotalColId=null;
			mapColumnToTotal=null;
			
			Map<String,String> mapCriteriaQuery=new LinkedHashMap<String,String>();
			
			String rowIds=request.getParameter("row_ids");
			String[] arRowIds=rowIds.split(",");
			
			List<String> rowIdsList=Arrays.asList(arRowIds);
			
			for(int i=0;i<arRowIds.length;i++){
				if(!arRowIds[i].isEmpty()){
					mapCriteriaQuery.put("db_query_"+arRowIds[i], request.getParameter("db_query_"+arRowIds[i]).replace("<c:out>", "").replace("</c:out>", "").replace("squote", "'"));
					mapCriteriaQuery.put("db_having_query_"+arRowIds[i], request.getParameter("db_having_query_"+arRowIds[i]).replace("<c:out>", "").replace("</c:out>", "").replace("squote", "'"));
					mapCriteriaQuery.put("query_"+arRowIds[i], request.getParameter("query_"+arRowIds[i]));
				}
			}
			cp.setMapCriteriaQuery(mapCriteriaQuery);
			cp.setCriteriaIds(rowIdsList);
			
			mapCriteriaQuery=null;
			rowIdsList=null;
			
//			String finalDbQuery=request.getParameter("db_query_").replace("<c:out>", "").replace("</c:out>", "").replace("squote", "'");
//			String finalDbHavingQuery=request.getParameter("db_having_query_").replace("<c:out>", "").replace("</c:out>", "").replace("squote", "'");
//			String finalQuery=request.getParameter("query_");
//			
//			cp.setFinalDbQuery(finalDbQuery);
//			cp.setFinalDbHavingQuery(finalDbHavingQuery);
//			cp.setFinalQuery(finalQuery);
			
			
			cp= cpm.updateColumnPreferences(cp,subdocumentId,userInfo,type);
			
			
			if(type==1){
//				MenuManager objMenuManager = new MenuManager();
//				List<MenuItem> reportmainmenus = objMenuManager.getReportMainMenuItems(userInfo);
//				request.getSession().setAttribute("report_main_menu", reportmainmenus);
//				MenuHolder objReport= objMenuManager.getReportMenus(userInfo);
//			    request.getSession().setAttribute("report_items", objReport);
				setReportMenus(request);
			}
			
			cp=null;
			
			doCloseCustomizeReport(request,response);
			
			userInfo=null;
//			String invokeServlet=request.getParameter("invoke_servlet");
//			String invokeMethod=request.getParameter("invoke_method");
//			if(invokeServlet!=null && !invokeServlet.isEmpty() && invokeMethod!=null && !invokeMethod.isEmpty()){
//				Class[] paramString = new Class[2];	
//				paramString[0] = HttpServletRequest.class;
//				paramString[1] = HttpServletResponse.class;
//				Class objClass= Class.forName(invokeServlet);
//				Object obj=objClass.newInstance();
//				Method method = objClass.getDeclaredMethod(invokeMethod,paramString);
//				method.invoke(obj,request,response);
//			}
//			else{
//				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_ALL_REPORTS,request,response);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doCloseCustomizeReport(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{
			String invokeServlet=request.getParameter("invoke_servlet");
			String invokeMethod=request.getParameter("invoke_method");
			if(invokeServlet!=null && !invokeServlet.isEmpty() && invokeMethod!=null && !invokeMethod.isEmpty()){
				Class[] paramString = new Class[2];	
				paramString[0] = HttpServletRequest.class;
				paramString[1] = HttpServletResponse.class;
				Class objClass= Class.forName(invokeServlet);
				Object obj=objClass.newInstance();
				Method method = objClass.getDeclaredMethod(invokeMethod,paramString);
				method.invoke(obj,request,response);
				
				objClass=null;
				paramString=null;
				invokeServlet=null;
				invokeMethod=null;
				obj=null;
				method=null;
			}
			else{
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_ALL_REPORTS,request,response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void setReportMenus(HttpServletRequest request){
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
//		MenuManager objMenuManager = new MenuManager();
		
//		List<MenuItem> mainmenus = objMenuManager.getMainMenuItems(userInfo);
//		MenuHolder objHolder = objMenuManager.getMenus(userInfo);
//		request.getSession().setAttribute("main_menu", mainmenus);
//		request.getSession().setAttribute("menu_items", objHolder);

//		List<MenuItem> reportmainmenus = objMenuManager.getReportMainMenuItems(userInfo);
//		request.getSession().setAttribute("report_main_menu", reportmainmenus);
//		MenuHolder objReport= objMenuManager.getReportMenus(userInfo);
//	    request.getSession().setAttribute("report_items", objReport);
//	    
//	    Map<Integer,String> subdocuments= objMenuManager.getSubdocuments(userInfo);
//	    request.getSession().setAttribute("subdocuments", subdocuments);
//	    
//	    userInfo=null;
//	    objMenuManager=null;
//	    reportmainmenus=null;
//	    subdocuments=null;
//	    objReport=null;
//	    objHolder=null;
		
		
		new LoginServlet().doSetMainMenu(request,userInfo);
		 
		new LoginServlet().doSetReportMenu(request,userInfo);
	}
	public void doSaveCustomizeReportName(HttpServletRequest request,HttpServletResponse response){
		try {
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			int subdocumentId=Validator.convertToInteger(request.getParameter("subdocument_id"));
			ColumnPreference cp=new ColumnPreference();
			String reportName=request.getParameter("new_report_name");
			String reportDesc=request.getParameter("new_report_description");
			cp.setReportName(reportName);
			cp.setReportDesc(reportDesc);
			ColumnPreference colPre=cpm.saveReportName(cp,subdocumentId,userInfo);
			if(!colPre.isSaved()){
				request.setAttribute("error_message", "Report name save failed.");
			}
			else{
				request.setAttribute("success_message", "Report name saved successfully.");
			}
			
			setReportMenus(request);
			cp=null;
			colPre=null;
			userInfo=null;
			reportName=null;
			reportDesc=null;
			
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_ALL_REPORTS,request,response);
//			doDisplayCustomizeReport(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doDeleteCustomizeReport(HttpServletRequest request,HttpServletResponse response){
		try {
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			int subdocumentId=Validator.convertToInteger(request.getParameter("subdocument_id"));
			ColumnPreference colPre=cpm.deleteReport(subdocumentId,userInfo);
			
			if(!colPre.isSaved()){
				request.setAttribute("error_message", "Report delete failed.");
			}
			else{
				request.setAttribute("success_message", "Report deleted successfully.");
			}
			
			setReportMenus(request);
			colPre=null;
			userInfo=null;
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_ALL_REPORTS,request,response);
//			doDisplayCustomizeReport(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void formPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		int subdocumentId=Validator.convertToInteger(request.getParameter("subdocument_id"));
		ColumnPreferenceManager cpm=new ColumnPreferenceManager();
		ColumnPreference colPre=cpm.getDataForReportCustomize(subdocumentId, userInfo);
		int rowNo=Validator.convertToInteger(request.getParameter("criteria_row_no"));
		String criteriaRow=formCriteriaCode(bundle,colPre.getColumnList(),colPre.getOperatorList(),rowNo,subdocumentId);
		response.setContentType("text/html");
		
		bundle=null;
		cpm=null;
		colPre=null;
		response.getWriter().write(criteriaRow);
		criteriaRow=null;
		userInfo=null;
	}
	
	public String formCriteriaCode(ResourceBundle bundle,List<ColumnPreference> columnList,List<ColumnPreference> operatorList,int rowNo,int subdocumentId){
		
		StringBuffer buffer=new StringBuffer();
		buffer.append("<div class='row' id=\"row"+rowNo+"\">");
				buffer.append("<div class='col-sm-3'>");
				buffer.append("<div class='form-group'>");
				buffer.append("<label for=\"tag\">Column</label> ");
				buffer.append("<div class=\"required-field-block\">");
				buffer.append("<select class=\"form-control\" id=\"column_name"+rowNo+"\" name=\"column_name"+rowNo+"\">");
				buffer.append("<option value=\"-1\">Select</option>");
				for(ColumnPreference cp:columnList){
//					 if(subdocumentId!=93 && subdocumentId!=20){
					buffer.append("<option value=\"<c:out>"+cp.getColumnName()+"</c:out>\" class=\"datatype"+cp.getDataType()+"\">"+cp.getColumnDisplayName()+"</option>");
//					 }
//					 else{
//						 buffer.append("<option value=\"<c:out>"+cp.getColumnName()+"</c:out>\" class=\"datatype"+cp.getDataType()+"\">"+bundle.getString(cp.getColumnDisplayName())+"</option>");
//					 }
					cp=null;
				}
				buffer.append("</select>");
				buffer.append("<div class=\"required-icon\">");
				buffer.append("<div class=\"text\">*</div>");
				buffer.append("</div>");
				buffer.append("</div>");
				buffer.append("</div>");
				buffer.append("</div>");

				buffer.append("<div class='col-sm-3'>");
				buffer.append("<div class='form-group'>");
				buffer.append("<label for=\"tag\">Operator</label>");
				buffer.append("<div class=\"required-field-block\">");
				buffer.append("<select class=\"form-control\" id=\"operator"+rowNo+"\" name=\"operator"+rowNo+"\"> ");
				buffer.append("<option value=\"-1\" class=\"datatype1 datatype2 datatype3 datatype5 datatype7\">Select</option>");
				for(ColumnPreference cp:operatorList){
					buffer.append("<option value=\"<c:out>"+cp.getOperatorDb()+"</c:out>\" class=\"datatype"+cp.getDataType()+"\">"+cp.getOperator()+"</option>");
					cp=null;
				}
				buffer.append("</select>");
				buffer.append("<div class=\"required-icon\">");
				buffer.append("<div class=\"text\">*</div>");
				buffer.append("</div>");
				buffer.append("</div>");
				buffer.append("</div>");
				buffer.append("</div>");

				buffer.append("<div class='col-sm-3'>");
				buffer.append("<div class='form-group'>");
				buffer.append("<label for=\"tag\">Value</label>");
				buffer.append("<div class=\"required-field-block\">");
				buffer.append("<input class=\"form-control\" type=\"text\" id=\"criteria_value"+rowNo+"\" name=\"criteria_value"+rowNo+"\" />");
				buffer.append("<div class=\"required-icon\">");
				buffer.append("<div class=\"text\">*</div>");
				buffer.append("</div>");
				buffer.append("</div>");
				buffer.append("</div>");
				buffer.append("</div>");
				
				buffer.append("<div class='col-sm-3'>");
				buffer.append("<div class='form-group'>");
				buffer.append("<label for=\"tag\">Column</label> ");
				buffer.append("<select class=\"form-control\" id=\"column_name_val"+rowNo+"\" name=\"column_name_val"+rowNo+"\">");
				buffer.append("<option value=\"-1\">Select</option>");
				for(ColumnPreference cp:columnList){
//					if(subdocumentId!=93 && subdocumentId!=20){
					buffer.append("<option value=\"<c:out>"+cp.getColumnName()+"</c:out>\" class=\"datatype"+cp.getDataType()+"\">"+cp.getColumnDisplayName()+"</option>");
//					}
//					else{
//						buffer.append("<option value=\"<c:out>"+cp.getColumnName()+"</c:out>\" class=\"datatype"+cp.getDataType()+"\">"+bundle.getString(cp.getColumnDisplayName())+"</option>");
//					}
					cp=null;
				}
				buffer.append("</select>");
				buffer.append("</div>");
				buffer.append("</div>");

				
//				buffer.append("<script language=\"javascript\">$(document).ready(function () {");    
//				buffer.append("var allOptions = $('#operator"+rowNo+" option')");
//				buffer.append("$('#column_name"+rowNo+"').change(function () {");
//				buffer.append("$('#operator"+rowNo+" option').remove()");
//				buffer.append("var classN = $('#column_name"+rowNo+" option:selected').prop('class');");
//				buffer.append("var opts = allOptions.filter('.' + classN);");
//				buffer.append("$.each(opts, function (i, j) {");
//				buffer.append("$(j).appendTo('#operator"+rowNo+"');");
//				buffer.append("});");
//				buffer.append("});");
//				buffer.append("});</script>");
//				buffer.append("<script language=\"javascript\">");
//				buffer.append("filterDropdown("+rowNo+");");
//				buffer.append("jQuery(document).ready(function () {");
//				buffer.append("var allOptions = jQuery('#operator"+rowNo+" option')");
//				buffer.append("jQuery('#column_name"+rowNo+"').change(function () {");
//				buffer.append("jQuery('#operator"+rowNo+" option').remove();");
//				buffer.append("var classN = jQuery('#column_name"+rowNo+" option:selected').prop('class');");
//				buffer.append("var opts = allOptions.filter('.' + classN);");
//				buffer.append("jQuery.each(opts, function (i, j) {");
//				buffer.append("jQuery(j).appendTo('#operator"+rowNo+"');");
//				buffer.append("});");
//				buffer.append("});});");
//				buffer.append("</script>");
				buffer.append("</div>");
				buffer.append("<div class='row pull-right'>");
				buffer.append("<div class='col-sm-12'>");
				buffer.append("<div class='form-group' id='btn-col"+rowNo+"'>");
				buffer.append("<a class=\"btn btn-primary\" href=\"javascript:formCriteria('"+rowNo+"','1')\" ><span class=\"btn-label\"><i class=\"glyphicon glyphicon glyphicon-filter\"></i></span>&nbsp;and</a>&nbsp;"); 
				buffer.append("<a class=\"btn btn-primary\" href=\"javascript:formCriteria('"+rowNo+"','2')\" ><span class=\"btn-label\"><i class=\"glyphicon glyphicon glyphicon-filter\"></i></span>&nbsp;or</a>");
				buffer.append("</div>");
				buffer.append("</div>");
				buffer.append("</div>");
				return buffer.toString();
	}
	private ColumnPreferenceServlet objColPreServlet=new ColumnPreferenceServlet();
	private ColumnPreferenceManager cpm=new ColumnPreferenceManager();
	private UserRights rights = null;
	private UserRightsManager objRight = new UserRightsManager();
}
