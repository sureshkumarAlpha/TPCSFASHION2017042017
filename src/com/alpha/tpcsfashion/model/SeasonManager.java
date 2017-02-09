package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.alpha.tpcsfashion.beans.Season;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.SeasonDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;


public class SeasonManager {


	public List<Season> getSeasonList(TPCSUser userInfo,PageConfig pc,String strSearhQuery){
		Connection con=null; 
		List<Season> SeasonConfigurationList=null;
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			SeasonConfigurationList= objDAO.getSeasonList(con,userInfo,pc,strSearhQuery);
			con.commit();
		}
		catch(Exception sqe){
			DatabaseConnection.connectionRollBack(con);	
			sqe.printStackTrace();
		}
		finally{
			DatabaseConnection.connectionClose(con);
		}
		return SeasonConfigurationList;
	}
	public Season isMasterSeasonExist(TPCSUser userInfo,Season seObj){
		Connection con=null;

		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false);      
			seObj = objDAO.isMasterSeasonExist(con,seObj,userInfo);      
			con.commit();
			//objList=null;
		}
		catch(Exception sqe){
			sqe.printStackTrace();   
			return seObj;
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
		return seObj;
	}	

	public int insertSeason(Season seObj,TPCSUser userInfo) {
		// TODO Auto-generated method stub
		Connection con = null;
		int SeId=0;
		try{
			con =new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false);

			SeId =objDAO.insertSeason(con,seObj,userInfo); 
			con.commit();
			//objList=null;
		}
		catch (Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		}
		finally	{
			DatabaseConnection.connectionClose(con);
		}
		return SeId;
	}
	public Season getSeasonInfo (int iScreenId,TPCSUser userInfo,int seId){
		Connection con=null; 
		Season seasonConfigurationList=null;
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 

			seasonConfigurationList= objDAO.getSeasonInfo(con,userInfo,seId,iScreenId);
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
		return seasonConfigurationList;
	}	
	public boolean deleteSeason(TPCSUser userInfo, int seId) {
		// TODO Auto-generated method stub
		boolean isDeleted =false;
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			isDeleted= objDAO.deleteSeason(con,seId); 
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
	public int getPageCount(TPCSUser userInfo, String strSearhQuery) {
		// TODO Auto-generated method stub
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

	public boolean bulkActionSeason(TPCSUser userInfo, String[] seId,String action_mode) {
		// TODO Auto-generated method stub
		boolean isBulkAction =false;
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			isBulkAction= objDAO.bulkActionSeason(con,seId,action_mode); 
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
	public Season deleteBulkSeason(TPCSUser userInfo, Season se) {
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 

			se= objDAO.deleteBulkSeason(con,se); 

			con.commit();
		}catch(Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace(); 
		}finally{
			DatabaseConnection.connectionClose(con);
		}
		return se;
	}

	SeasonDAO objDAO=new SeasonDAO();

}
