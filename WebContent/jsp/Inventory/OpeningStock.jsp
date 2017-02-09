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
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form action="" id="tpcslogin" method="post" role="form">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.OpeningStock" name="screen_name" />
			</jsp:include>
<div id="page-wrapper">
<div id="page-inner">
         <div class="row">
                <div class="col-md-11 col-sm-12 col-xs-8">
                    <h3 class="page-header">Opening Stock</h3>
                </div>	
             
         </div>
         
         <div class="row row-no-margin" > 
							<div class='col-sm-3'>
												<div class='form-group'>
													<label >Stock Location</label> <input
														type="text" class="form-control" name="bl_order_no" placeholder="Enter Stock Location"
														id="bl_order_no" />
												</div>
											</div>
											<div class='col-sm-2 pull-right new_tran_btn'>
			<div class='form-group pull-right'>
			<a onclick="addOpeningStock()"  class="btn btn-success"  ><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;New Opening Stock</a>
			</div>
			</div>	
		</div>														
										
		<div class="row paginationstyle">
			<div class='col-sm-12'>
			  <ul class="pagination pull-left"  >
					<!-- <li>
					<a href="javascript:addSalesOrder('n');" data-toggle="tooltip" title="Add Sales Order">
						<i class="fa fa-plus-circle"></i>
						</a>
					</li> -->
					<li>
							<a href='javascript:showOpeningStock();' data-toggle="tooltip" title="Refresh">
						<i class="fa fa-refresh"></i>
						</a> 
					</li>
			        <li><span data-toggle="modal" data-target="#orgModal" >
						<a  href="#"  data-toggle="tooltip" onclick="organizeColumns(${subdocument_id});" title="Reorder columns">
						<i class="fa fa-reorder"></i>
						</a> 
						</span>
					</li>
					<li >
						<a  href="#"  onclick="showConfigScreensForId('4')" data-toggle="tooltip" title="Customization">
						<i class="glyphicon glyphicon-cog"></i>
						</a> 
					</li>
				</ul>
			<div class="input-group-btn pull-left">
                    <div class="btn-group">
                        <div class="dropdown dropdown-lg">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="glyphicon glyphicon-filter text-primary" ></span>&nbsp;Filter&nbsp;<span class="caret"></span></button>
                            <div id="filter-dropdown" class="dropdown-menu dropdown-menu-left"  >
                                <div class="row">
                                
                                	<div class='col-sm-6'>
												<div class='form-group'>
													<label >Group</label> <input
														type="text" class="form-control" name="Group" id="Group" placeholder="Group"
														id="bl_order_no" />
												</div>
											</div>
											<div class='col-sm-6'>
												<div class='form-group'>
													<label >Warehouse</label> <input
														type="text" class="form-control" name="Warehouse" id="Warehouse" placeholder="Warehouse"
														id="bl_order_no" />
												</div>
											</div>
											<div class='col-sm-6'>
												<div class='form-group'>
													<label > Itemcode</label> <input
														type="text" class="form-control" name=" Itemcode" id=" Itemcode" placeholder=" Itemcode"
														id="bl_order_no" />
												</div>
											</div>
											<div class='col-sm-6'>
												<div class='form-group'>
													<label > Opening Stock</label> <input
														type="text" class="form-control" name=" Opening_Stock" id=" Opening_Stock" placeholder=" Opening Stock"
														id="bl_order_no" />
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
                  <div class="btn-group pull-right">
  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   <span class="glyphicon glyphicon-tasks text-primary" ></span>&nbsp; Bulk Action&nbsp;<span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
    <li><a href="#">Print Transactions</a></li>
    <li><a href="#">Print Delivery Challan</a></li>
    <li role="separator" class="divider"></li>
    <li><a href="#">Send Transactions</a></li>
  </ul>
