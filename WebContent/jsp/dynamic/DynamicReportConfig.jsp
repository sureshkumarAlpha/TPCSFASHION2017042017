<%@page import="com.alpha.tpcsfashion.beans.DynamicRefTable"%>
<%@page import="com.alpha.tpcsfashion.beans.DynamicField"%>
<%@page import="com.alpha.tpcsfashion.beans.DynamicFieldType"%>
<%@page import="com.alpha.tpcsfashion.beans.DynamicDataType"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.alpha.tpcsfashion.beans.MenuItem"%>
<%@ page pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<fmt:setLocale value="${sessionScope.login_user_locale}"/>
<fmt:bundle basename="${sessionScope.property_filename}">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>JENIX :: DYNAMIC DEVELOPMENT ORDER</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/inner.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<!--[if lt IE 7.]>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iepngfix_tilebg.js"></script>
<link href="${pageContext.request.contextPath}/css/inner-ie6.css" rel="stylesheet" type="text/css">
<![endif]-->

<script>
anylinkcssmenu.init("anchorclass");
</script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tpcs-calendar.css"/>
	<script language="javascript" src="${pageContext.request.contextPath}/js/common.js"></script>	
	<script language="javascript" src="${pageContext.request.contextPath}/js/order.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/js/prototype.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/js/tpcs-calendar.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/js/tpcs-calendar-en.js"></script>	
	<script language="javascript" src="${pageContext.request.contextPath}/js/tpcs-calendar-setup.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/js/calendar-setup.js"></script>	
	<script language="javascript">
	   var localizedStrings = {
			    VALID_LABEL: "<fmt:message key="Field.Valid.Label"/>",
			    VALID_ATTR_NAME: "<fmt:message key="Field.Valid.Name"/>",	
			    VALID_ATTR_DB_NAME: "<fmt:message key="Field.Valid.DBName"/>",	
			    VALID_ATTR_ALIGNMENT: "<fmt:message key="Field.Valid.fieldAlignment"/>"
		};	  
				
	</script>
