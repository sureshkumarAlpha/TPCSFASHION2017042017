package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alpha.tpcsfashion.model.CompanyAndYearSelectionManager;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.beans.Company;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.Year;

public class CompanyAndYearSelectionServlet{

	private static final long serialVersionUID = 1L;
	

    public void doDisplayCompanyAndYear(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        try{
        	TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
//     		String strMatDisplayOption = objManager.getMaterialDisplayStyle(iUserId,strDataSource,strDataBase,strServerIP,strSqlusername,strSqlpassword);
     		company = objManager.getCompany(userInfo);
     		year = objManager.getYear(userInfo);
            String strPageNo = request.getParameter("pageno");         
            int PageNo = (strPageNo!=null?Integer.parseInt(strPageNo):1);
//            request.getSession().setAttribute("matdisply_option",strMatDisplayOption);
            request.setAttribute("company_list", company);
            request.setAttribute("year_list", year);
            request.setAttribute("page_no", PageNo);       
            TPCSCommonUtil.RedirectURL(TPCSRedirectPage.LOGIN_SUCCESS,request,response);
          }catch(Exception e){
            e.printStackTrace();
          }
      }
	
    CompanyAndYearSelectionManager objManager=new CompanyAndYearSelectionManager();
    public List<Company> company=null;
    public List<Year> year=null;
    
}
