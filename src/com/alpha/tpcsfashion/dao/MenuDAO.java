package com.alpha.tpcsfashion.dao;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alpha.tpcsfashion.beans.MenuHolder;
import com.alpha.tpcsfashion.beans.MenuItem;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;

public class MenuDAO
{

  
  public List<MenuItem> getMainMenuItems(Connection con,int iUserId)throws SQLException,Exception{
    PreparedStatement ArrayOfPreparedStatement[] = null;
    ResultSet ArrayOfResultSet[] = null;
    PreparedStatement pstmtGetMainMenu=null;
    ResultSet rsGetMainMenu = null;   
    PreparedStatement pstmt2=null;
    ResultSet rs2 = null;   
    List<MenuItem> mainmenus=new ArrayList<MenuItem>();
    try{      
      
      pstmtGetMainMenu = con.prepareStatement(SQLUtil.MAIN_MENU_ITEMS);  
      pstmtGetMainMenu.setInt(1, iUserId);
      rsGetMainMenu = pstmtGetMainMenu.executeQuery();
      while(rsGetMainMenu.next()){
    	  boolean existCount=true;

    	  if(rsGetMainMenu.getInt(1)==12){
    		  existCount=false;
    		  pstmt2 = con.prepareStatement(SQLUtil.IS_SUBDOC_EXISTS_FOR_MODULE);
    		  pstmt2.setInt(1, iUserId);
    		  pstmt2.setInt(2, rsGetMainMenu.getInt(1));
    		  rs2 = pstmt2.executeQuery();
    		  if(rs2.next()){
    			  existCount=rs2.getInt(1)>0?true:false;
    		  }
    	  }
          if(existCount){
        	   MenuItem item = new MenuItem();
               item.setMenuId(rsGetMainMenu.getInt(1));
               item.setMenuName(rsGetMainMenu.getString(2));
               item.setMenuURL(rsGetMainMenu.getString(3));
               mainmenus.add(item);
        	  
          }
     
      }
      
    }finally{     
      ArrayOfPreparedStatement = new PreparedStatement[2];
      ArrayOfPreparedStatement[0] = pstmtGetMainMenu;
      ArrayOfPreparedStatement[1] = pstmt2;
      ArrayOfResultSet = new ResultSet[2];      
      ArrayOfResultSet[0] = rsGetMainMenu;
      ArrayOfResultSet[1] = rs2;
      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
    }
    return mainmenus;
  }
  
