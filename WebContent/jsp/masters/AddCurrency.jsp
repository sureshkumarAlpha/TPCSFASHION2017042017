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
<body>
	<form action="" method="post" role="form" id="validate-form"
		class="trans-form" autocomplete="off">
		<div class="wrapper">
			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Sales.Transactions.SalesOrder" name="screen_name" />
			</jsp:include>


			<div class="row-no-margin row">
				<div class="col-md-11 col-sm-8 col-xs-12">
					<h2 class="add-page-header" style="margin: 18px 0 0px 20px;">Add
						Currency</h2>
				</div>
			</div>

			<div class="page-wrapper">
				<div class="page-inner">

					<div class="row">
						<div class="col-sm-3">
							<div class='form-group'>
								<label for="customer">Currency Name</label>
								<div class="required-field-block">
									<input class="form-control" id="currency_name" name="currency_name"
								maxlength="20" size="30" type="text" placeholder="Enter Currency Name"
										value="${currency_info.currencyName}" /> 
										<input id="currency_id" name="currency_id" size="30" type="hidden" value="${currency_info.currencyId}" autofocus />
										
									<div class="required-icon">
										<div class="text">*</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-3">
							<div class='form-group'>
								<label for="customer">Coin Name</label>
								<div class="required-field-block">
									<input class="form-control" id="coin_name" name="coin_name"
										maxlength="15" size="30" type="text" placeholder="Enter Coin Name"
										value="${currency_info.coinName}" />
									<div class="required-icon">
										<div class="text">*</div>
									</div>
								</div>
							</div>
						</div>


					</div>
					<div class="row">

						
						<div class="col-sm-3">
							<div class='form-group'>
								<label for="customer">Symbol</label>
								<input class="form-control" id="symbol" name="symbol" maxlength=5
										size="5" type="text" placeholder="Enter Symbol"
										value="${currency_info.symbol}" />



							</div>
						</div>
							<div class='col-sm-3'>
							<div class='form-group'>
								<label>Status</label> <select class="form-control"
									id="is_active" name="is_active">
									<option value="1"
										<c:if test="${currency_info.isActive eq 1 }">selected="selected"</c:if>>Active</option>
									<option value="0"
										<c:if test="${currency_info.isActive eq 0 }">selected="selected"</c:if>>Inactive</option>
								</select>
							</div>
						</div>

					</div>

					<br>

					<div class="row pull-left">
						<div class="col-sm-12 col-xs-12 centered">


							<div class="btn-group dropdown">


								<button name="save" id="save"
									onclick="saveCurrencyConfiguration('${mode}','1')"
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
										href="javascript:saveCurrencyConfiguration('${mode}','2');">Save
											&amp; New</a></li>


									<li><a
										href="javascript:saveCurrencyConfiguration('${mode}','3');">Save
											&amp; Close</a></li>


								</ul>
							</div>


							<button type="button" class="btn btn-primary"
								onclick="showCurrencyMaster()">
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
							message : <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisValueIsNotValid"/>,
								feedbackIcons : {
									valid : 'glyphicon glyphicon-ok',
									invalid : 'glyphicon glyphicon-remove',
									validating : 'glyphicon glyphicon-refresh'
								},
								fields : {
									symbol:{
										validators : {
											stringLength : {
												max : 5,
												message : 'The message must be less than 5 characters long',
											},
										}
									},
									 currency_name : {
										validators : {
											notEmpty : {
												message : 'This field is required',
											},
											stringLength : {
												max : 14,
												message : 'The message must be less than 15 characters long',
											},
										}
									},
									coin_name : {
										validators : {
											notEmpty : {
												message : 'This field is required',
											},
											stringLength : {
												max : 9,
												message : 'The message must be less than 20 characters long',
											},
										}
									}, 
									
								}
							});

					</script>


	</form>
</body>
</html>