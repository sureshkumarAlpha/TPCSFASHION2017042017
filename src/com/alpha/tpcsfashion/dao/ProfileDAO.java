package com.alpha.tpcsfashion.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.alpha.tpcsfashion.beans.EntityRights;
import com.alpha.tpcsfashion.beans.Profiles;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;

public class ProfileDAO {
	 public int getProfileCount(Connection con,String strSearchCriteria)throws SQLException,Exception{    
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtProfileCount=null;
		    ResultSet rsProfileCount = null;
		    int iCount = 0;
		    try{ 
		    	StringBuffer query = new StringBuffer(SQLUtil.PROFILE_COUNT);
		    	//pstmtProfileCount = con.prepareStatement(SQLUtil.PROFILE_COUNT);
		    	 if(strSearchCriteria.length()>0){
			       	 // query.append(" where ");    	  
			       	  query.append(strSearchCriteria);} 
		    	 pstmtProfileCount = con.prepareStatement(query.toString());
		    	rsProfileCount = pstmtProfileCount.executeQuery();
		    	rsProfileCount.next();
		      iCount = rsProfileCount.getInt(1);
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmtProfileCount;
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0] =  rsProfileCount;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return iCount;
		  }
	 public List<Profiles> getProfiles(Connection con,PageConfig pc,String strSearchCriteria,ResourceBundle bundle )throws SQLException,Exception{    
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement cstmtGetProfiles=null;
		    ResultSet rsGetProfiles = null;    
		    List<Profiles> profiles=new ArrayList<Profiles>();
		  
		    try{ 
		    	  StringBuffer query = new StringBuffer(SQLUtil.SELECT_PROFILES);
			        if(strSearchCriteria.length()>0){
			       // query.append(" where ");    	  
			        query.append(strSearchCriteria);}    
			        query.append(" ORDER BY p.profile_id DESC,s.document_id,pd.subdocument_id asc ");
		    	cstmtGetProfiles = con.prepareCall("{call sp_Paging(?,?,?)}");
		    	//cstmtGetProfiles.setString(1,SQLUtil.SELECT_PROFILES);
		    	cstmtGetProfiles.setString(1,query.toString());
			       
		    	cstmtGetProfiles.setInt(2,pc.iStart);
		    	cstmtGetProfiles.setInt(3,pc.iEnd);
		    	rsGetProfiles = cstmtGetProfiles.executeQuery();
		      while(rsGetProfiles.next()){
		        EntityRights objEntityRights = new EntityRights();
		        Profiles objProfiles=new Profiles();
		        
		        objProfiles.setProfileId(rsGetProfiles.getInt(2));
		        objProfiles.setProfileName(rsGetProfiles.getString(3));
		        objEntityRights.setEntityId(rsGetProfiles.getInt(4));
		        objEntityRights.setEntityName(rsGetProfiles.getString(5));
		        objEntityRights.setDocumentName(bundle.containsKey(rsGetProfiles.getString(6))?bundle.getString(rsGetProfiles.getString(6)):rsGetProfiles.getString(6));
		        objProfiles.setEntity(objEntityRights);
		       
		        objProfiles.setEntryStatus((rsGetProfiles.getInt(7)!=1)?"Denied":"Allowed");
		        objProfiles.setEditStatus((rsGetProfiles.getInt(8)!=1)?"Denied":"Allowed");
		        objProfiles.setDeleteStatus((rsGetProfiles.getInt(9)!=1)?"Denied":"Allowed");
		        objProfiles.setViewStatus((rsGetProfiles.getInt(10)!=1)?"Denied":"Allowed");
		        objProfiles.setExcelStatus((rsGetProfiles.getInt(11)!=1)?"Denied":"Allowed");
		        objProfiles.setPrintStatus((rsGetProfiles.getInt(12)!=1)?"Denied":"Allowed");
		        objProfiles.setApprStatus((rsGetProfiles.getInt(13)!=1)?"Denied":"Allowed");
		        
		        
		        profiles.add(objProfiles);
		      }
		    }finally{
		      if(cstmtGetProfiles!=null)
		    	  cstmtGetProfiles.close();
		    
		      ArrayOfPreparedStatement = new PreparedStatement[0];
		      ArrayOfResultSet = new ResultSet[1];      
		      ArrayOfResultSet[0] = rsGetProfiles;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return profiles;
		  }
	 public String getEntitySelectedDocuments(ResourceBundle bundle,Connection con, int entityId,int profileId,List<Integer> list)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;    
		    ResultSet rsGetDocuments = null;   
		    PreparedStatement pstmtGetDocuments = null;
		    PreparedStatement pstmtGetSelectedDocuments = null;
		    ResultSet rsGetSelectedDocuments=null;
		    StringBuffer strBuffer = new StringBuffer();
		    try{      
		      
		    	pstmtGetDocuments = con.prepareStatement(SQLUtil.GET_ENTITY_SELECTED_DOCUMENTS);
		    	pstmtGetDocuments.setInt(1, entityId);
		    	rsGetDocuments = pstmtGetDocuments.executeQuery();
//		      strBuffer.append("<div id=\"sub_header\" style=\"position:absolute; border-style:none;margin-bottom:0;margin-top:0;height:220px; VISIBILITY:visible; OVERFLOW:auto;width:800px;\">");
		      strBuffer.append("<table class=\"table table-bordered table-condensed\">");        
		      strBuffer.append("<tr class=\"header\">");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" class=\"w-mini first\">");
		      strBuffer.append(bundle.getString("Profile.Page"));
		      strBuffer.append("</th>");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" class=\"w-mini\">" );
		      strBuffer.append(bundle.getString("Profile.Entry"));
		    
