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
                  <h2 class="page-header">Issues (ISS)</h2>
                </div>	
         </div>
         <div class="row row-no-margin" > 
      
				    <div class='col-sm-3'>    
                <div class='form-group'>
				<label for="so_customer">Issue No</label>
				<input type="text" class="form-control"  name="Issue_No" id="Issue_No" placeholder=" Issue No Search..." value="" onblur=""  onkeyup="" onkeypress=""/>
				</div>
            </div>
				
      
             
             		<div class='col-sm-3'>
					  <div class='form-group'>
										<label for="quote_type" style="display:block;">Warehouse</label>
										<p><input type="text" class="form-control"  name="Warehouse" id="Warehouse" placeholder=" Warehouse Search..." value="" onblur=""  onkeyup="" onkeypress=""/>
								    </p>
					  </div>
		   </div>
									<%-- <c:if test="${so_rights.addPermission==1 }"> --%>
									
										<div class='col-sm-2 pull-right new_tran_btn'>
			<div class='form-group pull-right'>
			<a onclick="addIssues()"  class="btn btn-success"  ><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;New Issues</a>
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
							<a href='javascript:showIssues();' data-toggle="tooltip" title="Refresh">
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
												<label >From  Date</label> 
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
												<label >To  Date</label>
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
						<div class='col-sm-6 '>
						<label for="material" >Issue Type</label>
						<div class="box-group ">
							<div class="form-group col-sm-6 ">
								<select class="form-control" id="Purchase_val_type" name="Purchase_val_type">
									<option value="1">Sample</option>
									<option value="2">Production</option>
									<option value="3">Common</option>
									<option value="3">Subcontract</option>
								</select> 
							</div>
						  </div>
				</div>								
				
				<div class='col-sm-6'>    
                <div class='form-group'>
					<label for="material">Issue To</label> 
					<input type="text" class="form-control" name="issue_to" id="issue_to" value="" onkeyup="" placeholder="Issue To"/>
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
      <li role="separator" class="divider"></li>
    <li><a href="#">Post Return</a></li>
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
				
			</div>
		</div>
		
         
        
        
<div class="row table-responsive">
		
