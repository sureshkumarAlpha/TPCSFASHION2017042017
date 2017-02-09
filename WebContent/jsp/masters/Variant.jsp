<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title><fmt:message bundle="${bundle}" key="Title.Title" /></title>

<jsp:include page="../common/Header.jsp" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/masters/masters.js"></script>

<script language="javascript">
	var localizedStrings = {
			SURE_TO_DELETE_GROUP : "<fmt:message bundle="${bundle}" key="Variant.AreYouSureYouToDelete"/>",
		SURE_TO_CHANGE_BULKACTION : "<fmt:message bundle="${bundle}" key="Variant.AreYouSureYouToChangeBulkAction"/>",
		SURE_TO_EDIT_Variant:  "<fmt:message bundle="${bundle}" key="Variant.AreYouSureYouToEdit"/>"
	};
</script>
<style>
.container {
	margin-top: 30px;
}

.filter-col {
	padding-left: 10px;
	padding-right: 10px;
}
</style>
<script language="javascript">
	var contextpath = "${pageContext.request.contextPath}";
</script>
	<script language="javascript">
	var contextpath = "${pageContext.request.contextPath}";

	function getData(selectedFields,totalColumns){		
		getMaterialData(selectedFields,totalColumns);
		}
	function getMaterialData(selectedFields,totalColumns){
		
		//var objPage = document.getElementById('selPageNumber');
		
			pageno = 1;
		
		setVal('pageno',pageno);	
		setVal('sel_columns',selectedFields);
		setVal('tot_columns',totalColumns);
		//setVal('divWidth',iDivWidth);
		setVal('request_type','Normal');
		setVal('servlet_name','VariantServlet');
		setVal('callbackmethod_name','doDisplayVariantAfterColumnOrganized');
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();
	}  

</script>

<script>
	$(function() {
		$('[data-toggle="tooltip"]').tooltip();
	});
	$(document).ready(function() {
		$('#filter-dropdown').click(function(e) {
			//	alert(e);
			e.stopPropagation();
		});
	});
</script>

<style>
.bootstrap-select {
	width: 150px !important;
}

.small {
	width: 144px;
}
</style>

</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form action="" id="tpcslogin" method="post" role="form"
		autocomplete="off">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Admin.Products.Variant" name="screen_name" />
				<jsp:param value="2" name="sub_document_type" />
			</jsp:include>
			<div id="page-wrapper">
				<div id="page-inner">
					<div class="row">
						<div class="col-md-11 col-sm-12 col-xs-8">
							<h2 class="page-header">Variant</h2>
						</div>
					</div>
					<div class="row row-no-margin">

						<div class='col-sm-3'>
							<div class='form-group'>
								<label for="so_customer">Variant Code</label> <input type="text"
									class="form-control" name="variant_code" id="variant_code"
									placeholder="Variant Code Search..." value="${Variant_code}"
									onblur="" onkeyup=""
									onkeypress="enterKeyPress(event,'search');" /> <input
									id="variant_id" name="variant_id" size="30" type="hidden"
									value="${Variant_id}" />
							</div>
						</div>


