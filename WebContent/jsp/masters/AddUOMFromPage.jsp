  <!-- --------------------------UOM MASTER------------------------------ -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
  <script language="javascript">
var uomlocalizedStrings = {
		UOM_ALREADY_EXISTS: "<fmt:message  bundle="${bundle}" key="UOM.AlreadyExixts"/>",
		UOM_SAVED: "<fmt:message  bundle="${bundle}" key="UOM.CreateSuccessfully"/>",
		UOM_SAVED_FAILED: "<fmt:message  bundle="${bundle}" key="UOM.CreateUOMFailed"/>"
}
</script>
  <form id="validate-form-uom" autocomplete="off">
    

  <div class="form-horizontal trans-form"> 
  <div class="modal fade" id="uomMasterModal" role="dialog" style="z-index:2040">
    <div class="modal-dialog modal-sm">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add SKU</h4>
        </div>
        <div class="modal-body">
   <div class='row'>       
           <div class="form-group">
            <label class="col-sm-4 control-label" for="uom_name">SKU :</label>
             <div class="col-sm-8">          
            <div class="required-field-block">
                    <input class="form-control" id="uom_name" name="uom_name"  type="text" maxlength="50" placeholder="Enter SKU"  />
		    <div class="required-icon" >
			<div class="text">*</div>
		     </div>
		     </div>
		     </div>
        </div>    
              
       
        </div>
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-success" onclick="saveUOMFromPage()">Save</button>	
          <button type="button" class="btn btn-danger" id="uom_close"  data-dismiss="modal">Close</button>
          <input type="submit" name="validation_uom_btn" id="validation_uom_btn" style="display:none;"/>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
	<script language="javascript">
	
	$('#validate-form-uom .modal').on('shown.bs.modal', function () {
	 	   $('#validate-form-uom input:text:visible:first', this).focus();
	 	});
	
		$('#validate-form-uom').bootstrapValidator({
			//  live: 'disabled',
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				uom_name : {
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