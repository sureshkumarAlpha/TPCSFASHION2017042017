package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.zip.DataFormatException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alpha.tpcsfashion.beans.Company;
import com.alpha.tpcsfashion.beans.HomePage;
import com.alpha.tpcsfashion.beans.MenuHolder;
import com.alpha.tpcsfashion.beans.MenuItem;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.model.CompanyAndYearSelectionManager;
import com.alpha.tpcsfashion.model.HomePageManager;
import com.alpha.tpcsfashion.model.LoginManager;
import com.alpha.tpcsfashion.model.MenuManager;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.Validator;

/**
 * Servlet implementation class MasterItemGroupServlet
 */
public class HomePageServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see LoginHandlerServlet#RequestHandlerServlet()
	 */
	public HomePageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	 public void doDisplayHome(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	        try{ 
	    	 
	    	 TPCSUser ui= (TPCSUser)request.getSession().getAttribute("user_info");
	        // System.out.println("IN HOME");
	         HomePage homePage=new HomePage();
//	         homePage=objMan.getHomePageData(ui,homePage);
	         request.setAttribute("home_page", homePage);
	         
	         
	         TPCSCommonUtil.RedirectURL(TPCSRedirectPage.HOME,request,response);
	           
	       }catch(Exception e){
	            e.printStackTrace();       
	        }
	      }
	
	 /*public void doGetMainMenu(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
			try{
				//System.out.println("-------------doGetMainMenu-------------");
				HttpSession session=request.getSession();
				TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
				 MenuManager objMenuManager = new MenuManager();
		    	 List<MenuItem> mainmenus = objMenuManager.getMainMenuItems(userInfo);
		    	 MenuHolder objHolder = objMenuManager.getMenus(userInfo);
		    	 ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request); 
		    	 
				String mainMenuData = buidMainmenu(objHolder,mainmenus,bundle,userInfo);
				response.setContentType("text/html");
				response.getWriter().write(mainMenuData);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}*/
	 
	 /*public String buidMainmenu(MenuHolder objHolder,List<MenuItem> mainmenus,ResourceBundle bundle,TPCSUser userInfo)
	 {
	 	StringBuffer buffer = new StringBuffer();
	 	
	 	  Map<Integer, List<MenuItem>> mapDocuments = objHolder.getDocuments();
	 	  Map<Integer, List<MenuItem>> mapSubDocuments = objHolder.getSubDocuments();
	 	  
	 	  
	 	  buffer.append("<div class=\"navbar-collapse collapse\">");
	 	  buffer.append("<ul class=\"nav navbar-nav navbar-right\" id=\"top_menu\">");
	 	  
	 	  List<MenuItem> items = mainmenus;
	 	  MenuItem mainMenu=null;
	 	  Iterator<MenuItem> iterator = items.iterator();

	 	  while(iterator.hasNext()){
	 	    mainMenu = (MenuItem)iterator.next();
	 	  buffer.append("<li class=\"dropdown\"><a  href=\"#"+mainMenu.getMenuURL()+"\" >"+bundle.getString(mainMenu.getMenuName())+"</a>");
	 	  buffer.append("<ul class=\"dropdown-menu\">");
	 	  List<MenuItem> documents = mapDocuments.get(mainMenu.getMenuId());
	 	  int docSize=documents.size();
	 	  int i=1;
	 	  
	 	  String  docClass="";
	 			  
	 	  for (MenuItem document : documents)
	 	  {
	 		  docClass="";
	 		  if(bundle.getString(document.getMenuName()).equalsIgnoreCase("Reports") || bundle.getString(document.getMenuName()).equalsIgnoreCase("Account Books") || bundle.getString(document.getMenuName()).equalsIgnoreCase("Payable Reports") || bundle.getString(document.getMenuName()).equalsIgnoreCase("Receivable Reports")|| bundle.getString(document.getMenuName()).equalsIgnoreCase("Final Reports")|| bundle.getString(document.getMenuName()).equalsIgnoreCase("Other Reports"))
	 		  {
	 			  docClass="fa fa-bar-chart-o fa-fw";
	 		  }
	 		  else{
	 			  docClass="fa fa-wrench fa-fw";
	 		  }
	 	  buffer.append("<li><a href=\"#\"><i class=\""+docClass+"\"></i>&nbsp;&nbsp;"+bundle.getString(document.getMenuName())+"</a>");
	 	  buffer.append(" <ul class=\"dropdown-menu\">");
	 	  
	 	  List<MenuItem> subdocuments = mapSubDocuments.get(document.getMenuId());	
	 	  int subSize=subdocuments.size();
	 	  int j=1;
	 	    for (MenuItem subDocument : subdocuments)
	 	    {
	 	    	//   if (subDocument.getMenuId() == iSubDocumentType)
	 		      
	 	    	buffer.append("<li><a href=\"#"+subDocument.getMenuURL()+"\" >"+bundle.getString(subDocument.getMenuName())+"</a></li>");
	 	    	
	 	    	if(subSize!=j){
	 	    		buffer.append("<li class=\"divider\"></li>");	
	 	    	}
	 	    	
	 	    	
	 	    	j++;
	 	    }
	 	    buffer.append(" </ul>");
	 	    buffer.append(" </li>");
	 	    if(docSize!=i){
	     		buffer.append("<li class=\"divider\"></li>");	
	     	}
	     	i++;	
	 	  }
	 	  buffer.append(" </ul>");
	 	  buffer.append(" </li>");
	 	  }
	 	  
	 	  buffer.append("<li class=\"dropdown\">");
	 	  buffer.append("<a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">");
	 	  buffer.append(" <i class=\"fa fa-user fa-fw\"></i> "+userInfo.getDisplayUserName()+" </a>");
	 	  buffer.append("<ul class=\"dropdown-menu dropdown-user\">");
	 	  buffer.append("<li><a href=\"#\"><i class=\"fa fa-user fa-fw\"></i> User Profile</a>");
	 	  buffer.append("</li>");
	 	  buffer.append("<li class=\"divider\"></li>");
	 	  buffer.append("<li><a href=\"#\"><i class=\"fa fa-gear fa-fw\"></i> User Settings</a>");
	 	  buffer.append("</li>");
	 	  buffer.append("<li class=\"divider\"></li>");
	 	  buffer.append("<li><a href=\"login.html\"><i class=\"fa fa-sign-out fa-fw\"></i> Logout</a>");
	 	  buffer.append("</li>");
	 	  buffer.append("</ul>");                 
	 	  buffer.append("</li>"); 
	 	  buffer.append(" </ul>");
	 	  buffer.append(" </div> ");     
	 	     
	 	 return buffer.toString();
	 }*/

	public void doSetInSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String strCompanyId = request.getParameter("company_id");
			String strPartyId = request.getParameter("party_id");
			String strCurrencydefaultId = request.getParameter("currency_defaultid");
			String strYearId = request.getParameter("year_id");
			String[] year = strYearId.split(",");
			request.getSession().setAttribute("g_currencydefaultid", strCurrencydefaultId);
	
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
            userInfo.setCompanyId(Integer.parseInt(strCompanyId));
            userInfo.setLocationId(Integer.parseInt(strPartyId));
            userInfo.setYearId(Integer.parseInt(year[0]));
            userInfo.setCurrencyId(Integer.parseInt(strCurrencydefaultId));
            
            objLoginManager.updateUserCompanyAndYear(userInfo);
            
          //  Company companyInfo=objCompanyAndYearSelectionManager.getCompanyInfo(Integer.parseInt(strCompanyId),userInfo.getDatasource(),userInfo.getDatabase(),userInfo.getServerip(),userInfo.getSqlusername(),userInfo.getSqlpassword());
