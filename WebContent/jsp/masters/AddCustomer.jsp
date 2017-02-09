
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title><fmt:message bundle="${bundle}" key="Title.Title" /></title>

<jsp:include page="../common/Header.jsp" />
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/masters/masters.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/panel.css" />

<!-- <style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}

</style> -->
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form action="" id="validate-form" method="post" role="form" autocomplete="off">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>

<div class="top-bar">
 <div class="row">
                <div class="col-md-11 col-sm-8 col-xs-12">
                    <h2 class="add-page-header" >Associate</h2>
                </div>
           </div>
<div id="page-wrapper" class="add-top-wrapper"> 
   <div id="page-inner">   
   
        <div class='row'>
        
          <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="company_name">Company Name</label>  
                    <div class="required-field-block">	                  
                    <input class="form-control" id="company_name" name="company_name"  maxlength="100"  size="30" type="text" value="${cust_info.partyName}" placeholder="Enter Company Name"/>                   
               <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
            </div>
            
            <div class='col-sm-3'>    
                <div class='form-group'>
                    <label for="customer_code">Code</label>	
                     <div class="required-field-block">	   
                    <input class="form-control" id="customer_code"   name="customer_code"  maxlength="25"  size="30" type="text" value="${cust_info.partyCode}" placeholder="Enter Code"/>
                    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
            </div>
          
            
</div>

</div>
</div>
</div>
    
<div id="page-wrapper" style="margin-top: 0;"> 
        <div id="page-inner">   
