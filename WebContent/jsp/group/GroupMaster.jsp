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
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/group/group_master.js"></script>
<script language="javascript">
var localizedStrings = {
	    	SURE_TO_DELETE_GROUP: "<fmt:message bundle="${bundle}" key="Group.AreYouSureYouToDelete"/>"
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
	<script language="javascript">
	var contextpath = "${pageContext.request.contextPath}";

	function getData(selectedFields,totalColumns){		
		getItemGroupData(selectedFields,totalColumns);
		}
	function getItemGroupData(selectedFields,totalColumns){
		
		//var objPage = document.getElementById('selPageNumber');
		
			pageno = 1;
		
		setVal('pageno',pageno);	
		setVal('sel_columns',selectedFields);
		setVal('tot_columns',totalColumns);
		setVal('request_type','Normal');
		setVal('servlet_name','GroupMasterServlet');
		setVal('callbackmethod_name','doDisplayMaterialAfterColumnOrganized');
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();
	}  

</script>
<style>
.bootstrap-select{
width:150px !important; 
} 

</style>
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
								<h3 >Item Groups</h3>
							</div>
						</div>
					</div>
					
         
         <div class="row" > 
				    <div class='col-sm-3'>
				    <div class='col-sm-12'>    
            	<div class='form-group'>
				<label >Item Group Name</label> 
				<input type="text" class="form-control" name="group" id="group" value="${group }" placeholder="Item Group Name Search..."  onkeypress="enterKeyPress(event,'search');" />
							<input type="hidden" name="group_id" id="group_id" value="${ group_id}"/>
			</div>
			</div>
            </div>
     		    <div class='col-sm-3'  >
     		      <div class='col-sm-12'>    
				<div class='form-group'>
				<label>Item Group Type</label>
			  
				<input class="form-control" id="group_type_name" name="group_type_name" size="30" type="text" value="<c:out value="${group_type_name}"/>"  placeholder="Select Item Group Type" onkeypress="enterKeyPress(event,'search');"/>
				<input class="form-control" id="group_type_id" name="group_type_id" size="30" type="hidden" value="<c:out value="${group_type_id}"/>"  />
                     <label id="errormessage"></label>	
					</div>
					</div>
					</div>
             
             		<div class='col-sm-2'>
             		  <div class='col-sm-12'>    
									<div class='form-group'>
										<label for="g_status"   >Status</label> 
										     <select  class="form-control selectpicker" data-live-search="false"   id="g_status" name="g_status" onchange="searchGroupMaster();" style="width:200px;">
										     <option value="-1" <c:if test="${g_status eq -1 }">selected="selected"</c:if> >All</option>
											<option  value="1" <c:if test="${g_status eq 1 }">selected="selected"</c:if> >Active</option>
											<option value="0" <c:if test="${g_status eq 0 }">selected="selected"</c:if> >Inactive</option>
											
									
										</select>
									</div>
									</div>
								</div>
								
									<c:if test="${group_rights.addPermission==1 }">	
									
									<div class='col-sm-3 pull-right '>
								<div class='col-sm-12 pull-right '>
									<div class='form-group pull-right'>
										<a id="bo_form_add_new" name="bo_form_add_new" onclick="addGroupMaster('n')" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i>&nbsp;New Item Groups</a>
									</div>
								</div>
							</div>
							
			
									</c:if>
											
								
       </div>
       
         
         
         <div class="row paginationstyle">		
			<div class='col-sm-12'> 
						<ul class="pagination pull-left margin-right">
							<li><a href='javascript:showGroupMaster()'  data-toggle="tooltip" title="Refresh">&nbsp;<i class="fa fa-refresh"></i>&nbsp;</a></li>
							<li><span data-toggle="modal" data-target="#orgModal" onclick="organizeColumns(${subdocument_id});">
							<a href="#" data-toggle="tooltip" onclick="organizeColumns(${subdocument_id});"
								title="Reorder columns">&nbsp;<i class="fa fa-reorder"></i>&nbsp;
							</a></span></li>
							<li >
						<a  href="#"  onclick="showConfigScreensForId('1')" data-toggle="tooltip" title="Customization">
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
												<label >Item Group Code</label> 
											<input class="form-control" type="text"  id="groupcode" name="groupcode" value="${groupcode}" placeholder="Enter Group Code"/>
											</div>
													</div>
													
													
													<c:if test="${dynamicfields.size() > 0}">

  				<div class='col-sm-6'>
                <div class='form-group'>
		                        <select name="dynamic_field_1" id="dynamic_field_1" class="form-control">	
		                        <option value="-1"><--Select Dynamic Field--></option>	                       
		                        		<c:forEach var="dynamicfieldObj" items="${dynamicfields}">		                        						                        					
				                        					<option value="${dynamicfieldObj.pageFieldName}" <c:if test="${dynamicfieldObj.pageFieldName eq dynamic_field_1 }"> selected </c:if> ><c:out value="${dynamicfieldObj.name}"/></option>
				                        				   
				                       	                   					  				
		                        			</c:forEach>	
		                        </select>
		                        <input type="text" class="form-control" name="dynamic_field_1_val" id="dynamic_field_1_val" value="${dynamic_field_1_val}"/>
                </div>
            </div>
            <div class='col-sm-6'>
                <div class='form-group'>
		                        <select name="dynamic_field_2" id="dynamic_field_2" class="form-control">	
		                        <option value="-1"><--Select Dynamic Field--></option>	                       
		                        		<c:forEach var="dynamicfieldObj" items="${dynamicfields}">		                        						                        					
				                        					<option value="${dynamicfieldObj.pageFieldName}" <c:if test="${dynamicfieldObj.pageFieldName eq dynamic_field_2 }"> selected </c:if>  ><c:out value="${dynamicfieldObj.name}"/></option>
				                        				   
				                       	                   					  				
		                        			</c:forEach>	
		                        </select>
		                        <input type="text" class="form-control" name="dynamic_field_2_val" id="dynamic_field_2_val" value="${dynamic_field_2_val}"/>
                </div>
            </div>
           
            
                </c:if>
</div>
            <div class="row">
                                  <button type="button"  class="btn btn-primary pull-left" onclick="clearSearchGroupMaster();"><span class="glyphicon glyphicon-remove-circle" ></span>&nbsp;Reset </button>
                                  <button type="button"  id="search" class="btn btn-primary pull-right" onclick="searchGroupMaster()"><span class="glyphicon glyphicon-search" ></span>&nbsp;Apply</button>
                                  </div>
                            </div>
                        </div>
                    </div>
                  <div class="btn-group pull-right margin-left">
  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   <span class="glyphicon glyphicon-tasks text-primary" ></span>&nbsp; Bulk Action&nbsp;<span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
   <li><a href="javascript:activeBulkGroupMaster('${pc.pageNo}',1)">Active</a></li>
    <li><a href="javascript:activeBulkGroupMaster('${pc.pageNo}',2)">Inactive</a></li>
      <li><a href="javascript:deleteBulkGroupMaster('${pc.pageNo}')">Delete</a></li>
  </ul>
</div>

						<ul class="pagination pagination-right" id="paging"
							 ></ul>
						<script>
				
				 var options = {
						  bootstrapMajorVersion:3,
				            currentPage: '${pc.pageNo}',
				            totalPages: '${pc.pageCount}',
				            size:'normal',
				            alignment:'right',
				            onPageClicked: function(e,originalEvent,type,page){
				            	changeGroupMasterPageNumber(page);
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
									${itemGroup_grid}
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
		<input type="hidden" name="total_pages" id="total_pages" value="${pc.pageCount}" />
			<input type="hidden" name="mode" id="mode" />
			<input type="hidden" name="screenId" id="screenId"  />
   <input type="hidden" name="seletedScreenId" id="seletedScreenId"  />
     <input type="hidden" name="invoke_servlet" id="invoke_servlet" value="${invoke_servlet}" /> 
<input type="hidden" name="invoke_method" id="invoke_method" value="${invoke_method}" /> 
<input type="hidden" name="active_mode" id="active_mode"  />
<input type="hidden" name="sel_columns" id="sel_columns" value="${selectedFields}"/>
<input type="hidden" name="tot_columns" id="tot_columns" value="${totalColumns}"/> 

<script type="text/javascript">

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
		  $('#group').listGroup({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroup",
			  nameField :'#group',
			  idField:'#group_id'
		  });			 
	 });   
	 $(function(){
		  $('#group_type_name').listGroupType({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroupType",
			  nameField :'#group_type_name',
			  idField:'#group_type_id',
			  
		  });			 
	 });
}(window.jQuery);

	 
</script> 
	</form>
	
	  <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>
</body>
</html>		
