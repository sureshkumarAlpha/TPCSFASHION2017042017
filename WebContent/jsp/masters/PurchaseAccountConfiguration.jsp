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
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/masters/masters.js"></script>
 

<script>

$(function(){
$('[data-toggle="tooltip"]').tooltip();
});
$(document).ready(function(){
    $('#filter-dropdown').click(function (e) {
    //	alert(e);
        e.stopPropagation();
    });
});


$(document).ready(function(){
	
	//jQuery('#purchase_account_2_child').hide();
	//jQuery('#purchase_account_3_child').hide();
	//jQuery('#purchase_account_4_child').hide();
	//jQuery('#purchase_account_5_child').hide();
	 $("#purchase_account_1").click(function(){
        $("#purchaseAccountModal").modal('show');
        $('#purchase_account_1').prop('checked', true);
    });
    $("#purchase_account_2_1").click(function(){
        $("#purchaseAccountModal").modal('show');
        $('#purchase_account_2_1').prop('checked', true);
    });
  
    $("#a_purchase_account_2_1").click(function(){
        $("#purchaseAccountModal").modal('show');
        $('#purchase_account_2_1').prop('checked', true);
    });
    $("#purchase_account_3_1").click(function(){
        $("#purchaseAccountModal").modal('show');
        $('#purchase_account_3_1').prop('checked', true);
    });
    $("#a_purchase_account_3_1").click(function(){
        $("#purchaseAccountModal").modal('show');
        $('#purchase_account_3_1').prop('checked', true);
    });
    
    $("#purchase_account_3_2").click(function(){
        $("#purchaseAccountModal").modal('show');
        $('#purchase_account_3_2').prop('checked', true);
    });
    $("#a_purchase_account_3_2").click(function(){
        $("#purchaseAccountModal").modal('show');
        $('#purchase_account_3_2').prop('checked', true);
    });
    $("#purchase_account_4_1").click(function(){
        $("#purchaseAccountModal").modal('show');
        $('#purchase_account_4_1').prop('checked', true);
    });
    $("#a_purchase_account_4_1").click(function(){
        $("#purchaseAccountModal").modal('show');
        $('#purchase_account_4_1').prop('checked', true);
    });
    $("#purchase_account_4_2").click(function(){
        $("#purchaseAccountModal").modal('show');
        $('#purchase_account_4_2').prop('checked', true);
    });
    $("#a_purchase_account_4_2").click(function(){
        $("#purchaseAccountModal").modal('show');
        $('#purchase_account_4_2').prop('checked', true);
    });
    $("#purchase_account_5_1").click(function(){
        $("#purchaseAccountModal").modal('show');
        $('#purchase_account_5_1').prop('checked', true);
    });
    
    $("#a_purchase_account_5_1").click(function(){
        $("#purchaseAccountModal").modal('show');
        $('#purchase_account_5_1').prop('checked', true);
    });
    $("#purchase_account_5_2").click(function(){
        $("#purchaseAccountModal").modal('show');
        $('#purchase_account_5_2').prop('checked', true);
    });
    $("#a_purchase_account_5_2").click(function(){
        $("#purchaseAccountModal").modal('show');
        $('#purchase_account_5_2').prop('checked', true);
    });
});


</script>

<style>
.bootstrap-select {
	width: 150px !important;
}

