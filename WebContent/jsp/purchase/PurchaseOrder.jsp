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
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/purchase/purchase-order.js"></script>

<script language="javascript">
	var contextpath = "${pageContext.request.contextPath}";

	function getData(selectedFields,totalColumns){		
			getPurchaseOrderData(selectedFields,totalColumns);
		}
	function getPurchaseOrderData(selectedFields,totalColumns){
		pageno = 1;
		setVal('total_columns_data',totalColumns);
		setVal('selected_columns_data',selectedFields);
		setVal('request_type','Normal');
		setVal('servlet_name','PurchaseOrderServlet');
		setVal('callbackmethod_name','doDisplayPurchaseOrderAfterColumnOrganized');
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
.bootstrap-select {
	width: 150px !important;
}
</style>

</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form id="bo_form" autocomplete="off">
		
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Order.Transactions.PurchaseOrder"
					name="screen_name" />
				<jsp:param value="2" name="sub_document_type" />
			</jsp:include>
			
			<div class="page-wrapper">
				<div class="page-inner">
					<div class="row header-row">
						<div class="col-sm-12 col-xs-12">
							<div class='col-sm-8'>
								<h3 >Purchase Order</h3>
							</div>
						</div>
					</div>
					<div class="row ">

						<div class='col-sm-3'>
							<div class='col-sm-12'>
								<div class='form-group'>
									<label for="po_buyer_name">Buyer Name</label> 
									<input type="text" class="form-control" name="po_buyer_name" id="po_buyer_name"
										placeholder="Buyer Name Search..." value="${po_buyer_name}"
										onkeypress="enterKeyPress(event,'search');" /> 
										<input type="hidden" name="po_buyer_id" id="po_buyer_id" value="${po_buyer_id}" />
	
								</div>
							</div>
						</div>

						<div class='col-sm-2'>
							<div class='col-sm-12'>
								<div class='form-group'>
									<label for="order_type" style="display: block;">Status</label> 
									<select class="form-control " id="order_type" name="order_type" onchange="searchPurchaseOrder()" >
										<option value="-1" <c:if test="${order_type eq '-1'}">selected="selected"</c:if>>All</option>
										<option value="Open" <c:if test="${order_type eq 'Open'}">selected="selected"</c:if>>Open</option>
										<option value="Canceled" <c:if test="${order_type eq 'Canceled'}">selected="selected"</c:if>>Canceled</option>
										<option value="Closed" <c:if test="${order_type eq 'Closed'}">selected="selected"</c:if>>Closed</option>
	
									</select>
								</div>
							</div>
						</div>
						<c:if test="${po_rights.addPermission==1 }">

							<div class='col-sm-3 pull-right '>
								<div class='col-sm-12 pull-right '>
									<div class='form-group pull-right'>
										<a onclick="addPurchaseOrder('n')" class="btn btn-success"> <i class="glyphicon glyphicon-plus-sign"></i>&nbsp;New Purchase Order</a>
									</div>
								</div>
							</div>

						</c:if>

					</div>


					<div class="row paginationstyle">
						<div class='col-sm-12'>

							<ul class="pagination pull-left margin-right">

								<li><a href='javascript:showPurchaseOrder();' data-toggle="tooltip" title="Refresh">&nbsp;<i class="fa fa-refresh"></i>&nbsp;</a></li>
								<li>
									<span data-toggle="modal" data-target="#orgModal"> 
										<a href="#" data-toggle="tooltip" onclick="organizeColumns(${subdocument_id});" title="Reorder columns">&nbsp;<i class="fa fa-reorder"></i>&nbsp;</a>
									</span>
								</li>
								<li><a href="#" onclick="showConfigScreensForId(${screenId})" data-toggle="tooltip" title="Customization">&nbsp;<i class="glyphicon glyphicon-cog"></i>&nbsp;</a></li>
							</ul>
							
							<div class="input-group-btn pull-left padding-right">
								<div class="btn-group">
									<div class="btn-group dropdown dropdown-lg">
										<button name="bo_form_filter" id="bo_form_filter" onclick="searchPurchaseOrder()" class="btn btn-default" > 
										<span class="glyphicon glyphicon-filter text-primary"></span>&nbsp;Filter</button>
										<button type="button" class="btn btn-default dropdown-toggle  text-primary" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><span class="caret"></span></button>
										<div id="filter-dropdown"
											class="dropdown-menu dropdown-menu-left">
											<div class="row">
												<c:forEach items="${fixedfields}" var="objFixedfields">

													<c:choose>
													<c:when test="${objFixedfields.pageFieldName=='po_date'}">

														<div class='col-sm-6'>
															<div class='form-group'>
																<label>From ${objFixedfields.labelName}</label>
																<div class='input-group date'>
																	<input type="text" class="form-control"
																		name="po_from_date" id="po_from_date"
																		placeholder="Select From ${objFixedfields.labelName}"
																		value="${po_from_date}" /> <span
																		class="input-group-addon"> <span
																		class="glyphicon glyphicon-calendar"></span>
																	</span>
																</div>
																<script type="text/javascript">
			
															      jQuery('#po_from_date').datepicker({
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
																		name="po_to_date"
																		placeholder="Select To ${objFixedfields.labelName}"
																		id="po_to_date" value="${po_to_date}" /> <span
																		class="input-group-addon"> <span
																		class="glyphicon glyphicon-calendar"></span>
																	</span>
																</div>
																<script type="text/javascript">
			
															      jQuery('#po_to_date').datepicker({
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
																	
													<c:when test="${objFixedfields.pageFieldName=='po_no'}">

														<div class='col-sm-6'>
															<div class='form-group'>
																<label for="po_po_no">${objFixedfields.labelName}</label> 
																<input
																	type="text" class="form-control" name="po_po_no"
																	id="po_po_no" value="<c:out value="${po_po_no}"/>"
																	placeholder="Select ${objFixedfields.labelName}" />
															</div>
														</div>
														</c:when>
													<c:otherwise>
													<c:choose>
													<c:when test="${objFixedfields.pageFieldName=='purchase_type'}">

														<div class='col-sm-6'>
															<div class='form-group'>
																<label for="po_purchase_type">${objFixedfields.labelName}</label> 
																<input
																	type="text" class="form-control" name="po_purchase_type"
																	id="po_purchase_type" value="<c:out value="${po_purchase_type}"/>"
																	placeholder="Select ${objFixedfields.labelName}" />
															</div>
														</div>
														</c:when>
													<c:otherwise>
													<c:choose>
													<c:when test="${objFixedfields.pageFieldName=='season_id'}">

														<div class='col-sm-6'>
															<div class='form-group'>
																<label for="po_season_name">${objFixedfields.labelName}</label> 
																<input
																	type="text" class="form-control" name="po_season_name"
																	id="po_season_name" value="<c:out value="${season_name}"/>"
																	placeholder="Select ${objFixedfields.labelName}" />
																<input type="hidden" name="po_season_id" id="po_season_id" value="<c:out value="${po_season_id}"/>" />
															</div>
														</div>
													</c:when>
													<c:otherwise>
													<c:choose>
													<c:when test="${objFixedfields.pageFieldName=='other_ref'}">

														<div class='col-sm-6'>
															<div class='form-group'>
																<label for="po_other_ref">${objFixedfields.labelName}</label> 
																<input type="text" class="form-control" name="po_other_ref"
																	id="po_other_ref" value="<c:out value="${po_other_ref}"/>"
																	placeholder="Select ${objFixedfields.labelName}" />
																
															</div>
														</div>
													</c:when>
													<c:otherwise>
													<c:choose>
													<c:when test="${objFixedfields.pageFieldName=='remark'}">

														<div class='col-sm-6'>
															<div class='form-group'>
																<label for="po_hremark">${objFixedfields.labelName}</label>
																<input type="text" class="form-control"
																	name="po_hremark" id="po_hremark"
																	value="<c:out value="${po_hremark}"/>"
																	placeholder="Select ${objFixedfields.labelName}" />

															</div>
														</div>
													</c:when>
													<c:otherwise>
													<c:choose>
													<c:when test="${objFixedfields.pageFieldName=='payment_terms'}">

														<div class='col-sm-6'>
															<div class='form-group'>
																<label for="po_payment_terms">${objFixedfields.labelName}</label>
																<input type="text" class="form-control"
																	name="po_payment_terms"
																	id="po_payment_terms"
																	value="<c:out value="${po_payment_terms}"/>"
																	placeholder="Select ${objFixedfields.labelName}" />

															</div>
														</div>
													</c:when>

													<c:otherwise>
													<c:choose>
													<c:when test="${objFixedfields.pageFieldName=='delivery_terms'}">

														<div class='col-sm-6'>
															<div class='form-group'>
																<label for="po_delivery_terms">${objFixedfields.labelName}</label>
																<input type="text" class="form-control"
																	name="po_delivery_terms"
																	id="po_delivery_terms"
																	value="<c:out value="${po_delivery_terms}"/>"
																	placeholder="Select ${objFixedfields.labelName}" />

															</div>
														</div>
													</c:when>

													<c:otherwise>
													<c:choose>
													<c:when test="${objFixedfields.pageFieldName=='ship_to_address'}">

														<div class='col-sm-6'>
															<div class='form-group'>
																<label for="po_ship_to_address">${objFixedfields.labelName}</label>
																<input type="text" class="form-control"
																	name="po_ship_to_address"
																	id="po_ship_to_address"
																	value="<c:out value="${po_ship_to_address}"/>"
																	placeholder="Select ${objFixedfields.labelName}" />

															</div>
														</div>
													</c:when>

													<c:otherwise>
													<c:choose>
													<c:when test="${objFixedfields.pageFieldName=='internal_memo'}">

														<div class='col-sm-6'>
															<div class='form-group'>
																<label for="po_internal_memo">${objFixedfields.labelName}</label>
																<input type="text"
																	class="form-control"
																	name="po_internal_memo"
																	id="po_internal_memo"
																	value="<c:out value="${po_internal_memo}"/>"
																	placeholder="Select ${objFixedfields.labelName}" />

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
													</c:otherwise>
													</c:choose>
													</c:otherwise>
													</c:choose>
													</c:otherwise>
													</c:choose>
														
												</c:forEach>

											<c:if test="${dynamicfields.size() > 0 }">
											<div class='col-sm-6'>
	                							<div class='form-group'>
				                        			<select name="dynamic_field_1" id="dynamic_field_1" class="form-control">	
				                        				<option value="-1"><--Select Dynamic Field--></option>	                       
			                        					<c:forEach var="dynamicfieldObj" items="${dynamicfields}">		                        						                        					
				                        				<option value="<c:out value="${dynamicfieldObj.dbFieldName}_${seperator}_${dynamicfieldObj.tableId}"/>"><c:out value="${dynamicfieldObj.labelName}"/></option>
		                        						</c:forEach>	
			                        				</select>
				                        			<input type="text" class="form-control" name="dynamic_field_val_1" id="dynamic_field_val_1"/>
	                							</div>
           									</div>
											
											<div class='col-sm-6'>
	                							<div class='form-group'>
				                        			<select name="dynamic_field_2" id="dynamic_field_2" class="form-control">	
				                        				<option value="-1"><--Select Dynamic Field--></option>	                       
			                        					<c:forEach var="dynamicfieldObj" items="${dynamicfields}">		                        						                        					
				                        				<option value="<c:out value="${dynamicfieldObj.dbFieldName}_${seperator}_${dynamicfieldObj.tableId}"/>"><c:out value="${dynamicfieldObj.labelName}"/></option>
		                        						</c:forEach>	
			                        				</select>
				                        			<input type="text" class="form-control" name="dynamic_field_val_2" id="dynamic_field_val_2"/>
	                							</div>
           									</div>
           									
           									<div class='col-sm-6'>
	                							<div class='form-group'>
				                        			<select name="dynamic_field_3" id="dynamic_field_3" class="form-control">	
				                        				<option value="-1"><--Select Dynamic Field--></option>	                       
			                        					<c:forEach var="dynamicfieldObj" items="${dynamicfields}">		                        						                        					
				                        				<option value="<c:out value="${dynamicfieldObj.dbFieldName}_${seperator}_${dynamicfieldObj.tableId}"/>"><c:out value="${dynamicfieldObj.labelName}"/></option>
		                        						</c:forEach>	
			                        				</select>
				                        			<input type="text" class="form-control" name="dynamic_field_val_3" id="dynamic_field_val_3"/>
	                							</div>
           									</div>
											
											</c:if>
											
											</div>
											
											<div class="row">
												<div class='col-sm-6'>
													<button type="button" class="btn btn-primary pull-left" onclick="clearPOSearch();">
														<span class="glyphicon glyphicon-remove-circle"></span>&nbsp;Reset
													</button>
												</div>
												<div class='col-sm-6'>
													<button id="search" type="button" class="btn btn-primary pull-right" onclick="searchPurchaseOrder()">
														<span class="glyphicon glyphicon-search"></span>&nbsp;Apply
													</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>


							<!-- Single button -->
							<div class="btn-group pull-right margin-left">
								<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									<span class="glyphicon glyphicon-tasks text-primary"></span>&nbsp; Bulk Action&nbsp;<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
								 <c:if test="${po_rights.deletePermission==1 }">
								  	  <li><a href="javaScript:deleteBulkPurchaseOrder()">Delete Transactions</a></li>
								  	   <c:if test="${enq_type ne 'Cancelled'}">
								  	   <c:if test="${ind_type ne 'Closed'}">
								  	    <li><a href="javaScript:closeBulkPurchaseOrder()">Close Transactions</a></li>
								  	   </c:if>
								  
								    <li><a href="javaScript:cancelBulkPurchaseOrder()">Cancel Transactions</a></li>
								    </c:if>
								 </c:if>
								 <c:if test="${po_rights.printPermission==1 }">
									<li><a href="javaScript:exportBulkPurchaseOrderToPDF()">Print Transactions</a></li>
								</c:if>
									<li role="separator" class="divider"></li>
									<li><a href="avaScript:exportSendBulkPurchaseOrder()">Send Transactions</a></li>
								</ul>
							</div>
							<ul class="pagination pagination-right" id="paging"></ul>
							<script>
				
							 var options = {
						  	bootstrapMajorVersion:3,
				            currentPage: '${pc.pageNo}',
				            totalPages: '${pc.pageCount}',
				            size:'normal',
				            alignment:'right',
				            onPageClicked: function(e,originalEvent,type,page){
				            	changePOPageNo(page);
				            }
				        }

				        $('#paging').bootstrapPaginator(options);
				
					</script>
						</div>
					</div>

					<div class="row ">
						<div class="col-sm-12">
							<div class="col-sm-12 ">
								<div class="table-responsive" id="fill_grid">
									<div id="fill_grid">${po_grid}</div>
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
		<input type="hidden" name="po_id" id="po_id" />
		<input type="hidden" name="po_det_id" id="po_det_id" /> 
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
			
		$('#po_buyer_name').listCustomer({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCustomer&create_new=0",
			  nameField :'#po_buyer_name',
			  idField:'#po_buyer_id'
		  });
		
		$('#po_season_name').listSeason({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSeason&create_new=0",
			  nameField :'#po_season_name',
			  idField:'#po_season_id'
		  });	
		
			
		  
	 });
}(window.jQuery);

	 
</script>
		<script>
			
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


	<script type="text/javascript" language="javascript"
		src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>
</body>
</html>
