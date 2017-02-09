  <!-- --------------------------CUSTOMER MASTER------------------------------ -->
  <form id="validate-form-customer"  action=""  method="post"  role="form" autocomplete="off">
  


  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/panel.css" />
  <div class="trans-form"> 
  <div class="modal fade" id="customerMasterModal" role="dialog">
    <div class="modal-dialog modal-lg" >
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add An Associate</h4>
        </div>
        <div class="modal-body" style="height:490px;">
   <!-- <div class='row'>      -->  
        <div class='row'>
        
         <div class='col-sm-4'>
                <div class='form-group'>
                    <label class="control-label" for="company_name">Company Name</label>  
                    <div class="required-field-block">	                  
                    <input class="form-control" id="company_name" name="company_name" size="30" type="text" maxlength="100" placeholder="Enter Company Name"/>                   
               <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
            </div>
            
            <div class='col-sm-4'>    
                <div class='form-group'>
                    <label class="control-label" for="customer_code">Code</label>	
                     <div class="required-field-block">	   
                    <input class="form-control" id="customer_code" name="customer_code" size="30" type="text" maxlength="25"   placeholder="Enter Code"/>
                    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
            </div>
           
            <div class='col-sm-4'>
                <div class='form-group'>                	
                    <label  for="display_name">Display Name</label>
                    <div class="required-field-block">
                    <input class="form-control" id="display_name" name="display_name" size="30" type="text" maxlength="60"  placeholder="Enter Display Name"/>                  
                	</div>
            	</div>
            </div>	    	
        </div>	
        <div class='row'>             
            <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="contact_salutation">Primary Contact</label>                  
                    <select class="form-control" id="contact_salutation" name="contact_salutation">
	                    <option value="0">Salutation</option>
					    <option value="Mr"  >Mr.</option>
					    <option value="Mrs" >Mrs.</option>
					    <option value="Ms" >Ms.</option>
					    <option value="Miss" >Miss.</option>
                    </select>                   
                </div>
            </div>   
            <div class='col-sm-4'>
                <div class='form-group'>   
                	<label for="first_name">First Name</label>                       
                    <input class="form-control" id="first_name" name="first_name"  maxlength="50" size="30" type="text" placeholder="Enter First Name"/>
                </div>
            </div>   
            <div class='col-sm-4'>
                <div class='form-group'>   
                	<label for="last_name">Last Name</label>                    
                    <input class="form-control" id="last_name" name="last_name" maxlength="100"  size="30" type="text" placeholder="Enter Last Name"/>                   
                </div>
            </div>           
        </div>
        <div class='row'>             
            <div class='col-sm-4'>
                <div class='form-group'>
                    <label class="control-label" for="email">Contact Email</label>
                    <input class="form-control" id="email" name="email"  size="30" type="text" maxlength="100" placeholder="Enter Contact Email"/>
                </div>
            </div>
            <div class='col-sm-4'>
                <div class='form-group'>
                    <label class="control-label" for="phone">Contact Phone</label>
                    <input class="form-control" id="phone" name="phone" size="30" type="text" maxlength="15" placeholder="Enter Contact Phone"/>
                </div>
            </div>
	    	<div class='col-sm-4'>
                <div class='form-group'>
                    <label for="contact_desg">Contact Designation</label>
                    <input class="form-control" id="contact_desg" name="contact_desg" size="30" type="text"  maxlength="50" placeholder="Enter Contact Designation"/>
                </div>
            </div>         
        </div>
        <div class='row'>      
        
        <div class='col-sm-2'>
                <div class='form-group'>
                    <label >Contact Type</label>
                     <div class="radio">  <input class="radio_1" type="radio" value="Customer"  checked  id="party_tag1" name="party_tag"  /><label class="radio_1" for="party_tag1">&nbsp;Customer</label></div>
         		</div>
         </div>
                     
                     <div class='col-sm-2'>
                <div class='form-group'>
                    <label >&nbsp;</label>
                    <div class="radio"><input class="radio_1"  type="radio" value="Vendor"  id="party_tag2" name="party_tag"  /><label class="radio_1" for="party_tag2">&nbsp;Vendor</label></div>
                    </div>
                    </div>
                    
                    <div class='col-sm-2'>
                <div class='form-group'>
                    <label >&nbsp;</label>
                    <div class="radio"><input class="radio_1"  type="radio" value="Both"  id="party_tag3" name="party_tag"  /><label class="radio_1" for="party_tag3">&nbsp;Both</label></div>
                    </div>
                    </div>
                    
            <!-- <div class='col-sm-8'>
                <div class='form-group'>
                    <label for="party_tag">Contact Type</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <label class="radio-inline"><input type="radio" value="Customer"  checked  id="party_tag" name="party_tag"  />&nbsp;&nbsp;Customer</label>
                    <label class="radio-inline"><input type="radio" value="Vendor"  id="party_tag" name="party_tag"   />&nbsp;&nbsp;Vendor</label>
                    <label class="radio-inline"><input type="radio" value="Both"  id="party_tag" name="party_tag"   />&nbsp;&nbsp;Both</label>
                </div>
            </div> -->
            
            <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="status">Status</label>
                    <select class="form-control" id="status" name="status">
					                    <option value="1" >Active</option>
									    <option value="0" >Inactive</option>
				                    </select>  
                </div>
            </div>
        </div>       
        
        
        <div class="row">
    	
        <div class="col-md-12">
            <div class="panel with-nav-tabs panel-primary">
                <div class="panel-heading">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab1primary" data-toggle="tab">PAYMENT DETAILS</a></li>
                            <li><a href="#tab2primary" data-toggle="tab">ADDRESS</a></li>
                            <li><a href="#tab3primary" data-toggle="tab">TAX SETTING</a></li>
                            <li><a href="#tab4primary" data-toggle="tab">ACCOUNTS</a></li>
                            <li><a href="#tab5primary" data-toggle="tab">NOTE</a></li>
                          
                        </ul>
                </div>
                <div class="panel-body">
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="tab1primary">
                        <div class='row'>             
				            <div class='col-sm-4'>
				                <div class='form-group'>
				                    <label for="currency">Currency</label>      
				                     <div class="required-field-block">       
				                     
				                          <input class="form-control" id="cust_currency" name="cust_currency" maxlength="50" size="30" type="text" placeholder="Type and select Currency"/>
				                     <input  id="cust_currency_id" name="cust_currency_id" type="hidden"  size="30"  />
				                          
				                <!--    <select name="currency" id="currency" class="form-control" >
						<option value="-1">&lt;--Select Currency--&gt;</option>
					</select>   -->
					<div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
				                </div>
				            </div>   
				            <div class='col-sm-4'>
				                <div class='form-group'>   
				                	<label for="payment_terms">Payment Terms</label>                       
				                    <input class="form-control" id="payment_terms" name="payment_terms" maxlength="200" size="30" type="text" placeholder="Enter Payment Terms"/>
				                </div>
				            </div>  
				            <div class='col-sm-4'>
				                <div class='form-group'>   
				                	<label class="control-label" for="credit_days">Credit (Days)</label>                       
				                    <input class="form-control" id="credit_days" name="credit_days" size="30" type="text"  placeholder="Enter Credit (Days)"/>
				                </div>
				            </div>  
				        </div>
                        </div>
                        <div class="tab-pane fade" id="tab2primary">
                        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>
				                    <label for="currency">BILLING ADDRESS</label>      
				                    <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="bill_street">Street</label>     
								                	 <input class="form-control" id="bill_street" name="bill_street" maxlength="100" size="30" type="text" placeholder="Enter Street"/>                  
								                </div>  
				            				</div>
				            		 </div>  
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="bill_location">Location</label>                       
								                    <input class="form-control" id="bill_location" name="bill_location" maxlength="100" size="30" type="text"  placeholder="Enter Location"/>
								                </div>  
				            				</div>
				            		 </div>           
				                     <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="bill_city">City</label>                       
								                    <input class="form-control" id="bill_city" name="bill_city" maxlength="50" size="30" type="text" placeholder="Enter City"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="bill_state">State</label>                       
								                    <input class="form-control" id="bill_state" name="bill_state" maxlength="50" size="30" type="text"  placeholder="Enter State"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="bill_zip">Zip Code</label>                       
								                    <input class="form-control" id="bill_zip" name="bill_zip" size="30" maxlength="10" type="text"  placeholder="Enter Zip Code"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="bill_country">Country</label>                       
								                    <input class="form-control" id="bill_country" name="bill_country" maxlength="50" size="30" type="text"  placeholder="Enter Country"/>
								                </div>  
				            				</div>
				            		 </div> 
				            		  <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label class="control-label" for="company_phone">Company Phone</label>                       
								                    <input class="form-control" id="company_phone" name="company_phone" maxlength="15" size="30" type="text"  placeholder="Enter Company Phone"/>
								                </div>  
				            				</div>
				            		 </div>
				            		  <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label class="control-label" for="company_email">Company Email</label>                       
								                    <input class="form-control" id="company_email" name="company_email" size="30" maxlength="100" type="text"  placeholder="Enter Company Email"/>
								                </div>  
				            				</div>
				            		 </div>
				            		  <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="company_fax">Company Fax</label>                       
								                    <input class="form-control" id="company_fax" name="company_fax" maxlength="25" size="30" type="text"  placeholder="Enter Company Fax"/>
								                </div>  
				            				</div>
				            		 </div>   
				            		                  
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                <label for="currency">SHIPPING ADDRESS</label> 
				                	<a href="javascript:copyBillingAddress();"><b style="margin-right: 20px;"><span class="glyphicon glyphicon-arrow-down"></span>&nbsp;Copy Billing Address</b></a>   
					                	<div class='row'>    
					                	<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="ship_street">Street</label>     
								                	 <input class="form-control" id="ship_street" name="ship_street" maxlength="100" size="30" type="text"  placeholder="Enter Street"/>                  
								                </div>  
				            				</div>
				            		 </div>  
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="ship_location">Location</label>                       
								                    <input class="form-control" id="ship_location" name="ship_location" maxlength="100" size="30" type="text"  placeholder="Enter Location"/>
								                </div>  
				            				</div>
				            		 </div>          
				                          <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="ship_city">City</label>                       
								                    <input class="form-control" id="ship_city" name="ship_city" maxlength="50" size="30" type="text"  placeholder="Enter City"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="ship_state">State</label>                       
								                    <input class="form-control" id="ship_state" name="ship_state" maxlength="50" size="30" type="text"  placeholder="Enter State"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="ship_zip">Zip Code</label>                       
								                    <input class="form-control" id="ship_zip" name="ship_zip" maxlength="10" size="30" type="text"  placeholder="Enter Zip Code"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="ship_country">Country</label>                       
								                    <input class="form-control" id="ship_country" name="ship_country" maxlength="50" size="30" type="text" placeholder="Enter Country"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label class="control-label" for="ship_phone">Phone</label>                       
								                    <input class="form-control" id="ship_phone" name="ship_phone" maxlength="15" size="30" type="text"  placeholder="Enter Phone"/>
								                </div>  
				            				</div>
				            		 </div>     
				                </div>
				            </div>  
				        </div>
                        </div>
                        <div class="tab-pane fade" id="tab3primary">
                        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label class="control-label" for="vat">VAT Reg.No</label>                       
				                    <input class="form-control" id="vat" name="vat" maxlength="25" size="30" type="text"  placeholder="Enter VAT Reg.No"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label class="control-label" for="service_no">Service Tax Reg.No</label>                       
				                    <input class="form-control" id="service_no" name="service_no" maxlength="25" size="30" type="text"  placeholder="Enter Service Tax Reg.No"/>
				                </div>
				            </div>  
				        </div>
				        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label class="control-label" for="pan_no">Pan No</label>                       
				                    <input class="form-control" id="pan_no" name="pan_no" maxlength="25" size="30" type="text"  placeholder="Enter Pan No"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label class="control-label" for="insurance">Insurance</label>                       
				                    <input class="form-control" id="insurance" name="insurance" maxlength="100" size="30" type="text" placeholder="Enter Insurance"/>
				                </div>
				            </div>  
				        </div>
                        </div>
                        <div class="tab-pane fade" id="tab4primary">
                        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label class="control-label" for="cust_account_group">Account Group</label>
				                	 <div class="required-field-block">                       
				                    <input class="form-control" id="cust_account_group" name="cust_account_group" maxlength="50" size="30" type="text" placeholder="Type and select Account Group"/>
				                     <input  id="cust_account_group_id" name="cust_account_group_id" type="hidden"  size="30"  />
				                     <div class="required-icon">
								<div class="text">*</div>
							     </div>
							     </div>
				                </div>
				            </div>  
				       <div class='col-sm-6'>				               
				                	<div class='form-group'>			
				                	    <label for="bill_tracking">&nbsp;</label><br/>             
					                    <div class="checkbox"><input class="checkbox_1" type="checkbox" id="bill_tracking"  checked name="bill_tracking" value="1" name="bill_tracking"><label class="checkbox_1" for="bill_tracking">&nbsp;I want to track Age of each bill</label></div>
				                </div>
				            </div>
				        </div>
                        </div>
                        <div class="tab-pane fade" id="tab5primary">
                        <div class='row'>
				             <div class='col-sm-12'>    
				                <div class='form-group'>
								<label for="notes">Note</label>
								<textarea class="form-control" rows="4" id="notes" name="notes" maxlength="500" Placeholder="Notes"></textarea>
				                </div>
				            </div>
				        </div> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
	<!-- </div> -->
   
        </div>
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-success" onclick="saveCustomerFromPage()">Save</button>	
          <button type="button" class="btn btn-danger" id="close_btn"  data-dismiss="modal">Close</button>
           <input id="customer_mode" name="customer_mode" type="hidden" value="n"/>
           <input type="hidden" name="customer_id" id="customer_id" />
          <input type="submit" name="validation_customer_btn" id="validation_customer_btn" style="display:none;"/>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
 <!-- <style>
  @media (min-width:768px){.modal-dialog{width: 1000px;margin:30px auto}.modal-content{-webkit-box-shadow:0 5px 15px rgba(0,0,0,.5);box-shadow:0 5px 15px rgba(0,0,0,.5)}.modal-sm{width:300px}}
  </style> -->
 	 <script type="text/javascript">

 	$('#validate-form-customer .modal').on('shown.bs.modal', function () {
  	   $('#validate-form-customer input:text:visible:first', this).focus();
  	});
 	

