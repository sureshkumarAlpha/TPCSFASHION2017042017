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
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/masters/masters.js"></script>

<script language="javascript">
var localizedStrings = {
	    	SURE_TO_DELETE_GROUP: "<fmt:message bundle="${bundle}" key="Operation.AreYouSureYouToDelete"/>",
	    	SURE_TO_CHANGE_BULKACTION : "<fmt:message bundle="${bundle}" key="Operation.AreYouSureYouToChangeBulkAction"/>",
	    	SURE_TO_DELETE_OPERATION: "<fmt:message bundle="${bundle}" key="Operation.AreYouSureYouToDeleteBulkAction"/>"
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

.small{
width: 144px;
}

</style>

</head>
<jsp:include page="../common/ValidateUser.jsp"/>

<body >
<form action=""  id="tpcslogin" method="post"   role="form" autocomplete="off">
<div id="wrapper">

 <jsp:include page="../common/MainMenu.jsp"> 
<jsp:param value="2" name="screen_type"/>
<jsp:param value="Admin.Products.OperationOrStage" name="screen_name"/>
<jsp:param value="2" name="sub_document_type"/>
		</jsp:include>
<div id="page-wrapper">
<div id="page-inner">
         <div class="row">
                <div class="col-md-11 col-sm-12 col-xs-8">
                  <h2 class="page-header">Operation/Stage</h2>
                </div>	
         </div>
         <div class="row row-no-margin" > 
      
				    <div class='col-sm-3'>    
                <div class='form-group'>
				<label for="so_customer">Operation</label>
				<input type="text" class="form-control"  name="operation" id="operation" placeholder="Operation Search..." value="${operation}" onblur=""  onkeyup="" onkeypress="enterKeyPress(event,'search');"/>
				<input  id="operation_id" name="operation_id" size="30" type="hidden" value="${operation_id}"/>
				</div>
            </div>
      
          
									
			<div class='col-sm-2 pull-right new_tran_btn'>
			<div class='form-group pull-right'>
			<a onclick="addOperationOrStage('n')"  class="btn btn-success"  ><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;New Operation/Stage</a>
			</div>
			</div>	
			
							
								
       </div>	
  
  
  <div class="row row-no-margin paginationstyle">		
			<div class='col-sm-12'>    

				<ul class="pagination pull-left"  >
					<li>
							<a href='javascript:showOperationOrStage();' data-toggle="tooltip" title="Refresh">
						<i class="fa fa-refresh"></i>
						</a> 
					</li>
			       <%--  <li><span data-toggle="modal" data-target="#orgModal" >
						<a  href="#"  data-toggle="tooltip" onclick="organizeColumns(${subdocument_id});" title="Reorder columns">
						<i class="fa fa-reorder"></i>
						</a> 
						</span>
					</li> --%>
					<!-- <li >
						<a  href="#"  onclick="showConfigScreensForId('4')" data-toggle="tooltip" title="Customization">
						<i class="glyphicon glyphicon-cog"></i>
						</a> 
					</li> -->
				</ul>
				<div class="input-group-btn pull-left">
                    <div class="btn-group">
                        <div class="dropdown dropdown-lg">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="glyphicon glyphicon-filter text-primary" ></span>&nbsp;Filter&nbsp;<span class="caret"></span></button>
                            <div id="filter-dropdown" class="dropdown-menu dropdown-menu-left"  >
                                <div class="row">
										<div class='col-sm-6'>    
                <div class='form-group'>
					<label  for="group">Seq.No</label> 
					<input type="text" class="form-control" name="seq_no" id="seq_no" value="${seq_no}"  placeholder="Seq No"/>
					</div>
					</div>
												
				
                        </div>
            <div class="row">
                                  <button type="button"  class="btn btn-primary pull-left" onclick="clearOperationSearch();"><span class="glyphicon glyphicon-remove-circle" ></span>&nbsp;Reset </button>
                                  <button id="search" type="button"  class="btn btn-primary pull-right" onclick="searchOperationMaster()"><span class="glyphicon glyphicon-search" ></span>&nbsp;Apply</button>
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
    <li><a href='javascript:activeBulkOperation(${pc.pageNo},1)'>Active</a></li>
    <li><a href='javascript:activeBulkOperation(${pc.pageNo},2)'>Inactive</a></li>
    <li role="separator" class="divider"></li>		
	<li><a href='javascript:deleteBulkOperation(${pc.pageNo})'>Delete</a></li>
    
     
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
				            	changeOperationOrStagePageNumber(page);
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
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Operation</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Sequence </th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Description</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Display Order</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Inspection Required</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Production</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Main Group</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Sub Group</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Status</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">
<div class="checkbox"><input class="checkbox_1" type="checkbox" id="toggle_check_all" name="toggle_check_all">
		 <label for="toggle_check_all" class="checkbox_1"></label>
		 </div></th>
</tr>
</thead>
<tbody>
<c:set var="k" value="${1 }"/>
<c:forEach var="obj" items="${operation_list}">
	<tr class="datarow">
	<td valign="middle"  nowrap="nowrap" align="left">
	<select class="form-control" id="select_action" name="select_action" onchange="OperationActionList(this.value,'${obj.operationId}','${pc.pageNo}')">
	<option selected="selected" value="-1">Action</option>
		<c:if test="${operation_rights.editPermission==1 }">	
		<option value="2">Edit</option>
		</c:if>
		<c:if test="${operation_rights.deletePermission==1 }">	
		<option value="1">Delete</option>
		</c:if>
		<c:if test="${operation_rights.editPermission==1 }">	
		<option value="3">Inactive</option>
		</c:if>
	
	</select>

	</td>
	<td >${obj.operationName}</td>
	<td >${obj.operationSeqNo}</td>
	<td >${obj.operationDesc}</td>
	 <c:choose>
	<c:when test="${obj.displayOrder eq 1}">
		<td nowrap="nowrap" class="dataalignment"><c:out value="Yes"/></td>
	</c:when>
	<c:otherwise>
		<td nowrap="nowrap" class="dataalignment"><c:out value="No"/></td>
	</c:otherwise>         
</c:choose>
	<%-- <td >${obj.displayOrder}</td> --%>
	 <c:choose>
	<c:when test="${obj.inspectionRequired eq 1}">
		<td nowrap="nowrap" class="dataalignment"><c:out value="Yes"/></td>
	</c:when>
	<c:otherwise>
		<td nowrap="nowrap" class="dataalignment"><c:out value="No"/></td>
	</c:otherwise>         
</c:choose>
	<%-- <td >${obj.inspectionRequired}</td> --%>
	 <c:choose>
	<c:when test="${obj.production eq 1}">
		<td nowrap="nowrap" class="dataalignment"><c:out value="Yes"/></td>
	</c:when>
	<c:otherwise>
		<td nowrap="nowrap" class="dataalignment"><c:out value="No"/></td>
	</c:otherwise>         
</c:choose>
	<%-- <td >${obj.production}</td> --%>
	<td >${obj.mainGroup}</td>
	<td >${obj.subGroup}</td>
	<%-- <td >${obj.operationStatus}</td> --%>
	 <c:choose>
	<c:when test="${obj.operationStatus eq 1}">
		<td nowrap="nowrap" class="dataalignment"><c:out value="Active"/></td>
	</c:when>
	<c:otherwise>
		<td nowrap="nowrap" class="dataalignment"><c:out value="Inactive"/></td>
	</c:otherwise>         
</c:choose>
	<td><div class="checkbox" > <input class="checkbox_1" type="checkbox" id="mat_check_${obj.operationId}" name="bulkoperatonId" value="${obj.operationId}" /><label for="mat_check_${obj.operationId}" class="checkbox_1"></label></div></td>
	
	
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
            <td>&nbsp;</td>
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
			
		  $('#operation').listOperation({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetOperation",
			  nameField :'#operation',
			  idField:'#operation_id'
		  });
		  
	 });
}(window.jQuery);

	 
</script>

			
      </form>
      <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>
      </body>
      </html>
      