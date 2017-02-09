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
</script>

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
							<h2 class="page-header">Location Configuration</h2>
						</div>

					</div>
					
					<div class="loc_config_div">
					
					<div class="row">
		<section>
        <div class="wizard">
            <div class="wizard-inner">
                <div class="connecting-line"></div>
                <ul class="nav nav-tabs" role="tablist">

                    <li role="presentation" <c:if test="${div_name eq 'general'}"> class="active" </c:if>   >
                        <a href="#step1" data-toggle="tab" aria-controls="step1" role="tab" title="General">
                            <span class="round-tab">
                                <i class="glyphicon ">1</i>
                            </span>
                        </a>
                         <input type="hidden" id="temp_div_name" name="temp_div_name" value="general"/>
                    </li>

                    <li role="presentation" <c:if test="${div_name eq 'finance'}"> class="active" </c:if>  >
                        <a href="#step2" data-toggle="tab" aria-controls="step2" role="tab" title="Financials">
                            <span class="round-tab">
                                <i class="glyphicon ">2</i>
                            </span>
                        </a>
                         <input type="hidden" id="temp_div_name" name="temp_div_name" value="finance"/>
                    </li>
                    <li role="presentation" <c:if test="${div_name eq 'inventory'}"> class="active" </c:if>  >
                        <a href="#step3" data-toggle="tab" aria-controls="step3" role="tab" title="Inventory">
                            <span class="round-tab">
                                <i class="glyphicon ">3</i>
                            </span>
                        </a>
                          <input type="hidden" id="temp_div_name" name="temp_div_name" value="inventory"/>
                    </li>
                    
                    <li role="presentation"  <c:if test="${div_name eq 'purchase_invoice'}"> class="active" </c:if>   >
                        <a href="#step4" data-toggle="tab" aria-controls="step4" role="tab" title="Purchase Invoice">
                            <span class="round-tab">
                                <i class="glyphicon ">4</i>
                            </span>
                        </a>
                         <input type="hidden" id="temp_div_name" name="temp_div_name" value="purchase_invoice"/>
                    </li>
                    
                    <li role="presentation"  <c:if test="${div_name eq 'purchase_order'}"> class="active" </c:if>  >
                        <a href="#step5" data-toggle="tab" aria-controls="step5" role="tab" title="Purchase Order">
                            <span class="round-tab">
                                <i class="glyphicon ">5</i>
                            </span>
                        </a>
                          <input type="hidden" id="temp_div_name" name="temp_div_name" value="purchase_order"/>
                    </li>
                    
                    <li role="presentation"  <c:if test="${div_name eq 'sales_invoice'}"> class="active" </c:if>  >
                        <a href="#step6" data-toggle="tab" aria-controls="step6" role="tab" title="Sales Invoice">
                            <span class="round-tab">
                                <i class="glyphicon ">6</i>
                            </span>
                        </a>
                        <input type="hidden" id="temp_div_name" name="temp_div_name" value="sales_invoice"/>
                    </li>
                    
                    <li role="presentation"  <c:if test="${div_name eq 'sales_order'}"> class="active" </c:if>  >
                        <a href="#step7" data-toggle="tab" aria-controls="step7" role="tab" title="Sales Order">
                            <span class="round-tab">
                                <i class="glyphicon ">7</i>
                            </span>
                        </a>
                        <input type="hidden" id="temp_div_name" name="temp_div_name" value="sales_order"/>
                    </li>
                    
                    <li role="presentation"  <c:if test="${div_name eq 'sales_quotation'}"> class="active" </c:if>  >
                        <a href="#step8" data-toggle="tab" aria-controls="step8" role="tab" title="Sales Quotation">
                            <span class="round-tab">
                                <i class="glyphicon ">8</i>
                            </span>
                        </a>
                        <input type="hidden" id="temp_div_name" name="temp_div_name" value="sales_quotation"/>
                    </li>
                    
                    <li role="presentation"  <c:if test="${div_name eq 'tax'}"> class="active" </c:if> >
                        <a href="#step9" data-toggle="tab" aria-controls="step9" role="tab" title="Tax">
                            <span class="round-tab">
                                <i class="glyphicon ">9</i>
                            </span>
                        </a>
                        <input type="hidden" id="temp_div_name" name="temp_div_name" value="tax"/>
                    </li>

                    <!-- <li role="presentation" class="disabled">
                        <a href="#complete" data-toggle="tab" aria-controls="complete" role="tab" title="Complete">
                            <span class="round-tab">
                                <i class="glyphicon glyphicon-ok"></i>
                            </span>
                        </a>
                    </li> -->
                </ul>
            </div>

            <!-- <form role="form"> -->
                <div class="tab-content">
                    <div class="tab-pane <c:if test="${div_name eq 'general'}"> active</c:if> " role="tabpanel" id="step1">
                       <div class="row row-no-margin tab-head" ><h3>General</h3></div>
                        
						
						${location_config_data.locConfigGeneral}
						

						<div class="row row-no-margin btn-foot">
	                        <ul class="list-inline pull-right">
	                        <li><button type="button" class="btn btn-primary btn-info-full" onclick="saveLocationConfiguration('general')" ><i class="glyphicon glyphicon-floppy-disk"></i>&nbsp;Save & Continue</button></li>
	                            <li><button type="button" class="btn btn-default next-step">Next&nbsp;<i class="glyphicon glyphicon-menu-right"></i></button></li>
	                        </ul>
                        </div>
                    </div>
                    <div class="tab-pane <c:if test="${div_name eq 'finance'}"> active</c:if>" role="tabpanel" id="step2">
                        <div class="row row-no-margin tab-head" ><h3>Financials</h3></div>
                        ${location_config_data.locConfigFinancial}
                        
							<div class="row row-no-margin btn-foot">
		                        <ul class="list-inline pull-right">
		                            <li><button type="button" class="btn btn-default prev-step"><i class="glyphicon glyphicon-menu-left"></i>&nbsp;Previous</button></li>
		                            <li><button type="button" class="btn btn-primary btn-info-full" onclick="saveLocationConfiguration('finance')" ><i class="glyphicon glyphicon-floppy-disk"></i>&nbsp;Save & Continue</button></li>
		                            <li><button type="button" class="btn btn-default next-step">Next&nbsp;<i class="glyphicon glyphicon-menu-right"></i></button></li>
		                        </ul>
                        </div>
                    </div>
                    <div class="tab-pane <c:if test="${div_name eq 'inventory'}"> active</c:if>" role="tabpanel" id="step3">
                       <div class="row row-no-margin tab-head" ><h3>Inventory</h3></div>
                        ${location_config_data.locConfigInventory}
                        
							<div class="row row-no-margin btn-foot">
                        <ul class="list-inline pull-right">
                            <li><button type="button" class="btn btn-default prev-step"><i class="glyphicon glyphicon-menu-left"></i>&nbsp;Previous</button></li>
                            <li><button type="button" class="btn btn-primary btn-info-full" onclick="saveLocationConfiguration('inventory')" ><i class="glyphicon glyphicon-floppy-disk"></i>&nbsp;Save & Continue</button></li>
                            <!-- <li><button type="button" class="btn btn-default next-step">Skip</button></li> -->
                            <!-- <li><button type="button" class="btn btn-primary btn-info-full next-step">Save and continue</button></li> -->
                            <li><button type="button" class="btn btn-default next-step">Next&nbsp;<i class="glyphicon glyphicon-menu-right"></i></button></li>
                        </ul>
                        </div>
                    </div>
                    <div class="tab-pane <c:if test="${div_name eq 'purchase_invoice'}"> active</c:if>" role="tabpanel" id="step4">
                       <div class="row row-no-margin tab-head" ><h3>Purchase Invoice</h3></div>
                        ${location_config_data.locConfigPurInv}
                        
							<div class="row row-no-margin btn-foot">
                        <ul class="list-inline pull-right">
                            <li><button type="button" class="btn btn-default prev-step"><i class="glyphicon glyphicon-menu-left"></i>&nbsp;Previous</button></li>
                            <li><button type="button" class="btn btn-primary btn-info-full" onclick="saveLocationConfiguration('purchase_invoice')" ><i class="glyphicon glyphicon-floppy-disk"></i>&nbsp;Save & Continue</button></li>
                            <!-- <li><button type="button" class="btn btn-default next-step">Skip</button></li> -->
                            <li><button type="button" class="btn btn-default next-step">Next&nbsp;<i class="glyphicon glyphicon-menu-right"></i></button></li>
                        </ul>
                        </div>
                    </div>
                    <div class="tab-pane <c:if test="${div_name eq 'purchase_order'}"> active</c:if>" role="tabpanel" id="step5">
                       <div class="row row-no-margin tab-head" ><h3>Purchase Order</h3></div>
                        ${location_config_data.locConfigPurOrder}
                        
							<div class="row row-no-margin btn-foot">
                        <ul class="list-inline pull-right">
                            <li><button type="button" class="btn btn-default prev-step"><i class="glyphicon glyphicon-menu-left"></i>&nbsp;Previous</button></li>
                            <li><button type="button" class="btn btn-primary btn-info-full" onclick="saveLocationConfiguration('purchase_order')" ><i class="glyphicon glyphicon-floppy-disk"></i>&nbsp;Save & Continue</button></li>
                            <!-- <li><button type="button" class="btn btn-default next-step">Skip</button></li> -->
                            <li><button type="button" class="btn btn-default next-step">Next&nbsp;<i class="glyphicon glyphicon-menu-right"></i></button></li>
                        </ul>
                        </div>
                    </div>
                    
                    <div class="tab-pane <c:if test="${div_name eq 'sales_invoice'}"> active</c:if>" role="tabpanel" id="step6">
                       <div class="row row-no-margin tab-head" ><h3>Sales Invoice</h3></div>
                        ${location_config_data.locConfigSalesInv}
                        
							<div class="row row-no-margin btn-foot">
                        <ul class="list-inline pull-right">
                            <li><button type="button" class="btn btn-default prev-step"><i class="glyphicon glyphicon-menu-left"></i>&nbsp;Previous</button></li>
                            <!-- <li><button type="button" class="btn btn-default next-step">Skip</button></li> -->
                            <li><button type="button" class="btn btn-primary btn-info-full" onclick="saveLocationConfiguration('sales_invoice')" ><i class="glyphicon glyphicon-floppy-disk"></i>&nbsp;Save & Continue</button></li>
                            <li><button type="button" class="btn btn-default next-step">Next&nbsp;<i class="glyphicon glyphicon-menu-right"></i></button></li>
                        </ul>
                        </div>
                    </div>
                    
                    
                    <div class="tab-pane <c:if test="${div_name eq 'sales_order'}"> active</c:if>" role="tabpanel" id="step7">
                       <div class="row row-no-margin tab-head" ><h3>Sales Order</h3></div>
                        ${location_config_data.locConfigSalesOrder}
                        
							<div class="row row-no-margin btn-foot">
                        <ul class="list-inline pull-right">
                            <li><button type="button" class="btn btn-default prev-step"><i class="glyphicon glyphicon-menu-left"></i>&nbsp;Previous</button></li>
                            <li><button type="button" class="btn btn-primary btn-info-full" onclick="saveLocationConfiguration('sales_order')" ><i class="glyphicon glyphicon-floppy-disk"></i>&nbsp;Save & Continue</button></li>
                            <!-- <li><button type="button" class="btn btn-default next-step">Skip</button></li> -->
                            <li><button type="button" class="btn btn-default next-step">Next&nbsp;<i class="glyphicon glyphicon-menu-right"></i></button></li>
                        </ul>
                        </div>
                    </div>
                    
                    <div class="tab-pane <c:if test="${div_name eq 'sales_quotation'}"> active</c:if>" role="tabpanel" id="step8">
                       <div class="row row-no-margin tab-head" ><h3>Sales Quotation</h3></div>
                        ${location_config_data.locConfigSalesQuotation}
                        
							<div class="row row-no-margin btn-foot">
                        <ul class="list-inline pull-right">
                            <li><button type="button" class="btn btn-default prev-step"><i class="glyphicon glyphicon-menu-left"></i>&nbsp;Previous</button></li>
                            <li><button type="button" class="btn btn-primary btn-info-full" onclick="saveLocationConfiguration('sales_quotation')" ><i class="glyphicon glyphicon-floppy-disk"></i>&nbsp;Save & Continue</button></li>
                            <!-- <li><button type="button" class="btn btn-default next-step">Skip</button></li> -->
                            <li><button type="button" class="btn btn-default next-step">Next&nbsp;<i class="glyphicon glyphicon-menu-right"></i></button></li>
                        </ul>
                        </div>
                    </div>
                    
                    <div class="tab-pane <c:if test="${div_name eq 'tax'}"> active</c:if>" role="tabpanel" id="step9">
                       <div class="row row-no-margin tab-head" ><h3>Tax</h3></div>
                        ${location_config_data.locConfigTax}
                        
							<div class="row row-no-margin btn-foot">
                        <ul class="list-inline pull-right">
                            <li><button type="button" class="btn btn-default prev-step"><i class="glyphicon glyphicon-menu-left"></i>&nbsp;Previous</button></li>
                            <!-- <li><button type="button" class="btn btn-default next-step">Skip</button></li> -->
                            <li><button type="button" class="btn btn-primary btn-info-full" onclick="saveLocationConfiguration('tax')" ><i class="glyphicon glyphicon-floppy-disk"></i>&nbsp;Save</button></li>
                        </ul>
                        </div>
                    </div>
                    
                    <!-- <div class="tab-pane" role="tabpanel" id="complete">
                        <div class="row row-no-margin tab-head" ><h3>Complete</h3></div>
                        <p>You have successfully completed all steps.</p>
                    </div> -->
                    <div class="clearfix"></div>
                </div>
            <!-- </form> -->
        </div>
    </section>
   </div>
					
						<%-- <div class="col-sm-12">
						
							<div>
							
							<c:forEach var="lc" items="${location_config_data.locConfigGeneralList}">
							
							<div class="row row-no-margin linerow ">
							
								<div class="col-sm-4">
									<div class="form-group">${lc.configName }</div>
								</div>
								
								<div class="col-sm-4">
									<div class="form-group">
									<c:if test="${lc.configOptionDataType eq 'account_dropdown'}">
									
									<select class="form-control" id="loc_config_option_${lc.autoId}" name="loc_config_option_${lc.autoId}" >
									<option value="-1"><--Select--></option>
									<option value="Manual">Manual</option>
									<option value="Single account">Single account</option>
									<option value="Based on Product Group">Based on Product Group</option>
									<option value="Based on Product">Based on Product</option>
									<option value="Based on Tax Group">Based on Tax Group</option>
									<option value="Based on Product Group and Tax group">Based on Product Group and Tax group</option>
									<option value="Based on Type of sales">Based on Type of sales</option>
									<option value="Based on Type of sales and Product Group">Based on Type of sales and Product Group</option>
									</select>
									
									</c:if>
									<c:if test="${lc.configOptionDataType eq 'service'}">
									
									<select class="form-control" id="loc_config_option_${lc.autoId}" name="loc_config_option_${lc.autoId}" >
									<option value="-1"><--Select--></option>
									<option value="Service">Service</option>
									</select>
									</c:if>
									
									<c:if test="${lc.configOptionDataType eq 'yes_or_no'}">
									<select class="form-control" id="loc_config_option_${lc.autoId}" name="loc_config_option_${lc.autoId}" >
									<option value="-1"><--Select--></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
									</select>
									</c:if>
									
									<c:if test="${lc.configOptionDataType eq 'input_number'}">
									<input type="number" class="form-control" id="loc_config_option_${lc.autoId}" name="loc_config_option_${lc.autoId}"  min="0" max="5"/>
									</c:if>
									
									<c:if test="${lc.configOptionDataType eq 'date'}">
									<input type="text" class="form-control" id="loc_config_option_${lc.autoId}" name="loc_config_option_${lc.autoId}" />
									
									<script type="text/javascript">
				
									      jQuery('#loc_config_option_${lc.autoId}').datepicker({
												    format: "dd-mm-yyyy"
												}).on('changeDate', function(e) {
										        });  
						
					        		</script>
									
									</c:if>
									
									
									
									</div>
								</div>
								
								<div class="col-sm-4">
									<div class="form-group">${lc.remarks}</div>
								</div>
							
							</div>
							
							</c:forEach>
							
							</div>
						
						</div> --%>
					
					</div>
					
					
					</div>
			</div>
		</div>
		<input type="hidden" name="servlet_name" id="servlet_name" /> 
		<input type="hidden" name="request_type" id="request_type" value="${request_type}" /> 
		<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" /> 
		<input type="hidden" name="pageno" id="pageno" value="${pc.pageNo}" /> 
		<input type="hidden" name="total_pages" id="total_pages" value="${pc.pageCount}" />
		
		<input type="hidden" name="loc_config_general_auto_id" id="loc_config_general_auto_id" value="${loc_config_general_auto_id}" />
		<input type="hidden" name="loc_config_financial_auto_id" id="loc_config_financial_auto_id" value="${loc_config_financial_auto_id}" />
		<input type="hidden" name="loc_config_inventory_auto_id" id="loc_config_inventory_auto_id" value="${loc_config_inventory_auto_id}" />
		<input type="hidden" name="loc_config_pur_inv_auto_id" id="loc_config_pur_inv_auto_id" value="${loc_config_pur_inv_auto_id}" />
		<input type="hidden" name="loc_config_pur_order_auto_id" id="loc_config_pur_order_auto_id" value="${loc_config_pur_order_auto_id}" />
		<input type="hidden" name="loc_config_sales_inv_auto_id" id="loc_config_sales_inv_auto_id" value="${loc_config_sales_inv_auto_id}" />
		<input type="hidden" name="loc_config_sales_order_auto_id" id="loc_config_sales_order_auto_id" value="${loc_config_sales_order_auto_id}" />
		<input type="hidden" name="loc_config_sales_quotation_auto_id" id="loc_config_sales_quotation_auto_id" value="${loc_config_sales_quotation_auto_id}" />
		<input type="hidden" name="loc_config_tax_auto_id" id="loc_config_tax_auto_id" value="${loc_config_tax_auto_id}" />
		
		<input type="hidden" name="date_id" id="date_id" value="${date_id}" />
		
		<input type="hidden" name="div_name" id="div_name" value="${div_name}" />
		
		

