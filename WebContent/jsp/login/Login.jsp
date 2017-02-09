<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<meta content="TPCSFASHION Customized ERP Software For Footwear, Apparel, Accessories and Tannery" name="description">
<meta content="TPCSFASHION, collaborative tool, apparel, footwear, Accessories and Tannery" name="keywords">
<meta content="concrete5 - 5.6.1.2" name="generator">

<link sizes="16x16 32x32 64x64" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
<link sizes="16x16 32x32 64x64" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico" rel="shortcut icon">
<title>TPCSFASHION</title>

<link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
<link  rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/custom-styles.css" rel="stylesheet"/>
<link type="text/css" href="${pageContext.request.contextPath}/css/jenix.css" rel="stylesheet"/>

<link type="text/css" href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" />
<link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-button-progress.css" rel="stylesheet" />
 
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
  <script  type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/common/bootstrap/bootstrap.min.js"></script>
<%-- <script language="javascript" src="${pageContext.request.contextPath}/js/prototype.js"></script>  --%>
<script language="javascript" src="${pageContext.request.contextPath}/js/login/login.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/common/common.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/common/bootstrap/bootstrap-button-progress.js"></script> 
<script language="javascript">
var contextpath = "${pageContext.request.contextPath}";
function clearLogin(){
	document.getElementById('login_name').value="";
	document.getElementById('password').value="";
}


</script>

<style>
body{
    background: #f9f9f9;
}
#page-wrapper{
background:transparent;
}
.sign-up-xs-out,
.sign-up-sm-out,
.sign-up-md-out, 
.sign-up-lg-out {
    border: 1px solid #eee;
    padding: 10px;
    background: #fff;
    border-radius: 5px;
}
.sign-up-xs-out{
    margin-top: 15%;
}


@media (min-width: 768px) {
    .sign-up-sm-out{
        margin-top: 8%;
    }
}

@media (min-width: 992px) {
    .sign-up-md-out{
        margin-top: 8%;
    }
}
@media (min-width: 1200px) {
    .sign-up-lg-out{
        margin-top: 8%;
    }
}
#page-wrapper {
    background: transparent;
}
#page-inner {
    width: 100%;
    background-color: transparent;
    padding: 10px;
    margin: 0px;
}

</style>
<% 
String scheme=(String)request.getScheme();
String server=(String)request.getServerName();
String contextPath=(String)request.getContextPath();
String serverContext=server+contextPath;
String schemeServerContext=scheme+"://"+serverContext;
%>
</head>
<body onLoad="getUserName()">
 <form action="" method="post" id="loginform" role="form">

