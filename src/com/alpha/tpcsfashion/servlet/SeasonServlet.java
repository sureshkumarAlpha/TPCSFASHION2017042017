package com.alpha.tpcsfashion.servlet;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.alpha.tpcsfashion.beans.Season;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.model.SeasonManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.Validator;
public class SeasonServlet {

	public void doDisplaySeason(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			//This is for page no..
			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			String strSearhQuery="";

			if("Search".equalsIgnoreCase(request.getParameter("request_type"))){
				strSearhQuery = getSearchCriteria(request);
				getAndSetAttributes(request);
			}
			int pageCount= objMan.getPageCount(userInfo,strSearhQuery);
			pc.setPageCount(pageCount);
				request.setAttribute("pc", pc);

			List<Season> seasonList = objMan.getSeasonList(userInfo,pc,strSearhQuery); 
			request.setAttribute("season_list", seasonList);

			doGetUserRights(request,response);

			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_SEASON_MASTER, request,response);

			seasonList=null;
			pc=null;
			userInfo=null;

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void getAndSetAttributes(HttpServletRequest request){
		request.setAttribute("season_name",request.getParameter("season_name"));
		request.setAttribute("season_id",request.getParameter("season_id"));
		request.setAttribute("from_date",request.getParameter("from_date"));
		request.setAttribute("to_date",request.getParameter("to_date"));
	}

	public String getSearchCriteria(HttpServletRequest request){

		String strSearchCriteria="";
		String seasonName= request.getParameter("season_name");
		String fromDate= request.getParameter("from_date");
		String toDate= request.getParameter("to_date");
		if(seasonName!=null && seasonName.length()>0){
			strSearchCriteria=strSearchCriteria+"  Season_Name like '%"+seasonName+"%' and ";
		}
		if(fromDate!=null &&fromDate.length()>0){
			strSearchCriteria=strSearchCriteria+" from_date>=convert(datetime,'"+fromDate+"',105) and";
		}
		if(toDate!=null && toDate.length()>0){
			strSearchCriteria=strSearchCriteria+" to_date<=convert(datetime,'"+toDate+"',105) and";
		}
		if(!strSearchCriteria.isEmpty()){
			strSearchCriteria=strSearchCriteria.substring(0,strSearchCriteria.length()-4);

		}
		return strSearchCriteria.toString();

	}

