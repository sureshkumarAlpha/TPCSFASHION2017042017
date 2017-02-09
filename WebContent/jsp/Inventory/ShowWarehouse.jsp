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


<jsp:include page="../common/Header.jsp"/>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/masters/masters.js"></script>
<script language="javascript">
var localizedStrings = {
		SURE_TO_DELETE_WAREHOUSE: "<fmt:message bundle="${bundle}" key="Warehouse.AreYouSureToDelete"/>"
	};	
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
                  <h2 class="page-header">Warehouse Master (WM)</h2>
                </div>	
         </div>
         <div class="row row-no-margin" > 
      
				    <div class='col-sm-3'>    
                <div class='form-group'>
				<label >Warehouse Name</label>
				<input type="text" class="form-control"  name="store_name" id="store_name" placeholder="Warehouse Name Search..." value="${store_name}" onblur=""  onkeyup="" onkeypress="enterKeyPress(event,'search');"/>
			<input id="store_id" name="store_id"  size="30" type="hidden" value="${store_id}" />
			
			</div>
            </div>
				
									
			<div class='col-sm-2 pull-right new_tran_btn'>
			<div class='form-group pull-right'>
			<a onclick="addNewWarehouse()"  class="btn btn-success"  ><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;New Warehouse </a>
			</div>
			</div>	
			
								
							
								
       </div>	
  
  
  <div class="row paginationstyle">		
			<div class='col-sm-12'>    

				<ul class="pagination pull-left"  >
					<li>
							<a href='javascript:showWarehouse();' data-toggle="tooltip" title="Refresh">
						<i class="fa fa-refresh"></i>
						</a> 
					</li>
			       <%--  <li><span data-toggle="modal" data-target="#orgModal" >
						<a  href="#"  data-toggle="tooltip" onclick="organizeColumns(${subdocument_id});" title="Reorder columns">
						<i class="fa fa-reorder"></i>
						</a> 
						</span>
					</li>
					<li >
						<a  href="#"  onclick="showConfigScreensForId('4')" data-toggle="tooltip" title="Customization">
						<i class="glyphicon glyphicon-cog"></i>
						</a> 
					</li> --%>
				</ul>
				 <div class="input-group-btn pull-left">
                    <div class="btn-group">
                        <div class="dropdown dropdown-lg">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="glyphicon glyphicon-filter text-primary" ></span>&nbsp;Filter&nbsp;<span class="caret"></span></button>
                            <div id="filter-dropdown" class="dropdown-menu dropdown-menu-left"  >
                                <div class="row">
                              
								
				  
				
				  <div class='col-sm-6'>    
                <div class='form-group'>
					<label >Warehouse Code</label> 
				
	  				<input type="text" class="form-control" name="store_code" id="store_code" value="${store_code}" placeholder="Enter Warehouse Code" />
					</div>
					</div>
				
								                   
                     </div>
            <div class="row">
                                 
                                  <button type="button"  class="btn btn-primary pull-left" onclick="clearWarehouseSearch();"><span class="glyphicon glyphicon-remove-circle" ></span>&nbsp;Reset </button>
                                  <button id="search" type="button"  class="btn btn-primary pull-right" onclick="searchWarehouse()"><span class="glyphicon glyphicon-search" ></span>&nbsp;Apply</button>
                                  </div>
                            </div>
                        </div>
                    </div>
                </div> 
                
				 <!--  --><button type="button" style="display:none;" id="search" class="btn btn-primary pull-right" onclick="searchWarehouse()"><span class="glyphicon glyphicon-search" ></span>&nbsp;Apply</button><!--  -->
				
				
<div class="btn-group pull-right">
  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   <span class="glyphicon glyphicon-tasks text-primary" ></span>&nbsp; Bulk Action&nbsp;<span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
    <li><a href='javascript: activeBulkWarehouse(${pc.pageNo},1)'>Active</a></li>
    <li><a href='javascript: activeBulkWarehouse(${pc.pageNo},2)'>Inactive</a></li>
    <%-- <li><a href='javascript: activeBulkWarehouse(${pc.pageNo},3)'>Delete</a></li> --%>
     
  </ul>
