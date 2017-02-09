package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alpha.tpcsfashion.beans.GroupType;
import com.alpha.tpcsfashion.beans.Material;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.model.CompanyAndYearSelectionManager;
import com.alpha.tpcsfashion.model.SpecificationSettingManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.Validator;

public class SpecificationSettingServlet {
	private static final long serialVersionUID = 1L;
	
	public SpecificationSettingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
public void doDisplaySpecificationSetting(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
		try {
			HttpSession session = request.getSession();
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");

			int iSubDocumentId=SPECSETTING_ID;
			PageConfig pc=new PageConfig(request.getParameter("pageno"));
			pageCount = objManager.getTotalPages(iPageSize,userInfo);
			pc.setPageCount(pageCount);
			request.setAttribute("pc", pc);
			rights = objRight.getUserRights(iSubDocumentId,userInfo);

			String strSearhQuery="";
			if("Search".equalsIgnoreCase(request.getParameter("request_type"))){	         
				strSearhQuery = getSearchCriteria(request);
				getAndSetAttributes(request);
			}
			specification=objManager.getSpecificationSetting(pc,userInfo,strSearhQuery);

			request.setAttribute("specification_list", specification);
			request.setAttribute("page_count", pageCount);
			session.setAttribute("specsetting_rights", rights);
			request.setAttribute("mode","n");
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_SPECIFICATION_SETTING, request, response);        
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}		
	}




    public void doNewSpecificationSetting(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        try{
        	HttpSession session = request.getSession();
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			
			int iSubDocumentId=SPECSETTING_ID;
          String strPageNo = request.getParameter("pageno");
          groupTypes = objManager.getGroupTypeList(userInfo);
          request.setAttribute("is_new",1);
          request.setAttribute("page_no", strPageNo);
          request.setAttribute("grouptype_list", groupTypes);
          rights = objRight.getUserRights(iSubDocumentId,userInfo);
          session.setAttribute("specsetting_rights", rights);
          request.setAttribute("mode","n");
          TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_SPECIFICATION_SETTING,request,response);
        }catch(Exception e){
          e.printStackTrace();
          //NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);    
        }
      }
	
    public void doSaveSpecificationSetting(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        try{         
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
      	  
			int iLoginUserId = Integer.parseInt((String)request.getSession().getAttribute("login_user_id"));
			String savetype=request.getParameter("save_type");
			String mode=request.getParameter("mode");

			// get client Ip address and Host Name
			 String clientIP = request.getRemoteAddr();
			  String clintHost = request.getRemoteHost();
			  if (clintHost.equals(request.getRemoteAddr())) {
                 InetAddress addr = InetAddress.getByName(request.getRemoteAddr());
                 clintHost = addr.getHostName();
         }
			//end
			  
//			String strGroupTypeId = request.getParameter("strGroupTypeId");
			int strGroupTypeId=Validator.convertToInteger(request.getParameter("group_type"));
			String strGroupTypeNmae=request.getParameter("group_type_name");
			String strSpec1 = request.getParameter("specification1");
			String strSpec1Length = request.getParameter("specification1_length");
			String strSpec2 = request.getParameter("specification2");
			String strSpec2Length = request.getParameter("specification2_length");
			String strSpec3 = request.getParameter("specification3");
			String strSpec3Length = request.getParameter("specification3_length");
			String strSpec4 = request.getParameter("specification4");
			String strSpec4Length = request.getParameter("specification4_length");
			String strSpec5 = request.getParameter("specification5");
			String strSpec5Length = request.getParameter("specification5_length");
			String strSpec6 = request.getParameter("specification6");
			String strSpec6Length = request.getParameter("specification6_length");
			String strSpec7 = request.getParameter("specification7");
			String strSpec7Length = request.getParameter("specification7_length");

			GroupType objGroupType=new GroupType();	    		
			objGroupType.setGroupTypeId(strGroupTypeId);
			objGroupType.setGroupType(strGroupTypeNmae);
			Material objMaterial=new Material();
			objMaterial.setSpec1(strSpec1);
			objMaterial.setSpecLen1(Integer.parseInt(strSpec1Length));
			objMaterial.setSpec2(strSpec2);
			objMaterial.setSpecLen2(Integer.parseInt(strSpec2Length));
			objMaterial.setSpec3(strSpec3);
			objMaterial.setSpecLen3(Integer.parseInt(strSpec3Length));
			objMaterial.setSpec4(strSpec4);
			objMaterial.setSpecLen4(Integer.parseInt(strSpec4Length));
			objMaterial.setSpec5(strSpec5);
			objMaterial.setSpecLen5(Integer.parseInt(strSpec5Length));
			objMaterial.setSpec6(strSpec6);
			objMaterial.setSpecLen6(Integer.parseInt(strSpec6Length));
			objMaterial.setSpec7(strSpec7);
			objMaterial.setSpecLen7(Integer.parseInt(strSpec7Length));
			objMaterial.setGroupType(objGroupType);
			objMaterial.setIpAddress(clientIP);
			objMaterial.setHostName(clintHost);
			int PageNo = 1;
			boolean bFlag = objManager.insertSpecificationSetting(objMaterial,userInfo);

			if(bFlag){ 
				groupTypes = objManager.getGroupTypeList(userInfo);
				request.setAttribute("is_new",1);
				request.setAttribute("page_no", PageNo);
				request.setAttribute("success_message","Create new specification setting successful!");
				
			}else{
				groupTypes = objManager.getGroupTypeList(userInfo);
				request.setAttribute("is_new",1);
				request.setAttribute("page_no", PageNo);
				request.setAttribute("error_message", "Create new specification setting failed!");
			}  		
			if(savetype.equals("1"))
			{
				request.setAttribute("mode","e");
				 request.setAttribute("specsetting_info", objMaterial );
				request.setAttribute("grouptype_list", groupTypes);
				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_SPECIFICATION_SETTING,request,response);
			}else if(savetype.equals("2"))
			{
				doNewSpecificationSetting(request,response);
			}else{
				 doDisplaySpecificationSetting(request,response);
			}
        }catch(Exception e){
            e.printStackTrace();
            //NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
        }
      }
	
    
    
    
    
    public void doDeleteSpecificationSetting(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        try{
      	 
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");

//			String strGroupTypeId = request.getParameter("group_type");
			int strGroupTypeId=Validator.convertToInteger(request.getParameter("group_type"));
			int iPageNo = Integer.parseInt(request.getParameter("pageno"));
			String strSpec1 = request.getParameter("specification1");
			String strSpec1Length = request.getParameter("specification1_length");
			String strSpec2 = request.getParameter("specification2");
			String strSpec2Length = request.getParameter("specification2_length");
			String strSpec3 = request.getParameter("specification3");        
			String strSpec3Length = request.getParameter("specification3_length");
			String strSpec4 = request.getParameter("specification4");
			String strSpec4Length = request.getParameter("specification4_length");
			String strSpec5 = request.getParameter("specification5");
			String strSpec5Length = request.getParameter("specification5_length");
			String strSpec6 = request.getParameter("specification6");
			String strSpec6Length = request.getParameter("specification6_length");
			String strSpec7 = request.getParameter("specification7");
			String strSpec7Length = request.getParameter("specification7_length");


			GroupType objGroupType=new GroupType();	    		
			objGroupType.setGroupTypeId(strGroupTypeId);

			Material objMaterial=new Material();

			objMaterial.setSpec1(strSpec1);
			objMaterial.setSpecLen1(Integer.parseInt(strSpec1Length));
			objMaterial.setSpec2(strSpec2);
			objMaterial.setSpecLen2(Integer.parseInt(strSpec2Length));
			objMaterial.setSpec3(strSpec3);
			objMaterial.setSpecLen3(Integer.parseInt(strSpec3Length));
			objMaterial.setSpec4(strSpec4);
			objMaterial.setSpecLen4(Integer.parseInt(strSpec4Length));
			objMaterial.setSpec5(strSpec5);
			objMaterial.setSpecLen5(Integer.parseInt(strSpec5Length));
			objMaterial.setSpec6(strSpec6);
			objMaterial.setSpecLen6(Integer.parseInt(strSpec6Length));
			objMaterial.setSpec7(strSpec7);
			objMaterial.setSpecLen7(Integer.parseInt(strSpec7Length));

			objMaterial.setGroupType(objGroupType);


    
         boolean bFlag = objManager.deleteSpecificationSetting(objMaterial,userInfo);
            if(bFlag){
            	request.setAttribute("success_message", "specification  setting deleted successfully.");
            	
              }
            else{
             request.setAttribute("error_message", "specification  setting delete failed. Selected supplier might associated with another entities!");
            }
	      
            doDisplaySpecificationSetting(request,response);
            objMaterial=null;
            objGroupType=null;
        }catch(Exception e){
            e.printStackTrace();
            //NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
        }
      }
   
        
  public void doEditSpecificationSetting(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        try{
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");

			int iPageNo = Integer.parseInt(request.getParameter("pageno"));
//			String strGroupTypeId = request.getParameter("group_type");
			int strGroupTypeId=Validator.convertToInteger(request.getParameter("group_type"));
			String strSpec1 = request.getParameter("specification1");
			String strSpec1Length = request.getParameter("specification1_length");
			String strSpec2 = request.getParameter("specification2");
			String strSpec2Length = request.getParameter("specification2_length");
			String strSpec3 = request.getParameter("specification3");
			String strSpec3Length = request.getParameter("specification3_length");
			String strSpec4 = request.getParameter("specification4");
			String strSpec4Length = request.getParameter("specification4_length");
			String strSpec5 = request.getParameter("specification5");
			String strSpec5Length = request.getParameter("specification5_length");
			String strSpec6 = request.getParameter("specification6");
			String strSpec6Length = request.getParameter("specification6_length");
			String strSpec7 = request.getParameter("specification7");
			String strSpec7Length = request.getParameter("specification7_length");


			GroupType objGroupType=new GroupType();	    		
			objGroupType.setGroupTypeId(strGroupTypeId);

			Material objMaterial=new Material();
			objMaterial.setGroupType(objGroupType);

			objMaterial.setSpec1(strSpec1);
			objMaterial.setSpecLen1(Integer.parseInt(strSpec1Length));
			objMaterial.setSpec2(strSpec2);
			objMaterial.setSpecLen2(Integer.parseInt(strSpec2Length));
			objMaterial.setSpec3(strSpec3);
			objMaterial.setSpecLen3(Integer.parseInt(strSpec3Length));
			objMaterial.setSpec4(strSpec4);
			objMaterial.setSpecLen4(Integer.parseInt(strSpec4Length));
			objMaterial.setSpec5(strSpec5);
			objMaterial.setSpecLen5(Integer.parseInt(strSpec5Length));
			objMaterial.setSpec6(strSpec6);
			objMaterial.setSpecLen6(Integer.parseInt(strSpec6Length));
			objMaterial.setSpec7(strSpec7);
			objMaterial.setSpecLen7(Integer.parseInt(strSpec7Length));


   		groupTypes = objManager.getGroupTypeList(userInfo);
   		Material objMaterials = objManager.getSpecsettingInfo(objMaterial,userInfo);
   		request.setAttribute("grouptype_list", groupTypes);
   		request.setAttribute("page_no", iPageNo);
   		request.setAttribute("specsetting_info", objMaterials );
   	    request.setAttribute("mode","e");

   	 objGroupType=null;
   	objMaterial=null;
         TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_SPECIFICATION_SETTING,request,response);
        }catch(Exception e){
            e.printStackTrace();
            //NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
        }
      }

    
    
    public void doupdateSpecificationSetting(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        try{
			TPCSUser userInfo= (TPCSUser)request.getSession().getAttribute("user_info");
			String savetype=request.getParameter("save_type");
			String mode=request.getParameter("mode");
			
			String strPageNo = request.getParameter("pageno");
			int PageNo = (strPageNo==null ||strPageNo== "" ?1:Integer.parseInt(strPageNo));
			String strSpec11 = request.getParameter("specification11");
			String strSpec11Length = request.getParameter("specification11_length");
			String strSpec22 = request.getParameter("specification22");
			String strSpec22Length = request.getParameter("specification22_length");
			String strSpec33 = request.getParameter("specification33");        
			String strSpec33Length = request.getParameter("specification33_length");
			String strSpec44 = request.getParameter("specification44");
			String strSpec44Length = request.getParameter("specification44_length");
			String strSpec55 = request.getParameter("specification55");
			String strSpec55Length = request.getParameter("specification55_length");
			String strSpec66 = request.getParameter("specification66");
			String strSpec66Length = request.getParameter("specification66_length");
			String strSpec77 = request.getParameter("specification77");
			String strSpec77Length = request.getParameter("specification77_length");
//			String strGroupTypeId = request.getParameter("group_type1");
			int strGroupTypeId=Validator.convertToInteger(request.getParameter("group_type1"));
			String strSpec1 = request.getParameter("specification1");
			String strSpec1Length = request.getParameter("specification1_length");
			String strSpec2 = request.getParameter("specification2");
			String strSpec2Length = request.getParameter("specification2_length");
			String strSpec3 = request.getParameter("specification3");        
			String strSpec3Length = request.getParameter("specification3_length");
			String strSpec4 = request.getParameter("specification4");  
			String strSpec4Length = request.getParameter("specification4_length");
			String strSpec5 = request.getParameter("specification5");
			String strSpec5Length = request.getParameter("specification5_length");
			String strSpec6 = request.getParameter("specification6");
			String strSpec6Length = request.getParameter("specification6_length");
			String strSpec7 = request.getParameter("specification7");
			String strSpec7Length = request.getParameter("specification7_length");
			String strGroupTypeNmae=request.getParameter("group_type_name");
			GroupType objGroupType=new GroupType();	    		
			objGroupType.setGroupTypeId(strGroupTypeId);
			objGroupType.setGroupType(strGroupTypeNmae);
			Material objMat = new Material();
		    objMat.setSpec1(strSpec11);
			objMat.setSpecLen1(Integer.parseInt(strSpec11Length));
			objMat.setSpec2(strSpec22);
			objMat.setSpecLen2(Integer.parseInt(strSpec22Length));
			objMat.setSpec3(strSpec33);
			objMat.setSpecLen3(Integer.parseInt(strSpec33Length));
			objMat.setSpec4(strSpec44);
			objMat.setSpecLen4(Integer.parseInt(strSpec44Length));
			objMat.setSpec5(strSpec55);
			objMat.setSpecLen5(Integer.parseInt(strSpec55Length));
			objMat.setSpec6(strSpec66);
			objMat.setSpecLen6(Integer.parseInt(strSpec66Length));
			objMat.setSpec7(strSpec77);
			objMat.setSpecLen7(Integer.parseInt(strSpec77Length));
			objMat.setGroupType(objGroupType);

			Material objMaterial=new Material();

			objMaterial.setSpec1(strSpec1);
			objMaterial.setSpecLen1(Integer.parseInt(strSpec1Length));
			objMaterial.setSpec2(strSpec2);
			objMaterial.setSpecLen2(Integer.parseInt(strSpec2Length));
			objMaterial.setSpec3(strSpec3);
			objMaterial.setSpecLen3(Integer.parseInt(strSpec3Length));
			objMaterial.setSpec4(strSpec4);
			objMaterial.setSpecLen4(Integer.parseInt(strSpec4Length));
			objMaterial.setSpec5(strSpec5);
			objMaterial.setSpecLen5(Integer.parseInt(strSpec5Length));
			objMaterial.setSpec6(strSpec6);
			objMaterial.setSpecLen6(Integer.parseInt(strSpec6Length));
			objMaterial.setSpec7(strSpec7);
			objMaterial.setSpecLen7(Integer.parseInt(strSpec7Length));
			objMaterial.setGroupType(objGroupType);
			boolean bFlag = objManager.updateSpecificationSetting(objMaterial,objMat,userInfo);
			objGroupType=null;
			objMat=null;
        	 if(bFlag){
        		  groupTypes = objManager.getGroupTypeList(userInfo);
                  request.setAttribute("success_message", "Updated succesfully.");
        	  }else{
                 
        		  request.setAttribute("page_no", PageNo);
        		  groupTypes = objManager.getGroupTypeList(userInfo);
        		  request.setAttribute("error_message", "Update group failed.");
        	  }
        	 if(savetype.equals("1"))
 			{
 				request.setAttribute("mode","e");
 				 request.setAttribute("specsetting_info", objMaterial );
 				request.setAttribute("grouptype_list", groupTypes);
 				TPCSCommonUtil.RedirectURL(TPCSRedirectPage.ADD_NEW_SPECIFICATION_SETTING,request,response);
 			}else if(savetype.equals("2"))
 			{
 				doNewSpecificationSetting(request,response);
 			}else{
 				 doDisplaySpecificationSetting(request,response);
 			}
    		  
    	 }catch(Exception e){
            e.printStackTrace();
            //NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
        }
      }
    
    public String getSearchCriteria(HttpServletRequest request){

		StringBuffer  strQuery=new StringBuffer("");


		int groupType=Validator.convertToInteger(request.getParameter("group_type_id"));
		String spec=request.getParameter("specification");
				
		if(groupType!=0 && groupType!=-1){
			strQuery.append("  and gt.group_type_id="+groupType);
			}
		if(spec!=null && !spec.isEmpty()){
			strQuery.append("  and (ss.spec1='"+spec+"' or ss.spec2='"+spec+"' or ss.spec3='"+spec+"' or ss.spec4='"+spec+"' or ss.spec5='"+spec+"' or ss.spec6='"+spec+"' or ss.spec7='"+spec+"')");
		}
		return strQuery.toString();

	}    
    
    public void getAndSetAttributes(HttpServletRequest request){
    	
		request.setAttribute("group_type_id",request.getParameter("group_type_id"));
		request.setAttribute("group_type_name",request.getParameter("group_type_name")); 
		request.setAttribute("specification",request.getParameter("specification"));
	}

     
    
	
	SpecificationSettingManager objManager=new SpecificationSettingManager();
	private int iStart = 1; 
	private int iEnd = 10;
	private int iPageSize = 10;
	private int pageCount;
	private List<Material> specification = null;
	private List<GroupType> groupTypes = null;
    public String strDataSource = null;
    public String strDataBase = null;
    public String strServerIP = null;
    public String strSqlusername = null;
    public String strSqlpassword = null;
	
  public static final int SPECSETTING_ID=30;
    private UserRights rights = null;
	private UserRightsManager objRight = new UserRightsManager();
	private CompanyAndYearSelectionManager objCompany = new CompanyAndYearSelectionManager();

}