<table class="table table-bordered table-condensed table-hover " id="task-table">
<thead>
<tr class="header">
<th width="114">Action</th>
<th width="60">Issue&nbsp;No</th>
<th width="75">Issue&nbsp;Date</th>
<th width="77">Issue&nbsp;Type</th>
<th width="96">Issue&nbsp;To</th>
<th width="122">Warehouse</th>
<th width="90">IO&nbsp;NO</th>
<th width="102">Item</th>
<th width="53">Colour</th>
<th width="47">UOM</th>
<th width="65">Quantity</th>
<th width="101">Description</th>
<th width="77">Attachment</th>
<th width="27"><div class="checkbox"><input class="checkbox_1" type="checkbox" id="toggle_check_all" name="toggle_check_all">
		 <label for="toggle_check_all" class="checkbox_1"></label>
		 </div></th>
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
<td height="34" width="64">233.2015</td>
<td width="94">23-Jan-16</td>
<td width="86">Production</td>
<td width="134">Cutting Line2</td>
<td width="64">LEATHER STORE</td>
<td width="123">231.2015</td>
<td width="116">COW BOOTY NAPPA    0.9-1.0</td>
<td width="64">Black</td>
<td width="64">Sq.Ft</td>
<td width="64">125</td>
<td width="64">&nbsp;</td>
<td width="64">&nbsp;</td>
<td><div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_1" name="se_check_1" value="">
		  <label for="se_check_1" class="checkbox_1"></label>
		  </div> </td>
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
<td height="34" width="64">234.2015</td>
<td width="94">24-Jan-16</td>
<td width="86">Sample    Issue</td>
<td width="134">Sample    Line 1</td>
<td width="64">SAMPLE    STORE</td>
<td width="123">0</td>
<td width="116">COW    FINISHED LINING 0.7/0.9</td>
<td width="64">MAHOGANY</td>
<td width="64">Sq.Ft</td>
<td width="64">245</td>
<td width="64">&nbsp;</td>
<td width="64">&nbsp;</td>
<td><div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_2" name="se_check_2" value="">
		  <label for="se_check_2" class="checkbox_1"></label>
		  </div> </td>
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
<td height="34" width="64">235.2015</td>
<td width="94">25-Jan-16</td>
<td width="86">Production</td>
<td width="134">Assemby    Line2</td>
<td width="64">Material    Store</td>
<td width="123">233.2015</td>
<td width="116">SOLE    HALF RUBBER KRISTIN</td>
<td width="64">MAHOGANY</td>
<td width="64">PAIR</td>
<td width="64">30</td>
<td width="64">&nbsp;</td>
<td width="64">&nbsp;</td>
<td><div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_3" name="se_check_3" value="">
		  <label for="se_check_3" class="checkbox_1"></label>
		  </div> </td>
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
<td height="34" width="64">236.2015</td>
<td width="94">26-Jan-16</td>
<td width="86">Production</td>
<td width="134">Component    Line 1</td>
<td width="64">Material    Store</td>
<td width="123">234.2015</td>
<td width="116">NO.8    YKK SLIDER 2WAY</td>
<td width="64">ANTIQUE    BLK</td>
<td width="64">NOS</td>
<td width="64">40</td>
<td width="64">&nbsp;</td>
<td width="64">&nbsp;</td>
<td><div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_4" name="se_check_4" value="">
		  <label for="se_check_4" class="checkbox_1"></label>
		  </div> </td>
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
<td height="34" width="64">237.2015</td>
<td width="94">27-Jan-16</td>
<td width="86">Sample    Issue</td>
<td width="134">Sample    Line 1</td>
<td width="64">SAMPLE    STORE</td>
<td width="123">0</td>
<td width="116">LIN    1988 COW DD LINING 0.9/1.0</td>
<td width="64">Black</td>
<td width="64">Sq.Ft</td>
<td width="64">115</td>
<td width="64">&nbsp;</td>
<td width="64">&nbsp;</td>
<td><div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_5" name="se_check_5" value="">
		  <label for="se_check_5" class="checkbox_1"></label>
		  </div> </td>
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
<td height="34" width="64">238.2015</td>
<td width="94">28-Jan-16</td>
<td width="86">Production</td>
<td width="134">Cutting    Line2</td>
<td width="64">LEATHER    STORE</td>
<td width="123">236.2015</td>
<td width="116">LIN    1901 COW DD LINING 1.1/1.3</td>
<td width="64">DK    BROWN</td>
<td width="64">Sq.Ft</td>
<td width="64">65</td>
<td width="64">&nbsp;</td>
<td width="64">&nbsp;</td>
<td><div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_6" name="se_check_6" value="">
		  <label for="se_check_6" class="checkbox_1"></label>
		  </div> </td>
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
<td height="34" width="64">239.2015</td>
<td width="94">29-Jan-16</td>
<td width="86">Production</td>
<td width="134">Cutting    Line2</td>
<td width="64">Material    Store</td>
<td width="123">237.2015</td>
<td width="116">No.5    YKK Coil Zipper</td>
<td width="64">BLACK</td>
<td width="64">MTR</td>
<td width="64">10</td>
<td width="64">&nbsp;</td>
<td width="64">&nbsp;</td>
<td><div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_7" name="se_check_7" value="">
		  <label for="se_check_7" class="checkbox_1"></label>
		  </div> </td>
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
<td height="34" width="64">240.2015</td>
<td width="94">30-Jan-16</td>
<td width="86">Sample    Issue</td>
<td width="134">Sample    Line 1</td>
<td width="64">SAMPLE    STORE</td>
<td width="123">0</td>
<td width="116">THK    40 APTAN WHITE</td>
<td width="64">Z0583</td>
<td width="64">C-1000</td>
<td width="64">2</td>
<td width="64">&nbsp;</td>
<td width="64">&nbsp;</td>
<td><div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_8" name="se_check_8" value="">
		  <label for="se_check_8" class="checkbox_1"></label>
		  </div> </td>
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
<td height="34" width="64">241.2015</td>
<td width="94">31-Jan-16</td>
<td width="86">Subcontrac</td>
<td width="134">Subcontractor    2</td>
<td width="64">Material    Store</td>
<td width="123">239.2015</td>
<td width="116">THK    40 APTAN BLACK</td>
<td width="64">Z0040</td>
<td width="64">C-1500</td>
<td width="64">1</td>
<td width="64">&nbsp;</td>
<td width="64">&nbsp;</td>
<td><div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_9" name="se_check_9" value="">
		  <label for="se_check_9" class="checkbox_1"></label>
		  </div> </td>
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
<td height="35" width="64">242.2015</td>
<td width="94">1-Feb-16</td>
<td width="86">Common</td>
<td width="134">Assemby    Line1</td>
<td width="64">Material    Store</td>
<td width="123">0</td>
<td width="116">FEVICOL    505</td>
<td width="64">NA</td>
<td width="64">TIN</td>
<td width="64">2</td>
<td width="64">&nbsp;</td>
<td>&nbsp;</td>
<td><div class="checkbox">
		  <input class="checkbox_1" type="checkbox" id="se_check_10" name="se_check_10" value="">
		  <label for="se_check_10" class="checkbox_1"></label>
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
      