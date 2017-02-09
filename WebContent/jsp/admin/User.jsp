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
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/admin/user.js"></script>

<style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}

</style>
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body onload="display()">
<c:set var="is_right_have" value="${1}"/>
	<form action="" id="tpcslogin" method="post" role="form">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>
<div id="page-wrapper">
<div id="page-inner">
         <div class="row">
                <div class="col-md-11 col-sm-12 col-xs-8">
                    <h3 class="page-header"><fmt:message bundle="${bundle}" key="User.Users"/></h3>
                </div>	
               
         </div>
         <div class="row row-no-margin">
										<div class='col-sm-3'>
											<div class='form-group'>
												<label ><fmt:message bundle="${bundle}" key="User.Selectentity" /></label> 
												<c:choose>
                        <c:when test="${ho_id==1}">
				<select class="form-control" name="selectentity" id="selectentity" onchange="selectUserEntity(2)" >
                        	    <option value="-1"><fmt:message bundle="${bundle}" key="User.Selectentity" /></option>
                        		<c:forEach var="entityObj" items="${entity_list}">  
	                        	 			<c:choose>
												<c:when test="${entityObj.id == user_info.entityId}">                      				    
	                        						<option value="<c:out value="${entityObj.id}"/>" selected="selected"><c:out value="${entityObj.name}"/></option>
	                        					</c:when>
												<c:otherwise>
													<option value="<c:out value="${entityObj.id}"/>"><c:out value="${entityObj.name}"/></option>
												</c:otherwise>         
											</c:choose>                    				
                        			</c:forEach>	
                        	</select>
				         </c:when>
				          <c:otherwise> 
				          <select class="form-control" name="selectentity" id="selectentity" onchange="selectUserEntity(2)" >
                        	    <option value="-1"><fmt:message bundle="${bundle}" key="User.Selectentity" /></option>
                        		<c:forEach var="entityObj" items="${entity_list}">  
	                        	 			<c:choose>
												<c:when test="${entityObj.id == user_info.entityId}">                      				    
	                        						<option value="<c:out value="${entityObj.id}"/>" selected="selected"><c:out value="${entityObj.name}"/></option>
	                        					</c:when>											        
											</c:choose>                    				
                        			</c:forEach>	
                        	</select>
                        	  </c:otherwise>  
				          </c:choose>
											</div>
										</div>

								<div id="custdisplay">
								<div class='col-sm-3'>
											<div class='form-group'>
												<label ><fmt:message bundle="${bundle}" key="User.Selectcustomer"/></label>
												
												<c:choose>
                        <c:when test="${ho_id==1}">
						<select class="form-control" name="selectcustomer" id="selectcustomer" onchange="displayEntityUsers()" >
                        	    <option value="-1"><fmt:message bundle="${bundle}" key="User.Selectcustomer" /></option>
                        		<c:forEach var="customerObj" items="${customer_list}">  
	                        	 			<c:choose>
												<c:when test="${customerObj.id == user_info.customerId}">                      				    
	                        						<option value="<c:out value="${customerObj.id}"/>" selected="selected"><c:out value="${customerObj.name}"/></option>
	                        					</c:when>
												<c:otherwise>
													<option value="<c:out value="${customerObj.id}"/>"><c:out value="${customerObj.name}"/></option>
												</c:otherwise>         
											</c:choose>                    				
                        			</c:forEach>	
                        	</select>
    	                    </c:when> 
    					   <c:otherwise> 
    					   <select class="form-control" name="selectcustomer" id="selectcustomer" onchange="displayEntityUsers()" >
                        	    <option value="-1"><fmt:message bundle="${bundle}" key="User.Selectcustomer" /></option>
                        		<c:forEach var="customerObj" items="${customer_list}">  
	                        	 			<c:choose>
												<c:when test="${customerObj.id == user_info.customerId}">                      				    
	                        						<option value="<c:out value="${customerObj.id}"/>" selected="selected"><c:out value="${customerObj.name}"/></option>
	                        					</c:when>												      
											</c:choose>                    				
                        			</c:forEach>	
                        	</select>
                        	  </c:otherwise>  
    					   </c:choose>	</div>
											</div>
										

							</div>
							 <div id="factdisplay">
							<div class='col-sm-3'>
											<div class='form-group'>
												<label ><fmt:message bundle="${bundle}" key="User.Selectfactory"/></label> 
												<c:choose>
                        <c:when test="${ho_id==1}">
					<select class="form-control" name="selectfactory" id="selectfactory" onchange="displayEntityUsers()" >
                        	    <option value="-1"><fmt:message bundle="${bundle}" key="User.Selectfactory" /></option>
                        		<c:forEach var="factoryObj" items="${factory_list}">  
	                        	 			<c:choose>
												<c:when test="${factoryObj.id == user_info.factoryId}">                      				    
	                        						<option value="<c:out value="${factoryObj.id}"/>" selected="selected"><c:out value="${factoryObj.name}"/></option>
	                        					</c:when>
												<c:otherwise>
													<option value="<c:out value="${factoryObj.id}"/>"><c:out value="${factoryObj.name}"/></option>
												</c:otherwise>         
											</c:choose>                    				
                        			</c:forEach>	
                        	</select>  
                        	</c:when> 
                        	   <c:otherwise>  
                        	   <select class="form-control" name="selectfactory" id="selectfactory" onchange="displayEntityUsers()" >
                        	    <option value="-1"><fmt:message bundle="${bundle}" key="User.Selectfactory" /></option>
                        		<c:forEach var="factoryObj" items="${factory_list}">  
	                        	 			<c:choose>
												<c:when test="${factoryObj.id == user_info.factoryId}">                      				    
	                        						<option value="<c:out value="${factoryObj.id}"/>" selected="selected"><c:out value="${factoryObj.name}"/></option>
	                        					</c:when>												        
											</c:choose>                    				
                        			</c:forEach>	
                        	</select>     	
				     	 </c:otherwise>   
                        	    </c:choose>	 
											</div> 
										</div>
										</div>
										
							<div class='col-sm-2 pull-right new_tran_btn'>
			<div class='form-group pull-right'>
			<a onclick="addNewUser()"  class="btn btn-success"  ><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;New User</a>
			</div>
			</div>				

						</div>
         
        <div class="row paginationstyle">	
						<div class='col-sm-12'>    

						<ul class="pagination pull-left"  >
						
							<li><a href='javascript:backViewUser(1);'
								data-toggle="tooltip" title="Refresh"> <i
									class="fa fa-refresh"></i>
							</a></li>
							<li><a href="#" data-toggle="modal" data-target="#orgModal"
								onclick="organizeColumns(${subdocument_id});"
								title="Reorder columns"> <i class="fa fa-reorder"></i>
							</a></li>
						</ul>
	    <ul class="pagination pagination-right"  id="paging"  ></ul>
						<script>
				
				 var options = {
						  bootstrapMajorVersion:3,
				            currentPage: '${pc.pageNo}',
				            totalPages: '${pc.pageCount}',
				            size:'normal',
				            alignment:'right',
				            onPageClicked: function(e,originalEvent,type,page){
				            	changeUserPageNumbers(page);
				            }
				        }

				        $('#paging').bootstrapPaginator(options);
				
				</script>
					</div>
					</div>
					<div class="row table-responsive">

						<table class="table table-bordered table-condensed">
