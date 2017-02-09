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

<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/jquery/mousetrap.js"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/order/buyer-order.js"></script>

<script language="javascript">
	var contextpath = "${pageContext.request.contextPath}";

	function getData(totalColumns,selectedFields){		
		getBuyerOrderData(totalColumns,selectedFields);
		}
	function getBuyerOrderData(totalColumns,selectedFields){
	
		setVal('total_columns_data',totalColumns);
		setVal('selected_columns_data',selectedFields);
		setVal('request_type','Normal');
		setVal('servlet_name','BuyerOrderServlet');
		setVal('callbackmethod_name','doUpdateBuyerOrderColumnPreferences');
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();
	}

</script>

<script>

$(function(){
	$('[data-toggle="tooltip"]').tooltip();
	});
	$(document).ready(function(){
	    $('#filter-dropdown').click(function (e) {
	        e.stopPropagation();
	    });
	});

	
</script>

<style>

/*  common  *//* 
.page-inner{
    margin: 15px;
}
.bootstrap-select {
	width: 150px !important;
}

.has-feedback .form-control {
    margin-right: 25px;
}
.form-control {
    height: 30px;
    border-radius: 2px;
    font-size: 12px;
    border-color: #eaeaea;
    padding-right: 22px;
}
.form-group {
    margin-bottom: 12px;
}
.form-group label {
    margin-bottom: 0px;
}
label {
    font-size: 12px;
}
.help-block{
	margin-top: 0px;
}
.form-control-feedback{
    width: 30px;
    height: 30px;
    line-height: 40px;
} 
.header-row {
    margin-top: 10px;
    margin-bottom: 30px;
    color: #337ab7;
}
.input-group-addon{
    background-color: #f5f5f5;
    border: 1px solid #eaeaea;
    }
    

.btn{
    border-radius: 2px;
    padding: 4px 8px;
	font-size: 13px;
}
 
.dropdown-menu>li>a{
	font-size: 13px;
}
.glyphicon {
    font-size: 11px;
}
.a-icon{
    padding: 8.5px;
    border: 1px solid #eaeaea;
    margin: 0px;
    border-radius: 1px;
}
.span-icon{
    padding: 8px;
    border: 1px solid #eaeaea;
    margin: 0px;
    border-radius: 1px;
}
.abs{
	position: absolute;
	margin-left: 2px;
}       */

/* view page  */
/* 
ul.pagination.pull-left li a {
    padding: 4px 8px;
    display: block;
}

ul.pagination.pull-left li span {
    padding: 0px;
}
.pagination>li:first-child>a, .pagination>li:first-child>span {
    margin-left: 0;
    border-top-left-radius: 2px;
    border-bottom-left-radius: 2px;
}
.pagination>li:last-child>a, .pagination>li:last-child>span {
    border-top-right-radius: 2px;
    border-bottom-right-radius: 2px;
}
ul.pagination.pull-right li a {
    padding: 4px 8px;
}
.dropdown-menu{
	border-radius: 4px;
}
ul.pagination.pull-left{
	padding:0;
}
.paginationstyle {
 	margin:0;
}
ul.pagination.pull-right {
    padding: 0;
}
.margin-right{
	margin-right:5px;
	margin-bottom:5px;
}
.margin-left{
	margin-left:5px;
	margin-bottom:5px;
}
#task-table{
	margin:0px;
}
#orgModal .btn{
	padding: 4px 8px;
} */
</style>

