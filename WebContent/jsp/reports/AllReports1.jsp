<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.ResourceBundle"%>
<%@page import="com.alpha.tpcsfashion.beans.MenuItem"%>
<%@page import="com.alpha.tpcsfashion.beans.TPCSUser"%>
<%@ page import="com.alpha.tpcsfashion.util.UserInfo"%>
<%@ page import="com.alpha.tpcsfashion.util.TPCSCommonUtil"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="com.alpha.tpcsfashion.beans.MenuHolder"%>

<fmt:setLocale value="${sessionScope.login_user_locale}"/>
<fmt:bundle basename="${sessionScope.appBundleName}">

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title><fmt:message bundle="${bundle}" key="Title.Title" /></title>

<jsp:include page="../common/Header.jsp" />


<style>
.accordion-toggle:hover {
      text-decoration: none;
    }
</style>

<script>
jQuery(document).ready(function(){
jQuery('.collapse').on('shown.bs.collapse', function(){
	jQuery(this).parent().find(".glyphicon-plus-sign").removeClass("glyphicon-plus-sign").addClass("glyphicon-minus-sign");
	}).on('hidden.bs.collapse', function(){
jQuery(this).parent().find(".glyphicon-minus-sign").removeClass("glyphicon-minus-sign").addClass("glyphicon-plus-sign");
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
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>
<div id="page-wrapper">
<div id="page-inner">
         <div class="row">
                <div class="col-md-11 col-sm-12 col-xs-8">
                    <h3 class="page-header"><i class="fa fa-bar-chart-o fa-fw"></i>Reports</h3>
                </div>	
             
         </div>
        
         
		<div >
<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne1" style="text-decoration: none;">
          <span class="indicator glyphicon glyphicon-minus-sign"></span>
         &nbsp;&nbsp;Sales
        </a>
      </h4>
      <div class="pull-right" id="">
			<a href="#" onclick="editModule()" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
		</div>
    </div>
    <div id="collapseOne1" class="panel-collapse collapse in">
      <div class="panel-body">
       	<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<label for="customer">Folder</label>
		</div>
		<div class="col-md-3" id="">
			<label for="customer">Report Name</label>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<label for="customer">Description</label>
		</div>
		<div class="col-md-1 col-xs-12" id="">
			<label for="customer">Action</label>
		</div>
       	</div>
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Enquiry</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showEnquiryPendingForSQ()">Enquiries Pending For Quotation</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Quotation</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showQuotationPendingForSO()">Quotations Pending For SalesOrder</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5></h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showQuotationVsSalesOrdersToComparePriceAndQty()">Quotation and sales order comparison</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Blanket Order</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showBlanketOrdersWithoutSalesOrder()">Blanket orders pending for sales order</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5></h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showBlanketOrdersVsSalesOrder()">Blanket orders and sales order comparison</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Sales</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showDailyWeeklyMonthlyAnalysis()">Daily, weekly, monthly sales analysis</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5></h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="">Top 10 analysis</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Sales Order</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showPendingSalesOrder(20)">Pending sales Order details</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(20)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(20)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
      </div>
    </div>
  </div>
  
</div>
</div>
		<div >
<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne2" style="text-decoration: none;">
          <span class="indicator glyphicon glyphicon-minus-sign"></span>
         &nbsp;&nbsp;Purchase
        </a>
      </h4>
      <div class="pull-right" id="">
			<a href="#" onclick="editModule()" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
		</div>
    </div>
    <div id="collapseOne2" class="panel-collapse collapse in">
      <div class="panel-body">
       	<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<label for="customer">Folder</label>
		</div>
		<div class="col-md-3" id="">
			<label for="customer">Report Name</label>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<label for="customer">Description</label>
		</div>
		<div class="col-md-1 col-xs-12" id="">
			<label for="customer">Action</label>
		</div>
       	</div>
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Indents</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showPendingIndents()">Pending Indents </a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Indents</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showPendingIndentsConsolidationBymaterial()">Pending Indents consolidation - by material</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Indents</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showIndentVsPurchaseOrders()">Indent And Purchase orders Comparison</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Purchase Order</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showPendingPurchaseOrders()">Pending purchase orders</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Purchase</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showPOVsGRN()">PO And GRN Comparison </a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Purchase</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showPriceComparisonOverAPeriod()">Price Comparison over a period</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Purchase</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showDailyPurchaseSummary()">Daily Purchase Summary</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Purchase</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showPurchaseSummaryByMaterial()">Purchase Summary by Material</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Purchase</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showMonthWisePurchaseAnalysis()">Month wise Purchase Analysis</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Purchase</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showApprovedPriceListLog()">Approved Price List Log - to check the previous &amp; current price</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       	
      </div>
    </div>
  </div>
  
</div>
</div>
		
		
		<div >
<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne3" style="text-decoration: none;">
          <span class="indicator glyphicon glyphicon-minus-sign"></span>
         &nbsp;&nbsp;Inventory
        </a>
      </h4>
      <div class="pull-right" id="">
			<a href="#" onclick="editModule()" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete icon-delete"></span>
        			</a>
		</div>
    </div>
    <div id="collapseOne3" class="panel-collapse collapse in">
      <div class="panel-body">
       	<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<label for="customer">Folder</label>
		</div>
		<div class="col-md-3" id="">
			<label for="customer">Report Name</label>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<label for="customer">Description</label>
		</div>
		<div class="col-md-1 col-xs-12" id="">
			<label for="customer">Action</label>
		</div>
       	</div>
     
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Inventory</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showStockStatement()">Stock Statement</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Inventory</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showStockLedger()">Stock Ledger</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Inventory</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showRejectionStockStatement()">Rejection Stock Statement</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Inventory</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showRejectionStockLedger()">Rejection Stock Ledger</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Inventory</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showMaterialsToPurchaseBasedOnMinimumStockLevel()">Materials To Reorder</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Inventory</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showNonMovingStockStatement()">Non Moving Stock Statement</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Inventory</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showInventoryPendingPurchaseOrders()">Pending Purchase Orders</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Inventory</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showStockAging()">Stock Aging</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		
       	
      </div>
    </div>
  </div>
  
</div>
</div>


<div >
<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne5" style="text-decoration: none;">
          <span class="indicator glyphicon glyphicon-minus-sign"></span>
         Finance
        </a>
      </h4>
      <div class="pull-right" id="">
			<a href="#" onclick="editModule()" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
		</div>
    </div>
    <div id="collapseOne5" class="panel-collapse collapse in">
      <div class="panel-body">
       	<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<label for="customer">Folder</label>
		</div>
		<div class="col-md-3" id="">
			<label for="customer">Report Name</label>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<label for="customer">Description</label>
		</div>
		<div class="col-md-1 col-xs-12" id="">
			<label for="customer">Action</label>
		</div>
       	</div>
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Account Books</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showAccountBooks()">Account books</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5></h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showBankBook()">Bank book</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5></h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showCashBook()">Cash  book</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5></h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showDayBook()">Day  book</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5></h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="">List of Accounts</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Payable</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showPendingPurchaseInvoicesForApprovals()">Pending purchase invoices for approvals</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5></h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showPayablesAgingAnalysis()">Payables aging analysis</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5></h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showPaymentStatement()">Payment statement</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5></h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showPurchaseRegister()">Purchase Register</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Receivable</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showReceivablesAgingAnalysis()">Receivables aging analysis</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5></h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showSalesRegister()">Sales Register</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Final</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showTrialBalance()">Trial Balance</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5></h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showPAndL()">P &amp; L</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5></h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showBalanceSheet()">Balance sheet</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5>Other</h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showCostcenterSummary()">Cost center summary</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       		
       	
       		
       		<div class="row" style="border-bottom:1px solid #f5f5f5;">
       	<div class="col-md-2 col-xs-12">
			<h5></h5>
		</div>
		<div class="col-md-3" id="">
			<h5><a href="#" onclick="showCostCenterAndAccountHeadSummary()">Cost center and Account head summary</a></h5>
		</div>
		<div class="col-md-6 col-xs-12" id="">
			<h5></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="#" onclick="editReport(22)" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="#" onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			</h5>
		</div>
       	</div>
       	
      </div>
    </div>
  </div>
  
</div>
</div>
       </div>           
     </div>  
    </div>

		<jsp:include page="../common/ColumnPreferences.jsp" />
		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> <input
			type="hidden" name="request_type" id="request_type" /> <input
			type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
			<input type="hidden" name="pageno" id="pageno" /> 

	</form>
</body>
</html>
   </fmt:bundle>         