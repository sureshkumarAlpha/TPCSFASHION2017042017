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
<style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}

</style>
</head>
<script language="javascript">
var contextpath = "${pageContext.request.contextPath}";
</script>
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
                    <h3 class="page-header">Raw Material Status Report</h3>
                </div>	
    </div>
    <div class="row">
					<div class="panel-default">
					<div class="panel-body" style="padding: 0;">
					<div class='col-sm-3'>
					<div class='form-group'>
					<label >From</label> 
					<div class='input-group date' id='from_date'> 
					<input	type="text" class="form-control" name="from_date"
							id="from_date" placeholder="Select From Date" /> 
					<span class="input-group-addon">
					<span class="glyphicon glyphicon-calendar"></span>
				    </span>
			        </div>
			    	<script type="text/javascript">
					      jQuery('#from_date').datepicker({
			   	          format: "dd-mm-yyyy"
						   });  
					</script>
					</div>
					</div>
					<div class='col-sm-3'>
				    <div class='form-group'>
					<label >To</label>
					<div class='input-group date' id='to_date'> 
					<input type="text" class="form-control" name="to_date" placeholder="Select To Date" id="to_date" /> 
					<span class="input-group-addon">
					<span class="glyphicon glyphicon-calendar"></span>
				    </span>
				    </div>
					<script type="text/javascript">
						      jQuery('#to_date').datepicker({
					     	  format: "dd-mm-yyyy"
									});  
																						
					</script>
					</div>
					</div>
					</div>
					</div>
		        	<!-- <div id="filter-panel" class="collapse filter-panel">
		            <div class="panel-default">
		            <div class="panel-body" style="padding: 0;">
		         	<div class='col-sm-3'>
					<div class='form-group'>
					<label>IO.No</label> <input type="text" class="form-control" name="Io_No" id="Io_No" placeholder="IO.No" /> 
					</div>
					</div>
					<div class='col-sm-3'>
					<div class='form-group'>
					<label >Customer </label> 
					<input type="text" class="form-control" name="Customer" id="Customer" placeholder="Customer" /> 
					</div>
					</div>
					<div class='col-sm-3'>
					<div class='form-group'>
					<label >Group</label> <input  type="text" class="form-control" name="group" placeholder="Group" id="group"  />
					</div>
					</div>
					 
			        </div>
		            </div>
		            </div>    
		     		 <button type="button" class="btn btn-primary" onclick="searchSalesQuotation()">
					<i class="fa fa-search"></i><span>&nbsp;Search</span>
					</button>
					<button type="button" class="btn btn-primary" name="clear" id="clear" onclick="clearSQSearch();">
					<i class="glyphicon glyphicon-remove-circle"></i><span>&nbsp;Clear</span>
					</button>
					<button type="button" class="btn btn-primary"data-toggle="collapse" data-target="#filter-panel">
					<span class="glyphicon glyphicon-filter"></span> Advanced Search
					</button> -->
	</div>
                     
	<div class="row">	
		<div class="row paginationstyle">		
			<div class='col-sm-12'>    
				 <ul class="pagination pull-right" style="padding:5px;">
					<li class="disabled"><a href="#">&laquo;</a></li>
					<li class="active"><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">&raquo;</a></li>
				</ul>
				<ul class="pagination pull-left" style="padding-top:5px;">
					
					<li>
						<a href='#'  data-toggle="tooltip" title="Refresh">
						<i class="fa fa-refresh"></i>
						</a> 
					</li>
					<li>
						<a href='#' data-toggle="tooltip" title="Reorder columns">
						<i class="fa fa-reorder"></i>
						</a> 
					</li>
				</ul>
				<div class="input-group-btn pull-left" style="padding-top:5px;">
                    <div class="btn-group">
                        <div class="dropdown dropdown-lg">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="glyphicon glyphicon-filter text-primary" ></span>&nbsp;Filter&nbsp;<span class="caret"></span></button>
                            <div id="filter-dropdown" class="dropdown-menu dropdown-menu-left"  >
                                <div class="row">
				                    <div class='col-sm-6'>
					<div class='form-group'>
					<label>IO.No</label> <input type="text" class="form-control" name="Io_No" id="Io_No" placeholder="IO.No" /> 
					</div>
					</div>
					<div class='col-sm-6'>
					<div class='form-group'>
					<label >Customer </label> 
					<input type="text" class="form-control" name="Customer" id="Customer" placeholder="Customer" /> 
					</div>
					</div>
					<div class='col-sm-6'>
					<div class='form-group'>
					<label >Group</label> <input  type="text" class="form-control" name="group" placeholder="Group" id="group"  />
					</div>
					</div> 
                                 </div>
                                 	<div class="row">
                                  <button type="button"  class="btn btn-primary pull-left" onclick="clearSESearch();"><span class="glyphicon glyphicon-remove-circle" ></span>&nbsp;Reset </button>
                                  <button type="button"  id="search" class="btn btn-primary pull-right" onclick="searchSalesEnquiry()"><span class="glyphicon glyphicon-search" ></span>&nbsp;Apply</button>
                                  </div>
                            </div>
                        </div>
                    </div>
                    </div>
			</div>
		</div>
		
		<div class="row table-responsive">
		<table class="table table-bordered table-condensed">
		     <thead>
		    <tr class="header">
				<th >S.No.</th>
						  <th>IO NO</th> 
						  <th>Customer</th>
						  <th >Buyer PO</th>
						   <th>Style</th>
						  <th >Color</th>
						  <th >Order Qty</th>
						  <th >Ship Date</th>
						  <th >Leather</th>
						  <th >Lining</th>
						  <th >Fittings</th>
						  <th >Zipper</th>
						  <th>Label</th>
						  <th >Components</th>
						  <th >Packing Materials</th>
			    </tr>
		    </thead>
		    <tbody>	
		    
		      <tr height="20">
                <td height="20" align="right">1</td>
		        <td height="34" width="64">475.1516</td>
		        <td width="65">CustomerA</td>
		        <td width="64">A45556</td>
		        <td width="64">Tote Bag</td>
		        <td width="145">Black</td>
		        <td width="64">750</td>
		        <td width="64">2-Jan-16</td>
		        <td width="90">ETA - 2-Dec-2016</td>
		        <td width="93">ETA - 2-Dec-2016</td>
		        <td width="114">Stock Available</td>
		        <td width="84">ETA - 22-Dec-2016</td>
		        <td width="101">ETA -    22-Dec-2016</td>
		        <td width="90">Not Required</td>
		        <td width="64">ETA - 28-Dec-2016</td>
		      </tr>
		          <tr height="20">
                <td height="20" align="right">2</td>
		        <td height="34" width="64">476.1516</td>
		        <td width="65">CustomerA</td>
		        <td width="64">A45556</td>
		        <td width="64">Duffle    Bag</td>
		        <td width="145">Brown</td>
		        <td width="64">550</td>
		        <td width="64">2-Jan-16</td>
		        <td width="90">Stock Available</td>
		        <td width="93">ETA - 2-Dec-2016</td>
		        <td width="114">Received</td>
		        <td width="84">ETA - 22-Dec-2016</td>
		        <td width="101">ETA -    22-Dec-2016</td>
		        <td>Not Required</td>
		        <td width="64">ETA - 28-Dec-2016</td>
	            </tr>
		          <tr height="20">
                <td height="20" align="right">3</td>
		        <td height="34" width="64">477.1516</td>
		        <td width="65">CustomerB</td>
		        <td width="64">B657846</td>
		        <td width="64">Woven    Belt</td>
		        <td width="145">Dark    Brown</td>
		        <td width="64">1250</td>
		        <td width="64">4-Jan-16</td>
		        <td width="90">Received</td>
		        <td width="93">ETA - 2-Dec-2016</td>
		        <td width="114">ETA - 2-Dec-2016</td>
		        <td>Not Required</td>
		        <td>Not Required</td>
		        <td>Not Required</td>
		        <td width="64">ETA - 28-Dec-2016</td>
	            </tr>
		          <tr height="20">
                <td height="20" align="right">4</td>
		        <td height="34" width="64">478.1516</td>
		        <td width="65">CustomerB</td>
		        <td width="64">B657854</td>
		        <td width="64">Passcase</td>
		        <td width="145">Brown</td>
		        <td width="64">4520</td>
		        <td width="64">5-Jan-16</td>
		        <td width="90">ETA - 2-Dec-2016</td>
		        <td width="93">ETA - 12-Dec-2016</td>
		        <td width="114">ETA -    22-Dec-2016</td>
		        <td width="84">ETA - 28-Dec-2016</td>
		        <td width="101">ETA -    28-Dec-2016</td>
		        <td>Not Required</td>
		        <td width="64">ETA - 28-Dec-2016</td>
	            </tr>
		          <tr height="20">
                <td height="20" align="right">5</td>
		        <td height="34" width="64">479.1516</td>
		        <td width="65">CustomerC</td>
		        <td width="64">C7578465</td>
		        <td width="64">Mocassin</td>
		        <td width="145">Beige</td>
		        <td width="64">3520</td>
		        <td width="64">6-Jan-16</td>
		        <td width="90">ETA - 2-Dec-2016</td>
		        <td width="93">ETA - 12-Dec-2016</td>
		        <td width="114">ETA -    22-Dec-2016</td>
		        <td width="84">Not Required</td>
		        <td width="101">ETA -    28-Dec-2016</td>
		        <td width="90">ETA - 22-Dec-2016</td>
		        <td width="64">ETA - 28-Dec-2016</td>
	            </tr>
		          <tr height="20">
                <td height="20" align="right">6</td>
		        <td height="34" width="64">480.1516</td>
		        <td width="65">CustomerA</td>
		        <td width="64">A45575</td>
		        <td width="64">Woven    bag</td>
		        <td width="145">Navy    Blue</td>
		        <td width="64">250</td>
		        <td width="64">7-Jan-16</td>
		        <td width="90">ETA - 12-Dec-2016</td>
		        <td width="93">ETA - 12-Dec-2016</td>
		        <td width="114">Stock Available</td>
		        <td width="84">ETA - 28-Dec-2016</td>
		        <td width="101">ETA -    28-Dec-2016</td>
		        <td width="90">ETA - 22-Dec-2016</td>
		        <td width="64">ETA - 28-Dec-2016</td>
	            </tr>
		          <tr height="20">
                <td height="20" align="right">7</td>
		        <td height="34" width="64">481.1516</td>
		        <td width="65">CustomerC</td>
		        <td width="64">C7578479</td>
		        <td width="64">Cemented</td>
		        <td width="145">Tan</td>
		        <td width="64">1200</td>
		        <td width="64">8-Jan-16</td>
		        <td width="90">ETA - 12-Dec-2016</td>
		        <td width="93">PO Not Released</td>
		        <td width="114">PO Not Released</td>
		        <td>Not Required</td>
		        <td width="101">PO Not Released</td>
		        <td width="90">ETA - 22-Dec-2016</td>
		        <td width="64">PO Not Released</td>
	            </tr>
		          <tr height="20">
                <td height="20" align="right">8</td>
		        <td height="34" width="64">482.1516</td>
		        <td width="65">CustomerC</td>
		        <td width="64">C7578485</td>
		        <td width="64">Mocassin</td>
		        <td width="145">Brown</td>
		        <td width="64">2400</td>
		        <td width="64">9-Jan-16</td>
		        <td width="90">ETA - 21-Dec-2016</td>
		        <td width="93">PO Not Released</td>
		        <td width="114">PO Not Released</td>
		        <td>Not Required</td>
		        <td width="101">PO Not Released</td>
		        <td width="90">ETA - 28-Dec-2016</td>
		        <td width="64">PO Not Released</td>
	            </tr>
		          <tr height="20">
                <td height="20" align="right">9</td>
		        <td height="34" width="64">483.1516</td>
		        <td width="65">CustomerA</td>
		        <td width="64">A45620</td>
		        <td width="64">Swing    Bag</td>
		        <td width="145">White</td>
		        <td width="64">350</td>
		        <td width="64">10-Jan-16</td>
		        <td width="90">ETA - 22-Dec-2016</td>
		        <td width="93">PO Not Released</td>
		        <td width="114">PO Not Released</td>
		        <td width="84">PO Not Released</td>
		        <td width="101">PO Not Released</td>
		        <td>Not Required</td>
		        <td width="64">PO Not Released</td>
	            </tr>
		          <tr height="20">
                <td height="20" align="right">10</td>
		        <td height="35" width="64">484.1516</td>
		        <td width="65">CustomerB</td>
		        <td width="64">B657884</td>
		        <td width="64">Clutch</td>
		        <td width="145">Red</td>
		        <td width="64">450</td>
		        <td width="64">11-Jan-16</td>
		        <td width="90">ETA - 22-Dec-2016</td>
		        <td width="93">PO Not Released</td>
		        <td width="114">PO Not Released</td>
		        <td width="84">PO Not Released</td>
		        <td width="101">PO Not Released</td>
		        <td>Not Required</td>
		        <td width="64">PO Not Released</td>
	            </tr>
		    </tbody> 
	    </table>
	    </div>
       </div>           
     </div>  
    </div>
</div>

		<%-- <jsp:include page="../common/ColumnPreferences.jsp" /> --%>
		<jsp:include page="../common/Footer.jsp" />

	 <input type="hidden" name="servlet_name" id="servlet_name" />
   <input type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
   <input type="hidden" name="pageno" id="pageno" />
   <input type="hidden" name="request_type" id="request_type" />

	</form>
</body>
</html>
            