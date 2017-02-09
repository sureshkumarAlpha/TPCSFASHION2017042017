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


label {
    display: inline-block;
    max-width: 100%;
    margin-bottom: 0px;
    font-weight: 700;
}

.form-group{
margin-bottom:4px;
}


.col-width-2{
width:2%;
}

.col-width-8{
width:100%;
}
.col-width-4{
width:4%;
}
.col-width-5{
width:5%;
}
.col-width-10{
width:10%;
}
.form-control{
	height:25px;
	font-size: 11px;
}
.input-group-addon {
    padding: 4px 8px;
    font-size: 12px;
}
.required-field-block .required-icon:after{
    border-bottom: 25px solid transparent;
}
   
   
.form-control-feedback {
    line-height: 30px;
    top: 0px;
}

.grid-slno {
        line-height: 0;
}
 
 
 .btn {
    padding: 1px 12px;
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
                    <h2 class="add-page-header" style="margin:18px 0 0px 20px;">Internal Order</h2>
                </div>
                  </div>
                  
  <div class="page-wrapper">
<div class="page-inner"> 
<div class="row"> 
<div class="col-sm-3">
		 <div class='form-group'>
                    <label for="customer">Buyer Name</label>
		    <div class="required-field-block">
                    <input class="form-control" id="ordern_name" name="order_name" size="30" type="text" placeholder="Type and select Buyer Name"/>
		    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
		</div>

			<div class="col-sm-3">
		 <div class='form-group'>
                    <label for="customer">IO No</label>
		    <div class="required-field-block">
                    <input class="form-control" id="io_no" name="io_no" size="30" type="text" placeholder="Type and select Io No"/>
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
                    <label for="quotation">Order Type</label>
                    <div class="required-field-block">
			<select class="form-control" id="order_type">
			    <option>Select Order Type</option>
			    <option>Original</option>  
			     <option>Jobwork</option>  
			      <option>Internal</option>  
			  </select>
			  <div class="required-icon">
			<div class="text">*</div>
		     </div>
                </div>
                </div>
            </div>
             <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="quotation">Unit</label>
                    <div class="required-field-block">
			<select class="form-control" id="unit">
			    <option>Select unit</option>
			    <option>DEMO COMPANY</option>  
			  </select>
			  <div class="required-icon">
			<div class="text">*</div>
		     </div>
                </div>
                  </div>
       </div>
</div>


					<div class="row table-responsive">
						<table class="table table-bordered table-condensed table-hover" id="grid_table"> <!-- style="min-width: 100%;" -->
							<thead>
								<tr class="header-det">
						<!-- 			<th class="col-width-2">Action</th>
									<th class="col-width-8">Style</th>
									<th class="col-width-8">Description</th>
									<th class="col-width-8">Color</th>
									<th class="col-width-8">BOM No</th>
									<th class="col-width-8">Size Range</th>
									<th class="col-width-2" >&nbsp;</th> -->
									
											<th >Action</th>
									<th>Style</th>
									<th >Description</th>
									<th >Color</th>
									<th >BOM No</th>
									<th >Size Range</th>
									<th>&nbsp;</th>
								</tr>
							</thead>
							<tbody>
								<tr class="datarow">
									<td class="col-width-2">
										<div class="form-group grid-slno">1</div>
									</td>
									<td >
										<div class="form-group has-feedback">
											<input id="heading_0" name="heading_0" value="" 
												placeholder="Enter Style" maxlength="50" type="text"
												class="form-control col-width-8" data-bv-field="heading_0">
										</div>
									</td>
									<td >
										<div class="form-group has-feedback">
											<!--    <div class="required-field-block"> -->
											
											<textarea class="form-control col-width-8" rows="1"  onfocus="this.rows=5;"  onblur="this.rows=1;" id="ship_to_address" name="ship_to_address" 
											placeholder="Description" maxlength="250" ></textarea>
											
										</div>
									</td>
									<td >
										<div class="form-group has-feedback">
											<input type="text"
												class="form-control  col-width-8"
												placeholder="Color" name="component_0" id="component_0" 
												data-bv-field="component_0" autocomplete="off">
										</div>
									</td>
									<td >
										<div class="form-group has-feedback">
											<input type="text"
												class="form-control  col-width-8"
												name="group_name_0" placeholder="BOM No" id="group_name_0" 
												maxlength="100" data-bv-field="group_name_0"
												autocomplete="off">
										</div>
									</td>
									<td >
											<div class="form-group required-field-block">
												<input type="text"
													class="form-control col-width-8"
													name="material_0" id="material_0"
													placeholder="Size Range"
													maxlength="50"
													>
												<div class="required-icon">
													<div class="text">*</div>
												</div>
											</div>
									</td>



									<td class="col-width-2"><a href="javascript:clearNewBOMDetail('0')"
										name="clear_0" id="clear_0" title="Clear"><span
											class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
									</td>
								</tr>
								<tr class="datarow">
									<td colspan="7"><a
										href="javascript:validateBOM('n','1',0);"><span
											class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;Add
											Another Line</a></td>
								</tr>

							</tbody>
						</table>

					</div>
					<!-- <div class="row" style="margin-top:50px;">
     	<div class="col-md-0-5 col-xs-12" id="header">
			<label>#</label>
			</div>
			<div class="col-md-2 col-xs-12" id="header">
					<label>Style</label>
				</div>
				<div class="col-md-3  col-xs-12" id="header">
					<label>Description</label>
				</div>
				<div class="col-md-2 col-xs-12" id="header">
					<label>Color</label>
				</div>
				<div class="col-md-2 col-xs-12" id="header">
					<label>Ship Date</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>BOM No</label>
				</div>
				<div class="col-md-1-5 col-xs-12" id="header">
					<label>Size Range</label>
				</div>
     </div>
 <div class="row linerow">
        <div class="col-md-0-5 col-xs-12">
        <div class="form-group grid-slno">1</div>
        </div>
        <div class="col-md-2 col-xs-12">
        <div class="required-field-block">
        <input class="form-control" id="style" name="style" type="text" placeholder="Style"/>
         <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
        </div>
        <div class="col-md-3 col-xs-12">
        <div class="required-field-block">
			<input class="form-control" id="Description" name="Description" type="text" placeholder="Description"/>
			 <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
		</div>
		<div class="col-md-2 col-xs-12" >
		<div class="required-field-block">
		<input class="form-control" id="Color" name="Color" type="text" placeholder="Color "/>
		 <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
		</div>
		<div class="col-md-2 col-xs-12" >
		
		 <div class='form-group' >
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
		<div class="col-md-1 col-xs-12" >
		<div class="required-field-block">
		<input class="form-control" id="bom" name="bom" type="text" placeholder="BOM No "/>
		 <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
		</div>
		<div class="col-md-1-5 col-xs-12" >
		<div class="required-field-block">
			<input class="form-control" id="Size_Range" name="Size_Range" type="text" placeholder="Size Range "/>
			 <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
		</div>													
</div> -->
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
	 
	  <div class='row' style="margin-top:20px;">
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
</div>
  


              
<div class='row'>
 <div class="col-sm-12">
<span class="btn btn-primary fileinput-button" onClick="addAttachment()" 
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
									
										<a href="#" name="del_attach${idx}" id="del_attach${idx}" onClick="deleteReceiptAttachment('${idx}','${filename.invId}','${filename.url}')">
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
                   <button type="button" class="btn btn-primary"  onclick="showInternalOrder()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>	
                 				             
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
 
 
 <style>
 
 /*  #grid_table input[type=text]:focus {
	    width: 150%;
	}
	 */
	
 </style>
 <script type="text/javascript">
 $(function () {
     $('#grid_table input[type=text]').focus(function () {
         $(this).parent().parent().css('width', '30%');
     });
     $('#grid_table input[type=text]').blur(function () {
         $(this).parent().parent().css('width', '0%');
     });
     
     $('#grid_table textarea').focus(function () {
         $(this).parent().parent().css('width', '30%');
     });
     $('#grid_table textarea').blur(function () {
         $(this).parent().parent().css('width', '0%');
     });
 });

/*  
 $(document).ready(function() {
     $('#heading_0').focus(function() {
         $('#heading_0').css("width", "150%");
     });
     $('#heading_0').blur(function() {
         $('#heading_0').css("width", "100%");
     });
 }); */
 
 
/*  $('#heading_0').focus(function()
		 {
		     $(this).animate({ width: '+=50' }, 'fast');
		 }).blur(function()
		 {
		     $(this).animate({ width: '-=50' }, 'fast');
		 }); */
 
 

 
 </script>
</form>
</body>
</html>