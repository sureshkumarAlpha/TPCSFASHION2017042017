

/*
function validateUserCredentials(){

 if(document.getElementById('remember_me').checked)
 {
 
	setCookie('jenixerpcookie',document.getElementById('login_name').value,30,'/','','');
 }
 		var login_name = $('login_name').value;
	    var password = $('password').value;
	    var Password_reset ='0';
	    var forgot_reset_password ='no';
	    var url=contextpath+"/LoginServlet";
	    
	    var tz=(new Date().getTimezoneOffset());
		var params = "login_name="+login_name+"&password="+password+"&Password_reset="+Password_reset+"&forgot_reset_password="+forgot_reset_password+"&user_timezone="+tz;				
		var myAjax = new Ajax.Request(url,{method: 'POST',parameters:params,onSuccess:displayCompanyAndYear,onFailure:displayError});
	
}*/

function validateUserCredentials(){
	 if(document.getElementById('remember_me').checked){
		setCookie('jenixerpcookie',document.getElementById('login_name').value,30,'/','','');
	 }

	var login_name = jQuery('#login_name').val();
	var password = jQuery('#password').val();
	var Password_reset ='0';
	var forgot_reset_password ='no';
    var tz=(new Date().getTimezoneOffset());
	var url =contextpath+"/LoginServlet";
	
	var request = jQuery.ajax({
	url: url,
	type: "POST",
	data: {login_name:login_name , password:password,Password_reset:Password_reset,forgot_reset_password:forgot_reset_password,user_timezone:tz},
	dataType: "xml"
	});
	request.done(function(xml) {
		jQuery(xml).find('login_credential').each(function(){
			msg=jQuery(this).find('valid_user').text();
	    	 yearSelection=jQuery(this).find('year_selection').text();
	    	 
	    	 if(msg=="Expired"){
//	 			alert("password expired..contact your admin...");
//	 			jQuery('#login_name').val = "";
//	 			jQuery('#password').val= "";
	    		 changePwd();
	 		}else if(msg=="Invalid"){
	 			jQuery('#message').html("<font color='red'>Username or Password are incorrect!</font>");
	 			jQuery('#login_name').val("");
	 			jQuery('#password').val("");
	 		}
	 		else if(message=="InActive"){
	 			jQuery('#message').html("<font color='red'>Not an active User.</font>");
	 		}
	 		else{
	 			if(yearSelection==0){
	 				document.getElementById('servlet_name').value="CompanyAndYearSelectionServlet";
		 			document.getElementById('callbackmethod_name').value="doDisplayCompanyAndYear";
		 			document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		 			document.forms[0].method="POST";	
		 			document.forms[0].submit();
	 				
	 			}
	 			else if(yearSelection==1){
	 				document.getElementById('servlet_name').value="HomePageServlet";
		 			document.getElementById('callbackmethod_name').value="doDisplayHome";
		 			document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		 			document.forms[0].method="POST";	
		 			document.forms[0].submit();
	 			}
	 		}
		});
		
	});
	request.fail(function(jqXHR, textStatus) {
	alert( "Request failed: " + textStatus );
	});
}

