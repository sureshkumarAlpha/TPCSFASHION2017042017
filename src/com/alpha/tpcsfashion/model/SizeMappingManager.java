package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.alpha.tpcsfashion.beans.ApprovedPriceList;
import com.alpha.tpcsfashion.beans.SizeMapping;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.ApprovedPriceListDAO;
import com.alpha.tpcsfashion.dao.SizeMappingDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class SizeMappingManager {

	public List<Map<String,String>> getAllSizeMappingListOnColumnMapping (PageConfig pc, TPCSUser userInfo,String strSearchCriteria,List<String> orderBy)
	{ 
		
	  Connection con = null;
	  List<Map<String,String>> orders = null;
	  try
	  { 
	    con =new DatabaseConnection().getConnection(userInfo);
	    orders = objDAO.getAllSizeMappingListOnColumnMapping(con, pc,userInfo,strSearchCriteria,orderBy);
	  }
	  catch (Exception sqe) 
	  {
	    sqe.printStackTrace();
	  }
	  finally
	  {
			DatabaseConnection.connectionClose(con);
	  }
	  return orders;
	}
	public int getPageCount(TPCSUser userInfo, PageConfig pc, String searchCriteria) {
		// TODO Auto-generated method stub
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
	    	DatabaseConnection.connectionClose(con);
	    }
	   return pageCount;
	}
	public SizeMapping getSizeGrid(TPCSUser ui,SizeMapping sizemapping,String sizemode) throws SQLException{
		Connection con=null;
//		 StringBuffer buffer=new StringBuffer();
		 SizeMapping strGrid=null;
		try {
			con = new DatabaseConnection().getConnection(ui);
			con.setAutoCommit(false);
			
			
			List<String> sizeDisplayList=objDAO.getSizeNameForSizeRangeId(con,sizemapping,sizemode);
			
			 strGrid=objDAO.writeSizeInputGrid(con,ui,sizemapping,sizeDisplayList,sizemode);
//			buffer.append(strGrid.getBuffer());
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		}finally{
		      try{
			        if(con!=null)
			           con.close();
			      }catch(SQLException se){
			        se.printStackTrace();
			      }
			    }
		return strGrid;	
	}
	
	
	public SizeMapping saveSizeMapping(TPCSUser userinfo,SizeMapping objheadersize,String sizeInput) {
		// TODO Auto-generated method stub
		Connection con = null;
		int purPriceid=0;
		try
		{
			con =new DatabaseConnection().getConnection(userinfo);
			con.setAutoCommit(false);
			boolean isExists=false;
			boolean isMatExists=false;
							
			objheadersize =objDAO.saveSizeMapping(con,userinfo,objheadersize,sizeInput); 
			
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
		return objheadersize;
	}	
	
	public SizeMapping getSizeMappingheat(int sizeschedId,TPCSUser userInfo) {
		// TODO Auto-generated method stub
		SizeMapping head=null;
		 Connection con = null;
		    try 
		    {
		      con =new DatabaseConnection().getConnection(userInfo);
				con.setAutoCommit(false); 
				head = objDAO.getSizeMappingheat(con,userInfo,sizeschedId);
				con.commit();
		    }
		    catch (Exception sqe){
		      sqe.printStackTrace();
		    }
		    finally{
		    	DatabaseConnection.connectionClose(con);
		    }
		
		return head;
	}	
	public SizeMapping getSizeMappingDetailsList(int sizeschedId,TPCSUser userInfo) {
		// TODO Auto-generated method stub
		SizeMapping det=null;
		 Connection con = null;
		    try 
		    {
		      con =new DatabaseConnection().getConnection(userInfo);
				con.setAutoCommit(false); 
				det = objDAO.getSizeMappingDetailsList(con,userInfo,sizeschedId);
				con.commit();
		    }
		    catch (Exception sqe){
		      sqe.printStackTrace();
		    }
		    finally{
		    	DatabaseConnection.connectionClose(con);
		    }
		
		return det;
	}
	
	public SizeMapping getEditSizeMappingDetailsList(SizeMapping appmode,TPCSUser userInfo) {
		// TODO Auto-generated method stub
		SizeMapping det=null;
		 Connection con = null;
		    try 
		    {
		      con =new DatabaseConnection().getConnection(userInfo);
				con.setAutoCommit(false); 
				det = objDAO.getEditSizeMappingDetailsList(con,userInfo,appmode);
				con.commit();
		    }
		    catch (Exception sqe){
		      sqe.printStackTrace();
		    }
		    finally{
		    	DatabaseConnection.connectionClose(con);
		    }
		
		return det;
	}
	
	public boolean deleteSizeMapping(TPCSUser userInfo, int sizeSchedId) {
		Connection con = null;
		boolean isDeleted = false;
		try {
			con = new DatabaseConnection().getConnection(userInfo);
			
			con.setAutoCommit(false);
			isDeleted = objDAO.deleteSizeMapping(con, sizeSchedId,userInfo); 
			con.commit();
		} catch (Exception sqe) {
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		} finally {
			DatabaseConnection.connectionClose(con);
		}
		return isDeleted;
	}
	public int deleteSizeMappingDetail(TPCSUser userInfo, int sizeSchedId,int sizeSchedDetId) {
		int iCount=0;
		
		 Connection con = null;
		    try
		    {
		      con =new DatabaseConnection().getConnection(userInfo);
		      con.setAutoCommit(false); 
		      iCount = objDAO.deleteSizeMappingDetail(con,userInfo,sizeSchedId,sizeSchedDetId);
		      con.commit();
		    }
		    catch (Exception sqe){
		    	DatabaseConnection.connectionRollBack(con);
		      sqe.printStackTrace();
		    }
		    finally{
		    	DatabaseConnection.connectionClose(con);
		    }
		
		return iCount;

	}
	private SizeMappingDAO objDAO=new SizeMappingDAO();	
}
