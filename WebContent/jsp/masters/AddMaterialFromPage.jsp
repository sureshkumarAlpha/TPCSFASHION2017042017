
<!-- Material Modal -->
<form id="validate-form-mat" autocomplete="off">

 
  <div class="modal fade" id="materialModal" role="dialog">
    <div class="modal-dialog modal-lg" >
    
      <!-- Modal content-->
      <div class="modal-content" >
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Material</h4>
        </div>
        <div class="modal-body" style="height:450px;overflow-y:auto;">
  
  <div class='row'>   
          
                 <div class='col-sm-4'>    
                <div class='form-group'>
                    <label class="control-label" for="material_code">Material Code</label>
		     <div class="required-field-block">
                   <input type="text" class="form-control"    name="material_code" id="material_code" maxlength="10" placeholder="Material Code" />
		     <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
          </div>

				

				<div class='col-sm-4'>    
                <div class='form-group'>
                    <label class="control-label" for="material_name">Material Name</label>
		     <div class="required-field-block">
                  <input type="text" class="form-control" name="material_name" id="material_name" maxlength="50" placeholder="Material Name"  />
		     <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
            </div>
            
            <div class='col-sm-4'>
					<div class='form-group'>
						<label>Status</label> <select class="form-control"
							id="material_status" name="material_status">
							<option value="1" selected >Active</option>
							<option value="0" >Inactive</option>
						</select>
					</div>
				</div>
</div>            
				
            <!-- <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="spec1">Spec1</label>
                       <input type="text" class="form-control" name="spec1" id="spec1"  placeholder="Spec1"  />
                </div>
            </div>
            <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="spec2">Spec2</label>
                        <input type="text" class="form-control" name="spec2" id="spec2"   placeholder="Spec2" />
                </div>
            </div>
           <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="spec3">Spec3</label>
                        <input type="text" class="form-control" name="spec3" id="spec3"  placeholder="Spec3"  />
                </div>
            </div>
                <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="spec4">Spec4</label>
                        <input type="text" class="form-control" name="spec4" id="spec4"    placeholder="Spec4" />
                </div>
            </div>
                <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="spec5">Spec5</label>
                         <input type="text" class="form-control" name="spec5" id="spec5"  placeholder="spec5" />
                </div>
            </div>
      
            <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="spec6">Spec6</label>
                      <input type="text" class="form-control" name="spec6" id="spec6"  placeholder="spec6"/>
                </div>
            </div>
             <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="spec7">Spec7</label>
                      <input type="text" class="form-control" name="spec7" id="spec7" placeholder="spec7" />
                </div>
            </div> -->
  <div class='row'>              
                      <div class='col-sm-4'>
                <div class='form-group'>
                    <label class="control-label" for="basic_unit">SKU</label>
                    <div class="required-field-block">
                    <input type="text" class="form-control" name="basic_unit" id="basic_unit"  maxlength="10" placeholder="SKU" />
                      <input type="hidden" name="basic_unit_id" id="basic_unit_id"  />
		     <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
            </div>
            
            <div class='col-sm-4'>    
                <div class='form-group'>
                    <label class="control-label" for="sub_group">Group</label>
		     <div class="required-field-block">
		      <input type="text" class="form-control"    name="sub_group" id="sub_group"  maxlength="50"  autofocus placeholder="Type and select Group" />
                   <input type="hidden" class="form-control"    name="sub_group_id" id="sub_group_id"    />
		     <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
            </div>
            
            </div>
              <div class='row'>
            
            <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="stock_location">Stock Location</label>
                     <input type="text" class="form-control" name="stock_location" id="stock_location" maxlength="30"  placeholder="Stock Location" />
                </div>
            </div>
            
            <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="bin_no">Shelf/Bin No</label>
                      <input type="text" class="form-control" name="bin_no" id="bin_no" maxlength="25" placeholder="Shelf/Bin No"  />
                </div>
            </div>
            </div>
            
            <div class="row  " style="margin-top: 30px;">                
			<div class='col-sm-12'  >
			<div class='form-group'>
			<h5><label>Track quantity on hand</label></h5>
			</div>
			</div>
			</div>
			
            <div class="row  ">                
				<div class='col-sm-12'  >
				<div class='form-group'>
				<div class="checkbox"> 
								  <input class="checkbox_1" type="checkbox" id="track_inventory" name="track_inventory" checked="checked"  value="1" onclick="showMatInventoryDet()" /><label for="track_inventory" class="checkbox_1">Yes I want to track quantity and manage inventory for this material</label>
								 </div>
				</div>
				</div>
				

				</div>
				
				<div class="row  " id="track_inventory_det"    >

