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
                    <h3 class="page-header">Entity Rights</h3>
                </div>	
         </div>
         
         <div class="row row-no-margin">
										<div class='col-sm-3'>
											<div class='form-group'>
												<label ><fmt:message bundle="${bundle}" key="EntityRights.Entity"/></label> 
												<input type="text" class="form-control" name="entity_name" id="entity_name" value="${entity_name}" placeholder="<fmt:message bundle="${bundle}" key="EntityRights.Entity" />Search..."  onkeypress="enterKeyPress(event,'search');"  onblur="SearchEntity()" /> 
											 <input type="hidden" name="search" id="search" onclick="SearchEntity()"/>
								</div> 
										</div>
										<div class='col-sm-2 pull-right new_tran_btn'>
			<div class='form-group pull-right'>
			<a onclick="addNewRights()"  class="btn btn-success"  ><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;New Entity Right</a>
			</div>
			</div>	

					<!-- 	 <button type="button" class="btn btn-primary"
							onclick="SearchEntity()">
							<i class="fa fa-search"></i><span>&nbsp;Search</span>
						</button>
						<button type="button" class="btn btn-primary" name="clear" onclick="document.getElementById('entity_name').value='';"
							id="clear" >
							<i class="glyphicon glyphicon-remove-circle"></i><span>&nbsp;Clear</span>
						</button> -->
						</div>
						
         
         <div class="row paginationstyle">	
						<div class='col-sm-12'>    

				<ul class="pagination pull-left"  >
							<li><a href='javascript:showEntityRights();'
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
				            	changeEntityRightsPageNumbers(page);
				            }
				        }

				        $('#paging').bootstrapPaginator(options);
				
				</script>
					</div>
					</div>
					<div class="row table-responsive">

						<table class="table table-bordered table-condensed">
<tr class="header">

					            <th align="center" valign="middle" class="w-mini first"><fmt:message bundle="${bundle}" key="Common.Action"/></th>
					        	 <th align="center" valign="middle" class="w-mini"><fmt:message bundle="${bundle}" key="EntityRights.Entity"/></th>
					             <th align="center" valign="middle" class="w-mini last"><fmt:message bundle="${bundle}" key="EntityRights.EntityRights"/></th>					            				            
 </tr>

					         <jsp:useBean id="entityrightsObj" scope ="request" class ="com.alpha.tpcsfashion.beans.EntityRights"/>
					                 <c:set var="previous_entityname"/>
					                 <c:set var="k" value="${1}"/>
					                 <c:forEach var="entityrightsObj" items="${entityrights_list}">
					                  
					                 <tr >
					                  			<td align="left" valign="middle">
					                  			<c:choose>
					                  				<c:when test="${previous_entityname!=entityrightsObj.entityName}">
					                  				
													<c:if test="${rights.deletePermission == is_right_have}">

						                  			<a class="advsrc" href="javascript:deleteEntityRights(${pc.pageNo},${entityrightsObj.entityId})"><fmt:message bundle="${bundle}" key="Common.Delete"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;
						                  			</c:if>
						                  			
													<c:if test="${rights.editPermission == is_right_have}">
					                  				
						            				<a class="advsrc" href="javascript:editEntityRights(${pc.pageNo},${entityrightsObj.entityId})"><fmt:message bundle="${bundle}" key="Common.Edit"/></a>
						            				</c:if>
					            					</c:when>
					            				</c:choose>
					            				</td>
					                  			<td align="left" valign="middle">
					                  			<c:choose>
					                  				<c:when test="${previous_entityname!=entityrightsObj.entityName}">
					                  					<c:out value="${entityrightsObj.entityName}"/>
					                  					<c:set var="previous_entityname" value="${entityrightsObj.entityName}"/>
					                  				</c:when>
					            				</c:choose>
					                  			</td>
					                  								                  			
					                  			<td align="left" valign="middle">${entityrightsObj.documentName}</td>
					                  								                  		
					          		  </tr>
					          		  <c:set var="k" value="${k+1}"/>
						            </c:forEach> 
</table>
					</div>
					    </div>  
    </div>
</div>

		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> <input
			type="hidden" name="request_type" id="request_type" /> <input
			type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
		<input type="hidden" name="pageno" id="pageno" value="${pc.pageNo}" />
		<input type="hidden" name="total_pages" id="total_pages"
			value="${pc.pageCount}" />
<input type="hidden" name="entityrights_id" id="entityrights_id"/>
<input type="hidden" name="notification_type_id" id="notification_type_id"/>
 <input type="hidden" id="main" />
	</form>
</body>
</html>        