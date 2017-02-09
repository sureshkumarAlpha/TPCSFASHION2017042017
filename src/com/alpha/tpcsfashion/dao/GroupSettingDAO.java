package com.alpha.tpcsfashion.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alpha.tpcsfashion.beans.GroupSetting;
import com.alpha.tpcsfashion.beans.RawMaterial;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;


public class GroupSettingDAO {
	
	
	public int getGroupSettingCount(Connection con,int iPageSize)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtGroupCount=null;
	    ResultSet rsGroupCount = null;
	    int iCount = 0;
	    try{ 
	    	//COMMENTED BELOW BECAUSE OF COMPILE ERROR
//	    	pstmtGroupCount = con.prepareStatement(SQLUtil.GROUPSETTING_COUNT);      
	    	rsGroupCount = pstmtGroupCount.executeQuery();
	    	rsGroupCount.next();
	      iCount = rsGroupCount.getInt(1);
	      iCount = (iCount/iPageSize)+(iCount%iPageSize>0?1:0);
	    }finally{     
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pstmtGroupCount;
	      ArrayOfResultSet = new ResultSet[1];
	      ArrayOfResultSet[0] =  rsGroupCount;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return iCount;
	  }
	
	
	public List<GroupSetting> getGroupSetting(Connection con,int iStart,int iPageSize)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    CallableStatement cstmtGetGroups=null;
	    ResultSet rsGetGroups = null;   
	    List<GroupSetting> groupsetting=new ArrayList<GroupSetting>();
	    try{      
	    	cstmtGetGroups = con.prepareCall("{call pr_groupsettingpaging (?,?)}");
	    	cstmtGetGroups.setInt(1,iStart);
	    	cstmtGetGroups.setInt(2,iPageSize);
	    	rsGetGroups = cstmtGetGroups.executeQuery();
	      while(rsGetGroups.next()){
	    	  GroupSetting objGroupsetting = new GroupSetting();
	    	  objGroupsetting.setGroupId(rsGetGroups.getInt(1));
	    	  objGroupsetting.setGroupName(rsGetGroups.getString(2));
	    	
	    	
	    	  objGroupsetting.setIsTrackApplicable(rsGetGroups.getInt(3));
	    	  objGroupsetting.setIsBarCodeApplicable(rsGetGroups.getInt(4));
	    	  groupsetting.add(objGroupsetting);
	      }
	    }finally{
	      if(cstmtGetGroups!=null)
	    	  cstmtGetGroups.close();
	    
	      ArrayOfPreparedStatement = new PreparedStatement[0];
	      ArrayOfResultSet = new ResultSet[1];      
	      ArrayOfResultSet[0] = rsGetGroups;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return groupsetting;
	  }
	
	
	
	public boolean deleteGroupSetting(Connection con,int iGroupId)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtDeleteMaterial=null;
	    int iCount = 0;
	    boolean bFlag = false;
	    try{     
	    	//COMMENTED BELOW BECAUSE OF COMPILE ERROR
//	      pstmtDeleteMaterial = con.prepareStatement(SQLUtil.DELETE_GROUPSETTING);    
	      pstmtDeleteMaterial.setInt(1, iGroupId); 
	      iCount = pstmtDeleteMaterial.executeUpdate();
	      bFlag = (iCount>0)?true:false;      
	    }finally{     
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pstmtDeleteMaterial;
	      ArrayOfResultSet = new ResultSet[0];
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return bFlag;
	  }
	
	
	public GroupSetting getGroupsettingInfo(Connection con,int iGroupId)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtGetGroupInfo=null;    
	    ResultSet rsGetGroupInfo=null;
	    GroupSetting objGroupSetting = null;
	    try{
	    	//COMMENTED BELOW BECAUSE OF COMPILE ERROR
//	    	pstmtGetGroupInfo = con.prepareStatement(SQLUtil.GET_GROUPSETTING_INFO);
	    	pstmtGetGroupInfo.setInt(1, iGroupId);
	    	rsGetGroupInfo = pstmtGetGroupInfo.executeQuery();
	      while(rsGetGroupInfo.next()){
	    	  objGroupSetting = new GroupSetting();
	    	  
	    	  objGroupSetting.setGroupId(rsGetGroupInfo.getInt(1));
	    	  objGroupSetting.setGroupName(rsGetGroupInfo.getString(2));
	    	  objGroupSetting.setIsTrackApplicable(rsGetGroupInfo.getInt(3));
	    	  objGroupSetting.setIsBarCodeApplicable(rsGetGroupInfo.getInt(4));
	    	 
	    	
	    	  
	      }
	    }finally{     
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pstmtGetGroupInfo;
	      ArrayOfResultSet = new ResultSet[1];
	      ArrayOfResultSet[0] = rsGetGroupInfo;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return objGroupSetting;
	  }
	
	
	
	
	public boolean isItemGroupSettingExist(Connection con,GroupSetting objGroupSetting)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtIsGroupExist=null;    
	    ResultSet rsIsGroupExist = null;    
	    boolean bFlag = false;
	    try{
	    	//COMMENTED BELOW BECAUSE OF COMPILE ERROR
//	    	pstmtIsGroupExist = con.prepareStatement(SQLUtil.GET_GROUPSETTING_EXIST);     
	    	pstmtIsGroupExist.setInt(1, objGroupSetting.getGroupId());
	    	rsIsGroupExist = pstmtIsGroupExist.executeQuery();
	      while(rsIsGroupExist.next()){
	      bFlag = (rsIsGroupExist.getInt(1)>0)?true:false;      
	      }
	    }finally{     
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pstmtIsGroupExist;
	      ArrayOfResultSet = new ResultSet[1];
	      ArrayOfResultSet[0] = rsIsGroupExist;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return bFlag;
	  }
	
	
	
