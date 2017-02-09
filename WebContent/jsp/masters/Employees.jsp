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
<%-- <title><fmt:message bundle="${bundle}" key="Title.Title" /></title> --%>

<jsp:include page="../common/Header.jsp" />

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
<body>
	<form action="" id="tpcslogin" method="post" role="form">
		<div id="wrapper">

			 <jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include> 

<div id="page-wrapper">
<div id="page-inner">

<div class="row">
                <div class="col-md-11 col-sm-8 col-xs-8">
                    <h3 class="page-header">Employee</h3>
                </div>	
                <!-- <div class="col-md-1 col-sm-4 col-xs-4 pull-right">
                        <img src="src/images/jenixerp.png" class="img-rounded" alt="Cinque Terre" width="150" height="60">						
                </div> -->
         </div>
         <div class="row row-no-margin">	
		<div class='col-sm-3'>
												<div class='form-group'>
												<label >Employee</label>			
                                <input type="text" class="form-control" placeholder="Employee Search..."/>
                                
                </div>
                </div>
                
			<div class='col-sm-2 pull-right new_tran_btn'>
			<div class='form-group pull-right'>
			<button onclick="addEmployees()"  class="btn btn-success"  ><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;New Employee</button>
			</div>
			</div>	
			</div>
			
			
                
		<div class="row paginationstyle">		
			<div class='col-sm-12'>    
				 
				
				<ul class="pagination pull-left"  >
					<!-- <li>
						<a href='javascript:addEmployees()' data-toggle="tooltip" title="Add Customer">
						<i class="fa fa-plus-circle"></i>
						</a>
					</li> -->
					<li>
						<a href='#' data-toggle="tooltip" title="Refresh">
						<i class="fa fa-refresh"></i>
						</a> 
					</li>
					<li>
						<a href='#' data-toggle="tooltip" title="Reorder columns">
						<i class="fa fa-reorder"></i>
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
													<label >Account Group</label>
														 <input class="form-control"  type="text" class="txt" name="account_group" id="account_group" value="${account_group}" placeholder="Type and select Account Group"/>
												        <input  id="account_group_id" name="account_group_id" type="hidden"  size="30" value="${account_group_id}" />
												</div>
												</div>	
												
												<div class='col-sm-6'>    
                <div class='form-group'>
                    <label for="customer_code">Employee Code</label>	
                    <input class="form-control" id="customer_code" name="customer_code" size="30" type="text" value="${cust_info.partyCode}" placeholder="Enter Code"/>
		     </div>
                </div>
                
                <div class='col-sm-6  '>    
                <div class='form-group'>
                    <label for="customer">Education</label>		   
                    <input class="form-control" id="groupName" name="groupName" size="30" type="text" placeholder="Enter Education" ng-model="data.groupName"/>	
                    <input class="hide" id="groupId" name="groupId" size="30" type="text" placeholder="Enter Education" ng-model="data.groupId"/>		  
                </div>
            </div>
            
            <div class='col-sm-6  '>    
                <div class='form-group'>
                    <label for="customer">Languages Known</label>		   
                    <input class="form-control" id="groupName" name="groupName" size="30" type="text" placeholder="Enter Longuages Known" ng-model="data.groupName"/>	
                    <input class="hide" id="groupId" name="groupId" size="30" type="text" placeholder="Enter Languages Known" ng-model="data.groupId"/>		  
                </div>
            </div>
                
            </div>

            <div class="row">
                                  <button type="button"  class="btn btn-primary pull-left" onclick="clearCustomer();"><span class="glyphicon glyphicon-remove-circle" ></span>&nbsp;Reset </button>
                                  <button type="button"  id="search" class="btn btn-primary pull-right" onclick="searchCustomer()"><span class="glyphicon glyphicon-search" ></span>&nbsp;Apply</button>
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
    <li><a href="#">Active</a></li>
    <li><a href="#">Inactive</a></li>
  </ul>
</div>
                    <ul class="pagination pull-right"  >
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
			<th>ACTION</th>			
			<th>EMPLOYEE CODE</th>
			<th>EMPLOYEE NAME</th>
			<th>SHORT NAME</th>
			<th>COMPANY NAME</th>
			<th>CONTACT PERSON</th>
		  <th><div class="checkbox"><input class="checkbox_1" type="checkbox" id="toggle_check_all" name="toggle_check_all"><label for="toggle_check_all" class="checkbox_1"></label></div></th>
					
		    </tr>
		    </thead>
		    <tbody>
		    <!-- ngRepeat: emp in employees --><tr class="datarow ng-scope" >
				<td>
				<select class="form-control">
				<option> Action</option>
				<option>Edit</option>
				<option>Delete</option>
				</select>
				</td>
				<td class="ng-binding">E001</td>
				<td class="ng-binding">customer1</td>
				<td class="ng-binding">c1</td>
				<td class="ng-binding">company1</td>
				<td class="ng-binding">contactPerson1</td>			
				<td><div class="checkbox"> <input class="checkbox_1" type="checkbox" id="se_check_1" name="se_check_1" value=""><label for="se_check_1" class="checkbox_1"></label></div></td>	
			</tr><!-- end ngRepeat: emp in employees --><tr class="datarow ng-scope" >
				<td>
					<select class="form-control">
					<option>Action</option>
				<option>Edit</option>
				<option>Delete</option>
				</select>
				</td>
				<td class="ng-binding">E002</td>
				<td class="ng-binding">customer2</td>
				<td class="ng-binding">c2</td>
				<td class="ng-binding">company2</td>
				<td class="ng-binding">contactPerson2</td>
				<td><div class="checkbox"> <input class="checkbox_1" type="checkbox" id="se_check_2" name="se_check_2" value=""><label for="se_check_2" class="checkbox_1"></label></div></td>				
			</tr><!-- end ngRepeat: emp in employees --><tr class="datarow ng-scope" >
				<td>
					<select class="form-control">
					<option>Action</option>
				<option>Edit</option>
				<option>Delete</option>
				</select>
				</td>
				<td class="ng-binding">E003</td>
				<td class="ng-binding">customer3</td>
				<td class="ng-binding">c3</td>
				<td class="ng-binding">company3</td>
				<td class="ng-binding">contactPerson3</td>				
				<td><div class="checkbox"> <input class="checkbox_1" type="checkbox" id="se_check_3" name="se_check_3" value=""><label for="se_check_3" class="checkbox_1"></label></div></td>
			</tr><!-- end ngRepeat: emp in employees -->
		    </tbody>	
	    </table>
	    </div>


 </div>  
  </div>  
  </div>
  
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

		<jsp:include page="../common/ColumnPreferences.jsp" />
		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> <input
			type="hidden" name="request_type" id="request_type" /> <input
			type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
		<input type="hidden" name="pageno" id="pageno" value="${pc.pageNo}" />
		<input type="hidden" name="total_pages" id="total_pages"
			value="${pc.pageCount}" />

	</form>
</body>
</html>
        