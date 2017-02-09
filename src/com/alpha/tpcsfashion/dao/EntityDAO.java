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
import com.alpha.tpcsfashion.beans.Generic;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;

public class EntityDAO {
	 public int getEntityRightsCount(Connection con,String strSearchCriteria)throws SQLException,Exception{    
		    PreparedStatement pst=null;
		    ResultSet rs= null;
		    int iCount = 0;
		    try{ 
		    	  StringBuffer query = new StringBuffer(SQLUtil.ENTITY_RIGHTS_COUNT);
		    	//pstmtEntityRightsCount = con.prepareStatement(SQLUtil.ENTITY_RIGHTS_COUNT); 
		    	 if(strSearchCriteria.length()>0){
		       	 // query.append(" where ");    	  
		       	  query.append(strSearchCriteria);}   
		    	 pst= con.prepareStatement(query.toString());
		    	rs= pst.executeQuery();
		    	if(rs.next()){
		      iCount = rs.getInt(1);
		    	}
		    }finally{     
		      DatabaseConnection.closeAll(pst,rs);
		    }
		    return iCount;
		  }
	 public List<EntityRights> getEntityRights(Connection con,PageConfig pc,String strSearchCriteria,ResourceBundle bundle )throws SQLException,Exception{    
		    CallableStatement cst=null;
		    ResultSet rs = null;    
		    List<EntityRights> entityrights=new ArrayList<EntityRights>();
		    try{      
		        StringBuffer query = new StringBuffer(SQLUtil.SELECT_ENTITY_RIGHTS);
		        if(strSearchCriteria.length()>0){
		       // query.append(" where ");    	  
		        query.append(strSearchCriteria);} 
		        query.append(" order by er.entity_id asc,d.module_id,d.document_id,er.subdocument_id ");
		        cst = con.prepareCall("{call sp_Paging(?,?,?)}");
		        cst.setString(1,query.toString());
		        cst.setInt(2,pc.iStart);
		        cst.setInt(3,pc.iEnd);
		        rs = cst.executeQuery();
		      while(rs.next()){
			        EntityRights objEntityRights = new EntityRights();
			        objEntityRights.setEntityId(rs.getInt(2));
			        objEntityRights.setEntityName(rs.getString(3));		       
			        objEntityRights.setDocumentId(rs.getInt(4));        
			        objEntityRights.setDocumentName(bundle.containsKey(rs.getString(5))?bundle.getString(rs.getString(5)):rs.getString(5));		       
			        entityrights.add(objEntityRights);
			      }
			    }finally{
			      DatabaseConnection.closeAll(cst,rs);
			    }
			    return entityrights;
			  } 
	 public List<Generic> getEntity(Connection con)throws SQLException,Exception{
		    PreparedStatement pst=null;
		    ResultSet rs = null;  
		    List<Generic> listentity=new ArrayList<Generic>();
		    try{      
		    	pst = con.prepareStatement(SQLUtil.GET_ENTITY);        
		    	rs = pst.executeQuery();
		      while(rs.next()){
		        Generic objGeneric = new Generic();
		        objGeneric.setId(rs.getInt(1));
		        objGeneric.setName(rs.getString(2));
		        listentity.add(objGeneric);
		      }
		    }finally{      
		      DatabaseConnection.closeAll(pst,rs);
		    }
		    return listentity;
		  }
	  
	  
	  public List<Generic> getModules(Connection con)throws SQLException,Exception{
		    PreparedStatement pst=null;
		    ResultSet rs = null;  
		    List<Generic> listentity=new ArrayList<Generic>();
		    try{      
		    	pst = con.prepareStatement(SQLUtil.GET_MODULES.concat(" WHERE module_id <> 12"));        
		    	rs = pst.executeQuery();
		      while(rs.next()){
		        Generic objGeneric = new Generic();	
		        objGeneric.setId(rs.getInt(1));
		        objGeneric.setName(rs.getString(2));
		        listentity.add(objGeneric);
		      }
		    }finally{      
		      DatabaseConnection.closeAll(pst,rs);
		    }
		    return listentity;
		  }
	  public List<Generic> getDocuments(Connection con,EntityRights rights)throws SQLException,Exception{
		    PreparedStatement pst=null;
		    ResultSet rs = null;  
		    List<Generic> listentity=new ArrayList<Generic>();
		    try{      
		    	pst = con.prepareStatement(SQLUtil.GET_DOCUMENTS);
		    	pst.setInt(1, rights.getModuleId());
		    	rs = pst.executeQuery();
		      while(rs.next()){
		        Generic objGeneric = new Generic();	
		        objGeneric.setId(rs.getInt(1));
		        objGeneric.setName(rs.getString(2));
		        listentity.add(objGeneric);
		      }
		    }finally{      
		      DatabaseConnection.closeAll(pst,rs);
		    }
		    return listentity;
		  }
	  public boolean isEntityRightExist(Connection con,EntityRights objEntityRight)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pst1=null;
		    PreparedStatement pst2=null;    
		    ResultSet rs1 = null;   
		    ResultSet rs2 = null; 
		    
