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

<jsp:include page="../common/Header.jsp" />
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/masters/masters.js"></script>

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

	<div class="row header-row">
						<div class="col-sm-12 col-xs-12">
							<div class='col-sm-8'>
								<h3 >Approved Price List</h3>
							</div>
						</div>
					</div>


					<div class="row">
						<div class='col-sm-3'>
							<div class='col-sm-12'>
								<div class='form-group'>
									<label for="quote_type" style="display: block;">Supplier</label>
									<p>
										<input type="text" class="form-control" name="Supplier_name"
											id="Supplier_name" placeholder=" Supplier Search..."
											value="${Supplier_name}" onblur="searchApprovedPriceList()"
											onkeypress="enterKeyPress(event,'search');" /> <input
											type="hidden" name="supplier_id" id="supplier_id"
											value="${supplier_id}" />
									</p>
								</div>
							</div>
						</div>
						<c:if test="${se_rights.addPermission==1 }">


						<div class='col-sm-3 pull-right '>
								<div class='col-sm-12 pull-right '>
									<div class='form-group pull-right'>
										<a id="bo_form_add_new" name="bo_form_add_new" onclick="addApprovedPriceList('n')"  class="btn btn-success"><i class="glyphicon glyphicon-plus"></i>&nbsp;New Approved Price List</a>
									</div>
								</div>
							</div>
					
					</c:if>


					</div>


					<div class="row paginationstyle">		
						<div class='col-sm-12'>

							<ul class="pagination pull-left margin-right">
					<!-- <li>
					<a href="javascript:addSalesOrder('n');" data-toggle="tooltip" title="Add Sales Order">
						<i class="fa fa-plus-circle"></i>
						</a>
					</li> -->
					<li>
							<a href='javascript:showApprovedPriceList();' data-toggle="tooltip" title="Refresh">
						&nbsp;<i class="fa fa-refresh"></i>&nbsp;
						</a> 
					</li>
			        <li><span data-toggle="modal" data-target="#orgModal" >
						<a  href="#"  data-toggle="tooltip" onclick="organizeColumns(${subdocument_id});" title="Reorder columns">
						&nbsp;<i class="fa fa-reorder"></i>&nbsp;
						</a> 
						</span>
					</li>
					<li >
						<a  href="#"  onclick="showConfigScreensForId('2')" data-toggle="tooltip" title="Customization">
						&nbsp;<i class="glyphicon glyphicon-cog"></i>&nbsp;
						</a> 
					</li>
				</ul>
			<div class="input-group-btn pull-left padding-right">
			     <div class="btn-group dropdown dropdown-lg">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="glyphicon glyphicon-filter text-primary" ></span>&nbsp;Filter&nbsp;<span class="caret"></span></button>
                            <div id="filter-dropdown" class="dropdown-menu dropdown-menu-left"  >
                                <div class="row">
				
							<div class='col-sm-6'>    
			                <div class='form-group'>
								<label for="material">Product</label> 
								<input type="text" class="form-control" name="material" id="material" value="${material}"  placeholder="Product"/>
								<input type="hidden" class="form-control" name="material_id" id="material_id" value="${material_id }" />
							</div>
							</div>
				
                     		</div>
            <div class="row">
                                  <button type="button"  class="btn btn-primary pull-left" onclick="clearApprovedPriceList();"><span class="glyphicon glyphicon-remove-circle" ></span>&nbsp;Reset </button>
                                  <button id="search" type="button"  class="btn btn-primary pull-right" onclick="searchApprovedPriceList()"><span class="glyphicon glyphicon-search" ></span>&nbsp;Apply</button>
                                  </div>
                            </div>
                        </div>
                    </div>
				<!-- Single button -->
<div class="btn-group pull-right margin-left" >
  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   <span class="glyphicon glyphicon-tasks text-primary" ></span>&nbsp; Bulk Action&nbsp;<span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
   <li><a href="javascript:dodeleteBulkApprovedPriceList()">Delete Transactions</a></li>
  </ul>
</div>
 <ul class="pagination pagination-right"  id="paging">
								</ul>
				<script>
				
				 var options = {
						  bootstrapMajorVersion:3,
				            currentPage: '${pc.pageNo}',
				            totalPages: '${pc.pageCount}',
				            size:'normal',
				            alignment:'right',
				            onPageClicked: function(e,originalEvent,type,page){
				            	changeApprovedPriceListPageNO(page);
				            }
				        }

				        $('#paging').bootstrapPaginator(options);
				
				</script>
			</div>
		</div>
		
		
				<div class="row ">
						<div class="col-sm-12">
							<div class="col-sm-12 ">
								<div class="table-responsive" id="fill_grid" >
									${ApprovedPriceList_grid}
								</div>
							</div>
						</div>
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



     <input type="hidden" name="view_mode" id="view_mode" />
      <input type="hidden" name="mode" id="mode" />
      <input type="hidden" name="purchase_price_id" id="purchase_price_id" />
      
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
			
		 $('#Supplier_name').listSupplier({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSupplier",
			  nameField :'#Supplier_name',
			  idField:'#supplier_id'
		  });
		  
		  $('#material').listMaterial({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterial",
			  nameField :'#material',
			  idField:'#material_id'
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
               <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script> 
      </body>
      </html>
      