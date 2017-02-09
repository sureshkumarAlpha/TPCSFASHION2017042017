package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.dao.ColumnPreferenceDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class ColumnPreferenceManager
{

  public Map<Integer, String> getAllColumns(int iDocumentId,TPCSUser userInfo){
    Connection con=null;
    Map<Integer, String> columns =null;
    try{
      con = new DatabaseConnection().getConnection(userInfo);
      ColumnPreferenceDAO objColumn = new ColumnPreferenceDAO();
      con.setAutoCommit(false);      
      columns = objColumn.getAllColumns(con,iDocumentId,userInfo);      
      con.commit();
    }catch(Exception sqe){ 
      sqe.printStackTrace();    
      //DatabaseConnection.connectionRollBack(con);      
    }finally{
      try{
        if(con!=null)
           con.close();
      }catch(SQLException se){
        se.printStackTrace();
      }
    }
    return columns;
  }
  
 
  public ColumnPreference getVisibleColumnNames(int iDocumentId, TPCSUser userInfo){
	    Connection con=null;
	    ColumnPreference visibleColumns =null;
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      ColumnPreferenceDAO objColumn = new ColumnPreferenceDAO();
	      con.setAutoCommit(false);      
	      visibleColumns = objColumn.getVisibleColumnNames(con,iDocumentId,userInfo);      
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
	    return visibleColumns;
	  }
  
  public Map<String,String> getVisibleColumnNamesMap(int iDocumentId, int iUserId,TPCSUser userInfo){
	    Connection con=null;
	    Map<String,String> visComMap=new LinkedHashMap<String,String>();
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      ColumnPreferenceDAO objColumn = new ColumnPreferenceDAO();
	      con.setAutoCommit(false);      
	      visComMap = objColumn.getVisibleColumnNamesMap(con,iDocumentId,iUserId);      
	      con.commit();
	    }catch(Exception sqe){
	      sqe.printStackTrace();    
	      //DatabaseConnection.connectionRollBack(con);      
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return visComMap;
	  }
  
  public boolean updateColumnPreferences(String strTotalColumns,int iDocumentId, TPCSUser userInfo){
    Connection con=null; 
    boolean bFlag = false;
    try{
      con = new DatabaseConnection().getConnection(userInfo);
      ColumnPreferenceDAO colPrefDAO = new ColumnPreferenceDAO();
      con.setAutoCommit(false);      
      bFlag = colPrefDAO.updateColumnPreferences(con,strTotalColumns,iDocumentId,userInfo.getUserId());       
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
  
  public ColumnPreference updateColumnPreferences(ColumnPreference cp,int iDocumentId, TPCSUser userInfo,int type) throws SQLException{
	    Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      ColumnPreferenceDAO colPrefDAO = new ColumnPreferenceDAO();
	      con.setAutoCommit(false);      
	      cp = colPrefDAO.updateColumnPreferences(con,cp,iDocumentId,userInfo.getUserId(),type);       
	      con.commit();
	    }catch(Exception sqe){ 
	      sqe.printStackTrace();
	      con.rollback();
	      DatabaseConnection.connectionRollBack(con);      
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    } 
	    return cp;
	  }
  
  public ColumnPreference saveReportName(ColumnPreference cp,int iDocumentId, TPCSUser userInfo){
	    Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      ColumnPreferenceDAO colPrefDAO = new ColumnPreferenceDAO();
	      con.setAutoCommit(false);      
	      cp = colPrefDAO.saveReportName(con,cp,iDocumentId,userInfo);       
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
	    return cp;
	  }

  public ColumnPreference deleteReport(int iDocumentId, TPCSUser userInfo){
	    Connection con=null;
	    ColumnPreference cp=new ColumnPreference();
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      ColumnPreferenceDAO colPrefDAO = new ColumnPreferenceDAO();
	      con.setAutoCommit(false);      
	      cp = colPrefDAO.deleteReport(con,iDocumentId);       
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
	    return cp;
	  }
  
public List<Integer> getVisibleColumns(int documentId, TPCSUser userInfo) {
	// TODO Auto-generated method stub
	  Connection con=null; 
	    List<Integer> visibleColumns =null;
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      ColumnPreferenceDAO objColumn = new ColumnPreferenceDAO();
	      con.setAutoCommit(false);      
	      visibleColumns = objColumn.getVisibleColumns(con,documentId,userInfo);      
	      con.commit();  
	    }catch(Exception sqe){
	      sqe.printStackTrace();    
	      //DatabaseConnection.connectionRollBack(con);      
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace(); 
	      }
	    }
	    return visibleColumns;
} 

public ColumnPreference getDataForReportCustomize(int documentId, TPCSUser userInfo) {
	// TODO Auto-generated method stub
	  Connection con=null; 
	  ColumnPreference colPre=new ColumnPreference();
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      ColumnPreferenceDAO objColumn = new ColumnPreferenceDAO();
	      con.setAutoCommit(false);      
	      colPre= objColumn.getDataForReportCustomize(con,documentId,userInfo);      
	      con.commit();  
	    }catch(Exception sqe){
	      sqe.printStackTrace();    
	      //DatabaseConnection.connectionRollBack(con);      
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace(); 
	      }
	    }
	    return colPre;
} 
  
}