<div class='col-sm-4'  >
<div class='form-group'>
	<label>Inventory Account</label>
			<input class="form-control"   type="text" name="inventory_account" id="inventory_account"  maxlength="30" placeholder="Type and select Inventory Account"   />
			<input type="hidden" name="inventory_account_id" id="inventory_account_id"    />

</div>
</div>
</div>


<div class="row  " style="margin-top: 30px;">                
<div class='col-sm-12'  >
<div class='form-group'>
<h5><label>Sales Information</label></h5>
</div>
</div>
</div>

<div class="row  ">                
<div class='col-sm-12'  >
<div class='form-group'>
<div class="checkbox"> 
				 <input class="checkbox_1" type="checkbox" id="sell_material" name="sell_material"  value="1" checked="checked" onclick="showMatSellDet()"/><label for="sell_material" class="checkbox_1">Yes, I want to sell this material</label>
				 </div>
</div>
</div>


</div>

<div class="row  " id="sell_det"   >
<div class="row row-no-margin">
<div class='col-sm-4'  >
<div class='form-group'>
  	<label>Description for selling purpose</label>
			<textarea class="form-control" rows="6"  type="text" name="sales_desc" id="sales_desc" maxlength="500" placeholder="Enter Sales Description"  > </textarea>
</div>
</div>

<div class='col-sm-4'  >
<div class='form-group'>
  	<label>Selling Price / Unit </label>
			<input class="form-control"   type="text" name="selling_price" id="selling_price"  placeholder="Enter Selling Price/Unit"  />
</div>
</div>
</div>


<div class="row row-no-margin ">
<div class='col-sm-4'  >
<div class='form-group'>
<label>&nbsp;</label>
<div class="checkbox"> 
<!-- onclick="showMaterialTaxGroup()" -->
				 <input class="checkbox_1" type="checkbox" id="include_tax" name="include_tax"   value="1"  /><label for="include_tax" class="checkbox_1">Price includes tax</label>
				 </div>
</div>
</div>

<%-- <div class='col-sm-4' id="tax_group_det"   >
<div class='form-group'>
	<label>Tax Group</label>
			<select class="form-control" id="tax_group" name="tax_group" >
			<option value="${taxGroup.taxGroupId }">${taxGroup.taxGroup}</option>
			</select>

</div>
</div> --%>

</div>
</div>


<div class="row  " style="    margin-top: 30px;">                
<div class='col-sm-4'  >
<div class='form-group'>
<h5><label><b>Purchase Information</b></label></h5>
</div>
</div>
</div>

<div class="row  ">                
<div class='col-sm-4'  >
<div class='form-group'>
<div class="checkbox"> 
				 <input class="checkbox_1" type="checkbox" id="purchase_material" name="purchase_material"  value="1" checked="checked" onclick="showMatPurchaseDet()"/><label for="purchase_material" class="checkbox_1">Yes, I want to purchase this item</label>
				 </div>
</div>
</div>


</div>

<div class="row  " id="purchase_det"   >
<div class="row row-no-margin">
<div class='col-sm-4'  >
<div class='form-group'>
  	<label>Description for purchase purpose</label>
			<textarea class="form-control" rows="6"  type="text" name="purchase_desc" id="purchase_desc" maxlength="500" placeholder="Enter Purchase Description"  > </textarea>
</div>
</div>

<div class='col-sm-4'  >
<div class='form-group'>
	<label>Purchase Account</label>
			<input class="form-control"   type="text" name="mat_purchase_account" id="mat_purchase_account"  maxlength="30" placeholder="Type and select Purchase Account"    />
			<input type="hidden" name="mat_purchase_account_id" id="mat_purchase_account_id"    />

</div>
</div>
</div>

<div class="row  row-no-margin">

<div class='col-sm-4'  >
<div class='form-group'>
	<label>Cost / Unit</label>
			<input class="form-control"   type="text" name="cost_price" id="cost_price"   placeholder="Enter Cost / Unit"    />

</div>
</div>
</div> 
</div>

<div class="row  " id="reorder_level_det"    >
<div class='col-sm-4'  >
<div class='form-group'>
	<label>Re-order level</label>
			<input class="form-control"   type="text" name="reorder_level" id="reorder_level"  maxlength="30" placeholder="Enter Re-order level"   />

