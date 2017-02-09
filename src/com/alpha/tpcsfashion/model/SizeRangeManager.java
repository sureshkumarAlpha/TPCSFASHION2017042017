package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
//import java.sql.SQLException;
import java.util.List;

import com.alpha.tpcsfashion.beans.SizeRange;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.SizeRangeDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class SizeRangeManager {
	public List<SizeRange> getAllSizeRange(TPCSUser userInfo,PageConfig pc,String strSearhQuery){
		Connection con=null; 
		List<SizeRange> sizeRangeList=null;
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			sizeRangeList= objDAO.getAllSizeRange(con,userInfo,pc,strSearhQuery);
			con.commit();
		}
		catch(Exception sqe){
			DatabaseConnection.connectionRollBack(con);	
			sqe.printStackTrace();
		}
		finally{
			DatabaseConnection.connectionClose(con);
		}
		return sizeRangeList;
	}	

	public SizeRange getSizeRangeInfo(TPCSUser userInfo,SizeRange sr) {
		// TODO Auto-generated method stub
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			sr= objDAO.getSizeRangeInfo(con,userInfo,sr);
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
		return sr;
	}	 
	
	public SizeRange saveSizeMaster(SizeRange objsi,TPCSUser ui) {
		Connection con=null;
		
		try {
			con = new DatabaseConnection().getConnection(ui);
			 con.setAutoCommit(false); 
			 objsi= objDAO.saveSizeMaster(con,objsi,ui);
			 con.commit();
		} catch (Exception e) {
			DatabaseConnection.connectionRollBack(con);
			e.printStackTrace();
		}
		finally {
			DatabaseConnection.connectionClose(con);
		    }
		return objsi;
	}
	
	
	
	
	public int saveSizeRange(TPCSUser ui,SizeRange si,String mode,int sizeRangeId){
		Connection con=null;

		try{
			con = new DatabaseConnection().getConnection(ui);
			con.setAutoCommit(false); 
			sizeRangeId= objDAO.saveSizeRange(con,ui, si,mode, sizeRangeId);
			con.commit();
		}catch(Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		}finally{
			DatabaseConnection.connectionClose(con);
		}
		return sizeRangeId;
	}	 
	public SizeRange isMasterColorExist(TPCSUser userInfo,SizeRange si){
		Connection con=null;
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false);      
			si = objDAO.isMasterColorExist(con,si,userInfo);      
			con.commit();
		}
		catch(Exception sqe){
			sqe.printStackTrace();   
			return si;
		}
		finally	{

			try{
				if(con!=null)
					con.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
		}
		return si;
	}	

	public boolean deleteSize(TPCSUser userInfo, int sizeRangeId) {
		// TODO Auto-generated method stub
		boolean isDeleted =false;
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			isDeleted= objDAO.deleteSize(con,sizeRangeId); 
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

	public int getPageCount(TPCSUser userInfo,String strSearhQuery) {
		// TODO Auto-generated method stub
		Connection con=null; 
		int pageCount=0;
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			pageCount= objDAO.getPageCount(con,userInfo,strSearhQuery);
			con.commit();
		}
		catch(Exception sqe){
			sqe.printStackTrace();
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
		return pageCount;
	}
	public SizeRange deleteSizeRange(TPCSUser userInfo, SizeRange sr) {
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 

			sr= objDAO.deleteSizeRange(con,sr); 

			con.commit();
		}catch(Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace(); 
		}finally{
			DatabaseConnection.connectionClose(con);
		}
		return sr;
	}
	public boolean bulkActionSizeRange(TPCSUser userInfo, String[] sizerangeId,String action_mode) {
		// TODO Auto-generated method stub
		boolean isBulkAction =false;
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			isBulkAction= objDAO.bulkActionSizeRange(con,sizerangeId,action_mode); 
			con.commit();
		}
		catch(Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace(); 
		}
		finally{
			DatabaseConnection.connectionClose(con);
		}
		return isBulkAction;
	}
	SizeRangeDAO objDAO = new SizeRangeDAO();
}

