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
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/purchase/indent.js"></script>
<jsp:include page="../common/Header.jsp" />

<style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}

.bootstrap-select{
width:150px !important; 
} 

tr.header{
    text-transform: capitalize;
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
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form action="" id="tpcslogin" method="post" role="form" autocomplete="off">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>
<div id="page-wrapper">
<div id="page-inner">

		<div class="row header-row">
						<div class="col-sm-12 col-xs-12">
							    <jsp:include page="../common/PageHead.jsp">
				<jsp:param value="${subdocument_id}" name="screen_name" />
				</jsp:include>
						</div>
					</div>
					
            
					<div class="row">
						<div class='col-sm-3'>
						<div class='col-sm-12'>
							<div class='form-group'>
								<label for="indent_name">Supplier Name</label> 
								<input type="text" class="form-control" name="supplier_name" id="supplier_name" placeholder="Supplier Search..." value="${supplier_name}"  onblur="searchSO()"  onkeyup="clearId(event,'supplier_name','supplier_id')" onkeypress="enterKeyPress(event,'search'); "/>
								<input type="hidden" name="supplier_id" id="supplier_id" value="${supplier_id}" />
								</div>
							</div>
						</div>

        </div>    
       
<div class="row paginationstyle">		
			<div class='col-sm-12'>   		
				
				<ul class="pagination pull-left margin-right">
					<li>
						<a href='javascript:showIndentApprovalRegister(${subdocument_id})' data-toggle="tooltip" title="Refresh">
						&nbsp;<i class="fa fa-refresh"></i>&nbsp;
						</a> 
					</li>
					<li>
					<a href="javascript:customizeReport(${subdocument_id})" data-toggle="tooltip" title="Customize">
          				&nbsp;<i class="glyphicon glyphicon-cog "></i>&nbsp;
        			</a>
					</li>
					<c:if test="${ser_rights ne null && ser_rights.printPermission eq 1}">
					<li>
						<a href='javascript:printSalesEnquiryRegister(${subdocument_id})' data-toggle="tooltip" title="Print">
						&nbsp;<i class="glyphicon glyphicon-print"></i>&nbsp;
						</a> 
					</li>
					</c:if>
					<c:if test="${ser_rights ne null && ser_rights.excelPermission eq 1}">
					<li>
						<a href='javascript:exportSalesEnquiryRegisterToExcel(${subdocument_id})' data-toggle="tooltip" title="Export to excel">
						&nbsp;<i class="fa fa-file-excel-o"></i>&nbsp;
						</a> 
					</li>
					</c:if>
					<c:if test="${ser_rights ne null && ser_rights.printPermission eq 1}">
						<li>
						<a href='javascript:exportSalesEnquiryRegisterToPDF(${subdocument_id})' data-toggle="tooltip" title="Export to PDF">
						&nbsp;<i class="fa fa-file-pdf-o"></i>&nbsp;
						</a> 
					</li>
					</c:if>
				</ul>
				
				<div class="input-group-btn pull-left padding-right">
							
								<div class="btn-group dropdown dropdown-lg">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="glyphicon glyphicon-filter text-primary" ></span>&nbsp;Filter&nbsp;<span class="caret"></span></button>
                            <div id="filter-dropdown" class="dropdown-menu dropdown-menu-left"  >
                                <div class="row">
                                
                                <c:forEach items="${fixedfields}" var="objFixedfields" >
                                 <%--  <c:if test="${objFixedfields.pageFieldName=='enq_no'}">
                
                <div class='col-sm-6'>    
                <div class='form-group'>
				<label for="se_prefix">Prefix</label> 
				<input type="text" class="form-control"  name="se_prefix" id="se_prefix" value="<c:out value="${se_prefix}"/>" placeholder="Enter Prefix"/>
				</div>
				</div>
                                  
				 <div class='col-sm-6'>    
                <div class='form-group'>
				<label for="enq_no">${objFixedfields.name}</label> 
				<input type="text" class="form-control"  name="enq_no" id="enq_no" value="<c:out value="${enq_no}"/>" placeholder="Enter ${objFixedfields.name}"/>
				</div>
				</div>
				</c:if> --%>
				<c:if test="${objFixedfields.pageFieldName=='variant_id'}">
				    <div class='col-sm-6'>    
                <div class='form-group'>
				<label for="se_group">${objFixedfields.name}</label>
				<input type="text" class="form-control"  name="variant_name" id="variant_name" placeholder="Type and select ${objFixedfields.name}" value="<c:out value="${variant_name}"/>"  />
				<input type="hidden" class="form-control"  name="variant_id" id="variant_id" value="${variant_id}"/>
						          </div>
            </div>
				</c:if>
				
				<c:if test="${objFixedfields.pageFieldName=='uom'}">
				    <div class='col-sm-6'>    
                <div class='form-group'>
				<label for="se_customer">${objFixedfields.name}</label>
				<input type="text" class="form-control"  name="uom" id="uom" placeholder="Enter ${objFixedfields.name}" value="<c:out value="${uom}"/>"  />
						          </div>
            	</div>
				</c:if>
		
				</c:forEach>
                                </div>
            					<div class="row">
                                  <button type="button"  class="btn btn-primary pull-left" onclick="clearIndentApprovalRegister();"><span class="glyphicon glyphicon-remove-circle" ></span>&nbsp;Reset </button>
                                  <button type="button"  id="search" class="btn btn-primary pull-right" onclick="searchIndentApprovalRegister()"><span class="glyphicon glyphicon-search" ></span>&nbsp;Apply</button>
                                  </div>
                            </div>
                        </div>
                </div>
                
               <ul class="pagination pagination-right margin-left"  id="paging"  ></ul>
						<script>
				
				 var options = {
						  bootstrapMajorVersion:3,
				            currentPage: '${pc.pageNo}',
				            totalPages: '${pc.pageCount}',
				            size:'normal',
				            alignment:'right',
				            onPageClicked: function(e,originalEvent,type,page){
				            	changeIndentApprovalRegisterPageNumbers(page);
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
									${se_register_grid}
								</div>
							</div>
						</div>
					</div>
					
							
     </div>  
    </div>
</div>

		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> 
		<input type="hidden" name="request_type" id="request_type" /> 
		<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
		<input type="hidden" name="pageno" id="pageno" value="${pc.pageNo}" />
		<input type="hidden" name="total_pages" id="total_pages" value="${pc.pageCount}" />
		<input type="hidden" name="subdocument_id" id="subdocument_id" value="${subdocument_id}"/>
		<input type="hidden" name="invoke_servlet" id="invoke_servlet" value="${invoke_servlet}" /> 
		<input type="hidden" name="invoke_method" id="invoke_method" value="${invoke_method}" /> 
<script>
reportTableTextStyle('.table-condensed');
/* 
$( "#se_customer" ).keyup(function() {
	$( "#se_customer_id" ).val("");	  
	});
$( "#se_group" ).keyup(function() {
	$( "#se_group_id" ).val("");	  
	});
$( "#se_material" ).keyup(function() {
	$( "#se_material_id" ).val("");	  
	});	 */

/* !function ($) {

	 $.fn.listCustomer=function (){
			//	alert(' in listAssorterName function');
			 $.getJSON('${pageContext.request.contextPath}/json_file/${sessionScope.user_info.clientId}/customer/${sessionScope.user_info.clientId}_customer.json', function(data) {
		      		 
				 $.each(data, function(key, val) {
			
					// alert('in in jquer of auto complete');
					
					 $("#se_customer").autocomplete({
					 minLength: 0,
					 source: val,

					 select: function( event, ui ) {
					 $( "#se_customer" ).val( ui.item.label );
					 $( "#se_customer_id" ).val( ui.item.value );
					 return false;
					 }
					 });
			
				});
				 
			   }).fail(function(){$.ajax({ 
		        type: 'POST',
		        url: contextpath+"/RequestHandlerServlet",
		        data: {servlet_name : 'JsonFileCreatorServlet' , callbackmethod_name : 'createJsonFile',name:"customer",query:"select party_id,party_name from party where party_tag='customer' order by party_name"}, 
		        success: function(msg) {
		        	$('#se_customer').listCustomer();    
		         }           
		    });});
		 };

		 $.fn.listMaterial=function (){
			 $.getJSON('${pageContext.request.contextPath}/json_file/${sessionScope.user_info.clientId}/material/${sessionScope.user_info.clientId}_material.json', function(data) {
		      		 
				 $.each(data, function(key, val) {
			
					
					 $("#se_material").autocomplete({
					 minLength: 0,
					 source: val,
		
					 select: function( event, ui ) {
						 
		                   if(parseInt(ui.item.value)!=-2){
								 
								 $( "#se_material" ).val( ui.item.label );
								 $( "#se_material_id" ).val( ui.item.value );
								 
							 }
		  				 else{
							 $( "#se_material" ).val('');
							 $( "#se_material_id" ).val('');
							 createNewMaterial('popup2');
						 }
						 
						 
						 return false;
					 },
					 focus: function(event, ui) {
						 event.preventDefault();
						 if(parseInt(ui.item.value)!=-2){
				            $("#se_material").val(ui.item.label);
						 }
						 else{
							 $("#se_material").val('');
						 }
				        }
					 });
			
				});
				 
			   }).fail(function(){$.ajax({ 
		        type: 'POST',
		        url: contextpath+"/RequestHandlerServlet",
		        data: {servlet_name : 'JsonFileCreatorServlet' , callbackmethod_name : 'createJsonFile',name:"material",query:"select Material_id,Material_name from material order by Material_id",create_req:"1"}, 
		        success: function(msg) {
		        	$("#se_material").listMaterial();    
		         }           
		    });});
		 };			    
		 	
				 
		$.fn.listGroup=function (){
			 $.getJSON('${pageContext.request.contextPath}/json_file/${sessionScope.user_info.clientId}/group/${sessionScope.user_info.clientId}_group.json', function(data) {
		      		 
				 $.each(data, function(key, val) {
			
					
					 $("#se_group").autocomplete({
					 minLength: 0,
					 source: val,
		
					 select: function( event, ui ) {
					 $("#se_group").val( ui.item.label );
					 $("#se_group_id").val( ui.item.value );
					 return false;
					 }
					 });
			
				});
				 
			   }).fail(function(){$.ajax({ 
		        type: 'POST',
		        url: contextpath+"/RequestHandlerServlet",
		        data: {servlet_name : 'JsonFileCreatorServlet' , callbackmethod_name : 'createJsonFile',name:"group",query:"select group_id,group_name from groups order by group_name"}, 
		        success: function(msg) {
		        	$("#se_group").listGroup();    
		         }           
		    });});
		 };			


	 $(function(){

		  $('#se_customer').listCustomer();
		  $('#se_group').listGroup();
		  $('#se_material').listMaterial();
		});

}(window.jQuery); */
	 
	 
/* !function ($) {
	 
	$(function(){
			
		  $('#se_customer').listCustomer({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCustomer",
			  nameField :'#se_customer',
			  idField:'#se_customer_id'
			  /* formId:'#validate-form',
			  validateId:'customer', */
		/*  });
		  
		  $('#se_material').listMaterial({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterial",
			  nameField :'#se_material',
			  idField:'#se_material_id'
			 /*  formId:'#validate-form',
			  validateId:'material_0',
				  createNew:'1' */
		 /* });
		  
		  $('#se_group').listGroup({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroup",
			  nameField :'#se_group',
			  idField:'#se_group_id'
			 /*  formId:'#validate-form',
			  validateId:'material_0',
				  createNew:'1' */
	/*	  });
		  <c:if test="${is_multi_branch_app eq 'Yes' }">
		  $('#se_branch_name').listBranch({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetBranch&create_new=0",
			  nameField :'#se_branch_name',
			  idField:'#se_branch_id'
		  });
		</c:if>
		  
	 }); */
	
</script>

<script>
	 !function ($) {
	 	 
	 	$(function(){
	 		
	 		
	 		$('#supplier_name').listCustomer({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCustomer&create_new=0",
				  nameField :'#supplier_name',
				  idField:'#supplier_id'
			  });
			
			 $('#variant_name').listVariant({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetVariant&create_new=0",
				  nameField :'#variant_name',
				  idField:'#variant_id'
			  }); 
	 		
	 		  
	 		  
	 		
	 		  
	 	 });
	 }(window.jQuery);
	 	 

	 </script>	
 
	</form>
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>
</body>
</html>