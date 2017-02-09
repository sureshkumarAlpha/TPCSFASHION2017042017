<%@page import="com.alpha.tpcsfashion.beans.DynamicEventType"%>
<%@page import="com.alpha.tpcsfashion.beans.DynamicField"%>
<%@page import="com.alpha.tpcsfashion.beans.DynamicJavaScript"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<fmt:setLocale value="${sessionScope.login_user_locale}" />
<fmt:bundle basename="${sessionScope.property_filename}">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>TPCS Product Life Cycle Management - Edit the Form Body Script</title>
	<jsp:include page="../common/Header.jsp" />	
	</head>
	<body>
<form name="fieldScript" id="fieldScript" action="${pageContext.request.contextPath}/RequestHandlerServlet" method="post">
	
	<div class="row">
                <div class="col-md-11 col-sm-8 col-xs-8">
                    <h3 class="add-page-header">Dynamic Field Script</h3>
                </div>
		</div>
		
	<table cellpadding="0" cellspacing="0" border="0" width="400px" align="center">
		<tr>
			<td align="center" valign="top">

			<table cellpadding="0" cellspacing="0" border="0" width="400px" align="center"
				style="margin: 0 auto">
				<tr>
					<td align="center" valign="top">
					<table cellpadding="0" cellspacing="0" border="0" width="400px"
						class="javascript-edit">
						<tr>
							<td align="left" valign="top">
							<% if(request.getParameter("isFormScript") != null && request.getParameter("isFormScript").equalsIgnoreCase("true")){ %>
								<select id="formEventTypeId" name="formEventTypeId" class="form-control"  style="width:185px;" onchange="updateCompEventType();">	
								<% if(session.getAttribute("formEventTypeId") == null || (Integer)session.getAttribute("formEventTypeId") == -1) {%>
								<option value="select" >Select</option><% } %>
									<% 			 
										List<DynamicEventType> scriptEvent = (List<DynamicEventType>)session.getAttribute("formEventsList");
			                		    for (int i = 0; i < scriptEvent.size(); i++) {
			                		    	DynamicEventType dynaScript = scriptEvent.get(i);
			                        		 if (session.getAttribute("formEventTypeId") != null && (Integer) session.getAttribute("formEventTypeId") == dynaScript.getEventId()) {		                                   	 
			                    	%>		                    
				                    <option value="<%= dynaScript.getEventId()%>" selected><%= dynaScript.getEventName()%></option>
				                    <%} else {%>
				                    <option value="<%= dynaScript.getEventId()%>"><%= dynaScript.getEventName()%></option>
				                    <% }
				                     }%>							
								</select>								
								<%} else { %>
								<select id="compEventTypeId" name="compEventTypeId" class="form-control"  style="width:185px;" onchange="updateCompEventType();">	
								<% if(session.getAttribute("compEventTypeId") == null || (Integer)session.getAttribute("compEventTypeId") == -1) {%>
								<option value="select" >Select</option><% } %>
									<% 			 
										List<DynamicEventType> scriptEvent = (List<DynamicEventType>)session.getAttribute("eventsList");
			                		    for (int i = 0; i < scriptEvent.size(); i++) {
			                		    	DynamicEventType dynaScript = scriptEvent.get(i);
			                        		 if (session.getAttribute("compEventTypeId") != null && (Integer) session.getAttribute("compEventTypeId") == dynaScript.getEventId()) {		                                   	 
			                    	%>		                    
				                    <option value="<%= dynaScript.getEventId()%>" selected><%= dynaScript.getEventName()%></option>
				                    <%} else {%>
				                    <option value="<%= dynaScript.getEventId()%>"><%= dynaScript.getEventName()%></option>
				                    <% }
				                     }%>							
								</select>
								<%} %>
								
								<span style="color: red; font-weight: bold; margin-left: 100px;">
								<%if(request.getAttribute("scriptMsg") !=null) {%>
									<%=request.getAttribute("scriptMsg") %> 
								<%} %></span>
							</td>
						</tr>
						
						<tr>						
							<td align="left" valign="top"><br/>		
							<% 
							List<DynamicJavaScript> dynamicJavaScriptList = null;
							String sessionParamName = "";
							if(request.getParameter("isFormScript") != null && request.getParameter("isFormScript").equalsIgnoreCase("true")){
								dynamicJavaScriptList =(List<DynamicJavaScript>) request.getSession().getAttribute("formJavaScriptList");
								sessionParamName = "formEventTypeId";
								
							}else{
							 	dynamicJavaScriptList =(List<DynamicJavaScript>) request.getSession().getAttribute("javaScriptList");
							 	sessionParamName = "compEventTypeId";
							}
							boolean showDefault= false;
							if(dynamicJavaScriptList != null && dynamicJavaScriptList.size()>0){
								for(DynamicJavaScript dyna :dynamicJavaScriptList){		
									if(session.getAttribute(sessionParamName) != null &&  (Integer)session.getAttribute(sessionParamName) == dyna.getJavaScriptEventId()){
										showDefault = true;
							%>
											
								<textarea id="scriptArea" name="scriptArea" cols="60" rows="10" style="text-align: left; vertical-align: top;"><%=dyna.getJavaScript() %></textarea>	
							<%} } }%>	
							<% if(!showDefault) {%>
								<textarea id="scriptArea" name="scriptArea" cols="60" rows="10" style="text-align: left; vertical-align: top;"></textarea>
							<%} %>
							</td>
							
							
						</tr>
						<tr>
						<td>&nbsp; </td>
						</tr>
						<tr>

							<td align="right" valign="top">
							<input type="button" id="saveScriptFieldBtn"
								value="Submit" onclick="saveFieldLevelScript();"
								class="btn btn-success"/> 
							<input type="button" value="Close" id="cancelButton" class="btn btn-danger" onclick="window.close();"
								 /></td>
							<td>	
						</tr>

					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>

	</table>

 <input type="hidden" id="fieldId" name="fieldId" value="<%= request.getParameter("fieldId") %>" /> 
 <input type="hidden" id="fieldName" name="fieldName" value="<%= request.getParameter("fieldName") %>" /> 
 <input type="hidden" id="isFormScript" name="isFormScript" value="<%= request.getParameter("isFormScript") %>" /> 
 <input type="hidden" id="rowSelected" name="rowSelected" value="${rowSelected}" />
 <input type="hidden" name="servlet_name" id="servlet_name"/>
 <input type="hidden" name="callbackmethod_name" id="callbackmethod_name"/>
</form>
	</body>
</fmt:bundle>
</html>