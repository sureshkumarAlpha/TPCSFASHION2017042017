<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
  <head>
  	<meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title><fmt:message bundle="${bundle}"   key="Title.Title"/></title>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/sales/sales-enquiry.js"></script>

<jsp:include page="../common/Header.jsp"/>
<style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}


label {
    display: inline-block;
    max-width: 100%;
    margin-bottom: 0px;
    font-weight: 700;
}

.form-group{
margin-bottom:4px;
}
 
.form-control{
	height:25px;
	font-size: 11px;
}
.input-group-addon {
    padding: 4px 8px;
    font-size: 12px;
}
.required-field-block .required-icon:after{
    border-bottom: 25px solid transparent;
}
    
</style>
<script language="javascript">
var contextpath = "${pageContext.request.contextPath}";
</script>
</head>
<jsp:include page="../common/ValidateUser.jsp" />
<body>
<form action="" id="tpcslogin" method="post" role="form">
<div class="wrapper">
 	<jsp:include page="../common/MainMenu.jsp"> 
	<jsp:param value="2" name="screen_type"/>
	<jsp:param value="Inventory.Transactions.OpeningStock" name="screen_name"/>
	</jsp:include>
	

<div class="top-bar">
	<div class="row">
		<div class="col-md-11 col-sm-8 col-xs-12">
		    <h2 class="add-page-header">Goods Received Note (GRN)</h2>
		</div>
	</div>
	<div id="page-wrapper" class="add-top-wrapper">
   		<div id="page-inner">   
			<div class="row">

				<div class='col-md-2 col-sm-3'>
	                <div class='form-group'>
	                    <label for="quatationNO">Supplier</label>
	                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" placeholder="Type and select Supplier"/>
	                </div>
            	</div>
           		<div class='col-md-2 col-sm-3'>
	                <div class='form-group'>
	                    <label for="customer">GRN No</label>
	                    <input class="form-control" id="orderno" name="orderno" size="30" type="text" placeholder="select GRN No"/>
			    
	                </div>
            	</div>
            	<div class='col-md-2 col-sm-3'>
	                <div class='form-group' >
	                    <label for="quotationdate">Date</label>
			    		<div class='input-group date' id='datepicker' >
				     		<div class="required-field-block">
					    		<input type='text' class="form-control" placeholder="Select Date" />
				    			<div class="required-icon">
								<div class="text">*</div>
				     			</div>
				     		</div>
				    	<span class="input-group-addon">
						<span class="glyphicon glyphicon-calendar"></span>
				    	</span>
	                	</div>		                
	                </div>
                
	                <script type="text/javascript">
				
			     	 jQuery('#datepicker').datepicker({
						    format: "dd-mm-yyyy"
						});  
		
	        			</script>
        			
            	</div>
			</div>
		</div>
	</div>
</div>
            
	
                  
