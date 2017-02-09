package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alpha.tpcsfashion.beans.SizeRange;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.model.SizeRangeManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.UserInfo;
import com.alpha.tpcsfashion.util.Validator;


public class SizeRangeServlet {

	public void doDisplaySizeRange(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {


			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			String strSearhQuery="";

			if("Search".equalsIgnoreCase(request.getParameter("request_type"))){
				strSearhQuery = getSearchCriteria(request);
				getAndSetAttributes(request);
			}
			int pageCount= objMan.getPageCount(userInfo,strSearhQuery);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);

			List<SizeRange> sizeRangeList = objMan.getAllSizeRange(userInfo,pc,strSearhQuery); 
			request.setAttribute("size_range_list", sizeRangeList);

			doGetUserRights(request,response);

			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_SIZE_RANGE_MASTER, request,response);

			sizeRangeList=null;
			pc=null;
			userInfo=null;

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	public String getSearchCriteria(HttpServletRequest request){
		String strSearchCriteria="";
		int sizeRangeId=Validator.convertToInteger(request.getParameter("size_range_id"));
		int applicableTo=Validator.convertToInteger(request.getParameter("applicable_to"));
		if(sizeRangeId>0){
			strSearchCriteria=strSearchCriteria+" si.size_range_id="+sizeRangeId+" and";
		}
		if( applicableTo!=-1){
			strSearchCriteria=strSearchCriteria+" si.Applicable_to="+applicableTo+" and";
		}
		if(!strSearchCriteria.isEmpty()){
			strSearchCriteria=strSearchCriteria.substring(0,strSearchCriteria.length()-4);

		}
		return strSearchCriteria;

	}
	public void getAndSetAttributes(HttpServletRequest request){
		request.setAttribute("size_range",request.getParameter("size_range"));
		request.setAttribute("size_range_id",request.getParameter("size_range_id"));
		request.setAttribute("applicable_to",request.getParameter("applicable_to"));
	}
		public void doSaveSizeMaster(HttpServletRequest request , HttpServletResponse response){
			try {
				TPCSUser ui=new UserInfo().getUserInfo(request);
				SizeRange objsi=new SizeRange();
				objsi.setSizeId(Validator.convertToInteger(request.getParameter("size_id")));
				objsi.setSizeName(request.getParameter("size_name"));
				objsi.setIsActive(Validator.convertToInteger(request.getParameter("is_Active")));
				objsi=objMan.saveSizeMaster(objsi,ui);

				String strInsertDetails="";

				if(objsi.isSizeExists()){
					strInsertDetails="2";
				}
				else if(objsi.isInserted()){
					strInsertDetails="1";
				}
				else{
					strInsertDetails="0";
				}
				response.setContentType("text/html");
				response.getWriter().write(strInsertDetails);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	public void doNewSizeRange(HttpServletRequest request ,HttpServletResponse response)throws ServletException,IOException{
		SizeRange sr=new SizeRange();
		String mode=request.getParameter("mode");
		sr.setMode(mode);
		int sizeRangeId=Validator.convertToInteger(request.getParameter("size_range_id"));
		sr.setSizeRangeId(sizeRangeId);
		doNewAndEditSizeRange(request ,response,sr);
	}

	public void doNewAndEditSizeRange(HttpServletRequest request ,HttpServletResponse response,SizeRange sr)throws ServletException,IOException{
		try {

			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			sr.setSizeRangeId(sr.getSizeRangeId());
			String sizeRange="";
			String rowIds="0,1,2,3,";
			int rowCount=4;

			sr.setRowIds(rowIds);
			sr.setRowCount(rowCount);

			String[] arRowIds=rowIds.substring(0,rowIds.length()).split(",");
			List<String> rowIdList=Arrays.asList(arRowIds);

			int applicableTo=0;
			int isActive=0;
		
			
			if(sr.getMode().equalsIgnoreCase("e")){
				sr= objMan.getSizeRangeInfo(userInfo,sr);
				List<SizeRange> objList=sr.getSizeRangeList();

				for(SizeRange obj:objList){
					//sizeRangeId=obj.getSizeRangeId();
					sizeRange=obj.getSizeRange();
					applicableTo=obj.getApplicableTo();
					isActive=obj.getIsactive();
					break;
				}
				
				request.setAttribute("size_range_id", sr.getSizeRangeId());
				request.setAttribute("size_range", sizeRange);
				request.setAttribute("applicable_to", applicableTo);
				request.setAttribute("is_active", isActive);
				request.setAttribute("sizeRangeInfo", objList);
				rowCount= objList.size();

				objList=null;
			}else{
				request.setAttribute("row_ids", sr.getRowIds());
			}
		
			request.setAttribute("row_count", sr.getRowCount());

			request.setAttribute("row_id_list", sr.getRowIds());
			request.setAttribute("mode",sr.getMode());
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_SIZE_RANGE, request,response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doSaveSizeRange(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			List<SizeRange> objList=getSizeRangeParameter(request,response);

			String mode=request.getParameter("mode");

			int sizeRangeId=Validator.convertToInteger(request.getParameter("size_range_id"));
			String sizeName=request.getParameter("size_range");
			SizeRange si=new SizeRange();
			si.setSizeRange(request.getParameter("size_range"));
			si.setSizeRangeList(objList);
			si.setSizeRangeId(sizeRangeId);
			si.setSizeName(sizeName);
			si.setMode(mode);
			int coId=0;

			si=objMan.isMasterColorExist(userInfo,si);

			if(!si.isSizeRangeExists()){			 
				coId= objMan.saveSizeRange(userInfo,si,mode,sizeRangeId);
				if(coId>0){
					request.setAttribute("success_message", "Size Range Size Range Inserted");
				}
				else{
					request.setAttribute("error_message", "Size Range Size Range NotInserted");

				}}
			else {
				request.setAttribute("mode","e");
				request.setAttribute("warning_message","Sizerange not inserted.\nSize range ("+si.getSizeRange()+") already exists");
			}
			request.setAttribute("size_range_id",sizeRangeId);
			String saveType=request.getParameter("save_type");
			si.setSizeRangeId(coId);
			if(saveType.equals("1")){
				if(coId>0){
					si.setMode("e");
					doNewAndEditSizeRange(request,response,si);
				
			}
				}
			else {
				si.setMode("n"); 
				doNewAndEditSizeRange(request,response,si);
			
			}
			if(saveType.equals("3")){
				doDisplaySizeRange(request,response) ;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private List<SizeRange> getSizeRangeParameter(HttpServletRequest request,HttpServletResponse response) {
		List<SizeRange> objList=new ArrayList<SizeRange>();
		String rowIds=request.getParameter("row_ids");
		String[] arRowIds=rowIds.split(",");

		for(int i=0;i<arRowIds.length;i++){

			SizeRange objSize=new SizeRange();

			if(!arRowIds[i].isEmpty()){

				objSize.setSizeRange(request.getParameter("size_range"));
				objSize.setApplicableTo(Validator.convertToInteger(request.getParameter("applicable_to")));
				objSize.setIsActive(Validator.convertToInteger(request.getParameter("is_active")));
				objSize.setSlNo(Validator.convertToInteger(request.getParameter("si_no_"+arRowIds[i])));
				objSize.setSizeId(Validator.convertToInteger(request.getParameter("size_id_"+arRowIds[i])));
				objSize.setIsActivedet(Validator.convertToInteger(request.getParameter("is_active_det_"+arRowIds[i])));

			}
			objList.add(objSize);
			objSize=null;
		}
		return objList;
	}
	public void doDeleteSizeMaster(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
		boolean isDeleted=false;
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		int sizeRangeId=Validator.convertToInteger(request.getParameter("size_range_id"));

		isDeleted=objMan.deleteSize(userInfo,sizeRangeId);
		if (isDeleted)
		{
			request.setAttribute("success_message", bundle.getString("SizeRange.SizeRangeDeleted"));
		}

		else if (!isDeleted){
			request.setAttribute("error_message",bundle.getString("SizeRange.SizeRangeNotDeleted") );
		}

		doDisplaySizeRange(request, response);
		bundle=null;
		userInfo=null;
	}
	public void doGetUserRights(HttpServletRequest request, HttpServletResponse response)
	{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		HttpSession session=request.getSession();

		rights =objRight.getUserRights(SubdocumentId.SIZE_RANGE, userInfo);
		session.setAttribute("size_range_rights",rights);

	}
	public void doDeleteBulkSizeRange(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		String[] sizerangeId=request.getParameterValues("size_range_Id");

		SizeRange sr=new SizeRange();

		int sizeRangeId=0;
		for(int i=0;i<sizerangeId.length;i++){

			sizeRangeId=Integer.parseInt(sizerangeId[i]);	

			sr.setSizeRangeId(sizeRangeId);

			sr=objMan.deleteSizeRange(userInfo,sr);

		}
		if(sr.isDeleted())
		{
			request.setAttribute("success_message","Size range deleted successfully");
		}
		else
		{
			request.setAttribute("error_message","failed");
		}


		doDisplaySizeRange(request, response);
		sr=null;
	}	 
	public void doBulkActionActiveSizeRange(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		boolean isBulkAction=false;
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		String[] sizerangeId=request.getParameterValues("size_range_Id");
		String activeMode=request.getParameter("active_mode");

		isBulkAction=objMan.bulkActionSizeRange(userInfo,sizerangeId,activeMode);
		if (isBulkAction==true)	{
			if(activeMode.equals("1")){
				request.setAttribute("success_message","Size range Activated!");
			}
			else{
				request.setAttribute("success_message","Size range Inactivated!");
			}
		}
		if (isBulkAction== false){

			if(activeMode.equals("1")){
				request.setAttribute("error_message","Size range not Activated!");
			}
			else{
				request.setAttribute("error_message","Size range not Inactivated!");
			}
		}

		doDisplaySizeRange(request, response);
		userInfo=null;
	}

	SizeRangeManager objMan=new SizeRangeManager();
	UserRights rights=null;
	private UserRightsManager objRight = new UserRightsManager();
	List<SizeRange> sizeList;
}