$('#validate-form-customer').bootstrapValidator({
	//  live: 'disabled',
	  message: 'This value is not valid',
	  feedbackIcons: {
	      valid: 'glyphicon glyphicon-ok',
	      invalid: 'glyphicon glyphicon-remove',
	      validating: 'glyphicon glyphicon-refresh'
	  },
	  excluded: ':disabled',
	  fields: {
		  
		  email: {
              validators: {
                  emailAddress: {
                      message: 'The value is not a valid email address'
                  },
                  regexp: {
                      regexp: '^[^@\\s]+@([^@\\s]+\\.)+[^@\\s]+$',
                      message: 'The value is not a valid email address'
                  }
              }
          },
          company_email: {
              validators: {
                  emailAddress: {
                      message: 'The value is not a valid email address'
                  },
                  regexp: {
                      regexp: '^[^@\\s]+@([^@\\s]+\\.)+[^@\\s]+$',
                      message: 'The value is not a valid email address'
                  }
              }
          },  
          phone: {
              validators: {
                  digits: {
                      message: 'The phone number is not valid'
                  }
              }
          },
          company_phone: {
              validators: {
                  digits: {
                      message: 'The phone number is not valid'
                  }
              }
          },
          ship_phone: {
              validators: {
                  digits: {
                      message: 'The phone number is not valid'
                  }
              }
          },
          vat: {
              validators: {
                  numeric: {
                      message: 'This field must be a number'
                  }
              }
          },
          service_no: {
              validators: {
                  numeric: {
                      message: 'This field must be a number'
                  }
              }
          },
          pan_no: {
              validators: {
                  numeric: {
                      message: 'This field must be a number'
                  }
              }
          },
          insurance: {
              validators: {
                  numeric: {
                      message: 'This field must be a number'
                  }
              }
          },
          credit_days: {
              validators: {
                  numeric: {
                      message: 'This field must be a number'
                  }
              }
          },
		  customer_code: {
              validators: {
                  notEmpty: {
                      message: 'This field is required'
                  }
              }
          },
          company_name: {
            validators: {
                notEmpty: {
                    message: 'This field is required'
                }
            }
        },
       /*  currency: {
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
        
        cust_currency: {
            validators: {
                notEmpty: {
                    message: 'This field is required'
                },
                callback: {
                    message: 'This field is required',
                    callback: function(value, validator, $field) {
                    	if ($("#cust_currency_id").val()=='') {
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
        cust_account_group: {
            validators: {
                notEmpty: {
                    message: 'This field is required'
                },
                callback: {
                    message: 'This field is required',
                    callback: function(value, validator, $field) {
                    	if ($("#cust_account_group_id").val()=='') {
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

 <script>		  
 !function ($) {
	 
		$(function(){
		 $('#validate-form-customer #cust_account_group').listGroupForAccount({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroupForAccount&create_new=1",
			  nameField :'#validate-form-customer #cust_account_group',
			  idField:'#validate-form-customer #cust_account_group_id',
			  formId:'#validate-form-customer',
			  validateId:'cust_account_group'
		  });
		 
		 $('#validate-form-customer #cust_currency').listCurrency({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCurrency&create_new=1",
			  nameField :'#validate-form-customer #cust_currency',
			  idField:'#validate-form-customer #cust_currency_id',
			  formId:'#validate-form-customer',
			  validateId:'cust_currency'
		  });
		 
		 });
}(window.jQuery);	
</script> 
</form>