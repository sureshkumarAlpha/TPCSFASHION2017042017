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
  <div class="modal fade" id="accountGroupModal" role="dialog" style="z-index: 2060;">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Account Group</h4>
        </div>
         <div class="modal-body" style="max-height:450px;overflow-y:auto;" >
        <!--  <div class="row">
          <label class="col-sm-6 control-label" for="parent_group">Select Parent Group&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>      
         </div> -->
        <!--  <div class='row'> 
         <div id="account_group_hry" >
         </div>
         
          <input id="parent_group" name="parent_group"  type="hidden"  />
           <input id="parent_group_id" name="parent_group_id"  type="hidden"  />
              <input id="parent_group_level" name="parent_group_level"  type="hidden"  />
         </div> -->
   <div class='row'>       
           <div class="form-group">
            <label class="col-sm-3 control-label" for="account_group_code">Group Code&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
             <div class="col-sm-8">          
            <div class="required-field-block">
                    <input class="form-control" id="account_group_code" name="account_group_code" maxlength="12"  type="text" placeholder="Group Code"  />
		    <div class="required-icon" >
			<div class="text">*</div>
		     </div>
		     </div>
		     </div>
        </div>    
                 <div class="form-group">
                 <label class="col-sm-3 control-label"  for="account_group_name">Group Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
      <div class="col-sm-8">
        <div class="required-field-block">
                         <input class="form-control" id="account_group_name" name="account_group_name"  maxlength="50" type="text" placeholder="Group Name" />
					    <div class="required-icon" >
						<div class="text">*</div>
		                    </div>
		                     </div>
      </div>
    </div>
      <div class="form-group">
                 <label class="col-sm-3 control-label"  for="account_parent_group">Parent Group&nbsp;&nbsp;&nbsp;&nbsp;:</label>
      <div class="col-sm-8">
       <!--  <div class="required-field-block"> -->
                         <input class="form-control" id="account_parent_group" name="account_parent_group"  maxlength="50" type="text" placeholder="Parent Group Name" />
                           <input id="account_parent_group_id" name="account_parent_group_id"  type="hidden" />
                            <input id="account_parent_group_level" name="account_parent_group_level"type="hidden" />
                           
					  <!--   <div class="required-icon" >
						<div class="text">*</div>
		                    </div> -->
		                    <!--  </div> -->
      </div>
    </div>
            <div class="form-group">
            <label class="col-sm-3 control-label" for="account_group_type">Group Type&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
             <div class="col-sm-8">          
             <div class="required-field-block">
             <select  name="account_group_type"  id="account_group_type"   class="form-control" >
							<option  value="-1">&lt;--Select--&gt;</option>

						</select>
					    <div class="required-icon" >
						<div class="text">*</div>
		                    </div>
		                     </div>
		     </div>
        </div>  
        <div class="form-group">
            <label class="col-sm-3 control-label" for="account_display_order">Display Order&nbsp;&nbsp;&nbsp;:</label>
             <div class="col-sm-8">          
                        <input type="text"  class="form-control"  name="account_display_order" id="account_display_order"  maxlength="5" placeholder="Display Order" />
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


$('#validate-form-account-group .modal').on('shown.bs.modal', function () {
	   $('#validate-form-account-group input:text:visible:first', this).focus();
	});

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
		
		account_parent_group: {
		    validators: {
		        notEmpty: {
		            message: 'This field is required'
		        },
		        callback: {
		            message: 'This field is required',
		            callback: function(value, validator, $field) {
		            	if ($("#account_parent_group_id").val()=='') {
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
		 account_group_code: {
             validators: {
                 notEmpty: {
                     message: 'This field is required'
                 }
             }
         },
         account_group_name: {
           validators: {
               notEmpty: {
                   message: 'This field is required'
               }
           }
       },
       account_group_type: {
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
         account_display_order: {
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

	
	
!function ($) {
	 
	$(function(){
			
		  $('#validate-form-account-group #account_parent_group').listParentGroup({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetParentGroup",
			  nameField :'#validate-form-account-group #account_parent_group',
			  idField:'#validate-form-account-group #account_parent_group_id',
			  levelField:'#validate-form-account-group #account_parent_group_level',
			  formId:'#validate-form-account-group',
			  validateId:'account_parent_group' 
		  });
		  
	 });
}(window.jQuery);

</script>

</form>