//            userInfo.setAddress1(companyInfo.getAddress1());
//            userInfo.setAddress2(companyInfo.getAddress2());
//            userInfo.setAddress3(companyInfo.getAddress3());
//            userInfo.setCountry(companyInfo.getCountry());
//            userInfo.setPhoneNo(companyInfo.getPhoneNo());
//            userInfo.setFaxNo(companyInfo.getFaxNo());
//            userInfo.setTaxRegNo1(companyInfo.getTaxRegNo1());
//            userInfo.setTaxRegNo2(companyInfo.getTaxRegNo2());
//            userInfo.setEmailId(companyInfo.getEmailId());
            request.getSession().setAttribute("user_info", userInfo);
			//String strCurrency = objCompanyAndYearSelectionManager.getCurrency(iCurrencydefaultId,strDataSource,strDataBase,strServerIP,strSqlusername,strSqlpassword);
			
			//  request.getSession().setAttribute("g_currencydefaultname",strCurrency);
            
			request.getSession().setAttribute("g_yearid", year[0]);
			request.getSession().setAttribute("g_startdate",dateFormatConverter(year[1]));
			request.getSession().setAttribute("g_enddate",dateFormatConverter(year[2]));
			doDisplayHome(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String dateFormatConverter(String strInput) throws IOException,
			DataFormatException {
		String strDate = "";
		try {
			SimpleDateFormat sdfSource = new SimpleDateFormat("dd MMM yyyy");
			Date date = sdfSource.parse(strInput);
			SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
			strDate = sdfDestination.format(date);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return strDate;
	}

	public void doLogout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			if(request.getSession().getAttribute("user_info")!=null){
				
				TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
				objLoginManager.updateLogoutTime(userInfo.getclientId(), userInfo.getUserId());
				request.getSession().removeAttribute("display_name");
				request.getSession().invalidate();
			}
			
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.LOGIN_FAILED, request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doChangeCompanyYear(HttpServletRequest request,HttpServletResponse response){
		try {
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			userInfo.setCompanyId(0);
			userInfo.setYearId(0);
			objLoginManager.updateUserCompanyAndYear(userInfo);
			request.getSession().removeAttribute("display_name");
			request.getSession().invalidate();
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.LOGIN_FAILED, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private HomePageManager objMan=new HomePageManager();
	private LoginManager objLoginManager = new LoginManager();
	private CompanyAndYearSelectionManager objCompanyAndYearSelectionManager=new CompanyAndYearSelectionManager(); 

	private int pageCount;
	private int iStart = 1;
	private int iEnd = 10;
	private int iPageSize = 10;



}