<style>
body{
	background: #eee;
}
#page-wrapper{
	background: #eee;
}

.tab-content .linerow {
    border-bottom: 1px solid #c7c7c7;
    margin: 0;
}
.tab-content .linerow .form-group {
    margin-bottom: 15px;
    margin-top: 10px;
}
.tab-content .linerow-icon {
    text-align: left;
}
.btn-foot{
padding:10px 10px;

}
.tab-head{
background: #5bc0de;
padding: 10px 10px;
/* margin-bottom: 30px; */
}
.wizard {
    /* margin: 20px auto; */
    background: #fff;
}

    .wizard .nav-tabs {
        position: relative;
        /* margin: 40px auto; */
        margin-bottom: 0;
        border-bottom-color: #e0e0e0;
    }

    .wizard > div.wizard-inner {
        position: relative;
            background: #fafafa;
    }

.tab-content{
    background: #fff;
}

.connecting-line {
    height: 1px;
    background: #e0e0e0;
    position: absolute;
    width: 90%;
    margin: 0 auto;
    left: 0;
    right: 0;
    top: 50%;
    z-index: 1;
}

.wizard .nav-tabs > li.active > a, .wizard .nav-tabs > li.active > a:hover, .wizard .nav-tabs > li.active > a:focus {
    color: #555555;
    cursor: default;
    border: 0;
    border-bottom-color: transparent;
}