function resetLoginvalidateUserCredentials(){
	 if(document.getElementById('remember_me').checked){
		setCookie('jenixerpcookie',document.getElementById('login_name').value,30,'/','','');
	 }

	var login_name = jQuery('#login_name').val();
	var password = jQuery('#password').val();
	var Password_reset ='0';
	var forgot_reset_password ='no';
    var tz=(new Date().getTimezoneOffset());
	var url =contextpath+"/SessionExpiredLoginServlet";
	
	var request = jQuery.ajax({
	url: url,
	type: "POST",
	data: {login_name:login_name , password:password,Password_reset:Password_reset,forgot_reset_password:forgot_reset_password,user_timezone:tz},
	dataType: "xml"
	});
	request.done(function(xml) {
		alert('succes');
		jQuery(xml).find('login_credential').each(function(){
			msg=jQuery(this).find('valid_user').text();
	    	 yearSelection=jQuery(this).find('year_selection').text();
	    	 alert(msg);
	    	 alert(yearSelection);
	    	 if(msg=="Expired"){
//	 			alert("password expired..contact your admin...");
//	 			jQuery('#login_name').val = "";
//	 			jQuery('#password').val= "";
	    		 changePwd();
	 		}else if(msg=="Invalid"){
	 			jQuery('#message').html("<font color='red'>Username or Password are incorrect!</font>");
	 			jQuery('#login_name').val("");
	 			jQuery('#password').val("");
	 		}
	 		else if(message=="InActive"){
	 			jQuery('#message').html("<font color='red'>Not an active User.</font>");
	 		}
	 		else{
	 			if(yearSelection==0)
	 			{
		 			document.getElementById('servlet_name').value="CompanyAndYearSelectionServlet";
		 			document.getElementById('callbackmethod_name').value="doDisplayCompanyAndYear";
		 			document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		 			document.forms[0].method="POST";	
		 			document.forms[0].submit();
	 			}
	 			else if(yearSelection==1){
	 				alert('reached')
	 				document.getElementById('close_login_modal').click();
//	 				document.getElementById('servlet_name').value="HomePageServlet";
//		 			document.getElementById('callbackmethod_name').value="doDisplayHome";
//		 			document.forms[0].action=contextpath+"/RequestHandlerServlet";	
//		 			document.forms[0].method="POST";	
//		 			document.forms[0].submit();
	 			}
	 		}
		});
		
	});
	request.fail(function(jqXHR, textStatus) {
	alert( "Request failed: " + textStatus );
	});
}
function displayCompanyAndYear(request)
{
	var message = request.responseText;
	if(message=="Expired"){
		alert("password expired..contact your admin...");
		$('login_name').value = "";
		$('password').value = "";
	}else if(message=="Invalid"){
		$('message').innerHTML = "<font color='red'>Username or Password are incorrect!</font>";
		$('login_name').value = "";
		$('password').value = "";
	}else{
		
		document.getElementById('servlet_name').value="CompanyAndYearSelectionServlet";
		document.getElementById('callbackmethod_name').value="doDisplayCompanyAndYear";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();

	}
}


function setCookie(name, value, expires, path,domain,secure) {

	// set time, it's in milliseconds
	var today = new Date();
	today.setTime(today.getTime());

	expires = expires * 1000 * 60 * 60 * 24;

	var expires_date = new Date( today.getTime() + (expires) );

	document.cookie = name + "=" +escape( value ) +
	( ( expires ) ? ";expires=" + expires_date.toGMTString() : "" ) +
	( ( path ) ? ";path=" + path : "" ) +
	( ( domain ) ? ";domain=" + domain : "" ) +
	( ( secure ) ? ";secure" : "" );

	}


function Get_Cookie(check_name) {
	
	var a_all_cookies = document.cookie.split( ';' );
	var a_temp_cookie = '';
	var cookie_name = '';
	var cookie_value = '';
	var b_cookie_found = false; // set boolean t/f default f

	for ( i = 0; i < a_all_cookies.length; i++ )
	{
		// now we'll split apart each name=value pair
		a_temp_cookie = a_all_cookies[i].split( '=' );


		// and trim left/right whitespace while we're at it
		cookie_name = a_temp_cookie[0].replace(/^\s+|\s+$/g, '');

		// if the extracted name matches passed check_name
		if ( cookie_name == check_name )
		{
			b_cookie_found = true;
			// we need to handle case where cookie has no value but exists (no = sign, that is):
			if ( a_temp_cookie.length > 1 )
			{
				cookie_value = unescape( a_temp_cookie[1].replace(/^\s+|\s+$/g, '') );
			}
			// note that in cases where cookie is initialized but no value, null is returned
			return cookie_value;
			break;
		}
		a_temp_cookie = null;
		cookie_name = '';
	}
	if ( !b_cookie_found )
	{
		return null;
	}
}


function getUserName(){
	document.getElementById('login_name').focus();
	var returnusername=Get_Cookie('jenixerpcookie');
	if(returnusername!=null)
	document.getElementById('login_name').value=returnusername;
	else
	document.getElementById('login_name').value="";
}




