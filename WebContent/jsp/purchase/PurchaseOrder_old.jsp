<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
  <head>
  <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title><fmt:message bundle="${bundle}"   key="Title.Title"/></title>

<%-- <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/sales/sales_order.js"></script> --%>

<jsp:include page="../common/Header.jsp"/>

<script language="javascript">
	var contextpath = "${pageContext.request.contextPath}";

	function getData(selectedFields,totalColumns){		
		getSalesOrderData(selectedFields,totalColumns);
		}
	function getSalesOrderData(selectedFields,totalColumns){
	
		setVal('request_type','Normal');
		setVal('servlet_name','SalesOrderServlet');
		setVal('callbackmethod_name','doDisplaySalesOrderAfterColumnOrganized');
		document.forms[0].action=contextpath+"/RequestHandlerServlet?pageno="+1+"&selected_columns="+selectedFields+"&total_columns="+totalColumns;	
		document.forms[0].method="POST";	
		document.forms[0].submit();
	}

</script>

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
.bootstrap-select{
width:150px !important; 
} 

</style>

</head>
<jsp:include page="../common/ValidateUser.jsp"/>

<body >
<form action=""  id="tpcslogin" method="post"   role="form" autocomplete="off">
<div id="wrapper">

 <jsp:include page="../common/MainMenu.jsp"> 
<jsp:param value="2" name="screen_type"/>
<jsp:param value="Order.Transactions.BuyerOrder" name="screen_name"/>
<jsp:param value="2" name="sub_document_type"/>
		</jsp:include>
