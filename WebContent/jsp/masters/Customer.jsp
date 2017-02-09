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
<script language="javascript">
	var contextpath = "${pageContext.request.contextPath}";

	function getData(selectedFields,totalColumns){		
		getCustomerData(selectedFields,totalColumns);
		}
	function getCustomerData(selectedFields,totalColumns){
	pageno = 1;
		setVal('pageno',pageno);	
		setVal('sel_columns',selectedFields);
		setVal('tot_columns',totalColumns);
		setVal('request_type','Normal');
		setVal('servlet_name','CustomerServlet');
		setVal('callbackmethod_name','doDisplayCustomerAfterColumnOrganized');
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();
	}  


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
                <div class="col-md-11 col-sm-8 col-xs-8">
                    <h3 class="page-header">Associates</h3>
                </div>	
        
         </div>
         <div class="row row-no-margin">
												<div class='col-sm-3'>
												<div class='form-group'>
													<label >Company Name</label>
														  <input class="form-control" type="text" name="comp_name" id="comp_name" value="${comp_name}"  onkeypress="enterKeyPress(event,'search');"  onblur="searchCustomer()" placeholder="Company Search..."  /> 
												</div>
												</div>		  
												<div class='col-sm-3'>
												<div class='form-group'>
													<label >Display Name</label>
														 <input class="form-control" type="text" class="txt" name="disp_name" id="disp_name"  value="${disp_name}"  onkeypress="enterKeyPress(event,'search');"  onblur="searchCustomer()" placeholder="Enter Display Name"/>
												</div>
												</div>		  
					<c:if test="${customer_rights.addPermission==1 }">									  
<div class='col-sm-2 pull-right new_tran_btn'>
			<div class='form-group pull-right'>
			<a onclick="addCustomer('n')"  class="btn btn-success"  ><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;New Associate</a>
			</div>
			</div>	
			</c:if>
							</div>
        
		
		 <div class="row paginationstyle">		
			<div class='col-sm-12'> 
						<ul class="pagination pull-left"  >
							<li><a href="javascript:showCustomer()"
								data-toggle="tooltip" title="Refresh"> <i
									class="fa fa-refresh"></i>
							</a></li>
							<li><span data-toggle="modal" data-target="#orgModal" onclick="organizeColumns(${subdocument_id});"><a href="#" data-toggle="tooltip" onclick="organizeColumns(${subdocument_id});"
								title="Reorder columns"> <i class="fa fa-reorder"></i>
							</a></span></li>
						</ul>
