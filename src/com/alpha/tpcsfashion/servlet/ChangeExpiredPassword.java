package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alpha.tpcsfashion.model.LoginManager;
import com.alpha.tpcsfashion.model.UserManager;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.User;

public class ChangeExpiredPassword
extends HttpServlet
{
	
	
private static final long serialVersionUID = 1L;

public ChangeExpiredPassword() {
  super();
  // TODO Auto-generated constructor stub
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub

}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try{
	


String type =request.getParameter("change_pwd_type");

String redirectPage=TPCSRedirectPage.DISPLAY_CHANGE_EXPIRED_PASSWORD;

	if(type.equals("display")){
		
		
		String loginName=request.getParameter("login_name");
		request.getSession().setAttribute("user_l_name", loginName);
		request.setAttribute("change_pwd_type", "save");
		redirectPage=TPCSRedirectPage.DISPLAY_CHANGE_EXPIRED_PASSWORD;
	}
	else if(type.equals("save")){
    	
    	String oldPassword = request.getParameter("old_password");
    	String newPassword = request.getParameter("new_password");
    	String securityQuestion = request.getParameter("security_question");
    	String securityAnswer= request.getParameter("answer");
    	String expDay= request.getParameter("days_to_exp");
    	int days = Integer.parseInt(expDay);
    	//String expDate= request.getParameter("exp_date");
    	
    	
    	String loginName=(String)request.getSession().getAttribute("user_l_name");
		TPCSUser ui=new TPCSUser();
		ui.setUserName(loginName);
		ui=objLoginManager.getUserId(ui);
    	
        int iUserId = ui.getUserId();
        int clientId = ui.getclientId();
        
        User retval= objManager.changePassword(clientId,iUserId,oldPassword,newPassword,securityQuestion,securityAnswer,days);
        redirectPage=TPCSRedirectPage.DISPLAY_CHANGE_EXPIRED_PASSWORD;
    	if(retval.isSuccess()){
    		 request.setAttribute("success_message", "Password changed successfully verify mail."); 
    		 objManager.mailchangePassword(retval);
    		 redirectPage=TPCSRedirectPage.LOGIN_FAILED;
    	}else{
    		request.setAttribute("error_message", "Old Password is incorrect");
    		 request.setAttribute("sec_que", securityQuestion);
    		 request.setAttribute("sec_ans", securityAnswer);
    		 request.setAttribute("no_days", expDay);
    		 request.setAttribute("change_pwd_type", "save");
    	}
    
    	
        
	}
	TPCSCommonUtil.RedirectURL(redirectPage,request,response);
	
}
catch (Exception e) {
	e.printStackTrace();
}
}


private LoginManager objLoginManager=new LoginManager();
private UserManager objManager = new UserManager();
}
