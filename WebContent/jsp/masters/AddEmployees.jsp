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
<style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}

</style>
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form action="" id="tpcslogin" method="post" role="form">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>

<div class="top-bar">
 <div class="row">
                <div class="col-md-11 col-sm-8 col-xs-12">
                    <h2 class="add-page-header" >Employee</h2>
                </div>
           </div>
<div id="page-wrapper" class="add-top-wrapper"> 
   <div id="page-inner">          
   
   <div class="row">
            <div class='col-sm-3 col-xs-12'>    
                <div class='form-group'>
                    <label for="customer">Employee Code</label>		   
                    <input class="form-control" id="groupName" name="groupName" size="30" type="text" placeholder="Enter Employee Code" ng-model="data.groupName"/>	
                    <input class="hide" id="groupId" name="groupId" size="30" type="text" ng-model="data.groupId"/>		  
                </div>
            </div>
            <div class='col-sm-3 col-xs-12'>
                <div class='form-group'>
                    <label for="sales_person">Employee Name</label>
                    <input class="form-control" id="groupCode" name="groupCode" required="required" size="30" type="text" placeholder="Enter Employee Name" ng-model="data.groupCode"/>
                </div>
            </div>            
        </div>
        

</div>
</div>
</div>
    
<div id="page-wrapper" style="margin-top: 0;"> 
        <div id="page-inner">           
        <div class='row'>
            <div class='col-sm-3 col-xs-12'>    
                <div class='form-group'>
                    <label for="customer">Father's Name</label>		   
                    <input class="form-control" id="groupName" name="groupName" size="30" type="text" placeholder="Enter Father's Name" ng-model="data.groupName"/>	
                    <input class="hide" id="groupId" name="groupId" size="30" type="text" ng-model="data.groupId"/>		  
                </div>
            </div>
            <div class='col-sm-3 col-xs-12'>
                <div class='form-group'>
                    <label for="sales_person">Gender</label>
                    <input class="form-control" id="groupCode" name="groupCode" required="required" size="30" type="text" placeholder="Enter Gender" ng-model="data.groupCode"/>
                </div>
            </div>            
        </div>
        
        <div class='row'>
            <div class='col-sm-3 col-xs-12'>    
                <div class='form-group'>
                    <label for="customer">Contact Email</label>		   
                    <input class="form-control" id="groupName" name="groupName" size="30" type="text" placeholder="Enter Cantact Email" ng-model="data.groupName"/>	
                    <input class="hide" id="groupId" name="groupId" size="30" type="text"  ng-model="data.groupId"/>		  
                </div>
            </div>
            <div class='col-sm-3 col-xs-12'>
                <div class='form-group'>
                    <label for="sales_person">Contact Phone</label>
                    <input class="form-control" id="groupCode" name="groupCode" required="required" size="30" type="text" placeholder="Enter Contact Phone" ng-model="data.groupCode"/>
                </div>
            </div>            
        </div>
        
        <div class="row">                
<div class='col-sm-3'  >
<div class='form-group'>
  	<label>Status</label>
			<select class="form-control" id="status" name="status">
			<option value="1">Active</option>
			<option value="2">Inactive</option>
			</select>
</div>
</div>
</div>
        
        
        <div class="row">
    	
        <div class="col-md-6">
            <div class="panel with-nav-tabs panel-primary">
                <div class="panel-heading">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab1primary" data-toggle="tab">PERSONAL</a></li>
                            <!-- <li><a href="#tab2primary" data-toggle="tab">WORKDETAILS</a></li> -->
                            <li><a href="#tab3primary" data-toggle="tab">ADDRESS</a></li>
                            <li><a href="#tab4primary" data-toggle="tab">NOTE</a></li>
                        </ul>
                </div>
                <div class="panel-body">
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="tab1primary">
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
				                	<label for="spec1">Account Number</label>                       
				                    <input class="form-control" id="servicetaxregno" name="servicetaxregno" size="30" type="text" placeholder="Enter Account Number"/>
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
				                	<label for="spec1">Education</label>                       
				                    <input class="form-control" id="taxregmp" name="taxregmp" size="30" type="text" placeholder="Enter Educaction"/>
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
				                    <input class="form-control" id="taxregmp" name="taxregmp" size="30" type="text" placeholder="Enter Languages Known"/>
				                </div>
				            </div>   
				          
				        </div>
                        
                        </div>
                        <!-- <div class="tab-pane fade" id="tab2primary">
                        <div class='row'>             
				            <div class='col-sm-6'>
				                <div class='form-group'>
				                    <label for="currency">BILLING ADDRESS</label>      
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
                        </div> -->
                        <div class="tab-pane fade" id="tab3primary">
                        <div class='row'>             
				            <div class='col-sm-12'>
				                <div class='form-group'>
				                   <!--  <label for="currency">BILLING ADDRESS</label>       -->
				                    <div class='row'>             
				            				<div class='col-sm-6'>    
				            					<div class='form-group'>   
								                	<label for="spec1">Street</label>                       
								                    <textarea class="form-control" rows="3" id="salesDesc" placeholder="Enter Street"></textarea>  
								                </div>  
				            				</div>
				            				<div class='col-sm-6'>    
				            					<div class='form-group'>   
								                	<label for="spec1">City</label>                       
								                    <input class="form-control" id="paymentTerms" name="paymentTerms" size="30" type="text" placeholder="Enter City"/>
								                </div>  
				            				</div>
				            		 </div>            
				            		 <div class='row'>             
				            				<div class='col-sm-6'>    
				            					<div class='form-group'>   
								                	<label for="spec1">State</label>                       
								                    <input class="form-control" id="paymentTerms" name="paymentTerms" size="30" type="text" placeholder="Enter State"/>
								                </div>  
				            				</div>
				            				<div class='col-sm-6'>    
				            					<div class='form-group'>   
								                	<label for="spec1">Zip Code</label>   
								                	 <input class="form-control" id="paymentTerms" name="paymentTerms" size="30" type="text" placeholder="Enter Zip Code"/>                   
								                </div>  
				            				</div>
				            		 </div>    
				            		    <div class='row'>             
				            				<div class='col-sm-6'>    
				            					<div class='form-group'>   
								                	<label for="spec1">Country</label>   
								                	 <input class="form-control" id="paymentTerms" name="paymentTerms" size="30" type="text" placeholder="Enter Country"/>                   
								                </div>  
				            				</div>
				            				<div class='col-sm-6'>    
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
                        <div class="tab-pane fade" id="tab4primary">
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
				                    <label for="currency">BILLING ADDRESS</label>      
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
        
        <div class='row pull-right' style="margin-bottom: 25px;">        	         	 
            <div class='col-sm-12 col-xs-12 centered'>               
                  	<div class="btn-group dropup">
                <input type="button" class="btn btn-success" name="save" id="save"  value="Save"  />
                <button class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="height:34px;"><span class="caret"></span></button>
                <ul class="dropdown-menu">
            <li><a href="#" >Save & Send</a></li>
            <li><a href="#" >Save & New</a></li>
                  <li class="divider"></li>
                  <li><a href="#">Save & Close</a></li>
                </ul>
              </div>
               <button type="button" class="btn btn-primary"  onclick="showEmployees()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>	
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
  