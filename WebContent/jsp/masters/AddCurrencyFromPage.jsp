  <!-- --------------------------UOM MASTER------------------------------ -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
  <script language="javascript">
var currencylocalizedStrings = {
		CURRENCY_ALREADY_EXISTS: "<fmt:message  bundle="${bundle}" key="CURRENCY.AlreadyExixts"/>",
		CURRENCY_SAVED: "<fmt:message  bundle="${bundle}" key="CURRENCY.CreateSuccessfully"/>",
		CURRENCY_SAVED_FAILED: "<fmt:message  bundle="${bundle}" key="CURRENCY.CreateCurrencyFailed"/>"
}
</script>
  <form id="validate-form-currency" autocomplete="off">
    

  <div class="form-horizontal trans-form"> 
  <div class="modal fade" id="currencyMasterModal" role="dialog" style="z-index:2040">
    <div class="modal-dialog modal-sm">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Currency</h4>
        </div>
        <div class="modal-body">
   <div class='row'>       
           <div class="form-group">
            <label class="col-sm-4 control-label" for="new_currency_name">Currency</label>
             <div class="col-sm-8">          
            <div class="required-field-block">
                    <input class="form-control" id="new_currency_name" name="new_currency_name"  type="text" maxlength="25" placeholder="Enter Currency"  />
		    <div class="required-icon" >
			<div class="text">*</div>
		     </div>
		     </div>
		     </div>
        </div>    
              
       
        </div>
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-success" onclick="saveCurrencyFromPage()">Save</button>	
          <button type="button" class="btn btn-danger" id="currency_close"  data-dismiss="modal">Close</button>
          <input type="submit" name="validation_currency_btn" id="validation_currency_btn" style="display:none;"/>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
	<script language="javascript">
	
	$('#validate-form-currency .modal').on('shown.bs.modal', function () {
	 	   $('#validate-form-currency input:text:visible:first', this).focus();
	 	});
	
		$('#validate-form-currency').bootstrapValidator({
			//  live: 'disabled',
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				new_currency_name : {
					validators : {
						notEmpty : {
							message : 'This field is required'
						}
					}
				}
			}
		});
	</script>
</form>