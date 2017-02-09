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
<script language="javascript">
var contextpath = "${pageContext.request.contextPath}";
</script>
</head>
<jsp:include page="../common/ValidateUser.jsp" />
<body>
<form action="" id="tpcslogin" method="post" role="form">
<div class="wrapper">
 	<jsp:include page="../common/MainMenu.jsp"> 
	<jsp:param value="2" name="screen_type"/>
	<jsp:param value="Sales.Transactions.SalesOrder" name="screen_name"/>
	</jsp:include>
<div class="row-no-margin row">
                <div class="col-md-11 col-sm-8 col-xs-12">
                    <h2 class="add-page-header" style="margin:18px 0 0px 20px;">Internal Order RMR</h2>
                </div>
                 </div>
                  
<div class="page-wrapper">
<div class="page-inner"> 
	<div class="row">
				<div class="col-sm-3">
		 		<div class='form-group'>
                <label for="customer">IO No</label>
		    	<div class="required-field-block">
                <input class="form-control" id="io_no" name="io_no" size="30" type="text" placeholder="Type and select IO No"/>
		    	<div class="required-icon">
				<div class="text">*</div>
		     	</div>
		     	</div>
                </div>
				</div>
				<div class="col-sm-3">
		 		<div class='form-group'>
                <label for="customer">Style</label>
		    	<div class="required-field-block">
           	    <input class="form-control" id="style" name="style" size="30" type="text" placeholder="Type and select Style"/>
		    	<div class="required-icon">
				<div class="text">*</div>
		     	</div>
		     	</div>
                </div>
                </div>
                <div class="col-sm-3">
                <div class='form-group'>
                <label for="customer">Material</label>
		   		<div class="required-field-block">
                <input class="form-control" id="material" name="material" size="30" type="text" placeholder="Type and select Material"/>
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
                <label for="customer">Color</label>
		    	<div class="required-field-block">
                <input class="form-control" id="color" name="color" size="30" type="text" placeholder="Type and select Color"/>
		    	<div class="required-icon">
				<div class="text">*</div>
		     	</div>
		     	</div>
                </div>
                </div>
                <div class="col-sm-3">
                <div class='form-group'>
                <label for="customer">Uom (Norms)</label>
		    	<div class="required-field-block">
                <input class="form-control" id="uom" name="uom" size="30" type="text" placeholder="Type and select Uom"/>
		    	<div class="required-icon">
				<div class="text">*</div>
		    	</div>
		     	</div>
                </div>
                </div>
                <div class="col-sm-3">
                <div class='form-group'>
                <label for="customer">Sample. Norms</label>
		    	<div class="required-field-block">
                <input class="form-control" id="sample_norms" name="sample_norms" size="30" type="text" placeholder="Type and select Sample. Norms"/>
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
                <label for="customer">Wastage(same.)%</label>
		    	<div class="required-field-block">
                <input class="form-control" id="wastage" name="wastage" size="30" type="text" placeholder="Type and select Wastage(same.)%"/>
		    	<div class="required-icon">
				<div class="text">*</div>
		     	</div>
		     	</div>
                </div>
                </div>
	</div>
<!-- <div class='row' style="margin-top:20px;">
             	<div class='col-sm-3'>    
                <div class='form-group'>
				<label for="remarks">Payment Terms</label>
				<textarea class="form-control" rows="6" id="remarks" name="remarks" placeholder="Payment Terms" ></textarea>
                </div>
            	</div>
	</div>


	<div class="row ">
				<div class='col-sm-3'  >
				<div class='form-group'>
				<label >Delivery Terms</label>
				<textarea class="form-control" rows="6"  id="internal_memo" name="internal_memo" maxlength="250" placeholder="Delivery Terms" ></textarea>
				</div>            
				</div>
	</div> -->
  
  <div class="row" style="margin-top:50px;">
     	<div class="col-md-0-5 col-xs-12" id="header">
			<label>#</label>
			</div>
			<div class="col-md-2 col-xs-12" id="header">
					<label>Product</label>
				</div>
				<div class="col-md-2  col-xs-12" id="header">
					<label>Group</label>
				</div>
				<div class="col-md-2 col-xs-12" id="header">
					<label>Material</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>Color</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>UOM</label>
				</div>
				<div class="col-md-1-5 col-xs-12" id="header">
					<label>Qty</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>Wastage</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>Total Qty</label>
				</div>
     </div>
        <div class="row linerow">
        <div class="col-md-0-5 col-xs-12">
        <div class="form-group grid-slno">1</div>
        </div>
        <div class="col-md-2 col-xs-12">
        <input class="form-control" id="Payment" name="Payment" type="text" placeholder="Product" value="Tote Bag"/>
        </div>
        <div class="col-md-2 col-xs-12">
			<input class="form-control" id="Delivery" name="Delivery" type="text" placeholder="Group" value="Leather"/>
		</div>
		<div class="col-md-2 col-xs-12" >
		<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Material" value="COW BURNISH"/>
		</div>
		<div class="col-md-1 col-xs-12" >
		<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Color" value="BROWN"/>
		</div>
		<div class="col-md-1 col-xs-12" >
		<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="UOM" value="SQFT"/>
		</div>
		<div class="col-md-1-5 col-xs-12" >
			<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Qty" value="105560"/>
		</div>	
		<div class="col-md-1 col-xs-12" >
			<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Qty" value="0"/>
		</div>
		<div class="col-md-1 col-xs-12" >
			<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Qty" value="105560"/>
		</div>												