<div class="row">                       
            <div class='col-sm-3'>
                <div class='form-group'>                	
                    <label for="display_name">Display Name</label>
                    <div class="required-field-block">
                    <input class="form-control" id="display_name" name="display_name"  maxlength="60"  size="30" type="text" value="${cust_info.displayName}" placeholder="Enter Display Name"/>                  
                	</div>
            	</div>
            </div>
            <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="contact_salutation">Primary Contact</label>                  
                    <select class="form-control" id="contact_salutation" name="contact_salutation">
	                    <option value="0">Salutation</option>
					    <option value="Mr"   <c:if test="${cust_info.contactSalutation eq 'Mr'}"> selected </c:if> >Mr.</option>
					    <option value="Mrs" <c:if test="${cust_info.contactSalutation eq 'Mrs'}"> selected </c:if> >Mrs.</option>
					    <option value="Ms" <c:if test="${cust_info.contactSalutation eq 'Ms'}"> selected </c:if> >Ms.</option>
					    <option value="Miss" <c:if test="${cust_info.contactSalutation eq 'Miss'}"> selected </c:if> >Miss.</option>
                    </select>                   
                </div>
            </div>   	    	
        </div>	
        <div class='row'>             
            
            <div class='col-sm-3'>
                <div class='form-group'>   
                	<label for="first_name">First Name</label>                       
                    <input class="form-control" id="first_name" name="first_name"  maxlength="50"  value="${cust_info.contactFirstname}" size="30" type="text" placeholder="Enter First Name"/>
                </div>
            </div>   
            <div class='col-sm-3'>
                <div class='form-group'>   
                	<label for="last_name">Last Name</label>                    
                    <input class="form-control" id="last_name" name="last_name"  maxlength="100"  value="${cust_info.contactLastname}" size="30" type="text" placeholder="Enter Last Name"/>                   
                </div>
            </div>           
        </div>
        <div class='row'>             
            <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="email">Contact Email</label>
                    <input class="form-control" id="email" name="email" value="${cust_info.contactEmail}"  maxlength="100"  size="30" type="text" placeholder="Enter Contact Email"/>
                </div>
            </div>
            <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="phone">Contact Phone</label>
                    <input class="form-control" id="phone" name="phone" size="30" type="text"  maxlength="15"  value="${cust_info.contactPhone}" placeholder="Enter Contact Phone"/>
                </div>
            </div>
	    	     
        </div>
        <div class='row'>     
			<div class='col-sm-3'>
                <div class='form-group'>
                    <label for="contact_desg">Contact Designation</label>
                    <input class="form-control" id="contact_desg" name="contact_desg" size="30"  maxlength="50"  type="text" value="${cust_info.contactDesignation}" placeholder="Enter Contact Designation"/>
                </div>
            </div>     
            <div class='col-sm-1-5'>
                <div class='form-group'>
                    <label >Contact Type</label> 
                   <div class="radio">  <input type="radio" class="radio_1"  value="Customer"  <c:if test="${mode eq 'n'}"> checked </c:if>  id="party_tag1" name="party_tag" <c:if test="${cust_info.partyTag eq 'Customer'}">checked</c:if> /><label class="radio_1" for="party_tag1">&nbsp;Customer</label></div>
                </div>
            </div>
            <div class='col-sm-1-5'>
                <div class='form-group'>
                    <label >&nbsp;</label> 
                   <div class="radio"> <input type="radio" class="radio_1" value="Vendor"  id="party_tag2" name="party_tag"  <c:if test="${cust_info.partyTag eq 'Vendor'}">checked</c:if> /><label class="radio_1" for="party_tag2">&nbsp;Vendor</label></div>
                </div>
            </div>
            <div class='col-sm-1-5'>
                <div class='form-group'>
                    <label >&nbsp;</label> 
                   <div class="radio"> <input type="radio" class="radio_1" value="Both"  id="party_tag3" name="party_tag"  <c:if test="${cust_info.partyTag eq 'Both'}">checked</c:if> /><label class="radio_1" for="party_tag3">&nbsp;Both</label></div>
                </div>
            </div>
            </div>
        <div class='row'>  
            <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="status">Status</label>
                    <select class="form-control" id="status" name="status">
					                    <option value="1" <c:if test="${cust_info.status==1}"> selected </c:if> >Active</option>
									    <option value="0" <c:if test="${cust_info.status==0}"> selected </c:if> >Inactive</option>
				                    </select>  
                </div>
            </div>
        </div>       
        
        
        <div class="row">
    	
        <div class="col-md-9">
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
				                     
				                        <input class="form-control" id="currency" name="currency" maxlength="50" value="${cust_info.currency}" size="30" type="text" placeholder="Type and select Currency"/>
				                     <input  id="currency_id" name="currency_id" type="hidden"  size="30"  value="${cust_info.currencyId}"/>
				                                
				                 <%--   <select name="currency" id="currency" class="form-control" >
						<option value="-1">&lt;--Select Currency--&gt;</option>
						<c:forEach var="objCur" items="${currency_list}">
							<c:choose>
								<c:when test="${objCur.currencyId==cust_info.currencyId}">
									<option value="${objCur.currencyId}" selected="selected">${objCur.currencyName}</option>
								</c:when>
								<c:otherwise>
									<option value="${objCur.currencyId}">${objCur.currencyName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>   --%>
					<div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
				                </div>
				            </div>   
				            <div class='col-sm-4'>
				                <div class='form-group'>   
				                	<label for="payment_terms">Payment Terms</label>                       
				                    <input class="form-control" id="payment_terms" name="payment_terms"  maxlength="200"  size="30" type="text"  value="${cust_info.paymentTerms}" placeholder="Enter Payment Terms"/>
				                </div>
				            </div>  
				            <div class='col-sm-4'>
				                <div class='form-group'>   
				                	<label for="credit_days">Credit (Days)</label>                       
				                    <input class="form-control" id="credit_days" name="credit_days" size="30" type="text" value="${cust_info.creditDays}" placeholder="Enter Credit (Days)"/>
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
								                	 <input class="form-control" id="bill_street" name="bill_street"  maxlength="100"  size="30" type="text" value="${cust_info.billStreet}" placeholder="Enter Street"/>                  
								                </div>  
				            				</div>
				            		 </div>  
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="bill_location">Location</label>                       
								                    <input class="form-control" id="bill_location" name="bill_location"  maxlength="100"  size="30" type="text" value="${cust_info.billLocation}" placeholder="Enter Location"/>
								                </div>  
				            				</div>
				            		 </div>           
				                     <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="bill_city">City</label>                       
								                    <input class="form-control" id="bill_city" name="bill_city"  maxlength="50"  size="30" type="text" value="${cust_info.billCity}" placeholder="Enter City"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="bill_state">State</label>                       
								                    <input class="form-control" id="bill_state" name="bill_state" size="30"  maxlength="50"  type="text" value="${cust_info.billState}" placeholder="Enter State"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="bill_zip">Zip Code</label>                       
								                    <input class="form-control" id="bill_zip" name="bill_zip" size="30"  maxlength="10"  type="text" value="${cust_info.billZip}" placeholder="Enter Zip Code"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="bill_country">Country</label>                       
								                    <input class="form-control" id="bill_country" name="bill_country"  maxlength="50"  size="30" type="text" value="${cust_info.billCountry}" placeholder="Enter Country"/>
								                </div>  
				            				</div>
				            		 </div> 
				            		  <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="company_phone">Company Phone</label>                       
								                    <input class="form-control" id="company_phone" name="company_phone"  maxlength="30"  size="30" type="text" value="${cust_info.companyPhone}" placeholder="Enter Company Phone"/>
								                </div>  
				            				</div>
				            		 </div>
				            		  <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="company_email">Company Email</label>                       
								                    <input class="form-control" id="company_email" name="company_email"  maxlength="100"  size="30" type="text" value="${cust_info.companyEmail}" placeholder="Enter Company Email"/>
								                </div>  
				            				</div>
				            		 </div>
				            		  <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="company_fax">Company Fax</label>                       
								                    <input class="form-control" id="company_fax" name="company_fax"  maxlength="25"  size="30" type="text" value="${cust_info.companyFax}" placeholder="Enter Company Fax"/>
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
								                	 <input class="form-control" id="ship_street" name="ship_street"  maxlength="100"  size="30" type="text" value="${cust_info.shipStreet}" placeholder="Enter Street"/>                  
								                </div>  
				            				</div>
				            		 </div>  
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="ship_location">Location</label>                       
								                    <input class="form-control" id="ship_location" name="ship_location"  maxlength="100"  size="30" type="text" value="${cust_info.shipLocation}" placeholder="Enter Location"/>
								                </div>  
				            				</div>
				            		 </div>          
				                          <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="ship_city">City</label>                       
								                    <input class="form-control" id="ship_city" name="ship_city" size="30"  maxlength="50"  type="text" value="${cust_info.shipCity}" placeholder="Enter City"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="ship_state">State</label>                       
								                    <input class="form-control" id="ship_state" name="ship_state"  maxlength="50"  size="30" type="text" value="${cust_info.shipState}" placeholder="Enter State"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="ship_zip">Zip Code</label>                       
								                    <input class="form-control" id="ship_zip" name="ship_zip"  maxlength="10"  size="30" type="text" value="${cust_info.shipZip}" placeholder="Enter Zip Code"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="ship_country">Country</label>                       
								                    <input class="form-control" id="ship_country" name="ship_country"  maxlength="50"  size="30" type="text" value="${cust_info.shipCountry}" placeholder="Enter Country"/>
								                </div>  
				            				</div>
				            		 </div>    
				            		 <div class='row'>             
				            				<div class='col-sm-12'>    
				            					<div class='form-group'>   
								                	<label for="ship_phone">Phone</label>                       
								                    <input class="form-control" id="ship_phone" name="ship_phone"  maxlength="50"  size="30" type="text" value="${cust_info.shipPhone}" placeholder="Enter Phone"/>
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
				                	<label for="vat">VAT Reg.No</label>                       
				                    <input class="form-control" id="vat" name="vat" size="30" type="text"  maxlength="25"  value="${cust_info.vatNo}" placeholder="Enter VAT Reg.No"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="service_no">Service Tax Reg.No</label>                       
				                    <input class="form-control" id="service_no" name="service_no" size="30"  maxlength="25"  type="text" value="${cust_info.servtaxNo}" placeholder="Enter Service Tax Reg.No"/>
				                </div>
				            </div>  
				        </div>
				        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="pan_no">Pan No</label>                       
				                    <input class="form-control" id="pan_no" name="pan_no" size="30"  maxlength="25"  type="text" value="${cust_info.panNO}" placeholder="Enter Pan No"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="insurance">Insurance</label>                       
				                    <input class="form-control" id="insurance" name="insurance"  maxlength="100"  size="30" type="text" value="${cust_info.insuranceDetails}" placeholder="Enter Insurance"/>
				                </div>
				            </div>  
				        </div>
                        </div>
                        <div class="tab-pane fade" id="tab4primary">
                        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="account_group">Account Group</label>
				                	 <div class="required-field-block">                       
				                    <input class="form-control" id="account_group" name="account_group"  onblur="getBillTracking();" maxlength="50"  size="30" type="text" value="${cust_info.accountGroup}" placeholder="Type and select Account Group"/>
				                     <input  id="account_group_id" name="account_group_id" type="hidden"  size="30" value="${cust_info.accountGroupId}" />
				                     <div class="required-icon">
								<div class="text">*</div>
							     </div>
							     </div>
				                </div>
				            </div>  
				            <div id="bill_exist" <c:if test="${cust_info.groupType ne 'Sundry Debtors' and cust_info.groupType ne 'Sundry Creditors'}"> style="display:none" </c:if>>
				             <div class='col-sm-6'>				               
				                	<div class='form-group'>			
				                	    <label for="bill_tracking">&nbsp;</label><br/>  
				                	    <div class="checkbox">
                         <div class="checkbox">  <input class="checkbox_1" type="checkbox" id="bill_tracking" name="bill_tracking" value="1" <c:if test="${mode eq 'n'}"> checked </c:if>  <c:if test="${cust_info.billTracking == 1}">checked</c:if>  /><label for="bill_tracking" class="checkbox_1">I want to track Age of each bill</label></div></div>        
				                </div>
				            </div>
				            </div>
				               
				            
				   
				        </div>
                        </div>
                        <div class="tab-pane fade" id="tab5primary">
                        <div class='row'>
				             <div class='col-sm-12'>    
				                <div class='form-group'>
								<label for="notes">Note</label>
								<textarea class="form-control" rows="4" id="notes" name="notes"  maxlength="500"  Placeholder="Notes">${cust_info.notes}</textarea>
				                </div>
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
        <div class='row pull-right'>        	         	 
            <div class='col-sm-12 col-xs-12 centered'>  
            <c:if test="${customer_rights.addPermission==1 or customer_rights.editPermission==1 }">		
            <div class="btn-group dropup">
            <button name="save" id="save"  onclick="saveCustomer('${mode}','1')"  class="btn btn-success ladda-button"  data-style="expand-right">
			     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			    </button>
                
                <button class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="height:34px;"><span class="caret"></span></button>
                <ul class="dropdown-menu">
                 <c:if test="${customer_rights.addPermission==1 }">	
					  <li><a <%-- href="javascript:saveCustomer('${mode}','2');" --%>>Save & New</a></li>
					  </c:if>
						  <c:if test="${customer_rights.viewPermission==1 }">			
						  <li><a <%-- href="javascript:saveCustomer('${mode}','3');" --%>>Save & Close</a></li>
						  </c:if>
                </ul>
              </div>
            
            </c:if>             
                   <c:if test="${customer_rights.viewPermission==1 }">			
               <button type="button" class="btn btn-primary"  onclick="showCustomer()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>	
               </c:if>
            </div>            
    </div>  
     <div class='row'>       
        </div>
   </div>
  </div> 
  		</div>

		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> 
		<input type="hidden" name="request_type" id="request_type" /> 
		<input type="hidden" name="pageno" id="pageno" /> 
		<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="party_id" id="party_id" value="${party_id}" />
		<input type="hidden" name="save_type" id="save_type" value="${save_type}" />
		<input type="submit" name="validation_btn" id="validation_btn" style="display:none;"/>
		<input type="hidden" id="add_new_accountgroup" data-toggle="modal" data-target="#accountGroupModal"  onclick="loadDataToGroupModal();"  />
		<input type="hidden" id="add_new_currency" data-toggle="modal" data-target="#currencyMasterModal"   />
		
<script type="text/javascript">


$('#validate-form').bootstrapValidator({
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
        currency: {
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
 <script>		  
 !function ($) {
	 
		$(function(){
		 $('#account_group').listGroupForAccount({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroupForAccount&create_new=1",
			  nameField :'#account_group',
			  idField:'#account_group_id',
			    formId:'#validate-form',
			  validateId:'account_group'
		  });
		 $('#currency').listCurrency({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCurrency&create_new=1",
			  nameField :'#currency',
			  idField:'#currency_id',
			  formId:'#validate-form',
			  validateId:'currency'
		  });
		 });
 }(window.jQuery);	
</script> 
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script> 
	</form>
	
	      <jsp:include page="../masters/AddAccountGroupFromPage.jsp" />
	        <jsp:include page="../masters/AddCurrencyFromPage.jsp" />
	      
</body>
</html>
  
