<!-- Account Modal -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
  <script language="javascript">
var accountlocalizedStrings = {
		ACCOUNT_ALREADY_EXISTS: "<fmt:message  bundle="${bundle}" key="Account.AlreadyExixts"/>",
		ACCOUNT_SAVED: "<fmt:message  bundle="${bundle}" key="Account.CreateSuccessfully"/>",
		ACCOUNT_SAVED_FAILED: "<fmt:message  bundle="${bundle}" key="Account.CreateGroupFailed"/>"
}
</script>
  <form id="validate-form-account">

  <div  class="trans-form" >
  <div class="modal fade" id="accountModal" role="dialog" style="z-index: 2050">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Account</h4>
        </div>
        <div class="modal-body">
  <div class='row'>   
                
          <div class='col-sm-6'>    
                <div class='form-group'>
                    <label class="control-label" for="account_code">Account Code</label>
		     <div class="required-field-block">
                   <input type="text" class="form-control"   maxlength="15" name="account_code" id="account_code"  placeholder="Account Code" />
		     <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
            </div>
                 <div class='col-sm-6'>    
                <div class='form-group'>
                    <label  class="control-label" for="account_name">Account Name</label>
		     <div class="required-field-block">
                   <input type="text" class="form-control"   maxlength="50" name="account_name" id="account_name" placeholder="Account Name" />
		     <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
            </div>
              <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label class="control-label" for="account_group">Account Group</label>
				                	 <div class="required-field-block">                       
				                    <input class="form-control" id="account_group" name="account_group" maxlength="50" size="30" onblur="getBillTracking();" type="text" placeholder="Type and select Account Group"/>
				                     <input  id="account_group_id" name="account_group_id" type="hidden"  size="30"  />
				                     <div class="required-icon">
								<div class="text">*</div>
							     </div>
							     </div>
				                </div>
            </div> 
            
            <div class='col-sm-6'>    
         <label class="control-label">&nbsp;</label>   
				 <div class="checkbox"> 
				 <input class="checkbox_1"  type="checkbox" id="acc_active" name="acc_active" value="1" checked /><label class="checkbox_1" for="acc_active">Active</label>
				 </div>
				</div>
				    <div id="bill_exist" style="display:none">
              <div class='col-sm-12'>    
				 <div class="checkbox"> 
				 <input class="checkbox_1" type="checkbox" id="bill_tracking" name="bill_tracking" value="1" checked  /><label class="checkbox_1" for="bill_tracking">I want to track Age of each bill</label>
				 </div>
				</div>
				</div>
				
				
            
          
             <div class='col-sm-12'>
                <div class='form-group'>
                    <label for="standard_narration">Account Description</label>
                   <textarea name="account_desc" id="account_desc"   maxlength="150" class="form-control" placeholder="Account Description" ></textarea>
                </div>
            </div>
            <div class='col-sm-12'>
                <div class='form-group'>
                    <label for="standard_narration">Standard Narration</label>
                   <textarea name="standard_narration" id="standard_narration"  maxlength="250" class="form-control" placeholder="Standard Narration" ></textarea>
                </div>
            </div>
           
            
        </div>
        </div>
        <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="saveAccountFromPage()">Save</button>	
          <button type="button" class="btn btn-danger" id="account_close" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  <input type="submit" name="validation_account_btn" id="validation_account_btn" style="display:none;"/>
  </div>
  
  
 
<script language="javascript">

$('#validate-form-account .modal').on('shown.bs.modal', function () {
	   $('#validate-form-account input:text:visible:first', this).focus();
	});
	
$('#validate-form-account').bootstrapValidator({
	//  live: 'disabled',
	message : 'This value is not valid',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		account_code: {
            validators: {
                notEmpty: {
                    message: 'This field is required'
                }
            }
        },
		account_name: {
	            validators: {
	                notEmpty: {
	                    message: 'This field is required'
	                }
	            }
	        },
	        account_group: {
		    validators: {
		        notEmpty: {
		            message: 'This field is required'
		        },
		        callback: {
		            message: 'This field is required',
		            callback: function(value, validator, $field) {
		            	if ($("#account_group_id").val()=='') {
		                    return {
		                        valid: false,
		                        message: 'This field is required'
		                    };
		                }
		            	return true;
		            }
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
			
		  $('#validate-form-account #account_group').listGroupForAccount({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroupForAccount&create_new=1",
			  nameField :'#validate-form-account #account_group',
			  idField:'#validate-form-account #account_group_id',
			    formId:'#validate-form-account',
			  validateId:'account_group' 
		  });
		  
	 });
}(window.jQuery);
</script>

</form>