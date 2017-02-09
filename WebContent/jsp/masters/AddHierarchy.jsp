<!-- GROUP Modal -->

<div class="form-horizontal trans-form"> 
  <div class="modal fade" id="groupModal" role="dialog" style="z-index: 2060;" >
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
       <!--  <div class="required-field-block"> -->
                         <input class="form-control" id="parent_group_name" name="parent_group_name"  maxlength="50" type="text" placeholder="Parent Group Name" value="${parent_group_name}" />
                           <input id="parent_group_id" name="parent_group_id"type="hidden" value="${parent_group_id}"/>
                            <input id="group_level" name="group_level"type="hidden" value="${group_level}"/>
                           
					    <!-- <div class="required-icon" >
						<div class="text">*</div>
		                    </div>
		                     </div> -->
      </div>
    </div>
            <div class="form-group">
            <label class="col-sm-3 control-label" for="groupTypeId">Group Type&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
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
            <label class="col-sm-3 control-label" for="display_order">Display Order&nbsp;&nbsp;&nbsp;:</label>
             <div class="col-sm-8">          
                        <input type="text"  class="form-control"  name="display_order" id="display_order"  maxlength="5" placeholder="Display Order" />
		     </div>
        </div>
        </div>
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-success" onclick="saveGroup()">Save</button>	
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
          
         <!--   <input type="hidden" id="group_id"  name="group_id"   />
          <input type="hidden" id="group_level"  name="group_level"   /> -->
           <input type="hidden" id="group_type_text"  name="group_type_text"   />
           
        </div>
      </div>
      
    </div>
  </div>
  
  <input type="submit" name="validation_btn" id="validation_btn" style="display:none;"/>
</div>


<!-- Account Modal -->
  <div  class="trans-form" >
  <div class="modal fade" id="accountModal" role="dialog">
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
                   <input type="text" class="form-control"    name="account_code"  maxlength="15" id="account_code"  placeholder="Account Code" />
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
                   <input type="text" class="form-control"    name="account_name"  maxlength="50" id="account_name" placeholder="Account Name" />
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
				                    <input class="form-control" id="acc_group" name="acc_group" maxlength="50" size="30" onblur="loadDataToAccountInGL();" type="text" placeholder="Type and select Account Group"/>
				                     <input  id="acc_group_id" name="acc_group_id" type="hidden"  size="30"  />
				                     <div class="required-icon">
								<div class="text">*</div>
							     </div>
							     </div>
				                </div>
            </div> 
         
				<div class='col-sm-6'>    
               <label class="control-label" for="acc_active">&nbsp;</label>
				 <div class="checkbox"> 
				 <input class="checkbox_1"  type="checkbox" id="acc_active" name="acc_active" value="1"   /><label class="checkbox_1" for="acc_active">Active</label>
				 </div>
				</div>
				
				<!-- <div id="bill_exist">
				
				</div> -->
				    <div id="bill_exist" style="display:none">
              <div class='col-sm-12'>    
				 <div class="checkbox"> 
				 <input class="checkbox_1" type="checkbox" id="bill_tracking" name="bill_tracking" value="1"   /><label class="checkbox_1" for="bill_tracking">I want to track Age of each bill</label>
				 </div>
				</div>
				</div>
				
				 
            <div class='col-sm-12'>
                <div class='form-group'>
                    <label for="standard_narration">Standard Narration</label>
                   <textarea name="standard_narration" id="standard_narration"    maxlength="250" class="form-control" placeholder="Standard Narration" ></textarea>
                </div>
            </div>
            <div class='col-sm-12'>
                <div class='form-group'>
                    <label for="standard_narration">Account Description</label>
                   <textarea name="account_desc" id="account_desc"   maxlength="150" class="form-control" placeholder="Account Description" ></textarea>
                </div>
            </div>
            
        </div>
        </div>
        <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="saveAccount()">Save</button>	
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  <input type="submit" name="validation_acc_btn" id="validation_acc_btn" style="display:none;"/>
  </div>
  
  
  <!-- Division Modal -->
  <div class="form-horizontal trans-form"> 
  <div class="modal fade" id="divModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Division</h4>
        </div>
        <div class="modal-body">
             <!-- The form is placed inside the body of modal -->
   <div class='row'>       
                 <div class="form-group">
                 <label class="col-sm-3 control-label"  for="group_name">Division Name :</label>
      <div class="col-sm-8">
        <div class="required-field-block">
                         <input class="form-control" id="division_name" name="division_name"  type="text" placeholder="Division Name" />
					    <div class="required-icon" >
						<div class="text">*</div>
		                    </div>
		                     </div>
      </div>
    </div>
            
        <div class="form-group">
            <label class="col-sm-3" for="display_order">Remarks :</label>
             <div class="col-sm-8">          
                        <textarea name="div_remarks" id="div_remarks"   class="form-control" placeholder="Remarks" ></textarea>
		     </div>
        </div>
        </div>
       
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-success" onclick="saveDivision()">Save</button>	
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            <input type="submit" name="validation_div_btn" id="validation_div_btn" style="display:none;"/>
        </div>
           
      </div>
      
    </div>

  </div>

</div>


 <!-- Cost Center Modal -->
  <div class="form-horizontal trans-form"> 
  <div class="modal fade" id="cstCntrModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Cost Center</h4>
        </div>
        <div class="modal-body">
   <div class='row'>       
                 <div class="form-group">
                 <label class="col-sm-4 control-label"  for="group_name">Cost Center Name :</label>
      <div class="col-sm-8">
        <div class="required-field-block">
                         <input class="form-control" id="costcenter_name" name="costcenter_name"   type="text" placeholder="Cost Center Name" />
					    <div class="required-icon" >
						<div class="text">*</div>
		                    </div>
		                     </div>
      </div>
    </div>
            
         <div class="form-group">
                 <label class="col-sm-4 control-label"  for="group_name">Cost Center Code :</label>
      <div class="col-sm-8">
        <div class="required-field-block">
                         <input class="form-control" id="costcenter_code" name="costcenter_code"  type="text" placeholder="Cost Center Code" />
					    <div class="required-icon" >
						<div class="text">*</div>
		                    </div>
		                     </div>
      </div>
    </div>
        </div>
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-success" onclick="saveCostCntr()">Save</button>	
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
           
        </div>
      </div>
      
    </div>
  </div>
  
  <input type="submit" name="validation_cst_btn" id="validation_cst_btn" style="display:none;"/>
  <script>
 		$('.modal').on('shown.bs.modal', function () {
 	   $('input:text:visible:first', this).focus();
 	});		
</script>			
</div>