<div id="page-wrapper">
<div id="page-inner">
         <div class="row">
                <div class="col-md-11 col-sm-12 col-xs-8">
                  <h2 class="page-header">Purchase Order</h2>
                </div>	
         </div>
         <div class="row row-no-margin" > 
       <%--          <c:forEach items="${fixedfields}" var="objFixedfields" > --%>
				<%-- <c:if test="${objFixedfields.pageFieldName=='party_id'}"> --%>
				    <div class='col-sm-3'>    
                <div class='form-group'>
				<label for="so_customer">Customer</label>
				<input type="text" class="form-control"  name="so_customer" id="so_customer" placeholder="Customer Search..." value="${so_customer}" onblur="searchSO()"  onkeyup="clearId(event,'so_customer','so_customer_id')" onkeypress="enterKeyPress(event,'search');"/>
				<%-- <input type="hidden" class="form-control"  name="so_customer_id" id="so_customer_id" value="${so_customer_id}"/> --%>
						          </div>
            </div>
				<%-- </c:if> --%>
		
				<%-- </c:forEach> --%>
      
             
             		<div class='col-sm-3'>
									<div class='form-group'>
										<label for="quote_type" style="display:block;">Status</label> 
										    <select  class="form-control selectpicker" data-live-search="false"   id="order_type" name="order_type" onchange="searchSO()" style="width:200px;">
											<option  value="-1" <c:if test="${order_type eq '-1'}">selected="selected"</c:if>>All</option>
										<%-- 	<option value="Awaiting" <c:if test="${order_type eq 'Awaiting'}">selected="selected"</c:if> >Awaiting Authorization</option> --%>
										<option value="Open" <c:if test="${order_type eq 'Open'}">selected="selected"</c:if>>Open</option>
											<option value="Canceled" <c:if test="${order_type eq 'Canceled'}">selected="selected"</c:if>>Canceled</option>
											<option value="Closed" <c:if test="${order_type eq 'Closed'}">selected="selected"</c:if>>Closed</option>
											<option value="Sent" <c:if test="${order_type eq 'Sent'}">selected="selected"</c:if>>Sent</option>
											<option value="NotSent" <c:if test="${order_type eq 'NotSent'}">selected="selected"</c:if>>Not Sent</option>
											<%-- <option value="DeliveryPending" <c:if test="${order_type eq 'DeliveryPending'}">selected="selected"</c:if>>Delivery Pending</option>
											<option value="InvoicePending" <c:if test="${order_type eq 'InvoicePending'}">selected="selected"</c:if>>Invoice Pending</option>
											<option value="Delivered" <c:if test="${order_type eq 'Delivered'}">selected="selected"</c:if>>Delivered</option> --%>
										<%-- 	<option value="Authorized" <c:if test="${order_type eq 'Authorized'}">selected="selected"</c:if>>Authorized</option> --%>
										<%-- 	<option value="Recd" <c:if test="${quote_type eq 'Recd'}">selected="selected"</c:if>>Order Recd</option> --%>
									
										</select>
									</div>
								</div>
									<%-- <c:if test="${so_rights.addPermission==1 }"> --%>
									
										<div class='col-sm-2 pull-right new_tran_btn'>
			<div class='form-group pull-right'>
			<a onclick="addPurchaseOrder()"  class="btn btn-success"  ><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;New Purchase Order</a>
			</div>
			</div>	
			
								<%-- 	</c:if> --%>
							
								
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
							<a href='javascript:showPurchaseOrder();' data-toggle="tooltip" title="Refresh">
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
                                 <%--  <c:forEach items="${fixedfields}" var="objFixedfields" >
                                  <c:if test="${objFixedfields.pageFieldName=='so_date'}"> --%>
							
										<div class='col-sm-6'>
											<div class='form-group'>
												<label >From PO Date</label> 
												<div class='input-group date' > 
												<input  type="text" class="form-control" name="so_from_date"
													id="so_from_date" placeholder="Select From Date" value="${so_from_date}"/> 
													<span class="input-group-addon">
																<span class="glyphicon glyphicon-calendar"></span>
															    </span>
												                </div>
													<script type="text/javascript">
			
																							      jQuery('#so_from_date').datepicker({
																										    format: "dd-mm-yyyy"
																										}).on('changeDate', function(e) {
																								            // Revalidate the date field
																								             	event.preventDefault();
					 																							event.stopPropagation();
																								          //  jQuery('#validate-form').bootstrapValidator('revalidateField', 'required_date_${t_det_id}');
																								        });   
																						
																					        			</script>
											</div>
										</div>


										<div class='col-sm-6'>
											<div class='form-group'>
												<label >To PO Date</label>
												<div class='input-group date' > 
												<input
													type="text" class="form-control" name="so_to_date" placeholder="Select To Date"
													id="so_to_date" value="${so_to_date}" /> 
													<span class="input-group-addon">
																<span class="glyphicon glyphicon-calendar"></span>
															    </span>
												                </div>
													<script type="text/javascript">
			
																							      jQuery('#so_to_date').datepicker({
																										    format: "dd-mm-yyyy"
																										}).on('changeDate', function(e) {
																								            // Revalidate the date field
																							             	event.preventDefault();
				 																							event.stopPropagation();
																							          //  jQuery('#validate-form').bootstrapValidator('revalidateField', 'required_date_${t_det_id}');
																							        });  
																						
																					        			</script>
											</div>
										</div>
										<%-- </c:if> --%>
				<%-- <c:if test="${objFixedfields.pageFieldName=='group_id'}"> --%>
				  <div class='col-sm-6'>    
                <div class='form-group'>
					<label  for="group">Group</label> 
					<input type="text" class="form-control" name="group" id="group" value="${group}" onkeyup="clearId(event,'group','group_id')" placeholder="Select Group"/>
					<input type="hidden" class="form-control"  name="group_id" id="group_id" value="${group_id }" />
					</div>
					</div>
				<%-- </c:if> --%>
				<%-- <c:if test="${objFixedfields.pageFieldName=='so_no'}"> --%>
				  <div class='col-sm-6'>    
                <div class='form-group'>
					<label for="so_no">Purchase Order No</label> 
	  				<input type="text" class="form-control" name="Purchase_Order_No" id="Purchase_Order_No" placeholder="Enter Purchase Order No" value="${so_no }"/>
					</div>
					</div>
				<%-- </c:if> --%>
				<%-- <c:if test="${objFixedfields.pageFieldName=='material_id'}"> --%>
				 		
				  
				<%-- </c:if> --%>
				<%-- </c:forEach> --%>
				<div class='col-sm-6'>    
                <div class='form-group'>
					<label for="material">Product</label> 
					<input type="text" class="form-control" name="executive" id="executive" value="${executive}" onkeyup="clearId(event,'executive','executive_id')" placeholder="Product"/>
					<input type="hidden" class="form-control" name="executive_id" id="executive_id" value="${executive_id }" />
				</div>
				</div>
				
				<div class='col-sm-6 '>
						<label for="material" >Purchase Value</label>
						<div class="box-group ">
							<div class="form-group col-sm-6 ">
								<select class="form-control" id="Purchase_val_type" name="Purchase_val_type">
									<option value="1" <c:if test="${order_val_type eq '1'}">selected="selected"</c:if>>Equal to</option>
									<option value="2" <c:if test="${order_val_type eq '2'}">selected="selected"</c:if>>Greater than</option>
									<option value="3" <c:if test="${order_val_type eq '3'}">selected="selected"</c:if>>Lesser than</option>
								</select> 
							</div>
							<div class="form-group col-sm-6 ">
								<input type="text" class="form-control" name="order_val" id="order_val" value="${order_val }" />
							</div>
						</div>
				</div>
				
				
				<div class='col-sm-6'>    
                <div class='form-group'>
					<label for="material">Message displayed in PO</label> 
					<input type="text" class="form-control" name="so_remarks" id="so_remarks" value="${so_remarks}" placeholder="Enter PO Message"/>
				</div>
				</div>
				
				<div class='col-sm-6'>    
                <div class='form-group'>
					<label for="material">Memo for internal use</label> 
					<input type="text" class="form-control" name="so_internal_memo" id="so_internal_memo" value="${so_internal_memo}"  placeholder="Enter Internal Memo"/>
				</div>
				</div>
				                   
                     </div>
            <div class="row">
                                  <button type="button"  class="btn btn-primary pull-left" onclick="clearSOSearch();"><span class="glyphicon glyphicon-remove-circle" ></span>&nbsp;Reset </button>
                                  <button id="search" type="button"  class="btn btn-primary pull-right" onclick="searchSO()"><span class="glyphicon glyphicon-search" ></span>&nbsp;Apply</button>
                                  </div>
                            </div>
                        </div>
                    </div>
                </div>
				
				
				<!-- Single button -->
