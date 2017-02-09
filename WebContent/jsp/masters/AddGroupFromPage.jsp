  <!-- --------------------------GROUP MASTER------------------------------ -->
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  <form id="validate-form-group" autocomplete="off">
  

  <div class="form-horizontal trans-form"> 
  <div class="modal fade" id="groupMasterModal" role="dialog" style="z-index:2040">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Group</h4>
        </div>
        <div class="modal-body">
   <div class='row'>       
           <div class="form-group">
            <label class="col-sm-3 control-label" for="group_code">Group Code&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
             <div class="col-sm-8">          
            <div class="required-field-block">
                    <input class="form-control" id="group_code" name="group_code"  type="text"  maxlength="15" placeholder="Group Code"  />
		    <div class="required-icon" >
			<div class="text">*</div>
		     </div>
		     </div>
		     </div>
        </div>    
                 <div class="form-group">
                 <label class="col-sm-3 control-label"  for="group_name">Group Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
      <div class="col-sm-8">
        <div class="required-field-block">
                         <input class="form-control" id="group_name" name="group_name"  type="text" maxlength="50"  placeholder="Group Name" />
					    <div class="required-icon" >
						<div class="text">*</div>
		                    </div>
		                     </div>
      </div>
    </div>
            <!-- <div class="form-group">
            <label class="col-sm-3 control-label" for="groupTypeId">Group Type :</label>
             <div class="col-sm-8">          
             <div class="required-field-block">
             <select  name="group_type"  id="group_type"   class="form-control" >
							<option  value="-1">&lt;--Select--&gt;</option>

						</select>
					    <div class="required-icon" >
						<div class="text">*</div>
		                    </div>
		                     </div>
		     </div>
        </div>   -->
        <div class="form-group">
            <label class="col-sm-3 control-label" for="parent_group">Parent Group&nbsp;&nbsp;&nbsp;&nbsp;:</label>
             <div class="col-sm-8">          
                        <input type="text" class="form-control"    name="parent_group" id="parent_group"   maxlength="50"  placeholder="Type and select parent group" />
                   <input type="hidden" class="form-control"    name="parent_group_id" id="parent_group_id"    />
		     </div>
        </div>
        
<div class='form-group'>
  	 <label class="col-sm-3 control-label" for="group_status">Status</label>
  	  <div class="col-sm-8">    
			<select class="form-control" id="group_status" name="group_status">
			<option value="1"  >Active</option>
			<option value="0"  >Inactive</option>
			</select>
</div>
</div>
         <!-- <div class="col-sm-6">
        <div class="form-group">
					 <div class="checkbox"> 
				 <label> <input type="checkbox" id="itemtracking" name="itemtracking" value="1"  /><span class="cr"><i class="cr-icon glyphicon glyphicon-ok"></i></span>Is item tracking ?</label>
				 </div>
        </div>
        </div> -->
         <!-- <div class="col-sm-6">
        <div class="form-group">
						 <div class="checkbox"> 
				 <label> <input type="checkbox" id="barcodetracking" name="barcodetracking" value="1"  /><span class="cr"><i class="cr-icon glyphicon glyphicon-ok"></i></span>Is barcode tracking ?</label>
				 </div>
        </div>
        </div> -->
        </div>
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-success" onclick="saveGroupFromPage()">Save</button>	
          <button type="button" class="btn btn-danger" id="group_close"  data-dismiss="modal">Close</button>
          <input type="submit" name="validation_grp_btn" id="validation_grp_btn" style="display:none;"/>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
 	 <script language="javascript">
 	 
 	$('#validate-form-group .modal').on('shown.bs.modal', function () {
 	   $('#validate-form-group input:text:visible:first', this).focus();
 	});
 	
 	
 	$('#validate-form-group').bootstrapValidator({
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
		 
	
		 
	!function ($) {
	 
	$(function(){	  
		  $('#validate-form-group #parent_group').listGroup({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroup",
			  nameField :'#validate-form-group #parent_group',
			  idField:'#validate-form-group #parent_group_id'
		  });
	 });
}(window.jQuery);	
</script>
</form>