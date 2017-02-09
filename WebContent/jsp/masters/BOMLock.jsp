
<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

  <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title><fmt:message bundle="${bundle}"   key="Title.Title"/></title>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/masters/BOM.js"></script>
<jsp:include page="../common/Header.jsp"/>
	
<style>
.container{
    margin-top:30px;
}


.filter-col{
    padding-left:10px;
    padding-right:10px;
}


label {
    display: inline-block;
    max-width: 100%;
    margin-bottom: 0px;
    font-weight: 700;
}

.form-group{
margin-bottom:4px;
}

.col-width-8{
width:8%;
}
.col-width-4{
width:4%;
}
.col-width-5{
width:5%;
}
.col-width-10{
width:10%;
}
.form-control{
	height:25px;
	font-size: 11px;
}
.input-group-addon {
    padding: 4px 8px;
    font-size: 12px;
}
.required-field-block .required-icon:after{
    border-bottom: 25px solid transparent;
}
   
   
.form-control-feedback {
    line-height: 30px;
    top: 0px;
}

.grid-slno {
        line-height: 0;
}
 
 
 .btn {
    padding: 1px 12px;
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
					<h2 class="add-page-header" style="margin: 18px 0 0px 20px;">BOM Lock</h2>
				</div>
			</div>

			<div class="page-wrapper">
				<div class="page-inner">

				
						<div class="row">
					<div class="col-sm-3">
					<div class="form-group">
					<label>Remarks</label>
					<textarea class="form-control address-text-area" rows="6" id="lock_remarks" name="lock_remarks" placeholder="Enter Lock Remarks" maxlength="200"></textarea>
					</div>            
					</div>
					</div>
					
					
					<div class="row">
<div class="col-sm-12 col-xs-12">
	<h5><label class="text-primary">Following open orders are using this BOM</label></h5>
	</div>
	</div>
				<div class="row">		
				<div class="row table-responsive">
<table class="table table-bordered table-condensed table-hover " >
<thead>
<tr class="header">
<c:if test="${lock_status eq '1'}">
<th>Select</th>
</c:if>
<th>Customer</th>
<th>IONO</th>
<th>Ship Date</th>
<th>Order Qty</th>
</tr>
</thead>
<tbody>
<tr class="datarow">						

<c:if test="${lock_status eq '1'}">
<td><div class="checkbox"> <input class="checkbox_1" type="checkbox" id="se_check_4" name="bom_id" value="4"><label for="se_check_4" class="checkbox_1"></label></div></td>
</c:if>

<td ></td>
<td ></td>
<td ></td>
<td ></td>
</tr>
<tr class="datarow">						
<c:if test="${lock_status eq '1'}">
<td><div class="checkbox"> <input class="checkbox_1" type="checkbox" id="se_check_4" name="bom_id" value="4"><label for="se_check_4" class="checkbox_1"></label></div></td>
</c:if>
<td ></td>
<td ></td>
<td ></td>
<td ></td>
</tr>
<tr class="datarow">						
<c:if test="${lock_status eq '1'}">
<td><div class="checkbox"> <input class="checkbox_1" type="checkbox" id="se_check_4" name="bom_id" value="4"><label for="se_check_4" class="checkbox_1"></label></div></td>
</c:if>
<td ></td>
<td ></td>
<td ></td>
<td ></td>
</tr>
<tr class="datarow">						
<c:if test="${lock_status eq '1'}">
<td><div class="checkbox"> <input class="checkbox_1" type="checkbox" id="se_check_4" name="bom_id" value="4"><label for="se_check_4" class="checkbox_1"></label></div></td>
</c:if>
<td ></td>
<td ></td>
<td ></td>
<td ></td>
</tr>
</tbody>
</table>
					</div>
					</div>

					<br>
					
					<c:if test="${lock_status eq '1'}">
	<div class="row">
<div class="col-sm-12 col-xs-12">
	<h5><label class="text-primary">Cost Sheet</label></h5>
	</div>
	</div>
				<div class="row">		
				<div class="row table-responsive">
<table class="table table-bordered table-condensed  " >
<thead>
<tr class="header">
<th>Material</th>
<th>Color</th>
<th>UOM</th>
<th>Required Qty</th>
<th>Rate</th>
<th>Value</th>
</tr>
</thead>

	<tbody>
			<c:set var="totalValue" value="${0}"/>
					<c:forEach var="obj" items="${detList}">
									<tr class="datarow">
								<td>${obj.material}</td>
								<td>${obj.variant}</td>
								<td>${obj.UOM}</td>
								<td class="text-right"><fmt:formatNumber pattern="#0.00" value="${obj.requiredQty}"/></td>
								<td class="text-right"><fmt:formatNumber pattern="#0.00" value="${obj.price}"/></td>
								<td class="text-right"><fmt:formatNumber pattern="#0.00" value="${obj.value}"/></td>
								 <c:set var="totalValue" value="${totalValue+obj.value}"/>
							</tr>
							</c:forEach>		
									<tr class="grand-total">
								<td colspan="5" >Total Value</td>
								<td class="text-right"><fmt:formatNumber pattern="#0.00" value="${totalValue}"/></td>
							</tr>	
			</tbody>
</table>
					</div>
					</div>
					</c:if>
					
				
		
					
					<div class="row pull-right" style="margin-top: 20px;">
						<div class="col-sm-12 col-xs-12 centered">



	                     <button type="button" class="btn btn-success" >
							 <span>&nbsp;Recalculate IORMR</span>
							</button>
							       <button type="button" class="btn btn-success" onclick="saveBOMLock()">
							 <span>&nbsp;Save</span>
							</button>
							<button type="button" class="btn btn-primary" onclick="showBOM()">
								<i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span>
							</button>


						</div>
					</div>



					<div class='row' style="margin-top: 20px;">
					
					
				





				</div>
			</div>




		</div>

		</div>
		<jsp:include page="../common/Footer.jsp" />

 <input type="hidden" name="bom_id" id="bom_id" value="${bom_id}" />
   <input type="hidden" name="bom_no" id="bom_no" value="${bom_no}" />
   <input type="hidden" name="lock_status" id="lock_status" value="${lock_status}" />
   
   
		<input type="hidden" name="servlet_name" id="servlet_name" /> <input
			type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
		<input type="hidden" name="pageno" id="pageno" /> <input
			type="hidden" name="request_type" id="request_type" /> <input
			type="submit" name="validation_btn" id="validation_btn"
			style="display: none;" />  
			
		<script type="text/javascript">
		

					</script>


	</form>
</body>
</html>