package com.alpha.tpcsfashion.framework;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alpha.tpcsfashion.beans.TPCSUser;

public class RequestHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RequestHandlerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try{
			InvokerClass objInvoker = new InvokerClass();
			String strCont_Name=null, strMethod_Name=null;
			HttpSession session = request.getSession();
			
			strCont_Name = request.getParameter("servlet_name");
			strMethod_Name = request.getParameter("callbackmethod_name");
			
			request.getSession().setAttribute("path",getServletContext().getRealPath("/"));
			
			if(session.getAttribute("display_name")!=null){
				if(strCont_Name!=null && strMethod_Name!=null){
					objInvoker.doProcess(request,response,strCont_Name, strMethod_Name);
				}  
				else{
					response.sendRedirect("jsp/login/Login.jsp");
				}   
			}
			else{
				response.sendRedirect("jsp/login/Login.jsp");  
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}


}
