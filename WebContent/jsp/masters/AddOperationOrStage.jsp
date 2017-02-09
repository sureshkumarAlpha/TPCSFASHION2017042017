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
<script language="javascript">
var contextpath = "${pageContext.request.contextPath}";
</script>
</head>
<jsp:include page="../common/ValidateUser.jsp" />
<body>
<form action=""  method="post" role="form" id="validate-form" class="trans-form" autocomplete="off">
<div class="wrapper">
 <jsp:include page="../common/MainMenu.jsp"> 
 <jsp:param value="2" name="screen_type"/>
		<jsp:param value="Sales.Transactions.SalesOrder" name="screen_name"/>
		</jsp:include>

 <div class="top-bar">
     <div class="row-no-margin row">
                <div class="col-md-11 col-sm-8 col-xs-12">
                    <h2 class="add-page-header" style="margin:18px 0 0px 20px;">Operation/Stage</h2>
                </div>
                  </div>
                  
    <div class="page-wrapper">
<div class="page-inner"> 
              
	<div class="row">
		 <div class="col-sm-3">
		 <div class='form-group'>
                    <label for="customer">Operation  Name</label>
                      <div class="required-field-block">
                    <input class="form-control" id="operation" name="operation" size="30" type="text" placeholder="Enter Operation Name" value="${operation_info.operationName}"/>
                    <input  id="operation_id" name="operation_id" size="30" type="hidden" value="${operation_info.operationId}"/>
		    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
		</div>
			<div class='col-sm-3'  >
			<div class='form-group'>
	  		<label>Status</label>
				<select class="form-control" id="op_status" name="op_status">
				<option value="1" <c:if test="${operation_info.operationStatus eq 1 }">selected="selected"</c:if>>Active</option>
				<option value="0" <c:if test="${operation_info.operationStatus eq 0 }">selected="selected"</c:if>>Inactive</option>
				</select>
		</div>
		</div>
		</div>
       </div>               
	</div>
	</div>	
	<br>
	 <div class="page-wrapper">
	<div class="page-inner"> 	
	<div class="row">
		 <div class="col-sm-3">
		 <div class='form-group'>
                    <label for="customer">Display Sequence</label>
                    <input class="form-control" id="seq_no" name="seq_no" size="30" type="text" placeholder="Enter Display Sequence" value="${operation_info.operationSeqNo}"/>
                </div>
		</div>
		<div class="col-sm-3">
		 <div class='form-group'>
                    <label for="customer">Description</label>
                    <textarea class="form-control address-text-area" rows="6" id="description" name="description" placeholder="Enter Description" maxlength="250" data-bv-field="ship_to_address" >${operation_info.operationDesc}</textarea>
                </div>
		</div>
     
	</div>	
	<!-- New Field -->
  <div class="row">
			 <div class="col-sm-3">
		 <div class='form-group'>
                    <label for="customer">Display Order</label>
                    <select class="form-control" id="display_order" name="display_order">
				<option value="0" <c:if test="${operation_info.displayOrder eq 0 }">selected="selected"</c:if>>No</option>
				<option value="1" <c:if test="${operation_info.displayOrder eq 1 }">selected="selected"</c:if>>Yes</option>
				</select>
                    <%-- <input class="form-control" id="display_order" name="display_order" value=0 size="30" type="text" placeholder="Enter Display Order" value="${operation_info.operationSeqNo}"/> --%>
                </div>
		</div>
		<div class='col-sm-3'  >
			<div class='form-group'>
	  		<label>Inspection Required </label>
				<select class="form-control" id="inspection_Required" name="inspection_Required">
				<option value="0" <c:if test="${operation_info.inspectionRequired eq 0 }">selected="selected"</c:if>>No</option>
				<option value="1" <c:if test="${operation_info.inspectionRequired eq 1 }">selected="selected"</c:if>>Yes</option>
				</select>
		</div>
		</div>
     
	</div>	
	<div class="row">
	
		 <div class="col-sm-3">
		 <div class='form-group'>
                    <label for="customer">Production</label>
                    <select class="form-control" id="production" name="production">
				<option value="1" <c:if test="${operation_info.production eq 1 }">selected="selected"</c:if>>Yes</option>
				<option value="0" <c:if test="${operation_info.production eq 0 }">selected="selected"</c:if>>No</option>
				</select>
                    <%-- <input class="form-control" id="production" name="production" size="30" type="text" value=1 placeholder="Enter Production" value="${operation_info.production}"/> --%>
                </div>
		</div>
		<div class="col-sm-3">
		 <div class='form-group'>
                    <label for="customer">Main Group</label>
                     <input class="form-control" id="main_group" name="main_group" size="30" type="text" placeholder="Enter Main Group" value="${operation_info.mainGroup}"/>
                </div>
		</div>
     
	</div>	
	<div class="row">
		 <div class="col-sm-3">
		 <div class='form-group'>
                    <label for="customer">Sub Group</label>
                    <input class="form-control" id="sub_group" name="sub_group" size="30" type="text" placeholder="Enter Sub Group" value="${operation_info.subGroup}"/>
                </div>
		</div>
			</div>

</div>
	</div>

<br>

<div class="row pull-left">        	         	 
            <div class="col-sm-12 col-xs-12 centered">   
            
            	  		
            	<div class="btn-group dropdown">
                  	
                
                <button name="save" id="save" onclick="saveoprationStage('${mode}','1')" class="btn btn-success ladda-button" data-style="expand-right">
			     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			    <span class="ladda-spinner"></span><span class="ladda-spinner"></span></button>
			    
                <button class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="height:34px;"><span class="caret"></span></button>
                <ul class="dropdown-menu">
               
                	
						<li><a href="javascript:saveoprationStage('${mode}','2');">Save &amp; New</a></li>
						
							
						<li><a href="javascript:saveoprationStage('${mode}','3');">Save &amp; Close</a></li>
						
            
                </ul>
              </div>
            	            
                  	
                  	 <button type="button" class="btn btn-primary" onclick="showOperationOrStage()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>
                  			
              
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
<input type="hidden" name="mode" id="mode" value="${mode}"/>

<script type="text/javascript">
			
			$('#validate-form').bootstrapValidator({
				//  live: 'disabled',
				  message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisValueIsNotValid"/>,
				  feedbackIcons: {
				      valid: 'glyphicon glyphicon-ok',
				      invalid: 'glyphicon glyphicon-remove',
				      validating: 'glyphicon glyphicon-refresh'
				  },
				  fields: {
					  operation: {
			              validators: {
			                  notEmpty: {
			                      message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                  }
			              }
			          }
			          
				  }
				});
			
			$('#seq_no').keypress(
					function(e) {
						var regex = /^[0-9 ]*$/;
						var str = String.fromCharCode(!e.charCode ? e.which
								: e.charCode);
						if (regex.test(str)) {
							return true;
						} else {
							e.preventDefault();

							return false;
						}
					});



	 
</script>


</form>
</body>
</html>