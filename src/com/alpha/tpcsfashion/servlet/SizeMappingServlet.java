package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

import com.alpha.tpcsfashion.beans.ApprovedPriceList;
import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.SizeMapping;
import com.alpha.tpcsfashion.beans.SizeRange;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.gridmaker.SizeMappingGridMaker;
import com.alpha.tpcsfashion.model.ColumnPreferenceManager;
import com.alpha.tpcsfashion.model.SizeMappingManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.UserInfo;
import com.alpha.tpcsfashion.util.Validator;

public class SizeMappingServlet {

	public void doDisplaySizeMapping(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

		try {
			HttpSession session=request.getSession();
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			
//			String strSearchQuery=getDefaultCriteria(request);
			String strSearchQuery="";
			String reqType=request.getParameter("request_type");
			
			if("Search".equalsIgnoreCase(reqType)){	
				strSearchQuery = getSearchCriteria(request);
				getAndSetAttributes(request);
			}
			

			doGetUserRights(request,response);
			
			organizeColumns(request,strSearchQuery);

			request.setAttribute("request_type", reqType);
			session.setAttribute("seletedScreenId", 2);
			request.setAttribute("invoke_servlet", "ApprovedPriceListServlet");
			request.setAttribute("invoke_method", "doDisplayApprovedPriceList");
			
			
			

			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_SIZEMAPPING, request,response);

			userInfo=null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void organizeColumns(HttpServletRequest request,String strSearchQuery)
	{
		try{
			TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
//			ExportToPdfTool.setRoundOff(userInfo);
			request.setAttribute("subdocument_id", SubdocumentId.SIZE_MAPPING);

			ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
			ColumnPreference DataList=cpm.getVisibleColumnNames(SubdocumentId.SIZE_MAPPING, userInfo);
			Map<String,String> visColMap=cpm.getVisibleColumnNamesMap(SubdocumentId.SIZE_MAPPING,userInfo.getUserId(),userInfo);

			List<String> listSelectedColumns=DataList.getVisibleColumns(); 
			List<String> orderBy=DataList.getOrderBy(); 

			String strPageNo = request.getParameter("pageno");
			int ipageno =Validator.convertToInteger(strPageNo);

			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			int pageCount= objMan.getPageCount(userInfo,pc, strSearchQuery);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);
			UserRights seRights=(UserRights) request.getSession().getAttribute("se_rights");
			UserRights sqRights=(UserRights) request.getSession().getAttribute("sq_rights");
			SizeMappingGridMaker objUtil=new SizeMappingGridMaker();
			
			List<Map<String,String>> objList =objMan.getAllSizeMappingListOnColumnMapping(pc,userInfo,strSearchQuery,orderBy);
			String Grid = objUtil.formSizeMappingGrid(userInfo,visColMap,listSelectedColumns, objList, ipageno, bundle,seRights,sqRights,pc);
			request.setAttribute("sizeMappingList_grid", Grid); 
			
			listSelectedColumns=null;
			objList=null;
			cpm=null;
			pc=null;
			objUtil=null;
			Grid=null;
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public void doDisplaySizeMappingAfterColumnOrganized(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        try{  
        	
        	HttpSession session=request.getSession();  
			TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
			String strTotalColumns = request.getParameter("total_columns");  
			String strRequestType=request.getParameter("request_type");
			String strSearchQuery="";

//			if("Search".equalsIgnoreCase(strRequestType)){	          
//				strSearchQuery=(String)request.getSession().getAttribute("search_query");
//			}	     
//			else{
//				if(request.getSession().getAttribute("search_query")!=null) 
//					request.getSession().removeAttribute("search_query"); 
//			}
			ColumnPreferenceManager cpm=new ColumnPreferenceManager();    
			boolean bFlag = cpm.updateColumnPreferences(strTotalColumns,SubdocumentId.SIZE_MAPPING,userInfo);

			organizeColumns(request,strSearchQuery);
			request.setAttribute("request_type", strRequestType);
			
			cpm=null;
       TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_SIZEMAPPING,request,response);
        }catch(Exception e){ 
          e.printStackTrace();      
        }
      }
	public void doNewSizeMapping(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {

			String mode=request.getParameter("mode");
			String detMode=request.getParameter("mode");
			request.setAttribute("mode",mode);
			request.setAttribute("det_mode",detMode);
			request.setAttribute("size_sched_id","0");
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_SIZEMAPPING, request,response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	
	
	public void doSaveSizeMapping(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {		      
			HttpSession session = request.getSession();
			 TPCSUser userInfo =(TPCSUser)session.getAttribute("user_info");
			 String saveType=request.getParameter("save_type");
			 ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			 String strPageNo = request.getParameter("pageno");
			 String headermode =request.getParameter("mode");
			 String DetailMode =request.getParameter("det_mode");
			 String scheduleNo = request.getParameter("schedule_no");
	         int status= Integer.parseInt(request.getParameter("status"));
	         String Remarks = request.getParameter("remark");
	         int sizeRangeId= Integer.parseInt(request.getParameter("size_range_id"));
	         String sizeRange = request.getParameter("size_range");
	         int PageNo = (strPageNo!=null?Integer.parseInt(strPageNo):1);
	         int size_sched_id=Validator.convertToInteger(request.getParameter("size_sched_id")!=null && !request.getParameter("size_sched_id").isEmpty()?request.getParameter("size_sched_id"):"0" );
			 int size_sched_Detid=Validator.convertToInteger(request.getParameter("size_sched_Detid")!=null && !request.getParameter("size_sched_Detid").isEmpty()?request.getParameter("size_sched_Detid"):"0" );
			 
	         SizeMapping objheadersize=new SizeMapping();

	         objheadersize.setMode(headermode);
	         objheadersize.setDetailMode(DetailMode);
	         objheadersize.setScheduleNo(scheduleNo);
	         objheadersize.setIsActive(status);
	         objheadersize.setRemark(Remarks);
	         objheadersize.setSizeRangeId(sizeRangeId);
	         objheadersize.setSizeRangeName(sizeRange);
	         objheadersize.setSizeScheduleId(size_sched_id);
	         objheadersize.setSizeScheduleDetId(size_sched_Detid);
//	        int sizeMappingDetailId = Integer.parseInt(request.getParameter("size_sched_det_id"));
	          
	        String sizeInput=doGetSizeInputGrid(request);
	      
	        SizeMapping  output = objMan.saveSizeMapping(userInfo,objheadersize,sizeInput); 
	        
	      System.out.println("output="+output.getSizeScheduleId());
	     
	      
	      if(output.getSizeScheduleId()!=0){  
	    	  if(saveType.equals("3")){//Save Type Save & New
	    		  
	    		  request.setAttribute("success_message","Size Mapping Saved Successfully" );
	    		  request.setAttribute("mode",'n');
				  request.setAttribute("det_mode",'n');
				  request.setAttribute("size_sched_id","0");
					TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_SIZEMAPPING, request,response);
			 }
	    	  else{
	    	  SizeMapping heatlst=objMan.getSizeMappingheat(output.getSizeScheduleId(),userInfo);
	    	  SizeMapping detList=objMan.getSizeMappingDetailsList(output.getSizeScheduleId(),userInfo);
	    	  
	          request.setAttribute("success_message","Size Mapping Saved Successfully" ); //bundle.getString("SizeMapping.SavedSuccessfully")
	          request.setAttribute("heatlst", heatlst);
	          request.setAttribute("detList", detList.getSizeMappingDetailList());    
	          request.setAttribute("mode",'e');
			  request.setAttribute("det_mode",'n');
			  request.setAttribute("size_sched_id",size_sched_id);
			  request.setAttribute("size_sched_Detid",size_sched_Detid);
			  TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_SIZEMAPPING, request, response);
	    	  }
	        }
	      else{            
	    	  request.setAttribute("mode",'n');
			  request.setAttribute("det_mode",'n');
	            request.setAttribute("error_message","Size Mapping SavedFailed");
	            TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_SIZEMAPPING, request, response);
	        }
	      request.setAttribute("page_no", PageNo);
	     

			userInfo=null;
	      
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	
	
	public void doEditSizeMapping(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {
			String Mode= Validator.trim(request.getParameter("mode"));
			SizeMapping appmode=new SizeMapping();
			appmode.setMode(Mode);

			int size_sched_id=Validator.convertToInteger(request.getParameter("size_sched_id"));
			int size_sched_Detid=Validator.convertToInteger(request.getParameter("size_sched_Detid"));
			appmode.setSizeScheduleId(size_sched_id);
			appmode.setSizeScheduleDetId(size_sched_Detid);
			
			HttpSession session=request.getSession();
			TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
			String clientId = (String)request.getSession().getAttribute("client_id");

			
			int iUserId=Integer.parseInt((String)request.getSession().getAttribute("login_user_id"));

			
			 SizeMapping heatlst=objMan.getSizeMappingheat(size_sched_id,userInfo);
	    	  SizeMapping detList=objMan.getSizeMappingDetailsList(size_sched_id,userInfo);
	    	  request.setAttribute("heatlst", heatlst);
	          request.setAttribute("detList", detList.getSizeMappingDetailList()); 
	          
			doGetUserRights(request,response);
			
			request.setAttribute("mode", appmode.getMode());
			
			request.setAttribute("det_mode",'n');
			request.setAttribute("size_sched_id",size_sched_id);
			request.setAttribute("size_sched_Detid",size_sched_Detid);
			
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_SIZEMAPPING, request,response);

			appmode=null;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	
	public void doEditSizeMappingDetails(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {
			String Mode= Validator.trim(request.getParameter("mode"));
			SizeMapping appmode=new SizeMapping();
			appmode.setMode(Mode);

			int size_sched_id=Validator.convertToInteger(request.getParameter("size_sched_id"));
			int size_sched_Detid=Validator.convertToInteger(request.getParameter("size_sched_Detid"));
			appmode.setSizeScheduleId(size_sched_id);
			appmode.setSizeScheduleDetId(size_sched_Detid);
			
			HttpSession session=request.getSession();
			TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
			String clientId = (String)request.getSession().getAttribute("client_id");

			
			int iUserId=Integer.parseInt((String)request.getSession().getAttribute("login_user_id"));

			
			 SizeMapping heatlst=objMan.getSizeMappingheat(size_sched_id,userInfo);
			 SizeMapping detList=objMan.getSizeMappingDetailsList(size_sched_id,userInfo);
	    	  SizeMapping detail=objMan.getEditSizeMappingDetailsList(appmode,userInfo);
	    	  
	    	  request.setAttribute("heatlst", heatlst);
	    	  request.setAttribute("detList", detList.getSizeMappingDetailList()); 
	          request.setAttribute("details", detail); 
	          request.setAttribute("sizeGrid", detail.getSizeData()); 
	          request.setAttribute("sizeIdList", detail.getSizeIdList()); 
			doGetUserRights(request,response);
			
			request.setAttribute("mode", appmode.getMode());
			request.setAttribute("size_sched_id",size_sched_id);
			request.setAttribute("size_sched_Detid",size_sched_Detid);
			
			
			request.setAttribute("det_mode",'e');
			request.setAttribute("size_sched", detail.getEditDetailsID());
			
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_SIZEMAPPING, request,response);

			appmode=null;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	
	
	public void doDeleteSizeMapping(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{

		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		int sizeSchedId=Validator.convertToInteger(request.getParameter("size_sched_id"));
		int page=Validator.convertToInteger(request.getParameter("page"));
		ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
		
		boolean isDeleted=objMan.deleteSizeMapping(userInfo,sizeSchedId);
		
		if(isDeleted)
		{
			
			request.setAttribute("success_message","Size Mapping Deleted");	//bundle.getString("ApprovedPriceList.ApprovedPriceListDelete")
			doDisplaySizeMapping(request,response);
		}
		else
		{
			request.setAttribute("error_message","Size Mapping Not Deleted");
			doDisplaySizeMapping(request,response);
		}
//		if(page>0)
//		{
//			ApprovedPriceList aplobj=new ApprovedPriceList();
//			aplobj.setMode("n");
//			doGetNewAndEditApprovedPriceList(request, response, 0, aplobj);
//
//		}else{
//			doDisplayApprovedPriceList(request, response); 
//		}

		bundle=null;
	}	
	public void doDeleteSizeMappingDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			TPCSUser userInfo =(TPCSUser)request.getSession().getAttribute("user_info");
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
			ApprovedPriceList appobj=new ApprovedPriceList();

			

			int sizeSchedId=Validator.convertToInteger(request.getParameter("size_sched_id"));
			int sizeSchedDetId=Validator.convertToInteger(request.getParameter("size_sched_Detid"));
			int iCount=objMan.deleteSizeMappingDetail(userInfo,sizeSchedId,sizeSchedDetId);
			
			
			if(iCount==1)
			{
				request.setAttribute("success_message","Size Mapping Detail Deteted");	//bundle.getString("ApprovedPriceList.ApprovedPriceListDetailDeleted")
				doEditSizeMapping(request,response);
			}
			else if(iCount==2)
			{
				request.setAttribute("success_message","Size Mapping  Deteted"); //bundle.getString("ApprovedPriceList.ApprovedPriceListDelete")
				doDisplaySizeMapping(request,response);
			}
			else
			{
				request.setAttribute("error_message","Size Mapping Detail Not Deteted");
				doEditSizeMapping(request,response);
			}
			
			appobj=null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	
	
	public String doGetSizeInputGrid(HttpServletRequest request){
		 StringBuffer buffer=new StringBuffer();
	try {
		 
//		    List<String> sizeIdList=null;
//		    List<String> detailsId=null;
			String detailId=request.getParameter("size_sched");
			if(!detailId.isEmpty() && detailId!=""){
				
			buffer.append("<size_schedules>");
			
			 detailId  = detailId.substring(0, detailId.length() - 1);
			 String[] detailIdarr=detailId.split(",");
			 List<String> detIdList=Arrays.asList(detailIdarr);
			 for(String detid:detIdList){
				if(!detid.isEmpty()){
		    String sizeIds=request.getParameter("size_sched_id_"+detid+"");
		    System.out.println("sizeIds=="+sizeIds);
			sizeIds  = sizeIds.substring(0, sizeIds.length() - 1);
			 String[] sizeIdarr=sizeIds.split(",");
			 List<String> sizeIdList=Arrays.asList(sizeIdarr);
			 for(String sid:sizeIdList){
		    buffer.append("<size_schedule>");	 
		    buffer.append("<size_schedule_id>");
		    buffer.append(detid);
		    buffer.append("</size_schedule_id>");
		    buffer.append("<size_id>");
		    buffer.append(sid);
		    buffer.append("</size_id>");
		    buffer.append("<rmsize_Range>");
		    buffer.append(request.getParameter("size_id_"+detid+"_"+sid+"_1"));
		    buffer.append("</rmsize_Range>");
		    buffer.append("<rmsizes>");
		    buffer.append(request.getParameter("size_id_"+detid+"_"+sid+"_2"));
		    buffer.append("</rmsizes>");
		    buffer.append("</size_schedule>");	 	 
			 }
				}
		} 
			 
		buffer.append("</size_schedules>");	 
//		System.out.println(buffer.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	   return buffer.toString();
	}	
	
	
	public void doUpdateSizeInputGrid(HttpServletRequest request, SizeRange objSizeRange,SizeMapping objsizemapping){
		try{
		
			TPCSUser ui=new UserInfo().getUserInfo(request);
		    String rowValue=request.getParameter("update");
		    List<String> sizeIdList=new ArrayList<String>();
		    String x="";
		    
		    objsizemapping.setSizeRange(objSizeRange);	
		    objsizemapping.setSizeIdList(sizeIdList);
		    objsizemapping.setValWithId(x);
		
	    
	    if(!rowValue.isEmpty()){
		    rowValue  = rowValue.substring(0, rowValue.length() - 1);
		    
		    
		    
		    String[] arr=rowValue.split(",");
		    for(String s1:arr){
		    	
		    	String[] ar=s1.split("&sp");
				String sizeId=ar[0];//sizeId
				sizeIdList.add(sizeId);
		    	String value=request.getParameter(s1);
		    	x=x+value+"&sp"+s1+","; 
		    }
		    
		    String m=x;
		    
		    String valWithId="";
		    
		    
		    objsizemapping.setValWithId(m);
		    objsizemapping.setSizeIdList(sizeIdList);
		    
//		    System.out.println(" inside objOrder.getSizeRange() :"+objOrder.getSizeRange());
//		    int v=objManager.updateSizeInputGridData(ui,objSizeRange,m,valWithId,insertMode,sizeIdList);
	    }
	    sizeIdList=null;
	    
		}
		catch(Exception e){
			e.printStackTrace();
		}
	   
	}	
	
public void doGetSizeGrid(HttpServletRequest request,HttpServletResponse response){
		try {
			int sizeRrangeId=Validator.convertToInteger(request.getParameter("size_Rrange_Id"));
			int sizeScheduleId=Validator.convertToInteger(request.getParameter("size_sched_id"));
			int sizeSchedDetId=Validator.convertToInteger(request.getParameter("size_sched_Detid"));
			
//			System.out.println("purchasePriceId===="+purchasePriceId);
			
//			String sizemode=request.getParameter("mode");
			String sizemode="n";
			TPCSUser userInfo=new UserInfo().getUserInfo(request);
			SizeMapping sizeObj=new SizeMapping();
			
			sizeObj.setSizeRangeId(sizeRrangeId);
			sizeObj.setSizeScheduleId(sizeScheduleId);
			sizeObj.setSizeScheduleDetId(sizeSchedDetId);
			
			SizeMapping sizeGrid=objMan.getSizeGrid(userInfo,sizeObj,sizemode);
			 
			 StringBuffer buffer=new StringBuffer();
				buffer.append("<size_mapping>");

				buffer.append("<size_map>");

				buffer.append("<data>");
				buffer.append(StringEscapeUtils.escapeXml(sizeGrid.getSizeData()));
				buffer.append("</data>");
				buffer.append("</size_map>");

				buffer.append(sizeGrid.getAutoCompleteIdNameString());
			 
				buffer.append("</size_mapping>");
				response.setContentType("text/xml");
				response.getWriter().write(buffer.toString());
			
			sizeGrid=null;
			userInfo=null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
public String getSearchCriteria(HttpServletRequest request){

	StringBuffer  strQuery=new StringBuffer("");
	TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
	
	String schedule_no=request.getParameter("schedule_no");
	int sizeRangeId=Validator.convertToInteger(request.getParameter("size_range_id"));
	

	
	if(schedule_no!=null && !schedule_no.isEmpty()){
		strQuery.append(" and ss..size_sched_ref="+schedule_no);
	}
	
	if(sizeRangeId!=0){
		strQuery.append(" and ssd.prod_size_range_id="+sizeRangeId);
	}
	
	
	return strQuery.toString();

}	
public void getAndSetAttributes(HttpServletRequest request){
	
	request.setAttribute("schedule_no",request.getParameter("schedule_no"));
	request.setAttribute("size_range",request.getParameter("size_range"));
	request.setAttribute("size_range_id",request.getParameter("size_range_id"));
			
}
	public void doGetUserRights(HttpServletRequest request, HttpServletResponse response)
	{
		TPCSUser userInfo=(TPCSUser)request.getSession().getAttribute("user_info");
		HttpSession session=request.getSession();

		rights =objRight.getUserRights(SubdocumentId.SIZE_MAPPING, userInfo);
		session.setAttribute("se_rights",rights);

	}

	SizeMappingManager objMan=new SizeMappingManager();
	UserRights rights=null;
	private UserRightsManager objRight = new UserRightsManager();
}