/* Forgot Password */
//
//	function forgotPassword()
//	{
//	
//		var login_name = $('login_name').value;
//		var url=contextpath+"/ForgotPasswordServlet";		
//		var params = "login_name="+login_name;		
//		var myAjax = new Ajax.Request(url,{method: 'POST',parameters:params,onSuccess:displayQuestion,onFailure:displayError});
//		
//	}
//
//							
//	function displayQuestion(request)
//	{
//	
//	var message = request.responseText;	
//	
//		if(message=="Invalid"){
//		$('message').innerHTML = "<font color='red'>User name you have entered is invalid!</font>";
//		}else if(message=="NoQuestion"){
//			if(confirm("Question not configured! Do You Want To Renew??")){
//			var login_name = $('login_name').value;
//			var password = $('password').value;
//			var Password_reset ='1';
//			var forgot_reset_password ='yes';
//			  var tz=(new Date().getTimezoneOffset());
//			var url =contextpath+"/login/LoginServlet";
//			var params = "login_name="+login_name+"&password="+password+"&Password_reset="+Password_reset+"&forgot_reset_password="+forgot_reset_password+"&user_timezone="+tz;	
//			var myAjax = new Ajax.Request(url,{method: 'POST',parameters:params,onSuccess:displayPasswordChange,onFailure:displayError});
//			}
//		}else if(message=="Question"){
//			var strFeatures="toolbar=no,status=no,menubar=no,left=150,top=100,location=no,scrollbars=yes,resizable=no,height=100,width=100";
//			window.open(contextpath+'/jsp/login/Question.jsp','ForgotPassword','strFeatures').focus();
//		}
//	}
//
//	function displayError(){
//		alert('..........error');	
//	}
//	
	function displayHome(){ 
		
		var objCompany = document.getElementById('company_name');
		var selectedCompany = objCompany.options[objCompany.selectedIndex].value;
		var split = selectedCompany.split(',');
		var iCompanyId = split[0];
		var iPartyId = split[1];
		var iCurrencydefaultId = split[2];
		
		var objYear = document.getElementById('year');
		var selectedYear = objYear.options[objYear.selectedIndex].value;
		
		if(selectedCompany==-1){ 
		 document.getElementById('errormessage').innerHTML="Please select valid company!";
		 return false;
		}else if(selectedYear==-1){ 
		 document.getElementById('errormessage').innerHTML="Please select valid year value!";
		 return false;
		}
		else{ 
		document.getElementById('company_id').value=iCompanyId;
		document.getElementById('party_id').value=iPartyId;
		document.getElementById('currency_defaultid').value=iCurrencydefaultId;
		document.getElementById('year_id').value=selectedYear;	
		document.getElementById('servlet_name').value="HomePageServlet";
		document.getElementById('callbackmethod_name').value="doSetInSession";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
		}
	}
	
	
	


/* Forgot Password */

	function forgotPassword()
	{
	
		var login_name = jQuery('#login_name').val();
//		var url=contextpath+"/ForgotPasswordServlet";		
//		var params = "login_name="+login_name;		
//		var myAjax = new Ajax.Request(url,{method: 'POST',parameters:params,onSuccess:displayQuestion,onFailure:displayError});
		
		var url =contextpath+"/ForgotPasswordServlet";
		
		var request = jQuery.ajax({
		url: url,
		type: "POST",
		data: {login_name:login_name},
		dataType: "xml"
		});
		request.done(function(xml) {
			try{
				
				jQuery(xml).find('forgot_pwd').each(function(){
					msg=jQuery(this).find('msg').text();
					if(msg=="Invalid"){
					jQuery('#message').html("<font color='red'>User name you have entered is invalid!</font>");
					}else if(msg=="NoQuestion"){
						renewPassword();
					}else if(msg=="Question"){
						
						jQuery('#question').val("");
						jQuery('#tmp_security_ans').val("");
						jQuery('#user_id').val("");
						jQuery('#client_id').val("");
						jQuery('#answer').val("");
						
						quest=jQuery(this).find('security_question').text();
						ans=jQuery(this).find('security_answer').text();
						userId=jQuery(this).find('user_id').text();
						clientId=jQuery(this).find('client_id').text();
						
						jQuery('#question').val(quest);
						jQuery('#tmp_security_ans').val(ans);
						jQuery('#user_id').val(userId);
						jQuery('#client_id').val(clientId);
						
						jQuery('#change_question').click();
						//var strFeatures="toolbar=no,status=no,menubar=no,left=150,top=100,location=no,scrollbars=yes,resizable=no,height=100,width=100";
						//window.open(contextpath+'/jsp/login/Question.jsp','ForgotPassword','strFeatures').focus();
					}
					
				});
				
			}catch(err){
				document.getElementById('message').innerHTML = "<font color='red'>Exception in forgot password</font>";
				showResponseMsg('message','succ-msg');
			}
			
		});
		request.fail(function(jqXHR, textStatus) {
		alert( "Request failed: " + textStatus );
		});	
		
	}

