<%@page import="com.alpha.tpcsfashion.beans.DynamicRefTable"%>
<%@page import="com.alpha.tpcsfashion.beans.DynamicField"%>
<%@page import="com.alpha.tpcsfashion.beans.DynamicFieldType"%>
<%@page import="com.alpha.tpcsfashion.beans.DynamicDataType"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.alpha.tpcsfashion.beans.MenuItem"%>
<%@page import="java.util.ArrayList"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title><fmt:message bundle="${bundle}" key="Title.Title" /></title>

<jsp:include page="../common/Header.jsp" />
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrapselect/bootstrap-select.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrapselect/bootstrap-select.min.css" />
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/common/bootstrapselect/bootstrap-select.js"></script>
 --%>
<style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}

</style>
<script language="javascript">
	   var localizedStrings = {
			    VALID_LABEL: "<fmt:message bundle="${bundle}" key="Field.Valid.Label"/>",
			    VALID_ATTR_NAME: "<fmt:message bundle="${bundle}" key="Field.Valid.Name"/>",	
			    VALID_ATTR_DB_NAME: "<fmt:message bundle="${bundle}" key="Field.Valid.DBName"/>",	
			    VALID_ATTR_ALIGNMENT: "<fmt:message bundle="${bundle}" key="Field.Valid.fieldAlignment"/>"
		};	  
				
	</script>
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form action="" id="validate-form" method="post" role="form">
	<% try { %>

<jsp:useBean id="dynamicField" class="com.alpha.tpcsfashion.beans.DynamicField" type="com.alpha.tpcsfashion.beans.DynamicField" scope="request"/>
<% 
				  List<MenuItem> items = (List<MenuItem>)session.getAttribute("screens_config_menus");
%>	
	
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>
<div id="page-wrapper">
<div id="page-inner">

<div class="row row-no-margin">

	<div class="row header-row">
						<div class="col-sm-12 col-xs-12">
							<div class='col-sm-8'>
								<h3  >Screen Customization</h3>   <!-- <i class="glyphicon glyphicon-cog" style="color:#4ca6a6;" ></i> -->
							</div>
						</div>
					</div>
					
               
		</div>
            <div class="row">
            <div class='col-sm-3'> 
               <div class='col-sm-12'>
                	<div class='form-group'>
                    <label >Screen</label>
                   <select class="form-control selectpicker" data-live-search="true"  id="screen_name" name="screen_name" onchange="showConfigScreensForId(this.value)" > 
                        	 <% 
				  
				  MenuItem mainMenu=null;
				  Iterator<MenuItem> iterator = items.iterator();
				  String menuId=null;
				  while(iterator.hasNext()){
				    mainMenu = (MenuItem)iterator.next();				    			   
		    	%>
		   
		    	<% if(mainMenu.getMenuId() == (Integer)session.getAttribute("seletedScreenId")){ %>		    			
		    			
		    			<option value="<%=mainMenu.getMenuId()%>" selected ><%=mainMenu.getMenuName()%></option>	
		    	<%} else { %>	  		
				  		
				  		<option value="<%=mainMenu.getMenuId()%>" ><%=mainMenu.getMenuName()%></option> 	
		  		<% } %>	    
     		
     			<%} %> 
                        		
                        </select>  
                    </div>
                    </div>
 					</div>
 					<div class='col-sm-3'>    
 					<div class='col-sm-12'>
                	<div class='form-group'>
                    <label >Section</label>
                   <select class="form-control" id="attrTableName" name="attrTableName" onchange="displayOrderTableData();" > 
                        <option value="-1">&lt;--Select--&gt;</option>	
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
                        </div> 
                    </div>
 					</div>
 					<!-- <div class='col-sm-3'>    
                <div class='form-group'>
                 <label >&nbsp;</label>
                 <div>
                    <input id="formScriptButton" type="button"
									value="Form Javascript" onclick="openFormJavaScript();"
									class="btn btn-primary" />
		    </div>
                </div>
            </div> -->
		</div>
		
		
		
            <div class="row">
            <div id="messages" class="messageDiv">                
	                    <% if (request.getAttribute("resultMsg") != null) {%>
	                    <%= request.getAttribute("resultMsg")%>
	                    <% }%>
	           		</div>
	           		</div>
            
          <%--   <% String allDisabled = "";
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
				%> --%>
				<% String allDisabled = "";
							String allReadOnly = "";
						if(dynamicField.isFixed()){
							allDisabled = "disabled";
							allReadOnly = "readonly";
						}
						
				   String fewfieldsReadonly = "";
				   		if(session.getAttribute("isEditField").equals(true)) {
					   		fewfieldsReadonly = "readonly";
				   		}
				   
				%>
				
				
				<div class='row'>
            <div class='col-sm-3'>  
            <div class='col-sm-12'>  
                <div class='form-group'>
                	<div class="tableDataField">
                    <label >Field Type</label>
                    <select class="form-control" name="componentType" id="componentType" <%=allDisabled %> onclick="disableDataType(this);" >
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
                    </div>
		    </div>
                </div>
            </div>
            <div class='col-sm-3'>
            <div class='col-sm-12'>    
                <div class='form-group'>
                	<div class="tableDataField">
                    <label >Display Name</label>
                     <div class="required-field-block">	  
                    <input class="form-control" name="attributeLabel" id="attributeLabel" type="text" class="controlInputText" maxlength="25" value="${dynamicField.labelName}" />
                     <div class="required-icon">
					<div class="text">*</div>
				     </div>
				     </div>
                    </div>
		    </div>
                </div>
            </div>
            <%-- <div class='col-sm-3'>    
                <div class='form-group'>
                	<div class="tableDataField">
                    <label >Name</label>
                     <div class="required-field-block">	  
                    <input class="form-control" name="attributeName" id="attributeName" type="text" class="controlInputText" maxlength="25" value="${dynamicField.fieldName}" <%=allReadOnly %>  />
                    <div class="required-icon">
					<div class="text">*</div>
				     </div>
				     </div>
                    </div>
		    
                </div>
            </div> --%>
            <div class='col-sm-3'>   
            <div class='col-sm-12'> 
                <div class='form-group'>
                	<div class="tableDataField">
                    <label >DB Field Name</label>
                      <div class="required-field-block">	 
                    <input class="form-control" name="attributeDBName" id="attributeDBName" type="text" class="controlInputText" maxlength="25"
                                   value="${dynamicField.dbfieldName}" <%=allReadOnly %> <%=fewfieldsReadonly %> />
                    <div class="required-icon">
					<div class="text">*</div>
				     </div>
				     </div>
                    </div>
		    </div>
                </div>
            </div>
            <div class='col-sm-1-5'>  
            <div class='col-sm-12'>  
                <div class='form-group'>
                	<div class="tableDataField">
                    <label >Length</label>
                    <input class="form-control" name="attributeLength" id="attributeLength" type="text" class="controlInputText" maxlength="3"
                                   value="${dynamicField.length}" <%=allDisabled %>   />
                                   <!-- onblur="lengthValidate(this.value);" -->
                    </div>
		    
                </div>
                </div>
            </div>
            <div class='col-sm-1-5'>   
            <div class='col-sm-12'> 
                <div class='form-group'>
                	<div class="tableDataField">
                    <label >Column Width %</label>
                    <input class="form-control" name="attributeColumnWidth" id="attributeColumnWidth" type="text" class="controlInputText" maxlength="3"
                                   value="${dynamicField.columnWidth}"   />
                                   <!-- onblur="lengthValidate(this.value);" -->
                    </div>
		    </div>
                </div>
            </div>
            
            <div class='col-sm-3'>    
            <div class='col-sm-12'>
                <div class='form-group'>
                	<div class="tableDataField">
                    <label >Data Type</label>
                    <select class="form-control" name="attributeDataType" id="attributeDataType" <%=allDisabled %>>
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
                    </div>
		    </div>
                </div>
            </div>
            <div class='col-sm-3'>    
            <div class='col-sm-12'>
            <div class="form-group" >
            <label>Position</label>
            <input class="form-control" name="attributePosition" id="attributePosition" type="text" onkeypress="enterKeyPress(event,'save');" 
                            maxlength="4" value="${dynamicField.floatPosition}" />
            </div>
            </div>
            </div>
            
            
                   <div class='col-sm-3'>    
            <div class='col-sm-12'>
            <div class="form-group" >
            <label>Alignment</label>
           <select name="attributeFieldAlignment" id="attributeFieldAlignment" class="form-control"   >
                             <% boolean displaySel= false;
                             if(dynamicField.getFieldAlignment() == null){ 
                            	 		dynamicField.setFieldAlignment("");
                            	 		displaySel = true; %>
                            	 		<option value="" selected>select</option><% } %>
                            	 		
			                          <% if (session.getAttribute("numOfColumns") != null) {		                                   	 
		                        	 String formAlignment =  session.getAttribute("formAlignment")!= null ?(String) session.getAttribute("formAlignment"):"";	
		                        	 
		                        	 System.out.println("formAlignment.length() :"+formAlignment.length());
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
            </div>
            </div>
            </div>
            
      
                        
            
            <div class='col-sm-2'>  
            <div class='col-sm-12'>
             <label>&nbsp;</label>  
            <div class="checkbox" >
            <c:choose>
                        <c:when test="${dynamicField.active != null && dynamicField.active}">  
                        <div class="checkbox"> <input class="checkbox_1" type="checkbox" id="active" name="active" value="true" checked  /><label for="active" class="checkbox_1">Visible this field </label></div>                      
                        </c:when>
                        <c:otherwise>
                         <div class="checkbox">  <input class="checkbox_1" type="checkbox" id="active" name="active" value="true"   /><label for="active" class="checkbox_1">Visible this field</label></div>
                         </c:otherwise>        
                         </c:choose>   
            </div>
            </div>
            </div>
            <div class='col-sm-4'> 
              <div class='col-sm-12'>   
                <div class='form-group'>
                <label >&nbsp;</label>
                    <div class="checkbox">
                    <c:choose>                       
                        <c:when test="${dynamicField.required != null && dynamicField.required}">
                         <div class="checkbox">  <input class="checkbox_1" type="checkbox" id="required" name="required" value="true" checked <%=allDisabled %>  /><label for="required" class="checkbox_1">Mandatory (Value must be entered for this field)</label></div>
                        </c:when>
                        <c:otherwise>
                         <div class="checkbox">  <input class="checkbox_1" type="checkbox" id="required" name="required" value="true"  <%=allDisabled %>  /><label for="required" class="checkbox_1"> Mandatory (Value must be entered for this field)</label></div>
                        </c:otherwise>   
                        </c:choose>
                      
                    </div>
		    
                </div>
            </div>
            
            <%-- <div class='col-sm-3'>    
                <div class='form-group'>
                 <label >&nbsp;</label>
                	<div class="tableDataField">
                    <input id="defaultValueButton" type="button"  value="Create/Edit" onclick="openDefaultValues();"
                           <%if(!dynamicField.isFixed()){ %> class="btn btn-primary" <%} else { %> class="btn btn-primary disable"<%} %>
                                 <%=allDisabled %> />
                    <input id="save" type="button"  value="Save" onclick="saveDynamicFormField();" class="btn btn-primary" />
                    <input id="clear" type="button"  value="Clear" onclick="clearAllFields();" class="btn btn-primary" />
                    </div>
		    
                </div>
            </div> --%>
                        
            
            </div>
            </div>
            
            
           <%--  <div class="row">
              <div class='col-sm-2'>    
            <div class="form-group">
            <label>Type</label>
            <select class="form-control" name="componentType" id="componentType" <%=allDisabled %> <%=screenDisabled %>  onclick="disableDataType(this);" >
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
            </div>
            </div>
            
             <div class='col-sm-2'>    
            <div class="form-group">
            <label>Label</label>
             <div class="required-field-block">	  
            <input class="form-control" name="attributeLabel" id="attributeLabel" type="text"   maxlength="25"
                                   value="${dynamicField.labelName}" <%=allReadOnly %>  />
                                   <div class="required-icon">
					<div class="text">*</div>
				     </div>
				     </div>
            </div>
            </div>
            
              <div class='col-sm-2'>    
            <div class="form-group">
            <label>Name</label>
            <input class="form-control" name="attributeName" id="attributeName" type="text"  maxlength="25"
                                   value="${dynamicField.fieldName}" <%=allReadOnly %> <%=screenReadOnly %> />
            </div>
            </div>
            
            <div class='col-sm-2' style="display: none;">    
            <div class="form-group" >
            <label>Name</label>
            <input class="form-control" name="attributeDBName" id="attributeDBName" type="text"  maxlength="25"
                                   value="${dynamicField.dbfieldName}" <%=allReadOnly %> <%=screenReadOnly %>  />
            </div>
            </div>
            
            <div class='col-sm-1'>    
            <div class="form-group" >
            <label>Length</label>
            <input class="form-control" name="attributeLength" id="attributeLength" type="text"  onkeypress="return validateNumber();"
                                   value="${dynamicField.length}" <%=allDisabled %> <%=screenReadOnly %> onblur="lengthValidate(this.value);"  />
            </div>
            </div>
            
            <div class='col-sm-2'>    
            <div class="form-group">
            <label>Data Type</label>
            <select class="form-control" name="attributeDataType" id="attributeDataType" <%=allDisabled %> <%=screenDisabled %> >
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
            </div>
            </div>
            
          
            
            <div class='col-sm-1'>    
            <div class="form-group" >
            <label>Position</label>
            <input class="form-control" name="attributePosition" id="attributePosition" type="text" onkeypress="return validateFloatNumber(event, this.value);" 
                            maxlength="5" value="${dynamicField.floatPosition}" />
            </div>
            </div>
            
            
            <div class='col-sm-1'>  
             <label>&nbsp;</label>  
            <div class="checkbox" >
            <label>
            <c:choose>
                        <c:when test="${dynamicField.active != null && dynamicField.active}">                        
                        <input name="active" id="active" type="checkbox" value="true" checked  /></c:when>
                        <c:otherwise>
                         <input name="active" id="active" type="checkbox" value="true"  /></c:otherwise>        
                         </c:choose>   
                         Active</label>  
            </div>
            </div>
            
            <div class='col-sm-1'>   
             <label>&nbsp;</label>   
            <div class="checkbox" >
            <label>
            <c:choose>                       
                        <c:when test="${dynamicField.required != null && dynamicField.required}">
                        <input name="required" id="required" type="checkbox" value="true" checked <%=allDisabled %> <%=screenDisabled %>  />
                        </c:when>
                        <c:otherwise>
                        <input name="required" id="required" type="checkbox" value="true" <%=allDisabled %> <%=screenDisabled %>  />
                        </c:otherwise>   
                        </c:choose>
                        Required
                        </label>
            </div>
            </div>
            
            </div> --%>
    
            <div class="row">
              <div class='col-sm-2' style="display: none;">  
              <div class='col-sm-12'>  
            <div class="form-group" >
            <label>JavaScript</label>
            <input id="defaultValueButton" type="button"  value="Create/Edit" onclick="openDefaultValues();"
                            	class="btn btn-primary"  <%=allDisabled %>   /> <%-- <%=screenDisabled %> --%>
            </div>
            </div>
            </div>
            <div class='col-sm-10'> 
            <div class='col-sm-12'>
                <div class='form-group'>
                 <label >&nbsp;</label>
                	<div class="tableDataField">
                    <input id="defaultValueButton" type="button"  value="Dropdown Values" onclick="openDefaultValues();"
                           <%if(!dynamicField.isFixed()){ %> class="btn btn-primary" <%} else { %> class="btn btn-primary disable"<%} %>
                                 <%=allDisabled %> />
                                  <input id="attributeJavaScript" type="button" value="Field Javascript" onclick="openFieldJavaScript();" 
                           <%if(!dynamicField.isFixed()){ %> class="btn btn-primary" <%} else { %> class="btn btn-primary disabled" <%} %>
                            <%=allDisabled %> />
                             <input id="formScriptButton" type="button" value="Form Javascript" onclick="openFormJavaScript();" class="btn btn-primary" />
                    <input id="save" type="button"  value="Save" onclick="saveDynamicFormField()" class="btn btn-primary" />
                    <input id="clear" type="button"  value="Clear" onclick="clearAllFields();" class="btn btn-primary" />
                     <input id="back" type="button"  value="Back" onclick="backToScreen();" class="btn btn-primary" />
                    </div>
		    
                </div>
               
            <%-- <div class="form-group" >
            <label></label>
            <input id="attributeJavaScript" type="button" value="Create/Edit" onclick="openFieldJavaScript();" 
                           <%if(!dynamicField.isFixed()){ %> class="btn btn-primary" <%} else { %> class="btn btn-primary disabled" <%} %>
                            <%=allDisabled %> />
            <input id="save" type="button"  value="Save" onclick="saveDynamicScreenField();" 
                              <%=notEditDisabled %>
                                <%if(notEditDisabled.equalsIgnoreCase("")){ %> class="btn btn-primary" <%} else { %> class="btn btn-primary disable" <%} %> />
                            
                            <input id="clear" type="button"  value="Clear" onclick="clearAllScreenFields();" 
                                <%=notEditDisabled %>
                                <%if(notEditDisabled.equalsIgnoreCase("")){ %> class="btn btn-primary" <%} else { %> class="btn btn-primary disable" <%} %> />                  
            </div> --%>
            </div>
            </div>
             </div>
            
            
            
            <div class="row ">
						<div class="col-sm-12">
							<div class="col-sm-12 ">
								<div class="table-responsive" id="tableStructure" >
                    <table class="table table-bordered table-condensed table-hover" id="task-table"> 
                    <thead>
                    <tr class="header">					
					<th>Type</th>	
					<th>Label</th>				
					<th>Name</th>
					<th>DB Field Name</th>
					<th>Length</th>
					<th>Column Width</th>
					<th>Required</th>
					<th>Data Type</th>	
					<th>Position</th>	
					<th>Alignment</th>		
					<th>Active</th>			
					<th>Action</th>
					</tr>  
					</thead>                     
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
				<tr class="datarow">					
					<td align="left" valign="middle">
						<%=formField.getControlType() %>
					</td>
					<td align="left" valign="middle">
						<c:out value="<%=formField.getLabelName()%>" />
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
						<%=(formField.getColumnWidth() )%>
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
					<td align="left" valign="middle">
						<c:out value="<%=formField.getFieldAlignment() %>" />
					</td>
					<td align="left" valign="middle">
						<% if(formField.isActive()){ %>
						yes <% } else { %> No <% } %>
					</td>					
					<td align="center" valign="middle">
					<a href="#" id="edit<%=formField.getFieldName()%>" onclick="editDynamicFormField('<%=formField.getFieldName()%>')"  title="Edit"><span class="glyphicon glyphicon-edit icon-edit"></span></a>
                     <% if(!formField.isFixed()){ %>
                     <a href="#" id="delete<%=formField.getFieldName()%>" onclick="deleteDynamicFormField('<%=formField.getFieldName()%>')"  title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
                     <% } else { %>
                     <%-- <input id="delete<%=formField.getFieldName()%>"  type="button" value="Delete" class="btn-danger"  disabled="disabled"  /> --%>
                     <%-- <a href="#" id="delete<%=formField.getFieldName()%>"   disabled="disabled"    title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a> --%>
                     <% } %>
                     </td>
					
				</tr>				
				<% }  //End of loop %>
              </table>
                </div>
                </div>
                </div>
                </div>
                
                
            <%-- <div id="tableStructure" class="row table-responsive">           
                    <table id="structureTable" class="table table-bordered table-condensed">  
                    <tr class="header">					
					<th>Active</th>
					<th>Type</th>					
					<th>Label</th>
					<th>Name</th>
					<th>DB Name</th>
					<th>Length</th>
					<th>Required</th>
					<th>Data Type</th>					
					<th>Position</th>
					<th>Alignment</th>				
					<th>Action</th>
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
				 	<input type="text" value="<%=formField.getLableName() %>" /> 
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
					<td align="left" valign="middle">
						<c:out value="<%=formField.getFieldAlignment() %>" />
					</td>
					
					<td align="center" valign="middle">
					<a href="#" id="edit<%=formField.getFieldName()%>"   onclick="editDynamicScreenField('<%=formField.getFieldName()%>')"  title="Edit"><span class="glyphicon glyphicon-edit icon-edit"></span></a>
                     <span style="margin-left: 2px;">&nbsp;</span>
                     </td>
					
				</tr>				
				<% }  //End of loop %>
              </table>
                </div> --%>
               <!--  <div id="structureActionDiv"  align="center">
                <br/>
                   	<input id="saveForm" type="button" value="Save Form" onclick="saveFieldStructureForScreens()" class="btn btn-primary" />
                </div>  -->        
 </div> 

  		</div>