span.round-tab {
    width: 40px;
    height: 40px;
    line-height: 40px ; 
    display: inline-block;
    border-radius: 100px;
    background: #fff;
    border: 1px solid #e0e0e0;
    z-index: 2;
    position: absolute;
    left: 0;
    text-align: center;
    font-size: 17px;
}
span.round-tab i{
    color:#555555;
    line-height: 36px ;
}
.wizard li.active span.round-tab {
    background: #fff;
    border: 1px solid #5bc0de;
    
}
.wizard li.active span.round-tab i{
    color: #5bc0de;
}

span.round-tab:hover {
    color: #333;
    border: 1px solid #333;
}

.wizard .nav-tabs > li {
    width: 11%;
}

.wizard li:after {
    content: " ";
    position: absolute;
    left: 42%;
    opacity: 0;
    margin: 0 auto;
    bottom: 0px;
    border: 5px solid transparent;
    border-bottom-color: #5bc0de;
    transition: 0.1s ease-in-out;
}

.wizard li.active:after {
    content: " ";
    position: absolute;
    left: 42%;
    opacity: 1;
    margin: 0 auto;
    bottom: 0px;
    border: 10px solid transparent;
    border-bottom-color: #5bc0de;
}

.wizard .nav-tabs > li a {
    width: 40px;
    height: 40px;
    margin: 20px auto;
    border-radius: 100%;
    padding: 0;
}

    .wizard .nav-tabs > li a:hover {
        background: transparent;
    }