function renewPassword(){
	if(confirm("Question not configured! Do you want to renew?")){
		var login_name = jQuery('#login_name').val();
		var password = jQuery('#password').val();
		var Password_reset ='1';
		var forgot_reset_password ='yes';
		  var tz=(new Date().getTimezoneOffset());
		  
		  var url =contextpath+"/LoginServlet";
		  var request = jQuery.ajax({
				url: url,
				type: "POST",
				data: {login_name : login_name , password : password,Password_reset:Password_reset,forgot_reset_password:forgot_reset_password,user_timezone:tz},
				dataType: "html"
				});
				request.done(function(html) {
					try{
						
							var message =html;	
							if(message=="Reset"){
								jQuery('#message').html("<font color='red'>Please check your email for new password!</font>");
								document.getElementById('password').value="";
								
							}else if (message == "NotReset"){
								jQuery('#message').html("<font color='red'>Password is not Renewed!</font>");
							}

					}catch(err){
						document.getElementById('message').innerHTML = "<font color='red'>Exception in renew password</font>";
						showResponseMsg('message','succ-msg');
					}
					
				});
				request.fail(function(jqXHR, textStatus) {
				alert( "Request failed: " + textStatus );
				});	
		  
//		var url =contextpath+"/login/LoginServlet";
//		var url =contextpath+"/LoginServlet";
//		var params = "login_name="+login_name+"&password="+password+"&Password_reset="+Password_reset+"&forgot_reset_password="+forgot_reset_password+"&user_timezone="+tz;	
//		var myAjax = new Ajax.Request(url,{method: 'POST',parameters:params,onSuccess:displayPasswordChange,onFailure:displayError});
		}
}
//	function displayQuestion(request)
//	{
//	
//	var message = request.responseText;	
//	
//		if(message=="Invalid"){
//		$('message').innerHTML = "<font color='red'>User name you have entered is invalid!</font>";
//		}else if(message=="NoQuestion"){
//			if(confirm("Question not configured! Do You Want To Renew??")){
//			var login_name = $('login_name').value;
//			var password = $('password').value;
//			var Password_reset ='1';
//			var forgot_reset_password ='yes';
//			  var tz=(new Date().getTimezoneOffset());
//			var url =contextpath+"/login/LoginServlet";
//			var params = "login_name="+login_name+"&password="+password+"&Password_reset="+Password_reset+"&forgot_reset_password="+forgot_reset_password+"&user_timezone="+tz;	
//			var myAjax = new Ajax.Request(url,{method: 'POST',parameters:params,onSuccess:displayPasswordChange,onFailure:displayError});
//			}
//		}else if(message=="Question"){
//			var strFeatures="toolbar=no,status=no,menubar=no,left=150,top=100,location=no,scrollbars=yes,resizable=no,height=100,width=100";
//			window.open(contextpath+'/jsp/login/Question.jsp','ForgotPassword','strFeatures').focus();
//		}
//	}


	function checkAnswer(){
		var tmpAns=document.getElementById('tmp_security_ans').value;
		var actAns=document.getElementById('answer').value.trim();
		var user_id=document.getElementById('user_id').value;
		var client_id=document.getElementById('client_id').value;
		
		 
		
		//alert(client_id);
		if(tmpAns==actAns){
			
			 var url =contextpath+"/ForgotPasswordServlet";
			  var request = jQuery.ajax({
					url: url,
					type: "POST",
					data: {user_id : user_id , client_id : client_id},
					dataType: "xml"
					});
					request.done(function(xml) {
						try{
							jQuery(xml).find('forgot_pwd').each(function(){
								msg=jQuery(this).find('msg').text();
								if(msg=="Success"){
									jQuery('#group_close').click();
								jQuery('#message').html("<font color='red'>Password sent to your email!</font>");
								}
							});

						}catch(err){
							document.getElementById('message').innerHTML = "<font color='red'>Exception in check answer</font>";
							showResponseMsg('message','succ-msg');
						}
						
					});
					request.fail(function(jqXHR, textStatus) {
					alert( "Request failed: " + textStatus );
					});	
			  
//		var url=contextpath+"/ForgotPasswordServlet";
//		var params = "user_id="+user_id+"&client_id="+client_id;	
//		var myAjax = new Ajax.Request(url,{method: 'POST',parameters:params,onSuccess:displaySuccess,onFailure:displayError});
		}else{
		jQuery('#message').html("<font color='red'>Enter the right answer</font>");
		}
	}
	
	
