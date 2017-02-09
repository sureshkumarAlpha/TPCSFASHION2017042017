<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<fmt:setLocale value="${sessionScope.login_user_locale}" />
<fmt:bundle basename="${sessionScope.property_filename}">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>TPCS Product Life Cycle Management - Edit the Form Body Script</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Master.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tpcs-calendar.css"/>
	<script language="javascript" src="${pageContext.request.contextPath}/js/common.js"></script>	
	<script language="javascript" src="${pageContext.request.contextPath}/js/order.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/js/prototype.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/js/tpcs-calendar.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/js/tpcs-calendar-en.js"></script>	
	<script language="javascript" src="${pageContext.request.contextPath}/js/tpcs-calendar-setup.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/js/calendar-setup.js"></script>	
	</head>
	<body>
<form name="formScript" id="formScript" action="${pageContext.request.contextPath}/RequestHandlerServlet" method="post">
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td align="center" valign="top">

			<table cellpadding="0" cellspacing="0" border="0" width="600px"
				style="margin: 0 auto">
				<tr>
					<td align="center" valign="top">
					<table cellpadding="0" cellspacing="0" border="0" width="350px"
						class="javascript-edit">
						<tr>
							<td align="left" valign="top">
							<select id="eventType" name="eventType">
								<option value="onload">OnLoad</option>						

							</select>							
							</td>
						</tr>
						<tr><td><span style="color: red; font-weight: bold;">
								<%if(request.getAttribute("scriptMsg") !=null) {%>
									<%=request.getAttribute("scriptMsg") %> 
								<%} %>
						</span></td></tr>
						<tr>
							<td align="left" valign="top"><br/>
								<TEXTAREA id="scriptText" name="scriptText" cols="60" rows="20" style="text-align: left; vertical-align: top;"><%if(session.getAttribute("scriptText") != null) { %><%=session.getAttribute("scriptText") %><%} %></TEXTAREA>		
							</td>
						</tr>
						<tr>

							<td align="right" valign="top">
							<input type="button" id="saveScriptFormBtn"
								value="Submit" onmouseout="goDim(this.id)" onclick="saveBodyLoadScript()"
								onmouseover="goLite(this.id)" class="groovybutton"
								style="padding: 3px" /> 
							<input type="button" value="Close" id="closeFormScriptButton"
								onmouseout="goDim(this.id)" onmouseover="goLite(this.id)"
								class="groovybutton" onclick="javascript:window.close()"
								style="padding: 3px; margin-left: 10px" /></td>
						</tr>

					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>

	</table>

 <input type="hidden" id="fieldId" name="fieldId" value="${fieldId}" /> 
 <input type="hidden" name="servlet_name" id="servlet_name"/>
 <input type="hidden" name="callbackmethod_name" id="callbackmethod_name"/>
</form>
	</body>
</fmt:bundle>
</html>