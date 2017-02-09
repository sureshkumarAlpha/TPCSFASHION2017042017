package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.alpha.tpcsfashion.beans.OperationOrStage;
import com.alpha.tpcsfashion.beans.Warehouse;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.Warehouse;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.GroupMasterDAO;
import com.alpha.tpcsfashion.dao.OperationOrStageDAO;
import com.alpha.tpcsfashion.dao.WarehouseMasterDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class WarehouseMasterManager {

	public int getPageCount(TPCSUser userInfo,String strSearhQuery) {
		// TODO Auto-generated method stub
		Connection con=null; 
		int pageCount=0;
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      con.setAutoCommit(false); 
	      pageCount= objDAO.getPageCount(con,userInfo,strSearhQuery);
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
	   return pageCount;
	}
	
	public List<Warehouse> getWarehouse(TPCSUser userInfo,PageConfig pc,String strSearhQuery){
		Connection con=null; 
		List<Warehouse> warehouseList=null;
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      con.setAutoCommit(false); 
	                 
	      warehouseList= objDAO.getWarehouse(con,userInfo,pc,strSearhQuery);
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
	   return warehouseList;
	}	
	
	
	public int insertWarehouse(Warehouse opObj,TPCSUser userInfo) {
		// TODO Auto-generated method stub
		 Connection con = null;
		 int stId=0;
		    try
		    {
		    	con =new DatabaseConnection().getConnection(userInfo);
				con.setAutoCommit(false);
				  
				stId =objDAO.insertWarehouse(con,opObj,userInfo); 
				con.commit();
		    }
		    catch (Exception sqe)
		    {
		    	DatabaseConnection.connectionRollBack(con);
		      sqe.printStackTrace();
		    }
		    finally
		    {
		    	DatabaseConnection.connectionClose(con);
		    }
		    return stId;
	}
	
	
	
	public Warehouse getWarehouseInfo (int iScreenId,TPCSUser userInfo,int stId){
		Connection con=null; 
		Warehouse warehouseList=null;
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      con.setAutoCommit(false); 
	      //GroupMasterDAO groupDAO=new GroupMasterDAO();
	                 
	      warehouseList= objDAO.getWarehouseInfo(con,userInfo,stId,iScreenId);
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
	   return warehouseList;
	}
	
	
	
	
	
	public Warehouse deleteWarehouse(TPCSUser userInfo, Warehouse opObj) {
		// TODO Auto-generated method stub
		boolean isDeleted =false;
		Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      con.setAutoCommit(false); 
	      opObj= objDAO.deleteWarehouse(con,opObj); 
	      con.commit();
	    }catch(Exception sqe){
	    	DatabaseConnection.connectionRollBack(con);
	      sqe.printStackTrace(); 
	    }finally{
	    	DatabaseConnection.connectionClose(con);
	    }
	   return opObj;
	}
	
	public boolean isMasterWarehouseCodeNameExist(TPCSUser userInfo,Warehouse opObj)
	{
		Connection con=null;
		   //int soId=0;
		boolean bFlag=true;
		    try{
		    	//Connection con=null;
		    	 con = new DatabaseConnection().getConnection(userInfo);
		      
		    	 con.setAutoCommit(false);      
		      
		      bFlag = objDAO.isMasterWarehouseCodeNameExist(con,opObj,userInfo);      
		    	 con.commit();
		    	 
		    }catch(Exception sqe){
		      sqe.printStackTrace();   
		      
		     // DatabaseConnection.connectionRollBack(con);
		      return bFlag;
		    }
		    finally
		    {
		      try{
		        if(con!=null)
		           con.close();
		      }catch(SQLException se){
		        se.printStackTrace();
		      }
		    }
		    return bFlag;

		  }	
	
	
	
	public boolean deActiveWarehouse(TPCSUser userInfo, int stId) {
		// TODO Auto-generated method stub
		boolean isDeActive =false;
		Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      con.setAutoCommit(false); 
	      isDeActive= objDAO.deActiveWarehouse(con,stId); 
	      con.commit();
	    }catch(Exception sqe){
	    	DatabaseConnection.connectionRollBack(con);
	      sqe.printStackTrace(); 
	    }finally{
	    	DatabaseConnection.connectionClose(con);
	    }
	   return isDeActive;
	}
	
	
	
	public boolean bulkActionWarehouse(TPCSUser userInfo, String[] stId,String action_mode) {
		// TODO Auto-generated method stub
		boolean isBulkAction =false;
		Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      con.setAutoCommit(false); 
	      isBulkAction= objDAO.bulkActionWarehouse(con,stId,action_mode); 
	      con.commit();
	    }catch(Exception sqe){
	    	DatabaseConnection.connectionRollBack(con);
	      sqe.printStackTrace(); 
	    }finally{
	    	DatabaseConnection.connectionClose(con);
	    }
	   return isBulkAction;
	}
	
	WarehouseMasterDAO objDAO = new WarehouseMasterDAO();
}
