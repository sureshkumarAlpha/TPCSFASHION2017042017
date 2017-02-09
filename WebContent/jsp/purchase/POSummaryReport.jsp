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
                    <h3 class="page-header">Pending PO Summary Report</h3>
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
		        	<div id="filter-panel" class="collapse filter-panel">
		            <div class="panel-default">
		            <div class="panel-body" style="padding: 0;">
		         	<div class='col-sm-3'>
					<div class='form-group'>
					<label >PO.No</label> <input type="text" class="form-control" name="PoNo" id="PoNo" placeholder="PO.No" /> 
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
					<label >BuyerPO</label> <input  type="text" class="form-control" name="buyerPO" placeholder="BuyerPO" id="buyerPO"  />
					</div>
					</div>
					 <div class='col-sm-3'>
				    <div class='form-group'>
					<label >Material</label> <input	type="text" class="form-control" name="bl_order_no" placeholder="Material" id="bl_order_no" />
					</div>
					</div> 
					 <div class='col-sm-3'>
				    <div class='form-group'>
					<label >Supplier </label> <input	type="text" class="form-control" name="Supplier" placeholder="Supplier" id="Supplier" />
					</div>
					</div> 
					 <div class='col-sm-3'>
				    <div class='form-group'>
					<label >Size Range </label> <input	type="text" class="form-control" name="sizerange" placeholder="Size Ranges" id="sizerange" />
					</div>
					</div> 
					 <div class='col-sm-3'>
				    <div class='form-group'>
					<label >Indent No </label> <input	type="text" class="form-control" name="indent_no" placeholder="Indent No" id="indent_no" />
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
					</button>
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
			</div>
		</div>
		
		<div class="row table-responsive">
		<table class="table table-bordered table-condensed">
		    <thead>
		    <tr class="header">
				<th width="37" height="28" >S.No.</th>
						  <th width="98">IO.No</th>
						  <th width="54">Po Date</th>
						  <th width="98">Material Code</th>
						  <th width="45">Colour</th>
						  <th width="55">Supplier</th>
						  <th width="41">Po No</th>
						  <th width="27">Size</th>
						  <th width="75">Size Range</th>
						  <th width="43">UOM </th>
						  <th width="65">OrderQty</th>
						  <th width="60">RecdQty</th>
						  <th width="51">GE Qty</th>
						  <th width="48">BalQty</th>
						  <th width="79">BalValue</th>
			    </tr>
		    </thead>
		    <tbody>	
		    
		             <tr>
		               <td height="20"></td>
		               <td>&nbsp;</td>
		               <td> </td>
		               <td> </td>
		               <td> </td>
		               <td> </td>
		               <td> </td>
		               <td> </td>
		               <td> </td>
		               <td> </td>
		               <td> </td>
		               <td> </td>
		               <td> </td>
		               <td> </td>
		               <td> </td>
                </tr>
		             <tr>
		               <td height="20">1</td>
		               <td align="right">1.2015</td>
		               <td align="right">02-Apr-15</td>
		               <td>CTN00001</td>
		               <td>A/Nkle</td>
		               <td>SAS-TEC GmbH</td>
		               <td align="right">22.2015</td>
		               <td></td>
		               <td></td>
		               <td>Pcs</td>
		               <td align="right">200</td>
		               <td align="right">196</td>
		               <td align="right">0</td>
		               <td align="right">4</td>
		               <td align="right">800</td>
                </tr>
		             <tr>
		               <td height="20">2</td>
		               <td align="right">2.2015</td>
		               <td align="right">02-Apr-15</td>
		               <td>CTN00002</td>
		               <td>JAUNE</td>
		               <td>R.S. Mourya</td>
		               <td align="right">25.2015</td>
		               <td></td>
		               <td></td>
		               <td>Nos</td>
		               <td align="right">500</td>
		               <td align="right">300</td>
		               <td align="right">0</td>
		               <td align="right">4</td>
		               <td align="right">800</td>
                </tr>
		             <tr>
		               <td height="20">3</td>
		               <td align="right">3.2015</td>
		               <td align="right">09-Jun-15</td>
		               <td>CTN00003</td>
		               <td>Silver</td>
		               <td>Celebrity Clothings</td>
		               <td align="right">293.2015</td>
		               <td></td>
		               <td></td>
		               <td>Cone</td>
		               <td align="right">2000</td>
		               <td align="right">1931.1</td>
		               <td align="right">0</td>
		               <td align="right">68.9</td>
		               <td align="right">9301.5</td>
                </tr>
		             <tr>
		               <td height="20">4</td>
		               <td align="right">4.2015</td>
		               <td align="right">29-Jul-15</td>
		               <td>CTN00004</td>
		               <td>copper</td>
		               <td>32 Bar Blues</td>
		               <td align="right">515.2015</td>
		               <td></td>
		               <td></td>
		               <td>Kgs</td>
		               <td align="right">3630</td>
		               <td align="right">3608.75</td>
		               <td align="right">0</td>
		               <td align="right">21.25</td>
		               <td align="right">2868.75</td>
                </tr>
		             <tr height="20">
                       <td height="20">5</td>
		               <td align="right">32.2015</td>
		               <td align="right">26-Jun-15</td>
		               <td>CTN00005</td>
		               <td>TAN</td>
		               <td>32 Bar Blues</td>
		               <td align="right">365.2015</td>
		               <td></td>
		               <td></td>
		               <td>cm</td>
		               <td align="right">140</td>
		               <td align="right">133.2</td>
		               <td align="right">0</td>
		               <td align="right">6.8</td>
		               <td align="right">918</td>
                </tr>
		             <tr height="20">
                       <td height="20">6</td>
		               <td align="right">58.2015</td>
		               <td align="right">29-Jul-15</td>
		               <td>CTN00006</td>
		               <td>Cognac</td>
		               <td>Kirti Interlinings</td>
		               <td align="right">519.2015</td>
		               <td></td>
		               <td></td>
		               <td>Inches</td>
		               <td align="right">350</td>
		               <td align="right">0</td>
		               <td align="right">0</td>
		               <td align="right">350</td>
		               <td align="right">47250</td>
                </tr>
		             <tr height="20">
                       <td height="20">7</td>
		               <td align="right">67.2015</td>
		               <td align="right">29-Jul-15</td>
		               <td>CTN00007</td>
		               <td>Oxblood</td>
		               <td>Sayed Traders</td>
		               <td align="right">516.2015</td>
		               <td></td>
		               <td></td>
		               <td>Skin</td>
		               <td align="right">20</td>
		               <td align="right">0</td>
		               <td align="right">0</td>
		               <td align="right">20</td>
		               <td align="right">2700</td>
                </tr>
		             <tr height="20">
                       <td height="20">8</td>
		               <td align="right">94.2015</td>
		               <td align="right">27-Oct-15</td>
		               <td>CTN00008</td>
		               <td>Anthracite</td>
		               <td>Shree Laxmi</td>
		               <td align="right">785.2015</td>
		               <td></td>
		               <td></td>
		               <td>Sheets</td>
		               <td align="right">1000</td>
		               <td align="right">323.6</td>
		               <td align="right">0</td>
		               <td align="right">676.4</td>
		               <td align="right">93343.2</td>
                </tr>
		             <tr height="20">
                       <td height="20">9</td>
		               <td align="right">122.2015</td>
		               <td align="right">09-Oct-15</td>
		               <td>CTN00009</td>
		               <td>MARINE</td>
		               <td>Leston International </td>
		               <td align="right">729.2015</td>
		               <td></td>
		               <td></td>
		               <td>Set</td>
		               <td align="right">15</td>
		               <td align="right">0</td>
		               <td align="right">0</td>
		               <td align="right">15</td>
		               <td align="right">2460</td>
                </tr>
		             
		             <tr height="20">
                       <td height="20">10</td>
		               <td align="right">235.2014</td>
		               <td align="right">29-Apr-15</td>
		               <td>CTN00010</td>
		               <td>CHAMPAGNE</td>
		               <td>DPC Distribution</td>
		               <td align="right">114.2015</td>
		               <td></td>
		               <td></td>
		               <td>Panel</td>
		               <td align="right">206</td>
		               <td align="right">0</td>
		               <td align="right">0</td>
		               <td align="right">206</td>
		               <td align="right">41200</td>
                </tr>
		             <tr height="20">
                       <td height="20"></td>
		               <td>Sub Total(1)</td>
		               <td></td>
		               <td></td>
		               <td></td>
		               <td></td>
		               <td></td>
		               <td></td>
		               <td></td>
		               <td></td>
		               <td align="right">7776</td>
		               <td align="right">6406.35</td>
		               <td align="right">0</td>
		               <td align="right">1369.65</td>
		               <td align="right">201101.45</td>
                </tr>
		             <tr height="20">
                       <td height="20"></td>
		               <td>Dark Grey Satin Plain</td>
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
                       <td height="20">1</td>
		               <td align="right">0</td>
		               <td align="right">02-Apr-15</td>
		               <td>LIN0000099</td>
		               <td>EBENE</td>
		               <td>Sri Raghavendhra &amp; Co.</td>
		               <td align="right">22.2015</td>
		               <td></td>
		               <td></td>
		               <td>DCM</td>
		               <td align="right">45</td>
		               <td align="right">2.5</td>
		               <td align="right">0</td>
		               <td align="right">42.5</td>
		               <td align="right">6162.5</td>
                </tr>
		             <tr height="20">
                       <td height="20">2</td>
		               <td align="right">0</td>
		               <td align="right">23-May-15</td>
		               <td>CML00006</td>
		               <td>BEIGE</td>
		               <td>Sai Chamois Inc</td>
		               <td align="right">219.2015</td>
		               <td></td>
		               <td></td>
		               <td>Ltrs</td>
		               <td align="right">50</td>
		               <td align="right">0</td>
		               <td align="right">0</td>
		               <td align="right">50</td>
		               <td align="right">10000</td>
                </tr>
		             <tr height="20">
                       <td height="20">3</td>
		               <td align="right">0</td>
		               <td align="right">24-May-15</td>
		               <td>CML00008</td>
		               <td>FICELLE</td>
		               <td>Vaan Leather Exports</td>
		               <td align="right">220.2015</td>
		               <td></td>
		               <td></td>
		               <td>144GRO</td>
		               <td align="right">55</td>
		               <td align="right">49.9</td>
		               <td align="right">0</td>
		               <td align="right">0.1</td>
		               <td align="right">20</td>
                </tr>
		             <tr height="20">
                       <td height="20">4</td>
		               <td align="right">36.2015</td>
		               <td align="right">26-Jun-15</td>
		               <td>CML00009</td>
		               <td>NOIR COBRA</td>
		               <td>Bayselle Marketing</td>
		               <td align="right">365.2015</td>
		               <td></td>
		               <td></td>
		               <td>GROSS</td>
		               <td align="right">60</td>
		               <td align="right">47.5</td>
		               <td align="right">0</td>
		               <td align="right">2.5</td>
		               <td align="right">500</td>
                </tr>
		             <tr height="20">
                       <td height="20"></td>
		               <td>Sub Total(1)</td>
		               <td></td>
		               <td></td>
		               <td></td>
		               <td></td>
		               <td></td>
		               <td></td>
		               <td></td>
		               <td></td>
		               <td align="right">195</td>
		               <td align="right">99.9</td>
		               <td align="right">0</td>
		               <td align="right">95.1</td>
		               <td align="right">16682.5</td>
                </tr>
		             <tr height="20">
                       <td height="20"></td>
		               <td>Maroon Satin Plain</td>
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
            