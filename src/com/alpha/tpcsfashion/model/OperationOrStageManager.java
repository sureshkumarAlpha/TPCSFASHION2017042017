package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.alpha.tpcsfashion.beans.OperationOrStage;
import com.alpha.tpcsfashion.beans.Season;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.GroupMasterDAO;
import com.alpha.tpcsfashion.dao.OperationOrStageDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class OperationOrStageManager {

	public List<OperationOrStage> getOperationOrStage(TPCSUser userInfo,PageConfig pc,String strSearhQuery){
		Connection con=null; 
		List<OperationOrStage> OperationOrStageList=null;
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 

			OperationOrStageList= objDAO.getOperationOrStage(con,userInfo,pc,strSearhQuery);
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
		return OperationOrStageList;
	}	
	public int insertOperationOrStage(OperationOrStage opObj,TPCSUser userInfo) {
		// TODO Auto-generated method stub
		Connection con = null;
		int OpId=0;
		try
		{
			con =new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false);

			OpId =objDAO.insertOperationOrStage(con,opObj,userInfo); 
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
		return OpId;
	}
	public boolean isMasterOperationOrStageExist(TPCSUser userInfo,OperationOrStage opObj)
	{
		Connection con=null;
		//int soId=0;
		boolean bFlag=true;
		try{
			//Connection con=null;
			con = new DatabaseConnection().getConnection(userInfo);

			con.setAutoCommit(false);      

			bFlag = objDAO.isMasterOperationOrStageExist(con,opObj,userInfo);      
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
	public OperationOrStage getOperationOrStageInfo (int iScreenId,TPCSUser userInfo,int opid){
		Connection con=null; 
		OperationOrStage OperationOrStageList=null;
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			GroupMasterDAO groupDAO=new GroupMasterDAO();

			OperationOrStageList= objDAO.getOperationOrStageInfo(con,userInfo,opid,iScreenId);
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
		return OperationOrStageList;
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
	public boolean deleteOperationOrStageMaster(TPCSUser userInfo, int opId) {
		// TODO Auto-generated method stub
		boolean isDeleted =false;
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			isDeleted= objDAO.deleteOperationOrStageMaster(con,opId); 
			con.commit();
		}catch(Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace(); 
		}finally{
			DatabaseConnection.connectionClose(con);
		}
		return isDeleted;
	}
	public boolean deActiveOperationOrStage(TPCSUser userInfo, int opId) {
		// TODO Auto-generated method stub
		boolean isDeActive =false;
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			isDeActive= objDAO.deActiveOperationOrStage(con,opId); 
			con.commit();
		}catch(Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace(); 
		}finally{
			DatabaseConnection.connectionClose(con);
		}
		return isDeActive;
	}

	public boolean bulkActionOperationOrStage(TPCSUser userInfo, String[] opId,String action_mode) {
		// TODO Auto-generated method stub
		boolean isBulkAction =false;
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			isBulkAction= objDAO.bulkActionOperationOrStage(con,opId,action_mode); 
			con.commit();
		}catch(Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace(); 
		}finally{
			DatabaseConnection.connectionClose(con);
		}
		return isBulkAction;
	}
	public OperationOrStage deleteBulkOperation(TPCSUser userInfo, OperationOrStage se) {
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 

			se= objDAO.deleteBulkOperation(con,se); 

			con.commit();
		}catch(Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace(); 
		}finally{
			DatabaseConnection.connectionClose(con);
		}
		return se;
	}


	OperationOrStageDAO objDAO = new OperationOrStageDAO();
}