</head>
<jsp:include page="../common/ValidateUser.jsp"/>
<body bgcolor="#FFFFFF">
<form name="mastercustomer" id="mastercolor" action="${pageContext.request.contextPath}/RequestHandlerServlet" method="post">
<% try { %>
<div id="container">
<jsp:include page="../common/top-menu.jsp" >
		<jsp:param value="13" name="screen_type"/>
</jsp:include>
</div>

<jsp:useBean id="dynamicField" class="com.alpha.tpcsfashion.beans.DynamicField" type="com.alpha.tpcsfashion.beans.DynamicField" scope="request"/>
<% 
				  List<MenuItem> items = (List<MenuItem>)session.getAttribute("reports_config_menus");
%>	
<div class="heading">
<div id="container">
<h1 class="head">Reports Configuration : 
                    <%for(MenuItem item : items) {
                    	if(item.getMenuId() == (Integer)session.getAttribute("seletedScreenIdForReports")){
                    	%> <%=item.getMenuName() %>
                    <%}  } %></h1>
</div>
</div>

    
    <!-- Dynamic Order Config coding starts from here  -->
<div id="main-container" >
<div id="contentcolumn" class="column" >
<b class="lft-crv"></b>

<div id="crvbg" style="width:1000px;">
<b class="slc"></b>
<b class="sblc"></b>     
                
           <div id="mainSection" style="margin-top: 10px;" align="left">
           			
                <div style="margin-top: 10px;margin-bottom: 10px; " >  
                <table width="100%" cellpadding="0" cellspacing="0">
                	<tr><td style="display: none;">
		    			<span style="margin-left: 0px;"><b>No. of Sections :</b> </span>
		                <select id="numOfColumns" name="numOfColumns" class="controlInputText" onchange="updateNumberOfColumnsForScreens()">
		                    <% 			                    
		                    for (int nCol = 1; nCol < 4; nCol++) {
		                         if (session.getAttribute("numOfColumns") != null && (Integer) session.getAttribute("numOfColumns") == nCol) {		                                   	 
		                    %>		                    
		                    <option value="<%= nCol%>" selected><%= nCol%></option>
		                    <%} else {%>
		                    <option value="<%= nCol%>" ><%= nCol%></option>
		                    <% }
		                     }%>
		                </select>
		               
		                <span style="margin-left: 10px;"><b>Alignment :</b> </span>
		                <select id="attributeAlignment" name="attributeAlignment" class="controlInputText" onchange="updateAlignmentForScreens()">
		                    <% if (session.getAttribute("numOfColumns") != null) {		                                   	 
		                        	 int section = (Integer) session.getAttribute("numOfColumns");
		                        	 String formAlignment = session.getAttribute("formAlignment")!= null ?(String) session.getAttribute("formAlignment"):"";
		                        	 if(section == 3){
		                        		 if(!formAlignment.equalsIgnoreCase("")){
		                        	 %>	                    
		                    			<option value="LMR" selected>LMR</option>	
		                    			<%} else { %>			                    			
			                    			<option value="select" selected>select</option>	 
			                    			<option value="LMR">LMR</option>      
		                    			<%} %>             
		                     	<%} else if(section == 2) {%>
		                     			<%if(formAlignment.equalsIgnoreCase("")){ %>
		                     			<option value="select" selected>select</option><% } %>
		                     			<% if(formAlignment.equalsIgnoreCase("LM")){ %>
		                     			<option value="LM" selected>LM</option>
		                     			<% } else { %><option value="LM">LM</option><% } %>
		                     			
		                     			<% if(formAlignment.equalsIgnoreCase("MR")){ %>
		                     			<option value="MR" selected>MR</option>
		                     			<% } else { %><option value="MR">MR</option><% } %>
		                     			
		                     			<% if(formAlignment.equalsIgnoreCase("LR")){ %>
		                     			<option value="LR" selected>LR</option>
		                     			<% } else { %><option value="LR">LR</option><% } %>
		                     					                     			
		                     	<%}else if(section == 1) {%>
		                     			<%if(formAlignment.equalsIgnoreCase("")){ %>
		                     			<option value="select" selected>select</option><% } %> 
		                     			<% if(formAlignment.equalsIgnoreCase("L")){ %>
		                     			<option value="L" selected>L</option>
		                     			<% } else { %><option value="L">L</option><% } %>
		                     			
		                     			<% if(formAlignment.equalsIgnoreCase("M")){ %>
		                     			<option value="M" selected>M</option>
		                     			<% } else { %><option value="M">M</option><% } %>
		                     			
		                     			<% if(formAlignment.equalsIgnoreCase("R")){ %>
		                     			<option value="R" selected>R</option>
		                     			<% } else { %><option value="R">R</option><% } %>
		                   		<%}%>  	
		                   <%}%> 
		                </select>		               
		                <span style="margin-left: 10px;">
		                <input id="formScriptButton" type="button" value="Form Javascript" onclick="openFormJavaScript();" class="btns" 
                            /> </span>                        
                        
                        </td>
                        <td>
                        	<span style="margin-left: 20px;"> <b>Tables:</b></span>
		           				<select id="attrTableName" name="attrTableName" onchange="doRefreshFieldsListOnScreen();"> 
		           						<c:if test="${tableId == null || tableId == -1}">
		           						<option value="" selected="selected" ><c:out value="select"/></option>
		           						</c:if>
		                        		 <c:forEach var="allTableList" items="${allTableList}">
						                        		<c:choose>
					                        					<c:when test="${allTableList.tableId != null and tableId == allTableList.tableId}">                     				    
					                        						<option value="${allTableList.tableId}" selected="selected"><c:out value="${allTableList.tableName}"/></option>
					                        					</c:when>
						                        				<c:otherwise>
						                        					<option value="${allTableList.tableId}" ><c:out value="${allTableList.tableName}"/></option>
						                        				</c:otherwise>         
						                        		</c:choose>                     					  				
		                        	</c:forEach> 
                        		
                        		</select> 
                        </td>
                        <td valign="middle">                        		
                       	<select name="selectedStructTableIds" id="selectedStructTableIds" size="3" ondblclick="doRefreshFieldsListOnScreenOnDoubleClick();"  style="width: 200px;">
							<c:forEach var="tables" items="${dynamicFormScreens.tables}">								
                        		 <c:forEach var="allTableList" items="${allTableList}">
                        		 	<c:if test="${tables.tableId == allTableList.tableId}">
                        		 	<c:choose>
                        		 		<c:when test="${tables.tableId == tableId}">
			    							<option value="${tables.tableId}" selected="selected"><c:out value="${allTableList.tableName }" /></option>
			    						</c:when>
			    						<c:otherwise>
			    							<option value="${tables.tableId}"><c:out value="${allTableList.tableName }" /></option>
			    						</c:otherwise>
			    					</c:choose>
			    					</c:if>
			    				</c:forEach>
			    			</c:forEach>
						</select>
                        </td>
                        <td>
                        	<input id="removeFromScreen" type="button" value="Remove Table" onclick="removeTableFromScreen();" class="btns" 
                            /> </span>
                        </td>                        
                        </tr>  
                    </table>                    
                        
                </div>
         <div id="controlSection" style="margin-top: 10px;">
         		<h3>Page Controls</h3> 
	           		<div id="messages" class="messageDiv">                
	                    <% if (request.getAttribute("resultMsg") != null) {%>
	                    <%= request.getAttribute("resultMsg")%>
	                    <% }%>
	           		</div>
                <table id="controlsTable" cellpadding="1" cellspacing="1" width="100%">
				<tr class="pageheader">
					<b><td>Type</td>					
					<td>Label</td>
					<td>Name</td>
					<!-- <td>DB Name</td> -->
					<td>Length</td>
					<td>Required</td>
					<td>Data Type</td>
					<!-- <td>Default Value</td> -->
					<!-- <td>JavaScript</td> -->
					<td>Position</td>
					<!-- <td>Alignment</td> -->
					<td>Active</td></b>
				</tr>
				<tr>
				<% String allDisabled = "";
					String allReadOnly = "";
					String screenReadOnly = "";
					String screenDisabled = "";
					String notEditDisabled = "";
						if(dynamicField.isFixed()){
							allDisabled = "disabled";
							allReadOnly = "readonly";
						}else {
							screenReadOnly = "readonly";
							screenDisabled = "disabled";
						}
					if(session.getAttribute("isEditField") == null || !(Boolean)session.getAttribute("isEditField")){
						notEditDisabled ="disabled";
					}
				%>
				
						<td class="tableDataField">                            
                            <select name="componentType" id="componentType" <%=allDisabled %> <%=screenDisabled %>  onclick="disableDataType(this);" >
                            <c:forEach var="dynamicFieldType" items="${fieldTypesList}">
				                        		<c:choose>
			                        					<c:when test="${dynamicField.controlType != null and dynamicFieldType.fieldTypeId == dynamicField.controlTypeId}">                     				    
			                        						<option value="${dynamicFieldType.fieldTypeId}" selected="selected"><c:out value="${dynamicFieldType.fieldTypeName}"/></option>
			                        					</c:when>
				                        				<c:otherwise>
				                        					<option value="${dynamicFieldType.fieldTypeId}"><c:out value="${dynamicFieldType.fieldTypeName}"/></option>
				                        				</c:otherwise>         
				                        		</c:choose>                     					  				
                        	</c:forEach>                            
                            </select>                         
                        </td>
                        <td class="tableDataField">
                            <input name="attributeLabel" id="attributeLabel" type="text" class="controlInputText" maxlength="25"
                                   value="${dynamicField.labelName}" <%=allReadOnly %> style="border: 1px solid green;"  />
                        </td>
                        <td class="tableDataField">
                            <input name="attributeName" id="attributeName" type="text" class="controlInputText" maxlength="25"
                                   value="${dynamicField.fieldName}" <%=allReadOnly %> <%=screenReadOnly %> />
                        </td>
                        <td class="tableDataField" style="display: none;">
                            <input name="attributeDBName" id="attributeDBName" type="text" class="controlInputText" maxlength="25"
                                   value="${dynamicField.dbfieldName}" <%=allReadOnly %> <%=screenReadOnly %>  />
                        </td>
                        <td class="tableDataField">
                            <input name="attributeLength" id="attributeLength" type="text" class="controlInputText" onkeypress="return validateNumber();"
                                   value="${dynamicField.length}" <%=allDisabled %> <%=screenReadOnly %> onblur="lengthValidate(this.value);"  />
                        </td>                        
                        <td class="tableDataField"> 
                        <c:choose>                       
                        <c:when test="${dynamicField.required != null && dynamicField.required}">
                        <input name="required" id="required" type="checkbox" value="true" checked <%=allDisabled %> <%=screenDisabled %>  />
                        </c:when>
                        <c:otherwise>
                        <input name="required" id="required" type="checkbox" value="true" <%=allDisabled %> <%=screenDisabled %>  />
                        </c:otherwise>   
                        </c:choose>                         
                        </td> 
                        <td class="tableDataField">                            
                            <select name="attributeDataType" id="attributeDataType" <%=allDisabled %> <%=screenDisabled %> >
                            <c:forEach var="dynamicDataType" items="${dataTypesList}">
				                        		<c:choose>
			                        					<c:when test="${dynamicField.dataType != null and dynamicDataType.dataTypeId == dynamicField.dataTypeId}">                     				    
			                        						<option value="${dynamicDataType.dataTypeId}" selected="selected"><c:out value="${dynamicDataType.dataTypeName}"/></option>
			                        					</c:when>
				                        				<c:otherwise>
				                        					<option value="${dynamicDataType.dataTypeId}"><c:out value="${dynamicDataType.dataTypeName}"/></option>
				                        				</c:otherwise>         
				                        		</c:choose>                     					  				
                        	</c:forEach>                            
                            </select> 

                        </td>
                        <td class="tableDataField" style="display: none;">
                           <input id="defaultValueButton" type="button"  value="Create/Edit" onclick="openDefaultValues();"
                            	class="disabledBtns"  <%=allDisabled %> <%=screenDisabled %>  /> 

                        </td>
                         <td class="tableDataField" style="display: none;">
                          <input id="attributeJavaScript" type="button" value="Create/Edit" onclick="openFieldJavaScript();" style="border: 1px solid green;"
                           <%if(!dynamicField.isFixed()){ %> class="btns" <%} else { %> class="disabledBtns" <%} %>
                            onmouseover="goLite(this.id)" onmouseout="goDim(this.id)" <%=allDisabled %> />    
                        </td>
                        <td class="tableDataField">
                            <input name="attributePosition" id="attributePosition" type="text" onkeypress="return validateFloatNumber(event, this.value);" style="width: 40px; border: 1px solid green;" 
                            maxlength="5" value="${dynamicField.floatPosition}" />

                        </td>  
                        <%-- <td class="tableDataField">
                             <select name="attributeFieldAlignment" id="attributeFieldAlignment" style="border: 1px solid green;" >
                             <% boolean displaySel= false;
                             if(dynamicField.getFieldAlignment() == null){ 
                            	 		dynamicField.setFieldAlignment("");
                            	 		displaySel = true; %>
                            	 		<option value="" selected>select</option><% } %>
                            	 		
			                          <% if (session.getAttribute("numOfColumns") != null) {		                                   	 
		                        	 String formAlignment =  session.getAttribute("formAlignment")!= null ?(String) session.getAttribute("formAlignment"):"";		                        	
		                        	 if(formAlignment.length() == 3){
		                      		 %>
		                    			<% if(dynamicField.getFieldAlignment().equalsIgnoreCase("L")){ %>
		                     			<option value="L" selected>L</option>
		                     			<% } else { %><option value="L">L</option><% } %>
		                     			
		                     			<% if(dynamicField.getFieldAlignment().equalsIgnoreCase("M")){ %>
		                     			<option value="M" selected>M</option>
		                     			<% } else { %><option value="M">M</option><% } %>
		                     			
		                     			<% if(dynamicField.getFieldAlignment().equalsIgnoreCase("R")){ %>
		                     			<option value="R" selected>R</option>
		                     			<% } else { %><option value="R">R</option><% } %>
		                     			<% if(!dynamicField.getFieldAlignment().equalsIgnoreCase("L") &&
		                     					!dynamicField.getFieldAlignment().equalsIgnoreCase("M")&&
		                     					!dynamicField.getFieldAlignment().equalsIgnoreCase("R")) {
		                     					if(!displaySel){%>
		                     					<option value="" selected>select</option><% }} %>
		                     				                    
		                     	<%} else if(formAlignment.length() == 2) {
		                     			String indviletter1 = formAlignment.substring(0,1);
		                     			String indviletter2 = formAlignment.substring(1);
		                     	%>
		                     			<% if(dynamicField.getFieldAlignment().equalsIgnoreCase(indviletter1)){ %>
		                     			<option value="<%=indviletter1 %>" selected><%=indviletter1 %></option>
		                     			<% } else { %><option value="<%=indviletter1 %>"><%=indviletter1 %></option><% } %>
		                     			<% if(dynamicField.getFieldAlignment().equalsIgnoreCase(indviletter2)){ %>
		                     			<option value="<%=indviletter2 %>" selected><%=indviletter2 %></option>
		                     			<% } else { %><option value="<%=indviletter2 %>"><%=indviletter2 %></option><% } %>	
		                     			<% if(!dynamicField.getFieldAlignment().equalsIgnoreCase(indviletter1) &&
		                     					!dynamicField.getFieldAlignment().equalsIgnoreCase(indviletter2)) {
		                     					if(!displaySel){%>
		                     					<option value="" selected>select</option><% }} %>	                     			
		                     					                     			
		                     	<%}else if(formAlignment.length() == 1) {%> 
		                     			<% if(dynamicField.getFieldAlignment().equalsIgnoreCase(formAlignment)){ %>
		                     			<option value="<%=formAlignment %>" selected><%=formAlignment %></option>
		                     			<% } else { %>		                     				
		                     				<option value="<%=formAlignment %>"><%=formAlignment %></option>
		                     				<%if(!displaySel){%>
	                     					<option value="" selected>select</option><% } %>
		                     			<% } %>
		                     			
		                   		<%}%>  	
		                   <%}%>    		
                             
                          	</select>

                        </td>    --%>                       
                        <td class="tableDataField">
                        <c:choose>
                        <c:when test="${dynamicField.active != null && dynamicField.active}">                        
                        <input name="active" id="active" type="checkbox" value="true" checked style="border: 1px solid green;" /></c:when>
                        <c:otherwise>
                         <input name="active" id="active" type="checkbox" value="true" style="border: 1px solid green;" /></c:otherwise>        
                         </c:choose>                   
                        </td>                       
                    </tr>
                    <tr>
                        <td height="30px" colspan="5"> 
                            <input id="save" type="button"  value="Save" onclick="saveDynamicScreenFieldForReports();" 
                               <%=notEditDisabled %>
                                <%if(notEditDisabled.equalsIgnoreCase("")){ %> class="btns" <%} else { %> class="disabledBtns" <%} %> />
                            
                            <input id="clear" type="button"  value="Clear" onclick="clearAllScreenFields();" 
                                <%=notEditDisabled %>
                                <%if(notEditDisabled.equalsIgnoreCase("")){ %> class="btns" <%} else { %> class="disabledBtns" <%} %> />
                           
                        </td>
                    </tr>
                </table>
    	</div>       
              <br/><br/>                       
             <div id="tableStructure">                
                    <table id="structureTable" width="100%" cellpadding="3" cellspacing="1" class="liting-table">  
                    <tr class="pageheader">					
					<b><td>Active</td>
					<td>Type</td>					
					<td>Label</td>
					<td>Name</td>
					<td>DB Name</td>
					<td>Length</td>
					<td>Required</td>
					<td>Data Type</td>					
					<td>Position</td></b>
					<!-- <td>Alignment</td>		 -->			
					<td> </td>
					</tr>                      
             		 <%
                                  List<DynamicField> allowedFields = new ArrayList<DynamicField>();
                                  allowedFields =(List<DynamicField>)session.getAttribute("structureList");
                                  DynamicField formField = null;                                                                
                                  int columnCount = 0;
                                  int nColumns = 1;
                                  if (session.getAttribute("numOfColumns") != null) {
                                      nColumns = (Integer) session.getAttribute("numOfColumns");
                                  }
                                  for (int i = 0; i < allowedFields.size(); i++) {
                                      formField = allowedFields.get(i); 
                        %>				
				<tr>	
					<td align="left" valign="middle">
						<% if(formField.isActive()){ %>
						yes <% } else { %> No <% } %>
					</td>				
					<td align="left" valign="middle">
						<%=formField.getControlType() %>
					</td>
					<td align="left" valign="middle">
						<c:out value="<%=formField.getLabelName()%>" />
				 	<%-- <input type="text" value="<%=formField.getLableName() %>" />  --%>
					</td>
					<td align="left" valign="middle">
						<%=formField.getFieldName() %>
					</td>
					<td align="left" valign="middle">
						<%=formField.getDbfieldName() %>
					</td>
					<td align="left" valign="middle">
						<%=formField.getLength() %>
					</td>
					<td align="left" valign="middle">
						<% if(formField.isRequired()){ %>
						yes <% } else { %> No <% } %>
					</td>
					<td align="left" valign="middle">
						<c:out value="<%=formField.getDataType() %>" />
					</td>					
					<td align="left" valign="middle">
						<c:out value="<%=formField.getFloatPosition() %>" />
					</td>					
					<%-- <td align="left" valign="middle">
						<c:out value="<%=formField.getFieldAlignment() %>" />
					</td> --%>
					
					<td align="left" valign="middle">
					<input id="edit<%=formField.getFieldName()%>"  type="button" value="Edit" onclick="editDynamicScreenField('<%=formField.getFieldName()%>')" class="btns"
                      />
                     <span style="margin-left: 2px;">&nbsp;</span>
                     <%-- <% if(!formField.isFixed()){ %>
                     <input id="delete<%=formField.getFieldName()%>"  type="button" value="Delete" onclick="deleteDynamicFormField('<%=formField.getFieldName()%>')" class="groovybutton"
                     onmouseover="goLite(this.id)" onmouseout="goDim(this.id)"  />
                     <% } else { %>
                     <input id="delete<%=formField.getFieldName()%>"  type="button" value="Delete" class="disabledGroovyBtn"  disabled="disabled"  />
                     <% } %> --%>
                     </td>
					
				</tr>				
				<% }  //End of loop %>
				<!-- <tr><td colspan="11" style="border: 1px solid #E3E2E0;height: 30px;" align="center">
					<input id="addScreen" type="button" value="Add to Screen" onclick="addTableToScreen();" class="groovybutton" 
                            onmouseover="goLite(this.id)" onmouseout="goDim(this.id)" /> 
                        
					
                    
                    </td></tr>-->
              </table>
                </div>
                 <div id="structureActionDiv" style="width:900px;" align="center">
                <br/>
                   <input id="saveForm" type="button" value="Save Form" onclick="saveFieldStructureForScreens()" class="btns" 
                     />
                </div>             
    </div>
	
    
 <b class="src"></b>
<b class="sbrc"></b>
<div class="clear"></div>
</div>
</div>
<div id="sidebar" class="column" >
		<ul class="left-menu">
		   <% 
				  
				  MenuItem mainMenu=null;
				  Iterator<MenuItem> iterator = items.iterator();
				  String menuId=null;
				  while(iterator.hasNext()){
				    mainMenu = (MenuItem)iterator.next();				    			   
		    	%>
		   
		    	<% if(session.getAttribute("seletedScreenIdForReports") != null && mainMenu.getMenuId() == (Integer)session.getAttribute("seletedScreenIdForReports")){ %>   			
		    			
		    			<li><a href="#" class="active" onclick="<%=mainMenu.getMenuURL()%>" ><%=mainMenu.getMenuName()%></a></li>	
		    	<%} else { %>	  		
				  		
				  		<li><a href="#" onclick="<%=mainMenu.getMenuURL()%>" ><%=mainMenu.getMenuName()%></a></li> 	
		  		<% } %>	    
     		
     	<%} %> 
		 </ul>
		</div>
</div>


		
		
		
<div class="clear"></div>
<div id="footer">
<div class="footer-container">
<div class="fot-logo"><a href="#"><img src="images/logo-footer.jpg" width="92" height="20" /></a></div>
<div class="fot-link"><a href="#">TERMS & CONDITIONS</a>  |  <a href="#">Privacy Policy</a>  |  <a href="#">Our Affiliate Program</a><br /> 
© Copyrights Alpha Systems Pvt Ltd. ALL RIGHTS RESERVED 2012 </div>
<div class="by"><a href="#">www.jenixcloud.com</a></div>
<div class="clear"></div>
</div>
</div>	
 <input type="hidden" id="fieldId" name="fieldId" value="${dynamicField.fieldId}" />  
 <input type="hidden" name="pageno" id="pageno" value="${page_no}"/> 
 <input type="hidden" name="servlet_name" id="servlet_name"/>
 <input type="hidden" name="callbackmethod_name" id="callbackmethod_name"/>
 <input type="hidden" id="rowSelected" name="rowSelected" value="" />
 <input type="hidden" id="fixed" name="fixed" value="${dynamicField.fixed}"/>
  <input type="hidden" id="tableId" name="tableId" value="<%=session.getAttribute("tableId")%>"/>
 <input type="hidden" id="dataSavedFlag" name="dataSavedFlag" value="<%= session.getAttribute("dataSavedFlag") %>"/>
 <input type="hidden" id="isEditField" name="isEditField" value="<%=session.getAttribute("isEditField")%>" />
 <input type="hidden" id="screenIdForReports" name="screenIdForReports" value="<%= session.getAttribute("seletedScreenIdForReports")%>" />  
 <% } catch(Exception e){
	e.printStackTrace();	 
 }%>
 
</form>
</body>
</fmt:bundle>
</html>