<div id="page-wrapper">
<div id="page-inner">
 
 	      <div class='row'>   
 	      
 	       <div class='col-md-2 col-sm-3'>
                <div class='form-group' >
                    <label for="quotationdate">Received On</label>
		    <div class='input-group date' id='datepicker1' >
			    <input type='text' class="form-control" placeholder="Select Received On" />
			    
			    <span class="input-group-addon">
				<span class="glyphicon glyphicon-calendar"></span>
			    </span>
                </div>		                
                </div>
                
                <script type="text/javascript">
			
		      jQuery('#datepicker1').datepicker({
					    format: "dd-mm-yyyy"
					});  
	
        			</script>
        			
            </div>
              <div class='col-md-2 col-sm-3'>
                <div class='form-group'>
                    <label for="quatationNO">DN No</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" placeholder="Enter DN No"/>
                </div>
            </div> 
            
            <div class='col-md-2 col-sm-3'>
                <div class='form-group' >
                    <label for="quotationdate">Date</label>
		    <div class='input-group date' id='datepicker3' >
		     <div class="required-field-block">
			    <input type='text' class="form-control" placeholder="Select Date" />
			    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
			    <span class="input-group-addon">
				<span class="glyphicon glyphicon-calendar"></span>
			    </span>
                </div>		                
                </div>
                
                <script type="text/javascript">
			
		      jQuery('#datepicker3').datepicker({
					    format: "dd-mm-yyyy"
					});  
	
        			</script>
        			
            </div>
          
	    
        </div>
          <div class='row'>    
           
           <div class='col-md-2 col-sm-3'>
                <div class='form-group'>
                    <label for="quatationNO">Store</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" placeholder="Enter Store"/>
                </div>
            </div>
               <div class='col-md-2 col-sm-3'>
                <div class='form-group'>
                    <label for="quatationNO">G.E No</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" placeholder="Enter G.E No"/>
                </div>
            </div>  
            
        </div>
            
            
            <div class="row trans-det-row">
				<div class="col-md-0-5 col-xs-12" id="header">
				<label>#</label>
				</div>
				
				<div class="col-md-2 col-xs-12" id="header">
				<label>Material</label>
				</div>
				
				<div class="col-md-1-5 col-xs-12" id="header">
				<label>PO No</label>
				</div>
				
				<div class="col-md-1-5 col-xs-12" id="header">
				<label>Quantity</label>
				</div>
				
				<div class="col-md-1 col-xs-12" id="header">
				<label>UOM</label>
				</div>
				
				<div class="col-md-1-5 col-xs-12" id="header">
				<label>Price</label>
				</div>
				
				<div class="col-md-1-5 col-xs-12 out-div" id="header">
				<label>Tax Group</label>
				</div>
				
				<div class="col-md-1-5 col-xs-12" id="header">
				<label>Amount</label>
				</div>
				
				<div id="header">
				<label>&nbsp;</label>
				</div>  
			
			</div>
			
			<div class="row linerow">
				
				<div class="row row-no-margin">
				
				<div class="col-md-0-5 col-xs-12">
				<div class="form-group grid-slno">1</div>
				</div>
				
				<div class="col-md-2 col-xs-12">
				
				<div class="form-group ">
				<div class="required-field-block">
				<input type="text" class="form-control " name="group_0" id="group_0" placeholder="Group" maxlength="50" value="Sole">
				<input type="hidden" name="group_id_0" id="group_id_0">
				<div class="required-icon">
				<div class="text">*</div>
				</div>
				</div>
				</div>
				
				<div class="form-group ">
				<div class="required-field-block">
				<input type="text" class="form-control " name="material_0" id="material_0" placeholder="Material" maxlength="50" value="EVA Sole">
				<input type="hidden" name="material_id_0" id="material_id_0">
				<div class="required-icon">
				<div class="text">*</div>
				</div>
				</div>
				</div>
				
				<div class="form-group ">
				<input type="text" class="form-control " name="variant_0" id="variant_0" placeholder="Variant" maxlength="50" value="Natural">
				<input type="hidden" name="variant_id_0" id="variant_id_0">
				</div>
				
				<div class="form-group ">
				<select class="form-control" id="size_range_0" name="size_range_0" >
					<option value="-1"><--Size Range--></option>
					<option value="US Men" selected>US Men</option>
				</select>
				</div>
				
				</div>
				
				
				<div class="col-md-1-5 col-xs-12" >
				
				<div class="form-group ">
				<select class="form-control" id="po_no_0" name="po_no_0" >
					<option value="-1"><--Select--></option>
					<option value="17.1516" selected>17.1516</option>
				</select>
				</div>
				
				</div>
				
				<div class="col-md-1-5 col-xs-12">
				
				<div class="form-group ">
				<div class="required-field-block">
				<input type="text" class="form-control text-right" placeholder="As per DN" name="qty_0" id="qty_0" value="75">
				<div class="required-icon">
				<div class="text">*</div>
				</div>
				</div>
				</div>
				
				<div class="form-group ">
				<input type="text" class="form-control text-right" placeholder="Received" name="qty_0" id="qty_0" value="70">
				</div>
				
				<div class="form-group ">
				<input type="text" class="form-control text-right" placeholder="Accepted" name="qty_0" id="qty_0" value="50">
				</div>
				
				<div class="form-group ">
				<input type="text" class="form-control text-right" placeholder="Rejected" name="qty_0" id="qty_0" value="20">
				</div>
				
				</div>
				
				<div class="col-md-1 col-xs-12">
				<div class="form-group ">
				<div class="required-field-block">
				<input type="text" class="form-control ui-autocomplete-input" placeholder="UOM" readonly tabindex="-1" name="sku_0" id="sku_0" maxlength="50" value="NOS"/>
				<input type="hidden" name="uom_id_0" id="uom_id_0">
				<div class="required-icon">
				<div class="text">*</div>
				</div>
				</div>
				</div>	
				</div>
				
				
				<div class="col-md-1-5 col-xs-12">
				<div class="form-group ">
				<div class="required-field-block">
				<input type="text" class="form-control text-right" name="price_0" id="price_0" placeholder="Price" value="100">
				<div class="required-icon">
				<div class="text">*</div>
				</div>
				</div>
				</div>
				</div>
				
				<div class="col-md-1-5 col-xs-12 ">
				<div class="form-group ">
				<select class="form-control" id="tax_group_id_0" name="tax_group_id_0" >
				<option value="-1"><--Select--></option>
				<option value="VAT 5%" selected>VAT 5%</option>
				</select>
				</div>
				</div>
				
				<div class="col-md-1-5 col-xs-12">
				<div class="form-group ">
				<input type="text" class="form-control text-right" placeholder="Amount" name="value_before_tax_fcy_0" readonly tabindex="-1" id="value_before_tax_fcy_0" >
				</div>
				</div>
				
				<div class="col-md-0-5 col-xs-12 linerow-icon">
				
				<a href="javascript:clearSINewDetail('0');" name="clear_0" id="clear_0" title="Clear"><span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
				
				</div>
				
			</div>
			
			<div class="row row-no-margin">
			
			<div class="col-sm-1" id="sub-header"><label>S1</label></div>
			<div class="col-sm-1" id="sub-header"><label>S2</label></div>
			<div class="col-sm-1" id="sub-header"><label>S3</label></div>
			<div class="col-sm-1" id="sub-header"><label>S4</label></div>
			<div class="col-sm-1" id="sub-header"><label>S5</label></div>
			<div class="col-sm-1" id="sub-header"><label>S6</label></div>
			<div class="col-sm-1" id="sub-header"><label>S7</label></div>
			<div class="col-sm-1" id="sub-header"><label>S8</label></div>
			<div class="col-sm-1" id="sub-header"><label>S9</label></div>
			<div class="col-sm-1" id="sub-header"><label>S10</label></div>
			<div class="col-sm-1" id="sub-header"><label>S11</label></div>
			<div class="col-sm-1" id="sub-header"><label>S12</label></div>
			
			</div> 
			
			<div class="row row-no-margin">
				<div class="col-md-1 col-xs-12">
					<div class="form-group ">
						<input type="text" class="form-control " name="variant_0" id="variant_0" placeholder="S1" maxlength="50" >
					</div>
				</div>
				
				<div class="col-md-1 col-xs-12">
					<div class="form-group ">
						<input type="text" class="form-control " name="variant_0" id="variant_0" placeholder="S2" maxlength="50" >
					</div>
				</div>
				<div class="col-md-1 col-xs-12">
					<div class="form-group ">
						<input type="text" class="form-control " name="variant_0" id="variant_0" placeholder="S3" maxlength="50" >
					</div>
				</div>
				<div class="col-md-1 col-xs-12">
					<div class="form-group ">
						<input type="text" class="form-control " name="variant_0" id="variant_0" placeholder="S4" maxlength="50" value="5">
					</div>
				</div>
				<div class="col-md-1 col-xs-12">
					<div class="form-group ">
						<input type="text" class="form-control " name="variant_0" id="variant_0" placeholder="S5" maxlength="50" value="5">
					</div>
				</div>
				<div class="col-md-1 col-xs-12">
					<div class="form-group ">
						<input type="text" class="form-control " name="variant_0" id="variant_0" placeholder="S6" maxlength="50" value="10">
					</div>
				</div>
				<div class="col-md-1 col-xs-12">
					<div class="form-group ">
						<input type="text" class="form-control " name="variant_0" id="variant_0" placeholder="S7" maxlength="50" value="10">
					</div>
				</div>
				<div class="col-md-1 col-xs-12">
					<div class="form-group ">
						<input type="text" class="form-control " name="variant_0" id="variant_0" placeholder="S8" maxlength="50" value="10">
					</div>
				</div>
				<div class="col-md-1 col-xs-12">
					<div class="form-group ">
						<input type="text" class="form-control " name="variant_0" id="variant_0" placeholder="S9" maxlength="50" value="10">
					</div>
				</div>
				<div class="col-md-1 col-xs-12">
					<div class="form-group ">
						<input type="text" class="form-control " name="variant_0" id="variant_0" placeholder="S10" maxlength="50" value="10">
					</div>
				</div>
				<div class="col-md-1 col-xs-12">
					<div class="form-group ">
						<input type="text" class="form-control " name="variant_0" id="variant_0" placeholder="S11" maxlength="50" value="10">
					</div>
				</div>
				<div class="col-md-1 col-xs-12">
					<div class="form-group ">
						<input type="text" class="form-control " name="variant_0" id="variant_0" placeholder="S12" maxlength="50" value="5">
					</div>
				</div>	
			</div>			
			
          </div>
          
          <!-- ------------------------------Second row------------------------------------------- -->
          
           
			
			<div class="row linerow">
				
				<div class="row row-no-margin">
				
				<div class="col-md-0-5 col-xs-12">
				<div class="form-group grid-slno">2</div>
				</div>
				
				<div class="col-md-2 col-xs-12">
				
				<div class="form-group ">
				<div class="required-field-block">
				<input type="text" class="form-control " name="group_0" id="group_0" placeholder="Group" maxlength="50" value="Sheet" >
				<input type="hidden" name="group_id_0" id="group_id_0">
				<div class="required-icon">
				<div class="text">*</div>
				</div>
				</div>
				</div>
				
				<div class="form-group ">
				<div class="required-field-block">
				<input type="text" class="form-control " name="material_0" id="material_0" placeholder="Material" maxlength="50" value="Sole Sheet">
				<input type="hidden" name="material_id_0" id="material_id_0">
				<div class="required-icon">
				<div class="text">*</div>
				</div>
				</div>
				</div>
				
				<div class="form-group ">
				<input type="text" class="form-control " name="variant_0" id="variant_0" placeholder="Variant" maxlength="50" value="Black">
				<input type="hidden" name="variant_id_0" id="variant_id_0">
				</div>
				
				<div class="form-group ">
				<select class="form-control" id="size_range_0" name="size_range_0" >
					<option value="-1"><--Size Range--></option>
				</select>
				</div>
				
				</div>
				
				
				<div class="col-md-1-5 col-xs-12" >
				
				<div class="form-group ">
				<select class="form-control" id="po_no_0" name="po_no_0" >
					<option value="-1"><--Select--></option>
					<option value="13.1516" selected>13.1516</option>
					
				</select>
				</div>
				
				</div>
				
				<div class="col-md-1-5 col-xs-12">
				
				<div class="form-group ">
				<div class="required-field-block">
				<input type="text" class="form-control text-right" placeholder="As per DN" name="qty_0" id="qty_0" value="100">
				<div class="required-icon">
				<div class="text">*</div>
				</div>
				</div>
				</div>
				
				<div class="form-group ">
				<input type="text" class="form-control text-right" placeholder="Received" name="qty_0" id="qty_0" value="100">
				</div>
				
				<div class="form-group ">
				<input type="text" class="form-control text-right" placeholder="Accepted" name="qty_0" id="qty_0" value="100">
				</div>
				
				<div class="form-group ">
				<input type="text" class="form-control text-right" placeholder="Rejected" name="qty_0" id="qty_0" value="100">
				</div>
				
				</div>
				
				<div class="col-md-1 col-xs-12">
				<div class="form-group ">
				<div class="required-field-block">
				<input type="text" class="form-control ui-autocomplete-input" placeholder="UOM" readonly tabindex="-1" name="sku_0" id="sku_0" maxlength="50" value="MTS"/>
				<input type="hidden" name="uom_id_0" id="uom_id_0">
				<div class="required-icon">
				<div class="text">*</div>
				</div>
				</div>
				</div>	
				</div>
				
				
				<div class="col-md-1-5 col-xs-12">
				<div class="form-group ">
				<div class="required-field-block">
				<input type="text" class="form-control text-right" name="price_0" id="price_0" placeholder="Price" value="50">
				<div class="required-icon">
				<div class="text">*</div>
				</div>
				</div>
				</div>
				</div>
				
				<div class="col-md-1-5 col-xs-12 ">
				<div class="form-group ">
				<select class="form-control" id="tax_group_id_0" name="tax_group_id_0" >
				<option value="-1"><--Select--></option>
				<option value="VAT 14.5%" selected>VAT 14.5%</option>
				</select>
				</div>
				</div>
				
				<div class="col-md-1-5 col-xs-12">
				<div class="form-group ">
				<input type="text" class="form-control text-right" placeholder="Amount" name="value_before_tax_fcy_0" readonly tabindex="-1" id="value_before_tax_fcy_0" >
				</div>
				</div>
				
				<div class="col-md-0-5 col-xs-12 linerow-icon">
				
				<a href="javascript:clearSINewDetail('0');" name="clear_0" id="clear_0" title="Clear"><span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
				
				</div>
				
			</div>
			 	
			
          </div>
	         
	         <!-- <div class="row row-no-margin" style="margin-top: 40px;">
	         	<div class="col-sm-8">
  					<div class="row">
  						<div class="col-md-12 col-xs-12" id="header">
							<label class="pull-left">Material Received</label>
							<label class="pull-right ">Display All</label>
						</div>
  					</div>
  					
	         		<div class="row" style="margin-top:15px;">
	          <div class="col-md-0-5 col-xs-12">
				<label>1</label>
				</div>
	                 <div class="col-sm-4">
                <div class="form-group">
                    <label for="quatationNO">Group Name</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" value="Sole" placeholder="Type and select Group Name">
                </div>
            </div>
            <div class="col-sm-4">    
                <div class="form-group">
                    <label for="customer">Po No</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" type="text" value="17.1516" placeholder="Enter Po No">
		    
                </div>
    </div>
    </div>
    <div class="row">
           <div class="col-md-0-5 col-xs-12">
				<label>&nbsp;</label>
				</div>
	                   <div class="col-sm-4">    
                <div class="form-group">
                    <label for="quatationNO">Material</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" value="Eva Sole" placeholder="Type and select Material">
                </div>
            </div>
         <div class="col-sm-4">    
                <div class="form-group">
                    <label for="customer" style="margin-top: 30px;">Qty (Pending Qty) :</label>75&nbsp;NOS
                    <input class="form-control" id="orderno" name="orderno" size="30" type="text" value="75" placeholder="Enter Qty"/>
		    
                </div>
    </div>
    </div>
     <div class="row">
          <div class="col-md-0-5 col-xs-12">
				<label>&nbsp;</label>
				</div>
	                 <div class="col-sm-4">    
                <div class="form-group">
                    <label for="quatationNO">Variant</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" value="Natural" placeholder="Type and select Variant">
                </div>
            </div>
           <div class="col-sm-2">    
                <div class="form-group">
                    <label for="customer">As per DN</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" type="text" value="75" placeholder="Enter As per DN">
		    
                </div>
    </div>
      <div class="col-sm-2">    
                <div class="form-group">
                    <label for="customer">Recd</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" value="70" type="text" placeholder="Enter Recd">
		     </div>
    </div>
      <div class="col-md-0-5 col-xs-12" style="margin-top: 30px;">
				NOS
				</div>
    </div>
    
    <div class="row">
         <div class="col-md-0-5 col-xs-12">
				<label>&nbsp;</label>
				</div>
	                 <div class="col-sm-4">    
                <div class="form-group">
                    <label for="quatationNO">Size Range</label>
                     <select class="form-control" id="discount_type" name="discount_type" style="padding:0px;">
                    <option value="per">&lt;--select--&gt;</option>
                    </select>
                </div>
            </div>
            <div class="col-sm-2">    
                <div class="form-group">
                    <label for="customer">Accepted</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" value="50" type="text" placeholder="Enter Accepted">
		   </div>
    </div>
      <div class="col-sm-2">    
                   <div class="form-group">
                    <label for="customer">Rej</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" value="20" type="text" placeholder="Enter Rej">
		    
                </div>
    </div>
          <div class="col-md-0-5 col-xs-12" style="margin-top: 30px;">
				NOS
				</div>
				
    </div>
      <div class="row">
         <div class="col-md-0-5 col-xs-12">
			<label>&nbsp;</label>
			</div>
    <div class="box-group ">
    <div class="col-sm-4">
                <div class="form-group">
                    <label for="quatationNO">Tax Group</label>
                     <select class="form-control" id="discount_type" name="discount_type" style="padding:0px;">
                    <option value="per">&lt;--select--&gt;</option>
                    </select>
                  <div class="inner-icon">
	<a href="#" ><span class="glyphicon glyphicon-info-sign"></span></a>																							
	</div>
                </div>
            </div>
    </div>
            <div class="col-sm-2">    
                    <label for="customer">&nbsp;</label>
                  <div style="margin-top: 10px;">&nbsp;&nbsp;&nbsp;Rs:325</div>
		    
    </div>
      <div class="col-sm-2">    
                <div class="form-group">
                    <label for="customer">Rate</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" value="100" type="text" placeholder="Enter Rate">
		    
                </div>
    </div>
          <div class="col-md-0-5 col-xs-12" style="margin-top: 30px;">
				RS
				</div>
    </div>
      <div class="row">
          <div class="col-md-0-5 col-xs-12">
				<label>&nbsp;</label>
				</div>
	                 <div class="col-sm-4">    
                <div class="form-group">
                    <label for="quatationNO">Value</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" value="" placeholder="">
                </div>
            </div>
             <div class="col-md-0-5 col-xs-12" style="margin-top: 30px;">
				RS
				</div>

    </div>
	<div class="row" style="margin-top:0px;">
     	<div class="col-md-2 col-xs-12" id="header">
			<label>Size</label>
			</div>
			<div class="col-md-2 col-xs-12" id="header">
					<label>7</label>
				</div>
				<div class="col-md-2  col-xs-12" id="header">
					<label>7.5</label>
				</div>
				<div class="col-md-2 col-xs-12" id="header">
					<label>8</label>
				</div>
				<div class="col-md-2 col-xs-12" id="header">
					<label>8.5</label>
				</div>
				<div class="col-md-2 col-xs-12" id="header">
					<label>9</label>
				</div>
			
			
     </div>
     
	 <div class="row ">
	
     <div class="col-md-2 col-xs-12" style="text-align: center;">
			<label>Qty</label>
			</div>
			<div class="col-md-2 col-xs-12">
				 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="10">
				</div>
				<div class="col-md-2  col-xs-12">
				 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="15">
				</div>
				<div class="col-md-2 col-xs-12">
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="20">
				</div>
				<div class="col-md-2 col-xs-12">
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="25">
				</div>
				<div class="col-md-2 col-xs-12">
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="25">
				</div>
				
		
	 </div>
	 --------------------------------------
	 
	 
	 <div class="row" style="margin-top:15px;">
	          <div class="col-md-0-5 col-xs-12">
				<label>2</label>
				</div>
	                 <div class="col-sm-4">
                <div class="form-group">
                    <label for="quatationNO">Group Name</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" value="Sheet" placeholder="Type and select Group Name">
                </div>
            </div>
            <div class="col-sm-4">    
                <div class="form-group">
                    <label for="customer">Po No</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" type="text" value="13.1516" placeholder="Enter Po No">
		    
                </div>
    </div>
    </div>
    <div class="row">
           <div class="col-md-0-5 col-xs-12">
				<label>&nbsp;</label>
				</div>
	                   <div class="col-sm-4">    
                <div class="form-group">
                    <label for="quatationNO">Material</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" value="Sole Sheet" placeholder="Type and select Material">
                </div>
            </div>
         <div class="col-sm-4">    
                <div class="form-group">
                    <label for="customer" style="margin-top: 30px;">Qty (Pending Qty) :</label>170&nbsp;NOS
                    <input class="form-control" id="orderno" name="orderno" size="30" type="text" value="75" placeholder="Enter Qty"/>
		    
                </div>
    </div>
    </div>
     <div class="row">
          <div class="col-md-0-5 col-xs-12">
				<label>&nbsp;</label>
				</div>
	                 <div class="col-sm-4">    
                <div class="form-group">
                    <label for="quatationNO">Variant</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" value="Black" placeholder="Type and select Variant">
                </div>
            </div>
           <div class="col-sm-2">    
                <div class="form-group">
                    <label for="customer">As per DN</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" type="text" value="100" placeholder="Enter As per DN">
		    
                </div>
    </div>
      <div class="col-sm-2">    
                <div class="form-group">
                    <label for="customer">Recd</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" value="100" type="text" placeholder="Enter Recd">
		     </div>
    </div>
      <div class="col-md-0-5 col-xs-12" style="margin-top: 30px;">
				NOS
				</div>
    </div>
    
    <div class="row">
         <div class="col-md-0-5 col-xs-12">
				<label>&nbsp;</label>
				</div>
	                 <div class="col-sm-4">    
                <div class="form-group">
                    <label for="quatationNO">Size Range</label>
                     <select class="form-control" id="discount_type" name="discount_type" style="padding:0px;">
                    <option value="per">&lt;--select--&gt;</option>
                    </select>
                </div>
            </div>
            <div class="col-sm-2">    
                <div class="form-group">
                    <label for="customer">Accepted</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" value="100" type="text" placeholder="Enter Accepted">
		   </div>
    </div>
      <div class="col-sm-2">    
                   <div class="form-group">
                    <label for="customer">Rej</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" value="100" type="text" placeholder="Enter Rej">
		    
                </div>
    </div>
          <div class="col-md-0-5 col-xs-12" style="margin-top: 30px;">
				NOS
				</div>
				
    </div>
      <div class="row">
         <div class="col-md-0-5 col-xs-12">
			<label>&nbsp;</label>
			</div>
    <div class="box-group ">
    <div class="col-sm-4">
                <div class="form-group">
                    <label for="quatationNO">Tax Group</label>
                     <select class="form-control" id="discount_type" name="discount_type" style="padding:0px;">
                    <option value="per">&lt;--select--&gt;</option>
                    </select>
                  <div class="inner-icon">
	<a href="#" ><span class="glyphicon glyphicon-info-sign"></span></a>																							
	</div>
                </div>
            </div>
    </div>
            <div class="col-sm-2">    
                    <label for="customer">&nbsp;</label>
                  <div style="margin-top: 10px;">&nbsp;&nbsp;&nbsp;Rs:625</div>
		    
    </div>
      <div class="col-sm-2">    
                <div class="form-group">
                    <label for="customer">Rate</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" value="50" type="text" placeholder="Enter Rate">
		    
                </div>
    </div>
          <div class="col-md-0-5 col-xs-12" style="margin-top: 30px;">
				RS
				</div>
    </div>
      <div class="row">
          <div class="col-md-0-5 col-xs-12">
				<label>&nbsp;</label>
				</div>
	                 <div class="col-sm-4">    
                <div class="form-group">
                    <label for="quatationNO">Value</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" value="" placeholder="">
                </div>
            </div>
             <div class="col-md-0-5 col-xs-12" style="margin-top: 30px;">
				RS
				</div>

    </div>
	 <div class="row" style="margin-top:15px;" >
	          <div class="col-md-0-5 col-xs-12" >
				<label>2</label>
				</div>
	                 <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="quatationNO">Group Name</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" value="Sheet" placeholder="Type and select Group Name"/>
                </div>
            </div>
              <div class='col-sm-4'> 
                <div class='form-group'>
                    <label for="customer">Po No</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" type="text" value="13.1516" placeholder="Enter Po No"/>
                </div>
    </div>
    </div>
    <div class="row"  >
           <div class="col-md-0-5 col-xs-12" >
				<label>&nbsp;</label>
				</div>
	                   <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="quatationNO">Material</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" value="Sole Sheet" type="text" placeholder="Type and select Material"/>
                </div>
            </div>
            <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="customer">Qty</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" type="text" value="50" placeholder="Enter Qty"/>
		    
                </div>
    </div>
    </div>
     <div class="row"  >
          <div class="col-md-0-5 col-xs-12" >
				<label>&nbsp;</label>
				</div>
	                  <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="quatationNO">Variant</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" value="Black" placeholder="Type and select Variant"/>
                </div>
            </div>
            <div class='col-sm-2'>    
                <div class='form-group'>
                    <label for="customer">As per DN</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" type="text" value="100" placeholder="Enter As per DN"/>
		    
                </div>
    </div>
      <div class='col-sm-2'>    
                <div class='form-group'>
                    <label for="customer">Recd</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" value="100" type="text" placeholder="Enter Recd"/>
		     </div>
    </div>
    </div>
    
    <div class="row"  >
         <div class="col-md-0-5 col-xs-12" >
				<label>&nbsp;</label>
				</div>
	                 <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="quatationNO">Size Range</label>
                     <select class="form-control" id="discount_type" name="discount_type"  style="padding:0px;">
                    <option value="per">&lt;--select--&gt;</option>
                    </select>
                </div>
            </div>
            <div class='col-sm-2'>    
                <div class='form-group'>
                    <label for="customer">Accepted</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" value="100" type="text" placeholder="Enter Accepted"/>
		   </div>
    </div>
      <div class='col-sm-2'>    
                   <div class='form-group'>
                    <label for="customer">Rej</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" value="100" type="text" placeholder="Enter Rej"/>
		    
                </div>
    </div>
    </div>
      <div class="row"  >
      <div class="col-md-0-5 col-xs-12" >
				<label>&nbsp;</label>
				</div>
    <div class="box-group ">
    <div class='col-sm-4'>
                <div class='form-group'>
                    <label for="quatationNO">Tax Group</label>
                     <select class="form-control" id="discount_type" name="discount_type"  style="padding:0px;">
                    <option value="per">&lt;--select--&gt;</option>
                    </select>
                  <div class="inner-icon">
	<a href="#" ><span class="glyphicon glyphicon-info-sign"></span></a>																							
	</div>
                </div>
            </div>
    </div>
            <div class='col-sm-2'>    
                    <label for="customer">&nbsp;</label>
                  <div>&nbsp;&nbsp;&nbsp;Rs</div>
		    
    </div>
      <div class='col-sm-2'>    
                <div class='form-group'>
                    <label for="customer">Rate</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" value="50" type="text" placeholder="Enter Rate"/>
		    
                </div>
    </div>
    </div>
    
  
	         
	         </div>
	         
	         <div class="col-sm-4">
  <div class="panel panel-default">
    <div class="panel-body">
        <div class="row row-no-margin">
     			<div class="col-md-1 col-xs-12" id="header">
				<label>#</label>
				</div>
				<div class="col-md-3 col-xs-12" id="header">
				<label>Item</label>
				</div>
					<div class="col-md-3 col-xs-12" id="header">
				<label>Qty</label>
				</div>
					<div class="col-md-2 col-xs-12" id="header">
				<label>Rate</label>
				</div>
					<div class="col-md-3 col-xs-12" id="header">
				<label>Value</label>
				</div>
    
    </div>
    
     <div class="row row-no-margin">
     			<div class="col-md-1 col-xs-12">
				1
				</div>
				<div class="col-md-3 col-xs-12">
				<label>Eva Sole</label>
			<a href="#demo" data-toggle="collapse">Eva Sole Natural</a>
				</div>
					<div class="col-md-3 col-xs-12 text-right">
				50
				</div>
					<div class="col-md-2 col-xs-12 text-right">
				100
				</div>
					<div class="col-md-3 col-xs-12 text-right">
				5000
				</div>
    
    </div>
    
      <div class="row row-no-margin">
      <div class="col-md-12">
    <div id="demo" class="collapse">
    <div class="row">
            <div class="col-md-0-5 col-xs-12" id="header">
				<label>&nbsp;</label>
				</div>
				<div class="col-md-2 col-xs-12" id="header">
				<label>Po No</label>
				</div>
					<div class="col-md-1-5 col-xs-12" id="header">
				<label>Dn Qty</label>
				</div>
					<div class="col-md-2 col-xs-12" id="header">
				<label>Recd Qty</label>
				</div>
					<div class="col-md-2 col-xs-12" id="header">
				<label>Accd Qty</label>
				</div>
				<div class="col-md-2 col-xs-12" id="header">
				<label>Tax Grp</label>
				</div>
				<div class="col-md-2 col-xs-12" id="header">
				<label>Tax Amt</label>
				</div>
				</div>
				<div class="row">
            <div class="col-md-0-5 col-xs-12">
				<label></label>
				</div>
				<div class="col-md-2 col-xs-12">
				<label>17.1516</label>
				</div>
					<div class="col-md-1-5 col-xs-12">
				<label>75</label>
				</div>
					<div class="col-md-2 col-xs-12">
				<label>70</label>
				</div>
					<div class="col-md-2 col-xs-12 ">
				<label>50</label>
				</div>
				<div class="col-md-2 col-xs-12">
				<label>&nbsp;</label>
				</div>
				<div class="col-md-2 col-xs-12">
				<label>&nbsp;</label>
				</div>
				</div>
  </div>
    </div>
    </div>
    
       <div class="row row-no-margin">
     			<div class="col-md-1 col-xs-12">
				2
				</div>
				<div class="col-md-3 col-xs-12">
				<label>Eva Sole</label>
			<a>Sole Sheet Black</a>
				</div>
					<div class="col-md-3 col-xs-12 text-right">
				100
				</div>
					<div class="col-md-2 col-xs-12 text-right">
				50
				</div>
					<div class="col-md-3 col-xs-12 text-right">
				5000
				</div>
    
    </div>
    
    
     <div class="row row-no-margin" style="margin-top:100px;">
       
    
    </div>
    
     <div class="row row-no-margin">
     <div id="det_info" style="text-align: right;">
            <div class="col-md-12 col-xs-12">
				<label>Total Items :2</label>
				</div>
			  <div class="col-md-12 col-xs-12">
				<label>Total Units :150</label>
				</div>
						  <div class="col-md-12 col-xs-12">
				<label>Total Value :10000</label>
				</div>
		</div>
				</div>
  </div>
