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
	<jsp:include page="../common/Header.jsp" />
	</head>
	
	<body onkeypress="enterKeyPress(event,'saveDefaultValuesBtn');">
<form name="defaultValueForm" id="defaultValueForm" action="${pageContext.request.contextPath}/RequestHandlerServlet" method="post">
	<div class="row">
                <div class="col-md-11 col-sm-8 col-xs-8">
                    <h3 class="add-page-header">Default Value</h3>
                </div>
		</div>
	<div class='row'>
            <div class='col-sm-4'>    
                <div class='form-group'>
                    <label for="attrDefaultValue">Value</label>	
                    <%
					List<String> defautValues = (List<String>)session.getAttribute("defaultValueList");
					String arValue[]=new String[2];
					if( request.getAttribute("selectedIndex") != null){
						int valueIndex = (Integer)request.getAttribute("selectedIndex");
						String value = defautValues.get(valueIndex);
						arValue=value.split("&1&");
					%>
						<input type="text" name="attrDefaultValue" class="form-control" size="30" placeholder="Enter Value" value="<%= arValue[0]!=null && !arValue[0].equals("null") ?arValue[0]:""%>" maxlength="<%=request.getParameter("length") %>" />
					<%}else{ %>
						<input type="text" name="attrDefaultValue" class="form-control" size="30" placeholder="Enter Value" value="" maxlength="<%=request.getParameter("length") %>" />
					<%} %>
                </div>
            </div>
            <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="company_name">Display Order</label>  
                    <input type="text" name="display_order" class="form-control"  placeholder="Enter Display Order" size="30" id="display_order" value="<%= arValue[1]!=null && !arValue[1].equals("null") ?arValue[1]:""%>" />
                </div>
            </div>
      </div>
	
	<div class='row pull-right'>        	         	 
            <div class='col-sm-12 col-xs-12 centered' style="margin-bottom: 12px;">     
            <input type="button" id="saveDefaultValuesBtn" value="Save" onclick="saveDefaultValues('<%=request.getAttribute("selectedIndex") %>')"   class="btn btn-success" /> 
			<input type="button" value="Close" id="closeDefaultValues"  onclick="javascript:window.close()" class="btn btn-danger"   />
            </div>            
    </div> 
	<div id="valuesTableDiv" style="margin-top: 15px;" class="table-responsive" >
	
	<%  if(session.getAttribute("defaultValueList") != null && defautValues.size()>0){  
		System.out.println("size---------->"+defautValues.size());
							%>						
						<table class="table table-bordered table-condensed" >							
							 <tr class="header">
								<td>Value</td>
								<td>Display Order</td>
								<td>Action </td>
							</tr>
							
							<% 
							
							for(int i=0; i<defautValues.size(); i++){ 
								
								System.out.println("defautValues.get(i)---------->"+defautValues.get(i));
								
								String values = defautValues.get(i);
								String arValue2[]=values.split("&1&");
							%>
							<tr>
								<td class="tableDataField"><%= arValue2[0] %>   </td>
								<td class="tableDataField"><%= arValue2[1] %>   </td>
								<%-- <td class="tableDataField" align="right">
										<input id="edit<%=i%>"  type="button" value="Edit" onclick="editDefaultValue('<%=i%>')" class="btns"
					                      />
					                     <span style="margin-left: 2px;">&nbsp;</span>					                    
					                     <input id="delete<%=i%>"  type="button" value="Delete" onclick="deleteDefaultValue('<%=i%>')" class="btns"
					                      />
					                     
								</td> --%>
								<td align="center" valign="middle">
					
					<a href="#"  id="edit<%=i%>" onclick="editDefaultValue('<%=i%>')"  title="Edit"><span class="glyphicon glyphicon-edit icon-edit"></span></a>
                     
                     <a href="#" id="delete<%=i%>"  onclick="deleteDefaultValue('<%=i%>')" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
                     
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