<div class="btn-group pull-right">
  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   <span class="glyphicon glyphicon-tasks text-primary" ></span>&nbsp; Bulk Action&nbsp;<span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
    <li><a href="#">Print Transactions</a></li>
    <li><a href="#">Print Delivery Challan</a></li>
      <li><a href="#">Pre-Close Transactions</a></li>
    <li role="separator" class="divider"></li>
    <li><a href="#">Send Transactions</a></li>
  </ul>
</div>
 <ul class="pagination pull-right" 	>
					<li class="disabled"><a href="#">&laquo;</a></li>
					<li class="active"><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">&raquo;</a></li>
				</ul>
				<!-- <script>
				
				 var options = {
						  bootstrapMajorVersion:3,
				            currentPage: '${pc.pageNo}',
				            totalPages: '${pc.pageCount}',
				            size:'normal',
				            alignment:'right',
				            onPageClicked: function(e,originalEvent,type,page){
				            	changeSOPageNO(page);
				            }
				        }

				        $('#paging').bootstrapPaginator(options);
				
				</script> -->
			</div>
		</div>
		
         
        
        
<div class="row table-responsive">
		<%-- <div  id="fill_grid">
			${sales_grid}
</div> --%>
<table class="table table-bordered table-condensed table-hover " id="task-table">
<thead>
<tr class="header">
<th style="width: 10%;">Action</th>
<th width="53">PO&nbsp;NO </th>
<th width="70">Date</th>
<th width="85">Indent&nbsp;Type </th>
<th width="99">Supplier</th>
<th width="60">IO No</th>
<th width="66">Item</th>
<td width="56"><strong>Colour</strong></td>
<th width="40">UOM</th>
<th width="25">Qty</th>
<th width="34">Price</th>
<th width="27">Dis %</th>
<th width="26">ED %</th>
<th width="24">ST %</th>
<td width="52">Amount</td>
<th width="73">Delivery</th>
<th width="91">Description</th>
<th width="77">Attachment</th>
<th width="25"><div class="checkbox"><input class="checkbox_1" type="checkbox" id="toggle_check_all" name="toggle_check_all">
		 <label for="toggle_check_all" class="checkbox_1"></label>
		 </div></input></th>
