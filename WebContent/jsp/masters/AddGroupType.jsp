<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script language="javascript">
var grouptypelocalizedStrings = {
		GROUPTYPE_SAVED: "CreateSuccessfully",
    	GROUPTYPE_SAVED_FAILED: "CreateFailed"
}
</script>
<style>
.container {
	margin-top: 30px;
}

.filter-col {
	padding-left: 10px;
	padding-right: 10px;
}
.add-page-header{
margin:18px 0 10px 0px;
}
</style>

<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form action="" id="validate_form_grouptype" method="post" role="form"  autocomplete="off">
<div class="trans-form"> 
  <div class="modal fade" id="groupTypeMasterModal" role="dialog">
    <div class="modal-dialog " >
<div class="modal-content">
 <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Group Type</h4>
        </div>
    <div class="modal-body" >     
			
			<div class='row row-no-margin' > 
							<div class="col-sm-6">
								<div class='row row-no-margin'>
									<div class='form-group'>
										<label for="form_group_type_name">Item Group Type  </label> 
											<div class="required-field-block">
										    <input class="form-control" id="form_group_type_name" name="form_group_type_name" maxlength="100" size="30" type="text" placeholder="Enter Item Group Type" />
											<div class="required-icon">
											<div class="text">*</div>
										</div>
										</div>
									</div>
								</div>
								</div>
						
							<div class="col-sm-6">
								<div class='row row-no-margin'>
									<div class='form-group'>
										<label for="parent_group_type_name">Parent Group Type  </label> 
											<div class="required-field-block">
										    <input class="form-control" id="parent_group_type_name" name="parent_group_type_name" maxlength="100" size="30" type="text" placeholder="select Parent Group Type" />
										    <input class="form-control" id="parent_group_type_id" name="parent_group_type_id" maxlength="100" size="30" type="hidden"  />
											<div class="required-icon">
											<div class="text">*</div>
										</div>
										</div>
									</div>
								</div>
								</div>
						
							<div class="col-sm-6">
								<div class='row row-no-margin'>
									<div class='form-group'>
										<label for="seq_no">Display Sl.no </label> 
											<div class="required-field-block">
										    <input class="form-control" id="seq_no" name="seq_no" maxlength="100" size="30" type="text" placeholder="Enter Display Sl.no" />
											<div class="required-icon">
											<div class="text">*</div>
										</div>
										</div>
									</div>
								</div>
								</div>
						</div>
           
	</div>
		  <div class="modal-footer">
             <button type="button" onclick="saveGroupTypeFormPage()" class="btn btn-success"> Save </button> 
              <button type="button" class="btn btn-danger" id="close_btn"  data-dismiss="modal">Close</button>
            
		<!-- <input type="hidden" id="add_new_currency" data-toggle="modal" data-target="#currencyMasterModal" /> -->
		<!-- <input type="hidden" name="servlet_name" id="servlet_name" /> 
		<input type="hidden" name="request_type" id="request_type" />
		<input type="hidden" name="invoke_method" id="invoke_method" />
		<input type="hidden" name="invoke_class" id="invoke_class" />
		<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" /> -->
		<input type="hidden" name="pageno" id="pageno" />
		<input type="hidden" name="mode" id="mode" value="n" />
		<input type="hidden" name="party_id" id="party_id" />
		
		<input type="submit" name="grouptype_validation_btn" id="grouptype_validation_btn" style="display:none;"/>
	</div>	
	</div>
	</div>
	</div>
	</div>
		
		<script>
		$('#validate_form_grouptype.modal').on('shown.bs.modal', function () {
		  	   $('input:text:visible:first', this).focus();
		  	});

$('#validate_form_grouptype').bootstrapValidator({
	
	  message: 'This value is not valid',
	  feedbackIcons: {
	      valid: 'glyphicon glyphicon-ok',
	      invalid: 'glyphicon glyphicon-remove',
	      validating: 'glyphicon glyphicon-refresh'
	  },
	/*   excluded: ':disabled', */
	  fields: { 
		  
		  form_group_type_name: {
          validators: {
              notEmpty: {
                  message: 'This field is required'
              }
          }
      },
      seq_no: {
          validators: {
              notEmpty: {
                  message: 'This field is required'
              }
          }
      },
      parent_group_type_name: {
	            validators: {
	                notEmpty: {
	                    message: 'This field is required'
	                },
	                callback: {
	                    message: 'This field is required',
	                    callback: function(value, validator, $field) {
	                    	if ($("#parent_group_type_id").val()=='' || $("#parent_group_type_id").val()==0 ) {
	                            return {
	                                valid: false,
	                                message: 'This field is required'
	                            };
	                        }
	                    	return true;
	                    }
	                }
	          
	            }
	        },
	  }
});
 !function ($) {
	 
		$(function(){
		 $('#parent_group_type_name').listParentGroupType({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetParentGroupType",
			  nameField :'#parent_group_type_name',
			  idField:'#parent_group_type_id',
			  formId:'#validate_form_grouptype',
			  validateId:'parent_group_type_name' 
		  });
		 });
 }(window.jQuery);	
</script>
	

	

	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>

</form>