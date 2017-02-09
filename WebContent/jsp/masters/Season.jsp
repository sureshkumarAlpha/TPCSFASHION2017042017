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
		SURE_TO_DELETE_GROUP : "<fmt:message bundle="${bundle}" key="Color.AreYouSureYouToDelete"/>",
		SURE_TO_CHANGE_BULKACTION : "<fmt:message bundle="${bundle}" key="Color.AreYouSureYouToChangeBulkAction"/>",
		SURE_TO_EDIT_COLOR:  "<fmt:message bundle="${bundle}" key="Color.AreYouSureYouToEdit"/>"
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
				<jsp:param value="Admin.Products.Season" name="screen_name" />
				<jsp:param value="2" name="sub_document_type" />
			</jsp:include>
			<div id="page-wrapper">
				<div id="page-inner">
					<div class="row">
						<div class="col-md-11 col-sm-12 col-xs-8">
							<h2 class="page-header">Season</h2>
						</div>
					</div>
					<div class="row row-no-margin">

						<div class='col-sm-3'>
							<div class='form-group'>
								<label for="so_customer">Season</label> <input type="text"
									class="form-control" name="season_name" id="season_name"
									placeholder="Season Search..." value="${season_name}"
									onblur="" onkeyup="" onkeypress="enterKeyPress(event,'search');" /> 
									
							</div>
						</div>



						<div class='col-sm-2 pull-right new_tran_btn'>
							<div class='form-group pull-right'>
								<a onclick="addSeason('n')" class="btn btn-success"><i
									class="glyphicon glyphicon-plus-sign"></i>&nbsp;New Season</a>
							</div>
						</div>
					</div>
					<div class="row row-no-margin paginationstyle">
						<div class='col-sm-12'>

							<ul class="pagination pull-left">
								<li><a href='javascript:showSeason();' data-toggle="tooltip"
									title="Refresh"> <i class="fa fa-refresh"></i>
								</a></li>

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
										<div class='col-sm-6'  >
										<div class='form-group'>
										<label for="from_date">From Date</label>
					                 <div class='input-group date'  >
							        <div class="required-field-block">
								    <input type='text' name="from_date" id="from_date"   class="form-control" <c:choose><c:when test="${new_page=='yes'}">value="${from_date}"</c:when><c:otherwise>value="${from_date}"</c:otherwise></c:choose>   placeholder="Select From Date" />
								    <%-- <input type="text" class="form-control" name="from_date" id="from_date" placeholder="Select From Date" value="${season_info.fromDate}" onkeypress="enterKeyPress(event,'search');"/>  --%>
								    </div>
								    <span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								    </span>
					                </div>
					                
					                   <script type="text/javascript">
				
									      jQuery('#from_date').datepicker({
												    format: "dd-mm-yyyy"
												}).on({
													changeDate: function(e) {
								             			event.preventDefault();
								             			event.stopPropagation();
								             		// Revalidate the date field
								             			 jQuery('#validate-form').bootstrapValidator('revalidateField', 'from_date');
									        		},
									        		changeMonth: function(e) {
								             			event.preventDefault();
								             			event.stopPropagation();
								        			},
								        			changeYear: function(e) {
								             			event.preventDefault();
								             			event.stopPropagation();
								        			}
							        			});
						
					        		</script>
					        		</div>
					        		</div>
						<div class='col-sm-6'  >
										<div class='form-group'>
										<label for="to_date">To Date</label>
					                 <div class='input-group date'  >
							        <div class="required-field-block">
								    <input type='text' name="to_date" id="to_date"   class="form-control" <c:choose><c:when test="${new_page=='yes'}">value="${to_date}"</c:when><c:otherwise>value="${to_date}"</c:otherwise></c:choose>   placeholder="Select From Date" />
								    <%-- <input type="text" class="form-control" name="from_date" id="from_date" placeholder="Select From Date" value="${season_info.fromDate}" onkeypress="enterKeyPress(event,'search');"/>  --%>
								   </div>
								    <span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								    </span>
					                </div>
					                
					                   <script type="text/javascript">
				
									      jQuery('#to_date').datepicker({
												    format: "dd-mm-yyyy"
												}).on({
													changeDate: function(e) {
								             			event.preventDefault();
								             			event.stopPropagation();
								             		// Revalidate the date field
								             			 jQuery('#validate-form').bootstrapValidator('revalidateField', 'to_date');
									        		},
									        		changeMonth: function(e) {
								             			event.preventDefault();
								             			event.stopPropagation();
								        			},
								        			changeYear: function(e) {
								             			event.preventDefault();
								             			event.stopPropagation();
								        			}
							        			});
						
					        		</script>
					        		</div>
					        		</div>
										<!-- 	</div> -->

											</div>
											<div class="row">
												<button type="button" class="btn btn-primary pull-left"
													onclick="clearSeasonSearch();">
													<span class="glyphicon glyphicon-remove-circle"></span>&nbsp;Reset
												</button>
												<button id="search" type="button"
													class="btn btn-primary pull-right"
													onclick="searchSeason()">
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
									<li><a href='javascript:activeBulkSeason(${pc.pageNo},1)'>Active</a></li>
									<li><a href='javascript:activeBulkSeason(${pc.pageNo},2)'>Inactive</a></li>
									 <li role="separator" class="divider"></li>		
									<li><a href='javascript:deleteBulkSizeRange(${pc.pageNo})'>Delete</a></li>

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
										changeSeasonPageNumber(page);
									}
								}

								$('#paging').bootstrapPaginator(options);
							</script>

						</div>
					</div>




					<div class="row table-responsive">

						<table class="table table-bordered table-condensed table-hover"
							id="task-table">
							<thead>
								<tr class="header">
									<th valign="middle" nowrap="nowrap" align="center"
										class="w-mini first small">Action</th>
									<th valign="middle" nowrap="nowrap" align="center"
										class="w-mini">Season</th>
									<th valign="middle" nowrap="nowrap" align="center"
										class="w-mini">From Date</th>
									<th valign="middle" nowrap="nowrap" align="center"
										class="w-mini">To Date</th>
									<th valign="middle" nowrap="nowrap" align="center"
										class="w-mini">Status</th>
									<th valign="middle" nowrap="nowrap" align="center"
										class="w-mini">
										<div class="checkbox">
											<input class="checkbox_1" type="checkbox"
												id="toggle_check_all" name="toggle_check_all"> <label
												for="toggle_check_all" class="checkbox_1"></label>
										</div>
									</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="k" value="${1 }" />
								<c:forEach var="obj" items="${season_list}">
									<tr class="datarow">
										<td valign="middle" nowrap="nowrap" align="left"><select
											class="form-control" id="select_action" name="select_action"
											onchange="SeasonActionList(this.value,'${obj.seasonId}','${pc.pageNo}')">
												<option selected="selected" value="-1">Action</option>
												<c:if test="${season_rights.editPermission==1 }">
													<option value="2">Edit</option>
												</c:if>
												<c:if test="${season_rights.deletePermission==1 }">
													<option value="1">Delete</option>
												</c:if>
											
										</select></td>
										
										<td>${obj.seasonName}</td>
										<td>${obj.fromDate}</td>
										<td>${obj.toDate}</td>
										
										<c:choose>
											<c:when test="${obj.isActive eq 1}">
												<td nowrap="nowrap" class="dataalignment"><c:out
														value="Active" /></td>
											</c:when>
											<c:otherwise>
												<td nowrap="nowrap" class="dataalignment"><c:out
														value="Inactive" /></td>
											</c:otherwise>
										</c:choose>
										<td><div class="checkbox">
												<input class="checkbox_1" type="checkbox"
													id="mat_check_${obj.seasonId}" name="bulkSeasonId"
													value="${obj.seasonId}" /><label
													for="mat_check_${obj.seasonId}" class="checkbox_1"></label>
											</div></td>


									</tr>
									<c:set var="k" value="${k+1}" />
								</c:forEach>

								<c:if test="${k < 15}">
									<c:set var="k" value="${15-k}" />
									<c:forEach begin="1" end="${k}" varStatus="loop">
										<tr>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../common/ColumnPreferences.jsp" />
		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> <input
			type="hidden" name="request_type" id="request_type"
			value="${request_type}" /> <input type="hidden"
			name="callbackmethod_name" id="callbackmethod_name" /> <input
			type="hidden" name="pageno" id="pageno" value="${pc.pageNo}" /> <input
			type="hidden" name="total_pages" id="total_pages"
			value="${pc.pageCount}" /> <input type="hidden" name="screenId"
			id="screenId" /> <input type="hidden" name="invoke_servlet"
			id="invoke_servlet" value="${invoke_servlet}" /> <input
			type="hidden" name="invoke_method" id="invoke_method"
			value="${invoke_method}" /> <input type="hidden" name="no_type"
			id="no_type" /> <input type="hidden" name="mode" id="mode"
			value="${mode}" /> <input type="hidden" name="active_mode"
			id="active_mode" />
			<input id="season_id" name="season_id" size="30" type="hidden" value="${season_info.seasonId}"/>



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

			</script>
		<script>
$('#validate-form').bootstrapValidator({
	
	  message: 'This value is not valid',
	  feedbackIcons: {
	      valid: 'glyphicon glyphicon-ok',
	      invalid: 'glyphicon glyphicon-remove',
	      validating: 'glyphicon glyphicon-refresh'
	  },
	/*   excluded: ':disabled', */
	  fields: {
		  from_date: {
              validators: {
                  notEmpty: {
                      message: 'This field is required'
                  },
                  date: {
                      format: 'DD-MM-YYYY',
                      message: 'The date is not a valid'
                  }
              }
          },
          to_date: {
              validators: {
                  notEmpty: {
                      message: 'This field is required'
                  },
                  date: {
                      format: 'DD-MM-YYYY',
                      message: 'The date is not a valid'
                  }
              }
          }
	  }
});
</script>
	</form>
<%-- 	 <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script> --%>
</body>
</html>