//	
//	function displaySuccess(request)
//	{
//	window.close("ForgotPassword"); 
//	var message = request.responseText;	
//	if(message=="Success"){
//	opener.document.all['message'].innerHTML="<font color='red'>Password sent to your email!</font>";
//	}
//	}
	
	function changePwd(){
		if(confirm("Password Expired! Do you want to renew ?")){
			document.forms[0].action=contextpath+"/ChangeExpiredPassword?change_pwd_type=display";
			document.forms[0].method="POST";
			document.forms[0].submit();
			}
	}
	function saveUsersExpiredPassword(){
		  
		  if(validateUserPassword()){
			
				  	document.forms[0].action=contextpath+"/ChangeExpiredPassword?change_pwd_type=save";
					document.forms[0].method="POST";
					document.forms[0].submit();
		  }
	}
	function validateUserPassword(){
		var oldPass = document.getElementById('old_password');
		var newPass = document.getElementById('new_password').value.trim();
		var confirmPass = document.getElementById('confirm_password').value.trim();
		var secQuestion = document.getElementById('security_question');
		var secuAnswer = document.getElementById('answer');
		var days = document.getElementById('days_to_exp');
		
					
		if(oldPass.value.trim().length== 0){
		document.getElementById('errormessage').innerHTML=localizedStrings.VALID_PASSWORD;//"Enter the correct password!";
		showResponseMsg('errormessage','succ-msg');
		oldPass.focus();
		return false;
		}
		else if(newPass != confirmPass){
		document.getElementById('errormessage').innerHTML=localizedStrings.COMFIRM_PASSWORD;//"Confirm password!";
		showResponseMsg('errormessage','succ-msg');
		return false;
		}
		else if(secQuestion.value.trim().length == 0){
		document.getElementById('errormessage').innerHTML=localizedStrings.VALID_QUESTION;//"Enter the right question!";
		showResponseMsg('errormessage','succ-msg');
		secQuestion.focus();
		return false;
		}else if(secuAnswer.value.trim().length == 0){
		document.getElementById('errormessage').innerHTML=localizedStrings.VALID_ANSWER;//"Enter the answer!";
		showResponseMsg('errormessage','succ-msg');
		secuAnswer.focus();
		return false;
		}else if(days.value.trim().length == 0){
		document.getElementById('errormessage').innerHTML=localizedStrings.VALID_DAYS;//"Enter valid password expiry days!";
		showResponseMsg('errormessage','succ-msg');
		days.focus();
		return false;
		}else if(isNaN(days.value.trim())){
		document.getElementById('errormessage').innerHTML=localizedStrings.VALID_DAYS;//"Enter valid password expiry days!";
		showResponseMsg('errormessage','succ-msg');
		document.getElementById('days_to_exp').focus();
		return false;
		}
		return true;
}