		      strBuffer.append("&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headerentry\" id=\"headerentry\" onclick=\"checkuncheckprofileentryrights()\" /><label class=\"checkbox_1\"  for=\"headerentry\"></label></div></th>");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" class=\"w-mini\">" );
		      strBuffer.append(bundle.getString("Common.Edit"));
		      strBuffer.append("&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headeredit\" id=\"headeredit\" onclick=\"checkuncheckprofileeditrights()\" /><label class=\"checkbox_1\"  for=\"headeredit\"></label></div></th>");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" class=\"w-mini\">" );
		      strBuffer.append(bundle.getString("Common.Delete"));
		      strBuffer.append("&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headerdelete\" id=\"headerdelete\" onclick=\"checkuncheckprofiledeleterights()\" /><label class=\"checkbox_1\"  for=\"headerdelete\"></label></div></th>");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" class=\"w-mini\">" );
		      strBuffer.append(bundle.getString("Common.View"));
		      strBuffer.append("&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headerview\" id=\"headerview\" onclick=\"checkuncheckprofileviewrights()\" /><label class=\"checkbox_1\"  for=\"headerview\"></label></div></th>");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" class=\"w-mini\">" );
		      strBuffer.append(bundle.getString("Profile.Excel"));
		      strBuffer.append("&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headerexcel\" id=\"headerexcel\" onclick=\"checkuncheckprofileexcelrights()\" /><label class=\"checkbox_1\"  for=\"headerexcel\"></label></div></th>");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" class=\"w-mini\">" );
		      strBuffer.append(bundle.getString("Profile.Print"));	
		      strBuffer.append("&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headerprint\" id=\"headerprint\" onclick=\"checkuncheckprofileprintrights()\" /><label class=\"checkbox_1\"  for=\"headerprint\"></label></div></th>");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" class=\"w-mini\">" );
		      strBuffer.append(bundle.getString("Profile.Approval"));
		      strBuffer.append("&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headerapproval\" id=\"headerapproval\" onclick=\"checkuncheckprofileapprovalrights()\" /><label class=\"checkbox_1\"  for=\"headerapproval\"></label></div></th>");
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
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"edit"+rsGetDocuments.getString(1)+"\" id=\"edit"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
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
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"delete"+rsGetDocuments.getString(1)+"\" id=\"delete"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
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
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"excel"+rsGetDocuments.getString(1)+"\" id=\"excel"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
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
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\"  name=\"print"+rsGetDocuments.getString(1)+"\" id=\"print"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
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
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\"  name=\"approval"+rsGetDocuments.getString(1)+"\" id=\"approval"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
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
		      ArrayOfPreparedStatement[1]=pstmtGetSelectedDocuments;
		      ArrayOfResultSet = new ResultSet[2];      
		      ArrayOfResultSet[0] = rsGetDocuments;
		      ArrayOfResultSet[1] = rsGetSelectedDocuments;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return strBuffer.toString();
		  } 
	 public boolean isProfileExist(Connection con,Profiles objProfile)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtProfileExist=null;		   
		    ResultSet rsIsProfileExist = null;   
		  
		    
		    boolean bFlag = false;
		    try{
		    	pstmtProfileExist=con.prepareStatement(SQLUtil.IS_PROFILE_EXIST);
		    	pstmtProfileExist.setString(1,objProfile.getProfileName());
		    	pstmtProfileExist.setInt(2,objProfile.getEntity().getEntityId());	
		    	
		    	rsIsProfileExist=pstmtProfileExist.executeQuery();
		    	while(rsIsProfileExist.next())
		    	{
				bFlag = (rsIsProfileExist.getInt(1)>0)?true:false;      
					     
		    	}
		    
		    
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmtProfileExist;		     
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0] = rsIsProfileExist;		     
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return bFlag;
		  }
	 public Profiles insertProfile(Connection con,Profiles objProfile)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement csInsertProfile=null;    
		    Profiles retVal = new Profiles();
		    boolean bFlag = false;    
		    try{     
		     
		    	csInsertProfile = con.prepareCall("{? = call pr_insertprofile(?,?,?,?,?,?,?,?,?)}");
		    	csInsertProfile.registerOutParameter(1, Types.INTEGER);
		    	csInsertProfile.setString(2, objProfile.getProfileName());   
		    	csInsertProfile.setInt(3, objProfile.getEntity().getEntityId());
		    	csInsertProfile.setString(4, objProfile.getEntityEntryDocuments());
		    	csInsertProfile.setString(5, objProfile.getEntityEditDocuments());
		    	csInsertProfile.setString(6, objProfile.getEntityDeleteDocuments());
		    	csInsertProfile.setString(7, objProfile.getEntityViewDocuments());
		    	csInsertProfile.setString(8, objProfile.getEntityExcelDocuments());
		    	csInsertProfile.setString(9, objProfile.getEntityPrintDocuments());
		    	csInsertProfile.setString(10, objProfile.getEntityApprDocuments());
		      		      
		    	csInsertProfile.executeUpdate();
		    	int iCount = csInsertProfile.getInt(1);		      
		    	bFlag=(iCount!=0)?true:false;
		    	
		    	retVal.setSuccess(bFlag);
		     
		      
		    }finally{     
		      if(csInsertProfile!=null)
		    	  csInsertProfile.close();
		      ArrayOfPreparedStatement = new PreparedStatement[0];      
		      ArrayOfResultSet = new ResultSet[0];
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return retVal;
	 }
	 public boolean deleteProfile(Connection con,int iProfileId)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement csDeleteProfile=null;    
		    boolean bFlag = false;    
		    try{ 
		      
		    	csDeleteProfile = con.prepareCall("{? = call pr_deleteprofile(?)}");
		    	csDeleteProfile.registerOutParameter(1, Types.INTEGER );
		    	csDeleteProfile.setInt(2,iProfileId);     
		    	csDeleteProfile.executeUpdate();      
		      int iCount = csDeleteProfile.getInt(1);      
		      bFlag = (iCount>1)?true:false;    
		  
		    }finally{     
		      if(csDeleteProfile!=null)
		    	  csDeleteProfile.close();
		      ArrayOfPreparedStatement = new PreparedStatement[0];      
		      ArrayOfResultSet = new ResultSet[0];
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return bFlag;
		  }
	 public String getEntitySelectedDocumentsList(ResourceBundle bundle,Connection con,int entityId,int profileId,List<Integer> list)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;    
		    ResultSet rsGetDocuments = null;   
		    PreparedStatement pstmtGetDocuments = null;
		    PreparedStatement pstmtGetSelectedDocuments = null;
		    ResultSet rsGetSelectedDocuments = null;
		    StringBuffer strBuffer = new StringBuffer();
		    try{      
		    	pstmtGetDocuments = con.prepareStatement(SQLUtil.GET_ENTITY_SELECTED_DOCUMENTS);
		    	pstmtGetDocuments.setInt(1, entityId);
		    	rsGetDocuments = pstmtGetDocuments.executeQuery();
//		      strBuffer.append("<div id=\"sub_header\" style=\"position:absolute; border-style:none;margin-bottom:0;margin-top:0;height:220px; VISIBILITY:visible; OVERFLOW:auto;width:800px;\">");
		      strBuffer.append("<table class=\"table table-bordered table-condensed\">");        
		      strBuffer.append("<tr class=\"header\">");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" >"+bundle.getString("Profile.Page")+"</th>");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" >"+bundle.getString("Profile.Entry")+" &nbsp;<div class=\"checkbox\"><input type=\"checkbox\"  class=\"checkbox_1\" name=\"headerentry\" id=\"headerentry\" onclick=\"checkuncheckprofileentryrights()\"   /><label class=\"checkbox_1\"  for=\"headerentry\"></label></div></th>");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" >"+bundle.getString("Common.Edit")+"&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headeredit\" id=\"headeredit\" onclick=\"checkuncheckprofileeditrights()\" /><label class=\"checkbox_1\"  for=\"headeredit\"></label></div></th>");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" >"+bundle.getString("Common.Delete")+"&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\"  name=\"headerdelete\" id=\"headerdelete\" onclick=\"checkuncheckprofiledeleterights()\" /><label class=\"checkbox_1\"  for=\"headerdelete\"></label></div></th>");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" >"+bundle.getString("Common.View")+"&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headerview\" id=\"headerview\" onclick=\"checkuncheckprofileviewrights()\" /><label class=\"checkbox_1\"  for=\"headerview\"></label></div></th>");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" >"+bundle.getString("Profile.Excel")+"&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\"  name=\"headerexcel\" id=\"headerexcel\" onclick=\"checkuncheckprofileexcelrights()\" /><label class=\"checkbox_1\"  for=\"headerexcel\"></label></div></th>");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" >"+bundle.getString("Profile.Print")+"&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headerprint\" id=\"headerprint\" onclick=\"checkuncheckprofileprintrights()\" /><label class=\"checkbox_1\"  for=\"headerprint\"></label></div></th>");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" >"+bundle.getString("Profile.Approval")+"&nbsp;<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"headerapproval\" id=\"headerapproval\" onclick=\"checkuncheckprofileapprovalrights()\" /><label class=\"checkbox_1\"  for=\"headerapproval\"></label></div></th>");
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
		        if(bundle.containsKey(rsGetDocuments.getString(2))){
		        	strBuffer.append(bundle.getString(rsGetDocuments.getString(2)));
		        }
		        else{
		        	strBuffer.append(rsGetDocuments.getString(2));
		        }
		        
		        strBuffer.append("</td>");
		        strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\"  name=\"entry"+rsGetDocuments.getString(1)+"\" id=\"entry"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
		        
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
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"edit"+rsGetDocuments.getString(1)+"\" id=\"edit"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
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
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\"  class=\"checkbox_1\" name=\"delete"+rsGetDocuments.getString(1)+"\" id=\"delete"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
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
		        strBuffer.append("<div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\" name=\"excel"+rsGetDocuments.getString(1)+"\" id=\"excel"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
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
	 public Profiles getProfileInfo(Connection con,int iProfileId)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtProfileInfo=null;    
		    ResultSet rsGetProfileInfo=null;
		    EntityRights objEntity = null;
		    Profiles objProfiles=null;
		    List<Integer> list = new ArrayList<Integer>();
		    try{
		    	pstmtProfileInfo = con.prepareStatement(SQLUtil.SELECT_PROFILE_INFO);
		    	pstmtProfileInfo.setInt(1, iProfileId);
		    	rsGetProfileInfo = pstmtProfileInfo.executeQuery();
		      while(rsGetProfileInfo.next()){
		    	  objEntity = new EntityRights();
		    	  objProfiles = new Profiles();		    	  
		    	  objEntity.setEntityId(rsGetProfileInfo.getInt(3));
		    	  objProfiles.setEntity(objEntity);
		    	  objProfiles.setProfileId(rsGetProfileInfo.getInt(1));
		    	  objProfiles.setProfileName(rsGetProfileInfo.getString(2));
		    	  //objProfiles.setProfileDetailInfo(getEntitySelectedDocumentsList(con,rsGetProfileInfo.getInt(1),rsGetProfileInfo.getInt(3),808,list));
		    	
		      }
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmtProfileInfo;
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0] = rsGetProfileInfo;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return objProfiles;
		  }
	 public boolean isEntityProfileExist(Connection con,Profiles objProfile)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtProfileExist=null;		   
		    ResultSet rsIsProfileExist = null;   
		  
		    
		    boolean bFlag = false;
		    try{
		    	pstmtProfileExist=con.prepareStatement(SQLUtil.IS_PROFILE_ENTITY_EXIST);
		    	pstmtProfileExist.setString(1,objProfile.getProfileName());
		    	pstmtProfileExist.setInt(2,objProfile.getEntity().getEntityId());	
		    	pstmtProfileExist.setInt(3,objProfile.getProfileId());
		    	rsIsProfileExist=pstmtProfileExist.executeQuery();
		    	while(rsIsProfileExist.next())
		    	{
				bFlag = (rsIsProfileExist.getInt(1)>0)?true:false;      
					     
		    	}
		    
		    
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmtProfileExist;		     
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0] = rsIsProfileExist;		     
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return bFlag;
		  }
	 public Profiles updateProfile(Connection con,Profiles objProfile)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement csUpdateProfile=null;    
		    Profiles retVal = new Profiles();
		    boolean bFlag = false;    
		    try{     
		     
		    	csUpdateProfile = con.prepareCall("{? = call pr_updateprofile(?,?,?,?,?,?,?,?,?,?)}");
		    	csUpdateProfile.registerOutParameter(1, Types.INTEGER);
		    	csUpdateProfile.setInt(2, objProfile.getProfileId());  
		    	csUpdateProfile.setString(3, objProfile.getProfileName());   
		    	csUpdateProfile.setInt(4, objProfile.getEntity().getEntityId());
		    	csUpdateProfile.setString(5, objProfile.getEntityEntryDocuments());
		    	csUpdateProfile.setString(6, objProfile.getEntityEditDocuments());
		    	csUpdateProfile.setString(7, objProfile.getEntityDeleteDocuments());
		    	csUpdateProfile.setString(8, objProfile.getEntityViewDocuments());
		    	csUpdateProfile.setString(9, objProfile.getEntityExcelDocuments());
		    	csUpdateProfile.setString(10, objProfile.getEntityPrintDocuments());
		    	csUpdateProfile.setString(11, objProfile.getEntityApprDocuments());
		      		      
		    	csUpdateProfile.executeUpdate();
		    	int iCount = csUpdateProfile.getInt(1);		      
		    	bFlag=(iCount!=0)?true:false;
		    	
		    	retVal.setSuccess(bFlag);
		     
		      
		    }finally{     
		      if(csUpdateProfile!=null)
		    	  csUpdateProfile.close();
		      ArrayOfPreparedStatement = new PreparedStatement[0];      
		      ArrayOfResultSet = new ResultSet[0];
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return retVal;
		  }
	  
}
