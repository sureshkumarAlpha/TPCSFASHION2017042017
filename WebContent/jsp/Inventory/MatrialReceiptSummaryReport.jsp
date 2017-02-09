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
                    <h3 class="page-header">Material Receipt Summary</h3>
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
		<table width="878" class="table table-bordered table-condensed">
		    <thead>
		    <tr class="header">
				<th width="37" >S.No.</th>
						  <th >Group</th>
						  <th>Material</th>
						  <th>Color</th>
						  <th>UOM</th> 
						  <th>Qty</th>
						  <th>Rate</th>
						  <th>Value</th>
		    </tr>
		    <tr height="20">
              <td height="20" align="right">1</td>
		      <td height="51" width="106">Leather</td>
		      <td width="94">COW BOOTY NAPPA    0.9-1.0</td>
		      <td width="86">Black</td>
		      <td width="134">Sq.Ft</td>
		      <td width="64">1234</td>
		      <td width="123">110</td>
		      <td width="116">135740</td>
		    </tr>
		    <tr height="20">
              <td height="20" align="right">2</td>
		      <td height="51" width="106">Leather</td>
		      <td width="94">COW    FINISHED LINING 0.7/0.9</td>
		      <td width="86">MAHOGANY</td>
		      <td width="134">Sq.Ft</td>
		      <td width="64">507</td>
		      <td width="123">120</td>
		      <td width="116">60840</td>
		    </tr>
		    <tr height="20">
              <td height="20" align="right">3</td>
		      <td height="51" width="106">Sole</td>
		      <td width="94">SOLE    HALF RUBBER KRISTIN</td>
		      <td width="86">MAHOGANY</td>
		      <td width="134">PAIR</td>
		      <td width="64">346</td>
		      <td width="123">300</td>
		      <td width="116">103800</td>
		    </tr>
		    <tr height="20">
              <td height="20" align="right">4</td>
		      <td height="51" width="106">Zippers &amp; Runners</td>
		      <td width="94">NO.8    YKK SLIDER 2WAY</td>
		      <td width="86">ANTIQUE    BLK</td>
		      <td width="134">NOS</td>
		      <td width="64">1245</td>
		      <td width="123">1.25</td>
		      <td width="116">1556.25</td>
		    </tr>
		    <tr height="20">
              <td height="20" align="right">5</td>
		      <td height="51" width="106">Lining</td>
		      <td width="94">LIN    1988 COW DD LINING 0.9/1.0</td>
		      <td width="86">Black</td>
		      <td width="134">Sq.Ft</td>
		      <td width="64">4567</td>
		      <td width="123">45</td>
		      <td width="116">205515</td>
		    </tr>
		    <tr height="20">
              <td height="20" align="right">6</td>
		      <td height="51" width="106">Lining</td>
		      <td width="94">LIN    1901 COW DD LINING 1.1/1.3</td>
		      <td width="86">DK    BROWN</td>
		      <td width="134">Sq.Ft</td>
		      <td width="64">5412</td>
		      <td width="123">45</td>
		      <td width="116">243540</td>
		    </tr>
		    <tr height="20">
              <td height="20" align="right">7</td>
		      <td height="34" width="106">Zippers &amp; Runners</td>
		      <td width="94">No.5    YKK Coil Zipper</td>
		      <td width="86">BLACK</td>
		      <td width="134">MTR</td>
		      <td width="64">75</td>
		      <td width="123">7</td>
		      <td width="116">525</td>
		    </tr>
		    <tr height="20">
              <td height="20" align="right">8</td>
		      <td height="51" width="106">Thread</td>
		      <td width="94">THK    40 APTAN WHITE</td>
		      <td width="86">Z0583</td>
		      <td width="134">C-1000</td>
		      <td width="64">25</td>
		      <td width="123">125</td>
		      <td width="116">3125</td>
		    </tr>
		    <tr height="20">
              <td height="20" align="right">9</td>
		      <td height="51" width="106">Thread</td>
		      <td width="94">THK    40 APTAN BLACK</td>
		      <td width="86">Z0040</td>
		      <td width="134">C-1500</td>
		      <td width="64">25</td>
		      <td width="123">145</td>
		      <td width="116">3625</td>
		    </tr>
		    <tr height="20">
              <td height="20" align="right">10</td>
		      <td height="34" width="106">Label</td>
		      <td width="94">Label-06294    Transperant</td>
		      <td width="86">NA</td>
		      <td width="134">NOS</td>
		      <td width="64">450</td>
		      <td width="123">2</td>
		      <td width="116">900</td>
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
            