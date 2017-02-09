package com.alpha.tpcsfashion.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringEscapeUtils;

import com.alpha.tpcsfashion.beans.Generic;
import com.alpha.tpcsfashion.beans.MailInfo;
import com.alpha.tpcsfashion.beans.Profiles;
import com.alpha.tpcsfashion.beans.Role;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.User;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.mail.SendMail;
import com.alpha.tpcsfashion.util.SQLUtil;

public class UserDAO {
	public int getTotalUsers(Connection con,User objUser)throws SQLException,Exception{    
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtEntityRightsCount=null;
	    ResultSet rsEntityRightsCount = null;
	    int iCount = 0;
	    try{ 
	    	pstmtEntityRightsCount = con.prepareStatement(SQLUtil.USERS_COUNT);
	    	pstmtEntityRightsCount.setInt(1, objUser.getEntityId());
	    	pstmtEntityRightsCount.setInt(2, objUser.getCustomerId());
	    	pstmtEntityRightsCount.setInt(3, objUser.getFactoryId());
	    	/*if( objUser.getEntityId()!=-1)
	    	{
	    		pstmtEntityRightsCount.setInt(1, objUser.getEntityId());
	    	}
	    	else
	    	{
	    		pstmtEntityRightsCount.setNull(1, Types.INTEGER);
	    	}
	    	
	    	if( objUser.getCustomerId()!=-1)
	    	{
	    		pstmtEntityRightsCount.setInt(2, objUser.getCustomerId());
	    	}
	    	else
	    	{
	    		pstmtEntityRightsCount.setNull(2, Types.INTEGER);
	    	}
	    	
	    	if( objUser.getFactoryId()!=-1)
	    	{
	    		pstmtEntityRightsCount.setInt(3, objUser.getFactoryId());
	    	}
	    	else
	    	{
	    		pstmtEntityRightsCount.setNull(3, Types.INTEGER);
	    	}*/
	    					    	
	    	rsEntityRightsCount = pstmtEntityRightsCount.executeQuery();
	    	rsEntityRightsCount.next();
	      iCount = rsEntityRightsCount.getInt(1);
	    }finally{     
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pstmtEntityRightsCount;
	      ArrayOfResultSet = new ResultSet[1];
	      ArrayOfResultSet[0] =  rsEntityRightsCount;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return iCount;
	  }
	 public List<User> getUsers(Connection con,User objUser,PageConfig pc,Map<Integer,Integer> map)throws SQLException,Exception{    
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;		   
		    ResultSet rsGetRole = null;    
		    List<User> users=new ArrayList<User>();
		    CallableStatement cstmtGetUsers=null;
		    try{      
		    	System.out.println(pc.iStart+"..."+pc.iEnd+"..."+objUser.getEntityId()+"..."+objUser.getCustomerId()+"..."+objUser.getFactoryId());
		    	cstmtGetUsers = con.prepareCall("{call pr_getusers(?,?,?,?,?)}");
		    	cstmtGetUsers.setInt(1,pc.iStart-1);
		    	cstmtGetUsers.setInt(2,pc.iEnd-1);
		    	System.out.println(1);
		    	cstmtGetUsers.setInt(3,objUser.getEntityId());
		    	System.out.println(2);
		    	cstmtGetUsers.setInt(4,objUser.getCustomerId());
		    	System.out.println(3);
		    	cstmtGetUsers.setInt(5,objUser.getFactoryId());
		    	System.out.println(4);
		    	rsGetRole = cstmtGetUsers.executeQuery();
		      while(rsGetRole.next()){
		       User objUserlist = new User();
		       Role objRole=new Role();
		       Profiles objProfile=new Profiles();
		       objUserlist.setUserId(rsGetRole.getInt(1));
		       System.out.println(5);
		       objUserlist.setUserName(rsGetRole.getString(2));
		       System.out.println(6);
		       objUserlist.setFirstName(rsGetRole.getString(3));
		       System.out.println(7);
		       objUserlist.setLastName(rsGetRole.getString(4));
		       System.out.println(8);
		       objRole.setRoleName(rsGetRole.getString(5));
		       System.out.println(9);
		       objUserlist.setEntityName(rsGetRole.getString(7));
		       System.out.println(10);
		       objUserlist.setRoles(objRole);
		       System.out.println(11);
		       objProfile.setProfileName(rsGetRole.getString(6));
		       System.out.println(12);
		       objUserlist.setProfiles(objProfile);
		       System.out.println(13);
//		       objUserlist.setEntityId(rsGetRole.getInt(8));
//		       objUserlist.setCustomerId(rsGetRole.getInt(9));
//		       objUserlist.setFactoryId(rsGetRole.getInt(10));
		       objUserlist.setIsActive(map.get(rsGetRole.getInt(1)));  
		       System.out.println(14);
		       users.add(objUserlist);
		      }
		    }finally{
		    	  if(cstmtGetUsers!=null)
		    		  cstmtGetUsers.close();
		    
		      ArrayOfPreparedStatement = new PreparedStatement[0];				       
		      ArrayOfResultSet = new ResultSet[1];      
		      ArrayOfResultSet[0] = rsGetRole;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return users;
		  } 
	 public int getIsSysAdmin(Connection con,TPCSUser ui)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmt=null;
		    ResultSet rs = null;  
		int isSysAdmin=0;
		    try{      
		    	pstmt = con.prepareStatement(SQLUtil.GET_IS_SYS_ADMIN);   
		    	pstmt.setInt(1, ui.getUserId());
		    	rs = pstmt.executeQuery();
		      while(rs.next()){
		    	  isSysAdmin=rs.getInt(1);
		      }
		    }finally{      
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] =  pstmt;
		      ArrayOfResultSet = new ResultSet[1];      
		      ArrayOfResultSet[0] = rs;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return isSysAdmin;
		  }
	 public boolean checkNoOfUsers(Connection con,TPCSUser ui)throws SQLException,Exception{
		  PreparedStatement ArrayOfPreparedStatement[] = null;
		  ResultSet ArrayOfResultSet[] = null;
		  
		    PreparedStatement pstmtCountUserInfo= null;
		    ResultSet rsCountUserInfo = null;
		    PreparedStatement pstmtCountClientInfo= null;
		    ResultSet rsCountClientInfo = null;
		    boolean bFlag = false; 
		    int iCount1=0;
		    int iCount2=0;
		    try{ 
				 pstmtCountUserInfo = con.prepareStatement(SQLUtil.COUNT_NO_OF_USER_INFO_ACTIVE_USERS);
		    	 pstmtCountUserInfo.setInt(1, ui.getclientId());
		    	 rsCountUserInfo=pstmtCountUserInfo.executeQuery();
		    	while(rsCountUserInfo.next()){
		    		 iCount1 = rsCountUserInfo.getInt(1);     
		      }
		    	
		    	pstmtCountClientInfo = con.prepareStatement(SQLUtil.GET_NO_OF_USERS);
		    	pstmtCountClientInfo.setInt(1, ui.getclientId());
		    	rsCountClientInfo=pstmtCountClientInfo.executeQuery();
		    	while(rsCountClientInfo.next()){
		    		 iCount2 = rsCountClientInfo.getInt(1);     
		      }
		    	
		    	bFlag = (iCount2>iCount1)?true:false;   
		  
		    }finally{     
		    	ArrayOfPreparedStatement = new PreparedStatement[2];
		        ArrayOfPreparedStatement[0] =pstmtCountUserInfo;
		        ArrayOfPreparedStatement[1] =pstmtCountClientInfo;
		        ArrayOfResultSet = new ResultSet[2];
		        ArrayOfResultSet[0] = rsCountUserInfo;
		        ArrayOfResultSet[1] =rsCountClientInfo;
		       
		        DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return bFlag;
		  }
	 public List<Generic> getLanguage(Connection con)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtGetLanguage=null;
		    ResultSet rsGetLanguage = null;  
		    List<Generic> listlanguage=new ArrayList<Generic>();
		    try{      
		    	pstmtGetLanguage = con.prepareStatement(SQLUtil.GET_LANGUAGE);        
		    	rsGetLanguage = pstmtGetLanguage.executeQuery();
		      while(rsGetLanguage.next()){
		        Generic objGeneric = new Generic();
		        objGeneric.setId(rsGetLanguage.getInt(1));
		        objGeneric.setName(rsGetLanguage.getString(2));
		        listlanguage.add(objGeneric);
		      }
		    }finally{      
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] =  pstmtGetLanguage;
		      ArrayOfResultSet = new ResultSet[1];      
		      ArrayOfResultSet[0] = rsGetLanguage;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return listlanguage;
		  }
	 public String getEntityRoles(Connection con,int ientityId,int icustomerId,int factoryId)throws SQLException,Exception{    
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtGetEntityRoles=null;
		    ResultSet rsGetEntityRoles = null;    
		    StringBuffer strBuffer = new StringBuffer();
		    try{      
		      System.out.println("ientityId :"+ientityId);
		      System.out.println("icustomerId :"+icustomerId);
		      System.out.println("factoryId :"+factoryId);
		    	pstmtGetEntityRoles = con.prepareStatement(SQLUtil.GET_ENTITY_ROLES);  
		    	pstmtGetEntityRoles.setInt(1, ientityId);
		    	pstmtGetEntityRoles.setInt(2, icustomerId);
		    	pstmtGetEntityRoles.setInt(3, factoryId);
		    	
		    	
		    	rsGetEntityRoles = pstmtGetEntityRoles.executeQuery();
		      strBuffer.append("<roles>");
		     
		      while(rsGetEntityRoles.next()){
		    	  String roleName=rsGetEntityRoles.getString(2);
		    	  roleName=StringEscapeUtils.escapeXml(roleName);
		        strBuffer.append("<role><roleid>"+rsGetEntityRoles.getInt(1)+"</roleid><rolename>"+roleName+"</rolename></role>");
		        }
		      strBuffer.append("</roles>");
		      
		      
		    }finally{
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmtGetEntityRoles;
		      ArrayOfResultSet = new ResultSet[1];      
		      ArrayOfResultSet[0] = rsGetEntityRoles;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return strBuffer.toString();
		  }
	 public String getEntityProfiles(Connection con,int ientityId)throws SQLException,Exception{    
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtGetEntityProfiles=null;
		    ResultSet rsGetEntityProfiles = null;    
		    StringBuffer strBuffer = new StringBuffer();
		    try{      
		      
		    	pstmtGetEntityProfiles = con.prepareStatement(SQLUtil.GET_ENTITY_PROFILES);  
		    	pstmtGetEntityProfiles.setInt(1, ientityId);				    	
		    	rsGetEntityProfiles = pstmtGetEntityProfiles.executeQuery();
		      strBuffer.append("<profiles>");
		      while(rsGetEntityProfiles.next()){
		        strBuffer.append("<profile><profileid>"+rsGetEntityProfiles.getInt(1)+"</profileid><profilename>"+rsGetEntityProfiles.getString(2)+"</profilename></profile>");
		      }
		      strBuffer.append("</profiles>");
		    }finally{
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmtGetEntityProfiles;
		      ArrayOfResultSet = new ResultSet[1];      
		      ArrayOfResultSet[0] = rsGetEntityProfiles;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return strBuffer.toString();
		  }
	 public String getProfileDocumentList(ResourceBundle bundle,Connection con, int profileId,List<Integer> list)throws SQLException,Exception{
		  PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;    
		    ResultSet rsGetDocuments = null;   
		    PreparedStatement pstmtGetDocuments = null;
		    PreparedStatement pstmtGetSelectedDocuments = null;
		    ResultSet rsGetSelectedDocuments = null;
		    StringBuffer strBuffer = new StringBuffer();
		    try{      
		      
		    	pstmtGetDocuments = con.prepareStatement(SQLUtil.GET_PROFILE_DOCUMENTS);
		    	pstmtGetDocuments.setInt(1, profileId);
		    	rsGetDocuments = pstmtGetDocuments.executeQuery();
//		      strBuffer.append("<div id=\"sub_header\" style=\"position:absolute; border-style:none;margin-bottom:0;margin-top:0;height:220px; VISIBILITY:visible; OVERFLOW:auto;width:"+iBomWidth+"px;\">");
		      strBuffer.append("<table class=\"table table-bordered table-condensed\">");        
		      strBuffer.append("<tr class=\"header\">");
		      strBuffer.append("<th class=\"subpageheader\" nowrap=\"nowrap\">"+bundle.getString("Profile.Page")+"</th>");
		      strBuffer.append("<th class=\"subpageheader\" nowrap=\"nowrap\">"+bundle.getString("Profile.Entry")+" &nbsp;<div class=\"checkbox\"> <input type=\"checkbox\" class=\"checkbox_1\" name=\"headerentry\" id=\"headerentry\" onclick=\"checkuncheckprofileentryrights()\" /><label class=\"checkbox_1\"  for=\"headerentry\"></label></div></th>");
		      strBuffer.append("<th class=\"subpageheader\" nowrap=\"nowrap\">"+bundle.getString("Common.Edit")+"&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headeredit\" id=\"headeredit\" onclick=\"checkuncheckprofileeditrights()\" /><label class=\"checkbox_1\"  for=\"headeredit\"></label></div></th>");
		      strBuffer.append("<th class=\"subpageheader\" nowrap=\"nowrap\">"+bundle.getString("Common.Delete")+"&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headerdelete\" id=\"headerdelete\" onclick=\"checkuncheckprofiledeleterights()\" /><label class=\"checkbox_1\"  for=\"headerdelete\"></label></div></th>");
		      strBuffer.append("<th class=\"subpageheader\" nowrap=\"nowrap\">"+bundle.getString("Common.View")+"&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headerview\" id=\"headerview\" onclick=\"checkuncheckprofileviewrights()\" /><label class=\"checkbox_1\"  for=\"headerview\"></label></div></th>");
		      strBuffer.append("<th class=\"subpageheader\" nowrap=\"nowrap\">"+bundle.getString("Profile.Excel")+"&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headerexcel\" id=\"headerexcel\" onclick=\"checkuncheckprofileexcelrights()\" /><label class=\"checkbox_1\"  for=\"headerexcel\"></label></div></th>");
		      strBuffer.append("<th class=\"subpageheader\" nowrap=\"nowrap\">"+bundle.getString("Profile.Print")+"&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headerprint\" id=\"headerprint\" onclick=\"checkuncheckprofileprintrights()\" /><label class=\"checkbox_1\"  for=\"headerprint\"></label></div></th>");
		      strBuffer.append("<th class=\"subpageheader\" nowrap=\"nowrap\">"+bundle.getString("Profile.Approval")+"&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headerapproval\" id=\"headerapproval\" onclick=\"checkuncheckprofileapprovalrights()\" /><label class=\"checkbox_1\"  for=\"headerapproval\"></label></div></th>");
		      strBuffer.append("</tr>");
//		      strBuffer.append("</div>");
//		      strBuffer.append("<div id=\"sub_tblbody\" style=\"position:absolute; border-style:none;margin-bottom:0;margin-top:0; VISIBILITY:visible; OVERFLOW:auto; HEIGHT:1;width:"+iBomWidth+"px;\">");
		      int i=1;
		      while(rsGetDocuments.next()){
		        if(i%2==0)
		          strBuffer.append("<tr class=\"eventabledata\" valign=\"top\">");
		        else
		          strBuffer.append("<tr class=\"oddtabledata\" valign=\"top\">");
		        strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        
		        System.out.println("bundle check :"+bundle.getString("Sales.Transactions.Quotations"));

		        
		        if(bundle.containsKey(rsGetDocuments.getString(2))){
		        	strBuffer.append(bundle.getString(rsGetDocuments.getString(2)));
		        }
		        else{
		        	strBuffer.append(rsGetDocuments.getString(2));
		        }
		        strBuffer.append("</td>");
		        strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"entry"+rsGetDocuments.getString(1)+"\" id=\"entry"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
		        
		        pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_ENTRY_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, profileId);
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		        while(rsGetSelectedDocuments.next()){
		        	list.add(rsGetSelectedDocuments.getInt(1));
		        }
		    	
		        if(list.contains(rsGetDocuments.getInt(1))){
		          strBuffer.append("checked=\"checked\"");
		        }
		        
		        list.clear();
		        strBuffer.append("/><label class=\"checkbox_1\"  for=\"entry"+rsGetDocuments.getString(1)+"\"></label>");
		        strBuffer.append("</div>");   
		        strBuffer.append("</td>");
		        
		        strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\"  name=\"edit"+rsGetDocuments.getString(1)+"\" id=\"edit"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
		        pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_EDIT_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, profileId);
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		        while(rsGetSelectedDocuments.next()){
		        	list.add(rsGetSelectedDocuments.getInt(1));
		        }
		        if(list.contains(rsGetDocuments.getInt(1))){
		          strBuffer.append("checked=\"checked\"");
		        }
		        list.clear();
		        strBuffer.append("/><label class=\"checkbox_1\"  for=\"edit"+rsGetDocuments.getString(1)+"\"></label>");
		        strBuffer.append("</div>");       
		        strBuffer.append("</td>");
		        
		        strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\"  name=\"delete"+rsGetDocuments.getString(1)+"\" id=\"delete"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
		        pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_DELETE_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, profileId);
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		        while(rsGetSelectedDocuments.next()){
		        	list.add(rsGetSelectedDocuments.getInt(1));
		        }
		        if(list.contains(rsGetDocuments.getInt(1))){
		          strBuffer.append("checked=\"checked\"");
		        }
		        list.clear();
		        strBuffer.append("/><label class=\"checkbox_1\"  for=\"delete"+rsGetDocuments.getString(1)+"\"></label>");
		        strBuffer.append("</div>");      
		       strBuffer.append("</td>");
		        
		       strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"view"+rsGetDocuments.getString(1)+"\" id=\"view"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
		        pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_VIEW_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, profileId);
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		        while(rsGetSelectedDocuments.next()){
		        	list.add(rsGetSelectedDocuments.getInt(1));
		        }
		        if(list.contains(rsGetDocuments.getInt(1))){
		          strBuffer.append("checked=\"checked\"");
		        }
		        list.clear();
		        strBuffer.append("/><label class=\"checkbox_1\"  for=\"view"+rsGetDocuments.getString(1)+"\"></label>");
		        strBuffer.append("</div>");       
		       strBuffer.append("</td>");
		       
		       strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\"  class=\"checkbox_1\" name=\"excel"+rsGetDocuments.getString(1)+"\" id=\"excel"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
		        pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_EXCEL_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, profileId);
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		        while(rsGetSelectedDocuments.next()){
		        	list.add(rsGetSelectedDocuments.getInt(1));
		        }
		        if(list.contains(rsGetDocuments.getInt(1))){
		          strBuffer.append("checked=\"checked\"");
		        }
		        list.clear();
		        strBuffer.append("/><label class=\"checkbox_1\"  for=\"excel"+rsGetDocuments.getString(1)+"\"></label>");
		        strBuffer.append("</div>");       
		       strBuffer.append("</td>");
		       
		       strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"print"+rsGetDocuments.getString(1)+"\" id=\"print"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
		        pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_PRINT_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, profileId);
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		        while(rsGetSelectedDocuments.next()){
		        	list.add(rsGetSelectedDocuments.getInt(1));
		        }
		        if(list.contains(rsGetDocuments.getInt(1))){
		          strBuffer.append("checked=\"checked\"");
		        }
		        list.clear();
		        strBuffer.append("/><label class=\"checkbox_1\"  for=\"print"+rsGetDocuments.getString(1)+"\"></label>");
		        strBuffer.append("</div>"); 
		       strBuffer.append("</td>");
		       
