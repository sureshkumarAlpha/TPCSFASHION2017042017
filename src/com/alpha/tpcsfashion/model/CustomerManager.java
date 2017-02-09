package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.alpha.tpcsfashion.beans.Currency;
import com.alpha.tpcsfashion.beans.Customer;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.GroupMaster;
import com.alpha.tpcsfashion.beans.MaterialMaster;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.CustomerDAO;
import com.alpha.tpcsfashion.dao.GroupMasterDAO;
import com.alpha.tpcsfashion.dao.MaterialMasterDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class CustomerManager {
	
	public List<Map<String,String>> getAllCustomerOnColumnMapping (PageConfig pc, TPCSUser userInfo,String strSearchCriteria,List<String> orderBy)
	  {  
	    Connection con = null;
	    List<Map<String,String>> orders = null;
	    try
	    { 
	      con =new DatabaseConnection().getConnection(userInfo);
	     // orders = objDAO.getAllCustomerOnColumnMapping(con, pc,userInfo,strSearchCriteria,orderBy);
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
	    return orders;
	  }
	
	
	public Currency saveCurrency(Currency objC,TPCSUser ui) {
		Connection con=null;
		
		try {
			
			con = new DatabaseConnection().getConnection(ui);
			con.setAutoCommit(false);
			 objC= objDAO.saveCurrency(con,objC,ui);
			con.commit();
		} catch (Exception e) {
			DatabaseConnection.connectionRollBack(con);
			e.printStackTrace();
		}
		finally {
			DatabaseConnection.connectionClose(con);
		    }
		return objC;
	}
	
	public int getPageCount(TPCSUser userInfo, PageConfig pc, String searchCriteria) {
		// TODO Auto-generated method stub
		Connection con=null; 
		int pageCount=0;
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      con.setAutoCommit(false); 
	     // pageCount= objDAO.getPageCount(con,userInfo, searchCriteria);
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
	
	public int saveCustomer(TPCSUser ui,Customer objC){
		Connection con=null; 
		int custId=0;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      con.setAutoCommit(false);
	      boolean flag=objDAO.checkExistence(con,ui,objC);
	      System.out.println("flag :"+flag);
	      if(!flag){
	    	  custId= objDAO.saveCustomer(con,ui, objC);
	      }
	      else{
	    	  custId=-1;//For exists.
	      }
	      con.commit();
	    }catch(Exception sqe){
	    	DatabaseConnection.connectionRollBack(con);
	      sqe.printStackTrace();
	    }finally{
	    	DatabaseConnection.connectionClose(con);
	    }
	   return custId;
}
	
	public Customer getCustomerInfo(TPCSUser ui,int custId){
		Connection con=null; 
		Customer custInfo=null;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      con.setAutoCommit(false); 
	      custInfo= objDAO.getCustomerInfo(con,ui,custId);
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
	   return custInfo;
}
	public Customer deleteCustomer(Customer objC,TPCSUser ui) {
		// TODO Auto-generated method stub
		Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      con.setAutoCommit(false); 
	      
	      boolean flag=objDAO.checkCustomerExistence(con,objC,ui);
			 System.out.println("CustomerExistence flag :"+flag);
		      if(!flag){
		    	  objC= objDAO.deleteCustomer(con,objC); 
		      }
		      else{
		    	  objC.setCustExists(true);
		      }
		      
	      con.commit();
	    }catch(Exception sqe){
	    	DatabaseConnection.connectionRollBack(con);
	      sqe.printStackTrace(); 
	    }finally{
	    	DatabaseConnection.connectionClose(con);
	    }
	   return objC;
	}
	 public String getCurrency(TPCSUser ui) {
			Connection con=null;
			String str="";
			try {
				con = new DatabaseConnection().getConnection(ui);
				str= objDAO.getCurrency(con);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return str;
		}
	 
	 public Customer saveCustomer(Customer objC,TPCSUser ui) throws SQLException {
			Connection con=null;
			
			try {
				con = new DatabaseConnection().getConnection(ui);
				con.setAutoCommit(false);
				
				
				 boolean flag=objDAO.checkExistence(con,ui,objC);
			      System.out.println("flag :"+flag);
			      if(!flag){
			    	  objC= objDAO.saveCustomer(con,objC,ui);
			      }
			      else{
			    	  objC.setInserted(false);
			      }
			      con.commit();
				
			} catch (Exception e) {
				DatabaseConnection.connectionRollBack(con);
				e.printStackTrace();
			}
			finally{
				DatabaseConnection.connectionClose(con);
			}
			return objC;
		}
	 public boolean getAccountBillTracking(TPCSUser ui,int groupId) {
			Connection con=null;
			boolean billExist=false;
			try {
				con = new DatabaseConnection().getConnection(ui);
				billExist= objDAO.getAccountBillTracking(con,ui,groupId);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return billExist;
		}
	  CustomerDAO objDAO=new CustomerDAO();
}
