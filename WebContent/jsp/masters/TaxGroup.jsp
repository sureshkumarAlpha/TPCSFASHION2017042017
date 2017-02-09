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
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/masters/masters.js"></script>
<style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}

</style>
<script>
$(function(){
	$('[data-toggle="tooltip"]').tooltip();
	});
</script>
<script>

$(function(){
$('[data-toggle="tooltip"]').tooltip();
});
$(document).ready(function(){
    $('#filter-dropdown').click(function (e) {
    //	alert(e);
        e.stopPropagation();
    });
});


</script>
<style>
.bootstrap-select{
width:150px !important; 
} 

</style>
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form action="" id="tpcslogin" method="post" role="form" autocomplete="off">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>

<div id="page-wrapper">
<div id="page-inner">
         <div class="row">
                <div class="col-md-11 col-sm-8 col-xs-8">
                    <h2 class="page-header">Tax Group</h2>
                </div>	
         </div>
         <div class="row row-no-margin">
												<div class='col-sm-3'>
												<div class='form-group'>
													<label >Tax Group</label>
														  <input class="form-control" type="text" name="tax_group" id="tax_group" value="<c:out value="${tax_group}"/>" onblur="searchTaxGroup()" onkeypress="enterKeyPress(event,'search');" placeholder="Tax Group Search..."  /> 
												</div>
												</div>	
												
													<div class='col-sm-3'>
									<div class='form-group'>
										<label for="tg_status"  style="display:block;">Status</label> 
										     <select  class="form-control selectpicker" data-live-search="false"   id="tg_status" name="tg_status" onchange="searchTaxGroup();" style="width:200px;">
											<option  value="-1" <c:if test="${tg_status eq -1 }">selected="selected"</c:if> >All</option>
											<option value="1" <c:if test="${tg_status eq 1 }">selected="selected"</c:if>>Active</option>
											<option value="0" <c:if test="${tg_status eq 0 }">selected="selected"</c:if>>Inactive</option>
									
										</select>
									</div>
								</div>	  
													  
													  
<div class='col-sm-2 pull-right new_tran_btn'>
			<div class='form-group pull-right'>
			<a onclick="addTaxGroup('n')"  class="btn btn-success"  ><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;New Tax Group</a>
			</div>
			</div>	
							</div>

		
		 <div class="row paginationstyle">		
			<div class='col-sm-12'> 
						<ul class="pagination pull-left"  >
							<li><a href="javascript:showTaxGroup()"
								data-toggle="tooltip" title="Refresh"> <i
									class="fa fa-refresh"></i>
							</a></li>
							<!-- <li><a href="#" data-toggle="tooltip" title="Reorder columns"> <i class="fa fa-reorder"></i></a></li> -->
						</ul>
						
						<div class="pull-left">
                    <div class="btn-group">
                        <div class="dropdown dropdown-lg">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="glyphicon glyphicon-filter text-primary" ></span>&nbsp;Filter&nbsp;<span class="caret"></span></button>
                            <div id="filter-dropdown" class="dropdown-menu dropdown-menu-left"  >
                                <div class="row">
                                 <div class='col-sm-6'>
												<div class='form-group'>
													<label >Tax</label>
														 <input class="form-control" type="text" class="txt" name="tax" id="tax"  value="<c:out value="${tax}"/>" placeholder="Enter Tax"/>
												</div>
												</div>	
</div>
            <div class="row">
                                  <button type="button"  class="btn btn-primary pull-left" onclick="clearTaxGroup();"><span class="glyphicon glyphicon-remove-circle" ></span>&nbsp;Reset </button>
                                  <button type="button"  id="search" class="btn btn-primary pull-right" onclick="searchTaxGroup()"><span class="glyphicon glyphicon-search" ></span>&nbsp;Apply</button>
                                  </div>
                            </div>
                        </div>
                    </div>
                    </div>
                                         <div class="btn-group pull-right">
  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   <span class="glyphicon glyphicon-tasks text-primary" ></span>&nbsp; Bulk Action&nbsp;<span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
    <li><a href="javascript:activeBulkTaxGroup('${pc.pageNo}',1)">Active</a></li>
    <li><a href="javascript:activeBulkTaxGroup('${pc.pageNo}',2)">Inactive</a></li>
     <li><a href="javascript:deleteBulTaxkGroup('${pc.pageNo}')">Delete</a></li>
  </ul>
</div>

						<ul class="pagination pagination-right" id="paging"
							 ></ul>
						<script>
				
				 var options = {
						  bootstrapMajorVersion:3,
				            currentPage: '${pc.pageNo}',
				            totalPages: '${pc.pageCount}',
				            size:'normal',
				            alignment:'right',
				            onPageClicked: function(e,originalEvent,type,page){
				            	changePageNumbersinTaxGroupMaster(page);
				            }
				        }

				        $('#paging').bootstrapPaginator(options);
				
				</script>
					</div>
					</div>
		
		<div class="row table-responsive">
		<table class="table table-bordered table-condensed">