		       strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"approval"+rsGetDocuments.getString(1)+"\" id=\"approval"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
		        pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_APPR_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, profileId);
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		        while(rsGetSelectedDocuments.next()){
		        	list.add(rsGetSelectedDocuments.getInt(1));
		        }
		        if(list.contains(rsGetDocuments.getInt(1))){
		          strBuffer.append("checked=\"checked\"");
		        }
		        list.clear();
		        strBuffer.append("/><label class=\"checkbox_1\"  for=\"approval"+rsGetDocuments.getString(1)+"\"></label>");
		        strBuffer.append("</div>");
		       strBuffer.append("</td>");
		       
		        strBuffer.append("</tr>"); 
		        i++;
		      }
		      strBuffer.append("</table>"); 
//		      strBuffer.append("</div>");
		    }finally{
		      
		      ArrayOfPreparedStatement = new PreparedStatement[2];
		      ArrayOfPreparedStatement[0] = pstmtGetDocuments;
		      ArrayOfPreparedStatement[1] = pstmtGetSelectedDocuments;
		      ArrayOfResultSet = new ResultSet[2];      
		      ArrayOfResultSet[0] = rsGetDocuments;
		      ArrayOfResultSet[1] = rsGetSelectedDocuments;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return strBuffer.toString();
		  }
	 public String getUserProfileDocumentList(ResourceBundle bundle,Connection con,int profileid, int userId,List<Integer> list)throws SQLException,Exception{
		  PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;    
		    ResultSet rsGetDocuments = null;   
		    PreparedStatement pstmtGetDocuments = null;
		    PreparedStatement pstmtGetSelectedDocuments = null;
		    ResultSet rsGetSelectedDocuments = null;
		    StringBuffer strBuffer = new StringBuffer();
		    try{      
		      
		    	pstmtGetDocuments = con.prepareStatement(SQLUtil.GET_PROFILE_DOCUMENTS);
		    	pstmtGetDocuments.setInt(1, profileid);
		    	rsGetDocuments = pstmtGetDocuments.executeQuery();
//		      strBuffer.append("<div id=\"sub_header\" style=\"position:absolute; border-style:none;margin-bottom:0;margin-top:0;height:220px; VISIBILITY:visible; OVERFLOW:auto;width:"+iBomWidth+"px;\">");
		      strBuffer.append("<table class=\"table table-bordered table-condensed\">");        
		      strBuffer.append("<tr class=\"header\">");
		      strBuffer.append("<th class=\"subpageheader\" nowrap=\"nowrap\">"+bundle.getString("Profile.Page")+"</th>");
		      strBuffer.append("<th class=\"subpageheader\" nowrap=\"nowrap\">"+bundle.getString("Profile.Entry")+" &nbsp;<div class=\"checkbox\"> <input type=\"checkbox\" class=\"checkbox_1\" name=\"headerentry\" id=\"headerentry\" onclick=\"checkuncheckprofileentryrights()\" /><label class=\"checkbox_1\"  for=\"headerentry\"></label></div></th>");
		      strBuffer.append("<th class=\"subpageheader\" nowrap=\"nowrap\">"+bundle.getString("Common.Edit")+"&nbsp;<div class=\"checkbox\"> <input type=\"checkbox\" class=\"checkbox_1\" name=\"headeredit\" id=\"headeredit\" onclick=\"checkuncheckprofileeditrights()\" /><label class=\"checkbox_1\"  for=\"headeredit\"></label></div></th>");
		      strBuffer.append("<th class=\"subpageheader\" nowrap=\"nowrap\">"+bundle.getString("Common.Delete")+"&nbsp;<div class=\"checkbox\"> <input type=\"checkbox\" class=\"checkbox_1\" name=\"headerdelete\" id=\"headerdelete\" onclick=\"checkuncheckprofiledeleterights()\" /><label class=\"checkbox_1\"  for=\"headerdelete\"></label></div></th>");
		      strBuffer.append("<th class=\"subpageheader\" nowrap=\"nowrap\">"+bundle.getString("Common.View")+"&nbsp;<div class=\"checkbox\"> <input type=\"checkbox\" class=\"checkbox_1\" name=\"headerview\" id=\"headerview\" onclick=\"checkuncheckprofileviewrights()\" /><label class=\"checkbox_1\"  for=\"headerview\"></label></div></th>");
		      strBuffer.append("<th class=\"subpageheader\" nowrap=\"nowrap\">"+bundle.getString("Profile.Excel")+"&nbsp;<div class=\"checkbox\"> <input type=\"checkbox\" class=\"checkbox_1\" name=\"headerexcel\" id=\"headerexcel\" onclick=\"checkuncheckprofileexcelrights()\" /><label class=\"checkbox_1\"  for=\"headerexcel\"></label></div></th>");
		      strBuffer.append("<th class=\"subpageheader\" nowrap=\"nowrap\">"+bundle.getString("Profile.Print")+"&nbsp;<div class=\"checkbox\"> <input type=\"checkbox\" class=\"checkbox_1\" name=\"headerprint\" id=\"headerprint\" onclick=\"checkuncheckprofileprintrights()\" /><label class=\"checkbox_1\"  for=\"headerprint\"></label></div></th>");
		      strBuffer.append("<th class=\"subpageheader\" nowrap=\"nowrap\">"+bundle.getString("Profile.Approval")+"&nbsp;<div class=\"checkbox\"> <input type=\"checkbox\" class=\"checkbox_1\" name=\"headerapproval\" id=\"headerapproval\" onclick=\"checkuncheckprofileapprovalrights()\" /><label class=\"checkbox_1\"  for=\"headerapproval\"></label></div></th>");
		      strBuffer.append("</tr>");
//		      strBuffer.append("</div>");
//		      strBuffer.append("<div id=\"sub_tblbody\" style=\"position:absolute; border-style:none;margin-bottom:0;margin-top:0; VISIBILITY:visible; OVERFLOW:auto; HEIGHT:1;width:"+iBomWidth+"px;\">");
		      int i=1;
		      while(rsGetDocuments.next()){
		        if(i%2==0){
		          strBuffer.append("<tr class=\"eventabledata\" valign=\"top\">");
		        }
		        else{
		          strBuffer.append("<tr class=\"oddtabledata\" valign=\"top\">");
		        }
		        strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        
		        if(bundle.containsKey(rsGetDocuments.getString(2))){
		        	strBuffer.append(bundle.getString(rsGetDocuments.getString(2)));	
		        }
		        else{
		        	strBuffer.append(rsGetDocuments.getString(2));
		        }
		        
//		        strBuffer.append(bundle.getString(rsGetDocuments.getString(2)));
		        strBuffer.append("</td>");
		        strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\"  name=\"entry"+rsGetDocuments.getString(1)+"\" id=\"entry"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
		        
		        pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_USER_ENTRY_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, userId);
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		        while(rsGetSelectedDocuments.next()){
		        	list.add(rsGetSelectedDocuments.getInt(1));
		        }
		    	
		        if(list.contains(rsGetDocuments.getInt(1))){
		          strBuffer.append("checked=\"checked\"");
		        }
		        
		        list.clear();
		        strBuffer.append("/><label class=\"checkbox_1\"  for=\"entry"+rsGetDocuments.getString(1)+"\"></label>");
		        strBuffer.append("</div>");      
		        strBuffer.append("</td>");
		        
		        strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"edit"+rsGetDocuments.getString(1)+"\" id=\"edit"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
		        pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_USER_EDIT_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, userId);
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		        while(rsGetSelectedDocuments.next()){
		        	list.add(rsGetSelectedDocuments.getInt(1));
		        }
		        if(list.contains(rsGetDocuments.getInt(1))){
		          strBuffer.append("checked=\"checked\"");
		        }
		        list.clear();
		        strBuffer.append("/><label class=\"checkbox_1\"  for=\"edit"+rsGetDocuments.getString(1)+"\"></label>");
		        strBuffer.append("</div>");      
		        strBuffer.append("</td>");
		        
		        strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\"  name=\"delete"+rsGetDocuments.getString(1)+"\" id=\"delete"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
		        pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_USER_DELETE_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, userId);
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		        while(rsGetSelectedDocuments.next()){
		        	list.add(rsGetSelectedDocuments.getInt(1));
		        }
		        if(list.contains(rsGetDocuments.getInt(1))){
		          strBuffer.append("checked=\"checked\"");
		        }
		        list.clear();
		        strBuffer.append("/><label class=\"checkbox_1\"  for=\"delete"+rsGetDocuments.getString(1)+"\"></label>");
		        strBuffer.append("</div>");      
		       strBuffer.append("</td>");
		        
		       strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"view"+rsGetDocuments.getString(1)+"\" id=\"view"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
		        pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_USER_VIEW_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, userId);
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		        while(rsGetSelectedDocuments.next()){
		        	list.add(rsGetSelectedDocuments.getInt(1));
		        }
		        if(list.contains(rsGetDocuments.getInt(1))){
		          strBuffer.append("checked=\"checked\"");
		        }
		        list.clear();
		        strBuffer.append("/><label class=\"checkbox_1\"  for=\"view"+rsGetDocuments.getString(1)+"\"></label>");
		        strBuffer.append("</div>");  
		       strBuffer.append("</td>");
		       
		       strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\"  name=\"excel"+rsGetDocuments.getString(1)+"\" id=\"excel"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
		        pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_USER_EXCEL_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, userId);
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		        while(rsGetSelectedDocuments.next()){
		        	list.add(rsGetSelectedDocuments.getInt(1));
		        }
		        if(list.contains(rsGetDocuments.getInt(1))){
		          strBuffer.append("checked=\"checked\"");
		        }
		        list.clear();
		        strBuffer.append("/><label class=\"checkbox_1\"  for=\"excel"+rsGetDocuments.getString(1)+"\"></label>");
		        strBuffer.append("</div>"); 
		       strBuffer.append("</td>");
		       
		       strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\"  name=\"print"+rsGetDocuments.getString(1)+"\" id=\"print"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
		        pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_USER_PRINT_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, userId);
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		        while(rsGetSelectedDocuments.next()){
		        	list.add(rsGetSelectedDocuments.getInt(1));
		        }
		        if(list.contains(rsGetDocuments.getInt(1))){
		          strBuffer.append("checked=\"checked\"");
		        }
		        list.clear();
		        strBuffer.append("/><label class=\"checkbox_1\"  for=\"print"+rsGetDocuments.getString(1)+"\"></label>");
		        strBuffer.append("</div>"); 
		       strBuffer.append("</td>");
		       
		       strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\"  name=\"approval"+rsGetDocuments.getString(1)+"\" id=\"approval"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
		        pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_USER_APPR_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, userId);
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		        while(rsGetSelectedDocuments.next()){
		        	list.add(rsGetSelectedDocuments.getInt(1));
		        }
		        if(list.contains(rsGetDocuments.getInt(1))){
		          strBuffer.append("checked=\"checked\"");
		        }
		        list.clear();
		        strBuffer.append("/><label class=\"checkbox_1\"  for=\"approval"+rsGetDocuments.getString(1)+"\"></label>");
		        strBuffer.append("</div>");
		       strBuffer.append("</td>");
		       
		        strBuffer.append("</tr>"); 
		        i++;
		      }
		      strBuffer.append("</table>"); 