</tr>
</thead>
<tbody>
<tr class="datarow">
<td>
<select class="form-control" id="unit">
			    <option>Action</option>
			    <option>Preview/Print</option>
			    <option>View</option>
			    <option>Edit</option>
			    <option>Cancel</option>
			    <option>Delete</option>  
			  </select></td>
<td height="68" width="64">1.1516</td>
<td width="70" nowrap="nowrap">15-Feb-16</td>
<td width="64">IORMR</td>
<td width="64">Tannery A</td>
<td width="145">164.1516</td>
<td width="64">COW BOOTY NAPPA    0.9-1.0</td>
<td width="64">Black</td>
<td width="90">Sq.Ft</td>
<td width="93">1500</td>
<td width="114">110</td>
<td width="84">0</td>
<td width="101">0</td>
<td width="90">5</td>
<td width="64">173250</td>
<td width="65" nowrap="nowrap">16-Mar-16</td>
<td width="64">&nbsp;</td>
<td></td>
<td> <div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_1" name="se_check_1" value="">
		  <label for="se_check_1" class="checkbox_1"></label>
		  </div> </td>
</tr>
<tr class="datarow">
  <td><select class="form-control" id="unit">
    <option>Action</option>
    <option>Preview/Print</option>
    <option>View</option>
    <option>Edit</option>
    <option>Cancel</option>
    <option>Delete</option>
  </select>  </td>
  <td height="68" width="64">1.1516</td>
  <td width="65" nowrap="nowrap">15-Feb-16</td>
  <td width="64">IORMR</td>
  <td width="64">Tannery A</td>
  <td width="145">164.1516</td>
  <td width="64">COW    FINISHED LINING 0.7/0.9</td>
  <td width="64">MAHOGANY</td>
  <td width="90">Sq.Ft</td>
  <td width="93">1250</td>
  <td width="114">120</td>
  <td width="84">0</td>
  <td width="101">0</td>
  <td width="90">5</td>
  <td width="64">157500</td>
  <td width="65" nowrap="nowrap">16-Mar-16</td>
  <td width="64">&nbsp;</td>
  <td></td>
  <td><div class="checkbox">
    <input class="checkbox_1" type="checkbox" id="se_check_2" name="se_check_2" value="" />
    <label for="se_check_2" class="checkbox_1"></label>
  </div></td>
</tr>
<tr class="datarow">
  <td><select class="form-control" id="unit">
    <option>Action</option>
    <option>Preview/Print</option>
    <option>View</option>
    <option>Edit</option>
    <option>Cancel</option>
    <option>Delete</option>
  </select>  </td>
  <td height="68" width="64">2.1516</td>
  <td width="65" nowrap="nowrap">17-Feb-16</td>
  <td width="64">IORMR</td>
  <td width="64">SUN SOLES</td>
  <td width="145">166.2015</td>
  <td width="64">SOLE    HALF RUBBER KRISTIN</td>
  <td width="64">MAHOGANY</td>
  <td width="90">PAIR</td>
  <td width="93">350</td>
  <td width="114">300</td>
  <td width="84">0</td>
  <td width="101">0</td>
  <td width="90">5</td>
  <td width="64">110250</td>
  <td width="65" nowrap="nowrap">3-Mar-16</td>
  <td width="64">&nbsp;</td>
  <td></td>
  <td><div class="checkbox">
    <input class="checkbox_1" type="checkbox" id="se_check_3" name="se_check_3" value="" />
    <label for="se_check_3" class="checkbox_1"></label>
  </div></td>
