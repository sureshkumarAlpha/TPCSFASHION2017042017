<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message bundle="${bundle}" key="Title.Title" /></title>
<jsp:include page="../common/Header.jsp" />
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/masters/masters.js"></script>
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
<script language="javascript">
var contextpath = "${pageContext.request.contextPath}";
</script>
<body>
<form action=""  id="validate-form" method="post" role="form">
<div class="wrapper">
    <jsp:include page="../common/MainMenu.jsp"> 
 	<jsp:param value="2" name="screen_type"/>
	<jsp:param value="Sales.Transactions.SalesOrder" name="screen_name"/>
	</jsp:include>

     <div class="row-no-margin row">
                <div class="col-md-11 col-sm-8 col-xs-12">
                    <h2 class="add-page-header" style="margin:18px 0 0px 20px;">Warehouse Master (WM)</h2>
                </div>
    </div>
<div class="page-wrapper">
<div class="page-inner"> 
	<div class="row">
				 <div class="col-sm-3">
		 		 <div class='form-group'>
                 <label >Warehouse Code</label>
		    	 <div class="required-field-block">
                 <input class="form-control" id="store_code" name="store_code" size="30" type="text" placeholder="Type and select Warehouse Code" value="${warehouse_info.storeCode}" />
		   		 <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
            	 </div>
				 </div>
				 <div class="col-sm-3">
		 		 <div class='form-group'>
                 <label >Warehouse Name</label>
		    	 <div class="required-field-block">
                 <input class="form-control" id="store_name" name="store_name" size="30" type="text" placeholder="Type and select Warehouse Name" value="${warehouse_info.storeName}" />
		   		 <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
            	 </div>
				 </div>
	</div>
	<div class="row">
	  			  <div class='col-sm-3'>
                <div class='form-group'>
                    <label>Status</label>
				<select class="form-control" id="store_status" name="store_status">
				<option value="1" <c:if test="${warehouse_info.storeStatus eq 1 }">selected="selected"</c:if>>Active</option>
				<option value="0" <c:if test="${warehouse_info.storeStatus eq 0 }">selected="selected"</c:if>>Inactive</option>
				</select>
		</div>
		</div>
				
	</div>
	
   
	<!-- <div class="row" style="margin-top:50px;">
     	<div class="col-md-1 col-xs-12" id="header">
			<label>Size</label>
			</div>
			<div class="col-md-1 col-xs-12" id="header">
					<label>7</label>
				</div>
				<div class="col-md-1  col-xs-12" id="header">
					<label>7.5</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>8</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>8.5</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>9</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>9.5</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>10</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>10.5</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>11</label>
				</div>
     </div> -->
     
		 
	<!--  <div class="row ">
	
     <div class="col-md-1 col-xs-12" style="text-align: center;">
			<label>Qty</label>
			</div>
			<div class="col-md-1 col-xs-12" >
				 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="10"/>
				</div>
				<div class="col-md-1  col-xs-12" >
				 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="15"/>
				</div>
				<div class="col-md-1 col-xs-12" >
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="20"/>
				</div>
				<div class="col-md-1 col-xs-12" >
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="25"/>
				</div>
				<div class="col-md-1 col-xs-12" >
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="25"/>
				</div>
				<div class="col-md-1 col-xs-12" >
				 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="20"/>
				</div>
				<div class="col-md-1 col-xs-12" >
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="10"/>
				</div>
				<div class="col-md-1 col-xs-12" >
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="15"/>
				</div>
				<div class="col-md-1 col-xs-12" >
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="15"/>
				</div>
	 </div> -->
        
	<!-- <div class='row' style="margin-top:50px;">
				<div class="col-sm-12">
				<h4><label class="text-primary">Followers </label></h4>
				</div>
	</div>
	<div class='row'>
				<div class="col-sm-12">
				<table class="col-sm-6 col-xs-12 " id="follower">
				<tr>
				<td align="left" valign="top" width="40%"><b>User Name</b></td>
				<td align="left" valign="top"><b>Remark</b></td>
				</tr> -->
