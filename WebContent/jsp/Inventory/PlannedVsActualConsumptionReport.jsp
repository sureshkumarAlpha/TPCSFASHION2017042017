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
                    <h3 class="page-header">Stock Aging Analysis</h3>
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
					<label >Customer </label> <input  type="text" class="form-control" name="Customer" placeholder="Customer" id="Customer"  />
					</div>
					</div>
					 <div class='col-sm-6'>
				   	<label >Issue Type</label> 
					<div class="box-group ">
							<div class="form-group col-sm-6 ">
								<select class="form-control" id="issue_type" name="issue_type">
									<option>Lot Issue</option>
									<option>Excess Issue</option>
									<option>Sample Issue</option>
									<option>Re-Cut Issue</option>
									<option>Rework Issue</option>
								</select> 
							</div>
							</div>
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
					<label >Customer </label> <input  type="text" class="form-control" name="Customer" placeholder="Customer" id="Customer"  />
					</div>
					</div>
					 <div class='col-sm-6'>
				   	<label >Issue Type</label> 
					<div class="box-group ">
							<div class="form-group col-sm-12 ">
								<select class="form-control" id="issue_type" name="issue_type">
									<option>Lot Issue</option>
									<option>Excess Issue</option>
									<option>Sample Issue</option>
									<option>Re-Cut Issue</option>
									<option>Rework Issue</option>
								</select> 
							</div>
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
				<th width="37" >Order No </th>
						  <th width="143" >Product</th>
						  <th width="106">Order Qty </th>
						  <th width="94">Group</th> 
						  <th width="86">Material</th>
						   <th width="134">Color</th>
						  <th width="64">UOM </th>
						  <th width="123">Planned</th>
						  <th width="116">Actual</th>
						  <th width="64">Variance</th>
						  <th width="64">Variance%</th>
			  </tr>
		    <tr height="20">
              <td height="20" align="right">475.1516</td>
		      <td>Tote Bag</td>
		      <td>750</td>
		      <td>Leather</td>
		      <td>COW BURNISH</td>
		      <td>BROWN</td>
		      <td>SQFT</td>
		      <td>129838.8</td>
		      <td>125610</td>
		      <td>4228.8</td>
		      <td>3.26 </td>
		      </tr>
		    <tr height="34">
              <td height="34" align="right">475.1516</td>
		      <td>Tote Bag</td>
		      <td>750</td>
		      <td>Lining</td>
		      <td>LINING    COTTON STRIPE 44&quot; 601</td>
		      <td>BEIGE</td>
		      <td>MTR</td>
		      <td>12377.2</td>
		      <td>12168</td>
		      <td>209.2</td>
		      <td>1.69 </td>
		      </tr>
		    <tr height="20">
              <td height="20" align="right">475.1516</td>
		      <td>Tote Bag</td>
		      <td>750</td>
		      <td>Lining</td>
		      <td>MICRO    FIBRE</td>
		      <td>BLACK</td>
		      <td>MTR</td>
		      <td>870</td>
		      <td>871</td>
		      <td>-1</td>
		      <td>-0.11 </td>
		      </tr>
		    <tr height="34">
              <td height="34" align="right">475.1516</td>
		      <td>Tote Bag</td>
		      <td>750</td>
		      <td>Lining</td>
		      <td>NON    WOVEN FABRIC 70 GSM</td>
		      <td>BLACK</td>
		      <td>MTR</td>
		      <td>580</td>
		      <td>576</td>
		      <td>4</td>
		      <td>0.69 </td>
		      </tr>
		    <tr height="34">
              <td height="34" align="right">475.1516</td>
		      <td>Tote Bag</td>
		      <td>750</td>
		      <td>Lining</td>
		      <td>TRANSPARENT    PLAIN 0.25MM</td>
		      <td>NA</td>
		      <td>MTR</td>
		      <td>46.4</td>
		      <td>45.2</td>
		      <td>1.2</td>
		      <td>2.59 </td>
		      </tr>
		    <tr height="20">
              <td height="20" align="right">475.1516</td>
		      <td>Tote Bag</td>
		      <td>750</td>
		      <td>Boards</td>
		      <td>BOARD    IRON</td>
		      <td>NA</td>
		      <td>PCS</td>
		      <td>1044</td>
		      <td>1044</td>
		      <td>0</td>
		      <td>-</td>
		      </tr>
		    <tr height="34">
              <td height="34" align="right">475.1516</td>
		      <td>Tote Bag</td>
		      <td>750</td>
		      <td>Boards</td>
		      <td>BOARD    LEVO 0.4 MM</td>
		      <td>NA</td>
		      <td>PCS</td>
		      <td>580</td>
		      <td>580</td>
		      <td>0</td>
		    <td>-</td>
		      </tr>
		    <tr height="34">
              <td height="34" align="right">475.1516</td>
		      <td>Tote Bag</td>
		      <td>750</td>
		      <td>Boards</td>
		      <td>BOARD    WHITE 24 KG</td>
		      <td>NA</td>
		      <td>PCS</td>
		      <td>169</td>
		      <td>169</td>
		      <td>0</td>
		     <td>-</td>
		      </tr>
		    <tr height="34">
              <td height="34" align="right">475.1516</td>
		      <td>Tote Bag</td>
		      <td>750</td>
		      <td>Buckles</td>
		      <td>ADJUSTER    FLAT 1 1/4&quot; DULL NICKLE</td>
		      <td>NA</td>
		      <td>PCS</td>
		      <td>5800</td>
		      <td>5800</td>
		      <td>0</td>
		   <td>-</td>
		      </tr>
		    <tr height="34">
              <td height="34" align="right">475.1516</td>
		      <td>Tote Bag</td>
		      <td>750</td>
		      <td>Buckles</td>
		      <td>BUCKLE    WATCH 3/8 &quot; DULL NICKLE</td>
		      <td>NA</td>
		      <td>PCS</td>
		      <td>5800</td>
		      <td>5802</td>
		      <td>-2</td>
		      <td> -0.03 </td>
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
            