</div>
</div> 
</div>
            
            
         <!--    <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="reorder_level">Recorder level</label>
                     <input type="text" class="form-control" name="reorder_level" id="reorder_level"  placeholder="Recorder level"/>
                </div>
            </div>
                <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="excess_allowance">Excess allowance for purchase</label>
                    <input type="text" class="form-control" name="excess_allowance" id="excess_allowance" placeholder="Excess allowance for purchase" />
                </div>
            </div>
                <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="selling_price">Selling Price</label>
                    <input type="text" class="form-control" name="selling_price" id="selling_price" placeholder="Selling Price"/>
                </div>
            </div>
             <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="standard_rate">Standard Rate</label>
                    <input type="text" class="form-control" name="standard_rate" id="standard_rate"  placeholder="Standard Rate" />
                </div>
            </div>
            <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="purchase_desc">Purchase Description</label>
                  <textarea class="form-control" type="text" name="purchase_desc" id="purchase_desc" maxlength="500" placeholder="Purchase Description" ></textarea>
                </div>
            </div>
               <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="sales_desc">Sales Description</label>
                  <textarea class="form-control" type="text" name="sales_desc" id="sales_desc" maxlength="500" placeholder="Purchase Description" ></textarea>
                </div>
            </div> -->
        </div>
        
        <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="saveMaterialFromPage()">Save</button>	
          <button type="button" class="btn btn-danger" id="mat_close" data-dismiss="modal">Close</button>
          <input type="submit" name="validation_mat_btn" id="validation_mat_btn" style="display:none;"/>
        </div>
        </div>
      </div>
      
    </div>
  </div>
  
<!--    <style>
  @media (min-width:768px){.modal-dialog{width: 1000px;margin:30px auto}.modal-content{-webkit-box-shadow:0 5px 15px rgba(0,0,0,.5);box-shadow:0 5px 15px rgba(0,0,0,.5)}.modal-sm{width:300px}}
  </style> -->
 
  
 	 <script language="javascript">
 	 
 	$('#validate-form-mat .modal').on('shown.bs.modal', function () {
  	   $('#validate-form-mat input:text:visible:first', this).focus();
  	});
 	
 	$('#validate-form-mat').bootstrapValidator({
		//  live: 'disabled',
		  message: 'This value is not valid',
		  feedbackIcons: {
		      valid: 'glyphicon glyphicon-ok',
		      invalid: 'glyphicon glyphicon-remove',
		      validating: 'glyphicon glyphicon-refresh'
		  },
		  fields: {
			  sub_group: {
		            validators: {
		                notEmpty: {
		                    message: 'This field is required'
		                },
		                callback: {
		                    message: 'This field is required',
		                    callback: function(value, validator, $field) {
		                    	if (jQuery("#sub_group_id").val()=='' || jQuery("#sub_group_id").val()==0) {
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
			    material_code: {
			        validators: {
			            notEmpty: {
			                message: 'This field is required'
			            }
			        }
			    },
			    material_name: {
			        validators: {
			            notEmpty: {
			                message: 'This field is required'
			            }
			        }
			    },
			    basic_unit: {
			        validators: {
			            notEmpty: {
			                message: 'This field is required'
			            }
			    		,
			    		callback: {
                    		message: 'This field is required',
                    		callback: function(value, validator, $field) {
                    			if ($("#basic_unit_id").val()=='' || $("#basic_unit_id").val()==0) {
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
 	
 	 
 	
	  
	!function ($) {

	 
	$(function(){	  
		  $('#validate-form-mat #sub_group').listGroup({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroup&create_new=1",
			  nameField :'#validate-form-mat #sub_group',
			  idField:'#validate-form-mat #sub_group_id',
			  formId:'#validate-form-mat',
			  validateId:'sub_group'
		  });
		  
		  $('#validate-form-mat #basic_unit').listUOM({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetUOM&create_new=1",
			  nameField :'#validate-form-mat #basic_unit',
			  idField:'#validate-form-mat #basic_unit_id',
			  formId:'#validate-form-mat',
			  validateId:'basic_unit'
		  });
		  $(function(){
			  $('#validate-form-mat #inventory_account').listInventoryAccount({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetInventoryAccount&create_new=1",
				  nameField :'#validate-form-mat #inventory_account',
				  idField:'#validate-form-mat #inventory_account_id',
				  //formId:'#validate-form'
				  //validateId:'group'
			  });			 
		 });
		 
		 $(function(){
			  $('#validate-form-mat #mat_purchase_account').listPurchaseAccount({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetPurchaseAccount&create_new=1",
				  nameField :'#validate-form-mat #mat_purchase_account',
				  idField:'#validate-form-mat #mat_purchase_account_id',
				  formId:'#validate-form-mat'
				  //validateId:'group'
			  });			 
		 });
	 });
}(window.jQuery);	
</script>
</form>