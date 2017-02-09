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
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/masters/hierarchy.js"></script>
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
	<form action="" id="validate-CstCntrs" method="post" role="form">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>

<div id="page-wrapper" onclick="document.getElementById('rightmenu').style.display = 'none'; ">
<div id="page-inner">
         <div class="row">
                <div class="col-md-11 col-sm-8 col-xs-8">
                    <h3 class="page-header"><i class="indicator glyphicon glyphicon-folder-open folder_col" ></i>Cost Centers</h3>
                </div>	
         </div>

</div>

<div  oncontextmenu="return false">
${costcenters_list}
</div>
<div style="display:none;"   id="rightmenu" class="rightmenu" onclick="HideRightMenu('rightmenu')" oncontextmenu="return false">
<ul id="right-menu_ul">

</ul>
</div>



	</div>
	
		<!--  --------------------------  -->
		
					<%-- <jsp:include page="../masters/AddHierarchy.jsp" /> --%>
			<!--  --------------------------  -->
			
			
  



	
	
  </div>  
  	<jsp:include page="../masters/AddHierarchy.jsp" />
		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> <input
			type="hidden" name="request_type" id="request_type" /> <input
			type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
			<input type="hidden" name="pageno" id="pageno"  />
			<input type="hidden" name="division_id" id="division_id" /> 
		<input type="hidden" name="costcenter_id" id="costcenter_id" /> 
			
  <input type="hidden" name="mode" id="mode" value="${mode}"/>
  <input type="hidden" name="idx" id="idx" /> 
	<script type="text/javascript">
			$('#validate-CstCntrs').bootstrapValidator({
				//  live: 'disabled',
				  message: 'This value is not valid',
				  feedbackIcons: {
				      valid: 'glyphicon glyphicon-ok',
				      invalid: 'glyphicon glyphicon-remove',
				      validating: 'glyphicon glyphicon-refresh'
				  },
				  fields: {
					  division_name: {
			              validators: {
			                  notEmpty: {
			                      message: 'This field is required'
			                  }
			              }
			          } ,costcenter_name: {
			              validators: {
			                  notEmpty: {
			                      message: 'This field is required'
			                  }
			              }
			          },
			          costcenter_code: {
			              validators: {
			                  notEmpty: {
			                      message: 'This field is required'
			                  }
			              }
			          }
				  }
				});
			</script>
	</form>
</body>
</html>