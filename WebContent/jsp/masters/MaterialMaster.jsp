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
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/masters/material_master.js"></script>

<script language="javascript">
var localizedStrings = {
	    	SURE_TO_DELETE_ITEM: "<fmt:message bundle="${bundle}" key="Item.AreYouSureYouToDelete"/>",
	    	SURE_TO_CHANGE_BULKACTION : "<fmt:message bundle="${bundle}" key="Item.AreYouSureYouToChangeBulkAction"/>",
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

<script language="javascript">
	var contextpath = "${pageContext.request.contextPath}";
</script>
	<script language="javascript">
	var contextpath = "${pageContext.request.contextPath}";

	function getData(selectedFields,totalColumns){		
		getMaterialData(selectedFields,totalColumns);
		}
	function getMaterialData(selectedFields,totalColumns){
		
		//var objPage = document.getElementById('selPageNumber');
		
			pageno = 1;
		
		setVal('pageno',pageno);	
		setVal('sel_columns',selectedFields);
		setVal('tot_columns',totalColumns);
		//setVal('divWidth',iDivWidth);
		setVal('request_type','Normal');
		setVal('servlet_name','MaterialMasterServlet');
		setVal('callbackmethod_name','doDisplayMaterialAfterColumnOrganized');
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
<div id="page-wrapper">
<div id="page-inner">

	<div class="row header-row">
						<div class="col-sm-12 col-xs-12">
							<div class='col-sm-8'>
								<h3 >Item</h3>
							</div>
						</div>
					</div>
					
         
         
         
         
         <div class="row" > 
		          <c:forEach items="${fixedfields}" var="objFixedfields" > 
												 <c:if test="${objFixedfields.pageFieldName=='group_id'}">
												<div class='col-sm-3'>
													<div class='col-sm-12'>
												<div class='form-group'>
												 	<label >${objFixedfields.name}</label> 
												<!-- <label>	Group</label> -->
														  <input class="form-control" type="text" name="group" id="group" value="${group_name}"   onkeypress="enterKeyPress(event,'search');" placeholder="Type and select group" /> 
														  <input type="hidden" name="group_id" id="group_id"  value="${group_id}" />
												</div>
												</div>
												</div>	  
												 </c:if> 
									
												
												 <c:if test="${objFixedfields.pageFieldName=='item_id'}"> 
												<div class='col-sm-3'>
													<div class='col-sm-12'>
												<div class='form-group'>
													 <label >${objFixedfields.name}</label>
													<!-- <label>Item Name</label> -->
														 <input class="form-control"  type="text" class="txt" name="mat_name" id="mat_name" value="${mat_name}"  onkeypress="enterKeyPress(event,'search');" placeholder="Material Name Search..."/>
														 <input type="hidden" name="mat_id" id="mat_id" value="${mat_id}"/>
												</div>
												</div>		
												</div>  
												 </c:if>			 						
											</c:forEach> 
      
             
             		<div class='col-sm-2'>
             			<div class='col-sm-12'>
									<div class='form-group'>
										<label for="quote_type"  style="display:block;">Status</label> 
										     <select  class="form-control selectpicker" data-live-search="false"   id="material_status" name="material_status" onchange="searchMaterialMaster()"  style="width:200px;">
										     <option value="-1" >All</option>
											<option  value="Active" <c:if test="${material_status eq 'Active' }">selected="selected"</c:if>>Active</option>
											<option value="Closed" <c:if test="${material_status eq 'Closed' }">selected="selected"</c:if>>Closed</option>
											
									
										</select>
									</div>
								</div>
								</div>
								
								
								<c:if test="${material_rights.addPermission==1 }">	
								
								<div class='col-sm-3 pull-right '>
								<div class='col-sm-12 pull-right '>
									<div class='form-group pull-right'>
										<a id="bo_form_add_new" name="bo_form_add_new" onclick="addMaterial('n')" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i>&nbsp;New Item</a>
									</div>
								</div>
							</div>
								</c:if>
											
								
       </div>
       
         
        
	
	
	<div class="row paginationstyle">		
			<div class='col-sm-12'> 
						<ul class="pagination pull-left margin-right">
							<!-- <li><a href="javascript:addMaterial('n')"
								data-toggle="tooltip" title="Add Product"> <i
									class="fa fa-plus-circle"></i>
							</a></li> -->
							<li><a href="javascript:showMaterial()"
								data-toggle="tooltip" title="Refresh">&nbsp;<i
									class="fa fa-refresh"></i>&nbsp;
							</a></li>
							<li><span data-toggle="modal" data-target="#orgModal" onclick="organizeColumns(${subdocument_id});">
							<a href="#" data-toggle="tooltip" onclick="organizeColumns(${subdocument_id});"
								title="Reorder columns">&nbsp;<i class="fa fa-reorder"></i>&nbsp;
							</a></span></li>
							<li >
						<a  href="#"  onclick="showConfigScreensForId('3')" data-toggle="tooltip" title="Customization">
						&nbsp;<i class="glyphicon glyphicon-cog"></i>&nbsp;
						</a> 
					</li>
						</ul>
<div class="input-group-btn pull-left padding-right">
                  <div class="btn-group dropdown dropdown-lg">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="glyphicon glyphicon-filter text-primary" ></span>&nbsp;Filter&nbsp;<span class="caret"></span></button>
                            <div id="filter-dropdown" class="dropdown-menu dropdown-menu-left"  >
                                <div class="row">
                               <%--   <c:forEach items="${fixedfields}" var="objFixedfields" > --%>
                              <%--   <c:if test="${objFixedfields.pageFieldName=='Material_code'}"> --%>
												<div class='col-sm-6'>
												<div class='form-group'>
													<label >Item Code</label>
														 <input class="form-control" type="text" class="txt" name="mat_code" id="mat_code"  value="${mat_code}" placeholder="Material Code"/>
												</div>
												</div>		  
												
</div>
            <div class="row">
                                  <button type="button"  class="btn btn-primary pull-left" onclick="clearMaterialMaster();"><span class="glyphicon glyphicon-remove-circle" ></span>&nbsp;Reset </button>
                                  <button type="button"  id="search" class="btn btn-primary pull-right" onclick="searchMaterialMaster()"><span class="glyphicon glyphicon-search" ></span>&nbsp;Apply</button>
                                  </div>
                            </div>
                    </div>
                    </div>
                    
                    
                    <div class="btn-group pull-right margin-left">
  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   <span class="glyphicon glyphicon-tasks text-primary" ></span>&nbsp; Bulk Action&nbsp;<span class="caret"></span>
  </button>
  
  
  
  <ul class="dropdown-menu">
    <li><a href='javascript:activeBulkMaterialMaster(${pc.pageNo},1)'>Active</a></li>
    <li><a href='javascript:activeBulkMaterialMaster(${pc.pageNo},2)'>Inactive</a></li>
     <li><a href='javascript:deleteBulkMaterialMaster(${pc.pageNo})'>Delete</a></li>
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
				            	changePageNumbersinMaterialMaster(page);
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
									${mat_grid}
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
			<input type="hidden" name="pageno" id="pageno" value="${pc.pageNo}" />
		<input type="hidden" name="active_mode" id="active_mode"  />
		<input type="hidden" name="total_pages" id="total_pages"
			value="${pc.pageCount}" />
			
  <input type="hidden" name="mode" id="mode" />
  <input type="hidden" name="mat_id" id="mat_id" /> 
<input type="hidden" name="sel_columns" id="sel_columns" value="${selectedFields}"/>
<input type="hidden" name="tot_columns" id="tot_columns" value="${totalColumns}"/> 
<input type="hidden" name="screenId" id="screenId"  />
   <input type="hidden" name="seletedScreenId" id="seletedScreenId"  />
     <input type="hidden" name="invoke_servlet" id="invoke_servlet" value="${invoke_servlet}" /> 
<input type="hidden" name="invoke_method" id="invoke_method" value="${invoke_method}" /> 


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
	 


 !function ($) {

		 
		 $(function(){
			  $('#group').listGroup({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroup&create_new=0",
				  nameField :'#group',
				  idField:'#group_id'
			  });			 
		 });   
		 
		 $(function(){
			  $('#mat_name').listMaterial({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterial&create_new=0",
				  nameField :'#mat_name',
				  idField:'#mat_id'
			  });			 
		 }); 

 }(window.jQuery);
 </script>
 
 <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script> 
	</form>
</body>
</html>
