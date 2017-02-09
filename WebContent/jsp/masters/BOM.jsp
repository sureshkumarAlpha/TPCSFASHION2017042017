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
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/masters/BOM.js"></script>
<style>
/* 


label {
    display: inline-block;
    max-width: 100%;
    margin-bottom: 0px;
    font-weight: 700;
}

.form-group{
margin-bottom:4px;
}


.form-control{
	height:25px;
	font-size: 11px;
}
.input-group-addon {
    padding: 4px 8px;
    font-size: 12px;
}

   ul.pagination.pull-right li a {
    padding: 3px 12px;
}
   
ul.pagination.pull-left li a {
    padding: 5px 12px;
}

ul.pagination.pull-left li span {
    padding: 2.5px 0px;
}
tr.datarow {
    text-align: left;
    border: 1px dotted #c7c7c7;
    font-size: 11px;
}

tr.header {
    background-color: #337ab7;
    color: white;
    text-align: center;
    text-transform: uppercase;
    font-size: 11px;
}
 
 .btn {
    padding: 1px 12px;
    } */
</style>

<script language="javascript">
	var contextpath = "${pageContext.request.contextPath}";
</script>
	<script language="javascript">
	var contextpath = "${pageContext.request.contextPath}";

	function getData(selectedFields,totalColumns){		
		getBOMData(selectedFields,totalColumns);
		}
	function getBOMData(selectedFields,totalColumns){
		
		//var objPage = document.getElementById('selPageNumber');
		
			pageno = 1;
		
		setVal('pageno',pageno);	
		setVal('sel_columns',selectedFields);
		setVal('tot_columns',totalColumns);
		//setVal('divWidth',iDivWidth);
		setVal('request_type','Normal');
		setVal('servlet_name','BOMServlet');
		setVal('callbackmethod_name','doDisplayBOMAfterColumnOrganized');
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
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
<!-- <style>
.bootstrap-select{
width:150px !important; 
} 

</style> -->
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form action="" id="tpcslogin" method="post" role="form">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>
	<div class="page-wrapper">
				<div class="page-inner">
       	<div class="row header-row">
						<div class="col-sm-12 col-xs-12">
							<div class='col-sm-8'>
								<h3 >BOM</h3>
							</div>
						</div>
					</div>
         <div class="row" > 
                <c:forEach items="${fixedfields}" var="objFixedfields" >
				<c:if test="${objFixedfields.pageFieldName=='customer_id'}">
				    <div class='col-sm-3'> 
				    <div class='col-sm-12'>   
                <div class='form-group'>
				<label for="customer">${objFixedfields.name}</label>
				<input type="text" class="form-control"  name="bom_customer" id="bom_customer"  maxlength="100" placeholder="${objFixedfields.name} Search..." value="<c:out value="${bom_customer}"/>" onkeyup="clearId(event,'bom_customer','bom_customer_id')" onkeypress="enterKeyPress(event,'search');"  onblur="searchBOM()" />
				<input type="hidden" class="form-control"  name="bom_customer_id" id="bom_customer_id" value="${bom_customer_id}"/>
						          </div>
            </div>
            </div>
				</c:if>
		
				</c:forEach>
            
            
     		<div class='col-sm-2'>
     		<div class='col-sm-12'>
										<div class='form-group'>
										<label for="quote_type"  style="display:block;">Status</label> 
										     <select  class="form-control selectpicker" data-live-search="false"   id="bom_type" name="bom_type" onchange="searchBOM()"  style="width:200px;">
										     <option value="-1" >All</option>
											<option  value="Active" <c:if test="${bom_type eq 'Active' }">selected="selected"</c:if>>Active</option>
											<option value="Closed" <c:if test="${bom_type eq 'Inactive' }">selected="selected"</c:if>>Inactive</option>
											
									
										</select>
									</div>
									</div>
								</div>
								
								<c:if test="${bom_rights.addPermission==1 }">
							<div class='col-sm-3 pull-right '>
								<div class='col-sm-12 pull-right '>
									<div class='form-group pull-right'>
										<a id="bo_form_add_new" name="bo_form_add_new" onclick="addBOM('n')" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i>&nbsp;New BOM</a>
									</div>
								</div>
							</div>

						</c:if>
		</div>		
         
         
	
	
	<div class="row paginationstyle">		
			<div class='col-sm-12'> 

						<ul class="pagination pull-left margin-right">
							<li><a href="javascript:showBOM()"
								data-toggle="tooltip" title="Refresh">&nbsp;<i class="fa fa-refresh"></i>&nbsp;</a></li>
							<li><span data-toggle="modal" data-target="#orgModal" onclick="organizeColumns(${subdocument_id});">
							<a href="#" data-toggle="tooltip" onclick="organizeColumns(${subdocument_id});"
								title="Reorder columns">&nbsp;<i class="fa fa-reorder"></i>&nbsp;
							</a></span></li>
							<li >
						<a  href="#"  onclick="showConfigScreensForId('14')" data-toggle="tooltip" title="Customization">
						&nbsp;<i class="glyphicon glyphicon-cog"></i>&nbsp;
						</a> 
					</li>
						</ul>
				<div class="input-group-btn pull-left padding-right">
                        	<div class="btn-group dropdown dropdown-lg">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="glyphicon glyphicon-filter text-primary" ></span>&nbsp;Filter&nbsp;<span class="caret"></span></button>
                            <div id="filter-dropdown" class="dropdown-menu dropdown-menu-left"  >
                                <div class="row">
                                  <c:forEach items="${fixedfields}" var="objFixedfields" >
                                  
                                  <c:if test="${objFixedfields.pageFieldName=='bom_date'}">
							
										<div class='col-sm-6'>
											<div class='form-group'>
												<label >From ${objFixedfields.name}</label> 
												<div class='input-group date' > 
												<input  type="text" class="form-control" name="bom_from_date"
													id="bom_from_date" placeholder="Select From ${objFixedfields.name}" value="${bom_from_date}"/> 
													<span class="input-group-addon">
																<span class="glyphicon glyphicon-calendar"></span>
															    </span>
												                </div>
													<script type="text/javascript">
			
																							      jQuery('#bom_from_date').datepicker({
																										    format: "dd-mm-yyyy"
																										}).on('show', function(e) {
																									        var $popup = $('.datepicker');
																									        $popup.click(function () { return false; });
																									    });
																						
																					        			</script>
											</div>
										</div>


										<div class='col-sm-6'>
											<div class='form-group'>
												<label >To ${objFixedfields.name}</label>
												<div class='input-group date' > 
												<input
													type="text" class="form-control" name="bom_to_date" placeholder="Select To ${objFixedfields.name}"
													id="bom_to_date" value="${bom_to_date}" /> 
													<span class="input-group-addon">
																<span class="glyphicon glyphicon-calendar"></span>
															    </span>
												                </div>
													<script type="text/javascript">
			
																							      jQuery('#bom_to_date').datepicker({
																										    format: "dd-mm-yyyy"
																										}).on('show', function(e) {
																									        var $popup = $('.datepicker');
																									        $popup.click(function () { return false; });
																									    });
																						
																					        			</script>
											</div>
										</div>
										</c:if>
                                  
				<c:if test="${objFixedfields.pageFieldName=='group_id'}">
				  <div class='col-sm-6'>    
                <div class='form-group'>
					<label  for="group">${objFixedfields.name}</label> 
					<input type="text" class="form-control" name="group" id="group" value="<c:out value="${group}"/>" onkeyup="clearId(event,'group','group_id')" placeholder="Select ${objFixedfields.name}"/>
					<input type="hidden" class="form-control"  name="group_id" id="group_id" value="${group_id }" />
					</div>
					</div>
				</c:if>
		
				<c:if test="${objFixedfields.pageFieldName=='item_id'}">
				  <div class='col-sm-6'>    
                <div class='form-group'>
					<label for="material">${objFixedfields.name}</label> 
					<input type="text" class="form-control" name="material" id="material" value="<c:out value="${material }"/>" onkeyup="clearId(event,'material','material_id')" placeholder="Select ${objFixedfields.name}"/>
					<input type="hidden" class="form-control" name="material_id" id="material_id" value="${material_id }" />
				</div>
				</div>
				</c:if>
					<c:if test="${objFixedfields.pageFieldName=='product_id'}">
				  <div class='col-sm-6'>    
                <div class='form-group'>
					<label for="material">${objFixedfields.name}</label> 
					<input type="text" class="form-control" name="product" id="product" value="<c:out value="${product }"/>" onkeyup="clearId(event,'product','product_id')" placeholder="Select ${objFixedfields.name}"/>
					<input type="hidden" class="form-control" name="product_id" id="product_id" value="${product_id }" />
				</div>
				</div>
				</c:if>
				
				<c:if test="${objFixedfields.pageFieldName=='operation_id'}">
				  <div class='col-sm-6'>    
                <div class='form-group'>
					<label for="material">${objFixedfields.name}</label> 
					<input type="text" class="form-control" name="operation" id="operation" value="<c:out value="${material }"/>" onkeyup="clearId(event,'operation','operation_id')" placeholder="Select ${objFixedfields.name}"/>
					<input type="hidden" class="form-control" name="operation_id" id="operation_id" value="${operation_id }" />
				</div>
				</div>
				</c:if>
				
				
				  <c:if test="${objFixedfields.pageFieldName=='bom_no'}">
				  <div class='col-sm-6'>    
                <div class='form-group'>
				<label for="enq_no">${objFixedfields.name}</label> 
				<input type="text" class="form-control"  name="bom_no" id="bom_no" value="${bom_no}" placeholder="Enter ${objFixedfields.name}"/>
				</div>
				</div>
				</c:if>
				
				
				
				</c:forEach>

			


				
				<c:if test="${dynamicfields.size() > 0}">

  				<div class='col-sm-6'>
                <div class='form-group'>
		                        <select name="dynamic_field_1" id="dynamic_field_1" class="form-control">	
		                        <option value="-1"><--Select Dynamic Field--></option>	                       
		                        		<c:forEach var="dynamicfieldObj" items="${dynamicfields}">		                        						                        					
				                        					<option value="${dynamicfieldObj.id}"><c:out value="${dynamicfieldObj.name}"/></option>
				                        				   
				                       	                   					  				
		                        			</c:forEach>	
		                        </select>
		                        <input type="text" class="form-control" name="dynamic_field_1_val" id="dynamic_field_1_val"/>
                </div>
            </div>
            <div class='col-sm-6'>
                <div class='form-group'>
		                        <select name="dynamic_field_2" id="dynamic_field_2" class="form-control">	
		                        <option value="-1"><--Select Dynamic Field--></option>	                       
		                        		<c:forEach var="dynamicfieldObj" items="${dynamicfields}">		                        						                        					
				                        					<option value="${dynamicfieldObj.id}"><c:out value="${dynamicfieldObj.name}"/></option>
				                        				   
				                       	                   					  				
		                        			</c:forEach>	
		                        </select>
		                        <input type="text" class="form-control" name="dynamic_field_2_val" id="dynamic_field_2_val"/>
                </div>
            </div>
            <div class='col-sm-6'>
                <div class='form-group'>
		                     <select name="dynamic_field_3" id="dynamic_field_3" class="form-control">	
		                        <option value="-1"><--Select Dynamic Field--></option>	                       
		                        		<c:forEach var="dynamicfieldObj" items="${dynamicfields}">		                        						                        					
				                        					<option value="${dynamicfieldObj.id}"><c:out value="${dynamicfieldObj.name}"/></option>
				                        				   
				                       	                   					  				
		                        			</c:forEach>	
		                        </select>
		                        <input type="text" class="form-control" name="dynamic_field_3_val" id="dynamic_field_3_val"/>
                </div>
            </div>
            
                </c:if>
                      
            </div>
            <div class="row">
                                  <button type="button"  class="btn btn-primary pull-left" onclick="clearBOMSearch();"><span class="glyphicon glyphicon-remove-circle" ></span>&nbsp;Reset </button>
                                  <button type="button"  id="search" class="btn btn-primary pull-right" onclick="searchBOM()"><span class="glyphicon glyphicon-search" ></span>&nbsp;Apply</button>
                                  </div>
                            </div>
                        </div>
                    </div>
                    
                    
                  <div class="btn-group pull-right margin-left">
  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   <span class="glyphicon glyphicon-tasks text-primary" ></span>&nbsp; Bulk Action&nbsp;<span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
    <li><a href="javascript:activeBulkMaterialMaster('${pc.pageNo}',1)">Active</a></li>
    <li><a href="javascript:activeBulkMaterialMaster('${pc.pageNo}',2)">Inactive</a></li>
     <li><a href="javascript:DeleteBulkMaterialMaster('${pc.pageNo}')">Delete</a></li>
  </ul>
</div>
                    
						<ul class="pagination pagination-right" id="paging"  ></ul>
						<script>
				
				 var options = {
						  bootstrapMajorVersion:3,
				            currentPage: '${pc.pageNo}',
				            totalPages: '${pc.pageCount}',
				            size:'normal',
				            alignment:'right',
				            onPageClicked: function(e,originalEvent,type,page){
				            	changeBOMPageNumbers(page);
				            }
				        }

				        $('#paging').bootstrapPaginator(options);
				
				</script>
						<!-- </div> -->
					</div>
					</div>
		
		
		
			<div class="row ">
						<div class="col-sm-12">
							<div class="col-sm-12 ">
								<div class="table-responsive" id="fill_grid" >
									${bom_grid}
								</div>
							</div>
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
		<input type="hidden" name="pageno" id="pageno"  />
		<input type="hidden" name="total_pages" id="total_pages" value="${pc.pageCount}" />
			
  <input type="hidden" name="head_mode" id="head_mode" />
  <input type="hidden" name="view_mode" id="view_mode" />
  <input type="hidden" name="bom_id" id="bom_id" />
   <input type="hidden" name="bom_no" id="bom_no" />
     <input type="hidden" name="lock_status" id="lock_status" />
 <input type="hidden" name="material_id" id="material_id" /> 
<input type="hidden" name="sel_columns" id="sel_columns" value="${selectedFields}"/>
<input type="hidden" name="tot_columns" id="tot_columns" value="${totalColumns}"/> 
<input type="hidden" name="screenId" id="screenId"  />
   <input type="hidden" name="seletedScreenId" id="seletedScreenId"  />
     <input type="hidden" name="invoke_servlet" id="invoke_servlet" value="${invoke_servlet}" /> 
<input type="hidden" name="invoke_method" id="invoke_method" value="${invoke_method}" /> 
<input type="hidden" name="active_mode" id="active_mode"  />

<script>

$('#task-table input:checkbox').click(function () {
	    $(this).closest('tr').removeClass('highlight-row');
	    if ($(this).is(':checked')) $(this).closest('tr').addClass('highlight-row');
	}) 


$('#toggle_check_all').click(function(e){
    var table= $(e.target).closest('table');
    $('td input:checkbox',table).prop('checked',this.checked);
}); 

	 jQuery(document).ready(function(){
			jQuery('[data-toggle="tooltip"]').tooltip();  
		});
	 
	
 </script>
  <script>
	 !function ($) {
	 	 
	 	$(function(){
	 		
	 		
	 		  $('#bom_customer').listCustomer({
	 			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCustomer&create_new=0",
	 			  nameField :'#bom_customer',
	 			  idField:'#bom_customer_id'
	 		  });
	 		  
	 		  $('#product').listMaterial({
	 			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetFGMaterial&create_new=0",
	 			  nameField :'#product',
	 			  idField:'#product_id'
	 		  });
	 	
	 		 
	 	
	 		  $('#material').listMaterial({
	 			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterial&create_new=0",
	 			  nameField :'#material',
	 			  idField:'#material_id'
	 		  });
	 		  
	 		  $('#group').listGroup({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroup&create_new=0",
				  nameField :'#group',
				  idField:'#group_id'
			  });
			  
	 		  
	 		  $('#operation').listOperation({
	 			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetOperation&create_new=0",
	 			  nameField :'#operation',
	 			  idField:'#operation_id'
	 		 });
	 		  
	 		
	 		  
	 		  
	 		
	 		  
	 	 });
	 }(window.jQuery);
	 	 

	 </script>	
 <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script> 
	</form>
</body>
</html>
