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
                    <h3 class="page-header">Stock Statement</h3>
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
					<label >Group</label> <input type="text" class="form-control" name="Group" id="Group" placeholder="Group" /> 
					</div>
					</div>
					<div class='col-sm-3'>
					<div class='form-group'>
					<label >Warehouse </label> 
					<input type="text" class="form-control" name="Warehouse" id="Warehouse" placeholder="Warehouse" /> 
					</div>
					</div>
					<div class='col-sm-3'>
					<div class='form-group'>
					<label >Item </label> <input  type="text" class="form-control" name="Item" placeholder="Item" id="buyerPO"  />
					</div>
					</div>
					 <div class='col-sm-3'>
				    <div class='form-group'>
					<label >Colour</label> <input	type="text" class="form-control" name="Colour" placeholder="Colour" id="Item" />
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
					<label >Group</label> <input type="text" class="form-control" name="Group" id="Group" placeholder="Group" /> 
					</div>
					</div>
					<div class='col-sm-6'>
					<div class='form-group'>
					<label >Warehouse </label> 
					<input type="text" class="form-control" name="Warehouse" id="Warehouse" placeholder="Warehouse" /> 
					</div>
					</div>
					<div class='col-sm-6'>
					<div class='form-group'>
					<label >Item </label> <input  type="text" class="form-control" name="Item" placeholder="Item" id="buyerPO"  />
					</div>
					</div>
					 <div class='col-sm-6'>
				    <div class='form-group'>
					<label >Colour</label> <input	type="text" class="form-control" name="Colour" placeholder="Colour" id="Item" />
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
		
			
		
		
		
		
		
		
		<table class="table table-bordered table-condensed" width="100%" >
		
		    <thead>
		    <tr class="header">
						  <th rowspan="2" >Store</th>
						  <th rowspan="2">Material</th>
						  <th rowspan="2">Color</th>
						  <th rowspan="2">UOM</th>
						  <th colspan="4">Qty</th>
						  <th colspan="4">Value</th>
			    </tr>
		      <tr id="tb-table-row" class="header">					                  		                  
					                  			
					           
							   <th>Op.Stock</th>
							   <th>Recd.</th>
							   <th>Issd</th>
							   <th>Cl.Stock</th>
							   <th>Op.Stock</th>
							   <th>Recd.</th>
							   <th>Issd</th>
							   <th>Cl.Stock</th>
			    </tr> 
		     <tr>
               <td height="34">LEATHER    STORE</td>
		       <td>COW BOOTY NAPPA    0.9-1.0</td>
		       <td>Black</td>
		       <td>Sq.Ft</td>
		       <td>0</td>
		       <td>136</td>
		       <td>0</td>
		       <td>136</td>
		       <td>0</td>
		       <td>14960</td>
		       <td>0</td>
		       <td>14960</td>
		       </tr>
		     <tr height="34">
               <td height="34">LEATHER STORE</td>
		       <td>COW    FINISHED LINING 0.7/0.9</td>
		       <td>MAHOGANY</td>
		       <td>Sq.Ft</td>
		       <td>0</td>
		       <td>1390</td>
		       <td>1068</td>
		       <td>322</td>
		       <td>0</td>
		       <td>166800</td>
		       <td>128160</td>
		       <td>38640</td>
		       </tr>
		     <tr height="51">
               <td height="51">LEATHER STORE</td>
		       <td>LIN    1988 COW DD LINING 0.9/1.0</td>
		       <td>Black</td>
		       <td>Sq.Ft</td>
		       <td>1622</td>
		       <td>290</td>
		       <td>1912</td>
		       <td>0</td>
		       <td>72990</td>
		       <td>13050</td>
		       <td>86040</td>
		       <td>0</td>
		       </tr>
		     <tr height="51">
               <td height="51">LEATHER STORE</td>
		       <td>LIN    1901 COW DD LINING 1.1/1.3</td>
		       <td>DK    BROWN</td>
		       <td>Sq.Ft</td>
		       <td>0</td>
		       <td>43055</td>
		       <td>43055</td>
		       <td>0</td>
		       <td>0</td>
		       <td>1937475</td>
		       <td>1937475</td>
		       <td>0</td>
		       </tr>
		     <tr height="51">
               <td height="51">MATERIAL STORE</td>
		       <td>SOLE    HALF RUBBER KRISTIN</td>
		       <td>MAHOGANY</td>
		       <td>PAIR</td>
		       <td>0</td>
		       <td>4567</td>
		       <td>3456</td>
		       <td>1111</td>
		       <td>0</td>
		       <td>1370100</td>
		       <td>1036800</td>
		       <td>333300</td>
		       </tr>
		     <tr height="34">
               <td height="34">MATERIAL STORE</td>
		       <td>NO.8    YKK SLIDER 2WAY</td>
		       <td>ANTIQUE    BLK</td>
		       <td>NOS</td>
		       <td>0</td>
		       <td>8629</td>
		       <td>8629</td>
		       <td>0</td>
		       <td>0</td>
		       <td>10786.25</td>
		       <td>10786.25</td>
		       <td>0</td>
		       </tr>
		     <tr height="34">
               <td height="34">MATERIAL STORE</td>
		       <td>No.5    YKK Coil Zipper</td>
		       <td>BLACK</td>
		       <td>MTR</td>
		       <td>0</td>
		       <td>436</td>
		       <td>0</td>
		       <td>436</td>
		       <td>0</td>
		       <td>3052</td>
		       <td>0</td>
		       <td>3052</td>
		       </tr>
		     <tr height="34">
               <td height="34">MATERIAL STORE</td>
		       <td>THK    40 APTAN WHITE</td>
		       <td>Z0583</td>
		       <td>C-1000</td>
		       <td>0</td>
		       <td>45</td>
		       <td>34</td>
		       <td>11</td>
		       <td>0</td>
		       <td>5625</td>
		       <td>4250</td>
		       <td>1375</td>
		       </tr>
		     <tr height="34">
               <td height="34">MATERIAL STORE</td>
		       <td>THK    40 APTAN BLACK</td>
		       <td>Z0040</td>
		       <td>C-1500</td>
		       <td>8</td>
		       <td>11</td>
		       <td>12</td>
		       <td>7</td>
		       <td>1160</td>
		       <td>1595</td>
		       <td>1740</td>
		       <td>1015</td>
		       </tr>
		     <tr height="34">
               <td height="34">MATERIAL STORE</td>
		       <td>Label-06294    Transperant</td>
		       <td>NA</td>
		       <td>NOS</td>
		       <td>901</td>
		       <td>2181</td>
		       <td>2190</td>
		       <td>892</td>
		       <td>1802</td>
		       <td>4362</td>
		       <td>4380</td>
		       <td>1784</td>
		       </tr>
		     <tr height="20">
               <td height="20"></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td>Total</td>
		       <td align="right">75952</td>
		       <td align="right">3527805.25</td>
		       <td align="right">3209631.25</td>
		       <td align="right">394126</td>
		       </tr>
   		         </thead>
   		  <tbody>
		    </tbody>
	    </table>
	   	</div>
       </div>           
     </div>  
    </div>
</div>

		<jsp:include page="../common/Footer.jsp" />
		
		<input type="hidden" name="pageno" id="pageno" />
   <input type="hidden" name="request_type" id="request_type" />

	</form>
</body>
</html>
            