<thead>

<tr class="header">		 						
<th valign="middle" nowrap="nowrap" align="center" class="w-mini first" style="width:10%;">Action</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Tax Group </th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Tax</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Tax Percentage</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Status</th>
<!-- <th valign="middle" nowrap="nowrap" align="center" class="w-mini">Status</th> -->
<th valign="middle" nowrap="nowrap" align="center" class="w-mini"><div class="checkbox"> <input class="checkbox_1" type="checkbox" id="toggle_check_all" name="toggle_check_all" /><label for="toggle_check_all" class="checkbox_1"></label></div></th>
</tr>	
</thead>

<tbody>				   
<c:set var="k" value="${1 }"/>
  <c:set var="chk" value="" />
<c:forEach var="obj" items="${tax_group_list}">
	<tr>
	                    <c:choose>
                    <c:when test="${chk!=obj.taxGroupId}">
	<td valign="middle"  nowrap="nowrap" align="left">
	<select class="form-control" id="select_action" name="select_action" onchange="displayTaxGroupActionList(this.value,'${obj.taxGroupId}','${pc.pageNo}')">
	<option selected="selected" value="-1">Action</option>
	<c:if test="${tg_rights ne null && tg_rights.editPermission eq 1 }">
	<option value="2">Edit</option>
	</c:if>
	<c:if test="${tg_rights ne null && tg_rights.deletePermission eq 1 }">
	<option value="1">Delete</option>
	</c:if>
	</select>
	</td>
	 	
	 	      </c:when>
                    <c:otherwise>
                  		<td>&nbsp;</td>
                    </c:otherwise>
                  </c:choose>
	<td >${obj.taxGroup}</td>
	<td >${obj.taxName}</td>
	<td >${obj.taxPercent}</td>
	
                    
	 <c:choose>
	 <c:when test="${chk!=obj.taxGroupId}">
	 <c:choose>
	<c:when test="${obj.status eq 1}">
		<td nowrap="nowrap" class="dataalignment"><c:out value="Active"/></td>
	</c:when>
	<c:otherwise>
		<td nowrap="nowrap" class="dataalignment"><c:out value="Inactive"/></td>
	</c:otherwise>  
	</c:choose>       
	 </c:when>
                    <c:otherwise>
                  		<td>&nbsp;</td>
                    </c:otherwise>
</c:choose>
	<!-- <td >Active</td> -->
	             <c:choose>
                    <c:when test="${chk!=obj.taxGroupId}">
<td><div class="checkbox"> <input class="checkbox_1" type="checkbox" id="mat_check_${obj.taxGroupId}" name="taxgroup_id" value="${obj.taxGroupId}" /><label for="mat_check_${obj.taxGroupId}" class="checkbox_1"></label> </div></td>
	 	<c:set var="chk" value="${obj.taxGroupId}" />
	 	      </c:when>
                    <c:otherwise>
                  		<td>&nbsp;</td>
                    </c:otherwise>
                  </c:choose>
	
	</tr>
	<c:set var="k" value="${k+1}"/>
	<c:set var="chk" value="${obj.taxGroupId}" />
</c:forEach>

<c:if test="${k < 15}">
		<c:set var="k" value="${15-k}"/>
		<c:forEach begin="1" end="${k}" varStatus="loop">
    	<tr> 
    	    <c:forEach begin="1" end="6" varStatus="loop"> 
        <td>&nbsp;</td>
        </c:forEach>
    	</tr>
		</c:forEach>
		</c:if>
</tbody></table>
		</div>
     
     </div>  
  </div>  
</div>

		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" />
		 <input type="hidden" name="request_type" id="request_type" /> 
		 <input type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
		<input type="hidden" name="pageno" id="pageno" value="${pc.pageNo}" />
		<input type="hidden" name="total_pages" id="total_pages" value="${pc.pageCount}" />
		<input type="hidden" name="mode" id="mode"  />
		<input type="hidden" name="tax_group_id" id="tax_group_id"  />
		<input type="hidden" name="active_mode" id="active_mode"  />
		<script type="text/javascript">

$('#task-table input:checkbox').click(function () {
    $(this).closest('tr').removeClass('highlight-row');
    if ($(this).is(':checked')) $(this).closest('tr').addClass('highlight-row');
}) 


$('#toggle_check_all').click(function(e){
var table= $(e.target).closest('table');
$('td input:checkbox',table).prop('checked',this.checked);
}); 

</script>

	</form>
</body>
</html>
          