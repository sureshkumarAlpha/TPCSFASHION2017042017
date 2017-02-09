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


<!-- <style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}

</style> -->
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form action="" id="validate-form" method="post" role="form"
		autocomplete="off">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Admin.Products.AddSizeRange" name="screen_name" />
			</jsp:include>


			<div class="top-bar">
				<div class="row">
					<div class="col-md-11 col-sm-8 col-xs-12">
						<h2 class="add-page-header">Size Range</h2>
					</div>
				</div>
				<div id="page-wrapper" class="add-top-wrapper">
					<div id="page-inner">

						<div class="row">
						 <div class="col-sm-3">
							<div class='form-group'>
								<label for="customer">Size Range</label>
								<div class="required-field-block">
									<input class="form-control ui-autocomplete-input" id="size_range" name="size_range" maxlength="50"
										size="30" type="text" placeholder="Enter Size Range"
										value="${size_range}" /> 
									<div class="required-icon">
										<div class="text">*</div>
									</div>
								</div>
							</div>		
						</div>
						
							
							<div class="row">
								<div class='col-sm-3'>
									<div class='form-group'>
										<label for="applicable_to">Applicable To</label>
										<div class="required-field-block">
											<select class="form-control" id="applicable_to" name="applicable_to" maxlength="30">
											<%-- <option value="-1" <c:if test="${applicable_to eq -1 }">selected="selected"</c:if>>--Select--</option> --%>
												<option value="1" <c:if test="${applicable_to eq 1 }">selected="selected"</c:if>>Products Only</option>
												<option value="2" <c:if test="${applicable_to eq 2 }">selected="selected"</c:if>>Material Only</option>
												<option value="3" <c:if test="${applicable_to eq 3 }">selected="selected"</c:if>>Both</option>
											</select>
											<div class="required-icon">
												<div class="text">*</div>
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>
						<div class="row">
						<div class='col-sm-3'>
							<div class='form-group'>
								<label>Status</label> <select class="form-control"
									id="is_active" name="is_active">
									<option value="1"
										<c:if test="${is_active eq 1 }">selected="selected"</c:if>>Active</option>
									<option value="0"
										<c:if test="${is_active eq 0 }">selected="selected"</c:if>>Inactive</option>
								</select>
							</div>
						</div> 
						</div>
						</div>
					</div>

					<div id="page-wrapper" style="margin-top: 0;">
						<div id="page-inner">
							<div class='row'>
								<div class='row'>
									<lable id="req_err_msg"
										style="color: #a94442;margin-left: 440px;"> </lable>
								</div>

								<div class='row row-no-margin'>
									<div class="col-sm-8" id="size_range_group_row_out">

										<div class="row row-no-margin">
											<div class="col-sm-3" id="header">
												<label>SI No</label>
											</div>
											<div class="col-sm-4" id="header">
												<label>Size</label>
											</div>
											<div class="col-sm-3" id="header">
												<label>Active</label>
											</div>
										<div class="col-sm-2" id="header">
												<label>&nbsp;</label>
											</div>
										</div>

										<c:set var="k" value="${0}" />

										<c:forEach var="obj" items="${sizeRangeInfo}">
											<div class="row row-no-margin linerow size_range_row"
												id="size_range_row_${k}">
												<div class="col-sm-3">
													<div class="form-group">
														<input class="form-control" id="si_no_${k}"
															name="si_no_${k}" value="${obj.slNo}"
															size="30" type="text" placeholder="Enter Sl no" />
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
													<div class="required-field-block">
														<input class="form-control" id="size_name_${k}" name="size_name_${k}" value="${obj.sizeName}" size="30" type="text" placeholder="Enter Size" readonly />
														<input type="hidden" id="size_id_${k}" name="size_id_${k}" value="${obj.sizeId}" size="30" />
														<div class="required-icon">
										<div class="text">*</div>
									</div>
								</div>
														</div>
												</div>
												
												<div class='col-sm-3'>
													<div class='form-group'>
													<select class="form-control"
									id="is_active_det_${k}" name="is_active_det_${k}" >
									<option value="1"
										<c:if test="${obj.isActivedet eq 1 }">selected="selected"</c:if>>Yes</option>
									<option value="2"
										<c:if test="${obj.isActivedet eq 2 }">selected="selected"</c:if>>No</option>
								</select>
													</div>
												</div>
												<div class="col-sm-2 linerow-icon">
													<a href="javascript:addAnotherLineForSizeRange('si_no_${k}')"
														data-toggle="tooltip" title="Add"><span
														class="glyphicon glyphicon-plus text-primary "></span></a>
														 <%-- <a	href="javascript:deleteSizeRow(document.getElementById('size_range_id_${k}').value,${size_range_id})"
														data-toggle="tooltip" title="Delete"><span
														class="glyphicon glyphicon-trash icon-delete"></span></a> --%>
												</div>
											</div>
											<c:set var="k" value="${k+1}" />
										</c:forEach>
									<!-- 	 1 row -->
									
										<c:if test="${mode eq 'n'}">
											<div class="row row-no-margin linerow size_range_row"
												id="size_range_row_0">
												<div class="col-sm-3">
													<div class="form-group">
														<input class="form-control" id="si_no_0"
															name="si_no_${k}" value="10"
															size="30" type="text" placeholder="Enter Sl no" />
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
													<div class="required-field-block">
														<input class="form-control" id="size_name_0"
															name="size_name_0" value="${obj.sizeName}"
															size="30" type="text" placeholder="Enter Size" />
														<input type="hidden" id="size_id_0" name="size_id_0" size="30" />
														<div class="required-icon">
										<div class="text">*</div>
									</div>
								</div>
														</div>
												</div>
										
												<div class='col-sm-3'>
													<div class='form-group'>
															<select class="form-control "
															id="is_active_det_0" name="is_active_det_0"
															"value="${obj.isActivedet}">
															<option value="1"
										<c:if test="${obj.isActivedet eq 1 }">selected="selected"</c:if>>Yes</option>
									<option value="2"
										<c:if test="${obj.isActivedet eq 2 }">selected="selected"</c:if>>No</option>
															

														</select>
													</div>
												</div>
												
												<div class="col-sm-2">&nbsp;</div>
											</div>

											<!-- 	 2nd row -->

											<div class="row row-no-margin linerow size_range_row"
												id="size_range_row_1">
												<div class="col-sm-3">
													<div class="form-group">
														<input class="form-control" id="si_no_1"
															name="si_no_1" value="20"
															size="30" type="text" placeholder="Enter Sl no" />
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
													<div class="required-field-block">
														<input class="form-control" id="size_name_1"
															name="size_name_1" value="${obj.sizeName}"
															size="30" type="text" placeholder="Enter Size" />
														<input type="hidden" id="size_id_1" name="size_id_1" size="30" />
														<div class="required-icon">
										<div class="text">*</div>
									</div>
								</div>
														</div>
												</div>
												
												<div class='col-sm-3'>
													<div class='form-group'>
														<!-- <label for="quote_type"  style="display:block;">Status</label> -->
														<select class="form-control "
															id="is_active_det_1" name="is_active_det_1"
															"value="${obj.isActivedet}">
														<option value="1"
										<c:if test="${obj.isActivedet eq 1 }">selected="selected"</c:if>>Yes</option>
									<option value="2"
										<c:if test="${obj.isActivedet eq 2 }">selected="selected"</c:if>>No</option>
															
														</select>
													</div>
												</div>
												<div class="col-sm-2">&nbsp;</div>
											</div>
											<!-- 	 3rd row -->

											<div class="row row-no-margin linerow size_range_row"
												id="size_range_row_2">
												<div class="col-sm-3">
													<div class="form-group">
												
														<input class="form-control" id="si_no_2"
															name="si_no_2" value="30"
															size="30" type="text" placeholder="Enter Sl no" />
													
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
													<div class="required-field-block">
														<input class="form-control" id="size_name_2"
															name="size_name_2" value="${obj.sizeName}"
															size="30" type="text" placeholder="Enter Size" />
														<input type="hidden" id="size_id_2" name="size_id_2" size="30" />
														<div class="required-icon">
										<div class="text">*</div>
									</div>
								</div>
														</div>
												</div>
																				<div class='col-sm-3'>
													<div class='form-group'>
															<select class="form-control "
															id="is_active_det_2" name="is_active_det_2" value="${obj.isActivedet}">
															<option value="1"
										<c:if test="${obj.isActivedet eq 1 }">selected="selected"</c:if>>Yes</option>
									<option value="2"
										<c:if test="${obj.isActivedet eq 2 }">selected="selected"</c:if>>No</option>

														</select>
													</div>
												</div>
												
												<div class="col-sm-2">&nbsp;</div>
											</div>

											<!-- 	 4th row -->

											<div class="row row-no-margin linerow size_range_row"
												id="size_range_row_3">
												<div class="col-sm-3">
													<div class="form-group">
														<input class="form-control" id="si_no_3"
															name="si_no_3" value="40" sl="40"
															size="30" type="text" placeholder="Enter Sl no" />
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
													<div class="required-field-block">
														<input class="form-control" id="size_name_3"
															name="size_name_3" value="${obj.sizeName}"
															size="30" type="text" placeholder="Enter Size" />
														<input type="hidden" id="size_id_3" name="size_id_3" size="30" />
															<div class="required-icon">
										<div class="text">*</div>
									</div>
								</div>
															</div>
												</div>
													<div class='col-sm-3'>
													<div class='form-group'>
														<select class="form-control "
															id="is_active_det_3" name="is_active_det_3"
															value="${obj.isActivedet}">
															<option value="1"
										<c:if test="${obj.isActivedet eq 1 }">selected="selected"</c:if>>Yes</option>
									<option value="2"
										<c:if test="${obj.isActivedet eq 2 }">selected="selected"</c:if>>No</option>
															
														</select>
													</div>
											</div>
												<div class="col-sm-2 linerow-icon">
													<a href="javascript:addAnotherLineForSizeRange('si_no_3')" data-toggle="tooltip" title="Add"><span
														class="glyphicon glyphicon-plus text-primary "></span></a>
												</div>
											</div>
												
										</c:if>

									</div>

									
								</div>

								<div class='row' style="margin-top: 15px;">
									<div class='col-sm-12'></div>
								</div>
								
								<div class="row pull-left">
									<div class="col-sm-12 col-xs-12 centered">


										<div class="btn-group dropdown">


											<button name="save" id="save"
												onclick="saveSizeRange('${mode}','1')"
												class="btn btn-success ladda-button"
												data-style="expand-right">
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
													href="javascript:saveSizeRange('${mode}','2');">Save
														&amp; New</a></li>


												<li><a
													href="javascript:saveSizeRange('${mode}','3');">Save
														&amp; Close</a></li>
											</ul>
										</div>
										<button type="button" class="btn btn-primary"
											onclick="showSizeRange()">
											<i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span>
										</button>
									</div>
								</div>
							</div>
						</div>
						<jsp:include page="../common/Footer.jsp" />

						<input type="hidden" name="servlet_name" id="servlet_name" /> 
						<input type="hidden" name="request_type" id="request_type" />
						 <input type="hidden" name="pageno" id="pageno" />
						 <input type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
						 <input type="hidden" name="mode" id="mode" value="${mode}" />
						<input type="hidden" name="size_range_id" id="size_range_id" value="${size_range_id}" />
						 <input type="hidden" name="save_type" id="save_type" value="${save_type}" /> 
						<input type="submit" name="validation_btn" id="validation_btn" style="display: none;" />
						<input type="hidden" name="row_count" id="row_count" value="${row_count}" />
						<input type="hidden" id="add_new_size" data-toggle="modal" data-target="#sizeMaster" />
						<c:choose>
							<c:when test="${mode eq 'n'}">
								<input type="hidden" name="row_ids" id="row_ids" value="${row_ids }" />
							</c:when>
							<c:otherwise>
								<c:set var="a" value="0" />
								<c:set var="b" value="" />
								<c:set var="c" value="" />
								<c:if test="${k>0}">
									<c:forEach begin="1" end="${k}" varStatus="loop">

										<c:set var="b" value="${a}," />
										<c:set var="c" value="${c}${b}" />
										<c:set var="a" value="${a+1}" />
									</c:forEach>
								</c:if>
								<input type="hidden" name="row_ids" id="row_ids"
									value="${c}${row_ids }" />
							</c:otherwise>
						</c:choose>
<!-- <script type="text/javascript">
!function ($) {
	
	 $(function(){
	
	 $('#size_name').listSizeRange({
	 url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeRange",
	 nameField :'#size_name',
	 idField:'#size_id'
	 });
	
	 });
	 }(window.jQuery); 
</script>
 -->
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
									size_range : {
										validators : {
											notEmpty : {
												message : 'This field is required',
											},
											stringLength : {
												max : 50,
												message : 'The message must be less than 50 characters long',
											},
										}
									}
									 		} 

									});

			
		</script>
	  <script>		  
	!function ($) {
	 
		$(function(){
			
			<c:forEach var="rowId" items="${row_id_list}">
			
			$('#size_name_${rowId}').listSizeRange({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSize&create_new=1",
			  nameField :'#size_name_${rowId}',
			  idField:'#size_id_${rowId}'
				//  validateId:'size_name_${rowId}'
		  });
			</c:forEach>
				
		 });
 }(window.jQuery);	
</script> 
	</form>
	 <jsp:include page="../masters/AddSizeMaster.jsp" />
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>
</body>
</html>