</div>
	         </div>
	   </div>
	    -->
	     <div class="row trans-det-row"   >
	       	<div class="col-md-6 col-xs-12"  >
	       	<h4><label class="text-primary">Document Reference</label></h4>
	       	</div>
	       	
				
				<!--   	<div class="col-md-6 col-xs-12" >
				<button type="button" class="btn btn-primary" ><span>&nbsp;Display All</span></button>
			
				</div>  -->
	         </div>
	         
	<!--     <div class="row "  >
	    <h5><label class="text-primary">Document Reference</label></h5>
	         </div> -->
	         
	         
	           <div class="row "  >
	           <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="quatationNO">Invoice No</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" placeholder="Enter Invoice No"/>
                </div>
            </div>
           <div class='col-sm-3'>    
                <div class='form-group' >
                    <label for="quotationdate">Date</label>
		    <div class='input-group date' id='datepicker4' >
			    <input type='text' class="form-control" placeholder="Select Date" />
			    <span class="input-group-addon">
				<span class="glyphicon glyphicon-calendar"></span>
			    </span>
                </div>		                
                </div>
                
                <script type="text/javascript">
			
		      jQuery('#datepicker4').datepicker({
					    format: "dd-mm-yyyy"
					});  
	
        			</script>
        			
            </div>
            </div>
	           <div class="row"  >
	           <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="quatationNO">BE No</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" placeholder="Enter BE No"/>
                </div>
            </div>
           <div class='col-sm-3'>    
                <div class='form-group' >
                    <label for="quotationdate">Date</label>
		    <div class='input-group date' id='datepicker5' >
			    <input type='text' class="form-control" placeholder="Select Date" />
			    <span class="input-group-addon">
				<span class="glyphicon glyphicon-calendar"></span>
			    </span>
                </div>		                
                </div>
                
                <script type="text/javascript">
			
		      jQuery('#datepicker5').datepicker({
					    format: "dd-mm-yyyy"
					});  
	
        			</script>
        			
            </div>
             </div>
	            
	            
	                <div class="row"  style="margin-top:15px;" >
	       	<div class="col-md-6 col-xs-12" > 
	       	<h4><label class="text-primary">Other charges</label></h4> 
				</div>
				</div>
	    <!--          <div class="row "  >
	    <h5><label class="text-primary">Other charges</label></h5>
	         </div> -->
	         
	         
	          <div class="row"  style="margin-top:7px;" >
	          
	           <div class='col-sm-3'>
                <div class='form-group'>
                  <!--   <label for="quatationNO">Invoice No</label> -->
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" value="Charge1" placeholder=""/>
                </div>
            </div>
              <div class='col-sm-3'>
                <div class='form-group'>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" value="20" placeholder=""/>
                </div>
            </div>
            </div>   
             <div class="row"  >
	          
	           <div class='col-sm-3'>
                <div class='form-group'>
                  <!--   <label for="quatationNO">Invoice No</label> -->
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" value="Charge2" placeholder=""/>
                </div>
            </div>
              <div class='col-sm-3'>
                <div class='form-group'>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" value="5" placeholder=""/>
                </div>
            </div>
            </div>  
    <div class='row ' style="margin-top:40px;">
    			<div class='col-sm-10 col-xs-12 col-sm-offset-1'>
    			<div class='pull-right centered save-view'>            
                <div class="btn-group dropup">
                <button name="save" id="save"    class="btn btn-success ladda-button"  data-style="expand-right">
			    <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			    </button>
                <button    class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="height:34px;"><span class="caret"></span></button>
                <ul class="dropdown-menu">
            	<li><a href="#" >Save & Send</a></li>
            	<li><a href="#" >Save & New</a></li>
                <li class="divider"></li>
                <li><a  href="#">Save & Close</a></li>
                </ul>
              	</div>
                <button type="button" class="btn btn-primary"  onclick="showGoodsReceipt()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>	
            	</div>
                </div>      
    </div> 
     <div class='row'  style="margin-top:20px;">
	</div>

</div>
</div>        
</div>
<jsp:include page="../common/Footer.jsp"/>

 <input type="hidden" name="servlet_name" id="servlet_name" />
 <input type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
 <input type="hidden" name="pageno" id="pageno" />
 <input type="hidden" name="request_type" id="request_type" />
</form>
</body>
</html>