		    boolean bFlag = false;
		    try{
		    	pst2=con.prepareStatement(SQLUtil.GET_SUB_DOCUMENT_IDS);
		    	pst2.setInt(1,objEntityRight.getDocumentId());
		    	rs2=pst2.executeQuery();
		    	while(rs2.next())
		    	{
		    		pst1 = con.prepareStatement(SQLUtil.IS_ENTITY_RIGHT_EXIST);
			    	pst1.setInt(1, objEntityRight.getEntityId());    
			    	pst1.setInt(2,rs2.getInt(1));
			    	rs1 = pst1.executeQuery();
			    	 while(rs1.next()){
					      bFlag = (rs1.getInt(1)>0)?true:false;      
					      }
		    		if(bFlag==true)
		    			break;
		    	}
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[2];
		      ArrayOfPreparedStatement[0] = pst1;
		      ArrayOfPreparedStatement[1] = pst2;
		      ArrayOfResultSet = new ResultSet[2];
		      ArrayOfResultSet[0] = rs1;
		      ArrayOfResultSet[1] = rs2;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return bFlag;
		  }
	  public String getSubDocuments(Connection con, EntityRights objEntityRight,List<Integer> list,ResourceBundle bundle)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;    
		    ResultSet rs = null;   
		    PreparedStatement pst = null;
		    StringBuffer strBuffer = new StringBuffer();
		    try{      
		    	
		    	pst = con.prepareStatement(SQLUtil.GET_SUB_DOCUMENTS);
//		    	pst.setInt(1, objEntityRight.getModuleId());
		    	pst.setInt(1, objEntityRight.getDocumentId());
		    	rs = pst.executeQuery();
//		      strBuffer.append("<div id=\"sub_header\" style=\"position:absolute; border-style:none;margin-bottom:0;margin-top:0;height:220px; VISIBILITY:visible; OVERFLOW:auto;width:800px;\">");
		      strBuffer.append("<table class=\"table table-bordered table-condensed\">");        
		      strBuffer.append("<tr class=\"header\">");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" class=\"w-mini first\">" +bundle.getString("EntityRights.Rights") +"</th>");	     
		      strBuffer.append("<th align=\"center\" valign=\"middle\" class=\"w-mini last\">" +bundle.getString("EntityRights.AllowDeny") +"</th>");	
		      strBuffer.append("</tr>");
//		      strBuffer.append("</div>");
//		      strBuffer.append("<div id=\"sub_tblbody\" style=\"position:absolute; border-style:none;margin-bottom:0;margin-top:0; VISIBILITY:visible; OVERFLOW:auto; HEIGHT:1;width:"+iBomWidth+"px;\">");
		      int i=1;
		      while(rs.next()){
		        if(i%2==0)
		          strBuffer.append("<tr class=\"eventabledata\" valign=\"top\">");
		        else
		          strBuffer.append("<tr class=\"oddtabledata\" valign=\"top\">");
		        strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append(bundle.getString(rs.getString(2)));
		        strBuffer.append("</td>");
		        strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append(" <div class=\"checkbox\">  <input class=\"checkbox_1\" type=\"checkbox\" id=\"chk"+rs.getString(1)+"\" name=\"chk"+rs.getString(1)+"\"   value=\""+rs.getString(1)+"\"");
		        if(list.contains(rs.getInt(1))){
		          strBuffer.append("checked=\"checked\"");
		        }
		        strBuffer.append("/><label class=\"checkbox_1\"  for=\"chk"+rs.getString(1)+"\"></label>");        
		        strBuffer.append("</div>");
		        
		        strBuffer.append("</td>");
		        strBuffer.append("</tr>"); 
		        i++;
		      }
		      strBuffer.append("</table>"); 
//		      strBuffer.append("</div>");
		    }finally{
		      
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pst;
		      ArrayOfResultSet = new ResultSet[1];      
		      ArrayOfResultSet[0] = rs;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return strBuffer.toString();
		  }
	  public EntityRights insertEntityRights(Connection con,EntityRights objEntityRights)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement cst=null;    
		    EntityRights retVal = new EntityRights();
		    boolean bFlag = false;    
		    try{     
		     
		    	cst = con.prepareCall("{? = call pr_insertentityrights(?,?)}");
		    	cst.registerOutParameter(1, Types.INTEGER);
		    	cst.setInt(2, objEntityRights.getEntityId());      
		    	cst.setString(3, objEntityRights.getEntityDocuments());
		      		      
		    	cst.executeUpdate();
		      int iCount = cst.getInt(1);
		      
		      bFlag=(iCount!=0)?true:false;
		      retVal.setSuccess(bFlag);
		     
		      
		    }finally{     
		      if(cst!=null)
		    	  cst.close();
		      ArrayOfPreparedStatement = new PreparedStatement[0];      
		      ArrayOfResultSet = new ResultSet[0];
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return retVal;
		  }
	  public String getSelectedDocuments(Connection con,EntityRights rights,List<Integer> list,ResourceBundle bundle)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;    
		    ResultSet rsGetDocuments = null;   
		    PreparedStatement pstmtGetDocuments = null;
		    ResultSet rsGetSeledtedDocuments = null;   
		    PreparedStatement pstmtGetSelectedDocuments = null;
		    StringBuffer strBuffer = new StringBuffer();
		    //EntityRights entity=new EntityRights();
		    
		    try{      
		    	pstmtGetSelectedDocuments=con.prepareStatement(SQLUtil.GET_ENTITY_SUB_DOCUMENTS);
		    	pstmtGetSelectedDocuments.setInt(1, rights.getEntityId());
		    	rsGetSeledtedDocuments=pstmtGetSelectedDocuments.executeQuery();
		    	
		    	
		    	
		    	while(rsGetSeledtedDocuments.next()){
		    		list.add(rsGetSeledtedDocuments.getInt(1));		    		
		    	}
		    	
		    	pstmtGetDocuments = con.prepareStatement(SQLUtil.GET_SUB_DOCUMENTS);
		    	pstmtGetDocuments.setInt(1, rights.getDocumentId());
		    	rsGetDocuments = pstmtGetDocuments.executeQuery();
//		      strBuffer.append("<div id=\"sub_header\" style=\"position:absolute; border-style:none;margin-bottom:0;margin-top:0;height:220px; VISIBILITY:visible; OVERFLOW:auto;width:800px;\">");
		      strBuffer.append("<table class=\"table table-bordered table-condensed\">");        
		      strBuffer.append("<tr class=\"header\">");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" class=\"w-mini first\">" +bundle.getString("EntityRights.Rights") +"</th>");
		      strBuffer.append("<th align=\"center\" valign=\"middle\" class=\"w-mini last\">" +bundle.getString("EntityRights.AllowDeny") +"&nbsp;<div class=\"checkbox\"><input type=\"checkbox\"  class=\"checkbox_1\" name=\"cb_rights\" id=\"cb_rights\" onclick=\"checkUncheckRights()\" /><label class=\"checkbox_1\"  for=\"cb_rights\"></label></div></th>");
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
		        if(rsGetDocuments.getInt(3)!=2 && bundle.containsKey(rsGetDocuments.getString(2))){
		        	strBuffer.append(bundle.getString(rsGetDocuments.getString(2)));
		        }
		        else{
		        	strBuffer.append(rsGetDocuments.getString(2));
		        }
		        strBuffer.append("</td>");
		        strBuffer.append("<td class=\"dataalignment\" nowrap=\"nowrap\">");
		        strBuffer.append(" <div class=\"checkbox\"><input type=\"checkbox\" class=\"checkbox_1\"  name=\"chk"+rsGetDocuments.getString(1)+"\" id=\"chk"+rsGetDocuments.getString(1)+"\" value=\""+rsGetDocuments.getString(1)+"\"");
		        if(list.contains(rsGetDocuments.getInt(1))){
		          strBuffer.append("checked=\"checked\"");
		        }
		        strBuffer.append("/><label class=\"checkbox_1\"  for=\"chk"+rsGetDocuments.getString(1)+"\"></label>");        
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
		      ArrayOfResultSet[1] = rsGetSeledtedDocuments;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return strBuffer.toString();
		  } 
	  public EntityRights updateEntityRights(Connection con,EntityRights objEntityRights)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement csInsertEntityRights=null;    
		    EntityRights retVal = new EntityRights();
		    
		    PreparedStatement pstmtDeleteEntityRight=null;
		    PreparedStatement pstmtDeleteProfileRight=null;
		    PreparedStatement pstmtIsEntityRightExist=null;
		    PreparedStatement pstmtGetDocIds=null;    
		    ResultSet rsIsEntityRightExist = null;   
		    ResultSet rsGetDocIds = null;
		    int iCount=0;
		    
		    boolean bFlag = false;    
		    try{     
		     
		    	pstmtGetDocIds=con.prepareStatement(SQLUtil.GET_SUB_DOCUMENT_IDS);
		    	pstmtGetDocIds.setInt(1,objEntityRights.getDocumentId());
		    	rsGetDocIds=pstmtGetDocIds.executeQuery();
		    	while(rsGetDocIds.next())
		    	{
		    		pstmtIsEntityRightExist = con.prepareStatement(SQLUtil.IS_ENTITY_RIGHT_EXIST);
			    	pstmtIsEntityRightExist.setInt(1, objEntityRights.getEntityId());    
			    	pstmtIsEntityRightExist.setInt(2,rsGetDocIds.getInt(1));
			    	rsIsEntityRightExist = pstmtIsEntityRightExist.executeQuery();
			    	 while(rsIsEntityRightExist.next()){
					      if(rsIsEntityRightExist.getInt(1)>0) {
					    	  pstmtDeleteEntityRight = con.prepareStatement(SQLUtil.DELETE_ENTITY_RIGHTS);
					    	  pstmtDeleteEntityRight.setInt(1, objEntityRights.getEntityId());    
					    	  pstmtDeleteEntityRight.setInt(2,rsGetDocIds.getInt(1));
					    	  pstmtDeleteEntityRight.executeUpdate(); 
					      }
					    	  
					      }
		    	}
		    	
		    	csInsertEntityRights = con.prepareCall("{? = call pr_insertentityrights(?,?)}");
		    	csInsertEntityRights.registerOutParameter(1, Types.INTEGER);
		    	csInsertEntityRights.setInt(2, objEntityRights.getEntityId());      
		    	csInsertEntityRights.setString(3, objEntityRights.getEntityDocuments());		      		      
		    	csInsertEntityRights.executeUpdate();
		        iCount = csInsertEntityRights.getInt(1);
		        
		        csInsertEntityRights = con.prepareCall("{? = call pr_deleteprofilerights(?,?,?)}");
		    	csInsertEntityRights.registerOutParameter(1, Types.INTEGER);
		    	csInsertEntityRights.setInt(2, objEntityRights.getEntityId());      
		    	csInsertEntityRights.setString(3, objEntityRights.getEntityDocuments());	
		    	csInsertEntityRights.setInt(4, objEntityRights.getDocumentId());	
		    	csInsertEntityRights.executeUpdate();
		        iCount = csInsertEntityRights.getInt(1);
		        
		        System.out.println("objEntityRights.getEntityId() :"+objEntityRights.getEntityId());
		        System.out.println("objEntityRights.getEntityDocuments() :"+objEntityRights.getEntityDocuments());
		        System.out.println("objEntityRights.getDocumentId() :"+objEntityRights.getDocumentId());
		        
		        csInsertEntityRights = con.prepareCall("{? = call pr_deletetpcsuserrights(?,?,?)}");
		    	csInsertEntityRights.registerOutParameter(1, Types.INTEGER);
		    	csInsertEntityRights.setInt(2, objEntityRights.getEntityId());      
		    	csInsertEntityRights.setString(3, objEntityRights.getEntityDocuments());
		    	csInsertEntityRights.setInt(4, objEntityRights.getDocumentId());
		    	csInsertEntityRights.executeUpdate();
		        iCount = csInsertEntityRights.getInt(1);
		      
		      bFlag=(iCount!=0)?true:false;
		      retVal.setSuccess(bFlag);
		     
		      
		    }finally{     
		      if(csInsertEntityRights!=null)
		    	  csInsertEntityRights.close();
		      ArrayOfPreparedStatement = new PreparedStatement[4]; 
		      ArrayOfPreparedStatement[0] = pstmtGetDocIds;
		      ArrayOfPreparedStatement[1] = pstmtIsEntityRightExist;
		      ArrayOfPreparedStatement[2] = pstmtDeleteEntityRight;
		      ArrayOfPreparedStatement[3] = pstmtDeleteProfileRight;
		      ArrayOfResultSet = new ResultSet[2];		      
		      ArrayOfResultSet[0] =  rsGetDocIds;
		      ArrayOfResultSet[1] =  rsIsEntityRightExist;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return retVal;
		  }
	  public boolean updateProfileAtEntityUpdate(Connection con,int iEntityId)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pst1=null,pst2 = null;
		    boolean bFlag = false;
		    int iCount = 0;
		    try{     
		    	pst1 = con.prepareStatement(SQLUtil.DELETE_FROM_PROFILE_DETAILS);    
		    	pst1.setInt(1, iEntityId); 
		    	pst1.setInt(2, iEntityId); 
		    	pst1.executeUpdate();
		    	
		        pst2 = con.prepareStatement(SQLUtil.INSERT_INTO_PROFILE_DETAILS);    
		        pst2.setInt(1, iEntityId); 
		        iCount = pst2.executeUpdate();
		        bFlag = (iCount>0)?true:false;
		    	
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[2]; 
		      ArrayOfPreparedStatement[0] = pst1;
		      ArrayOfPreparedStatement[1] = pst2;
		      ArrayOfResultSet = new ResultSet[0];		      
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return bFlag;
		  }
	  public boolean updateUserAtEntityUpdate(Connection con,int iEntityId)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pst1=null,pst2 = null;
		    boolean bFlag = false;
		    int iCount = 0;
		    try{     
		    	pst1 = con.prepareStatement(SQLUtil.DELETE_FROM_TPCS_USER_RIGHTS);    
		    	pst1.setInt(1, iEntityId); 
		    	pst1.setInt(2, iEntityId); 
		    	pst1.executeUpdate();
		    	
		        pst2 = con.prepareStatement(SQLUtil.INSERT_INTO_TPCS_USER_RIGHTS);    
		        pst2.setInt(1, iEntityId); 
		        iCount = pst2.executeUpdate();
		        bFlag = (iCount>0)?true:false;
		    	
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[2]; 
		      ArrayOfPreparedStatement[0] = pst1;
		      ArrayOfPreparedStatement[1] = pst2;
		      ArrayOfResultSet = new ResultSet[0];		      
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return bFlag;
		  }

}