</tr>
<tr class="datarow">
  <td><select class="form-control" id="unit">
    <option>Action</option>
    <option>Preview/Print</option>
    <option>View</option>
    <option>Edit</option>
    <option>Cancel</option>
    <option>Delete</option>
  </select>  </td>
  <td height="68" width="64">3.1516</td>
  <td width="65" nowrap="nowrap">17-Feb-16</td>
  <td width="64">Stock Indent</td>
  <td width="64">S S Creation</td>
  <td width="145">0</td>
  <td width="64">NO.8    YKK SLIDER 2WAY</td>
  <td width="64">ANTIQUE    BLK</td>
  <td width="90">NOS</td>
  <td width="93">500</td>
  <td width="114">1.25</td>
  <td width="84">0</td>
  <td width="101">0</td>
  <td width="90">5</td>
  <td width="64">656.25</td>
  <td width="65" nowrap="nowrap">24-Feb-16</td>
  <td width="64">&nbsp;</td>
  <td></td>
  <td><div class="checkbox">
    <input class="checkbox_1" type="checkbox" id="se_check_4" name="se_check_4" value="" />
    <label for="se_check_4" class="checkbox_1"></label>
  </div></td>
</tr>
<tr class="datarow">
  <td><select class="form-control" id="unit">
    <option>Action</option>
    <option>Preview/Print</option>
    <option>View</option>
    <option>Edit</option>
    <option>Cancel</option>
    <option>Delete</option>
  </select>  </td>
  <td height="68" width="64">4.1516</td>
  <td width="65" nowrap="nowrap">21-Feb-16</td>
  <td width="64">Stock Indent</td>
  <td width="64">Tannery B</td>
  <td width="145">168.2015</td>
  <td width="64">LIN    1988 COW DD LINING 0.9/1.0</td>
  <td width="64">Black</td>
  <td width="90">Sq.Ft</td>
  <td width="93">3000</td>
  <td width="114">45</td>
  <td width="84">0</td>
  <td width="101">0</td>
  <td width="90">5</td>
  <td width="64">141750</td>
  <td width="65" nowrap="nowrap">16-Mar-16</td>
  <td width="64">&nbsp;</td>
  <td></td>
  <td><div class="checkbox">
    <input class="checkbox_1" type="checkbox" id="se_check_5" name="se_check_5" value="" />
    <label for="se_check_5" class="checkbox_1"></label>
  </div></td>
</tr>
<tr class="datarow">
  <td><select class="form-control" id="unit">
    <option>Action</option>
    <option>Preview/Print</option>
    <option>View</option>
    <option>Edit</option>
    <option>Cancel</option>
    <option>Delete</option>
  </select>  </td>
  <td height="68" width="64">4.1516</td>
  <td width="65" nowrap="nowrap">21-Feb-16</td>
  <td width="64">IORMR</td>
  <td width="64">Tannery B</td>
  <td width="145">169.2015</td>
  <td width="64">LIN    1901 COW DD LINING 1.1/1.3</td>
  <td width="64">DK    BROWN</td>
  <td width="90">Sq.Ft</td>
  <td width="93">4000</td>
  <td width="114">45</td>
  <td width="84">0</td>
  <td width="101">0</td>
  <td width="90">5</td>
  <td width="64">189000</td>
  <td width="65" nowrap="nowrap">16-Mar-16</td>
  <td width="64">&nbsp;</td>
  <td></td>
  <td><div class="checkbox">
    <input class="checkbox_1" type="checkbox" id="se_check_6" name="se_check_6" value="" />
    <label for="se_check_6" class="checkbox_1"></label>
  </div></td>
</tr>
<tr class="datarow">
  <td><select class="form-control" id="unit">
    <option>Action</option>
    <option>Preview/Print</option>
    <option>View</option>
    <option>Edit</option>
    <option>Cancel</option>
    <option>Delete</option>
  </select>  </td>
  <td height="51" width="64">5.1516</td>
  <td width="65" nowrap="nowrap">21-Feb-16</td>
  <td width="64">Stock Indent</td>
  <td width="64">S S Creation</td>
  <td width="145">0</td>
  <td width="64">No.5    YKK Coil Zipper</td>
  <td width="64">BLACK</td>
  <td width="90">MTR</td>
  <td width="93">60</td>
  <td width="114">7</td>
  <td width="84">0</td>
  <td width="101">0</td>
  <td width="90">5</td>
  <td width="64">441</td>
  <td width="65" nowrap="nowrap">27-Feb-16</td>
  <td width="64">&nbsp;</td>
  <td></td>
  <td><div class="checkbox">
    <input class="checkbox_1" type="checkbox" id="se_check_7" name="se_check_7" value="" />
    <label for="se_check_7" class="checkbox_1"></label>
  </div></td>
