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

<style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}

</style>


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
<script language="javascript">
var contextpath = "${pageContext.request.contextPath}";
</script>
<body>
	<form action="" id="tpcslogin" method="post" role="form">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Order.Transactions.InternalOrder" name="screen_name" />
			</jsp:include>
<div id="page-wrapper">
<div id="page-inner">
	<div class="row">
                <div class="col-md-11 col-sm-12 col-xs-8">
                    <h3 class="page-header">Pending Internal Order  </h3>
                </div>	
    </div>
    <div class="row">
					<div class="panel-default">
					<div class="panel-body" style="padding: 0;">
					<div class='col-sm-3'>
					<div class='form-group'>
					<label >Ship Date From</label> 
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
					<label >Buyer Name</label> <input type="text" class="form-control" name="buyer_name" id="buyer_name" placeholder="Buyer Name" /> 
					</div>
					</div>
					<div class='col-sm-3'>
					<div class='form-group'>
					<label >Buyer PO </label> 
					<input type="text" class="form-control" name="buyer_po" id="buyer_po" placeholder="Buyer PO" /> 
					</div>
					</div>
					<div class='col-sm-3'>
					<div class='form-group'>
					<label >IO No.</label> <input  type="text" class="form-control" name="io_no" placeholder="IO No." id="io_no"  />
					</div>
					</div>
					<div class='col-sm-3'>
				    <div class='form-group'>
					<label >Material</label> <input	type="text" class="form-control" name="bl_order_no" placeholder="Material" id="bl_order_no" />
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
					<label >Buyer Name</label> <input type="text" class="form-control" name="buyer_name" id="buyer_name" placeholder="Buyer Name" /> 
					</div>
					</div>
					<div class='col-sm-6'>
					<div class='form-group'>
					<label >Buyer PO </label> 
					<input type="text" class="form-control" name="buyer_po" id="buyer_po" placeholder="Buyer PO" /> 
					</div>
					</div>
					<div class='col-sm-6'>
					<div class='form-group'>
					<label >IO No.</label> <input  type="text" class="form-control" name="io_no" placeholder="IO No." id="io_no"  />
					</div>
					</div>
					<div class='col-sm-6'>
				    <div class='form-group'>
					<label >Material</label> <input	type="text" class="form-control" name="bl_order_no" placeholder="Material" id="bl_order_no" />
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
						  <th>Order No</th> 
						  <th>Ship Date</th>
						  <th>Customer</th>
						  <th >Buyer PO</th>
						  <th >Group</th>
						  <th >Product</th>
						  <th >Description</th>
						  <th >Color</th>
						  <th >UOM</th>
						  <th >Order.Qty.</th>
						  <th >Shipped</th>
						  <th>Balance To Ship</th>
			    </tr>
		    </thead>
		    <tbody>	
		    
		      <tr height="20">
                <td height="20" align="right">1</td>
		        <td>475.1516</td>
		        <td>2-Jan-16</td>
		        <td>CustomerA</td>
		        <td>A45556</td>
		        <td>Bag</td>
		        <td>4571-A</td>
		        <td>Tote Bag</td>
		        <td>Black</td>
		        <td>PCS</td>
		        <td>750</td>
		        <td>0</td>
		        <td>750</td>
		        </tr>
		      <tr height="20">
                <td height="20" align="right">2</td>
		        <td>476.1516</td>
		        <td>2-Jan-16</td>
		        <td>CustomerA</td>
		        <td>A45556</td>
		        <td>Bag</td>
		        <td>5478-A</td>
		        <td>Duffle    Bag</td>
		        <td>Brown</td>
		        <td>PCS</td>
		        <td>550</td>
		        <td>0</td>
		        <td>550</td>
		        </tr>
		      <tr height="34">
                <td height="34" align="right">3</td>
		        <td>477.1516</td>
		        <td>4-Jan-16</td>
		        <td>CustomerB</td>
		        <td>B657846</td>
		        <td>Belt</td>
		        <td>BLK2892-DB</td>
		        <td>Woven    Belt</td>
		        <td>Dark    Brown</td>
		        <td>PCS</td>
		        <td>1250</td>
		        <td>650</td>
		        <td>600</td>
		        </tr>
		      <tr height="20">
                <td height="20" align="right">4</td>
		        <td>478.1516</td>
		        <td>5-Jan-16</td>
		        <td>CustomerB</td>
		        <td>B657854</td>
		        <td>Wallet</td>
		        <td>5478-01</td>
		        <td>Passcase</td>
		        <td>Brown</td>
		        <td>PCS</td>
		        <td>4520</td>
		        <td>2500</td>
		        <td>2020</td>
		        </tr>
		      <tr height="34">
                <td height="34" align="right">5</td>
		        <td>479.1516</td>
		        <td>6-Jan-16</td>
		        <td>CustomerC</td>
		        <td>C7578465</td>
		        <td>Shoe</td>
		        <td>G355R4</td>
		        <td>Mocassin</td>
		        <td>Beige</td>
		        <td>PAIR</td>
		        <td>3520</td>
		        <td>1250</td>
		        <td>2270</td>
		        </tr>
		      <tr height="34">
                <td height="34" align="right">6</td>
		        <td>480.1516</td>
		        <td>7-Jan-16</td>
		        <td>CustomerA</td>
		        <td>A45575</td>
		        <td>Bag</td>
		        <td>T35W4</td>
		        <td>Woven    bag</td>
		        <td>Navy    Blue</td>
		        <td>PCS</td>
		        <td>250</td>
		        <td>0</td>
		        <td>250</td>
		        </tr>
		      <tr height="34">
                <td height="34" align="right">7</td>
		        <td>481.1516</td>
		        <td>8-Jan-16</td>
		        <td>CustomerC</td>
		        <td>C7578479</td>
		        <td>Shoe</td>
		        <td>C23445</td>
		        <td>Cemented</td>
		        <td>Tan</td>
		        <td>PAIR</td>
		        <td>1200</td>
		        <td>0</td>
		        <td>1200</td>
		        </tr>
		      <tr height="34">
                <td height="34" align="right">8</td>
		        <td>482.1516</td>
		        <td>9-Jan-16</td>
		        <td>CustomerC</td>
		        <td>C7578485</td>
		        <td>Shoe    Upper</td>
		        <td>C34543</td>
		        <td>Mocassin</td>
		        <td>Brown</td>
		        <td>PAIR</td>
		        <td>2400</td>
		        <td>0</td>
		        <td>2400</td>
		        </tr>
		      <tr height="34">
                <td height="34" align="right">9</td>
		        <td>483.1516</td>
		        <td>10-Jan-16</td>
		        <td>CustomerA</td>
		        <td>A45620</td>
		        <td>Bag</td>
		        <td>457515-0S</td>
		        <td>Swing    Bag</td>
		        <td>White</td>
		        <td>PCS</td>
		        <td>350</td>
		        <td>0</td>
		        <td>350</td>
		        </tr>
		      <tr height="35">
                <td height="35" align="right">10</td>
		        <td>484.1516</td>
		        <td>11-Jan-16</td>
		        <td>CustomerB</td>
		        <td>B657884</td>
		        <td>Wallet</td>
		        <td>547845-SD</td>
		        <td>Clutch</td>
		        <td>Red</td>
		        <td>PCS</td>
		        <td>450</td>
		        <td>0</td>
		        <td>450</td>
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
            