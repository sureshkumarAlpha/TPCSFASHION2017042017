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
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/masters/masters.js"></script>
<script language="javascript">
var localizedStrings = {
	    	SURE_TO_DELETE_GROUP: "<fmt:message bundle="${bundle}" key="Group.AreYouSureYouToDelete"/>"
	};	
	
</script>
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
                    <h3 class="page-header">Specification Setting </h3>
                </div>	
               
         </div>
         
         <div class="row row-no-margin" > 
				
             
             		
				                <div class='col-sm-3'  >
								<div class='form-group'>
								<label>Group Type</label>
								<input class="form-control" id="group_type_name" name="group_type_name" size="30" type="text" value="<c:out value="${group_type_name}"/>"  placeholder="Select group type" onkeypress="enterKeyPress(event,'search');"/>
								<input class="form-control" id="group_type_id" name="group_type_id" size="30" type="hidden" value="<c:out value="${group_type_id}"/>"  />
				                     <label id="errormessage"></label>	
								</div>
								</div>
								
								
									<c:if test="${specsetting_rights.addPermission==1 }">	
									
									<div class='col-sm-2 pull-right new_tran_btn'>
			<div class='form-group pull-right'>
			<a onclick="addNewSpecificationSetting()"  class="btn btn-success"  ><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;New Specification Setting</a>
			</div>
			</div>
			
									</c:if>
											
								
       </div>
       
         
         
         <div class="row paginationstyle">		
			<div class='col-sm-12'> 
						<!-- <div >   -->

						<ul class="pagination pull-left"  >
						 <li><a href='javascript:showSpecSetting()'  data-toggle="tooltip" title="Refresh"> <i class="fa fa-refresh"></i></a></li>
						</ul>
						
						<div class="pull-left">
                    <div class="btn-group">
                        <div class="dropdown dropdown-lg">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="glyphicon glyphicon-filter text-primary" ></span>&nbsp;Filter&nbsp;<span class="caret"></span></button>
                            <div id="filter-dropdown" class="dropdown-menu dropdown-menu-left"  >
                                <div class="row">
				                <div class='col-sm-8'  >
								<div class='form-group'>
								<label>Specification</label>
								<input class="form-control" id="specification" name="specification" size="30" type="text" value="<c:out value="${specification}"/>"  placeholder="Enter Specification" onkeypress="enterKeyPress(event,'search');"/>
				                     <label id="errormessage"></label>	
								</div>
								</div>
								</div>
           					 	<div class="row">
                                  <button type="button"  class="btn btn-primary pull-left" onclick="clearSearchSpecSetting();"><span class="glyphicon glyphicon-remove-circle" ></span>&nbsp;Reset </button>
                                  <button type="button"  id="search" class="btn btn-primary pull-right" onclick="searchspecSetting()"><span class="glyphicon glyphicon-search" ></span>&nbsp;Apply</button>
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
    <!-- <li><a href="#">Active</a></li>
    <li><a href="#">Inactive</a></li> -->
      <li><a href="#">Delete</a></li>
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
				            	changeSpecSettingPageNumber(page);
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
<th valign="middle" nowrap="nowrap" align="center" class="w-mini first">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Action &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Group Type</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Specification1</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Specification1 Length</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Specification2</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Specification2 Length</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Specification3</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Specification3 Length</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Specification4</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Specification4 Length</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Specification5</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Specification5 Length</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Specification6</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Specification6 Length</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Specification7</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Specification7 Length</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini"><div class="checkbox"><input class="checkbox_1" type="checkbox" id="toggle_check_all" name="toggle_check_all" /><label for="toggle_check_all" class="checkbox_1"></label></div></th>
</tr>	
</thead>

