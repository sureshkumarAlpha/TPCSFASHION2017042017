<!-- Account Group Modal -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
  <script language="javascript">
var accountGrouplocalizedStrings = {
		ACCOUNT_GROUP_ALREADY_EXISTS: "<fmt:message  bundle="${bundle}" key="AccountGroup.AlreadyExixts"/>",
		ACCOUNT_GROUP_SAVED: "<fmt:message  bundle="${bundle}" key="AccountGroup.CreateSuccessfully"/>",
		ACCOUNT_GROUP_SAVED_FAILED: "<fmt:message  bundle="${bundle}" key="AccountGroup.CreateGroupFailed"/>"
}
</script>
  <form id="validate-form-account-group">
  <div  class="form-horizontal trans-form" >
  <div class="modal fade" id="accountGroupModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Group</h4>
        </div>
         <div class="modal-body" style="max-height:450px;overflow-y:auto;" >

   <div class='row'>       
           <div class="form-group">
            <label class="col-sm-3 control-label" for="group_code">Group Code&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
             <div class="col-sm-8">          
            <div class="required-field-block">
                    <input class="form-control" id="group_code" name="group_code" maxlength="12"  type="text" placeholder="Group Code"  />
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
                         <input class="form-control" id="group_name" name="group_name"  maxlength="50" type="text" placeholder="Group Name" />
					    <div class="required-icon" >
						<div class="text">*</div>
		                    </div>
		                     </div>
      </div>
    </div>
      <div class="form-group">
                 <label class="col-sm-3 control-label"  for="group_name">Parent Group&nbsp;&nbsp;&nbsp;&nbsp;:</label>
      <div class="col-sm-8">
        <div class="required-field-block">
                         <input class="form-control" id="parent_group" name="parent_group"  maxlength="50" type="text" placeholder="Parent Group Name" />
                           <input id="parent_group_id" name="parent_group_id"type="hidden" />
                            <input id="parent_group_level" name="parent_group_level"type="hidden" />
                           
					    <div class="required-icon" >
						<div class="text">*</div>
		                    </div>
		                     </div>
      </div>
    </div>
            <div class="form-group">
            <label class="col-sm-3 control-label" for="group_type">Group Type&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
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
        </div>  
        <div class="form-group">
            <label class="col-sm-3 control-label" for="display_order">Display Order&nbsp;&nbsp;&nbsp;:</label>
             <div class="col-sm-8">          
                        <input type="text"  class="form-control"  name="display_order" id="display_order"  maxlength="5" placeholder="Display Order" />
		     </div>
        </div>
        </div>
        </div>
        <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="saveAccountGroupFromPage()">Save</button>	
          <button type="button" class="btn btn-danger" id="account_group_close" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  <input type="submit" name="validation_account_btn" id="validation_acc_group_btn" style="display:none;"/>
  </div>
  
  
 
<script language="javascript">

/* CollapsibleLists.apply();
 */
$('#validate-form-account-group').bootstrapValidator({
	//  live: 'disabled',
	message : 'This value is not valid',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	 excluded: ':disabled',
	fields : {
		
        parent_group: {
		    validators: {
		        notEmpty: {
		            message: 'This field is required'
		        },
		        callback: {
		            message: 'This field is required',
		            callback: function(value, validator, $field) {
		            	if ($("#parent_group_id").val()=='') {
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
		 group_code: {
             validators: {
                 notEmpty: {
                     message: 'This field is required'
                 }
             }
         },
         group_name: {
           validators: {
               notEmpty: {
                   message: 'This field is required'
               }
           }
       },
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
         },
         display_order: {
             validators: {
                 numeric: {
                     message: 'This field must be a number'
                 }
             }
         }

	}
});
</script>
<script type="text/javascript">

$('.modal').on('shown.bs.modal', function () {
	   $('input:text:visible:first', this).focus();
	});
	
	
!function ($) {
	 
	$(function(){
			
		  $('#parent_group').listParentGroup({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetParentGroup",
			  nameField :'#parent_group',
			  idField:'#parent_group_id',
			  levelField:'#parent_group_level',
			  formId:'#validate-form-account-group',
			  validateId:'parent_group' 
		  });
		  
	 });
}(window.jQuery);

</script>

</form>