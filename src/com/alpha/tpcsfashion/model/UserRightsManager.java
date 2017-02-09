package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;

import com.alpha.tpcsfashion.beans.Common;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.dao.UserRightsDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class UserRightsManager
{

  /*public UserRights getUserRights(int iSubDocumentId,int iUserId, Common objCommon)
  {
    Connection con = null;
    UserRights objRights = null;
    try
    {
      con =
          new DatabaseConnection().getConnection(objCommon.getDatasource(),
                                                 objCommon.getDatabase(), objCommon.getServerIp(),
                                                 objCommon.getUserName(), objCommon.getPassword());
      UserRightsDAO objRightDAO = new UserRightsDAO();
      objRights = objRightDAO.getUserRights(con, iSubDocumentId,iUserId);
    }
    catch (Exception sqe)
    {
      sqe.printStackTrace();
    }
    finally
    {
      try
      {
        if (con != null)
          con.close();
      }
      catch (SQLException se)
      {
        se.printStackTrace();
      }
    }
    return objRights;
  }*/
   
  
  public UserRights getUserRights(int iSubDocumentId,TPCSUser userInfo)
  {
    Connection con = null;
    UserRights objRights = null;
    try
    {
      con =
          new DatabaseConnection().getConnection(userInfo);
      UserRightsDAO objRightDAO = new UserRightsDAO();
      objRights = objRightDAO.getUserRights(con, iSubDocumentId,userInfo);
    }
    catch (Exception sqe)
    {
      sqe.printStackTrace();
    }
    finally
    {
      try
      {
        if (con != null)
          con.close();
      }
      catch (SQLException se)
      {
        se.printStackTrace();
      }
    }
    return objRights;
  }
  
}
