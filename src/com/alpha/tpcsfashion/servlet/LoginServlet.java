package com.alpha.tpcsfashion.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;

import com.alpha.tpcsfashion.beans.Entity;
import com.alpha.tpcsfashion.beans.MenuHolder;
import com.alpha.tpcsfashion.beans.MenuItem;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.User;
import com.alpha.tpcsfashion.model.LoginManager;
import com.alpha.tpcsfashion.model.MenuManager;
import com.alpha.tpcsfashion.model.ValidateUser;
import com.alpha.tpcsfashion.util.ResourceBundleProvider;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {

		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String strUserName = request.getParameter("login_name");
			String strUserPassword = request.getParameter("password");
			int ipasswordreset = Integer.parseInt(request.getParameter("Password_reset"));
			String strforgotPassword = request.getParameter("forgot_reset_password");

			int iTimeZone=0;
			if(request.getParameter("user_timezone")!=null && !(request.getParameter("user_timezone")).isEmpty()){
				
				iTimeZone= Integer.parseInt(request.getParameter("user_timezone"));
				iTimeZone = iTimeZone*-1;
			}
			if("no".equalsIgnoreCase(strforgotPassword)){
				
				TPCSUser objTPCSUser = new TPCSUser();
				objTPCSUser.setUserName(strUserName);
				objTPCSUser.setPassword(strUserPassword);

				String strMessage = "Invalid";
				ValidateUser objValidateUser = new ValidateUser();		
				TPCSUser objUserInfo = objValidateUser.validateCredentials(objTPCSUser);

				System.out.println("objUserInfo.isUserValid() :"+objUserInfo.isUserValid());
				int yearSelection=0;


				if(ipasswordreset==0){

					if(objUserInfo.isUserValid()){			
						
						strMessage = "Expired";
						if(!objUserInfo.isPasswordExpired()){			
							
							strMessage = "Valid";

							HttpSession session= request.getSession();
							objUserInfo.setTimeZone(iTimeZone);

							session.setAttribute("client_currenty", "dollar");
						
							
							LoginManager objLoginManager=new LoginManager();   
							objLoginManager.updateLoginTime(objUserInfo.getclientId(),objUserInfo.getUserId());
							Entity objEntity=objLoginManager.getEntityDetails(objUserInfo.getUserId(),objUserInfo);
//
							request.getSession().setAttribute("ho_id", String.valueOf(objEntity.getEntityHOId()));
							request.getSession().setAttribute("customer_id", String.valueOf(objEntity.getEntityCustomerId()));
							request.getSession().setAttribute("factory_id", String.valueOf(objEntity.getEntityFactoryId()));
							session.setAttribute("display_name", objUserInfo.getDisplayUserName());
							request.getSession().setAttribute("client_id", String.valueOf(objUserInfo.getclientId()));
							request.getSession().setAttribute("company_name", objUserInfo.getCompanyName());
							request.getSession().setAttribute("sql_password",objUserInfo.getSqlpassword());
							request.getSession().setAttribute("client_name", objUserInfo.getClientName());
							request.getSession().setAttribute("login_user_id", String.valueOf(objUserInfo.getUserId()));
							MenuManager objMenuManager = new MenuManager();
							List<MenuItem> mainmenus = objMenuManager.getMainMenuItems(objUserInfo);
							MenuHolder objHolder = objMenuManager.getMenus(objUserInfo);
							session.setAttribute("main_menu", mainmenus);
							session.setAttribute("menu_items", objHolder);
							List<MenuItem> reportmainmenus = objMenuManager.getReportMainMenuItems(objUserInfo);
							session.setAttribute("report_main_menu", reportmainmenus);
							MenuHolder objReport= objMenuManager.getReportMenus(objUserInfo);
							session.setAttribute("report_items", objReport);
							Map<Integer,String> subdocuments= objMenuManager.getSubdocuments(objUserInfo);
							session.setAttribute("subdocuments", subdocuments);
							ResourceBundleProvider rbp=new ResourceBundleProvider();
							session.setAttribute("bundle",new LocalizationContext(rbp.getResourceBundle(objUserInfo)));
							if(objUserInfo.getCompanyId()>0 && objUserInfo.getYearId()>0){
								yearSelection=1;
							}
							if(objUserInfo.getYearId()>0){
								request.getSession().setAttribute("g_yearid", objUserInfo.getYearId());
								request.getSession().setAttribute("g_startdate",objUserInfo.getStartDate());
								request.getSession().setAttribute("g_enddate",objUserInfo.getEndDate());
							}

							String filePath="images/uploadimages/"+objUserInfo.getclientId()+"/logo/";

							String clientLogoFolder=request.getServletContext().getRealPath("/").concat(filePath);

							File f=new File(clientLogoFolder);
							f.mkdir();

							//				boolean clientLogo=ManageFile.isfileExist(clientLogoFolder);
							//				System.out.println("clientLogo--->"+clientLogo);
							//				
							//				if(!clientLogo){
							//					clientLogoFolder="no_image";
							//				}
							File[] files = new File(clientLogoFolder).listFiles();
							// String clientLogoPath=request.getContextPath()+"/images/logo-small.png";
							clientLogoFolder="";
							if(files!=null){
								for (File file : files) {
									if (file.isFile()) {
										file.getName();
										clientLogoFolder=request.getContextPath()+"/"+filePath.concat(file.getName());
									}
								}
							}
							//			  request.getSession().setAttribute("client_logo", clientLogoFolder);
							objUserInfo.setLogoPath(clientLogoFolder);
							session.setAttribute("user_info", objUserInfo);	

						}

					}



					StringBuffer buffer=new StringBuffer();
					buffer.append("<login_credentials>");
					buffer.append("<login_credential>");
					buffer.append("<valid_user>");
					buffer.append(strMessage);
					buffer.append("</valid_user>");

					buffer.append("<year_selection>");
					buffer.append(yearSelection);
					buffer.append("</year_selection>");

					buffer.append("</login_credential>");
					buffer.append("</login_credentials>");
					response.setContentType("text/xml");
					response.getWriter().write(buffer.toString());
				}
				else if(ipasswordreset==1){

					strMessage = "NotReset";
					objValidateUser = new ValidateUser();

					User objuser = objValidateUser.passwordreset(strUserName);

					if(objuser.isSuccess()){
						strMessage = "Reset";
						objValidateUser.mailresetPassword(objuser);
					}
					response.getWriter().write(strMessage);
				}
			}
			else if("yes".equalsIgnoreCase(strforgotPassword)){

				//Reset Password at forgot
				ValidateUser objValidateUser = new ValidateUser();
				String strMessage = "Invalid";

				User objuser = objValidateUser.passwordreset(strUserName);

				if(objuser.isSuccess()){
					strMessage = "Reset";
					objValidateUser.mailresetPassword(objuser);
				}
				response.getWriter().write(strMessage);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void doSetMainMenu(HttpServletRequest request,TPCSUser objUserInfo){
		MenuManager objMenuManager = new MenuManager();
		List<MenuItem> mainmenus = objMenuManager.getMainMenuItems(objUserInfo);
		//			    List<MenuItem> mastersubmenus = objMenuManager.getSubMenuItems(objUserInfo.getUserId(),1,objUserInfo);
		//			    List<MenuItem> mastersubdocuments = objMenuManager.getSubDocumentItems(objUserInfo.getUserId(),1,objUserInfo);
		MenuHolder objHolder = objMenuManager.getMenus(objUserInfo);
		request.getSession().setAttribute("main_menu", mainmenus);
		request.getSession().setAttribute("menu_items", objHolder);
		
		objMenuManager=null;
		objHolder=null;
	}
	public void doSetReportMenu(HttpServletRequest request,TPCSUser objUserInfo){
		MenuManager objMenuManager = new MenuManager();
		List<MenuItem> reportmainmenus = objMenuManager.getReportMainMenuItems(objUserInfo);
		request.getSession().setAttribute("report_main_menu", reportmainmenus);
		MenuHolder objReport= objMenuManager.getReportMenus(objUserInfo);
		request.getSession().setAttribute("report_items", objReport);
		Map<Integer,String> subdocuments= objMenuManager.getSubdocuments(objUserInfo);
		request.getSession().setAttribute("subdocuments", subdocuments);
		objMenuManager=null;
		reportmainmenus=null;
		objReport=null;
		subdocuments=null;
	}
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {

		    InvokerClass objInvoker = new InvokerClass();
		      String strUserName=null, strUserPassword=null;

		      StringBuffer jb = new StringBuffer();
		      String line = null;
		      try {
		        BufferedReader reader = request.getReader();
		        while ((line = reader.readLine()) != null)
		          jb.append(line);
		      } catch (Exception e) { report an error }

		      System.out.println("jb Login:"+jb.toString());
		      JSONObject jsonObject = null;
		      try {
		        jsonObject = new JSONObject(jb.toString());
		      } catch (JSONException e) {		       
		        throw new IOException("Error parsing JSON request string");
		      }

		      strUserName = jsonObject.getString("userName");  
		      strUserPassword = jsonObject.getString("password");	

			int iTimeZone=0;
			if(request.getParameter("user_timezone")!=null && !(request.getParameter("user_timezone")).isEmpty())
			{
			 iTimeZone= Integer.parseInt(request.getParameter("user_timezone"));
			iTimeZone = iTimeZone*-1;
			}

			TPCSUser objTPCSUser = new TPCSUser();
			objTPCSUser.setUserName(strUserName);
			objTPCSUser.setPassword(strUserPassword);
			JSONObject main = new JSONObject();
			String strMessage = "Invalid";
			ValidateUser objValidateUser = new ValidateUser();		
			TPCSUser objUserInfo = objValidateUser.validateCredentials(objTPCSUser);


			if(objUserInfo.isUserValid()){			
				  strMessage = "Expired";
				if(!objUserInfo.isPasswordExpired()){					
				  strMessage = "Valid";

				  HttpSession session= request.getSession();
				//  objUserInfo.setTimeZone(iTimeZone);

				  session.setAttribute("user_info", objUserInfo);
				  session.setAttribute("client_currenty", "dollar");



			    LoginManager objLoginManager=new LoginManager();   
			    objLoginManager.updateLoginTime(objUserInfo.getclientId(),objUserInfo.getUserId());

			    session.setAttribute("display_name", objUserInfo.getDisplayUserName());
			    request.getSession().setAttribute("login_user_id", String.valueOf(objUserInfo.getUserId()));
			    MenuManager objMenuManager = new MenuManager();
			    List<MenuItem> mainmenus = objMenuManager.getMainMenuItems(objUserInfo);
			    MenuHolder objHolder = objMenuManager.getMenus(objUserInfo);
			    session.setAttribute("main_menu", mainmenus);
			    session.setAttribute("menu_items", objHolder);
			    ResourceBundleProvider rbp=new ResourceBundleProvider();
				}
			  }

			   main.put("message", strMessage);
			  StringWriter output = new StringWriter();
		        main.write(output);
		        response.getWriter().write(output.toString());  

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


	}*/


}