</div>
		<jsp:include page="../common/ColumnPreferences.jsp" />
		<jsp:include page="../common/Footer.jsp" />

<input type="hidden" name="servlet_name" id="servlet_name" /> 
<input type="hidden" name="request_type" id="request_type" /> 
<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
<input type="hidden" name="pageno" id="pageno" value="${pc.pageNo}" />
<input type="hidden" name="total_pages" id="total_pages" value="${pc.pageCount}" />
<input type="submit" name="validation_btn" id="validation_btn" style="display:none;"/>		
<input type="hidden" id="fieldId" name="fieldId" value="${dynamicField.fieldId}" />
<input type="hidden" id="rowSelected" name="rowSelected" value="" />
<input type="hidden" id="fixed" name="fixed" value="${dynamicField.fixed}"/>
<input type="hidden" id="tableId" name="tableId" value="<%=session.getAttribute("tableId")%>"/>
<input type="hidden" id="dataSavedFlag" name="dataSavedFlag" value="<%= session.getAttribute("dataSavedFlag") %>"/>
<input type="hidden" id="isEditField" name="isEditField" value="<%=session.getAttribute("isEditField")%>" />
<input type="hidden" id="screenId" name="screenId" value="<%= session.getAttribute("seletedScreenId")%>" />  
<input type="hidden" name="invoke_servlet" id="invoke_servlet" value="${invoke_servlet}" /> 
<input type="hidden" name="invoke_method" id="invoke_method" value="${invoke_method}" /> 
 <% } catch(Exception e){
	e.printStackTrace();	 
 }%>
			

