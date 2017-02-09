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
</head>
<%--  <jsp:include page="../common/ValidateUser.jsp" />  --%>

<body>
	<form action="" id="tpcslogin" method="post" role="form">
		<div id="wrapper">

			<%--  <jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>  --%>

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
         <div class="row">	
		 <div class="input-group custom-search-form col-sm-3">				
                                <input type="text" class="form-control" placeholder="Customer Search..."/>
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                </div>
<div class='col-sm-2 pull-right new_tran_btn'>
			<div class='form-group pull-right'>
			<button onclick="addEmployees()"  class="btn btn-success"  ><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;New Employee</button>
			</div>
			</div>	
			</div>
                
		<div class="row paginationstyle">		
			<div class='col-sm-12'>    
				 <ul class="pagination pull-right" style="padding:5px;">
					<li class="disabled"><a href="#">&laquo;</a></li>
					<li class="active"><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">&raquo;</a></li>
				</ul>
				<ul class="pagination pull-left" style="padding-top:5px;">
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
			</div>
		</div>	
		
		<div class="table-responsive">
		<table class="table table-bordered table-condensed">
		    <thead>
		    <tr class="header">
			<th>ACTION</th>			
			<th>EMPLOYEE CODE</th>
			<th>EMPLOYEE NAME</th>
			<th>SHORT NAME</th>
			<th>COMPANY NAME</th>
			<th>CONTACT PERSON</th>		
		    </tr>
		    </thead>
		    <tbody>
		    <!-- ngRepeat: emp in employees --><tr class="datarow ng-scope" >
				<td>
					&nbsp;&nbsp;
					<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-remove"></span>
        			</a>
        			&nbsp;&nbsp;
        			<a href="#" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit"></span>
        			</a>
				</td>
				<td class="ng-binding">E001</td>
				<td class="ng-binding">customer1</td>
				<td class="ng-binding">c1</td>
				<td class="ng-binding">company1</td>
				<td class="ng-binding">contactPerson1</td>				
			</tr><!-- end ngRepeat: emp in employees --><tr class="datarow ng-scope" >
				<td>
					&nbsp;&nbsp;
					<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-remove"></span>
        			</a>
        			&nbsp;&nbsp;
        			<a href="#" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit"></span>
        			</a>
				</td>
				<td class="ng-binding">E002</td>
				<td class="ng-binding">customer2</td>
				<td class="ng-binding">c2</td>
				<td class="ng-binding">company2</td>
				<td class="ng-binding">contactPerson2</td>				
			</tr><!-- end ngRepeat: emp in employees --><tr class="datarow ng-scope" >
				<td>
					&nbsp;&nbsp;
					<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-remove"></span>
        			</a>
        			&nbsp;&nbsp;
        			<a href="#" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit"></span>
        			</a>
				</td>
				<td class="ng-binding">E003</td>
				<td class="ng-binding">customer3</td>
				<td class="ng-binding">c3</td>
				<td class="ng-binding">company3</td>
				<td class="ng-binding">contactPerson3</td>				
			</tr><!-- end ngRepeat: emp in employees -->
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

	</form>
</body>
</html>
        