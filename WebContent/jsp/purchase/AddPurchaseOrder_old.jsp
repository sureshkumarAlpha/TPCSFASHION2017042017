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
<form action=""  id="tpcslogin" method="post" role="form">
<div class="wrapper">
 	<jsp:include page="../common/MainMenu.jsp"> 
 	<jsp:param value="2" name="screen_type"/>
	<jsp:param value="Sales.Transactions.SalesOrder" name="screen_name"/>
	</jsp:include>

     <div class="row-no-margin row">
                <div class="col-md-11 col-sm-8 col-xs-12">
                <h2 class="add-page-header" style="margin:18px 0 0px 20px;">Purchase Order</h2>
                </div>
     </div>
<div class="page-wrapper">
<div class="page-inner">
	 <div class="row">
	 			<div class="col-sm-3">
		 		<div class='form-group'>
                    <label for="customer">PO. No.</label>
		    	<div class="required-field-block">
                    <input class="form-control" id="Po_No" name="Po_No" size="30" type="text" placeholder="Type and select PO. No."/>
		   		 <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
            	 </div>
				 </div>
				 <div class="col-sm-3">
		 		 <div class='form-group'>
                    <label for="customer">AMNo</label>
		    	 <div class="required-field-block">
                    <input class="form-control" id="amno" name="amno" size="30" type="text" placeholder="Type and select AMNo"/>
		   		 <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
            	 </div>
				 </div>
				 <div class='col-sm-3'>    
               	 <div class='form-group' >
                 <label for="quotationdate">Date</label>
		    	 <div class='input-group date' id='datepicker' >
		    	 <div class="required-field-block">
			     <input type='text' class="form-control" placeholder="Select Date" />
			     <div class="required-icon">
			 	 <div class="text">*</div>
		     	 </div>
		     	 </div>
			     <span class="input-group-addon">
				 <span class="glyphicon glyphicon-calendar"></span>
			     </span>
                 </div>		                
                 </div>
                
                 <script type="text/javascript">
			
		     			 jQuery('#datepicker').datepicker({
					    format: "dd-mm-yyyy"
					     });  
	
        		 </script>
        		 </div>
	 </div>
	 <div class="row">
	 			 <div class='col-sm-3'>
               	 <div class='form-group'>
                    <label for="quotation">Unit</label>
                 <div class="required-field-block">
				 <select class="form-control" id="unit">
			     <option>Select Unit</option>
			     <option>DEMO COMPANY</option>  
			     </select>
			      <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
                 </div>
                 </div>
                 <div class="col-sm-3">
		 		 <div class='form-group'>
                 <label for="customer">Supplier</label>
		    	 <div class="required-field-block">
                 <input class="form-control" id="supplier" name="supplier" size="30" type="text" placeholder="Type and select Supplier"/>
		   		 <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
            	 </div>
				 </div>
				 <div class="col-sm-3">
		 		 <div class='form-group'>
                 <label for="customer">Currency</label>
		    	 <div class="required-field-block">
                 <input class="form-control" id="currency" name="currency" size="30" type="text" placeholder="Type and select Currency"/>
		   		 <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
            	 </div>
				 </div>
	 </div>
	 <div class="row">
	 			 <div class="col-sm-3">
		 		 <div class='form-group'>
                 <label for="customer">Ex. Rate</label>
		    	 <div class="required-field-block">
                 <input class="form-control" id="ex_rate" name="ex_rate" size="30" type="text" placeholder="Type and select Ex. Rate"/>
		   		 <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
            	 </div>
				 </div> 
				 <div class='col-sm-3'>
               	 <div class='form-group'>
                    <label for="quotation">Tax Type</label>
                 <div class="required-field-block">
				 <select class="form-control" id="tax_type">
			     <option>Select Tax Type</option>
			     <option>Local</option>  
			     <option>InterState</option> 
			     <option>Import</option> 
			     <option>No Tax</option> 
			     </select>
			      <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
                 </div>
                 </div>
                 <div class='col-sm-3'>
               	 <div class='form-group'>
                    <label for="quotation">Freight</label>
                 <div class="required-field-block">
				 <select class="form-control" id="freight">
			     <option>Select Freight</option>
			     <option>Paid</option>  
			     <option>To Pay</option> 
			     <option>Not Applicable</option> 
			     <option>Included In Price</option> 
			     </select>
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
                    <label for="quotation">Form</label>
                 <div class="required-field-block">
				 <select class="form-control" id="form">
			     <option>Select Form</option>
			     <option>Applicable</option>  
			     <option>Not Applicable</option> 
			     <option>Not Issued</option> 
			     </select>
			      <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
                 </div>
                 </div>
                 <div class="col-sm-3">
		 		 <div class='form-group'>
                 <label for="customer">Form Name</label>
		    	 <div class="required-field-block">
                 <input class="form-control" id="form_name" name="form_name" size="30" type="text" placeholder="Type and select Form Name"/>
		   		 <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
            	 </div>
				 </div>
	 </div>
	 <div class="row" style="margin-top:50px;">
     			<div class="col-md-0-5 col-xs-12" id="header">
				<label>#</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
				<label>Indent No</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
				<label>Io No</label>
				</div>
				<div class="col-md-2  col-xs-12" id="header">
				<label>Item</label>
				</div>
				<div class="col-md-2 col-xs-12" id="header">
				<label>Color</label>
				</div>
				
				<div class="col-md-1 col-xs-12" id="header">
				<label>UOM</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
				<label>Quantity</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
				<label>Rate</label>
				</div>	
				<div class="col-md-2 col-xs-12" id="header">
				<label>ETA(Deliver Date)</label>
				</div>
				<div class="col-md-1-5 col-xs-12" id="header">
				<label>Description</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
				<label>Dis%</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
				<label>ED%</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
				<label>ECESS%</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
				<label>SHECESS%</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
				<label>ST%</label>
				</div>	
				<div class="col-md-1 col-xs-12" id="header">
				<label>Surcharge%</label>
				</div>
     </div>
      <div class="row linerow">
		        <div class="col-md-0-5 col-xs-12">
		        <div class="form-group grid-slno">1</div>
		        </div>
		         <div class="col-md-1 col-xs-12">
				<input class="form-control" id="Indent_No" name="Indent_No" type="text" placeholder="Indent No"/>
				</div>
				 <div class="col-md-1 col-xs-12">
				<input class="form-control" id="Io_No" name="Io_No" type="text" placeholder="Io No"/>
				</div>
		        <div class="col-md-2 col-xs-12">
				<input class="form-control" id="item" name="item" type="text" placeholder="Item"/>
				</div>
				<div class="col-md-2 col-xs-12" >
				<input class="form-control" id="color" name="color" type="text" placeholder="Color "/>
				</div>
				<div class="col-md-1 col-xs-12" >
				<input class="form-control" id="uom" name="uom" type="text" placeholder="UOM "/>
				</div>
				<div class="col-md-1 col-xs-12" >
				<input class="form-control" id="quantity" name="quantity" type="text" placeholder="Quantity"/>
				</div>
				<div class="col-md-1 col-xs-12" >
				<input class="form-control" id="rate" name="rate" type="text" placeholder="Rate"/>
				</div>	
				<div class="col-md-2 col-xs-12" >
			    <div class='form-group' >
           	    <div class='input-group date' id='datepicker1' >
		        <div class="required-field-block">
			    <input type='text' class="form-control" placeholder="Select ETA(Deliver Date)" />
			    <div class="required-icon">
			    <div class="text">*</div>
		        </div>
		        </div>
			    <span class="input-group-addon">
				<span class="glyphicon glyphicon-calendar"></span>
			    </span>
                </div>		                
                </div>
                    <script type="text/javascript">
			              jQuery('#datepicker1').datepicker({
					      format: "dd-mm-yyyy"
					       });  
	
        			</script>
				</div>		
				<div class="col-md-1-5 col-xs-12" >
				<input class="form-control" id="Description" name="Description" type="text" placeholder="Description"/>
				</div>
				<div class="col-md-1 col-xs-12" >
				<input class="form-control" id="Dis" name="Dis" type="text" placeholder="Dis%"/>
				</div>
				<div class="col-md-1 col-xs-12" >
				<input class="form-control" id="ED" name="ED" type="text" placeholder="ED%"/>
				</div>
				<div class="col-md-1 col-xs-12" >
				<input class="form-control" id="ECESS" name="ECESS" type="text" placeholder="ECESS%"/>
				</div>
				<div class="col-md-1 col-xs-12" >
				<input class="form-control" id="SHECESS" name="SHECESS" type="text" placeholder="SHECESS%"/>
				</div>
				<div class="col-md-1 col-xs-12" >
				<input class="form-control" id="ST" name="ST" type="text" placeholder="ST%"/>
				</div>
				<div class="col-md-1 col-xs-12" >
				<input class="form-control" id="Surcharge" name="Surcharge" type="text" placeholder="Surcharge%"/>
				</div>									
	</div>
	
	<div class="row" style="margin-top:50px;">
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
     </div>
     
	 <div class="row ">
	
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
	 </div>
	
	<div class='row' style="margin-top:50px;">
                <div class='col-sm-3'>    
                <div class='form-group'>
				<label for="remarks">Payment Terms</label>
				<textarea class="form-control" rows="6" id="remarks" name="remarks" placeholder="Payment Terms" ></textarea>
                </div>
            	</div>
    </div>
    <div class="row">
				<div class='col-sm-3'  >
				<div class='form-group'>
				<label >Delivery Terms</label>
				<textarea class="form-control" rows="6"  id="internal_memo" name="internal_memo" maxlength="250" placeholder="Delivery Terms" ></textarea>
				</div>            
				</div>
	</div>
	<div class='row'>
				<div class="col-sm-12">
				<h4><label class="text-primary">Followers </label></h4>
				<%-- <table width="">
				<tr>
				    <c:set var="k" value="${0}" />
					<c:forEach var="siContactImage" items="${si_followers_list}">
					<c:if test="${k%18 eq 0 }">
				</tr>
				<tr>
					</c:if>
				     ${ siContactImage.contactImage}
					<c:set var="k" value="${k+1}" />
					</c:forEach>
				</tr>
				</table> --%>
				</div>
	</div>
	<div class='row'>
				<div class="col-sm-12">
				<table class="col-sm-6 col-xs-12 " id="follower">
				<tr>
				<td align="left" valign="top" width="40%"><b>User Name</b></td>
				<td align="left" valign="top"><b>Remark</b></td>
				</tr>

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
				<select class="form-control" name="user_name" id="user_name" onChange="addRow('follower')" align="left">
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
				<select class="form-control" name="user_name"id="user_name" onChange="addRow('follower')" align="left">
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
	</div>
	<div class='row '>
   				 <div class='col-sm-10 col-xs-12 col-sm-offset-1'>
   				 <div class='pull-right centered save-view'>            
                 <div class="btn-group dropup">
               	 <button name="save" id="save"    class="btn btn-success ladda-button"  data-style="expand-right">
			     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			   	 </button>
               	 <button    class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="height:34px;"><span class="caret"></span></button>
                 <ul class="dropdown-menu">
           		 <li><a href="#" >Save & Send</a></li>
          		 <li><a href="#" >Save & New</a></li>
                 <li class="divider"></li>
                 <li><a  href="#">Save & Close</a></li>
                 </ul>
                 </div>
                 <button type="button" class="btn btn-primary"  onclick="showPurchaseOrder()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>	
                 </div>
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
</form>
</body>
</html>