<%-- 
					<c:set var="k" value="${1}" />
					<c:set var="iCountUsers" value="${0}" />
					<c:forEach var="styleuserObj" items="${so_user_list}">
					<c:set var="k" value="${k+1}" />
					<c:set var="iCountUsers" value="${iCountUsers+1}" />
					</c:forEach>

					<c:choose>
					<c:when test="${iCountUsers != 0}">
			  	<tr>
				<td >
				<select class="form-control" name="user_name" id="user_name" onchange="addRow('follower')" align="left">
				<option value="-1">&lt;-- select user name --&gt;</option>
					<c:forEach var="user" items="${si_user_list}">
				<option value="<c:out value="${user.userId}"/>"><c:out
						value="${user.userFullName}" /></option>
					</c:forEach>
				</select>
				</td>
				<td >
				<input class="form-control" type="text" size="25" />
				</td>
				</tr>
					</c:when>
					</c:choose>


					<c:choose>
					<c:when test="${iCountUsers == 0}">
				<tr>
				<td >
				<select class="form-control" name="user_name"id="user_name" onchange="addRow('follower')" align="left">
				<option value="-1">&lt;-- select user name --&gt;</option>
					<c:forEach var="user" items="${si_user_list}">
			    <option value="<c:out value="${user.userId}"/>"><c:out
						value="${user.userFullName}" /></option>
				    </c:forEach>
				</select>
				</td>
				<td >
				<input type="text" class="form-control" size="25" />
				</td>
				</tr>
					</c:when>
					</c:choose>
				</table>
				</div>
	</div> --%>
	<br>
	<div class="row pull-left">        	         	 
            <div class="col-sm-12 col-xs-12 centered">   
            
            	  		
            	<div class="btn-group dropdown">
                  	
                
                <button name="save" id="save" onclick="saveWarehouse('${mode}','1')" class="btn btn-success ladda-button" data-style="expand-right">
			     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			    <span class="ladda-spinner"></span><span class="ladda-spinner"></span></button>
			    
                <button class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="height:34px;"><span class="caret"></span></button>
                <ul class="dropdown-menu">
               
                	
						<li><a href="javascript:saveWarehouse('${mode}','2');">Save &amp; New</a></li>
						
							
						<li><a href="javascript:saveWarehouse('${mode}','3');">Save &amp; Close</a></li>
						
            
                </ul>
              </div>
                 <button type="button" class="btn btn-primary"  onclick="showWarehouse()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>	
                 </div>
           		 </div>
           		      
    	
    <div class='row'  style="margin-top:20px;">
 	</div>


</div>
</div>
</div>
<jsp:include page="../common/Footer.jsp"/>

   <input type="hidden" name="servlet_name" id="servlet_name" />
   <input type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
   <input type="hidden" name="pageno" id="pageno" />
   <input type="hidden" name="request_type" id="request_type" />
   <input type="submit" name="validation_btn" id="validation_btn" style="display:none;"/>
   <input type="hidden" name="save_type" id="save_type" />
   <input type="hidden" name="store_id" id="store_id" value="${warehouse_info.storeId}"/>
   <input type="hidden" name="mode" id="mode" value="${mode}"/>
   <script>
   $('#validate-form').bootstrapValidator({
				//  live: 'disabled',
				  message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisValueIsNotValid"/>,
				  feedbackIcons: {
				      valid: 'glyphicon glyphicon-ok',
				      invalid: 'glyphicon glyphicon-remove',
				      validating: 'glyphicon glyphicon-refresh'
				  },
				  fields: {
					  store_code: {
			              validators: {
			                  notEmpty: {
			                      message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                  }
			              }
			          },
			          store_name: {
			              validators: {
			                  notEmpty: {
			                      message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                  }
			              }
			          }
			          
				  }
				});
   </script>
			
</form>
</body>
</html>