.sales_acc_div  h5{
display:inline;
}
.sales_acc_div .col-sm-offset-1{
margin-left:35px;
}
</style>
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form action="" id="tpcslogin" method="post" role="form">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Sales.Transactions.SalesEnquiry"
					name="screen_name" />
			</jsp:include>


			<div id="page-wrapper">
				<div id="page-inner">
					<div class="row">
						<div class="col-md-6 col-sm-12 col-xs-12">
							<h2 class="page-header">Purchase Account Configuration</h2>
						</div>

					</div>
					 
			<div class="sales_acc_div">
					<div class="row">

						<div class='col-sm-12'>
							<div class='form-group'>
							<div class="checkbox" >  
			           		<input class="radio_1" type="radio" id="purchase_account_1" name="radio_purchase_account" <c:if test="${pa_config_info.configKey eq '1_0'}">checked</c:if> value="1"  onclick="showHidePurchaseAccDiv();getPurchaseAccountConfigData(1,0);" />
			           		<label for="purchase_account_1" class="radio_1" >
			           		 I will select purchase account in every Transaction manually. <!-- <h5 class="text-primary">(Manual)</h5> -->
			           		</label>
			           		</div>
								
							</div>
						</div>

					</div>
					
					<div class="row">

						<div class='col-sm-12'>
							<div class='form-group'>
							<div class="checkbox"  >  
			           		<input class="radio_1" type="radio" id="purchase_account_2" name="radio_purchase_account" <c:if test="${pa_config_info.configKey eq '2_1'}">checked</c:if> value="1" onclick="showHidePurchaseAccDiv()" />
			           		<label for="purchase_account_2" class="radio_1" > I want all my purchase to be under a single account head. <!-- <h5 class="text-primary">(Single account)</h5> --></label>
			           		</div>
							</div>
						</div>
						
						<div id="purchase_account_2_child" <c:if test="${pa_config_info.configKey ne '2_1'}">style="display:none"</c:if> >
						
								<div class='col-sm-offset-1 col-sm-10' >
									<div class='form-group'>
									<div class="checkbox"  >  
									<input class="radio_1" type="radio" id="purchase_account_2_1" name="radio_purchase_account_sub_2" <c:if test="${pa_config_info.configKey eq '2_1'}">checked</c:if> value="1" />
					           		<label for="purchase_account_2_1" class="radio_1" onclick="getPurchaseAccountConfigData(2,1);">To choose account name <a href="#" id="a_purchase_account_2_1" >click here</a></label>
					           		</div>
									</div>
								</div>
								
						</div>

					</div>
					
					<div class="row">

						<div class='col-sm-12'>
							<div class='form-group'>
							<div class="checkbox">  
			           		<input class="radio_1" type="radio" id="purchase_account_3" name="radio_purchase_account" <c:if test="${pa_config_info.configKey eq '3_1' or pa_config_info.configKey eq '3_2'}">checked</c:if> value="1" onclick="showHidePurchaseAccDiv()"/>
			           		<label for="purchase_account_3" class="radio_1">I want to choose my purchase account based on product</label>
			           		</div>
							</div>
						</div>
						
						<div id="purchase_account_3_child" <c:if test="${pa_config_info.configKey ne '3_1' and pa_config_info.configKey ne '3_2'}">style="display:none"</c:if> >
						
							<div class='col-sm-offset-1 col-sm-10'>
								<div class='form-group'>
								<div class="checkbox">  
								<input class="radio_1" type="radio" id="purchase_account_3_1" name="radio_purchase_account_sub_3" <c:if test="${pa_config_info.configKey eq '3_1'}">checked</c:if>  value="1"  />
				           		<label for="purchase_account_3_1" class="radio_1"  onclick="getPurchaseAccountConfigData(3,1);">To choose / view purchase account head for the product groups <a id="a_purchase_account_3_1" href="#">click here</a> <!-- <h5 class="text-primary">(Based on Product Group)</h5> --></label>
				           		</div>
								</div>
							</div>
							
							<div class='col-sm-offset-1 col-sm-10'>
								<div class='form-group'>
								<div class="checkbox">  
								<input class="radio_1" type="radio" id="purchase_account_3_2" name="radio_purchase_account_sub_3" <c:if test="${pa_config_info.configKey eq '3_2'}">checked</c:if>  value="1" />
				           		<label for="purchase_account_3_2" class="radio_1"  onclick="getPurchaseAccountConfigData(3,2);">To choose / view purchase account head for each product <a href="#" id="a_purchase_account_3_2">click here</a> <!-- <h5 class="text-primary">(Based on Product)</h5> --></label>
				           		</div>
								</div>
							</div>
							
						</div>

					</div>
					
					<div class="row">

						<div class='col-sm-12'>
							<div class='form-group'>
							<div class="checkbox">  
			           		<input class="radio_1" type="radio" id="purchase_account_4" name="radio_purchase_account" <c:if test="${pa_config_info.configKey eq '4_1' or pa_config_info.configKey eq '4_2'}">checked</c:if>  value="1" onclick="showHidePurchaseAccDiv()"/>
			           		<label for="purchase_account_4" class="radio_1">I want to choose my purchase account based on &quot;tax group&quot;</label>
			           		</div>
							</div>
						</div>
						
						<div id="purchase_account_4_child" <c:if test="${pa_config_info.configKey ne '4_1' and pa_config_info.configKey ne '4_2'}">style="display:none"</c:if>>
						
							<div class='col-sm-offset-1 col-sm-10'>
								<div class='form-group'>
								<div class="checkbox">  
								<input class="radio_1" type="radio" id="purchase_account_4_1" name="radio_purchase_account_sub_4" <c:if test="${pa_config_info.configKey eq '4_1'}">checked</c:if>  value="1" />
				           		<label for="purchase_account_4_1" class="radio_1"  onclick="getPurchaseAccountConfigData(4,1);">To choose / view purchase account head for your &quot;tax groups&quot; <a href="#" id="a_purchase_account_4_1">click here</a> <!-- <h5 class="text-primary">(Based on Tax Group)</h5> --></label>
				           		</div>
								</div>
							</div>
							
							<div class='col-sm-offset-1 col-sm-10'>
								<div class='form-group'>
								<div class="checkbox">  
								<input class="radio_1" type="radio" id="purchase_account_4_2" name="radio_purchase_account_sub_4" value="1" <c:if test="${pa_config_info.configKey eq '4_2'}">checked</c:if>  />
				           		<label for="purchase_account_4_2" class="radio_1" onclick="getPurchaseAccountConfigData(4,2);">To choose / view purchase account head based on your &quot;tax group&quot; and product group <a href="#" id="a_purchase_account_4_2">click here</a> <!-- <h5 class="text-primary">(Based on Product Group and Tax group)</h5> --></label>
				           		</div>
								</div>
							</div>
							
						</div>
						
					</div>
					
					<div class="row">

						<div class='col-sm-12'>
							<div class='form-group'>
							<div class="checkbox">  
			           		<input class="radio_1" type="radio" id="purchase_account_5" name="radio_purchase_account" value="1" <c:if test="${pa_config_info.configKey eq '5_1' or pa_config_info.configKey eq '5_2'}">checked</c:if>  onclick="showHidePurchaseAccDiv()"/>
			           		<label for="purchase_account_5" class="radio_1">I want to choose my purchase account based on &quot;type of purchase&quot;</label>
			           		</div>
							</div>
						</div>
						
						<div id="purchase_account_5_child" <c:if test="${pa_config_info.configKey ne '5_1' and pa_config_info.configKey ne '5_2'}">style="display:none"</c:if>>
						
							<div class='col-sm-offset-1 col-sm-10'>
								<div class='form-group'>
								<div class="checkbox">  
								<input class="radio_1" type="radio" id="purchase_account_5_1" name="radio_purchase_account_sub_5" <c:if test="${pa_config_info.configKey eq '5_1'}">checked</c:if>  value="1" />
				           		<label for="purchase_account_5_1" class="radio_1" onclick="getPurchaseAccountConfigData(5,1);">To choose / view purchase account head for your &quot;Type of purchase&quot; <a href="#" id="a_purchase_account_5_1">click here</a> <!-- <h5 class="text-primary">(Based on Type of purchase)</h5> --></label>
				           		</div>
								</div>
							</div>
							
							<div class='col-sm-offset-1 col-sm-10'>
								
								<div class='form-group'>
								<div class="checkbox">  
								<input class="radio_1" type="radio" id="purchase_account_5_2" name="radio_purchase_account_sub_5" <c:if test="${pa_config_info.configKey eq '5_2'}">checked</c:if>  value="1" />
				           		<label for="purchase_account_5_2" class="radio_1" onclick="getPurchaseAccountConfigData(5,2);">To choose / view purchase account head based on your &quot;type of purchase&quot; and &quot;product group&quot; <a href="#" id="a_purchase_account_5_2">click here</a> <!-- <h5 class="text-primary">(Based on Type of purchase and Product Group)</h5> --></label>
				           		</div>
								</div>
								
							</div>
						
						</div>
					</div>
					 
				</div>

				</div>
			</div>
		</div>
		<input type="hidden" id="add_new_account" data-toggle="modal" data-target="#accountModal" /><!-- onclick="loadDataToAccountModal()" -->
		<input type="hidden" name="servlet_name" id="servlet_name" /> 
		<input type="hidden" name="request_type" id="request_type" value="${request_type}" /> 
		<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" /> 
		<input type="hidden" name="pageno" id="pageno" value="${pc.pageNo}" /> 
		<input type="hidden" name="total_pages" id="total_pages" value="${pc.pageCount}" />
		
		<input type="hidden" name="config_option" id="config_option" value="${pa_config_info.configOption}" />
		<input type="hidden" name="config_key" id="config_key" value="${pa_config_info.configKey}" /> 
				 
		<script language="javascript">
			showHidePurchaseAccDiv();
		</script>		 
				 
	</form>
	<jsp:include page="../common/Footer.jsp" />
	
	 <jsp:include page="../masters/PurchaseAccountConfigModal.jsp" />
	 <jsp:include page="../masters/AddAccountFromPage.jsp" />
 	 <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>
	
</body>
</html>