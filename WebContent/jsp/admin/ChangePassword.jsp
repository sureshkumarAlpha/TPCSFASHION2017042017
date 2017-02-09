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
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/admin/user.js"></script>

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
		var localizedStrings = {

					VALID_PASSWORD: "<fmt:message bundle="${bundle}" key="Password.CorrectPassword"/>" ,
				    COMFIRM_PASSWORD: "<fmt:message bundle="${bundle}" key="Password.ComfirmPassword"/>" ,
				    VALID_QUESTION: "<fmt:message bundle="${bundle}" key="Password.ValidQuestion"/>" ,
				    VALID_ANSWER: "<fmt:message bundle="${bundle}" key="Password.GiveAns"/>" ,
				    VALID_DAYS: "<fmt:message bundle="${bundle}" key="Password.ValidDays"/>" 
			    	
			};	
	</script>
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body onload="display()">
<c:set var="is_right_have" value="${1}"/>
	<form action="" id="tpcslogin" method="post" role="form">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>
			
			
			<div class="top-bar">
           <div class="row">
             <div class="col-md-11 col-sm-8 col-xs-12">
                 <h2 class="add-page-header" ><fmt:message bundle="${bundle}"  key="Password.ChangePassword"/></h2>
             </div>
        </div>
        
<div id="page-wrapper" class="add-top-wrapper"> 
   <div id="page-inner">   
	 <div class='row'>
            <div class='col-sm-3'>    
                <div class='form-group'>
                    <label ><fmt:message bundle="${bundle}" key="Password.OldPassword" /></label>
                    <div class="required-field-block">
					<input class="form-control" name="old_password" id="old_password" type="password" size="25" maxlength="25" placeholder="<fmt:message bundle="${bundle}" key="Password.OldPassword" />"/>
					<div class="required-icon">
										<div class="text">*</div>
									</div>
									
								</div>
					
                </div>
        </div>
        <div class='col-sm-3'>    
                <div class='form-group'>
                    <label ><fmt:message bundle="${bundle}" key="Password.NewPassword" /></label>
                    <div class="required-field-block">
						<input class="form-control" name="new_password" id="new_password" type="password" size="25" maxlength="25" placeholder="<fmt:message bundle="${bundle}" key="Password.NewPassword" />"/>
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
<div class='row'>
        <div class='col-sm-3'>    
                <div class='form-group'>
                    <label ><fmt:message bundle="${bundle}" key="Password.ConfirmPassword" /></label>
                    <div class="required-field-block">
						
						<input class="form-control" name="confirm_password" id="confirm_password" type="password" size="25" maxlength="25" placeholder="<fmt:message bundle="${bundle}" key="Password.ConfirmPassword" />"/>
					<div class="required-icon">
										<div class="text">*</div>
									</div>
									
								</div>
					
                </div>
        </div>
        
        <div class='col-sm-3'>    
                <div class='form-group'>
                    <label ><fmt:message bundle="${bundle}" key="Password.SecurityQuestion" /></label>
                    <div class="required-field-block">
						
						<input class="form-control" name="security_question" id="security_question" type="text" size="25" maxlength="25" value="${sec_que}" placeholder="<fmt:message bundle="${bundle}" key="Password.SecurityQuestion" />"/>
					<div class="required-icon">
										<div class="text">*</div>
									</div>
									
								</div>
					
                </div>
        </div>
        </div>
        <div class='row'>
        <div class='col-sm-3'>    
                <div class='form-group'>
                    <label ><fmt:message bundle="${bundle}" key="Password.Answer" /></label>
                    <div class="required-field-block">
						<input class="form-control" name="answer" id="answer" type="text" size="25" maxlength="25" value="${sec_ans}" placeholder="<fmt:message bundle="${bundle}" key="Password.Answer" />"/>
					<div class="required-icon">
										<div class="text">*</div>
									</div>
									
								</div>
					
                </div>
        </div>
        
        <div class='col-sm-3'>    
                <div class='form-group'>
                    <label ><fmt:message bundle="${bundle}" key="Password.ExpiryDays" /></label>
                    <div class="required-field-block">
                    <input class="form-control" name="days_to_exp" id="days_to_exp" type="text" size="25" maxlength="25" value="${no_days}" placeholder="<fmt:message bundle="${bundle}" key="Password.ExpiryDays" />"/>
					
					<div class="required-icon">
										<div class="text">*</div>
									</div>
									
								</div>
					
                </div>
        </div>
        </div>
        
        
        
        	<div class='row pull-left'>        	         	 
            <div class='col-sm-12 col-xs-12 centered' style="margin-bottom: 10px;">  
            <c:if test="${rights.addPermission == is_right_have}">    
             <button name="save_color" id="save_color"   class="btn btn-success ladda-button" value="<fmt:message bundle="${bundle}" key="Common.Save" />"onclick="saveUsersPassword()" data-style="expand-right">
				<span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			</button>   
				</c:if>      
			    <button type="button" class="btn btn-danger" name="clear" id="clear"  onclick="clearColors(1)"><i class="glyphicon glyphicon-remove-circle"></i><span>&nbsp;<fmt:message bundle="${bundle}" key="Common.Clear" /></span></button>					                   
            </div>            
    </div>
    
     <%--    <div class='row pull-right'>        	         	 
            <div class='col-sm-12 col-xs-12 centered'>               
		  		<c:if test="${rights.addPermission == is_right_have}">
                    <input class="btn btn-primary"   type="button" name="save_color" id="save_color" value="<fmt:message bundle="${bundle}" key="Common.Save" />" onclick="saveUsersPassword()"/>
				</c:if>           
				<input class="btn btn-primary"  type="button" name="clear" id="clear" value="<fmt:message bundle="${bundle}" key="Common.Clear" />" onclick="clearColors(1)"/>
            </div>         
    	</div> --%>
        
        
						
         
     </div>  
    </div>
</div>

		<jsp:include page="../common/ColumnPreferences.jsp" />
		<jsp:include page="../common/Footer.jsp" />

<input type="hidden" name="servlet_name" id="servlet_name" /> 
<input type="hidden" name="request_type" id="request_type" />
<input type="hidden" name="pageno" id="pageno" />
<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
<input type="hidden" name="notification_type_id" id="notification_type_id"/>
</form>
</body>
</html>        				         