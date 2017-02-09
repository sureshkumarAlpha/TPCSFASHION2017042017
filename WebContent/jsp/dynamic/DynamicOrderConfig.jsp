<%@page import="com.alpha.tpcsfashion.beans.DynamicField"%>
<%@page import="com.alpha.tpcsfashion.beans.DynamicFieldType"%>
<%@page import="com.alpha.tpcsfashion.beans.DynamicDataType"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.alpha.tpcsfashion.beans.MenuItem"%>
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

<body onload="disableDataType();">
	<form action="" id="validate-form" method="post" role="form">
<% try { %>	
<jsp:useBean id="dynamicField" class="com.alpha.tpcsfashion.beans.DynamicField" type="com.alpha.tpcsfashion.beans.DynamicField" scope="request"/>
  <% 
				  List<MenuItem> items = (List<MenuItem>)session.getAttribute("dynamic_order_menus");
  %>
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>
			
			<div id="messages" class="messageDiv">                
	                    <% if (request.getAttribute("resultMsg") != null) {%>
	                    <%= request.getAttribute("resultMsg")%>
	                    <% }%>
			</div> 
<div id="page-wrapper">
<div id="page-inner">


            <div class="row">
            
                
                <div class='col-sm-3'>    
                	<div class='form-group'>
                    <label >Screen</label>
                   <select class="form-control selectpicker" data-live-search="true"  id="field_config_name" name="field_config_name" onchange="displayOrderTableData(this.value)" > 
                        	 <% 
				  
                        	  MenuItem mainMenu=null;
           				  Iterator<MenuItem> iterator = items.iterator();
           				  String menuId=null;
           				  while(iterator.hasNext()){
           				    mainMenu = (MenuItem)iterator.next();		    			   
		    	%>
		   
		    	<% if(mainMenu.getMenuId() == (Integer)session.getAttribute("tableId")){ %>		    			
		    			
		    			<option value="<%=mainMenu.getMenuId()%>" selected ><%=mainMenu.getMenuName()%></option>	
		    	<%} else { %>	  		
				  		
				  		<option value="<%=mainMenu.getMenuId()%>" ><%=mainMenu.getMenuName()%></option> 	
		  		<% } %>	    
     		
     			<%} %> 
                        		
                        </select>  
                    </div>
 					</div>
                
		</div>
		
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
                <div class='form-group'>
                	<div class="tableDataField">
                    <label >Type</label>
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
            <div class='col-sm-3'>    
                <div class='form-group'>
                	<div class="tableDataField">
                    <label >Label</label>
                     <div class="required-field-block">	  
                    <input class="form-control" name="attributeLabel" id="attributeLabel" type="text" class="controlInputText" maxlength="25" value="${dynamicField.labelName}" />
                     <div class="required-icon">
					<div class="text">*</div>
				     </div>
				     </div>
                    </div>
		    
                </div>
            </div>
            <div class='col-sm-3'>    
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
            </div>
            <div class='col-sm-3'>    
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
            <div class='col-sm-3'>    
                <div class='form-group'>
                	<div class="tableDataField">
                    <label >Length</label>
                    <input class="form-control" name="attributeLength" id="attributeLength" type="text" class="controlInputText" onkeypress="return validateNumber();"
                                   value="${dynamicField.length}" <%=allDisabled %> onblur="lengthValidate(this.value);" />
                    </div>
		    
                </div>
            </div>
            
            <div class='col-sm-3'>    
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
            <div class='col-sm-2'>    
                <div class='form-group'>
                <label >&nbsp;</label>
                    <div class="checkbox">
                    <label >
                    <c:choose>                       
                        <c:when test="${dynamicField.required != null && dynamicField.required}">
                        <input name="required" id="required" type="checkbox" value="true" checked <%=allDisabled %> />
                        </c:when>
                        <c:otherwise>
                        <input name="required" id="required" type="checkbox" value="true" <%=allDisabled %> />
                        </c:otherwise>   
                        </c:choose>
                        Required</label>
                    </div>
		    
                </div>
            </div>
            
            <div class='col-sm-3'>    
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
            </div>
                        
            
            
            </div>
            
            <div id="tableStructure"  class="row table-responsive">
                    <table class="table table-bordered table-condensed table-hover">  
                    <tr class="header">					
					<td>Type</td>	
					<td>Label</td>				
					<td>Name</td>
					<td>DB Field Name</td>
					<td>Length</td>
					<td>Required</td>
					<td>Data Type</td>					
					<td>Action</td>
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
						<% if(formField.isRequired()){ %>
						yes <% } else { %> No <% } %>
					</td>
					<td align="left" valign="middle">
						<c:out value="<%=formField.getDataType() %>" />
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
                 <div id="structureActionDiv" align="center">
                <br/>
                    <input id="saveForm" type="button" value="Save Form" onclick="saveFieldStructure()" class="btn btn-primary" 
                     /> 
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
<input type="submit" name="validation_btn" id="validation_btn" style="display:none;"/>
<input type="hidden" id="fieldId" name="fieldId" value="${dynamicField.fieldId}" />  
<input type="hidden" id="rowSelected" name="rowSelected" value="" />
<input type="hidden" id="fixed" name="fixed" value="${dynamicField.fixed}"/>
<input type="hidden" id="dataSavedFlag" name="dataSavedFlag" value="<%= session.getAttribute("dataSavedFlag") %>"/>
<input type="hidden" id="tableId" name="tableId" value="<%= session.getAttribute("tableId")%>" />  
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
          attributeName: {
            validators: {
                notEmpty: {
                    message: 'This field is required'
                }
            }
        },
        attributeDBName: {
            validators: {
                notEmpty: {
                    message: 'This field is required'
                }
            }
        }
          
         
          
	  }
	});
	 
</script>
</form>
</body>
</html>