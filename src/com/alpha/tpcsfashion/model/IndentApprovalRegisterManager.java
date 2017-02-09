package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.IndentApprovalRegisterDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class IndentApprovalRegisterManager {
	public List<Map<String,String>> getAllIndentApprovalRegisterOnColumnMapping (PageConfig pc, TPCSUser userInfo,String strSearchCriteria,ColumnPreference DataList,int subdocumentId) throws SQLException
	  {
	    Connection con = null;
	    List<Map<String,String>> orders = null;
	    try
	    { 
	      con =new DatabaseConnection().getConnection(userInfo);
	      con.setAutoCommit(false);
	      orders = objDAO.getAllIndentApprovalRegisterOnColumnMapping(con, pc,userInfo,strSearchCriteria,DataList,subdocumentId);
	      con.commit();
	    }
	    catch (Exception sqe) 
	    {
	    	con.rollback();
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
	
	
	public ColumnPreference getTotalPages(TPCSUser userInfo,String strSearchCriteria,ColumnPreference DataList ,int subdocumentId) throws SQLException {
		Connection con = null;
		ColumnPreference cp=new ColumnPreference();
		try {
			 con =new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			cp= objDAO.getTotalPages(con, userInfo,strSearchCriteria, DataList,subdocumentId);
			con.commit(); 
		} catch (Exception sqe) {
			con.rollback();
			sqe.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return cp;
	}
	
	IndentApprovalRegisterDAO objDAO=new IndentApprovalRegisterDAO();
}