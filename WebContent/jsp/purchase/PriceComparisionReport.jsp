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
                    <h3 class="page-header">Price Comparison</h3>
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
					<label>Suplier</label> <input type="text" class="form-control" name="Suplier" id="Suplier" placeholder="Suplier" /> 
					</div>
					</div>
					<div class='col-sm-6'>
					<div class='form-group'>
					<label >Color </label> 
					<input type="text" class="form-control" name="Color" id="Color" placeholder="Color" /> 
					</div>
					</div>
					<div class='col-sm-6'>
					<div class='form-group'>
					<label >UOM</label> <input  type="text" class="form-control" name="UOM" placeholder="UOM" id="UOM"  />
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
						  <th>Supplier</th> 
						  <th>Item</th>
						  <th >Color</th>
						   <th>UOM</th>
						  <th >Jan</th>
						  <th >Feb</th>
						  <th >Mar</th>
						  <th >Apr</th>
						  <th >May</th>
						  <th >Jun</th>
						  <th >Jul</th>
						  <th>Aug</th>
						  <th >Sep</th>
						  <th >Oct</th>
						  <th >Nov</th>
						  <th >Dec</th>
			    </tr>
		    </thead>
		    <tbody>	
		    
		      <tr height="20">
                <td height="20" align="right">1</td>
		        <td height="68" width="64">Tannery A</td>
		        <td width="65">COW BOOTY NAPPA    0.9-1.0</td>
		        <td width="64">Black</td>
		        <td width="64">Sq.Ft</td>
		        <td width="145">110</td>
		        <td width="64">110</td>
		        <td width="64">112</td>
		        <td width="90">112</td>
		        <td width="93">108</td>
		        <td width="114">111</td>
		        <td width="84">111</td>
		        <td width="101">110</td>
		        <td width="90">110</td>
		        <td width="64">110.5</td>
		        <td width="65">110.5</td>
		        <td width="64">112</td>
		      </tr>
		          <tr height="20">
                <td height="20" align="right">2</td>
		        <td height="68" width="64">Tannery A</td>
		        <td width="65">COW    FINISHED LINING 0.7/0.9</td>
		        <td width="64">MAHOGANY</td>
		        <td width="64">Sq.Ft</td>
		        <td width="145">120</td>
		        <td width="64">120</td>
		        <td width="64">120.5</td>
		        <td width="90">121</td>
		        <td width="93">121</td>
		        <td width="114">121</td>
		        <td width="84">121</td>
		        <td width="101">122</td>
		        <td width="90">122</td>
		        <td width="64">122</td>
		        <td width="65">122</td>
		        <td width="64">122</td>
	            </tr>
		          <tr height="20">
                <td height="20" align="right">3</td>
		        <td height="68" width="64">SUN SOLES</td>
		        <td width="65">SOLE    HALF RUBBER KRISTIN</td>
		        <td width="64">MAHOGANY</td>
		        <td width="64">PAIR</td>
		        <td width="145">300</td>
		        <td width="64">300</td>
		        <td width="64">&nbsp;</td>
		        <td width="90">&nbsp;</td>
		        <td width="93">300</td>
		        <td width="114">300</td>
		        <td width="84">&nbsp;</td>
		        <td width="101">&nbsp;</td>
		        <td width="90">300</td>
		        <td width="64">305</td>
		        <td width="65">305</td>
		        <td width="64">305</td>
	            </tr>
		          <tr height="20">
                <td height="20" align="right">4</td>
		        <td height="68" width="64">S S Creation</td>
		        <td width="65">NO.8    YKK SLIDER 2WAY</td>
		        <td width="64">ANTIQUE    BLK</td>
		        <td width="64">NOS</td>
		        <td width="145">1.25</td>
		        <td width="64">1.25</td>
		        <td width="64">&nbsp;</td>
		        <td width="90">1.25</td>
		        <td width="93">&nbsp;</td>
		        <td width="114">1.25</td>
		        <td width="84">1.25</td>
		        <td width="101">&nbsp;</td>
		        <td width="90">1.25</td>
		        <td width="64">1.25</td>
		        <td width="65">1.25</td>
		        <td width="64">&nbsp;</td>
	            </tr>
		          <tr height="20">
                <td height="20" align="right">5</td>
		        <td height="68" width="64">Tannery B</td>
		        <td width="65">LIN    1988 COW DD LINING 0.9/1.0</td>
		        <td width="64">Black</td>
		        <td width="64">Sq.Ft</td>
		        <td width="145">45</td>
		        <td width="64">45</td>
		        <td width="64">45.25</td>
		        <td width="90">45.25</td>
		        <td width="93">45.25</td>
		        <td width="114">45.25</td>
		        <td width="84">45.75</td>
		        <td width="101">45.75</td>
		        <td width="90">45.75</td>
		        <td width="64">45.75</td>
		        <td width="65">45.75</td>
		        <td width="64">45.75</td>
	            </tr>
		          <tr height="20">
                <td height="20" align="right">6</td>
		        <td height="68" width="64">Tannery B</td>
		        <td width="65">LIN    1901 COW DD LINING 1.1/1.3</td>
		        <td width="64">DK    BROWN</td>
		        <td width="64">Sq.Ft</td>
		        <td width="145">45</td>
		        <td width="64">45</td>
		        <td width="64">45.25</td>
		        <td width="90">45.25</td>
		        <td width="93">45.25</td>
		        <td width="114">45.25</td>
		        <td width="84">45.75</td>
		        <td width="101">45.75</td>
		        <td width="90">45.75</td>
		        <td width="64">45.75</td>
		        <td width="65">45.75</td>
		        <td width="64">45.75</td>
	            </tr>
		          <tr height="20">
                <td height="20" align="right">7</td>
		        <td height="51" width="64">S S Creation</td>
		        <td width="65">No.5    YKK Coil Zipper</td>
		        <td width="64">BLACK</td>
		        <td width="64">MTR</td>
		        <td width="145">7</td>
		        <td width="64">&nbsp;</td>
		        <td width="64">7</td>
		        <td width="90">&nbsp;</td>
		        <td width="93">7</td>
		        <td width="114">&nbsp;</td>
		        <td width="84">7</td>
		        <td width="101">&nbsp;</td>
		        <td width="90">7.25</td>
		        <td width="64">7.25</td>
		        <td width="65">&nbsp;</td>
		        <td width="64">7.25</td>
	            </tr>
		          <tr height="20">
                <td height="20" align="right">8</td>
		        <td height="51" width="64">ASTRA IMPEX</td>
		        <td width="65">THK    40 APTAN WHITE</td>
		        <td width="64">Z0583</td>
		        <td width="64">C-1000</td>
		        <td width="145">125</td>
		        <td width="64">125</td>
		        <td width="64">&nbsp;</td>
		        <td width="90">125</td>
		        <td width="93">125</td>
		        <td width="114">&nbsp;</td>
		        <td width="84">125</td>
		        <td width="101">&nbsp;</td>
		        <td width="90">125</td>
		        <td width="64">&nbsp;</td>
		        <td width="65">125</td>
		        <td width="64">125</td>
	            </tr>
		          <tr height="20">
                <td height="20" align="right">9</td>
		        <td height="51" width="64">ASTRA IMPEX</td>
		        <td width="65">THK    40 APTAN BLACK</td>
		        <td width="64">Z0040</td>
		        <td width="64">C-1500</td>
		        <td width="145">145</td>
		        <td width="64">145</td>
		        <td width="64">145</td>
		        <td width="90">145</td>
		        <td width="93">&nbsp;</td>
		        <td width="114">145</td>
		        <td width="84">&nbsp;</td>
		        <td width="101">145</td>
		        <td width="90">&nbsp;</td>
		        <td width="64">145</td>
		        <td width="65">&nbsp;</td>
		        <td width="64">145</td>
	            </tr>
		          <tr height="20">
                <td height="20" align="right">10</td>
		        <td height="68" width="64">S S Creation</td>
		        <td width="65">Label-06294    Transperant</td>
		        <td width="64">NA</td>
		        <td width="64">NOS</td>
		        <td width="145">2</td>
		        <td width="64">2</td>
		        <td width="64">2</td>
		        <td width="90">&nbsp;</td>
		        <td width="93">0</td>
		        <td width="114">2</td>
		        <td width="84">&nbsp;</td>
		        <td width="101">&nbsp;</td>
		        <td width="90">2</td>
		        <td width="64">2</td>
		        <td width="65">2</td>
		        <td width="64">2</td>
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
            