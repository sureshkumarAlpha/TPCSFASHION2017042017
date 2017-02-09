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
                    <h3 class="page-header">Material Average Lead Time </h3>
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
					<label >Indent No</label> <input type="text" class="form-control" name="Indent_No" id="Indent_No" placeholder="Indent No." /> 
					</div>
					</div>
					<div class='col-sm-3'>
					<div class='form-group'>
					<label >Unit </label> 
					<input type="text" class="form-control" name="Unit" id="Unit" placeholder="Unit" /> 
					</div>
					</div>
					<div class='col-sm-3'>
					<div class='form-group'>
					<label >Indent Type</label> <input  type="text" class="form-control" name="Indent Type" placeholder="Indent Type" id="Indent Type"  />
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
					<label >Supplier</label> <input type="text" class="form-control" name="Indent_No" id="Indent_No" placeholder="Supplier" /> 
					</div>
					</div>
					<div class='col-sm-6'>
					<div class='form-group'>
					<label >Unit </label> 
					<input type="text" class="form-control" name="Unit" id="Unit" placeholder="Unit" /> 
					</div>
					</div>
					<div class='col-sm-6'>
					<div class='form-group'>
					<label >Group</label> <input  type="text" class="form-control" name="Indent Type" placeholder="Group" id="Indent Type"  />
					</div>
					</div>
					<div class='col-sm-6'>
				    <div class='form-group'>
					<label >Color</label> <input	type="text" class="form-control" name="bl_order_no" placeholder="Color" id="bl_order_no" />
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
				<th style="width: 5%;">S.No.</th>
						  <th >Supplier</th>
						  <th >Group</th>
						  <th >Item</th>
						  <th >Color</th>
						  <th >UOM</th>
						  <th >Last Inward In</th>
						  <th >Lead Time</th>
		    </tr>
		    </thead>
		    <tbody>	
		    
		     <tr class="datarow">					                  		                  
					                  			
					            <td >1</td>
	                            <td height="68" width="203">Tannery    A</td>
	                            <td width="64">Leather</td>
	                            <td width="65">COW BOOTY NAPPA    0.9-1.0</td>
	                            <td width="64">Black</td>
	                            <td width="64">Sq.Ft</td>
	                            <td width="145">12/Dec/15</td>
	                            <td width="64">25</td>
		     </tr>	
          		     <tr class="datarow">	                  		                  
					 <td >2</td>
							    <td height="68" width="203">Tannery A</td>
							    <td width="64">Leather</td>
							    <td width="65">COW    FINISHED LINING 0.7/0.9</td>
							    <td width="64">MAHOGANY</td>
							    <td width="64">Sq.Ft</td>
							    <td width="145">12/Dec/15</td>
							    <td width="64">25</td>
          		     </tr>
								<tr class="datarow">					                  		                  
					              <td >3</td>
								  <td height="68" width="203">SUN SOLES</td>
								  <td width="64">Sole</td>
								  <td width="65">SOLE    HALF RUBBER KRISTIN</td>
								  <td width="64">MAHOGANY</td>
								  <td width="64">PAIR</td>
								  <td width="145">03/Feb/16</td>
								  <td width="64">15</td>
								</tr>
					          		  
						             	
					          <tr class="datarow">	                 		                  
					          <td >4</td>
							  <td height="68" width="203">S S Creation</td>
							  <td width="64">Zippers    &amp; Runners</td>
							  <td width="65">NO.8    YKK SLIDER 2WAY</td>
							  <td width="64">ANTIQUE    BLK</td>
							  <td width="64">NOS</td>
							  <td width="145">05/Jan/16</td>
							  <td width="64">6</td>
				            </tr>
							<tr class="datarow">				                  		                  
					                  			
					          <td >5</td>
							  <td height="68" width="203">Tannery B</td>
							  <td width="64">Lining</td>
							  <td width="65">LIN    1988 COW DD LINING 0.9/1.0</td>
							  <td width="64">Black</td>
							  <td width="64">Sq.Ft</td>
							  <td width="145">25/Jan/16</td>
							  <td width="64">25</td>
					 </tr>
				  <tr class="datarow">			
					  <td >6</td>
							  <td height="68" width="203">Tannery B</td>
							  <td width="64">Lining</td>
							  <td width="65">LIN    1901 COW DD LINING 1.1/1.3</td>
							  <td width="64">DK    BROWN</td>
							  <td width="64">Sq.Ft</td>
							  <td width="145">25/Nov/15</td>
							  <td width="64">25</td>
			    </tr>         		  
				  <tr class="datarow">			
          <td >7</td>
							  <td height="51" width="203">S S Creation</td>
							  <td width="64">Zippers    &amp; Runners</td>
							  <td width="65">No.5    YKK Coil Zipper</td>
							  <td width="64">BLACK</td>
							  <td width="64">MTR</td>
							  <td width="145">12/Feb/16</td>
							  <td width="64">6</td>
			    </tr>
         <tr class="datarow">			
          <td >8</td>
							  <td height="51" width="203">ASTRA IMPEX</td>
							  <td width="64">Thread</td>
							  <td width="65">THK    40 APTAN WHITE</td>
							  <td width="64">Z0583</td>
							  <td width="64">C-1000</td>
							  <td width="145">04/Feb/16</td>
							  <td width="64">6</td>
         </tr>
         <tr class="datarow">			
         <td >9</td>
							  <td height="51" width="203">ASTRA IMPEX</td>
							  <td width="64">Thread</td>
							  <td width="65">THK    40 APTAN BLACK</td>
							  <td width="64">Z0040</td>
							  <td width="64">C-1500</td>
							  <td width="145">06/Feb/16</td>
							  <td width="64">6</td>
         </tr>
         <tr class="datarow">			
          <td >10</td>
							  <td height="68" width="203">S S Creation</td>
							  <td width="64">Label</td>
							  <td width="65">Label-06294    Transperant</td>
							  <td width="64">NA</td>
							  <td width="64">NOS</td>
							  <td width="145">25/Jan/16</td>
							  <td width="64">6</td>
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
            