<script type="text/javascript">


$('#validate-form').bootstrapValidator({
	//  live: 'disabled',
	  message: 'This value is not valid',
	  feedbackIcons: {
	      valid: 'glyphicon glyphicon-ok',
	      invalid: 'glyphicon glyphicon-remove',
	      validating: 'glyphicon glyphicon-refresh'
	  },
	  fields: {
		  attributeLabel: {
              validators: {
                  notEmpty: {
                      message: 'This field is required'
                  }
              }
          },
          attrTableName: {
              validators: {
                  notEmpty: {
                      message: 'This field is required'
                  },
                    callback: {
                        message: 'This field is required',
                        callback: function(value, validator, $field) {
                        	if (value< 1) {
                                return {
                                    valid: false,
                                    message: 'This field is required'
                                };
                            }
                        	return true;
                        }
                    }
              }
          },
          attributeLength: {
        	    validators: {
                    numeric: {
                        message: 'This field must be a number'
                    }
                }
        },
        attributePosition: {
    	    validators: {
                numeric: {
                    message: 'This field must be a number'
                }
            }
    },
        attributeDBName: {
            validators: {
                notEmpty: {
                    message: 'This field is required'
                },
                regexp: {
                	 /* regexp:/^[a-z\s_0-9]+$/i, */
                    regexp:/^[a-z_0-9]+$/i,
                    message: 'This field must be in alphabetical characters,underscore but no spaces'
                }
            }
        }
          
         
          
	  }
	});
	 
</script>

	</form>
</body>
</html>
