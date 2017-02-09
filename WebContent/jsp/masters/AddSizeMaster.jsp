  <!-- --------------------------GROUP MASTER------------------------------ -->
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
 <script language="javascript">
var sizelocalizedStrings = {
		SIZE_ALREADY_EXISTS: "<fmt:message  bundle="${bundle}" key="Size.AlreadyExixts"/>",
		SIZE_SAVED: "<fmt:message  bundle="${bundle}" key="Size.CreateSuccessfully"/>",
		SIZE_SAVED_FAILED: "<fmt:message  bundle="${bundle}" key="Size.CreateSizeFailed"/>"
}
</script>

 
 
  <form id="validate-form-sizeMaster" autocomplete="off">
  

  <div class="form-horizontal trans-form"> 
  <div class="modal fade" id="sizeMaster" role="dialog" style="z-index:2040">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Size</h4>
        </div>
        <div class="modal-body">
   <div class='row'>       
           <div class="form-group">
            <label class="col-sm-3 control-label" for="size_name">Size Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
             <div class="col-sm-8">          
            <div class="required-field-block">
                    <input class="form-control" id="size_name" name="size_name"  type="text"  maxlength="15" placeholder="Enter Size "  />
		  <input type="hidden" name="size_id" id="size_id"/>
		    <div class="required-icon" >
			<div class="text">*</div>
		     </div>
		     </div>
		     </div>
        </div>
         <div class="form-group">
            <label class="col-sm-3 control-label" for="group_code">Status &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
             <div class="col-sm-8">          
            <div class="required-field-block">
		      <select class="form-control" id="is_active" name="is_active">
			   <option value="1"> Active</option>
		  	   <option value="0" > Inactive</option>
				</select>
				</div>
				</div>     
                 
           
        </div>
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-success" onclick="saveSizeMaster()">Save</button>	
          <button type="button" class="btn btn-danger" id="group_close"  data-dismiss="modal">Close</button>
          <input type="submit" name="validation_grp_btn" id="validation_grp_btn" style="display:none;"/>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
 	 <script language="javascript">
 	 
 	$('#validate-form-sizeMaster .modal').on('shown.bs.modal', function () {
 	   $('#validate-form-sizeMaster input:text:visible:first', this).focus();
 	});
 	
 	
 	$('#validate-form-sizeMaster').bootstrapValidator({
		//  live: 'disabled',
		  message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisValueIsNotValid"/>,
		  feedbackIcons: {
		      valid: 'glyphicon glyphicon-ok',
		      invalid: 'glyphicon glyphicon-remove',
		      validating: 'glyphicon glyphicon-refresh'
		  },
		  fields: {
			  group_name: {
	              validators: {
	                  notEmpty: {
	                      message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
	                  }
	              }
	          },
	          group_code: {
	            validators: {
	                notEmpty: {
	                    message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
	                }
	            }
	        }/* ,
	        group_type: {
	              validators: {
	                  notEmpty: {
	                      message: 'This field is required'
	                  },
	                    callback: {
	                        message: 'This field is required',
	                        callback: function(value, validator, $field) {
	                        	if (value< 1) {
	                                return {
	                                    valid: false,
	                                    message: 'This field is required'
	                                };
	                            }
	                        	return true;
	                        }
	                    }
	              }
	          } */
		  }
		});
		 
	
	</script>
</form>
