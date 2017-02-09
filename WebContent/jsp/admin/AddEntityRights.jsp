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
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/admin/entity.js"></script>
<style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}

</style>
<script type="text/javascript">	    
		var localizedStrings = {
				    HO: "<fmt:message bundle="${bundle}" key="EntityRights.HO"/>",
				    CUSTOMER: "<fmt:message bundle="${bundle}" key="EntityRights.Customer"/>",
				    FACTORY: "<fmt:message bundle="${bundle}" key="EntityRights.Factory"/>",
				    VALID_ENTITY: "<fmt:message bundle="${bundle}" key="EntityRights.ValidEntity"/>",
				    VALID_MODULE: "<fmt:message bundle="${bundle}" key="EntityRights.ValidModule"/>",
				    SELECT_AT_LEAST: "<fmt:message bundle="${bundle}" key="EntityRights.SelectAtleastScreen"/>"
			};	
		var iProfile = "${set_success}";
	</script>
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body <c:if test="${mode eq 'e' }">onload="check()"</c:if> >
	<form name="addentityrights" id="addentityrights" action="${pageContext.request.contextPath}/RequestHandlerServlet" method="post" role="form">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>
                
     <div class="top-bar">
           <div class="row">
             <div class="col-md-11 col-sm-8 col-xs-12">
                 <h2 class="add-page-header" ><fmt:message bundle="${bundle}"  key="EntityRights.AddEntityRights"/></h2>
             </div>
        </div>
           
                
<div id="page-wrapper" class="add-top-wrapper"> 
   <div id="page-inner">   
	 <div class='row'>
            <div class='col-sm-3'>    
                <div class='form-group'>
                <label >Entity</label>
                <!-- onchange="labelchange(this.selectedIndex,this.value)"  -->
                 <div class="required-field-block">	 
                   <select class="form-control" name="selectentity" id="selectentity" >
                        	    <option value="-1"><fmt:message bundle="${bundle}" key="EntityRights.SelectEntity"/></option>
                        		<c:forEach var="entityObj" items="${entity_list}">  
	                        	 			<c:choose>
												<c:when test="${entityObj.id == entityrights_info.entityId}">                      				    
	                        						<option value="<c:out value="${entityObj.id}"/>" selected="selected"><c:out value="${entityObj.name}"/></option>
	                        					</c:when>
												<c:otherwise>
													<option value="<c:out value="${entityObj.id}"/>"><c:out value="${entityObj.name}"/></option>
												</c:otherwise>         
											</c:choose>                    				
                        			</c:forEach>	
                        	</select>
                        	  <div class="required-icon">
							<div class="text">*</div>
						     </div>
						     </div>
                        	<%-- <fmt:message bundle="${bundle}"  key="EntityRights.SltModuleApplicableFor"/> <span id="entity"></span>: --%>
		    
                </div>
            </div>
            <div class='col-sm-3'>    
                <div class='form-group'>
                <label >Module</label>
                 <div class="required-field-block">	 
            <select class="form-control"  name="selectmodule" id="selectmodule" onchange="getdocuments(this.selectedIndex,this.value)" >
                        	    <option value="-1"><fmt:message bundle="${bundle}" key="EntityRights.SelectModule"/></option>
                        		<c:forEach var="moduleObj" items="${module_list}">  
	                        	 			<c:choose>
												<c:when test="${moduleObj.id == entityrights_info.moduleId}">                      				    
	                        						<option value="<c:out value="${moduleObj.id}"/>"  selected="selected"><fmt:message bundle="${bundle}"  key="${moduleObj.name}" /></option>
	                        					</c:when>
												<c:otherwise>
													<option value="<c:out value="${moduleObj.id}"/>"><fmt:message bundle="${bundle}"  key="${moduleObj.name}"/></option>
												</c:otherwise>         
											</c:choose>                    			
											<c:out value="${moduleObj.name}"></c:out>	
                        			</c:forEach>	
                        	</select>
                        	  <div class="required-icon">
							<div class="text">*</div>
						     </div>
						     </div>
                        	</div>
                        	</div>
			<div class='col-sm-3'>    
                <div class='form-group'>
                <label >Documents</label>
            <select class="form-control"  name="selectdocument" id="selectdocument" <c:if test="${mode eq 'n'}">onchange="getsubdocuments(this.selectedIndex,this.value)" </c:if> <c:if test="${mode eq 'e' }">onchange="getSelecteddocuments(this.selectedIndex,this.value)" </c:if>  >
                        	    <option value="-1"><fmt:message bundle="${bundle}" key="EntityRights.SelectDocument"/></option>
                        		<c:forEach var="documentObj" items="${document_list}">  
	                        	 			<c:choose>
												<c:when test="${documentObj.id == entityrights_info.documentId}">                      				    
	                        						<option value="<c:out value="${documentObj.id}"/>"  selected="selected"><fmt:message bundle="${bundle}"  key="${documentObj.name}" /></option>
	                        					</c:when>
												<c:otherwise>
													<option value="<c:out value="${documentObj.id}"/>"><fmt:message bundle="${bundle}"  key="${documentObj.name}"/></option>
												</c:otherwise>         
											</c:choose>                    			
											<c:out value="${documentObj.name}"></c:out>	
                        			</c:forEach>	
                        	</select>
                        	</div>
                        	</div>                        	
                        	
	</div>
	</div> 
</div>
</div>
<div id="page-wrapper" style="margin-top: 0;"> 
        <div id="page-inner">
        <div class="row">
	<div class="row table-responsive">
		<table class="table table-bordered table-condensed" id="submain">
		<tr><td>
		<div id="sub_document_list">
                      		 ${entityrights_info.documentName}
        </div>
        </td></tr>
		</table>
		</div>
	</div>
	 </div>
	<div class='row pull-right'>        	         	 
            <div class='col-sm-12 col-xs-12 centered' style="margin-bottom: 10px;">      
             <button name="save" id="save"   class="btn btn-success ladda-button" value="Save" <c:if test="${mode eq 'n' }">onclick="saveEntityRights()"</c:if> <c:if test="${mode eq 'e' }">onclick="updateEntityRights()"</c:if> data-style="expand-right">
				<span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			</button>   
			    <button type="button" class="btn btn-danger"  onclick="clearEntityRights()"><i class="glyphicon glyphicon-remove-circle"></i><span>&nbsp;Clear</span></button>					                   
           <button type="button" class="btn btn-primary"  onclick="backViewEntityRights()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>					             
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
<input type="hidden" name="module_id" id="module_id"/>
<input type="hidden" name="document_id" id="document_id"/>
<input type="hidden" name="selected_documents" id="selected_documents"/>
<input type="hidden" name="notification_type_id" id="notification_type_id"/>
<input type="hidden" name="entityrights_id" id="entityrights_id"/>
<input type="hidden" name="entity_id_for_alert" id="entity_id_for_alert" value="${entity_id_for_alert}"/>
<input type="hidden" name="mode" id="mode" value="${mode }"/>

<script type="text/javascript">


$('#addentityrights').bootstrapValidator({
	//  live: 'disabled',
	  message: 'This value is not valid',
	  feedbackIcons: {
	      valid: 'glyphicon glyphicon-ok',
	      invalid: 'glyphicon glyphicon-remove',
	      validating: 'glyphicon glyphicon-refresh'
	  },
	  fields: {
        selectentity: {
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
        selectmodule: {
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
        }
          
         
          
	  }
	});
	
	
	

	 
</script> 

	</form>
</body>
</html>                