.wizard .tab-pane {
    position: relative;
        padding:0px 0px 0px 0px;
}

.wizard h3 {
    margin-top: 0;
}

@media( max-width : 585px ) {

    .wizard {
        width: 100%;
        height: auto !important;
    }

    span.round-tab {
        font-size: 12px;
        width: 30px;
        height: 30px;
        line-height: 30px;
    }
	span.round-tab i{
	    color:#555555;
	    line-height: 26px ;
	}
    .wizard .nav-tabs > li a {
        width: 30px;
        height: 30px;
        line-height: 26px;
    }

    .wizard li.active:after {
        content: " ";
        position: absolute;
        left: 25%;
    }
}
</style>

<script language="javascript">
$(document).ready(function () {
    //Initialize tooltips
    $('.nav-tabs > li a[title]').tooltip();
    
    //Wizard
  /*   $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {

        var $target = $(e.target);
    
        if ($target.parent().hasClass('disabled')) {
            return false;
        }
    }); */

    $(".next-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        $active.next().removeClass('disabled');
        nextTab($active);

    });
    $(".prev-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        prevTab($active);

    });
});

function nextTab(elem) {
    $(elem).next().find('a[data-toggle="tab"]').click();
    $('#div_name').val($(elem).next().find('#temp_div_name').val());
}
function prevTab(elem) {
    $(elem).prev().find('a[data-toggle="tab"]').click();
    $('#div_name').val($(elem).prev().find('#temp_div_name').val());
}


var dateIds=jQuery('#date_id').val();
var arDateIds=dateIds.split(',');

for(var i=0;i<arDateIds.length;i++){
	
	  jQuery('#'+arDateIds[i]).datepicker({
		    format: "dd-MM-yyyy"
		}).on('changeDate', function(e) {
      });  
}

</script>


				 
	</form>
	<jsp:include page="../common/Footer.jsp" />
	
	
	
	
</body>
</html>