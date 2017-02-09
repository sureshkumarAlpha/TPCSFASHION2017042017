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
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form action="" id="tpcslogin" method="post" role="form">
<c:set var="is_right_have" value="${1}"/>	
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>
<div id="page-wrapper">
<div id="page-inner">
         <div class="row">
                <div class="col-md-11 col-sm-12 col-xs-8">
                    <h3 class="page-header"><fmt:message bundle="${bundle}" key="Profile.UserProfile"/></h3>
                </div>	
         </div>
         
         <div class="row row-no-margin">
										<div class='col-sm-3'>
											<div class='form-group'>
												<label ><fmt:message bundle="${bundle}"  key="Profile.Profile" /></label> 
												<input type="text" class="form-control" name="profile" id="profile" value="${profile}" placeholder="<fmt:message bundle="${bundle}" key="Profile.Profile"/>&nbsp;Search..." onkeypress="enterKeyPress(event,'search');"  onblur="SearchProfile()" /> 
											</div> 
										</div>
										
										<div class='col-sm-3'>
											<div class='form-group'>
												<label ><fmt:message bundle="${bundle}"  key="EntityRights.Entity" /></label> 
												<input type="text" class="form-control" name="entity" id="entity" value="${entity}" placeholder="<fmt:message bundle="${bundle}" key="EntityRights.Entity"/>Search..." onkeypress="enterKeyPress(event,'search');"  onblur="SearchProfile()" /> 
											</div> 
										</div>
										<div class='col-sm-2 pull-right new_tran_btn'>
			<div class='form-group pull-right'>
			<a onclick="addNewProfile()"  class="btn btn-success"  ><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;New Profile</a>
			</div>
			</div>	
				<input type="hidden" name="search" id="search" onclick="SearchProfile()"/>						

					<!-- 	 <button type="button" class="btn btn-primary"
							onclick="SearchProfile()">
							<i class="fa fa-search"></i><span>&nbsp;Search</span>
						</button>
						<button type="button" class="btn btn-primary" name="clear" id="clear"  onclick="clearUserProfile()">
							<i class="glyphicon glyphicon-remove-circle"></i><span>&nbsp;Clear</span>
						</button> -->
						</div>
         
         <div class="row paginationstyle">
         	<div class='col-sm-12'>    
						<ul class="pagination pull-left" >
							<!-- <li><a href="javascript:addNewProfile();"
								data-toggle="tooltip" title="Add Profile"> <i
									class="fa fa-plus-circle"></i>
							</a></li> -->
							<li><a href='javascript:showProfile();'
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
				            	changeProfilePageNumbers(page);
				            }
				        }

				        $('#paging').bootstrapPaginator(options);
				
				</script>
						</div>
					</div>
					
					<div class="row table-responsive">

						<table class="table table-bordered table-condensed">
