<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>TPCSFASHION</title>

<link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="${pageContext.request.contextPath}/css/jenix.css" rel="stylesheet"/>

<link type="text/css" href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" />
 
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
  <script  type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/common/bootstrap/bootstrap.min.js"></script>
<%-- <script language="javascript" src="${pageContext.request.contextPath}/js/prototype.js"></script>  --%>
<script language="javascript" src="${pageContext.request.contextPath}/js/login/login.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/common/common.js"></script> 
<script language="javascript">
	var contextpath = "${pageContext.request.contextPath}";
		var localizedStrings = {

					VALID_PASSWORD: "Enter the correct password!" ,
				    COMFIRM_PASSWORD: "Confirm password!" ,
				    VALID_QUESTION: "Enter the right question!" ,
				    VALID_ANSWER: "Enter the answer!" ,
				    VALID_DAYS: "Enter valid password expiry days!" 
			    	
			};	
	</script>
	<script type="text/javascript">
window.onload = function(){
    document.getElementById('changepassword').setAttribute('autocomplete', 'off'); 
}
</script>
</head>
<body >
 <form action="" method="post" id="changepassword" class="form-horizontal" role="form">

		<div id="wrapper">
		<div class="col-sm-6 col-sm-offset-3" style="margin-top:100px;">
		<div class="row">
		<div class="col-sm-12">
		<div class="form-group">
		<font color="red">${error_message}</font><font color="blue">${success_message}</font><font color="red"><div id="errormessage"></div></font>
		</div>
		</div>
		<div class="form-group">
            <label class="col-sm-3 control-label" >Old Password</label>
             <div class="col-sm-8">          
                    <input class="form-control" name="old_password" id="old_password" type="password" size="25" maxlength="25" placeholder="Enter old password"/>
		    
		     </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" >New Password</label>
             <div class="col-sm-8">          
                    <input class="form-control" name="new_password" id="new_password" type="password" size="25" maxlength="25" placeholder="Enter new password"/>
		    
		     </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" >Confirm Password</label>
             <div class="col-sm-8">          
            <input class="form-control" name="confirm_password" id="confirm_password" type="password" size="25" maxlength="25" placeholder="Enter confirm password"/>
		    
		     </div>
        </div>
        
        <div class="form-group">
            <label class="col-sm-3 control-label" >Security Question</label>
             <div class="col-sm-8">          
						<input class="form-control" name="security_question" id="security_question" type="text" size="25" maxlength="25" value="${sec_que}" placeholder="Enter security question"/>
		     </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" >Answer</label>
             <div class="col-sm-8">          
						<input class="form-control" name="answer" id="answer" type="text" size="25" maxlength="25" value="${sec_ans}" placeholder="Enter answer"/>
		     </div>
        </div>
        
        <div class="form-group">
            <label class="col-sm-3 control-label" >Password Expiry Days</label>
             <div class="col-sm-8">          
						<input class="form-control" name="days_to_exp" id="days_to_exp" type="text" size="25" maxlength="25" value="${no_days}" placeholder="Enter expiry days"/>
		     </div>
        </div>
        
        <div class="col-sm-12 col-sm-offset-4">        
        <input class="btn btn-primary"  type="button" name="save_chg_pwd" id="save_chg_pwd" value="Save" onclick="saveUsersExpiredPassword()"/>
        </div>
        </div>
        </div>
</div>
 <input type="hidden" name="servlet_name" id="servlet_name"/>
 <input type="hidden" name="callbackmethod_name" id="callbackmethod_name"/>
 <input type="hidden" name="notification_type_id" id="notification_type_id"/>
 <input type="hidden" name="change_pwd_type" id="change_pwd_type" value="${change_pwd_type}"/>
 <input type="hidden" name="user_login_name" id="user_login_name" value="${user_login_name}"/>
</form>
</body>
</html>