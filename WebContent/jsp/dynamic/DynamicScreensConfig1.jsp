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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrapselect/bootstrap-select.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrapselect/bootstrap-select.min.css" />
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/common/bootstrapselect/bootstrap-select.js"></script>

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
	<form action="" id="tpcslogin" method="post" role="form">
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

            <div class="row">
            <div class='col-sm-3'>    
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
 					<div class='col-sm-3'>    
                	<div class='form-group'>
                    <label >Table</label>
                   <select class="form-control" id="attrTableName" name="attrTableName" onchange="doRefreshFieldsListOnScreen();" > 
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
 					<div class='col-sm-3'>    
                <div class='form-group'>
                 <label >&nbsp;</label>
                 <div>
                    <input id="formScriptButton" type="button"
									value="Form Javascript" onclick="openFormJavaScript();"
									class="btn btn-primary" />
		    </div>
                </div>
            </div>
		</div>
		
		
		
            <div class="row">
            <div id="messages" class="messageDiv">                
	                    <% if (request.getAttribute("resultMsg") != null) {%>
	                    <%= request.getAttribute("resultMsg")%>
	                    <% }%>
	           		</div>
	           		</div>
            
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
				
            
            <div class="row">
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
            
            </div>
    
            <div class="row">
              <div class='col-sm-2' style="display: none;">    
            <div class="form-group" >
            <label>JavaScript</label>
            <input id="defaultValueButton" type="button"  value="Create/Edit" onclick="openDefaultValues();"
                            	class="btn btn-primary"  <%=allDisabled %> <%=screenDisabled %>  />
            </div>
            </div>
            
            <div class='col-sm-3'>    
            <div class="form-group" >
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
            </div>
            </div>
             </div>
            
            <div id="tableStructure" class="row table-responsive">           
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
					<td align="left" valign="middle">
						<c:out value="<%=formField.getFieldAlignment() %>" />
					</td>
					
					<td align="center" valign="middle">
					<%-- <input id="edit<%=formField.getFieldName()%>"  type="button" value="Edit" onclick="editDynamicScreenField('<%=formField.getFieldName()%>')" class="btns"/> --%>
					<a href="#" id="edit<%=formField.getFieldName()%>"   onclick="editDynamicScreenField('<%=formField.getFieldName()%>')"  title="Edit"><span class="glyphicon glyphicon-edit icon-edit"></span></a>
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
			<!--	<tr><td colspan="11" style="border: 1px solid #E3E2E0;height: 30px;" align="center">
					 <input id="addScreen" type="button" value="Add to Screen" onclick="addTableToScreen();" class="btns" 
                            onmouseover="goLite(this.id)" onmouseout="goDim(this.id)" />
                        
				 
                  
                    
                    </td></tr> -->
              </table>
                </div>
                <div id="structureActionDiv"  align="center">
                <br/>
                   	<input id="saveForm" type="button" value="Save Form" onclick="saveFieldStructureForScreens()" class="btn btn-primary" />
                </div>         
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
			
<input type="hidden" id="fieldId" name="fieldId" value="${dynamicField.fieldId}" />
<input type="hidden" id="rowSelected" name="rowSelected" value="" />
<input type="hidden" id="fixed" name="fixed" value="${dynamicField.fixed}"/>
<input type="hidden" id="tableId" name="tableId" value="<%=session.getAttribute("tableId")%>"/>
<input type="hidden" id="dataSavedFlag" name="dataSavedFlag" value="<%= session.getAttribute("dataSavedFlag") %>"/>
<input type="hidden" id="isEditField" name="isEditField" value="<%=session.getAttribute("isEditField")%>" />
<input type="hidden" id="screenId" name="screenId" value="<%= session.getAttribute("seletedScreenId")%>" />  
 <% } catch(Exception e){
	e.printStackTrace();	 
 }%>
			

	</form>
</body>
</html>
