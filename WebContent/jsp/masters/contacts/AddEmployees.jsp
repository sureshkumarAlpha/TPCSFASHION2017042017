<form id="addCustomer" ng-controller="savecustomerCtrl">
<div id="page-wrapper">
<div id="page-inner">
        <div class="row">
                <div class="col-md-11 col-sm-8 col-xs-8">
                    <h4 class="add-page-header">Add Employee</h4>
                </div>
                <div class="col-md-1 col-sm-4 col-xs-4 pull-right">
                        <img src="src/images/jenixerp.png" class="img-rounded" alt="Cinque Terre" width="150" height="60">						
                </div>
		</div>
        <div class='row'>
            <div class='col-sm-3 col-xs-12'>    
                <div class='form-group'>
                    <label for="customer">Employee Code</label>		   
                    <input class="form-control" id="groupName" name="groupName" size="30" type="text" placeholder="Group Name" ng-model="data.groupName"/>	
                    <input class="hide" id="groupId" name="groupId" size="30" type="text" placeholder="Group Name" ng-model="data.groupId"/>		  
                </div>
            </div>
            <div class='col-sm-3 col-xs-12'>
                <div class='form-group'>
                    <label for="sales_person">Employee Name</label>
                    <input class="form-control" id="groupCode" name="groupCode" required="required" size="30" type="text" placeholder="Group Code" ng-model="data.groupCode"/>
                </div>
            </div>            
        </div>
        <div class='row'>
            <div class='col-sm-3 col-xs-12'>    
                <div class='form-group'>
                    <label for="customer">Father's Name</label>		   
                    <input class="form-control" id="groupName" name="groupName" size="30" type="text" placeholder="Group Name" ng-model="data.groupName"/>	
                    <input class="hide" id="groupId" name="groupId" size="30" type="text" placeholder="Group Name" ng-model="data.groupId"/>		  
                </div>
            </div>
            <div class='col-sm-3 col-xs-12'>
                <div class='form-group'>
                    <label for="sales_person">Gender</label>
                    <input class="form-control" id="groupCode" name="groupCode" required="required" size="30" type="text" placeholder="Group Code" ng-model="data.groupCode"/>
                </div>
            </div>            
        </div>
        
        <div class='row'>
            <div class='col-sm-3 col-xs-12'>    
                <div class='form-group'>
                    <label for="customer">Contact Email</label>		   
                    <input class="form-control" id="groupName" name="groupName" size="30" type="text" placeholder="Group Name" ng-model="data.groupName"/>	
                    <input class="hide" id="groupId" name="groupId" size="30" type="text" placeholder="Group Name" ng-model="data.groupId"/>		  
                </div>
            </div>
            <div class='col-sm-3 col-xs-12'>
                <div class='form-group'>
                    <label for="sales_person">Contact Phone</label>
                    <input class="form-control" id="groupCode" name="groupCode" required="required" size="30" type="text" placeholder="Group Code" ng-model="data.groupCode"/>
                </div>
            </div>            
        </div>
        
             
        <div class='row'>       
            <div class='col-sm-12'>
            	
            	<div class="bs-docs-example">
				    <ul id="myTab" class="nav nav-tabs">
				      <li class="active"><a href="#personal" data-toggle="tab">PERSONAL</a></li>
				      <li><a href="#work" data-toggle="tab">WORKDETAILS</a></li>
				      <li><a href="#address" data-toggle="tab">ADDRESS</a></li>
				      <li><a href="#notes" data-toggle="tab">NOTE</a></li>
				    </ul>
				    <div id="myTabContent" class="tab-content">
				      <div class="tab-pane fade in active" id="personal">
				        
				         <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Community</label>                       
				                    <input class="form-control" id="taxregmp" name="taxregmp" size="30" type="text" placeholder="Enter Community"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">PF Nominee</label>                       
				                    <input class="form-control" id="servicetaxregno" name="servicetaxregno" size="30" type="text" placeholder="Enter PF Nominee"/>
				                </div>
				            </div>  
				        </div>
				        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Home Town</label>                       
				                    <input class="form-control" id="taxregmp" name="taxregmp" size="30" type="text" placeholder="Enter Home Town"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Relationship</label>                       
				                    <input class="form-control" id="servicetaxregno" name="servicetaxregno" size="30" type="text" placeholder="Enter Relationship"/>
				                </div>
				            </div>  
				        </div>
				        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Date Of Birth</label>                       
				                    <input class="form-control" id="taxregmp" name="taxregmp" size="30" type="text" placeholder="Enter Date Of Birth"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Group Nominee</label>                       
				                    <input class="form-control" id="servicetaxregno" name="servicetaxregno" size="30" type="text" placeholder="Enter Group Nominee"/>
				                </div>
				            </div>  
				        </div>
				         <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Date Of Joining</label>                       
				                    <input class="form-control" id="taxregmp" name="taxregmp" size="30" type="text" placeholder="Enter Date Of Joining"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Acc No</label>                       
				                    <input class="form-control" id="servicetaxregno" name="servicetaxregno" size="30" type="text" placeholder="Enter Acc No"/>
				                </div>
				            </div>  
				        </div>
				         <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Marital Status</label>                       
				                    <input class="form-control" id="taxregmp" name="taxregmp" size="30" type="text" placeholder="Enter Marital Status"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">PF No</label>                       
				                    <input class="form-control" id="servicetaxregno" name="servicetaxregno" size="30" type="text" placeholder="Enter PF No"/>
				                </div>
				            </div>  
				        </div>
				        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Edu.Qualification</label>                       
				                    <input class="form-control" id="taxregmp" name="taxregmp" size="30" type="text" placeholder="Enter Edu.Qualification"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">ESI No</label>                       
				                    <input class="form-control" id="servicetaxregno" name="servicetaxregno" size="30" type="text" placeholder="Enter ESI No"/>
				                </div>
				            </div>  
				        </div>
				        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Languages Known</label>                       
				                    <input class="form-control" id="taxregmp" name="taxregmp" size="30" type="text" placeholder="Enter Languages"/>
				                </div>
				            </div>   
				          
				        </div>
				      </div>
				      <div class="tab-pane fade" id="work">
				       
				       <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>
				                   <!--  <label for="currency">BILLING ADDRESS</label>       -->
				                    <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="spec1">Company</label>                       
								                    <input class="form-control" id="paymentTerms" name="paymentTerms" size="30" type="text" placeholder="Enter Company"/>
								                </div>  
				            				</div>
				            		 </div>            
				                     <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="spec1">Position</label>                       
								                    <input class="form-control" id="paymentTerms" name="paymentTerms" size="30" type="text" placeholder="Enter Position"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="spec1">City/Town</label>                       
								                    <input class="form-control" id="paymentTerms" name="paymentTerms" size="30" type="text" placeholder="Enter City/Town"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="spec1">Description</label>   
								                	 <textarea class="form-control" rows="3" id="salesDesc" placeholder="Enter Description"></textarea>                    
								                </div>  
				            				</div>
				            		 </div>    
				            		       
				                </div>
				            </div>   
				             
				        </div>
				       
				       
				      </div>
				      <div class="tab-pane fade" id="address">
				        
				        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>
				                   <!--  <label for="currency">BILLING ADDRESS</label>       -->
				                    <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="spec1">Street</label>                       
								                    <textarea class="form-control" rows="3" id="salesDesc" placeholder="Enter Street"></textarea>  
								                </div>  
				            				</div>
				            		 </div>            
				                     <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="spec1">City</label>                       
								                    <input class="form-control" id="paymentTerms" name="paymentTerms" size="30" type="text" placeholder="Enter City"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="spec1">State</label>                       
								                    <input class="form-control" id="paymentTerms" name="paymentTerms" size="30" type="text" placeholder="Enter State"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="spec1">Zip Code</label>   
								                	 <input class="form-control" id="paymentTerms" name="paymentTerms" size="30" type="text" placeholder="Enter Zip Code"/>                   
								                </div>  
				            				</div>
				            		 </div>    
				            		    <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="spec1">Country</label>   
								                	 <input class="form-control" id="paymentTerms" name="paymentTerms" size="30" type="text" placeholder="Enter Country"/>                   
								                </div>  
				            				</div>
				            		 </div>   
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="spec1">Fax</label>   
								                	 <input class="form-control" id="paymentTerms" name="paymentTerms" size="30" type="text" placeholder="Enter Fax"/>                   
								                </div>  
				            				</div>
				            		 </div>        
				                </div>
				            </div>   
				             
				        </div>
				        
				      </div>    
				      
				      <div class="tab-pane fade" id="notes">
				        
				        <div class='row'>
				             <div class='col-sm-12'>    
				                <div class='form-group'>
								<label for="notes">Note</label>
								<textarea class="form-control" rows="4" id="note"></textarea>
				                </div>
				            </div>
				        </div> 
				        
				      </div>
				    </div>
				</div>            	
            </div>
        </div> 
        <div class='row'>       
            <div class='col-sm-12'>
            </div>
        </div>
        <div class='row'>       
            <div class='col-sm-12'>
            </div>
        </div>
        <div class='row'>
        			<div class="col-md-10 col-sm-12 col-xs-12">                        					
                    </div>
                    <div class="col-md-2 col-sm-12 col-xs-12 pull-right">
                        <button type="submit" class="btn btn-success" ng-click="formJSON()">Save</button>	
                    	<button type="button" class="btn btn-danger">Cancel</button>									
                    </div>        	
        </div>   
   </div>
  </div> 
<input type="hidden" name="servlet_name" id="servlet_name" value="{{servletname}}"/>
<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" value="{{methodname}}"/>
<input type="hidden" name="mode" id="mode" value="{{mode}}"/>
</form>