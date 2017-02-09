
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
	<form action="" id="tpcslogin" method="post" role="form">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>

<div id="page-wrapper">
<div id="page-inner">
        <div class="row">
                <div class="col-md-11 col-sm-8 col-xs-8">
                    <h3 class="add-page-header">Add Customer</h3>
                </div>
               <!--  <div class="col-md-1 col-sm-4 col-xs-4 pull-right">
                        <img src="src/images/jenixerp.png" class="img-rounded" alt="Cinque Terre" width="150" height="60">						
                </div> -->
		</div>
        <div class='row'>
            <div class='col-sm-4'>    
                <div class='form-group'>
                    <label for="group">Customer Code</label>		   
                    <input class="form-control" id="customerCode" name="customerCode" size="30" type="text" placeholder="Enter Customer Code"/>
                    <input type="hidden" id="customerId" name="customerId" value="40"/>		   
                </div>
            </div>
            <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="companyName">Company Name</label>                  
                    <input class="form-control" id="companyName" name="companyName" size="30" type="text" placeholder="Enter Company Name"/>                   
                </div>
            </div>
            <div class='col-sm-4'>
                <div class='form-group'>                	
                    <label for="displayName">Display Name</label>
                    <div class="required-field-block">
                    <input class="form-control" id="displayName" name="displayName" size="30" type="text" placeholder="Enter Material Name"/>                  
                	</div>
            	</div>
            </div>	    	
        </div>	
        <div class='row'>             
            <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="spec1">Primary Contact</label>                  
                    <select class="form-control" id="contactType" name="contactType">
	                    <option value="0">Salutation</option>
					    <option value="1">Mr.</option>
					    <option value="2">Mrs.</option>
					    <option value="3">Ms.</option>
					    <option value="4">Miss.</option>
                    </select>                   
                </div>
            </div>   
            <div class='col-sm-4'>
                <div class='form-group'>   
                	<label for="spec1">&nbsp;</label>                       
                    <input class="form-control" id="firstName" name="firstName" size="30" type="text" placeholder="Enter First Name"/>
                </div>
            </div>   
            <div class='col-sm-4'>
                <div class='form-group'>   
                	<label for="spec1">&nbsp;</label>                    
                    <input class="form-control" id="lastName" name="lastName" size="30" type="text" placeholder="Enter Last Name"/>                   
                </div>
            </div>           
        </div>
        <div class='row'>             
            <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="email">Contact Email</label>
                    <input class="form-control" id="email" name="email" size="30" type="text" placeholder="Enter Contact Email"/>
                </div>
            </div>
            <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="phone">Contact Phone</label>
                    <input class="form-control" id="phone" name="phone" size="30" type="text" placeholder="Enter Contact Phone"/>
                </div>
            </div>
	    	<div class='col-sm-4'>
                <div class='form-group'>
                    <label for="contactPerson">Contact Person</label>
                    <input class="form-control" id="contactPerson" name="contactPerson" size="30" type="text" placeholder="Enter Contact Person"/>
                </div>
            </div>         
        </div>
        <div class='row'>       
            <div class='col-sm-12'>
                <div class='form-group'>
                    <label for="stockLoc">Contact Type</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <label class="radio-inline"><input type="radio" name="radio">&nbsp;&nbsp;Customer</label>
                    <label class="radio-inline"><input type="radio" name="radio">&nbsp;&nbsp;Vendor</label>
                    <label class="radio-inline"><input type="radio" name="radio">&nbsp;&nbsp;Both</label>
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
                            <!-- <li class="dropdown">
                                <a href="#" data-toggle="dropdown">Dropdown <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="#tab4primary" data-toggle="tab">Primary 4</a></li>
                                    <li><a href="#tab5primary" data-toggle="tab">Primary 5</a></li>
                                </ul>
                            </li> -->
                        </ul>
                </div>
                <div class="panel-body">
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="tab1primary">
                        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>
				                    <label for="currency">Currency</label>                  
				                    <select class="form-control" id="currency" name="currency">
					                    <option value="0" selected="selected">Select Currency</option>
									    <option value="1">Mr.</option>
									    <option value="2">Mrs.</option>
									    <option value="3">Ms.</option>
									    <option value="4">Miss.</option>
				                    </select>                   
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Payment Terms</label>                       
				                    <input class="form-control" id="paymentTerms" name="paymentTerms" size="30" type="text" placeholder="Enter Payment Terms"/>
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
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="currency">SHIPPING ADDRESS</label>         
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
                        <div class="tab-pane fade" id="tab3primary">
                        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Tax Reg.No</label>                       
				                    <input class="form-control" id="taxregmp" name="taxregmp" size="30" type="text" placeholder="Enter Tax Reg.No"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Service Tax Reg.No</label>                       
				                    <input class="form-control" id="servicetaxregno" name="servicetaxregno" size="30" type="text" placeholder="Enter Service Tax Reg.No"/>
				                </div>
				            </div>  
				        </div>
				        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Pan No</label>                       
				                    <input class="form-control" id="panno" name="panno" size="30" type="text" placeholder="Enter Pan No"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Insurance</label>                       
				                    <input class="form-control" id="insurance" name="insurance" size="30" type="text" placeholder="Enter Insurance"/>
				                </div>
				            </div>  
				        </div>
                        </div>
                        <div class="tab-pane fade" id="tab4primary">
                        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Group</label>                       
				                    <input class="form-control" id="group" name="group" size="30" type="text" placeholder="Enter Group"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Opening Balance</label>                       
				                    <input class="form-control" id="openingbalance" name="openingbalance" size="30" type="text" placeholder="Enter Opening Balance"/>
				                </div>
				            </div>  
				        </div>
				        <div class='row'>             
				            <div class='col-sm-6'>				               
				                	<div class='form-group'>			
				                	    <label for="check">&nbsp;</label><br/>             
					                    <label class="checkbox-inline"><input type="checkbox" name="checkbox">&nbsp;Credit</label>
					                    <label class="checkbox-inline"><input type="checkbox" name="checkbox">&nbsp;Bill By Bill</label>
					                    <label class="checkbox-inline"><input type="checkbox" name="checkbox">&nbsp;Cost Center Applicable</label>					                
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Credit</label>                       
				                    <input class="form-control" id="credit" name="credit" size="30" type="text" placeholder="Enter Credit"/>
				                </div>
				            </div>  
				        </div>
				        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Credit days Allowed</label>                       
				                    <input class="form-control" id="crdaysallowed" name="crdaysallowed" size="30" type="text" placeholder="Enter Credit days Allowed"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Against Bill No</label>                       
				                    <input class="form-control" id="agstbillno" name="agstbillno" size="30" type="text" placeholder="Enter Against Bill No"/>
				                </div>
				            </div>  
				        </div>
				        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Debit</label>                       
				                    <input class="form-control" id="debit" name="debit" size="30" type="text" placeholder="Enter Debit"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Due Date</label>                       
				                    <input class="form-control" id="duedate" name="duedate" size="30" type="text" placeholder="Enter Due Date"/>
				                </div>
				            </div>  
				        </div>
                        </div>
                        <div class="tab-pane fade" id="tab5primary">
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
	</div>
        
        
        <!-- <div class='row'>       
            <div class='col-sm-12'>
            	
            	<div class="bs-docs-example">
				    <ul id="myTab" class="nav nav-tabs">
				      <li class="active"><a href="#payment" data-toggle="tab">PAYMENT DETAILS</a></li>
				      <li><a href="#address" data-toggle="tab">ADDRESS</a></li>
				      <li><a href="#tax" data-toggle="tab">TAX SETTING</a></li>
				      <li><a href="#account" data-toggle="tab">ACCOUNTS</a></li>
				      <li><a href="#notes" data-toggle="tab">NOTE</a></li>
				    </ul>
				    <div id="myTabContent" class="tab-content">
				      <div class="tab-pane fade in active" id="payment">
				        
				         <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>
				                    <label for="currency">Currency</label>                  
				                    <select class="form-control" id="currency" name="currency">
					                    <option value="0" selected="selected">Select Currency</option>
									    <option value="1">Mr.</option>
									    <option value="2">Mrs.</option>
									    <option value="3">Ms.</option>
									    <option value="4">Miss.</option>
				                    </select>                   
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Payment Terms</label>                       
				                    <input class="form-control" id="paymentTerms" name="paymentTerms" size="30" type="text" placeholder="Enter Payment Terms"/>
				                </div>
				            </div>  
				        </div>
				        
				      </div>
				      <div class="tab-pane fade" id="address">
				       
				       <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>
				                    <label for="currency">BILLING ADDRESS</label>      
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
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="currency">SHIPPING ADDRESS</label>         
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
				      <div class="tab-pane fade" id="tax">
				        
				        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Tax Reg.No</label>                       
				                    <input class="form-control" id="taxregmp" name="taxregmp" size="30" type="text" placeholder="Enter Tax Reg.No"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Service Tax Reg.No</label>                       
				                    <input class="form-control" id="servicetaxregno" name="servicetaxregno" size="30" type="text" placeholder="Enter Service Tax Reg.No"/>
				                </div>
				            </div>  
				        </div>
				        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Pan No</label>                       
				                    <input class="form-control" id="panno" name="panno" size="30" type="text" placeholder="Enter Pan No"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Insurance</label>                       
				                    <input class="form-control" id="insurance" name="insurance" size="30" type="text" placeholder="Enter Insurance"/>
				                </div>
				            </div>  
				        </div>
				        
				      </div>    
				      <div class="tab-pane fade" id="account">
				        
				        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Group</label>                       
				                    <input class="form-control" id="group" name="group" size="30" type="text" placeholder="Enter Group"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Opening Balance</label>                       
				                    <input class="form-control" id="openingbalance" name="openingbalance" size="30" type="text" placeholder="Enter Opening Balance"/>
				                </div>
				            </div>  
				        </div>
				        <div class='row'>             
				            <div class='col-sm-6'>				               
				                	<div class='form-group'>			
				                	    <label for="check">&nbsp;</label><br/>             
					                    <label class="checkbox-inline"><input type="checkbox" name="checkbox">&nbsp;Credit</label>
					                    <label class="checkbox-inline"><input type="checkbox" name="checkbox">&nbsp;Bill By Bill</label>
					                    <label class="checkbox-inline"><input type="checkbox" name="checkbox">&nbsp;Cost Center Applicable</label>					                
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Credit</label>                       
				                    <input class="form-control" id="credit" name="credit" size="30" type="text" placeholder="Enter Credit"/>
				                </div>
				            </div>  
				        </div>
				        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Credit days Allowed</label>                       
				                    <input class="form-control" id="crdaysallowed" name="crdaysallowed" size="30" type="text" placeholder="Enter Credit days Allowed"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Against Bill No</label>                       
				                    <input class="form-control" id="agstbillno" name="agstbillno" size="30" type="text" placeholder="Enter Against Bill No"/>
				                </div>
				            </div>  
				        </div>
				        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Debit</label>                       
				                    <input class="form-control" id="debit" name="debit" size="30" type="text" placeholder="Enter Debit"/>
				                </div>
				            </div>   
				            <div class='col-sm-6'>
				                <div class='form-group'>   
				                	<label for="spec1">Due Date</label>                       
				                    <input class="form-control" id="duedate" name="duedate" size="30" type="text" placeholder="Enter Due Date"/>
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
        </div>  -->
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
                    	<button type="button" class="btn btn-danger" onclick="showCustomer()">Cancel</button>									
                    </div>        	
        </div>   
   </div>
  </div> 
<input type="hidden" name="servlet_name" id="servlet_name" value="{{servletname}}"/>
<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" value="{{methodname}}"/>
<input type="hidden" name="mode" id="mode" value="{{mode}}"/>
  		</div>

		<jsp:include page="../common/ColumnPreferences.jsp" />
		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> <input
			type="hidden" name="request_type" id="request_type" /> <input
			type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
		<input type="hidden" name="pageno" id="pageno" value="${pc.pageNo}" />
		<input type="hidden" name="total_pages" id="total_pages"
			value="${pc.pageCount}" />

	</form>
</body>
</html>
  