</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form id="bo_form" autocomplete="off">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Order.Transactions.BuyerOrder" name="screen_name" />
				<jsp:param value="2" name="sub_document_type" />
			</jsp:include>
			
			<div class="page-wrapper">
				<div class="page-inner">
					<div class="row header-row">
						<div class="col-sm-12 col-xs-12">
							<div class='col-sm-8'>
								<h3 >Buyer Order</h3>
							</div>
						</div>
					</div>
					
					<div class="row ">

						<div class='col-sm-3'>
							<div class='col-sm-12'>
								<div class='form-group'>
									<label for="bo_buyer_name">Buyer Name</label> 
									<input type="text" class="form-control" name="bo_buyer_name" id="bo_buyer_name" placeholder="Buyer Name Search..." value="${bo_buyer_name}" onkeypress="enterKeyPress(event,'bo_form_apply_filter');" />
									<input type="hidden" name="bo_buyer_id" id="bo_buyer_id" value="${bo_buyer_id}" />
	
								</div>
							</div>
						</div>

						<div class='col-sm-2'>
							<div class='col-sm-12'>
							<div class='form-group'>
								<label for="order_type" >Status</label> 
								<select
									class="form-control " id="order_type" name="order_type"
									onchange="searchBuyerOrder()" >
									<option value="-1" <c:if test="${order_type eq '-1'}">selected="selected"</c:if>>All</option>
									<option value="Open" <c:if test="${order_type eq 'Open'}">selected="selected"</c:if>>Open</option>
									<option value="Canceled" <c:if test="${order_type eq 'Canceled'}">selected="selected"</c:if>>Canceled</option>
									<option value="Closed" <c:if test="${order_type eq 'Closed'}">selected="selected"</c:if>>Closed</option>

								</select>
							</div>
							</div>
						</div>
						<c:if test="${bo_rights.addPermission==1 }">
							<div class='col-sm-3 pull-right '>
								<div class='col-sm-12 pull-right '>
									<div class='form-group pull-right'>
										<a id="bo_form_add_new" name="bo_form_add_new" onclick="addBuyerOrder('n')" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i>&nbsp;New Buyer Order</a>
									</div>
								</div>
							</div>

						</c:if>

					</div>


					<div class="row paginationstyle">
						<div class='col-sm-12'>

							<ul class="pagination pull-left margin-right">

								<li>
								<a href='javascript:showBuyerOrder();' data-toggle="tooltip" title="Refresh">&nbsp;<i class="fa fa-refresh"></i>&nbsp;</a>
								</li>
								<li>
									<span data-toggle="modal" data-target="#orgModal">
										<a href="#" id="bo_form_organize_column" name="bo_form_organize_column" data-toggle="tooltip" onclick="organizeColumns(${subdocument_id});" title="Reorder columns">&nbsp;<i class="fa fa-reorder"></i>&nbsp;</a>
									</span>
								</li>
								<li>
									<a href="#" id="bo_form_customize_screen" onclick="showConfigScreensForId(${screen_id})" data-toggle="tooltip" title="Customization">&nbsp;<i class="fa fa-cog"></i>&nbsp;</a>
								</li>
							</ul>
							<div class="input-group-btn pull-left padding-right">
							
								<div class="btn-group dropdown dropdown-lg">
										<button name="bo_form_filter" id="bo_form_filter" onclick="searchBuyerOrder()" class="btn btn-default" > 
										<span class="glyphicon glyphicon-filter text-primary"></span>&nbsp;Filter</button>
										<button type="button" class="btn btn-default dropdown-toggle  text-primary" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><span class="caret"></span></button>
										<div id="filter-dropdown" class="dropdown-menu dropdown-menu-left">
											<div class="row">
												<c:forEach items="${fixedfields}" var="objFixedfields">

													<c:choose>
															
													<c:when test="${objFixedfields.pageFieldName=='so_date'}">

														<div class='col-sm-6'>
															<div class='form-group'>
																<label>From ${objFixedfields.labelName}</label>
																<div class='input-group date'>
																	<input type="text" class="form-control"
																		name="bo_from_date" id="bo_from_date"
																		placeholder="Select From ${objFixedfields.labelName}"
																		value="${bo_from_date}" /> <span
																		class="input-group-addon"> <span
																		class="glyphicon glyphicon-calendar"></span>
																	</span>
																</div>
																<script type="text/javascript">
			
															      jQuery('#bo_from_date').datepicker({
																		    format: "dd-mm-yyyy"
																		}).on('show', function(e) {
																			var $popup = $('.datepicker');
																			$popup.click(function () { return false; });
																			});
												
											        			</script>
															</div>
														</div>


														<div class='col-sm-6'>
															<div class='form-group'>
																<label>To ${objFixedfields.labelName}</label>
																<div class='input-group date'>
																	<input type="text" class="form-control"
																		name="bo_to_date"
																		placeholder="Select To ${objFixedfields.labelName}"
																		id="bo_to_date" value="${bo_to_date}" /> <span
																		class="input-group-addon"> <span
																		class="glyphicon glyphicon-calendar"></span>
																	</span>
																</div>
																<script type="text/javascript">
			
															      jQuery('#bo_to_date').datepicker({
																		    format: "dd-mm-yyyy"
																		}).on('show', function(e) {
																			var $popup = $('.datepicker');
																			$popup.click(function () { return false; });
																			});
												
											        			</script>
															</div>
														</div>
													</c:when>
													<c:otherwise>
													<c:choose>
																	
													<c:when test="${objFixedfields.pageFieldName=='buyer_po'}">
														<div class='col-sm-6'>
															<div class='form-group'>
																<label for="bo_buyer_po_no">${objFixedfields.labelName}</label> 
																<input
																	type="text" class="form-control" name="bo_buyer_po_no"
																	id="bo_buyer_po_no" value="<c:out value="${bo_buyer_po_no}"/>"
																	placeholder="Select ${objFixedfields.labelName}" /> 
															</div>
														</div>
													</c:when>
													<c:otherwise>
													<c:choose>
													<c:when test="${objFixedfields.pageFieldName=='season_id'}">
														<div class='col-sm-6'>
															<div class='form-group'>
																<label for="bo_season_name">${objFixedfields.labelName}</label> 
																<input
																	type="text" class="form-control" name="bo_season_name"
																	id="bo_season_name" value="${bo_season_name }"
																	placeholder="Enter ${objFixedfields.labelName}" />
																<input
																	type="hidden"name="bo_season_id"
																	id="bo_season_id" value="${bo_season_id }"/>	
															</div>
														</div>
													</c:when>
													<c:otherwise>
													<c:choose>
													<c:when test="${objFixedfields.pageFieldName=='agent_id'}">
														<div class='col-sm-6'>
															<div class='form-group'>
																<label for="bo_agent_name">${objFixedfields.labelName}</label> 
																<input
																	type="text" class="form-control" name="bo_agent_name"
																	id="bo_agent_name" value="${bo_agent_name}"
																	placeholder="Enter ${objFixedfields.labelName}" />
																<input
																	type="hidden" name="bo_agent_id"
																	id="bo_agent_id" value="${bo_agent_id }"/>		
															</div>
														</div>
													</c:when>
													<c:otherwise>
													<c:choose>

													<c:when test="${objFixedfields.pageFieldName=='customer_plan_no'}">
														<div class='col-sm-6'>
															<div class='form-group'>
																<label for="bo_customer_plan_no">${objFixedfields.labelName}</label> 
																<input
																	type="text" class="form-control" name="bo_customer_plan_no"
																	id="bo_customer_plan_no" value="<c:out value="${bo_customer_plan_no }"/>"
																	placeholder="Select ${objFixedfields.labelName}" /> 
															</div>
														</div>
													</c:when>
													
													<c:otherwise>
													<c:choose>

													<c:when test="${objFixedfields.pageFieldName=='special_instruction'}">
													<div class='col-sm-6'>
													<div class='form-group'>
														<label for="bo_special_instruction">${objFixedfields.labelName}</label> 
														<input
															type="text" class="form-control" name="bo_special_instruction"
															id="bo_special_instruction" value="${bo_special_instruction}"
															placeholder="Enter ${objFixedfields.labelName}" />
													</div>
													</div>
													</c:when>
													<c:otherwise>
													<c:choose>

													<c:when test="${objFixedfields.pageFieldName=='packing_labeling'}">
												<div class='col-sm-6'>
													<div class='form-group'>
														<label for="bo_packing_labeling">${objFixedfields.labelName}</label> 
														<input
															type="text" class="form-control" name="bo_packing_labeling"
															id="bo_packing_labeling" value="${bo_packing_labeling}"
															placeholder="Enter ${objFixedfields.labelName}" />
													</div>
												</div>
													</c:when>
													</c:choose>
													</c:otherwise>
													</c:choose>
													</c:otherwise>
													</c:choose>
													</c:otherwise>
													</c:choose>
													</c:otherwise>
													</c:choose>
													</c:otherwise>
													</c:choose>
													</c:otherwise>
													</c:choose>

												</c:forEach>

												

											</div>
											<div class="row">
												<div class='col-sm-6'>
													<button id="bo_form_clear_filter" name="bo_form_clear_filter" type="button" class="btn btn-primary pull-left" onclick="clearBOSearch();">
														<span class="glyphicon glyphicon-remove"></span>&nbsp;Reset
													</button>
												</div>
												<div class='col-sm-6'>
													<button id="bo_form_apply_filter" name="bo_form_apply_filter" type="button" class="btn btn-primary pull-right" onclick="searchBuyerOrder()">
														<span class="glyphicon glyphicon-search"></span>&nbsp;Apply
													</button>
												</div>
											</div>
										</div>
									<!-- </div> -->
								</div>
							</div>


							<!-- Single button -->
							<div class="btn-group pull-right margin-left">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">
									<span class="glyphicon glyphicon-tasks text-primary"></span>&nbsp;
									Bulk Action&nbsp;<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
								 <c:if test="${bo_rights.deletePermission==1 }">
								  	  <li><a href="javaScript:deleteBulkBuyerOrder()">Delete Transactions</a></li>
								  	   <c:if test="${enq_type ne 'Cancelled'}">
								  	   <c:if test="${ind_type ne 'Closed'}">
								  	    <li><a href="javaScript:closeBulkBuyerOrder()">Close Transactions</a></li>
								  	   </c:if>
								  
								    <li><a href="javaScript:cancelBulkBuyerOrder()">Cancel Transactions</a></li>
								    </c:if>
								 </c:if>
								 <c:if test="${bo_rights.printPermission==1 }">
									<li><a href="javaScript:exportBulkBuyerOrderToPDF()">Print Transactions</a></li>
								</c:if>
									<li role="separator" class="divider"></li>
									<li><a href="avaScript:exportSendBulkBuyerOrder()">Send Transactions</a></li>
								</ul>
							</div>
							<ul class="pagination pagination-right margin-left"  id="paging"  ></ul>
							<script>
				
							 var options = {
						  	bootstrapMajorVersion:3,
				            currentPage: '${pc.pageNo}',
				            totalPages: '${pc.pageCount}',
				            size:'normal',
				            alignment:'right',
				            onPageClicked: function(e,originalEvent,type,page){
				            	changeBOPageNo(page);
				            }
				        }

				        $('#paging').bootstrapPaginator(options);
				
				</script>
						</div>
					</div>




					<div class="row ">
						<div class="col-sm-12">
							<div class="col-sm-12 ">
								<div class="table-responsive" id="fill_grid" >
									${bo_grid}
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../common/ColumnPreferences.jsp" />
		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> 
		<input type="hidden" name="request_type" id="request_type" value="${request_type}" /> 
		<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" /> 
		<input type="hidden" name="pageno" id="pageno" value="${pc.pageNo}" /> 
		<input type="hidden" name="total_pages" id="total_pages" value="${pc.pageCount}" /> 
		<input type="hidden" name="header_mode" id="header_mode" /> 
		<input type="hidden" name="bo_id" id="bo_id" />
		<input type="hidden" name="bo_det_id" id="bo_det_id" /> 
		<input type="hidden" name="screenId" id="screenId" /> 
		<input type="hidden" name="invoke_servlet" id="invoke_servlet" value="${invoke_servlet}" />
		<input type="hidden" name="invoke_method" id="invoke_method" value="${invoke_method}" /> 
		<input type="hidden" name="no_type" id="no_type" />
		<input type="hidden" name="view_mode" id="view_mode" />
		
		<input type="hidden" name="selected_columns_data" id="selected_columns_data" />
		<input type="hidden" name="total_columns_data" id="total_columns_data" />

		<!-- for manual sales account configuration start   -->
		<input type="hidden" name="config_option" id="config_option" /> 
		<input type="hidden" name="config_key" id="config_key" />
		<!-- for manual sales account configuration end   -->

		<script>
		  
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
			
		$('#bo_buyer_name').listCustomer({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCustomer&create_new=1",
			  nameField :'#bo_buyer_name',
			  idField:'#bo_buyer_id'
		  });
		  
		$('#bo_agent_name').listAgent({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetAgent&create_new=1",
			  nameField :'#bo_agent_name',
			  idField:'#bo_agent_id'
		  });	
		
		$('#bo_season_name').listSeason({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSeason&create_new=1",
			  nameField :'#bo_season_name',
			  idField:'#bo_season_id'
		  });	
		
		  
	 });
}(window.jQuery);

	 
</script>
		<script>
		
	/* 	$(document).jkey('alt+n',function(){
			
			var thisFormId= $(this).closest("form").attr('id').val();
			alert('#'+thisFormId+"_add_new");
			jQuery('#'+thisFormId+"_add_new").click();
		    alert('You pressed the a key.');
		     
		});*/
		
		var thisFormId= $(this).closest("form").attr('id');
		
		Mousetrap.bind('alt+n', function(e, combo) {
			
			//alert($(this).closest("form"));
			//alert(this.form.id)
			
			alert('#'+thisFormId+"_add_new");
			jQuery('#'+thisFormId+"_add_new").click();
		    console.log(combo); // logs 'ctrl+shift+up'
		});
		
		
			
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
	 

	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>
</body>
</html>
