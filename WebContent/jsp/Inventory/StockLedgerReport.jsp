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
                    <h3 class="page-header">Stock Ledger</h3>
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
		        	<!-- <!-- <div id="filter-panel" class="collapse filter-panel">
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
		<table width="878" class="table table-bordered table-condensed">
		    <thead>
		    <tr class="header">
				<th  rowspan="2">Category</th>
						  <th rowspan="2" >Item&nbsp;Code</th>
						  <th rowspan="2">Item</th>
						  <th rowspan="2">Colour</th>
						  <th rowspan="2">UOM</th>
						  <th rowspan="2">Re-Order</th>
						  <th rowspan="2">Rate</th>
						  <th rowspan="2">Date</th>
						  <th rowspan="2">TrTag&nbsp;:&nbsp;TrNo</th>
						  <th rowspan="2">Io.No</th>
						  <th colspan="4">Quantity</th>
						  <th colspan="4">Value</th>
						  <th rowspan="2">Price</th>
						  <th rowspan="2">Cl.Stk&nbsp;Pcs</th>
		    </tr>
		     <tr class="header">					                  		                  
					                  			
					           
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
               <td height="20">1.Lining</td>
		       <td></td>
		       <td></td>
		       <th></th>
		       <th></th>
		       <th></th>
		       <th></th>
		       <th></th>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <th></th>
		       </tr>
		     <tr height="20">
               <td height="20"></td>
		       <td>PST00001</td>
		       <td>Red Satin Plain</td>
		       <td>BLACK</td>
		       <td>Mtrs</td>
		       <td align="right">0</td>
		       <td align="right">46</td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td align="right">18</td>
		       <td></td>
		       <td></td>
		       <td align="right">18</td>
		       <td align="right">833.9999</td>
		       <td></td>
		       <td></td>
		       <td align="right">833.9999</td>
		       <td></td>
		       <td align="right">0</td>
		       </tr>
		     <tr height="20">
               <td height="20">2.Accessories</td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       </tr>
		     <tr height="20">
               <td height="20"></td>
		       <td>PST00002</td>
		       <td>Golden Plain Satin</td>
		       <td>Brown</td>
		       <td>Nos</td>
		       <td align="right">0</td>
		       <td align="right">5</td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td align="right">15</td>
		       <td></td>
		       <td></td>
		       <td align="right">15</td>
		       <td align="right">75</td>
		       <td></td>
		       <td></td>
		       <td align="right">75</td>
		       <td></td>
		       <td align="right">0</td>
		       </tr>
		     <!-- <tr height="20">
               <td height="20"></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       </tr> -->
		     <tr height="20">
               <td height="20"></td>
		       <td>PST00003</td>
		       <td>Light Grey Satin Plain</td>
		       <td>Dark Brown</td>
		       <td>Cone</td>
		       <td align="right">0</td>
		       <td align="right">6</td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td align="right">5</td>
		       <td></td>
		       <td></td>
		       <td align="right">5</td>
		       <td align="right">25</td>
		       <td></td>
		       <td></td>
		       <td align="right">25</td>
		       <td></td>
		       <td align="right">0</td>
		       </tr>
		     <!-- <tr height="20">
               <td height="20"></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       </tr> -->
		     <tr height="20">
               <td height="20"></td>
		       <td>PST00004</td>
		       <td>Dark Grey Satin Plain</td>
		       <td>Coffee Brown</td>
		       <td>Kgs</td>
		       <td align="right">0</td>
		       <td align="right">7</td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td align="right">8</td>
		       <td></td>
		       <td></td>
		       <td align="right">8</td>
		       <td align="right">40</td>
		       <td></td>
		       <td></td>
		       <td align="right">40</td>
		       <td></td>
		       <td align="right">0</td>
		       </tr>
		     <tr height="20">
               <td height="20"></td>
		       <td>PST00005</td>
		       <td>Off White Satin Plain</td>
		       <td>Cream</td>
		       <td>cm</td>
		       <td align="right">0</td>
		       <td align="right">8</td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td align="right">3</td>
		       <td></td>
		       <td></td>
		       <td align="right">3</td>
		       <td align="right">15</td>
		       <td></td>
		       <td></td>
		       <td align="right">15</td>
		       <td></td>
		       <td align="right">0</td>
		       </tr>
		     <tr height="20">
               <td height="20">3.Label</td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       </tr>
		     <tr height="20">
               <td height="20"></td>
		       <td>PST00006</td>
		       <td>Maroon Satin Plain</td>
		       <td>Navy Blue</td>
		       <td>Inches</td>
		       <td align="right">0</td>
		       <td align="right">9</td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td align="right">128</td>
		       <td></td>
		       <td></td>
		       <td align="right">128</td>
		       <td align="right">0</td>
		       <td></td>
		       <td></td>
		       <td align="right">0</td>
		       <td></td>
		       <td align="right">0</td>
		       </tr>
		     <!-- <tr height="20">
               <td height="20"></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td></td>
		       </tr> -->
		     <tr height="20">
               <td height="20"></td>
		       <td>PST00007</td>
		       <td>Golden Cream Embossed Satin</td>
		       <td>Off White</td>
		       <td>Skin</td>
		       <td align="right">0</td>
		       <td align="right">10</td>
		       <td></td>
		       <td></td>
		       <td></td>
		       <td align="right">418</td>
		       <td></td>
		       <td></td>
		       <td align="right">418</td>
		       <td align="right">0</td>
		       <td></td>
		       <td></td>
		       <td align="right">0</td>
		       <td></td>
		       <td align="right">0</td>
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
            