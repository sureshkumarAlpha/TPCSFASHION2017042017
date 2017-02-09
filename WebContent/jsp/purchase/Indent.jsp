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
<script type="text/javascript" language="javascript"
	src="${pageContext.request.contextPath}/js/purchase/indent.js"></script>

<script language="javascript">
	var contextpath = "${pageContext.request.contextPath}";

	function getData(selectedFields,totalColumns){		
		getIndentData(selectedFields,totalColumns);
		}
	function getIndentData(selectedFields,totalColumns){
		
		
		
		pageno = 1;
	
		setVal('request_type','Normal');
		setVal('servlet_name','IndentServlet');
		setVal('callbackmethod_name','doUpdateIndentColumnPreferences');
		document.forms[0].action=contextpath+"/RequestHandlerServlet?pageno="+pageno+"&selected_columns="+selectedFields+"&total_columns="+totalColumns;
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
	<form id="ind_form" autocomplete="off">
		<div id="wrapper">


	<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Order.Transactions.Indent" name="screen_name" />
				<jsp:param value="2" name="sub_document_type" />
			</jsp:include>
			
			<div class="page-wrapper">
				<div class="page-inner">
					<div class="row header-row">
						<div class="col-sm-12 col-xs-12">
							<div class='col-sm-8'>
								<h3 >Indent</h3>
							</div>
						</div>
					</div>

						<div class="row ">
						<div class='col-sm-3'>
						<div class='col-sm-12'>
							<div class='form-group'>
								<label for="indent_name">Supplier Name</label> 
								<input type="text" class="form-control" name="supplier_name" id="supplier_name" placeholder="Supplier Search..." value="${supplier_name}" onkeypress="enterKeyPress(event,'search');" />
								<input type="hidden" name="supplier_id" id="supplier_id" value="${supplier_id}" />
								</div>
							</div>
						</div>

						<div class='col-sm-3'>
						<div class='col-sm-12'>
							<div class='form-group'>
								<label for="quote_type" style="display: block;">Status</label> <select
									class="form-control selectpicker" id="ind_type" name="ind_type"	onchange="searchIndent()" style="width: 200px;">
									<option value="-1" <c:if test="${ind_type eq '-1'}">selected="selected"</c:if>>All</option>
									<option value="Open" <c:if test="${ind_type eq 'Open'}">selected="selected"</c:if>>Open</option>
									<option value="Cancelled" <c:if test="${ind_type eq 'Cancelled'}">selected="selected"</c:if>>Cancelled</option>
									<option value="Closed" <c:if test="${ind_type eq 'Closed'}">selected="selected"</c:if>>Closed</option>
								</select>
							</div>
						</div>
						</div>
						
						
						
						<c:if test="${indent_rights.addPermission==1 }">
							<div class='col-sm-3 pull-right '>
								<div class='col-sm-12 pull-right '>
									<div class='form-group pull-right'>
										<a id="bo_form_add_new" name="bo_form_add_new" onclick="addIndent('n')" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i>&nbsp;New Indent</a>
									</div>
								</div>
							</div>

						</c:if>
						

					</div>


					<div class="row paginationstyle">
						<div class='col-sm-12'>

							<ul class="pagination pull-left margin-right">

								<li>
								<a href='javascript:showIndent();' data-toggle="tooltip" title="Refresh">&nbsp;<i class="fa fa-refresh"></i>&nbsp;</a>
								</li>
								<li>
									<span data-toggle="modal" data-target="#orgModal">
										<a href="#" data-toggle="tooltip" onclick="organizeColumns(${subdocument_id});" title="Reorder columns">&nbsp;<i class="fa fa-reorder"></i>&nbsp;</a>
									</span>
								</li>
								<li>
									<a href="#" onclick="showConfigScreensForId(${screenId})" data-toggle="tooltip" title="Customization">&nbsp;<i class="glyphicon glyphicon-cog"></i>&nbsp;</a>
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
															
													<c:when test="${objFixedfields.pageFieldName=='indent_date'}">

														<div class='col-sm-6'>
															<div class='form-group'>
																<label>From ${objFixedfields.labelName}</label>
																<div class='input-group date'>
																	<input type="text" class="form-control"
																		name="indent_from_date" id="indent_from_date"
																		placeholder="Select From ${objFixedfields.labelName}"
																		value="${indent_from_date}" /> <span
																		class="input-group-addon"> <span
																		class="glyphicon glyphicon-calendar"></span>
																	</span>
																</div>
																<script type="text/javascript">
			
															      jQuery('#indent_from_date').datepicker({
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
																		name="indent_to_date"
																		placeholder="Select To ${objFixedfields.labelName}"
																		id="indent_to_date" value="${indent_to_date}" /> <span
																		class="input-group-addon"> <span
																		class="glyphicon glyphicon-calendar"></span>
																	</span>
																</div>
																<script type="text/javascript">
			
															      jQuery('#indent_to_date').datepicker({
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
																	
													<c:when test="${objFixedfields.pageFieldName=='indent_no'}">
														<div class='col-sm-6'>
															<div class='form-group'>
																<label for="indent_no">${objFixedfields.labelName}</label> 
																<input
																	type="text" class="form-control" name="indent_no"
																	id="indent_no" value="<c:out value="${indent_no}"/>"
																	placeholder="Select ${objFixedfields.labelName}" /> 
															</div>
														</div>
													</c:when>
													<c:otherwise>
													<c:choose>
													<c:when test="${objFixedfields.pageFieldName=='indent_type'}">
														<div class='col-sm-6'>
															<div class='form-group'>
																<label for="indent_type">${objFixedfields.labelName}</label> 
																<input
																	type="text" class="form-control" name="indent_type"
																	id="indent_type" value="<c:out value="${indent_type}"/>"
																	placeholder="Select ${objFixedfields.labelName}" /> 
															</div>
														</div>
													</c:when>
													<c:otherwise>
													<c:choose>
													<c:when test="${objFixedfields.pageFieldName=='department'}">
														<div class='col-sm-6'>
															<div class='form-group'>
																<label for="indent_type">${objFixedfields.labelName}</label> 
																<input
																	type="text" class="form-control" name="deptartment"
																	id="deptartment" value="<c:out value="${deptartment}"/>"
																	placeholder="Select ${objFixedfields.labelName}" /> 
															</div>
														</div>
													</c:when>
													<c:otherwise>
													<c:choose>

													<c:when test="${objFixedfields.pageFieldName=='remarks'}">
														<div class='col-sm-6'>
													<div class='form-group'>
														<label for="remarks">Remarks</label> 
														<input
															type="text" class="form-control" name="remarks"
															id="remarks" value="${remarks}"
															placeholder="Enter Remarks" />
													</div>
												</div>
													</c:when>
													
													<c:otherwise>
													<c:choose>

													<c:when test="${objFixedfields.pageFieldName=='other_ref'}">
														<div class='col-sm-6'>
													<div class='form-group'>
														<label for="other_ref">Other Reference</label> 
														<input
															type="text" class="form-control" name="other_ref"
															id="other_ref" value="${other_ref}"
															placeholder="Enter Other Reference" />
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

												</c:forEach>

											</div>
											<div class="row">
												<div class='col-sm-6'>
													<button id="bo_form_clear_filter" name="bo_form_clear_filter" type="button" class="btn btn-primary pull-left" onclick="clearINDSearch();">
														<span class="glyphicon glyphicon-remove"></span>&nbsp;Reset
													</button>
												</div>
												<div class='col-sm-6'>
													<button id="bo_form_apply_filter" name="bo_form_apply_filter" type="button" class="btn btn-primary pull-right" onclick="searchIndent()">
														<span class="glyphicon glyphicon-search"></span>&nbsp;Apply
													</button>
												</div>
											</div>
										</div>
								</div>
							</div>
							

							<!-- Single button -->
							<div class="btn-group pull-right margin-left">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">
									<span class="glyphicon glyphicon-tasks text-primary"></span>&nbsp;Bulk Action&nbsp;<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
								 <c:if test="${indent_rights.deletePermission==1 }">
								  	  <li><a href="javaScript:deleteBulkIndent()">Delete Transactions</a></li>
								  	   <c:if test="${enq_type ne 'Cancelled'}">
								  	   <c:if test="${ind_type ne 'Closed'}">
								  	    <li><a href="javaScript:closeBulkIndent()">Close Transactions</a></li>
								  	   </c:if>
								  
								    <li><a href="javaScript:cancelBulkIndent()">Cancel Transactions</a></li>
								    </c:if>
								 </c:if>
									<li><a href="javaScript:exportBulkIndentToPDF()">Print Transactions</a></li>
									<li role="separator" class="divider"></li>
									<li><a href="avaScript:exportSendBulkIndent()">Send Transactions</a></li>
								</ul>
							</div>
							
							
						
							
							
							
							<ul class="pagination pagination-right"  id="paging"  ></ul>
				 <script>
				
							var options = {
						  	bootstrapMajorVersion:3,
				            currentPage: '${pc.pageNo}',
				            totalPages: '${pc.pageCount}',
				            size:'normal',
				            alignment:'right',
				            onPageClicked: function(e,originalEvent,type,page){
				            	changeINDPageNo(page);
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
									${ind_grid}
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
		<input type="hidden" name="indent_id" id="indent_id" />
		<input type="hidden" name="indent_det_id" id="indent_det_id" /> 
		<input type="hidden" name="screenId" id="screenId" /> 
		<!-- <input type="hidden" name="selectedScreenId" id="selectedScreenId"  /> -->
		<input type="hidden" name="invoke_servlet" id="invoke_servlet" value="${invoke_servlet}" />
		<input type="hidden" name="invoke_method" id="invoke_method" value="${invoke_method}" /> 
		<input type="hidden" name="no_type" id="no_type" />
		<input type="hidden" name="view_mode" id="view_mode" />

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
			
		$('#supplier_name').listCustomer({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCustomer&create_new=0",
			  nameField :'#supplier_name',
			  idField:'#supplier_id'
		  });
		  
		$('#indent_type').listIndentType({
			 url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetIndentType&create_new=0",
			 nameField : '#indent_type'
		}) ;
		 
		  
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
	 

	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>
</body>
</html>
