<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
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
<form action=""  id="tpcslogin" method="post" role="form">
<div class="wrapper">
 <jsp:include page="../common/MainMenu.jsp"> 
 <jsp:param value="2" name="screen_type"/>
		<jsp:param value="Sales.Transactions.SalesOrder" name="screen_name"/>
		</jsp:include>


     <div class="row-no-margin row">
                <div class="col-md-11 col-sm-8 col-xs-12">
                    <h2 class="add-page-header" style="margin:18px 0 0px 20px;">Production End</h2>
                </div>
                  </div>
                  
    <div class="page-wrapper">
<div class="page-inner"> 
              
	<div class="row">
		<div class="col-sm-3">
		 <div class='form-group'>
                    <label for="customer">Production No</label>
		    <div class="required-field-block">
                    <input class="form-control" id="sr_no" name="sr_no" size="30" type="text" placeholder="Enter IO No"/>
		    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
		</div>
		
		
		<div class='col-sm-3'>    
                <div class='form-group' >
                    <label for="quotationdate">Production End Date</label>
		    <div class='input-group date' id='datepicker1' >
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
			
		      jQuery('#datepicker1').datepicker({
					    format: "dd-mm-yyyy"
					});  
	
        			</script>
        			
            </div>
            
		       
	</div>		
	<div class="row">
		<div class="col-sm-3">
		 <div class='form-group'>
                    <label for="customer">Operation</label>
                    <input class="form-control" id="sr_no" name="sr_no" size="30" type="text" placeholder="Enter Operation"/>
                </div>
		</div>
		
		<div class='col-sm-3'>    
                <div class='form-group' >
                    <label for="quotationdate">Production End Time</label>
		    <div class='input-group date' id='datepicker2' >
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
			
		      jQuery('#datepicker2').datepicker({
					    format: "dd-mm-yyyy"
					});  
	
        			</script>
        			
            </div>
            
		
	
                      
	</div>	
	<div class="row">
	<div class="col-sm-3">
		 <div class='form-group'>
                    <label for="customer">Operator</label>
                    <input class="form-control" id="sr_no" name="sr_no" size="30" type="text" placeholder="Enter Operator"/>
                </div>
		</div>
		
		<div class="col-sm-3">
		 <div class='form-group'>
                    <label for="customer">Description</label>
                  <textarea class="form-control address-text-area" rows="6" id="ship_to_address" name="ship_to_address" placeholder="Enter Description" maxlength="250" data-bv-field="ship_to_address"></textarea>
                </div>
		</div>
            
	
                      
	</div>
    
    
    
     <div class="row" style="margin-top:50px;">
     	<div class="col-md-0-5 col-xs-12" id="header">
			<label>#</label>
			</div>
			<div class="col-md-1-5 col-xs-12" id="header">
					<label>Jobcard No</label>
				</div>
				<div class="col-md-1-5  col-xs-12" id="header">
					<label>IO No</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>Style</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>Color</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>Produced</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>Accepted</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>Rejected</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>Rework</label>
				</div>
					<div class="col-md-1 col-xs-12" id="header">
					<label>Downgrade</label>
				</div>
						<div class="col-md-1 col-xs-12" id="header">
					<label>Shortage</label>
				</div>
			
     </div>
        <div class="row linerow">
        <div class="col-md-0-5 col-xs-12">
        <div class="form-group grid-slno">1</div>
        </div>
        <div class="col-md-1-5 col-xs-12">
        <input class="form-control" id="Buyer_Order_No" name="Buyer_Order_No" type="text" placeholder="Jobcard No"/>
        </div>
        <div class="col-md-1-5 col-xs-12">
			<input class="form-control" id="Product" name="Product" type="text" placeholder="IO No"/>
		</div>
		<div class="col-md-1 col-xs-12" >
		<input class="form-control" id="Color" name="Color" type="text" placeholder="Style"/>
		</div>
		<div class="col-md-1 col-xs-12" >
		<input class="form-control" id="Qty" name="Qty" type="text" placeholder="Color"/>
		</div>
		<div class="col-md-1 col-xs-12" >
		<input class="form-control" id="Qty" name="Qty" type="text" placeholder="Produced "/>
		</div>
			<div class="col-md-1 col-xs-12" >
		<input class="form-control" id="Qty" name="Qty" type="text" placeholder="Accepted "/>
		</div>
		<div class="col-md-1 col-xs-12" >
		<input class="form-control" id="Qty" name="Qty" type="text" placeholder="Rejected "/>
		</div>
		<div class="col-md-1 col-xs-12" >
		<input class="form-control" id="Qty" name="Qty" type="text" placeholder="Rework "/>
		</div>
		<div class="col-md-1 col-xs-12" >
		<input class="form-control" id="Qty" name="Qty" type="text" placeholder="Downgrade "/>
		</div>
		<div class="col-md-1 col-xs-12" >
		<input class="form-control" id="Qty" name="Qty" type="text" placeholder="Shortage "/>
		</div>												
