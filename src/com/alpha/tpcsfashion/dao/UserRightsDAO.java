package com.alpha.tpcsfashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;

public class UserRightsDAO
{

  public UserRights getUserRights(Connection con, int iSubDocumenyId,TPCSUser userinfo)throws SQLException,Exception{
    PreparedStatement ArrayOfPreparedStatement[] = null;
    ResultSet ArrayOfResultSet[] = null;
    PreparedStatement pstmtGetUserRights=null;    
    ResultSet rsGetUserRights=null;
    UserRights objRights = null;
    try{ 

    	   objRights = new UserRights();
           objRights.setAddPermission(0);
           objRights.setEditPermission(0);
           objRights.setDeletePermission(0);
           objRights.setAmendmentPermission(0);
           objRights.setViewPermission(0);
           objRights.setExcelPermission(0);
           objRights.setPrintPermission(0);
           objRights.setApprovalPermission(0);  
           
      
      pstmtGetUserRights = con.prepareStatement(SQLUtil.GET_USER_RIGHTS);
   //  System.out.println("iUserId :"+iUserId);
    // System.out.println("iSubDocumenyId :"+iSubDocumenyId);
      pstmtGetUserRights.setInt(1,userinfo.getUserId());   
      pstmtGetUserRights.setInt(2, iSubDocumenyId);      
      
      rsGetUserRights = pstmtGetUserRights.executeQuery();
      while(rsGetUserRights.next()){       
        objRights = new UserRights();
        objRights.setAddPermission(rsGetUserRights.getInt(1));
        objRights.setEditPermission(rsGetUserRights.getInt(2));
        objRights.setDeletePermission(rsGetUserRights.getInt(3));
        objRights.setAmendmentPermission(rsGetUserRights.getInt(4));
        objRights.setViewPermission(rsGetUserRights.getInt(5));
        objRights.setExcelPermission(rsGetUserRights.getInt(6));
        objRights.setPrintPermission(rsGetUserRights.getInt(7));
        objRights.setApprovalPermission(rsGetUserRights.getInt(8));              
      }
      
      
    }finally{
      ArrayOfPreparedStatement = new PreparedStatement[1];
      ArrayOfPreparedStatement[0] = pstmtGetUserRights;
      ArrayOfResultSet = new ResultSet[1];
      ArrayOfResultSet[0] = rsGetUserRights;
      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
    }
    return objRights;
  }
  
}