</tr>
<tr class="datarow">
  <td><select class="form-control" id="unit">
    <option>Action</option>
    <option>Preview/Print</option>
    <option>View</option>
    <option>Edit</option>
    <option>Cancel</option>
    <option>Delete</option>
  </select>  </td>
  <td height="51" width="64">6.1516</td>
  <td width="65" nowrap="nowrap">22-Feb-16</td>
  <td width="64">Stock Indent</td>
  <td width="64">ASTRA IMPEX</td>
  <td width="145">0</td>
  <td width="64">THK    40 APTAN WHITE</td>
  <td width="64">Z0583</td>
  <td width="90">C-1000</td>
  <td width="93">20</td>
  <td width="114">125</td>
  <td width="84">0</td>
  <td width="101">0</td>
  <td width="90">5</td>
  <td width="64">2625</td>
  <td width="65" nowrap="nowrap">28-Feb-16</td>
  <td width="64" nowrap="nowrap">&nbsp;</td>
  <td></td>
  <td><div class="checkbox">
    <input class="checkbox_1" type="checkbox" id="se_check_8" name="se_check_8" value="" />
    <label for="se_check_8" class="checkbox_1"></label>
  </div></td>
</tr>
<tr class="datarow">
  <td><select class="form-control" id="unit">
    <option>Action</option>
    <option>Preview/Print</option>
    <option>View</option>
    <option>Edit</option>
    <option>Cancel</option>
    <option>Delete</option>
  </select>  </td>
  <td height="51" width="64">6.1516</td>
  <td width="65" nowrap="nowrap">22-Feb-16</td>
  <td width="64">Stock Indent</td>
  <td width="64">ASTRA IMPEX</td>
  <td width="145">0</td>
  <td width="64">THK    40 APTAN BLACK</td>
  <td width="64">Z0040</td>
  <td width="90">C-1500</td>
  <td width="93">20</td>
  <td width="114">145</td>
  <td width="84">0</td>
  <td width="101">0</td>
  <td width="90">5</td>
  <td width="64">3045</td>
  <td width="65" nowrap="nowrap">28-Feb-16</td>
  <td width="64">&nbsp;</td>
  <td></td>
  <td><div class="checkbox">
    <input class="checkbox_1" type="checkbox" id="se_check_9" name="se_check_9" value="" />
    <label for="se_check_9" class="checkbox_1"></label>
  </div></td>
</tr>
<tr class="datarow">
  <td><select class="form-control" id="unit">
    <option>Action</option>
    <option>Preview/Print</option>
    <option>View</option>
    <option>Edit</option>
    <option>Cancel</option>
    <option>Delete</option>
  </select>  </td>
  <td height="68" width="64">7.1516</td>
  <td width="65" nowrap="nowrap">23-Feb-16</td>
  <td width="64">IORMR</td>
  <td width="64">S S Creation</td>
  <td width="145">173.2015</td>
  <td width="64">Label-06294    Transperant</td>
  <td width="64">NA</td>
  <td width="90">NOS</td>
  <td width="93">250</td>
  <td width="114">2</td>
  <td width="84">0</td>
  <td width="101">0</td>
  <td width="90">5</td>
  <td width="64">525</td>
  <td width="65" nowrap="nowrap">29-Feb-16</td>
  <td width="64">&nbsp;</td>
  <td></td>
  <td><div class="checkbox">
    <input class="checkbox_1" type="checkbox" id="se_check_10" name="se_check_10" value="" />
    <label for="se_check_10" class="checkbox_1"></label>
  </div></td>
</tr>
<tr class="datarow">
<td>
<select class="form-control" id="unit">
			    <option>Action</option>
			    <option>Preview/Print</option>
			    <option>View</option>
			    <option>Edit</option>
			    <option>Cancel</option>
			    <option>Delete</option>  
			  </select></td>
