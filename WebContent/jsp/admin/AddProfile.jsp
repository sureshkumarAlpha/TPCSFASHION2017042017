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
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/admin/profile.js"></script>
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
				    VALID_PROFILE: "<fmt:message bundle="${bundle}" key="Profile.ValidProfile"/>" ,
				    VALID_ENTITY: "<fmt:message bundle="${bundle}" key="Profile.ValidEntity"/>" ,
				    VALID_SCREEN: "<fmt:message bundle="${bundle}" key="Profile.OneScreenName"/>" 
			    	
			};	
	</script>
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form name="addprofile" id="addprofile" action="${pageContext.request.contextPath}/RequestHandlerServlet" method="post" role="form">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>

     <div class="top-bar">			
			       <div class="row">
                 <div class="col-md-11 col-sm-8 col-xs-12">
                    <h2 class="add-page-header">Profile</h2>
                </div>
		</div>
		
<div id="page-wrapper" class="add-top-wrapper"> 
   <div id="page-inner"> 
		 <div class='row'>
            <div class='col-sm-3'>
							<div class='form-group'>
								<label><fmt:message bundle="${bundle}"
										key="Profile.Profile" /></label>
								<div class="required-field-block">
									<input class="form-control" type="text" name="profile_name"
										id="profile_name" value="${profile_info.profileName}" placeholder="<fmt:message bundle="${bundle}" key="Profile.Profile" />"/>
									<div class="required-icon">
										<div class="text">*</div>
									</div>
								</div>
							</div>
						</div>
            <div class='col-sm-3'>    
                <div class='form-group'>
                <label ><fmt:message bundle="${bundle}" key="Profile.Entity" /></label>
                   <select  class="form-control"  name="selectentity" id="selectentity" onchange="getentitydocumentlist(this.selectedIndex,this.value)" >
                        	    <option value="-1"><fmt:message bundle="${bundle}" key="Profile.SelectEntity" /></option>
                        		<c:forEach var="entityObj" items="${entity_list}">  
	                        	 			<c:choose>
												<c:when test="${entityObj.id== profile_info.entity.entityId}">                      				    
	                        						<option value="<c:out value="${entityObj.id}"/>" selected="selected"><c:out value="${entityObj.name}"/></option>
	                        					</c:when>
												<c:otherwise>
													<option value="<c:out value="${entityObj.id}"/>"><c:out value="${entityObj.name}"/></option>
												</c:otherwise>         
											</c:choose>                    				
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
		<div id="entity_document_list">
                      		  ${entity_document_list}
                      		</div>
        </td></tr>
		</table>
		</div>

    	<div class='row pull-right'>        	         	 
            <div class='col-sm-12 col-xs-12 centered' style="margin-bottom: 10px;">      
             <button name="save" id="save"   class="btn btn-success ladda-button" value="Save" <c:if test="${mode eq 'n' }">onclick="saveProfile()"</c:if> <c:if test="${mode eq 'e' }">onclick="updateProfile()"</c:if> data-style="expand-right">
				<span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			</button>   
			    <button type="button" class="btn btn-danger"  <c:if test="${mode eq 'n' }">onclick="clearProfile(1)"</c:if> <c:if test="${mode eq 'e' }">onclick="clearProfile(2)"</c:if>><i class="glyphicon glyphicon-remove-circle"></i><span>&nbsp;Clear</span></button>					                   
           <button type="button" class="btn btn-primary"  onclick="backViewProfile()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>					             
            </div>            
    </div>
     
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
<input type="hidden" name="entity_id" id="entity_id"/>
<input type="hidden" name="entry_documents" id="entry_documents"/>
<input type="hidden" name="edit_documents" id="edit_documents"/>
<input type="hidden" name="delete_documents" id="delete_documents"/>
<input type="hidden" name="view_documents" id="view_documents"/>
<input type="hidden" name="excel_documents" id="excel_documents"/>
<input type="hidden" name="print_documents" id="print_documents"/>
<input type="hidden" name="appr_documents" id="appr_documents"/>
<input type="hidden" name="notification_type_id" id="notification_type_id"/>
<input type="hidden" name="entity_id" id="entity_id" value="${entity_id}"/>
<input type="hidden" name="profile_id" id="profile_id" value="${profile_id}" />
<input type="hidden" name="mode" id="mode" value="${mode}"/>

<script type="text/javascript">


$('#addprofile').bootstrapValidator({
	//  live: 'disabled',
	  message: 'This value is not valid',
	  feedbackIcons: {
	      valid: 'glyphicon glyphicon-ok',
	      invalid: 'glyphicon glyphicon-remove',
	      validating: 'glyphicon glyphicon-refresh'
	  },
	  fields: {
		  profile_name: {
              validators: {
                  notEmpty: {
                      message: 'This field is required'
                  }
              }
          },
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
        }
          
	  }
	});
	
	 
</script> 

	</form>
</body>
</html>