<div class="pull-left">
                    <div class="btn-group">
                        <div class="dropdown dropdown-lg">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="glyphicon glyphicon-filter text-primary" ></span>&nbsp;Filter&nbsp;<span class="caret"></span></button>
                            <div id="filter-dropdown" class="dropdown-menu dropdown-menu-left"  >
                                <div class="row">
                                 <div class='col-sm-6'>
												<div class='form-group'>
													<label >Account Group</label>
														 <input class="form-control"  type="text" class="txt" name="account_group" id="account_group" value="${account_group}" placeholder="Type and select Account Group"/>
												        <input  id="account_group_id" name="account_group_id" type="hidden"  size="30" value="${account_group_id}" />
												</div>
												</div>	
												
												<div class='col-sm-6'>    
                <div class='form-group'>
                    <label for="customer_code">Code</label>	
                    <input class="form-control" id="customer_code" name="customer_code" size="30" type="text" value="${cust_info.partyCode}" placeholder="Enter Code"/>
		     </div>
                </div>
            </div>

            <div class="row">
                                  <button type="button"  class="btn btn-primary pull-left" onclick="clearCustomer();"><span class="glyphicon glyphicon-remove-circle" ></span>&nbsp;Reset </button>
                                  <button type="button"  id="search" class="btn btn-primary pull-right" onclick="searchCustomer()"><span class="glyphicon glyphicon-search" ></span>&nbsp;Apply</button>
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
    <li><a href="#">Active</a></li>
    <li><a href="#">Inactive</a></li>
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
				            	changePageNumbersinCustomerMaster(page);
				            }
				        }

				        $('#paging').bootstrapPaginator(options);
				
				</script>
					</div>
					</div>
		
		<%-- <div class="row table-responsive">
		${cust_grid}
		</div> --%>
		
		<div class="row table-responsive">
		<table class="table table-bordered table-condensed table-hover" id="task-table"><thead><tr class="header"><th style="width: 107px;">Action</th><th>Code</th><th>Company Name</th><th>Dispaly Name</th><th>currency</th><th>Account Group</th><th>Status</th><th><div class="checkbox"><input class="checkbox_1" type="checkbox" id="toggle_check_all" name="toggle_check_all"><label for="toggle_check_all" class="checkbox_1"></label></div></th></tr> </thead><tbody><tr class="datarow"> <td><select style=" align:left;width: 97px; " class="form-control" id="select_action" name="select_action" onchange="displayCustomerActionList(this.value,'1','328')"><option selected="selected" value="-1">Action</option><option value="1">Delete</option><option value="2">Edit</option></select></td><td align="left" valign="middle">Customer1</td><td align="left" valign="middle">Customer1</td><td align="left" valign="middle">Customer1</td><td align="left" valign="middle">Rupees</td><td align="left" valign="middle">Sundry Debtor</td><td align="left" valign="middle">Active</td><td><div class="checkbox"> <input class="checkbox_1" type="checkbox" id="cust_check_328" name="cust_check_328" value=""><label for="cust_check_328" class="checkbox_1"></label></div></td></tr><tr class="datarow"> <td><select style=" align:left;width: 97px; " class="form-control" id="select_action" name="select_action" onchange="displayCustomerActionList(this.value,'1','329')"><option selected="selected" value="-1">Action</option><option value="1">Delete</option><option value="2">Edit</option></select></td><td align="left" valign="middle">Supplier1</td><td align="left" valign="middle">Supplier1</td><td align="left" valign="middle">Supplier1</td><td align="left" valign="middle">Rupees</td><td align="left" valign="middle">Sundry Creditor</td><td align="left" valign="middle">Active</td><td><div class="checkbox"> <input class="checkbox_1" type="checkbox" id="cust_check_329" name="cust_check_329" value=""><label for="cust_check_329" class="checkbox_1"></label></div></td></tr></tbody></table>
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
		<input type="hidden" name="mode" id="mode"  />
		<input type="hidden" name="party_id" id="party_id"  />
<input type="hidden" name="sel_columns" id="sel_columns" value="${selectedFields}"/>
<input type="hidden" name="tot_columns" id="tot_columns" value="${totalColumns}"/> 

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


		$.fn.listAccountGroup=function (){
			 $.getJSON('${pageContext.request.contextPath}/json_file/${sessionScope.user_info.clientId}/account_group/${sessionScope.user_info.clientId}_account_group.json', function(data) {
		      		 
				 $.each(data, function(key, val) {
			
					
					 $("#account_group").autocomplete({
					 minLength: 0,
					 source: val,
		
					 select: function( event, ui ) {
					 $("#account_group").val( ui.item.label );
					 $("#account_group_id").val( ui.item.value );
					 return false;
					 }
					 });
			
				});
				 
			   }).fail(function(){$.ajax({ 
		        type: 'POST',
		        url: contextpath+"/RequestHandlerServlet",
		        data: {servlet_name : 'JsonFileCreatorServlet' , callbackmethod_name : 'createJsonFile',name:"account_group",query:"select group_id,group_name from account_group order by group_name"}, 
		        success: function(msg) {
		        	$("#account_group").listAccountGroup();    
		         }           
		    });});
		 };			
	
						 				 
	 $(function(){


		  $('#account_group').listAccountGroup();


		});

}(window.jQuery);
	 
</script>
	</form>
</body>
</html>
          