</div>
<div class="row linerow">
        <div class="col-md-0-5 col-xs-12">
        <div class="form-group grid-slno">2</div>
        </div>
        <div class="col-md-2 col-xs-12">
        <input class="form-control" id="Payment" name="Payment" type="text" placeholder="Product" value="Tote Bag"/>
        </div>
        <div class="col-md-2 col-xs-12">
			<input name="Delivery" type="text" class="form-control" id="Delivery" value="Lining" placeholder="Group"/>
		</div>
		<div class="col-md-2 col-xs-12" >
		<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Material" value="LINING COTTON STRIPE 44&quot; 601"/>
		</div>
		<div class="col-md-1 col-xs-12" >
		<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Color" value="BEIGE"/>
		</div>
		<div class="col-md-1 col-xs-12" >
		<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="UOM" value="MTR"/>
		</div>
		<div class="col-md-1-5 col-xs-12" >
			<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Qty" value="11252"/>
		</div>	
		<div class="col-md-1 col-xs-12" >
			<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Qty" value="0"/>
		</div>
		<div class="col-md-1 col-xs-12" >
			<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Qty" value="11252"/>
		</div>												
</div>
<div class="row linerow">
        <div class="col-md-0-5 col-xs-12">
        <div class="form-group grid-slno">3</div>
        </div>
        <div class="col-md-2 col-xs-12">
        <input class="form-control" id="Payment" name="Payment" type="text" placeholder="Product" value="Tote Bag"/>
        </div>
        <div class="col-md-2 col-xs-12">
			<input class="form-control" id="Delivery" name="Delivery" type="text" placeholder="Group" value="Lining"/>
		</div>
		<div class="col-md-2 col-xs-12" >
		<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Material" value="MICRO FIBRE"/>
		</div>
		<div class="col-md-1 col-xs-12" >
		<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Color" value="BLACK"/>
		</div>
		<div class="col-md-1 col-xs-12" >
		<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="UOM" value="MTR"/>
		</div>
		<div class="col-md-1-5 col-xs-12" >
			<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Qty" value="870"/>
		</div>	
		<div class="col-md-1 col-xs-12" >
			<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Qty" value="0"/>
		</div>
		<div class="col-md-1 col-xs-12" >
			<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Qty" value="870"/>
		</div>												
</div>
<div class="row linerow">
        <div class="col-md-0-5 col-xs-12">
        <div class="form-group grid-slno">4</div>
        </div>
        <div class="col-md-2 col-xs-12">
        <input class="form-control" id="Payment" name="Payment" type="text" placeholder="Product" value="Tote Bag"/>
        </div>
        <div class="col-md-2 col-xs-12">
			<input class="form-control" id="Delivery" name="Delivery" type="text" placeholder="Group" value="Lining"/>
		</div>
		<div class="col-md-2 col-xs-12" >
		<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Material" value="NON WOVEN FABRIC 70 GSM"/>
		</div>
		<div class="col-md-1 col-xs-12" >
		<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Color" value="BLACK"/>
		</div>
		<div class="col-md-1 col-xs-12" >
		<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="UOM" value="MTR"/>
		</div>
		<div class="col-md-1-5 col-xs-12" >
			<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Qty" value="580"/>
		</div>	
		<div class="col-md-1 col-xs-12" >
			<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Qty" value="0"/>
		</div>
		<div class="col-md-1 col-xs-12" >
			<input class="form-control" id="Insurance" name="Insurance" type="text" placeholder="Qty" value="580"/>
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
				<div class="col-sm-12">
				<h4><label class="text-primary">Followers </label></h4>
				<table width="">
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
				</table>
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
				<select class="form-control" name="user_name"
				id="user_name" onChange="addRow('follower')" align="left">
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
				<select class="form-control" name="user_name"
						id="user_name" onChange="addRow('follower')" align="left">
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
                <button type="button" class="btn btn-primary"  onclick="showInternalOrderRMR()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>	
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