</div>
 <ul class="pagination pull-right" 	id="paging">	</ul>
 <script>
				
				 var options = {
						  bootstrapMajorVersion:3,
				            currentPage: '${pc.pageNo}',
				            totalPages: '${pc.pageCount}',
				            size:'normal',
				            alignment:'right',
				            onPageClicked: function(e,originalEvent,type,page){
				            	changeWarehousePageNumber(page);
				            }
				        }

				        $('#paging').bootstrapPaginator(options);
				
				</script>
				
			</div>
		</div>
		
         
        
        
<div class="row table-responsive">
		
<table class="table table-bordered table-condensed table-hover"  id="task-table" >
<thead>
<tr class="header">
<th valign="middle" nowrap="nowrap" align="center" class="w-mini first small">Action</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Warehouse Code</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Warehouse Name</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Status</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">
<div class="checkbox"><input class="checkbox_1" type="checkbox" id="toggle_check_all" name="toggle_check_all">
		 <label for="toggle_check_all" class="checkbox_1"></label>
		 </div></th>
</tr>
</thead>
<tbody>
<c:set var="k" value="${1 }"/>
<c:forEach var="obj" items="${warehouse_list}">
	<tr class="datarow">
	<td valign="middle"  nowrap="nowrap" align="left">
	<select class="form-control" id="select_action" name="select_action" onchange="warehouseActionList(this.value,'${obj.storeId}','${pc.pageNo}')">
	<option selected="selected" value="-1">Action</option>
		<c:if test="${warehouse_rights.editPermission==1 }">	
		<option value="2">Edit</option>
		</c:if>
		<c:if test="${warehouse_rights.deletePermission==1 }">	
		<option value="1">Delete</option>
		</c:if>
		 <c:if test="${warehouse_rights.editPermission==1 }">	
		<option value="3">Inactive</option>
		</c:if> 
	
	</select>

	</td>
	
	<td >${obj.storeCode}</td>
	<td >${obj.storeName}</td>
	<%-- <td >${obj.operationStatus}</td> --%>
	 <c:choose>
	<c:when test="${obj.storeStatus eq 1}">
		<td nowrap="nowrap" class="dataalignment"><c:out value="Active"/></td>
	</c:when>
	<c:otherwise>
		<td nowrap="nowrap" class="dataalignment"><c:out value="Inactive"/></td>
	</c:otherwise>         
</c:choose>
	<td><div class="checkbox" > <input class="checkbox_1" type="checkbox" id="mat_check_${obj.storeId}" name="bulkoperatonId" value="${obj.storeId}" /><label for="mat_check_${obj.storeId}" class="checkbox_1"></label></div></td>
	
	
	</tr>
	<c:set var="k" value="${k+1}"/>
</c:forEach>

<c:if test="${k < 15}">
		<c:set var="k" value="${15-k}"/>
		<c:forEach begin="1" end="${k}" varStatus="loop">
    	<tr>      
        <td>&nbsp;</td>
         <td>&nbsp;</td>
          <td>&nbsp;</td>
           <td>&nbsp;</td>
            <td>&nbsp;</td>
            
             
    	</tr>
		</c:forEach>
		</c:if>
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
<input type="hidden" name="screenId" id="screenId"  />

<input type="hidden" name="invoke_servlet" id="invoke_servlet" value="${invoke_servlet}" /> 
<input type="hidden" name="invoke_method" id="invoke_method" value="${invoke_method}" /> 
<input type="hidden" name="no_type" id="no_type"  />
<input type="hidden" name="mode" id="mode" value="${mode}"/>
 <input type="hidden" name="active_mode" id="active_mode"  />
  
         
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
				
			  $('#store_name').listWarehouse({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetWarehouse",
				  nameField :'#store_name',
				  idField:'#store_id'
			  });
			  
		 });
	}(window.jQuery);

		 
	</script>

				
	      </form>
	      <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>
	      </body>
	      </html>
	      
      