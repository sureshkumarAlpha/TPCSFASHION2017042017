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
                    <h3 class="page-header">Pending Indent </h3>
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
											<label >Indent No</label> <input type="text" class="form-control" name="Indent_No" id="Indent_No" placeholder="Indent No." /> 
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
											<label >Indent Type</label> <input  type="text" class="form-control" name="Indent Type" placeholder="Indent Type" id="Indent Type"  />
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
				<th style="width: 5%;">S.No.</th>
						  <th >Indent No.</th>
						  <th  style="width: 6%;">Date</th>
						  <th >Indent Type </th>
						  <th >Material</th>
						  <th >color</th>
						  <th >UOM</th>
						  <th >Qty</th>
						  <th >Pur Qty.</th>
						  <th >Bal. Qty.</th>
						  <th >Required Date</th>
						  <th >PreparedBy</th>
						  <th>Department</th>
		    </tr>
		    </thead>
		    <tbody>	
		    
		     <tr class="datarow">					                  		                  
					                  			
					            <td >1</td>
	                            <td height="68" width="64">1.1516</td>
	                            <td width="65" nowrap="nowrap">15-Feb-16</td>
	                            <td width="64">IORMR</td>
	                            <td width="64">COW BOOTY NAPPA 0.9-1.0</td>
	                            <td width="145">Black</td>
	                            <td width="64">Sq.Ft</td>
	                            <td width="64">1500</td>
	                            <td align="right" width="90">1200</td>
	                            <td align="right" width="93">300</td>
	                            <td width="114">16-Mar-16</td>
	                            <td width="84">Aravind</td>
	                            <td width="101">Planning</td>
		     </tr>	
          		     <tr class="datarow">	                  		                  
					 <td >2</td>
							    <td height="68" width="64">1.1516</td>
							    <td width="65" nowrap="nowrap">15-Feb-16</td>
							    <td width="64">IORMR</td>
							    <td width="64">COW FINISHED    LINING 0.7/0.9</td>
							    <td width="145">MAHOGANY</td>
							    <td width="64">Sq.Ft</td>
							    <td width="64">1250</td>
							    <td align="right">100</td>
							    <td align="right">1150</td>
							    <td width="114">16-Mar-16</td>
							    <td>Aravind</td>
							    <td>Planning</td>
          		     </tr>
								<tr class="datarow">					                  		                  
					              <td >3</td>
								  <td height="68" width="64">2.1516</td>
								  <td width="65" nowrap="nowrap">17-Feb-16</td>
								  <td width="64">IORMR</td>
								  <td width="64">SOLE HALF RUBBER    KRISTIN</td>
								  <td width="145">MAHOGANY</td>
								  <td width="64">PAIR</td>
								  <td width="64">350</td>
								  <td align="right">0</td>
								  <td align="right">350</td>
								  <td width="114">3-Mar-16</td>
								  <td>Aravind</td>
								  <td>Planning</td>
								</tr>
					          		  
						             	
					          <tr class="datarow">	                 		                  
					          <td >4</td>
							  <td height="68" width="64">3.1516</td>
							  <td width="65" nowrap="nowrap">17-Feb-16</td>
							  <td width="64">Stock Indent</td>
							  <td width="64">NO.8 YKK SLIDER    2WAY</td>
							  <td width="145">ANTIQUE    BLK</td>
							  <td width="64">NOS</td>
							  <td width="64">500</td>
							  <td align="right">500</td>
							  <td align="right">0</td>
							  <td width="114">24-Feb-16</td>
							  <td>Vishal</td>
							  <td>Material Store</td>
				            </tr>
							<tr class="datarow">				                  		                  
					                  			
					          <td >5</td>
							  <td height="68" width="64">4.1516</td>
							  <td width="65" nowrap="nowrap">21-Feb-16</td>
							  <td width="64">IORMR</td>
							  <td width="64">LIN 1988 COW DD    LINING 0.9/1.0</td>
							  <td width="145">Black</td>
							  <td width="64">Sq.Ft</td>
							  <td width="64">3000</td>
							  <td align="right">2500</td>
							  <td align="right">500</td>
							  <td width="114">16-Mar-16</td>
							  <td>Aravind</td>
							  <td>Planning</td>
					 </tr>
				  <tr class="datarow">			
					  <td >6</td>
							  <td height="68" width="64">4.1516</td>
							  <td width="65" nowrap="nowrap">21-Feb-16</td>
							  <td width="64">IORMR</td>
							  <td width="64">LIN 1901 COW DD    LINING 1.1/1.3</td>
							  <td width="145">DK    BROWN</td>
							  <td width="64">Sq.Ft</td>
							  <td width="64">4000</td>
							  <td align="right">2500</td>
							  <td align="right">1500</td>
							  <td width="114">16-Mar-16</td>
							  <td>Aravind</td>
							  <td>Planning</td>
			    </tr>         		  
				  <tr class="datarow">			
          <td >7</td>
							  <td height="51" width="64">5.1516</td>
							  <td width="65" nowrap="nowrap">21-Feb-16</td>
							  <td width="64">Stock Indent</td>
							  <td width="64">No.5 YKK Coil    Zipper</td>
							  <td width="145">BLACK</td>
							  <td width="64">MTR</td>
							  <td width="64">55</td>
							  <td align="right">60</td>
							  <td align="right">-5</td>
							  <td width="114">27-Feb-16</td>
							  <td>Vishal</td>
							  <td>Material Store</td>
			    </tr>
         <tr class="datarow">			
          <td >8</td>
							  <td height="51" width="64">6.1516</td>
							  <td width="65" nowrap="nowrap">22-Feb-16</td>
							  <td width="64">Stock Indent</td>
							  <td width="64">THK 40 APTAN WHITE</td>
							  <td width="145">Z0583</td>
							  <td width="64">C-1000</td>
							  <td width="64">20</td>
							  <td align="right">10</td>
							  <td align="right">10</td>
							  <td width="114">28-Feb-16</td>
							  <td>Vishal</td>
							  <td>Material Store</td>
         </tr>
         <tr class="datarow">			
         <td >9</td>
							  <td height="51" width="64">6.1516</td>
							  <td width="65" nowrap="nowrap">22-Feb-16</td>
							  <td width="64">Stock Indent</td>
							  <td width="64">THK 40 APTAN BLACK</td>
							  <td width="145">Z0040</td>
							  <td width="64">C-1500</td>
							  <td width="64">20</td>
							  <td align="right">10</td>
							  <td align="right">10</td>
							  <td width="114">28-Feb-16</td>
							  <td>Vishal</td>
							  <td>Material Store</td>
         </tr>
         <tr class="datarow">			
          <td >10</td>
							  <td height="68" width="64">7.1516</td>
							  <td width="65" nowrap="nowrap">23-Feb-16</td>
							  <td width="64">IORMR</td>
							  <td width="64">Label-06294    Transperant</td>
							  <td width="145">NA</td>
							  <td width="64">NOS</td>
							  <td width="64">250</td>
							  <td align="right">250</td>
							  <td align="right">0</td>
							  <td width="114">29-Feb-16</td>
							  <td>Aravind</td>
							  <td>Planning</td>
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
            