	public void doNewSeason(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {
			request.setAttribute("mode","n");
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_SEASON, request,response);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	private Season setAndGetParametter(HttpServletRequest request,HttpServletResponse response){
		String seasonName=request.getParameter("season_name");
		int seasonId=Validator.convertToInteger(request.getParameter("season_id"));
		String fromDate=request.getParameter("from_date");
		String toDate=request.getParameter("to_date");
		int status=Validator.convertToInteger(request.getParameter("is_active"));
		String mode=request.getParameter("mode");

		Season seObj=new Season();

		seObj.setSeasonName(seasonName);
		seObj.setSeasonId(seasonId);
		seObj.setFromDate(fromDate);
		seObj.setToDate(toDate);
		seObj.setIsActive(status);
		seObj.setMode(mode);
		return seObj;
	}

	public void doSaveSeason(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			int seasonId=Validator.convertToInteger(request.getParameter("season_id"));
			String seasonName=request.getParameter("season_name");
			String fromDate=request.getParameter("from_date");
			String toDate=request.getParameter("to_date");
			int status=Validator.convertToInteger(request.getParameter("is_active"));
			String mode=request.getParameter("mode");

			Season seObj=new Season();

			seObj.setSeasonName(seasonName);
			seObj.setSeasonId(seasonId);
			seObj.setFromDate(fromDate);
			seObj.setToDate(toDate);
			seObj.setIsActive(status);
			seObj.setMode(mode);

			int seId=0;

			seObj=objMan.isMasterSeasonExist(userInfo,seObj);

			if(!seObj.isSeasonExists() ){			 
				seId = objMan.insertSeason(seObj,userInfo);

				if(seId>0){
					mode="e";
					request.setAttribute("success_message",bundle.getString("Season.SeasonInserted")); 
				}
				else{
					mode="n";
					request.setAttribute("error_message",bundle.getString("Season.SeasonNotInserted")); 
				}
			}
			else if(seObj.isSeasonExists())	{
				request.setAttribute("mode", mode);
				request.setAttribute("warning_message","Season not inserted.\n Season name ("+seObj.getSeasonName()+") already exists");

			}

			String saveType=request.getParameter("save_type");

			if(saveType.equals("1")){
				if(seId>0){
					seObj=objMan.getSeasonInfo(1,userInfo,seId);
					request.setAttribute("season_info", seObj);
					request.setAttribute("mode", mode);
				}
				else{
					Season seObj1=new Season();
					seObj1=setAndGetParametter(request,response);
					request.setAttribute("season_info",seObj1);
				}

				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_SEASON, request,response);
			}

			else if(saveType.equals("3")){
				doDisplaySeason(request,response) ;
			}
			else{
				request.setAttribute("mode","n");
				doNewSeason(request,response);
			}

			seObj=null;
			userInfo=null;
			bundle=null;
		}
		catch (Exception e) {
			// TODO: handle exception 
			e.printStackTrace();
		}
	}

	public void doEditSeason(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		Season seObj=new Season();
		String mode=request.getParameter("mode");
		seObj.setMode(mode);
		request.setAttribute("mode", mode);
		int seasonId=Validator.convertToInteger(request.getParameter("season_id"));

		seObj=objMan.getSeasonInfo(1,userInfo,seasonId);
		request.setAttribute("season_info", seObj);

		TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_SEASON, request,response);

		seObj=null;
		userInfo=null;
	}	

	public void doDeleteSeasonMaster(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
		boolean isDeleted=false;
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		int seasonId=Validator.convertToInteger(request.getParameter("season_id"));

		isDeleted=objMan.deleteSeason(userInfo,seasonId);
		if (isDeleted){
			request.setAttribute("success_message", bundle.getString("Season.SeasonDeleted"));
		}

		else if (!isDeleted){
			request.setAttribute("error_message",bundle.getString("Season.SeasonNotDeleted") );
		}

		doDisplaySeason(request, response);
		bundle=null;
		userInfo=null;
	}

	public void doBulkActionSeason(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		boolean isBulkAction=false;
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		String[] seasonId=request.getParameterValues("bulkSeasonId");
		String active_mode=request.getParameter("active_mode");
		isBulkAction=objMan.bulkActionSeason(userInfo,seasonId,active_mode);

		if (isBulkAction==true)	{
			if(active_mode.equals("1")){
				request.setAttribute("success_message","Season activated!");
			}
			else{
				request.setAttribute("success_message","Season inactivated!");
			}
		}
		if (isBulkAction== false){
			if(active_mode.equals("1")){
				request.setAttribute("error_message","Season Not Activated!");
			}
			else{
				request.setAttribute("error_message","Season Not Inactivated!");
			}
		}
		doDisplaySeason(request, response);
		userInfo=null;
	}

	public void doDeleteBulkSeason(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		String[] seasonId=request.getParameterValues("bulkSeasonId");
		Season sr=new Season();
		int sizeRangeId=0;

		for(int i=0;i<seasonId.length;i++){

			sizeRangeId=Integer.parseInt(seasonId[i]);	
			sr.setSeasonId(sizeRangeId);
			sr=objMan.deleteBulkSeason(userInfo,sr);
		}
		if(sr.isDeleted()){
			request.setAttribute("success_message","Size range deleted successfully");
		}
		else{
			request.setAttribute("error_message","failed");
		}

		doDisplaySeason(request, response);
		sr=null;
	}	

	public void doGetUserRights(HttpServletRequest request, HttpServletResponse response){
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		HttpSession session=request.getSession();

		rights =objRight.getUserRights(SubdocumentId.SEASON, userInfo);
		session.setAttribute("season_rights",rights);
	}

	SeasonManager objMan=new SeasonManager();
	UserRights rights=null;
	private UserRightsManager objRight = new UserRightsManager();
}