<c:if test="${Variant_rights.addPermission==1 }">	
						<div class='col-sm-2 pull-right new_tran_btn'>
							<div class='form-group pull-right'>
								<a onclick="addVariant('n')" class="btn btn-success"><i
									class="glyphicon glyphicon-plus-sign"></i>&nbsp;New Variant</a>
							</div>
						</div>

		</c:if>

					</div>


					<div class="row row-no-margin paginationstyle">
						<div class='col-sm-12'>

							<ul class="pagination pull-left">
								<li><a href='javascript:showVariant();' data-toggle="tooltip"
									title="Refresh"> <i class="fa fa-refresh"></i>
								</a></li>
								 <li><span data-toggle="modal" data-target="#orgModal" >
						<a  href="#"  data-toggle="tooltip" onclick="organizeColumns(${subdocument_id});" title="Reorder columns">
						<i class="fa fa-reorder"></i>
						</a> 
						</span>
					</li>
					<li >
						<a  href="#"  onclick="showVariantConfigScreensForId('4')" data-toggle="tooltip" title="Customization">
						<i class="glyphicon glyphicon-cog"></i>
						</a> 
					</li>
								
							</ul>
							<div class="input-group-btn pull-left">
								<div class="btn-group">
									<div class="dropdown dropdown-lg">
										<button type="button" class="btn btn-default dropdown-toggle"
											data-toggle="dropdown" aria-expanded="false">
											<span class="glyphicon glyphicon-filter text-primary"></span>&nbsp;Filter&nbsp;<span
												class="caret"></span>
										</button>
										<div id="filter-dropdown"
											class="dropdown-menu dropdown-menu-left">
											<div class="row">
												<div class='col-sm-6'>
													<div class='form-group'>
														<label for="group">Variant Name</label> <input type="text"
															class="form-control" name="variant_name" id="variant_name"
															value="${Variant_Name}" placeholder="Type Variant Name..." />
													</div>
												</div>

											</div>
											<div class="row">
												<button type="button" class="btn btn-primary pull-left"
													onclick="clearVariantSearch();">
													<span class="glyphicon glyphicon-remove-circle"></span>&nbsp;Reset
												</button>
												<button id="search" type="button"
													class="btn btn-primary pull-right"
													onclick="searchVariantMaster()">
													<span class="glyphicon glyphicon-search"></span>&nbsp;Apply
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>


							<!-- Single button -->
							<div class="btn-group pull-right">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">
									<span class="glyphicon glyphicon-tasks text-primary"></span>&nbsp;
									Bulk Action&nbsp;<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href='javascript:activeBulkVariant(${pc.pageNo},1)'>Active</a></li>
									<li><a href='javascript:activeBulkVariant(${pc.pageNo},2)'>Inactive</a></li>

								</ul>
							</div>
							<ul class="pagination pull-right" id="paging">
							</ul>
							<script>
								var options = {
									bootstrapMajorVersion : 3,
									currentPage : '${pc.pageNo}',
									totalPages : '${pc.pageCount}',
									size : 'normal',
									alignment : 'right',
									onPageClicked : function(e, originalEvent,
											type, page) {
										changeVariantPageNumber(page);
									}
								}

								$('#paging').bootstrapPaginator(options);
							</script>

						</div>
					</div>




					<div class="row table-responsive">
					
					${col_grid}
											
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../common/ColumnPreferences.jsp" />
		<jsp:include page="../common/Footer.jsp" />
		
		<input type="hidden" name="sel_columns" id="sel_columns" value="${selectedFields}"/>
		<input type="hidden" name="tot_columns" id="tot_columns" value="${totalColumns}"/> 
		<input type="hidden" name="servlet_name" id="servlet_name" />
		 <input type="hidden" name="request_type" id="request_type" value="${request_type}" /> 
		<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" /> 
		<input type="hidden" name="pageno" id="pageno" value="${pc.pageNo}" /> 

			<input type="hidden" name="total_pages" id="total_pages" value="${pc.pageCount}" />
			 <input type="hidden" name="screenId" id="screenId" />
			 <input type="hidden" name="invoke_servlet" id="invoke_servlet" value="${invoke_servlet}" /> 
			<input type="hidden" name="invoke_method" id="invoke_method" value="${invoke_method}" /> 
			<input type="hidden" name="no_type" id="no_type" />
			 <input type="hidden" name="mode" id="mode" value="${mode}" />
			 <input type="hidden" name="active_mode" id="active_mode" />



		<script>
			viewTableTextStyle('.table-condensed');

			$('#task-table input:checkbox').click(function() {
				$(this).closest('tr').removeClass('highlight-row');
				if ($(this).is(':checked'))
					$(this).closest('tr').addClass('highlight-row');
			})

			$('#toggle_check_all').click(function(e) {
				var table = $(e.target).closest('table');
				$('td input:checkbox', table).prop('checked', this.checked);
			});

			/* !function ($) {
			
			 $(function(){
			
			 $('#Variant_code').listVariant({
			 url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetVariant",
			 nameField :'#Variant_code',
			 idField:'#Variant_id'
			 });
			
			 });
			 }(window.jQuery); */
		</script>


	</form>
	<%--  <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script> --%>
</body>
</html>
