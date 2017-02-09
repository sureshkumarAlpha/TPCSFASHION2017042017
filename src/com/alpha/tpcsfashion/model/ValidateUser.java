package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;

import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.User;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.dao.LoginDAO;
import com.alpha.tpcsfashion.dao.UserDAO;


public class ValidateUser
{

  public TPCSUser validateCredentials(TPCSUser objUser){
    Connection con=null;
    TPCSUser objLocalUser = new TPCSUser();
    try{
      con = new DatabaseConnection().getCommonConnection();
      LoginDAO objLogin = new LoginDAO();
      objLocalUser = objLogin.validateCredentials(con,objUser);
      if(objLocalUser.isUserValid()){
	      con =new DatabaseConnection().getConnection(objLocalUser);
	      objLogin.getUserCompanyAndYearDet(con,objLocalUser);
	      objLogin.setLocationConfig(con,objLocalUser);
      }
    }catch(SQLException sqe){
      sqe.printStackTrace();
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      try{
        if(con!=null)
           con.close();
      }catch(SQLException se){
        se.printStackTrace();
      }
    }
    return objLocalUser;
  }
  public User passwordreset(String strLoginName){
      Connection con=null;
      boolean bFlag = false;
      User objUser = new User();
      try{
        con = new DatabaseConnection().getCommonConnection();
        LoginDAO objLogin = new LoginDAO();
        objUser = objLogin.passwordreset(con,strLoginName);      
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
      return objUser;
    }
  
  public void mailresetPassword(User objUser){
	    Connection con=null;
	    
	    try{
	      con = new DatabaseConnection().getCommonConnection();
	      con.setAutoCommit(false);     
	      UserDAO objAdmin = new UserDAO();           
	      objAdmin.mailresetPassword(con,objUser);
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
