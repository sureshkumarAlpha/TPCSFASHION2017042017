package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.TaxGroup;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.TaxGroupDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class TaxGroupManager {

	 public List<TaxGroup> getAllTax(TPCSUser ui){
			Connection con=null;
			List<TaxGroup> objList = null;
			    
			try{
				con = new DatabaseConnection().getConnection(ui);
				con.setAutoCommit(false);
				objList = objDAO.getAllTax(con,ui);
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
			return objList;
		  }
	 
	 public List<TaxGroup> getAllTaxGroup(TPCSUser ui,PageConfig pc,String strSearhQuery){
			Connection con=null; 
			List<TaxGroup> taxGroupList=null;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      con.setAutoCommit(false); 
		      taxGroupList= objDAO.getAllTaxGroup(con,ui,pc,strSearhQuery);
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
		   return taxGroupList;
		}
	public int getPageCount(TPCSUser userInfo, PageConfig pc, String searchCriteria) {
		Connection con=null; 
		int pageCount=0;
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      con.setAutoCommit(false); 
	      pageCount= objDAO.getPageCount(con,userInfo, searchCriteria);
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
	
	public int saveTaxGroup(TPCSUser ui,TaxGroup tg,String mode,int taxGropuId){
		Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      con.setAutoCommit(false); 
	      taxGropuId= objDAO.saveTaxGroup(con,ui, tg,mode,taxGropuId);
	      con.commit();
	    }catch(Exception sqe){
	    	DatabaseConnection.connectionRollBack(con);
	      sqe.printStackTrace();
	    }finally{
	    	DatabaseConnection.connectionClose(con);
	    }
	   return taxGropuId;
}
	
	public List<TaxGroup> getTaxGroupInfo(TPCSUser ui,int taxGroupId){
		Connection con=null; 
		List<TaxGroup> taxGroupInfo=null;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      con.setAutoCommit(false); 
	      taxGroupInfo= objDAO.getTaxGroupInfo(con,ui,taxGroupId);
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
	   return taxGroupInfo;
}
	public TaxGroup deleteTaxGroup(TPCSUser userInfo, TaxGroup objT) {
		Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      con.setAutoCommit(false); 
	      
	     /* boolean flag=objDAO.checkTaxGroupExistence(con,objT,userInfo);*/
		     /* if(!flag){*/
		    	  objT= objDAO.deleteTaxGroup(con,objT); 
	   /* }
		      else{
		    	  objT.setTaxGroupExists(true);
		      }*/
		      
	      con.commit();
	    }catch(Exception sqe){
	    	DatabaseConnection.connectionRollBack(con);
	      sqe.printStackTrace(); 
	    }finally{
	    	DatabaseConnection.connectionClose(con);
	    }
	   return objT;
	}
	
	public boolean deleteTaxGroupRow(TPCSUser userInfo, int taxGroupId,int taxId) {
		boolean isDeleted =false;
		Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      con.setAutoCommit(false); 
	      isDeleted= objDAO.deleteTaxGroupRow(con,taxGroupId,taxId); 
	      con.commit();
	    }
	    catch(Exception sqe){
	    	DatabaseConnection.connectionRollBack(con);
	      sqe.printStackTrace(); 
	    }
	    finally{
	    	DatabaseConnection.connectionClose(con);
	    }
	   return isDeleted;
	}
public boolean bulkActiveTaxgroup(TPCSUser userInfo, String[] taxgroup_id,String action_mode) {
		// TODO Auto-generated method stub
		boolean isBulkAction =false;
		Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      con.setAutoCommit(false); 
	      isBulkAction= objDAO.bulkActiveTaxgroup(con,taxgroup_id,action_mode); 
	      con.commit();
	    }catch(Exception sqe){
	    	DatabaseConnection.connectionRollBack(con);
	      sqe.printStackTrace(); 
	    }finally{
	    	DatabaseConnection.connectionClose(con);
	    }
	   return isBulkAction;
	}
	 
	 
	  TaxGroupDAO objDAO=new TaxGroupDAO();
}
