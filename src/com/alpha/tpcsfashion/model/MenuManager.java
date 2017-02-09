package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.alpha.tpcsfashion.beans.MenuHolder;
import com.alpha.tpcsfashion.beans.MenuItem;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.dao.MenuDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class MenuManager
{

  
	
  public List<MenuItem> getMainMenuItems(TPCSUser ui){
    Connection con=null;
    List<MenuItem> mainmenus = null;
    
    try{
      con = new DatabaseConnection().getConnection(ui);
      mainmenus = objMenuDAO.getMainMenuItems(con, ui.getUserId());
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
    return mainmenus;
  }
  
  public List<MenuItem> getReportMainMenuItems(TPCSUser ui){
	    Connection con=null;
	    List<MenuItem> mainmenus = null;
	    
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      mainmenus = objMenuDAO.getReportMainMenuItems(con, ui.getUserId());
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
	    return mainmenus;
	  }
  
  
  public List<MenuItem> getSubMenuItems(int iUserId, int iMainMenuType,TPCSUser ui){
    Connection con=null;
    List<MenuItem> submenus = null;
    try{
      con = new DatabaseConnection().getConnection(ui);
      submenus = objMenuDAO.getSubMenuItems(con, iUserId,iMainMenuType);
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
    return submenus;
  }

  public List<MenuItem> getSubMenuItems(int mainMenu,TPCSUser ui){
	    
	    return getSubMenuItems(ui.getUserId(), mainMenu, ui);
	  }
  
  public List<MenuItem> getSubDocumentItems(int iUserId,int iMainMenuType,TPCSUser ui){
	    Connection con=null;
	    List<MenuItem> subdocuments= null;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      subdocuments = objMenuDAO.getSubDocumentItems(con,iUserId,iMainMenuType);
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
	    return subdocuments;
	  }
  
  public List<MenuItem> getSubDocumentItems(int mainMenu,TPCSUser ui){

	    return getSubDocumentItems(ui.getUserId(), mainMenu, ui);
	  }




public MenuHolder getMenus(TPCSUser userInfo){
    Connection con = null;
    MenuHolder objHolder = null;
    try{
      con = new DatabaseConnection().getConnection(userInfo);
      objHolder = objMenuDAO.getMenus(con,userInfo);      
      con.commit();
    }catch(SQLException sqe){
      sqe.printStackTrace();    
      DatabaseConnection.connectionRollBack(con);     
      return null;
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
    return objHolder;
  }
public MenuHolder getReportMenus(TPCSUser userInfo){
    Connection con = null;
    MenuHolder objHolder = null;
    try{
      con = new DatabaseConnection().getConnection(userInfo);
      objHolder = objMenuDAO.getReportMenus(con,userInfo);      
      con.commit();
    }catch(SQLException sqe){
      sqe.printStackTrace();    
      DatabaseConnection.connectionRollBack(con);     
      return null;
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
    return objHolder;
  }
public Map<Integer,String> getSubdocuments(TPCSUser userInfo){
    Connection con = null;
    Map<Integer,String> objHolder = null;
    try{
      con = new DatabaseConnection().getConnection(userInfo);
      objHolder = objMenuDAO.getSubdocuments(con,userInfo);      
      con.commit();
    }catch(SQLException sqe){
      sqe.printStackTrace();    
      DatabaseConnection.connectionRollBack(con);     
      return null;
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
    return objHolder;
  }

private MenuDAO objMenuDAO = new MenuDAO();      
}
