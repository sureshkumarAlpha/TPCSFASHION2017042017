package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.alpha.tpcsfashion.beans.Generic;
import com.alpha.tpcsfashion.beans.Profiles;
import com.alpha.tpcsfashion.beans.Role;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.User;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.UserDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class UserManager {
	public int getTotalUsers(User objUser,TPCSUser ui){
	    Connection con=null;
	    int iCount = 0;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      UserDAO objAdmin = new UserDAO();
	      iCount = objAdmin.getTotalUsers(con,objUser);    
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return iCount;
	  }
	public List<User> getUsers(User objUser,TPCSUser ui,PageConfig pc){
	    Connection con=null;
	    List<User> users = null;
	    try{
	    	 UserDAO objAdminDAO = new UserDAO(); 
	    	  con = new DatabaseConnection().getCommonConnection();
	    	  
	    	  Map<Integer,Integer> mapActive = objAdminDAO.getIsActiveList(con,ui);
	      System.out.println("mapActive  :"+mapActive.toString());
	    	  
	    	  con = new DatabaseConnection().getConnection(ui);
	            
	      users = objAdminDAO.getUsers(con,objUser,pc,mapActive);
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return users;
	  }
	public int getIsSysAdmin(TPCSUser ui){
	    Connection con=null;
	    int isSysAdmin=0;
	    try{
	      con = new DatabaseConnection().getCommonConnection();
	      UserDAO objAdmin = new UserDAO();         
	      isSysAdmin = objAdmin.getIsSysAdmin(con,ui);
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return isSysAdmin;
	  }
	public boolean checkNoOfUsers(TPCSUser ui){
	    Connection con=null;
	    boolean bFlag = false;
	    try{
	        con = new DatabaseConnection().getCommonConnection();
	      UserDAO objUser = new UserDAO();
	      con.setAutoCommit(false);
	     
	      bFlag = objUser.checkNoOfUsers(con,ui);    
	      con.commit();
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	      DatabaseConnection.connectionRollBack(con);
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return bFlag;
	  }
	public List<Generic> getLanguage(){
	    Connection con=null;
	    List<Generic> factories = null;
	    try{
	      con = new DatabaseConnection().getCommonConnection();
	      UserDAO objAdmin = new UserDAO();         
	      factories = objAdmin.getLanguage(con);
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return factories;
	  }
	public String getEntityRoles(int ientityId,int customerId,int factoryId,TPCSUser ui){
	    Connection con=null;
	    String strRoles = null;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      UserDAO objAdminDAO = new UserDAO();        
	      strRoles = objAdminDAO.getEntityRoles(con, ientityId,customerId,factoryId);
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return strRoles;
	  }
	public String getEntityProfiles(int ientityId,TPCSUser ui){
	    Connection con=null;
	    String strRoles = null;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      UserDAO objAdminDAO = new UserDAO();        
	      strRoles = objAdminDAO.getEntityProfiles(con, ientityId);
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return strRoles;
	  }
	public String getProfileDocumentList(ResourceBundle bundle,int profileId,List<Integer> list,TPCSUser ui){
	    Connection con=null;
	    String strDocument = null;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      UserDAO objAdmin = new UserDAO();         
	      strDocument = objAdmin.getProfileDocumentList(bundle,con,profileId,list);
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return strDocument;
	    
	  }
	public String getUserProfileDocumentList(ResourceBundle bundle,int profileid,int userId,List<Integer> list,TPCSUser ui){
	    Connection con=null;
	    String strDocument = null;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      UserDAO objAdmin = new UserDAO();         
	      strDocument = objAdmin.getUserProfileDocumentList(bundle,con,profileid,userId,list);
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return strDocument;
	    
	  }
	public boolean isSubscriptionExceed(int clientId){
	    Connection con=null;
	    boolean bFlag = false;
	    try{
	      con = new DatabaseConnection().getCommonConnection();
	      UserDAO objAdmin = new UserDAO();         
	      bFlag = objAdmin.isSubscriptionExceed(con,clientId);
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return bFlag;    
	  }
	public boolean isUserExist(User objUser){
	    Connection con=null;
	    boolean bFlag = false;
	    try{
	      con = new DatabaseConnection().getCommonConnection();
	      UserDAO objAdmin = new UserDAO();         
	      bFlag = objAdmin.isUserExist(con,objUser);
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return bFlag;    
	  }
	public User insertClientInfo(User objUser,int clientId){
	    Connection con=null;
	    User retVal = new User();
	    try{
	      con = new DatabaseConnection().getCommonConnection();
	      con.setAutoCommit(false);     
	      UserDAO objAdmin = new UserDAO();           
	      retVal = objAdmin.insertClientInfo(con,objUser,clientId);
	      con.commit();
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return retVal;    
	  }
	public User insertUser(User objUser,int userId,TPCSUser ui){
	    Connection con=null;
	    User retVal = new User();
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      con.setAutoCommit(false);     
	      UserDAO objAdmin = new UserDAO();           
	      retVal = objAdmin.insertUser(con,objUser,userId);
	      con.commit();
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }
	    finally{
	      try{
	        if(con!=null)
	           con.close();
	      }
	      catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return retVal;    
	  }
	public boolean deleteUser(int iUserId,TPCSUser ui) throws SQLException{
	    Connection con=null;
	    boolean bFlag = false;
	    UserDAO objUser = new UserDAO();
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      con.setAutoCommit(false);
	      bFlag = objUser.deleteUser(con,iUserId);
	      con.commit();
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	      DatabaseConnection.connectionRollBack(con);
	    }  
	    try{
	      if(bFlag){
	    	  con = new DatabaseConnection().getCommonConnection();
	    	  con.setAutoCommit(false);
	    	  bFlag =objUser.deleteInUsersInfo(con,iUserId,ui);
	    	  con.commit();
	      }
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	      DatabaseConnection.connectionRollBack(con);
	    }  
	    finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return bFlag;
	  }
	public User getUserInfo(User objUser,TPCSUser ui){
	    Connection con=null;
	    User objUserInfo = new User();
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      UserDAO objUserDAO = new UserDAO();
	      con.setAutoCommit(false);
	      objUserInfo = objUserDAO.getUserInfo(con,objUser,objUserInfo);  
	      
	      
	      con = new DatabaseConnection().getCommonConnection();
	      
	      int isSysAdmin = objUserDAO.getIsSysAdmin(con,ui);  
	      int isActive = objUserDAO.getIsActive(con,objUser);  
	      objUserInfo.setIsSystemAdmin(isSysAdmin);
	      objUserInfo.setIsActive(isActive);
	      con.commit();
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	      DatabaseConnection.connectionRollBack(con);
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return objUserInfo;
	  }
	public List<Profiles> getEntityProfileInfo(User objUser,TPCSUser ui){
	    Connection con=null;
	    List<Profiles> profiles = null;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      UserDAO objAdminDAO = new UserDAO();        
	      profiles = objAdminDAO.getEntityProfileInfo(con, objUser);
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return profiles;
	  }
	public List<Role> getEntityRolesInfo(User objUser,TPCSUser ui){
	    Connection con=null;
	    List<Role> roles = null;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      UserDAO objAdminDAO = new UserDAO();        
	      roles = objAdminDAO.getEntityRolesInfo(con, objUser);
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return roles;
	  }
	public boolean checkNoOfUsersToUpdate(TPCSUser ui,User objUser){
	    Connection con=null;
	    boolean bFlag = false;
	    try{
	        con = new DatabaseConnection().getCommonConnection();
	      UserDAO objDAO = new UserDAO();
	      con.setAutoCommit(false);
	     
	      bFlag = objDAO.checkNoOfUsersToUpdate(con,ui,objUser);    
	      con.commit();
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	      DatabaseConnection.connectionRollBack(con);
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return bFlag;
	  }
	public boolean isEntityUserExist(User objUser,int clientId){
	    Connection con=null;
	    boolean bFlag = false;
	    try{
	      con = new DatabaseConnection().getCommonConnection();
	      UserDAO objAdmin = new UserDAO();         
	      bFlag = objAdmin.isEntityUserExist(con,objUser,clientId);
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return bFlag;    
	  }
	public User updateUser(User objUser,TPCSUser ui){
	    Connection con=null;
	    User retVal = new User();
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      con.setAutoCommit(false);     
	      UserDAO objAdmin = new UserDAO();           
	      retVal = objAdmin.updateUser(con,objUser);
	      con.commit();
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return retVal;    
	  }
	public User updateClientInfo(User objUser,int clientId){
	    Connection con=null;
	    User retVal = new User();
	    try{
	      con = new DatabaseConnection().getCommonConnection();
	      con.setAutoCommit(false);     
	      UserDAO objAdmin = new UserDAO();           
	      retVal = objAdmin.updateClientInfo(con,objUser,clientId);
	      con.commit();
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return retVal;    
	  }
	public int getSubscriptionCount(int clientId){
	    Connection con=null;
	    int iCount = 0;
	    try{
	      con = new DatabaseConnection().getCommonConnection();
	      UserDAO objAdmin = new UserDAO();
	      iCount = objAdmin.getSubscriptionCount(con,clientId);    
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return iCount;
	  }
	public void mailPassword(User objUser,int loginId){
	    Connection con=null;
	    
	    try{
	      con = new DatabaseConnection().getCommonConnection();
	      con.setAutoCommit(false);     
	      UserDAO objAdmin = new UserDAO();           
	      objAdmin.mailPassword(con,objUser,loginId);
	      con.commit();
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	   
	  }
	public User changePassword(int clientId,int iUserId,String oldPassword,String newPassword,String securityQuestion,String securityAnswer,int days)
	{
	  Connection con=null;
	  boolean bFlag = false;
	  User objUserInfo = null;
	  try{
	    con = new DatabaseConnection().getCommonConnection();
	    UserDAO objAdminDAO= new UserDAO(); 
	    con.setAutoCommit(false);      
	    objUserInfo = objAdminDAO.changePassword(con,clientId,iUserId,oldPassword,newPassword,securityQuestion,securityAnswer,days);      
	    con.commit();
	  }catch(Exception sqe){
	    sqe.printStackTrace();    
	    DatabaseConnection.connectionRollBack(con);
	   
	  }finally{
	    try{
	      if(con!=null)
	         con.close();
	    }catch(SQLException se){
	      se.printStackTrace();
	    }
	  }
	  return objUserInfo;
	}
	public void mailchangePassword(User objUser){
	    Connection con=null;
	    
	    try{
	      con = new DatabaseConnection().getCommonConnection();
	      con.setAutoCommit(false);     
	      UserDAO objAdmin = new UserDAO();           
	      objAdmin.mailchangePassword(con,objUser);
	      con.commit();
	    }catch(Exception sqe){
	      sqe.printStackTrace();
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	   
	  }
}
