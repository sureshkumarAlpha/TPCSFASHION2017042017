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
			</jsp:include><div id="page-wrapper">
<div id="page-inner">
        <div class="row">
                <div class="col-md-11 col-sm-8 col-xs-8">
                    <h3 class="add-page-header">Edit Module</h3>
                </div>
            
		</div>
        <div class='row'>
            <div class='col-sm-3 col-xs-12'>    
                <div class='form-group'>
                    <label for="BasicUnit">Module Name</label>	
                         <div class="required-field-block">	   
                    <input class="form-control" id="BasicUnit" name="basicUnit" size="30" type="text" placeholder="Enter Module Name"/>	
                      <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>	  
                </div>
            </div>            
        </div>
       
        <div class='row'>        	         	 
            <div class='col-sm-6 col-xs-12 centered'>               
                   <button type="button" class="btn btn-success" ng-click="formJSON()">Save</button>	
                    <button type="button" class="btn btn-danger" onclick="showReports()">Cancel</button>						             
            </div>
            <div class='col-sm-6 col-xs-12'>
            </div>	    
        </div>      
   </div>
  </div> 
  		</div>

		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> <input
			type="hidden" name="request_type" id="request_type" /> <input
			type="hidden" name="callbackmethod_name" id="callbackmethod_name" />

	</form>
</body>
</html>
  