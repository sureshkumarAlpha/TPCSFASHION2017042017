<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title><fmt:message bundle="${bundle}" key="Title.Title" /></title>

<jsp:include page="../common/Header.jsp" />
<script type="text/javascript" language="javascript"
	src="${pageContext.request.contextPath}/js/masters/masters.js"></script>
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
</head>
<jsp:include page="../common/ValidateUser.jsp" />
<body >
	<form action="" method="post" role="form" id="validate-form"
		class="trans-form" autocomplete="off">
		<div class="wrapper">
			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Admin.Products.Season" name="screen_name" />
			</jsp:include>

 <div class="top-bar">
					<div id="page-wrapper" class="add-top-wrapper">
						<div id="page-inner">

							<div class='row'>
							<div class="col-sm-4">
								<div class="row row-no-margin">
									<h2 class="add-page-header" >Add Season</h2>
								</div>
							
								<div class='row row-no-margin'>
								</div>
								</div>
														
							</div>
				
					<div class="row">
						<div class="col-sm-3">
							<div class='form-group'>
								<label for="customer">Season </label>
								<div class="required-field-block">
									<input class="form-control" id="season_name" maxlength="50" name="season_name"	size="30" type="text" placeholder="Enter Season " value="${season_info.seasonName}" /> 
										<input id="season_id" name="season_id" size="30" type="hidden" value="${season_info.seasonId}" autofocus />
									<div class="required-icon">
										<div class="text">*</div>
									</div>
								</div>
							</div>
						</div>
			
									<div class='col-sm-3'>
									<div class='form-group'>
								<label>Status</label>
								 <select class="form-control" id="is_active" name="is_active">
									<option value="1"
										<c:if test="${season_info.isActive eq 1 }">selected="selected"</c:if>>Active</option>
									<option value="0"
										<c:if test="${season_info.isActive eq 0 }">selected="selected"</c:if>>Inactive</option>
								</select>
							</div>
						
					</div>
					</div>
					</div>
					</div>
					</div>
					
			<br>
			<div id="page-wrapper" class="add-top-wrapper">
						<div id="page-inner">
									<div class="row">
										<div class='col-sm-3'  >
										<div class='form-group'>
										<label for="from_date">From Date</label>
					                 <div class='input-group date'  >
							        <div class="required-field-block">
								    <input type='text' name="from_date" id="from_date"   class="form-control" <c:choose><c:when test="${new_page=='yes'}">value="${from_date}"</c:when><c:otherwise>value="${season_info.fromDate}"</c:otherwise></c:choose>  placeholder="Select From Date" />
								    <%-- <input type="text" class="form-control" name="from_date" id="from_date" placeholder="Select From Date" value="${season_info.fromDate}" onkeypress="enterKeyPress(event,'validateStartAndEndDates(fromDate,toDate)');"/>  --%>
								    </div>
								    <span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								    </span>
					                </div>
					                
					                   <script type="text/javascript">
				
									      jQuery('#from_date').datepicker({
									    	  autoclose: true,
									            format : "dd-mm-yyyy",
									            orientation: 'top'
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
									<div class='col-sm-3'  >
										<div class='form-group'>
										<label for="to_date">To Date</label>
					                 <div class='input-group date'  >
							        <div class="required-field-block">
								    <input type='text' name="to_date" id="to_date"   class="form-control" <c:choose><c:when test="${new_page=='yes'}">value="${to_date}"</c:when><c:otherwise>value="${season_info.toDate}"</c:otherwise></c:choose>  placeholder="Select From Date" />
								    <%-- <input type="text" class="form-control" name="from_date" id="from_date" placeholder="Select To Date" value="${season_info.fromDate}" onkeypress="enterKeyPress(event,'search');"/>  --%>
								   </div>
								    <span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								    </span>
					                </div>
					                
					                   <script type="text/javascript">
				
									      jQuery('#to_date').datepicker({
									    	  autoclose: true,
									            format : "dd-mm-yyyy",
									            orientation: 'top'
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
									</div>
										</div>
					</div>
					<!-- 	</div> -->
					<br>
	<div id="page-wrapper" class="add-top-wrapper">
						<div id="page-inner">
					<div class="row pull-left">
						<div class="col-sm-12 col-xs-12 centered">


							<div class="btn-group dropdown">


								<button name="save" id="save"
									onclick="saveSeasonConfiguration('${mode}','1')"
									class="btn btn-success ladda-button" data-style="expand-right">
									<span class="ladda-label"> <i
										class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save <span
										class="ladda-spinner"></span><span class="ladda-spinner"></span>
								</button>

								<button class="btn btn-success dropdown-toggle"
									data-toggle="dropdown" style="height: 34px;">
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">


									<li><a
										href="javascript:saveSeasonConfiguration('${mode}','2');">Save
											&amp; New</a></li>


									<li><a
										href="javascript:saveSeasonConfiguration('${mode}','3');">Save
											&amp; Close</a></li>


								</ul>
							</div>


							<button type="button" class="btn btn-primary"
								onclick="showSeason()">
								<i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span>
							</button>


						</div>
					</div>



					<div class='row' style="margin-top: 20px;"></div>

				</div>
			</div>

		</div>

		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> <input
			type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
		<input type="hidden" name="pageno" id="pageno" /> <input
			type="hidden" name="request_type" id="request_type" /> <input
			type="submit" name="validation_btn" id="validation_btn"
			style="display: none;" /> <input type="hidden" name="save_type"
			id="save_type" /> <input type="hidden" name="mode" id="mode"
			value="${mode}" />

		<script type="text/javascript">
				$('#validate-form')
					.bootstrapValidator(
							{
								//  live: 'disabled',
								message : <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisValueIsNotValid"/>,
								feedbackIcons : {
									valid : 'glyphicon glyphicon-ok',
									invalid : 'glyphicon glyphicon-remove',
									validating : 'glyphicon glyphicon-refresh'
								},
								fields : {
									season_name : {
										validators : {
											notEmpty : {
												message : 'This field is required',
											},
											stringLength : {
												max : 50,
												message : 'The message must be less than 50 characters long',
											}
										}
									},
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
</body>
</html>