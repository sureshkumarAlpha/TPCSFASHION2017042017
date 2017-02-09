<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<fmt:setLocale value="${sessionScope.login_user_locale}" />
<fmt:bundle basename="${sessionScope.property_filename}">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="-1">
	<title>TPCS Product Life Cycle Management - Edit the Form Body Script</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Master.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tpcs-calendar.css"/>
	<script language="javascript" src="${pageContext.request.contextPath}/js/common/common.js"></script>	
	<script language="javascript" src="${pageContext.request.contextPath}/js/order.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/js/prototype.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/js/tpcs-calendar.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/js/tpcs-calendar-en.js"></script>	
	<script language="javascript" src="${pageContext.request.contextPath}/js/tpcs-calendar-setup.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/js/calendar-setup.js"></script>	
	</head>
	<body onkeypress="enterKeyPress(event,'saveDefaultValuesBtn');">
<form name="defaultValueForm" id="defaultValueForm" action="${pageContext.request.contextPath}/RequestHandlerServlet" method="post">
	<table cellpadding="0" cellspacing="0" border="0" width="100%" style="margin-top: 50px;">
		<tr>
			<td align="center" valign="top">

			<table cellpadding="3" cellspacing="0" border="0" width="500px"
				style="margin: 0 auto">
				
				<tr>				
					<td>Value
					<%
					List<String> defautValues = (List<String>)session.getAttribute("defaultValueList");
					String arValue[]=new String[2];
					if( request.getAttribute("selectedIndex") != null){
						int valueIndex = (Integer)request.getAttribute("selectedIndex");
						String value = defautValues.get(valueIndex);
						arValue=value.split("&1&");
					%>
						<input type="text" name="attrDefaultValue" class="dValuecontrolInputText" value="<%= arValue[0]!=null && !arValue[0].equals("null") ?arValue[0]:""%>" maxlength="<%=request.getParameter("length") %>" />
					<%}else{ %>
						<input type="text" name="attrDefaultValue" class="dValuecontrolInputText" value="" maxlength="<%=request.getParameter("length") %>" />
					<%} %>	
					</td>
					<td>
					Display Order<input type="text" name="display_order" id="display_order" value="<%= arValue[1]!=null && !arValue[1].equals("null") ?arValue[0]:""%>" />
					</td>
					</tr>
					
					<tr>
					<td colspan="2" align="right">
						<input type="button" id="saveDefaultValuesBtn" value="Save" onclick="saveDefaultValues('<%=request.getAttribute("selectedIndex") %>')"  class="btns" style="margin-left: 10px;" /> 
						<input type="button" value="Close" id="closeDefaultValues" class="btns" onclick="javascript:window.close()" style="margin-left: 10px" />
					</td>
				</tr>				
			</table>
			</td>
		</tr>		
	</table>
	
	<div id="valuesTableDiv" style="margin-top: 15px;">
	
	<% if(session.getAttribute("defaultValueList") != null && defautValues.size()>0){  
							%>						
						<table cellpadding="3" cellspacing="0" border="0" width="500px" style="margin: 0 auto;border: 1px solid #1865AD;" >							
							<tr class="pageheader">
								<td>Value</td>
								<td>Display Order</td>
								<td>  </td>
							</tr>
							
							<% 
							
							for(int i=0; i<defautValues.size(); i++){ 
								
								String values = defautValues.get(i);
								String arValue2[]=values.split("&1&");
							%>
							<tr>
								<td class="tableDataField"><%= arValue2[0] %>   </td>
								<td class="tableDataField"><%= arValue2[1] %>   </td>
								<td class="tableDataField" align="right">
										<input id="edit<%=i%>"  type="button" value="Edit" onclick="editDefaultValue('<%=i%>')" class="btns"
					                      />
					                     <span style="margin-left: 2px;">&nbsp;</span>					                    
					                     <input id="delete<%=i%>"  type="button" value="Delete" onclick="deleteDefaultValue('<%=i%>')" class="btns"
					                      />
					                     
								</td>
							</tr>
							<% } %>
						</table>
		<% } %>	
		</div>	

 <input type="hidden" id="fieldId" name="fieldId" value="<%= request.getParameter("fieldId") %>" /> 
 <input type="hidden" id="fieldName" name="fieldName" value="<%= request.getParameter("fieldName") %>" /> 
 <input type="hidden" id="compType" name="compType" value="<%= request.getParameter("compType") %>" />
 <input type="hidden" id="selectedIndex" name="selectedIndex" /> 
 <input type="hidden" name="servlet_name" id="servlet_name"/>
 <input type="hidden" name="callbackmethod_name" id="callbackmethod_name"/>
 <% if(request.getParameter("length") != null){ %>
 <input type="hidden" id="length" name="length" value="<%=request.getParameter("length") %>" />
 <%} else{ %>
 <input type="hidden" id="length" name="length" value="<%=request.getAttribute("length") %>" />
 <%} %>
</form>
	</body>
</fmt:bundle>
</html>