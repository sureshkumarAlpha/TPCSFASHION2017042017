<%@ page import="com.alpha.tpcsfashion.beans.TPCSUser" %>
<script language="javascript">
var contextpath = "${pageContext.request.contextPath}";
</script>

 <%-- <div id="login_form" > 
  <div class="modal fade" id="loginModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" id="close_login_modal" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Login</h4>
        </div>
        <div class="modal-body">
  
        
			<div class="container">
				<div id="loginbox" 
					class="mainbox col-md-6 col-sm-8 ">
					<div class="panel panel-primary" style="border-radius: 15px;">
						<!-- <div class="panel-heading">
								<div class="panel-title">Sign In</div>
								<div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">Forgot password?</a></div>
							</div> -->

						<div class="panel-body">
							<form>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12"
									style="text-align: center;">
									<img
										src="${pageContext.request.contextPath}/images/jenixerp.png"
										class="img-rounded" height="80" />
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
											onkeypress="enterKeyPress(event,'reset_login');" />
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
											onkeypress="enterKeyPress(event,'reset_login');" />
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-6 col-xs-6 col-sm-offset-1 col-md-offset-1">
									<span class="button-checkbox"> <input type="checkbox"
										name="remember_me" id="remember_me"
										onkeypress="enterKeyPress(event,'reset_login');" checked="checked" />&nbsp;<span
										class="tab">Remember me!</span>
									</span>
								</div>

								<div class="col-sm-4" class="col-xs-4">
									<span class="button-checkbox"> <a href="#forgotpassword"
										class="btn btn-link pull-right">Forgot Password?</a>
									</span>
								</div>
							</div>


							<div style="margin-top: 10px; text-align: center;"
								class="form-group">
								<!-- Button -->

								<div class="col-sm-12 controls">
									<div class="col-sm-4 col-xs-4 col-md-offset-2 col-xs-offset-1">
										<a id="reset_login" onclick="resetLoginvalidateUserCredentials()"
											class="btn btn-success btn-block">Login </a>
									</div>
									<div class="col-sm-4 col-xs-4">
										<a id="clear" onclick="clearLogin()"
											class="btn btn-primary btn-block">Clear</a>
									</div>

								</div>
							</div>

							<div class="row" style="font-size: 10px; text-align: center;">
								<p style="margin: 0px;">TERMS & CONDITIONS | PRIVACY POLICY
									| OUR AFFILIATE PROGRAM</p>
								<p style="margin: 0px;">© COPYRIGHTS ALPHA SYSTEMS PVT LTD.
									ALL RIGHTS RESERVED 2012</p>


							</div>
							  <input type="hidden" id="submit-login" data-toggle="modal" data-target="#loginModal">
							</form>
						</div>
					</div>
				</div>
			</div>
        
        </div>
        
      </div>
      
    </div>
  </div>

</div>  --%>

<%
	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	if(userInfo.getDisplayUserName()==null){
%>
<!-- <script language="javascript">
document.getElementById('submit-login').click();
</script> -->

<jsp:forward page="../login/Login.jsp" /> 
<%	
	}
%>