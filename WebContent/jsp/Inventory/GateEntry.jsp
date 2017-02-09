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
				<jsp:param value="Inventory.Purchase.GoodReceipt" name="screen_name" />
			</jsp:include>
<div id="page-wrapper">
<div id="page-inner">
         <div class="row">
                <div class="col-md-11 col-sm-12 col-xs-8">
                    <h3 class="page-header">Gate Entry (GE) </h3>
                </div>	
             
         </div>
         
         <div class="row row-no-margin" > 
							<div class='col-sm-3'>
												<div class='form-group'>
													<label >Supplier</label> <input
														type="text" class="form-control" name="Supplier" id="Supplier" placeholder="Enter Supplier"
														id="bl_order_no" />
												</div>
											</div>
											<div class='col-sm-2 pull-right new_tran_btn'>
			<div class='form-group pull-right'>
			<a onclick="addGateEntry()"  class="btn btn-success"  ><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;New Gate Entry</a>
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
							<a href='javascript:showGateEntry();' data-toggle="tooltip" title="Refresh">
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
												<label >From Date</label> 
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
												<label >To Date</label>
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
										<div class='col-sm-6'>
												<div class='form-group'>
													<label >Group</label> <input
														type="text" class="form-control" name="Group" id="Group" placeholder="Group" />
												</div>
											</div>
									<div class='col-sm-6'>
												<div class='form-group'>
													<label >Group Type</label> 
												<select class="form-control">
													<option value="0">&lt;--Select--&gt;</option>
													  <option value="1">Raw material</option>
													  <option value="2">Accessories</option>
													  <option value="3">Consumables</option>
													  <option value="4">Components</option>
													  <option value="5">Spares and Tools</option>
													  <option value="6">Fixed assets</option>
													  <option value="7">Finished Goods</option>
													  <option value="8">Others</option>
													</select>
												</div>
											</div>
                                	
											<div class='col-sm-6'>
												<div class='form-group'>
													<label >Warehouse</label> <input
														type="text" class="form-control" name="Warehouse" id="Warehouse" placeholder="Warehouse"/>
												</div>
											</div>
											<div class='col-sm-6'>
												<div class='form-group'>
													<label > Goods Receipt Type</label> 
														<select class="form-control">
													<option value="0">&lt;--Select--&gt;</option>
													  <option value="1">Against PO</option>
													  <option value="2">Case</option>
													  <option value="3">Sample</option>
													  <option value="4">Customer</option>
													 </select>
												</div>
											</div>
											<div class='col-sm-6'>
												<div class='form-group'>
													<label > PO No</label> <input
														type="text" class="form-control" name=" Po_No" id=" Po_No" placeholder="Po No" />
												</div>
											</div>
											<div class='col-sm-6'>
												<div class='form-group'>
													<label > Variant</label> <input
														type="text" class="form-control" name=" Variant" id=" Variant" placeholder="Variant" />
												</div>
											</div>
											<div class='col-sm-6'>
												<div class='form-group'>
													<label > Item</label> <input
														type="text" class="form-control" name=" Material" id=" Material" placeholder="Material" />
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
		<table class="table table-bordered table-condensed table-hover " id="task-table">
		    <thead>
		    <tr class="header">
			 <th >&nbsp;&nbsp;Action&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
					              <th >GE No.</th>
						          <th >Date</th>
						  <th >Supplier</th>
						  <th >Supplier DN No.</th>
						  <th >Supplier DN Date</th>  
						  <th >Invoice Num</th>
						  <th >invoice Date</th>  
						  <th >Warehouse</th>   
						  <th >Currency</th> 
						  <th >Exchange Rate</th>   
						  <th > PO No.</th>
						  <th >IO No.</th>
						  <th >GROUP</th>
						  <th >Item Code</th>
						  <th >Item</th>
						  <th >Colour</th>
					     <th >UOM</th>
						  <th >Qty as per DN</th>  
						  <th >Rate/Unit</th>   
						  <th >DISCOUNT %</th>  
						  <th >Excise Duty %</th>
						  <th >ECESS %</th>  
						  <th >SH ECESS %</th>  
						  <th ><div align="right">VAT/ST/CST %</div></th>   
						  <th >Surcharge %</th>
						  <th >Value with Tax</th> 
						  <th >Lot No.</th>   
						  <th >Expiry Date</th>
		    </tr>
		    </thead>
		    <tbody>	
		    
		     <tr class="datarow">	
		     <td>
					<select class="form-control" id="sel1">
					    <option>Select Action</option>
					    <option>Edit</option>
					    <option>Cancel</option>
					    <option>View</option>
					    <option>Print GRN</option>
					    <option>Rate Revise</option>
					    <option>Post Return</option>					  
					</select>				</td>				                  		                  
			 <td height="68" width="64">1.1516</td>
			 <td width="94" nowrap="nowrap">22-Feb-16</td>
			 <td width="86">Tannery A</td>
			 <td width="134">4586</td>
			 <td width="64">22-Feb-16</td>
			 <td width="123">54671</td>
			 <td width="116">1-Oct-15</td>
			 <td width="64">LEATHER STORE</td>
			 <td width="64">INR</td>
			 <td width="64">1</td>
			 <td width="64">1.1516</td>
			 <td width="64">164.1516</td>
			 <td width="64">Leather</td>
			 <td width="64">UPL000000001</td>
			 <td width="64">COW BOOTY NAPPA    0.9-1.0</td>
			 <td width="64">Black</td>
			 <td width="64">Sq.Ft</td>
			 <td width="64">1000</td>
			 <td width="64">110</td>
			 <td width="64">0</td>
			 <td width="64">0</td>
			 <td width="64">0</td>
			 <td width="64">0</td>
			 <td width="64">5</td>
			 <td width="64">0</td>
			 <td width="64">115500</td>
			 <td width="64">GL4578</td>
			 <td width="64">&nbsp;</td>
		     </tr>	
          		      <tr class="datarow">	 
          		      <td>
					<select class="form-control" id="sel1">
					     <option>Select Action</option>
					    <option>Edit</option>
					    <option>Cancel</option>
					    <option>View</option>
					    <option>Print GRN</option>
					    <option>Rate Revise</option>
					    <option>Post Return</option>			  
					</select>				</td>                 		                  
					  <td height="68" width="64">1.1516</td>
					  <td width="94">22-Feb-16</td>
					  <td width="86">Tannery    A</td>
					  <td width="134">4586</td>
					  <td width="64">22-Feb-16</td>
					  <td width="123">54671</td>
					  <td width="116">1-Oct-15</td>
					  <td width="64">LEATHER    STORE</td>
					  <td width="64">INR</td>
					  <td width="64">1</td>
					  <td width="64">1.1516</td>
					  <td width="64">164.1516</td>
					  <td width="64">Leather</td>
					  <td width="64">LIN000000001</td>
					  <td width="64">COW    FINISHED LINING 0.7/0.9</td>
					  <td width="64">MAHOGANY</td>
					  <td width="64">Sq.Ft</td>
					  <td width="64">750</td>
					  <td width="64">120</td>
					  <td width="64">0</td>
					  <td width="64">0</td>
					  <td width="64">0</td>
					  <td width="64">0</td>
					  <td width="64">5</td>
					  <td width="64">0</td>
					  <td width="64">94500</td>
					  <td width="64">GL4579</td>
					  <td width="64">&nbsp;</td>
       		      </tr>
									   <tr class="datarow">	
									   <td>
					<select class="form-control" id="sel1">
					     <option>Select Action</option>
					    <option>Edit</option>
					    <option>Cancel</option>
					    <option>View</option>
					    <option>Print GRN</option>
					    <option>Rate Revise</option>
					    <option>Post Return</option>			  
					</select>				</td>				                  		                  
					                   <td height="68" width="64">3.1516</td>
					                   <td width="94">22-Feb-16</td>
					                   <td width="86">SUN    SOLES</td>
					                   <td width="134">5489</td>
					                   <td width="64">22-Feb-16</td>
					                   <td width="123">SD2323</td>
					                   <td width="116">1-Oct-15</td>
					                   <td width="64">Material    Store</td>
					                   <td width="64">INR</td>
					                   <td width="64">1</td>
					                   <td width="64">2.1516</td>
					                   <td width="64">166.2015</td>
					                   <td width="64">Sole</td>
					                   <td width="64">SOL000000001</td>
					                   <td width="64">SOLE    HALF RUBBER KRISTIN</td>
					                   <td width="64">MAHOGANY</td>
					                   <td width="64">PAIR</td>
					                   <td width="64">350</td>
					                   <td width="64">300</td>
					                   <td width="64">0</td>
					                   <td width="64">0</td>
					                   <td width="64">0</td>
					                   <td width="64">0</td>
					                   <td width="64">5</td>
					                   <td width="64">0</td>
					                   <td width="64">110250</td>
					                   <td width="64">&nbsp;</td>
					                   <td width="64">&nbsp;</td>
				     </tr>
					          		  
						             	
					          <tr class="datarow">	 
					          <td>
					<select class="form-control" id="sel1">
					    <option>Select Action</option>
					    <option>Edit</option>
					    <option>Cancel</option>
					    <option>View</option>
					    <option>Print GRN</option>
					    <option>Rate Revise</option>
					    <option>Post Return</option>				  
					</select>				</td>                		                  
					          <td height="68" width="64">4.1516</td>
					          <td width="94">22-Feb-16</td>
					          <td width="86">S    S Creation</td>
					          <td width="134">15</td>
					          <td width="64">22-Feb-16</td>
					          <td width="123">2234/1516</td>
					          <td width="116">1-Oct-15</td>
					          <td width="64">Material    Store</td>
					          <td width="64">INR</td>
					          <td width="64">1</td>
					          <td width="64">3.1516</td>
					          <td width="64">0</td>
					          <td width="64">Zippers    &amp; Runners</td>
					          <td width="64">ZIP000000001</td>
					          <td width="64">NO.8    YKK SLIDER 2WAY</td>
					          <td width="64">ANTIQUE    BLK</td>
					          <td width="64">NOS</td>
					          <td width="64">500</td>
					          <td width="64">1.25</td>
					          <td width="64">0</td>
					          <td width="64">0</td>
					          <td width="64">0</td>
					          <td width="64">0</td>
					          <td width="64">5</td>
					          <td width="64">0</td>
					          <td width="64">656.25</td>
					          <td width="64">&nbsp;</td>
					          <td width="64">&nbsp;</td>
		          </tr>
          		  <tr class="datarow">	
          		   <td>
					<select class="form-control" id="sel1">
					    <option>Select Action</option>
					    <option>Edit</option>
					    <option>Cancel</option>
					    <option>View</option>
					    <option>Print GRN</option>
					    <option>Rate Revise</option>
					    <option>Post Return</option>		  
					</select>				</td>                
          		   <td height="69" width="64">5.1516</td>
          		   <td width="94">22-Feb-16</td>
          		   <td width="86">Tannery    B</td>
          		   <td width="134">465</td>
          		   <td width="64">22-Feb-16</td>
          		   <td width="123">54675</td>
          		   <td width="116">1-Oct-15</td>
          		   <td width="64">LEATHER    STORE</td>
          		   <td width="64">INR</td>
          		   <td width="64">1</td>
          		   <td width="64">4.1516</td>
          		   <td width="64">168.2015</td>
          		   <td width="64">Lining</td>
          		   <td width="64">LIN000000002</td>
          		   <td width="64">LIN    1988 COW DD LINING 0.9/1.0</td>
          		   <td width="64">Black</td>
          		   <td width="64">Sq.Ft</td>
          		   <td width="64">2200</td>
          		   <td width="64">45</td>
          		   <td width="64">0</td>
          		   <td width="64">0</td>
          		   <td width="64">0</td>
          		   <td width="64">0</td>
          		   <td align="right">5</td>
          		   <td width="64">0</td>
          		   <td width="64">103950</td>
          		   <td width="64">GL4580</td>
          		   <td>&nbsp;</td>
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
            