	public boolean insertGroupSetting(Connection con,GroupSetting groupsetting,int iLocationId)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    PreparedStatement psInsertOrigin=null;
	    ResultSet ArrayOfResultSet[] = null; 
	    boolean bFlag = false;    
	    try{
	    	//COMMENTED BELOW BECAUSE OF COMPILE ERROR
//	    	psInsertOrigin=con.prepareStatement(SQLUtil.GET_GROUPSETTING_INSERT);
	    	psInsertOrigin.setInt(1,groupsetting.getCompanyId());   
	    	psInsertOrigin.setInt(2,iLocationId);  

	    	psInsertOrigin.setInt(3,groupsetting.getGroupId());   
	    	psInsertOrigin.setInt(4,groupsetting.getIsTrackApplicable());
		    
	    	psInsertOrigin.setInt(5,groupsetting.getIsBarCodeApplicable());
	    	
    	     
	    int iCount =psInsertOrigin.executeUpdate();
	    	bFlag=(iCount>0)?true:false;
	    }finally{       
	      ArrayOfPreparedStatement = new PreparedStatement[1];   
	      ArrayOfPreparedStatement[0] = psInsertOrigin;
	      ArrayOfResultSet = new ResultSet[0];
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return bFlag;
	  }
	

	public boolean updateGroupSetting(Connection con,GroupSetting groupsetting1,GroupSetting groupsetting )throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtupdateSpecificationSetting=null;  
	   
	  
	    boolean bFlag = false;    
	    
	 try{ 
		//COMMENTED BELOW BECAUSE OF COMPILE ERROR
//	    	pstmtupdateSpecificationSetting = con.prepareStatement(SQLUtil.GET_GROUPSETTING_UPDATE);
	    
	    	
	    	pstmtupdateSpecificationSetting.setInt(1,groupsetting.getGroupId());
	    	pstmtupdateSpecificationSetting.setInt(2,groupsetting.getIsTrackApplicable());
	    	pstmtupdateSpecificationSetting.setInt(3,groupsetting.getIsBarCodeApplicable());

	    	pstmtupdateSpecificationSetting.setInt(4,groupsetting1.getGroupId());
	    	pstmtupdateSpecificationSetting.setInt(5,groupsetting1.getIsTrackApplicable());
	    	pstmtupdateSpecificationSetting.setInt(6,groupsetting1.getIsBarCodeApplicable());
	    	


	       		    	int iCount = pstmtupdateSpecificationSetting.executeUpdate();
	    		    	
		    	bFlag=(iCount>0)?true:false;
	    }finally{
		      ArrayOfPreparedStatement = new PreparedStatement[1];
            ArrayOfPreparedStatement[0] = pstmtupdateSpecificationSetting;
			      ArrayOfResultSet = new ResultSet[0];
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
			    }
			    return bFlag;
			  }
	 
	public int CheckGroupSetting(Connection con,GroupSetting objGroupSetting)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtIsGroupExist=null;    
	    ResultSet rsIsGroupExist = null;    
	    boolean bFlag = false;
	    int checkGroup=0;
	    try{
	    	//COMMENTED BELOW BECAUSE OF COMPILE ERROR
//	    	pstmtIsGroupExist = con.prepareStatement(SQLUtil.GET_GROUPSETTING_CHECK);     
	    	pstmtIsGroupExist.setInt(1, objGroupSetting.getGroupId());
	    	rsIsGroupExist = pstmtIsGroupExist.executeQuery();
	      while(rsIsGroupExist.next()){
	      checkGroup = (rsIsGroupExist.getInt(1));      
	      }
	    }finally{     
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pstmtIsGroupExist;
	      ArrayOfResultSet = new ResultSet[1];
	      ArrayOfResultSet[0] = rsIsGroupExist;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return checkGroup;
	  }
	
		

	
	public String getGroupSettingForPrint(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		  List<GroupSetting> groupsetting=new ArrayList<GroupSetting>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer strBuffer=new StringBuffer();
		RawMaterial objRawMaterial=null;
		try{
			//COMMENTED BELOW BECAUSE OF COMPILE ERROR
//		   StringBuffer query = new StringBuffer(SQLUtil.GET_ALL_GROUP_SETTING_PRINT);
//		   pstmt=con.prepareCall(query.toString());
		   rs=pstmt.executeQuery();
		      while(rs.next()){
		    	  GroupSetting objGroupsetting = new GroupSetting();
		    	  objGroupsetting.setGroupId(rs.getInt(1));
		    	  objGroupsetting.setGroupName(rs.getString(2));
		    	  objGroupsetting.setIsTrackApplicable(rs.getInt(3));
		    	  objGroupsetting.setIsBarCodeApplicable(rs.getInt(4));
		    	  groupsetting.add(objGroupsetting);
		      }
		   strBuffer.append("<table class=\"rowline\" cellspacing=\"1\" cellpadding=\"4\" width=\"100%\" style=\"border-top:4px solid #193678;border-right:1px solid #193678;border-bottom:1px solid #193678;\" >");                                
		   strBuffer.append("<tr>");
	    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Group Name</th>");
	    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Itrack Applicability</th>");
	    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Barcode Applicability</th>");
	    	  strBuffer.append("</tr>");
	    	  
	    	  
	    	  for(int i=0;i<groupsetting.size();i++)
			    {
			       strBuffer.append("<tr>");  
				   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+groupsetting.get(i).getGroupName()+"</td>");
				   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+groupsetting.get(i).getIsTrackApplicable()+"</td>");
				   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+groupsetting.get(i).getIsBarCodeApplicable()+"</td>");
			       strBuffer.append("</tr>");
			    }
	    	     strBuffer.append("</table>");
		   
		}
		finally {
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return strBuffer.toString();
	}
	
	
}