<div id="wrapper">

			<div id="page-wrapper">
				<div id="page-inner">
				
				<div class=" col-xs-12 col-sm-7 col-md-7 col-lg-5 col-centered sign-up-xs-out sign-up-sm-out sign-up-md-out sign-up-lg-out">
				
					<div class="row">
								<div class="col-xs-12 col-sm-6 col-md-6 col-centered ">
									<img src="${pageContext.request.contextPath}/images/tpcsfashion.png" class="img-rounded img-responsive col-centered " alt="TPCS Fashion" height="50">
								</div>
					</div>
				
					<div class="row row-no-margin">
						<div id="message" class="text-center"></div>
					</div>
				

					<div class="row row-no-margin">
								
								<div class="col-xs-12 col-sm-8 col-centered">
									<label for="login_name">Login name </label>
									<div class="form-group ">
										<div class="input-group ">
											<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
											<input class="form-control" id="login_name" name="login_name" autofocus maxlength="50" type="text" placeholder="Enter login name" onkeypress="enterKeyPress(event,'login');"/>
							        	</div>
							        </div>
								</div>
								
					</div>

					<div class="row row-no-margin">
								 
								<div class="col-xs-12 col-sm-8 col-centered">
									<label for="organization">Password</label>
									<div class="form-group "> 
										<div class="input-group ">
											<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
											<input class="form-control" id="password" name="password" maxlength="25" type="password" placeholder="Enter password" onkeypress="enterKeyPress(event,'login');"/>
										</div>
									</div>	
								</div>
					</div>
					
					<div class="row row-no-margin ">
						<div class="col-xs-6 col-sm-4 col-sm-offset-2 ">
							<div class="checkbox ">
								<input class="checkbox_1" type="checkbox" id="remember_me" name="remember_me" onkeypress="enterKeyPress(event,'login');" checked="checked"><label for="remember_me" class="checkbox_1">Remember me!</label>
	           				</div>
	           			</div>
	           			<div class="col-xs-6 col-sm-4">
	           				<div class="checkbox pull-right ">
								<a  href="javascript:;" onkeypress="enterKeyPress(event,'login');" onClick="forgotPassword()">Forgot Password?</a>
							</div>
	           			</div>
						
					</div>	
					
					<div class="row row-no-margin " style="    margin-bottom: 20px;	">
						<div class="col-xs-6 col-sm-4 col-sm-offset-2 ">
							<button type="button" name="login" id="login" class="btn btn-success pull-left ladda-button btn-block" data-style="expand-right" onclick="validateUserCredentials()" onkeypress="enterKeyPress(event,'login');">
								<span class="ladda-label"> <i class="fa fa-sign-in "></i></span>&nbsp;Login
							<span class="ladda-spinner"></span></button>
						</div>
					
						<div class="col-xs-6 col-sm-4">
							<button type="reset" class="btn btn-primary pull-right btn-block" onclick="clearLogin()">
								<span> <i class="fa fa-times"></i></span>&nbsp;Clear
							</button>
						</div>

					</div>
					
					<div class="row row-no-margin " style="font-size: 10px; text-align: center;    margin-bottom: 20px;">
						<p style="margin: 0px;">TERMS & CONDITIONS | PRIVACY POLICY | OUR AFFILIATE PROGRAM</p>
						<p style="margin: 0px;">© COPYRIGHTS JENIXCLOUD SOFTWARE SOLUTIONS PVT LTD. ALL RIGHTS RESERVED 2016</p>

					</div>
					
				</div>
			</div>
		</div>
	</div>


		<%-- <div id="wrapper">

			<div class="container">
				<div id="loginbox" style="margin-top: 100px;"
					class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
					<div class="panel panel-primary" style="border-radius: 15px;">

						<div class="panel-body">

							<div class="row">
								<div class="col-xs-12 col-sm-9 col-sm-offset-1 col-md-9 col-md-offset-1"
									style="text-align: center;">
									<img
										src="${pageContext.request.contextPath}/images/tpcsfashion.png"
										class="img-rounded img-responsive" alt="Cinque Terre" height="80" />
								</div>
							</div>
							<div class='row'>
								<div id="message" style="text-align: center;">&nbsp;</div>
							</div>

							<div class="row">
								<div class="col-sm-10 col-xs-10 col-sm-offset-1 col-md-offset-1">
									<label for="tag">User name</label>
									<div style="margin-bottom: 25px" class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-user"></i></span> <input id="login_name"
											type="text" class="form-control" name="login_name" value=""
											placeholder="Username"
											onkeypress="enterKeyPress(event,'login');" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-10 col-xs-10 col-sm-offset-1 col-md-offset-1">
									<label for="tag">Password</label>
									<div style="margin-bottom: 25px" class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-lock"></i></span> <input id="password"
											type="password" class="form-control" name="password"
											placeholder="Password"
											onkeypress="enterKeyPress(event,'login');" />
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-6 col-xs-12 col-sm-offset-1 col-md-offset-1">
									<span class="button-checkbox"> <input type="checkbox"
										name="remember_me" id="remember_me"
										onkeypress="enterKeyPress(event,'login');" checked="checked" />&nbsp;<span
										class="tab">Remember me!</span>
									</span>
								</div>

								<div class="col-sm-4" class="col-xs-12">
									<span class="button-checkbox"> <a href="javascript:forgotPassword()"
										class="btn btn-link pull-right">Forgot Password?</a>
									</span>
								</div>
							</div>


							<div style="margin-top: 10px; text-align: center;"
								class="form-group">
								<!-- Button -->

								<div class="col-sm-12 controls">
									<div class="col-sm-4 col-xs-12 col-md-offset-2 ">
									<a id="login" onclick="validateUserCredentials()" class="btn btn-success btn-block ladda-button"  data-style="expand-right">
								        <span class="ladda-label"> Login</span>
								    </a>
								    </div>
									<div class="col-sm-4 col-xs-12">
										<a id="clear" onclick="clearLogin()" class="btn btn-primary btn-block">Clear</a>
									</div>

								</div>
							</div>

							<div class="row" style="font-size: 10px; text-align: center;">
								<p style="margin: 0px;">TERMS & CONDITIONS | PRIVACY POLICY
									| OUR AFFILIATE PROGRAM</p>
								<p style="margin: 0px;">© COPYRIGHTS ALPHA SYSTEMS PVT LTD.
									ALL RIGHTS RESERVED 2012</p>

							</div>
						</div>
					</div>
				</div>
			</div> --%>
			
			
			<div class="form-horizontal trans-form">
			<div class="modal fade" id="changePwdModal" role="dialog">
    <div class="modal-dialog" >
    
      <!-- Modal content-->
      <div class="modal-content" >
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Secutity Question</h4>
        </div>
        <div class="modal-body">
		<div id="message"></div>
		
		<div class="row">
		<div class="form-group">
            <label class="col-sm-3 control-label" >Security Question</label>
             <div class="col-sm-8">          
                    <input class="form-control" type="text" name="question" id="question" value="${security_question}" readonly="readonly" placeholder="Security Question"/>
		    
		     </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" >Answer</label>
             <div class="col-sm-8">          
                    <input class="form-control" type="text" name="answer" id="answer" value="" placeholder="Answer"/>
		    
		     </div>
        </div>  
        
        </div>

</div>
        <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="checkAnswer()">Save</button>	
         <button type="button" class="btn btn-danger" id="group_close"  data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
    			<input type="hidden" name="tmp_security_ans" id="tmp_security_ans" value="${security_answer}" />
			    <input type="hidden" name="user_id" id="user_id" value="${user_id}" />
			     <input type="hidden" name="client_id" id="client_id" value="${client_id}" />
  </div>
			</div>
			

<input type="hidden" id="change_question" data-toggle="modal" data-target="#changePwdModal"  />
<input type="hidden" name="servlet_name" id="servlet_name"/>
<input type="hidden" name="callbackmethod_name" id="callbackmethod_name"/>
<input type="hidden" name="pageno" id="pageno" value="1"/>
</form>
</body>
</html>