</div>


<div class="row" style="margin-top:50px;">
     	<div class="col-md-2 col-xs-12" id="header">
			<label>Reason</label>
			</div>
			<div class="col-md-2 col-xs-12" id="header">
					<label>Rejected</label>
				</div>
				<div class="col-md-2  col-xs-12" id="header">
					<label>Rework</label>
				</div>
				<div class="col-md-2 col-xs-12" id="header">
					<label>Downgrade</label>
				</div>
		
     </div>
     
	 <div class="row ">
	
  
			<div class="col-md-2 col-xs-12" >
				 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Reason" />
				</div>
				<div class="col-md-2  col-xs-12" >
				 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Rejected" />
				</div>
				<div class="col-md-2 col-xs-12" >
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Rework" />
				</div>
				<div class="col-md-2 col-xs-12" >
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Downgrade" />
				</div>
				
	 </div>
     
	 
  
<br>

              
<div class='row'>
 <div class="col-sm-12">
<span class="btn btn-primary fileinput-button" onclick="addAttachment()" 
								> <i
								class="glyphicon glyphicon-plus"></i> <span>Add files...</span>
								
							</span>
<img id="ajax-loader" src="images/ajax-loader.gif" class="ajax-loader">
</div>
</div>

					<div class='row'>
					<div class="col-sm-12">
						<table style="border-collapse: collapse;" class="attach-table">

							<c:set var="idx" value="1" />
							<c:forEach var="filename" items="${attached_files}">
								<c:set var="fileV" value="${filename.url}" />
								<%
									String fV = (String) pageContext.getAttribute("fileV");
										if (fV != null && !fV.isEmpty()) {
											request.setAttribute("fileNV", fV);
										} else {
											request.setAttribute("fileNV", "");
										}
								%>
								<tr id="attach_file${idx}">
									<td width="50px">
									
										<a href="#" name="del_attach${idx}" id="del_attach${idx}" onclick="deleteReceiptAttachment('${idx}','${filename.invId}','${filename.url}')">
											<span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
									
										<%-- <button type="button" class="btn btn-danger destroy"
											name="del_attach${idx}" id="del_attach${idx}"
											onclick="deleteInvoiceAttachment('${idx}','${filename.invId}','${filename.url}')">
											<i class="glyphicon glyphicon-trash"></i> <span>Delete</span>
										</button> --%>
									</td>
									<td><a target="_blank"
										href="${salesReceiptAttachPath}${fileNV}">${fileNV}</a></td>
								</tr>
								<c:set var="idx" value="${idx+1}" />
							</c:forEach>
						</table>
						</div>
					</div>
					<div class='row'>
					<div class="col-sm-12">
						<table id="attachments" class="attach-table"
							style="border-collapse: collapse;">

						</table>
						</div>
					</div>

					<div class='row'>
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
									id="user_name" onchange="addRow('follower')" align="left">
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
									id="user_name" onchange="addRow('follower')" align="left">
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
                   <button type="button" class="btn btn-primary"  onclick="showProductionEnd()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>	
                 				             
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