<tbody>				   
<c:set var="k" value="${1 }"/>
<c:forEach var="obj" items="${specification_list}">
	<tr>
	<td valign="middle" nowrap="nowrap" align="left">
	<select class="form-control" id="select_action" name="select_action" onchange="specSettingActionList(this.value,${pc.pageNo},'${obj.groupType.groupTypeId}','${obj.spec1}','${obj.specLen1}','${obj.spec2}','${obj.specLen2}','${obj.spec3}','${obj.specLen3}','${obj.spec4}','${obj.specLen4}','${obj.spec5}','${obj.specLen5}','${obj.spec6}','${obj.specLen6}','${obj.spec7}','${obj.specLen7}')">
	<option selected="selected" value="-1">Action</option>
		<c:if test="${specsetting_rights.editPermission==1 }">	
		<option value="2">Edit</option>
		</c:if>
		<c:if test="${specsetting_rights.deletePermission==1 }">	
		<option value="1">Delete</option>
		</c:if>
	
	</select>

	</td>
	<td >${obj.groupType.groupType}</td>
	<td >${obj.spec1}</td>
	<td >${obj.specLen1}</td>
	<td >${obj.spec2}</td>
	<td >${obj.specLen2}</td>
	<td >${obj.spec3}</td>
	<td >${obj.specLen3}</td>
	<td >${obj.spec4}</td>
	<td >${obj.specLen4}</td>
	<td >${obj.spec5}</td>
	<td >${obj.specLen5}</td>
	<td >${obj.spec6}</td>
	<td >${obj.specLen6}</td>
	<td >${obj.spec7}</td>
	<td >${obj.specLen7}</td>
	<td><div class="checkbox"> <input class="checkbox_1" type="checkbox" id="mat_check_${obj.groupType.groupType}" name="mat_check_${obj.groupType.groupType}" value="" /><label for="mat_check_${obj.groupType.groupType}" class="checkbox_1"></label></div></td>
	
	
	</tr>
	<c:set var="k" value="${k+1}"/>
</c:forEach>

<c:if test="${k < 15}">
		<c:set var="k" value="${15-k}"/>
		<c:forEach begin="1" end="${k}" varStatus="loop">
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
		</c:forEach>
		</c:if>
</tbody></table> 


		</div>
</div>  
    </div>
</div>

		<%-- <jsp:include page="../common/ColumnPreferences.jsp" /> --%>
		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> 
		<input type="hidden" name="request_type" id="request_type" /> 
		<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
		<input type="hidden" name="pageno" id="pageno" value="${pageNo}" />
		<input type="hidden" name="total_pages" id="total_pages" value="${pc.pageCount}" />
		<input type="hidden" name="mode" id="mode" />
		<input type="hidden" name="screenId" id="screenId"  />
   		<input type="hidden" name="seletedScreenId" id="seletedScreenId"  />
     	<input type="hidden" name="invoke_servlet" id="invoke_servlet" value="${invoke_servlet}" /> 
		<input type="hidden" name="invoke_method" id="invoke_method" value="${invoke_method}" /> 

<input type="hidden" name="party_id" id="party_id"/>
<input type="hidden" name="pageno" id="pageno" value="${page_no}"/>
<input type="hidden" name="materialcount" id="materialcount" value="${material_count}"/>
<input type="hidden" name="group_type" id="group_type" value="${materialObj.groupType.groupTypeId}"/>
<input type="hidden" name="specification1" id="specification1" value="${materialObj.spec1}"/>
<input type="hidden" name="specification1_length" id="specification1_length" value="${materialObj.specLen1}"/>
<input type="hidden" name="specification2" id="specification2" value="${materialObj.spec2}"/>
<input type="hidden" name="specification2_length" id="specification2_length" value="${materialObj.specLen2}"/>
<input type="hidden" name="specification3" id="specification3" value="${materialObj.spec3}"/>
<input type="hidden" name="specification3_length" id="specification3_length" value="${materialObj.specLen3}"/>
<input type="hidden" name="specification4" id="specification4" value="${materialObj.spec4}"/>
<input type="hidden" name="specification4_length" id="specification4_length" value="${materialObj.specLen4}"/>
<input type="hidden" name="specification5" id="specification5" value="${materialObj.spec5}"/>
<input type="hidden" name="specification5_length" id="specification5_length" value="${materialObj.specLen5}"/>
<input type="hidden" name="specification6" id="specification6" value="${materialObj.spec6}"/>
<input type="hidden" name="specification6_length" id="specification6_length" value="${materialObj.specLen6}"/>
<input type="hidden" name="specification7" id="specification7" value="${materialObj.spec7}"/>
<input type="hidden" name="specification7_length" id="specification7_length" value="${materialObj.specLen7}"/>
<input type="hidden" name="request_type" id="request_type" />
<script type="text/javascript">

$('#task-table input:checkbox').click(function () {
    $(this).closest('tr').removeClass('highlight-row');
    if ($(this).is(':checked')) $(this).closest('tr').addClass('highlight-row');
}) 


$('#toggle_check_all').click(function(e){
var table= $(e.target).closest('table');
$('td input:checkbox',table).prop('checked',this.checked);
}); 

!function ($) {

	 
	$(function(){
		  $('#group_type_name').listGroupType({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroupType",
			  nameField :'#group_type_name',
			  idField:'#group_type_id',
			  
		  });			 
	 });  

}(window.jQuery);

	 
</script> 
	</form>
	
	  <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>
</body>
</html>		