<tr class="header">


  
					            <th align="center" valign="middle" class="w-mini first"><fmt:message bundle="${bundle}"  key="Common.Action" /></th>
					        	<th align="center" valign="middle" class="w-mini"><fmt:message bundle="${bundle}"  key="Profile.Profile" /></th>
					           <th align="center" valign="middle" class="w-mini"><fmt:message bundle="${bundle}"  key="Profile.Entity" /></th>	
					           <th align="center" valign="middle" class="w-mini"><fmt:message bundle="${bundle}"  key="Profile.Page" /></th>					            
					            <th align="center" valign="middle" class="w-mini"><fmt:message bundle="${bundle}"  key="Profile.Entry" /></th>	
					            <th align="center" valign="middle" class="w-mini"><fmt:message bundle="${bundle}"  key="Common.Edit" /></th>	
					           <th align="center" valign="middle" class="w-mini"><fmt:message bundle="${bundle}"  key="Common.Delete" /></th>	
					           <th align="center" valign="middle" class="w-mini"><fmt:message bundle="${bundle}"  key="Common.View" /></th>	
					           <th align="center" valign="middle" class="w-mini"><fmt:message bundle="${bundle}"  key="Profile.Excel" /></th>	
					           <th align="center" valign="middle" class="w-mini"><fmt:message bundle="${bundle}"  key="Profile.Print" /></th>	
					           <th align="center" valign="middle" class="w-mini last"><fmt:message bundle="${bundle}"  key="Profile.Approval" /></th>						            				            
					        </tr>
					   
					 
					    
					    <jsp:useBean id="profileObj" scope ="request" class ="com.alpha.tpcsfashion.beans.Profiles" />
					                 <c:set var="previous_profileid"/>
					                 <c:set var="k" value="${1}"/>
					                 <c:forEach var="profileObj" items="${profile_list}">
					                  
					                  <tr >
					                  			<td align="left" valign="middle">
					                  			<c:choose>
					                  				<c:when test="${previous_profileid!=profileObj.profileId}">
					                  				
													<c:if test="${rights.deletePermission == is_right_have}">

						                  			<a class="advsrc" href="javascript:deleteProfile(${pc.pageNo},${profileObj.entity.entityId},${profileObj.profileId})"><fmt:message bundle="${bundle}"  key="Common.Delete" /></a>&nbsp;&nbsp;|&nbsp;&nbsp;
						                  			</c:if>
						                  			<c:if test="${rights.editPermission == is_right_have}">
						            				<a class="advsrc" href="javascript:editProfile(${pc.pageNo},${profileObj.entity.entityId},${profileObj.profileId})"><fmt:message bundle="${bundle}"  key="Common.Edit" /></a>
						            				</c:if>
					            					</c:when>
					            				</c:choose>
					            				</td>
					            				<td align="left" valign="middle">
					                  			<c:choose>
					                  				<c:when test="${previous_profileid!=profileObj.profileId}">
					                  					<c:out value="${profileObj.profileName}"/>					                  					
					                  				</c:when>
					            				</c:choose>
					                  			</td>
					                  			<td align="left" valign="middle">
					                  			<c:choose>
					                  				<c:when test="${previous_profileid!=profileObj.profileId}">
					                  					<c:out value="${profileObj.entity.entityName}"/>
					                  					<c:set var="previous_profileid" value="${profileObj.profileId}"/>
					                  				</c:when>
					            				</c:choose>
					                  			</td>
					                  								                  			
					                  			<td align="left" valign="middle"><c:out value="${profileObj.entity.documentName}" /></td>
					                  			<td align="left" valign="middle"><c:out value="${profileObj.entryStatus}"/></td>					                  		
					                  			<td align="left" valign="middle"><c:out value="${profileObj.editStatus}"/></td>
					                  			<td align="left" valign="middle"><c:out value="${profileObj.deleteStatus}"/></td>
					                  			<td align="left" valign="middle"><c:out value="${profileObj.viewStatus}"/></td>
					                  			<td align="left" valign="middle"><c:out value="${profileObj.excelStatus}"/></td>
					                  			<td align="left" valign="middle"><c:out value="${profileObj.printStatus}"/></td>
					                  			<td align="left" valign="middle"><c:out value="${profileObj.apprStatus}"/></td>
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
<input type="hidden" name="request_type" id="request_type" value="${type_of_request}" /> 
<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
<input type="hidden" name="pageno" id="pageno" value="${pc.pageNo}" />
<input type="hidden" name="total_pages" id="total_pages" value="${pc.pageCount}" />
<input type="hidden" name="profile_id" id="profile_id"/>
<input type="hidden" name="entity_id" id="entity_id"/>
<input type="hidden" name="entityrights_id" id="entityrights_id"/>
<input type="hidden" name="notification_type_id" id="notification_type_id"/>
<input type="hidden" name="entity_document_list" id="entity_document_list"/>
<input type="hidden" id="main" />


	</form>
</body>
</html>        