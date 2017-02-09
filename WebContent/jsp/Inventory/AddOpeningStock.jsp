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
                    <h2 class="add-page-header" style="margin:18px 0 0px 20px;">Opening Stock</h2>
                </div>
                 </div>
                  
<div class="page-wrapper">
<div class="page-inner"> 
	<div class="row">
				<div class="col-sm-3">
		 		<div class='form-group'>
                <label for="customer">Warehouse</label>
		    	<div class="required-field-block">
                <input class="form-control" id="Warehouse" name="Warehouse" size="30" type="text" placeholder="Type and select Warehouse"/>
		    	<div class="required-icon">
				<div class="text">*</div>
		     	</div>
		     	</div>
                </div>
				</div>
				<div class="col-sm-3">
		 		<div class='form-group'>
                <label for="customer">Groups</label>
		    	<input class="form-control" id="Groups" name="Groups" size="30" type="text" placeholder="Type and select Groups"/>
		    	</div>
                </div>
                <div class="col-sm-3">
                <div class='form-group'>
                <label for="customer">Itemcode </label>
		   		<div class="required-field-block">
                <input class="form-control" id="Itemcode " name="Itemcode " size="30" type="text" placeholder="Type and select Itemcode"/>
		    	<div class="required-icon">
				<div class="text">*</div>
		     	</div>
		     	</div>
                </div>
                </div>
	</div>
 	<div class="row">
              	<div class="col-sm-3">
                <div class='form-group'>
                <label for="customer">Description</label>
		    	<input class="form-control" id="Description" name="Description" size="30" type="text" placeholder="Type and select Description"/>
		    	</div>
                </div>
                <div class="col-sm-3">
                <div class='form-group'>
                <label for="customer">Variant</label>
		    	<input class="form-control" id="Variant" name="Variant" size="30" type="text" placeholder="Type and select Variant"/>
		    	</div>
                </div>
                <div class='col-sm-3'>    
               	 <div class='form-group' >
                 <label for="quotationdate">  Stock Date</label>
		    	 <div class='input-group date' id='datepicker' >
		    	 <div class="required-field-block">
			     <input type='text' class="form-control" placeholder="Select Stock Date" />
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
	<div class="row">
				<div class="col-sm-3">
                <div class='form-group'>
                <label for="customer"> Opening Stock </label>
		    	<div class="required-field-block">
                <input class="form-control" id="Opening_Stock " name="Opening_Stock " size="30" type="text" placeholder="Type and select  Opening Stock "/>
		    	<div class="required-icon">
				<div class="text">*</div>
		     	</div>
		     	</div>
                </div>
                </div>
                <div class="col-sm-3">
                <div class='form-group'>
                <label for="customer">Rate  </label>
		    	<div class="required-field-block">
                <input class="form-control" id="Rate  " name="Rate  " size="30" type="text" placeholder="Type and select  Rate  "/>
		    	<div class="required-icon">
				<div class="text">*</div>
		     	</div>
		     	</div>
                </div>
                </div>
                 <div class="col-sm-3">
                <div class='form-group'>
                <label for="customer"> Itracking No  </label>
		        <input class="form-control" id=" Itracking_No  " name=" Itracking_No  " size="30" type="text" placeholder="Type and select   Itracking No  "/>
		    	</div>
                </div>
	</div>
   	<div class="row">
   	 			<div class="col-sm-3">
                <div class='form-group'>
                <label for="customer">  Supplier  </label>
		    	<input class="form-control" id="  Supplier  " name=" Supplier " size="30" type="text" placeholder="Type and select  Supplier "/>
		    	</div>
                </div>
                <div class='col-sm-3'>    
               	 <div class='form-group' >
                 <label for="quotationdate"> Expiry Date</label>
		    	 <div class='input-group date' id='datepicker1' >
		    	 <input type='text' class="form-control" placeholder="Select  Expiry Date" />
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
	
    <div class='row 'style="margin-top:50px;">
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
                <button type="button" class="btn btn-primary"  onclick="showOpeningStock()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>	
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