</div>   
				 <ul class="pagination pull-right" >
					<li class="disabled"><a href="#">&laquo;</a></li>
					<li class="active"><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">&raquo;</a></li>
				</ul>
				
			</div>
		</div>
		<div class="row">
		<div class="table-responsive">
		<table class="table table-bordered table-condensed table-hover" id="task-table">
		<thead>
		<tr class="header">
		<th style="width: 100px;">Action</th>
		<th width="128">Warehouse</th>
		<th width="113">Group</th>
		<th width="66">ItemCode</th>
		<th width="242">Description</th>
		<th width="45">Colour</th>
		<th width="64">Stock Date</th>
		<th width="81">Opening Stock</th>
		<th width="33">Rate</th>
		<th width="74">Itracking No</th>
		<th width="55">Supplier</th>
		<th width="64">Expiry Date</th>
		 <th width="24">
		 <div class="checkbox"><input class="checkbox_1" type="checkbox" id="toggle_check_all" name="toggle_check_all">
		 <label for="toggle_check_all" class="checkbox_1"></label>
		 </div>		 </th>
		 </tr>
		  </thead>
		  <tbody>
		  
		 <tr class="datarow"> 
		 <td width="121">
		 <select class="form-control" id="select_action" name="select_action" onchange=""><option selected="selected" value="-1">Action</option>
		 <option value="1">Delete</option>
		 <option value="2">Edit</option>
		 </select>		 </td>
		 <td height="34" width="64">Leather</td>
		 <td width="94">Leather</td>
		 <td width="86">UPL000000001</td>
		 <td width="134">COW BOOTY NAPPA    0.9-1.0</td>
		 <td width="64">Black</td>
		 <td width="123">12-Dec-15</td>
		 <td width="116">1500</td>
		 <td width="64">110</td>
		 <td width="64">S993</td>
		 <td width="64">Tannery A</td>
		 <td width="64">&nbsp;</td>
		 <td>
		 <div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_1" name="se_check_8" value="">
		  <label for="se_check_1" class="checkbox_1"></label>
		  </div>		  </td>
		  </tr>
		  
		   <tr class="datarow"> 
		 <td width="121">
		 <select class="form-control" id="select_action" name="select_action" onchange=""><option selected="selected" value="-1">Action</option>
		 <option value="1">Delete</option>
		 <option value="2">Edit</option>
		 </select>		 </td>
		 <td height="34" width="64">Leather</td>
		 <td width="94">Leather</td>
		 <td width="86">LIN000000001</td>
		 <td width="134">COW    FINISHED LINING 0.7/0.9</td>
		 <td width="64">MAHOGANY</td>
		 <td width="123">12-Oct-15</td>
		 <td width="116">1250</td>
		 <td width="64">120</td>
		 <td width="64">S994</td>
		 <td width="64">Tannery    A</td>
		 <td width="64">&nbsp;</td>
		 <td>
		 <div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_2" name="se_check_2" value="">
		  <label for="se_check_2" class="checkbox_1"></label>
		  </div>		  </td>
		  </tr>
		  
		   <tr class="datarow"> 
		 <td width="121">
		 <select class="form-control" id="select_action" name="select_action" onchange=""><option selected="selected" value="-1">Action</option>
		 <option value="1">Delete</option>
		 <option value="2">Edit</option>
		 </select>		 </td>
		 <td height="34" width="64">Material</td>
		 <td width="94">Sole</td>
		 <td width="86">SOL000000001</td>
		 <td width="134">SOLE    HALF RUBBER KRISTIN</td>
		 <td width="64">MAHOGANY</td>
		 <td width="123">12-Nov-15</td>
		 <td width="116">350</td>
		 <td width="64">300</td>
		 <td width="64">&nbsp;</td>
		 <td width="64">SUN    SOLES</td>
		 <td width="64">&nbsp;</td>
		 <td>
		 <div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_3" name="se_check_3" value="">
		  <label for="se_check_3" class="checkbox_1"></label>
		  </div>		  </td>
		  </tr>
		   <tr class="datarow"> 
		 <td width="121">
		 <select class="form-control" id="select_action" name="select_action" onchange=""><option selected="selected" value="-1">Action</option>
		 <option value="1">Delete</option>
		 <option value="2">Edit</option>
		 </select>		 </td>
		 <td height="34" width="64">Material</td>
		 <td width="94">Zippers    &amp; Runners</td>
		 <td width="86">ZIP000000001</td>
		 <td width="134">NO.8    YKK SLIDER 2WAY</td>
		 <td width="64">ANTIQUE    BLK</td>
		 <td width="123">20-Nov-15</td>
		 <td width="116">500</td>
		 <td width="64">1.25</td>
		 <td width="64">&nbsp;</td>
		 <td width="64">S    S Creation</td>
		 <td width="64">&nbsp;</td>
		 <td>
		 <div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_4" name="se_check_8" value="">
		  <label for="se_check_4" class="checkbox_1"></label>
		  </div>		  </td>
		  </tr>
		  
		   <tr class="datarow"> 
		 <td width="121">
		 <select class="form-control" id="select_action" name="select_action" onchange=""><option selected="selected" value="-1">Action</option>
		 <option value="1">Delete</option>
		 <option value="2">Edit</option>
		 </select>		 </td>
		 <td height="34" width="64">Leather</td>
		 <td width="94">Lining</td>
		 <td width="86">LIN000000002</td>
		 <td width="134">LIN    1988 COW DD LINING 0.9/1.0</td>
		 <td width="64">Black</td>
		 <td width="123">12-Nov-15</td>
		 <td width="116">3000</td>
		 <td width="64">45</td>
		 <td width="64">SC438</td>
		 <td width="64">Tannery    B</td>
		 <td width="64">&nbsp;</td>
		 <td>
		 <div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_5" name="se_check_8" value="">
		  <label for="se_check_5" class="checkbox_1"></label>
		  </div>		  </td>
		  </tr>
		  
		   <tr class="datarow"> 
		 <td width="121">
		 <select class="form-control" id="select_action" name="select_action" onchange=""><option selected="selected" value="-1">Action</option>
		 <option value="1">Delete</option>
		 <option value="2">Edit</option>
		 </select>		 </td>
		 <td height="34" width="64">Leather</td>
		 <td width="94">Lining</td>
		 <td width="86">LIN000000003</td>
		 <td width="134">LIN    1901 COW DD LINING 1.1/1.3</td>
		 <td width="64">DK    BROWN</td>
		 <td width="123">22-Jan-15</td>
		 <td width="116">4000</td>
		 <td width="64">45</td>
		 <td width="64">SC439</td>
		 <td width="64">Tannery    B</td>
		 <td width="64">&nbsp;</td>
		 <td>
		 <div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_6" name="se_check_8" value="">
		  <label for="se_check_6" class="checkbox_1"></label>
		  </div>		  </td>
		  </tr>
		  
		   <tr class="datarow"> 
		 <td width="121">
		 <select class="form-control" id="select_action" name="select_action" onchange=""><option selected="selected" value="-1">Action</option>
		 <option value="1">Delete</option>
		 <option value="2">Edit</option>
		 </select>		 </td>
		 <td height="34" width="64">Material</td>
		 <td width="94">Zippers    &amp; Runners</td>
		 <td width="86">ZIP000000004</td>
		 <td width="134">No.5    YKK Coil Zipper</td>
		 <td width="64">BLACK</td>
		 <td width="123">22-Nov-15</td>
		 <td width="116">55</td>
		 <td width="64">7</td>
		 <td></td>
		 <td width="64">S S Creation</td>
		 <td width="64">&nbsp;</td>
		 <td>
		 <div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_7" name="se_check_8" value="">
		  <label for="se_check_7" class="checkbox_1"></label>
		  </div>		  </td>
		  </tr>
		  
		   <tr class="datarow"> 
		 <td width="121">
		 <select class="form-control" id="select_action" name="select_action" onchange=""><option selected="selected" value="-1">Action</option>
		 <option value="1">Delete</option>
		 <option value="2">Edit</option>
		 </select>		 </td>
		 <td height="34" width="64">Material</td>
		 <td width="94">Thread</td>
		 <td width="86">THR000000005</td>
		 <td width="134">THK    40 APTAN WHITE</td>
		 <td width="64">Z0583</td>
		 <td width="123">30-Nov-15</td>
		 <td width="116">20</td>
		 <td width="64">125</td>
		 <td width="64">&nbsp;</td>
		 <td width="64">ASTRA    IMPEX</td>
		 <td width="64">&nbsp;</td>
		 <td>
		 <div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_8" name="se_check_8" value="">
		  <label for="se_check_8" class="checkbox_1"></label>
		  </div>		  </td>
		  </tr>
		  
		   <tr class="datarow"> 
		 <td width="121">
		 <select class="form-control" id="select_action" name="select_action" onchange=""><option selected="selected" value="-1">Action</option>
		 <option value="1">Delete</option>
		 <option value="2">Edit</option>
		 </select>		 </td>
		 <td height="34" width="64">Non-Moving</td>
		 <td width="94">Thread</td>
		 <td width="86">THR000000006</td>
		 <td width="134">THK    40 APTAN BLACK</td>
		 <td width="64">Z0040</td>
		 <td width="123">13-Nov-14</td>
		 <td width="116">20</td>
		 <td width="64">145</td>
		 <td width="64">&nbsp;</td>
		 <td width="64">ASTRA    IMPEX</td>
		 <td width="64">&nbsp;</td>
		 <td>
		 <div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_9" name="se_check_8" value="">
		  <label for="se_check_9" class="checkbox_1"></label>
		  </div>		  </td>
		  </tr>
		  
		   <tr class="datarow"> 
		 <td width="121">
		 <select class="form-control" id="select_action" name="select_action" onchange=""><option selected="selected" value="-1">Action</option>
		 <option value="1">Delete</option>
		 <option value="2">Edit</option>
		 </select>		 </td>
		 <td height="34" width="64">Non-Moving</td>
		 <td width="94">Label</td>
		 <td width="86">LAB000000007</td>
		 <td width="134">Label-06294    Transperant</td>
		 <td width="64">NA</td>
		 <td width="123">19-Nov-14</td>
		 <td width="116">250</td>
		 <td width="64">2</td>
		 <td width="64">&nbsp;</td>
		 <td width="64">S    S Creation</td>
		 <td width="64">&nbsp;</td>
		 <td>
		 <div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_10" name="se_check_8" value="">
		  <label for="se_check_10" class="checkbox_1"></label>
		  </div>		  </td>
		  </tr>
		  
		   <tr class="datarow"> 
		 <td width="121">
		 <select class="form-control" id="select_action" name="select_action" onchange=""><option selected="selected" value="-1">Action</option>
		 <option value="1">Delete</option>
		 <option value="2">Edit</option>
		 </select>		 </td>
		 <td height="35" width="64">SAMPLE STORE</td>
		 <td width="94">Adhesive</td>
		 <td width="86">ADH000000008</td>
		 <td width="134">FEVICOL    505</td>
		 <td width="64">NA</td>
		 <td width="123">15-Nov-15</td>
		 <td width="116">25</td>
		 <td width="64">545</td>
		 <td width="64">&nbsp;</td>
		 <td width="64">United    Chem</td>
		 <td>&nbsp;</td>
		 <td>
		 <div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_11" name="se_check_8" value="">
		  <label for="se_check_11" class="checkbox_1"></label>
		  </div>		  </td>
		  </tr>
		  </tbody>
		  </table>
		  </div>
       </div>           
     </div>  
    </div>
</div>

		<jsp:include page="../common/ColumnPreferences.jsp" />
		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> <input
			type="hidden" name="request_type" id="request_type" /> <input
			type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
		<input type="hidden" name="pageno" id="pageno" value="${pc.pageNo}" />
		<input type="hidden" name="total_pages" id="total_pages"
			value="${pc.pageCount}" />


<script>
			
			$('#task-table input:checkbox').click(function () {
			    $(this).closest('tr').removeClass('highlight-row');
			    if ($(this).is(':checked')) $(this).closest('tr').addClass('highlight-row');
			}) 


			$('#toggle_check_all').click(function(e){
			var table= $(e.target).closest('table');
			$('td input:checkbox',table).prop('checked',this.checked);
			}); 
			</script>
			
	</form>
</body>
</html>
            