<tr class="header">				     	
	
  
					              <th align="center" valign="middle" class="w-mini first"><fmt:message bundle="${bundle}" key="Common.Action" /></th>
					              <th align="center" valign="middle" class="w-mini first">
					               <c:choose>
					                <c:when test="${user_info.entityId==1}">HO</c:when>
					                <c:otherwise>
					                  <c:choose>
					                   <c:when test="${user_info.entityId==2}">Customer</c:when>
					                   <c:otherwise>Factory</c:otherwise>
					                  </c:choose>
					                </c:otherwise>
					               </c:choose>
					              </th>
					              
					        
					          <th align="center" valign="middle" class="w-mini"><fmt:message bundle="${bundle}" key="User.UserName" /></th>	
					           <th align="center" valign="middle" class="w-mini"><fmt:message bundle="${bundle}" key="User.FirstName" /></th>	
					            <th align="center" valign="middle" class="w-mini"><fmt:message bundle="${bundle}" key="User.LastName" /></th>	
					              <th align="center" valign="middle" class="w-mini"><fmt:message bundle="${bundle}" key="User.Role" /></th>	
					                  <th align="center" valign="middle" class="w-mini last"><fmt:message bundle="${bundle}" key="User.Profile" /></th>					            				            
					        </tr>
					   
					
					    
					   		    <jsp:useBean id="userObj" scope ="request" class ="com.alpha.tpcsfashion.beans.User"/>
					                 <c:set var="k" value="${1}"/>
					                 <c:forEach var="userObj" items="${user_list}">
					                 
					                  <tr >
				                  			
					                 <td align="left" valign="middle">
					                 
											<c:if test="${rights.deletePermission == is_right_have}">

												<a class="advsrc" href="javascript:deleteUser(${pc.pageNo},${userObj.userId})"><fmt:message bundle="${bundle}" key="Common.Delete" /></a>&nbsp;&nbsp;|&nbsp;&nbsp;
											</c:if>
											
											<c:if test="${rights.editPermission == is_right_have}">
												<a class="advsrc" href="javascript:editUser(${pc.pageNo},${userObj.userId})"><fmt:message bundle="${bundle}" key="Common.Edit" /></a>
											</c:if>			
									</td>
											
												<td align="left" valign="middle"><c:out value="${userObj.entityName}"/></td>
													<c:choose>
											<c:when test="${userObj.isActive == 0}">
											<td align="left"  valign="middle"><font color="#F0586A"><c:out value="${userObj.userName}"/></font></td>
											</c:when>
											<c:otherwise>
											<td align="left" valign="middle"><c:out value="${userObj.userName}"/></td>
											</c:otherwise>
											</c:choose>
												<td align="left" valign="middle"><c:out value="${userObj.firstName}"/></td>
												<td align="left" valign="middle"><c:out value="${userObj.lastName}"/></td>
												<td align="left" valign="middle"><c:out value="${userObj.roles.roleName}"/></td>  		
												<td align="left" valign="middle"><c:out value="${userObj.profiles.profileName}"/></td>
																       
					          		  </tr>
					          		  <c:set var="k" value="${k+1}"/>
						            </c:forEach>
					   
						</table>
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
<input type="hidden" name="user_id" id="user_id"/>
<input type="hidden" name="entity_id" id="entity_id"/>
<input type="hidden" name="customer_id" id="customer_id"/>
<input type="hidden" name="factory_id" id="factory_id"/>
<input type="hidden" name="notification_type_id" id="notification_type_id"/>
<input type="hidden" name="customer_id" id="customer_id" value="${customer_id}"/>
<input type="hidden" name="factory_id" id="factory_id" value="${factory_id}"/>
<input type="hidden" name="ho_id" id="ho_id" value="${ho_id}"/>
<input type="hidden" id="main" />
</form>
</body>
</html>        						