  public List<MenuItem> getReportMainMenuItems(Connection con,int iUserId)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtGetMainMenu=null;
	    ResultSet rsGetMainMenu = null;   
	    List<MenuItem> mainmenus=new ArrayList<MenuItem>();
	    try{      
	      
	      pstmtGetMainMenu = con.prepareStatement(SQLUtil.REPORT_MAIN_MENU_ITEMS );  
	      pstmtGetMainMenu.setInt(1, iUserId);
	      rsGetMainMenu = pstmtGetMainMenu.executeQuery();
	      while(rsGetMainMenu.next()){
	        MenuItem item = new MenuItem();
	        item.setMenuId(rsGetMainMenu.getInt(1));
	        item.setMenuName(rsGetMainMenu.getString(2));
	        item.setMenuURL(rsGetMainMenu.getString(3));
	        mainmenus.add(item);
	      }
	      
	    }finally{     
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pstmtGetMainMenu;
	      ArrayOfResultSet = new ResultSet[1];      
	      ArrayOfResultSet[0] = rsGetMainMenu;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return mainmenus;
	  }
  
  
  public List<MenuItem> getSubMenuItems(Connection con,int iUserId,int iMainMenuId)throws SQLException,Exception{
    PreparedStatement ArrayOfPreparedStatement[] = null;
    ResultSet ArrayOfResultSet[] = null;
    PreparedStatement pstmtGetSubMenu=null;
    ResultSet rsGetSubMenu = null;   
    List<MenuItem> submenuitems=new ArrayList<MenuItem>();
    try{      
      
      pstmtGetSubMenu = con.prepareStatement(SQLUtil.SUB_MENU_ITEMS); 
      pstmtGetSubMenu.setInt(1, iMainMenuId);
      pstmtGetSubMenu.setInt(2, iUserId);
      rsGetSubMenu = pstmtGetSubMenu.executeQuery();
      while(rsGetSubMenu.next()){
        MenuItem item = new MenuItem();
        item.setMenuId(rsGetSubMenu.getInt(1));
        item.setMenuName(rsGetSubMenu.getString(2));
        item.setMenuURL(rsGetSubMenu.getString(3));
        submenuitems.add(item);
      }
      
    }finally{      
      ArrayOfPreparedStatement = new PreparedStatement[1];
      ArrayOfPreparedStatement[0] = pstmtGetSubMenu;
      ArrayOfResultSet = new ResultSet[1];      
      ArrayOfResultSet[0] = rsGetSubMenu;
      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
    }
    return submenuitems;
  }
  
  
  public List<MenuItem> getSubDocumentItems(Connection con,int iUserId,int iMainMenuId)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtGetSubMenu=null;
	    ResultSet rsGetSubMenu = null;   
	    List<MenuItem> submenuitems=new ArrayList<MenuItem>();
	    try{      
	      
	      pstmtGetSubMenu = con.prepareStatement(SQLUtil.SUB_DOCUMENT_ITEMS);    
	      pstmtGetSubMenu.setInt(1, iMainMenuId);
	      pstmtGetSubMenu.setInt(2, iUserId);
	      rsGetSubMenu = pstmtGetSubMenu.executeQuery();
	      while(rsGetSubMenu.next()){
	        MenuItem item = new MenuItem(); 
	        item.setMenuId(rsGetSubMenu.getInt(1));
	        item.setMenuName(rsGetSubMenu.getString(2));
	        item.setMenuURL(rsGetSubMenu.getString(3));
	        item.setMenuDocId(rsGetSubMenu.getInt(4));
	        submenuitems.add(item);
	      }
	      
	    }finally{      
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pstmtGetSubMenu;
	      ArrayOfResultSet = new ResultSet[1];      
	      ArrayOfResultSet[0] = rsGetSubMenu;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return submenuitems;
	  }


public Map<Integer, List<MenuItem>> getCreateNewItems(Connection con,
		TPCSUser userInfo) throws SQLException {
	// TODO Auto-generated method stub
    PreparedStatement pstmt=null;
    ResultSet rs = null;   
    Map<Integer, List<MenuItem>> mapModuleTosubDoc=new HashMap<Integer, List<MenuItem>>();
    List<MenuItem> newMenuList=null;
    int lastModuleId=-1;
    try{      
      
      pstmt = con.prepareStatement(SQLUtil.CREATE_NEW_ITEMS);    
      pstmt.setInt(1, userInfo.getUserId());
      rs = pstmt.executeQuery();
      
      while(rs.next()){
    	
    	  if(lastModuleId!=rs.getInt(1))
    	  {
    		  if(lastModuleId!=-1)
    		  {
    			  mapModuleTosubDoc.put(lastModuleId,newMenuList);  
    		  }
    		  
    		  newMenuList=new ArrayList<MenuItem>();
    	  }
        MenuItem item = new MenuItem();
        
        item.setMenuName(rs.getString(2));
        lastModuleId=rs.getInt(1);
        newMenuList.add(item);

      }
      mapModuleTosubDoc.put(lastModuleId,newMenuList);
    }finally{      
      DatabaseConnection.closeAll(pstmt,rs);
    }
    return mapModuleTosubDoc;
}


public Map<Integer, List<MenuItem>> getQuickLinksMenuItem(Connection con,
		TPCSUser userInfo) throws SQLException {
	// TODO Auto-generated method stub
    PreparedStatement pstmt=null;
    ResultSet rs = null;   
    Map<Integer, List<MenuItem>> mapModuleTosubDoc=new HashMap<Integer, List<MenuItem>>();
    List<MenuItem> newMenuList=null;
    int lastModuleId=-1;
    try{      
      
      pstmt = con.prepareStatement(SQLUtil.QUICK_LINKS);    
      pstmt.setInt(1, userInfo.getUserId());
      rs = pstmt.executeQuery();
      
      while(rs.next()){
    	
    	  if(lastModuleId!=rs.getInt(1))
    	  {
    		  if(lastModuleId!=-1)
    		  {
    			  mapModuleTosubDoc.put(lastModuleId,newMenuList);  
    		  }
    		  
    		  newMenuList=new ArrayList<MenuItem>();
    	  }
        MenuItem item = new MenuItem();
        
        item.setMenuName(rs.getString(2));
        lastModuleId=rs.getInt(1);
        newMenuList.add(item);

      }
      mapModuleTosubDoc.put(lastModuleId,newMenuList);
    }finally{      
      DatabaseConnection.closeAll(pstmt,rs);
    }
    return mapModuleTosubDoc;
}

public MenuHolder getMenus(Connection con,TPCSUser userInfo)throws SQLException,Exception{
    PreparedStatement pstmt=null;    
    ResultSet rs=null;
    PreparedStatement pstmt2=null;    
    ResultSet rs2=null;
    MenuHolder objHolder = new MenuHolder();
    List<MenuItem> menus = new ArrayList<MenuItem>();
    HashMap<Integer,List<MenuItem>> documents = new HashMap<Integer,List<MenuItem>>();
    HashMap<Integer,List<MenuItem>> subDocuments = new HashMap<Integer,List<MenuItem>>();
    try{      
      pstmt = con.prepareStatement(SQLUtil.GET_MAIN_MENUS);
      pstmt.setInt(1, userInfo.getUserId());
      rs = pstmt.executeQuery();
      
    
    
      while(rs.next()){
    	  boolean existCount=true;

    	  if(rs.getInt(1)==12){
    		  existCount=false;
    		  pstmt2 = con.prepareStatement(SQLUtil.IS_SUBDOC_EXISTS_FOR_MODULE);
    		  pstmt2.setInt(1, userInfo.getUserId());
    		  pstmt2.setInt(2, rs.getInt(1));
    		  rs2 = pstmt2.executeQuery();
    		  if(rs2.next()){
    			  existCount=rs2.getInt(1)>0?true:false;
    		  }
    	  }
          if(existCount){
        	 
          	  MenuItem objMenu = new MenuItem(rs.getInt(1), rs.getString(2), rs.getString(3)==null?"#":rs.getString(3), 0,"",0);
              menus.add(objMenu);
              List<MenuItem> tdocuments = getDocuments(con,rs.getInt(1),userInfo);        
              documents.put(rs.getInt(1), tdocuments);
              for(MenuItem menu:tdocuments){
                List<MenuItem> tsubDocuments = getSubDocuments(con,rs.getInt(1),menu.getMenuId(),userInfo);
                subDocuments.put(menu.getMenuId(), tsubDocuments);
              }
          }
      }
      
      objHolder.setMainMenu(menus);
      objHolder.setDocuments(documents);
      objHolder.setSubDocuments(subDocuments);
    }finally{
      DatabaseConnection.closeAll(pstmt,rs);
      DatabaseConnection.closeAll(pstmt2,rs2);
    } 
    return objHolder;
  }

public MenuHolder getReportMenus(Connection con,TPCSUser userInfo)throws SQLException,Exception{
    PreparedStatement pstmt=null;    
    ResultSet rs=null;
    MenuHolder objHolder = new MenuHolder();
    List<MenuItem> menus = new ArrayList<MenuItem>();
    HashMap<Integer,List<MenuItem>> documents = new HashMap<Integer,List<MenuItem>>();
    HashMap<Integer,List<MenuItem>> subDocuments = new HashMap<Integer,List<MenuItem>>();
    try{      
      pstmt = con.prepareStatement(SQLUtil.GET_REPORT_MAIN_MENUS);
      pstmt.setInt(1, userInfo.getUserId());
      rs = pstmt.executeQuery();
      while(rs.next()){
    	  MenuItem objMenu = new MenuItem(rs.getInt(1), rs.getString(2), rs.getString(3)==null?"#":rs.getString(3), 0,"",0);
        menus.add(objMenu);
        List<MenuItem> tdocuments = getReportDocuments(con,rs.getInt(1),userInfo);        
        documents.put(rs.getInt(1), tdocuments);
        for(MenuItem menu:tdocuments){
          List<MenuItem> tsubDocuments = getReportSubDocuments(con,rs.getInt(1),menu.getMenuId(),userInfo);
          subDocuments.put(menu.getMenuId(), tsubDocuments);
        }
      }
      
      objHolder.setMainMenu(menus);
      objHolder.setDocuments(documents);
      objHolder.setSubDocuments(subDocuments);
    }finally{
      DatabaseConnection.closeAll(pstmt,rs);
    } 
    return objHolder;
  }



public List<MenuItem> getDocuments(Connection con,int iModuleId,TPCSUser userInfo)throws SQLException,Exception{
  PreparedStatement pstmtGetDocuments=null;    
  ResultSet rsGetDocuments=null;   
  PreparedStatement pstmt2=null;    
  ResultSet rs2=null;   
  List<MenuItem> documents = new ArrayList<MenuItem>();
  try{        
      pstmtGetDocuments = con.prepareStatement(SQLUtil.GET_MAIN_DOCUMENTS);
      pstmtGetDocuments.setInt(1, userInfo.getUserId());
      pstmtGetDocuments.setInt(2, iModuleId);
      rsGetDocuments = pstmtGetDocuments.executeQuery();
      while(rsGetDocuments.next()){
    	  
    	  boolean existCount=true;

    	  if(iModuleId==12){
    		  existCount=false;
    		  pstmt2 = con.prepareStatement(SQLUtil.IS_SUBDOC_EXISTS_FOR_DOCUMENT);
    		  pstmt2.setInt(1, userInfo.getUserId());
    		  pstmt2.setInt(2, iModuleId);
    		  pstmt2.setInt(3, rsGetDocuments.getInt(1));
    		  rs2 = pstmt2.executeQuery();
    		  if(rs2.next()){
    			  existCount=rs2.getInt(1)>0?true:false;
    		  }
    	  }
          if(existCount){
        	  MenuItem objMenu = new MenuItem(rsGetDocuments.getInt(1), rsGetDocuments.getString(2), rsGetDocuments.getString(3)==null?"#":rsGetDocuments.getString(3), 0,"",0);
              documents.add(objMenu);
          }
      
      }
  
    }finally{     
    DatabaseConnection.closeAll(pstmtGetDocuments,rsGetDocuments);
    DatabaseConnection.closeAll(pstmt2,rs2);
  }
  return documents;
}

public List<MenuItem> getReportDocuments(Connection con,int iModuleId,TPCSUser userInfo)throws SQLException,Exception{
	  PreparedStatement pstmtGetDocuments=null;    
	  ResultSet rsGetDocuments=null;    
	  List<MenuItem> documents = new ArrayList<MenuItem>();
	  try{        
	      pstmtGetDocuments = con.prepareStatement(SQLUtil.GET_REPORT_DOCUMENTS);
	      pstmtGetDocuments.setInt(1, userInfo.getUserId());
	      pstmtGetDocuments.setInt(2, iModuleId);
	      rsGetDocuments = pstmtGetDocuments.executeQuery();
	      while(rsGetDocuments.next()){
	        MenuItem objMenu = new MenuItem(rsGetDocuments.getInt(1), rsGetDocuments.getString(2), rsGetDocuments.getString(3)==null?"#":rsGetDocuments.getString(3), 0,"",0);
	        documents.add(objMenu);
	      }
	  
	    }finally{     
	    DatabaseConnection.closeAll(pstmtGetDocuments,rsGetDocuments);
	  }
	  return documents;
	}

public List<MenuItem> getSubDocuments(Connection con,int iModuleId,int iDocumentId,TPCSUser userInfo)throws SQLException,Exception{
  PreparedStatement pstmtGetSubDocuments=null;    
  ResultSet rsGetSubDocuments=null;    
  List<MenuItem> subDocuments = new ArrayList<MenuItem>();
  try{           
    
	  if(iModuleId!=12&&iDocumentId!=28)//If Not New Transaction Module
	  {
		  pstmtGetSubDocuments = con.prepareStatement(SQLUtil.GET_MAIN_SUB_DOCUMENTS);
	      pstmtGetSubDocuments.setInt(1, userInfo.getUserId());
	      pstmtGetSubDocuments.setInt(2, iModuleId);
	      pstmtGetSubDocuments.setInt(3, iDocumentId);
		  
	  }
	  else{
		  pstmtGetSubDocuments = con.prepareStatement(SQLUtil.GET_NEW_TRANS_SUB_DOCUMENTS);
	      pstmtGetSubDocuments.setInt(1, userInfo.getUserId());
	      pstmtGetSubDocuments.setInt(2, iModuleId);
	      pstmtGetSubDocuments.setInt(3, iDocumentId);
	  }
     
      
      rsGetSubDocuments = pstmtGetSubDocuments.executeQuery();
      while(rsGetSubDocuments.next()){          
        MenuItem menu = new MenuItem(rsGetSubDocuments.getInt(1), rsGetSubDocuments.getString(2), rsGetSubDocuments.getString(3)==null?"#":rsGetSubDocuments.getString(3), 0,"",0);
        subDocuments.add(menu);
      }
    
    }finally{     
    DatabaseConnection.closeAll(pstmtGetSubDocuments,rsGetSubDocuments);
  }
  return subDocuments;
} 


public List<MenuItem> getReportSubDocuments(Connection con,int iModuleId,int iDocumentId,TPCSUser userInfo)throws SQLException,Exception{
  PreparedStatement pstmtGetSubDocuments=null;    
  ResultSet rsGetSubDocuments=null;    
  List<MenuItem> subDocuments = new ArrayList<MenuItem>();
  try{           
    
      pstmtGetSubDocuments = con.prepareStatement(SQLUtil.GET_REPORT_SUB_DOCUMENTS);
      pstmtGetSubDocuments.setInt(1, userInfo.getUserId());
      pstmtGetSubDocuments.setInt(2, iModuleId);
      pstmtGetSubDocuments.setInt(3, iDocumentId);
      
      rsGetSubDocuments = pstmtGetSubDocuments.executeQuery();
      while(rsGetSubDocuments.next()){          
        MenuItem menu = new MenuItem(rsGetSubDocuments.getInt(1), rsGetSubDocuments.getString(2), rsGetSubDocuments.getString(3)==null?"#":rsGetSubDocuments.getString(3), 0,rsGetSubDocuments.getString(6)!=null && !rsGetSubDocuments.getString(6).equals("null")?rsGetSubDocuments.getString(6):"",rsGetSubDocuments.getInt(7));
        subDocuments.add(menu);
      }
    
    }finally{     
    DatabaseConnection.closeAll(pstmtGetSubDocuments,rsGetSubDocuments);
  }
  return subDocuments;
} 

public Map<Integer,String> getSubdocuments(Connection con,TPCSUser userInfo)throws SQLException,Exception{
	  PreparedStatement pst=null;    
	  ResultSet rs=null;    
	  Map<Integer,String> subDocumentsMap = new LinkedHashMap<Integer,String>();
	  try{           
	    
	      pst= con.prepareStatement(SQLUtil.GET_SUBDOCUMENTS);
	      rs= pst.executeQuery();
	      while(rs.next()){          
	        subDocumentsMap.put(rs.getInt(1), rs.getString(2));
	      }
	    
	    }finally{     
	    DatabaseConnection.closeAll(pst,rs);
	  }
	  return subDocumentsMap;
	}


}