<td height="41" width="64">8.1516</td>
<td width="65" nowrap="nowrap">23-Feb-16</td>
<td width="64">Stock Indent</td>
<td width="64">United Chem</td>
<td width="145">0</td>
<td width="64">FEVICOL    505</td>
<td width="64">NA</td>
<td width="90">TIN</td>
<td width="93">25</td>
<td width="114">545</td>
<td width="84">0</td>
<td width="101">0</td>
<td width="90">5</td>
<td width="64">14306.25</td>
<td width="65" nowrap="nowrap">29-Feb-16</td>
<td width="64">&nbsp;</td>
<td></td>
<td> <div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_11" name="se_check_11" value="">
		  <label for="se_check_11" class="checkbox_1"></label>
		  </div> </td>
</tr>
</tbody>
</table>
	    </div>
       </div>           
     </div>  
    </div>
    <jsp:include page="../common/ColumnPreferences.jsp"/>
      <jsp:include page="../common/Footer.jsp"/>
      
<input type="hidden" name="servlet_name" id="servlet_name"/>
<input type="hidden" name="request_type" id="request_type" value="${request_type}"/>
<input type="hidden" name="callbackmethod_name" id="callbackmethod_name"/>
<input type="hidden" name="pageno" id="pageno" value="${pc.pageNo}"/>
<input type="hidden" name="total_pages" id="total_pages" value="${pc.pageCount}"/>
<input type="hidden" name="head_mode" id="head_mode"/>
<input type="hidden" name="so_id" id="so_id"  />
<input type="hidden" name="so_det_id" id="so_det_id"  />
<input type="hidden" name="screenId" id="screenId"  />
<input type="hidden" name="invoke_servlet" id="invoke_servlet" value="${invoke_servlet}" /> 
<input type="hidden" name="invoke_method" id="invoke_method" value="${invoke_method}" /> 
<input type="hidden" name="no_type" id="no_type"  />

<!-- for manual sales account configuration start   -->     
<input type="hidden" name="config_option" id="config_option" />
<input type="hidden" name="config_key" id="config_key" />
<!-- for manual sales account configuration end   -->      
     
<input type="hidden" id="trno_invoice" data-toggle="modal" data-target="#trNoModal"   />
<input type="hidden" id="autono_table_name" name="autono_table_name" value="set_autono_sales_invoice"  />
    <input type="hidden" name="tr_tag" id="tr_tag"  />
     <input type="hidden" name="view_mode" id="view_mode" />
<input type="hidden" id="trno_po" data-toggle="modal" data-target="#poNoModal"   />
 
         
  <script>
  viewTableTextStyle('.table-condensed');
  
  
  $('#task-table input:checkbox').click(function () {
	    $(this).closest('tr').removeClass('highlight-row');
	    if ($(this).is(':checked')) $(this).closest('tr').addClass('highlight-row');
	}) 


$('#toggle_check_all').click(function(e){
    var table= $(e.target).closest('table');
    $('td input:checkbox',table).prop('checked',this.checked);
}); 


  


!function ($) {
	 
	$(function(){
			
		  $('#so_customer').listCustomer({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCustomer",
			  nameField :'#so_customer',
			  idField:'#so_customer_id'
		  });
		  
		  $('#material').listMaterial({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterial",
			  nameField :'#material',
			  idField:'#material_id'
		  });
		  
		  $('#group').listGroup({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroup",
			  nameField :'#group',
			  idField:'#group_id'
		  });
		
		  
	 });
}(window.jQuery);

	 
</script>
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
            <%-- <jsp:include page="../purchase/ManualNoForPurchaseOrder.jsp"/> --%>
			
           <%--  <jsp:include page="../common/TrNoForTransaction.jsp">
			<jsp:param value="Invoice No." name="title"/>
				<jsp:param value="Invoice No." name="tr_lable"/>
			</jsp:include>
			
			
			<jsp:include page="../common/PONoForTransaction.jsp">
			<jsp:param value="Purchase Order No." name="po_title"/>
				<jsp:param value="Purchase Order No." name="po_lable"/>
			</jsp:include>
			
			<jsp:include page="../masters/SalesAccountConfigModal.jsp" /> --%>
			
               <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script> 
      </body>
      </html>
      