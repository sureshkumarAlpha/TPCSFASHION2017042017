<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message bundle="${bundle}" key="Title.Title" /></title>
<jsp:include page="../common/Header.jsp" />
<style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
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
<div class="row-no-margin row">
                <div class="col-md-11 col-sm-8 col-xs-12">
                    <h2 class="add-page-header" style="margin:18px 0 0px 20px;">Goods Receipt (GR)</h2>
                </div>
                 </div>
                  
<div class="page-wrapper">
<div class="page-inner"> 

                      <div class='row'>
            <div class='col-sm-3'>    
                <div class='form-group'>
                    <label for="customer">GRN No</label>
                    <input class="form-control" id="orderno" name="orderno" size="30" type="text" placeholder="select GRN No"/>
		    
                </div>
            </div>
            <div class='col-sm-3'>    
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
            
             <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="quatationNO">Supplier</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" placeholder="Type and select supplier"/>
                </div>
            </div>
               <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="quatationNO">Warehouse</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" placeholder="Enter Warehouse"/>
                </div>
            </div> 
           
	    
        </div>	
 	      <div class='row'>   
 	      
 	        <div class='col-sm-3'>    
                <div class='form-group' >
                    <label for="quotationdate">LR Date</label>
		    <div class='input-group date' id='datepicker1' >
			    <input type='text' class="form-control" placeholder="Select Date" />
			    
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
                      
            <div class='col-sm-3'>
                <div class='form-group'>
                   <label for="tag">Currency</label>
			<select class="form-control" id="sel1">
			    <option>Select Currency</option>
			    <option>Dollor</option>
			    <option>Rupees</option>					  
			  </select>
                </div>
            </div>
            
            <div class='col-sm-3'>    
                <div class='form-group'>
                    <label for="quotationdate">Exchange Rate</label>
                    <input class="form-control" id="quotationdate" name="quotationdate" size="30" type="text" placeholder="Select Exchange Rate"/>
                </div>
            </div>
          
           <div class='col-sm-3'>    
                <div class='form-group'>
                    <label for="quotationdate">Customs duty</label>
                    <input class="form-control" id="quotationdate" name="quotationdate" size="30" type="text" placeholder="Enter Customs duty"/>
                </div>
            </div>
	    
        </div>
          <div class='row'>    
           
            <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="quatationNO">LR No</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" placeholder="Enter LR No"/>
                </div>
            </div>
                <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="quatationNO">DN No</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" placeholder="Enter DN No"/>
                </div>
            </div>  
            <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="sales_person">Other Charges</label>
                    <input class="form-control" id="sales_person" name="sales_person" required="required" size="30" type="text" placeholder="Enter Other Charges"/>
                </div>
            </div>
             <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="quatationNO">Service Charges</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" placeholder="Enter Service Charges"/>
                </div>
            </div>
            
        </div>
           <div class='row'>    
           
           <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="quatationNO">Freight</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" placeholder="Enter Freight"/>
                </div>
            </div>
            <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="quatationNO">Transporter</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" placeholder="Enter Transporter"/>
                </div>
            </div>
            
             <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="quatationNO">Insurance</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" placeholder="Enter Insurance"/>
                </div>
            </div>
             <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="quatationNO">Packing Charges</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" placeholder="Enter Packing Charges"/>
                </div>
            </div>
        </div> 
          <div class='row'>    
         <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="quatationNO">Invoice No.</label>
                    <input class="form-control" id="quatationNO" name="quatationNO" size="30" type="text" placeholder="Enter Invoice No"/>
                </div>
            </div>   
             <div class='col-sm-3'>    
                <div class='form-group' >
                    <label for="quotationdate">Invoice Date</label>
		    <div class='input-group date' id='datepicker3' >
			    <input type='text' class="form-control" placeholder="Select Date" />
			    
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
             <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="quatationNO">Purchase Request No</label>
                    <input class="form-control" id="Purchase_Request_No" name="Purchase_Request_No" size="30" type="text" placeholder="Enter Purchase Request No"/>
                </div>
            </div> 
            
            
            
        </div>
        
      <div class="row" style="margin-top:50px;">
     			<div class="col-md-0-5 col-xs-12" id="header">
				<label>#</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
				<label>PO NO</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
				<label>Indent NO</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
				<label>IO NO</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
				<label>Item</label>
				</div>	
				<div class="col-md-1 col-xs-12" id="header">
				<label>Colour</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
				<label>UOM</label>
				</div>	
				<div class="col-md-1-5 col-xs-12" id="header">
				<label>Quantity As Per DC</label>
				</div>
				<div class="col-md-1-5 col-xs-12" id="header">
				<label>Received Quantity</label>
				</div>
				<div class="col-md-1-5 col-xs-12" id="header">
				<label>Accepted Quantity</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
				<label>Rate</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
				<label>Lot No</label>
				</div>	
				<div class="col-md-2 col-xs-12" id="header">
				<label>Description</label>
				</div>
     </div>   
      <div class="row linerow">
		       <div class="col-md-0-5 col-xs-12">
		        <div class="form-group grid-slno">1</div>
		        </div>
		        <div class="col-md-1 col-xs-12">
		        <input class="form-control" id="po_no" name="po_no" type="text" placeholder="Po No"/>
		        </div>
		         <div class="col-md-1 col-xs-12">
		        <input class="form-control" id="indent_no" name="indent_no" type="text" placeholder="Indent No"/>
		        </div>
		         <div class="col-md-1 col-xs-12">
		        <input class="form-control" id="io_no" name="io_no" type="text" placeholder="Io No"/>
		        </div>
		        <div class="col-md-1 col-xs-12" >
				<input class="form-control" id="Item" name="Item" type="text" placeholder="Item "/>
				</div>
				 <div class="col-md-1 col-xs-12" >
				<input class="form-control" id="colour" name="colour" type="text" placeholder="Colour "/>
				</div>
				<div class="col-md-1 col-xs-12" >
				<input class="form-control" id="UOM" name="UOM" type="text" placeholder="UOM "/>
				</div>
		       <div class="col-md-1-5 col-xs-12" >
				<input class="form-control" id="Quantity_As_Per_DC" name="Quantity_As_Per_DC" type="text" placeholder="Quantity As Per DC "/>
				</div>
				<div class="col-md-1-5 col-xs-12" >
				<input class="form-control" id="Received_Quantity" name="Received_Quantity" type="text" placeholder="Received Quantity "/>
				</div>
				<div class="col-md-1-5 col-xs-12" >
				<input class="form-control" id="Accepted_Quantity" name="Accepted_Quantity" type="text" placeholder="Accepted Quantity "/>
				</div>
				<div class="col-md-1 col-xs-12" >
				<input class="form-control" id="Rate" name="Rate" type="text" placeholder="Rate "/>
				</div>
				<div class="col-md-1 col-xs-12" >
				<input class="form-control" id="Lot_No" name="Lot_No" type="text" placeholder="Lot No "/>
				</div>
				<div class="col-md-2 col-xs-12" >
				<input class="form-control" id="Description" name="Description" type="text" placeholder="Description"/>
				</div>
																
	</div>
	<div class="row" style="margin-top:50px;">
     	<div class="col-md-1 col-xs-12" id="header">
			<label>Size</label>
			</div>
			<div class="col-md-1 col-xs-12" id="header">
					<label>7</label>
				</div>
				<div class="col-md-1  col-xs-12" id="header">
					<label>7.5</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>8</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>8.5</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>9</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>9.5</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>10</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>10.5</label>
				</div>
				<div class="col-md-1 col-xs-12" id="header">
					<label>11</label>
				</div>
     </div>
     
	 <div class="row ">
	
     <div class="col-md-1 col-xs-12" style="text-align: center;">
			<label>Qty</label>
			</div>
			<div class="col-md-1 col-xs-12" >
				 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="10"/>
				</div>
				<div class="col-md-1  col-xs-12" >
				 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="15"/>
				</div>
				<div class="col-md-1 col-xs-12" >
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="20"/>
				</div>
				<div class="col-md-1 col-xs-12" >
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="25"/>
				</div>
				<div class="col-md-1 col-xs-12" >
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="25"/>
				</div>
				<div class="col-md-1 col-xs-12" >
				 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="20"/>
				</div>
				<div class="col-md-1 col-xs-12" >
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="10"/>
				</div>
				<div class="col-md-1 col-xs-12" >
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="15"/>
				</div>
				<div class="col-md-1 col-xs-12" >
					 <input class="form-control" id="Qty" name="Qty" type="text" placeholder="Qty" value="15"/>
				</div>
	 </div>
	
    <div class='row 'style="margin-top:40px;">
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