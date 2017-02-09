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
                    <h3 class="add-page-header" style="margin-left: -5px;">Storage Location</h3>
                </div>
               <!--  <div class="col-md-1 col-sm-4 col-xs-4 pull-right">
                        <img src="src/images/jenixerp.png" class="img-rounded" alt="Cinque Terre" width="150" height="60">						
                </div> -->
		</div>
        <div class='row'>
            <div class='col-sm-3 col-xs-12'>    
                <div class='form-group'>
                    <label for="BasicUnit">Storage Location</label>		   
                    <input class="form-control" id="BasicUnit" name="basicUnit" size="30" type="text" placeholder="Enter Storage Location"/>		  
                </div>
            </div>            
        </div>
        <div class='row'>            
            <div class='col-sm-3 col-xs-12'>
                <div class='form-group'>
                    <label for="Unit">Description</label>
                    <input class="form-control" id="Unit" name="unit" required="required" size="30" type="text" placeholder="Enter Description"/>
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
         
         
          <div class='row pull-left'  style="margin-bottom: 25px;">        	         	 
            <div class='col-sm-12 col-xs-12 centered'>               
                  	<div class="btn-group dropup">
					<button name="save" id="save"  onclick="#" class="btn btn-success ladda-button"  data-style="expand-right">
			     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			    </button>
                <button class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="height:34px;"><span class="caret"></span></button>
                <ul class="dropdown-menu">
						<li><a href="#">Save & New</a></li>
						<li class="divider"></li>
						<li><a href="#">Save & Close</a></li>
            
                </ul>
              </div>
                   <button type="button" class="btn btn-primary"  onclick="showStorageLocation()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>						             
            </div>            
    </div> 
            
   </div>
  </div> 
  <input type="hidden" name="servlet_name" id="servlet_name" value="MyServlet"/>
  <input type="hidden" name="callbackmethod_name" id="callbackmethod_name" value="addUOM"/>
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
  