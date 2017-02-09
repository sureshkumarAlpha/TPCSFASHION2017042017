package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;

import com.alpha.tpcsfashion.beans.Entity;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.dao.LoginDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class LoginManager {
	
	  public void updateLoginTime(int iclientId,int iUserId){
		    Connection con=null;
		  
		    try{
		      con = new DatabaseConnection().getCommonConnection();
		      con.setAutoCommit(false); 
		     
		      LoginDAO objLogin = new LoginDAO();           
		      objLogin.updateLoginTime(con,iclientId,iUserId);
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
	  
	 
	  
	  
	  public void updateLogoutTime(int iclientId,int iUserId){
		    Connection con=null;
		  
		    try{
		      con = new DatabaseConnection().getCommonConnection();
		      con.setAutoCommit(false); 
		     
		      LoginDAO objLogin = new LoginDAO();           
		      objLogin.updateLogoutTime(con,iclientId,iUserId);
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
	  
	  public void updateUserCompanyAndYear(TPCSUser userInfo){
		    Connection con=null;
		    try{
		      con = new DatabaseConnection().getConnection(userInfo);
		      con.setAutoCommit(false); 
		      LoginDAO objLogin = new LoginDAO();           
		      objLogin.updateUserCompanyAndYear(con,userInfo);
//		      objLogin.setLocationConfig(con,userInfo);//Setting location configuration in map
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
	  public Entity getEntityDetails(int iUserId,TPCSUser ui){
		    Connection con=null;
		    Entity  objEntity=new Entity();	     
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      con.setAutoCommit(false); 
		     
		      LoginDAO objLogin = new LoginDAO();           
		      objEntity= objLogin.getEntityDetails(con,iUserId);
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
		   return objEntity;
		  }
	  public TPCSUser getUserId(TPCSUser ui) {
			
			Connection con=null;
			
			try {
				con = new DatabaseConnection().getCommonConnection();
		        con.setAutoCommit(false); 
		        LoginDAO objAdmin = new LoginDAO(); 
		        ui=objAdmin.getUserId(con,ui);
		        con.commit();			
				
			} catch (Exception e) {
				e.printStackTrace();
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
			return ui;
		}  
}