//		      strBuffer.append("</div>");
		    }finally{
		      
		      ArrayOfPreparedStatement = new PreparedStatement[2];
		      ArrayOfPreparedStatement[0] = pstmtGetDocuments;
		      ArrayOfPreparedStatement[1] = pstmtGetSelectedDocuments;
		      ArrayOfResultSet = new ResultSet[2];      
		      ArrayOfResultSet[0] = rsGetDocuments;
		      ArrayOfResultSet[1] = rsGetSelectedDocuments;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return strBuffer.toString();
		  }
	 public boolean isSubscriptionExceed(Connection con,int clientId)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtUserCount=null;		   
		    ResultSet rsUserCount = null;   
		    PreparedStatement pstmtSubscriptionExceed=null;		   
		    ResultSet rsSubscriptionExceed = null;   
		  
		    
		    boolean bFlag = false;
		    int userCount=0;
		    int no_of_subscriptions=0;
		    try{
		    	pstmtUserCount=con.prepareStatement(SQLUtil.GET_CLIENT_USER_COUNT);
		    	pstmtUserCount.setInt(1,clientId);
		    					    	
		    	rsUserCount=pstmtUserCount.executeQuery();
		    	while(rsUserCount.next())
		    	{						
		    		userCount=rsUserCount.getInt(1);
					     
		    	}
		    	
		    	pstmtSubscriptionExceed=con.prepareStatement(SQLUtil.GET_CLIENT_USER_SUBSCRIPTION_COUNT);
		    	pstmtSubscriptionExceed.setInt(1,clientId);
		    					    	
		    	rsSubscriptionExceed=pstmtSubscriptionExceed.executeQuery();
		    	while(rsSubscriptionExceed.next())
		    	{
				//bFlag = (rsUserCount.getInt(1)>0)?true:false;
		    		no_of_subscriptions=rsSubscriptionExceed.getInt(1);							     
		    	}
		    	
		    	bFlag = (userCount<no_of_subscriptions)?true:false;
		    
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[2];
		      ArrayOfPreparedStatement[0] = pstmtUserCount;	
		      ArrayOfPreparedStatement[1] = pstmtSubscriptionExceed;	
		      ArrayOfResultSet = new ResultSet[2];
		      ArrayOfResultSet[0] = rsUserCount;
		      ArrayOfResultSet[1] = rsSubscriptionExceed;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return bFlag;
		  }
	 public boolean isUserExist(Connection con,User objUser)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtUserExist=null;		   
		    ResultSet rsIsUserExist = null;   
		  
		    
		    boolean bFlag = false;
		    try{
		    	pstmtUserExist=con.prepareStatement(SQLUtil.IS_USER_EXIST);
		    	pstmtUserExist.setString(1,objUser.getUserName());
		    					    	
		    	rsIsUserExist=pstmtUserExist.executeQuery();
		    	while(rsIsUserExist.next())
		    	{
				bFlag = (rsIsUserExist.getInt(1)>0)?true:false;      
					     
		    	}
		    
		    
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmtUserExist;		     
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0] = rsIsUserExist;		     
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return bFlag;
		  }
	 public User insertClientInfo(Connection con,User objUser,int clientId)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement csInsertUser=null;    
		    User retVal = new User();
		    boolean bFlag = false;   
		    PreparedStatement pstmtGetPasswordDetails=null;    
		    ResultSet rsGetPasswordDetails = null;
		   
		    try{     
		 			     
		    
		    	csInsertUser = con.prepareCall("{? = call pr_insertuser(?,?,?,?,?,?,?)}");
		    	csInsertUser.registerOutParameter(1, Types.INTEGER);				    	
		    	csInsertUser.setInt(2, clientId);
		    	csInsertUser.setString(3, objUser.getUserName());			    
		    	csInsertUser.setString(4, objUser.getFirstName());
		    	csInsertUser.setString(5, objUser.getLastName());
		    	csInsertUser.setInt(6, objUser.getLanguageId());
		    	csInsertUser.setInt(7, objUser.getIsSystemAdmin());
		    	csInsertUser.setInt(8, objUser.getIsActive());
		    	csInsertUser.executeUpdate();
		    	int iCount = csInsertUser.getInt(1);		      
		    	bFlag=(iCount!=0)?true:false;
		    	//objUser.setUserId(iCount);
		    	retVal.setSuccess(bFlag);
		    	
		    	pstmtGetPasswordDetails=con.prepareStatement(SQLUtil.GET_PASSWORD);
		    	pstmtGetPasswordDetails.setString(1, objUser.getUserName());
		    
		    	rsGetPasswordDetails=pstmtGetPasswordDetails.executeQuery();
		    	
		    	while(rsGetPasswordDetails.next()){
		    		retVal.setUserId(rsGetPasswordDetails.getInt(1));
		    		retVal.setUserName(objUser.getUserName());
		    		retVal.setPassword(rsGetPasswordDetails.getString(2));
		    	}
		     
		      
		    }finally{     
		      if(csInsertUser!=null)
		    	  csInsertUser.close();
		      ArrayOfPreparedStatement = new PreparedStatement[1];   
		      ArrayOfPreparedStatement[0]=pstmtGetPasswordDetails;
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0]=rsGetPasswordDetails;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return retVal;
		  }
	 public User insertUser(Connection con,User objUser,int userId)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement csInsertUser=null;    
		    User retVal = new User();
		    boolean bFlag = false;   
		    PreparedStatement pstmtGetSelectedDocuments = null;
		    ResultSet rsGetSelectedDocuments = null;
		    String doc_ids="";
		    Profiles objProfile=new Profiles();
		    try{     
		    	
		    	System.out.println("objUser.getProfiles().getEntityEntryDocuments() :"+objUser.getProfiles().getEntityEntryDocuments());
		    	System.out.println("objUser.getProfiles().getEntityEditDocuments() :"+objUser.getProfiles().getEntityEditDocuments());
		    	System.out.println("objUser.getProfiles().getEntityDeleteDocuments() :"+objUser.getProfiles().getEntityDeleteDocuments());
		    	System.out.println("objUser.getProfiles().getEntityViewDocuments() :"+objUser.getProfiles().getEntityViewDocuments());
		    	System.out.println("objUser.getProfiles().getEntityExcelDocuments() :"+objUser.getProfiles().getEntityExcelDocuments());
		    	System.out.println("objUser.getProfiles().getEntityPrintDocuments() :"+objUser.getProfiles().getEntityPrintDocuments());
		    	System.out.println("objUser.getProfiles().getEntityApprDocuments() :"+objUser.getProfiles().getEntityApprDocuments());
		     if((objUser.getProfiles().getEntityEntryDocuments().equals("0"))&&(objUser.getProfiles().getEntityEditDocuments().equals("0"))&&(objUser.getProfiles().getEntityDeleteDocuments().equals("0"))&&(objUser.getProfiles().getEntityViewDocuments().equals("0"))&&(objUser.getProfiles().getEntityExcelDocuments().equals("0"))&&(objUser.getProfiles().getEntityPrintDocuments().equals("0"))&&(objUser.getProfiles().getEntityApprDocuments().equals("0")))
		     {
		    	 objProfile.setProfileId(objUser.getProfiles().getProfileId());
		    	pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_ENTRY_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getProfiles().getProfileId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityEntryDocuments(doc_ids);
		       
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_EDIT_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getProfiles().getProfileId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityEditDocuments(doc_ids);
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_DELETE_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getProfiles().getProfileId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityDeleteDocuments(doc_ids);
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_VIEW_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getProfiles().getProfileId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityViewDocuments(doc_ids);
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_EXCEL_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getProfiles().getProfileId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityExcelDocuments(doc_ids);
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_PRINT_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getProfiles().getProfileId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityPrintDocuments(doc_ids);
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_APPR_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getProfiles().getProfileId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityApprDocuments(doc_ids);				       
		       
		       objUser.setProfiles(objProfile);
		   
		     }				     
		    
		     
		    	csInsertUser = con.prepareCall("{? = call pr_insertuser(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		    	csInsertUser.registerOutParameter(1, Types.INTEGER);
		    	csInsertUser.setString(2, objUser.getUserName());
		    	csInsertUser.setString(3, objUser.getFirstName());
		    	csInsertUser.setString(4, objUser.getLastName());
		    	csInsertUser.setInt(5, objUser.getEntityId()); 
		    	csInsertUser.setInt(6, objUser.getCustomerId());
		    	 csInsertUser.setInt(7, objUser.getFactoryId());
		    	/*if(objUser.getCustomerId()!=-1)
			     {
		    		 csInsertUser.setInt(6, objUser.getCustomerId());
			     }
			     else
			     {
			    	 csInsertUser.setNull(6, Types.INTEGER);
			     }
		    	 
		    	 if(objUser.getFactoryId()!=-1)
			     {
		    		 csInsertUser.setInt(7, objUser.getFactoryId());
			     }
			     else
			     {
			    	 csInsertUser.setNull(7, Types.INTEGER);
			     }*/
			    				    	
		    	csInsertUser.setInt(8, objUser.getRoles().getRoleId());
		    	csInsertUser.setInt(9, objUser.getProfiles().getProfileId());
		    	csInsertUser.setString(10, objUser.getProfiles().getEntityEntryDocuments());
		    	csInsertUser.setString(11, objUser.getProfiles().getEntityEditDocuments());
		    	csInsertUser.setString(12, objUser.getProfiles().getEntityDeleteDocuments());
		    	csInsertUser.setString(13, objUser.getProfiles().getEntityViewDocuments());
		    	csInsertUser.setString(14, objUser.getProfiles().getEntityExcelDocuments());
		    	csInsertUser.setString(15, objUser.getProfiles().getEntityPrintDocuments());
		    	csInsertUser.setString(16, objUser.getProfiles().getEntityApprDocuments());
		    	csInsertUser.setInt(17,userId);	      
		    	csInsertUser.setInt(18,objUser.getLanguageId());
		    	csInsertUser.executeUpdate();
		    	int iCount = csInsertUser.getInt(1);		      
		    	bFlag=(iCount!=0)?true:false;
		    	objUser.setUserId(iCount);
		    	objUser.setUserName(objUser.getUserName());
		    	objUser.setFirstName(objUser.getFirstName());
		    	objUser.setLastName(objUser.getLastName());
		    	retVal.setSuccess(bFlag);
		    	//insert notification_config for mail alerts
		    	if(bFlag){
			    	pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.INSERT_OPTIONS);
			    	pstmtGetSelectedDocuments.setInt(2,userId);
			    	pstmtGetSelectedDocuments.setInt(3, 1);//Is Notification On
			    	for(int i=1;i<=10;i++){
			    		pstmtGetSelectedDocuments.setInt(1, i);//Notification Type Id
			    		pstmtGetSelectedDocuments.addBatch();      
		            }
		         
		            int[] it = pstmtGetSelectedDocuments.executeBatch();
		    	}
		    	
		    }finally{     
		      if(csInsertUser!=null)
		    	  csInsertUser.close();
		      ArrayOfPreparedStatement = new PreparedStatement[1];   
		      ArrayOfPreparedStatement[0]=pstmtGetSelectedDocuments;
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0]=rsGetSelectedDocuments;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return retVal;
		  }
	 public boolean deleteUser(Connection con,int iUserId)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement csDeleteUser=null;    
		    boolean bFlag = false;    
		    try{ 
		      
		    	csDeleteUser = con.prepareCall("{? = call [pr_deleteuser](?)}");
		    	csDeleteUser.registerOutParameter(1, Types.INTEGER );
		    	csDeleteUser.setInt(2,iUserId);     
		    	csDeleteUser.executeUpdate();      
		      int iCount = csDeleteUser.getInt(1);      
		      bFlag = (iCount>1)?true:false;    
		  
		    }finally{     
		      if(csDeleteUser!=null)
		    	  csDeleteUser.close();
		      ArrayOfPreparedStatement = new PreparedStatement[0];      
		      ArrayOfResultSet = new ResultSet[0];
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return bFlag;
		  }
	 public boolean deleteInUsersInfo(Connection con,int iUserId,TPCSUser ui)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement csDeleteUser=null;    
		    boolean bFlag = false;    
		    try{ 
		    	System.out.println("iUserId :"+iUserId);
		    	System.out.println("ui.getclientId() :"+ui.getclientId());
		    	csDeleteUser = con.prepareCall(" delete from users_info where user_id=? and client_id=?");
		    	csDeleteUser.setInt(1,iUserId);
		    	csDeleteUser.setInt(2,ui.getclientId());
		    	int iCount =csDeleteUser.executeUpdate();      
		      bFlag = (iCount>0)?true:false;    
		  
		    }finally{     
		      if(csDeleteUser!=null)
		    	  csDeleteUser.close();
		      ArrayOfPreparedStatement = new PreparedStatement[0];      
		      ArrayOfResultSet = new ResultSet[0];
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return bFlag;
		  }
	 public User getUserInfo(Connection con,User objUser,User objUserInfo)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtGetUserInfo=null;    
		    ResultSet rsGetUserInfo=null;					    
		    Role objRole=new Role();
		    Profiles objProfile=new Profiles();
		    try{
		    	pstmtGetUserInfo = con.prepareStatement(SQLUtil.GET_USER_INFO);
		    	pstmtGetUserInfo.setInt(1, objUser.getUserId());		    	
		    	
		    	rsGetUserInfo = pstmtGetUserInfo.executeQuery();
		    	while(rsGetUserInfo.next()){
		    		objUserInfo.setUserId(rsGetUserInfo.getInt(1));
		    		objUserInfo.setUserName(rsGetUserInfo.getString(2));
		    		objUserInfo.setFirstName(rsGetUserInfo.getString(3));
		    		objUserInfo.setLastName(rsGetUserInfo.getString(4));
		    		objUserInfo.setEntityId(rsGetUserInfo.getInt(5));
		    		objUserInfo.setCustomerId(rsGetUserInfo.getInt(6));
		    		objUserInfo.setFactoryId(rsGetUserInfo.getInt(7));
		    		objRole.setRoleId(rsGetUserInfo.getInt(8));
		    		objUserInfo.setRoles(objRole);
		    		objProfile.setProfileId(rsGetUserInfo.getInt(9));
		    		objUserInfo.setProfiles(objProfile);	
		    		objUserInfo.setLanguageId(rsGetUserInfo.getInt(10));
		    		
		      }
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmtGetUserInfo;					     
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0] = rsGetUserInfo;					      
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return objUserInfo;
		  }
	 public List<Profiles> getEntityProfileInfo(Connection con,User objUser)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtGetProfiles=null;   
		    ResultSet rsGetProfiles = null;   
		    List<Profiles> listProfiles = new ArrayList<Profiles>();
		    try{ 
		      
		    	pstmtGetProfiles = con.prepareStatement(SQLUtil.GET_ENTITY_PROFILES);
		    	pstmtGetProfiles.setInt(1, objUser.getEntityId());					    						    	
		    	rsGetProfiles = pstmtGetProfiles.executeQuery();
		      while(rsGetProfiles.next()){
		        Profiles objProfile = new Profiles();					        
		        objProfile.setProfileId(rsGetProfiles.getInt(1));
		        objProfile.setProfileName(rsGetProfiles.getString(2));
		        listProfiles.add(objProfile);
		      }
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmtGetProfiles;
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0] = rsGetProfiles;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return listProfiles;
		  }
	 public List<Role> getEntityRolesInfo(Connection con,User objUser)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtGetRoles=null;   
		    ResultSet rsGetRoles = null;   
		    List<Role> listRoles = new ArrayList<Role>();
		    try{ 
		    	pstmtGetRoles = con.prepareStatement(SQLUtil.GET_ENTITY_ROLES);
		    	pstmtGetRoles.setInt(1, objUser.getEntityId());  
		    	pstmtGetRoles.setInt(2, objUser.getCustomerId());
		    	pstmtGetRoles.setInt(3, objUser.getFactoryId());					    	
		    	rsGetRoles = pstmtGetRoles.executeQuery();
		      while(rsGetRoles.next()){
		        Role objRole = new Role();					        
		        objRole.setRoleId(rsGetRoles.getInt(1));
		        objRole.setRoleName(rsGetRoles.getString(2));
		        listRoles.add(objRole);
		      }
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmtGetRoles;
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0] = rsGetRoles;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return listRoles;
		  }
	 public boolean checkNoOfUsersToUpdate(Connection con,TPCSUser ui,User objUser)throws SQLException,Exception{
		  PreparedStatement ArrayOfPreparedStatement[] = null;
		  ResultSet ArrayOfResultSet[] = null;
		  
		    PreparedStatement pstmtCountUserInfo= null;
		    ResultSet rsCountUserInfo = null;
		    PreparedStatement pstmtCountClientInfo= null;
		    ResultSet rsCountClientInfo = null;
		    boolean bFlag = false; 
		    int iCount1=0;
		    int iCount2=0;
		    try{ 
		      
		    	
				 pstmtCountUserInfo = con.prepareStatement(SQLUtil.COUNT_NO_OF_USERS_ACTIVE);
		    	 pstmtCountUserInfo.setInt(1, ui.getclientId());
		    	 pstmtCountUserInfo.setInt(2, objUser.getUserId());
		    	 rsCountUserInfo=pstmtCountUserInfo.executeQuery();
		    	while(rsCountUserInfo.next()){
		    		 iCount1 = rsCountUserInfo.getInt(1);     
		      }	

		    	
		    	pstmtCountClientInfo = con.prepareStatement(SQLUtil.GET_NO_OF_USERS);
		    	pstmtCountClientInfo.setInt(1, ui.getclientId());
		    	rsCountClientInfo=pstmtCountClientInfo.executeQuery();
		    	while(rsCountClientInfo.next()){
		    		 iCount2 = rsCountClientInfo.getInt(1);     
		      }
		    
		    
		    	bFlag = (iCount2>iCount1)?true:false;   
		    	
		  
		    }finally{     
		    	ArrayOfPreparedStatement = new PreparedStatement[2];
		        ArrayOfPreparedStatement[0] =pstmtCountUserInfo;
		        ArrayOfPreparedStatement[1] =pstmtCountClientInfo;
		        ArrayOfResultSet = new ResultSet[2];
		        ArrayOfResultSet[0] = rsCountUserInfo;
		        ArrayOfResultSet[1] =rsCountClientInfo;
		       
		        DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return bFlag;
		  }
	 public User updateUser(Connection con,User objUser)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement csInsertUser=null;    
		    User retVal = new User();
		    boolean bFlag = false;   
		    PreparedStatement pstmtGetSelectedDocuments = null;
		    ResultSet rsGetSelectedDocuments = null;
		    String doc_ids="";
		    Profiles objProfile=new Profiles();
		    try{  
		    	
		     if((objUser.getProfiles().getEntityEntryDocuments().equals("0"))&&(objUser.getProfiles().getEntityEditDocuments().equals("0"))&&(objUser.getProfiles().getEntityDeleteDocuments().equals("0"))&&(objUser.getProfiles().getEntityViewDocuments().equals("0"))&&(objUser.getProfiles().getEntityExcelDocuments().equals("0"))&&(objUser.getProfiles().getEntityPrintDocuments().equals("0"))&&(objUser.getProfiles().getEntityApprDocuments().equals("0"))&&(objUser.getProfileStatus().equals("1")))
		     {
		    	 objProfile.setProfileId(objUser.getProfiles().getProfileId());
		    	pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_ENTRY_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getProfiles().getProfileId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityEntryDocuments(doc_ids);
		       
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_EDIT_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getProfiles().getProfileId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityEditDocuments(doc_ids);
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_DELETE_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getProfiles().getProfileId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityDeleteDocuments(doc_ids);
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_VIEW_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getProfiles().getProfileId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityViewDocuments(doc_ids);
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_EXCEL_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getProfiles().getProfileId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityExcelDocuments(doc_ids);
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_PRINT_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getProfiles().getProfileId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityPrintDocuments(doc_ids);
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_APPR_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getProfiles().getProfileId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityApprDocuments(doc_ids);				       
		       
		       objUser.setProfiles(objProfile);
		   
		     }else if((objUser.getProfiles().getEntityEntryDocuments().equals("0"))&&(objUser.getProfiles().getEntityEditDocuments().equals("0"))&&(objUser.getProfiles().getEntityDeleteDocuments().equals("0"))&&(objUser.getProfiles().getEntityViewDocuments().equals("0"))&&(objUser.getProfiles().getEntityExcelDocuments().equals("0"))&&(objUser.getProfiles().getEntityPrintDocuments().equals("0"))&&(objUser.getProfiles().getEntityApprDocuments().equals("0"))&&(objUser.getProfileStatus().equals("0")))
		     {
		    	 
		    	 objProfile.setProfileId(objUser.getProfiles().getProfileId());
		    	pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_USER_ENTRY_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getUserId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityEntryDocuments(doc_ids);
		       
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_USER_EDIT_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getUserId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityEditDocuments(doc_ids);
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_USER_DELETE_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1,objUser.getUserId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityDeleteDocuments(doc_ids);
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_USER_VIEW_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getUserId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityViewDocuments(doc_ids);
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_USER_EXCEL_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getUserId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityExcelDocuments(doc_ids);
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_USER_PRINT_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getUserId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityPrintDocuments(doc_ids);
		       doc_ids="";
		       pstmtGetSelectedDocuments = con.prepareStatement(SQLUtil.GET_USER_APPR_SELECTED_DOCUMENTS);
		        pstmtGetSelectedDocuments.setInt(1, objUser.getUserId());
		        rsGetSelectedDocuments = pstmtGetSelectedDocuments.executeQuery();
		       while(rsGetSelectedDocuments.next()){
		    	   doc_ids=doc_ids+rsGetSelectedDocuments.getString(1)+",";
		        }
		       if(doc_ids.length()==0)
		       {
		    	   doc_ids="0";
		       }
		       else
		       {
		    	   doc_ids=doc_ids.substring(0, doc_ids.length()-1);
		       }				       
		       objProfile.setEntityApprDocuments(doc_ids);				       
		       
		       objUser.setProfiles(objProfile);
		   
		     }					     
		    
		    	csInsertUser = con.prepareCall("{? = call pr_updateuser(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		    	csInsertUser.registerOutParameter(1, Types.INTEGER);
		    	csInsertUser.setString(2, objUser.getUserName());
		    	csInsertUser.setString(3, objUser.getFirstName());
		    	csInsertUser.setString(4, objUser.getLastName());
		    	csInsertUser.setInt(5, objUser.getEntityId()); 
		    	csInsertUser.setInt(6, objUser.getCustomerId());
		    	 csInsertUser.setInt(7, objUser.getFactoryId());
		    	
		    	/*if(objUser.getCustomerId()!=-1)
			     {
		    		 csInsertUser.setInt(6, objUser.getCustomerId());
			     }
			     else
			     {
			    	 csInsertUser.setNull(6, Types.INTEGER);
			     }
		    	 
		    	 if(objUser.getFactoryId()!=-1)
			     {
		    		 csInsertUser.setInt(7, objUser.getFactoryId());
			     }
			     else
			     {
			    	 csInsertUser.setNull(7, Types.INTEGER);
			     }*/
			    				    	
		    	csInsertUser.setInt(8, objUser.getRoles().getRoleId());
		    	csInsertUser.setInt(9, objUser.getProfiles().getProfileId());
		    	csInsertUser.setString(10, objUser.getProfiles().getEntityEntryDocuments());
		    	csInsertUser.setString(11, objUser.getProfiles().getEntityEditDocuments());
		    	csInsertUser.setString(12, objUser.getProfiles().getEntityDeleteDocuments());
		    	csInsertUser.setString(13, objUser.getProfiles().getEntityViewDocuments());
		    	csInsertUser.setString(14, objUser.getProfiles().getEntityExcelDocuments());
		    	csInsertUser.setString(15, objUser.getProfiles().getEntityPrintDocuments());
		    	csInsertUser.setString(16, objUser.getProfiles().getEntityApprDocuments());
		    	 csInsertUser.setInt(17, objUser.getUserId());	      
		    	 csInsertUser.setInt(18, objUser.getLanguageId());
		    	csInsertUser.executeUpdate();
		    	int iCount = csInsertUser.getInt(1);		      
		    	bFlag=(iCount!=0)?true:false;
		    	
		    	objUser.setUserId(objUser.getUserId());
		    	objUser.setUserName(objUser.getUserName());
		    	objUser.setFirstName(objUser.getFirstName());
		    	objUser.setLastName(objUser.getLastName());
		    	retVal.setSuccess(bFlag);
		     
		      
		    }finally{     
		      if(csInsertUser!=null)
		    	  csInsertUser.close();
		      ArrayOfPreparedStatement = new PreparedStatement[1];   
		      ArrayOfPreparedStatement[0]=pstmtGetSelectedDocuments;
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0]=rsGetSelectedDocuments;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return retVal;
		  }
	 public int getSubscriptionCount(Connection con,int clientId)throws SQLException,Exception{    
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtSubscriptionCount=null;		   
		    ResultSet rsSubscriptionCount = null; 
		    int iSubscripCount = 0;
		    try{ 
		    	pstmtSubscriptionCount = con.prepareStatement(SQLUtil.GET_CLIENT_USER_SUBSCRIPTION_COUNT);
		    	pstmtSubscriptionCount.setInt(1,clientId );
		    					    					    	
		    	rsSubscriptionCount = pstmtSubscriptionCount.executeQuery();
		    	rsSubscriptionCount.next();
		    	iSubscripCount = rsSubscriptionCount.getInt(1);
		      
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmtSubscriptionCount;
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0] =  rsSubscriptionCount;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return iSubscripCount;
		  }
	 public void mailPassword(Connection con,User objUser,int loginId)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement csInsertUser=null;    
		    PreparedStatement pstmtGetServer=null;
		    ResultSet rsGetServer = null; 
		    PreparedStatement pstmtGetUserName=null;
		    ResultSet rsGetUserName = null; 
		   
		   
		    try{ 		 			     


		    	String subject="jenixbooks Account Activated";
		    	String messageBody="User Name :"+objUser.getUserName()+"\n Password :"+objUser.getPassword();
		    	String[] attachments=null;     	
		    	SendMail objSendMail=new SendMail();      
		    	List<String> toIds=new ArrayList<String>(); 
		    	try {

		    		toIds.add(objUser.getUserName());
		    		pstmtGetUserName = con.prepareStatement(SQLUtil.GET_CLIENT_SERVER);
		    		pstmtGetUserName.setInt(1, loginId);
		    		rsGetUserName = pstmtGetUserName.executeQuery();

		    		if(rsGetUserName.next()){
		    			MailInfo mailInfo=new MailInfo();
		    			objSendMail.sendMail(rsGetUserName.getString(1),toIds,rsGetUserName.getString(2),rsGetUserName.getString(3),rsGetUserName.getString(4),subject,messageBody,"",mailInfo);
		    		}

		    	}
		    	catch(Exception e) 
		    	{ 
		    		e.printStackTrace(System.out); 
		    	} 

		         
		      
		    }finally{     
		      if(csInsertUser!=null)
		    	  csInsertUser.close();
		      ArrayOfPreparedStatement = new PreparedStatement[2];   
		      ArrayOfPreparedStatement[0]=pstmtGetServer;
		      ArrayOfPreparedStatement[1]=pstmtGetUserName;
		      ArrayOfResultSet = new ResultSet[2];
		      ArrayOfResultSet[0]=rsGetServer;
		      ArrayOfResultSet[1]=rsGetUserName;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    
		  }
	 public Map<Integer,Integer> getIsActiveList(Connection con,TPCSUser ui)throws SQLException,Exception{
		  PreparedStatement pstmt=null;
		  ResultSet rs = null; 

		 Map<Integer,Integer> activeListMap=new LinkedHashMap<Integer, Integer>();

		  try{
			  System.out.println("ui.getclientId() :"+ui.getclientId());
			  pstmt = con.prepareStatement(SQLUtil.GET_ACTIVE_USER_LIST);
			  pstmt.setInt(1, ui.getclientId());

			  rs=pstmt.executeQuery();
			  while(rs.next()){  
				  activeListMap.put(rs.getInt(1), rs.getInt(2));
				
			  }

		  }
		  finally{

			  DatabaseConnection.closeAll(pstmt,rs);
		  }
		  return activeListMap;
	  }
	 public int  getIsActive(Connection con,User objUser)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtGetUserInfo=null;    
		    ResultSet rsGetUserInfo=null;					    
		   int IsActive=0;
		
		    try{
		    	pstmtGetUserInfo = con.prepareStatement(SQLUtil.GET_IS_ACTIVE);
		    	pstmtGetUserInfo.setInt(1, objUser.getUserId());		    	
		    	
		    	rsGetUserInfo = pstmtGetUserInfo.executeQuery();
		    	while(rsGetUserInfo.next()){
		    		IsActive=rsGetUserInfo.getInt(1);
		      }
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmtGetUserInfo;					     
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0] = rsGetUserInfo;					      
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return IsActive;
		  }
	 public boolean isEntityUserExist(Connection con,User objUser,int clientId)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtUserExist=null;		   
		    ResultSet rsIsUserExist = null;   
		  
		    
		    boolean bFlag = false;
		    try{
		    	pstmtUserExist=con.prepareStatement(SQLUtil.IS_ENTITY_USER_EXIST);
		    	pstmtUserExist.setString(1,objUser.getUserName());
		    	pstmtUserExist.setInt(2,objUser.getUserId());
		    	pstmtUserExist.setInt(3,clientId);
		    					    	
		    	rsIsUserExist=pstmtUserExist.executeQuery();
		    	while(rsIsUserExist.next())
		    	{
				bFlag = (rsIsUserExist.getInt(1)>0)?true:false;      
					     
		    	}
		    
		    
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmtUserExist;		     
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0] = rsIsUserExist;		     
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return bFlag;
		  }
	 public User updateClientInfo(Connection con,User objUser,int clientId)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement csInsertUser=null;    
		    User retVal = new User();
		    boolean bFlag = false;   
		    PreparedStatement pstmtGetSelectedDocuments = null;
		    ResultSet rsGetSelectedDocuments = null;
		    String doc_ids="";
		    Profiles objProfile=new Profiles();
		    try{  
		    	
		         
		    
		    	csInsertUser = con.prepareCall("{? = call pr_updateuser(?,?,?,?,?,?,?,?)}");
		    	csInsertUser.registerOutParameter(1, Types.INTEGER);
		    	csInsertUser.setInt(2, objUser.getUserId());
		    	csInsertUser.setInt(3, clientId);
		    	csInsertUser.setString(4, objUser.getUserName());					    	
		    	csInsertUser.setString(5, objUser.getFirstName());
		    	csInsertUser.setString(6, objUser.getLastName());
		    	csInsertUser.setInt(7, objUser.getLanguageId());	
		    	csInsertUser.setInt(8, objUser.getIsSystemAdmin());
		    	csInsertUser.setInt(9, objUser.getIsActive());
		    	
		    	csInsertUser.executeUpdate();
		    	int iCount = csInsertUser.getInt(1);		      
		    	bFlag=(iCount!=0)?true:false;
		    	
		    	objUser.setUserId(objUser.getUserId());
		    	objUser.setUserName(objUser.getUserName());
		    	objUser.setFirstName(objUser.getFirstName());
		    	objUser.setLastName(objUser.getLastName());
	    		objUser.setIsSystemAdmin(objUser.getIsSystemAdmin());
	    		objUser.setIsActive(objUser.getIsActive());
		    	retVal.setSuccess(bFlag);
		     
		      
		    }finally{     
		      if(csInsertUser!=null)
		    	  csInsertUser.close();
		      ArrayOfPreparedStatement = new PreparedStatement[1];   
		      ArrayOfPreparedStatement[0]=pstmtGetSelectedDocuments;
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0]=rsGetSelectedDocuments;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return retVal;
		  }
	 public void mailresetPassword(Connection con,User objUser)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement csInsertUser=null;    
		    PreparedStatement pstmtGetServer=null;
		    ResultSet rsGetServer = null; 
		    PreparedStatement pstmtGetUserName=null;
		    ResultSet rsGetUserName = null; 
		   
		   
		    try{ 		 			     
		    	
		    	String subject="Jenixerp Password Reset";
		         String messageBody="User Name :"+objUser.getUserName()+"\n Password :"+objUser.getPassword();
		         String[] attachments=null;     	
		         SendMail objSendMail=new SendMail();      
		         List<String> toIds=new ArrayList<String>(); 
		      	   try
		      	   {
		      		 toIds.add(objUser.getUserName());
		      		 pstmtGetUserName = con.prepareStatement(SQLUtil.GET_CLIENT_SERVER);
		      		pstmtGetUserName.setInt(1, objUser.getUserId());   
		      		rsGetUserName = pstmtGetUserName.executeQuery();
			       
			       if(rsGetUserName.next()){
			    	   MailInfo mailInfo=new MailInfo();
			    	   objSendMail.sendMail(rsGetUserName.getString(1),toIds,rsGetUserName.getString(2),rsGetUserName.getString(3),rsGetUserName.getString(4),subject,messageBody,"",mailInfo);
			       }
		       	 
		      	   }
		      	   catch(Exception e) 
		             { 
		                 e.printStackTrace(System.out); 
		             } 
		           
		         
		      
		    }finally{     
		      if(csInsertUser!=null)
		    	  csInsertUser.close();
		      ArrayOfPreparedStatement = new PreparedStatement[2];   
		      ArrayOfPreparedStatement[0]=pstmtGetServer;
		      ArrayOfPreparedStatement[1]=pstmtGetUserName;
		      ArrayOfResultSet = new ResultSet[2];
		      ArrayOfResultSet[0]=rsGetServer;
		      ArrayOfResultSet[1]=rsGetUserName;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    
		  }
	 public User changePassword(Connection con,int clientId,int iUserId,String oldPassword,String newPassword,String securityQuestion,String securityAnswer,int days)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement cstmInsertChangePassword = null;
		    PreparedStatement pstmtGetPasswordDetails=null;    
		    ResultSet rsGetPasswordDetails = null;
		    boolean bFlag = false;  
		    User retVal = new User();
		    try{ 
		    	cstmInsertChangePassword = con.prepareCall("{? = call pr_passwordchange(?,?,?,?,?,?,?)}");
		    	cstmInsertChangePassword.registerOutParameter(1,Types.INTEGER);
		    	cstmInsertChangePassword.setInt(2,clientId); 
		    	cstmInsertChangePassword.setInt(3,iUserId);      
		    	cstmInsertChangePassword.setString(4,securityQuestion);
		    	cstmInsertChangePassword.setString(5,securityAnswer);
		    	cstmInsertChangePassword.setInt(6,days); 
		    	cstmInsertChangePassword.setString(7,newPassword);
		    	cstmInsertChangePassword.setString(8,oldPassword);
		    	cstmInsertChangePassword.executeUpdate();
		    	
		    	
		    	int iCount = cstmInsertChangePassword.getInt(1);   
			    bFlag=(iCount>0)?true:false;
			    retVal.setSuccess(bFlag);
				
		    	pstmtGetPasswordDetails=con.prepareStatement(SQLUtil.GET_CLIENT_USER_INFO);
		    	pstmtGetPasswordDetails.setInt(1,iUserId);
		    	pstmtGetPasswordDetails.setInt(2,clientId);
		    
		    	rsGetPasswordDetails=pstmtGetPasswordDetails.executeQuery();
		    	
		    	while(rsGetPasswordDetails.next()){	
		    		retVal.setUserId(iUserId);
		    		retVal.setUserName(rsGetPasswordDetails.getString(1));
		    		retVal.setPassword(rsGetPasswordDetails.getString(2));
		    	}
		    	
		     
		    }finally{     
		  
		    }
		    return retVal;
		  }
	 public void mailchangePassword(Connection con,User objUser)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement csInsertUser=null;    
		    PreparedStatement pstmtGetServer=null;
		    ResultSet rsGetServer = null; 
		    PreparedStatement pstmtGetUserName=null;
		    ResultSet rsGetUserName = null; 
		    try{ 		 			     
		    	
		    	String subject="Jenixerp Password Changed";
		         String messageBody="User Name :"+objUser.getUserName()+"\n Password :"+objUser.getPassword();
		         String[] attachments=null;     	
		         SendMail objSendMail=new SendMail();      
		         List<String> toIds=new ArrayList<String>(); 
		      	   try
		      	   {
		      		 toIds.add(objUser.getUserName());
		      		 pstmtGetUserName = con.prepareStatement(SQLUtil.GET_CLIENT_SERVER);
		      		pstmtGetUserName.setInt(1, objUser.getUserId());   
		      		rsGetUserName = pstmtGetUserName.executeQuery();
			       
			       if(rsGetUserName.next()){
			    	   MailInfo mailInfo=new MailInfo();
			    	   objSendMail.sendMail(rsGetUserName.getString(1),toIds,rsGetUserName.getString(2),rsGetUserName.getString(3),rsGetUserName.getString(4),subject,messageBody,"",mailInfo);
			       }
		       	 
		      	   }
		      	   catch(Exception e) 
		             { 
		                 e.printStackTrace(System.out); 
		             } 
		           
		         
		      
		    }finally{     
		      if(csInsertUser!=null)
		    	  csInsertUser.close();
		      ArrayOfPreparedStatement = new PreparedStatement[2];   
		      ArrayOfPreparedStatement[0]=pstmtGetServer;
		      ArrayOfPreparedStatement[1]=pstmtGetUserName;
		      ArrayOfResultSet = new ResultSet[2];
		      ArrayOfResultSet[0]=rsGetServer;
		      ArrayOfResultSet[1]=rsGetUserName;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    
		  }
}
